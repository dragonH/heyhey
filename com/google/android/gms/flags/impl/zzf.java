package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.internal.zzbvp;

public final class zzf extends zza<Long> {
  public static Long zza(SharedPreferences paramSharedPreferences, String paramString, Long paramLong) {
    try {
      zzg zzg = new zzg();
      this(paramSharedPreferences, paramString, paramLong);
      return (Long)zzbvp.zza(zzg);
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        str = "Flag value not available, returning default: ".concat(str);
      } else {
        str = new String("Flag value not available, returning default: ");
      } 
      Log.w("FlagDataUtils", str);
      return paramLong;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/flags/impl/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */