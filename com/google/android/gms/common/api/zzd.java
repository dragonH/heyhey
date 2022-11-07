package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbp;

public final class zzd {
  private Looper zzakg;
  
  private zzcz zzfgu;
  
  public final zzd zza(Looper paramLooper) {
    zzbp.zzb(paramLooper, "Looper must not be null.");
    this.zzakg = paramLooper;
    return this;
  }
  
  public final zzd zza(zzcz paramzzcz) {
    zzbp.zzb(paramzzcz, "StatusExceptionMapper must not be null.");
    this.zzfgu = paramzzcz;
    return this;
  }
  
  public final GoogleApi.zza zzafn() {
    if (this.zzfgu == null)
      this.zzfgu = (zzcz)new zzg(); 
    if (this.zzakg == null) {
      Looper looper;
      if (Looper.myLooper() != null) {
        looper = Looper.myLooper();
      } else {
        looper = Looper.getMainLooper();
      } 
      this.zzakg = looper;
    } 
    return new GoogleApi.zza(this.zzfgu, null, this.zzakg, null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */