package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.internal.zzg;

final class zzn extends zzy {
  zzn(GoogleMap paramGoogleMap, GoogleMap.OnGroundOverlayClickListener paramOnGroundOverlayClickListener) {}
  
  public final void zza(zzg paramzzg) {
    this.zzifp.onGroundOverlayClick(new GroundOverlay(paramzzg));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */