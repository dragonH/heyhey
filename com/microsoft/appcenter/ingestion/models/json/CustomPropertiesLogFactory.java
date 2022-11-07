package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.CustomPropertiesLog;
import com.microsoft.appcenter.ingestion.models.Log;

public class CustomPropertiesLogFactory implements LogFactory {
  public Log create() {
    return (Log)new CustomPropertiesLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/CustomPropertiesLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */