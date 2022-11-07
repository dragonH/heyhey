package com.microsoft.appcenter.persistence;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import java.io.Closeable;
import java.util.List;

public abstract class Persistence implements Closeable {
  static final int DEFAULT_CAPACITY = 300;
  
  private LogSerializer mLogSerializer;
  
  public abstract void clearPendingLogState();
  
  public abstract int countLogs(@NonNull String paramString);
  
  public abstract void deleteLogs(String paramString);
  
  public abstract void deleteLogs(@NonNull String paramString1, @NonNull String paramString2);
  
  LogSerializer getLogSerializer() {
    LogSerializer logSerializer = this.mLogSerializer;
    if (logSerializer != null)
      return logSerializer; 
    throw new IllegalStateException("logSerializer not configured");
  }
  
  @Nullable
  public abstract String getLogs(@NonNull String paramString, @IntRange(from = 0L) int paramInt, @NonNull List<Log> paramList);
  
  public abstract void putLog(@NonNull String paramString, @NonNull Log paramLog) throws PersistenceException;
  
  public void setLogSerializer(@NonNull LogSerializer paramLogSerializer) {
    this.mLogSerializer = paramLogSerializer;
  }
  
  public static class PersistenceException extends Exception {
    public PersistenceException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/persistence/Persistence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */