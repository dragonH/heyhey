package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzd extends zzb<Void> {
  private zzcr<Api.zzb, ?> zzfid;
  
  private zzdn<Api.zzb, ?> zzfie;
  
  public zzd(zzcs paramzzcs, TaskCompletionSource<Void> paramTaskCompletionSource) {
    super(3, paramTaskCompletionSource);
    this.zzfid = paramzzcs.zzfid;
    this.zzfie = paramzzcs.zzfie;
  }
  
  public final void zzb(zzbr<?> paramzzbr) throws RemoteException {
    this.zzfid.zzb(paramzzbr.zzagn(), (TaskCompletionSource)this.zzdzc);
    if (this.zzfid.zzaik() != null)
      paramzzbr.zzahw().put(this.zzfid.zzaik(), new zzcs(this.zzfid, this.zzfie)); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */