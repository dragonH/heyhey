package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

final class zzn extends BroadcastReceiver {
  zzn(zzl paramzzl) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    if (Log.isLoggable("InstanceID/Rpc", 3)) {
      String str = String.valueOf(paramIntent.getExtras());
      StringBuilder stringBuilder = new StringBuilder(str.length() + 44);
      stringBuilder.append("Received GSF callback via dynamic receiver: ");
      stringBuilder.append(str);
      Log.d("InstanceID/Rpc", stringBuilder.toString());
    } 
    this.zznfx.zzi(paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */