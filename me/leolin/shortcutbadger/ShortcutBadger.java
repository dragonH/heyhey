package me.leolin.shortcutbadger;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;
import me.leolin.shortcutbadger.impl.AdwHomeBadger;
import me.leolin.shortcutbadger.impl.ApexHomeBadger;
import me.leolin.shortcutbadger.impl.AsusHomeBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import me.leolin.shortcutbadger.impl.EverythingMeHomeBadger;
import me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import me.leolin.shortcutbadger.impl.NovaHomeBadger;
import me.leolin.shortcutbadger.impl.OPPOHomeBader;
import me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import me.leolin.shortcutbadger.impl.SonyHomeBadger;
import me.leolin.shortcutbadger.impl.VivoHomeBadger;
import me.leolin.shortcutbadger.impl.ZTEHomeBadger;
import me.leolin.shortcutbadger.impl.ZukHomeBadger;

public final class ShortcutBadger {
  private static final List<Class<? extends Badger>> BADGERS;
  
  private static final String LOG_TAG = "ShortcutBadger";
  
  private static final int SUPPORTED_CHECK_ATTEMPTS = 3;
  
  private static ComponentName sComponentName;
  
  private static final Object sCounterSupportedLock = new Object();
  
  private static volatile Boolean sIsBadgeCounterSupported;
  
  private static Badger sShortcutBadger;
  
  static {
    linkedList.add(AdwHomeBadger.class);
    linkedList.add(ApexHomeBadger.class);
    linkedList.add(DefaultBadger.class);
    linkedList.add(NewHtcHomeBadger.class);
    linkedList.add(NovaHomeBadger.class);
    linkedList.add(SonyHomeBadger.class);
    linkedList.add(AsusHomeBadger.class);
    linkedList.add(HuaweiHomeBadger.class);
    linkedList.add(OPPOHomeBader.class);
    linkedList.add(SamsungHomeBadger.class);
    linkedList.add(ZukHomeBadger.class);
    linkedList.add(VivoHomeBadger.class);
    linkedList.add(ZTEHomeBadger.class);
    linkedList.add(EverythingMeHomeBadger.class);
  }
  
  public static boolean applyCount(Context paramContext, int paramInt) {
    try {
      applyCountOrThrow(paramContext, paramInt);
      return true;
    } catch (ShortcutBadgeException shortcutBadgeException) {
      if (Log.isLoggable("ShortcutBadger", 3))
        Log.d("ShortcutBadger", "Unable to execute badge", shortcutBadgeException); 
      return false;
    } 
  }
  
  public static void applyCountOrThrow(Context paramContext, int paramInt) throws ShortcutBadgeException {
    if (sShortcutBadger != null || initBadger(paramContext))
      try {
        sShortcutBadger.executeBadge(paramContext, sComponentName, paramInt);
        return;
      } catch (Exception exception) {
        throw new ShortcutBadgeException("Unable to execute badge", exception);
      }  
    throw new ShortcutBadgeException("No default launcher available");
  }
  
  public static void applyNotification(Context paramContext, Notification paramNotification, int paramInt) {
    if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
      try {
        Object object = paramNotification.getClass().getDeclaredField("extraNotification").get(paramNotification);
        object.getClass().getDeclaredMethod("setMessageCount", new Class[] { int.class }).invoke(object, new Object[] { Integer.valueOf(paramInt) });
      } catch (Exception exception) {
        if (Log.isLoggable("ShortcutBadger", 3))
          Log.d("ShortcutBadger", "Unable to execute badge", exception); 
      }  
  }
  
  private static boolean initBadger(Context paramContext) {
    StringBuilder stringBuilder;
    Intent intent2 = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (intent2 == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to find launch intent for package ");
      stringBuilder.append(paramContext.getPackageName());
      Log.e("ShortcutBadger", stringBuilder.toString());
      return false;
    } 
    sComponentName = stringBuilder.getComponent();
    Intent intent1 = new Intent("android.intent.action.MAIN");
    intent1.addCategory("android.intent.category.HOME");
    ResolveInfo resolveInfo = paramContext.getPackageManager().resolveActivity(intent1, 65536);
    if (resolveInfo == null || resolveInfo.activityInfo.name.toLowerCase().contains("resolver"))
      return false; 
    String str = resolveInfo.activityInfo.packageName;
    for (Class<Badger> clazz : BADGERS) {
      Badger badger;
      resolveInfo = null;
      try {
        Badger badger1 = clazz.newInstance();
        badger = badger1;
      } catch (Exception exception) {}
      if (badger != null && badger.getSupportLaunchers().contains(str)) {
        sShortcutBadger = badger;
        break;
      } 
    } 
    if (sShortcutBadger == null) {
      String str1 = Build.MANUFACTURER;
      if (str1.equalsIgnoreCase("ZUK")) {
        sShortcutBadger = (Badger)new ZukHomeBadger();
      } else if (str1.equalsIgnoreCase("OPPO")) {
        sShortcutBadger = (Badger)new OPPOHomeBader();
      } else if (str1.equalsIgnoreCase("VIVO")) {
        sShortcutBadger = (Badger)new VivoHomeBadger();
      } else if (str1.equalsIgnoreCase("ZTE")) {
        sShortcutBadger = (Badger)new ZTEHomeBadger();
      } else {
        sShortcutBadger = (Badger)new DefaultBadger();
      } 
    } 
    return true;
  }
  
  public static boolean isBadgeCounterSupported(Context paramContext) {
    if (sIsBadgeCounterSupported == null)
      synchronized (sCounterSupportedLock) {
        Boolean bool = sIsBadgeCounterSupported;
        if (bool == null) {
          String str;
          bool = null;
          for (byte b = 0; b < 3; b++) {
            try {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Checking if platform supports badge counters, attempt ");
              stringBuilder.append(String.format("%d/%d.", new Object[] { Integer.valueOf(b + 1), Integer.valueOf(3) }));
              Log.i("ShortcutBadger", stringBuilder.toString());
              if (initBadger(paramContext)) {
                sShortcutBadger.executeBadge(paramContext, sComponentName, 0);
                sIsBadgeCounterSupported = Boolean.TRUE;
                Log.i("ShortcutBadger", "Badge counter is supported in this platform.");
                break;
              } 
              str = "Failed to initialize the badge counter.";
            } catch (Exception exception) {
              str = exception.getMessage();
            } 
          } 
          if (sIsBadgeCounterSupported == null) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Badge counter seems not supported for this platform: ");
            stringBuilder.append(str);
            Log.w("ShortcutBadger", stringBuilder.toString());
            sIsBadgeCounterSupported = Boolean.FALSE;
          } 
        } 
      }  
    return sIsBadgeCounterSupported.booleanValue();
  }
  
  public static boolean removeCount(Context paramContext) {
    return applyCount(paramContext, 0);
  }
  
  public static void removeCountOrThrow(Context paramContext) throws ShortcutBadgeException {
    applyCountOrThrow(paramContext, 0);
  }
  
  static {
    LinkedList<Class<? extends Badger>> linkedList = new LinkedList();
    BADGERS = linkedList;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/ShortcutBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */