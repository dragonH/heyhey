package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzeym;
import com.google.android.gms.internal.zzezo;
import com.google.android.gms.internal.zzezp;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzc {
  private static Bundle zza(@NonNull zzezp paramzzezp) {
    return zzay(paramzzezp.zzoxs, paramzzezp.zzoxt);
  }
  
  @Nullable
  private static Object zza(@NonNull zzezp paramzzezp, @NonNull String paramString, @NonNull zzb paramzzb) {
    Field field = null;
    String str = null;
    try {
      Class<?> clazz = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
      Bundle bundle = zza(paramzzezp);
      Field field1 = (Field)clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
      try {
        String str1;
        clazz.getField("mOrigin").set(field1, paramString);
        clazz.getField("mCreationTimestamp").set(field1, Long.valueOf(paramzzezp.zzoxu));
        clazz.getField("mName").set(field1, paramzzezp.zzoxs);
        clazz.getField("mValue").set(field1, paramzzezp.zzoxt);
        if (TextUtils.isEmpty(paramzzezp.zzoxv)) {
          paramString = str;
        } else {
          paramString = paramzzezp.zzoxv;
        } 
        clazz.getField("mTriggerEventName").set(field1, paramString);
        field = clazz.getField("mTimedOutEventName");
        if (!TextUtils.isEmpty(paramzzezp.zzoya)) {
          paramString = paramzzezp.zzoya;
        } else {
          paramString = paramzzb.zzboc();
        } 
        field.set(field1, paramString);
        clazz.getField("mTimedOutEventParams").set(field1, bundle);
        clazz.getField("mTriggerTimeout").set(field1, Long.valueOf(paramzzezp.zzoxw));
        field = clazz.getField("mTriggeredEventName");
        if (!TextUtils.isEmpty(paramzzezp.zzoxy)) {
          paramString = paramzzezp.zzoxy;
        } else {
          paramString = paramzzb.zzbob();
        } 
        field.set(field1, paramString);
        clazz.getField("mTriggeredEventParams").set(field1, bundle);
        clazz.getField("mTimeToLive").set(field1, Long.valueOf(paramzzezp.zzgcc));
        Field field2 = clazz.getField("mExpiredEventName");
        if (!TextUtils.isEmpty(paramzzezp.zzoyb)) {
          str1 = paramzzezp.zzoyb;
        } else {
          str1 = paramzzb.zzbod();
        } 
        field2.set(field1, str1);
        clazz.getField("mExpiredEventParams").set(field1, bundle);
        field2 = field1;
      } catch (ClassNotFoundException classNotFoundException) {
        Field field2 = field1;
        Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", classNotFoundException);
      } catch (NoSuchMethodException noSuchMethodException) {
      
      } catch (IllegalAccessException illegalAccessException) {
      
      } catch (InvocationTargetException invocationTargetException) {
      
      } catch (NoSuchFieldException noSuchFieldException) {
      
      } catch (InstantiationException null) {}
    } catch (ClassNotFoundException classNotFoundException) {
      Field field1 = field;
    } catch (NoSuchMethodException noSuchMethodException) {
      Field field1 = field;
    } catch (IllegalAccessException illegalAccessException) {
      Field field1 = field;
    } catch (InvocationTargetException invocationTargetException) {
      Field field1 = field;
    } catch (NoSuchFieldException noSuchFieldException) {
      Field field1 = field;
    } catch (InstantiationException instantiationException) {
      Field field1 = field;
    } 
    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", instantiationException);
  }
  
  private static String zza(@Nullable zzezp paramzzezp, @NonNull zzb paramzzb) {
    return (paramzzezp != null && !TextUtils.isEmpty(paramzzezp.zzoxz)) ? paramzzezp.zzoxz : paramzzb.zzboe();
  }
  
  private static List<Object> zza(@NonNull AppMeasurement paramAppMeasurement, @NonNull String paramString) {
    ArrayList arrayList = new ArrayList();
    try {
      Method method = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[] { String.class, String.class });
      method.setAccessible(true);
      List list = (List)method.invoke(paramAppMeasurement, new Object[] { paramString, "" });
    } catch (NoSuchMethodException noSuchMethodException) {
      Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", noSuchMethodException);
      ArrayList arrayList1 = arrayList;
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
      int i = invocationTargetException.size();
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 55);
      stringBuilder.append("Number of currently set _Es for origin: ");
      stringBuilder.append(paramString);
      stringBuilder.append(" is ");
      stringBuilder.append(i);
      Log.v("FirebaseAbtUtil", stringBuilder.toString());
    } 
    return (List<Object>)invocationTargetException;
  }
  
  private static void zza(@NonNull Context paramContext, @NonNull String paramString1, @NonNull String paramString2, @NonNull String paramString3, @NonNull String paramString4) {
    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
      paramString1 = String.valueOf(paramString1);
      if (paramString1.length() != 0) {
        paramString1 = "_CE(experimentId) called by ".concat(paramString1);
      } else {
        paramString1 = new String("_CE(experimentId) called by ");
      } 
      Log.v("FirebaseAbtUtil", paramString1);
    } 
    if (!zzeh(paramContext))
      return; 
    AppMeasurement appMeasurement = zzcs(paramContext);
    try {
      Method method = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[] { String.class, String.class, Bundle.class });
      method.setAccessible(true);
      if (Log.isLoggable("FirebaseAbtUtil", 2)) {
        int i = String.valueOf(paramString2).length();
        int j = String.valueOf(paramString3).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 17 + j);
        stringBuilder.append("Clearing _E: [");
        stringBuilder.append(paramString2);
        stringBuilder.append(", ");
        stringBuilder.append(paramString3);
        stringBuilder.append("]");
        Log.v("FirebaseAbtUtil", stringBuilder.toString());
      } 
      method.invoke(appMeasurement, new Object[] { paramString2, paramString4, zzay(paramString2, paramString3) });
      return;
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", invocationTargetException);
  }
  
  public static void zza(@NonNull Context paramContext, @NonNull String paramString, @NonNull byte[] paramArrayOfbyte, @NonNull zzb paramzzb, int paramInt) {
    String str = "com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty";
    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
      String str1 = String.valueOf(paramString);
      if (str1.length() != 0) {
        str1 = "_SE called by ".concat(str1);
      } else {
        str1 = new String("_SE called by ");
      } 
      Log.v("FirebaseAbtUtil", str1);
    } 
    if (!zzeh(paramContext))
      return; 
    AppMeasurement appMeasurement = zzcs(paramContext);
    zzezp zzezp = zzal(paramArrayOfbyte);
    if (zzezp == null) {
      if (Log.isLoggable("FirebaseAbtUtil", 2))
        Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P."); 
      return;
    } 
    try {
      Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
      Iterator<Object> iterator = zza(appMeasurement, paramString).iterator();
      paramInt = 0;
      String str1 = str;
      while (true) {
        String str2;
        boolean bool = iterator.hasNext();
        if (bool) {
          StringBuilder stringBuilder = (StringBuilder)iterator.next();
          str = zzat(stringBuilder);
          String str3 = zzau(stringBuilder);
          long l = ((Long)Class.forName(str1).getField("mCreationTimestamp").get(stringBuilder)).longValue();
          if (zzezp.zzoxs.equals(str) && zzezp.zzoxt.equals(str3)) {
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
              int k = String.valueOf(str).length();
              paramInt = String.valueOf(str3).length();
              stringBuilder = new StringBuilder();
              this(k + 23 + paramInt);
              stringBuilder.append("_E is already set. [");
              stringBuilder.append(str);
              stringBuilder.append(", ");
              stringBuilder.append(str3);
              stringBuilder.append("]");
              Log.v("FirebaseAbtUtil", stringBuilder.toString());
            } 
            paramInt = 1;
            continue;
          } 
          zzezo[] arrayOfZzezo = zzezp.zzoyd;
          int j = arrayOfZzezo.length;
          int i = 0;
          while (true) {
            if (i < j) {
              if ((arrayOfZzezo[i]).zzoxs.equals(str)) {
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                  j = String.valueOf(str).length();
                  i = String.valueOf(str3).length();
                  StringBuilder stringBuilder1 = new StringBuilder();
                  this(j + 33 + i);
                  stringBuilder1.append("_E is found in the _OE list. [");
                  stringBuilder1.append(str);
                  stringBuilder1.append(", ");
                  stringBuilder1.append(str3);
                  stringBuilder1.append("]");
                  Log.v("FirebaseAbtUtil", stringBuilder1.toString());
                } 
                i = 1;
                break;
              } 
              i++;
              continue;
            } 
            i = 0;
            break;
          } 
          if (i == 0) {
            if (zzezp.zzoxu > l) {
              if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                i = String.valueOf(str).length();
                j = String.valueOf(str3).length();
                StringBuilder stringBuilder1 = new StringBuilder();
                this(i + 115 + j);
                stringBuilder1.append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [");
                stringBuilder1.append(str);
                stringBuilder1.append(", ");
                stringBuilder1.append(str3);
                stringBuilder1.append("]");
                Log.v("FirebaseAbtUtil", stringBuilder1.toString());
              } 
              zza(paramContext, paramString, str, str3, zza(zzezp, paramzzb));
              continue;
            } 
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
              j = String.valueOf(str).length();
              i = String.valueOf(str3).length();
              StringBuilder stringBuilder1 = new StringBuilder();
              this(j + 109 + i);
              stringBuilder1.append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [");
              stringBuilder1.append(str);
              stringBuilder1.append(", ");
              stringBuilder1.append(str3);
              stringBuilder1.append("]");
              Log.v("FirebaseAbtUtil", stringBuilder1.toString());
            } 
          } 
          continue;
        } 
        if (paramInt != 0) {
          if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            str2 = zzezp.zzoxs;
            paramString = zzezp.zzoxt;
            paramInt = String.valueOf(str2).length();
            int i = String.valueOf(paramString).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(paramInt + 44 + i);
            stringBuilder.append("_E is already set. Not setting it again [");
            stringBuilder.append(str2);
            stringBuilder.append(", ");
            stringBuilder.append(paramString);
            stringBuilder.append("]");
            Log.v("FirebaseAbtUtil", stringBuilder.toString());
          } 
          return;
        } 
        zza(appMeasurement, (Context)str2, paramString, zzezp, paramzzb, 1);
        return;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (NoSuchFieldException noSuchFieldException) {}
    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", noSuchFieldException);
  }
  
  private static void zza(@NonNull AppMeasurement paramAppMeasurement, @NonNull Context paramContext, @NonNull String paramString, @NonNull zzezp paramzzezp, @NonNull zzb paramzzb, int paramInt) {
    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
      String str1 = paramzzezp.zzoxs;
      String str2 = paramzzezp.zzoxt;
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 7 + String.valueOf(str2).length());
      stringBuilder.append("_SEI: ");
      stringBuilder.append(str1);
      stringBuilder.append(" ");
      stringBuilder.append(str2);
      Log.v("FirebaseAbtUtil", stringBuilder.toString());
    } 
    try {
      String str;
      StringBuilder stringBuilder;
      Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
      List<Object> list = zza(paramAppMeasurement, paramString);
      paramInt = zzb(paramAppMeasurement, paramString);
      int i = zza(paramAppMeasurement, paramString).size();
      if (i >= paramInt) {
        paramInt = paramzzezp.zzoyc;
        if (paramInt == 0)
          paramInt = 1; 
        if (paramInt == 1) {
          Object object1 = list.get(0);
          String str1 = zzat(object1);
          object1 = zzau(object1);
          if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            paramInt = String.valueOf(str1).length();
            StringBuilder stringBuilder1 = new StringBuilder();
            this(paramInt + 38);
            stringBuilder1.append("Clearing _E due to overflow policy: [");
            stringBuilder1.append(str1);
            stringBuilder1.append("]");
            Log.v("FirebaseAbtUtil", stringBuilder1.toString());
          } 
          zza(paramContext, paramString, str1, (String)object1, zza(paramzzezp, paramzzb));
        } else {
          if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            str = paramzzezp.zzoxs;
            paramString = paramzzezp.zzoxt;
            i = String.valueOf(str).length();
            paramInt = String.valueOf(paramString).length();
            stringBuilder = new StringBuilder();
            this(i + 44 + paramInt);
            stringBuilder.append("_E won't be set due to overflow policy. [");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(paramString);
            stringBuilder.append("]");
            Log.v("FirebaseAbtUtil", stringBuilder.toString());
          } 
          return;
        } 
      } 
      for (String str2 : list) {
        String str1 = zzat(str2);
        str2 = zzau(str2);
        if (str1.equals(paramzzezp.zzoxs) && !str2.equals(paramzzezp.zzoxt) && Log.isLoggable("FirebaseAbtUtil", 2)) {
          paramInt = str1.length();
          i = str2.length();
          StringBuilder stringBuilder1 = new StringBuilder();
          this(paramInt + 77 + i);
          stringBuilder1.append("Clearing _E, as only one _V of the same _E can be set atany given time: [");
          stringBuilder1.append(str1);
          stringBuilder1.append(", ");
          stringBuilder1.append(str2);
          stringBuilder1.append("].");
          Log.v("FirebaseAbtUtil", stringBuilder1.toString());
          zza((Context)stringBuilder, paramString, str1, str2, zza(paramzzezp, paramzzb));
        } 
      } 
      Object object = zza(paramzzezp, paramString, paramzzb);
      if (object == null) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
          str = paramzzezp.zzoxs;
          paramString = paramzzezp.zzoxt;
          paramInt = String.valueOf(str).length();
          i = String.valueOf(paramString).length();
          stringBuilder = new StringBuilder();
          this(paramInt + 42 + i);
          stringBuilder.append("Could not create _CUP for: [");
          stringBuilder.append(str);
          stringBuilder.append(", ");
          stringBuilder.append(paramString);
          stringBuilder.append("]. Skipping.");
          Log.v("FirebaseAbtUtil", stringBuilder.toString());
        } 
        return;
      } 
      if (Log.isLoggable("FirebaseAbtUtil", 2)) {
        String str1 = paramzzezp.zzoxs;
        String str2 = paramzzezp.zzoxt;
        String str3 = paramzzezp.zzoxv;
        i = String.valueOf(str1).length();
        paramInt = String.valueOf(str2).length();
        int j = String.valueOf(str3).length();
        StringBuilder stringBuilder1 = new StringBuilder();
        this(i + 27 + paramInt + j);
        stringBuilder1.append("Setting _CUP for _E: [");
        stringBuilder1.append(str1);
        stringBuilder1.append(", ");
        stringBuilder1.append(str2);
        stringBuilder1.append(", ");
        stringBuilder1.append(str3);
        stringBuilder1.append("]");
        Log.v("FirebaseAbtUtil", stringBuilder1.toString());
      } 
      try {
        String str1;
        Method method = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[] { Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty") });
        method.setAccessible(true);
        if (!TextUtils.isEmpty(paramzzezp.zzoxx)) {
          str1 = paramzzezp.zzoxx;
        } else {
          str1 = paramzzb.zzboa();
        } 
        str.logEventInternal(paramString, str1, zza(paramzzezp));
        method.invoke(str, new Object[] { object });
        return;
      } catch (ClassNotFoundException classNotFoundException) {
      
      } catch (NoSuchMethodException noSuchMethodException) {
      
      } catch (IllegalAccessException illegalAccessException) {
      
      } catch (InvocationTargetException invocationTargetException) {}
      Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", invocationTargetException);
      return;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (NoSuchFieldException noSuchFieldException) {}
    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", noSuchFieldException);
  }
  
  @Nullable
  private static zzezp zzal(@NonNull byte[] paramArrayOfbyte) {
    try {
      return zzezp.zzbi(paramArrayOfbyte);
    } catch (zzeym zzeym) {
      return null;
    } 
  }
  
  private static String zzat(@NonNull Object paramObject) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    return (String)Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(paramObject);
  }
  
  private static String zzau(@NonNull Object paramObject) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    return (String)Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(paramObject);
  }
  
  private static Bundle zzay(@NonNull String paramString1, @NonNull String paramString2) {
    Bundle bundle = new Bundle();
    bundle.putString(paramString1, paramString2);
    return bundle;
  }
  
  private static int zzb(@NonNull AppMeasurement paramAppMeasurement, @NonNull String paramString) {
    try {
      Method method = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[] { String.class });
      method.setAccessible(true);
      return ((Integer)method.invoke(paramAppMeasurement, new Object[] { paramString })).intValue();
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", invocationTargetException);
    return 20;
  }
  
  @Nullable
  private static AppMeasurement zzcs(Context paramContext) {
    try {
      return AppMeasurement.getInstance(paramContext);
    } catch (NoClassDefFoundError noClassDefFoundError) {
      return null;
    } 
  }
  
  private static boolean zzeh(Context paramContext) {
    if (zzcs(paramContext) == null) {
      if (Log.isLoggable("FirebaseAbtUtil", 2))
        Log.v("FirebaseAbtUtil", "Firebase Analytics not available"); 
      return false;
    } 
    try {
      Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      if (Log.isLoggable("FirebaseAbtUtil", 2))
        Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version."); 
      return false;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */