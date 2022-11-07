package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddPlaceRequest extends zzbck {
  public static final Parcelable.Creator<AddPlaceRequest> CREATOR = new zzb();
  
  private final String mName;
  
  private final String zzgsd;
  
  private final LatLng zziai;
  
  private final List<Integer> zziaj;
  
  private final String zziak;
  
  private final Uri zzial;
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, Uri paramUri) {
    this(paramString1, paramLatLng, paramString2, paramList, null, (Uri)zzbp.zzu(paramUri));
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3) {
    this(paramString1, paramLatLng, paramString2, paramList, zzbp.zzgg(paramString3), null);
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3, Uri paramUri) {
    this.mName = zzbp.zzgg(paramString1);
    this.zziai = (LatLng)zzbp.zzu(paramLatLng);
    this.zzgsd = zzbp.zzgg(paramString2);
    ArrayList<Integer> arrayList = new ArrayList((Collection)zzbp.zzu(paramList));
    this.zziaj = arrayList;
    boolean bool = arrayList.isEmpty();
    boolean bool1 = true;
    zzbp.zzb(bool ^ true, "At least one place type should be provided.");
    bool = bool1;
    if (TextUtils.isEmpty(paramString3))
      if (paramUri != null) {
        bool = bool1;
      } else {
        bool = false;
      }  
    zzbp.zzb(bool, "One of phone number or URI should be provided.");
    this.zziak = paramString3;
    this.zzial = paramUri;
  }
  
  public String getAddress() {
    return this.zzgsd;
  }
  
  public LatLng getLatLng() {
    return this.zziai;
  }
  
  public String getName() {
    return this.mName;
  }
  
  @Nullable
  public String getPhoneNumber() {
    return this.zziak;
  }
  
  public List<Integer> getPlaceTypes() {
    return this.zziaj;
  }
  
  @Nullable
  public Uri getWebsiteUri() {
    return this.zzial;
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("name", this.mName).zzg("latLng", this.zziai).zzg("address", this.zzgsd).zzg("placeTypes", this.zziaj).zzg("phoneNumer", this.zziak).zzg("websiteUri", this.zzial).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, getName(), false);
    zzbcn.zza(paramParcel, 2, (Parcelable)getLatLng(), paramInt, false);
    zzbcn.zza(paramParcel, 3, getAddress(), false);
    zzbcn.zza(paramParcel, 4, getPlaceTypes(), false);
    zzbcn.zza(paramParcel, 5, getPhoneNumber(), false);
    zzbcn.zza(paramParcel, 6, (Parcelable)getWebsiteUri(), paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/AddPlaceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */