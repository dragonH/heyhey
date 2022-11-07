package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest extends zzbck {
  public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzv();
  
  private final List<LocationRequest> zzgzq;
  
  private final boolean zzhyi;
  
  private final boolean zzhyj;
  
  private zzt zzhyk;
  
  LocationSettingsRequest(List<LocationRequest> paramList, boolean paramBoolean1, boolean paramBoolean2, zzt paramzzt) {
    this.zzgzq = paramList;
    this.zzhyi = paramBoolean1;
    this.zzhyj = paramBoolean2;
    this.zzhyk = paramzzt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, Collections.unmodifiableList(this.zzgzq), false);
    zzbcn.zza(paramParcel, 2, this.zzhyi);
    zzbcn.zza(paramParcel, 3, this.zzhyj);
    zzbcn.zza(paramParcel, 5, (Parcelable)this.zzhyk, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public static final class Builder {
    private boolean zzhyi = false;
    
    private boolean zzhyj = false;
    
    private zzt zzhyk = null;
    
    private final ArrayList<LocationRequest> zzhyl = new ArrayList<LocationRequest>();
    
    public final Builder addAllLocationRequests(Collection<LocationRequest> param1Collection) {
      for (LocationRequest locationRequest : param1Collection) {
        if (locationRequest != null)
          this.zzhyl.add(locationRequest); 
      } 
      return this;
    }
    
    public final Builder addLocationRequest(@NonNull LocationRequest param1LocationRequest) {
      if (param1LocationRequest != null)
        this.zzhyl.add(param1LocationRequest); 
      return this;
    }
    
    public final LocationSettingsRequest build() {
      return new LocationSettingsRequest(this.zzhyl, this.zzhyi, this.zzhyj, null);
    }
    
    public final Builder setAlwaysShow(boolean param1Boolean) {
      this.zzhyi = param1Boolean;
      return this;
    }
    
    public final Builder setNeedBle(boolean param1Boolean) {
      this.zzhyj = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationSettingsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */