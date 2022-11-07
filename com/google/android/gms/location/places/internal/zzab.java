package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzm;
import java.util.Locale;

public final class zzab extends zzaa<zzq> {
  private final Locale zzibw;
  
  private final zzat zzict;
  
  private zzab(Context paramContext, Looper paramLooper, zzq paramzzq, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions) {
    super(paramContext, paramLooper, 67, paramzzq, paramConnectionCallbacks, paramOnConnectionFailedListener);
    Locale locale = Locale.getDefault();
    this.zzibw = locale;
    if (paramzzq.getAccount() != null) {
      String str = (paramzzq.getAccount()).name;
    } else {
      paramContext = null;
    } 
    this.zzict = new zzat(paramString, locale, (String)paramContext, null, 0);
  }
  
  public final void zza(zzm paramzzm, PlaceFilter paramPlaceFilter) throws RemoteException {
    PlaceFilter placeFilter = paramPlaceFilter;
    if (paramPlaceFilter == null)
      placeFilter = PlaceFilter.zzasy(); 
    ((zzq)zzajj()).zza(placeFilter, this.zzict, (zzw)paramzzm);
  }
  
  public final void zza(zzm paramzzm, PlaceReport paramPlaceReport) throws RemoteException {
    zzbp.zzu(paramPlaceReport);
    ((zzq)zzajj()).zza(paramPlaceReport, this.zzict, (zzw)paramzzm);
  }
  
  protected final String zzhc() {
    return "com.google.android.gms.location.places.PlaceDetectionApi";
  }
  
  protected final String zzhd() {
    return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */