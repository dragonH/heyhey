package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.ingestion.models.Model;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class StackFrame implements Model {
  private static final String CLASS_NAME = "className";
  
  private static final String FILE_NAME = "fileName";
  
  private static final String LINE_NUMBER = "lineNumber";
  
  private static final String METHOD_NAME = "methodName";
  
  private String className;
  
  private String fileName;
  
  private Integer lineNumber;
  
  private String methodName;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    String str2 = this.className;
    if ((str2 != null) ? !str2.equals(((StackFrame)paramObject).className) : (((StackFrame)paramObject).className != null))
      return false; 
    str2 = this.methodName;
    if ((str2 != null) ? !str2.equals(((StackFrame)paramObject).methodName) : (((StackFrame)paramObject).methodName != null))
      return false; 
    Integer integer = this.lineNumber;
    if ((integer != null) ? !integer.equals(((StackFrame)paramObject).lineNumber) : (((StackFrame)paramObject).lineNumber != null))
      return false; 
    String str1 = this.fileName;
    paramObject = ((StackFrame)paramObject).fileName;
    if (str1 != null) {
      bool = str1.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public String getClassName() {
    return this.className;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public Integer getLineNumber() {
    return this.lineNumber;
  }
  
  public String getMethodName() {
    return this.methodName;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    String str2 = this.className;
    int i = 0;
    if (str2 != null) {
      b1 = str2.hashCode();
    } else {
      b1 = 0;
    } 
    str2 = this.methodName;
    if (str2 != null) {
      b2 = str2.hashCode();
    } else {
      b2 = 0;
    } 
    Integer integer = this.lineNumber;
    if (integer != null) {
      b3 = integer.hashCode();
    } else {
      b3 = 0;
    } 
    String str1 = this.fileName;
    if (str1 != null)
      i = str1.hashCode(); 
    return ((b1 * 31 + b2) * 31 + b3) * 31 + i;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    setClassName(paramJSONObject.optString("className", null));
    setMethodName(paramJSONObject.optString("methodName", null));
    setLineNumber(JSONUtils.readInteger(paramJSONObject, "lineNumber"));
    setFileName(paramJSONObject.optString("fileName", null));
  }
  
  public void setClassName(String paramString) {
    this.className = paramString;
  }
  
  public void setFileName(String paramString) {
    this.fileName = paramString;
  }
  
  public void setLineNumber(Integer paramInteger) {
    this.lineNumber = paramInteger;
  }
  
  public void setMethodName(String paramString) {
    this.methodName = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    JSONUtils.write(paramJSONStringer, "className", getClassName());
    JSONUtils.write(paramJSONStringer, "methodName", getMethodName());
    JSONUtils.write(paramJSONStringer, "lineNumber", getLineNumber());
    JSONUtils.write(paramJSONStringer, "fileName", getFileName());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/StackFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */