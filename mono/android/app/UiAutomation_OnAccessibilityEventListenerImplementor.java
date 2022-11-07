package mono.android.app;

import android.app.UiAutomation;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class UiAutomation_OnAccessibilityEventListenerImplementor implements IGCUserPeer, UiAutomation.OnAccessibilityEventListener {
  public static final String __md_methods = "n_onAccessibilityEvent:(Landroid/view/accessibility/AccessibilityEvent;)V:GetOnAccessibilityEvent_Landroid_view_accessibility_AccessibilityEvent_Handler:Android.App.UiAutomation/IOnAccessibilityEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.UiAutomation+IOnAccessibilityEventListenerImplementor, Mono.Android", UiAutomation_OnAccessibilityEventListenerImplementor.class, "n_onAccessibilityEvent:(Landroid/view/accessibility/AccessibilityEvent;)V:GetOnAccessibilityEvent_Landroid_view_accessibility_AccessibilityEvent_Handler:Android.App.UiAutomation/IOnAccessibilityEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public UiAutomation_OnAccessibilityEventListenerImplementor() {
    if (getClass() == UiAutomation_OnAccessibilityEventListenerImplementor.class)
      TypeManager.Activate("Android.App.UiAutomation+IOnAccessibilityEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent);
  
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
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    n_onAccessibilityEvent(paramAccessibilityEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/UiAutomation_OnAccessibilityEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */