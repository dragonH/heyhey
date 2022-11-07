package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzl;
import java.util.regex.Pattern;

public class FirebaseMessaging {
  public static final String INSTANCE_ID_SCOPE = "FCM";
  
  private static final Pattern zzngi = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
  
  private static FirebaseMessaging zzngj;
  
  private final FirebaseInstanceId zznfi;
  
  private FirebaseMessaging(FirebaseInstanceId paramFirebaseInstanceId) {
    this.zznfi = paramFirebaseInstanceId;
  }
  
  public static FirebaseMessaging getInstance() {
    // Byte code:
    //   0: ldc com/google/firebase/messaging/FirebaseMessaging
    //   2: monitorenter
    //   3: getstatic com/google/firebase/messaging/FirebaseMessaging.zzngj : Lcom/google/firebase/messaging/FirebaseMessaging;
    //   6: ifnonnull -> 24
    //   9: new com/google/firebase/messaging/FirebaseMessaging
    //   12: astore_0
    //   13: aload_0
    //   14: invokestatic getInstance : ()Lcom/google/firebase/iid/FirebaseInstanceId;
    //   17: invokespecial <init> : (Lcom/google/firebase/iid/FirebaseInstanceId;)V
    //   20: aload_0
    //   21: putstatic com/google/firebase/messaging/FirebaseMessaging.zzngj : Lcom/google/firebase/messaging/FirebaseMessaging;
    //   24: getstatic com/google/firebase/messaging/FirebaseMessaging.zzngj : Lcom/google/firebase/messaging/FirebaseMessaging;
    //   27: astore_0
    //   28: ldc com/google/firebase/messaging/FirebaseMessaging
    //   30: monitorexit
    //   31: aload_0
    //   32: areturn
    //   33: astore_0
    //   34: ldc com/google/firebase/messaging/FirebaseMessaging
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   3	24	33	finally
    //   24	28	33	finally
  }
  
  public void send(RemoteMessage paramRemoteMessage) {
    if (!TextUtils.isEmpty(paramRemoteMessage.getTo())) {
      Context context = FirebaseApp.getInstance().getApplicationContext();
      String str = zzl.zzdf(context);
      if (str == null) {
        Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
        return;
      } 
      Intent intent = new Intent("com.google.android.gcm.intent.SEND");
      zzl.zzd(context, intent);
      intent.setPackage(str);
      intent.putExtras(paramRemoteMessage.mBundle);
      context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
      return;
    } 
    throw new IllegalArgumentException("Missing 'to'");
  }
  
  public void subscribeToTopic(String paramString) {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
      if (paramString.startsWith("/topics/")) {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
        str = paramString.substring(8);
      } 
    } 
    if (str != null && zzngi.matcher(str).matches()) {
      FirebaseInstanceId firebaseInstanceId = this.zznfi;
      if (str.length() != 0) {
        paramString = "S!".concat(str);
      } else {
        paramString = new String("S!");
      } 
      firebaseInstanceId.zzqw(paramString);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 55 + "[a-zA-Z0-9-_.~%]{1,900}".length());
    stringBuilder.append("Invalid topic name: ");
    stringBuilder.append(str);
    stringBuilder.append(" does not match the allowed format ");
    stringBuilder.append("[a-zA-Z0-9-_.~%]{1,900}");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void unsubscribeFromTopic(String paramString) {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
      if (paramString.startsWith("/topics/")) {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
        str = paramString.substring(8);
      } 
    } 
    if (str != null && zzngi.matcher(str).matches()) {
      FirebaseInstanceId firebaseInstanceId = this.zznfi;
      if (str.length() != 0) {
        paramString = "U!".concat(str);
      } else {
        paramString = new String("U!");
      } 
      firebaseInstanceId.zzqw(paramString);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 55 + "[a-zA-Z0-9-_.~%]{1,900}".length());
    stringBuilder.append("Invalid topic name: ");
    stringBuilder.append(str);
    stringBuilder.append(" does not match the allowed format ");
    stringBuilder.append("[a-zA-Z0-9-_.~%]{1,900}");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/FirebaseMessaging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */