package com.microsoft.appcenter.crashes.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog;
import com.microsoft.appcenter.crashes.ingestion.models.StackFrame;
import com.microsoft.appcenter.crashes.ingestion.models.Thread;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.DeviceInfoHelper;
import com.microsoft.appcenter.utils.UUIDUtils;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ErrorLogHelper {
  @VisibleForTesting
  static final String ERROR_DIRECTORY = "error";
  
  public static final String ERROR_LOG_FILE_EXTENSION = ".json";
  
  @VisibleForTesting
  public static final int FRAME_LIMIT = 256;
  
  private static final int FRAME_LIMIT_HALF = 128;
  
  public static final String THROWABLE_FILE_EXTENSION = ".throwable";
  
  private static File sErrorLogDirectory;
  
  @NonNull
  public static ManagedErrorLog createErrorLog(@NonNull Context paramContext, @NonNull Thread paramThread, @NonNull Exception paramException, @NonNull Map<Thread, StackTraceElement[]> paramMap, long paramLong, boolean paramBoolean) {
    ManagedErrorLog managedErrorLog = new ManagedErrorLog();
    managedErrorLog.setId(UUIDUtils.randomUUID());
    managedErrorLog.setTimestamp(new Date());
    try {
      managedErrorLog.setDevice(DeviceInfoHelper.getDeviceInfo(paramContext));
    } catch (com.microsoft.appcenter.utils.DeviceInfoHelper.DeviceInfoException deviceInfoException) {
      AppCenterLog.error("AppCenterCrashes", "Could not attach device properties snapshot to error log, will attach at sending time", (Throwable)deviceInfoException);
    } 
    managedErrorLog.setProcessId(Integer.valueOf(Process.myPid()));
    ActivityManager activityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (activityManager != null)
      for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
        if (runningAppProcessInfo.pid == Process.myPid())
          managedErrorLog.setProcessName(runningAppProcessInfo.processName); 
      }  
    managedErrorLog.setArchitecture(getArchitecture());
    managedErrorLog.setErrorThreadId(Long.valueOf(paramThread.getId()));
    managedErrorLog.setErrorThreadName(paramThread.getName());
    managedErrorLog.setFatal(Boolean.valueOf(paramBoolean));
    managedErrorLog.setAppLaunchTimestamp(new Date(paramLong));
    managedErrorLog.setException(paramException);
    ArrayList<Thread> arrayList = new ArrayList(paramMap.size());
    for (Map.Entry<Thread, StackTraceElement> entry : paramMap.entrySet()) {
      Thread thread = new Thread();
      thread.setId(((Thread)entry.getKey()).getId());
      thread.setName(((Thread)entry.getKey()).getName());
      thread.setFrames(getModelFramesFromStackTrace((StackTraceElement[])entry.getValue()));
      arrayList.add(thread);
    } 
    managedErrorLog.setThreads(arrayList);
    return managedErrorLog;
  }
  
  @NonNull
  public static ManagedErrorLog createErrorLog(@NonNull Context paramContext, @NonNull Thread paramThread, @NonNull Throwable paramThrowable, @NonNull Map<Thread, StackTraceElement[]> paramMap, long paramLong, boolean paramBoolean) {
    return createErrorLog(paramContext, paramThread, getModelExceptionFromThrowable(paramThrowable), paramMap, paramLong, paramBoolean);
  }
  
  @TargetApi(21)
  private static String getArchitecture() {
    return (Build.VERSION.SDK_INT >= 21) ? Build.SUPPORTED_ABIS[0] : Build.CPU_ABI;
  }
  
  @NonNull
  public static ErrorReport getErrorReportFromErrorLog(@NonNull ManagedErrorLog paramManagedErrorLog, Throwable paramThrowable) {
    ErrorReport errorReport = new ErrorReport();
    errorReport.setId(paramManagedErrorLog.getId().toString());
    errorReport.setThreadName(paramManagedErrorLog.getErrorThreadName());
    errorReport.setThrowable(paramThrowable);
    errorReport.setAppStartTime(paramManagedErrorLog.getAppLaunchTimestamp());
    errorReport.setAppErrorTime(paramManagedErrorLog.getTimestamp());
    errorReport.setDevice(paramManagedErrorLog.getDevice());
    return errorReport;
  }
  
  @NonNull
  public static File getErrorStorageDirectory() {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/crashes/utils/ErrorLogHelper
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/crashes/utils/ErrorLogHelper.sErrorLogDirectory : Ljava/io/File;
    //   6: ifnonnull -> 33
    //   9: new java/io/File
    //   12: astore_0
    //   13: aload_0
    //   14: getstatic com/microsoft/appcenter/Constants.FILES_PATH : Ljava/lang/String;
    //   17: ldc 'error'
    //   19: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   22: aload_0
    //   23: putstatic com/microsoft/appcenter/crashes/utils/ErrorLogHelper.sErrorLogDirectory : Ljava/io/File;
    //   26: aload_0
    //   27: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   30: invokestatic mkdir : (Ljava/lang/String;)V
    //   33: getstatic com/microsoft/appcenter/crashes/utils/ErrorLogHelper.sErrorLogDirectory : Ljava/io/File;
    //   36: astore_0
    //   37: ldc com/microsoft/appcenter/crashes/utils/ErrorLogHelper
    //   39: monitorexit
    //   40: aload_0
    //   41: areturn
    //   42: astore_0
    //   43: ldc com/microsoft/appcenter/crashes/utils/ErrorLogHelper
    //   45: monitorexit
    //   46: aload_0
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   3	33	42	finally
    //   33	37	42	finally
  }
  
  @Nullable
  public static File getLastErrorLogFile() {
    return StorageHelper.InternalStorage.lastModifiedFile(getErrorStorageDirectory(), new FilenameFilter() {
          public boolean accept(File param1File, String param1String) {
            return param1String.endsWith(".json");
          }
        });
  }
  
  @NonNull
  public static Exception getModelExceptionFromThrowable(@NonNull Throwable paramThrowable) {
    Exception exception1 = null;
    for (Exception exception2 = null; paramThrowable != null; exception2 = exception) {
      Exception exception = new Exception();
      exception.setType(paramThrowable.getClass().getName());
      exception.setMessage(paramThrowable.getMessage());
      exception.setFrames(getModelFramesFromStackTrace(paramThrowable));
      if (exception1 == null) {
        exception1 = exception;
      } else {
        exception2.setInnerExceptions(Collections.singletonList(exception));
      } 
      paramThrowable = paramThrowable.getCause();
    } 
    return exception1;
  }
  
  @NonNull
  private static List<StackFrame> getModelFramesFromStackTrace(@NonNull Throwable paramThrowable) {
    StackTraceElement[] arrayOfStackTraceElement1 = paramThrowable.getStackTrace();
    StackTraceElement[] arrayOfStackTraceElement2 = arrayOfStackTraceElement1;
    if (arrayOfStackTraceElement1.length > 256) {
      arrayOfStackTraceElement2 = new StackTraceElement[256];
      System.arraycopy(arrayOfStackTraceElement1, 0, arrayOfStackTraceElement2, 0, 128);
      System.arraycopy(arrayOfStackTraceElement1, arrayOfStackTraceElement1.length - 128, arrayOfStackTraceElement2, 128, 128);
      paramThrowable.setStackTrace(arrayOfStackTraceElement2);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Crash frames truncated from ");
      stringBuilder.append(arrayOfStackTraceElement1.length);
      stringBuilder.append(" to ");
      stringBuilder.append(256);
      stringBuilder.append(" frames.");
      AppCenterLog.warn("AppCenterCrashes", stringBuilder.toString());
    } 
    return getModelFramesFromStackTrace(arrayOfStackTraceElement2);
  }
  
  @NonNull
  private static List<StackFrame> getModelFramesFromStackTrace(@NonNull StackTraceElement[] paramArrayOfStackTraceElement) {
    ArrayList<StackFrame> arrayList = new ArrayList();
    int i = paramArrayOfStackTraceElement.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(getModelStackFrame(paramArrayOfStackTraceElement[b])); 
    return arrayList;
  }
  
  @NonNull
  private static StackFrame getModelStackFrame(StackTraceElement paramStackTraceElement) {
    StackFrame stackFrame = new StackFrame();
    stackFrame.setClassName(paramStackTraceElement.getClassName());
    stackFrame.setMethodName(paramStackTraceElement.getMethodName());
    stackFrame.setLineNumber(Integer.valueOf(paramStackTraceElement.getLineNumber()));
    stackFrame.setFileName(paramStackTraceElement.getFileName());
    return stackFrame;
  }
  
  @Nullable
  static File getStoredErrorLogFile(@NonNull UUID paramUUID) {
    return getStoredFile(paramUUID, ".json");
  }
  
  @NonNull
  public static File[] getStoredErrorLogFiles() {
    File[] arrayOfFile = getErrorStorageDirectory().listFiles(new FilenameFilter() {
          public boolean accept(File param1File, String param1String) {
            return param1String.endsWith(".json");
          }
        });
    if (arrayOfFile == null || arrayOfFile.length <= 0)
      arrayOfFile = new File[0]; 
    return arrayOfFile;
  }
  
  @Nullable
  private static File getStoredFile(@NonNull final UUID id, @NonNull final String extension) {
    File[] arrayOfFile = getErrorStorageDirectory().listFiles(new FilenameFilter() {
          public boolean accept(File param1File, String param1String) {
            boolean bool;
            if (param1String.startsWith(id.toString()) && param1String.endsWith(extension)) {
              bool = true;
            } else {
              bool = false;
            } 
            return bool;
          }
        });
    if (arrayOfFile != null && arrayOfFile.length > 0) {
      File file = arrayOfFile[0];
    } else {
      arrayOfFile = null;
    } 
    return (File)arrayOfFile;
  }
  
  @Nullable
  public static File getStoredThrowableFile(@NonNull UUID paramUUID) {
    return getStoredFile(paramUUID, ".throwable");
  }
  
  public static void removeStoredErrorLogFile(@NonNull UUID paramUUID) {
    File file = getStoredErrorLogFile(paramUUID);
    if (file != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Deleting error log file ");
      stringBuilder.append(file.getName());
      AppCenterLog.info("AppCenterCrashes", stringBuilder.toString());
      StorageHelper.InternalStorage.delete(file);
    } 
  }
  
  public static void removeStoredThrowableFile(@NonNull UUID paramUUID) {
    File file = getStoredThrowableFile(paramUUID);
    if (file != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Deleting throwable file ");
      stringBuilder.append(file.getName());
      AppCenterLog.info("AppCenterCrashes", stringBuilder.toString());
      StorageHelper.InternalStorage.delete(file);
    } 
  }
  
  @VisibleForTesting
  static void setErrorLogDirectory(File paramFile) {
    sErrorLogDirectory = paramFile;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/utils/ErrorLogHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */