package mono;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

public class MonoPackageManager {
  static Context Context;
  
  static boolean initialized;
  
  static Object lock = new Object();
  
  public static void LoadApplication(Context paramContext, ApplicationInfo paramApplicationInfo, String[] paramArrayOfString) {
    // Byte code:
    //   0: getstatic mono/MonoPackageManager.lock : Ljava/lang/Object;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: aload_0
    //   7: instanceof android/app/Application
    //   10: ifeq -> 17
    //   13: aload_0
    //   14: putstatic mono/MonoPackageManager.Context : Landroid/content/Context;
    //   17: getstatic mono/MonoPackageManager.initialized : Z
    //   20: ifne -> 286
    //   23: new android/content/IntentFilter
    //   26: astore #4
    //   28: aload #4
    //   30: ldc 'android.intent.action.TIMEZONE_CHANGED'
    //   32: invokespecial <init> : (Ljava/lang/String;)V
    //   35: new mono/android/app/NotifyTimeZoneChanges
    //   38: astore #5
    //   40: aload #5
    //   42: invokespecial <init> : ()V
    //   45: aload_0
    //   46: aload #5
    //   48: aload #4
    //   50: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   53: pop
    //   54: invokestatic getDefault : ()Ljava/util/Locale;
    //   57: astore #5
    //   59: new java/lang/StringBuilder
    //   62: astore #4
    //   64: aload #4
    //   66: invokespecial <init> : ()V
    //   69: aload #4
    //   71: aload #5
    //   73: invokevirtual getLanguage : ()Ljava/lang/String;
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload #4
    //   82: ldc '-'
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload #4
    //   90: aload #5
    //   92: invokevirtual getCountry : ()Ljava/lang/String;
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #4
    //   101: invokevirtual toString : ()Ljava/lang/String;
    //   104: astore #5
    //   106: aload_0
    //   107: invokevirtual getFilesDir : ()Ljava/io/File;
    //   110: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   113: astore #6
    //   115: aload_0
    //   116: invokevirtual getCacheDir : ()Ljava/io/File;
    //   119: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   122: astore #4
    //   124: aload_0
    //   125: invokestatic getNativeLibraryPath : (Landroid/content/Context;)Ljava/lang/String;
    //   128: astore #7
    //   130: aload_0
    //   131: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   134: astore #8
    //   136: aload_1
    //   137: invokestatic getNativeLibraryPath : (Landroid/content/pm/ApplicationInfo;)Ljava/lang/String;
    //   140: astore #9
    //   142: iconst_3
    //   143: anewarray java/lang/String
    //   146: astore_0
    //   147: iconst_0
    //   148: istore #10
    //   150: aload_0
    //   151: iconst_0
    //   152: aload #6
    //   154: aastore
    //   155: aload_0
    //   156: iconst_1
    //   157: aload #4
    //   159: aastore
    //   160: aload_0
    //   161: iconst_2
    //   162: aload #7
    //   164: aastore
    //   165: getstatic android/os/Build$VERSION.SDK_INT : I
    //   168: istore #11
    //   170: iload #11
    //   172: bipush #21
    //   174: if_icmplt -> 198
    //   177: aload_1
    //   178: getfield splitSourceDirs : [Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: ifnull -> 198
    //   186: aload_1
    //   187: arraylength
    //   188: iconst_1
    //   189: if_icmple -> 195
    //   192: iconst_1
    //   193: istore #10
    //   195: goto -> 201
    //   198: iconst_0
    //   199: istore #10
    //   201: getstatic mono/android/BuildConfig.Debug : Z
    //   204: ifeq -> 224
    //   207: ldc 'xamarin-debug-app-helper'
    //   209: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   212: aload_2
    //   213: aload #9
    //   215: aload_0
    //   216: iload #10
    //   218: invokestatic init : ([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Z)V
    //   221: goto -> 229
    //   224: ldc 'monosgen-2.0'
    //   226: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   229: ldc 'xamarin-app'
    //   231: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   234: getstatic mono/android/BuildConfig.DotNetRuntime : Z
    //   237: ifne -> 248
    //   240: ldc 'mono-native'
    //   242: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   245: goto -> 253
    //   248: ldc 'System.Security.Cryptography.Native.Android'
    //   250: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   253: ldc 'monodroid'
    //   255: invokestatic loadLibrary : (Ljava/lang/String;)V
    //   258: aload #5
    //   260: aload_2
    //   261: aload #9
    //   263: aload_0
    //   264: aload #8
    //   266: getstatic mono/MonoPackageManager_Resources.Assemblies : [Ljava/lang/String;
    //   269: iload #11
    //   271: invokestatic isEmulator : ()Z
    //   274: iload #10
    //   276: invokestatic initInternal : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/ClassLoader;[Ljava/lang/String;IZZ)V
    //   279: invokestatic registerApplications : ()V
    //   282: iconst_1
    //   283: putstatic mono/MonoPackageManager.initialized : Z
    //   286: aload_3
    //   287: monitorexit
    //   288: return
    //   289: astore_0
    //   290: aload_3
    //   291: monitorexit
    //   292: aload_0
    //   293: athrow
    // Exception table:
    //   from	to	target	type
    //   6	17	289	finally
    //   17	147	289	finally
    //   165	170	289	finally
    //   177	182	289	finally
    //   186	192	289	finally
    //   201	221	289	finally
    //   224	229	289	finally
    //   229	245	289	finally
    //   248	253	289	finally
    //   253	286	289	finally
    //   286	288	289	finally
    //   290	292	289	finally
  }
  
  public static String[] getAssemblies() {
    return MonoPackageManager_Resources.Assemblies;
  }
  
  public static String[] getDependencies() {
    return MonoPackageManager_Resources.Dependencies;
  }
  
  static String getNativeLibraryPath(Context paramContext) {
    return getNativeLibraryPath(paramContext.getApplicationInfo());
  }
  
  static String getNativeLibraryPath(ApplicationInfo paramApplicationInfo) {
    return paramApplicationInfo.nativeLibraryDir;
  }
  
  static boolean isEmulator() {
    String str = Build.HARDWARE;
    return (str.contains("ranchu") || str.contains("goldfish"));
  }
  
  public static void setContext(Context paramContext) {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/MonoPackageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */