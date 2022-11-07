package com.google.android.gms.internal;

import android.content.Context;

public final class zzbvk {
  private boolean zzaqf = false;
  
  private zzbvl zzhay = null;
  
  public final void initialize(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzaqf : Z
    //   6: ifeq -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_1
    //   13: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpt : Lcom/google/android/gms/dynamite/DynamiteModule$zzd;
    //   16: ldc 'com.google.android.gms.flags'
    //   18: invokestatic zza : (Landroid/content/Context;Lcom/google/android/gms/dynamite/DynamiteModule$zzd;Ljava/lang/String;)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   21: ldc 'com.google.android.gms.flags.impl.FlagProviderImpl'
    //   23: invokevirtual zzgv : (Ljava/lang/String;)Landroid/os/IBinder;
    //   26: invokestatic asInterface : (Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzbvl;
    //   29: astore_2
    //   30: aload_0
    //   31: aload_2
    //   32: putfield zzhay : Lcom/google/android/gms/internal/zzbvl;
    //   35: aload_2
    //   36: aload_1
    //   37: invokestatic zzw : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   40: invokeinterface init : (Lcom/google/android/gms/dynamic/IObjectWrapper;)V
    //   45: aload_0
    //   46: iconst_1
    //   47: putfield zzaqf : Z
    //   50: goto -> 67
    //   53: astore_1
    //   54: goto -> 58
    //   57: astore_1
    //   58: ldc 'FlagValueProvider'
    //   60: ldc 'Failed to initialize flags module.'
    //   62: aload_1
    //   63: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   66: pop
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	70	finally
    //   12	50	57	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   12	50	53	android/os/RemoteException
    //   12	50	70	finally
    //   58	67	70	finally
    //   67	69	70	finally
    //   71	73	70	finally
  }
  
  public final <T> T zzb(zzbvd<T> paramzzbvd) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzaqf : Z
    //   6: ifne -> 18
    //   9: aload_1
    //   10: invokevirtual zzil : ()Ljava/lang/Object;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: aload_0
    //   22: getfield zzhay : Lcom/google/android/gms/internal/zzbvl;
    //   25: invokevirtual zza : (Lcom/google/android/gms/internal/zzbvl;)Ljava/lang/Object;
    //   28: areturn
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	29	finally
    //   18	20	29	finally
    //   30	32	29	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */