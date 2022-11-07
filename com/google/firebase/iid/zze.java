package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final class zze implements Runnable {
  zze(zzd paramzzd) {}
  
  public final void run() {
    String str = intent.getAction();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 61);
    stringBuilder.append("Service took too long to process intent: ");
    stringBuilder.append(str);
    stringBuilder.append(" App may get closed.");
    Log.w("EnhancedIntentService", stringBuilder.toString());
    this.zznez.finish();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */