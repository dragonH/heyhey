package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzd;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public final class zzn extends zzaa<zzs> {
  private final zzat zzict;
  
  private zzn(Context paramContext, Looper paramLooper, zzq paramzzq, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions) {
    super(paramContext, paramLooper, 65, paramzzq, paramConnectionCallbacks, paramOnConnectionFailedListener);
    Locale locale = Locale.getDefault();
    if (paramzzq.getAccount() != null) {
      String str = (paramzzq.getAccount()).name;
    } else {
      paramContext = null;
    } 
    this.zzict = new zzat(paramString, locale, (String)paramContext, null, 0);
  }
  
  public final void zza(zzd paramzzd, String paramString) throws RemoteException {
    zzbp.zzb(paramzzd, "callback cannot be null");
    ((zzs)zzajj()).zza(paramString, this.zzict, (zzu)paramzzd);
  }
  
  public final void zza(zzd paramzzd, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    zzbp.zzb(paramzzd, "callback cannot be null");
    ((zzs)zzajj()).zza(paramString, paramInt1, paramInt2, paramInt3, this.zzict, (zzu)paramzzd);
  }
  
  public final void zza(zzm paramzzm, AddPlaceRequest paramAddPlaceRequest) throws RemoteException {
    zzbp.zzb(paramAddPlaceRequest, "userAddedPlace == null");
    ((zzs)zzajj()).zza(paramAddPlaceRequest, this.zzict, (zzw)paramzzm);
  }
  
  public final void zza(zzm paramzzm, String paramString, @Nullable LatLngBounds paramLatLngBounds, @Nullable AutocompleteFilter paramAutocompleteFilter) throws RemoteException {
    zzbp.zzb(paramzzm, "callback == null");
    String str = paramString;
    if (paramString == null)
      str = ""; 
    AutocompleteFilter autocompleteFilter = paramAutocompleteFilter;
    if (paramAutocompleteFilter == null)
      autocompleteFilter = (new AutocompleteFilter.Builder()).build(); 
    ((zzs)zzajj()).zza(str, paramLatLngBounds, autocompleteFilter, this.zzict, (zzw)paramzzm);
  }
  
  public final void zza(zzm paramzzm, List<String> paramList) throws RemoteException {
    ((zzs)zzajj()).zza(paramList, this.zzict, (zzw)paramzzm);
  }
  
  protected final String zzhc() {
    return "com.google.android.gms.location.places.GeoDataApi";
  }
  
  protected final String zzhd() {
    return "com.google.android.gms.location.places.internal.IGooglePlacesService";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */