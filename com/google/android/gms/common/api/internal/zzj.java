package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

public final class zzj {
  private final ArrayMap<zzh<?>, ConnectionResult> zzfgj = new ArrayMap();
  
  private final TaskCompletionSource<Void> zzfip = new TaskCompletionSource();
  
  private int zzfiq;
  
  private boolean zzfir = false;
  
  public zzj(Iterable<? extends GoogleApi<?>> paramIterable) {
    for (GoogleApi<?> googleApi : paramIterable)
      this.zzfgj.put(googleApi.zzafk(), null); 
    this.zzfiq = this.zzfgj.keySet().size();
  }
  
  public final Task<Void> getTask() {
    return this.zzfip.getTask();
  }
  
  public final void zza(zzh<?> paramzzh, ConnectionResult paramConnectionResult) {
    this.zzfgj.put(paramzzh, paramConnectionResult);
    this.zzfiq--;
    if (!paramConnectionResult.isSuccess())
      this.zzfir = true; 
    if (this.zzfiq == 0) {
      if (this.zzfir) {
        AvailabilityException availabilityException = new AvailabilityException(this.zzfgj);
        this.zzfip.setException((Exception)availabilityException);
        return;
      } 
      this.zzfip.setResult(null);
    } 
  }
  
  public final Set<zzh<?>> zzafx() {
    return this.zzfgj.keySet();
  }
  
  public final void zzafy() {
    this.zzfip.setResult(null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */