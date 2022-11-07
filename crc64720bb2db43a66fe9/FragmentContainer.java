package crc64720bb2db43a66fe9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FragmentContainer extends Fragment implements IGCUserPeer {
  public static final String __md_methods = "n_getUserVisibleHint:()Z:GetGetUserVisibleHintHandler\nn_setUserVisibleHint:(Z)V:GetSetUserVisibleHint_ZHandler\nn_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onDestroyView:()V:GetOnDestroyViewHandler\nn_onHiddenChanged:(Z)V:GetOnHiddenChanged_ZHandler\nn_onPause:()V:GetOnPauseHandler\nn_onResume:()V:GetOnResumeHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.FragmentContainer, Xamarin.Forms.Platform.Android", FragmentContainer.class, "n_getUserVisibleHint:()Z:GetGetUserVisibleHintHandler\nn_setUserVisibleHint:(Z)V:GetSetUserVisibleHint_ZHandler\nn_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onDestroyView:()V:GetOnDestroyViewHandler\nn_onHiddenChanged:(Z)V:GetOnHiddenChanged_ZHandler\nn_onPause:()V:GetOnPauseHandler\nn_onResume:()V:GetOnResumeHandler\n");
  }
  
  public FragmentContainer() {
    if (getClass() == FragmentContainer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FragmentContainer, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_getUserVisibleHint();
  
  private native View n_onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);
  
  private native void n_onDestroyView();
  
  private native void n_onHiddenChanged(boolean paramBoolean);
  
  private native void n_onPause();
  
  private native void n_onResume();
  
  private native void n_setUserVisibleHint(boolean paramBoolean);
  
  public boolean getUserVisibleHint() {
    return n_getUserVisibleHint();
  }
  
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
  
  public void onDestroyView() {
    n_onDestroyView();
  }
  
  public void onHiddenChanged(boolean paramBoolean) {
    n_onHiddenChanged(paramBoolean);
  }
  
  public void onPause() {
    n_onPause();
  }
  
  public void onResume() {
    n_onResume();
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    n_setUserVisibleHint(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64720bb2db43a66fe9/FragmentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */