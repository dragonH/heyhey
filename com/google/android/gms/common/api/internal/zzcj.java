package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;

public final class zzcj<L> {
  private volatile L mListener;
  
  private final zzck zzfou;
  
  private final zzcl<L> zzfov;
  
  zzcj(@NonNull Looper paramLooper, @NonNull L paramL, @NonNull String paramString) {
    this.zzfou = new zzck(this, paramLooper);
    this.mListener = (L)zzbp.zzb(paramL, "Listener must not be null");
    this.zzfov = new zzcl<L>(paramL, zzbp.zzgg(paramString));
  }
  
  public final void clear() {
    this.mListener = null;
  }
  
  public final void zza(zzcm<? super L> paramzzcm) {
    zzbp.zzb(paramzzcm, "Notifier must not be null");
    Message message = this.zzfou.obtainMessage(1, paramzzcm);
    this.zzfou.sendMessage(message);
  }
  
  @NonNull
  public final zzcl<L> zzaik() {
    return this.zzfov;
  }
  
  final void zzb(zzcm<? super L> paramzzcm) {
    L l = this.mListener;
    if (l == null) {
      paramzzcm.zzagx();
      return;
    } 
    try {
      paramzzcm.zzq(l);
      return;
    } catch (RuntimeException runtimeException) {
      paramzzcm.zzagx();
      throw runtimeException;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */