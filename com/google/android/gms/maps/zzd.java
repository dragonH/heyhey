package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzd extends zzac {
  zzd(GoogleMap paramGoogleMap, GoogleMap.OnInfoWindowClickListener paramOnInfoWindowClickListener) {}
  
  public final void zze(zzp paramzzp) {
    this.zziff.onInfoWindowClick(new Marker(paramzzp));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */