package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
interface GhostViewImpl {
  void reserveEndViewTransition(ViewGroup paramViewGroup, View paramView);
  
  void setVisibility(int paramInt);
  
  public static interface Creator {
    GhostViewImpl addGhost(View param1View, ViewGroup param1ViewGroup, Matrix param1Matrix);
    
    void removeGhost(View param1View);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/GhostViewImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */