package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
  private static final long HOVER_HIDE_TIMEOUT_MS = 15000L;
  
  private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000L;
  
  private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500L;
  
  private static final String TAG = "TooltipCompatHandler";
  
  private static TooltipCompatHandler sActiveHandler;
  
  private final View mAnchor;
  
  private int mAnchorX;
  
  private int mAnchorY;
  
  private boolean mFromTouch;
  
  private final Runnable mHideRunnable = new Runnable() {
      public void run() {
        TooltipCompatHandler.this.hide();
      }
    };
  
  private TooltipPopup mPopup;
  
  private final Runnable mShowRunnable = new Runnable() {
      public void run() {
        TooltipCompatHandler.this.show(false);
      }
    };
  
  private final CharSequence mTooltipText;
  
  private TooltipCompatHandler(View paramView, CharSequence paramCharSequence) {
    this.mAnchor = paramView;
    this.mTooltipText = paramCharSequence;
    paramView.setOnLongClickListener(this);
    paramView.setOnHoverListener(this);
  }
  
  private void hide() {
    if (sActiveHandler == this) {
      sActiveHandler = null;
      TooltipPopup tooltipPopup = this.mPopup;
      if (tooltipPopup != null) {
        tooltipPopup.hide();
        this.mPopup = null;
        this.mAnchor.removeOnAttachStateChangeListener(this);
      } else {
        Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
      } 
    } 
    this.mAnchor.removeCallbacks(this.mShowRunnable);
    this.mAnchor.removeCallbacks(this.mHideRunnable);
  }
  
  public static void setTooltipText(View paramView, CharSequence paramCharSequence) {
    TooltipCompatHandler tooltipCompatHandler;
    if (TextUtils.isEmpty(paramCharSequence)) {
      tooltipCompatHandler = sActiveHandler;
      if (tooltipCompatHandler != null && tooltipCompatHandler.mAnchor == paramView)
        tooltipCompatHandler.hide(); 
      paramView.setOnLongClickListener(null);
      paramView.setLongClickable(false);
      paramView.setOnHoverListener(null);
    } else {
      new TooltipCompatHandler(paramView, (CharSequence)tooltipCompatHandler);
    } 
  }
  
  private void show(boolean paramBoolean) {
    long l;
    if (!ViewCompat.isAttachedToWindow(this.mAnchor))
      return; 
    TooltipCompatHandler tooltipCompatHandler = sActiveHandler;
    if (tooltipCompatHandler != null)
      tooltipCompatHandler.hide(); 
    sActiveHandler = this;
    this.mFromTouch = paramBoolean;
    TooltipPopup tooltipPopup = new TooltipPopup(this.mAnchor.getContext());
    this.mPopup = tooltipPopup;
    tooltipPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
    this.mAnchor.addOnAttachStateChangeListener(this);
    if (this.mFromTouch) {
      l = 2500L;
    } else {
      int i;
      if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 0x1) == 1) {
        l = 3000L;
        i = ViewConfiguration.getLongPressTimeout();
      } else {
        l = 15000L;
        i = ViewConfiguration.getLongPressTimeout();
      } 
      l -= i;
    } 
    this.mAnchor.removeCallbacks(this.mHideRunnable);
    this.mAnchor.postDelayed(this.mHideRunnable, l);
  }
  
  public boolean onHover(View paramView, MotionEvent paramMotionEvent) {
    if (this.mPopup != null && this.mFromTouch)
      return false; 
    AccessibilityManager accessibilityManager = (AccessibilityManager)this.mAnchor.getContext().getSystemService("accessibility");
    if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled())
      return false; 
    int i = paramMotionEvent.getAction();
    if (i != 7) {
      if (i == 10)
        hide(); 
    } else if (this.mAnchor.isEnabled() && this.mPopup == null) {
      this.mAnchorX = (int)paramMotionEvent.getX();
      this.mAnchorY = (int)paramMotionEvent.getY();
      this.mAnchor.removeCallbacks(this.mShowRunnable);
      this.mAnchor.postDelayed(this.mShowRunnable, ViewConfiguration.getLongPressTimeout());
    } 
    return false;
  }
  
  public boolean onLongClick(View paramView) {
    this.mAnchorX = paramView.getWidth() / 2;
    this.mAnchorY = paramView.getHeight() / 2;
    show(true);
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView) {
    hide();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/TooltipCompatHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */