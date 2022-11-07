package com.google.android.gms.internal;

import android.content.Context;

public final class zzbeb {
  private static Context zzfzq;
  
  private static Boolean zzfzr;
  
  public static boolean zzcp(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/zzbeb
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   7: astore_1
    //   8: getstatic com/google/android/gms/internal/zzbeb.zzfzq : Landroid/content/Context;
    //   11: astore_2
    //   12: aload_2
    //   13: ifnull -> 41
    //   16: getstatic com/google/android/gms/internal/zzbeb.zzfzr : Ljava/lang/Boolean;
    //   19: astore_3
    //   20: aload_3
    //   21: ifnull -> 41
    //   24: aload_2
    //   25: aload_1
    //   26: if_acmpne -> 41
    //   29: aload_3
    //   30: invokevirtual booleanValue : ()Z
    //   33: istore #4
    //   35: ldc com/google/android/gms/internal/zzbeb
    //   37: monitorexit
    //   38: iload #4
    //   40: ireturn
    //   41: aconst_null
    //   42: putstatic com/google/android/gms/internal/zzbeb.zzfzr : Ljava/lang/Boolean;
    //   45: invokestatic isAtLeastO : ()Z
    //   48: ifeq -> 69
    //   51: aload_1
    //   52: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   55: invokevirtual isInstantApp : ()Z
    //   58: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   61: astore_0
    //   62: aload_0
    //   63: putstatic com/google/android/gms/internal/zzbeb.zzfzr : Ljava/lang/Boolean;
    //   66: goto -> 96
    //   69: aload_0
    //   70: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   73: ldc 'com.google.android.instantapps.supervisor.InstantAppsRuntime'
    //   75: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   78: pop
    //   79: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   82: putstatic com/google/android/gms/internal/zzbeb.zzfzr : Ljava/lang/Boolean;
    //   85: goto -> 96
    //   88: astore_0
    //   89: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   92: astore_0
    //   93: goto -> 62
    //   96: aload_1
    //   97: putstatic com/google/android/gms/internal/zzbeb.zzfzq : Landroid/content/Context;
    //   100: getstatic com/google/android/gms/internal/zzbeb.zzfzr : Ljava/lang/Boolean;
    //   103: invokevirtual booleanValue : ()Z
    //   106: istore #4
    //   108: ldc com/google/android/gms/internal/zzbeb
    //   110: monitorexit
    //   111: iload #4
    //   113: ireturn
    //   114: astore_0
    //   115: ldc com/google/android/gms/internal/zzbeb
    //   117: monitorexit
    //   118: goto -> 123
    //   121: aload_0
    //   122: athrow
    //   123: goto -> 121
    // Exception table:
    //   from	to	target	type
    //   3	12	114	finally
    //   16	20	114	finally
    //   29	35	114	finally
    //   41	62	114	finally
    //   62	66	114	finally
    //   69	85	88	java/lang/ClassNotFoundException
    //   69	85	114	finally
    //   89	93	114	finally
    //   96	108	114	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */