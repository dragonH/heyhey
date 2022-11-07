package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat {
  public static final int BADGE_ICON_LARGE = 2;
  
  public static final int BADGE_ICON_NONE = 0;
  
  public static final int BADGE_ICON_SMALL = 1;
  
  public static final String CATEGORY_ALARM = "alarm";
  
  public static final String CATEGORY_CALL = "call";
  
  public static final String CATEGORY_EMAIL = "email";
  
  public static final String CATEGORY_ERROR = "err";
  
  public static final String CATEGORY_EVENT = "event";
  
  public static final String CATEGORY_MESSAGE = "msg";
  
  public static final String CATEGORY_PROGRESS = "progress";
  
  public static final String CATEGORY_PROMO = "promo";
  
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  
  public static final String CATEGORY_REMINDER = "reminder";
  
  public static final String CATEGORY_SERVICE = "service";
  
  public static final String CATEGORY_SOCIAL = "social";
  
  public static final String CATEGORY_STATUS = "status";
  
  public static final String CATEGORY_SYSTEM = "sys";
  
  public static final String CATEGORY_TRANSPORT = "transport";
  
  @ColorInt
  public static final int COLOR_DEFAULT = 0;
  
  public static final int DEFAULT_ALL = -1;
  
  public static final int DEFAULT_LIGHTS = 4;
  
  public static final int DEFAULT_SOUND = 1;
  
  public static final int DEFAULT_VIBRATE = 2;
  
  public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
  
  public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
  
  public static final String EXTRA_BIG_TEXT = "android.bigText";
  
  public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
  
  public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
  
  public static final String EXTRA_INFO_TEXT = "android.infoText";
  
  public static final String EXTRA_LARGE_ICON = "android.largeIcon";
  
  public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
  
  public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
  
  public static final String EXTRA_MESSAGES = "android.messages";
  
  public static final String EXTRA_PEOPLE = "android.people";
  
  public static final String EXTRA_PICTURE = "android.picture";
  
  public static final String EXTRA_PROGRESS = "android.progress";
  
  public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
  
  public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
  
  public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
  
  public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
  
  public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
  
  public static final String EXTRA_SHOW_WHEN = "android.showWhen";
  
  public static final String EXTRA_SMALL_ICON = "android.icon";
  
  public static final String EXTRA_SUB_TEXT = "android.subText";
  
  public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
  
  public static final String EXTRA_TEMPLATE = "android.template";
  
  public static final String EXTRA_TEXT = "android.text";
  
  public static final String EXTRA_TEXT_LINES = "android.textLines";
  
  public static final String EXTRA_TITLE = "android.title";
  
  public static final String EXTRA_TITLE_BIG = "android.title.big";
  
  public static final int FLAG_AUTO_CANCEL = 16;
  
  public static final int FLAG_FOREGROUND_SERVICE = 64;
  
  public static final int FLAG_GROUP_SUMMARY = 512;
  
  @Deprecated
  public static final int FLAG_HIGH_PRIORITY = 128;
  
  public static final int FLAG_INSISTENT = 4;
  
  public static final int FLAG_LOCAL_ONLY = 256;
  
  public static final int FLAG_NO_CLEAR = 32;
  
  public static final int FLAG_ONGOING_EVENT = 2;
  
  public static final int FLAG_ONLY_ALERT_ONCE = 8;
  
  public static final int FLAG_SHOW_LIGHTS = 1;
  
  public static final int GROUP_ALERT_ALL = 0;
  
  public static final int GROUP_ALERT_CHILDREN = 2;
  
  public static final int GROUP_ALERT_SUMMARY = 1;
  
  static final NotificationCompatImpl IMPL;
  
  public static final int PRIORITY_DEFAULT = 0;
  
  public static final int PRIORITY_HIGH = 1;
  
  public static final int PRIORITY_LOW = -1;
  
  public static final int PRIORITY_MAX = 2;
  
  public static final int PRIORITY_MIN = -2;
  
  public static final int STREAM_DEFAULT = -1;
  
  public static final int VISIBILITY_PRIVATE = 0;
  
  public static final int VISIBILITY_PUBLIC = 1;
  
  public static final int VISIBILITY_SECRET = -1;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 26) {
      IMPL = new NotificationCompatApi26Impl();
    } else if (i >= 24) {
      IMPL = new NotificationCompatApi24Impl();
    } else if (i >= 21) {
      IMPL = new NotificationCompatApi21Impl();
    } else if (i >= 20) {
      IMPL = new NotificationCompatApi20Impl();
    } else if (i >= 19) {
      IMPL = new NotificationCompatApi19Impl();
    } else {
      IMPL = new NotificationCompatApi16Impl();
    } 
  }
  
  static void addActionsToBuilder(NotificationBuilderWithActions paramNotificationBuilderWithActions, ArrayList<Action> paramArrayList) {
    Iterator<Action> iterator = paramArrayList.iterator();
    while (iterator.hasNext())
      paramNotificationBuilderWithActions.addAction(iterator.next()); 
  }
  
  public static Action getAction(Notification paramNotification, int paramInt) {
    return IMPL.getAction(paramNotification, paramInt);
  }
  
  public static int getActionCount(Notification paramNotification) {
    Notification.Action[] arrayOfAction;
    if (Build.VERSION.SDK_INT >= 19) {
      boolean bool;
      arrayOfAction = paramNotification.actions;
      if (arrayOfAction != null) {
        bool = arrayOfAction.length;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return NotificationCompatJellybean.getActionCount((Notification)arrayOfAction);
  }
  
  public static int getBadgeIconType(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getBadgeIconType() : 0;
  }
  
  public static String getCategory(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 21) ? paramNotification.category : null;
  }
  
  public static String getChannelId(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getChannelId() : null;
  }
  
  public static Bundle getExtras(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 19) ? paramNotification.extras : NotificationCompatJellybean.getExtras(paramNotification);
  }
  
  public static String getGroup(Notification paramNotification) {
    int i = Build.VERSION.SDK_INT;
    return (i >= 20) ? paramNotification.getGroup() : ((i >= 19) ? paramNotification.extras.getString("android.support.groupKey") : NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.groupKey"));
  }
  
  public static int getGroupAlertBehavior(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getGroupAlertBehavior() : 0;
  }
  
  public static boolean getLocalOnly(Notification paramNotification) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 20) {
      boolean bool;
      if ((paramNotification.flags & 0x100) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return (i >= 19) ? paramNotification.extras.getBoolean("android.support.localOnly") : NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.localOnly");
  }
  
  static Notification[] getNotificationArrayFromBundle(Bundle paramBundle, String paramString) {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    if (arrayOfParcelable instanceof Notification[] || arrayOfParcelable == null)
      return (Notification[])arrayOfParcelable; 
    Notification[] arrayOfNotification = new Notification[arrayOfParcelable.length];
    for (byte b = 0; b < arrayOfParcelable.length; b++)
      arrayOfNotification[b] = (Notification)arrayOfParcelable[b]; 
    paramBundle.putParcelableArray(paramString, (Parcelable[])arrayOfNotification);
    return arrayOfNotification;
  }
  
  public static String getShortcutId(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getShortcutId() : null;
  }
  
  public static String getSortKey(Notification paramNotification) {
    int i = Build.VERSION.SDK_INT;
    return (i >= 20) ? paramNotification.getSortKey() : ((i >= 19) ? paramNotification.extras.getString("android.support.sortKey") : NotificationCompatJellybean.getExtras(paramNotification).getString("android.support.sortKey"));
  }
  
  public static long getTimeoutAfter(Notification paramNotification) {
    return (Build.VERSION.SDK_INT >= 26) ? paramNotification.getTimeoutAfter() : 0L;
  }
  
  public static boolean isGroupSummary(Notification paramNotification) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 20) {
      boolean bool;
      if ((paramNotification.flags & 0x200) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return (i >= 19) ? paramNotification.extras.getBoolean("android.support.isGroupSummary") : NotificationCompatJellybean.getExtras(paramNotification).getBoolean("android.support.isGroupSummary");
  }
  
  public static class Action extends NotificationCompatBase.Action {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final NotificationCompatBase.Action.Factory FACTORY = new NotificationCompatBase.Action.Factory() {
        public NotificationCompatBase.Action build(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent, Bundle param2Bundle, RemoteInputCompatBase.RemoteInput[] param2ArrayOfRemoteInput1, RemoteInputCompatBase.RemoteInput[] param2ArrayOfRemoteInput2, boolean param2Boolean) {
          return new NotificationCompat.Action(param2Int, param2CharSequence, param2PendingIntent, param2Bundle, (RemoteInput[])param2ArrayOfRemoteInput1, (RemoteInput[])param2ArrayOfRemoteInput2, param2Boolean);
        }
        
        public NotificationCompat.Action[] newArray(int param2Int) {
          return new NotificationCompat.Action[param2Int];
        }
      };
    
    public PendingIntent actionIntent;
    
    public int icon;
    
    private boolean mAllowGeneratedReplies;
    
    private final RemoteInput[] mDataOnlyRemoteInputs;
    
    final Bundle mExtras;
    
    private final RemoteInput[] mRemoteInputs;
    
    public CharSequence title;
    
    public Action(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(param1Int, param1CharSequence, param1PendingIntent, new Bundle(), null, null, true);
    }
    
    Action(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput1, RemoteInput[] param1ArrayOfRemoteInput2, boolean param1Boolean) {
      this.icon = param1Int;
      this.title = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.actionIntent = param1PendingIntent;
      if (param1Bundle == null)
        param1Bundle = new Bundle(); 
      this.mExtras = param1Bundle;
      this.mRemoteInputs = param1ArrayOfRemoteInput1;
      this.mDataOnlyRemoteInputs = param1ArrayOfRemoteInput2;
      this.mAllowGeneratedReplies = param1Boolean;
    }
    
    public PendingIntent getActionIntent() {
      return this.actionIntent;
    }
    
    public boolean getAllowGeneratedReplies() {
      return this.mAllowGeneratedReplies;
    }
    
    public RemoteInput[] getDataOnlyRemoteInputs() {
      return this.mDataOnlyRemoteInputs;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public int getIcon() {
      return this.icon;
    }
    
    public RemoteInput[] getRemoteInputs() {
      return this.mRemoteInputs;
    }
    
    public CharSequence getTitle() {
      return this.title;
    }
    
    public static final class Builder {
      private boolean mAllowGeneratedReplies;
      
      private final Bundle mExtras;
      
      private final int mIcon;
      
      private final PendingIntent mIntent;
      
      private ArrayList<RemoteInput> mRemoteInputs;
      
      private final CharSequence mTitle;
      
      public Builder(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
        this(param2Int, param2CharSequence, param2PendingIntent, new Bundle(), null, true);
      }
      
      private Builder(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent, Bundle param2Bundle, RemoteInput[] param2ArrayOfRemoteInput, boolean param2Boolean) {
        ArrayList<RemoteInput> arrayList;
        this.mAllowGeneratedReplies = true;
        this.mIcon = param2Int;
        this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(param2CharSequence);
        this.mIntent = param2PendingIntent;
        this.mExtras = param2Bundle;
        if (param2ArrayOfRemoteInput == null) {
          param2CharSequence = null;
        } else {
          arrayList = new ArrayList(Arrays.asList((Object[])param2ArrayOfRemoteInput));
        } 
        this.mRemoteInputs = arrayList;
        this.mAllowGeneratedReplies = param2Boolean;
      }
      
      public Builder(NotificationCompat.Action param2Action) {
        this(param2Action.icon, param2Action.title, param2Action.actionIntent, new Bundle(param2Action.mExtras), param2Action.getRemoteInputs(), param2Action.getAllowGeneratedReplies());
      }
      
      public Builder addExtras(Bundle param2Bundle) {
        if (param2Bundle != null)
          this.mExtras.putAll(param2Bundle); 
        return this;
      }
      
      public Builder addRemoteInput(RemoteInput param2RemoteInput) {
        if (this.mRemoteInputs == null)
          this.mRemoteInputs = new ArrayList<RemoteInput>(); 
        this.mRemoteInputs.add(param2RemoteInput);
        return this;
      }
      
      public NotificationCompat.Action build() {
        RemoteInput[] arrayOfRemoteInput1;
        RemoteInput[] arrayOfRemoteInput2;
        ArrayList<RemoteInput> arrayList1 = new ArrayList();
        ArrayList<RemoteInput> arrayList2 = new ArrayList();
        ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
        if (arrayList3 != null)
          for (RemoteInput remoteInput : arrayList3) {
            if (remoteInput.isDataOnly()) {
              arrayList1.add(remoteInput);
              continue;
            } 
            arrayList2.add(remoteInput);
          }  
        boolean bool = arrayList1.isEmpty();
        arrayList3 = null;
        if (bool) {
          arrayList1 = null;
        } else {
          arrayOfRemoteInput1 = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
        } 
        if (!arrayList2.isEmpty())
          arrayOfRemoteInput2 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]); 
        return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput2, arrayOfRemoteInput1, this.mAllowGeneratedReplies);
      }
      
      public Builder extend(NotificationCompat.Action.Extender param2Extender) {
        param2Extender.extend(this);
        return this;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public Builder setAllowGeneratedReplies(boolean param2Boolean) {
        this.mAllowGeneratedReplies = param2Boolean;
        return this;
      }
    }
    
    public static interface Extender {
      NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param2Builder);
    }
    
    public static final class WearableExtender implements Extender {
      private static final int DEFAULT_FLAGS = 1;
      
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      
      private static final int FLAG_AVAILABLE_OFFLINE = 1;
      
      private static final int FLAG_HINT_DISPLAY_INLINE = 4;
      
      private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
      
      private static final String KEY_CANCEL_LABEL = "cancelLabel";
      
      private static final String KEY_CONFIRM_LABEL = "confirmLabel";
      
      private static final String KEY_FLAGS = "flags";
      
      private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
      
      private CharSequence mCancelLabel;
      
      private CharSequence mConfirmLabel;
      
      private int mFlags = 1;
      
      private CharSequence mInProgressLabel;
      
      public WearableExtender() {}
      
      public WearableExtender(NotificationCompat.Action param2Action) {
        Bundle bundle = param2Action.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
          this.mFlags = bundle.getInt("flags", 1);
          this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
          this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
          this.mCancelLabel = bundle.getCharSequence("cancelLabel");
        } 
      }
      
      private void setFlag(int param2Int, boolean param2Boolean) {
        if (param2Boolean) {
          this.mFlags = param2Int | this.mFlags;
        } else {
          this.mFlags = (param2Int ^ 0xFFFFFFFF) & this.mFlags;
        } 
      }
      
      public WearableExtender clone() {
        WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.mFlags = this.mFlags;
        wearableExtender.mInProgressLabel = this.mInProgressLabel;
        wearableExtender.mConfirmLabel = this.mConfirmLabel;
        wearableExtender.mCancelLabel = this.mCancelLabel;
        return wearableExtender;
      }
      
      public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param2Builder) {
        Bundle bundle = new Bundle();
        int i = this.mFlags;
        if (i != 1)
          bundle.putInt("flags", i); 
        CharSequence charSequence = this.mInProgressLabel;
        if (charSequence != null)
          bundle.putCharSequence("inProgressLabel", charSequence); 
        charSequence = this.mConfirmLabel;
        if (charSequence != null)
          bundle.putCharSequence("confirmLabel", charSequence); 
        charSequence = this.mCancelLabel;
        if (charSequence != null)
          bundle.putCharSequence("cancelLabel", charSequence); 
        param2Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return param2Builder;
      }
      
      public CharSequence getCancelLabel() {
        return this.mCancelLabel;
      }
      
      public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
      }
      
      public boolean getHintDisplayActionInline() {
        boolean bool;
        if ((this.mFlags & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean getHintLaunchesActivity() {
        boolean bool;
        if ((this.mFlags & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
      }
      
      public boolean isAvailableOffline() {
        int i = this.mFlags;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public WearableExtender setAvailableOffline(boolean param2Boolean) {
        setFlag(1, param2Boolean);
        return this;
      }
      
      public WearableExtender setCancelLabel(CharSequence param2CharSequence) {
        this.mCancelLabel = param2CharSequence;
        return this;
      }
      
      public WearableExtender setConfirmLabel(CharSequence param2CharSequence) {
        this.mConfirmLabel = param2CharSequence;
        return this;
      }
      
      public WearableExtender setHintDisplayActionInline(boolean param2Boolean) {
        setFlag(4, param2Boolean);
        return this;
      }
      
      public WearableExtender setHintLaunchesActivity(boolean param2Boolean) {
        setFlag(2, param2Boolean);
        return this;
      }
      
      public WearableExtender setInProgressLabel(CharSequence param2CharSequence) {
        this.mInProgressLabel = param2CharSequence;
        return this;
      }
    }
  }
  
  static final class null implements NotificationCompatBase.Action.Factory {
    public NotificationCompatBase.Action build(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInputCompatBase.RemoteInput[] param1ArrayOfRemoteInput1, RemoteInputCompatBase.RemoteInput[] param1ArrayOfRemoteInput2, boolean param1Boolean) {
      return new NotificationCompat.Action(param1Int, param1CharSequence, param1PendingIntent, param1Bundle, (RemoteInput[])param1ArrayOfRemoteInput1, (RemoteInput[])param1ArrayOfRemoteInput2, param1Boolean);
    }
    
    public NotificationCompat.Action[] newArray(int param1Int) {
      return new NotificationCompat.Action[param1Int];
    }
  }
  
  public static final class Builder {
    private boolean mAllowGeneratedReplies;
    
    private final Bundle mExtras;
    
    private final int mIcon;
    
    private final PendingIntent mIntent;
    
    private ArrayList<RemoteInput> mRemoteInputs;
    
    private final CharSequence mTitle;
    
    public Builder(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(param1Int, param1CharSequence, param1PendingIntent, new Bundle(), null, true);
    }
    
    private Builder(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput, boolean param1Boolean) {
      ArrayList<RemoteInput> arrayList;
      this.mAllowGeneratedReplies = true;
      this.mIcon = param1Int;
      this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mIntent = param1PendingIntent;
      this.mExtras = param1Bundle;
      if (param1ArrayOfRemoteInput == null) {
        param1CharSequence = null;
      } else {
        arrayList = new ArrayList(Arrays.asList((Object[])param1ArrayOfRemoteInput));
      } 
      this.mRemoteInputs = arrayList;
      this.mAllowGeneratedReplies = param1Boolean;
    }
    
    public Builder(NotificationCompat.Action param1Action) {
      this(param1Action.icon, param1Action.title, param1Action.actionIntent, new Bundle(param1Action.mExtras), param1Action.getRemoteInputs(), param1Action.getAllowGeneratedReplies());
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput param1RemoteInput) {
      if (this.mRemoteInputs == null)
        this.mRemoteInputs = new ArrayList<RemoteInput>(); 
      this.mRemoteInputs.add(param1RemoteInput);
      return this;
    }
    
    public NotificationCompat.Action build() {
      RemoteInput[] arrayOfRemoteInput1;
      RemoteInput[] arrayOfRemoteInput2;
      ArrayList<RemoteInput> arrayList1 = new ArrayList();
      ArrayList<RemoteInput> arrayList2 = new ArrayList();
      ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
      if (arrayList3 != null)
        for (RemoteInput remoteInput : arrayList3) {
          if (remoteInput.isDataOnly()) {
            arrayList1.add(remoteInput);
            continue;
          } 
          arrayList2.add(remoteInput);
        }  
      boolean bool = arrayList1.isEmpty();
      arrayList3 = null;
      if (bool) {
        arrayList1 = null;
      } else {
        arrayOfRemoteInput1 = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
      } 
      if (!arrayList2.isEmpty())
        arrayOfRemoteInput2 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]); 
      return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput2, arrayOfRemoteInput1, this.mAllowGeneratedReplies);
    }
    
    public Builder extend(NotificationCompat.Action.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowGeneratedReplies(boolean param1Boolean) {
      this.mAllowGeneratedReplies = param1Boolean;
      return this;
    }
  }
  
  public static interface Extender {
    NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param1Builder);
  }
  
  public static final class WearableExtender implements Action.Extender {
    private static final int DEFAULT_FLAGS = 1;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_DISPLAY_INLINE = 4;
    
    private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
    
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    
    private CharSequence mCancelLabel;
    
    private CharSequence mConfirmLabel;
    
    private int mFlags = 1;
    
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {}
    
    public WearableExtender(NotificationCompat.Action param1Action) {
      Bundle bundle = param1Action.getExtras().getBundle("android.wearable.EXTENSIONS");
      if (bundle != null) {
        this.mFlags = bundle.getInt("flags", 1);
        this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
        this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
        this.mCancelLabel = bundle.getCharSequence("cancelLabel");
      } 
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags = param1Int | this.mFlags;
      } else {
        this.mFlags = (param1Int ^ 0xFFFFFFFF) & this.mFlags;
      } 
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mInProgressLabel = this.mInProgressLabel;
      wearableExtender.mConfirmLabel = this.mConfirmLabel;
      wearableExtender.mCancelLabel = this.mCancelLabel;
      return wearableExtender;
    }
    
    public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder param1Builder) {
      Bundle bundle = new Bundle();
      int i = this.mFlags;
      if (i != 1)
        bundle.putInt("flags", i); 
      CharSequence charSequence = this.mInProgressLabel;
      if (charSequence != null)
        bundle.putCharSequence("inProgressLabel", charSequence); 
      charSequence = this.mConfirmLabel;
      if (charSequence != null)
        bundle.putCharSequence("confirmLabel", charSequence); 
      charSequence = this.mCancelLabel;
      if (charSequence != null)
        bundle.putCharSequence("cancelLabel", charSequence); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    public CharSequence getCancelLabel() {
      return this.mCancelLabel;
    }
    
    public CharSequence getConfirmLabel() {
      return this.mConfirmLabel;
    }
    
    public boolean getHintDisplayActionInline() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintLaunchesActivity() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public CharSequence getInProgressLabel() {
      return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public WearableExtender setAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    public WearableExtender setCancelLabel(CharSequence param1CharSequence) {
      this.mCancelLabel = param1CharSequence;
      return this;
    }
    
    public WearableExtender setConfirmLabel(CharSequence param1CharSequence) {
      this.mConfirmLabel = param1CharSequence;
      return this;
    }
    
    public WearableExtender setHintDisplayActionInline(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintLaunchesActivity(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    public WearableExtender setInProgressLabel(CharSequence param1CharSequence) {
      this.mInProgressLabel = param1CharSequence;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface BadgeIconType {}
  
  public static class BigPictureStyle extends Style {
    private Bitmap mBigLargeIcon;
    
    private boolean mBigLargeIconSet;
    
    private Bitmap mPicture;
    
    public BigPictureStyle() {}
    
    public BigPictureStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      NotificationCompatJellybean.addBigPictureStyle(param1NotificationBuilderWithBuilderAccessor, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mPicture, this.mBigLargeIcon, this.mBigLargeIconSet);
    }
    
    public BigPictureStyle bigLargeIcon(Bitmap param1Bitmap) {
      this.mBigLargeIcon = param1Bitmap;
      this.mBigLargeIconSet = true;
      return this;
    }
    
    public BigPictureStyle bigPicture(Bitmap param1Bitmap) {
      this.mPicture = param1Bitmap;
      return this;
    }
    
    public BigPictureStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigPictureStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class BigTextStyle extends Style {
    private CharSequence mBigText;
    
    public BigTextStyle() {}
    
    public BigTextStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      NotificationCompatJellybean.addBigTextStyle(param1NotificationBuilderWithBuilderAccessor, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mBigText);
    }
    
    public BigTextStyle bigText(CharSequence param1CharSequence) {
      this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigTextStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public BigTextStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class Builder {
    private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ArrayList<NotificationCompat.Action> mActions = new ArrayList<NotificationCompat.Action>();
    
    int mBadgeIcon = 0;
    
    RemoteViews mBigContentView;
    
    String mCategory;
    
    String mChannelId;
    
    int mColor = 0;
    
    boolean mColorized;
    
    boolean mColorizedSet;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mContentInfo;
    
    PendingIntent mContentIntent;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mContentText;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mContentTitle;
    
    RemoteViews mContentView;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Context mContext;
    
    Bundle mExtras;
    
    PendingIntent mFullScreenIntent;
    
    private int mGroupAlertBehavior = 0;
    
    String mGroupKey;
    
    boolean mGroupSummary;
    
    RemoteViews mHeadsUpContentView;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bitmap mLargeIcon;
    
    boolean mLocalOnly = false;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Notification mNotification;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int mNumber;
    
    public ArrayList<String> mPeople;
    
    int mPriority;
    
    int mProgress;
    
    boolean mProgressIndeterminate;
    
    int mProgressMax;
    
    Notification mPublicVersion;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence[] mRemoteInputHistory;
    
    String mShortcutId;
    
    boolean mShowWhen = true;
    
    String mSortKey;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public NotificationCompat.Style mStyle;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mSubText;
    
    RemoteViews mTickerView;
    
    long mTimeout;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mUseChronometer;
    
    int mVisibility = 0;
    
    @Deprecated
    public Builder(Context param1Context) {
      this(param1Context, null);
    }
    
    public Builder(@NonNull Context param1Context, @NonNull String param1String) {
      Notification notification = new Notification();
      this.mNotification = notification;
      this.mContext = param1Context;
      this.mChannelId = param1String;
      notification.when = System.currentTimeMillis();
      this.mNotification.audioStreamType = -1;
      this.mPriority = 0;
      this.mPeople = new ArrayList<String>();
    }
    
    protected static CharSequence limitCharSequenceLength(CharSequence param1CharSequence) {
      if (param1CharSequence == null)
        return param1CharSequence; 
      CharSequence charSequence = param1CharSequence;
      if (param1CharSequence.length() > 5120)
        charSequence = param1CharSequence.subSequence(0, 5120); 
      return charSequence;
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        Notification notification = this.mNotification;
        notification.flags = param1Int | notification.flags;
      } else {
        Notification notification = this.mNotification;
        notification.flags = (param1Int ^ 0xFFFFFFFF) & notification.flags;
      } 
    }
    
    public Builder addAction(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this.mActions.add(new NotificationCompat.Action(param1Int, param1CharSequence, param1PendingIntent));
      return this;
    }
    
    public Builder addAction(NotificationCompat.Action param1Action) {
      this.mActions.add(param1Action);
      return this;
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null) {
        Bundle bundle = this.mExtras;
        if (bundle == null) {
          this.mExtras = new Bundle(param1Bundle);
        } else {
          bundle.putAll(param1Bundle);
        } 
      } 
      return this;
    }
    
    public Builder addPerson(String param1String) {
      this.mPeople.add(param1String);
      return this;
    }
    
    public Notification build() {
      return NotificationCompat.IMPL.build(this, getExtender());
    }
    
    public Builder extend(NotificationCompat.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getBigContentView() {
      return this.mBigContentView;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getColor() {
      return this.mColor;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getContentView() {
      return this.mContentView;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected NotificationCompat.BuilderExtender getExtender() {
      return new NotificationCompat.BuilderExtender();
    }
    
    public Bundle getExtras() {
      if (this.mExtras == null)
        this.mExtras = new Bundle(); 
      return this.mExtras;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews getHeadsUpContentView() {
      return this.mHeadsUpContentView;
    }
    
    @Deprecated
    public Notification getNotification() {
      return build();
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getPriority() {
      return this.mPriority;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getWhenIfShowing() {
      long l;
      if (this.mShowWhen) {
        l = this.mNotification.when;
      } else {
        l = 0L;
      } 
      return l;
    }
    
    public Builder setAutoCancel(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public Builder setBadgeIconType(int param1Int) {
      this.mBadgeIcon = param1Int;
      return this;
    }
    
    public Builder setCategory(String param1String) {
      this.mCategory = param1String;
      return this;
    }
    
    public Builder setChannelId(@NonNull String param1String) {
      this.mChannelId = param1String;
      return this;
    }
    
    public Builder setColor(@ColorInt int param1Int) {
      this.mColor = param1Int;
      return this;
    }
    
    public Builder setColorized(boolean param1Boolean) {
      this.mColorized = param1Boolean;
      this.mColorizedSet = true;
      return this;
    }
    
    public Builder setContent(RemoteViews param1RemoteViews) {
      this.mNotification.contentView = param1RemoteViews;
      return this;
    }
    
    public Builder setContentInfo(CharSequence param1CharSequence) {
      this.mContentInfo = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setContentIntent(PendingIntent param1PendingIntent) {
      this.mContentIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setContentText(CharSequence param1CharSequence) {
      this.mContentText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setContentTitle(CharSequence param1CharSequence) {
      this.mContentTitle = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setCustomBigContentView(RemoteViews param1RemoteViews) {
      this.mBigContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomContentView(RemoteViews param1RemoteViews) {
      this.mContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomHeadsUpContentView(RemoteViews param1RemoteViews) {
      this.mHeadsUpContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setDefaults(int param1Int) {
      Notification notification = this.mNotification;
      notification.defaults = param1Int;
      if ((param1Int & 0x4) != 0)
        notification.flags |= 0x1; 
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent param1PendingIntent) {
      this.mNotification.deleteIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setFullScreenIntent(PendingIntent param1PendingIntent, boolean param1Boolean) {
      this.mFullScreenIntent = param1PendingIntent;
      setFlag(128, param1Boolean);
      return this;
    }
    
    public Builder setGroup(String param1String) {
      this.mGroupKey = param1String;
      return this;
    }
    
    public Builder setGroupAlertBehavior(int param1Int) {
      this.mGroupAlertBehavior = param1Int;
      return this;
    }
    
    public Builder setGroupSummary(boolean param1Boolean) {
      this.mGroupSummary = param1Boolean;
      return this;
    }
    
    public Builder setLargeIcon(Bitmap param1Bitmap) {
      this.mLargeIcon = param1Bitmap;
      return this;
    }
    
    public Builder setLights(@ColorInt int param1Int1, int param1Int2, int param1Int3) {
      Notification notification = this.mNotification;
      notification.ledARGB = param1Int1;
      notification.ledOnMS = param1Int2;
      notification.ledOffMS = param1Int3;
      if (param1Int2 != 0 && param1Int3 != 0) {
        param1Int1 = 1;
      } else {
        param1Int1 = 0;
      } 
      notification.flags = param1Int1 | notification.flags & 0xFFFFFFFE;
      return this;
    }
    
    public Builder setLocalOnly(boolean param1Boolean) {
      this.mLocalOnly = param1Boolean;
      return this;
    }
    
    public Builder setNumber(int param1Int) {
      this.mNumber = param1Int;
      return this;
    }
    
    public Builder setOngoing(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    public Builder setOnlyAlertOnce(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
    
    public Builder setPriority(int param1Int) {
      this.mPriority = param1Int;
      return this;
    }
    
    public Builder setProgress(int param1Int1, int param1Int2, boolean param1Boolean) {
      this.mProgressMax = param1Int1;
      this.mProgress = param1Int2;
      this.mProgressIndeterminate = param1Boolean;
      return this;
    }
    
    public Builder setPublicVersion(Notification param1Notification) {
      this.mPublicVersion = param1Notification;
      return this;
    }
    
    public Builder setRemoteInputHistory(CharSequence[] param1ArrayOfCharSequence) {
      this.mRemoteInputHistory = param1ArrayOfCharSequence;
      return this;
    }
    
    public Builder setShortcutId(String param1String) {
      this.mShortcutId = param1String;
      return this;
    }
    
    public Builder setShowWhen(boolean param1Boolean) {
      this.mShowWhen = param1Boolean;
      return this;
    }
    
    public Builder setSmallIcon(int param1Int) {
      this.mNotification.icon = param1Int;
      return this;
    }
    
    public Builder setSmallIcon(int param1Int1, int param1Int2) {
      Notification notification = this.mNotification;
      notification.icon = param1Int1;
      notification.iconLevel = param1Int2;
      return this;
    }
    
    public Builder setSortKey(String param1String) {
      this.mSortKey = param1String;
      return this;
    }
    
    public Builder setSound(Uri param1Uri) {
      Notification notification = this.mNotification;
      notification.sound = param1Uri;
      notification.audioStreamType = -1;
      return this;
    }
    
    public Builder setSound(Uri param1Uri, int param1Int) {
      Notification notification = this.mNotification;
      notification.sound = param1Uri;
      notification.audioStreamType = param1Int;
      return this;
    }
    
    public Builder setStyle(NotificationCompat.Style param1Style) {
      if (this.mStyle != param1Style) {
        this.mStyle = param1Style;
        if (param1Style != null)
          param1Style.setBuilder(this); 
      } 
      return this;
    }
    
    public Builder setSubText(CharSequence param1CharSequence) {
      this.mSubText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence param1CharSequence) {
      this.mNotification.tickerText = limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public Builder setTicker(CharSequence param1CharSequence, RemoteViews param1RemoteViews) {
      this.mNotification.tickerText = limitCharSequenceLength(param1CharSequence);
      this.mTickerView = param1RemoteViews;
      return this;
    }
    
    public Builder setTimeoutAfter(long param1Long) {
      this.mTimeout = param1Long;
      return this;
    }
    
    public Builder setUsesChronometer(boolean param1Boolean) {
      this.mUseChronometer = param1Boolean;
      return this;
    }
    
    public Builder setVibrate(long[] param1ArrayOflong) {
      this.mNotification.vibrate = param1ArrayOflong;
      return this;
    }
    
    public Builder setVisibility(int param1Int) {
      this.mVisibility = param1Int;
      return this;
    }
    
    public Builder setWhen(long param1Long) {
      this.mNotification.when = param1Long;
      return this;
    }
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected static class BuilderExtender {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      NotificationCompat.Style style = param1Builder.mStyle;
      if (style != null) {
        RemoteViews remoteViews = style.makeContentView(param1NotificationBuilderWithBuilderAccessor);
      } else {
        style = null;
      } 
      Notification notification = param1NotificationBuilderWithBuilderAccessor.build();
      if (style != null) {
        notification.contentView = (RemoteViews)style;
      } else {
        RemoteViews remoteViews = param1Builder.mContentView;
        if (remoteViews != null)
          notification.contentView = remoteViews; 
      } 
      int i = Build.VERSION.SDK_INT;
      style = param1Builder.mStyle;
      if (style != null) {
        RemoteViews remoteViews = style.makeBigContentView(param1NotificationBuilderWithBuilderAccessor);
        if (remoteViews != null)
          notification.bigContentView = remoteViews; 
      } 
      if (i >= 21) {
        NotificationCompat.Style style1 = param1Builder.mStyle;
        if (style1 != null) {
          RemoteViews remoteViews = style1.makeHeadsUpContentView(param1NotificationBuilderWithBuilderAccessor);
          if (remoteViews != null)
            notification.headsUpContentView = remoteViews; 
        } 
      } 
      return notification;
    }
  }
  
  public static final class CarExtender implements Extender {
    private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    
    private static final String EXTRA_COLOR = "app_color";
    
    private static final String EXTRA_CONVERSATION = "car_conversation";
    
    private static final String EXTRA_LARGE_ICON = "large_icon";
    
    private static final String TAG = "CarExtender";
    
    private int mColor;
    
    private Bitmap mLargeIcon;
    
    private UnreadConversation mUnreadConversation;
    
    public CarExtender() {
      this.mColor = 0;
    }
    
    public CarExtender(Notification param1Notification) {
      Bundle bundle;
      this.mColor = 0;
      if (Build.VERSION.SDK_INT < 21)
        return; 
      if (NotificationCompat.getExtras(param1Notification) == null) {
        param1Notification = null;
      } else {
        bundle = NotificationCompat.getExtras(param1Notification).getBundle("android.car.EXTENSIONS");
      } 
      if (bundle != null) {
        this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
        this.mColor = bundle.getInt("app_color", 0);
        bundle = bundle.getBundle("car_conversation");
        this.mUnreadConversation = (UnreadConversation)NotificationCompat.IMPL.getUnreadConversationFromBundle(bundle, UnreadConversation.FACTORY, RemoteInput.FACTORY);
      } 
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder) {
      if (Build.VERSION.SDK_INT < 21)
        return param1Builder; 
      Bundle bundle = new Bundle();
      Bitmap bitmap = this.mLargeIcon;
      if (bitmap != null)
        bundle.putParcelable("large_icon", (Parcelable)bitmap); 
      int i = this.mColor;
      if (i != 0)
        bundle.putInt("app_color", i); 
      UnreadConversation unreadConversation = this.mUnreadConversation;
      if (unreadConversation != null)
        bundle.putBundle("car_conversation", NotificationCompat.IMPL.getBundleForUnreadConversation(unreadConversation)); 
      param1Builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    @ColorInt
    public int getColor() {
      return this.mColor;
    }
    
    public Bitmap getLargeIcon() {
      return this.mLargeIcon;
    }
    
    public UnreadConversation getUnreadConversation() {
      return this.mUnreadConversation;
    }
    
    public CarExtender setColor(@ColorInt int param1Int) {
      this.mColor = param1Int;
      return this;
    }
    
    public CarExtender setLargeIcon(Bitmap param1Bitmap) {
      this.mLargeIcon = param1Bitmap;
      return this;
    }
    
    public CarExtender setUnreadConversation(UnreadConversation param1UnreadConversation) {
      this.mUnreadConversation = param1UnreadConversation;
      return this;
    }
    
    public static class UnreadConversation extends NotificationCompatBase.UnreadConversation {
      static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompatBase.UnreadConversation.Factory() {
          public NotificationCompat.CarExtender.UnreadConversation build(String[] param3ArrayOfString1, RemoteInputCompatBase.RemoteInput param3RemoteInput, PendingIntent param3PendingIntent1, PendingIntent param3PendingIntent2, String[] param3ArrayOfString2, long param3Long) {
            return new NotificationCompat.CarExtender.UnreadConversation(param3ArrayOfString1, (RemoteInput)param3RemoteInput, param3PendingIntent1, param3PendingIntent2, param3ArrayOfString2, param3Long);
          }
        };
      
      private final long mLatestTimestamp;
      
      private final String[] mMessages;
      
      private final String[] mParticipants;
      
      private final PendingIntent mReadPendingIntent;
      
      private final RemoteInput mRemoteInput;
      
      private final PendingIntent mReplyPendingIntent;
      
      UnreadConversation(String[] param2ArrayOfString1, RemoteInput param2RemoteInput, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String[] param2ArrayOfString2, long param2Long) {
        this.mMessages = param2ArrayOfString1;
        this.mRemoteInput = param2RemoteInput;
        this.mReadPendingIntent = param2PendingIntent2;
        this.mReplyPendingIntent = param2PendingIntent1;
        this.mParticipants = param2ArrayOfString2;
        this.mLatestTimestamp = param2Long;
      }
      
      public long getLatestTimestamp() {
        return this.mLatestTimestamp;
      }
      
      public String[] getMessages() {
        return this.mMessages;
      }
      
      public String getParticipant() {
        String[] arrayOfString = this.mParticipants;
        if (arrayOfString.length > 0) {
          String str = arrayOfString[0];
        } else {
          arrayOfString = null;
        } 
        return (String)arrayOfString;
      }
      
      public String[] getParticipants() {
        return this.mParticipants;
      }
      
      public PendingIntent getReadPendingIntent() {
        return this.mReadPendingIntent;
      }
      
      public RemoteInput getRemoteInput() {
        return this.mRemoteInput;
      }
      
      public PendingIntent getReplyPendingIntent() {
        return this.mReplyPendingIntent;
      }
      
      public static class Builder {
        private long mLatestTimestamp;
        
        private final List<String> mMessages = new ArrayList<String>();
        
        private final String mParticipant;
        
        private PendingIntent mReadPendingIntent;
        
        private RemoteInput mRemoteInput;
        
        private PendingIntent mReplyPendingIntent;
        
        public Builder(String param3String) {
          this.mParticipant = param3String;
        }
        
        public Builder addMessage(String param3String) {
          this.mMessages.add(param3String);
          return this;
        }
        
        public NotificationCompat.CarExtender.UnreadConversation build() {
          List<String> list = this.mMessages;
          String[] arrayOfString = list.<String>toArray(new String[list.size()]);
          String str = this.mParticipant;
          RemoteInput remoteInput = this.mRemoteInput;
          PendingIntent pendingIntent1 = this.mReplyPendingIntent;
          PendingIntent pendingIntent2 = this.mReadPendingIntent;
          long l = this.mLatestTimestamp;
          return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
        }
        
        public Builder setLatestTimestamp(long param3Long) {
          this.mLatestTimestamp = param3Long;
          return this;
        }
        
        public Builder setReadPendingIntent(PendingIntent param3PendingIntent) {
          this.mReadPendingIntent = param3PendingIntent;
          return this;
        }
        
        public Builder setReplyAction(PendingIntent param3PendingIntent, RemoteInput param3RemoteInput) {
          this.mRemoteInput = param3RemoteInput;
          this.mReplyPendingIntent = param3PendingIntent;
          return this;
        }
      }
    }
    
    static final class null implements NotificationCompatBase.UnreadConversation.Factory {
      public NotificationCompat.CarExtender.UnreadConversation build(String[] param2ArrayOfString1, RemoteInputCompatBase.RemoteInput param2RemoteInput, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String[] param2ArrayOfString2, long param2Long) {
        return new NotificationCompat.CarExtender.UnreadConversation(param2ArrayOfString1, (RemoteInput)param2RemoteInput, param2PendingIntent1, param2PendingIntent2, param2ArrayOfString2, param2Long);
      }
    }
    
    public static class Builder {
      private long mLatestTimestamp;
      
      private final List<String> mMessages = new ArrayList<String>();
      
      private final String mParticipant;
      
      private PendingIntent mReadPendingIntent;
      
      private RemoteInput mRemoteInput;
      
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String param2String) {
        this.mParticipant = param2String;
      }
      
      public Builder addMessage(String param2String) {
        this.mMessages.add(param2String);
        return this;
      }
      
      public NotificationCompat.CarExtender.UnreadConversation build() {
        List<String> list = this.mMessages;
        String[] arrayOfString = list.<String>toArray(new String[list.size()]);
        String str = this.mParticipant;
        RemoteInput remoteInput = this.mRemoteInput;
        PendingIntent pendingIntent1 = this.mReplyPendingIntent;
        PendingIntent pendingIntent2 = this.mReadPendingIntent;
        long l = this.mLatestTimestamp;
        return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long param2Long) {
        this.mLatestTimestamp = param2Long;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent param2PendingIntent) {
        this.mReadPendingIntent = param2PendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent param2PendingIntent, RemoteInput param2RemoteInput) {
        this.mRemoteInput = param2RemoteInput;
        this.mReplyPendingIntent = param2PendingIntent;
        return this;
      }
    }
  }
  
  public static class UnreadConversation extends NotificationCompatBase.UnreadConversation {
    static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompatBase.UnreadConversation.Factory() {
        public NotificationCompat.CarExtender.UnreadConversation build(String[] param3ArrayOfString1, RemoteInputCompatBase.RemoteInput param3RemoteInput, PendingIntent param3PendingIntent1, PendingIntent param3PendingIntent2, String[] param3ArrayOfString2, long param3Long) {
          return new NotificationCompat.CarExtender.UnreadConversation(param3ArrayOfString1, (RemoteInput)param3RemoteInput, param3PendingIntent1, param3PendingIntent2, param3ArrayOfString2, param3Long);
        }
      };
    
    private final long mLatestTimestamp;
    
    private final String[] mMessages;
    
    private final String[] mParticipants;
    
    private final PendingIntent mReadPendingIntent;
    
    private final RemoteInput mRemoteInput;
    
    private final PendingIntent mReplyPendingIntent;
    
    UnreadConversation(String[] param1ArrayOfString1, RemoteInput param1RemoteInput, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, String[] param1ArrayOfString2, long param1Long) {
      this.mMessages = param1ArrayOfString1;
      this.mRemoteInput = param1RemoteInput;
      this.mReadPendingIntent = param1PendingIntent2;
      this.mReplyPendingIntent = param1PendingIntent1;
      this.mParticipants = param1ArrayOfString2;
      this.mLatestTimestamp = param1Long;
    }
    
    public long getLatestTimestamp() {
      return this.mLatestTimestamp;
    }
    
    public String[] getMessages() {
      return this.mMessages;
    }
    
    public String getParticipant() {
      String[] arrayOfString = this.mParticipants;
      if (arrayOfString.length > 0) {
        String str = arrayOfString[0];
      } else {
        arrayOfString = null;
      } 
      return (String)arrayOfString;
    }
    
    public String[] getParticipants() {
      return this.mParticipants;
    }
    
    public PendingIntent getReadPendingIntent() {
      return this.mReadPendingIntent;
    }
    
    public RemoteInput getRemoteInput() {
      return this.mRemoteInput;
    }
    
    public PendingIntent getReplyPendingIntent() {
      return this.mReplyPendingIntent;
    }
    
    public static class Builder {
      private long mLatestTimestamp;
      
      private final List<String> mMessages = new ArrayList<String>();
      
      private final String mParticipant;
      
      private PendingIntent mReadPendingIntent;
      
      private RemoteInput mRemoteInput;
      
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String param3String) {
        this.mParticipant = param3String;
      }
      
      public Builder addMessage(String param3String) {
        this.mMessages.add(param3String);
        return this;
      }
      
      public NotificationCompat.CarExtender.UnreadConversation build() {
        List<String> list = this.mMessages;
        String[] arrayOfString = list.<String>toArray(new String[list.size()]);
        String str = this.mParticipant;
        RemoteInput remoteInput = this.mRemoteInput;
        PendingIntent pendingIntent1 = this.mReplyPendingIntent;
        PendingIntent pendingIntent2 = this.mReadPendingIntent;
        long l = this.mLatestTimestamp;
        return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long param3Long) {
        this.mLatestTimestamp = param3Long;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent param3PendingIntent) {
        this.mReadPendingIntent = param3PendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent param3PendingIntent, RemoteInput param3RemoteInput) {
        this.mRemoteInput = param3RemoteInput;
        this.mReplyPendingIntent = param3PendingIntent;
        return this;
      }
    }
  }
  
  static final class null implements NotificationCompatBase.UnreadConversation.Factory {
    public NotificationCompat.CarExtender.UnreadConversation build(String[] param1ArrayOfString1, RemoteInputCompatBase.RemoteInput param1RemoteInput, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, String[] param1ArrayOfString2, long param1Long) {
      return new NotificationCompat.CarExtender.UnreadConversation(param1ArrayOfString1, (RemoteInput)param1RemoteInput, param1PendingIntent1, param1PendingIntent2, param1ArrayOfString2, param1Long);
    }
  }
  
  public static class Builder {
    private long mLatestTimestamp;
    
    private final List<String> mMessages = new ArrayList<String>();
    
    private final String mParticipant;
    
    private PendingIntent mReadPendingIntent;
    
    private RemoteInput mRemoteInput;
    
    private PendingIntent mReplyPendingIntent;
    
    public Builder(String param1String) {
      this.mParticipant = param1String;
    }
    
    public Builder addMessage(String param1String) {
      this.mMessages.add(param1String);
      return this;
    }
    
    public NotificationCompat.CarExtender.UnreadConversation build() {
      List<String> list = this.mMessages;
      String[] arrayOfString = list.<String>toArray(new String[list.size()]);
      String str = this.mParticipant;
      RemoteInput remoteInput = this.mRemoteInput;
      PendingIntent pendingIntent1 = this.mReplyPendingIntent;
      PendingIntent pendingIntent2 = this.mReadPendingIntent;
      long l = this.mLatestTimestamp;
      return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
    }
    
    public Builder setLatestTimestamp(long param1Long) {
      this.mLatestTimestamp = param1Long;
      return this;
    }
    
    public Builder setReadPendingIntent(PendingIntent param1PendingIntent) {
      this.mReadPendingIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setReplyAction(PendingIntent param1PendingIntent, RemoteInput param1RemoteInput) {
      this.mRemoteInput = param1RemoteInput;
      this.mReplyPendingIntent = param1PendingIntent;
      return this;
    }
  }
  
  public static class DecoratedCustomViewStyle extends Style {
    private static final int MAX_ACTION_BUTTONS = 3;
    
    private RemoteViews createRemoteViews(RemoteViews param1RemoteViews, boolean param1Boolean) {
      // Byte code:
      //   0: getstatic android/support/compat/R$layout.notification_template_custom_big : I
      //   3: istore_3
      //   4: iconst_1
      //   5: istore #4
      //   7: iconst_0
      //   8: istore #5
      //   10: aload_0
      //   11: iconst_1
      //   12: iload_3
      //   13: iconst_0
      //   14: invokevirtual applyStandardTemplate : (ZIZ)Landroid/widget/RemoteViews;
      //   17: astore #6
      //   19: aload #6
      //   21: getstatic android/support/compat/R$id.actions : I
      //   24: invokevirtual removeAllViews : (I)V
      //   27: iload_2
      //   28: ifeq -> 111
      //   31: aload_0
      //   32: getfield mBuilder : Landroid/support/v4/app/NotificationCompat$Builder;
      //   35: getfield mActions : Ljava/util/ArrayList;
      //   38: astore #7
      //   40: aload #7
      //   42: ifnull -> 111
      //   45: aload #7
      //   47: invokevirtual size : ()I
      //   50: iconst_3
      //   51: invokestatic min : (II)I
      //   54: istore #8
      //   56: iload #8
      //   58: ifle -> 111
      //   61: iconst_0
      //   62: istore #9
      //   64: iload #4
      //   66: istore_3
      //   67: iload #9
      //   69: iload #8
      //   71: if_icmpge -> 113
      //   74: aload_0
      //   75: aload_0
      //   76: getfield mBuilder : Landroid/support/v4/app/NotificationCompat$Builder;
      //   79: getfield mActions : Ljava/util/ArrayList;
      //   82: iload #9
      //   84: invokevirtual get : (I)Ljava/lang/Object;
      //   87: checkcast android/support/v4/app/NotificationCompat$Action
      //   90: invokespecial generateActionButton : (Landroid/support/v4/app/NotificationCompat$Action;)Landroid/widget/RemoteViews;
      //   93: astore #7
      //   95: aload #6
      //   97: getstatic android/support/compat/R$id.actions : I
      //   100: aload #7
      //   102: invokevirtual addView : (ILandroid/widget/RemoteViews;)V
      //   105: iinc #9, 1
      //   108: goto -> 64
      //   111: iconst_0
      //   112: istore_3
      //   113: iload_3
      //   114: ifeq -> 123
      //   117: iload #5
      //   119: istore_3
      //   120: goto -> 126
      //   123: bipush #8
      //   125: istore_3
      //   126: aload #6
      //   128: getstatic android/support/compat/R$id.actions : I
      //   131: iload_3
      //   132: invokevirtual setViewVisibility : (II)V
      //   135: aload #6
      //   137: getstatic android/support/compat/R$id.action_divider : I
      //   140: iload_3
      //   141: invokevirtual setViewVisibility : (II)V
      //   144: aload_0
      //   145: aload #6
      //   147: aload_1
      //   148: invokevirtual buildIntoRemoteViews : (Landroid/widget/RemoteViews;Landroid/widget/RemoteViews;)V
      //   151: aload #6
      //   153: areturn
    }
    
    private RemoteViews generateActionButton(NotificationCompat.Action param1Action) {
      boolean bool;
      int i;
      if (param1Action.actionIntent == null) {
        bool = true;
      } else {
        bool = false;
      } 
      String str = this.mBuilder.mContext.getPackageName();
      if (bool) {
        i = R.layout.notification_action_tombstone;
      } else {
        i = R.layout.notification_action;
      } 
      RemoteViews remoteViews = new RemoteViews(str, i);
      remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(param1Action.getIcon(), this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
      remoteViews.setTextViewText(R.id.action_text, param1Action.title);
      if (!bool)
        remoteViews.setOnClickPendingIntent(R.id.action_container, param1Action.actionIntent); 
      remoteViews.setContentDescription(R.id.action_container, param1Action.title);
      return remoteViews;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 24)
        param1NotificationBuilderWithBuilderAccessor.getBuilder().setStyle((Notification.Style)new Notification.DecoratedCustomViewStyle()); 
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 24)
        return null; 
      RemoteViews remoteViews = this.mBuilder.getBigContentView();
      if (remoteViews == null)
        remoteViews = this.mBuilder.getContentView(); 
      return (remoteViews == null) ? null : createRemoteViews(remoteViews, true);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return (Build.VERSION.SDK_INT >= 24) ? null : ((this.mBuilder.getContentView() == null) ? null : createRemoteViews(this.mBuilder.getContentView(), false));
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      RemoteViews remoteViews1;
      if (Build.VERSION.SDK_INT >= 24)
        return null; 
      RemoteViews remoteViews2 = this.mBuilder.getHeadsUpContentView();
      if (remoteViews2 != null) {
        remoteViews1 = remoteViews2;
      } else {
        remoteViews1 = this.mBuilder.getContentView();
      } 
      return (remoteViews2 == null) ? null : createRemoteViews(remoteViews1, true);
    }
  }
  
  public static interface Extender {
    NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder);
  }
  
  public static class InboxStyle extends Style {
    private ArrayList<CharSequence> mTexts = new ArrayList<CharSequence>();
    
    public InboxStyle() {}
    
    public InboxStyle(NotificationCompat.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    public InboxStyle addLine(CharSequence param1CharSequence) {
      this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence));
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      NotificationCompatJellybean.addInboxStyle(param1NotificationBuilderWithBuilderAccessor, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mTexts);
    }
    
    public InboxStyle setBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      return this;
    }
    
    public InboxStyle setSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(param1CharSequence);
      this.mSummaryTextSet = true;
      return this;
    }
  }
  
  public static class MessagingStyle extends Style {
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    
    CharSequence mConversationTitle;
    
    List<Message> mMessages = new ArrayList<Message>();
    
    CharSequence mUserDisplayName;
    
    MessagingStyle() {}
    
    public MessagingStyle(@NonNull CharSequence param1CharSequence) {
      this.mUserDisplayName = param1CharSequence;
    }
    
    public static MessagingStyle extractMessagingStyleFromNotification(Notification param1Notification) {
      MessagingStyle messagingStyle;
      Bundle bundle = NotificationCompat.getExtras(param1Notification);
      param1Notification = null;
      if (bundle == null || bundle.containsKey("android.selfDisplayName"))
        try {
          MessagingStyle messagingStyle1 = new MessagingStyle();
          this();
          messagingStyle1.restoreFromCompatExtras(bundle);
          messagingStyle = messagingStyle1;
        } catch (ClassCastException classCastException) {} 
      return messagingStyle;
    }
    
    @Nullable
    private Message findLatestIncomingMessage() {
      for (int i = this.mMessages.size() - 1; i >= 0; i--) {
        Message message = this.mMessages.get(i);
        if (!TextUtils.isEmpty(message.getSender()))
          return message; 
      } 
      if (!this.mMessages.isEmpty()) {
        List<Message> list = this.mMessages;
        return list.get(list.size() - 1);
      } 
      return null;
    }
    
    private boolean hasMessagesWithoutSender() {
      for (int i = this.mMessages.size() - 1; i >= 0; i--) {
        if (((Message)this.mMessages.get(i)).getSender() == null)
          return true; 
      } 
      return false;
    }
    
    @NonNull
    private TextAppearanceSpan makeFontColorSpan(int param1Int) {
      return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(param1Int), null);
    }
    
    private CharSequence makeMessageLine(Message param1Message) {
      CharSequence charSequence1;
      boolean bool;
      byte b;
      BidiFormatter bidiFormatter = BidiFormatter.getInstance();
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
      if (Build.VERSION.SDK_INT >= 21) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        b = -16777216;
      } else {
        b = -1;
      } 
      CharSequence charSequence2 = param1Message.getSender();
      boolean bool1 = TextUtils.isEmpty(param1Message.getSender());
      String str = "";
      int i = b;
      if (bool1) {
        charSequence2 = this.mUserDisplayName;
        CharSequence charSequence = charSequence2;
        if (charSequence2 == null)
          charSequence = ""; 
        i = b;
        charSequence2 = charSequence;
        if (bool) {
          i = b;
          charSequence2 = charSequence;
          if (this.mBuilder.getColor() != 0) {
            i = this.mBuilder.getColor();
            charSequence2 = charSequence;
          } 
        } 
      } 
      CharSequence charSequence3 = bidiFormatter.unicodeWrap(charSequence2);
      spannableStringBuilder.append(charSequence3);
      spannableStringBuilder.setSpan(makeFontColorSpan(i), spannableStringBuilder.length() - charSequence3.length(), spannableStringBuilder.length(), 33);
      if (param1Message.getText() == null) {
        charSequence1 = str;
      } else {
        charSequence1 = charSequence1.getText();
      } 
      spannableStringBuilder.append("  ").append(bidiFormatter.unicodeWrap(charSequence1));
      return (CharSequence)spannableStringBuilder;
    }
    
    public void addCompatExtras(Bundle param1Bundle) {
      super.addCompatExtras(param1Bundle);
      CharSequence charSequence = this.mUserDisplayName;
      if (charSequence != null)
        param1Bundle.putCharSequence("android.selfDisplayName", charSequence); 
      charSequence = this.mConversationTitle;
      if (charSequence != null)
        param1Bundle.putCharSequence("android.conversationTitle", charSequence); 
      if (!this.mMessages.isEmpty())
        param1Bundle.putParcelableArray("android.messages", (Parcelable[])Message.getBundleArrayForMessages(this.mMessages)); 
    }
    
    public MessagingStyle addMessage(Message param1Message) {
      this.mMessages.add(param1Message);
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0); 
      return this;
    }
    
    public MessagingStyle addMessage(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      this.mMessages.add(new Message(param1CharSequence1, param1Long, param1CharSequence2));
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0); 
      return this;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      if (Build.VERSION.SDK_INT >= 24) {
        ArrayList<CharSequence> arrayList1 = new ArrayList();
        ArrayList<Long> arrayList = new ArrayList();
        ArrayList<CharSequence> arrayList2 = new ArrayList();
        ArrayList<String> arrayList3 = new ArrayList();
        ArrayList<Uri> arrayList4 = new ArrayList();
        for (Message message : this.mMessages) {
          arrayList1.add(message.getText());
          arrayList.add(Long.valueOf(message.getTimestamp()));
          arrayList2.add(message.getSender());
          arrayList3.add(message.getDataMimeType());
          arrayList4.add(message.getDataUri());
        } 
        NotificationCompatApi24.addMessagingStyle(param1NotificationBuilderWithBuilderAccessor, this.mUserDisplayName, this.mConversationTitle, arrayList1, arrayList, arrayList2, arrayList3, arrayList4);
      } else {
        boolean bool;
        Message message = findLatestIncomingMessage();
        if (this.mConversationTitle != null) {
          param1NotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.mConversationTitle);
        } else if (message != null) {
          param1NotificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(message.getSender());
        } 
        if (message != null) {
          CharSequence charSequence;
          Notification.Builder builder = param1NotificationBuilderWithBuilderAccessor.getBuilder();
          if (this.mConversationTitle != null) {
            charSequence = makeMessageLine(message);
          } else {
            charSequence = charSequence.getText();
          } 
          builder.setContentText(charSequence);
        } 
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (this.mConversationTitle != null || hasMessagesWithoutSender()) {
          bool = true;
        } else {
          bool = false;
        } 
        for (int i = this.mMessages.size() - 1; i >= 0; i--) {
          CharSequence charSequence;
          message = this.mMessages.get(i);
          if (bool) {
            charSequence = makeMessageLine(message);
          } else {
            charSequence = charSequence.getText();
          } 
          if (i != this.mMessages.size() - 1)
            spannableStringBuilder.insert(0, "\n"); 
          spannableStringBuilder.insert(0, charSequence);
        } 
        NotificationCompatJellybean.addBigTextStyle(param1NotificationBuilderWithBuilderAccessor, null, false, null, (CharSequence)spannableStringBuilder);
      } 
    }
    
    public CharSequence getConversationTitle() {
      return this.mConversationTitle;
    }
    
    public List<Message> getMessages() {
      return this.mMessages;
    }
    
    public CharSequence getUserDisplayName() {
      return this.mUserDisplayName;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle param1Bundle) {
      this.mMessages.clear();
      this.mUserDisplayName = param1Bundle.getString("android.selfDisplayName");
      this.mConversationTitle = param1Bundle.getString("android.conversationTitle");
      Parcelable[] arrayOfParcelable = param1Bundle.getParcelableArray("android.messages");
      if (arrayOfParcelable != null)
        this.mMessages = Message.getMessagesFromBundleArray(arrayOfParcelable); 
    }
    
    public MessagingStyle setConversationTitle(CharSequence param1CharSequence) {
      this.mConversationTitle = param1CharSequence;
      return this;
    }
    
    public static final class Message {
      static final String KEY_DATA_MIME_TYPE = "type";
      
      static final String KEY_DATA_URI = "uri";
      
      static final String KEY_EXTRAS_BUNDLE = "extras";
      
      static final String KEY_SENDER = "sender";
      
      static final String KEY_TEXT = "text";
      
      static final String KEY_TIMESTAMP = "time";
      
      private String mDataMimeType;
      
      private Uri mDataUri;
      
      private Bundle mExtras = new Bundle();
      
      private final CharSequence mSender;
      
      private final CharSequence mText;
      
      private final long mTimestamp;
      
      public Message(CharSequence param2CharSequence1, long param2Long, CharSequence param2CharSequence2) {
        this.mText = param2CharSequence1;
        this.mTimestamp = param2Long;
        this.mSender = param2CharSequence2;
      }
      
      static Bundle[] getBundleArrayForMessages(List<Message> param2List) {
        Bundle[] arrayOfBundle = new Bundle[param2List.size()];
        int i = param2List.size();
        for (byte b = 0; b < i; b++)
          arrayOfBundle[b] = ((Message)param2List.get(b)).toBundle(); 
        return arrayOfBundle;
      }
      
      static Message getMessageFromBundle(Bundle param2Bundle) {
        try {
          if (param2Bundle.containsKey("text") && param2Bundle.containsKey("time")) {
            Message message = new Message();
            this(param2Bundle.getCharSequence("text"), param2Bundle.getLong("time"), param2Bundle.getCharSequence("sender"));
            if (param2Bundle.containsKey("type") && param2Bundle.containsKey("uri"))
              message.setData(param2Bundle.getString("type"), (Uri)param2Bundle.getParcelable("uri")); 
            if (param2Bundle.containsKey("extras"))
              message.getExtras().putAll(param2Bundle.getBundle("extras")); 
            return message;
          } 
        } catch (ClassCastException classCastException) {}
        return null;
      }
      
      static List<Message> getMessagesFromBundleArray(Parcelable[] param2ArrayOfParcelable) {
        ArrayList<Message> arrayList = new ArrayList(param2ArrayOfParcelable.length);
        for (byte b = 0; b < param2ArrayOfParcelable.length; b++) {
          Parcelable parcelable = param2ArrayOfParcelable[b];
          if (parcelable instanceof Bundle) {
            Message message = getMessageFromBundle((Bundle)parcelable);
            if (message != null)
              arrayList.add(message); 
          } 
        } 
        return arrayList;
      }
      
      private Bundle toBundle() {
        Bundle bundle1 = new Bundle();
        CharSequence charSequence = this.mText;
        if (charSequence != null)
          bundle1.putCharSequence("text", charSequence); 
        bundle1.putLong("time", this.mTimestamp);
        charSequence = this.mSender;
        if (charSequence != null)
          bundle1.putCharSequence("sender", charSequence); 
        charSequence = this.mDataMimeType;
        if (charSequence != null)
          bundle1.putString("type", (String)charSequence); 
        Uri uri = this.mDataUri;
        if (uri != null)
          bundle1.putParcelable("uri", (Parcelable)uri); 
        Bundle bundle2 = this.mExtras;
        if (bundle2 != null)
          bundle1.putBundle("extras", bundle2); 
        return bundle1;
      }
      
      public String getDataMimeType() {
        return this.mDataMimeType;
      }
      
      public Uri getDataUri() {
        return this.mDataUri;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public CharSequence getSender() {
        return this.mSender;
      }
      
      public CharSequence getText() {
        return this.mText;
      }
      
      public long getTimestamp() {
        return this.mTimestamp;
      }
      
      public Message setData(String param2String, Uri param2Uri) {
        this.mDataMimeType = param2String;
        this.mDataUri = param2Uri;
        return this;
      }
    }
  }
  
  public static final class Message {
    static final String KEY_DATA_MIME_TYPE = "type";
    
    static final String KEY_DATA_URI = "uri";
    
    static final String KEY_EXTRAS_BUNDLE = "extras";
    
    static final String KEY_SENDER = "sender";
    
    static final String KEY_TEXT = "text";
    
    static final String KEY_TIMESTAMP = "time";
    
    private String mDataMimeType;
    
    private Uri mDataUri;
    
    private Bundle mExtras = new Bundle();
    
    private final CharSequence mSender;
    
    private final CharSequence mText;
    
    private final long mTimestamp;
    
    public Message(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      this.mText = param1CharSequence1;
      this.mTimestamp = param1Long;
      this.mSender = param1CharSequence2;
    }
    
    static Bundle[] getBundleArrayForMessages(List<Message> param1List) {
      Bundle[] arrayOfBundle = new Bundle[param1List.size()];
      int i = param1List.size();
      for (byte b = 0; b < i; b++)
        arrayOfBundle[b] = ((Message)param1List.get(b)).toBundle(); 
      return arrayOfBundle;
    }
    
    static Message getMessageFromBundle(Bundle param1Bundle) {
      try {
        if (param1Bundle.containsKey("text") && param1Bundle.containsKey("time")) {
          Message message = new Message();
          this(param1Bundle.getCharSequence("text"), param1Bundle.getLong("time"), param1Bundle.getCharSequence("sender"));
          if (param1Bundle.containsKey("type") && param1Bundle.containsKey("uri"))
            message.setData(param1Bundle.getString("type"), (Uri)param1Bundle.getParcelable("uri")); 
          if (param1Bundle.containsKey("extras"))
            message.getExtras().putAll(param1Bundle.getBundle("extras")); 
          return message;
        } 
      } catch (ClassCastException classCastException) {}
      return null;
    }
    
    static List<Message> getMessagesFromBundleArray(Parcelable[] param1ArrayOfParcelable) {
      ArrayList<Message> arrayList = new ArrayList(param1ArrayOfParcelable.length);
      for (byte b = 0; b < param1ArrayOfParcelable.length; b++) {
        Parcelable parcelable = param1ArrayOfParcelable[b];
        if (parcelable instanceof Bundle) {
          Message message = getMessageFromBundle((Bundle)parcelable);
          if (message != null)
            arrayList.add(message); 
        } 
      } 
      return arrayList;
    }
    
    private Bundle toBundle() {
      Bundle bundle1 = new Bundle();
      CharSequence charSequence = this.mText;
      if (charSequence != null)
        bundle1.putCharSequence("text", charSequence); 
      bundle1.putLong("time", this.mTimestamp);
      charSequence = this.mSender;
      if (charSequence != null)
        bundle1.putCharSequence("sender", charSequence); 
      charSequence = this.mDataMimeType;
      if (charSequence != null)
        bundle1.putString("type", (String)charSequence); 
      Uri uri = this.mDataUri;
      if (uri != null)
        bundle1.putParcelable("uri", (Parcelable)uri); 
      Bundle bundle2 = this.mExtras;
      if (bundle2 != null)
        bundle1.putBundle("extras", bundle2); 
      return bundle1;
    }
    
    public String getDataMimeType() {
      return this.mDataMimeType;
    }
    
    public Uri getDataUri() {
      return this.mDataUri;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public CharSequence getSender() {
      return this.mSender;
    }
    
    public CharSequence getText() {
      return this.mText;
    }
    
    public long getTimestamp() {
      return this.mTimestamp;
    }
    
    public Message setData(String param1String, Uri param1Uri) {
      this.mDataMimeType = param1String;
      this.mDataUri = param1Uri;
      return this;
    }
  }
  
  @RequiresApi(16)
  static class NotificationCompatApi16Impl extends NotificationCompatBaseImpl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatJellybean.Builder builder = new NotificationCompatJellybean.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mExtras, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mContentView, param1Builder.mBigContentView);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style = param1Builder.mStyle;
      if (style != null)
        style.apply(builder); 
      Notification notification = param1BuilderExtender.build(param1Builder, builder);
      if (param1Builder.mStyle != null) {
        Bundle bundle = NotificationCompat.getExtras(notification);
        if (bundle != null)
          param1Builder.mStyle.addCompatExtras(bundle); 
      } 
      return notification;
    }
    
    public NotificationCompat.Action getAction(Notification param1Notification, int param1Int) {
      return (NotificationCompat.Action)NotificationCompatJellybean.getAction(param1Notification, param1Int, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> param1ArrayList) {
      return (NotificationCompat.Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(param1ArrayList, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] param1ArrayOfAction) {
      return NotificationCompatJellybean.getParcelableArrayListForActions((NotificationCompatBase.Action[])param1ArrayOfAction);
    }
  }
  
  @RequiresApi(19)
  static class NotificationCompatApi19Impl extends NotificationCompatApi16Impl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatKitKat.Builder builder = new NotificationCompatKitKat.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mShowWhen, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mPeople, param1Builder.mExtras, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mContentView, param1Builder.mBigContentView);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style = param1Builder.mStyle;
      if (style != null)
        style.apply(builder); 
      return param1BuilderExtender.build(param1Builder, builder);
    }
    
    public NotificationCompat.Action getAction(Notification param1Notification, int param1Int) {
      return (NotificationCompat.Action)NotificationCompatKitKat.getAction(param1Notification, param1Int, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
  }
  
  @RequiresApi(20)
  static class NotificationCompatApi20Impl extends NotificationCompatApi19Impl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatApi20.Builder builder = new NotificationCompatApi20.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mShowWhen, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mPeople, param1Builder.mExtras, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mContentView, param1Builder.mBigContentView, param1Builder.mGroupAlertBehavior);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style2 = param1Builder.mStyle;
      if (style2 != null)
        style2.apply(builder); 
      Notification notification = param1BuilderExtender.build(param1Builder, builder);
      NotificationCompat.Style style1 = param1Builder.mStyle;
      if (style1 != null)
        style1.addCompatExtras(NotificationCompat.getExtras(notification)); 
      return notification;
    }
    
    public NotificationCompat.Action getAction(Notification param1Notification, int param1Int) {
      return (NotificationCompat.Action)NotificationCompatApi20.getAction(param1Notification, param1Int, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> param1ArrayList) {
      return (NotificationCompat.Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(param1ArrayList, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] param1ArrayOfAction) {
      return NotificationCompatApi20.getParcelableArrayListForActions((NotificationCompatBase.Action[])param1ArrayOfAction);
    }
  }
  
  @RequiresApi(21)
  static class NotificationCompatApi21Impl extends NotificationCompatApi20Impl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatApi21.Builder builder = new NotificationCompatApi21.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mShowWhen, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mCategory, param1Builder.mPeople, param1Builder.mExtras, param1Builder.mColor, param1Builder.mVisibility, param1Builder.mPublicVersion, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mContentView, param1Builder.mBigContentView, param1Builder.mHeadsUpContentView, param1Builder.mGroupAlertBehavior);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style2 = param1Builder.mStyle;
      if (style2 != null)
        style2.apply(builder); 
      Notification notification = param1BuilderExtender.build(param1Builder, builder);
      NotificationCompat.Style style1 = param1Builder.mStyle;
      if (style1 != null)
        style1.addCompatExtras(NotificationCompat.getExtras(notification)); 
      return notification;
    }
    
    public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation param1UnreadConversation) {
      return NotificationCompatApi21.getBundleForUnreadConversation(param1UnreadConversation);
    }
    
    public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle param1Bundle, NotificationCompatBase.UnreadConversation.Factory param1Factory, RemoteInputCompatBase.RemoteInput.Factory param1Factory1) {
      return NotificationCompatApi21.getUnreadConversationFromBundle(param1Bundle, param1Factory, param1Factory1);
    }
  }
  
  @RequiresApi(24)
  static class NotificationCompatApi24Impl extends NotificationCompatApi21Impl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatApi24.Builder builder = new NotificationCompatApi24.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mShowWhen, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mCategory, param1Builder.mPeople, param1Builder.mExtras, param1Builder.mColor, param1Builder.mVisibility, param1Builder.mPublicVersion, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mRemoteInputHistory, param1Builder.mContentView, param1Builder.mBigContentView, param1Builder.mHeadsUpContentView, param1Builder.mGroupAlertBehavior);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style2 = param1Builder.mStyle;
      if (style2 != null)
        style2.apply(builder); 
      Notification notification = param1BuilderExtender.build(param1Builder, builder);
      NotificationCompat.Style style1 = param1Builder.mStyle;
      if (style1 != null)
        style1.addCompatExtras(NotificationCompat.getExtras(notification)); 
      return notification;
    }
    
    public NotificationCompat.Action getAction(Notification param1Notification, int param1Int) {
      return (NotificationCompat.Action)NotificationCompatApi24.getAction(param1Notification, param1Int, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> param1ArrayList) {
      return (NotificationCompat.Action[])NotificationCompatApi24.getActionsFromParcelableArrayList(param1ArrayList, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
    }
    
    public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] param1ArrayOfAction) {
      return NotificationCompatApi24.getParcelableArrayListForActions((NotificationCompatBase.Action[])param1ArrayOfAction);
    }
  }
  
  @RequiresApi(26)
  static class NotificationCompatApi26Impl extends NotificationCompatApi24Impl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      NotificationCompatApi26.Builder builder = new NotificationCompatApi26.Builder(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate, param1Builder.mShowWhen, param1Builder.mUseChronometer, param1Builder.mPriority, param1Builder.mSubText, param1Builder.mLocalOnly, param1Builder.mCategory, param1Builder.mPeople, param1Builder.mExtras, param1Builder.mColor, param1Builder.mVisibility, param1Builder.mPublicVersion, param1Builder.mGroupKey, param1Builder.mGroupSummary, param1Builder.mSortKey, param1Builder.mRemoteInputHistory, param1Builder.mContentView, param1Builder.mBigContentView, param1Builder.mHeadsUpContentView, param1Builder.mChannelId, param1Builder.mBadgeIcon, param1Builder.mShortcutId, param1Builder.mTimeout, param1Builder.mColorized, param1Builder.mColorizedSet, param1Builder.mGroupAlertBehavior);
      NotificationCompat.addActionsToBuilder(builder, param1Builder.mActions);
      NotificationCompat.Style style2 = param1Builder.mStyle;
      if (style2 != null)
        style2.apply(builder); 
      Notification notification = param1BuilderExtender.build(param1Builder, builder);
      NotificationCompat.Style style1 = param1Builder.mStyle;
      if (style1 != null)
        style1.addCompatExtras(NotificationCompat.getExtras(notification)); 
      return notification;
    }
  }
  
  static class NotificationCompatBaseImpl implements NotificationCompatImpl {
    public Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender) {
      return param1BuilderExtender.build(param1Builder, new BuilderBase(param1Builder.mContext, param1Builder.mNotification, param1Builder.mContentTitle, param1Builder.mContentText, param1Builder.mContentInfo, param1Builder.mTickerView, param1Builder.mNumber, param1Builder.mContentIntent, param1Builder.mFullScreenIntent, param1Builder.mLargeIcon, param1Builder.mProgressMax, param1Builder.mProgress, param1Builder.mProgressIndeterminate));
    }
    
    public NotificationCompat.Action getAction(Notification param1Notification, int param1Int) {
      return null;
    }
    
    public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> param1ArrayList) {
      return null;
    }
    
    public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation param1UnreadConversation) {
      return null;
    }
    
    public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] param1ArrayOfAction) {
      return null;
    }
    
    public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle param1Bundle, NotificationCompatBase.UnreadConversation.Factory param1Factory, RemoteInputCompatBase.RemoteInput.Factory param1Factory1) {
      return null;
    }
    
    public static class BuilderBase implements NotificationBuilderWithBuilderAccessor {
      private Notification.Builder mBuilder;
      
      BuilderBase(Context param2Context, Notification param2Notification, CharSequence param2CharSequence1, CharSequence param2CharSequence2, CharSequence param2CharSequence3, RemoteViews param2RemoteViews, int param2Int1, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, Bitmap param2Bitmap, int param2Int2, int param2Int3, boolean param2Boolean) {
        boolean bool2;
        Notification.Builder builder = (new Notification.Builder(param2Context)).setWhen(param2Notification.when).setSmallIcon(param2Notification.icon, param2Notification.iconLevel).setContent(param2Notification.contentView).setTicker(param2Notification.tickerText, param2RemoteViews).setSound(param2Notification.sound, param2Notification.audioStreamType).setVibrate(param2Notification.vibrate).setLights(param2Notification.ledARGB, param2Notification.ledOnMS, param2Notification.ledOffMS);
        int i = param2Notification.flags;
        boolean bool1 = true;
        if ((i & 0x2) != 0) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        builder = builder.setOngoing(bool2);
        if ((param2Notification.flags & 0x8) != 0) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        builder = builder.setOnlyAlertOnce(bool2);
        if ((param2Notification.flags & 0x10) != 0) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        builder = builder.setAutoCancel(bool2).setDefaults(param2Notification.defaults).setContentTitle(param2CharSequence1).setContentText(param2CharSequence2).setContentInfo(param2CharSequence3).setContentIntent(param2PendingIntent1).setDeleteIntent(param2Notification.deleteIntent);
        if ((param2Notification.flags & 0x80) != 0) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        this.mBuilder = builder.setFullScreenIntent(param2PendingIntent2, bool2).setLargeIcon(param2Bitmap).setNumber(param2Int1).setProgress(param2Int2, param2Int3, param2Boolean);
      }
      
      public Notification build() {
        return this.mBuilder.getNotification();
      }
      
      public Notification.Builder getBuilder() {
        return this.mBuilder;
      }
    }
  }
  
  public static class BuilderBase implements NotificationBuilderWithBuilderAccessor {
    private Notification.Builder mBuilder;
    
    BuilderBase(Context param1Context, Notification param1Notification, CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3, RemoteViews param1RemoteViews, int param1Int1, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Bitmap param1Bitmap, int param1Int2, int param1Int3, boolean param1Boolean) {
      boolean bool2;
      Notification.Builder builder = (new Notification.Builder(param1Context)).setWhen(param1Notification.when).setSmallIcon(param1Notification.icon, param1Notification.iconLevel).setContent(param1Notification.contentView).setTicker(param1Notification.tickerText, param1RemoteViews).setSound(param1Notification.sound, param1Notification.audioStreamType).setVibrate(param1Notification.vibrate).setLights(param1Notification.ledARGB, param1Notification.ledOnMS, param1Notification.ledOffMS);
      int i = param1Notification.flags;
      boolean bool1 = true;
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
      builder = builder.setAutoCancel(bool2).setDefaults(param1Notification.defaults).setContentTitle(param1CharSequence1).setContentText(param1CharSequence2).setContentInfo(param1CharSequence3).setContentIntent(param1PendingIntent1).setDeleteIntent(param1Notification.deleteIntent);
      if ((param1Notification.flags & 0x80) != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mBuilder = builder.setFullScreenIntent(param1PendingIntent2, bool2).setLargeIcon(param1Bitmap).setNumber(param1Int1).setProgress(param1Int2, param1Int3, param1Boolean);
    }
    
    public Notification build() {
      return this.mBuilder.getNotification();
    }
    
    public Notification.Builder getBuilder() {
      return this.mBuilder;
    }
  }
  
  static interface NotificationCompatImpl {
    Notification build(NotificationCompat.Builder param1Builder, NotificationCompat.BuilderExtender param1BuilderExtender);
    
    NotificationCompat.Action getAction(Notification param1Notification, int param1Int);
    
    NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> param1ArrayList);
    
    Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation param1UnreadConversation);
    
    ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] param1ArrayOfAction);
    
    NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle param1Bundle, NotificationCompatBase.UnreadConversation.Factory param1Factory, RemoteInputCompatBase.RemoteInput.Factory param1Factory1);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NotificationVisibility {}
  
  public static abstract class Style {
    CharSequence mBigContentTitle;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected NotificationCompat.Builder mBuilder;
    
    CharSequence mSummaryText;
    
    boolean mSummaryTextSet = false;
    
    private int calculateTopPadding() {
      Resources resources = this.mBuilder.mContext.getResources();
      int i = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
      int j = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
      float f = (constrain((resources.getConfiguration()).fontScale, 1.0F, 1.3F) - 1.0F) / 0.29999995F;
      return Math.round((1.0F - f) * i + f * j);
    }
    
    private static float constrain(float param1Float1, float param1Float2, float param1Float3) {
      if (param1Float1 >= param1Float2) {
        param1Float2 = param1Float1;
        if (param1Float1 > param1Float3)
          param1Float2 = param1Float3; 
      } 
      return param1Float2;
    }
    
    private Bitmap createColoredBitmap(int param1Int1, int param1Int2, int param1Int3) {
      Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(param1Int1);
      if (param1Int3 == 0) {
        param1Int1 = drawable.getIntrinsicWidth();
      } else {
        param1Int1 = param1Int3;
      } 
      int i = param1Int3;
      if (param1Int3 == 0)
        i = drawable.getIntrinsicHeight(); 
      Bitmap bitmap = Bitmap.createBitmap(param1Int1, i, Bitmap.Config.ARGB_8888);
      drawable.setBounds(0, 0, param1Int1, i);
      if (param1Int2 != 0)
        drawable.mutate().setColorFilter((ColorFilter)new PorterDuffColorFilter(param1Int2, PorterDuff.Mode.SRC_IN)); 
      drawable.draw(new Canvas(bitmap));
      return bitmap;
    }
    
    private Bitmap createIconWithBackground(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      int i = R.drawable.notification_icon_background;
      int j = param1Int4;
      if (param1Int4 == 0)
        j = 0; 
      Bitmap bitmap = createColoredBitmap(i, j, param1Int2);
      Canvas canvas = new Canvas(bitmap);
      Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(param1Int1).mutate();
      drawable.setFilterBitmap(true);
      param1Int1 = (param1Int2 - param1Int3) / 2;
      param1Int2 = param1Int3 + param1Int1;
      drawable.setBounds(param1Int1, param1Int1, param1Int2, param1Int2);
      drawable.setColorFilter((ColorFilter)new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
      drawable.draw(canvas);
      return bitmap;
    }
    
    private void hideNormalContent(RemoteViews param1RemoteViews) {
      param1RemoteViews.setViewVisibility(R.id.title, 8);
      param1RemoteViews.setViewVisibility(R.id.text2, 8);
      param1RemoteViews.setViewVisibility(R.id.text, 8);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addCompatExtras(Bundle param1Bundle) {}
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {}
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews applyStandardTemplate(boolean param1Boolean1, int param1Int, boolean param1Boolean2) {
      Bitmap bitmap;
      Resources resources = this.mBuilder.mContext.getResources();
      RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), param1Int);
      param1Int = this.mBuilder.getPriority();
      boolean bool1 = true;
      boolean bool2 = false;
      if (param1Int < -1) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      int i = Build.VERSION.SDK_INT;
      if (i < 21)
        if (param1Int != 0) {
          remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
          remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
        } else {
          remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
          remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
        }  
      NotificationCompat.Builder builder2 = this.mBuilder;
      if (builder2.mLargeIcon != null) {
        param1Int = R.id.icon;
        remoteViews.setViewVisibility(param1Int, 0);
        remoteViews.setImageViewBitmap(param1Int, this.mBuilder.mLargeIcon);
        if (param1Boolean1 && this.mBuilder.mNotification.icon != 0) {
          param1Int = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
          int j = resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding);
          if (i >= 21) {
            builder2 = this.mBuilder;
            bitmap = createIconWithBackground(builder2.mNotification.icon, param1Int, param1Int - j * 2, builder2.getColor());
            remoteViews.setImageViewBitmap(R.id.right_icon, bitmap);
          } else {
            remoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
          } 
          remoteViews.setViewVisibility(R.id.right_icon, 0);
        } 
      } else if (param1Boolean1 && ((NotificationCompat.Builder)bitmap).mNotification.icon != 0) {
        int j = R.id.icon;
        remoteViews.setViewVisibility(j, 0);
        if (i >= 21) {
          int k = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width);
          param1Int = resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
          int m = resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
          NotificationCompat.Builder builder = this.mBuilder;
          remoteViews.setImageViewBitmap(j, createIconWithBackground(builder.mNotification.icon, k - param1Int, m, builder.getColor()));
        } else {
          remoteViews.setImageViewBitmap(j, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
        } 
      } 
      CharSequence charSequence2 = this.mBuilder.mContentTitle;
      if (charSequence2 != null)
        remoteViews.setTextViewText(R.id.title, charSequence2); 
      charSequence2 = this.mBuilder.mContentText;
      if (charSequence2 != null) {
        remoteViews.setTextViewText(R.id.text, charSequence2);
        boolean bool = true;
      } else {
        boolean bool = false;
      } 
      if (i < 21 && this.mBuilder.mLargeIcon != null) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      NotificationCompat.Builder builder1 = this.mBuilder;
      CharSequence charSequence3 = builder1.mContentInfo;
      if (charSequence3 != null) {
        param1Int = R.id.info;
        remoteViews.setTextViewText(param1Int, charSequence3);
        remoteViews.setViewVisibility(param1Int, 0);
      } else if (builder1.mNumber > 0) {
        param1Int = resources.getInteger(R.integer.status_bar_notification_info_maxnum);
        if (this.mBuilder.mNumber > param1Int) {
          remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
        } else {
          NumberFormat numberFormat = NumberFormat.getIntegerInstance();
          remoteViews.setTextViewText(R.id.info, numberFormat.format(this.mBuilder.mNumber));
        } 
        remoteViews.setViewVisibility(R.id.info, 0);
      } else {
        remoteViews.setViewVisibility(R.id.info, 8);
        CharSequence charSequence = this.mBuilder.mSubText;
      } 
      boolean bool3 = true;
      param1Int = 1;
      CharSequence charSequence1 = this.mBuilder.mSubText;
    }
    
    public Notification build() {
      NotificationCompat.Builder builder = this.mBuilder;
      if (builder != null) {
        Notification notification = builder.build();
      } else {
        builder = null;
      } 
      return (Notification)builder;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void buildIntoRemoteViews(RemoteViews param1RemoteViews1, RemoteViews param1RemoteViews2) {
      hideNormalContent(param1RemoteViews1);
      int i = R.id.notification_main_column;
      param1RemoteViews1.removeAllViews(i);
      param1RemoteViews1.addView(i, param1RemoteViews2.clone());
      param1RemoteViews1.setViewVisibility(i, 0);
      if (Build.VERSION.SDK_INT >= 21)
        param1RemoteViews1.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0); 
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bitmap createColoredBitmap(int param1Int1, int param1Int2) {
      return createColoredBitmap(param1Int1, param1Int2, 0);
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor param1NotificationBuilderWithBuilderAccessor) {
      return null;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected void restoreFromCompatExtras(Bundle param1Bundle) {}
    
    public void setBuilder(NotificationCompat.Builder param1Builder) {
      if (this.mBuilder != param1Builder) {
        this.mBuilder = param1Builder;
        if (param1Builder != null)
          param1Builder.setStyle(this); 
      } 
    }
  }
  
  public static final class WearableExtender implements Extender {
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    
    private static final int DEFAULT_FLAGS = 1;
    
    private static final int DEFAULT_GRAVITY = 80;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    
    private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    
    private static final int FLAG_HINT_HIDE_ICON = 2;
    
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    
    private static final String KEY_ACTIONS = "actions";
    
    private static final String KEY_BACKGROUND = "background";
    
    private static final String KEY_BRIDGE_TAG = "bridgeTag";
    
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    
    private static final String KEY_CONTENT_ICON = "contentIcon";
    
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    
    private static final String KEY_DISMISSAL_ID = "dismissalId";
    
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_GRAVITY = "gravity";
    
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    
    private static final String KEY_PAGES = "pages";
    
    public static final int SCREEN_TIMEOUT_LONG = -1;
    
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    
    public static final int SIZE_DEFAULT = 0;
    
    public static final int SIZE_FULL_SCREEN = 5;
    
    public static final int SIZE_LARGE = 4;
    
    public static final int SIZE_MEDIUM = 3;
    
    public static final int SIZE_SMALL = 2;
    
    public static final int SIZE_XSMALL = 1;
    
    public static final int UNSET_ACTION_INDEX = -1;
    
    private ArrayList<NotificationCompat.Action> mActions = new ArrayList<NotificationCompat.Action>();
    
    private Bitmap mBackground;
    
    private String mBridgeTag;
    
    private int mContentActionIndex = -1;
    
    private int mContentIcon;
    
    private int mContentIconGravity = 8388613;
    
    private int mCustomContentHeight;
    
    private int mCustomSizePreset = 0;
    
    private String mDismissalId;
    
    private PendingIntent mDisplayIntent;
    
    private int mFlags = 1;
    
    private int mGravity = 80;
    
    private int mHintScreenTimeout;
    
    private ArrayList<Notification> mPages = new ArrayList<Notification>();
    
    public WearableExtender() {}
    
    public WearableExtender(Notification param1Notification) {
      Bundle bundle = NotificationCompat.getExtras(param1Notification);
      if (bundle != null) {
        bundle = bundle.getBundle("android.wearable.EXTENSIONS");
      } else {
        bundle = null;
      } 
      if (bundle != null) {
        NotificationCompat.Action[] arrayOfAction = NotificationCompat.IMPL.getActionsFromParcelableArrayList(bundle.getParcelableArrayList("actions"));
        if (arrayOfAction != null)
          Collections.addAll(this.mActions, arrayOfAction); 
        this.mFlags = bundle.getInt("flags", 1);
        this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
        Notification[] arrayOfNotification = NotificationCompat.getNotificationArrayFromBundle(bundle, "pages");
        if (arrayOfNotification != null)
          Collections.addAll(this.mPages, arrayOfNotification); 
        this.mBackground = (Bitmap)bundle.getParcelable("background");
        this.mContentIcon = bundle.getInt("contentIcon");
        this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
        this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
        this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
        this.mCustomContentHeight = bundle.getInt("customContentHeight");
        this.mGravity = bundle.getInt("gravity", 80);
        this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
        this.mDismissalId = bundle.getString("dismissalId");
        this.mBridgeTag = bundle.getString("bridgeTag");
      } 
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags = param1Int | this.mFlags;
      } else {
        this.mFlags = (param1Int ^ 0xFFFFFFFF) & this.mFlags;
      } 
    }
    
    public WearableExtender addAction(NotificationCompat.Action param1Action) {
      this.mActions.add(param1Action);
      return this;
    }
    
    public WearableExtender addActions(List<NotificationCompat.Action> param1List) {
      this.mActions.addAll(param1List);
      return this;
    }
    
    public WearableExtender addPage(Notification param1Notification) {
      this.mPages.add(param1Notification);
      return this;
    }
    
    public WearableExtender addPages(List<Notification> param1List) {
      this.mPages.addAll(param1List);
      return this;
    }
    
    public WearableExtender clearActions() {
      this.mActions.clear();
      return this;
    }
    
    public WearableExtender clearPages() {
      this.mPages.clear();
      return this;
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mActions = new ArrayList<NotificationCompat.Action>(this.mActions);
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mDisplayIntent = this.mDisplayIntent;
      wearableExtender.mPages = new ArrayList<Notification>(this.mPages);
      wearableExtender.mBackground = this.mBackground;
      wearableExtender.mContentIcon = this.mContentIcon;
      wearableExtender.mContentIconGravity = this.mContentIconGravity;
      wearableExtender.mContentActionIndex = this.mContentActionIndex;
      wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
      wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
      wearableExtender.mGravity = this.mGravity;
      wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
      wearableExtender.mDismissalId = this.mDismissalId;
      wearableExtender.mBridgeTag = this.mBridgeTag;
      return wearableExtender;
    }
    
    public NotificationCompat.Builder extend(NotificationCompat.Builder param1Builder) {
      Bundle bundle = new Bundle();
      if (!this.mActions.isEmpty()) {
        NotificationCompat.NotificationCompatImpl notificationCompatImpl = NotificationCompat.IMPL;
        ArrayList<NotificationCompat.Action> arrayList = this.mActions;
        bundle.putParcelableArrayList("actions", notificationCompatImpl.getParcelableArrayListForActions(arrayList.<NotificationCompat.Action>toArray(new NotificationCompat.Action[arrayList.size()])));
      } 
      int i = this.mFlags;
      if (i != 1)
        bundle.putInt("flags", i); 
      PendingIntent pendingIntent = this.mDisplayIntent;
      if (pendingIntent != null)
        bundle.putParcelable("displayIntent", (Parcelable)pendingIntent); 
      if (!this.mPages.isEmpty()) {
        ArrayList<Notification> arrayList = this.mPages;
        bundle.putParcelableArray("pages", (Parcelable[])arrayList.toArray((Object[])new Notification[arrayList.size()]));
      } 
      Bitmap bitmap = this.mBackground;
      if (bitmap != null)
        bundle.putParcelable("background", (Parcelable)bitmap); 
      i = this.mContentIcon;
      if (i != 0)
        bundle.putInt("contentIcon", i); 
      i = this.mContentIconGravity;
      if (i != 8388613)
        bundle.putInt("contentIconGravity", i); 
      i = this.mContentActionIndex;
      if (i != -1)
        bundle.putInt("contentActionIndex", i); 
      i = this.mCustomSizePreset;
      if (i != 0)
        bundle.putInt("customSizePreset", i); 
      i = this.mCustomContentHeight;
      if (i != 0)
        bundle.putInt("customContentHeight", i); 
      i = this.mGravity;
      if (i != 80)
        bundle.putInt("gravity", i); 
      i = this.mHintScreenTimeout;
      if (i != 0)
        bundle.putInt("hintScreenTimeout", i); 
      String str = this.mDismissalId;
      if (str != null)
        bundle.putString("dismissalId", str); 
      str = this.mBridgeTag;
      if (str != null)
        bundle.putString("bridgeTag", str); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    public List<NotificationCompat.Action> getActions() {
      return this.mActions;
    }
    
    public Bitmap getBackground() {
      return this.mBackground;
    }
    
    public String getBridgeTag() {
      return this.mBridgeTag;
    }
    
    public int getContentAction() {
      return this.mContentActionIndex;
    }
    
    public int getContentIcon() {
      return this.mContentIcon;
    }
    
    public int getContentIconGravity() {
      return this.mContentIconGravity;
    }
    
    public boolean getContentIntentAvailableOffline() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public int getCustomContentHeight() {
      return this.mCustomContentHeight;
    }
    
    public int getCustomSizePreset() {
      return this.mCustomSizePreset;
    }
    
    public String getDismissalId() {
      return this.mDismissalId;
    }
    
    public PendingIntent getDisplayIntent() {
      return this.mDisplayIntent;
    }
    
    public int getGravity() {
      return this.mGravity;
    }
    
    public boolean getHintAmbientBigPicture() {
      boolean bool;
      if ((this.mFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintAvoidBackgroundClipping() {
      boolean bool;
      if ((this.mFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintContentIntentLaunchesActivity() {
      boolean bool;
      if ((this.mFlags & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintHideIcon() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getHintScreenTimeout() {
      return this.mHintScreenTimeout;
    }
    
    public boolean getHintShowBackgroundOnly() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public List<Notification> getPages() {
      return this.mPages;
    }
    
    public boolean getStartScrollBottom() {
      boolean bool;
      if ((this.mFlags & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public WearableExtender setBackground(Bitmap param1Bitmap) {
      this.mBackground = param1Bitmap;
      return this;
    }
    
    public WearableExtender setBridgeTag(String param1String) {
      this.mBridgeTag = param1String;
      return this;
    }
    
    public WearableExtender setContentAction(int param1Int) {
      this.mContentActionIndex = param1Int;
      return this;
    }
    
    public WearableExtender setContentIcon(int param1Int) {
      this.mContentIcon = param1Int;
      return this;
    }
    
    public WearableExtender setContentIconGravity(int param1Int) {
      this.mContentIconGravity = param1Int;
      return this;
    }
    
    public WearableExtender setContentIntentAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    public WearableExtender setCustomContentHeight(int param1Int) {
      this.mCustomContentHeight = param1Int;
      return this;
    }
    
    public WearableExtender setCustomSizePreset(int param1Int) {
      this.mCustomSizePreset = param1Int;
      return this;
    }
    
    public WearableExtender setDismissalId(String param1String) {
      this.mDismissalId = param1String;
      return this;
    }
    
    public WearableExtender setDisplayIntent(PendingIntent param1PendingIntent) {
      this.mDisplayIntent = param1PendingIntent;
      return this;
    }
    
    public WearableExtender setGravity(int param1Int) {
      this.mGravity = param1Int;
      return this;
    }
    
    public WearableExtender setHintAmbientBigPicture(boolean param1Boolean) {
      setFlag(32, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintAvoidBackgroundClipping(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintContentIntentLaunchesActivity(boolean param1Boolean) {
      setFlag(64, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintHideIcon(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintScreenTimeout(int param1Int) {
      this.mHintScreenTimeout = param1Int;
      return this;
    }
    
    public WearableExtender setHintShowBackgroundOnly(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setStartScrollBottom(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */