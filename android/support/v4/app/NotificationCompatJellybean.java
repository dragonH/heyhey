package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(16)
class NotificationCompatJellybean {
  static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
  
  static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
  
  private static final String KEY_ACTION_INTENT = "actionIntent";
  
  private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
  
  private static final String KEY_EXTRAS = "extras";
  
  private static final String KEY_ICON = "icon";
  
  private static final String KEY_REMOTE_INPUTS = "remoteInputs";
  
  private static final String KEY_TITLE = "title";
  
  public static final String TAG = "NotificationCompat";
  
  private static Class<?> sActionClass;
  
  private static Field sActionIconField;
  
  private static Field sActionIntentField;
  
  private static Field sActionTitleField;
  
  private static boolean sActionsAccessFailed;
  
  private static Field sActionsField;
  
  private static final Object sActionsLock;
  
  private static Field sExtrasField;
  
  private static boolean sExtrasFieldAccessFailed;
  
  private static final Object sExtrasLock = new Object();
  
  static {
    sActionsLock = new Object();
  }
  
  public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean1, CharSequence paramCharSequence2, Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean2) {
    Notification.BigPictureStyle bigPictureStyle = (new Notification.BigPictureStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(paramCharSequence1).bigPicture(paramBitmap1);
    if (paramBoolean2)
      bigPictureStyle.bigLargeIcon(paramBitmap2); 
    if (paramBoolean1)
      bigPictureStyle.setSummaryText(paramCharSequence2); 
  }
  
  public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean, CharSequence paramCharSequence2, CharSequence paramCharSequence3) {
    Notification.BigTextStyle bigTextStyle = (new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(paramCharSequence1).bigText(paramCharSequence3);
    if (paramBoolean)
      bigTextStyle.setSummaryText(paramCharSequence2); 
  }
  
  public static void addInboxStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence1, boolean paramBoolean, CharSequence paramCharSequence2, ArrayList<CharSequence> paramArrayList) {
    Notification.InboxStyle inboxStyle = (new Notification.InboxStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder())).setBigContentTitle(paramCharSequence1);
    if (paramBoolean)
      inboxStyle.setSummaryText(paramCharSequence2); 
    Iterator<CharSequence> iterator = paramArrayList.iterator();
    while (iterator.hasNext())
      inboxStyle.addLine(iterator.next()); 
  }
  
  public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> paramList) {
    int i = paramList.size();
    SparseArray<Bundle> sparseArray = null;
    byte b = 0;
    while (b < i) {
      Bundle bundle = paramList.get(b);
      SparseArray<Bundle> sparseArray1 = sparseArray;
      if (bundle != null) {
        sparseArray1 = sparseArray;
        if (sparseArray == null)
          sparseArray1 = new SparseArray(); 
        sparseArray1.put(b, bundle);
      } 
      b++;
      sparseArray = sparseArray1;
    } 
    return sparseArray;
  }
  
  private static boolean ensureActionReflectionReadyLocked() {
    if (sActionsAccessFailed)
      return false; 
    try {
      if (sActionsField == null) {
        Class<?> clazz = Class.forName("android.app.Notification$Action");
        sActionClass = clazz;
        sActionIconField = clazz.getDeclaredField("icon");
        sActionTitleField = sActionClass.getDeclaredField("title");
        sActionIntentField = sActionClass.getDeclaredField("actionIntent");
        Field field = Notification.class.getDeclaredField("actions");
        sActionsField = field;
        field.setAccessible(true);
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      Log.e("NotificationCompat", "Unable to access notification actions", classNotFoundException);
      sActionsAccessFailed = true;
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("NotificationCompat", "Unable to access notification actions", noSuchFieldException);
      sActionsAccessFailed = true;
    } 
    return sActionsAccessFailed ^ true;
  }
  
  public static NotificationCompatBase.Action getAction(Notification paramNotification, int paramInt, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    // Byte code:
    //   0: getstatic android/support/v4/app/NotificationCompatJellybean.sActionsLock : Ljava/lang/Object;
    //   3: astore #4
    //   5: aload #4
    //   7: monitorenter
    //   8: aload_0
    //   9: invokestatic getActionObjectsLocked : (Landroid/app/Notification;)[Ljava/lang/Object;
    //   12: astore #5
    //   14: aload #5
    //   16: ifnull -> 119
    //   19: aload #5
    //   21: iload_1
    //   22: aaload
    //   23: astore #5
    //   25: aload_0
    //   26: invokestatic getExtras : (Landroid/app/Notification;)Landroid/os/Bundle;
    //   29: astore_0
    //   30: aload_0
    //   31: ifnull -> 57
    //   34: aload_0
    //   35: ldc 'android.support.actionExtras'
    //   37: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   40: astore_0
    //   41: aload_0
    //   42: ifnull -> 57
    //   45: aload_0
    //   46: iload_1
    //   47: invokevirtual get : (I)Ljava/lang/Object;
    //   50: checkcast android/os/Bundle
    //   53: astore_0
    //   54: goto -> 59
    //   57: aconst_null
    //   58: astore_0
    //   59: aload_2
    //   60: aload_3
    //   61: getstatic android/support/v4/app/NotificationCompatJellybean.sActionIconField : Ljava/lang/reflect/Field;
    //   64: aload #5
    //   66: invokevirtual getInt : (Ljava/lang/Object;)I
    //   69: getstatic android/support/v4/app/NotificationCompatJellybean.sActionTitleField : Ljava/lang/reflect/Field;
    //   72: aload #5
    //   74: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   77: checkcast java/lang/CharSequence
    //   80: getstatic android/support/v4/app/NotificationCompatJellybean.sActionIntentField : Ljava/lang/reflect/Field;
    //   83: aload #5
    //   85: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   88: checkcast android/app/PendingIntent
    //   91: aload_0
    //   92: invokestatic readAction : (Landroid/support/v4/app/NotificationCompatBase$Action$Factory;Landroid/support/v4/app/RemoteInputCompatBase$RemoteInput$Factory;ILjava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/os/Bundle;)Landroid/support/v4/app/NotificationCompatBase$Action;
    //   95: astore_0
    //   96: aload #4
    //   98: monitorexit
    //   99: aload_0
    //   100: areturn
    //   101: astore_0
    //   102: goto -> 124
    //   105: astore_0
    //   106: ldc 'NotificationCompat'
    //   108: ldc 'Unable to access notification actions'
    //   110: aload_0
    //   111: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   114: pop
    //   115: iconst_1
    //   116: putstatic android/support/v4/app/NotificationCompatJellybean.sActionsAccessFailed : Z
    //   119: aload #4
    //   121: monitorexit
    //   122: aconst_null
    //   123: areturn
    //   124: aload #4
    //   126: monitorexit
    //   127: aload_0
    //   128: athrow
    // Exception table:
    //   from	to	target	type
    //   8	14	105	java/lang/IllegalAccessException
    //   8	14	101	finally
    //   25	30	105	java/lang/IllegalAccessException
    //   25	30	101	finally
    //   34	41	105	java/lang/IllegalAccessException
    //   34	41	101	finally
    //   45	54	105	java/lang/IllegalAccessException
    //   45	54	101	finally
    //   59	96	105	java/lang/IllegalAccessException
    //   59	96	101	finally
    //   96	99	101	finally
    //   106	119	101	finally
    //   119	122	101	finally
    //   124	127	101	finally
  }
  
  public static int getActionCount(Notification paramNotification) {
    synchronized (sActionsLock) {
      boolean bool;
      Object[] arrayOfObject = getActionObjectsLocked(paramNotification);
      if (arrayOfObject != null) {
        bool = arrayOfObject.length;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  private static NotificationCompatBase.Action getActionFromBundle(Bundle paramBundle, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    boolean bool;
    Bundle bundle = paramBundle.getBundle("extras");
    if (bundle != null) {
      bool = bundle.getBoolean("android.support.allowGeneratedReplies", false);
    } else {
      bool = false;
    } 
    return paramFactory.build(paramBundle.getInt("icon"), paramBundle.getCharSequence("title"), (PendingIntent)paramBundle.getParcelable("actionIntent"), paramBundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "remoteInputs"), paramFactory1), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "dataOnlyRemoteInputs"), paramFactory1), bool);
  }
  
  private static Object[] getActionObjectsLocked(Notification paramNotification) {
    synchronized (sActionsLock) {
      if (!ensureActionReflectionReadyLocked())
        return null; 
      try {
        return (Object[])sActionsField.get(paramNotification);
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("NotificationCompat", "Unable to access notification actions", illegalAccessException);
        sActionsAccessFailed = true;
        return null;
      } 
    } 
  }
  
  public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList, NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1) {
    if (paramArrayList == null)
      return null; 
    NotificationCompatBase.Action[] arrayOfAction = paramFactory.newArray(paramArrayList.size());
    for (byte b = 0; b < arrayOfAction.length; b++)
      arrayOfAction[b] = getActionFromBundle((Bundle)paramArrayList.get(b), paramFactory, paramFactory1); 
    return arrayOfAction;
  }
  
  private static Bundle getBundleForAction(NotificationCompatBase.Action paramAction) {
    Bundle bundle2;
    Bundle bundle1 = new Bundle();
    bundle1.putInt("icon", paramAction.getIcon());
    bundle1.putCharSequence("title", paramAction.getTitle());
    bundle1.putParcelable("actionIntent", (Parcelable)paramAction.getActionIntent());
    if (paramAction.getExtras() != null) {
      bundle2 = new Bundle(paramAction.getExtras());
    } else {
      bundle2 = new Bundle();
    } 
    bundle2.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    bundle1.putBundle("extras", bundle2);
    bundle1.putParcelableArray("remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(paramAction.getRemoteInputs()));
    return bundle1;
  }
  
  public static Bundle getExtras(Notification paramNotification) {
    synchronized (sExtrasLock) {
      if (sExtrasFieldAccessFailed)
        return null; 
      try {
        if (sExtrasField == null) {
          Field field = Notification.class.getDeclaredField("extras");
          if (!Bundle.class.isAssignableFrom(field.getType())) {
            Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
            sExtrasFieldAccessFailed = true;
            return null;
          } 
          field.setAccessible(true);
          sExtrasField = field;
        } 
        Bundle bundle2 = (Bundle)sExtrasField.get(paramNotification);
        Bundle bundle1 = bundle2;
        if (bundle2 == null) {
          bundle1 = new Bundle();
          this();
          sExtrasField.set(paramNotification, bundle1);
        } 
        return bundle1;
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("NotificationCompat", "Unable to access notification extras", illegalAccessException);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("NotificationCompat", "Unable to access notification extras", noSuchFieldException);
      } 
      sExtrasFieldAccessFailed = true;
      return null;
    } 
  }
  
  public static ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompatBase.Action[] paramArrayOfAction) {
    if (paramArrayOfAction == null)
      return null; 
    ArrayList<Bundle> arrayList = new ArrayList(paramArrayOfAction.length);
    int i = paramArrayOfAction.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(getBundleForAction(paramArrayOfAction[b])); 
    return (ArrayList)arrayList;
  }
  
  public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1, int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle) {
    RemoteInputCompatBase.RemoteInput.Factory factory;
    boolean bool;
    if (paramBundle != null) {
      RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput2 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "android.support.remoteInputs"), paramFactory1);
      RemoteInputCompatBase.RemoteInput[] arrayOfRemoteInput1 = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(paramBundle, "android.support.dataRemoteInputs"), paramFactory1);
      bool = paramBundle.getBoolean("android.support.allowGeneratedReplies");
    } else {
      factory = null;
      paramFactory1 = factory;
      bool = false;
    } 
    return paramFactory.build(paramInt, paramCharSequence, paramPendingIntent, paramBundle, (RemoteInputCompatBase.RemoteInput[])factory, (RemoteInputCompatBase.RemoteInput[])paramFactory1, bool);
  }
  
  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompatBase.Action paramAction) {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    Bundle bundle = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null)
      bundle.putParcelableArray("android.support.remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(paramAction.getRemoteInputs())); 
    if (paramAction.getDataOnlyRemoteInputs() != null)
      bundle.putParcelableArray("android.support.dataRemoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(paramAction.getDataOnlyRemoteInputs())); 
    bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return bundle;
  }
  
  public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
    private Notification.Builder b;
    
    private List<Bundle> mActionExtrasList;
    
    private RemoteViews mBigContentView;
    
    private RemoteViews mContentView;
    
    private final Bundle mExtras;
    
    public Builder(Context param1Context, Notification param1Notification, CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3, RemoteViews param1RemoteViews1, int param1Int1, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Bitmap param1Bitmap, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, CharSequence param1CharSequence4, boolean param1Boolean3, Bundle param1Bundle, String param1String1, boolean param1Boolean4, String param1String2, RemoteViews param1RemoteViews2, RemoteViews param1RemoteViews3) {
      boolean bool2;
      this.mActionExtrasList = new ArrayList<Bundle>();
      Notification.Builder builder = (new Notification.Builder(param1Context)).setWhen(param1Notification.when).setSmallIcon(param1Notification.icon, param1Notification.iconLevel).setContent(param1Notification.contentView).setTicker(param1Notification.tickerText, param1RemoteViews1).setSound(param1Notification.sound, param1Notification.audioStreamType).setVibrate(param1Notification.vibrate).setLights(param1Notification.ledARGB, param1Notification.ledOnMS, param1Notification.ledOffMS);
      int i = param1Notification.flags;
      boolean bool1 = false;
      if ((i & 0x2) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      builder = builder.setOngoing(bool2);
      if ((param1Notification.flags & 0x8) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      builder = builder.setOnlyAlertOnce(bool2);
      if ((param1Notification.flags & 0x10) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      builder = builder.setAutoCancel(bool2).setDefaults(param1Notification.defaults).setContentTitle(param1CharSequence1).setContentText(param1CharSequence2).setSubText(param1CharSequence4).setContentInfo(param1CharSequence3).setContentIntent(param1PendingIntent1).setDeleteIntent(param1Notification.deleteIntent);
      if ((param1Notification.flags & 0x80) != 0) {
        bool2 = true;
      } else {
        bool2 = bool1;
      } 
      this.b = builder.setFullScreenIntent(param1PendingIntent2, bool2).setLargeIcon(param1Bitmap).setNumber(param1Int1).setUsesChronometer(param1Boolean2).setPriority(param1Int4).setProgress(param1Int2, param1Int3, param1Boolean1);
      Bundle bundle = new Bundle();
      this.mExtras = bundle;
      if (param1Bundle != null)
        bundle.putAll(param1Bundle); 
      if (param1Boolean3)
        bundle.putBoolean("android.support.localOnly", true); 
      if (param1String1 != null) {
        bundle.putString("android.support.groupKey", param1String1);
        if (param1Boolean4) {
          bundle.putBoolean("android.support.isGroupSummary", true);
        } else {
          bundle.putBoolean("android.support.useSideChannel", true);
        } 
      } 
      if (param1String2 != null)
        bundle.putString("android.support.sortKey", param1String2); 
      this.mContentView = param1RemoteViews2;
      this.mBigContentView = param1RemoteViews3;
    }
    
    public void addAction(NotificationCompatBase.Action param1Action) {
      this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, param1Action));
    }
    
    public Notification build() {
      Notification notification = this.b.build();
      Bundle bundle1 = NotificationCompatJellybean.getExtras(notification);
      Bundle bundle2 = new Bundle(this.mExtras);
      for (String str : this.mExtras.keySet()) {
        if (bundle1.containsKey(str))
          bundle2.remove(str); 
      } 
      bundle1.putAll(bundle2);
      SparseArray<Bundle> sparseArray = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (sparseArray != null)
        NotificationCompatJellybean.getExtras(notification).putSparseParcelableArray("android.support.actionExtras", sparseArray); 
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      return notification;
    }
    
    public Notification.Builder getBuilder() {
      return this.b;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */