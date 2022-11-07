package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzs extends zzbc {
  zzs(GoogleMap paramGoogleMap, GoogleMap.OnPoiClickListener paramOnPoiClickListener) {}
  
  public final void zza(PointOfInterest paramPointOfInterest) throws RemoteException {
    this.zzifu.onPoiClick(paramPointOfInterest);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */