package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.internal.zzs;

final class zzp extends zzbe {
  zzp(GoogleMap paramGoogleMap, GoogleMap.OnPolygonClickListener paramOnPolygonClickListener) {}
  
  public final void zza(zzs paramzzs) {
    this.zzifr.onPolygonClick(new Polygon(paramzzs));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */