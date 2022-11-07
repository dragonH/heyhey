package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public final class zzdg<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
  private final Object zzfjf;
  
  private final WeakReference<GoogleApiClient> zzfjh;
  
  private ResultTransform<? super R, ? extends Result> zzfph;
  
  private zzdg<? extends Result> zzfpi;
  
  private volatile ResultCallbacks<? super R> zzfpj;
  
  private PendingResult<R> zzfpk;
  
  private Status zzfpl;
  
  private final zzdi zzfpm;
  
  private boolean zzfpn;
  
  public zzdg(WeakReference<GoogleApiClient> paramWeakReference) {
    Looper looper;
    this.zzfph = null;
    this.zzfpi = null;
    this.zzfpj = null;
    this.zzfpk = null;
    this.zzfjf = new Object();
    this.zzfpl = null;
    this.zzfpn = false;
    zzbp.zzb(paramWeakReference, "GoogleApiClient reference must not be null");
    this.zzfjh = paramWeakReference;
    GoogleApiClient googleApiClient = paramWeakReference.get();
    if (googleApiClient != null) {
      looper = googleApiClient.getLooper();
    } else {
      looper = Looper.getMainLooper();
    } 
    this.zzfpm = new zzdi(this, looper);
  }
  
  private final void zzain() {
    if (this.zzfph == null && this.zzfpj == null)
      return; 
    GoogleApiClient googleApiClient = this.zzfjh.get();
    if (!this.zzfpn && this.zzfph != null && googleApiClient != null) {
      googleApiClient.zza(this);
      this.zzfpn = true;
    } 
    Status status = this.zzfpl;
    if (status != null) {
      zzw(status);
      return;
    } 
    PendingResult<R> pendingResult = this.zzfpk;
    if (pendingResult != null)
      pendingResult.setResultCallback(this); 
  }
  
  private final boolean zzaip() {
    GoogleApiClient googleApiClient = this.zzfjh.get();
    return (this.zzfpj != null && googleApiClient != null);
  }
  
  private static void zzd(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(str.length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        Log.w("TransformedResultImpl", stringBuilder.toString(), runtimeException);
      }  
  }
  
  private final void zzd(Status paramStatus) {
    synchronized (this.zzfjf) {
      this.zzfpl = paramStatus;
      zzw(paramStatus);
      return;
    } 
  }
  
  private final void zzw(Status paramStatus) {
    synchronized (this.zzfjf) {
      ResultTransform<? super R, ? extends Result> resultTransform = this.zzfph;
      if (resultTransform != null) {
        paramStatus = resultTransform.onFailure(paramStatus);
        zzbp.zzb(paramStatus, "onFailure must not return null");
        this.zzfpi.zzd(paramStatus);
      } else if (zzaip()) {
        this.zzfpj.onFailure(paramStatus);
      } 
      return;
    } 
  }
  
  public final void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks) {
    synchronized (this.zzfjf) {
      boolean bool2;
      ResultCallbacks<? super R> resultCallbacks = this.zzfpj;
      boolean bool1 = true;
      if (resultCallbacks == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call andFinally() twice.");
      if (this.zzfph == null) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call then() and andFinally() on the same TransformedResult.");
      this.zzfpj = paramResultCallbacks;
      zzain();
      return;
    } 
  }
  
  public final void onResult(R paramR) {
    synchronized (this.zzfjf) {
      if (paramR.getStatus().isSuccess()) {
        if (this.zzfph != null) {
          ExecutorService executorService = zzct.zzahn();
          zzdh zzdh = new zzdh();
          this(this, (Result)paramR);
          executorService.submit(zzdh);
        } else if (zzaip()) {
          this.zzfpj.onSuccess((Result)paramR);
        } 
      } else {
        zzd(paramR.getStatus());
        zzd((Result)paramR);
      } 
      return;
    } 
  }
  
  @NonNull
  public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform) {
    synchronized (this.zzfjf) {
      boolean bool2;
      ResultTransform<? super R, ? extends Result> resultTransform = this.zzfph;
      boolean bool1 = true;
      if (resultTransform == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call then() twice.");
      if (this.zzfpj == null) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      zzbp.zza(bool2, "Cannot call then() and andFinally() on the same TransformedResult.");
      this.zzfph = paramResultTransform;
      zzdg<? extends Result> zzdg1 = new zzdg();
      this(this.zzfjh);
      this.zzfpi = zzdg1;
      zzain();
      return (TransformedResult)zzdg1;
    } 
  }
  
  public final void zza(PendingResult<?> paramPendingResult) {
    synchronized (this.zzfjf) {
      this.zzfpk = (PendingResult)paramPendingResult;
      zzain();
      return;
    } 
  }
  
  final void zzaio() {
    this.zzfpj = null;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */