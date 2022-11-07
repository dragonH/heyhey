package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.List;

public final class zzaf extends zzbck {
  public static final Parcelable.Creator<zzaf> CREATOR = new zzag();
  
  private final List<Integer> zziaj;
  
  private final String zziak;
  
  private final Uri zzial;
  
  private final float zzidc;
  
  private final int zzidd;
  
  zzaf(List<Integer> paramList, String paramString, Uri paramUri, float paramFloat, int paramInt) {
    this.zziaj = Collections.unmodifiableList(paramList);
    this.zziak = paramString;
    this.zzial = paramUri;
    this.zzidc = paramFloat;
    this.zzidd = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zziaj, false);
    zzbcn.zza(paramParcel, 2, this.zziak, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzial, paramInt, false);
    zzbcn.zza(paramParcel, 4, this.zzidc);
    zzbcn.zzc(paramParcel, 5, this.zzidd);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */