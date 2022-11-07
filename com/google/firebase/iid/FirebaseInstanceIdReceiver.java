package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
  public final void onReceive(Context paramContext, Intent paramIntent) {
    String str1 = null;
    paramIntent.setComponent(null);
    paramIntent.setPackage(paramContext.getPackageName());
    if (Build.VERSION.SDK_INT <= 18)
      paramIntent.removeCategory(paramContext.getPackageName()); 
    String str2 = paramIntent.getStringExtra("gcm.rawData64");
    if (str2 != null) {
      paramIntent.putExtra("rawData", Base64.decode(str2, 0));
      paramIntent.removeExtra("gcm.rawData64");
    } 
    str2 = paramIntent.getStringExtra("from");
    if ("google.com/iid".equals(str2) || "gcm.googleapis.com/refresh".equals(str2)) {
      str1 = "com.google.firebase.INSTANCE_ID_EVENT";
    } else if ("com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction())) {
      str1 = "com.google.firebase.MESSAGING_EVENT";
    } else {
      Log.d("FirebaseInstanceId", "Unexpected intent");
    } 
    byte b = -1;
    int i = b;
    if (str1 != null)
      if (FirebaseInstanceIdInternalReceiver.zzel(paramContext)) {
        if (isOrderedBroadcast())
          setResultCode(-1); 
        FirebaseInstanceIdInternalReceiver.zzag(paramContext, str1).zza(paramIntent, goAsync());
        i = b;
      } else {
        i = zzq.zzcge().zza(paramContext, str1, paramIntent);
      }  
    if (isOrderedBroadcast())
      setResultCode(i); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/FirebaseInstanceIdReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */