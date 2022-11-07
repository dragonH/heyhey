package crc64679161735e174a39;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TDragDropListViewShadowBuilder extends View.DragShadowBuilder implements IGCUserPeer {
  public static final String __md_methods = "n_onProvideShadowMetrics:(Landroid/graphics/Point;Landroid/graphics/Point;)V:GetOnProvideShadowMetrics_Landroid_graphics_Point_Landroid_graphics_Point_Handler\nn_onDrawShadow:(Landroid/graphics/Canvas;)V:GetOnDrawShadow_Landroid_graphics_Canvas_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.Renderers.TDragDropListViewShadowBuilder, SCS Mobile APP", TDragDropListViewShadowBuilder.class, "n_onProvideShadowMetrics:(Landroid/graphics/Point;Landroid/graphics/Point;)V:GetOnProvideShadowMetrics_Landroid_graphics_Point_Landroid_graphics_Point_Handler\nn_onDrawShadow:(Landroid/graphics/Canvas;)V:GetOnDrawShadow_Landroid_graphics_Canvas_Handler\n");
  }
  
  public TDragDropListViewShadowBuilder(View paramView) {
    super(paramView);
    if (getClass() == TDragDropListViewShadowBuilder.class)
      TypeManager.Activate("SCSAPPForms.Droid.Renderers.TDragDropListViewShadowBuilder, SCS Mobile APP", "Android.Views.View, Mono.Android", this, new Object[] { paramView }); 
  }
  
  private native void n_onDrawShadow(Canvas paramCanvas);
  
  private native void n_onProvideShadowMetrics(Point paramPoint1, Point paramPoint2);
  
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
  
  public void onDrawShadow(Canvas paramCanvas) {
    n_onDrawShadow(paramCanvas);
  }
  
  public void onProvideShadowMetrics(Point paramPoint1, Point paramPoint2) {
    n_onProvideShadowMetrics(paramPoint1, paramPoint2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64679161735e174a39/TDragDropListViewShadowBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */