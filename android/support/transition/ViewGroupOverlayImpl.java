package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(14)
interface ViewGroupOverlayImpl extends ViewOverlayImpl {
  void add(@NonNull View paramView);
  
  void remove(@NonNull View paramView);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewGroupOverlayImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */