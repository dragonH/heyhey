package com.xamarin.forms.platform.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class FormsViewGroup extends ViewGroup {
  boolean inputTransparent;
  
  public FormsViewGroup(Context paramContext) {
    super(paramContext);
  }
  
  public FormsViewGroup(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public FormsViewGroup(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected boolean getInputTransparent() {
    return this.inputTransparent;
  }
  
  public void measureAndLayout(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    measure(paramInt1, paramInt2);
    layout(paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    return this.inputTransparent ? false : super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return this.inputTransparent ? false : super.onTouchEvent(paramMotionEvent);
  }
  
  public void sendBatchUpdate(float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) {
    setPivotX(paramFloat1);
    setPivotY(paramFloat2);
    if (getVisibility() != paramInt)
      setVisibility(paramInt); 
    if (isEnabled() != paramBoolean)
      setEnabled(paramBoolean); 
    setAlpha(paramFloat3);
    setRotation(paramFloat4);
    setRotationX(paramFloat5);
    setRotationY(paramFloat6);
    setScaleX(paramFloat7);
    setScaleY(paramFloat7);
    setTranslationX(paramFloat8);
    setTranslationY(paramFloat9);
  }
  
  protected void setInputTransparent(boolean paramBoolean) {
    this.inputTransparent = paramBoolean;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/xamarin/forms/platform/android/FormsViewGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */