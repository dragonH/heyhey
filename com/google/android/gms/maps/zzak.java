package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzak extends zzaq {
  zzak(SupportMapFragment.zza paramzza, OnMapReadyCallback paramOnMapReadyCallback) {}
  
  public final void zza(IGoogleMapDelegate paramIGoogleMapDelegate) throws RemoteException {
    this.zzigv.onMapReady(new GoogleMap(paramIGoogleMapDelegate));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */