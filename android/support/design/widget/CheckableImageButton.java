package android.support.design.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
  private static final int[] DRAWABLE_STATE_CHECKED = new int[] { 16842912 };
  
  private boolean mChecked;
  
  public CheckableImageButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.imageButtonStyle);
  }
  
  public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegateCompat() {
          public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
            super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
            param1AccessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
          }
          
          public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
            param1AccessibilityNodeInfoCompat.setCheckable(true);
            param1AccessibilityNodeInfoCompat.setChecked(CheckableImageButton.this.isChecked());
          }
        });
  }
  
  public boolean isChecked() {
    return this.mChecked;
  }
  
  public int[] onCreateDrawableState(int paramInt) {
    if (this.mChecked) {
      int[] arrayOfInt = DRAWABLE_STATE_CHECKED;
      return ImageButton.mergeDrawableStates(super.onCreateDrawableState(paramInt + arrayOfInt.length), arrayOfInt);
    } 
    return super.onCreateDrawableState(paramInt);
  }
  
  public void setChecked(boolean paramBoolean) {
    if (this.mChecked != paramBoolean) {
      this.mChecked = paramBoolean;
      refreshDrawableState();
      sendAccessibilityEvent(2048);
    } 
  }
  
  public void toggle() {
    setChecked(this.mChecked ^ true);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/CheckableImageButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */