package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzbec;
import com.google.android.gms.internal.zzbed;

public final class zzbe {
  private static Object zzaqd = new Object();
  
  private static boolean zzclj;
  
  private static String zzfvo;
  
  private static int zzfvp;
  
  public static String zzcf(Context paramContext) {
    zzch(paramContext);
    return zzfvo;
  }
  
  public static int zzcg(Context paramContext) {
    zzch(paramContext);
    return zzfvp;
  }
  
  private static void zzch(Context paramContext) {
    synchronized (zzaqd) {
      if (zzclj)
        return; 
      zzclj = true;
      String str = paramContext.getPackageName();
      zzbec zzbec = zzbed.zzcr(paramContext);
      try {
        Bundle bundle = (zzbec.getApplicationInfo(str, 128)).metaData;
        if (bundle == null)
          return; 
        zzfvo = bundle.getString("com.google.app.id");
        zzfvp = bundle.getInt("com.google.android.gms.version");
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        Log.wtf("MetadataValueReader", "This should never happen.", (Throwable)nameNotFoundException);
      } 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */