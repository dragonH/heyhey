package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzq;
import java.util.Iterator;

public class FirebaseMessagingService extends zzb {
  static boolean zzag(Bundle paramBundle) {
    return (paramBundle == null) ? false : "1".equals(paramBundle.getString("google.c.a.e"));
  }
  
  static void zzq(Bundle paramBundle) {
    Iterator<String> iterator = paramBundle.keySet().iterator();
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (str != null && str.startsWith("google.c."))
        iterator.remove(); 
    } 
  }
  
  public void handleIntent(Intent paramIntent) {
    String str1;
    String str2 = paramIntent.getAction();
    String str3 = str2;
    if (str2 == null)
      str3 = ""; 
    if (!str3.equals("com.google.firebase.messaging.NOTIFICATION_DISMISS")) {
      if (!str3.equals("com.google.android.c2dm.intent.RECEIVE")) {
        str1 = String.valueOf(paramIntent.getAction());
        if (str1.length() != 0) {
          str1 = "Unknown intent action: ".concat(str1);
        } else {
          str1 = new String("Unknown intent action: ");
        } 
        Log.d("FirebaseMessaging", str1);
      } else {
        Bundle bundle1;
        Bundle bundle2;
        str2 = str1.getStringExtra("message_type");
        str3 = str2;
        if (str2 == null)
          str3 = "gcm"; 
        byte b = -1;
        switch (str3.hashCode()) {
          case 814800675:
            if (!str3.equals("send_event"))
              break; 
            b = 3;
            break;
          case 814694033:
            if (!str3.equals("send_error"))
              break; 
            b = 2;
            break;
          case 102161:
            if (!str3.equals("gcm"))
              break; 
            b = 1;
            break;
          case -2062414158:
            if (!str3.equals("deleted_messages"))
              break; 
            b = 0;
            break;
        } 
        switch (b) {
          default:
            if (str3.length() != 0) {
              str1 = "Received message with unknown type: ".concat(str3);
            } else {
              str1 = new String("Received message with unknown type: ");
            } 
            Log.w("FirebaseMessaging", str1);
            return;
          case 3:
            onMessageSent(str1.getStringExtra("google.message_id"));
            return;
          case 2:
            str2 = str1.getStringExtra("google.message_id");
            str3 = str2;
            if (str2 == null)
              str3 = str1.getStringExtra("message_id"); 
            onSendError(str3, new SendException(str1.getStringExtra("error")));
            return;
          case 1:
            if (zzag(str1.getExtras()))
              zzd.zzg((Context)this, (Intent)str1); 
            bundle1 = str1.getExtras();
            bundle2 = bundle1;
            if (bundle1 == null)
              bundle2 = new Bundle(); 
            bundle2.remove("android.support.content.wakelockid");
            if (zza.zzad(bundle2))
              if (!zza.zzeq((Context)this).zzs(bundle2)) {
                if (zzag(bundle2))
                  zzd.zzj((Context)this, (Intent)str1); 
              } else {
                return;
              }  
            onMessageReceived(new RemoteMessage(bundle2));
            return;
          case 0:
            break;
        } 
        onDeletedMessages();
        return;
      } 
    } else if (zzag(str1.getExtras())) {
      zzd.zzi((Context)this, (Intent)str1);
    } 
  }
  
  @WorkerThread
  public void onDeletedMessages() {}
  
  @WorkerThread
  public void onMessageReceived(RemoteMessage paramRemoteMessage) {}
  
  @WorkerThread
  public void onMessageSent(String paramString) {}
  
  @WorkerThread
  public void onSendError(String paramString, Exception paramException) {}
  
  protected final Intent zzn(Intent paramIntent) {
    return zzq.zzcge().zzcgf();
  }
  
  public final boolean zzo(Intent paramIntent) {
    if ("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(paramIntent.getAction())) {
      PendingIntent pendingIntent = (PendingIntent)paramIntent.getParcelableExtra("pending_intent");
      if (pendingIntent != null)
        try {
          pendingIntent.send();
        } catch (android.app.PendingIntent.CanceledException canceledException) {
          Log.e("FirebaseMessaging", "Notification pending intent canceled");
        }  
      if (zzag(paramIntent.getExtras()))
        zzd.zzh((Context)this, paramIntent); 
      return true;
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/FirebaseMessagingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */