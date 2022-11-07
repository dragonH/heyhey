package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class zzy extends zzbck {
  public static final Parcelable.Creator<zzy> CREATOR = new zzz();
  
  private int zzhyt;
  
  private int zzhyu;
  
  private long zzhyv;
  
  private long zzhyw;
  
  zzy(int paramInt1, int paramInt2, long paramLong1, long paramLong2) {
    this.zzhyt = paramInt1;
    this.zzhyu = paramInt2;
    this.zzhyv = paramLong1;
    this.zzhyw = paramLong2;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && zzy.class == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzhyt == ((zzy)paramObject).zzhyt && this.zzhyu == ((zzy)paramObject).zzhyu && this.zzhyv == ((zzy)paramObject).zzhyv && this.zzhyw == ((zzy)paramObject).zzhyw)
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzhyu), Integer.valueOf(this.zzhyt), Long.valueOf(this.zzhyw), Long.valueOf(this.zzhyv) });
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder("NetworkLocationStatus:");
    stringBuilder.append(" Wifi status: ");
    stringBuilder.append(this.zzhyt);
    stringBuilder.append(" Cell status: ");
    stringBuilder.append(this.zzhyu);
    stringBuilder.append(" elapsed time NS: ");
    stringBuilder.append(this.zzhyw);
    stringBuilder.append(" system time ms: ");
    stringBuilder.append(this.zzhyv);
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzhyt);
    zzbcn.zzc(paramParcel, 2, this.zzhyu);
    zzbcn.zza(paramParcel, 3, this.zzhyv);
    zzbcn.zza(paramParcel, 4, this.zzhyw);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */