package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.StartServiceLog;

public class StartServiceLogFactory implements LogFactory {
  public Log create() {
    return (Log)new StartServiceLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/StartServiceLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */