package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;

@RequiresApi(21)
class FloatingActionButtonLollipop extends FloatingActionButtonImpl {
  private InsetDrawable mInsetDrawable;
  
  FloatingActionButtonLollipop(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate) {
    super(paramVisibilityAwareImageButton, paramShadowViewDelegate);
  }
  
  public float getElevation() {
    return this.mView.getElevation();
  }
  
  void getPadding(Rect paramRect) {
    if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
      float f1 = this.mShadowViewDelegate.getRadius();
      float f2 = getElevation() + this.mPressedTranslationZ;
      int i = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(f2, f1, false));
      int j = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(f2, f1, false));
      paramRect.set(i, j, i, j);
    } else {
      paramRect.set(0, 0, 0, 0);
    } 
  }
  
  void jumpDrawableToCurrentState() {}
  
  CircularBorderDrawable newCircularDrawable() {
    return new CircularBorderDrawableLollipop();
  }
  
  GradientDrawable newGradientDrawableForShape() {
    return new AlwaysStatefulGradientDrawable();
  }
  
  void onCompatShadowChanged() {
    updatePadding();
  }
  
  void onDrawableStateChanged(int[] paramArrayOfint) {}
  
  void onElevationsChanged(float paramFloat1, float paramFloat2) {
    int i = Build.VERSION.SDK_INT;
    if (i == 21) {
      if (this.mView.isEnabled()) {
        this.mView.setElevation(paramFloat1);
        if (this.mView.isFocused() || this.mView.isPressed()) {
          this.mView.setTranslationZ(paramFloat2);
        } else {
          this.mView.setTranslationZ(0.0F);
        } 
      } else {
        this.mView.setElevation(0.0F);
        this.mView.setTranslationZ(0.0F);
      } 
    } else {
      StateListAnimator stateListAnimator = new StateListAnimator();
      AnimatorSet animatorSet1 = new AnimatorSet();
      AnimatorSet.Builder builder = animatorSet1.play((Animator)ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L));
      VisibilityAwareImageButton visibilityAwareImageButton = this.mView;
      Property property = View.TRANSLATION_Z;
      builder.with((Animator)ObjectAnimator.ofFloat(visibilityAwareImageButton, property, new float[] { paramFloat2 }).setDuration(100L));
      Interpolator interpolator = FloatingActionButtonImpl.ANIM_INTERPOLATOR;
      animatorSet1.setInterpolator((TimeInterpolator)interpolator);
      stateListAnimator.addState(FloatingActionButtonImpl.PRESSED_ENABLED_STATE_SET, (Animator)animatorSet1);
      animatorSet1 = new AnimatorSet();
      animatorSet1.play((Animator)ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L)).with((Animator)ObjectAnimator.ofFloat(this.mView, property, new float[] { paramFloat2 }).setDuration(100L));
      animatorSet1.setInterpolator((TimeInterpolator)interpolator);
      stateListAnimator.addState(FloatingActionButtonImpl.FOCUSED_ENABLED_STATE_SET, (Animator)animatorSet1);
      AnimatorSet animatorSet2 = new AnimatorSet();
      ArrayList<ObjectAnimator> arrayList = new ArrayList();
      arrayList.add(ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L));
      if (i >= 22 && i <= 24) {
        VisibilityAwareImageButton visibilityAwareImageButton1 = this.mView;
        arrayList.add(ObjectAnimator.ofFloat(visibilityAwareImageButton1, property, new float[] { visibilityAwareImageButton1.getTranslationZ() }).setDuration(100L));
      } 
      arrayList.add(ObjectAnimator.ofFloat(this.mView, property, new float[] { 0.0F }).setDuration(100L));
      animatorSet2.playSequentially((Animator[])arrayList.toArray((Object[])new ObjectAnimator[0]));
      animatorSet2.setInterpolator((TimeInterpolator)interpolator);
      stateListAnimator.addState(FloatingActionButtonImpl.ENABLED_STATE_SET, (Animator)animatorSet2);
      animatorSet1 = new AnimatorSet();
      animatorSet1.play((Animator)ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { 0.0F }).setDuration(0L)).with((Animator)ObjectAnimator.ofFloat(this.mView, property, new float[] { 0.0F }).setDuration(0L));
      animatorSet1.setInterpolator((TimeInterpolator)interpolator);
      stateListAnimator.addState(FloatingActionButtonImpl.EMPTY_STATE_SET, (Animator)animatorSet1);
      this.mView.setStateListAnimator(stateListAnimator);
    } 
    if (this.mShadowViewDelegate.isCompatPaddingEnabled())
      updatePadding(); 
  }
  
  void onPaddingUpdated(Rect paramRect) {
    if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
      InsetDrawable insetDrawable = new InsetDrawable(this.mRippleDrawable, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
      this.mInsetDrawable = insetDrawable;
      this.mShadowViewDelegate.setBackgroundDrawable((Drawable)insetDrawable);
    } else {
      this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
    } 
  }
  
  boolean requirePreDrawListener() {
    return false;
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2) {
    Drawable drawable1;
    Drawable drawable2 = DrawableCompat.wrap((Drawable)createShapeDrawable());
    this.mShapeDrawable = drawable2;
    DrawableCompat.setTintList(drawable2, paramColorStateList);
    if (paramMode != null)
      DrawableCompat.setTintMode(this.mShapeDrawable, paramMode); 
    if (paramInt2 > 0) {
      this.mBorderDrawable = createBorderDrawable(paramInt2, paramColorStateList);
      LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] { this.mBorderDrawable, this.mShapeDrawable });
    } else {
      this.mBorderDrawable = null;
      drawable1 = this.mShapeDrawable;
    } 
    RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(paramInt1), drawable1, null);
    this.mRippleDrawable = (Drawable)rippleDrawable;
    this.mContentBackground = (Drawable)rippleDrawable;
    this.mShadowViewDelegate.setBackgroundDrawable((Drawable)rippleDrawable);
  }
  
  void setRippleColor(int paramInt) {
    Drawable drawable = this.mRippleDrawable;
    if (drawable instanceof RippleDrawable) {
      ((RippleDrawable)drawable).setColor(ColorStateList.valueOf(paramInt));
    } else {
      super.setRippleColor(paramInt);
    } 
  }
  
  static class AlwaysStatefulGradientDrawable extends GradientDrawable {
    public boolean isStateful() {
      return true;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/FloatingActionButtonLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */