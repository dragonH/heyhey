package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.Thread;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.ArrayList;
import java.util.List;

public class ThreadFactory implements ModelFactory<Thread> {
  private static final ThreadFactory sInstance = new ThreadFactory();
  
  public static ThreadFactory getInstance() {
    return sInstance;
  }
  
  public Thread create() {
    return new Thread();
  }
  
  public List<Thread> createList(int paramInt) {
    return new ArrayList<Thread>(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/ThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */