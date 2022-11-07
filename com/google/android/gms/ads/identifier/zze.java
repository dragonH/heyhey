package com.google.android.gms.ads.identifier;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public final class zze {
  public final void zzb(Map<String, String> paramMap) {
    StringBuilder stringBuilder;
    String str1;
    String str2;
    Uri.Builder builder = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
    for (String null : paramMap.keySet())
      builder.appendQueryParameter(str2, paramMap.get(str2)); 
    String str3 = builder.build().toString();
    try {
      URL uRL = new URL();
      this(str3);
      HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
      try {
        int i = httpURLConnection.getResponseCode();
        if (i < 200 || i >= 300) {
          int j = String.valueOf(str3).length();
          stringBuilder = new StringBuilder();
          this(j + 65);
          stringBuilder.append("Received non-success response code ");
          stringBuilder.append(i);
          stringBuilder.append(" from pinging URL: ");
          stringBuilder.append(str3);
          Log.w("HttpUrlPinger", stringBuilder.toString());
        } 
        return;
      } finally {
        httpURLConnection.disconnect();
      } 
    } catch (IndexOutOfBoundsException null) {
      str1 = runtimeException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 32 + String.valueOf(str1).length());
      str2 = "Error while parsing ping URL: ";
    } catch (IOException iOException) {
      str1 = iOException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 27 + String.valueOf(str1).length());
      str2 = "Error while pinging URL: ";
    } catch (RuntimeException runtimeException) {
      str1 = runtimeException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 27 + String.valueOf(str1).length());
      str2 = "Error while pinging URL: ";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(str3);
    stringBuilder.append(". ");
    stringBuilder.append(str1);
    Log.w("HttpUrlPinger", stringBuilder.toString(), runtimeException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/ads/identifier/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */