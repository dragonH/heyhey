package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.location.places.internal.zzv;

public final class zzd extends zzv {
  private final zzf zziaq = null;
  
  private final zze zziar;
  
  public zzd(zze paramzze) {
    this.zziar = paramzze;
  }
  
  public zzd(zzf paramzzf) {
    this.zziar = null;
  }
  
  public final void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult) throws RemoteException {
    this.zziaq.setResult(paramPlacePhotoMetadataResult);
  }
  
  public final void zza(PlacePhotoResult paramPlacePhotoResult) throws RemoteException {
    this.zziar.setResult(paramPlacePhotoResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */