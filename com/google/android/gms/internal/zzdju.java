package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

final class zzdju extends zzdjr {
  private final zzdjs zzlic = new zzdjs();
  
  public final void zza(Throwable paramThrowable, PrintStream paramPrintStream) {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokevirtual printStackTrace : (Ljava/io/PrintStream;)V
    //   5: aload_0
    //   6: getfield zzlic : Lcom/google/android/gms/internal/zzdjs;
    //   9: aload_1
    //   10: iconst_0
    //   11: invokevirtual zza : (Ljava/lang/Throwable;Z)Ljava/util/List;
    //   14: astore_1
    //   15: aload_1
    //   16: ifnonnull -> 20
    //   19: return
    //   20: aload_1
    //   21: monitorenter
    //   22: aload_1
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore_3
    //   29: aload_3
    //   30: invokeinterface hasNext : ()Z
    //   35: ifeq -> 64
    //   38: aload_3
    //   39: invokeinterface next : ()Ljava/lang/Object;
    //   44: checkcast java/lang/Throwable
    //   47: astore #4
    //   49: aload_2
    //   50: ldc 'Suppressed: '
    //   52: invokevirtual print : (Ljava/lang/String;)V
    //   55: aload #4
    //   57: aload_2
    //   58: invokevirtual printStackTrace : (Ljava/io/PrintStream;)V
    //   61: goto -> 29
    //   64: aload_1
    //   65: monitorexit
    //   66: return
    //   67: astore_2
    //   68: aload_1
    //   69: monitorexit
    //   70: goto -> 75
    //   73: aload_2
    //   74: athrow
    //   75: goto -> 73
    // Exception table:
    //   from	to	target	type
    //   22	29	67	finally
    //   29	61	67	finally
    //   64	66	67	finally
    //   68	70	67	finally
  }
  
  public final void zza(Throwable paramThrowable, PrintWriter paramPrintWriter) {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokevirtual printStackTrace : (Ljava/io/PrintWriter;)V
    //   5: aload_0
    //   6: getfield zzlic : Lcom/google/android/gms/internal/zzdjs;
    //   9: aload_1
    //   10: iconst_0
    //   11: invokevirtual zza : (Ljava/lang/Throwable;Z)Ljava/util/List;
    //   14: astore_1
    //   15: aload_1
    //   16: ifnonnull -> 20
    //   19: return
    //   20: aload_1
    //   21: monitorenter
    //   22: aload_1
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore_3
    //   29: aload_3
    //   30: invokeinterface hasNext : ()Z
    //   35: ifeq -> 64
    //   38: aload_3
    //   39: invokeinterface next : ()Ljava/lang/Object;
    //   44: checkcast java/lang/Throwable
    //   47: astore #4
    //   49: aload_2
    //   50: ldc 'Suppressed: '
    //   52: invokevirtual print : (Ljava/lang/String;)V
    //   55: aload #4
    //   57: aload_2
    //   58: invokevirtual printStackTrace : (Ljava/io/PrintWriter;)V
    //   61: goto -> 29
    //   64: aload_1
    //   65: monitorexit
    //   66: return
    //   67: astore_2
    //   68: aload_1
    //   69: monitorexit
    //   70: goto -> 75
    //   73: aload_2
    //   74: athrow
    //   75: goto -> 73
    // Exception table:
    //   from	to	target	type
    //   22	29	67	finally
    //   29	61	67	finally
    //   64	66	67	finally
    //   68	70	67	finally
  }
  
  public final void zzd(Throwable paramThrowable) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual printStackTrace : ()V
    //   4: aload_0
    //   5: getfield zzlic : Lcom/google/android/gms/internal/zzdjs;
    //   8: aload_1
    //   9: iconst_0
    //   10: invokevirtual zza : (Ljava/lang/Throwable;Z)Ljava/util/List;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnonnull -> 19
    //   18: return
    //   19: aload_1
    //   20: monitorenter
    //   21: aload_1
    //   22: invokeinterface iterator : ()Ljava/util/Iterator;
    //   27: astore_2
    //   28: aload_2
    //   29: invokeinterface hasNext : ()Z
    //   34: ifeq -> 62
    //   37: aload_2
    //   38: invokeinterface next : ()Ljava/lang/Object;
    //   43: checkcast java/lang/Throwable
    //   46: astore_3
    //   47: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   50: ldc 'Suppressed: '
    //   52: invokevirtual print : (Ljava/lang/String;)V
    //   55: aload_3
    //   56: invokevirtual printStackTrace : ()V
    //   59: goto -> 28
    //   62: aload_1
    //   63: monitorexit
    //   64: return
    //   65: astore_2
    //   66: aload_1
    //   67: monitorexit
    //   68: goto -> 73
    //   71: aload_2
    //   72: athrow
    //   73: goto -> 71
    // Exception table:
    //   from	to	target	type
    //   21	28	65	finally
    //   28	59	65	finally
    //   62	64	65	finally
    //   66	68	65	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdju.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */