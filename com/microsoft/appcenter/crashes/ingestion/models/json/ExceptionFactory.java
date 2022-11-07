package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.Exception;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.ArrayList;
import java.util.List;

public class ExceptionFactory implements ModelFactory<Exception> {
  private static final ExceptionFactory sInstance = new ExceptionFactory();
  
  public static ExceptionFactory getInstance() {
    return sInstance;
  }
  
  public Exception create() {
    return new Exception();
  }
  
  public List<Exception> createList(int paramInt) {
    return new ArrayList<Exception>(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/ExceptionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */