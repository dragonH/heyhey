package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.File;

public final class zzas {
  public static zzs zza(Context paramContext, zzan paramzzan) {
    File file = new File(paramContext.getCacheDir(), "volley");
    try {
      String str = paramContext.getPackageName();
      int i = (paramContext.getPackageManager().getPackageInfo(str, 0)).versionCode;
      int j = String.valueOf(str).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(j + 12);
      stringBuilder.append(str);
      stringBuilder.append("/");
      stringBuilder.append(i);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    zzad zzad = new zzad(new zzao());
    zzs zzs = new zzs(new zzag(file), zzad);
    zzs.start();
    return zzs;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */