package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends zza {
  public static final Parcelable.Creator<PlaceFilter> CREATOR = new zzh();
  
  private static final PlaceFilter zziat = new PlaceFilter();
  
  private List<Integer> zziau;
  
  private boolean zziav;
  
  private List<zzo> zziaw;
  
  private List<String> zziax;
  
  private final Set<Integer> zziay;
  
  private final Set<zzo> zziaz;
  
  private final Set<String> zziba;
  
  public PlaceFilter() {
    this(false, null);
  }
  
  private PlaceFilter(@Nullable Collection<Integer> paramCollection, boolean paramBoolean, @Nullable Collection<String> paramCollection1, @Nullable Collection<zzo> paramCollection2) {
    this(zza.zzi(null), paramBoolean, zza.zzi(paramCollection1), zza.zzi(null));
  }
  
  PlaceFilter(@Nullable List<Integer> paramList, boolean paramBoolean, @Nullable List<String> paramList1, @Nullable List<zzo> paramList2) {
    if (paramList == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList);
    } 
    this.zziau = paramList;
    this.zziav = paramBoolean;
    if (paramList2 == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList2);
    } 
    this.zziaw = (List)paramList;
    if (paramList1 == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList1);
    } 
    this.zziax = (List)paramList;
    this.zziay = zza.zzaa(this.zziau);
    this.zziaz = zza.zzaa(this.zziaw);
    this.zziba = zza.zzaa(this.zziax);
  }
  
  public PlaceFilter(boolean paramBoolean, @Nullable Collection<String> paramCollection) {
    this((Collection<Integer>)null, paramBoolean, paramCollection, (Collection<zzo>)null);
  }
  
  @Deprecated
  public static PlaceFilter zzasy() {
    new zza(null);
    return new PlaceFilter(null, false, null, null);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof PlaceFilter))
      return false; 
    paramObject = paramObject;
    return (this.zziay.equals(((PlaceFilter)paramObject).zziay) && this.zziav == ((PlaceFilter)paramObject).zziav && this.zziaz.equals(((PlaceFilter)paramObject).zziaz) && this.zziba.equals(((PlaceFilter)paramObject).zziba));
  }
  
  public final Set<String> getPlaceIds() {
    return this.zziba;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zziay, Boolean.valueOf(this.zziav), this.zziaz, this.zziba });
  }
  
  public final boolean isRestrictedToPlacesOpenNow() {
    return this.zziav;
  }
  
  public final String toString() {
    zzbh zzbh = zzbf.zzt(this);
    if (!this.zziay.isEmpty())
      zzbh.zzg("types", this.zziay); 
    zzbh.zzg("requireOpenNow", Boolean.valueOf(this.zziav));
    if (!this.zziba.isEmpty())
      zzbh.zzg("placeIds", this.zziba); 
    if (!this.zziaz.isEmpty())
      zzbh.zzg("requestedUserDataTypes", this.zziaz); 
    return zzbh.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zziau, false);
    zzbcn.zza(paramParcel, 3, this.zziav);
    zzbcn.zzc(paramParcel, 4, this.zziaw, false);
    zzbcn.zzb(paramParcel, 6, this.zziax, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  @Deprecated
  public static final class zza {
    private boolean zziav = false;
    
    private Collection<Integer> zzibb = null;
    
    private Collection<zzo> zzibc = null;
    
    private String[] zzibd = null;
    
    private zza() {}
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */