package com.microsoft.appcenter.crashes.ingestion.models.json;

import com.microsoft.appcenter.crashes.ingestion.models.StackFrame;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.ArrayList;
import java.util.List;

public class StackFrameFactory implements ModelFactory<StackFrame> {
  private static final StackFrameFactory sInstance = new StackFrameFactory();
  
  public static StackFrameFactory getInstance() {
    return sInstance;
  }
  
  public StackFrame create() {
    return new StackFrame();
  }
  
  public List<StackFrame> createList(int paramInt) {
    return new ArrayList<StackFrame>(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/json/StackFrameFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */