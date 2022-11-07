package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzt extends zzeb implements zzs {
  zzt(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.places.internal.IGooglePlacesService");
  }
  
  public final void zza(AddPlaceRequest paramAddPlaceRequest, zzat paramzzat, zzw paramzzw) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramAddPlaceRequest);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzw);
    zzb(14, parcel);
  }
  
  public final void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzat paramzzat, zzu paramzzu) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    parcel.writeInt(paramInt1);
    parcel.writeInt(paramInt2);
    parcel.writeInt(paramInt3);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzu);
    zzb(20, parcel);
  }
  
  public final void zza(String paramString, zzat paramzzat, zzu paramzzu) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzu);
    zzb(19, parcel);
  }
  
  public final void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, zzat paramzzat, zzw paramzzw) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzed.zza(parcel, (Parcelable)paramLatLngBounds);
    zzed.zza(parcel, (Parcelable)paramAutocompleteFilter);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzw);
    zzb(13, parcel);
  }
  
  public final void zza(List<String> paramList, zzat paramzzat, zzw paramzzw) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeStringList(paramList);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzw);
    zzb(17, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */