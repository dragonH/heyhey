package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Locale;

public final class zzat extends zzbck {
  public static final Parcelable.Creator<zzat> CREATOR;
  
  private static zzat zzieg = new zzat("com.google.android.gms", Locale.getDefault(), null);
  
  private String zzgcg;
  
  private String zzibu;
  
  private String zzieh;
  
  private String zziei;
  
  private int zziej;
  
  private int zziek;
  
  static {
    CREATOR = new zzau();
  }
  
  public zzat(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2) {
    this.zzieh = paramString1;
    this.zziei = paramString2;
    this.zzgcg = paramString3;
    this.zzibu = paramString4;
    this.zziej = paramInt1;
    this.zziek = paramInt2;
  }
  
  private zzat(String paramString1, Locale paramLocale, String paramString2) {
    this(paramString1, paramLocale.toString(), null, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
  }
  
  public zzat(String paramString1, Locale paramLocale, String paramString2, String paramString3, int paramInt) {
    this(paramString1, paramLocale.toString(), paramString2, paramString3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, paramInt);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && paramObject instanceof zzat) {
      paramObject = paramObject;
      if (this.zziej == ((zzat)paramObject).zziej && this.zziek == ((zzat)paramObject).zziek && this.zziei.equals(((zzat)paramObject).zziei) && this.zzieh.equals(((zzat)paramObject).zzieh) && zzbf.equal(this.zzgcg, ((zzat)paramObject).zzgcg) && zzbf.equal(this.zzibu, ((zzat)paramObject).zzibu))
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzieh, this.zziei, this.zzgcg, this.zzibu, Integer.valueOf(this.zziej), Integer.valueOf(this.zziek) });
  }
  
  @SuppressLint({"DefaultLocale"})
  public final String toString() {
    return zzbf.zzt(this).zzg("clientPackageName", this.zzieh).zzg("locale", this.zziei).zzg("accountName", this.zzgcg).zzg("gCoreClientName", this.zzibu).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzieh, false);
    zzbcn.zza(paramParcel, 2, this.zziei, false);
    zzbcn.zza(paramParcel, 3, this.zzgcg, false);
    zzbcn.zza(paramParcel, 4, this.zzibu, false);
    zzbcn.zzc(paramParcel, 6, this.zziej);
    zzbcn.zzc(paramParcel, 7, this.zziek);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */