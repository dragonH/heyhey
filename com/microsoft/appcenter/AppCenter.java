package com.microsoft.appcenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.channel.DefaultChannel;
import com.microsoft.appcenter.ingestion.models.CustomPropertiesLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.StartServiceLog;
import com.microsoft.appcenter.ingestion.models.WrapperSdk;
import com.microsoft.appcenter.ingestion.models.json.CustomPropertiesLogFactory;
import com.microsoft.appcenter.ingestion.models.json.DefaultLogSerializer;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.json.StartServiceLogFactory;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.IdHelper;
import com.microsoft.appcenter.utils.ShutdownHelper;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AppCenter {
  @VisibleForTesting
  static final String CORE_GROUP = "group_core";
  
  public static final String LOG_TAG = "AppCenter";
  
  private static final int SHUTDOWN_TIMEOUT = 5000;
  
  @SuppressLint({"StaticFieldLeak"})
  private static AppCenter sInstance;
  
  private AppCenterHandler mAppCenterHandler;
  
  private String mAppSecret;
  
  private Application mApplication;
  
  private Channel mChannel;
  
  private Handler mHandler;
  
  private HandlerThread mHandlerThread;
  
  private boolean mLogLevelConfigured;
  
  private LogSerializer mLogSerializer;
  
  private String mLogUrl;
  
  private Set<AppCenterService> mServices;
  
  private UncaughtExceptionHandler mUncaughtExceptionHandler;
  
  private boolean checkPrecondition() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial isInstanceConfigured : ()Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq -> 15
    //   11: aload_0
    //   12: monitorexit
    //   13: iconst_1
    //   14: ireturn
    //   15: ldc 'AppCenter'
    //   17: ldc 'App Center hasn't been configured. You need to call AppCenter.start with appSecret or AppCenter.configure first.'
    //   19: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: iconst_0
    //   25: ireturn
    //   26: astore_2
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_2
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
    //   15	22	26	finally
  }
  
  public static void configure(Application paramApplication, String paramString) {
    getInstance().instanceConfigure(paramApplication, paramString);
  }
  
  @SafeVarargs
  private final void configureAndStartServices(Application paramApplication, String paramString, Class<? extends AppCenterService>... paramVarArgs) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokespecial instanceConfigure : (Landroid/app/Application;Ljava/lang/String;)Z
    //   8: ifeq -> 16
    //   11: aload_0
    //   12: aload_3
    //   13: invokespecial startServices : ([Ljava/lang/Class;)V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  @WorkerThread
  private void finishConfiguration() {
    Constants.loadFromContext((Context)this.mApplication);
    StorageHelper.initialize((Context)this.mApplication);
    boolean bool = isInstanceEnabled();
    UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler();
    this.mUncaughtExceptionHandler = uncaughtExceptionHandler;
    if (bool)
      uncaughtExceptionHandler.register(); 
    DefaultLogSerializer defaultLogSerializer = new DefaultLogSerializer();
    this.mLogSerializer = (LogSerializer)defaultLogSerializer;
    defaultLogSerializer.addLogFactory("startService", (LogFactory)new StartServiceLogFactory());
    this.mLogSerializer.addLogFactory("customProperties", (LogFactory)new CustomPropertiesLogFactory());
    DefaultChannel defaultChannel = new DefaultChannel((Context)this.mApplication, this.mAppSecret, this.mLogSerializer, this.mHandler);
    this.mChannel = (Channel)defaultChannel;
    defaultChannel.setEnabled(bool);
    this.mChannel.addGroup("group_core", 50, 3000L, 3, null);
    String str = this.mLogUrl;
    if (str != null)
      this.mChannel.setLogUrl(str); 
    AppCenterLog.debug("AppCenter", "App Center storage initialized.");
  }
  
  @WorkerThread
  private void finishStartServices(Iterable<AppCenterService> paramIterable) {
    ArrayList<String> arrayList = new ArrayList();
    for (AppCenterService appCenterService : paramIterable) {
      Map<String, LogFactory> map = appCenterService.getLogFactories();
      if (map != null)
        for (Map.Entry<String, LogFactory> entry : map.entrySet())
          this.mLogSerializer.addLogFactory((String)entry.getKey(), (LogFactory)entry.getValue());  
      appCenterService.onStarted((Context)this.mApplication, this.mAppSecret, this.mChannel);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(appCenterService.getClass().getSimpleName());
      stringBuilder.append(" service started.");
      AppCenterLog.info("AppCenter", stringBuilder.toString());
      arrayList.add(appCenterService.getServiceName());
    } 
    if (isInstanceEnabled()) {
      StartServiceLog startServiceLog = new StartServiceLog();
      startServiceLog.setServices(arrayList);
      this.mChannel.enqueue((Log)startServiceLog, "group_core");
    } 
  }
  
  public static AppCenterFuture<UUID> getInstallId() {
    return getInstance().getInstanceInstallId();
  }
  
  static AppCenter getInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/AppCenter
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/AppCenter.sInstance : Lcom/microsoft/appcenter/AppCenter;
    //   6: ifnonnull -> 21
    //   9: new com/microsoft/appcenter/AppCenter
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/microsoft/appcenter/AppCenter.sInstance : Lcom/microsoft/appcenter/AppCenter;
    //   21: getstatic com/microsoft/appcenter/AppCenter.sInstance : Lcom/microsoft/appcenter/AppCenter;
    //   24: astore_0
    //   25: ldc com/microsoft/appcenter/AppCenter
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/microsoft/appcenter/AppCenter
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  private AppCenterFuture<UUID> getInstanceInstallId() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: invokespecial checkPrecondition : ()Z
    //   14: ifeq -> 56
    //   17: aload_0
    //   18: getfield mAppCenterHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   21: astore_2
    //   22: new com/microsoft/appcenter/AppCenter$9
    //   25: astore_3
    //   26: aload_3
    //   27: aload_0
    //   28: aload_1
    //   29: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   32: new com/microsoft/appcenter/AppCenter$10
    //   35: astore #4
    //   37: aload #4
    //   39: aload_0
    //   40: aload_1
    //   41: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   44: aload_2
    //   45: aload_3
    //   46: aload #4
    //   48: invokeinterface post : (Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   53: goto -> 61
    //   56: aload_1
    //   57: aconst_null
    //   58: invokevirtual complete : (Ljava/lang/Object;)V
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: areturn
    //   65: astore_3
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_3
    //   69: athrow
    // Exception table:
    //   from	to	target	type
    //   2	53	65	finally
    //   56	61	65	finally
  }
  
  @IntRange(from = 2L, to = 8L)
  public static int getLogLevel() {
    return AppCenterLog.getLogLevel();
  }
  
  public static String getSdkVersion() {
    return "1.0.0";
  }
  
  private void handlerAppCenterOperation(Runnable paramRunnable1, Runnable paramRunnable2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial checkPrecondition : ()Z
    //   6: ifeq -> 48
    //   9: new com/microsoft/appcenter/AppCenter$4
    //   12: astore_3
    //   13: aload_3
    //   14: aload_0
    //   15: aload_1
    //   16: aload_2
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   20: invokestatic currentThread : ()Ljava/lang/Thread;
    //   23: aload_0
    //   24: getfield mHandlerThread : Landroid/os/HandlerThread;
    //   27: if_acmpne -> 39
    //   30: aload_1
    //   31: invokeinterface run : ()V
    //   36: goto -> 48
    //   39: aload_0
    //   40: getfield mHandler : Landroid/os/Handler;
    //   43: aload_3
    //   44: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   47: pop
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	51	finally
    //   39	48	51	finally
  }
  
  @SuppressLint({"VisibleForTests"})
  private boolean instanceConfigure(Application paramApplication, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull -> 22
    //   6: ldc 'AppCenter'
    //   8: ldc_w 'application may not be null'
    //   11: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: iconst_0
    //   17: ireturn
    //   18: astore_1
    //   19: goto -> 200
    //   22: aload_2
    //   23: ifnull -> 188
    //   26: aload_2
    //   27: invokevirtual isEmpty : ()Z
    //   30: ifeq -> 36
    //   33: goto -> 188
    //   36: aload_0
    //   37: getfield mHandler : Landroid/os/Handler;
    //   40: ifnull -> 55
    //   43: ldc 'AppCenter'
    //   45: ldc_w 'App Center may only be configured once.'
    //   48: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   51: aload_0
    //   52: monitorexit
    //   53: iconst_0
    //   54: ireturn
    //   55: aload_0
    //   56: getfield mLogLevelConfigured : Z
    //   59: ifne -> 79
    //   62: aload_1
    //   63: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   66: getfield flags : I
    //   69: iconst_2
    //   70: iand
    //   71: iconst_2
    //   72: if_icmpne -> 79
    //   75: iconst_5
    //   76: invokestatic setLogLevel : (I)V
    //   79: aload_0
    //   80: aload_1
    //   81: putfield mApplication : Landroid/app/Application;
    //   84: aload_0
    //   85: aload_2
    //   86: putfield mAppSecret : Ljava/lang/String;
    //   89: new android/os/HandlerThread
    //   92: astore_1
    //   93: aload_1
    //   94: ldc_w 'AppCenter.Looper'
    //   97: invokespecial <init> : (Ljava/lang/String;)V
    //   100: aload_0
    //   101: aload_1
    //   102: putfield mHandlerThread : Landroid/os/HandlerThread;
    //   105: aload_1
    //   106: invokevirtual start : ()V
    //   109: new android/os/Handler
    //   112: astore_1
    //   113: aload_1
    //   114: aload_0
    //   115: getfield mHandlerThread : Landroid/os/HandlerThread;
    //   118: invokevirtual getLooper : ()Landroid/os/Looper;
    //   121: invokespecial <init> : (Landroid/os/Looper;)V
    //   124: aload_0
    //   125: aload_1
    //   126: putfield mHandler : Landroid/os/Handler;
    //   129: new com/microsoft/appcenter/AppCenter$2
    //   132: astore_1
    //   133: aload_1
    //   134: aload_0
    //   135: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;)V
    //   138: aload_0
    //   139: aload_1
    //   140: putfield mAppCenterHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   143: new java/util/HashSet
    //   146: astore_1
    //   147: aload_1
    //   148: invokespecial <init> : ()V
    //   151: aload_0
    //   152: aload_1
    //   153: putfield mServices : Ljava/util/Set;
    //   156: aload_0
    //   157: getfield mHandler : Landroid/os/Handler;
    //   160: astore_1
    //   161: new com/microsoft/appcenter/AppCenter$3
    //   164: astore_2
    //   165: aload_2
    //   166: aload_0
    //   167: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;)V
    //   170: aload_1
    //   171: aload_2
    //   172: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   175: pop
    //   176: ldc 'AppCenter'
    //   178: ldc_w 'App Center SDK configured successfully.'
    //   181: invokestatic logAssert : (Ljava/lang/String;Ljava/lang/String;)V
    //   184: aload_0
    //   185: monitorexit
    //   186: iconst_1
    //   187: ireturn
    //   188: ldc 'AppCenter'
    //   190: ldc_w 'appSecret may not be null or empty'
    //   193: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   196: aload_0
    //   197: monitorexit
    //   198: iconst_0
    //   199: ireturn
    //   200: aload_0
    //   201: monitorexit
    //   202: aload_1
    //   203: athrow
    // Exception table:
    //   from	to	target	type
    //   6	14	18	finally
    //   26	33	18	finally
    //   36	51	18	finally
    //   55	79	18	finally
    //   79	184	18	finally
    //   188	196	18	finally
  }
  
  public static boolean isConfigured() {
    return getInstance().isInstanceConfigured();
  }
  
  public static AppCenterFuture<Boolean> isEnabled() {
    return getInstance().isInstanceEnabledAsync();
  }
  
  private boolean isInstanceConfigured() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mApplication : Landroid/app/Application;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 16
    //   11: iconst_1
    //   12: istore_2
    //   13: goto -> 18
    //   16: iconst_0
    //   17: istore_2
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_2
    //   21: ireturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  private boolean isInstanceEnabled() {
    return StorageHelper.PreferencesStorage.getBoolean("enabled", true);
  }
  
  private AppCenterFuture<Boolean> isInstanceEnabledAsync() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: invokespecial checkPrecondition : ()Z
    //   14: ifeq -> 56
    //   17: aload_0
    //   18: getfield mAppCenterHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   21: astore_2
    //   22: new com/microsoft/appcenter/AppCenter$6
    //   25: astore_3
    //   26: aload_3
    //   27: aload_0
    //   28: aload_1
    //   29: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   32: new com/microsoft/appcenter/AppCenter$7
    //   35: astore #4
    //   37: aload #4
    //   39: aload_0
    //   40: aload_1
    //   41: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   44: aload_2
    //   45: aload_3
    //   46: aload #4
    //   48: invokeinterface post : (Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   53: goto -> 63
    //   56: aload_1
    //   57: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   60: invokevirtual complete : (Ljava/lang/Object;)V
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_1
    //   66: areturn
    //   67: astore #4
    //   69: aload_0
    //   70: monitorexit
    //   71: aload #4
    //   73: athrow
    // Exception table:
    //   from	to	target	type
    //   2	53	67	finally
    //   56	63	67	finally
  }
  
  @WorkerThread
  private void queueCustomProperties(@NonNull Map<String, Object> paramMap) {
    CustomPropertiesLog customPropertiesLog = new CustomPropertiesLog();
    customPropertiesLog.setProperties(paramMap);
    this.mChannel.enqueue((Log)customPropertiesLog, "group_core");
  }
  
  public static void setCustomProperties(CustomProperties paramCustomProperties) {
    getInstance().setInstanceCustomProperties(paramCustomProperties);
  }
  
  public static AppCenterFuture<Void> setEnabled(boolean paramBoolean) {
    return getInstance().setInstanceEnabledAsync(paramBoolean);
  }
  
  private void setInstanceCustomProperties(CustomProperties paramCustomProperties) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull -> 17
    //   6: ldc 'AppCenter'
    //   8: ldc_w 'Custom properties may not be null.'
    //   11: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_1
    //   18: invokevirtual getProperties : ()Ljava/util/Map;
    //   21: astore_1
    //   22: aload_1
    //   23: invokeinterface size : ()I
    //   28: ifne -> 42
    //   31: ldc 'AppCenter'
    //   33: ldc_w 'Custom properties may not be empty.'
    //   36: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: new com/microsoft/appcenter/AppCenter$1
    //   45: astore_2
    //   46: aload_2
    //   47: aload_0
    //   48: aload_1
    //   49: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Ljava/util/Map;)V
    //   52: aload_0
    //   53: aload_2
    //   54: aconst_null
    //   55: invokespecial handlerAppCenterOperation : (Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Exception table:
    //   from	to	target	type
    //   6	14	61	finally
    //   17	39	61	finally
    //   42	58	61	finally
  }
  
  @WorkerThread
  private void setInstanceEnabled(boolean paramBoolean) {
    boolean bool1;
    boolean bool2;
    this.mChannel.setEnabled(paramBoolean);
    boolean bool = isInstanceEnabled();
    if (bool && !paramBoolean) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (!bool && paramBoolean) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool2) {
      this.mUncaughtExceptionHandler.register();
    } else if (bool1) {
      this.mUncaughtExceptionHandler.unregister();
    } 
    String str = "enabled";
    if (paramBoolean)
      StorageHelper.PreferencesStorage.putBoolean("enabled", true); 
    for (AppCenterService appCenterService : this.mServices) {
      if (appCenterService.isInstanceEnabled() != paramBoolean)
        appCenterService.setInstanceEnabled(paramBoolean); 
    } 
    if (!paramBoolean)
      StorageHelper.PreferencesStorage.putBoolean("enabled", false); 
    if (bool1) {
      AppCenterLog.info("AppCenter", "App Center has been disabled.");
    } else if (bool2) {
      AppCenterLog.info("AppCenter", "App Center has been enabled.");
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("App Center has already been ");
      if (!paramBoolean)
        str = "disabled"; 
      stringBuilder.append(str);
      stringBuilder.append(".");
      AppCenterLog.info("AppCenter", stringBuilder.toString());
    } 
  }
  
  private AppCenterFuture<Void> setInstanceEnabledAsync(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: invokespecial checkPrecondition : ()Z
    //   14: ifeq -> 45
    //   17: aload_0
    //   18: getfield mHandler : Landroid/os/Handler;
    //   21: astore_3
    //   22: new com/microsoft/appcenter/AppCenter$8
    //   25: astore #4
    //   27: aload #4
    //   29: aload_0
    //   30: iload_1
    //   31: aload_2
    //   32: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;ZLcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   35: aload_3
    //   36: aload #4
    //   38: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   41: pop
    //   42: goto -> 50
    //   45: aload_2
    //   46: aconst_null
    //   47: invokevirtual complete : (Ljava/lang/Object;)V
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_2
    //   53: areturn
    //   54: astore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_2
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   2	42	54	finally
    //   45	50	54	finally
  }
  
  private void setInstanceLogLevel(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield mLogLevelConfigured : Z
    //   7: iload_1
    //   8: invokestatic setLogLevel : (I)V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }
  
  private void setInstanceLogUrl(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mLogUrl : Ljava/lang/String;
    //   7: aload_0
    //   8: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   11: astore_2
    //   12: aload_2
    //   13: ifnull -> 23
    //   16: aload_2
    //   17: aload_1
    //   18: invokeinterface setLogUrl : (Ljava/lang/String;)V
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	26	finally
    //   16	23	26	finally
  }
  
  private void setInstanceWrapperSdk(WrapperSdk paramWrapperSdk) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic setWrapperSdk : (Lcom/microsoft/appcenter/ingestion/models/WrapperSdk;)V
    //   6: aload_0
    //   7: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull -> 21
    //   15: aload_1
    //   16: invokeinterface invalidateDeviceCache : ()V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	24	finally
    //   15	21	24	finally
  }
  
  public static void setLogLevel(@IntRange(from = 2L, to = 8L) int paramInt) {
    getInstance().setInstanceLogLevel(paramInt);
  }
  
  public static void setLogUrl(String paramString) {
    getInstance().setInstanceLogUrl(paramString);
  }
  
  public static void setWrapperSdk(WrapperSdk paramWrapperSdk) {
    getInstance().setInstanceWrapperSdk(paramWrapperSdk);
  }
  
  @SafeVarargs
  public static void start(Application paramApplication, String paramString, Class<? extends AppCenterService>... paramVarArgs) {
    getInstance().configureAndStartServices(paramApplication, paramString, paramVarArgs);
  }
  
  @SafeVarargs
  public static void start(Class<? extends AppCenterService>... paramVarArgs) {
    getInstance().startServices(paramVarArgs);
  }
  
  @SafeVarargs
  private final void startServices(Class<? extends AppCenterService>... paramVarArgs) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull -> 17
    //   6: ldc 'AppCenter'
    //   8: ldc_w 'Cannot start services, services array is null. Failed to start services.'
    //   11: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: getfield mApplication : Landroid/app/Application;
    //   21: astore_2
    //   22: iconst_0
    //   23: istore_3
    //   24: aload_2
    //   25: ifnonnull -> 139
    //   28: ldc_w ''
    //   31: astore_2
    //   32: aload_1
    //   33: arraylength
    //   34: istore #4
    //   36: iload_3
    //   37: iload #4
    //   39: if_icmpge -> 105
    //   42: aload_1
    //   43: iload_3
    //   44: aaload
    //   45: astore #5
    //   47: new java/lang/StringBuilder
    //   50: astore #6
    //   52: aload #6
    //   54: invokespecial <init> : ()V
    //   57: aload #6
    //   59: aload_2
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload #6
    //   66: ldc_w '\\t'
    //   69: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload #6
    //   75: aload #5
    //   77: invokevirtual getName : ()Ljava/lang/String;
    //   80: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload #6
    //   86: ldc_w '\\n'
    //   89: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload #6
    //   95: invokevirtual toString : ()Ljava/lang/String;
    //   98: astore_2
    //   99: iinc #3, 1
    //   102: goto -> 36
    //   105: new java/lang/StringBuilder
    //   108: astore_1
    //   109: aload_1
    //   110: invokespecial <init> : ()V
    //   113: aload_1
    //   114: ldc_w 'Cannot start services, App Center has not been configured. Failed to start the following services:\\n'
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: aload_1
    //   122: aload_2
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: ldc 'AppCenter'
    //   129: aload_1
    //   130: invokevirtual toString : ()Ljava/lang/String;
    //   133: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   136: aload_0
    //   137: monitorexit
    //   138: return
    //   139: new java/util/ArrayList
    //   142: astore_2
    //   143: aload_2
    //   144: invokespecial <init> : ()V
    //   147: aload_1
    //   148: arraylength
    //   149: istore #4
    //   151: iconst_0
    //   152: istore_3
    //   153: iload_3
    //   154: iload #4
    //   156: if_icmpge -> 365
    //   159: aload_1
    //   160: iload_3
    //   161: aaload
    //   162: astore #5
    //   164: aload #5
    //   166: ifnonnull -> 180
    //   169: ldc 'AppCenter'
    //   171: ldc_w 'Skipping null service, please check your varargs/array does not contain any null reference.'
    //   174: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   177: goto -> 359
    //   180: aload #5
    //   182: ldc_w 'getInstance'
    //   185: iconst_0
    //   186: anewarray java/lang/Class
    //   189: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   192: aconst_null
    //   193: iconst_0
    //   194: anewarray java/lang/Object
    //   197: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   200: checkcast com/microsoft/appcenter/AppCenterService
    //   203: astore #6
    //   205: aload_0
    //   206: getfield mServices : Ljava/util/Set;
    //   209: aload #6
    //   211: invokeinterface contains : (Ljava/lang/Object;)Z
    //   216: ifeq -> 262
    //   219: new java/lang/StringBuilder
    //   222: astore #6
    //   224: aload #6
    //   226: invokespecial <init> : ()V
    //   229: aload #6
    //   231: ldc_w 'App Center has already started the service with class name: '
    //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: pop
    //   238: aload #6
    //   240: aload #5
    //   242: invokevirtual getName : ()Ljava/lang/String;
    //   245: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: pop
    //   249: ldc 'AppCenter'
    //   251: aload #6
    //   253: invokevirtual toString : ()Ljava/lang/String;
    //   256: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   259: goto -> 359
    //   262: aload #6
    //   264: aload_0
    //   265: getfield mAppCenterHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   268: invokeinterface onStarting : (Lcom/microsoft/appcenter/AppCenterHandler;)V
    //   273: aload_0
    //   274: getfield mApplication : Landroid/app/Application;
    //   277: aload #6
    //   279: invokevirtual registerActivityLifecycleCallbacks : (Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   282: aload_0
    //   283: getfield mServices : Ljava/util/Set;
    //   286: aload #6
    //   288: invokeinterface add : (Ljava/lang/Object;)Z
    //   293: pop
    //   294: aload_2
    //   295: aload #6
    //   297: invokeinterface add : (Ljava/lang/Object;)Z
    //   302: pop
    //   303: goto -> 359
    //   306: astore #6
    //   308: new java/lang/StringBuilder
    //   311: astore #7
    //   313: aload #7
    //   315: invokespecial <init> : ()V
    //   318: aload #7
    //   320: ldc_w 'Failed to get service instance ''
    //   323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: pop
    //   327: aload #7
    //   329: aload #5
    //   331: invokevirtual getName : ()Ljava/lang/String;
    //   334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: pop
    //   338: aload #7
    //   340: ldc_w '', skipping it.'
    //   343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: ldc 'AppCenter'
    //   349: aload #7
    //   351: invokevirtual toString : ()Ljava/lang/String;
    //   354: aload #6
    //   356: invokestatic error : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   359: iinc #3, 1
    //   362: goto -> 153
    //   365: aload_2
    //   366: invokeinterface size : ()I
    //   371: ifle -> 397
    //   374: aload_0
    //   375: getfield mHandler : Landroid/os/Handler;
    //   378: astore #5
    //   380: new com/microsoft/appcenter/AppCenter$5
    //   383: astore_1
    //   384: aload_1
    //   385: aload_0
    //   386: aload_2
    //   387: invokespecial <init> : (Lcom/microsoft/appcenter/AppCenter;Ljava/util/Collection;)V
    //   390: aload #5
    //   392: aload_1
    //   393: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   396: pop
    //   397: aload_0
    //   398: monitorexit
    //   399: return
    //   400: astore_1
    //   401: aload_0
    //   402: monitorexit
    //   403: goto -> 408
    //   406: aload_1
    //   407: athrow
    //   408: goto -> 406
    // Exception table:
    //   from	to	target	type
    //   6	14	400	finally
    //   17	22	400	finally
    //   32	36	400	finally
    //   47	99	400	finally
    //   105	136	400	finally
    //   139	151	400	finally
    //   169	177	400	finally
    //   180	259	306	java/lang/Exception
    //   180	259	400	finally
    //   262	303	306	java/lang/Exception
    //   262	303	400	finally
    //   308	359	400	finally
    //   365	397	400	finally
  }
  
  @VisibleForTesting
  static void unsetInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/AppCenter
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic com/microsoft/appcenter/AppCenter.sInstance : Lcom/microsoft/appcenter/AppCenter;
    //   7: ldc com/microsoft/appcenter/AppCenter
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/microsoft/appcenter/AppCenter
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  @VisibleForTesting
  Application getApplication() {
    return this.mApplication;
  }
  
  @VisibleForTesting
  Set<AppCenterService> getServices() {
    return this.mServices;
  }
  
  @VisibleForTesting
  UncaughtExceptionHandler getUncaughtExceptionHandler() {
    return this.mUncaughtExceptionHandler;
  }
  
  @VisibleForTesting
  void setChannel(Channel paramChannel) {
    this.mChannel = paramChannel;
  }
  
  @VisibleForTesting
  class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    
    @VisibleForTesting
    Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
      return this.mDefaultUncaughtExceptionHandler;
    }
    
    void register() {
      this.mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    public void uncaughtException(Thread param1Thread, Throwable param1Throwable) {
      if (AppCenter.this.isInstanceEnabled()) {
        final Semaphore semaphore = new Semaphore(0);
        AppCenter.this.mHandler.post(new Runnable() {
              public void run() {
                if (AppCenter.this.mChannel != null)
                  AppCenter.this.mChannel.shutdown(); 
                AppCenterLog.debug("AppCenter", "Channel completed shutdown.");
                semaphore.release();
              }
            });
        try {
          if (!semaphore.tryAcquire(5000L, TimeUnit.MILLISECONDS))
            AppCenterLog.error("AppCenter", "Timeout waiting for looper tasks to complete."); 
        } catch (InterruptedException interruptedException) {
          AppCenterLog.warn("AppCenter", "Interrupted while waiting looper to flush.", interruptedException);
        } 
      } 
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultUncaughtExceptionHandler;
      if (uncaughtExceptionHandler != null) {
        uncaughtExceptionHandler.uncaughtException(param1Thread, param1Throwable);
      } else {
        ShutdownHelper.shutdown(10);
      } 
    }
    
    void unregister() {
      Thread.setDefaultUncaughtExceptionHandler(this.mDefaultUncaughtExceptionHandler);
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (AppCenter.this.mChannel != null)
        AppCenter.this.mChannel.shutdown(); 
      AppCenterLog.debug("AppCenter", "Channel completed shutdown.");
      semaphore.release();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/AppCenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */