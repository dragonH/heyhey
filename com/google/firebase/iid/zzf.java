package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzf extends Binder {
  private final zzb zznfa;
  
  zzf(zzb paramzzb) {
    this.zznfa = paramzzb;
  }
  
  public final void zza(zzd paramzzd) {
    if (Binder.getCallingUid() == Process.myUid()) {
      if (Log.isLoggable("EnhancedIntentService", 3))
        Log.d("EnhancedIntentService", "service received new intent via bind strategy"); 
      if (this.zznfa.zzo(paramzzd.intent)) {
        paramzzd.finish();
        return;
      } 
      if (Log.isLoggable("EnhancedIntentService", 3))
        Log.d("EnhancedIntentService", "intent being queued for bg execution"); 
      this.zznfa.zzisa.execute(new zzg(this, paramzzd));
      return;
    } 
    throw new SecurityException("Binding only allowed within app");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */