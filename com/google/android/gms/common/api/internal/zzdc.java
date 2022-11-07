package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzdc implements Runnable {
  zzdc(zzdb paramzzdb, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run() {
    if (zzdb.zza(this.zzfpg) > 0) {
      Bundle bundle;
      LifecycleCallback lifecycleCallback = this.zzfos;
      if (zzdb.zzb(this.zzfpg) != null) {
        bundle = zzdb.zzb(this.zzfpg).getBundle(this.zzao);
      } else {
        bundle = null;
      } 
      lifecycleCallback.onCreate(bundle);
    } 
    if (zzdb.zza(this.zzfpg) >= 2)
      this.zzfos.onStart(); 
    if (zzdb.zza(this.zzfpg) >= 3)
      this.zzfos.onResume(); 
    if (zzdb.zza(this.zzfpg) >= 4)
      this.zzfos.onStop(); 
    if (zzdb.zza(this.zzfpg) >= 5)
      this.zzfos.onDestroy(); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */