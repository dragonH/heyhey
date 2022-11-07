package crc643f46942d9dd1fff9;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public abstract class CellAdapter extends BaseAdapter implements IGCUserPeer, AdapterView.OnItemLongClickListener, ActionMode.Callback, AdapterView.OnItemClickListener, ActionMode.Callback {
  public static final String __md_methods = "n_onItemLongClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)Z:GetOnItemLongClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemLongClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActionItemClicked:(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z:GetOnActionItemClicked_Landroid_view_ActionMode_Landroid_view_MenuItem_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onCreateActionMode:(Landroid/view/ActionMode;Landroid/view/Menu;)Z:GetOnCreateActionMode_Landroid_view_ActionMode_Landroid_view_Menu_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDestroyActionMode:(Landroid/view/ActionMode;)V:GetOnDestroyActionMode_Landroid_view_ActionMode_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPrepareActionMode:(Landroid/view/ActionMode;Landroid/view/Menu;)Z:GetOnPrepareActionMode_Landroid_view_ActionMode_Landroid_view_Menu_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onItemClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V:GetOnItemClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActionItemClicked:(Landroid/support/v7/view/ActionMode;Landroid/view/MenuItem;)Z:GetOnActionItemClicked_Landroid_support_v7_view_ActionMode_Landroid_view_MenuItem_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onCreateActionMode:(Landroid/support/v7/view/ActionMode;Landroid/view/Menu;)Z:GetOnCreateActionMode_Landroid_support_v7_view_ActionMode_Landroid_view_Menu_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onDestroyActionMode:(Landroid/support/v7/view/ActionMode;)V:GetOnDestroyActionMode_Landroid_support_v7_view_ActionMode_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onPrepareActionMode:(Landroid/support/v7/view/ActionMode;Landroid/view/Menu;)Z:GetOnPrepareActionMode_Landroid_support_v7_view_ActionMode_Landroid_view_Menu_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.CellAdapter, Xamarin.Forms.Platform.Android", CellAdapter.class, "n_onItemLongClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)Z:GetOnItemLongClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemLongClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActionItemClicked:(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z:GetOnActionItemClicked_Landroid_view_ActionMode_Landroid_view_MenuItem_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onCreateActionMode:(Landroid/view/ActionMode;Landroid/view/Menu;)Z:GetOnCreateActionMode_Landroid_view_ActionMode_Landroid_view_Menu_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDestroyActionMode:(Landroid/view/ActionMode;)V:GetOnDestroyActionMode_Landroid_view_ActionMode_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPrepareActionMode:(Landroid/view/ActionMode;Landroid/view/Menu;)Z:GetOnPrepareActionMode_Landroid_view_ActionMode_Landroid_view_Menu_Handler:Android.Views.ActionMode/ICallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onItemClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V:GetOnItemClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onActionItemClicked:(Landroid/support/v7/view/ActionMode;Landroid/view/MenuItem;)Z:GetOnActionItemClicked_Landroid_support_v7_view_ActionMode_Landroid_view_MenuItem_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onCreateActionMode:(Landroid/support/v7/view/ActionMode;Landroid/view/Menu;)Z:GetOnCreateActionMode_Landroid_support_v7_view_ActionMode_Landroid_view_Menu_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onDestroyActionMode:(Landroid/support/v7/view/ActionMode;)V:GetOnDestroyActionMode_Landroid_support_v7_view_ActionMode_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onPrepareActionMode:(Landroid/support/v7/view/ActionMode;Landroid/view/Menu;)Z:GetOnPrepareActionMode_Landroid_support_v7_view_ActionMode_Landroid_view_Menu_Handler:Android.Support.V7.View.ActionMode/ICallbackInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public CellAdapter() {
    if (getClass() == CellAdapter.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.CellAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  public CellAdapter(Context paramContext) {
    if (getClass() == CellAdapter.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.CellAdapter, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  private native boolean n_onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem);
  
  private native boolean n_onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem);
  
  private native boolean n_onCreateActionMode(ActionMode paramActionMode, Menu paramMenu);
  
  private native boolean n_onCreateActionMode(ActionMode paramActionMode, Menu paramMenu);
  
  private native void n_onDestroyActionMode(ActionMode paramActionMode);
  
  private native void n_onDestroyActionMode(ActionMode paramActionMode);
  
  private native void n_onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong);
  
  private native boolean n_onItemLongClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong);
  
  private native boolean n_onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu);
  
  private native boolean n_onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu);
  
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
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem) {
    return n_onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem) {
    return n_onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu) {
    return n_onCreateActionMode(paramActionMode, paramMenu);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu) {
    return n_onCreateActionMode(paramActionMode, paramMenu);
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode) {
    n_onDestroyActionMode(paramActionMode);
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode) {
    n_onDestroyActionMode(paramActionMode);
  }
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong) {
    n_onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }
  
  public boolean onItemLongClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong) {
    return n_onItemLongClick(paramAdapterView, paramView, paramInt, paramLong);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu) {
    return n_onPrepareActionMode(paramActionMode, paramMenu);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu) {
    return n_onPrepareActionMode(paramActionMode, paramMenu);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/CellAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */