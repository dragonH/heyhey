package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.internal.zzbed;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzx {
  private static final Method zzfzi = zzall();
  
  private static final Method zzfzj = zzalm();
  
  private static final Method zzfzk = zzaln();
  
  private static final Method zzfzl = zzalo();
  
  private static final Method zzfzm = zzalp();
  
  private static int zza(WorkSource paramWorkSource) {
    Method method = zzfzk;
    if (method != null)
      try {
        return ((Integer)method.invoke(paramWorkSource, new Object[0])).intValue();
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      }  
    return 0;
  }
  
  @Nullable
  private static String zza(WorkSource paramWorkSource, int paramInt) {
    Method method = zzfzm;
    if (method != null)
      try {
        return (String)method.invoke(paramWorkSource, new Object[] { Integer.valueOf(paramInt) });
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      }  
    return null;
  }
  
  @Nullable
  public static WorkSource zzac(Context paramContext, @Nullable String paramString) {
    if (paramContext != null && paramContext.getPackageManager() != null && paramString != null)
      try {
        String str;
        ApplicationInfo applicationInfo = zzbed.zzcr(paramContext).getApplicationInfo(paramString, 0);
        if (applicationInfo == null) {
          if (paramString.length() != 0) {
            str = "Could not get applicationInfo from package: ".concat(paramString);
          } else {
            str = new String("Could not get applicationInfo from package: ");
          } 
          Log.e("WorkSourceUtil", str);
          return null;
        } 
        return zze(((ApplicationInfo)str).uid, paramString);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        String str;
        if (paramString.length() != 0) {
          str = "Could not find package: ".concat(paramString);
        } else {
          str = new String("Could not find package: ");
        } 
        Log.e("WorkSourceUtil", str);
      }  
    return null;
  }
  
  private static Method zzall() {
    try {
      Method method = WorkSource.class.getMethod("add", new Class[] { int.class });
    } catch (Exception exception) {
      exception = null;
    } 
    return (Method)exception;
  }
  
  private static Method zzalm() {
    if (zzq.zzalg())
      try {
        return WorkSource.class.getMethod("add", new Class[] { int.class, String.class });
      } catch (Exception exception) {} 
    return null;
  }
  
  private static Method zzaln() {
    try {
      Method method = WorkSource.class.getMethod("size", new Class[0]);
    } catch (Exception exception) {
      exception = null;
    } 
    return (Method)exception;
  }
  
  private static Method zzalo() {
    try {
      Method method = WorkSource.class.getMethod("get", new Class[] { int.class });
    } catch (Exception exception) {
      exception = null;
    } 
    return (Method)exception;
  }
  
  private static Method zzalp() {
    if (zzq.zzalg())
      try {
        return WorkSource.class.getMethod("getName", new Class[] { int.class });
      } catch (Exception exception) {} 
    return null;
  }
  
  public static List<String> zzb(@Nullable WorkSource paramWorkSource) {
    int i;
    byte b = 0;
    if (paramWorkSource == null) {
      i = 0;
    } else {
      i = zza(paramWorkSource);
    } 
    if (i == 0)
      return Collections.emptyList(); 
    ArrayList<String> arrayList = new ArrayList();
    while (b < i) {
      String str = zza(paramWorkSource, b);
      if (!zzt.zzgm(str))
        arrayList.add(str); 
      b++;
    } 
    return arrayList;
  }
  
  public static boolean zzco(Context paramContext) {
    return (paramContext == null) ? false : ((paramContext.getPackageManager() == null) ? false : ((zzbed.zzcr(paramContext).checkPermission("android.permission.UPDATE_DEVICE_STATS", paramContext.getPackageName()) == 0)));
  }
  
  private static WorkSource zze(int paramInt, String paramString) {
    WorkSource workSource = new WorkSource();
    Method method = zzfzj;
    if (method != null) {
      String str = paramString;
      if (paramString == null)
        str = ""; 
      try {
        method.invoke(workSource, new Object[] { Integer.valueOf(paramInt), str });
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      } 
    } else {
      Method method1 = zzfzi;
      if (method1 != null)
        method1.invoke(workSource, new Object[] { Integer.valueOf(paramInt) }); 
    } 
    return workSource;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */