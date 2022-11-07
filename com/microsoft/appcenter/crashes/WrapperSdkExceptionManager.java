package com.microsoft.appcenter.crashes;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.storage.StorageHelper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WrapperSdkExceptionManager {
  private static final String DATA_FILE_EXTENSION = ".dat";
  
  @VisibleForTesting
  static final Map<String, byte[]> sWrapperExceptionDataContainer = (Map)new HashMap<String, byte>();
  
  public static void deleteWrapperExceptionData(UUID paramUUID) {
    if (paramUUID == null) {
      AppCenterLog.error("AppCenterCrashes", "Failed to delete wrapper exception data: null errorId");
      return;
    } 
    File file = getFile(paramUUID);
    if (file.exists()) {
      if (loadWrapperExceptionData(paramUUID) == null)
        AppCenterLog.error("AppCenterCrashes", "Failed to delete wrapper exception data: data not found"); 
      StorageHelper.InternalStorage.delete(file);
    } 
  }
  
  private static File getFile(@NonNull UUID paramUUID) {
    File file = ErrorLogHelper.getErrorStorageDirectory();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramUUID.toString());
    stringBuilder.append(".dat");
    return new File(file, stringBuilder.toString());
  }
  
  public static AppCenterFuture<Collection<ErrorReport>> getUnprocessedErrorReports() {
    return Crashes.getInstance().getUnprocessedErrorReports();
  }
  
  public static byte[] loadWrapperExceptionData(UUID paramUUID) {
    if (paramUUID == null) {
      AppCenterLog.error("AppCenterCrashes", "Failed to load wrapper exception data: null errorId");
      return null;
    } 
    Map<String, byte[]> map = sWrapperExceptionDataContainer;
    byte[] arrayOfByte = map.get(paramUUID.toString());
    if (arrayOfByte != null)
      return arrayOfByte; 
    File file = getFile(paramUUID);
    try {
      byte[] arrayOfByte1 = (byte[])StorageHelper.InternalStorage.readObject(file);
      if (arrayOfByte1 != null)
        map.put(paramUUID.toString(), arrayOfByte1); 
      return arrayOfByte1;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (IOException iOException) {}
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot access wrapper exception data file ");
    stringBuilder.append(file.getName());
    AppCenterLog.error("AppCenterCrashes", stringBuilder.toString(), iOException);
    return null;
  }
  
  public static UUID saveWrapperException(Thread paramThread, Exception paramException, byte[] paramArrayOfbyte) {
    try {
      UUID uUID = Crashes.getInstance().saveUncaughtException(paramThread, null, paramException);
      if (uUID != null) {
        sWrapperExceptionDataContainer.put(uUID.toString(), paramArrayOfbyte);
        File file = getFile(uUID);
        StorageHelper.InternalStorage.writeObject(file, (Serializable)paramArrayOfbyte);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Saved raw wrapper exception data into ");
        stringBuilder.append(file);
        AppCenterLog.debug("AppCenterCrashes", stringBuilder.toString());
      } 
      return uUID;
    } catch (Exception exception) {
      AppCenterLog.error("AppCenterCrashes", "Failed to save wrapper exception data to file", exception);
      return null;
    } 
  }
  
  public static AppCenterFuture<Boolean> sendCrashReportsOrAwaitUserConfirmation(Collection<String> paramCollection) {
    return Crashes.getInstance().sendCrashReportsOrAwaitUserConfirmation(paramCollection);
  }
  
  public static void sendErrorAttachments(String paramString, Iterable<ErrorAttachmentLog> paramIterable) {
    Crashes.getInstance().sendErrorAttachments(paramString, paramIterable);
  }
  
  public static void setAutomaticProcessing(boolean paramBoolean) {
    Crashes.getInstance().setAutomaticProcessing(paramBoolean);
  }
  
  public static void trackException(Exception paramException) {
    Crashes.getInstance().queueException(paramException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/WrapperSdkExceptionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */