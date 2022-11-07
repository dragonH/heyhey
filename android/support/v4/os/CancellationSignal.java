package android.support.v4.os;

public final class CancellationSignal {
  private boolean mCancelInProgress;
  
  private Object mCancellationSignalObj;
  
  private boolean mIsCanceled;
  
  private OnCancelListener mOnCancelListener;
  
  private void waitForCancelFinishedLocked() {
    while (this.mCancelInProgress) {
      try {
        wait();
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  public void cancel() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsCanceled : Z
    //   6: ifeq -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield mIsCanceled : Z
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield mCancelInProgress : Z
    //   22: aload_0
    //   23: getfield mOnCancelListener : Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   26: astore_1
    //   27: aload_0
    //   28: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: ifnull -> 51
    //   38: aload_1
    //   39: invokeinterface onCancel : ()V
    //   44: goto -> 51
    //   47: astore_2
    //   48: goto -> 65
    //   51: aload_2
    //   52: ifnull -> 85
    //   55: aload_2
    //   56: checkcast android/os/CancellationSignal
    //   59: invokevirtual cancel : ()V
    //   62: goto -> 85
    //   65: aload_0
    //   66: monitorenter
    //   67: aload_0
    //   68: iconst_0
    //   69: putfield mCancelInProgress : Z
    //   72: aload_0
    //   73: invokevirtual notifyAll : ()V
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_2
    //   79: athrow
    //   80: astore_2
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    //   85: aload_0
    //   86: monitorenter
    //   87: aload_0
    //   88: iconst_0
    //   89: putfield mCancelInProgress : Z
    //   92: aload_0
    //   93: invokevirtual notifyAll : ()V
    //   96: aload_0
    //   97: monitorexit
    //   98: return
    //   99: astore_2
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_2
    //   103: athrow
    //   104: astore_2
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_2
    //   108: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	104	finally
    //   12	34	104	finally
    //   38	44	47	finally
    //   55	62	47	finally
    //   67	78	80	finally
    //   81	83	80	finally
    //   87	98	99	finally
    //   100	102	99	finally
    //   105	107	104	finally
  }
  
  public Object getCancellationSignalObject() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   6: ifnonnull -> 38
    //   9: new android/os/CancellationSignal
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_1
    //   19: putfield mCancellationSignalObj : Ljava/lang/Object;
    //   22: aload_0
    //   23: getfield mIsCanceled : Z
    //   26: ifeq -> 38
    //   29: aload_1
    //   30: checkcast android/os/CancellationSignal
    //   33: astore_2
    //   34: aload_1
    //   35: invokevirtual cancel : ()V
    //   38: aload_0
    //   39: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: areturn
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   2	38	47	finally
    //   38	45	47	finally
    //   48	50	47	finally
  }
  
  public boolean isCanceled() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsCanceled : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial waitForCancelFinishedLocked : ()V
    //   6: aload_0
    //   7: getfield mOnCancelListener : Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   10: aload_1
    //   11: if_acmpne -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: aload_1
    //   19: putfield mOnCancelListener : Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   22: aload_0
    //   23: getfield mIsCanceled : Z
    //   26: ifeq -> 45
    //   29: aload_1
    //   30: ifnonnull -> 36
    //   33: goto -> 45
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: invokeinterface onCancel : ()V
    //   44: return
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	48	finally
    //   17	29	48	finally
    //   36	38	48	finally
    //   45	47	48	finally
    //   49	51	48	finally
  }
  
  public void throwIfCanceled() {
    if (!isCanceled())
      return; 
    throw new OperationCanceledException();
  }
  
  public static interface OnCancelListener {
    void onCancel();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/os/CancellationSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */