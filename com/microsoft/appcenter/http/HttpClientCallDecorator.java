package com.microsoft.appcenter.http;

import java.util.Map;

abstract class HttpClientCallDecorator implements Runnable, ServiceCall, ServiceCallback {
  final HttpClient.CallTemplate mCallTemplate;
  
  final HttpClient mDecoratedApi;
  
  final Map<String, String> mHeaders;
  
  final String mMethod;
  
  ServiceCall mServiceCall;
  
  final ServiceCallback mServiceCallback;
  
  final String mUrl;
  
  HttpClientCallDecorator(HttpClient paramHttpClient, String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate, ServiceCallback paramServiceCallback) {
    this.mDecoratedApi = paramHttpClient;
    this.mUrl = paramString1;
    this.mMethod = paramString2;
    this.mHeaders = paramMap;
    this.mCallTemplate = paramCallTemplate;
    this.mServiceCallback = paramServiceCallback;
  }
  
  public void cancel() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mServiceCall : Lcom/microsoft/appcenter/http/ServiceCall;
    //   6: invokeinterface cancel : ()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }
  
  public void onCallSucceeded(String paramString) {
    this.mServiceCallback.onCallSucceeded(paramString);
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield mDecoratedApi : Lcom/microsoft/appcenter/http/HttpClient;
    //   7: aload_0
    //   8: getfield mUrl : Ljava/lang/String;
    //   11: aload_0
    //   12: getfield mMethod : Ljava/lang/String;
    //   15: aload_0
    //   16: getfield mHeaders : Ljava/util/Map;
    //   19: aload_0
    //   20: getfield mCallTemplate : Lcom/microsoft/appcenter/http/HttpClient$CallTemplate;
    //   23: aload_0
    //   24: invokeinterface callAsync : (Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/microsoft/appcenter/http/HttpClient$CallTemplate;Lcom/microsoft/appcenter/http/ServiceCallback;)Lcom/microsoft/appcenter/http/ServiceCall;
    //   29: putfield mServiceCall : Lcom/microsoft/appcenter/http/ServiceCall;
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	35	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpClientCallDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */