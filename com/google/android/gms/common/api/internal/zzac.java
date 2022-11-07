package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;

public final class zzac<O extends Api.ApiOptions> extends GoogleApi<O> {
  private final Api.zza<? extends zzcps, zzcpt> zzfhm;
  
  private final Api.zze zzfkh;
  
  private final zzw zzfki;
  
  private final zzq zzfkj;
  
  public zzac(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper, @NonNull Api.zze paramzze, @NonNull zzw paramzzw, zzq paramzzq, Api.zza<? extends zzcps, zzcpt> paramzza) {
    super(paramContext, paramApi, paramLooper);
    this.zzfkh = paramzze;
    this.zzfki = paramzzw;
    this.zzfkj = paramzzq;
    this.zzfhm = paramzza;
    this.zzfgv.zza(this);
  }
  
  public final Api.zze zza(Looper paramLooper, zzbr<O> paramzzbr) {
    this.zzfki.zza(paramzzbr);
    return this.zzfkh;
  }
  
  public final zzcw zza(Context paramContext, Handler paramHandler) {
    return new zzcw(paramContext, paramHandler, this.zzfkj, this.zzfhm);
  }
  
  public final Api.zze zzagn() {
    return this.zzfkh;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */