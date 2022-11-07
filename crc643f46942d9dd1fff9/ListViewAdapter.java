package crc643f46942d9dd1fff9;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ListViewAdapter extends CellAdapter implements IGCUserPeer {
  public static final String __md_methods = "n_getCount:()I:GetGetCountHandler\nn_hasStableIds:()Z:GetHasStableIdsHandler\nn_getItem:(I)Ljava/lang/Object;:GetGetItem_IHandler\nn_getViewTypeCount:()I:GetGetViewTypeCountHandler\nn_areAllItemsEnabled:()Z:GetAreAllItemsEnabledHandler\nn_getItemId:(I)J:GetGetItemId_IHandler\nn_getItemViewType:(I)I:GetGetItemViewType_IHandler\nn_getView:(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;:GetGetView_ILandroid_view_View_Landroid_view_ViewGroup_Handler\nn_isEnabled:(I)Z:GetIsEnabled_IHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.ListViewAdapter, Xamarin.Forms.Platform.Android", ListViewAdapter.class, "n_getCount:()I:GetGetCountHandler\nn_hasStableIds:()Z:GetHasStableIdsHandler\nn_getItem:(I)Ljava/lang/Object;:GetGetItem_IHandler\nn_getViewTypeCount:()I:GetGetViewTypeCountHandler\nn_areAllItemsEnabled:()Z:GetAreAllItemsEnabledHandler\nn_getItemId:(I)J:GetGetItemId_IHandler\nn_getItemViewType:(I)I:GetGetItemViewType_IHandler\nn_getView:(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;:GetGetView_ILandroid_view_View_Landroid_view_ViewGroup_Handler\nn_isEnabled:(I)Z:GetIsEnabled_IHandler\n");
  }
  
  public ListViewAdapter() {
    if (getClass() == ListViewAdapter.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.ListViewAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  public ListViewAdapter(Context paramContext) {
    if (getClass() == ListViewAdapter.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.ListViewAdapter, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  private native boolean n_areAllItemsEnabled();
  
  private native int n_getCount();
  
  private native Object n_getItem(int paramInt);
  
  private native long n_getItemId(int paramInt);
  
  private native int n_getItemViewType(int paramInt);
  
  private native View n_getView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  private native int n_getViewTypeCount();
  
  private native boolean n_hasStableIds();
  
  private native boolean n_isEnabled(int paramInt);
  
  public boolean areAllItemsEnabled() {
    return n_areAllItemsEnabled();
  }
  
  public int getCount() {
    return n_getCount();
  }
  
  public Object getItem(int paramInt) {
    return n_getItem(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return n_getItemId(paramInt);
  }
  
  public int getItemViewType(int paramInt) {
    return n_getItemViewType(paramInt);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return n_getView(paramInt, paramView, paramViewGroup);
  }
  
  public int getViewTypeCount() {
    return n_getViewTypeCount();
  }
  
  public boolean hasStableIds() {
    return n_hasStableIds();
  }
  
  public boolean isEnabled(int paramInt) {
    return n_isEnabled(paramInt);
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/ListViewAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */