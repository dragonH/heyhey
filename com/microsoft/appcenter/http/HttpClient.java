package com.microsoft.appcenter.http;

import java.io.Closeable;
import java.net.URL;
import java.util.Map;
import org.json.JSONException;

public interface HttpClient extends Closeable {
  ServiceCall callAsync(String paramString1, String paramString2, Map<String, String> paramMap, CallTemplate paramCallTemplate, ServiceCallback paramServiceCallback);
  
  public static interface CallTemplate {
    String buildRequestBody() throws JSONException;
    
    void onBeforeCalling(URL param1URL, Map<String, String> param1Map);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */