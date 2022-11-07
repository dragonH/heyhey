package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbp;

public final class zzcam {
  public static Looper zzatk() {
    boolean bool;
    if (Looper.myLooper() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "Can't create handler inside thread that has not called Looper.prepare()");
    return Looper.myLooper();
  }
  
  public static Looper zzb(@Nullable Looper paramLooper) {
    return (paramLooper != null) ? paramLooper : zzatk();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */