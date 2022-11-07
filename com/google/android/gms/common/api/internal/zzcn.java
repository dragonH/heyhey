package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzcn {
  private final Set<zzcj<?>> zzeuk = Collections.newSetFromMap(new WeakHashMap<zzcj<?>, Boolean>());
  
  public static <L> zzcl<L> zza(@NonNull L paramL, @NonNull String paramString) {
    zzbp.zzb(paramL, "Listener must not be null");
    zzbp.zzb(paramString, "Listener type must not be null");
    zzbp.zzh(paramString, "Listener type must not be empty");
    return new zzcl<L>(paramL, paramString);
  }
  
  public static <L> zzcj<L> zzb(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString) {
    zzbp.zzb(paramL, "Listener must not be null");
    zzbp.zzb(paramLooper, "Looper must not be null");
    zzbp.zzb(paramString, "Listener type must not be null");
    return new zzcj<L>(paramLooper, paramL, paramString);
  }
  
  public final void release() {
    Iterator<zzcj<?>> iterator = this.zzeuk.iterator();
    while (iterator.hasNext())
      ((zzcj)iterator.next()).clear(); 
    this.zzeuk.clear();
  }
  
  public final <L> zzcj<L> zza(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString) {
    zzcj<L> zzcj = zzb(paramL, paramLooper, paramString);
    this.zzeuk.add(zzcj);
    return zzcj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */