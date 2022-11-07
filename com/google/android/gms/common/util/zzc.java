package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbed;

public final class zzc {
  @Nullable
  private static PackageInfo zzaa(Context paramContext, String paramString) {
    try {
      return zzbed.zzcr(paramContext).getPackageInfo(paramString, 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static boolean zzab(Context paramContext, String paramString) {
    "com.google.android.gms".equals(paramString);
    try {
      int i = (zzbed.zzcr(paramContext).getApplicationInfo(paramString, 0)).flags;
      if ((i & 0x200000) != 0)
        return true; 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
  
  public static int zzz(Context paramContext, String paramString) {
    PackageInfo packageInfo = zzaa(paramContext, paramString);
    if (packageInfo != null) {
      ApplicationInfo applicationInfo = packageInfo.applicationInfo;
      if (applicationInfo != null) {
        Bundle bundle = applicationInfo.metaData;
        return (bundle == null) ? -1 : bundle.getInt("com.google.android.gms.version", -1);
      } 
    } 
    return -1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */