package com.microsoft.appcenter.http;

public interface ServiceCallback {
  void onCallFailed(Exception paramException);
  
  void onCallSucceeded(String paramString);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/ServiceCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */