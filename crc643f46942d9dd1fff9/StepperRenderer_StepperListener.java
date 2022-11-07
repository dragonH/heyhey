package crc643f46942d9dd1fff9;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StepperRenderer_StepperListener implements IGCUserPeer, View.OnClickListener {
  public static final String __md_methods = "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.StepperRenderer+StepperListener, Xamarin.Forms.Platform.Android", StepperRenderer_StepperListener.class, "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public StepperRenderer_StepperListener() {
    if (getClass() == StepperRenderer_StepperListener.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.StepperRenderer+StepperListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_onClick(View paramView);
  
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
  
  public void onClick(View paramView) {
    n_onClick(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/StepperRenderer_StepperListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */