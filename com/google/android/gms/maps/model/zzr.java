package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzz;

final class zzr implements TileProvider {
  private final zzz zzikf;
  
  zzr(TileOverlayOptions paramTileOverlayOptions) {
    this.zzikf = TileOverlayOptions.zza(paramTileOverlayOptions);
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) {
    try {
      return this.zzikf.getTile(paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */