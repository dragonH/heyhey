package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzah;

final class zzl extends ILocationSourceDelegate.zza {
  zzl(GoogleMap paramGoogleMap, LocationSource paramLocationSource) {}
  
  public final void activate(zzah paramzzah) {
    this.zzifn.activate(new zzm(this, paramzzah));
  }
  
  public final void deactivate() {
    this.zzifn.deactivate();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */