package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zza extends zzbck implements AutocompletePrediction {
  public static final Parcelable.Creator<zza> CREATOR = new zzc();
  
  private static final List<zzb> zzicc = Collections.emptyList();
  
  private List<Integer> zziaj;
  
  private String zzibj;
  
  private String zzicd;
  
  private List<zzb> zzice;
  
  private int zzicf;
  
  private String zzicg;
  
  private List<zzb> zzich;
  
  private String zzici;
  
  private List<zzb> zzicj;
  
  zza(String paramString1, List<Integer> paramList, int paramInt, String paramString2, List<zzb> paramList1, String paramString3, List<zzb> paramList2, String paramString4, List<zzb> paramList3) {
    this.zzibj = paramString1;
    this.zziaj = paramList;
    this.zzicf = paramInt;
    this.zzicd = paramString2;
    this.zzice = paramList1;
    this.zzicg = paramString3;
    this.zzich = paramList2;
    this.zzici = paramString4;
    this.zzicj = paramList3;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zza))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.zzibj, ((zza)paramObject).zzibj) && zzbf.equal(this.zziaj, ((zza)paramObject).zziaj) && zzbf.equal(Integer.valueOf(this.zzicf), Integer.valueOf(((zza)paramObject).zzicf)) && zzbf.equal(this.zzicd, ((zza)paramObject).zzicd) && zzbf.equal(this.zzice, ((zza)paramObject).zzice) && zzbf.equal(this.zzicg, ((zza)paramObject).zzicg) && zzbf.equal(this.zzich, ((zza)paramObject).zzich) && zzbf.equal(this.zzici, ((zza)paramObject).zzici) && zzbf.equal(this.zzicj, ((zza)paramObject).zzicj));
  }
  
  public final CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(this.zzicd, this.zzice, paramCharacterStyle);
  }
  
  @Nullable
  public final String getPlaceId() {
    return this.zzibj;
  }
  
  public final List<Integer> getPlaceTypes() {
    return this.zziaj;
  }
  
  public final CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(this.zzicg, this.zzich, paramCharacterStyle);
  }
  
  public final CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle) {
    return zzg.zza(this.zzici, this.zzicj, paramCharacterStyle);
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzibj, this.zziaj, Integer.valueOf(this.zzicf), this.zzicd, this.zzice, this.zzicg, this.zzich, this.zzici, this.zzicj });
  }
  
  public final boolean isDataValid() {
    return true;
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("placeId", this.zzibj).zzg("placeTypes", this.zziaj).zzg("fullText", this.zzicd).zzg("fullTextMatchedSubstrings", this.zzice).zzg("primaryText", this.zzicg).zzg("primaryTextMatchedSubstrings", this.zzich).zzg("secondaryText", this.zzici).zzg("secondaryTextMatchedSubstrings", this.zzicj).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzicd, false);
    zzbcn.zza(paramParcel, 2, this.zzibj, false);
    zzbcn.zza(paramParcel, 3, this.zziaj, false);
    zzbcn.zzc(paramParcel, 4, this.zzice, false);
    zzbcn.zzc(paramParcel, 5, this.zzicf);
    zzbcn.zza(paramParcel, 6, this.zzicg, false);
    zzbcn.zzc(paramParcel, 7, this.zzich, false);
    zzbcn.zza(paramParcel, 8, this.zzici, false);
    zzbcn.zzc(paramParcel, 9, this.zzicj, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */