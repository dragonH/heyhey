package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;

@RequiresApi(9)
class ActionBarBackgroundDrawable extends Drawable {
  final ActionBarContainer mContainer;
  
  public ActionBarBackgroundDrawable(ActionBarContainer paramActionBarContainer) {
    this.mContainer = paramActionBarContainer;
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable;
    ActionBarContainer actionBarContainer = this.mContainer;
    if (actionBarContainer.mIsSplit) {
      drawable = actionBarContainer.mSplitBackground;
      if (drawable != null)
        drawable.draw(paramCanvas); 
    } else {
      drawable = ((ActionBarContainer)drawable).mBackground;
      if (drawable != null)
        drawable.draw(paramCanvas); 
      ActionBarContainer actionBarContainer1 = this.mContainer;
      drawable = actionBarContainer1.mStackedBackground;
      if (drawable != null && actionBarContainer1.mIsStacked)
        drawable.draw(paramCanvas); 
    } 
  }
  
  public int getOpacity() {
    return 0;
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/ActionBarBackgroundDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */