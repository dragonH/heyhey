package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class LocationSettingsResult extends zzbck implements Result {
  public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzw();
  
  private final Status mStatus;
  
  private final LocationSettingsStates zzhym;
  
  public LocationSettingsResult(Status paramStatus) {
    this(paramStatus, null);
  }
  
  public LocationSettingsResult(Status paramStatus, LocationSettingsStates paramLocationSettingsStates) {
    this.mStatus = paramStatus;
    this.zzhym = paramLocationSettingsStates;
  }
  
  public final LocationSettingsStates getLocationSettingsStates() {
    return this.zzhym;
  }
  
  public final Status getStatus() {
    return this.mStatus;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)getStatus(), paramInt, false);
    zzbcn.zza(paramParcel, 2, (Parcelable)getLocationSettingsStates(), paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationSettingsResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */