package mono.android;

import java.util.TimeZone;

public class Runtime {
  static Class java_lang_Class = Class.class;
  
  static Class java_lang_System = System.class;
  
  static Class java_util_TimeZone = TimeZone.class;
  
  static Class mono_android_GCUserPeer;
  
  static Class mono_android_IGCUserPeer = IGCUserPeer.class;
  
  static {
    mono_android_GCUserPeer = GCUserPeer.class;
    Thread.setDefaultUncaughtExceptionHandler(new XamarinUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
  }
  
  public static native int createNewContext(String[] paramArrayOfString1, String[] paramArrayOfString2, ClassLoader paramClassLoader);
  
  public static native int createNewContextWithData(String[] paramArrayOfString1, String[] paramArrayOfString2, byte[][] paramArrayOfbyte, String[] paramArrayOfString3, ClassLoader paramClassLoader, boolean paramBoolean);
  
  public static native void destroyContexts(int[] paramArrayOfint);
  
  public static native void dumpTimingData();
  
  public static native void init(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, ClassLoader paramClassLoader, String[] paramArrayOfString3, String[] paramArrayOfString4, String paramString3, int paramInt, String[] paramArrayOfString5);
  
  public static native void initInternal(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, ClassLoader paramClassLoader, String[] paramArrayOfString3, int paramInt, boolean paramBoolean1, boolean paramBoolean2);
  
  public static native void notifyTimeZoneChanged();
  
  public static native void propagateUncaughtException(Thread paramThread, Throwable paramThrowable);
  
  public static native void register(String paramString1, Class paramClass, String paramString2);
  
  public static native void switchToContext(int paramInt);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/Runtime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */