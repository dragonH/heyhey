package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ScrimInsetsFrameLayout extends FrameLayout {
  Drawable mInsetForeground;
  
  Rect mInsets;
  
  private Rect mTempRect = new Rect();
  
  public ScrimInsetsFrameLayout(Context paramContext) {
    this(paramContext, null);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ScrimInsetsFrameLayout, paramInt, R.style.Widget_Design_ScrimInsetsFrameLayout);
    this.mInsetForeground = typedArray.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
    typedArray.recycle();
    setWillNotDraw(true);
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.mInsets == null)
              scrimInsetsFrameLayout.mInsets = new Rect(); 
            ScrimInsetsFrameLayout.this.mInsets.set(param1WindowInsetsCompat.getSystemWindowInsetLeft(), param1WindowInsetsCompat.getSystemWindowInsetTop(), param1WindowInsetsCompat.getSystemWindowInsetRight(), param1WindowInsetsCompat.getSystemWindowInsetBottom());
            ScrimInsetsFrameLayout.this.onInsetsChanged(param1WindowInsetsCompat);
            scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (!param1WindowInsetsCompat.hasSystemWindowInsets() || ScrimInsetsFrameLayout.this.mInsetForeground == null) {
              boolean bool1 = true;
              scrimInsetsFrameLayout.setWillNotDraw(bool1);
              ViewCompat.postInvalidateOnAnimation((View)ScrimInsetsFrameLayout.this);
              return param1WindowInsetsCompat.consumeSystemWindowInsets();
            } 
            boolean bool = false;
            scrimInsetsFrameLayout.setWillNotDraw(bool);
            ViewCompat.postInvalidateOnAnimation((View)ScrimInsetsFrameLayout.this);
            return param1WindowInsetsCompat.consumeSystemWindowInsets();
          }
        });
  }
  
  public void draw(@NonNull Canvas paramCanvas) {
    super.draw(paramCanvas);
    int i = getWidth();
    int j = getHeight();
    if (this.mInsets != null && this.mInsetForeground != null) {
      int k = paramCanvas.save();
      paramCanvas.translate(getScrollX(), getScrollY());
      this.mTempRect.set(0, 0, i, this.mInsets.top);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      this.mTempRect.set(0, j - this.mInsets.bottom, i, j);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      Rect rect1 = this.mTempRect;
      Rect rect2 = this.mInsets;
      rect1.set(0, rect2.top, rect2.left, j - rect2.bottom);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      rect1 = this.mTempRect;
      rect2 = this.mInsets;
      rect1.set(i - rect2.right, rect2.top, i, j - rect2.bottom);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      paramCanvas.restoreToCount(k);
    } 
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    Drawable drawable = this.mInsetForeground;
    if (drawable != null)
      drawable.setCallback((Drawable.Callback)this); 
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    Drawable drawable = this.mInsetForeground;
    if (drawable != null)
      drawable.setCallback(null); 
  }
  
  protected void onInsetsChanged(WindowInsetsCompat paramWindowInsetsCompat) {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/internal/ScrimInsetsFrameLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */