package me.leolin.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;
import me.leolin.shortcutbadger.util.CloseHelper;

public class OPPOHomeBader implements Badger {
  private static final String INTENT_ACTION = "com.oppo.unsettledevent";
  
  private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
  
  private static final String INTENT_EXTRA_BADGE_COUNT = "number";
  
  private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
  
  private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
  
  private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
  
  private static int ROMVERSION = -1;
  
  private boolean checkObjExists(Object paramObject) {
    return (paramObject == null || paramObject.toString().equals("") || paramObject.toString().trim().equals("null"));
  }
  
  private Object executeClassLoad(Class paramClass, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
    Object object = null;
    Object object1 = object;
    if (paramClass != null) {
      object1 = object;
      if (!checkObjExists(paramString)) {
        Method method = getMethod(paramClass, paramString, paramArrayOfClass);
        object1 = object;
        if (method != null) {
          method.setAccessible(true);
          try {
            object1 = method.invoke(null, paramArrayOfObject);
          } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            object1 = object;
          } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
            object1 = object;
          } 
        } 
      } 
    } 
    return object1;
  }
  
  private Class getClass(String paramString) {
    try {
      Class<?> clazz = Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      classNotFoundException = null;
    } 
    return (Class)classNotFoundException;
  }
  
  private Method getMethod(Class paramClass, String paramString, Class[] paramArrayOfClass) {
    Method method1 = null;
    Method method2 = method1;
    if (paramClass != null)
      if (checkObjExists(paramString)) {
        method2 = method1;
      } else {
        try {
          paramClass.getMethods();
          paramClass.getDeclaredMethods();
          return paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
        } catch (Exception exception) {
          try {
            return paramClass.getMethod(paramString, paramArrayOfClass);
          } catch (Exception exception1) {
            method2 = method1;
            if (paramClass.getSuperclass() != null)
              method2 = getMethod(paramClass.getSuperclass(), paramString, paramArrayOfClass); 
          } 
        } 
      }  
    return method2;
  }
  
  private int getSupportVersion() {
    int i = ROMVERSION;
    if (i >= 0)
      return i; 
    try {
      i = ((Integer)executeClassLoad(getClass("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
    } catch (Exception exception) {
      i = 0;
    } 
    if (i == 0)
      try {
        String str = getSystemProperty("ro.build.version.opporom");
        if (str.startsWith("V1.4"))
          return 3; 
        if (str.startsWith("V2.0"))
          return 4; 
        boolean bool = str.startsWith("V2.1");
        if (bool)
          return 5; 
      } catch (Exception exception) {} 
    ROMVERSION = i;
    return i;
  }
  
  private String getSystemProperty(String paramString) {
    String str = null;
    try {
      Runtime runtime = Runtime.getRuntime();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("getprop ");
      stringBuilder.append(paramString);
      Process process = runtime.exec(stringBuilder.toString());
      BufferedReader bufferedReader = new BufferedReader();
      InputStreamReader inputStreamReader = new InputStreamReader();
      this(process.getInputStream());
      this(inputStreamReader, 1024);
      try {
        return bufferedReader.readLine();
      } catch (IOException iOException) {
      
      } finally {}
      CloseHelper.closeQuietly(bufferedReader);
      throw process;
    } catch (IOException iOException) {
    
    } finally {
      paramString = str;
      CloseHelper.closeQuietly((Closeable)paramString);
    } 
    CloseHelper.closeQuietly((Closeable)paramString);
    return null;
  }
  
  @TargetApi(11)
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    int i = paramInt;
    if (paramInt == 0)
      i = -1; 
    Intent intent = new Intent("com.oppo.unsettledevent");
    intent.putExtra("pakeageName", paramComponentName.getPackageName());
    intent.putExtra("number", i);
    intent.putExtra("upgradeNumber", i);
    if (BroadcastHelper.canResolveBroadcast(paramContext, intent)) {
      paramContext.sendBroadcast(intent);
    } else if (getSupportVersion() == 6) {
      try {
        Bundle bundle = new Bundle();
        this();
        bundle.putInt("app_badge_count", i);
        paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, bundle);
      } finally {
        paramContext = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unable to resolve intent: ");
        stringBuilder.append(intent.toString());
      } 
    } 
  }
  
  public List<String> getSupportLaunchers() {
    return Collections.singletonList("com.oppo.launcher");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/OPPOHomeBader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */