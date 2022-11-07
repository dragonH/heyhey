package com.microsoft.appcenter.ingestion.models.json;

import android.support.annotation.NonNull;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import org.json.JSONException;

public interface LogSerializer {
  void addLogFactory(@NonNull String paramString, @NonNull LogFactory paramLogFactory);
  
  @NonNull
  LogContainer deserializeContainer(@NonNull String paramString) throws JSONException;
  
  @NonNull
  Log deserializeLog(@NonNull String paramString) throws JSONException;
  
  @NonNull
  String serializeContainer(@NonNull LogContainer paramLogContainer) throws JSONException;
  
  @NonNull
  String serializeLog(@NonNull Log paramLog) throws JSONException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/LogSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */