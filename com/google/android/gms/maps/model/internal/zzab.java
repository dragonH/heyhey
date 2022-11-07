package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.Tile;

public final class zzab extends zzeb implements zzz {
  zzab(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = zzax();
    parcel1.writeInt(paramInt1);
    parcel1.writeInt(paramInt2);
    parcel1.writeInt(paramInt3);
    Parcel parcel2 = zza(1, parcel1);
    Tile tile = (Tile)zzed.zza(parcel2, Tile.CREATOR);
    parcel2.recycle();
    return tile;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */