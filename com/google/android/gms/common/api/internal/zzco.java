package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzco extends zzo {
  private TaskCompletionSource<Void> zzdzc = new TaskCompletionSource();
  
  private zzco(zzcg paramzzcg) {
    super(paramzzcg);
    this.zzfoo.zza("GmsAvailabilityHelper", this);
  }
  
  public static zzco zzp(Activity paramActivity) {
    zzcg zzcg = LifecycleCallback.zzn(paramActivity);
    zzco zzco1 = zzcg.<zzco>zza("GmsAvailabilityHelper", zzco.class);
    if (zzco1 != null) {
      if (zzco1.zzdzc.getTask().isComplete())
        zzco1.zzdzc = new TaskCompletionSource(); 
      return zzco1;
    } 
    return new zzco(zzcg);
  }
  
  public final Task<Void> getTask() {
    return this.zzdzc.getTask();
  }
  
  public final void onDestroy() {
    super.onDestroy();
    this.zzdzc.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    this.zzdzc.setException((Exception)zzb.zzx(new Status(paramConnectionResult.getErrorCode(), paramConnectionResult.getErrorMessage(), paramConnectionResult.getResolution())));
  }
  
  protected final void zzafw() {
    int i = this.zzfhl.isGooglePlayServicesAvailable((Context)this.zzfoo.zzaij());
    if (i == 0) {
      this.zzdzc.setResult(null);
      return;
    } 
    if (!this.zzdzc.getTask().isComplete())
      zzb(new ConnectionResult(i, null), 0); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzco.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */