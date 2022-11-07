package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzao extends zzbck {
  public static final Parcelable.Creator<zzao> CREATOR = new zzf();
  
  private int zzidx;
  
  private int zzidy;
  
  private int zzidz;
  
  private int zziea;
  
  private int zzieb;
  
  private int zziec;
  
  private List<zzan> zzied;
  
  zzao(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, List<zzan> paramList) {
    this.zzidx = paramInt1;
    this.zzidy = paramInt2;
    this.zzidz = paramInt3;
    this.zziea = paramInt4;
    this.zzieb = paramInt5;
    this.zziec = paramInt6;
    this.zzied = Collections.unmodifiableList(paramList);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzidx);
    zzbcn.zzc(paramParcel, 2, this.zzidy);
    zzbcn.zzc(paramParcel, 3, this.zzidz);
    zzbcn.zzc(paramParcel, 4, this.zziea);
    zzbcn.zzc(paramParcel, 5, this.zzieb);
    zzbcn.zzc(paramParcel, 6, this.zziec);
    zzbcn.zzc(paramParcel, 7, this.zzied, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */