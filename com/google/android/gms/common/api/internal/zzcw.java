package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbs;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcpy;
import com.google.android.gms.internal.zzcqf;
import java.util.Set;

public final class zzcw extends zzcpx implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private static Api.zza<? extends zzcps, zzcpt> zzfpd = zzcpp.zzdwq;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private Set<Scope> zzecm;
  
  private final Api.zza<? extends zzcps, zzcpt> zzfgf;
  
  private zzq zzfkj;
  
  private zzcps zzflp;
  
  private zzcy zzfpe;
  
  @WorkerThread
  public zzcw(Context paramContext, Handler paramHandler, @NonNull zzq paramzzq) {
    this(paramContext, paramHandler, paramzzq, zzfpd);
  }
  
  @WorkerThread
  public zzcw(Context paramContext, Handler paramHandler, @NonNull zzq paramzzq, Api.zza<? extends zzcps, zzcpt> paramzza) {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.zzfkj = (zzq)zzbp.zzb(paramzzq, "ClientSettings must not be null");
    this.zzecm = paramzzq.zzajr();
    this.zzfgf = paramzza;
  }
  
  @WorkerThread
  private final void zzc(zzcqf paramzzcqf) {
    ConnectionResult connectionResult1 = paramzzcqf.zzagd();
    ConnectionResult connectionResult2 = connectionResult1;
    if (connectionResult1.isSuccess()) {
      String str;
      zzbs zzbs = paramzzcqf.zzbcc();
      connectionResult2 = zzbs.zzagd();
      if (!connectionResult2.isSuccess()) {
        str = String.valueOf(connectionResult2);
        StringBuilder stringBuilder = new StringBuilder(str.length() + 48);
        stringBuilder.append("Sign-in succeeded with resolve account failure: ");
        stringBuilder.append(str);
        Log.wtf("SignInCoordinator", stringBuilder.toString(), new Exception());
      } else {
        this.zzfpe.zzb(str.zzakl(), this.zzecm);
        this.zzflp.disconnect();
      } 
    } 
    this.zzfpe.zzh(connectionResult2);
    this.zzflp.disconnect();
  }
  
  @WorkerThread
  public final void onConnected(@Nullable Bundle paramBundle) {
    this.zzflp.zza((zzcpy)this);
  }
  
  @WorkerThread
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    this.zzfpe.zzh(paramConnectionResult);
  }
  
  @WorkerThread
  public final void onConnectionSuspended(int paramInt) {
    this.zzflp.disconnect();
  }
  
  @WorkerThread
  public final void zza(zzcy paramzzcy) {
    zzcps zzcps2 = this.zzflp;
    if (zzcps2 != null)
      zzcps2.disconnect(); 
    this.zzfkj.zzc(Integer.valueOf(System.identityHashCode(this)));
    Api.zza<? extends zzcps, zzcpt> zza1 = this.zzfgf;
    Context context = this.mContext;
    Looper looper = this.mHandler.getLooper();
    zzq zzq1 = this.zzfkj;
    zzcps zzcps1 = (zzcps)zza1.zza(context, looper, zzq1, zzq1.zzajx(), this, this);
    this.zzflp = zzcps1;
    this.zzfpe = paramzzcy;
    zzcps1.connect();
  }
  
  public final zzcps zzaic() {
    return this.zzflp;
  }
  
  public final void zzaim() {
    zzcps zzcps1 = this.zzflp;
    if (zzcps1 != null)
      zzcps1.disconnect(); 
  }
  
  @BinderThread
  public final void zzb(zzcqf paramzzcqf) {
    this.mHandler.post(new zzcx(this, paramzzcqf));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */