package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.ManagedErrorLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;

public class ManagedErrorLogFactory implements LogFactory {
  private static final ManagedErrorLogFactory sInstance = new ManagedErrorLogFactory();
  
  public static ManagedErrorLogFactory getInstance() {
    return sInstance;
  }
  
  public ManagedErrorLog create() {
    return new ManagedErrorLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/ManagedErrorLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */