package com.google.android.gms.common.internal;

public abstract class zzi<TListener> {
  private TListener mListener;
  
  private boolean zzftm;
  
  public zzi(zzd paramzzd, TListener paramTListener) {
    this.mListener = paramTListener;
    this.zzftm = false;
  }
  
  public final void removeListener() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield mListener : Ljava/lang/Object;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	10	finally
    //   11	13	10	finally
  }
  
  public final void unregister() {
    removeListener();
    synchronized (zzd.zzf(this.zzftl)) {
      zzd.zzf(this.zzftl).remove(this);
      return;
    } 
  }
  
  public final void zzajo() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mListener : Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_0
    //   8: getfield zzftm : Z
    //   11: ifeq -> 72
    //   14: aload_0
    //   15: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   18: astore_2
    //   19: aload_2
    //   20: invokevirtual length : ()I
    //   23: istore_3
    //   24: new java/lang/StringBuilder
    //   27: astore #4
    //   29: aload #4
    //   31: iload_3
    //   32: bipush #47
    //   34: iadd
    //   35: invokespecial <init> : (I)V
    //   38: aload #4
    //   40: ldc 'Callback proxy '
    //   42: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload #4
    //   48: aload_2
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload #4
    //   55: ldc ' being reused. This is not safe.'
    //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: ldc 'GmsClient'
    //   63: aload #4
    //   65: invokevirtual toString : ()Ljava/lang/String;
    //   68: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   71: pop
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: ifnull -> 89
    //   78: aload_0
    //   79: aload_1
    //   80: invokevirtual zzs : (Ljava/lang/Object;)V
    //   83: goto -> 89
    //   86: astore_1
    //   87: aload_1
    //   88: athrow
    //   89: aload_0
    //   90: monitorenter
    //   91: aload_0
    //   92: iconst_1
    //   93: putfield zzftm : Z
    //   96: aload_0
    //   97: monitorexit
    //   98: aload_0
    //   99: invokevirtual unregister : ()V
    //   102: return
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: athrow
    //   108: astore_1
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_1
    //   112: athrow
    // Exception table:
    //   from	to	target	type
    //   2	72	108	finally
    //   72	74	108	finally
    //   78	83	86	java/lang/RuntimeException
    //   91	98	103	finally
    //   104	106	103	finally
    //   109	111	108	finally
  }
  
  protected abstract void zzs(TListener paramTListener);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */