package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzk;

final class zzbzp extends zzk {
  private final zzcj<LocationCallback> zzfpc;
  
  zzbzp(zzcj<LocationCallback> paramzzcj) {
    this.zzfpc = paramzzcj;
  }
  
  public final void onLocationAvailability(LocationAvailability paramLocationAvailability) {
    this.zzfpc.zza(new zzbzr(this, paramLocationAvailability));
  }
  
  public final void onLocationResult(LocationResult paramLocationResult) {
    this.zzfpc.zza(new zzbzq(this, paramLocationResult));
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */