package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.crashes.ingestion.models.json.ExceptionFactory;
import com.microsoft.appcenter.crashes.ingestion.models.json.StackFrameFactory;
import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import com.microsoft.appcenter.ingestion.models.json.ModelFactory;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Exception implements Model {
  private static final String INNER_EXCEPTIONS = "innerExceptions";
  
  private static final String MESSAGE = "message";
  
  private static final String STACK_TRACE = "stackTrace";
  
  private static final String WRAPPER_SDK_NAME = "wrapperSdkName";
  
  private List<StackFrame> frames;
  
  private List<Exception> innerExceptions;
  
  private String message;
  
  private String stackTrace;
  
  private String type;
  
  private String wrapperSdkName;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    String str2 = this.type;
    if ((str2 != null) ? !str2.equals(((Exception)paramObject).type) : (((Exception)paramObject).type != null))
      return false; 
    str2 = this.message;
    if ((str2 != null) ? !str2.equals(((Exception)paramObject).message) : (((Exception)paramObject).message != null))
      return false; 
    str2 = this.stackTrace;
    if ((str2 != null) ? !str2.equals(((Exception)paramObject).stackTrace) : (((Exception)paramObject).stackTrace != null))
      return false; 
    List<StackFrame> list1 = this.frames;
    if ((list1 != null) ? !list1.equals(((Exception)paramObject).frames) : (((Exception)paramObject).frames != null))
      return false; 
    List<Exception> list = this.innerExceptions;
    if ((list != null) ? !list.equals(((Exception)paramObject).innerExceptions) : (((Exception)paramObject).innerExceptions != null))
      return false; 
    String str1 = this.wrapperSdkName;
    paramObject = ((Exception)paramObject).wrapperSdkName;
    if (str1 != null) {
      bool = str1.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public List<StackFrame> getFrames() {
    return this.frames;
  }
  
  public List<Exception> getInnerExceptions() {
    return this.innerExceptions;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getStackTrace() {
    return this.stackTrace;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getWrapperSdkName() {
    return this.wrapperSdkName;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    byte b5;
    String str2 = this.type;
    int i = 0;
    if (str2 != null) {
      b1 = str2.hashCode();
    } else {
      b1 = 0;
    } 
    str2 = this.message;
    if (str2 != null) {
      b2 = str2.hashCode();
    } else {
      b2 = 0;
    } 
    str2 = this.stackTrace;
    if (str2 != null) {
      b3 = str2.hashCode();
    } else {
      b3 = 0;
    } 
    List<StackFrame> list1 = this.frames;
    if (list1 != null) {
      b4 = list1.hashCode();
    } else {
      b4 = 0;
    } 
    List<Exception> list = this.innerExceptions;
    if (list != null) {
      b5 = list.hashCode();
    } else {
      b5 = 0;
    } 
    String str1 = this.wrapperSdkName;
    if (str1 != null)
      i = str1.hashCode(); 
    return ((((b1 * 31 + b2) * 31 + b3) * 31 + b4) * 31 + b5) * 31 + i;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    setType(paramJSONObject.optString("type", null));
    setMessage(paramJSONObject.optString("message", null));
    setStackTrace(paramJSONObject.optString("stackTrace", null));
    setFrames(JSONUtils.readArray(paramJSONObject, "frames", (ModelFactory)StackFrameFactory.getInstance()));
    setInnerExceptions(JSONUtils.readArray(paramJSONObject, "innerExceptions", (ModelFactory)ExceptionFactory.getInstance()));
    setWrapperSdkName(paramJSONObject.optString("wrapperSdkName", null));
  }
  
  public void setFrames(List<StackFrame> paramList) {
    this.frames = paramList;
  }
  
  public void setInnerExceptions(List<Exception> paramList) {
    this.innerExceptions = paramList;
  }
  
  public void setMessage(String paramString) {
    this.message = paramString;
  }
  
  public void setStackTrace(String paramString) {
    this.stackTrace = paramString;
  }
  
  public void setType(String paramString) {
    this.type = paramString;
  }
  
  public void setWrapperSdkName(String paramString) {
    this.wrapperSdkName = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    JSONUtils.write(paramJSONStringer, "type", getType());
    JSONUtils.write(paramJSONStringer, "message", getMessage());
    JSONUtils.write(paramJSONStringer, "stackTrace", getStackTrace());
    JSONUtils.writeArray(paramJSONStringer, "frames", getFrames());
    JSONUtils.writeArray(paramJSONStringer, "innerExceptions", getInnerExceptions());
    JSONUtils.write(paramJSONStringer, "wrapperSdkName", getWrapperSdkName());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/Exception.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */