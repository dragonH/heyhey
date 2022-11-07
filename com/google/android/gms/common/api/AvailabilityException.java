package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;
import java.util.Iterator;

public class AvailabilityException extends Exception {
  private final ArrayMap<zzh<?>, ConnectionResult> zzfgj;
  
  public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> paramArrayMap) {
    this.zzfgj = paramArrayMap;
  }
  
  public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> paramGoogleApi) {
    boolean bool;
    zzh<? extends Api.ApiOptions> zzh = paramGoogleApi.zzafk();
    if (this.zzfgj.get(zzh) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "The given API was not part of the availability request.");
    return (ConnectionResult)this.zzfgj.get(zzh);
  }
  
  public String getMessage() {
    String str;
    ArrayList<String> arrayList = new ArrayList();
    Iterator<zzh> iterator = this.zzfgj.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      zzh zzh = iterator.next();
      ConnectionResult connectionResult = (ConnectionResult)this.zzfgj.get(zzh);
      if (connectionResult.isSuccess())
        bool = false; 
      String str1 = zzh.zzafv();
      String str2 = String.valueOf(connectionResult);
      StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 2 + str2.length());
      stringBuilder1.append(str1);
      stringBuilder1.append(": ");
      stringBuilder1.append(str2);
      arrayList.add(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (bool) {
      str = "None of the queried APIs are available. ";
    } else {
      str = "Some of the queried APIs are unavailable. ";
    } 
    stringBuilder.append(str);
    stringBuilder.append(TextUtils.join("; ", arrayList));
    return stringBuilder.toString();
  }
  
  public final ArrayMap<zzh<?>, ConnectionResult> zzafh() {
    return this.zzfgj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/AvailabilityException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */