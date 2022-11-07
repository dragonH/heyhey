package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzx extends zzec implements zzw {
  public zzx() {
    attachInterface(this, "com.google.android.gms.location.places.internal.IPlacesCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5)
              return false; 
            zzar((DataHolder)zzed.zza(paramParcel1, DataHolder.CREATOR));
          } else {
            zzaf((Status)zzed.zza(paramParcel1, Status.CREATOR));
          } 
        } else {
          zzaq((DataHolder)zzed.zza(paramParcel1, DataHolder.CREATOR));
        } 
      } else {
        zzap((DataHolder)zzed.zza(paramParcel1, DataHolder.CREATOR));
      } 
    } else {
      zzao((DataHolder)zzed.zza(paramParcel1, DataHolder.CREATOR));
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */