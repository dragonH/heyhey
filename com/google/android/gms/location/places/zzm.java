package com.google.android.gms.location.places;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzcak;
import com.google.android.gms.location.places.internal.zzx;

public class zzm extends zzx {
  private static final String TAG = zzm.class.getSimpleName();
  
  private final zzd zzibo = null;
  
  private final zza zzibp;
  
  private final zze zzibq;
  
  private final zzf zzibr;
  
  private final zzc zzibs;
  
  public zzm(zza paramzza) {
    this.zzibp = paramzza;
    this.zzibq = null;
    this.zzibr = null;
    this.zzibs = null;
  }
  
  public zzm(zzc paramzzc) {
    this.zzibp = null;
    this.zzibq = null;
    this.zzibr = null;
    this.zzibs = paramzzc;
  }
  
  public zzm(zzd paramzzd) {
    this.zzibp = null;
    this.zzibq = null;
    this.zzibr = null;
    this.zzibs = null;
  }
  
  public zzm(zzf paramzzf) {
    this.zzibp = null;
    this.zzibq = null;
    this.zzibr = paramzzf;
    this.zzibs = null;
  }
  
  public final void zzaf(Status paramStatus) throws RemoteException {
    this.zzibr.setResult((Result)paramStatus);
  }
  
  public final void zzao(DataHolder paramDataHolder) throws RemoteException {
    String str;
    boolean bool;
    int i;
    if (this.zzibo != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "placeEstimator cannot be null");
    if (paramDataHolder == null) {
      str = TAG;
      if (Log.isLoggable(str, 6))
        Log.e(str, "onPlaceEstimated received null DataHolder", new Throwable()); 
      this.zzibo.zzt(Status.zzfhx);
      return;
    } 
    Bundle bundle = str.zzafi();
    if (bundle == null) {
      i = 100;
    } else {
      i = PlaceLikelihoodBuffer.zzw(bundle);
    } 
    PlaceLikelihoodBuffer placeLikelihoodBuffer = new PlaceLikelihoodBuffer((DataHolder)str, i);
    this.zzibo.setResult(placeLikelihoodBuffer);
  }
  
  public final void zzap(DataHolder paramDataHolder) throws RemoteException {
    String str;
    if (paramDataHolder == null) {
      str = TAG;
      if (Log.isLoggable(str, 6))
        Log.e(str, "onAutocompletePrediction received null DataHolder", new Throwable()); 
      this.zzibp.zzt(Status.zzfhx);
      return;
    } 
    this.zzibp.setResult(new AutocompletePredictionBuffer((DataHolder)str));
  }
  
  public final void zzaq(DataHolder paramDataHolder) throws RemoteException {
    Status status;
    if (paramDataHolder == null) {
      String str = TAG;
      if (Log.isLoggable(str, 6))
        Log.e(str, "onPlaceUserDataFetched received null DataHolder", new Throwable()); 
      status = Status.zzfhx;
      throw null;
    } 
    new zzcak((DataHolder)status);
    throw null;
  }
  
  public final void zzar(DataHolder paramDataHolder) throws RemoteException {
    PlaceBuffer placeBuffer = new PlaceBuffer(paramDataHolder);
    this.zzibs.setResult(placeBuffer);
  }
  
  public static abstract class zza<A extends Api.zze> extends zzb<AutocompletePredictionBuffer, A> {
    public zza(Api param1Api, GoogleApiClient param1GoogleApiClient) {
      super(param1Api, param1GoogleApiClient);
    }
  }
  
  public static abstract class zzb<R extends Result, A extends Api.zze> extends com.google.android.gms.common.api.internal.zzm<R, A> {
    public zzb(Api param1Api, GoogleApiClient param1GoogleApiClient) {
      super(param1Api, param1GoogleApiClient);
    }
  }
  
  public static abstract class zzc<A extends Api.zze> extends zzb<PlaceBuffer, A> {
    public zzc(Api param1Api, GoogleApiClient param1GoogleApiClient) {
      super(param1Api, param1GoogleApiClient);
    }
  }
  
  public static abstract class zzd<A extends Api.zze> extends zzb<PlaceLikelihoodBuffer, A> {
    public zzd(Api param1Api, GoogleApiClient param1GoogleApiClient) {
      super(param1Api, param1GoogleApiClient);
    }
  }
  
  @Deprecated
  public static abstract class zze<A extends Api.zze> extends zzb<zzcak, A> {}
  
  public static abstract class zzf<A extends Api.zze> extends zzb<Status, A> {
    public zzf(Api param1Api, GoogleApiClient param1GoogleApiClient) {
      super(param1Api, param1GoogleApiClient);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */