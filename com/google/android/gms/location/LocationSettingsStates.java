package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.internal.zzbcp;

public final class LocationSettingsStates extends zzbck {
  public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzx();
  
  private final boolean zzhyn;
  
  private final boolean zzhyo;
  
  private final boolean zzhyp;
  
  private final boolean zzhyq;
  
  private final boolean zzhyr;
  
  private final boolean zzhys;
  
  public LocationSettingsStates(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6) {
    this.zzhyn = paramBoolean1;
    this.zzhyo = paramBoolean2;
    this.zzhyp = paramBoolean3;
    this.zzhyq = paramBoolean4;
    this.zzhyr = paramBoolean5;
    this.zzhys = paramBoolean6;
  }
  
  public static LocationSettingsStates fromIntent(Intent paramIntent) {
    return (LocationSettingsStates)zzbcp.zza(paramIntent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
  }
  
  public final boolean isBlePresent() {
    return this.zzhys;
  }
  
  public final boolean isBleUsable() {
    return this.zzhyp;
  }
  
  public final boolean isGpsPresent() {
    return this.zzhyq;
  }
  
  public final boolean isGpsUsable() {
    return this.zzhyn;
  }
  
  public final boolean isLocationPresent() {
    return (this.zzhyq || this.zzhyr);
  }
  
  public final boolean isLocationUsable() {
    return (this.zzhyn || this.zzhyo);
  }
  
  public final boolean isNetworkLocationPresent() {
    return this.zzhyr;
  }
  
  public final boolean isNetworkLocationUsable() {
    return this.zzhyo;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, isGpsUsable());
    zzbcn.zza(paramParcel, 2, isNetworkLocationUsable());
    zzbcn.zza(paramParcel, 3, isBleUsable());
    zzbcn.zza(paramParcel, 4, isGpsPresent());
    zzbcn.zza(paramParcel, 5, isNetworkLocationPresent());
    zzbcn.zza(paramParcel, 6, isBlePresent());
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationSettingsStates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */