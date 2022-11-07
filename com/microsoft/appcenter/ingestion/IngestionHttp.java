package com.microsoft.appcenter.ingestion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.HttpClientNetworkStateHandler;
import com.microsoft.appcenter.http.HttpClientRetryer;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.http.ServiceCall;
import com.microsoft.appcenter.http.ServiceCallback;
import com.microsoft.appcenter.ingestion.models.LogContainer;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.NetworkStateHelper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;

public class IngestionHttp implements Ingestion {
  @VisibleForTesting
  static final String API_PATH = "/logs?api-version=1.0.0";
  
  @VisibleForTesting
  static final String APP_SECRET = "App-Secret";
  
  public static final String DEFAULT_LOG_URL = "https://in.appcenter.ms";
  
  @VisibleForTesting
  static final String INSTALL_ID = "Install-ID";
  
  private final HttpClient mHttpClient;
  
  private final LogSerializer mLogSerializer;
  
  private String mLogUrl;
  
  public IngestionHttp(@NonNull Context paramContext, @NonNull LogSerializer paramLogSerializer) {
    this.mLogSerializer = paramLogSerializer;
    this.mHttpClient = (HttpClient)new HttpClientNetworkStateHandler((HttpClient)new HttpClientRetryer((HttpClient)new DefaultHttpClient()), NetworkStateHelper.getSharedInstance(paramContext));
    this.mLogUrl = "https://in.appcenter.ms";
  }
  
  public void close() throws IOException {
    this.mHttpClient.close();
  }
  
  public ServiceCall sendAsync(String paramString, UUID paramUUID, LogContainer paramLogContainer, ServiceCallback paramServiceCallback) throws IllegalArgumentException {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("Install-ID", paramUUID.toString());
    hashMap.put("App-Secret", paramString);
    IngestionCallTemplate ingestionCallTemplate = new IngestionCallTemplate(this.mLogSerializer, paramLogContainer);
    HttpClient httpClient = this.mHttpClient;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mLogUrl);
    stringBuilder.append("/logs?api-version=1.0.0");
    return httpClient.callAsync(stringBuilder.toString(), "POST", hashMap, ingestionCallTemplate, paramServiceCallback);
  }
  
  public void setLogUrl(@NonNull String paramString) {
    this.mLogUrl = paramString;
  }
  
  private static class IngestionCallTemplate implements HttpClient.CallTemplate {
    private final LogContainer mLogContainer;
    
    private final LogSerializer mLogSerializer;
    
    IngestionCallTemplate(LogSerializer param1LogSerializer, LogContainer param1LogContainer) {
      this.mLogSerializer = param1LogSerializer;
      this.mLogContainer = param1LogContainer;
    }
    
    public String buildRequestBody() throws JSONException {
      return this.mLogSerializer.serializeContainer(this.mLogContainer);
    }
    
    public void onBeforeCalling(URL param1URL, Map<String, String> param1Map) {
      if (AppCenterLog.getLogLevel() <= 2) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Calling ");
        stringBuilder2.append(param1URL);
        stringBuilder2.append("...");
        AppCenterLog.verbose("AppCenter", stringBuilder2.toString());
        HashMap<String, String> hashMap = new HashMap<String, String>(param1Map);
        String str = hashMap.get("App-Secret");
        if (str != null)
          hashMap.put("App-Secret", HttpUtils.hideSecret(str)); 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Headers: ");
        stringBuilder1.append(hashMap);
        AppCenterLog.verbose("AppCenter", stringBuilder1.toString());
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/IngestionHttp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */