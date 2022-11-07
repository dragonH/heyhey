package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzg extends zzi {
  zzg(GoogleMap paramGoogleMap, GoogleMap.InfoWindowAdapter paramInfoWindowAdapter) {}
  
  public final IObjectWrapper zzh(zzp paramzzp) {
    return zzn.zzw(this.zzifi.getInfoWindow(new Marker(paramzzp)));
  }
  
  public final IObjectWrapper zzi(zzp paramzzp) {
    return zzn.zzw(this.zzifi.getInfoContents(new Marker(paramzzp)));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */