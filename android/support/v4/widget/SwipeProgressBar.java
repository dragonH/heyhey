package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

final class SwipeProgressBar {
  private static final int ANIMATION_DURATION_MS = 2000;
  
  private static final int COLOR1 = -1291845632;
  
  private static final int COLOR2 = -2147483648;
  
  private static final int COLOR3 = 1291845632;
  
  private static final int COLOR4 = 436207616;
  
  private static final int FINISH_ANIMATION_DURATION_MS = 1000;
  
  private static final Interpolator INTERPOLATOR = (Interpolator)new FastOutSlowInInterpolator();
  
  private Rect mBounds = new Rect();
  
  private final RectF mClipRect = new RectF();
  
  private int mColor1;
  
  private int mColor2;
  
  private int mColor3;
  
  private int mColor4;
  
  private long mFinishTime;
  
  private final Paint mPaint = new Paint();
  
  private View mParent;
  
  private boolean mRunning;
  
  private long mStartTime;
  
  private float mTriggerPercentage;
  
  SwipeProgressBar(View paramView) {
    this.mParent = paramView;
    this.mColor1 = -1291845632;
    this.mColor2 = Integer.MIN_VALUE;
    this.mColor3 = 1291845632;
    this.mColor4 = 436207616;
  }
  
  private void drawCircle(Canvas paramCanvas, float paramFloat1, float paramFloat2, int paramInt, float paramFloat3) {
    this.mPaint.setColor(paramInt);
    paramCanvas.save();
    paramCanvas.translate(paramFloat1, paramFloat2);
    paramFloat2 = INTERPOLATOR.getInterpolation(paramFloat3);
    paramCanvas.scale(paramFloat2, paramFloat2);
    paramCanvas.drawCircle(0.0F, 0.0F, paramFloat1, this.mPaint);
    paramCanvas.restore();
  }
  
  private void drawTrigger(Canvas paramCanvas, int paramInt1, int paramInt2) {
    this.mPaint.setColor(this.mColor1);
    float f = paramInt1;
    paramCanvas.drawCircle(f, paramInt2, this.mTriggerPercentage * f, this.mPaint);
  }
  
  void draw(Canvas paramCanvas) {
    int i = this.mBounds.width();
    int j = this.mBounds.height();
    int k = i / 2;
    int m = j / 2;
    i = paramCanvas.save();
    paramCanvas.clipRect(this.mBounds);
    if (this.mRunning || this.mFinishTime > 0L) {
      long l1 = AnimationUtils.currentAnimationTimeMillis();
      long l2 = this.mStartTime;
      long l3 = (l1 - l2) / 2000L;
      float f = (float)((l1 - l2) % 2000L) / 20.0F;
      boolean bool = this.mRunning;
      boolean bool1 = false;
      if (!bool) {
        l2 = this.mFinishTime;
        if (l1 - l2 >= 1000L) {
          this.mFinishTime = 0L;
          return;
        } 
        float f1 = (float)((l1 - l2) % 1000L) / 10.0F / 100.0F;
        float f2 = k;
        f1 = INTERPOLATOR.getInterpolation(f1) * f2;
        this.mClipRect.set(f2 - f1, 0.0F, f2 + f1, j);
        paramCanvas.saveLayerAlpha(this.mClipRect, 0, 0);
        bool1 = true;
      } 
      if (l3 == 0L) {
        paramCanvas.drawColor(this.mColor1);
      } else if (f >= 0.0F && f < 25.0F) {
        paramCanvas.drawColor(this.mColor4);
      } else if (f >= 25.0F && f < 50.0F) {
        paramCanvas.drawColor(this.mColor1);
      } else if (f >= 50.0F && f < 75.0F) {
        paramCanvas.drawColor(this.mColor2);
      } else {
        paramCanvas.drawColor(this.mColor3);
      } 
      if (f >= 0.0F && f <= 25.0F) {
        float f1 = (f + 25.0F) * 2.0F / 100.0F;
        drawCircle(paramCanvas, k, m, this.mColor1, f1);
      } 
      if (f >= 0.0F && f <= 50.0F) {
        float f1 = f * 2.0F / 100.0F;
        drawCircle(paramCanvas, k, m, this.mColor2, f1);
      } 
      if (f >= 25.0F && f <= 75.0F) {
        float f1 = (f - 25.0F) * 2.0F / 100.0F;
        drawCircle(paramCanvas, k, m, this.mColor3, f1);
      } 
      if (f >= 50.0F && f <= 100.0F) {
        float f1 = (f - 50.0F) * 2.0F / 100.0F;
        drawCircle(paramCanvas, k, m, this.mColor4, f1);
      } 
      if (f >= 75.0F && f <= 100.0F) {
        f = (f - 75.0F) * 2.0F / 100.0F;
        drawCircle(paramCanvas, k, m, this.mColor1, f);
      } 
      j = i;
      if (this.mTriggerPercentage > 0.0F) {
        j = i;
        if (bool1) {
          paramCanvas.restoreToCount(i);
          j = paramCanvas.save();
          paramCanvas.clipRect(this.mBounds);
          drawTrigger(paramCanvas, k, m);
        } 
      } 
      View view = this.mParent;
      Rect rect = this.mBounds;
      ViewCompat.postInvalidateOnAnimation(view, rect.left, rect.top, rect.right, rect.bottom);
    } else {
      float f = this.mTriggerPercentage;
      j = i;
      if (f > 0.0F) {
        j = i;
        if (f <= 1.0D) {
          drawTrigger(paramCanvas, k, m);
          j = i;
        } 
      } 
    } 
    paramCanvas.restoreToCount(j);
  }
  
  boolean isRunning() {
    return (this.mRunning || this.mFinishTime > 0L);
  }
  
  void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Rect rect = this.mBounds;
    rect.left = paramInt1;
    rect.top = paramInt2;
    rect.right = paramInt3;
    rect.bottom = paramInt4;
  }
  
  void setColorScheme(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mColor1 = paramInt1;
    this.mColor2 = paramInt2;
    this.mColor3 = paramInt3;
    this.mColor4 = paramInt4;
  }
  
  void setTriggerPercentage(float paramFloat) {
    this.mTriggerPercentage = paramFloat;
    this.mStartTime = 0L;
    View view = this.mParent;
    Rect rect = this.mBounds;
    ViewCompat.postInvalidateOnAnimation(view, rect.left, rect.top, rect.right, rect.bottom);
  }
  
  void start() {
    if (!this.mRunning) {
      this.mTriggerPercentage = 0.0F;
      this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
      this.mRunning = true;
      this.mParent.postInvalidate();
    } 
  }
  
  void stop() {
    if (this.mRunning) {
      this.mTriggerPercentage = 0.0F;
      this.mFinishTime = AnimationUtils.currentAnimationTimeMillis();
      this.mRunning = false;
      this.mParent.postInvalidate();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/SwipeProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */