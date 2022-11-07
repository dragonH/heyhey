package com.microsoft.appcenter.http;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.IOException;

public class HttpException extends IOException {
  private final String payload;
  
  private final int statusCode;
  
  public HttpException(int paramInt) {
    this(paramInt, "");
  }
  
  public HttpException(int paramInt, @NonNull String paramString) {
    super(getDetailMessage(paramInt, paramString));
    this.payload = paramString;
    this.statusCode = paramInt;
  }
  
  @NonNull
  private static String getDetailMessage(int paramInt, @NonNull String paramString) {
    if (TextUtils.isEmpty(paramString))
      return String.valueOf(paramInt); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append(" - ");
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.statusCode != ((HttpException)paramObject).statusCode || !this.payload.equals(((HttpException)paramObject).payload))
      bool = false; 
    return bool;
  }
  
  @NonNull
  public String getPayload() {
    return this.payload;
  }
  
  public int getStatusCode() {
    return this.statusCode;
  }
  
  public int hashCode() {
    return this.statusCode * 31 + this.payload.hashCode();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */