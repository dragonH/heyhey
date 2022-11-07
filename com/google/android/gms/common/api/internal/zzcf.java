package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzbp;

public final class zzcf {
  private final Object zzfon;
  
  public zzcf(Activity paramActivity) {
    zzbp.zzb(paramActivity, "Activity must not be null");
    this.zzfon = paramActivity;
  }
  
  public final boolean isAndroid() {
    return this.zzfon instanceof Activity;
  }
  
  public final boolean zzaig() {
    return this.zzfon instanceof FragmentActivity;
  }
  
  public final Activity zzaih() {
    return (Activity)this.zzfon;
  }
  
  public final FragmentActivity zzaii() {
    return (FragmentActivity)this.zzfon;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */