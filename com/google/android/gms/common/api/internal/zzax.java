package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zzax extends zzbb {
  private final ArrayList<Api.zze> zzfmd;
  
  public zzax(zzar paramzzar, ArrayList<Api.zze> paramArrayList) {
    super(paramzzar, null);
    this.zzfmd = paramArrayList;
  }
  
  @WorkerThread
  public final void zzagz() {
    (zzar.zzd(this.zzflx)).zzfju.zzfmo = zzar.zzg(this.zzflx);
    ArrayList<Api.zze> arrayList = this.zzfmd;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Api.zze zze = (Api.zze)arrayList.get(b);
      b++;
      ((Api.zze)zze).zza(zzar.zzh(this.zzflx), (zzar.zzd(this.zzflx)).zzfju.zzfmo);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */