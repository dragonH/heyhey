package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;

public interface TintableCompoundButton {
  @Nullable
  ColorStateList getSupportButtonTintList();
  
  @Nullable
  PorterDuff.Mode getSupportButtonTintMode();
  
  void setSupportButtonTintList(@Nullable ColorStateList paramColorStateList);
  
  void setSupportButtonTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/TintableCompoundButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */