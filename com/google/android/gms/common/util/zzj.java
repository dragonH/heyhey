package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

public final class zzj {
  private static IntentFilter zzfyx = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  
  private static long zzfyy;
  
  private static float zzfyz = Float.NaN;
  
  @TargetApi(20)
  public static int zzcm(Context paramContext) {
    int i;
    boolean bool1;
    if (paramContext == null || paramContext.getApplicationContext() == null)
      return -1; 
    Intent intent = paramContext.getApplicationContext().registerReceiver(null, zzfyx);
    boolean bool = false;
    if (intent == null) {
      i = 0;
    } else {
      i = intent.getIntExtra("plugged", 0);
    } 
    if ((i & 0x7) != 0)
      bool = true; 
    PowerManager powerManager = (PowerManager)paramContext.getSystemService("power");
    if (powerManager == null)
      return -1; 
    if (zzq.zzali()) {
      bool1 = powerManager.isInteractive();
    } else {
      bool1 = powerManager.isScreenOn();
    } 
    return bool1 << 1 | bool;
  }
  
  public static float zzcn(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/zzj
    //   2: monitorenter
    //   3: invokestatic elapsedRealtime : ()J
    //   6: getstatic com/google/android/gms/common/util/zzj.zzfyy : J
    //   9: lsub
    //   10: ldc2_w 60000
    //   13: lcmp
    //   14: ifge -> 35
    //   17: getstatic com/google/android/gms/common/util/zzj.zzfyz : F
    //   20: invokestatic isNaN : (F)Z
    //   23: ifne -> 35
    //   26: getstatic com/google/android/gms/common/util/zzj.zzfyz : F
    //   29: fstore_1
    //   30: ldc com/google/android/gms/common/util/zzj
    //   32: monitorexit
    //   33: fload_1
    //   34: freturn
    //   35: aload_0
    //   36: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   39: aconst_null
    //   40: getstatic com/google/android/gms/common/util/zzj.zzfyx : Landroid/content/IntentFilter;
    //   43: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   46: astore_0
    //   47: aload_0
    //   48: ifnull -> 75
    //   51: aload_0
    //   52: ldc 'level'
    //   54: iconst_m1
    //   55: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   58: istore_2
    //   59: aload_0
    //   60: ldc 'scale'
    //   62: iconst_m1
    //   63: invokevirtual getIntExtra : (Ljava/lang/String;I)I
    //   66: istore_3
    //   67: iload_2
    //   68: i2f
    //   69: iload_3
    //   70: i2f
    //   71: fdiv
    //   72: putstatic com/google/android/gms/common/util/zzj.zzfyz : F
    //   75: invokestatic elapsedRealtime : ()J
    //   78: putstatic com/google/android/gms/common/util/zzj.zzfyy : J
    //   81: getstatic com/google/android/gms/common/util/zzj.zzfyz : F
    //   84: fstore_1
    //   85: ldc com/google/android/gms/common/util/zzj
    //   87: monitorexit
    //   88: fload_1
    //   89: freturn
    //   90: astore_0
    //   91: ldc com/google/android/gms/common/util/zzj
    //   93: monitorexit
    //   94: aload_0
    //   95: athrow
    // Exception table:
    //   from	to	target	type
    //   3	30	90	finally
    //   35	47	90	finally
    //   51	75	90	finally
    //   75	85	90	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */