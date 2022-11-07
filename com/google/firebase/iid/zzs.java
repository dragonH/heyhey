package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class zzs {
  private static final long zzngf = TimeUnit.DAYS.toMillis(7L);
  
  private long timestamp;
  
  private String zzhtt;
  
  final String zzkoo;
  
  private zzs(String paramString1, String paramString2, long paramLong) {
    this.zzkoo = paramString1;
    this.zzhtt = paramString2;
    this.timestamp = paramLong;
  }
  
  static String zzc(String paramString1, String paramString2, long paramLong) {
    try {
      JSONObject jSONObject = new JSONObject();
      this();
      jSONObject.put("token", paramString1);
      jSONObject.put("appVersion", paramString2);
      jSONObject.put("timestamp", paramLong);
      return jSONObject.toString();
    } catch (JSONException jSONException) {
      String str = String.valueOf(jSONException);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 24);
      stringBuilder.append("Failed to encode token: ");
      stringBuilder.append(str);
      Log.w("InstanceID/Store", stringBuilder.toString());
      return null;
    } 
  }
  
  static zzs zzrf(String paramString) {
    StringBuilder stringBuilder;
    if (TextUtils.isEmpty(paramString))
      return null; 
    if (paramString.startsWith("{"))
      try {
        JSONObject jSONObject = new JSONObject();
        this(paramString);
        return new zzs(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
      } catch (JSONException jSONException) {
        String str = String.valueOf(jSONException);
        stringBuilder = new StringBuilder(str.length() + 23);
        stringBuilder.append("Failed to parse token: ");
        stringBuilder.append(str);
        Log.w("InstanceID/Store", stringBuilder.toString());
        return null;
      }  
    return new zzs((String)stringBuilder, null, 0L);
  }
  
  final boolean zzrg(String paramString) {
    return (System.currentTimeMillis() > this.timestamp + zzngf || !paramString.equals(this.zzhtt));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */