package com.google.android.gms.common.util;

import android.os.Build;

public final class zzq {
  public static boolean isAtLeastN() {
    return (Build.VERSION.SDK_INT >= 24);
  }
  
  public static boolean isAtLeastO() {
    if (Build.VERSION.SDK_INT < 26) {
      String str = Build.VERSION.CODENAME;
      if (!"O".equals(str) && !str.startsWith("OMR") && !str.startsWith("ODR"))
        return false; 
    } 
    return true;
  }
  
  public static boolean zzald() {
    return true;
  }
  
  public static boolean zzale() {
    return true;
  }
  
  public static boolean zzalf() {
    return (Build.VERSION.SDK_INT >= 17);
  }
  
  public static boolean zzalg() {
    return (Build.VERSION.SDK_INT >= 18);
  }
  
  public static boolean zzalh() {
    return (Build.VERSION.SDK_INT >= 19);
  }
  
  public static boolean zzali() {
    return (Build.VERSION.SDK_INT >= 20);
  }
  
  public static boolean zzalj() {
    return (Build.VERSION.SDK_INT >= 21);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */