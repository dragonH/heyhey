package com.google.android.gms.internal;

final class zzevc {
  private static Class<?> zzonu = zzctr();
  
  private static Class<?> zzctr() {
    try {
      return Class.forName("com.google.protobuf.ExtensionRegistry");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static zzevd zzcts() {
    if (zzonu != null)
      try {
        return zztl("getEmptyRegistry");
      } catch (Exception exception) {} 
    return zzevd.zzonx;
  }
  
  private static final zzevd zztl(String paramString) throws Exception {
    return (zzevd)zzonu.getDeclaredMethod(paramString, new Class[0]).invoke(null, new Object[0]);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */