package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;

public abstract class zzm<R extends Result, A extends Api.zzb> extends zzs<R> implements zzn<R> {
  private final Api<?> zzfdg;
  
  private final Api.zzc<A> zzfiv;
  
  @Deprecated
  protected zzm(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient) {
    super((GoogleApiClient)zzbp.zzb(paramGoogleApiClient, "GoogleApiClient must not be null"));
    this.zzfiv = (Api.zzc<A>)zzbp.zzu(paramzzc);
    this.zzfdg = null;
  }
  
  protected zzm(Api<?> paramApi, GoogleApiClient paramGoogleApiClient) {
    super((GoogleApiClient)zzbp.zzb(paramGoogleApiClient, "GoogleApiClient must not be null"));
    this.zzfiv = paramApi.zzafe();
    this.zzfdg = paramApi;
  }
  
  private final void zzc(RemoteException paramRemoteException) {
    zzt(new Status(8, paramRemoteException.getLocalizedMessage(), null));
  }
  
  protected abstract void zza(A paramA) throws RemoteException;
  
  public final Api.zzc<A> zzafe() {
    return this.zzfiv;
  }
  
  public final Api<?> zzafj() {
    return this.zzfdg;
  }
  
  public final void zzb(A paramA) throws DeadObjectException {
    try {
      zza(paramA);
      return;
    } catch (DeadObjectException deadObjectException) {
      zzc((RemoteException)deadObjectException);
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      zzc(remoteException);
      return;
    } 
  }
  
  public final void zzt(Status paramStatus) {
    zzbp.zzb(paramStatus.isSuccess() ^ true, "Failed result must not be success");
    setResult(zzb(paramStatus));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */