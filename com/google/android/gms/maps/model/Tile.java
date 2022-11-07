package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class Tile extends zzbck {
  public static final Parcelable.Creator<Tile> CREATOR = new zzq();
  
  public final byte[] data;
  
  public final int height;
  
  public final int width;
  
  public Tile(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    this.width = paramInt1;
    this.height = paramInt2;
    this.data = paramArrayOfbyte;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 2, this.width);
    zzbcn.zzc(paramParcel, 3, this.height);
    zzbcn.zza(paramParcel, 4, this.data, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/Tile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */