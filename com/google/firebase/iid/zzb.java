package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
  private final Object mLock = new Object();
  
  @VisibleForTesting
  final ExecutorService zzisa = Executors.newSingleThreadExecutor();
  
  private Binder zzner;
  
  private int zznes;
  
  private int zznet = 0;
  
  private final void zzm(Intent paramIntent) {
    if (paramIntent != null)
      WakefulBroadcastReceiver.completeWakefulIntent(paramIntent); 
    synchronized (this.mLock) {
      int i = this.zznet - 1;
      this.zznet = i;
      if (i == 0)
        stopSelfResult(this.zznes); 
      return;
    } 
  }
  
  public abstract void handleIntent(Intent paramIntent);
  
  public final IBinder onBind(Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: ifeq -> 19
    //   11: ldc 'EnhancedIntentService'
    //   13: ldc 'Service received bind request'
    //   15: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_0
    //   20: getfield zzner : Landroid/os/Binder;
    //   23: ifnonnull -> 40
    //   26: new com/google/firebase/iid/zzf
    //   29: astore_1
    //   30: aload_1
    //   31: aload_0
    //   32: invokespecial <init> : (Lcom/google/firebase/iid/zzb;)V
    //   35: aload_0
    //   36: aload_1
    //   37: putfield zzner : Landroid/os/Binder;
    //   40: aload_0
    //   41: getfield zzner : Landroid/os/Binder;
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	49	finally
    //   19	40	49	finally
    //   40	45	49	finally
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    synchronized (this.mLock) {
      this.zznes = paramInt2;
      this.zznet++;
      null = zzn(paramIntent);
      if (null == null) {
        zzm(paramIntent);
        return 2;
      } 
      if (zzo((Intent)null)) {
        zzm(paramIntent);
        return 2;
      } 
      this.zzisa.execute(new zzc(this, (Intent)null, paramIntent));
      return 3;
    } 
  }
  
  protected Intent zzn(Intent paramIntent) {
    return paramIntent;
  }
  
  public boolean zzo(Intent paramIntent) {
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */