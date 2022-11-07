package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Set;

public final class zzo extends zzbck {
  public static final Parcelable.Creator<zzo> CREATOR;
  
  private static zzo zzibx = zzv("test_type", 1);
  
  private static zzo zziby = zzv("labeled_place", 6);
  
  private static zzo zzibz;
  
  private static Set<zzo> zzica;
  
  private String zzcum;
  
  private int zzicb;
  
  static {
    zzo zzo1 = zzv("here_content", 7);
    zzibz = zzo1;
    zzica = zze.zza(zzibx, zziby, zzo1);
    CREATOR = new zzp();
  }
  
  zzo(String paramString, int paramInt) {
    zzbp.zzgg(paramString);
    this.zzcum = paramString;
    this.zzicb = paramInt;
  }
  
  private static zzo zzv(String paramString, int paramInt) {
    return new zzo(paramString, paramInt);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzo))
      return false; 
    paramObject = paramObject;
    return (this.zzcum.equals(((zzo)paramObject).zzcum) && this.zzicb == ((zzo)paramObject).zzicb);
  }
  
  public final int hashCode() {
    return this.zzcum.hashCode();
  }
  
  public final String toString() {
    return this.zzcum;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzcum, false);
    zzbcn.zzc(paramParcel, 2, this.zzicb);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */