package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
  private static final CompoundButtonCompatBaseImpl IMPL;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      IMPL = new CompoundButtonCompatApi23Impl();
    } else if (i >= 21) {
      IMPL = new CompoundButtonCompatApi21Impl();
    } else {
      IMPL = new CompoundButtonCompatBaseImpl();
    } 
  }
  
  @Nullable
  public static Drawable getButtonDrawable(@NonNull CompoundButton paramCompoundButton) {
    return IMPL.getButtonDrawable(paramCompoundButton);
  }
  
  @Nullable
  public static ColorStateList getButtonTintList(@NonNull CompoundButton paramCompoundButton) {
    return IMPL.getButtonTintList(paramCompoundButton);
  }
  
  @Nullable
  public static PorterDuff.Mode getButtonTintMode(@NonNull CompoundButton paramCompoundButton) {
    return IMPL.getButtonTintMode(paramCompoundButton);
  }
  
  public static void setButtonTintList(@NonNull CompoundButton paramCompoundButton, @Nullable ColorStateList paramColorStateList) {
    IMPL.setButtonTintList(paramCompoundButton, paramColorStateList);
  }
  
  public static void setButtonTintMode(@NonNull CompoundButton paramCompoundButton, @Nullable PorterDuff.Mode paramMode) {
    IMPL.setButtonTintMode(paramCompoundButton, paramMode);
  }
  
  @RequiresApi(21)
  static class CompoundButtonCompatApi21Impl extends CompoundButtonCompatBaseImpl {
    public ColorStateList getButtonTintList(CompoundButton param1CompoundButton) {
      return param1CompoundButton.getButtonTintList();
    }
    
    public PorterDuff.Mode getButtonTintMode(CompoundButton param1CompoundButton) {
      return param1CompoundButton.getButtonTintMode();
    }
    
    public void setButtonTintList(CompoundButton param1CompoundButton, ColorStateList param1ColorStateList) {
      param1CompoundButton.setButtonTintList(param1ColorStateList);
    }
    
    public void setButtonTintMode(CompoundButton param1CompoundButton, PorterDuff.Mode param1Mode) {
      param1CompoundButton.setButtonTintMode(param1Mode);
    }
  }
  
  @RequiresApi(23)
  static class CompoundButtonCompatApi23Impl extends CompoundButtonCompatApi21Impl {
    public Drawable getButtonDrawable(CompoundButton param1CompoundButton) {
      return param1CompoundButton.getButtonDrawable();
    }
  }
  
  static class CompoundButtonCompatBaseImpl {
    private static final String TAG = "CompoundButtonCompat";
    
    private static Field sButtonDrawableField;
    
    private static boolean sButtonDrawableFieldFetched;
    
    public Drawable getButtonDrawable(CompoundButton param1CompoundButton) {
      if (!sButtonDrawableFieldFetched) {
        try {
          Field field1 = CompoundButton.class.getDeclaredField("mButtonDrawable");
          sButtonDrawableField = field1;
          field1.setAccessible(true);
        } catch (NoSuchFieldException noSuchFieldException) {
          Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", noSuchFieldException);
        } 
        sButtonDrawableFieldFetched = true;
      } 
      Field field = sButtonDrawableField;
      if (field != null)
        try {
          return (Drawable)field.get(param1CompoundButton);
        } catch (IllegalAccessException illegalAccessException) {
          Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", illegalAccessException);
          sButtonDrawableField = null;
        }  
      return null;
    }
    
    public ColorStateList getButtonTintList(CompoundButton param1CompoundButton) {
      return (param1CompoundButton instanceof TintableCompoundButton) ? ((TintableCompoundButton)param1CompoundButton).getSupportButtonTintList() : null;
    }
    
    public PorterDuff.Mode getButtonTintMode(CompoundButton param1CompoundButton) {
      return (param1CompoundButton instanceof TintableCompoundButton) ? ((TintableCompoundButton)param1CompoundButton).getSupportButtonTintMode() : null;
    }
    
    public void setButtonTintList(CompoundButton param1CompoundButton, ColorStateList param1ColorStateList) {
      if (param1CompoundButton instanceof TintableCompoundButton)
        ((TintableCompoundButton)param1CompoundButton).setSupportButtonTintList(param1ColorStateList); 
    }
    
    public void setButtonTintMode(CompoundButton param1CompoundButton, PorterDuff.Mode param1Mode) {
      if (param1CompoundButton instanceof TintableCompoundButton)
        ((TintableCompoundButton)param1CompoundButton).setSupportButtonTintMode(param1Mode); 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/CompoundButtonCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */