package crc64125a4b40fed45ff3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import java.util.ArrayList;
import mono.MonoPackageManager;
import mono.android.IGCUserPeer;

public class MainApplication extends MultiDexApplication implements IGCUserPeer, Application.ActivityLifecycleCallbacks {
  public static final String __md_methods = "n_onCreate:()V:GetOnCreateHandler\nn_onTerminate:()V:GetOnTerminateHandler\nn_onActivityCreated:(Landroid/app/Activity;Landroid/os/Bundle;)V:GetOnActivityCreated_Landroid_app_Activity_Landroid_os_Bundle_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivityDestroyed:(Landroid/app/Activity;)V:GetOnActivityDestroyed_Landroid_app_Activity_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivityPaused:(Landroid/app/Activity;)V:GetOnActivityPaused_Landroid_app_Activity_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivityResumed:(Landroid/app/Activity;)V:GetOnActivityResumed_Landroid_app_Activity_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivitySaveInstanceState:(Landroid/app/Activity;Landroid/os/Bundle;)V:GetOnActivitySaveInstanceState_Landroid_app_Activity_Landroid_os_Bundle_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivityStarted:(Landroid/app/Activity;)V:GetOnActivityStarted_Landroid_app_Activity_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActivityStopped:(Landroid/app/Activity;)V:GetOnActivityStopped_Landroid_app_Activity_Handler:Android.App.Application/IActivityLifecycleCallbacksInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  public MainApplication() {
    MonoPackageManager.setContext((Context)this);
  }
  
  private native void n_onActivityCreated(Activity paramActivity, Bundle paramBundle);
  
  private native void n_onActivityDestroyed(Activity paramActivity);
  
  private native void n_onActivityPaused(Activity paramActivity);
  
  private native void n_onActivityResumed(Activity paramActivity);
  
  private native void n_onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle);
  
  private native void n_onActivityStarted(Activity paramActivity);
  
  private native void n_onActivityStopped(Activity paramActivity);
  
  private native void n_onCreate();
  
  private native void n_onTerminate();
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    n_onActivityCreated(paramActivity, paramBundle);
  }
  
  public void onActivityDestroyed(Activity paramActivity) {
    n_onActivityDestroyed(paramActivity);
  }
  
  public void onActivityPaused(Activity paramActivity) {
    n_onActivityPaused(paramActivity);
  }
  
  public void onActivityResumed(Activity paramActivity) {
    n_onActivityResumed(paramActivity);
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    n_onActivitySaveInstanceState(paramActivity, paramBundle);
  }
  
  public void onActivityStarted(Activity paramActivity) {
    n_onActivityStarted(paramActivity);
  }
  
  public void onActivityStopped(Activity paramActivity) {
    n_onActivityStopped(paramActivity);
  }
  
  public void onCreate() {
    n_onCreate();
  }
  
  public void onTerminate() {
    n_onTerminate();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64125a4b40fed45ff3/MainApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */