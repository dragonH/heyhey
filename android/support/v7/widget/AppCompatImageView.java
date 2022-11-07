package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.TintableImageSourceView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class AppCompatImageView extends ImageView implements TintableBackgroundView, TintableImageSourceView {
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  
  private final AppCompatImageHelper mImageHelper;
  
  public AppCompatImageView(Context paramContext) {
    this(paramContext, null);
  }
  
  public AppCompatImageView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AppCompatImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper((View)this);
    this.mBackgroundTintHelper = appCompatBackgroundHelper;
    appCompatBackgroundHelper.loadFromAttributes(paramAttributeSet, paramInt);
    AppCompatImageHelper appCompatImageHelper = new AppCompatImageHelper(this);
    this.mImageHelper = appCompatImageHelper;
    appCompatImageHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.applySupportBackgroundTint(); 
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.applySupportImageTint(); 
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      ColorStateList colorStateList = appCompatBackgroundHelper.getSupportBackgroundTintList();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (ColorStateList)appCompatBackgroundHelper;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      PorterDuff.Mode mode = appCompatBackgroundHelper.getSupportBackgroundTintMode();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (PorterDuff.Mode)appCompatBackgroundHelper;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportImageTintList() {
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null) {
      ColorStateList colorStateList = appCompatImageHelper.getSupportImageTintList();
    } else {
      appCompatImageHelper = null;
    } 
    return (ColorStateList)appCompatImageHelper;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportImageTintMode() {
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null) {
      PorterDuff.Mode mode = appCompatImageHelper.getSupportImageTintMode();
    } else {
      appCompatImageHelper = null;
    } 
    return (PorterDuff.Mode)appCompatImageHelper;
  }
  
  public boolean hasOverlappingRendering() {
    boolean bool;
    if (this.mImageHelper.hasOverlappingRendering() && super.hasOverlappingRendering()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable); 
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt) {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundResource(paramInt); 
  }
  
  public void setImageBitmap(Bitmap paramBitmap) {
    super.setImageBitmap(paramBitmap);
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.applySupportImageTint(); 
  }
  
  public void setImageDrawable(@Nullable Drawable paramDrawable) {
    super.setImageDrawable(paramDrawable);
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.applySupportImageTint(); 
  }
  
  public void setImageIcon(@Nullable Icon paramIcon) {
    super.setImageIcon(paramIcon);
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.applySupportImageTint(); 
  }
  
  public void setImageResource(@DrawableRes int paramInt) {
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.setImageResource(paramInt); 
  }
  
  public void setImageURI(@Nullable Uri paramUri) {
    super.setImageURI(paramUri);
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.applySupportImageTint(); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportImageTintList(@Nullable ColorStateList paramColorStateList) {
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.setSupportImageTintList(paramColorStateList); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportImageTintMode(@Nullable PorterDuff.Mode paramMode) {
    AppCompatImageHelper appCompatImageHelper = this.mImageHelper;
    if (appCompatImageHelper != null)
      appCompatImageHelper.setSupportImageTintMode(paramMode); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AppCompatImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */