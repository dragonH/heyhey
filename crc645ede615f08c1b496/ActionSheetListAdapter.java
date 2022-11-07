package crc645ede615f08c1b496;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionSheetListAdapter extends ArrayAdapter implements IGCUserPeer {
  public static final String __md_methods = "n_getView:(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;:GetGetView_ILandroid_view_View_Landroid_view_ViewGroup_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Acr.UserDialogs.ActionSheetListAdapter, Acr.UserDialogs", ActionSheetListAdapter.class, "n_getView:(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;:GetGetView_ILandroid_view_View_Landroid_view_ViewGroup_Handler\n");
  }
  
  public ActionSheetListAdapter(Context paramContext, int paramInt1, int paramInt2, List paramList) {
    super(paramContext, paramInt1, paramInt2, paramList);
    if (getClass() == ActionSheetListAdapter.class)
      TypeManager.Activate("Acr.UserDialogs.ActionSheetListAdapter, Acr.UserDialogs", "Android.Content.Context, Mono.Android:System.Int32, mscorlib:System.Int32, mscorlib:System.Collections.Generic.IList`1<T>, mscorlib", this, new Object[] { paramContext, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramList }); 
  }
  
  private native View n_getView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return n_getView(paramInt, paramView, paramViewGroup);
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc645ede615f08c1b496/ActionSheetListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */