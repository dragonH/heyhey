package com.microsoft.appcenter.http;

import android.net.TrafficStats;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.HandlerUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

public class DefaultHttpClient implements HttpClient {
  private static final String CHARSET_NAME = "UTF-8";
  
  private static final int CONNECT_TIMEOUT = 60000;
  
  private static final String CONTENT_TYPE_KEY = "Content-Type";
  
  private static final String CONTENT_TYPE_VALUE = "application/json";
  
  private static final int DEFAULT_STRING_BUILDER_CAPACITY = 16;
  
  public static final String METHOD_GET = "GET";
  
  public static final String METHOD_POST = "POST";
  
  private static final int READ_BUFFER_SIZE = 1024;
  
  private static final int READ_TIMEOUT = 20000;
  
  private static final int THREAD_STATS_TAG = -667034599;
  
  private static String doCall(String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate) throws Exception {
    TrafficStats.setThreadStatsTag(-667034599);
    try {
      paramString1 = doHttpCall(paramString1, paramString2, paramMap, paramCallTemplate);
      return paramString1;
    } finally {
      TrafficStats.clearThreadStatsTag();
    } 
  }
  
  private static String doHttpCall(String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate) throws Exception {
    null = new URL(paramString1);
    HttpURLConnection httpURLConnection = (HttpURLConnection)null.openConnection();
    try {
      httpURLConnection.setConnectTimeout(60000);
      httpURLConnection.setReadTimeout(20000);
      httpURLConnection.setRequestMethod(paramString2);
      httpURLConnection.setRequestProperty("Content-Type", "application/json");
      for (Map.Entry<String, String> entry : paramMap.entrySet())
        httpURLConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue()); 
      if (paramCallTemplate != null)
        paramCallTemplate.onBeforeCalling(null, paramMap); 
      boolean bool = paramString2.equals("POST");
      if (bool && paramCallTemplate != null) {
        String str1 = paramCallTemplate.buildRequestBody();
        AppCenterLog.verbose("AppCenter", str1);
        byte[] arrayOfByte = str1.getBytes("UTF-8");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(arrayOfByte.length);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(arrayOfByte);
        outputStream.close();
      } 
      int i = httpURLConnection.getResponseCode();
      paramString2 = dump(httpURLConnection);
      String str = httpURLConnection.getHeaderField("Content-Type");
      if (str == null || str.startsWith("text/") || str.startsWith("application/")) {
        str = paramString2;
      } else {
        str = "<binary>";
      } 
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("HTTP response status=");
      stringBuilder.append(i);
      stringBuilder.append(" payload=");
      stringBuilder.append(str);
      AppCenterLog.verbose("AppCenter", stringBuilder.toString());
      if (i >= 200 && i < 300)
        return paramString2; 
      HttpException httpException = new HttpException();
      this(i, paramString2);
      throw httpException;
    } finally {
      httpURLConnection.disconnect();
    } 
  }
  
  private static String dump(HttpURLConnection paramHttpURLConnection) throws IOException {
    InputStream inputStream;
    null = new StringBuilder(Math.max(paramHttpURLConnection.getContentLength(), 16));
    int i = paramHttpURLConnection.getResponseCode();
    if (i >= 200 && i < 400) {
      inputStream = paramHttpURLConnection.getInputStream();
    } else {
      inputStream = inputStream.getErrorStream();
    } 
    try {
      InputStreamReader inputStreamReader = new InputStreamReader();
      this(inputStream, "UTF-8");
      char[] arrayOfChar = new char[1024];
      while (true) {
        i = inputStreamReader.read(arrayOfChar);
        if (i > 0) {
          null.append(arrayOfChar, 0, i);
          continue;
        } 
        return null.toString();
      } 
    } finally {
      inputStream.close();
    } 
  }
  
  public ServiceCall callAsync(String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate, final ServiceCallback serviceCallback) {
    final Call call = new Call(paramString1, paramString2, paramMap, paramCallTemplate, serviceCallback);
    try {
      call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } catch (RejectedExecutionException rejectedExecutionException) {
      HandlerUtils.runOnUiThread(new Runnable() {
            public void run() {
              serviceCallback.onCallFailed(e);
            }
          });
    } 
    return new ServiceCall() {
        public void cancel() {
          if (!call.isCancelled())
            call.cancel(true); 
        }
      };
  }
  
  public void close() throws IOException {}
  
  @VisibleForTesting
  static class Call extends AsyncTask<Void, Void, Object> {
    private final HttpClient.CallTemplate mCallTemplate;
    
    private final Map<String, String> mHeaders;
    
    private final String mMethod;
    
    private final ServiceCallback mServiceCallback;
    
    private final String mUrl;
    
    public Call(String param1String1, String param1String2, Map<String, String> param1Map, HttpClient.CallTemplate param1CallTemplate, ServiceCallback param1ServiceCallback) {
      this.mUrl = param1String1;
      this.mMethod = param1String2;
      this.mHeaders = param1Map;
      this.mCallTemplate = param1CallTemplate;
      this.mServiceCallback = param1ServiceCallback;
    }
    
    protected Object doInBackground(Void... param1VarArgs) {
      try {
        return DefaultHttpClient.doCall(this.mUrl, this.mMethod, this.mHeaders, this.mCallTemplate);
      } catch (Exception null) {
        return null;
      } 
    }
    
    protected void onPostExecute(Object param1Object) {
      if (param1Object instanceof Exception) {
        this.mServiceCallback.onCallFailed((Exception)param1Object);
      } else {
        this.mServiceCallback.onCallSucceeded(param1Object.toString());
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/DefaultHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */