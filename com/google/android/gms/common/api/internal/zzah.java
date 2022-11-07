package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzah {
  private final Map<zzs<?>, Boolean> zzfla = Collections.synchronizedMap(new WeakHashMap<zzs<?>, Boolean>());
  
  private final Map<TaskCompletionSource<?>, Boolean> zzflb = Collections.synchronizedMap(new WeakHashMap<TaskCompletionSource<?>, Boolean>());
  
  private final void zza(boolean paramBoolean, Status paramStatus) {
    synchronized (this.zzfla) {
      Map<TaskCompletionSource<?>, Boolean> map;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this((Map)this.zzfla);
      synchronized (this.zzflb) {
        null = new HashMap<zzs<?>, Boolean>();
        super((Map)this.zzflb);
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
          if (paramBoolean || ((Boolean)entry.getValue()).booleanValue())
            ((zzs)entry.getKey()).zzu(paramStatus); 
        } 
        for (Map.Entry<zzs<?>, Boolean> entry1 : null.entrySet()) {
          if (paramBoolean || ((Boolean)entry1.getValue()).booleanValue())
            ((TaskCompletionSource)entry1.getKey()).trySetException((Exception)new ApiException(paramStatus)); 
        } 
        return;
      } 
    } 
  }
  
  final void zza(zzs<? extends Result> paramzzs, boolean paramBoolean) {
    this.zzfla.put(paramzzs, Boolean.valueOf(paramBoolean));
    paramzzs.zza(new zzai(this, paramzzs));
  }
  
  final <TResult> void zza(TaskCompletionSource<TResult> paramTaskCompletionSource, boolean paramBoolean) {
    this.zzflb.put(paramTaskCompletionSource, Boolean.valueOf(paramBoolean));
    paramTaskCompletionSource.getTask().addOnCompleteListener(new zzaj(this, paramTaskCompletionSource));
  }
  
  final boolean zzags() {
    return (!this.zzfla.isEmpty() || !this.zzflb.isEmpty());
  }
  
  public final void zzagt() {
    zza(false, zzbp.zzfnk);
  }
  
  public final void zzagu() {
    zza(true, zzdj.zzfpq);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */