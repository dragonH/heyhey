package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
  zzj(GoogleMap paramGoogleMap, GoogleMap.OnMyLocationClickListener paramOnMyLocationClickListener) {}
  
  public final void onMyLocationClick(@NonNull Location paramLocation) throws RemoteException {
    this.zzifl.onMyLocationClick(paramLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */