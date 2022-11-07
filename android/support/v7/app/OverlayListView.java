package android.support.v7.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class OverlayListView extends ListView {
  private final List<OverlayObject> mOverlayObjects = new ArrayList<OverlayObject>();
  
  public OverlayListView(Context paramContext) {
    super(paramContext);
  }
  
  public OverlayListView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public OverlayListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void addOverlayObject(OverlayObject paramOverlayObject) {
    this.mOverlayObjects.add(paramOverlayObject);
  }
  
  public void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mOverlayObjects.size() > 0) {
      Iterator<OverlayObject> iterator = this.mOverlayObjects.iterator();
      while (iterator.hasNext()) {
        OverlayObject overlayObject = iterator.next();
        BitmapDrawable bitmapDrawable = overlayObject.getBitmapDrawable();
        if (bitmapDrawable != null)
          bitmapDrawable.draw(paramCanvas); 
        if (!overlayObject.update(getDrawingTime()))
          iterator.remove(); 
      } 
    } 
  }
  
  public void startAnimationAll() {
    for (OverlayObject overlayObject : this.mOverlayObjects) {
      if (!overlayObject.isAnimationStarted())
        overlayObject.startAnimation(getDrawingTime()); 
    } 
  }
  
  public void stopAnimationAll() {
    Iterator<OverlayObject> iterator = this.mOverlayObjects.iterator();
    while (iterator.hasNext())
      ((OverlayObject)iterator.next()).stopAnimation(); 
  }
  
  public static class OverlayObject {
    private BitmapDrawable mBitmap;
    
    private float mCurrentAlpha = 1.0F;
    
    private Rect mCurrentBounds;
    
    private int mDeltaY;
    
    private long mDuration;
    
    private float mEndAlpha = 1.0F;
    
    private Interpolator mInterpolator;
    
    private boolean mIsAnimationEnded;
    
    private boolean mIsAnimationStarted;
    
    private OnAnimationEndListener mListener;
    
    private float mStartAlpha = 1.0F;
    
    private Rect mStartRect;
    
    private long mStartTime;
    
    public OverlayObject(BitmapDrawable param1BitmapDrawable, Rect param1Rect) {
      this.mBitmap = param1BitmapDrawable;
      this.mStartRect = param1Rect;
      this.mCurrentBounds = new Rect(param1Rect);
      param1BitmapDrawable = this.mBitmap;
      if (param1BitmapDrawable != null) {
        param1BitmapDrawable.setAlpha((int)(this.mCurrentAlpha * 255.0F));
        this.mBitmap.setBounds(this.mCurrentBounds);
      } 
    }
    
    public BitmapDrawable getBitmapDrawable() {
      return this.mBitmap;
    }
    
    public boolean isAnimationStarted() {
      return this.mIsAnimationStarted;
    }
    
    public OverlayObject setAlphaAnimation(float param1Float1, float param1Float2) {
      this.mStartAlpha = param1Float1;
      this.mEndAlpha = param1Float2;
      return this;
    }
    
    public OverlayObject setAnimationEndListener(OnAnimationEndListener param1OnAnimationEndListener) {
      this.mListener = param1OnAnimationEndListener;
      return this;
    }
    
    public OverlayObject setDuration(long param1Long) {
      this.mDuration = param1Long;
      return this;
    }
    
    public OverlayObject setInterpolator(Interpolator param1Interpolator) {
      this.mInterpolator = param1Interpolator;
      return this;
    }
    
    public OverlayObject setTranslateYAnimation(int param1Int) {
      this.mDeltaY = param1Int;
      return this;
    }
    
    public void startAnimation(long param1Long) {
      this.mStartTime = param1Long;
      this.mIsAnimationStarted = true;
    }
    
    public void stopAnimation() {
      this.mIsAnimationStarted = true;
      this.mIsAnimationEnded = true;
      OnAnimationEndListener onAnimationEndListener = this.mListener;
      if (onAnimationEndListener != null)
        onAnimationEndListener.onAnimationEnd(); 
    }
    
    public boolean update(long param1Long) {
      if (this.mIsAnimationEnded)
        return false; 
      float f1 = Math.min(1.0F, (float)(param1Long - this.mStartTime) / (float)this.mDuration);
      float f2 = 0.0F;
      f1 = Math.max(0.0F, f1);
      if (this.mIsAnimationStarted)
        f2 = f1; 
      Interpolator interpolator = this.mInterpolator;
      if (interpolator == null) {
        f1 = f2;
      } else {
        f1 = interpolator.getInterpolation(f2);
      } 
      int i = (int)(this.mDeltaY * f1);
      Rect rect1 = this.mCurrentBounds;
      Rect rect2 = this.mStartRect;
      rect2.top += i;
      rect2.bottom += i;
      float f3 = this.mStartAlpha;
      f1 = f3 + (this.mEndAlpha - f3) * f1;
      this.mCurrentAlpha = f1;
      BitmapDrawable bitmapDrawable = this.mBitmap;
      if (bitmapDrawable != null && rect1 != null) {
        bitmapDrawable.setAlpha((int)(f1 * 255.0F));
        this.mBitmap.setBounds(this.mCurrentBounds);
      } 
      if (this.mIsAnimationStarted && f2 >= 1.0F) {
        this.mIsAnimationEnded = true;
        OnAnimationEndListener onAnimationEndListener = this.mListener;
        if (onAnimationEndListener != null)
          onAnimationEndListener.onAnimationEnd(); 
      } 
      return this.mIsAnimationEnded ^ true;
    }
    
    public static interface OnAnimationEndListener {
      void onAnimationEnd();
    }
  }
  
  public static interface OnAnimationEndListener {
    void onAnimationEnd();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/OverlayListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */