package mono.android.widget;

import android.widget.SeekBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SeekBar_OnSeekBarChangeListenerImplementor implements IGCUserPeer, SeekBar.OnSeekBarChangeListener {
  public static final String __md_methods = "n_onProgressChanged:(Landroid/widget/SeekBar;IZ)V:GetOnProgressChanged_Landroid_widget_SeekBar_IZHandler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStartTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStartTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStopTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStopTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SeekBar+IOnSeekBarChangeListenerImplementor, Mono.Android", SeekBar_OnSeekBarChangeListenerImplementor.class, "n_onProgressChanged:(Landroid/widget/SeekBar;IZ)V:GetOnProgressChanged_Landroid_widget_SeekBar_IZHandler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStartTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStartTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStopTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStopTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SeekBar_OnSeekBarChangeListenerImplementor() {
    if (getClass() == SeekBar_OnSeekBarChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SeekBar+IOnSeekBarChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean);
  
  private native void n_onStartTrackingTouch(SeekBar paramSeekBar);
  
  private native void n_onStopTrackingTouch(SeekBar paramSeekBar);
  
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
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {
    n_onProgressChanged(paramSeekBar, paramInt, paramBoolean);
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {
    n_onStartTrackingTouch(paramSeekBar);
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {
    n_onStopTrackingTouch(paramSeekBar);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SeekBar_OnSeekBarChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */