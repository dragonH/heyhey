package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzdi extends Handler {
  public zzdi(zzdg paramzzdg, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    // Byte code:
    //   0: aload_1
    //   1: getfield what : I
    //   4: istore_2
    //   5: iload_2
    //   6: ifeq -> 100
    //   9: iload_2
    //   10: iconst_1
    //   11: if_icmpeq -> 48
    //   14: new java/lang/StringBuilder
    //   17: dup
    //   18: bipush #70
    //   20: invokespecial <init> : (I)V
    //   23: astore_1
    //   24: aload_1
    //   25: ldc 'TransformationResultHandler received unknown message type: '
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_1
    //   32: iload_2
    //   33: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: ldc 'TransformedResultImpl'
    //   39: aload_1
    //   40: invokevirtual toString : ()Ljava/lang/String;
    //   43: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   46: pop
    //   47: return
    //   48: aload_1
    //   49: getfield obj : Ljava/lang/Object;
    //   52: checkcast java/lang/RuntimeException
    //   55: astore_3
    //   56: aload_3
    //   57: invokevirtual getMessage : ()Ljava/lang/String;
    //   60: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   63: astore_1
    //   64: aload_1
    //   65: invokevirtual length : ()I
    //   68: ifeq -> 81
    //   71: ldc 'Runtime exception on the transformation worker thread: '
    //   73: aload_1
    //   74: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   77: astore_1
    //   78: goto -> 91
    //   81: new java/lang/String
    //   84: dup
    //   85: ldc 'Runtime exception on the transformation worker thread: '
    //   87: invokespecial <init> : (Ljava/lang/String;)V
    //   90: astore_1
    //   91: ldc 'TransformedResultImpl'
    //   93: aload_1
    //   94: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   97: pop
    //   98: aload_3
    //   99: athrow
    //   100: aload_1
    //   101: getfield obj : Ljava/lang/Object;
    //   104: checkcast com/google/android/gms/common/api/PendingResult
    //   107: astore_3
    //   108: aload_0
    //   109: getfield zzfpp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   112: invokestatic zzf : (Lcom/google/android/gms/common/api/internal/zzdg;)Ljava/lang/Object;
    //   115: astore_1
    //   116: aload_1
    //   117: monitorenter
    //   118: aload_3
    //   119: ifnonnull -> 152
    //   122: aload_0
    //   123: getfield zzfpp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   126: invokestatic zzg : (Lcom/google/android/gms/common/api/internal/zzdg;)Lcom/google/android/gms/common/api/internal/zzdg;
    //   129: astore #4
    //   131: new com/google/android/gms/common/api/Status
    //   134: astore_3
    //   135: aload_3
    //   136: bipush #13
    //   138: ldc 'Transform returned null'
    //   140: invokespecial <init> : (ILjava/lang/String;)V
    //   143: aload #4
    //   145: aload_3
    //   146: invokestatic zza : (Lcom/google/android/gms/common/api/internal/zzdg;Lcom/google/android/gms/common/api/Status;)V
    //   149: goto -> 190
    //   152: aload_3
    //   153: instanceof com/google/android/gms/common/api/internal/zzcu
    //   156: ifeq -> 179
    //   159: aload_0
    //   160: getfield zzfpp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   163: invokestatic zzg : (Lcom/google/android/gms/common/api/internal/zzdg;)Lcom/google/android/gms/common/api/internal/zzdg;
    //   166: aload_3
    //   167: checkcast com/google/android/gms/common/api/internal/zzcu
    //   170: invokevirtual getStatus : ()Lcom/google/android/gms/common/api/Status;
    //   173: invokestatic zza : (Lcom/google/android/gms/common/api/internal/zzdg;Lcom/google/android/gms/common/api/Status;)V
    //   176: goto -> 190
    //   179: aload_0
    //   180: getfield zzfpp : Lcom/google/android/gms/common/api/internal/zzdg;
    //   183: invokestatic zzg : (Lcom/google/android/gms/common/api/internal/zzdg;)Lcom/google/android/gms/common/api/internal/zzdg;
    //   186: aload_3
    //   187: invokevirtual zza : (Lcom/google/android/gms/common/api/PendingResult;)V
    //   190: aload_1
    //   191: monitorexit
    //   192: return
    //   193: astore_3
    //   194: aload_1
    //   195: monitorexit
    //   196: aload_3
    //   197: athrow
    // Exception table:
    //   from	to	target	type
    //   122	149	193	finally
    //   152	176	193	finally
    //   179	190	193	finally
    //   190	192	193	finally
    //   194	196	193	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */