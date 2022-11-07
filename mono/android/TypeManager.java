package mono.android;

public class TypeManager {
  static {
    Runtime.register("Java.Interop.TypeManager+JavaTypeManager, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null", TypeManager.class, "n_activate:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)V:GetActivateHandler\n");
  }
  
  public static void Activate(String paramString1, String paramString2, Object paramObject, Object[] paramArrayOfObject) {
    n_activate(paramString1, paramString2, paramObject, paramArrayOfObject);
  }
  
  private static native void n_activate(String paramString1, String paramString2, Object paramObject, Object[] paramArrayOfObject);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/TypeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */