package com.microsoft.appcenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.HandlerUtils;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import java.util.Map;

public abstract class AbstractAppCenterService implements AppCenterService {
  private static final String PREFERENCE_KEY_SEPARATOR = "_";
  
  protected Channel mChannel;
  
  private AppCenterHandler mHandler;
  
  private void runIfEnabled(Runnable paramRunnable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isInstanceEnabled : ()Z
    //   6: ifeq -> 15
    //   9: aload_1
    //   10: invokeinterface run : ()V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  protected void applyEnabledState(boolean paramBoolean) {
    /* monitor enter ThisExpression{ObjectType{com/microsoft/appcenter/AbstractAppCenterService}} */
    /* monitor exit ThisExpression{ObjectType{com/microsoft/appcenter/AbstractAppCenterService}} */
  }
  
  protected Channel.GroupListener getChannelListener() {
    return null;
  }
  
  @NonNull
  protected String getEnabledPreferenceKey() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("enabled_");
    stringBuilder.append(getServiceName());
    return stringBuilder.toString();
  }
  
  protected abstract String getGroupName();
  
  public Map<String, LogFactory> getLogFactories() {
    return null;
  }
  
  protected abstract String getLoggerTag();
  
  protected int getTriggerCount() {
    return 50;
  }
  
  protected int getTriggerInterval() {
    return 3000;
  }
  
  protected int getTriggerMaxParallelRequests() {
    return 3;
  }
  
  public boolean isInstanceEnabled() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getEnabledPreferenceKey : ()Ljava/lang/String;
    //   6: iconst_1
    //   7: invokestatic getBoolean : (Ljava/lang/String;Z)Z
    //   10: istore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: iload_1
    //   14: ireturn
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	15	finally
  }
  
  protected AppCenterFuture<Boolean> isInstanceEnabledAsync() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: new com/microsoft/appcenter/AbstractAppCenterService$1
    //   13: astore_2
    //   14: aload_2
    //   15: aload_0
    //   16: aload_1
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   20: aload_0
    //   21: aload_2
    //   22: aload_1
    //   23: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   26: invokevirtual postAsyncGetter : (Ljava/lang/Runnable;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;Ljava/lang/Object;)V
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: areturn
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	33	finally
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity) {}
  
  public void onActivityResumed(Activity paramActivity) {}
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity) {}
  
  public void onActivityStopped(Activity paramActivity) {}
  
  public void onStarted(@NonNull Context paramContext, @NonNull String paramString, @NonNull Channel paramChannel) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getGroupName : ()Ljava/lang/String;
    //   6: astore_1
    //   7: aload_0
    //   8: invokevirtual isInstanceEnabled : ()Z
    //   11: istore #4
    //   13: aload_1
    //   14: ifnull -> 63
    //   17: aload_3
    //   18: aload_1
    //   19: invokeinterface removeGroup : (Ljava/lang/String;)V
    //   24: iload #4
    //   26: ifeq -> 56
    //   29: aload_3
    //   30: aload_1
    //   31: aload_0
    //   32: invokevirtual getTriggerCount : ()I
    //   35: aload_0
    //   36: invokevirtual getTriggerInterval : ()I
    //   39: i2l
    //   40: aload_0
    //   41: invokevirtual getTriggerMaxParallelRequests : ()I
    //   44: aload_0
    //   45: invokevirtual getChannelListener : ()Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   48: invokeinterface addGroup : (Ljava/lang/String;IJILcom/microsoft/appcenter/channel/Channel$GroupListener;)V
    //   53: goto -> 63
    //   56: aload_3
    //   57: aload_1
    //   58: invokeinterface clear : (Ljava/lang/String;)V
    //   63: aload_0
    //   64: aload_3
    //   65: putfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   68: iload #4
    //   70: ifeq -> 78
    //   73: aload_0
    //   74: iconst_1
    //   75: invokevirtual applyEnabledState : (Z)V
    //   78: aload_0
    //   79: monitorexit
    //   80: return
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	81	finally
    //   17	24	81	finally
    //   29	53	81	finally
    //   56	63	81	finally
    //   63	68	81	finally
    //   73	78	81	finally
  }
  
  public final void onStarting(@NonNull AppCenterHandler paramAppCenterHandler) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  protected void post(Runnable paramRunnable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aconst_null
    //   5: aconst_null
    //   6: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   9: pop
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	13	finally
  }
  
  protected boolean post(Runnable paramRunnable1, Runnable paramRunnable2, Runnable paramRunnable3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mHandler : Lcom/microsoft/appcenter/AppCenterHandler;
    //   6: astore #4
    //   8: aload #4
    //   10: ifnonnull -> 52
    //   13: new java/lang/StringBuilder
    //   16: astore_1
    //   17: aload_1
    //   18: invokespecial <init> : ()V
    //   21: aload_1
    //   22: aload_0
    //   23: invokeinterface getServiceName : ()Ljava/lang/String;
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_1
    //   33: ldc ' needs to be started before it can be used.'
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: ldc 'AppCenter'
    //   41: aload_1
    //   42: invokevirtual toString : ()Ljava/lang/String;
    //   45: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_0
    //   49: monitorexit
    //   50: iconst_0
    //   51: ireturn
    //   52: new com/microsoft/appcenter/AbstractAppCenterService$4
    //   55: astore #5
    //   57: aload #5
    //   59: aload_0
    //   60: aload_1
    //   61: aload_3
    //   62: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   65: aload #4
    //   67: aload #5
    //   69: aload_2
    //   70: invokeinterface post : (Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    //   75: aload_0
    //   76: monitorexit
    //   77: iconst_1
    //   78: ireturn
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	79	finally
    //   13	48	79	finally
    //   52	75	79	finally
  }
  
  protected <T> void postAsyncGetter(Runnable paramRunnable, DefaultAppCenterFuture<T> paramDefaultAppCenterFuture, T paramT) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/AbstractAppCenterService$5
    //   5: astore #4
    //   7: aload #4
    //   9: aload_0
    //   10: aload_2
    //   11: aload_3
    //   12: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;Ljava/lang/Object;)V
    //   15: new com/microsoft/appcenter/AbstractAppCenterService$6
    //   18: astore_2
    //   19: aload_2
    //   20: aload_0
    //   21: aload_1
    //   22: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Ljava/lang/Runnable;)V
    //   25: aload_0
    //   26: aload_2
    //   27: aload #4
    //   29: aload #4
    //   31: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   34: ifne -> 44
    //   37: aload #4
    //   39: invokeinterface run : ()V
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   2	44	47	finally
  }
  
  protected void postOnUiThread(Runnable paramRunnable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/AbstractAppCenterService$7
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Ljava/lang/Runnable;)V
    //   12: new com/microsoft/appcenter/AbstractAppCenterService$8
    //   15: astore_1
    //   16: aload_1
    //   17: aload_0
    //   18: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;)V
    //   21: aload_0
    //   22: aload_2
    //   23: aload_1
    //   24: aconst_null
    //   25: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   28: pop
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	32	finally
  }
  
  public void setInstanceEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: aload_0
    //   4: invokevirtual isInstanceEnabled : ()Z
    //   7: if_icmpne -> 62
    //   10: aload_0
    //   11: invokevirtual getLoggerTag : ()Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: invokeinterface getServiceName : ()Ljava/lang/String;
    //   21: astore_3
    //   22: iload_1
    //   23: ifeq -> 33
    //   26: ldc 'enabled'
    //   28: astore #4
    //   30: goto -> 37
    //   33: ldc 'disabled'
    //   35: astore #4
    //   37: aload_2
    //   38: ldc '%s service has already been %s.'
    //   40: iconst_2
    //   41: anewarray java/lang/Object
    //   44: dup
    //   45: iconst_0
    //   46: aload_3
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: aload #4
    //   52: aastore
    //   53: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   56: invokestatic info : (Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_0
    //   60: monitorexit
    //   61: return
    //   62: aload_0
    //   63: invokevirtual getGroupName : ()Ljava/lang/String;
    //   66: astore #4
    //   68: aload #4
    //   70: ifnull -> 130
    //   73: iload_1
    //   74: ifeq -> 108
    //   77: aload_0
    //   78: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   81: aload #4
    //   83: aload_0
    //   84: invokevirtual getTriggerCount : ()I
    //   87: aload_0
    //   88: invokevirtual getTriggerInterval : ()I
    //   91: i2l
    //   92: aload_0
    //   93: invokevirtual getTriggerMaxParallelRequests : ()I
    //   96: aload_0
    //   97: invokevirtual getChannelListener : ()Lcom/microsoft/appcenter/channel/Channel$GroupListener;
    //   100: invokeinterface addGroup : (Ljava/lang/String;IJILcom/microsoft/appcenter/channel/Channel$GroupListener;)V
    //   105: goto -> 130
    //   108: aload_0
    //   109: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   112: aload #4
    //   114: invokeinterface clear : (Ljava/lang/String;)V
    //   119: aload_0
    //   120: getfield mChannel : Lcom/microsoft/appcenter/channel/Channel;
    //   123: aload #4
    //   125: invokeinterface removeGroup : (Ljava/lang/String;)V
    //   130: aload_0
    //   131: invokevirtual getEnabledPreferenceKey : ()Ljava/lang/String;
    //   134: iload_1
    //   135: invokestatic putBoolean : (Ljava/lang/String;Z)V
    //   138: aload_0
    //   139: invokevirtual getLoggerTag : ()Ljava/lang/String;
    //   142: astore_3
    //   143: aload_0
    //   144: invokeinterface getServiceName : ()Ljava/lang/String;
    //   149: astore_2
    //   150: iload_1
    //   151: ifeq -> 161
    //   154: ldc 'enabled'
    //   156: astore #4
    //   158: goto -> 165
    //   161: ldc 'disabled'
    //   163: astore #4
    //   165: aload_3
    //   166: ldc '%s service has been %s.'
    //   168: iconst_2
    //   169: anewarray java/lang/Object
    //   172: dup
    //   173: iconst_0
    //   174: aload_2
    //   175: aastore
    //   176: dup
    //   177: iconst_1
    //   178: aload #4
    //   180: aastore
    //   181: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   184: invokestatic info : (Ljava/lang/String;Ljava/lang/String;)V
    //   187: aload_0
    //   188: iload_1
    //   189: invokevirtual applyEnabledState : (Z)V
    //   192: aload_0
    //   193: monitorexit
    //   194: return
    //   195: astore #4
    //   197: aload_0
    //   198: monitorexit
    //   199: aload #4
    //   201: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	195	finally
    //   37	59	195	finally
    //   62	68	195	finally
    //   77	105	195	finally
    //   108	130	195	finally
    //   130	150	195	finally
    //   165	192	195	finally
  }
  
  protected final AppCenterFuture<Void> setInstanceEnabledAsync(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: new com/microsoft/appcenter/AbstractAppCenterService$2
    //   13: astore_3
    //   14: aload_3
    //   15: aload_0
    //   16: aload_2
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   20: new com/microsoft/appcenter/AbstractAppCenterService$3
    //   23: astore #4
    //   25: aload #4
    //   27: aload_0
    //   28: iload_1
    //   29: aload_2
    //   30: invokespecial <init> : (Lcom/microsoft/appcenter/AbstractAppCenterService;ZLcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   33: aload_0
    //   34: aload #4
    //   36: aload_3
    //   37: aload #4
    //   39: invokevirtual post : (Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;)Z
    //   42: ifne -> 50
    //   45: aload_2
    //   46: aconst_null
    //   47: invokevirtual complete : (Ljava/lang/Object;)V
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_2
    //   53: areturn
    //   54: astore #4
    //   56: aload_0
    //   57: monitorexit
    //   58: aload #4
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   2	50	54	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/AbstractAppCenterService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */