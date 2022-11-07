package com.microsoft.appcenter.http;

import java.io.IOException;

abstract class HttpClientDecorator implements HttpClient {
  final HttpClient mDecoratedApi;
  
  HttpClientDecorator(HttpClient paramHttpClient) {
    this.mDecoratedApi = paramHttpClient;
  }
  
  public void close() throws IOException {
    this.mDecoratedApi.close();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpClientDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */