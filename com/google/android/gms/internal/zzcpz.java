package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public abstract class zzcpz extends zzec implements zzcpy {
  public zzcpz() {
    attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 3) {
      if (paramInt1 != 4 && paramInt1 != 6) {
        if (paramInt1 != 7) {
          if (paramInt1 != 8)
            return false; 
          zzb(zzed.<zzcqf>zza(paramParcel1, zzcqf.CREATOR));
        } else {
          zzed.zza(paramParcel1, Status.CREATOR);
          Parcelable.Creator<Parcelable> creator = GoogleSignInAccount.CREATOR;
          zzed.zza(paramParcel1, creator);
        } 
      } else {
        Parcelable.Creator<Parcelable> creator = Status.CREATOR;
        zzed.zza(paramParcel1, creator);
      } 
    } else {
      zzed.zza(paramParcel1, ConnectionResult.CREATOR);
      Parcelable.Creator<zzcpv> creator = zzcpv.CREATOR;
      zzed.zza(paramParcel1, creator);
    } 
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcpz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */