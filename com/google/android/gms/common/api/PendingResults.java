package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzcp;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzs;
import com.google.android.gms.common.internal.zzbp;

public final class PendingResults {
  public static PendingResult<Status> canceledPendingResult() {
    zzda zzda = new zzda(Looper.getMainLooper());
    zzda.cancel();
    return (PendingResult<Status>)zzda;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR) {
    boolean bool;
    zzbp.zzb(paramR, "Result must not be null");
    if (paramR.getStatus().getStatusCode() == 16) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "Status code must be CommonStatusCodes.CANCELED");
    zza<R> zza = new zza<R>(paramR);
    zza.cancel();
    return (PendingResult<R>)zza;
  }
  
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR) {
    zzbp.zzb(paramR, "Result must not be null");
    zzc<Result> zzc = new zzc<Result>(null);
    zzc.setResult((Result)paramR);
    return (OptionalPendingResult<R>)new zzcp((PendingResult)zzc);
  }
  
  public static PendingResult<Status> immediatePendingResult(Status paramStatus) {
    zzbp.zzb(paramStatus, "Result must not be null");
    zzda zzda = new zzda(Looper.getMainLooper());
    zzda.setResult(paramStatus);
    return (PendingResult<Status>)zzda;
  }
  
  public static <R extends Result> PendingResult<R> zza(R paramR, GoogleApiClient paramGoogleApiClient) {
    zzbp.zzb(paramR, "Result must not be null");
    zzbp.zzb(paramR.getStatus().isSuccess() ^ true, "Status code must not be SUCCESS");
    zzb<R> zzb = new zzb<R>(paramGoogleApiClient, paramR);
    zzb.setResult((Result)paramR);
    return (PendingResult<R>)zzb;
  }
  
  public static PendingResult<Status> zza(Status paramStatus, GoogleApiClient paramGoogleApiClient) {
    zzbp.zzb(paramStatus, "Result must not be null");
    zzda zzda = new zzda(paramGoogleApiClient);
    zzda.setResult(paramStatus);
    return (PendingResult<Status>)zzda;
  }
  
  public static <R extends Result> OptionalPendingResult<R> zzb(R paramR, GoogleApiClient paramGoogleApiClient) {
    zzbp.zzb(paramR, "Result must not be null");
    zzc<Result> zzc = new zzc<Result>(paramGoogleApiClient);
    zzc.setResult((Result)paramR);
    return (OptionalPendingResult<R>)new zzcp((PendingResult)zzc);
  }
  
  static final class zza<R extends Result> extends zzs<R> {
    private final R zzfhq;
    
    public zza(R param1R) {
      super(Looper.getMainLooper());
      this.zzfhq = param1R;
    }
    
    protected final R zzb(Status param1Status) {
      if (param1Status.getStatusCode() == this.zzfhq.getStatus().getStatusCode())
        return this.zzfhq; 
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
  
  static final class zzb<R extends Result> extends zzs<R> {
    private final R zzfhr;
    
    public zzb(GoogleApiClient param1GoogleApiClient, R param1R) {
      super(param1GoogleApiClient);
      this.zzfhr = param1R;
    }
    
    protected final R zzb(Status param1Status) {
      return this.zzfhr;
    }
  }
  
  static final class zzc<R extends Result> extends zzs<R> {
    public zzc(GoogleApiClient param1GoogleApiClient) {
      super(param1GoogleApiClient);
    }
    
    protected final R zzb(Status param1Status) {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/PendingResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */