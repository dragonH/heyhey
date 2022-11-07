package com.microsoft.appcenter.ingestion;

import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import java.io.Closeable;
import java.util.UUID;

public interface Ingestion extends Closeable {
  ServiceCall sendAsync(String paramString, UUID paramUUID, LogContainer paramLogContainer, ServiceCallback paramServiceCallback) throws IllegalArgumentException;
  
  void setLogUrl(String paramString);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/Ingestion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */