package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzc extends zzau {
  zzc(GoogleMap paramGoogleMap, GoogleMap.OnMarkerDragListener paramOnMarkerDragListener) {}
  
  public final void zzb(zzp paramzzp) {
    this.zzife.onMarkerDragStart(new Marker(paramzzp));
  }
  
  public final void zzc(zzp paramzzp) {
    this.zzife.onMarkerDragEnd(new Marker(paramzzp));
  }
  
  public final void zzd(zzp paramzzp) {
    this.zzife.onMarkerDrag(new Marker(paramzzp));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */