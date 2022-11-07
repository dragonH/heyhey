package crc6480997b3ef81bf9b2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZXingScannerFragment extends Fragment implements IGCUserPeer {
  public static final String __md_methods = "n_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("ZXing.Mobile.ZXingScannerFragment, ZXingNetMobile", ZXingScannerFragment.class, "n_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\n");
  }
  
  public ZXingScannerFragment() {
    if (getClass() == ZXingScannerFragment.class)
      TypeManager.Activate("ZXing.Mobile.ZXingScannerFragment, ZXingNetMobile", "", this, new Object[0]); 
  }
  
  private native View n_onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);
  
  private native void n_onStart();
  
  private native void n_onStop();
  
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
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return n_onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  public void onStart() {
    n_onStart();
  }
  
  public void onStop() {
    n_onStop();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6480997b3ef81bf9b2/ZXingScannerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */