package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzci implements Runnable {
  zzci(zzch paramzzch, LifecycleCallback paramLifecycleCallback, String paramString) {}
  
  public final void run() {
    if (zzch.zza(this.zzfot) > 0) {
      Bundle bundle;
      LifecycleCallback lifecycleCallback = this.zzfos;
      if (zzch.zzb(this.zzfot) != null) {
        bundle = zzch.zzb(this.zzfot).getBundle(this.zzao);
      } else {
        bundle = null;
      } 
      lifecycleCallback.onCreate(bundle);
    } 
    if (zzch.zza(this.zzfot) >= 2)
      this.zzfos.onStart(); 
    if (zzch.zza(this.zzfot) >= 3)
      this.zzfos.onResume(); 
    if (zzch.zza(this.zzfot) >= 4)
      this.zzfos.onStop(); 
    if (zzch.zza(this.zzfot) >= 5)
      this.zzfos.onDestroy(); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */