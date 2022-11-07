package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.crashes.ingestion.models.json.ThreadFactory;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ManagedErrorLog extends AbstractErrorLog {
  private static final String EXCEPTION = "exception";
  
  private static final String THREADS = "threads";
  
  public static final String TYPE = "managedError";
  
  private Exception exception;
  
  private List<Thread> threads;
  
  public boolean equals(Object<Thread> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    Exception exception = this.exception;
    if ((exception != null) ? !exception.equals(((ManagedErrorLog)paramObject).exception) : (((ManagedErrorLog)paramObject).exception != null))
      return false; 
    List<Thread> list = this.threads;
    paramObject = (Object<Thread>)((ManagedErrorLog)paramObject).threads;
    if (list != null) {
      bool = list.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Exception getException() {
    return this.exception;
  }
  
  public List<Thread> getThreads() {
    return this.threads;
  }
  
  public String getType() {
    return "managedError";
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    Exception exception = this.exception;
    int j = 0;
    if (exception != null) {
      b = exception.hashCode();
    } else {
      b = 0;
    } 
    List<Thread> list = this.threads;
    if (list != null)
      j = list.hashCode(); 
    return (i * 31 + b) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    if (paramJSONObject.has("exception")) {
      JSONObject jSONObject = paramJSONObject.getJSONObject("exception");
      Exception exception = new Exception();
      exception.read(jSONObject);
      setException(exception);
    } 
    setThreads(JSONUtils.readArray(paramJSONObject, "threads", (ModelFactory)ThreadFactory.getInstance()));
  }
  
  public void setException(Exception paramException) {
    this.exception = paramException;
  }
  
  public void setThreads(List<Thread> paramList) {
    this.threads = paramList;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    if (getException() != null) {
      paramJSONStringer.key("exception").object();
      this.exception.write(paramJSONStringer);
      paramJSONStringer.endObject();
    } 
    JSONUtils.writeArray(paramJSONStringer, "threads", getThreads());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/ManagedErrorLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */