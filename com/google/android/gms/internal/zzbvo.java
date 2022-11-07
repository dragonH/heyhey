package com.google.android.gms.internal;

public final class zzbvo {
  private static zzbvo zzhaz;
  
  private final zzbvj zzhba = new zzbvj();
  
  private final zzbvk zzhbb = new zzbvk();
  
  static {
    // Byte code:
    //   0: new com/google/android/gms/internal/zzbvo
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_0
    //   8: ldc com/google/android/gms/internal/zzbvo
    //   10: monitorenter
    //   11: aload_0
    //   12: putstatic com/google/android/gms/internal/zzbvo.zzhaz : Lcom/google/android/gms/internal/zzbvo;
    //   15: ldc com/google/android/gms/internal/zzbvo
    //   17: monitorexit
    //   18: return
    //   19: astore_0
    //   20: ldc com/google/android/gms/internal/zzbvo
    //   22: monitorexit
    //   23: aload_0
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   11	18	19	finally
    //   20	23	19	finally
  }
  
  private static zzbvo zzape() {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzbvo
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/internal/zzbvo.zzhaz : Lcom/google/android/gms/internal/zzbvo;
    //   6: astore_0
    //   7: ldc com/google/android/gms/internal/zzbvo
    //   9: monitorexit
    //   10: aload_0
    //   11: areturn
    //   12: astore_0
    //   13: ldc com/google/android/gms/internal/zzbvo
    //   15: monitorexit
    //   16: aload_0
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	12	finally
    //   13	16	12	finally
  }
  
  public static zzbvj zzapf() {
    return (zzape()).zzhba;
  }
  
  public static zzbvk zzapg() {
    return (zzape()).zzhbb;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */