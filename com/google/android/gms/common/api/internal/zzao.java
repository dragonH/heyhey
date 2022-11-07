package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzby;
import java.util.Iterator;

public final class zzao implements zzbk {
  private final zzbl zzflh;
  
  private boolean zzfli = false;
  
  public zzao(zzbl paramzzbl) {
    this.zzflh = paramzzbl;
  }
  
  public final void begin() {}
  
  public final void connect() {
    if (this.zzfli) {
      this.zzfli = false;
      this.zzflh.zza(new zzaq(this, this));
    } 
  }
  
  public final boolean disconnect() {
    if (this.zzfli)
      return false; 
    if (this.zzflh.zzfju.zzahj()) {
      this.zzfli = true;
      Iterator<zzdg> iterator = this.zzflh.zzfju.zzfms.iterator();
      while (iterator.hasNext())
        ((zzdg)iterator.next()).zzaio(); 
      return false;
    } 
    this.zzflh.zzg(null);
    return true;
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {
    this.zzflh.zzg(null);
    this.zzflh.zzfng.zzf(paramInt, this.zzfli);
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
  
  final void zzagy() {
    if (this.zzfli) {
      this.zzfli = false;
      this.zzflh.zzfju.zzfmt.release();
      disconnect();
    } 
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T paramT) {
    return (T)zze((zzm<? extends Result, Api.zzb>)paramT);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T paramT) {
    try {
      this.zzflh.zzfju.zzfmt.zzb((zzs<? extends Result>)paramT);
      zzbd zzbd = this.zzflh.zzfju;
      Api.zzc zzc = paramT.zzafe();
      Api.zze zze = zzbd.zzfmn.get(zzc);
      zzbp.zzb(zze, "Appropriate Api was not requested.");
      if (!zze.isConnected() && this.zzflh.zzfnc.containsKey(paramT.zzafe())) {
        Status status = new Status();
        this(17);
        paramT.zzt(status);
      } else {
        Api.zzg zzg;
        Api.zze zze1 = zze;
        if (zze instanceof zzby)
          zzg = zzby.zzako(); 
        paramT.zzb(zzg);
      } 
    } catch (DeadObjectException deadObjectException) {
      this.zzflh.zza(new zzap(this, this));
    } 
    return paramT;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */