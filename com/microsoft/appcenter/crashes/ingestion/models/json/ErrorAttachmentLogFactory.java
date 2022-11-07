package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;

public class ErrorAttachmentLogFactory implements LogFactory {
  private static final ErrorAttachmentLogFactory sInstance = new ErrorAttachmentLogFactory();
  
  public static ErrorAttachmentLogFactory getInstance() {
    return sInstance;
  }
  
  public ErrorAttachmentLog create() {
    return new ErrorAttachmentLog();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/ErrorAttachmentLogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */