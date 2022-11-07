package mono.android.app;

import crc64125a4b40fed45ff3.MainApplication;
import mono.android.Runtime;

public class ApplicationRegistration {
  public static void registerApplications() {
    Runtime.register("ScsCloud.MainApplication, SCS Mobile APP, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", MainApplication.class, MainApplication.__md_methods);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/ApplicationRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */