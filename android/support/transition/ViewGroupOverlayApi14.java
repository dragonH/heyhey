package android.support.transition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
class ViewGroupOverlayApi14 extends ViewOverlayApi14 implements ViewGroupOverlayImpl {
  ViewGroupOverlayApi14(Context paramContext, ViewGroup paramViewGroup, View paramView) {
    super(paramContext, paramViewGroup, paramView);
  }
  
  static ViewGroupOverlayApi14 createFrom(ViewGroup paramViewGroup) {
    return (ViewGroupOverlayApi14)ViewOverlayApi14.createFrom((View)paramViewGroup);
  }
  
  public void add(@NonNull View paramView) {
    this.mOverlayViewGroup.add(paramView);
  }
  
  public void remove(@NonNull View paramView) {
    this.mOverlayViewGroup.remove(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewGroupOverlayApi14.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */