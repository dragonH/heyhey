package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzay;

final class zzh extends zzay {
  zzh(GoogleMap paramGoogleMap, GoogleMap.OnMyLocationChangeListener paramOnMyLocationChangeListener) {}
  
  public final void zzy(IObjectWrapper paramIObjectWrapper) {
    this.zzifj.onMyLocationChange((Location)zzn.zzx(paramIObjectWrapper));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */