package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.zzp;
import com.google.android.gms.internal.zzbed;

public final class zzw {
  @TargetApi(19)
  public static boolean zzb(Context paramContext, int paramInt, String paramString) {
    return zzbed.zzcr(paramContext).zzf(paramInt, paramString);
  }
  
  public static boolean zzf(Context paramContext, int paramInt) {
    if (!zzb(paramContext, paramInt, "com.google.android.gms"))
      return false; 
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
      return zzp.zzbz(paramContext).zza(paramContext.getPackageManager(), packageInfo);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      if (Log.isLoggable("UidVerifier", 3))
        Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false"); 
      return false;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */