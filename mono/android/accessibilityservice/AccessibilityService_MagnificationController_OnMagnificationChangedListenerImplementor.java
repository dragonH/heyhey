package mono.android.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Region;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AccessibilityService_MagnificationController_OnMagnificationChangedListenerImplementor implements IGCUserPeer, AccessibilityService.MagnificationController.OnMagnificationChangedListener {
  public static final String __md_methods = "n_onMagnificationChanged:(Landroid/accessibilityservice/AccessibilityService$MagnificationController;Landroid/graphics/Region;FFF)V:GetOnMagnificationChanged_Landroid_accessibilityservice_AccessibilityService_MagnificationController_Landroid_graphics_Region_FFFHandler:Android.AccessibilityServices.AccessibilityService/MagnificationController/IOnMagnificationChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.AccessibilityServices.AccessibilityService+MagnificationController+IOnMagnificationChangedListenerImplementor, Mono.Android", AccessibilityService_MagnificationController_OnMagnificationChangedListenerImplementor.class, "n_onMagnificationChanged:(Landroid/accessibilityservice/AccessibilityService$MagnificationController;Landroid/graphics/Region;FFF)V:GetOnMagnificationChanged_Landroid_accessibilityservice_AccessibilityService_MagnificationController_Landroid_graphics_Region_FFFHandler:Android.AccessibilityServices.AccessibilityService/MagnificationController/IOnMagnificationChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AccessibilityService_MagnificationController_OnMagnificationChangedListenerImplementor() {
    if (getClass() == AccessibilityService_MagnificationController_OnMagnificationChangedListenerImplementor.class)
      TypeManager.Activate("Android.AccessibilityServices.AccessibilityService+MagnificationController+IOnMagnificationChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMagnificationChanged(AccessibilityService.MagnificationController paramMagnificationController, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3);
  
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
  
  public void onMagnificationChanged(AccessibilityService.MagnificationController paramMagnificationController, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) {
    n_onMagnificationChanged(paramMagnificationController, paramRegion, paramFloat1, paramFloat2, paramFloat3);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/accessibilityservice/AccessibilityService_MagnificationController_OnMagnificationChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */