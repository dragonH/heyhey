package com.microsoft.windowsazure.messaging;

import android.os.Build;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class Connection {
  private static final String API_VERSION = "2014-09";
  
  private static final String API_VERSION_KEY = "api-version";
  
  private static final String AUTHORIZATION_HEADER = "Authorization";
  
  private static final String ENDPOINT_KEY = "Endpoint";
  
  private static final int EXPIRE_MINUTES = 5;
  
  private static final String SDK_VERSION = "2014-09";
  
  private static final String SHARED_ACCESS_KEY = "SharedAccessKey";
  
  private static final String SHARED_ACCESS_KEY_NAME = "SharedAccessKeyName";
  
  private static final String UTC_TIME_ZONE = "UTC";
  
  private static final String UTF8_ENCODING = "UTF-8";
  
  private Map<String, String> mConnectionData;
  
  public Connection(String paramString) {
    this.mConnectionData = ConnectionStringParser.parse(paramString);
  }
  
  private String AddApiVersionToUrl(String paramString) {
    if (URI.create(paramString).getQuery() == null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("?");
      paramString = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("&");
      paramString = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("api-version");
    stringBuilder.append("=");
    stringBuilder.append("2014-09");
    return stringBuilder.toString();
  }
  
  private void addAuthorizationHeader(HttpURLConnection paramHttpURLConnection) throws InvalidKeyException {
    paramHttpURLConnection.setRequestProperty("Authorization", generateAuthToken(paramHttpURLConnection.getURL().toString()));
  }
  
  private String executeRequest(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) throws Exception {
    StringBuilder stringBuilder;
    addAuthorizationHeader(paramHttpURLConnection);
    try {
      paramHttpURLConnection.setRequestProperty("User-Agent", getUserAgent());
      boolean bool = Utils.isNullOrWhiteSpace(paramString2);
      boolean bool1 = true;
      if (bool) {
        paramHttpURLConnection.connect();
      } else {
        paramHttpURLConnection.setDoOutput(true);
        OutputStream outputStream = paramHttpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
        this(outputStream, "UTF-8");
        this(outputStreamWriter);
        bufferedWriter.write(paramString2);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
      } 
      int i = paramHttpURLConnection.getResponseCode();
      String str2 = getResponseContent(paramHttpURLConnection);
      String str1 = null;
      paramString2 = null;
      if (paramString1 != null) {
        if (paramHttpURLConnection.getHeaderField(paramString1) == null) {
          paramString2 = str1;
        } else {
          paramString2 = paramHttpURLConnection.getHeaderField(paramString1);
          bool1 = false;
        } 
        paramHttpURLConnection.disconnect();
        if (i >= 200 && i < 300) {
          if (!bool1)
            return str; 
          stringBuilder = new StringBuilder();
          stringBuilder.append("The '");
          stringBuilder.append(paramString1);
          stringBuilder.append("' header does not present in collection");
          throw new NotificationHubException(stringBuilder.toString(), i);
        } 
        if (i != 404) {
          if (i != 401) {
            if (i == 410)
              throw new RegistrationGoneException(); 
            throw new NotificationHubException(str2, i);
          } 
          throw new NotificationHubUnauthorizedException();
        } 
        throw new NotificationHubResourceNotFoundException();
      } 
    } finally {
      if (stringBuilder != null)
        stringBuilder.disconnect(); 
    } 
  }
  
  private String generateAuthToken(String paramString) throws InvalidKeyException {
    String str = this.mConnectionData.get("SharedAccessKeyName");
    if (!Utils.isNullOrWhiteSpace(str)) {
      String str1 = this.mConnectionData.get("SharedAccessKey");
      if (!Utils.isNullOrWhiteSpace(str1)) {
        Mac mac;
        try {
          String str3 = URLEncoder.encode(paramString, "UTF-8").toLowerCase(Locale.ENGLISH);
          paramString = str3;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {}
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(12, 5);
        long l = calendar.getTimeInMillis() / 1000L;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append('\n');
        stringBuilder.append(l);
        byte[] arrayOfByte = stringBuilder.toString().getBytes();
        stringBuilder = null;
        try {
          Mac mac1 = Mac.getInstance("HmacSHA256");
          mac = mac1;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {}
        mac.init(new SecretKeySpec(str1.getBytes(), mac.getAlgorithm()));
        String str2 = Base64.encodeToString(mac.doFinal(arrayOfByte), 0).trim();
        try {
          String str3 = URLEncoder.encode(str2, "UTF-8");
          str2 = str3;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {}
      } 
      throw new AssertionError("SharedAccessKey");
    } 
    throw new AssertionError("SharedAccessKeyName");
  }
  
  private String getResponseContent(HttpURLConnection paramHttpURLConnection) throws IOException {
    try {
      InputStream inputStream = paramHttpURLConnection.getInputStream();
      BufferedReader bufferedReader = new BufferedReader();
      InputStreamReader inputStreamReader = new InputStreamReader();
      this(inputStream);
      this(inputStreamReader);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      String str = bufferedReader.readLine();
      null = str;
      if (str == null)
        return null; 
      while (null != null) {
        stringBuilder.append(null);
        stringBuilder.append('\n');
        null = bufferedReader.readLine();
      } 
      return stringBuilder.toString();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private String getUserAgent() {
    return String.format("NOTIFICATIONHUBS/%s (api-origin=%s; os=%s; os_version=%s;)", new Object[] { "2014-09", PnsSpecificRegistrationFactory.getInstance().getAPIOrigin(), "Android", Build.VERSION.RELEASE });
  }
  
  public String executeRequest(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, AbstractMap.SimpleEntry<String, String>... paramVarArgs) throws Exception {
    URI uRI = URI.create(this.mConnectionData.get("Endpoint"));
    String str1 = uRI.getScheme();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("https");
    stringBuilder2.append(uRI.toString().substring(str1.length()));
    str1 = stringBuilder2.toString();
    String str2 = str1;
    if (!str1.endsWith("/")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("/");
      str2 = stringBuilder.toString();
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append(paramString1);
    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(AddApiVersionToUrl(stringBuilder1.toString()))).openConnection();
    httpURLConnection.setRequestMethod(paramString4);
    httpURLConnection.setRequestProperty("Content-Type", paramString3);
    if (paramVarArgs != null) {
      int i = paramVarArgs.length;
      for (byte b = 0; b < i; b++) {
        AbstractMap.SimpleEntry<String, String> simpleEntry = paramVarArgs[b];
        httpURLConnection.setRequestProperty(simpleEntry.getKey(), simpleEntry.getValue());
      } 
    } 
    return executeRequest(httpURLConnection, paramString5, paramString2);
  }
  
  public String executeRequest(String paramString1, String paramString2, String paramString3, String paramString4, AbstractMap.SimpleEntry<String, String>... paramVarArgs) throws Exception {
    return executeRequest(paramString1, paramString2, paramString3, paramString4, null, paramVarArgs);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/Connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */