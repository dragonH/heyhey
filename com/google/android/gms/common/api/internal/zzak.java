package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbp;

public class zzak extends zzo {
  private zzbp zzfgv;
  
  private final ArraySet<zzh<?>> zzfle = new ArraySet();
  
  private zzak(zzcg paramzzcg) {
    super(paramzzcg);
    this.zzfoo.zza("ConnectionlessLifecycleHelper", this);
  }
  
  public static void zza(Activity paramActivity, zzbp paramzzbp, zzh<?> paramzzh) {
    LifecycleCallback.zzn(paramActivity);
    zzcg zzcg = LifecycleCallback.zzn(paramActivity);
    zzak zzak2 = zzcg.<zzak>zza("ConnectionlessLifecycleHelper", zzak.class);
    zzak zzak1 = zzak2;
    if (zzak2 == null)
      zzak1 = new zzak(zzcg); 
    zzak1.zzfgv = paramzzbp;
    zzbp.zzb(paramzzh, "ApiKey cannot be null");
    zzak1.zzfle.add(paramzzh);
    paramzzbp.zza(zzak1);
  }
  
  private final void zzagw() {
    if (!this.zzfle.isEmpty())
      this.zzfgv.zza(this); 
  }
  
  public final void onResume() {
    super.onResume();
    zzagw();
  }
  
  public final void onStart() {
    super.onStart();
    zzagw();
  }
  
  public final void onStop() {
    super.onStop();
    this.zzfgv.zzb(this);
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    this.zzfgv.zza(paramConnectionResult, paramInt);
  }
  
  protected final void zzafw() {
    this.zzfgv.zzafw();
  }
  
  final ArraySet<zzh<?>> zzagv() {
    return this.zzfle;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */