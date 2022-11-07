package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zzb implements zzo<Object> {
  zzb(zza paramzza) {}
  
  public final void zza(Object paramObject) {
    zza.zza(this.zzgoy, (LifecycleDelegate)paramObject);
    paramObject = zza.zza(this.zzgoy).iterator();
    while (paramObject.hasNext())
      ((zzi)paramObject.next()).zzb(zza.zzb(this.zzgoy)); 
    zza.zza(this.zzgoy).clear();
    zza.zza(this.zzgoy, (Bundle)null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */