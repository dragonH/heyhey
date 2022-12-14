package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

class VisibilityAwareImageButton extends ImageButton {
  private int mUserSetVisibility = getVisibility();
  
  public VisibilityAwareImageButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public VisibilityAwareImageButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public VisibilityAwareImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  final int getUserSetVisibility() {
    return this.mUserSetVisibility;
  }
  
  final void internalSetVisibility(int paramInt, boolean paramBoolean) {
    super.setVisibility(paramInt);
    if (paramBoolean)
      this.mUserSetVisibility = paramInt; 
  }
  
  public void setVisibility(int paramInt) {
    internalSetVisibility(paramInt, true);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/VisibilityAwareImageButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */