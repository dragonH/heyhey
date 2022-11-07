package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;

@RequiresApi(21)
class NotificationCompatApi21 {
  private static final String KEY_AUTHOR = "author";
  
  private static final String KEY_MESSAGES = "messages";
  
  private static final String KEY_ON_READ = "on_read";
  
  private static final String KEY_ON_REPLY = "on_reply";
  
  private static final String KEY_PARTICIPANTS = "participants";
  
  private static final String KEY_REMOTE_INPUT = "remote_input";
  
  private static final String KEY_TEXT = "text";
  
  private static final String KEY_TIMESTAMP = "timestamp";
  
  private static RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput paramRemoteInput) {
    return (new RemoteInput.Builder(paramRemoteInput.getResultKey())).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }
  
  static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation paramUnreadConversation) {
    String str1 = null;
    if (paramUnreadConversation == null)
      return null; 
    Bundle bundle = new Bundle();
    String[] arrayOfString = paramUnreadConversation.getParticipants();
    byte b = 0;
    String str2 = str1;
    if (arrayOfString != null) {
      str2 = str1;
      if ((paramUnreadConversation.getParticipants()).length > 1)
        str2 = paramUnreadConversation.getParticipants()[0]; 
    } 
    int i = (paramUnreadConversation.getMessages()).length;
    Parcelable[] arrayOfParcelable = new Parcelable[i];
    while (b < i) {
      Bundle bundle1 = new Bundle();
      bundle1.putString("text", paramUnreadConversation.getMessages()[b]);
      bundle1.putString("author", str2);
      arrayOfParcelable[b] = (Parcelable)bundle1;
      b++;
    } 
    bundle.putParcelableArray("messages", arrayOfParcelable);
    RemoteInputCompatBase.RemoteInput remoteInput = paramUnreadConversation.getRemoteInput();
    if (remoteInput != null)
      bundle.putParcelable("remote_input", (Parcelable)fromCompatRemoteInput(remoteInput)); 
    bundle.putParcelable("on_reply", (Parcelable)paramUnreadConversation.getReplyPendingIntent());
    bundle.putParcelable("on_read", (Parcelable)paramUnreadConversation.getReadPendingIntent());
    bundle.putStringArray("participants", paramUnreadConversation.getParticipants());
    bundle.putLong("timestamp", paramUnreadConversation.getLatestTimestamp());
    return bundle;
  }
  
  static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle, NotificationCompatBase.UnreadConversation.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    String[] arrayOfString1;
    RemoteInputCompatBase.RemoteInput remoteInput = null;
    if (paramBundle == null)
      return null; 
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("messages");
    if (arrayOfParcelable != null) {
      int i = arrayOfParcelable.length;
      arrayOfString1 = new String[i];
      boolean bool = false;
      byte b = 0;
      while (true) {
        if (b < i) {
          Parcelable parcelable = arrayOfParcelable[b];
          if (!(parcelable instanceof Bundle)) {
            b = bool;
            break;
          } 
          String str = ((Bundle)parcelable).getString("text");
          arrayOfString1[b] = str;
          if (str == null) {
            b = bool;
            break;
          } 
          b++;
          continue;
        } 
        b = 1;
        break;
      } 
      if (b == 0)
        return null; 
    } else {
      arrayOfString1 = null;
    } 
    PendingIntent pendingIntent2 = (PendingIntent)paramBundle.getParcelable("on_read");
    PendingIntent pendingIntent1 = (PendingIntent)paramBundle.getParcelable("on_reply");
    RemoteInput remoteInput1 = (RemoteInput)paramBundle.getParcelable("remote_input");
    String[] arrayOfString2 = paramBundle.getStringArray("participants");
    if (arrayOfString2 == null || arrayOfString2.length != 1)
      return null; 
    if (remoteInput1 != null)
      remoteInput = toCompatRemoteInput(remoteInput1, paramFactory1); 
    return paramFactory.build(arrayOfString1, remoteInput, pendingIntent1, pendingIntent2, arrayOfString2, paramBundle.getLong("timestamp"));
  }
  
  private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(RemoteInput paramRemoteInput, RemoteInputCompatBase.RemoteInput.Factory paramFactory) {
    return paramFactory.build(paramRemoteInput.getResultKey(), paramRemoteInput.getLabel(), paramRemoteInput.getChoices(), paramRemoteInput.getAllowFreeFormInput(), paramRemoteInput.getExtras(), null);
  }
  
  public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
    private Notification.Builder b;
    
    private RemoteViews mBigContentView;
    
    private RemoteViews mContentView;
    
    private Bundle mExtras;
    
    private int mGroupAlertBehavior;
    
    private RemoteViews mHeadsUpContentView;
    
    public Builder(Context param1Context, Notification param1Notification1, CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3, RemoteViews param1RemoteViews1, int param1Int1, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Bitmap param1Bitmap, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, int param1Int4, CharSequence param1CharSequence4, boolean param1Boolean4, String param1String1, ArrayList<String> param1ArrayList, Bundle param1Bundle, int param1Int5, int param1Int6, Notification param1Notification2, String param1String2, boolean param1Boolean5, String param1String3, RemoteViews param1RemoteViews2, RemoteViews param1RemoteViews3, RemoteViews param1RemoteViews4, int param1Int7) {
      Notification.Builder builder = (new Notification.Builder(param1Context)).setWhen(param1Notification1.when).setShowWhen(param1Boolean2).setSmallIcon(param1Notification1.icon, param1Notification1.iconLevel).setContent(param1Notification1.contentView).setTicker(param1Notification1.tickerText, param1RemoteViews1).setSound(param1Notification1.sound, param1Notification1.audioStreamType).setVibrate(param1Notification1.vibrate).setLights(param1Notification1.ledARGB, param1Notification1.ledOnMS, param1Notification1.ledOffMS);
      int i = param1Notification1.flags;
      boolean bool = true;
      if ((i & 0x2) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setOngoing(param1Boolean2);
      if ((param1Notification1.flags & 0x8) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setOnlyAlertOnce(param1Boolean2);
      if ((param1Notification1.flags & 0x10) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setAutoCancel(param1Boolean2).setDefaults(param1Notification1.defaults).setContentTitle(param1CharSequence1).setContentText(param1CharSequence2).setSubText(param1CharSequence4).setContentInfo(param1CharSequence3).setContentIntent(param1PendingIntent1).setDeleteIntent(param1Notification1.deleteIntent);
      if ((param1Notification1.flags & 0x80) != 0) {
        param1Boolean2 = bool;
      } else {
        param1Boolean2 = false;
      } 
      this.b = builder.setFullScreenIntent(param1PendingIntent2, param1Boolean2).setLargeIcon(param1Bitmap).setNumber(param1Int1).setUsesChronometer(param1Boolean3).setPriority(param1Int4).setProgress(param1Int2, param1Int3, param1Boolean1).setLocalOnly(param1Boolean4).setGroup(param1String2).setGroupSummary(param1Boolean5).setSortKey(param1String3).setCategory(param1String1).setColor(param1Int5).setVisibility(param1Int6).setPublicVersion(param1Notification2);
      Bundle bundle = new Bundle();
      this.mExtras = bundle;
      if (param1Bundle != null)
        bundle.putAll(param1Bundle); 
      for (String str : param1ArrayList)
        this.b.addPerson(str); 
      this.mContentView = param1RemoteViews2;
      this.mBigContentView = param1RemoteViews3;
      this.mHeadsUpContentView = param1RemoteViews4;
      this.mGroupAlertBehavior = param1Int7;
    }
    
    private void removeSoundAndVibration(Notification param1Notification) {
      param1Notification.sound = null;
      param1Notification.vibrate = null;
      param1Notification.defaults = param1Notification.defaults & 0xFFFFFFFE & 0xFFFFFFFD;
    }
    
    public void addAction(NotificationCompatBase.Action param1Action) {
      NotificationCompatApi20.addAction(this.b, param1Action);
    }
    
    public Notification build() {
      this.b.setExtras(this.mExtras);
      Notification notification = this.b.build();
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      remoteViews = this.mHeadsUpContentView;
      if (remoteViews != null)
        notification.headsUpContentView = remoteViews; 
      if (this.mGroupAlertBehavior != 0) {
        if (notification.getGroup() != null && (notification.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2)
          removeSoundAndVibration(notification); 
        if (notification.getGroup() != null && (notification.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1)
          removeSoundAndVibration(notification); 
      } 
      return notification;
    }
    
    public Notification.Builder getBuilder() {
      return this.b;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */