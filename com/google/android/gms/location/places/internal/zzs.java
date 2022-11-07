package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public interface zzs extends IInterface {
  void zza(AddPlaceRequest paramAddPlaceRequest, zzat paramzzat, zzw paramzzw) throws RemoteException;
  
  void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzat paramzzat, zzu paramzzu) throws RemoteException;
  
  void zza(String paramString, zzat paramzzat, zzu paramzzu) throws RemoteException;
  
  void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, zzat paramzzat, zzw paramzzw) throws RemoteException;
  
  void zza(List<String> paramList, zzat paramzzat, zzw paramzzw) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */