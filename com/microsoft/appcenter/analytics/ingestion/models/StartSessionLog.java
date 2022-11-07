package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.AbstractLog;

public class StartSessionLog extends AbstractLog {
  public static final String TYPE = "startSession";
  
  public String getType() {
    return "startSession";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/ingestion/models/StartSessionLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */