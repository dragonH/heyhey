package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.google.android.gms.common.zzo;

public class zzb {
  private static zzb zzaly;
  
  private final Context zzahz;
  
  private zzb(Context paramContext) {
    this.zzahz = paramContext;
  }
  
  private final void zza(AdvertisingIdClient.Info paramInfo, boolean paramBoolean, long paramLong) {
    float f = (new zzd(this.zzahz)).getFloat("gads:ad_id_use_shared_preference:ping_ratio", 0.0F);
    if (Math.random() > f)
      return; 
    (new Thread(new zzc(paramInfo, paramBoolean, paramLong))).start();
  }
  
  public static zzb zzd(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/ads/identifier/zzb
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/ads/identifier/zzb.zzaly : Lcom/google/android/gms/ads/identifier/zzb;
    //   6: ifnonnull -> 22
    //   9: new com/google/android/gms/ads/identifier/zzb
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/content/Context;)V
    //   18: aload_1
    //   19: putstatic com/google/android/gms/ads/identifier/zzb.zzaly : Lcom/google/android/gms/ads/identifier/zzb;
    //   22: getstatic com/google/android/gms/ads/identifier/zzb.zzaly : Lcom/google/android/gms/ads/identifier/zzb;
    //   25: astore_0
    //   26: ldc com/google/android/gms/ads/identifier/zzb
    //   28: monitorexit
    //   29: aload_0
    //   30: areturn
    //   31: astore_0
    //   32: ldc com/google/android/gms/ads/identifier/zzb
    //   34: monitorexit
    //   35: aload_0
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   3	22	31	finally
    //   22	29	31	finally
    //   32	35	31	finally
  }
  
  public final AdvertisingIdClient.Info getInfo() {
    AdvertisingIdClient.Info info;
    Context context1 = zzo.getRemoteContext(this.zzahz);
    Context context2 = null;
    if (context1 == null) {
      zza(null, false, -1L);
      return null;
    } 
    SharedPreferences sharedPreferences = context1.getSharedPreferences("adid_settings", 0);
    if (sharedPreferences == null) {
      zza(null, false, -1L);
      return null;
    } 
    long l = SystemClock.elapsedRealtime();
    context1 = context2;
    if (sharedPreferences.contains("adid_key"))
      if (!sharedPreferences.contains("enable_limit_ad_tracking")) {
        context1 = context2;
      } else {
        info = new AdvertisingIdClient.Info(sharedPreferences.getString("adid_key", ""), sharedPreferences.getBoolean("enable_limit_ad_tracking", false));
      }  
    zza(info, true, SystemClock.elapsedRealtime() - l);
    return info;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/ads/identifier/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */