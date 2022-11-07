package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
  private static Boolean zzgpi;
  
  private static zzk zzgpj;
  
  private static zzm zzgpk;
  
  private static String zzgpl;
  
  private static final ThreadLocal<zza> zzgpm = new ThreadLocal<zza>();
  
  private static final zzi zzgpn = new zza();
  
  public static final zzd zzgpo = new zzb();
  
  private static zzd zzgpp = new zzc();
  
  public static final zzd zzgpq = new zzd();
  
  public static final zzd zzgpr = new zze();
  
  public static final zzd zzgps = new zzf();
  
  public static final zzd zzgpt = new zzg();
  
  private final Context zzgpu;
  
  private DynamiteModule(Context paramContext) {
    this.zzgpu = (Context)zzbp.zzu(paramContext);
  }
  
  private static Context zza(Context paramContext, String paramString, int paramInt, Cursor paramCursor, zzm paramzzm) {
    try {
      return (Context)zzn.zzx(paramzzm.zza(zzn.zzw(paramContext), paramString, paramInt, zzn.zzw(paramCursor)));
    } catch (Exception exception) {
      String str = String.valueOf(exception.toString());
      if (str.length() != 0) {
        str = "Failed to load DynamiteLoader: ".concat(str);
      } else {
        str = new String("Failed to load DynamiteLoader: ");
      } 
      Log.e("DynamiteModule", str);
      return null;
    } 
  }
  
  public static DynamiteModule zza(Context paramContext, zzd paramzzd, String paramString) throws zzc {
    ThreadLocal<zza> threadLocal = zzgpm;
    zza zza1 = threadLocal.get();
    zza zza2 = new zza(null);
    threadLocal.set(zza2);
    try {
      zzj zzj = paramzzd.zza(paramContext, paramString, zzgpn);
      int i = zzj.zzgpy;
      int j = zzj.zzgpz;
      int k = String.valueOf(paramString).length();
      int m = String.valueOf(paramString).length();
      StringBuilder stringBuilder2 = new StringBuilder();
      this(k + 68 + m);
      stringBuilder2.append("Considering local module ");
      stringBuilder2.append(paramString);
      stringBuilder2.append(":");
      stringBuilder2.append(i);
      stringBuilder2.append(" and remote module ");
      stringBuilder2.append(paramString);
      stringBuilder2.append(":");
      stringBuilder2.append(j);
      Log.i("DynamiteModule", stringBuilder2.toString());
      k = zzj.zzgqa;
      if (k != 0 && (k != -1 || zzj.zzgpy != 0) && (k != 1 || zzj.zzgpz != 0)) {
        DynamiteModule dynamiteModule;
        Cursor cursor;
        if (k == -1) {
          dynamiteModule = zzaf(paramContext, paramString);
          return dynamiteModule;
        } 
        if (k == 1) {
          Cursor cursor1;
          try {
            return zza((Context)dynamiteModule, paramString, zzj.zzgpz);
          } catch (zzc zzc3) {
            String str = String.valueOf(zzc3.getMessage());
            if (str.length() != 0) {
              str = "Failed to load remote module: ".concat(str);
            } else {
              str = new String("Failed to load remote module: ");
            } 
            Log.w("DynamiteModule", str);
            k = zzj.zzgpy;
            if (k != 0) {
              zzb zzb = new zzb();
              this(k, 0);
              if ((cursor.zza((Context)cursor1, paramString, zzb)).zzgqa == -1)
                return zzaf((Context)cursor1, paramString); 
            } 
            zzc zzc2 = new zzc();
            this("Remote load failed. No local fallback found.", zzc3, null);
            throw zzc2;
          } 
        } 
        zzc zzc1 = new zzc();
        k = zzj.zzgqa;
        StringBuilder stringBuilder = new StringBuilder();
        this(47);
        stringBuilder.append("VersionPolicy returned invalid code:");
        stringBuilder.append(k);
        this(stringBuilder.toString(), (zza)null);
        throw zzc1;
      } 
      zzc zzc = new zzc();
      k = zzj.zzgpy;
      j = zzj.zzgpz;
      StringBuilder stringBuilder1 = new StringBuilder();
      this(91);
      stringBuilder1.append("No acceptable module found. Local version is ");
      stringBuilder1.append(k);
      stringBuilder1.append(" and remote version is ");
      stringBuilder1.append(j);
      stringBuilder1.append(".");
      this(stringBuilder1.toString(), (zza)null);
      throw zzc;
    } finally {
      Cursor cursor = zza2.zzgpv;
      if (cursor != null)
        cursor.close(); 
      zzgpm.set(zza1);
    } 
  }
  
  private static DynamiteModule zza(Context paramContext, String paramString, int paramInt) throws zzc {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpi : Ljava/lang/Boolean;
    //   6: astore_3
    //   7: ldc com/google/android/gms/dynamite/DynamiteModule
    //   9: monitorexit
    //   10: aload_3
    //   11: ifnull -> 35
    //   14: aload_3
    //   15: invokevirtual booleanValue : ()Z
    //   18: ifeq -> 28
    //   21: aload_0
    //   22: aload_1
    //   23: iload_2
    //   24: invokestatic zzc : (Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   27: areturn
    //   28: aload_0
    //   29: aload_1
    //   30: iload_2
    //   31: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   34: areturn
    //   35: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   38: dup
    //   39: ldc 'Failed to determine which loading route to use.'
    //   41: aconst_null
    //   42: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   45: athrow
    //   46: astore_0
    //   47: ldc com/google/android/gms/dynamite/DynamiteModule
    //   49: monitorexit
    //   50: aload_0
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	46	finally
    //   47	50	46	finally
  }
  
  private static void zza(ClassLoader paramClassLoader) throws zzc {
    try {
      IInterface iInterface;
      IBinder iBinder = paramClassLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
      if (iBinder == null) {
        paramClassLoader = null;
      } else {
        iInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
        if (iInterface instanceof zzm) {
          iInterface = iInterface;
        } else {
          iInterface = new zzn(iBinder);
        } 
      } 
      zzgpk = (zzm)iInterface;
      return;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new zzc("Failed to instantiate dynamite loader", noSuchMethodException, null);
  }
  
  public static int zzad(Context paramContext, String paramString) {
    try {
      StringBuilder stringBuilder2;
      ClassLoader classLoader = paramContext.getApplicationContext().getClassLoader();
      int i = "com.google.android.gms.dynamite.descriptors.".length();
      int j = String.valueOf(paramString).length();
      null = "ModuleDescriptor".length();
      StringBuilder stringBuilder1 = new StringBuilder();
      this(i + 1 + j + null);
      stringBuilder1.append("com.google.android.gms.dynamite.descriptors.");
      stringBuilder1.append(paramString);
      stringBuilder1.append(".");
      stringBuilder1.append("ModuleDescriptor");
      Class<?> clazz = classLoader.loadClass(stringBuilder1.toString());
      Field field1 = clazz.getDeclaredField("MODULE_ID");
      Field field2 = clazz.getDeclaredField("MODULE_VERSION");
      if (!field1.get(null).equals(paramString)) {
        String str = String.valueOf(field1.get(null));
        i = str.length();
        null = String.valueOf(paramString).length();
        stringBuilder2 = new StringBuilder();
        this(i + 51 + null);
        stringBuilder2.append("Module descriptor id '");
        stringBuilder2.append(str);
        stringBuilder2.append("' didn't match expected id '");
        stringBuilder2.append(paramString);
        stringBuilder2.append("'");
        Log.e("DynamiteModule", stringBuilder2.toString());
        return 0;
      } 
      return stringBuilder2.getInt(null);
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 45);
      stringBuilder.append("Local module descriptor class for ");
      stringBuilder.append(paramString);
      stringBuilder.append(" not found.");
      Log.w("DynamiteModule", stringBuilder.toString());
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        str = "Failed to load module descriptor class: ".concat(str);
      } else {
        str = new String("Failed to load module descriptor class: ");
      } 
      Log.e("DynamiteModule", str);
    } 
    return 0;
  }
  
  public static int zzae(Context paramContext, String paramString) {
    return zzb(paramContext, paramString, false);
  }
  
  private static DynamiteModule zzaf(Context paramContext, String paramString) {
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Selected local version of ".concat(paramString);
    } else {
      paramString = new String("Selected local version of ");
    } 
    Log.i("DynamiteModule", paramString);
    return new DynamiteModule(paramContext.getApplicationContext());
  }
  
  public static int zzb(Context paramContext, String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpi : Ljava/lang/Boolean;
    //   6: astore_3
    //   7: aload_3
    //   8: astore #4
    //   10: aload_3
    //   11: ifnonnull -> 296
    //   14: aload_0
    //   15: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   18: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   21: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   23: invokevirtual getName : ()Ljava/lang/String;
    //   26: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   29: astore_3
    //   30: aload_3
    //   31: ldc_w 'sClassLoader'
    //   34: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   37: astore #4
    //   39: aload_3
    //   40: monitorenter
    //   41: aload #4
    //   43: aconst_null
    //   44: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   47: checkcast java/lang/ClassLoader
    //   50: astore #5
    //   52: aload #5
    //   54: ifnull -> 86
    //   57: aload #5
    //   59: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   62: if_acmpne -> 73
    //   65: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   68: astore #4
    //   70: goto -> 208
    //   73: aload #5
    //   75: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   78: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   81: astore #4
    //   83: goto -> 208
    //   86: ldc_w 'com.google.android.gms'
    //   89: aload_0
    //   90: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   93: invokevirtual getPackageName : ()Ljava/lang/String;
    //   96: invokevirtual equals : (Ljava/lang/Object;)Z
    //   99: ifeq -> 114
    //   102: aload #4
    //   104: aconst_null
    //   105: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   108: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   111: goto -> 65
    //   114: aload_0
    //   115: aload_1
    //   116: iload_2
    //   117: invokestatic zzd : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   120: istore #6
    //   122: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpl : Ljava/lang/String;
    //   125: astore #5
    //   127: aload #5
    //   129: ifnull -> 186
    //   132: aload #5
    //   134: invokevirtual isEmpty : ()Z
    //   137: ifeq -> 143
    //   140: goto -> 186
    //   143: new com/google/android/gms/dynamite/zzh
    //   146: astore #5
    //   148: aload #5
    //   150: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpl : Ljava/lang/String;
    //   153: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   156: invokespecial <init> : (Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   159: aload #5
    //   161: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   164: aload #4
    //   166: aconst_null
    //   167: aload #5
    //   169: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   172: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   175: putstatic com/google/android/gms/dynamite/DynamiteModule.zzgpi : Ljava/lang/Boolean;
    //   178: aload_3
    //   179: monitorexit
    //   180: ldc com/google/android/gms/dynamite/DynamiteModule
    //   182: monitorexit
    //   183: iload #6
    //   185: ireturn
    //   186: aload_3
    //   187: monitorexit
    //   188: ldc com/google/android/gms/dynamite/DynamiteModule
    //   190: monitorexit
    //   191: iload #6
    //   193: ireturn
    //   194: astore #5
    //   196: aload #4
    //   198: aconst_null
    //   199: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   202: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   205: goto -> 65
    //   208: aload_3
    //   209: monitorexit
    //   210: goto -> 291
    //   213: astore #4
    //   215: aload_3
    //   216: monitorexit
    //   217: aload #4
    //   219: athrow
    //   220: astore #4
    //   222: goto -> 232
    //   225: astore #4
    //   227: goto -> 232
    //   230: astore #4
    //   232: aload #4
    //   234: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   237: astore_3
    //   238: aload_3
    //   239: invokevirtual length : ()I
    //   242: istore #6
    //   244: new java/lang/StringBuilder
    //   247: astore #4
    //   249: aload #4
    //   251: iload #6
    //   253: bipush #30
    //   255: iadd
    //   256: invokespecial <init> : (I)V
    //   259: aload #4
    //   261: ldc_w 'Failed to load module via V2: '
    //   264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload #4
    //   270: aload_3
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: ldc 'DynamiteModule'
    //   277: aload #4
    //   279: invokevirtual toString : ()Ljava/lang/String;
    //   282: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   289: astore #4
    //   291: aload #4
    //   293: putstatic com/google/android/gms/dynamite/DynamiteModule.zzgpi : Ljava/lang/Boolean;
    //   296: ldc com/google/android/gms/dynamite/DynamiteModule
    //   298: monitorexit
    //   299: aload #4
    //   301: invokevirtual booleanValue : ()Z
    //   304: ifeq -> 365
    //   307: aload_0
    //   308: aload_1
    //   309: iload_2
    //   310: invokestatic zzd : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   313: istore #6
    //   315: iload #6
    //   317: ireturn
    //   318: astore_0
    //   319: aload_0
    //   320: invokevirtual getMessage : ()Ljava/lang/String;
    //   323: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   326: astore_0
    //   327: aload_0
    //   328: invokevirtual length : ()I
    //   331: ifeq -> 345
    //   334: ldc_w 'Failed to retrieve remote module version: '
    //   337: aload_0
    //   338: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   341: astore_0
    //   342: goto -> 356
    //   345: new java/lang/String
    //   348: dup
    //   349: ldc_w 'Failed to retrieve remote module version: '
    //   352: invokespecial <init> : (Ljava/lang/String;)V
    //   355: astore_0
    //   356: ldc 'DynamiteModule'
    //   358: aload_0
    //   359: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   362: pop
    //   363: iconst_0
    //   364: ireturn
    //   365: aload_0
    //   366: aload_1
    //   367: iload_2
    //   368: invokestatic zzc : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   371: ireturn
    //   372: astore_0
    //   373: ldc com/google/android/gms/dynamite/DynamiteModule
    //   375: monitorexit
    //   376: goto -> 381
    //   379: aload_0
    //   380: athrow
    //   381: goto -> 379
    //   384: astore #4
    //   386: goto -> 78
    // Exception table:
    //   from	to	target	type
    //   3	7	372	finally
    //   14	41	230	java/lang/ClassNotFoundException
    //   14	41	225	java/lang/IllegalAccessException
    //   14	41	220	java/lang/NoSuchFieldException
    //   14	41	372	finally
    //   41	52	213	finally
    //   57	65	213	finally
    //   65	70	213	finally
    //   73	78	384	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   73	78	213	finally
    //   78	83	213	finally
    //   86	111	213	finally
    //   114	127	194	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   114	127	213	finally
    //   132	140	194	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   132	140	213	finally
    //   143	178	194	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   143	178	213	finally
    //   178	180	213	finally
    //   180	183	372	finally
    //   186	188	213	finally
    //   188	191	372	finally
    //   196	205	213	finally
    //   208	210	213	finally
    //   215	217	213	finally
    //   217	220	230	java/lang/ClassNotFoundException
    //   217	220	225	java/lang/IllegalAccessException
    //   217	220	220	java/lang/NoSuchFieldException
    //   217	220	372	finally
    //   232	291	372	finally
    //   291	296	372	finally
    //   296	299	372	finally
    //   307	315	318	com/google/android/gms/dynamite/DynamiteModule$zzc
    //   373	376	372	finally
  }
  
  private static DynamiteModule zzb(Context paramContext, String paramString, int paramInt) throws zzc {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 51);
    stringBuilder.append("Selected remote version of ");
    stringBuilder.append(paramString);
    stringBuilder.append(", version >= ");
    stringBuilder.append(paramInt);
    Log.i("DynamiteModule", stringBuilder.toString());
    zzk zzk1 = zzcv(paramContext);
    if (zzk1 != null)
      try {
        IObjectWrapper iObjectWrapper = zzk1.zza(zzn.zzw(paramContext), paramString, paramInt);
        if (zzn.zzx(iObjectWrapper) != null)
          return new DynamiteModule((Context)zzn.zzx(iObjectWrapper)); 
        throw new zzc("Failed to load remote module.", null);
      } catch (RemoteException remoteException) {
        throw new zzc("Failed to load remote module.", remoteException, null);
      }  
    throw new zzc("Failed to create IDynamiteLoader.", null);
  }
  
  private static int zzc(Context paramContext, String paramString, boolean paramBoolean) {
    zzk zzk1 = zzcv(paramContext);
    if (zzk1 == null)
      return 0; 
    try {
      return zzk1.zza(zzn.zzw(paramContext), paramString, paramBoolean);
    } catch (RemoteException remoteException) {
      String str = String.valueOf(remoteException.getMessage());
      if (str.length() != 0) {
        str = "Failed to retrieve remote module version: ".concat(str);
      } else {
        str = new String("Failed to retrieve remote module version: ");
      } 
      Log.w("DynamiteModule", str);
      return 0;
    } 
  }
  
  private static DynamiteModule zzc(Context paramContext, String paramString, int paramInt) throws zzc {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: aload_1
    //   5: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   8: invokevirtual length : ()I
    //   11: bipush #51
    //   13: iadd
    //   14: invokespecial <init> : (I)V
    //   17: astore_3
    //   18: aload_3
    //   19: ldc_w 'Selected remote version of '
    //   22: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_3
    //   27: aload_1
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_3
    //   33: ldc_w ', version >= '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_3
    //   41: iload_2
    //   42: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: ldc 'DynamiteModule'
    //   48: aload_3
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: ldc com/google/android/gms/dynamite/DynamiteModule
    //   58: monitorenter
    //   59: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpk : Lcom/google/android/gms/dynamite/zzm;
    //   62: astore #4
    //   64: ldc com/google/android/gms/dynamite/DynamiteModule
    //   66: monitorexit
    //   67: aload #4
    //   69: ifnull -> 146
    //   72: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpm : Ljava/lang/ThreadLocal;
    //   75: invokevirtual get : ()Ljava/lang/Object;
    //   78: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   81: astore_3
    //   82: aload_3
    //   83: ifnull -> 134
    //   86: aload_3
    //   87: getfield zzgpv : Landroid/database/Cursor;
    //   90: ifnull -> 134
    //   93: aload_0
    //   94: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   97: aload_1
    //   98: iload_2
    //   99: aload_3
    //   100: getfield zzgpv : Landroid/database/Cursor;
    //   103: aload #4
    //   105: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;ILandroid/database/Cursor;Lcom/google/android/gms/dynamite/zzm;)Landroid/content/Context;
    //   108: astore_0
    //   109: aload_0
    //   110: ifnull -> 122
    //   113: new com/google/android/gms/dynamite/DynamiteModule
    //   116: dup
    //   117: aload_0
    //   118: invokespecial <init> : (Landroid/content/Context;)V
    //   121: areturn
    //   122: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   125: dup
    //   126: ldc_w 'Failed to get module context'
    //   129: aconst_null
    //   130: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   133: athrow
    //   134: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   137: dup
    //   138: ldc_w 'No result cursor'
    //   141: aconst_null
    //   142: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   145: athrow
    //   146: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   149: dup
    //   150: ldc_w 'DynamiteLoaderV2 was not cached.'
    //   153: aconst_null
    //   154: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   157: athrow
    //   158: astore_0
    //   159: ldc com/google/android/gms/dynamite/DynamiteModule
    //   161: monitorexit
    //   162: aload_0
    //   163: athrow
    // Exception table:
    //   from	to	target	type
    //   59	67	158	finally
    //   159	162	158	finally
  }
  
  private static zzk zzcv(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpj : Lcom/google/android/gms/dynamite/zzk;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 16
    //   11: ldc com/google/android/gms/dynamite/DynamiteModule
    //   13: monitorexit
    //   14: aload_1
    //   15: areturn
    //   16: invokestatic zzaex : ()Lcom/google/android/gms/common/zze;
    //   19: aload_0
    //   20: invokevirtual isGooglePlayServicesAvailable : (Landroid/content/Context;)I
    //   23: ifeq -> 31
    //   26: ldc com/google/android/gms/dynamite/DynamiteModule
    //   28: monitorexit
    //   29: aconst_null
    //   30: areturn
    //   31: aload_0
    //   32: ldc_w 'com.google.android.gms'
    //   35: iconst_3
    //   36: invokevirtual createPackageContext : (Ljava/lang/String;I)Landroid/content/Context;
    //   39: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   42: ldc_w 'com.google.android.gms.chimera.container.DynamiteLoaderImpl'
    //   45: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   48: invokevirtual newInstance : ()Ljava/lang/Object;
    //   51: checkcast android/os/IBinder
    //   54: astore_1
    //   55: aload_1
    //   56: ifnonnull -> 64
    //   59: aconst_null
    //   60: astore_0
    //   61: goto -> 98
    //   64: aload_1
    //   65: ldc_w 'com.google.android.gms.dynamite.IDynamiteLoader'
    //   68: invokeinterface queryLocalInterface : (Ljava/lang/String;)Landroid/os/IInterface;
    //   73: astore_0
    //   74: aload_0
    //   75: instanceof com/google/android/gms/dynamite/zzk
    //   78: ifeq -> 89
    //   81: aload_0
    //   82: checkcast com/google/android/gms/dynamite/zzk
    //   85: astore_0
    //   86: goto -> 98
    //   89: new com/google/android/gms/dynamite/zzl
    //   92: dup
    //   93: aload_1
    //   94: invokespecial <init> : (Landroid/os/IBinder;)V
    //   97: astore_0
    //   98: aload_0
    //   99: ifnull -> 156
    //   102: aload_0
    //   103: putstatic com/google/android/gms/dynamite/DynamiteModule.zzgpj : Lcom/google/android/gms/dynamite/zzk;
    //   106: ldc com/google/android/gms/dynamite/DynamiteModule
    //   108: monitorexit
    //   109: aload_0
    //   110: areturn
    //   111: astore_0
    //   112: aload_0
    //   113: invokevirtual getMessage : ()Ljava/lang/String;
    //   116: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   119: astore_0
    //   120: aload_0
    //   121: invokevirtual length : ()I
    //   124: ifeq -> 138
    //   127: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   130: aload_0
    //   131: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   134: astore_0
    //   135: goto -> 149
    //   138: new java/lang/String
    //   141: dup
    //   142: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   145: invokespecial <init> : (Ljava/lang/String;)V
    //   148: astore_0
    //   149: ldc 'DynamiteModule'
    //   151: aload_0
    //   152: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   155: pop
    //   156: ldc com/google/android/gms/dynamite/DynamiteModule
    //   158: monitorexit
    //   159: aconst_null
    //   160: areturn
    //   161: astore_0
    //   162: ldc com/google/android/gms/dynamite/DynamiteModule
    //   164: monitorexit
    //   165: aload_0
    //   166: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	161	finally
    //   11	14	161	finally
    //   16	29	161	finally
    //   31	55	111	java/lang/Exception
    //   31	55	161	finally
    //   64	86	111	java/lang/Exception
    //   64	86	161	finally
    //   89	98	111	java/lang/Exception
    //   89	98	161	finally
    //   102	106	111	java/lang/Exception
    //   102	106	161	finally
    //   106	109	161	finally
    //   112	135	161	finally
    //   138	149	161	finally
    //   149	156	161	finally
    //   156	159	161	finally
    //   162	165	161	finally
  }
  
  private static int zzd(Context paramContext, String paramString, boolean paramBoolean) throws zzc {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore #4
    //   5: iload_2
    //   6: ifeq -> 17
    //   9: ldc_w 'api_force_staging'
    //   12: astore #5
    //   14: goto -> 22
    //   17: ldc_w 'api'
    //   20: astore #5
    //   22: ldc_w 'content://com.google.android.gms.chimera/'
    //   25: invokevirtual length : ()I
    //   28: istore #6
    //   30: aload #5
    //   32: invokevirtual length : ()I
    //   35: istore #7
    //   37: aload_1
    //   38: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   41: invokevirtual length : ()I
    //   44: istore #8
    //   46: new java/lang/StringBuilder
    //   49: astore #9
    //   51: aload #9
    //   53: iload #6
    //   55: iconst_1
    //   56: iadd
    //   57: iload #7
    //   59: iadd
    //   60: iload #8
    //   62: iadd
    //   63: invokespecial <init> : (I)V
    //   66: aload #9
    //   68: ldc_w 'content://com.google.android.gms.chimera/'
    //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload #9
    //   77: aload #5
    //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload #9
    //   85: ldc_w '/'
    //   88: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload #9
    //   94: aload_1
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #9
    //   101: invokevirtual toString : ()Ljava/lang/String;
    //   104: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   107: astore_1
    //   108: aload_0
    //   109: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   112: aload_1
    //   113: aconst_null
    //   114: aconst_null
    //   115: aconst_null
    //   116: aconst_null
    //   117: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore_0
    //   121: aload_0
    //   122: ifnull -> 215
    //   125: aload_0
    //   126: invokeinterface moveToFirst : ()Z
    //   131: ifeq -> 215
    //   134: aload_0
    //   135: iconst_0
    //   136: invokeinterface getInt : (I)I
    //   141: istore #6
    //   143: iload #6
    //   145: ifle -> 202
    //   148: ldc com/google/android/gms/dynamite/DynamiteModule
    //   150: monitorenter
    //   151: aload_0
    //   152: iconst_2
    //   153: invokeinterface getString : (I)Ljava/lang/String;
    //   158: putstatic com/google/android/gms/dynamite/DynamiteModule.zzgpl : Ljava/lang/String;
    //   161: ldc com/google/android/gms/dynamite/DynamiteModule
    //   163: monitorexit
    //   164: getstatic com/google/android/gms/dynamite/DynamiteModule.zzgpm : Ljava/lang/ThreadLocal;
    //   167: invokevirtual get : ()Ljava/lang/Object;
    //   170: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   173: astore_1
    //   174: aload_1
    //   175: ifnull -> 202
    //   178: aload_1
    //   179: getfield zzgpv : Landroid/database/Cursor;
    //   182: ifnonnull -> 202
    //   185: aload_1
    //   186: aload_0
    //   187: putfield zzgpv : Landroid/database/Cursor;
    //   190: aload #4
    //   192: astore_0
    //   193: goto -> 202
    //   196: astore_1
    //   197: ldc com/google/android/gms/dynamite/DynamiteModule
    //   199: monitorexit
    //   200: aload_1
    //   201: athrow
    //   202: aload_0
    //   203: ifnull -> 212
    //   206: aload_0
    //   207: invokeinterface close : ()V
    //   212: iload #6
    //   214: ireturn
    //   215: ldc 'DynamiteModule'
    //   217: ldc_w 'Failed to retrieve remote module version.'
    //   220: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   223: pop
    //   224: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   227: astore_1
    //   228: aload_1
    //   229: ldc_w 'Failed to connect to dynamite module ContentResolver.'
    //   232: aconst_null
    //   233: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   236: aload_1
    //   237: athrow
    //   238: astore_1
    //   239: goto -> 283
    //   242: astore_1
    //   243: goto -> 255
    //   246: astore_1
    //   247: aload_3
    //   248: astore_0
    //   249: goto -> 283
    //   252: astore_1
    //   253: aconst_null
    //   254: astore_0
    //   255: aload_1
    //   256: instanceof com/google/android/gms/dynamite/DynamiteModule$zzc
    //   259: ifeq -> 264
    //   262: aload_1
    //   263: athrow
    //   264: new com/google/android/gms/dynamite/DynamiteModule$zzc
    //   267: astore #5
    //   269: aload #5
    //   271: ldc_w 'V2 version check failed'
    //   274: aload_1
    //   275: aconst_null
    //   276: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   279: aload #5
    //   281: athrow
    //   282: astore_1
    //   283: aload_0
    //   284: ifnull -> 293
    //   287: aload_0
    //   288: invokeinterface close : ()V
    //   293: aload_1
    //   294: athrow
    // Exception table:
    //   from	to	target	type
    //   22	121	252	java/lang/Exception
    //   22	121	246	finally
    //   125	143	242	java/lang/Exception
    //   125	143	238	finally
    //   148	151	242	java/lang/Exception
    //   148	151	238	finally
    //   151	164	196	finally
    //   164	174	242	java/lang/Exception
    //   164	174	238	finally
    //   178	190	242	java/lang/Exception
    //   178	190	238	finally
    //   197	200	196	finally
    //   200	202	242	java/lang/Exception
    //   200	202	238	finally
    //   215	238	242	java/lang/Exception
    //   215	238	238	finally
    //   255	264	282	finally
    //   264	282	282	finally
  }
  
  public final Context zzaog() {
    return this.zzgpu;
  }
  
  public final IBinder zzgv(String paramString) throws zzc {
    try {
      return (IBinder)this.zzgpu.getClassLoader().loadClass(paramString).newInstance();
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (IllegalAccessException illegalAccessException) {}
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Failed to instantiate module class: ".concat(paramString);
    } else {
      paramString = new String("Failed to instantiate module class: ");
    } 
    throw new zzc(paramString, illegalAccessException, null);
  }
  
  @DynamiteApi
  public static class DynamiteLoaderClassLoader {
    public static ClassLoader sClassLoader;
  }
  
  static final class zza {
    public Cursor zzgpv;
    
    private zza() {}
  }
  
  static final class zzb implements zzi {
    private final int zzgpw;
    
    private final int zzgpx;
    
    public zzb(int param1Int1, int param1Int2) {
      this.zzgpw = param1Int1;
      this.zzgpx = 0;
    }
    
    public final int zzad(Context param1Context, String param1String) {
      return this.zzgpw;
    }
    
    public final int zzb(Context param1Context, String param1String, boolean param1Boolean) {
      return 0;
    }
  }
  
  public static final class zzc extends Exception {
    private zzc(String param1String) {
      super(param1String);
    }
    
    private zzc(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
  
  public static interface zzd {
    zzj zza(Context param1Context, String param1String, zzi param1zzi) throws DynamiteModule.zzc;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamite/DynamiteModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */