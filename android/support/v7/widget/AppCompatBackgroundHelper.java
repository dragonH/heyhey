package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper {
  private int mBackgroundResId = -1;
  
  private TintInfo mBackgroundTint;
  
  private final AppCompatDrawableManager mDrawableManager;
  
  private TintInfo mInternalBackgroundTint;
  
  private TintInfo mTmpInfo;
  
  private final View mView;
  
  AppCompatBackgroundHelper(View paramView) {
    this.mView = paramView;
    this.mDrawableManager = AppCompatDrawableManager.get();
  }
  
  private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable paramDrawable) {
    if (this.mTmpInfo == null)
      this.mTmpInfo = new TintInfo(); 
    TintInfo tintInfo = this.mTmpInfo;
    tintInfo.clear();
    ColorStateList colorStateList = ViewCompat.getBackgroundTintList(this.mView);
    if (colorStateList != null) {
      tintInfo.mHasTintList = true;
      tintInfo.mTintList = colorStateList;
    } 
    PorterDuff.Mode mode = ViewCompat.getBackgroundTintMode(this.mView);
    if (mode != null) {
      tintInfo.mHasTintMode = true;
      tintInfo.mTintMode = mode;
    } 
    if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, tintInfo, this.mView.getDrawableState());
      return true;
    } 
    return false;
  }
  
  private boolean shouldApplyFrameworkTintUsingColorFilter() {
    int i = Build.VERSION.SDK_INT;
    boolean bool = true;
    if (i > 21) {
      if (this.mInternalBackgroundTint == null)
        bool = false; 
      return bool;
    } 
    return (i == 21);
  }
  
  void applySupportBackgroundTint() {
    Drawable drawable = this.mView.getBackground();
    if (drawable != null) {
      if (shouldApplyFrameworkTintUsingColorFilter() && applyFrameworkTintUsingColorFilter(drawable))
        return; 
      TintInfo tintInfo = this.mBackgroundTint;
      if (tintInfo != null) {
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
      } else {
        tintInfo = this.mInternalBackgroundTint;
        if (tintInfo != null)
          AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState()); 
      } 
    } 
  }
  
  ColorStateList getSupportBackgroundTintList() {
    TintInfo tintInfo = this.mBackgroundTint;
    if (tintInfo != null) {
      ColorStateList colorStateList = tintInfo.mTintList;
    } else {
      tintInfo = null;
    } 
    return (ColorStateList)tintInfo;
  }
  
  PorterDuff.Mode getSupportBackgroundTintMode() {
    TintInfo tintInfo = this.mBackgroundTint;
    if (tintInfo != null) {
      PorterDuff.Mode mode = tintInfo.mTintMode;
    } else {
      tintInfo = null;
    } 
    return (PorterDuff.Mode)tintInfo;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, R.styleable.ViewBackgroundHelper, paramInt, 0);
    try {
      paramInt = R.styleable.ViewBackgroundHelper_android_background;
      if (tintTypedArray.hasValue(paramInt)) {
        this.mBackgroundResId = tintTypedArray.getResourceId(paramInt, -1);
        ColorStateList colorStateList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
        if (colorStateList != null)
          setInternalBackgroundTint(colorStateList); 
      } 
      paramInt = R.styleable.ViewBackgroundHelper_backgroundTint;
      if (tintTypedArray.hasValue(paramInt))
        ViewCompat.setBackgroundTintList(this.mView, tintTypedArray.getColorStateList(paramInt)); 
      paramInt = R.styleable.ViewBackgroundHelper_backgroundTintMode;
      if (tintTypedArray.hasValue(paramInt))
        ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(tintTypedArray.getInt(paramInt, -1), null)); 
      return;
    } finally {
      tintTypedArray.recycle();
    } 
  }
  
  void onSetBackgroundDrawable(Drawable paramDrawable) {
    this.mBackgroundResId = -1;
    setInternalBackgroundTint(null);
    applySupportBackgroundTint();
  }
  
  void onSetBackgroundResource(int paramInt) {
    this.mBackgroundResId = paramInt;
    AppCompatDrawableManager appCompatDrawableManager = this.mDrawableManager;
    if (appCompatDrawableManager != null) {
      ColorStateList colorStateList = appCompatDrawableManager.getTintList(this.mView.getContext(), paramInt);
    } else {
      appCompatDrawableManager = null;
    } 
    setInternalBackgroundTint((ColorStateList)appCompatDrawableManager);
    applySupportBackgroundTint();
  }
  
  void setInternalBackgroundTint(ColorStateList paramColorStateList) {
    if (paramColorStateList != null) {
      if (this.mInternalBackgroundTint == null)
        this.mInternalBackgroundTint = new TintInfo(); 
      TintInfo tintInfo = this.mInternalBackgroundTint;
      tintInfo.mTintList = paramColorStateList;
      tintInfo.mHasTintList = true;
    } else {
      this.mInternalBackgroundTint = null;
    } 
    applySupportBackgroundTint();
  }
  
  void setSupportBackgroundTintList(ColorStateList paramColorStateList) {
    if (this.mBackgroundTint == null)
      this.mBackgroundTint = new TintInfo(); 
    TintInfo tintInfo = this.mBackgroundTint;
    tintInfo.mTintList = paramColorStateList;
    tintInfo.mHasTintList = true;
    applySupportBackgroundTint();
  }
  
  void setSupportBackgroundTintMode(PorterDuff.Mode paramMode) {
    if (this.mBackgroundTint == null)
      this.mBackgroundTint = new TintInfo(); 
    TintInfo tintInfo = this.mBackgroundTint;
    tintInfo.mTintMode = paramMode;
    tintInfo.mHasTintMode = true;
    applySupportBackgroundTint();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AppCompatBackgroundHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */