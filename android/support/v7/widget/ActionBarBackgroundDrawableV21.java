package android.support.v7.widget;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class ActionBarBackgroundDrawableV21 extends ActionBarBackgroundDrawable {
  public ActionBarBackgroundDrawableV21(ActionBarContainer paramActionBarContainer) {
    super(paramActionBarContainer);
  }
  
  public void getOutline(@NonNull Outline paramOutline) {
    Drawable drawable;
    ActionBarContainer actionBarContainer = this.mContainer;
    if (actionBarContainer.mIsSplit) {
      drawable = actionBarContainer.mSplitBackground;
      if (drawable != null)
        drawable.getOutline(paramOutline); 
    } else {
      drawable = ((ActionBarContainer)drawable).mBackground;
      if (drawable != null)
        drawable.getOutline(paramOutline); 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/ActionBarBackgroundDrawableV21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */