package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzaz;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf {
  private static zzaz zzffl;
  
  private static final Object zzffm = new Object();
  
  private static Context zzffn;
  
  static boolean zza(String paramString, zzg paramzzg) {
    return zza(paramString, paramzzg, false);
  }
  
  private static boolean zza(String paramString, zzg paramzzg, boolean paramBoolean) {
    if (!zzaey())
      return false; 
    zzbp.zzu(zzffn);
    try {
      zzm zzm = new zzm();
      this(paramString, paramzzg, paramBoolean);
      return zzffl.zza(zzm, zzn.zzw(zzffn.getPackageManager()));
    } catch (RemoteException remoteException) {
      Log.e("GoogleCertificates", "Failed to get Google certificates from remote", (Throwable)remoteException);
      return false;
    } 
  }
  
  private static boolean zzaey() {
    if (zzffl != null)
      return true; 
    zzbp.zzu(zzffn);
    synchronized (zzffm) {
      zzaz zzaz1 = zzffl;
      if (zzaz1 == null)
        try {
          zzffl = zzba.zzal(DynamiteModule.zza(zzffn, DynamiteModule.zzgpr, "com.google.android.gms.googlecertificates").zzgv("com.google.android.gms.common.GoogleCertificatesImpl"));
        } catch (com.google.android.gms.dynamite.DynamiteModule.zzc zzc) {
          Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", (Throwable)zzc);
          return false;
        }  
      return true;
    } 
  }
  
  static boolean zzb(String paramString, zzg paramzzg) {
    return zza(paramString, paramzzg, true);
  }
  
  static void zzbx(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/zzf
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/zzf.zzffn : Landroid/content/Context;
    //   6: ifnonnull -> 24
    //   9: aload_0
    //   10: ifnull -> 32
    //   13: aload_0
    //   14: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   17: putstatic com/google/android/gms/common/zzf.zzffn : Landroid/content/Context;
    //   20: ldc com/google/android/gms/common/zzf
    //   22: monitorexit
    //   23: return
    //   24: ldc 'GoogleCertificates'
    //   26: ldc 'GoogleCertificates has been initialized already'
    //   28: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   31: pop
    //   32: ldc com/google/android/gms/common/zzf
    //   34: monitorexit
    //   35: return
    //   36: astore_0
    //   37: ldc com/google/android/gms/common/zzf
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   3	9	36	finally
    //   13	20	36	finally
    //   24	32	36	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */