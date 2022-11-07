package mono.android.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor implements IGCUserPeer, AccessibilityService.SoftKeyboardController.OnShowModeChangedListener {
  public static final String __md_methods = "n_onShowModeChanged:(Landroid/accessibilityservice/AccessibilityService$SoftKeyboardController;I)V:GetOnShowModeChanged_Landroid_accessibilityservice_AccessibilityService_SoftKeyboardController_IHandler:Android.AccessibilityServices.AccessibilityService/SoftKeyboardController/IOnShowModeChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.AccessibilityServices.AccessibilityService+SoftKeyboardController+IOnShowModeChangedListenerImplementor, Mono.Android", AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor.class, "n_onShowModeChanged:(Landroid/accessibilityservice/AccessibilityService$SoftKeyboardController;I)V:GetOnShowModeChanged_Landroid_accessibilityservice_AccessibilityService_SoftKeyboardController_IHandler:Android.AccessibilityServices.AccessibilityService/SoftKeyboardController/IOnShowModeChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor() {
    if (getClass() == AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor.class)
      TypeManager.Activate("Android.AccessibilityServices.AccessibilityService+SoftKeyboardController+IOnShowModeChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onShowModeChanged(AccessibilityService.SoftKeyboardController paramSoftKeyboardController, int paramInt);
  
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
  
  public void onShowModeChanged(AccessibilityService.SoftKeyboardController paramSoftKeyboardController, int paramInt) {
    n_onShowModeChanged(paramSoftKeyboardController, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/accessibilityservice/AccessibilityService_SoftKeyboardController_OnShowModeChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */