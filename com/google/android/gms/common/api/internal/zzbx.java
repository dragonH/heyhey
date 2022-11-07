package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;

public final class zzbx<O extends Api.ApiOptions> extends zzan {
  private final GoogleApi<O> zzfoh;
  
  public zzbx(GoogleApi<O> paramGoogleApi) {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    this.zzfoh = paramGoogleApi;
  }
  
  public final Context getContext() {
    return this.zzfoh.getApplicationContext();
  }
  
  public final Looper getLooper() {
    return this.zzfoh.getLooper();
  }
  
  public final void zza(zzdg paramzzdg) {}
  
  public final void zzb(zzdg paramzzdg) {}
  
  public final <A extends Api.zzb, R extends com.google.android.gms.common.api.Result, T extends zzm<R, A>> T zzd(@NonNull T paramT) {
    return (T)this.zzfoh.zza((zzm)paramT);
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends com.google.android.gms.common.api.Result, A>> T zze(@NonNull T paramT) {
    return (T)this.zzfoh.zzb((zzm)paramT);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */