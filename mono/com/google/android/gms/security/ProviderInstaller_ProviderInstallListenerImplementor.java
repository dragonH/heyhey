package mono.com.google.android.gms.security;

import android.content.Intent;
import com.google.android.gms.security.ProviderInstaller;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ProviderInstaller_ProviderInstallListenerImplementor implements IGCUserPeer, ProviderInstaller.ProviderInstallListener {
  public static final String __md_methods = "n_onProviderInstallFailed:(ILandroid/content/Intent;)V:GetOnProviderInstallFailed_ILandroid_content_Intent_Handler:Android.Gms.Security.ProviderInstaller/IProviderInstallListenerInvoker, Xamarin.GooglePlayServices.Basement\nn_onProviderInstalled:()V:GetOnProviderInstalledHandler:Android.Gms.Security.ProviderInstaller/IProviderInstallListenerInvoker, Xamarin.GooglePlayServices.Basement\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Security.ProviderInstaller+IProviderInstallListenerImplementor, Xamarin.GooglePlayServices.Basement", ProviderInstaller_ProviderInstallListenerImplementor.class, "n_onProviderInstallFailed:(ILandroid/content/Intent;)V:GetOnProviderInstallFailed_ILandroid_content_Intent_Handler:Android.Gms.Security.ProviderInstaller/IProviderInstallListenerInvoker, Xamarin.GooglePlayServices.Basement\nn_onProviderInstalled:()V:GetOnProviderInstalledHandler:Android.Gms.Security.ProviderInstaller/IProviderInstallListenerInvoker, Xamarin.GooglePlayServices.Basement\n");
  }
  
  public ProviderInstaller_ProviderInstallListenerImplementor() {
    if (getClass() == ProviderInstaller_ProviderInstallListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Security.ProviderInstaller+IProviderInstallListenerImplementor, Xamarin.GooglePlayServices.Basement", "", this, new Object[0]); 
  }
  
  private native void n_onProviderInstallFailed(int paramInt, Intent paramIntent);
  
  private native void n_onProviderInstalled();
  
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
  
  public void onProviderInstallFailed(int paramInt, Intent paramIntent) {
    n_onProviderInstallFailed(paramInt, paramIntent);
  }
  
  public void onProviderInstalled() {
    n_onProviderInstalled();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/security/ProviderInstaller_ProviderInstallListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */