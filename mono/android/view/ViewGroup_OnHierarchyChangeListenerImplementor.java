package mono.android.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewGroup_OnHierarchyChangeListenerImplementor implements IGCUserPeer, ViewGroup.OnHierarchyChangeListener {
  public static final String __md_methods = "n_onChildViewAdded:(Landroid/view/View;Landroid/view/View;)V:GetOnChildViewAdded_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewGroup/IOnHierarchyChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onChildViewRemoved:(Landroid/view/View;Landroid/view/View;)V:GetOnChildViewRemoved_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewGroup/IOnHierarchyChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewGroup+IOnHierarchyChangeListenerImplementor, Mono.Android", ViewGroup_OnHierarchyChangeListenerImplementor.class, "n_onChildViewAdded:(Landroid/view/View;Landroid/view/View;)V:GetOnChildViewAdded_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewGroup/IOnHierarchyChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onChildViewRemoved:(Landroid/view/View;Landroid/view/View;)V:GetOnChildViewRemoved_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewGroup/IOnHierarchyChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewGroup_OnHierarchyChangeListenerImplementor() {
    if (getClass() == ViewGroup_OnHierarchyChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewGroup+IOnHierarchyChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onChildViewAdded(View paramView1, View paramView2);
  
  private native void n_onChildViewRemoved(View paramView1, View paramView2);
  
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
  
  public void onChildViewAdded(View paramView1, View paramView2) {
    n_onChildViewAdded(paramView1, paramView2);
  }
  
  public void onChildViewRemoved(View paramView1, View paramView2) {
    n_onChildViewRemoved(paramView1, paramView2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewGroup_OnHierarchyChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */