package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzby;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbr<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzx {
  private final zzh<O> zzfgs;
  
  private final Api.zze zzfkh;
  
  private boolean zzfmi;
  
  private final Queue<zza> zzfnv = new LinkedList<zza>();
  
  private final Api.zzb zzfnw;
  
  private final zzah zzfnx;
  
  private final Set<zzj> zzfny = new HashSet<zzj>();
  
  private final Map<zzcl<?>, zzcs> zzfnz = new HashMap<zzcl<?>, zzcs>();
  
  private final int zzfoa;
  
  private final zzcw zzfob;
  
  private ConnectionResult zzfoc = null;
  
  @WorkerThread
  public zzbr(zzbp paramzzbp, GoogleApi<O> paramGoogleApi) {
    Api.zze zze1 = paramGoogleApi.zza(zzbp.zza(paramzzbp).getLooper(), this);
    this.zzfkh = zze1;
    if (zze1 instanceof zzby) {
      this.zzfnw = (Api.zzb)zzby.zzako();
    } else {
      this.zzfnw = (Api.zzb)zze1;
    } 
    this.zzfgs = paramGoogleApi.zzafk();
    this.zzfnx = new zzah();
    this.zzfoa = paramGoogleApi.getInstanceId();
    if (zze1.zzaac()) {
      this.zzfob = paramGoogleApi.zza(zzbp.zzb(paramzzbp), zzbp.zza(paramzzbp));
      return;
    } 
    this.zzfob = null;
  }
  
  @WorkerThread
  private final void zzahu() {
    zzahx();
    zzi(ConnectionResult.zzfff);
    zzahz();
    for (zzcs zzcs : this.zzfnz.values()) {
      try {
        zzcr<Api.zzb, ?> zzcr = zzcs.zzfid;
        Api.zzb zzb1 = this.zzfnw;
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource();
        this();
        zzcr.zzb(zzb1, taskCompletionSource);
      } catch (DeadObjectException deadObjectException) {
        onConnectionSuspended(1);
        this.zzfkh.disconnect();
        break;
      } catch (RemoteException remoteException) {}
    } 
    while (this.zzfkh.isConnected() && !this.zzfnv.isEmpty())
      zzb(this.zzfnv.remove()); 
    zzaia();
  }
  
  @WorkerThread
  private final void zzahv() {
    zzahx();
    this.zzfmi = true;
    this.zzfnx.zzagu();
    zzbp.zza(this.zzfnu).sendMessageDelayed(Message.obtain(zzbp.zza(this.zzfnu), 9, this.zzfgs), zzbp.zzc(this.zzfnu));
    zzbp.zza(this.zzfnu).sendMessageDelayed(Message.obtain(zzbp.zza(this.zzfnu), 11, this.zzfgs), zzbp.zzd(this.zzfnu));
    zzbp.zza(this.zzfnu, -1);
  }
  
  @WorkerThread
  private final void zzahz() {
    if (this.zzfmi) {
      zzbp.zza(this.zzfnu).removeMessages(11, this.zzfgs);
      zzbp.zza(this.zzfnu).removeMessages(9, this.zzfgs);
      this.zzfmi = false;
    } 
  }
  
  private final void zzaia() {
    zzbp.zza(this.zzfnu).removeMessages(12, this.zzfgs);
    zzbp.zza(this.zzfnu).sendMessageDelayed(zzbp.zza(this.zzfnu).obtainMessage(12, this.zzfgs), zzbp.zzh(this.zzfnu));
  }
  
  @WorkerThread
  private final void zzb(zza paramzza) {
    paramzza.zza(this.zzfnx, zzaac());
    try {
      paramzza.zza(this);
      return;
    } catch (DeadObjectException deadObjectException) {
      onConnectionSuspended(1);
      this.zzfkh.disconnect();
      return;
    } 
  }
  
  @WorkerThread
  private final void zzi(ConnectionResult paramConnectionResult) {
    Iterator<zzj> iterator = this.zzfny.iterator();
    while (iterator.hasNext())
      ((zzj)iterator.next()).zza(this.zzfgs, paramConnectionResult); 
    this.zzfny.clear();
  }
  
  @WorkerThread
  public final void connect() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    if (!this.zzfkh.isConnected() && !this.zzfkh.isConnecting()) {
      if (this.zzfkh.zzaff() && zzbp.zzi(this.zzfnu) != 0) {
        zzbp zzbp1 = this.zzfnu;
        zzbp.zza(zzbp1, zzbp.zzg(zzbp1).isGooglePlayServicesAvailable(zzbp.zzb(this.zzfnu)));
        if (zzbp.zzi(this.zzfnu) != 0) {
          onConnectionFailed(new ConnectionResult(zzbp.zzi(this.zzfnu), null));
          return;
        } 
      } 
      zzbv zzbv = new zzbv(this.zzfnu, this.zzfkh, this.zzfgs);
      if (this.zzfkh.zzaac())
        this.zzfob.zza(zzbv); 
      this.zzfkh.zza(zzbv);
    } 
  }
  
  public final int getInstanceId() {
    return this.zzfoa;
  }
  
  final boolean isConnected() {
    return this.zzfkh.isConnected();
  }
  
  public final void onConnected(@Nullable Bundle paramBundle) {
    if (Looper.myLooper() == zzbp.zza(this.zzfnu).getLooper()) {
      zzahu();
      return;
    } 
    zzbp.zza(this.zzfnu).post(new zzbs(this));
  }
  
  @WorkerThread
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    zzbp.zza(zzbp.zza(this.zzfnu));
    zzcw zzcw1 = this.zzfob;
    if (zzcw1 != null)
      zzcw1.zzaim(); 
    zzahx();
    zzbp.zza(this.zzfnu, -1);
    zzi(paramConnectionResult);
    if (paramConnectionResult.getErrorCode() == 4) {
      zzv(zzbp.zzahs());
      return;
    } 
    if (this.zzfnv.isEmpty()) {
      this.zzfoc = paramConnectionResult;
      return;
    } 
    synchronized (zzbp.zzaht()) {
      if (zzbp.zze(this.zzfnu) != null && zzbp.zzf(this.zzfnu).contains(this.zzfgs)) {
        zzbp.zze(this.zzfnu).zzb(paramConnectionResult, this.zzfoa);
        return;
      } 
      if (!this.zzfnu.zzc(paramConnectionResult, this.zzfoa)) {
        if (paramConnectionResult.getErrorCode() == 18)
          this.zzfmi = true; 
        if (this.zzfmi) {
          zzbp.zza(this.zzfnu).sendMessageDelayed(Message.obtain(zzbp.zza(this.zzfnu), 9, this.zzfgs), zzbp.zzc(this.zzfnu));
          return;
        } 
        String str = this.zzfgs.zzafv();
        null = new StringBuilder(String.valueOf(str).length() + 38);
        null.append("API: ");
        null.append(str);
        null.append(" is not available on this device.");
        zzv(new Status(17, null.toString()));
      } 
      return;
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    if (Looper.myLooper() == zzbp.zza(this.zzfnu).getLooper()) {
      zzahv();
      return;
    } 
    zzbp.zza(this.zzfnu).post(new zzbt(this));
  }
  
  @WorkerThread
  public final void resume() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    if (this.zzfmi)
      connect(); 
  }
  
  @WorkerThread
  public final void signOut() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    zzv(zzbp.zzfnk);
    this.zzfnx.zzagt();
    Iterator<zzcl> iterator = this.zzfnz.keySet().iterator();
    while (iterator.hasNext())
      zza(new zzf(iterator.next(), new TaskCompletionSource())); 
    zzi(new ConnectionResult(4));
    this.zzfkh.disconnect();
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    if (Looper.myLooper() == zzbp.zza(this.zzfnu).getLooper()) {
      onConnectionFailed(paramConnectionResult);
      return;
    } 
    zzbp.zza(this.zzfnu).post(new zzbu(this, paramConnectionResult));
  }
  
  @WorkerThread
  public final void zza(zza paramzza) {
    zzbp.zza(zzbp.zza(this.zzfnu));
    if (this.zzfkh.isConnected()) {
      zzb(paramzza);
      zzaia();
      return;
    } 
    this.zzfnv.add(paramzza);
    ConnectionResult connectionResult = this.zzfoc;
    if (connectionResult != null && connectionResult.hasResolution()) {
      onConnectionFailed(this.zzfoc);
      return;
    } 
    connect();
  }
  
  @WorkerThread
  public final void zza(zzj paramzzj) {
    zzbp.zza(zzbp.zza(this.zzfnu));
    this.zzfny.add(paramzzj);
  }
  
  public final boolean zzaac() {
    return this.zzfkh.zzaac();
  }
  
  public final Api.zze zzagn() {
    return this.zzfkh;
  }
  
  @WorkerThread
  public final void zzahh() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    if (this.zzfmi) {
      Status status;
      zzahz();
      if (zzbp.zzg(this.zzfnu).isGooglePlayServicesAvailable(zzbp.zzb(this.zzfnu)) == 18) {
        status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
      } else {
        status = new Status(8, "API failed to connect while resuming due to an unknown error.");
      } 
      zzv(status);
      this.zzfkh.disconnect();
    } 
  }
  
  public final Map<zzcl<?>, zzcs> zzahw() {
    return this.zzfnz;
  }
  
  @WorkerThread
  public final void zzahx() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    this.zzfoc = null;
  }
  
  @WorkerThread
  public final ConnectionResult zzahy() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    return this.zzfoc;
  }
  
  @WorkerThread
  public final void zzaib() {
    zzbp.zza(zzbp.zza(this.zzfnu));
    if (this.zzfkh.isConnected() && this.zzfnz.size() == 0) {
      if (this.zzfnx.zzags()) {
        zzaia();
        return;
      } 
      this.zzfkh.disconnect();
    } 
  }
  
  final zzcps zzaic() {
    zzcw zzcw1 = this.zzfob;
    return (zzcw1 == null) ? null : zzcw1.zzaic();
  }
  
  @WorkerThread
  public final void zzh(@NonNull ConnectionResult paramConnectionResult) {
    zzbp.zza(zzbp.zza(this.zzfnu));
    this.zzfkh.disconnect();
    onConnectionFailed(paramConnectionResult);
  }
  
  @WorkerThread
  public final void zzv(Status paramStatus) {
    zzbp.zza(zzbp.zza(this.zzfnu));
    Iterator<zza> iterator = this.zzfnv.iterator();
    while (iterator.hasNext())
      ((zza)iterator.next()).zzr(paramStatus); 
    this.zzfnv.clear();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */