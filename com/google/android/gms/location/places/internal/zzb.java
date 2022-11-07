package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class zzb extends zzbck {
  public static final Parcelable.Creator<zzb> CREATOR = new zzaw();
  
  final int mLength;
  
  final int mOffset;
  
  public zzb(int paramInt1, int paramInt2) {
    this.mOffset = paramInt1;
    this.mLength = paramInt2;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzb))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(Integer.valueOf(this.mOffset), Integer.valueOf(((zzb)paramObject).mOffset)) && zzbf.equal(Integer.valueOf(this.mLength), Integer.valueOf(((zzb)paramObject).mLength)));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength) });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.mOffset);
    zzbcn.zzc(paramParcel, 2, this.mLength);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */