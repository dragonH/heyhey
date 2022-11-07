package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.crashes.ingestion.models.json.StackFrameFactory;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Thread implements Model {
  private List<StackFrame> frames;
  
  private long id;
  
  private String name;
  
  public boolean equals(Object<StackFrame> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.id != ((Thread)paramObject).id)
      return false; 
    String str = this.name;
    if ((str != null) ? !str.equals(((Thread)paramObject).name) : (((Thread)paramObject).name != null))
      return false; 
    List<StackFrame> list = this.frames;
    paramObject = (Object<StackFrame>)((Thread)paramObject).frames;
    if (list != null) {
      bool = list.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public List<StackFrame> getFrames() {
    return this.frames;
  }
  
  public long getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int hashCode() {
    byte b;
    long l = this.id;
    int i = (int)(l ^ l >>> 32L);
    String str = this.name;
    int j = 0;
    if (str != null) {
      b = str.hashCode();
    } else {
      b = 0;
    } 
    List<StackFrame> list = this.frames;
    if (list != null)
      j = list.hashCode(); 
    return (i * 31 + b) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    setId(paramJSONObject.getLong("id"));
    setName(paramJSONObject.optString("name", null));
    setFrames(JSONUtils.readArray(paramJSONObject, "frames", (ModelFactory)StackFrameFactory.getInstance()));
  }
  
  public void setFrames(List<StackFrame> paramList) {
    this.frames = paramList;
  }
  
  public void setId(long paramLong) {
    this.id = paramLong;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    JSONUtils.write(paramJSONStringer, "id", Long.valueOf(getId()));
    JSONUtils.write(paramJSONStringer, "name", getName());
    JSONUtils.writeArray(paramJSONStringer, "frames", getFrames());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/Thread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */