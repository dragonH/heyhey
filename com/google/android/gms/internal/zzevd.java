package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzevd {
  private static volatile boolean zzonv = false;
  
  private static final Class<?> zzonw = zzctt();
  
  static final zzevd zzonx = new zzevd(true);
  
  private final Map<Object, Object> zzony = new HashMap<Object, Object>();
  
  zzevd() {}
  
  private zzevd(boolean paramBoolean) {}
  
  private static Class<?> zzctt() {
    try {
      return Class.forName("com.google.protobuf.Extension");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static zzevd zzctu() {
    return zzevc.zzcts();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */