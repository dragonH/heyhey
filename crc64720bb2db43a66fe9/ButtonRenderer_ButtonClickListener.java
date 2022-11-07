package crc64720bb2db43a66fe9;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ButtonRenderer_ButtonClickListener implements IGCUserPeer, View.OnClickListener {
  public static final String __md_methods = "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.ButtonRenderer+ButtonClickListener, Xamarin.Forms.Platform.Android", ButtonRenderer_ButtonClickListener.class, "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ButtonRenderer_ButtonClickListener() {
    if (getClass() == ButtonRenderer_ButtonClickListener.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.ButtonRenderer+ButtonClickListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64720bb2db43a66fe9/ButtonRenderer_ButtonClickListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */