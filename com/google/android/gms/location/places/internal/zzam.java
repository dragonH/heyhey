package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzam extends zzbck {
  public static final Parcelable.Creator<zzam> CREATOR = new zzap();
  
  private List<zzan> zzidt;
  
  private List<zzao> zzidu;
  
  zzam(List<zzan> paramList, List<zzao> paramList1) {
    this.zzidt = Collections.unmodifiableList(paramList);
    this.zzidu = Collections.unmodifiableList(paramList1);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzidt, false);
    zzbcn.zzc(paramParcel, 2, this.zzidu, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */