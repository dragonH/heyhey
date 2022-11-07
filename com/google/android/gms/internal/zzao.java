package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public final class zzao implements zzan {
  private final zzap zzcc = null;
  
  private final SSLSocketFactory zzcd = null;
  
  public zzao() {
    this(null);
  }
  
  private zzao(zzap paramzzap) {
    this(null, null);
  }
  
  private zzao(zzap paramzzap, SSLSocketFactory paramSSLSocketFactory) {}
  
  private static HttpEntity zza(HttpURLConnection paramHttpURLConnection) {
    InputStream inputStream;
    BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
    try {
      inputStream = paramHttpURLConnection.getInputStream();
    } catch (IOException iOException) {
      inputStream = paramHttpURLConnection.getErrorStream();
    } 
    basicHttpEntity.setContent(inputStream);
    basicHttpEntity.setContentLength(paramHttpURLConnection.getContentLength());
    basicHttpEntity.setContentEncoding(paramHttpURLConnection.getContentEncoding());
    basicHttpEntity.setContentType(paramHttpURLConnection.getContentType());
    return (HttpEntity)basicHttpEntity;
  }
  
  private static void zza(HttpURLConnection paramHttpURLConnection, zzp<?> paramzzp) throws IOException, zza {
    byte[] arrayOfByte = paramzzp.zzg();
    if (arrayOfByte != null) {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.addRequestProperty("Content-Type", zzp.zzf());
      DataOutputStream dataOutputStream = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      dataOutputStream.write(arrayOfByte);
      dataOutputStream.close();
    } 
  }
  
  public final HttpResponse zza(zzp<?> paramzzp, Map<String, String> paramMap) throws IOException, zza {
    String str1;
    String str2;
    String str4 = paramzzp.getUrl();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.putAll(paramzzp.getHeaders());
    hashMap.putAll(paramMap);
    zzap zzap1 = this.zzcc;
    String str3 = str4;
    if (zzap1 != null) {
      str3 = zzap1.zzg(str4);
      if (str3 == null) {
        str1 = String.valueOf(str4);
        if (str1.length() != 0) {
          str1 = "URL blocked by rewriter: ".concat(str1);
        } else {
          str1 = new String("URL blocked by rewriter: ");
        } 
        throw new IOException(str1);
      } 
    } 
    URL uRL = new URL(str3);
    HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
    httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
    int i = str1.zzi();
    httpURLConnection.setConnectTimeout(i);
    httpURLConnection.setReadTimeout(i);
    httpURLConnection.setUseCaches(false);
    i = 1;
    httpURLConnection.setDoInput(true);
    "https".equals(uRL.getProtocol());
    for (String str : hashMap.keySet())
      httpURLConnection.addRequestProperty(str, (String)hashMap.get(str)); 
    switch (str1.getMethod()) {
      default:
        throw new IllegalStateException("Unknown method type.");
      case 7:
        str2 = "PATCH";
        httpURLConnection.setRequestMethod(str2);
        zza(httpURLConnection, (zzp<?>)str1);
        break;
      case 6:
        str2 = "TRACE";
        httpURLConnection.setRequestMethod(str2);
        break;
      case 5:
        str2 = "OPTIONS";
        httpURLConnection.setRequestMethod(str2);
        break;
      case 4:
        str2 = "HEAD";
        httpURLConnection.setRequestMethod(str2);
        break;
      case 3:
        str2 = "DELETE";
        httpURLConnection.setRequestMethod(str2);
        break;
      case 2:
        str2 = "PUT";
        httpURLConnection.setRequestMethod(str2);
        zza(httpURLConnection, (zzp<?>)str1);
        break;
      case 1:
        str2 = "POST";
        httpURLConnection.setRequestMethod(str2);
        zza(httpURLConnection, (zzp<?>)str1);
        break;
      case 0:
        str2 = "GET";
        httpURLConnection.setRequestMethod(str2);
        break;
      case -1:
        break;
    } 
    ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
    if (httpURLConnection.getResponseCode() != -1) {
      BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
      BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)basicStatusLine);
      int j = str1.getMethod();
      int k = basicStatusLine.getStatusCode();
      if (j == 4 || (100 <= k && k < 200) || k == 204 || k == 304)
        i = 0; 
      if (i != 0)
        basicHttpResponse.setEntity(zza(httpURLConnection)); 
      for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
        if (entry.getKey() != null)
          basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), ((List<String>)entry.getValue()).get(0))); 
      } 
      return (HttpResponse)basicHttpResponse;
    } 
    IOException iOException = new IOException("Could not retrieve response code from HttpUrlConnection.");
    throw iOException;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */