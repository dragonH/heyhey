package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.internal.zzbvp;

public final class zzh extends zza<String> {
  public static String zza(SharedPreferences paramSharedPreferences, String paramString1, String paramString2) {
    try {
      zzi zzi = new zzi();
      this(paramSharedPreferences, paramString1, paramString2);
      return (String)zzbvp.zza(zzi);
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        str = "Flag value not available, returning default: ".concat(str);
      } else {
        str = new String("Flag value not available, returning default: ");
      } 
      Log.w("FlagDataUtils", str);
      return paramString2;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/flags/impl/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */