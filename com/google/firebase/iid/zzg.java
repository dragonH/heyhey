package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
  zzg(zzf paramzzf, zzd paramzzd) {}
  
  public final void run() {
    if (Log.isLoggable("EnhancedIntentService", 3))
      Log.d("EnhancedIntentService", "bg processing of the intent starting now"); 
    zzf.zza(this.zznfc).handleIntent(this.zznfb.intent);
    this.zznfb.finish();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */