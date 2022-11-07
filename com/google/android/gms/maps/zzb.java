package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzb extends zzas {
  zzb(GoogleMap paramGoogleMap, GoogleMap.OnMarkerClickListener paramOnMarkerClickListener) {}
  
  public final boolean zza(zzp paramzzp) {
    return this.zzifd.onMarkerClick(new Marker(paramzzp));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */