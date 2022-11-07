package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzd {
  final Intent intent;
  
  private final BroadcastReceiver.PendingResult zznew;
  
  private boolean zznex = false;
  
  private final ScheduledFuture<?> zzney;
  
  zzd(Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult, ScheduledExecutorService paramScheduledExecutorService) {
    this.intent = paramIntent;
    this.zznew = paramPendingResult;
    this.zzney = paramScheduledExecutorService.schedule(new zze(this, paramIntent), 9500L, TimeUnit.MILLISECONDS);
  }
  
  final void finish() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zznex : Z
    //   6: ifne -> 32
    //   9: aload_0
    //   10: getfield zznew : Landroid/content/BroadcastReceiver$PendingResult;
    //   13: invokevirtual finish : ()V
    //   16: aload_0
    //   17: getfield zzney : Ljava/util/concurrent/ScheduledFuture;
    //   20: iconst_0
    //   21: invokeinterface cancel : (Z)Z
    //   26: pop
    //   27: aload_0
    //   28: iconst_1
    //   29: putfield zznex : Z
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	35	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */