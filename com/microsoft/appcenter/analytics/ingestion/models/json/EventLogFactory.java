package com.microsoft.appcenter.analytics.ingestion.models.json;

import com.microsoft.appcenter.analytics.ingestion.models.EventLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;

public class EventLogFactory implements LogFactory {
  public EventLog create() {
    return new EventLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/ingestion/models/json/EventLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */