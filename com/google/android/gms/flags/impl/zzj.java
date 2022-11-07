package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;

public final class zzj {
  private static SharedPreferences zzhbi;
  
  public static SharedPreferences zzcy(Context paramContext) throws Exception {
    // Byte code:
    //   0: ldc android/content/SharedPreferences
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/flags/impl/zzj.zzhbi : Landroid/content/SharedPreferences;
    //   6: ifnonnull -> 28
    //   9: new com/google/android/gms/flags/impl/zzk
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/content/Context;)V
    //   18: aload_1
    //   19: invokestatic zza : (Ljava/util/concurrent/Callable;)Ljava/lang/Object;
    //   22: checkcast android/content/SharedPreferences
    //   25: putstatic com/google/android/gms/flags/impl/zzj.zzhbi : Landroid/content/SharedPreferences;
    //   28: getstatic com/google/android/gms/flags/impl/zzj.zzhbi : Landroid/content/SharedPreferences;
    //   31: astore_0
    //   32: ldc android/content/SharedPreferences
    //   34: monitorexit
    //   35: aload_0
    //   36: areturn
    //   37: astore_0
    //   38: ldc android/content/SharedPreferences
    //   40: monitorexit
    //   41: aload_0
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   3	28	37	finally
    //   28	35	37	finally
    //   38	41	37	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/flags/impl/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */