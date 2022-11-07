package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zzd {
  private static zzh zzfyr = new zzh();
  
  public static zzd zzalc() {
    return zzfyr;
  }
  
  public final long currentTimeMillis() {
    return System.currentTimeMillis();
  }
  
  public final long elapsedRealtime() {
    return SystemClock.elapsedRealtime();
  }
  
  public final long nanoTime() {
    return System.nanoTime();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */