package com.microsoft.appcenter.analytics.ingestion.models.json;

import com.microsoft.appcenter.analytics.ingestion.models.PageLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;

public class PageLogFactory implements LogFactory {
  public PageLog create() {
    return new PageLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/ingestion/models/json/PageLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */