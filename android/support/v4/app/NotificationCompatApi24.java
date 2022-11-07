package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(24)
class NotificationCompatApi24 {
  public static void addAction(Notification.Builder paramBuilder, NotificationCompatBase.Action paramAction) {
    Bundle bundle;
    Notification.Action.Builder builder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    if (paramAction.getRemoteInputs() != null) {
      RemoteInput[] arrayOfRemoteInput = RemoteInputCompatApi20.fromCompat(paramAction.getRemoteInputs());
      int i = arrayOfRemoteInput.length;
      for (byte b = 0; b < i; b++)
        builder.addRemoteInput(arrayOfRemoteInput[b]); 
    } 
    if (paramAction.getExtras() != null) {
      bundle = new Bundle(paramAction.getExtras());
    } else {
      bundle = new Bundle();
    } 
    bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    builder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
    builder.addExtras(bundle);
    paramBuilder.addAction(builder.build());
  }
  
  public static void addMessagingStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, CharSequence paramCharSequence2, List<CharSequence> paramList1, List<Long> paramList, List<CharSequence> paramList2, List<String> paramList3, List<Uri> paramList4) {
    Notification.MessagingStyle messagingStyle = (new Notification.MessagingStyle(paramCharSequence1)).setConversationTitle(paramCharSequence2);
    for (byte b = 0; b < paramList1.size(); b++) {
      Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message(paramList1.get(b), ((Long)paramList.get(b)).longValue(), paramList2.get(b));
      if (paramList3.get(b) != null)
        message.setData(paramList3.get(b), paramList4.get(b)); 
      messagingStyle.addMessage(message);
    } 
    messagingStyle.setBuilder(paramNotificationBuilderWithBuilderAccessor.getBuilder());
  }
  
  public static NotificationCompatBase.Action getAction(Notification paramNotification, int paramInt, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    return getActionCompatFromAction(paramNotification.actions[paramInt], paramFactory, paramFactory1);
  }
  
  private static NotificationCompatBase.Action getActionCompatFromAction(Notification.Action paramAction, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = RemoteInputCompatApi20.toCompat(paramAction.getRemoteInputs(), paramFactory1);
    if (paramAction.getExtras().getBoolean("android.support.allowGeneratedReplies") || paramAction.getAllowGeneratedReplies()) {
      boolean bool1 = true;
      return paramFactory.build(paramAction.icon, paramAction.title, paramAction.actionIntent, paramAction.getExtras(), arrayOfRemoteInput, null, bool1);
    } 
    boolean bool = false;
    return paramFactory.build(paramAction.icon, paramAction.title, paramAction.actionIntent, paramAction.getExtras(), arrayOfRemoteInput, null, bool);
  }
  
  private static Notification.Action getActionFromActionCompat(NotificationCompatBase.Action paramAction) {
    Bundle bundle;
    Notification.Action.Builder builder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    if (paramAction.getExtras() != null) {
      bundle = new Bundle(paramAction.getExtras());
    } else {
      bundle = new Bundle();
    } 
    bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    builder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
    builder.addExtras(bundle);
    RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput = paramAction.getRemoteInputs();
    if (arrayOfRemoteInput != null) {
      RemoteInput[] arrayOfRemoteInput1 = RemoteInputCompatApi20.fromCompat(arrayOfRemoteInput);
      int i = arrayOfRemoteInput1.length;
      for (byte b = 0; b < i; b++)
        builder.addRemoteInput(arrayOfRemoteInput1[b]); 
    } 
    return builder.build();
  }
  
  public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    if (paramArrayList == null)
      return null; 
    NotificationCompatBase.Action[] arrayOfAction = paramFactory.newArray(paramArrayList.size());
    for (byte b = 0; b < arrayOfAction.length; b++)
      arrayOfAction[b] = getActionCompatFromAction((Notification.Action)paramArrayList.get(b), paramFactory, paramFactory1); 
    return arrayOfAction;
  }
  
  public static ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompatBase.Action[] paramArrayOfAction) {
    if (paramArrayOfAction == null)
      return null; 
    ArrayList<Notification.Action> arrayList = new ArrayList(paramArrayOfAction.length);
    int i = paramArrayOfAction.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(getActionFromActionCompat(paramArrayOfAction[b])); 
    return (ArrayList)arrayList;
  }
  
  public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
    private Notification.Builder b;
    
    private int mGroupAlertBehavior;
    
    public Builder(Context param1Context, Notification param1Notification1, CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3, RemoteViews param1RemoteViews1, int param1Int1, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Bitmap param1Bitmap, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, int param1Int4, CharSequence param1CharSequence4, boolean param1Boolean4, String param1String1, ArrayList<String> param1ArrayList, Bundle param1Bundle, int param1Int5, int param1Int6, Notification param1Notification2, String param1String2, boolean param1Boolean5, String param1String3, CharSequence[] param1ArrayOfCharSequence, RemoteViews param1RemoteViews2, RemoteViews param1RemoteViews3, RemoteViews param1RemoteViews4, int param1Int7) {
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
      builder = builder.setFullScreenIntent(param1PendingIntent2, param1Boolean2).setLargeIcon(param1Bitmap).setNumber(param1Int1).setUsesChronometer(param1Boolean3).setPriority(param1Int4).setProgress(param1Int2, param1Int3, param1Boolean1).setLocalOnly(param1Boolean4).setExtras(param1Bundle).setGroup(param1String2).setGroupSummary(param1Boolean5).setSortKey(param1String3).setCategory(param1String1).setColor(param1Int5).setVisibility(param1Int6).setPublicVersion(param1Notification2).setRemoteInputHistory(param1ArrayOfCharSequence);
      this.b = builder;
      if (param1RemoteViews2 != null)
        builder.setCustomContentView(param1RemoteViews2); 
      if (param1RemoteViews3 != null)
        this.b.setCustomBigContentView(param1RemoteViews3); 
      if (param1RemoteViews4 != null)
        this.b.setCustomHeadsUpContentView(param1RemoteViews4); 
      for (String str : param1ArrayList)
        this.b.addPerson(str); 
      this.mGroupAlertBehavior = param1Int7;
    }
    
    private void removeSoundAndVibration(Notification param1Notification) {
      param1Notification.sound = null;
      param1Notification.vibrate = null;
      param1Notification.defaults = param1Notification.defaults & 0xFFFFFFFE & 0xFFFFFFFD;
    }
    
    public void addAction(NotificationCompatBase.Action param1Action) {
      NotificationCompatApi24.addAction(this.b, param1Action);
    }
    
    public Notification build() {
      Notification notification = this.b.build();
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompatApi24.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */