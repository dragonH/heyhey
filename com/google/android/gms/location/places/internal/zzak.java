package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class zzak extends zzbck {
  public static final Parcelable.Creator<zzak> CREATOR = new zzal();
  
  private String address;
  
  private String name;
  
  private String zzidq;
  
  private String zzidr;
  
  private List<String> zzids;
  
  public zzak(String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList) {
    this.name = paramString1;
    this.address = paramString2;
    this.zzidq = paramString3;
    this.zzidr = paramString4;
    this.zzids = paramList;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzak))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.name, ((zzak)paramObject).name) && zzbf.equal(this.address, ((zzak)paramObject).address) && zzbf.equal(this.zzidq, ((zzak)paramObject).zzidq) && zzbf.equal(this.zzidr, ((zzak)paramObject).zzidr) && zzbf.equal(this.zzids, ((zzak)paramObject).zzids));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.name, this.address, this.zzidq, this.zzidr });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzidq).zzg("regularOpenHours", this.zzidr).zzg("attributions", this.zzids).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.name, false);
    zzbcn.zza(paramParcel, 2, this.address, false);
    zzbcn.zza(paramParcel, 3, this.zzidq, false);
    zzbcn.zza(paramParcel, 4, this.zzidr, false);
    zzbcn.zzb(paramParcel, 5, this.zzids, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */