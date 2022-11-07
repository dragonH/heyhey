package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.zzn;

final class zzbzs extends zzn {
  private final zzcj<LocationListener> zzfpc;
  
  zzbzs(zzcj<LocationListener> paramzzcj) {
    this.zzfpc = paramzzcj;
  }
  
  public final void onLocationChanged(Location paramLocation) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzfpc : Lcom/google/android/gms/common/api/internal/zzcj;
    //   6: astore_2
    //   7: new com/google/android/gms/internal/zzbzt
    //   10: astore_3
    //   11: aload_3
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial <init> : (Lcom/google/android/gms/internal/zzbzs;Landroid/location/Location;)V
    //   17: aload_2
    //   18: aload_3
    //   19: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzcm;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  public final void release() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzfpc : Lcom/google/android/gms/common/api/internal/zzcj;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */