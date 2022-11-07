package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity extends zzbck implements ReflectedParcelable, Place {
  public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzae();
  
  private final String mName;
  
  private final String zzbsx;
  
  private final String zzgsd;
  
  private final LatLng zziai;
  
  private final List<Integer> zziaj;
  
  private final String zziak;
  
  private final Uri zzial;
  
  private Locale zzibw;
  
  private final Bundle zzicw;
  
  @Deprecated
  private final zzak zzicx;
  
  private final float zzicy;
  
  private final LatLngBounds zzicz;
  
  private final String zzida;
  
  private final boolean zzidb;
  
  private final float zzidc;
  
  private final int zzidd;
  
  private final List<Integer> zzide;
  
  private final String zzidf;
  
  private final List<String> zzidg;
  
  private final zzam zzidh;
  
  private final zzaf zzidi;
  
  private final String zzidj;
  
  private final Map<Integer, String> zzidk;
  
  private final TimeZone zzidl;
  
  PlaceEntity(String paramString1, List<Integer> paramList1, List<Integer> paramList2, Bundle paramBundle, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString6, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt, zzak paramzzak, zzam paramzzam, zzaf paramzzaf, String paramString7) {
    Bundle bundle;
    List<?> list;
    String str;
    this.zzbsx = paramString1;
    this.zziaj = Collections.unmodifiableList(paramList1);
    this.zzide = paramList2;
    if (paramBundle != null) {
      bundle = paramBundle;
    } else {
      bundle = new Bundle();
    } 
    this.zzicw = bundle;
    this.mName = paramString2;
    this.zzgsd = paramString3;
    this.zziak = paramString4;
    this.zzidf = paramString5;
    if (paramList != null) {
      list = paramList;
    } else {
      list = Collections.emptyList();
    } 
    this.zzidg = (List)list;
    this.zziai = paramLatLng;
    this.zzicy = paramFloat1;
    this.zzicz = paramLatLngBounds;
    if (paramString6 != null) {
      str = paramString6;
    } else {
      str = "UTC";
    } 
    this.zzida = str;
    this.zzial = paramUri;
    this.zzidb = paramBoolean;
    this.zzidc = paramFloat2;
    this.zzidd = paramInt;
    this.zzidk = Collections.unmodifiableMap(new HashMap<Integer, String>());
    this.zzidl = null;
    this.zzibw = null;
    this.zzicx = paramzzak;
    this.zzidh = paramzzam;
    this.zzidi = paramzzaf;
    this.zzidj = paramString7;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof PlaceEntity))
      return false; 
    paramObject = paramObject;
    return (this.zzbsx.equals(((PlaceEntity)paramObject).zzbsx) && zzbf.equal(this.zzibw, ((PlaceEntity)paramObject).zzibw));
  }
  
  public final CharSequence getAttributions() {
    return zzg.zzj(this.zzidg);
  }
  
  public final String getId() {
    return this.zzbsx;
  }
  
  public final LatLng getLatLng() {
    return this.zziai;
  }
  
  public final Locale getLocale() {
    return this.zzibw;
  }
  
  public final List<Integer> getPlaceTypes() {
    return this.zziaj;
  }
  
  public final int getPriceLevel() {
    return this.zzidd;
  }
  
  public final float getRating() {
    return this.zzidc;
  }
  
  public final LatLngBounds getViewport() {
    return this.zzicz;
  }
  
  public final Uri getWebsiteUri() {
    return this.zzial;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzbsx, this.zzibw });
  }
  
  public final boolean isDataValid() {
    return true;
  }
  
  public final void setLocale(Locale paramLocale) {
    this.zzibw = paramLocale;
  }
  
  @SuppressLint({"DefaultLocale"})
  public final String toString() {
    return zzbf.zzt(this).zzg("id", this.zzbsx).zzg("placeTypes", this.zziaj).zzg("locale", this.zzibw).zzg("name", this.mName).zzg("address", this.zzgsd).zzg("phoneNumber", this.zziak).zzg("latlng", this.zziai).zzg("viewport", this.zzicz).zzg("websiteUri", this.zzial).zzg("isPermanentlyClosed", Boolean.valueOf(this.zzidb)).zzg("priceLevel", Integer.valueOf(this.zzidd)).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, getId(), false);
    zzbcn.zza(paramParcel, 2, this.zzicw, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzicx, paramInt, false);
    zzbcn.zza(paramParcel, 4, (Parcelable)getLatLng(), paramInt, false);
    zzbcn.zza(paramParcel, 5, this.zzicy);
    zzbcn.zza(paramParcel, 6, (Parcelable)getViewport(), paramInt, false);
    zzbcn.zza(paramParcel, 7, this.zzida, false);
    zzbcn.zza(paramParcel, 8, (Parcelable)getWebsiteUri(), paramInt, false);
    zzbcn.zza(paramParcel, 9, this.zzidb);
    zzbcn.zza(paramParcel, 10, getRating());
    zzbcn.zzc(paramParcel, 11, getPriceLevel());
    zzbcn.zza(paramParcel, 13, this.zzide, false);
    zzbcn.zza(paramParcel, 14, (String)getAddress(), false);
    zzbcn.zza(paramParcel, 15, (String)getPhoneNumber(), false);
    zzbcn.zza(paramParcel, 16, this.zzidf, false);
    zzbcn.zzb(paramParcel, 17, this.zzidg, false);
    zzbcn.zza(paramParcel, 19, (String)getName(), false);
    zzbcn.zza(paramParcel, 20, getPlaceTypes(), false);
    zzbcn.zza(paramParcel, 21, (Parcelable)this.zzidh, paramInt, false);
    zzbcn.zza(paramParcel, 22, (Parcelable)this.zzidi, paramInt, false);
    zzbcn.zza(paramParcel, 23, this.zzidj, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public static final class zza {
    private String mName;
    
    private String zzbsx;
    
    private String zzgsd;
    
    private LatLng zziai;
    
    private String zziak;
    
    private Uri zzial;
    
    private float zzicy;
    
    private LatLngBounds zzicz;
    
    private boolean zzidb;
    
    private float zzidc = -1.0F;
    
    private int zzidd = -1;
    
    private List<String> zzidg;
    
    private zzam zzidh;
    
    private String zzidj;
    
    private List<Integer> zzidm;
    
    private zzaf zzidn;
    
    public final zza zza(zzaf param1zzaf) {
      this.zzidn = param1zzaf;
      return this;
    }
    
    public final zza zza(zzam param1zzam) {
      this.zzidh = param1zzam;
      return this;
    }
    
    public final zza zza(LatLng param1LatLng) {
      this.zziai = param1LatLng;
      return this;
    }
    
    public final zza zza(LatLngBounds param1LatLngBounds) {
      this.zzicz = param1LatLngBounds;
      return this;
    }
    
    public final zza zzab(List<Integer> param1List) {
      this.zzidm = param1List;
      return this;
    }
    
    public final zza zzac(List<String> param1List) {
      this.zzidg = param1List;
      return this;
    }
    
    public final PlaceEntity zzatf() {
      String str1 = this.zzbsx;
      List<Integer> list = this.zzidm;
      List<?> list1 = Collections.emptyList();
      String str2 = this.mName;
      String str3 = this.zzgsd;
      String str4 = this.zziak;
      List<String> list2 = this.zzidg;
      return new PlaceEntity(str1, list, (List)list1, null, str2, str3, str4, null, list2, this.zziai, this.zzicy, this.zzicz, null, this.zzial, this.zzidb, this.zzidc, this.zzidd, new zzak(str2, str3, str4, null, list2), this.zzidh, this.zzidn, this.zzidj);
    }
    
    public final zza zzbl(boolean param1Boolean) {
      this.zzidb = param1Boolean;
      return this;
    }
    
    public final zza zzc(float param1Float) {
      this.zzicy = param1Float;
      return this;
    }
    
    public final zza zzd(float param1Float) {
      this.zzidc = param1Float;
      return this;
    }
    
    public final zza zzdt(int param1Int) {
      this.zzidd = param1Int;
      return this;
    }
    
    public final zza zzic(String param1String) {
      this.zzbsx = param1String;
      return this;
    }
    
    public final zza zzid(String param1String) {
      this.mName = param1String;
      return this;
    }
    
    public final zza zzie(String param1String) {
      this.zzgsd = param1String;
      return this;
    }
    
    public final zza zzif(String param1String) {
      this.zziak = param1String;
      return this;
    }
    
    public final zza zzig(String param1String) {
      this.zzidj = param1String;
      return this;
    }
    
    public final zza zzo(Uri param1Uri) {
      this.zzial = param1Uri;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/PlaceEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */