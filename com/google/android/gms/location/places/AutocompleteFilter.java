package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;
import java.util.List;

public class AutocompleteFilter extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<AutocompleteFilter> CREATOR = new zzc();
  
  public static final int TYPE_FILTER_ADDRESS = 2;
  
  public static final int TYPE_FILTER_CITIES = 5;
  
  public static final int TYPE_FILTER_ESTABLISHMENT = 34;
  
  public static final int TYPE_FILTER_GEOCODE = 1007;
  
  public static final int TYPE_FILTER_NONE = 0;
  
  public static final int TYPE_FILTER_REGIONS = 4;
  
  private int zzdxs;
  
  private boolean zziam;
  
  private List<Integer> zzian;
  
  private String zziao;
  
  private int zziap;
  
  AutocompleteFilter(int paramInt, boolean paramBoolean, List<Integer> paramList, String paramString) {
    this.zzdxs = paramInt;
    this.zzian = paramList;
    if (paramList == null || paramList.isEmpty()) {
      paramInt = 0;
    } else {
      paramInt = ((Integer)paramList.iterator().next()).intValue();
    } 
    this.zziap = paramInt;
    this.zziao = paramString;
    if (this.zzdxs <= 0) {
      this.zziam = paramBoolean ^ true;
      return;
    } 
    this.zziam = paramBoolean;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof AutocompleteFilter))
      return false; 
    paramObject = paramObject;
    return (this.zziap == ((AutocompleteFilter)paramObject).zziap && this.zziam == ((AutocompleteFilter)paramObject).zziam && this.zziao == ((AutocompleteFilter)paramObject).zziao);
  }
  
  public int getTypeFilter() {
    return this.zziap;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Boolean.valueOf(this.zziam), Integer.valueOf(this.zziap), this.zziao });
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zziam)).zzg("typeFilter", Integer.valueOf(this.zziap)).zzg("country", this.zziao).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zziam);
    zzbcn.zza(paramParcel, 2, this.zzian, false);
    zzbcn.zza(paramParcel, 3, this.zziao, false);
    zzbcn.zzc(paramParcel, 1000, this.zzdxs);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public static final class Builder {
    private boolean zziam = false;
    
    private String zziao = "";
    
    private int zziap = 0;
    
    public final AutocompleteFilter build() {
      return new AutocompleteFilter(1, false, Arrays.asList(new Integer[] { Integer.valueOf(this.zziap) }, ), this.zziao);
    }
    
    public final Builder setCountry(String param1String) {
      this.zziao = param1String;
      return this;
    }
    
    public final Builder setTypeFilter(int param1Int) {
      this.zziap = param1Int;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/AutocompleteFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */