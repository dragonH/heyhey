package com.microsoft.appcenter.crashes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.AbstractAppCenterService;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.ingestion.models.HandledErrorLog;
import com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog;
import com.microsoft.appcenter.crashes.ingestion.models.json.ErrorAttachmentLogFactory;
import com.microsoft.appcenter.crashes.ingestion.models.json.HandledErrorLogFactory;
import com.microsoft.appcenter.crashes.ingestion.models.json.ManagedErrorLogFactory;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.crashes.model.TestCrashException;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.DefaultLogSerializer;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.HandlerUtils;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;

public class Crashes extends AbstractAppCenterService {
  public static final int ALWAYS_SEND = 2;
  
  private static final CrashesListener DEFAULT_ERROR_REPORTING_LISTENER = new DefaultCrashesListener();
  
  public static final int DONT_SEND = 1;
  
  @VisibleForTesting
  static final String ERROR_GROUP = "groupErrors";
  
  public static final String LOG_TAG = "AppCenterCrashes";
  
  private static final int MAX_ATTACHMENT_PER_CRASH = 2;
  
  @VisibleForTesting
  public static final String PREF_KEY_ALWAYS_SEND = "com.microsoft.appcenter.crashes.always.send";
  
  public static final int SEND = 0;
  
  private static final String SERVICE_NAME = "Crashes";
  
  @SuppressLint({"StaticFieldLeak"})
  private static Crashes sInstance = null;
  
  private boolean mAutomaticProcessing = true;
  
  private Context mContext;
  
  private CrashesListener mCrashesListener;
  
  private final Map<UUID, ErrorLogReport> mErrorReportCache;
  
  private final Map<String, LogFactory> mFactories;
  
  private long mInitializeTimestamp;
  
  private ErrorReport mLastSessionErrorReport;
  
  private LogSerializer mLogSerializer;
  
  private boolean mSavedUncaughtException;
  
  private UncaughtExceptionHandler mUncaughtExceptionHandler;
  
  private final Map<UUID, ErrorLogReport> mUnprocessedErrorReports;
  
  private Crashes() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    this.mFactories = (Map)hashMap;
    hashMap.put("managedError", ManagedErrorLogFactory.getInstance());
    hashMap.put("handledError", HandledErrorLogFactory.getInstance());
    hashMap.put("errorAttachment", ErrorAttachmentLogFactory.getInstance());
    DefaultLogSerializer defaultLogSerializer = new DefaultLogSerializer();
    this.mLogSerializer = (LogSerializer)defaultLogSerializer;
    defaultLogSerializer.addLogFactory("managedError", (LogFactory)ManagedErrorLogFactory.getInstance());
    this.mLogSerializer.addLogFactory("errorAttachment", (LogFactory)ErrorAttachmentLogFactory.getInstance());
    this.mCrashesListener = DEFAULT_ERROR_REPORTING_LISTENER;
    this.mUnprocessedErrorReports = new LinkedHashMap<UUID, ErrorLogReport>();
    this.mErrorReportCache = new LinkedHashMap<UUID, ErrorLogReport>();
  }
  
  public static void generateTestCrash() {
    if (!Constants.APPLICATION_DEBUGGABLE) {
      AppCenterLog.warn("AppCenterCrashes", "The application is not debuggable so SDK won't generate test crash");
      return;
    } 
    throw new TestCrashException();
  }
  
  @NonNull
  public static Crashes getInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/crashes/Crashes
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/crashes/Crashes.sInstance : Lcom/microsoft/appcenter/crashes/Crashes;
    //   6: ifnonnull -> 21
    //   9: new com/microsoft/appcenter/crashes/Crashes
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/microsoft/appcenter/crashes/Crashes.sInstance : Lcom/microsoft/appcenter/crashes/Crashes;
    //   21: getstatic com/microsoft/appcenter/crashes/Crashes.sInstance : Lcom/microsoft/appcenter/crashes/Crashes;
    //   24: astore_0
    //   25: ldc com/microsoft/appcenter/crashes/Crashes
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/microsoft/appcenter/crashes/Crashes
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  private AppCenterFuture<ErrorReport> getInstanceLastSessionCrashReport() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: new com/microsoft/appcenter/crashes/Crashes$2
    //   13: astore_2
    //   14: aload_2
    //   15: aload_0
    //   16: aload_1
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
    //   20: aload_0
    //   21: aload_2
    //   22: aload_1
    //   23: aconst_null
    //   24: invokevirtual postAsyncGetter : (Ljava/lang/Runnable;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;Ljava/lang/Object;)V
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	31	finally
  }
  
  public static AppCenterFuture<ErrorReport> getLastSessionCrashReport() {
    return getInstance().getInstanceLastSessionCrashReport();
  }
  
  @VisibleForTesting
  private void handleUserConfirmation(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/crashes/Crashes$8
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: iload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;I)V
    //   12: aload_0
    //   13: aload_2
    //   14: invokevirtual post : (Ljava/lang/Runnable;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_2
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  public static AppCenterFuture<Boolean> hasCrashedInLastSession() {
    return getInstance().hasInstanceCrashedInLastSession();
  }
  
  private AppCenterFuture<Boolean> hasInstanceCrashedInLastSession() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture
    //   5: astore_1
    //   6: aload_1
    //   7: invokespecial <init> : ()V
    //   10: new com/microsoft/appcenter/crashes/Crashes$1
    //   13: astore_2
    //   14: aload_2
    //   15: aload_0
    //   16: aload_1
    //   17: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;)V
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
  
  private void initialize() {
    long l;
    boolean bool = isInstanceEnabled();
    if (bool) {
      l = System.currentTimeMillis();
    } else {
      l = -1L;
    } 
    this.mInitializeTimestamp = l;
    if (!bool) {
      UncaughtExceptionHandler uncaughtExceptionHandler = this.mUncaughtExceptionHandler;
      if (uncaughtExceptionHandler != null) {
        uncaughtExceptionHandler.unregister();
        this.mUncaughtExceptionHandler = null;
      } 
    } else {
      UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler();
      this.mUncaughtExceptionHandler = uncaughtExceptionHandler;
      uncaughtExceptionHandler.register();
      File file = ErrorLogHelper.getLastErrorLogFile();
      if (file != null) {
        AppCenterLog.debug("AppCenterCrashes", "Processing crash report for the last session.");
        String str = StorageHelper.InternalStorage.read(file);
        if (str == null) {
          AppCenterLog.error("AppCenterCrashes", "Error reading last session error log.");
        } else {
          try {
            this.mLastSessionErrorReport = buildErrorReport((ManagedErrorLog)this.mLogSerializer.deserializeLog(str));
            AppCenterLog.debug("AppCenterCrashes", "Processed crash report for the last session.");
          } catch (JSONException jSONException) {
            AppCenterLog.error("AppCenterCrashes", "Error parsing last session error log.", (Throwable)jSONException);
          } 
        } 
      } 
    } 
  }
  
  public static AppCenterFuture<Boolean> isEnabled() {
    return getInstance().isInstanceEnabledAsync();
  }
  
  public static void notifyUserConfirmation(int paramInt) {
    getInstance().handleUserConfirmation(paramInt);
  }
  
  private void processPendingErrors() {
    for (File file : ErrorLogHelper.getStoredErrorLogFiles()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Process pending error file: ");
      stringBuilder.append(file);
      AppCenterLog.debug("AppCenterCrashes", stringBuilder.toString());
      String str = StorageHelper.InternalStorage.read(file);
      if (str != null)
        try {
          ManagedErrorLog managedErrorLog = (ManagedErrorLog)this.mLogSerializer.deserializeLog(str);
          UUID uUID = managedErrorLog.getId();
          ErrorReport errorReport = buildErrorReport(managedErrorLog);
          if (errorReport == null) {
            removeAllStoredErrorLogFiles(uUID);
          } else if (!this.mAutomaticProcessing || this.mCrashesListener.shouldProcess(errorReport)) {
            if (!this.mAutomaticProcessing) {
              StringBuilder stringBuilder1 = new StringBuilder();
              this();
              stringBuilder1.append("CrashesListener.shouldProcess returned true, continue processing log: ");
              stringBuilder1.append(uUID.toString());
              AppCenterLog.debug("AppCenterCrashes", stringBuilder1.toString());
            } 
            this.mUnprocessedErrorReports.put(uUID, this.mErrorReportCache.get(uUID));
          } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append("CrashesListener.shouldProcess returned false, clean up and ignore log: ");
            stringBuilder1.append(uUID.toString());
            AppCenterLog.debug("AppCenterCrashes", stringBuilder1.toString());
            removeAllStoredErrorLogFiles(uUID);
          } 
        } catch (JSONException jSONException) {
          AppCenterLog.error("AppCenterCrashes", "Error parsing error log", (Throwable)jSONException);
        }  
    } 
    if (this.mAutomaticProcessing)
      sendCrashReportsOrAwaitUserConfirmation(); 
  }
  
  private void queueException(@NonNull ExceptionModelBuilder paramExceptionModelBuilder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/crashes/Crashes$6
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;Lcom/microsoft/appcenter/crashes/Crashes$ExceptionModelBuilder;)V
    //   12: aload_0
    //   13: aload_2
    //   14: invokevirtual post : (Ljava/lang/Runnable;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  private void queueException(@NonNull Throwable paramThrowable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/crashes/Crashes$4
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;Ljava/lang/Throwable;)V
    //   12: aload_0
    //   13: aload_2
    //   14: invokespecial queueException : (Lcom/microsoft/appcenter/crashes/Crashes$ExceptionModelBuilder;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  private void removeAllStoredErrorLogFiles(UUID paramUUID) {
    ErrorLogHelper.removeStoredErrorLogFile(paramUUID);
    removeStoredThrowable(paramUUID);
  }
  
  private void removeStoredThrowable(UUID paramUUID) {
    this.mErrorReportCache.remove(paramUUID);
    WrapperSdkExceptionManager.deleteWrapperExceptionData(paramUUID);
    ErrorLogHelper.removeStoredThrowableFile(paramUUID);
  }
  
  private boolean sendCrashReportsOrAwaitUserConfirmation() {
    final boolean alwaysSend = StorageHelper.PreferencesStorage.getBoolean("com.microsoft.appcenter.crashes.always.send", false);
    HandlerUtils.runOnUiThread(new Runnable() {
          public void run() {
            if (Crashes.this.mUnprocessedErrorReports.size() > 0) {
              if (alwaysSend) {
                AppCenterLog.debug("AppCenterCrashes", "The flag for user confirmation is set to ALWAYS_SEND, will send logs.");
                Crashes.this.handleUserConfirmation(0);
                return;
              } 
              if (!Crashes.this.mAutomaticProcessing) {
                AppCenterLog.debug("AppCenterCrashes", "Automatic processing disabled, will wait for explicit user confirmation.");
                return;
              } 
              if (!Crashes.this.mCrashesListener.shouldAwaitUserConfirmation()) {
                AppCenterLog.debug("AppCenterCrashes", "CrashesListener.shouldAwaitUserConfirmation returned false, will send logs.");
                Crashes.this.handleUserConfirmation(0);
              } else {
                AppCenterLog.debug("AppCenterCrashes", "CrashesListener.shouldAwaitUserConfirmation returned true, wait sending logs.");
              } 
            } 
          }
        });
    return bool;
  }
  
  private void sendErrorAttachment(UUID paramUUID, Iterable<ErrorAttachmentLog> paramIterable) {
    StringBuilder stringBuilder;
    if (paramIterable == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("CrashesListener.getErrorAttachments returned null, no additional information will be attached to log: ");
      stringBuilder.append(paramUUID.toString());
      AppCenterLog.debug("AppCenterCrashes", stringBuilder.toString());
    } else {
      byte b = 0;
      for (ErrorAttachmentLog errorAttachmentLog : stringBuilder) {
        if (errorAttachmentLog != null) {
          errorAttachmentLog.setId(UUID.randomUUID());
          errorAttachmentLog.setErrorId(paramUUID);
          if (errorAttachmentLog.isValid()) {
            b++;
            this.mChannel.enqueue((Log)errorAttachmentLog, "groupErrors");
            continue;
          } 
          AppCenterLog.error("AppCenterCrashes", "Not all required fields are present in ErrorAttachmentLog.");
          continue;
        } 
        AppCenterLog.warn("AppCenterCrashes", "Skipping null ErrorAttachmentLog in CrashesListener.getErrorAttachments.");
      } 
      if (b > 2)
        AppCenterLog.warn("AppCenterCrashes", "A limit of 2 attachments per error report might be enforced by server."); 
    } 
  }
  
  public static AppCenterFuture<Void> setEnabled(boolean paramBoolean) {
    return getInstance().setInstanceEnabledAsync(paramBoolean);
  }
  
  public static void setListener(CrashesListener paramCrashesListener) {
    getInstance().setInstanceListener(paramCrashesListener);
  }
  
  static void trackException(@NonNull Throwable paramThrowable) {
    getInstance().queueException(paramThrowable);
  }
  
  @VisibleForTesting
  static void unsetInstance() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/crashes/Crashes
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic com/microsoft/appcenter/crashes/Crashes.sInstance : Lcom/microsoft/appcenter/crashes/Crashes;
    //   7: ldc com/microsoft/appcenter/crashes/Crashes
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/microsoft/appcenter/crashes/Crashes
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  protected void applyEnabledState(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial initialize : ()V
    //   6: iload_1
    //   7: ifne -> 131
    //   10: invokestatic getErrorStorageDirectory : ()Ljava/io/File;
    //   13: invokevirtual listFiles : ()[Ljava/io/File;
    //   16: astore_2
    //   17: aload_2
    //   18: arraylength
    //   19: istore_3
    //   20: iconst_0
    //   21: istore #4
    //   23: iload #4
    //   25: iload_3
    //   26: if_icmpge -> 123
    //   29: aload_2
    //   30: iload #4
    //   32: aaload
    //   33: astore #5
    //   35: new java/lang/StringBuilder
    //   38: astore #6
    //   40: aload #6
    //   42: invokespecial <init> : ()V
    //   45: aload #6
    //   47: ldc_w 'Deleting file '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload #6
    //   56: aload #5
    //   58: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: ldc 'AppCenterCrashes'
    //   64: aload #6
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: invokestatic debug : (Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload #5
    //   74: invokevirtual delete : ()Z
    //   77: ifne -> 117
    //   80: new java/lang/StringBuilder
    //   83: astore #6
    //   85: aload #6
    //   87: invokespecial <init> : ()V
    //   90: aload #6
    //   92: ldc_w 'Failed to delete file '
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #6
    //   101: aload #5
    //   103: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: ldc 'AppCenterCrashes'
    //   109: aload #6
    //   111: invokevirtual toString : ()Ljava/lang/String;
    //   114: invokestatic warn : (Ljava/lang/String;Ljava/lang/String;)V
    //   117: iinc #4, 1
    //   120: goto -> 23
    //   123: ldc 'AppCenterCrashes'
    //   125: ldc_w 'Deleted crashes local files'
    //   128: invokestatic info : (Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload_0
    //   132: monitorexit
    //   133: return
    //   134: astore_2
    //   135: aload_0
    //   136: monitorexit
    //   137: goto -> 142
    //   140: aload_2
    //   141: athrow
    //   142: goto -> 140
    // Exception table:
    //   from	to	target	type
    //   2	6	134	finally
    //   10	20	134	finally
    //   35	117	134	finally
    //   123	131	134	finally
  }
  
  @Nullable
  @VisibleForTesting
  ErrorReport buildErrorReport(ManagedErrorLog paramManagedErrorLog) {
    UUID uUID = paramManagedErrorLog.getId();
    if (this.mErrorReportCache.containsKey(uUID))
      return (this.mErrorReportCache.get(uUID)).report; 
    File file = ErrorLogHelper.getStoredThrowableFile(uUID);
    if (file != null)
      try {
        Throwable throwable;
        if (file.length() > 0L) {
          throwable = (Throwable)StorageHelper.InternalStorage.readObject(file);
        } else {
          throwable = null;
        } 
        ErrorReport errorReport = ErrorLogHelper.getErrorReportFromErrorLog(paramManagedErrorLog, throwable);
        Map<UUID, ErrorLogReport> map = this.mErrorReportCache;
        ErrorLogReport errorLogReport = new ErrorLogReport();
        this(paramManagedErrorLog, errorReport);
        map.put(uUID, errorLogReport);
        return errorReport;
      } catch (ClassNotFoundException classNotFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot read throwable file ");
        stringBuilder.append(file.getName());
        AppCenterLog.error("AppCenterCrashes", stringBuilder.toString(), classNotFoundException);
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot access serialized throwable file ");
        stringBuilder.append(file.getName());
        AppCenterLog.error("AppCenterCrashes", stringBuilder.toString(), iOException);
      }  
    return null;
  }
  
  protected Channel.GroupListener getChannelListener() {
    return new Channel.GroupListener() {
        private void processCallback(final Log log, final Crashes.CallbackProcessor callbackProcessor) {
          Crashes.this.post(new Runnable() {
                public void run() {
                  StringBuilder stringBuilder;
                  Log log = log;
                  if (log instanceof ManagedErrorLog) {
                    ManagedErrorLog managedErrorLog = (ManagedErrorLog)log;
                    final ErrorReport report = Crashes.this.buildErrorReport(managedErrorLog);
                    UUID uUID = managedErrorLog.getId();
                    if (errorReport != null) {
                      if (callbackProcessor.shouldDeleteThrowable())
                        Crashes.this.removeStoredThrowable(uUID); 
                      HandlerUtils.runOnUiThread(new Runnable() {
                            public void run() {
                              callbackProcessor.onCallBack(report);
                            }
                          });
                    } else {
                      stringBuilder = new StringBuilder();
                      stringBuilder.append("Cannot find crash report for the error log: ");
                      stringBuilder.append(uUID);
                      AppCenterLog.warn("AppCenterCrashes", stringBuilder.toString());
                    } 
                  } else if (!(stringBuilder instanceof ErrorAttachmentLog) && !(stringBuilder instanceof HandledErrorLog)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("A different type of log comes to crashes: ");
                    stringBuilder.append(log.getClass().getName());
                    AppCenterLog.warn("AppCenterCrashes", stringBuilder.toString());
                  } 
                }
              });
        }
        
        public void onBeforeSending(Log param1Log) {
          processCallback(param1Log, new Crashes.CallbackProcessor() {
                public void onCallBack(ErrorReport param2ErrorReport) {
                  Crashes.this.mCrashesListener.onBeforeSending(param2ErrorReport);
                }
                
                public boolean shouldDeleteThrowable() {
                  return false;
                }
              });
        }
        
        public void onFailure(Log param1Log, final Exception e) {
          processCallback(param1Log, new Crashes.CallbackProcessor() {
                public void onCallBack(ErrorReport param2ErrorReport) {
                  Crashes.this.mCrashesListener.onSendingFailed(param2ErrorReport, e);
                }
                
                public boolean shouldDeleteThrowable() {
                  return true;
                }
              });
        }
        
        public void onSuccess(Log param1Log) {
          processCallback(param1Log, new Crashes.CallbackProcessor() {
                public void onCallBack(ErrorReport param2ErrorReport) {
                  Crashes.this.mCrashesListener.onSendingSucceeded(param2ErrorReport);
                }
                
                public boolean shouldDeleteThrowable() {
                  return true;
                }
              });
        }
      };
  }
  
  protected String getGroupName() {
    return "groupErrors";
  }
  
  @VisibleForTesting
  long getInitializeTimestamp() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mInitializeTimestamp : J
    //   6: lstore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: lload_1
    //   10: lreturn
    //   11: astore_3
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_3
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  @VisibleForTesting
  CrashesListener getInstanceListener() {
    return this.mCrashesListener;
  }
  
  public Map<String, LogFactory> getLogFactories() {
    return this.mFactories;
  }
  
  protected String getLoggerTag() {
    return "AppCenterCrashes";
  }
  
  public String getServiceName() {
    return "Crashes";
  }
  
  protected int getTriggerCount() {
    return 1;
  }
  
  @VisibleForTesting
  UncaughtExceptionHandler getUncaughtExceptionHandler() {
    return this.mUncaughtExceptionHandler;
  }
  
  AppCenterFuture<Collection<ErrorReport>> getUnprocessedErrorReports() {
    final DefaultAppCenterFuture future = new DefaultAppCenterFuture();
    postAsyncGetter(new Runnable() {
          public void run() {
            ArrayList<ErrorReport> arrayList = new ArrayList(Crashes.this.mUnprocessedErrorReports.size());
            Iterator<Crashes.ErrorLogReport> iterator = Crashes.this.mUnprocessedErrorReports.values().iterator();
            while (iterator.hasNext())
              arrayList.add((iterator.next()).report); 
            future.complete(arrayList);
          }
        },  defaultAppCenterFuture, Collections.emptyList());
    return (AppCenterFuture<Collection<ErrorReport>>)defaultAppCenterFuture;
  }
  
  public void onStarted(@NonNull Context paramContext, @NonNull String paramString, @NonNull Channel paramChannel) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: aload_3
    //   6: invokespecial onStarted : (Landroid/content/Context;Ljava/lang/String;Lcom/microsoft/appcenter/channel/Channel;)V
    //   9: aload_0
    //   10: aload_1
    //   11: putfield mContext : Landroid/content/Context;
    //   14: aload_0
    //   15: invokevirtual isInstanceEnabled : ()Z
    //   18: ifeq -> 28
    //   21: aload_0
    //   22: invokespecial processPendingErrors : ()V
    //   25: goto -> 32
    //   28: aload_0
    //   29: invokespecial initialize : ()V
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	35	finally
    //   28	32	35	finally
  }
  
  void queueException(@NonNull Exception paramException) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/microsoft/appcenter/crashes/Crashes$5
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/microsoft/appcenter/crashes/Crashes;Lcom/microsoft/appcenter/crashes/ingestion/models/Exception;)V
    //   12: aload_0
    //   13: aload_2
    //   14: invokespecial queueException : (Lcom/microsoft/appcenter/crashes/Crashes$ExceptionModelBuilder;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  UUID saveUncaughtException(Thread paramThread, Throwable paramThrowable, Exception paramException) throws JSONException, IOException {
    if (!((Boolean)isEnabled().get()).booleanValue())
      return null; 
    if (this.mSavedUncaughtException)
      return null; 
    this.mSavedUncaughtException = true;
    ManagedErrorLog managedErrorLog = ErrorLogHelper.createErrorLog(this.mContext, paramThread, paramException, Thread.getAllStackTraces(), this.mInitializeTimestamp, true);
    File file1 = ErrorLogHelper.getErrorStorageDirectory();
    UUID uUID = managedErrorLog.getId();
    String str = uUID.toString();
    AppCenterLog.debug("AppCenterCrashes", "Saving uncaught exception.");
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(str);
    stringBuilder3.append(".json");
    File file2 = new File(file1, stringBuilder3.toString());
    StorageHelper.InternalStorage.write(file2, this.mLogSerializer.serializeLog((Log)managedErrorLog));
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Saved JSON content for ingestion into ");
    stringBuilder1.append(file2);
    AppCenterLog.debug("AppCenterCrashes", stringBuilder1.toString());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str);
    stringBuilder2.append(".throwable");
    file1 = new File(file1, stringBuilder2.toString());
    if (paramThrowable != null) {
      StorageHelper.InternalStorage.writeObject(file1, paramThrowable);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Saved Throwable as is for client side inspection in ");
      stringBuilder.append(file1);
      AppCenterLog.debug("AppCenterCrashes", stringBuilder.toString());
    } else {
      if (file1.createNewFile()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Saved empty Throwable file in ");
        stringBuilder.append(file1);
        AppCenterLog.debug("AppCenterCrashes", stringBuilder.toString());
        return uUID;
      } 
      throw new IOException(file1.getName());
    } 
    return uUID;
  }
  
  void saveUncaughtException(Thread paramThread, Throwable paramThrowable) {
    try {
      saveUncaughtException(paramThread, paramThrowable, ErrorLogHelper.getModelExceptionFromThrowable(paramThrowable));
    } catch (JSONException jSONException) {
      AppCenterLog.error("AppCenterCrashes", "Error serializing error log to JSON", (Throwable)jSONException);
    } catch (IOException iOException) {
      AppCenterLog.error("AppCenterCrashes", "Error writing error log to file", iOException);
    } 
  }
  
  AppCenterFuture<Boolean> sendCrashReportsOrAwaitUserConfirmation(final Collection<String> filteredReportIds) {
    final DefaultAppCenterFuture future = new DefaultAppCenterFuture();
    postAsyncGetter(new Runnable() {
          public void run() {
            Iterator<Map.Entry> iterator = Crashes.this.mUnprocessedErrorReports.entrySet().iterator();
            while (iterator.hasNext()) {
              StringBuilder stringBuilder1;
              Map.Entry entry = iterator.next();
              UUID uUID = (UUID)entry.getKey();
              String str = ((Crashes.ErrorLogReport)entry.getValue()).report.getId();
              Collection collection = filteredReportIds;
              if (collection != null && collection.contains(str)) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("CrashesListener.shouldProcess returned true, continue processing log: ");
                stringBuilder1.append(str);
                AppCenterLog.debug("AppCenterCrashes", stringBuilder1.toString());
                continue;
              } 
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("CrashesListener.shouldProcess returned false, clean up and ignore log: ");
              stringBuilder2.append(str);
              AppCenterLog.debug("AppCenterCrashes", stringBuilder2.toString());
              Crashes.this.removeAllStoredErrorLogFiles((UUID)stringBuilder1);
              iterator.remove();
            } 
            future.complete(Boolean.valueOf(Crashes.this.sendCrashReportsOrAwaitUserConfirmation()));
          }
        }defaultAppCenterFuture, Boolean.FALSE);
    return (AppCenterFuture<Boolean>)defaultAppCenterFuture;
  }
  
  void sendErrorAttachments(final String errorReportId, final Iterable<ErrorAttachmentLog> attachments) {
    post(new Runnable() {
          public void run() {
            try {
              UUID uUID = UUID.fromString(errorReportId);
              Crashes.this.sendErrorAttachment(uUID, attachments);
              return;
            } catch (RuntimeException runtimeException) {
              AppCenterLog.error("AppCenterCrashes", "Error report identifier has an invalid format for sending attachments.");
              return;
            } 
          }
        });
  }
  
  void setAutomaticProcessing(boolean paramBoolean) {
    this.mAutomaticProcessing = paramBoolean;
  }
  
  @VisibleForTesting
  void setInstanceListener(CrashesListener paramCrashesListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: astore_2
    //   4: aload_1
    //   5: ifnonnull -> 12
    //   8: getstatic com/microsoft/appcenter/crashes/Crashes.DEFAULT_ERROR_REPORTING_LISTENER : Lcom/microsoft/appcenter/crashes/CrashesListener;
    //   11: astore_2
    //   12: aload_0
    //   13: aload_2
    //   14: putfield mCrashesListener : Lcom/microsoft/appcenter/crashes/CrashesListener;
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   8	12	20	finally
    //   12	17	20	finally
  }
  
  @VisibleForTesting
  void setLogSerializer(LogSerializer paramLogSerializer) {
    this.mLogSerializer = paramLogSerializer;
  }
  
  @VisibleForTesting
  void setUncaughtExceptionHandler(UncaughtExceptionHandler paramUncaughtExceptionHandler) {
    this.mUncaughtExceptionHandler = paramUncaughtExceptionHandler;
  }
  
  private static interface CallbackProcessor {
    void onCallBack(ErrorReport param1ErrorReport);
    
    boolean shouldDeleteThrowable();
  }
  
  private static class DefaultCrashesListener extends AbstractCrashesListener {
    private DefaultCrashesListener() {}
  }
  
  private static class ErrorLogReport {
    private final ManagedErrorLog log;
    
    private final ErrorReport report;
    
    private ErrorLogReport(ManagedErrorLog param1ManagedErrorLog, ErrorReport param1ErrorReport) {
      this.log = param1ManagedErrorLog;
      this.report = param1ErrorReport;
    }
  }
  
  private static interface ExceptionModelBuilder {
    Exception buildExceptionModel();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/Crashes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */