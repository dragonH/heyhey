package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.HandledErrorLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;

public class HandledErrorLogFactory implements LogFactory {
  private static final HandledErrorLogFactory sInstance = new HandledErrorLogFactory();
  
  public static HandledErrorLogFactory getInstance() {
    return sInstance;
  }
  
  public HandledErrorLog create() {
    return new HandledErrorLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/HandledErrorLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */