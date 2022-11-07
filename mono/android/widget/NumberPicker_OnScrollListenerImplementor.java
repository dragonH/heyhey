package mono.android.widget;

import android.widget.NumberPicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NumberPicker_OnScrollListenerImplementor implements IGCUserPeer, NumberPicker.OnScrollListener {
  public static final String __md_methods = "n_onScrollStateChange:(Landroid/widget/NumberPicker;I)V:GetOnScrollStateChange_Landroid_widget_NumberPicker_IHandler:Android.Widget.NumberPicker/IOnScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.NumberPicker+IOnScrollListenerImplementor, Mono.Android", NumberPicker_OnScrollListenerImplementor.class, "n_onScrollStateChange:(Landroid/widget/NumberPicker;I)V:GetOnScrollStateChange_Landroid_widget_NumberPicker_IHandler:Android.Widget.NumberPicker/IOnScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public NumberPicker_OnScrollListenerImplementor() {
    if (getClass() == NumberPicker_OnScrollListenerImplementor.class)
      TypeManager.Activate("Android.Widget.NumberPicker+IOnScrollListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onScrollStateChange(NumberPicker paramNumberPicker, int paramInt);
  
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
  
  public void onScrollStateChange(NumberPicker paramNumberPicker, int paramInt) {
    n_onScrollStateChange(paramNumberPicker, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/NumberPicker_OnScrollListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */