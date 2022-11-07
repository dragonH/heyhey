package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public final class TextViewCompat {
  public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
  
  public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
  
  static final TextViewCompatBaseImpl IMPL;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 26) {
      IMPL = new TextViewCompatApi26Impl();
    } else if (i >= 23) {
      IMPL = new TextViewCompatApi23Impl();
    } else if (i >= 18) {
      IMPL = new TextViewCompatApi18Impl();
    } else if (i >= 17) {
      IMPL = new TextViewCompatApi17Impl();
    } else {
      IMPL = new TextViewCompatApi16Impl();
    } 
  }
  
  public static int getAutoSizeMaxTextSize(TextView paramTextView) {
    return IMPL.getAutoSizeMaxTextSize(paramTextView);
  }
  
  public static int getAutoSizeMinTextSize(TextView paramTextView) {
    return IMPL.getAutoSizeMinTextSize(paramTextView);
  }
  
  public static int getAutoSizeStepGranularity(TextView paramTextView) {
    return IMPL.getAutoSizeStepGranularity(paramTextView);
  }
  
  public static int[] getAutoSizeTextAvailableSizes(TextView paramTextView) {
    return IMPL.getAutoSizeTextAvailableSizes(paramTextView);
  }
  
  public static int getAutoSizeTextType(TextView paramTextView) {
    return IMPL.getAutoSizeTextType(paramTextView);
  }
  
  public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView paramTextView) {
    return IMPL.getCompoundDrawablesRelative(paramTextView);
  }
  
  public static int getMaxLines(@NonNull TextView paramTextView) {
    return IMPL.getMaxLines(paramTextView);
  }
  
  public static int getMinLines(@NonNull TextView paramTextView) {
    return IMPL.getMinLines(paramTextView);
  }
  
  public static void setAutoSizeTextTypeUniformWithConfiguration(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    IMPL.setAutoSizeTextTypeUniformWithConfiguration(paramTextView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView paramTextView, @NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    IMPL.setAutoSizeTextTypeUniformWithPresetSizes(paramTextView, paramArrayOfint, paramInt);
  }
  
  public static void setAutoSizeTextTypeWithDefaults(TextView paramTextView, int paramInt) {
    IMPL.setAutoSizeTextTypeWithDefaults(paramTextView, paramInt);
  }
  
  public static void setCompoundDrawablesRelative(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    IMPL.setCompoundDrawablesRelative(paramTextView, paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, @DrawableRes int paramInt4) {
    IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(paramTextView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(paramTextView, paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setTextAppearance(@NonNull TextView paramTextView, @StyleRes int paramInt) {
    IMPL.setTextAppearance(paramTextView, paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AutoSizeTextType {}
  
  @RequiresApi(16)
  static class TextViewCompatApi16Impl extends TextViewCompatBaseImpl {
    public int getMaxLines(TextView param1TextView) {
      return param1TextView.getMaxLines();
    }
    
    public int getMinLines(TextView param1TextView) {
      return param1TextView.getMinLines();
    }
  }
  
  @RequiresApi(17)
  static class TextViewCompatApi17Impl extends TextViewCompatApi16Impl {
    public Drawable[] getCompoundDrawablesRelative(@NonNull TextView param1TextView) {
      int i = param1TextView.getLayoutDirection();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      Drawable[] arrayOfDrawable = param1TextView.getCompoundDrawables();
      if (bool) {
        Drawable drawable2 = arrayOfDrawable[2];
        Drawable drawable1 = arrayOfDrawable[0];
        arrayOfDrawable[0] = drawable2;
        arrayOfDrawable[2] = drawable1;
      } 
      return arrayOfDrawable;
    }
    
    public void setCompoundDrawablesRelative(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      Drawable drawable;
      int i = param1TextView.getLayoutDirection();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      if (bool) {
        drawable = param1Drawable3;
      } else {
        drawable = param1Drawable1;
      } 
      if (!bool)
        param1Drawable1 = param1Drawable3; 
      param1TextView.setCompoundDrawables(drawable, param1Drawable2, param1Drawable1, param1Drawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @DrawableRes int param1Int1, @DrawableRes int param1Int2, @DrawableRes int param1Int3, @DrawableRes int param1Int4) {
      int i = param1TextView.getLayoutDirection();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      if (bool) {
        i = param1Int3;
      } else {
        i = param1Int1;
      } 
      if (!bool)
        param1Int1 = param1Int3; 
      param1TextView.setCompoundDrawablesWithIntrinsicBounds(i, param1Int2, param1Int1, param1Int4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      Drawable drawable;
      int i = param1TextView.getLayoutDirection();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      if (bool) {
        drawable = param1Drawable3;
      } else {
        drawable = param1Drawable1;
      } 
      if (!bool)
        param1Drawable1 = param1Drawable3; 
      param1TextView.setCompoundDrawablesWithIntrinsicBounds(drawable, param1Drawable2, param1Drawable1, param1Drawable4);
    }
  }
  
  @RequiresApi(18)
  static class TextViewCompatApi18Impl extends TextViewCompatApi17Impl {
    public Drawable[] getCompoundDrawablesRelative(@NonNull TextView param1TextView) {
      return param1TextView.getCompoundDrawablesRelative();
    }
    
    public void setCompoundDrawablesRelative(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      param1TextView.setCompoundDrawablesRelative(param1Drawable1, param1Drawable2, param1Drawable3, param1Drawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @DrawableRes int param1Int1, @DrawableRes int param1Int2, @DrawableRes int param1Int3, @DrawableRes int param1Int4) {
      param1TextView.setCompoundDrawablesRelativeWithIntrinsicBounds(param1Int1, param1Int2, param1Int3, param1Int4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      param1TextView.setCompoundDrawablesRelativeWithIntrinsicBounds(param1Drawable1, param1Drawable2, param1Drawable3, param1Drawable4);
    }
  }
  
  @RequiresApi(23)
  static class TextViewCompatApi23Impl extends TextViewCompatApi18Impl {
    public void setTextAppearance(@NonNull TextView param1TextView, @StyleRes int param1Int) {
      param1TextView.setTextAppearance(param1Int);
    }
  }
  
  @RequiresApi(26)
  static class TextViewCompatApi26Impl extends TextViewCompatApi23Impl {
    public int getAutoSizeMaxTextSize(TextView param1TextView) {
      return param1TextView.getAutoSizeMaxTextSize();
    }
    
    public int getAutoSizeMinTextSize(TextView param1TextView) {
      return param1TextView.getAutoSizeMinTextSize();
    }
    
    public int getAutoSizeStepGranularity(TextView param1TextView) {
      return param1TextView.getAutoSizeStepGranularity();
    }
    
    public int[] getAutoSizeTextAvailableSizes(TextView param1TextView) {
      return param1TextView.getAutoSizeTextAvailableSizes();
    }
    
    public int getAutoSizeTextType(TextView param1TextView) {
      return param1TextView.getAutoSizeTextType();
    }
    
    public void setAutoSizeTextTypeUniformWithConfiguration(TextView param1TextView, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws IllegalArgumentException {
      param1TextView.setAutoSizeTextTypeUniformWithConfiguration(param1Int1, param1Int2, param1Int3, param1Int4);
    }
    
    public void setAutoSizeTextTypeUniformWithPresetSizes(TextView param1TextView, @NonNull int[] param1ArrayOfint, int param1Int) throws IllegalArgumentException {
      param1TextView.setAutoSizeTextTypeUniformWithPresetSizes(param1ArrayOfint, param1Int);
    }
    
    public void setAutoSizeTextTypeWithDefaults(TextView param1TextView, int param1Int) {
      param1TextView.setAutoSizeTextTypeWithDefaults(param1Int);
    }
  }
  
  static class TextViewCompatBaseImpl {
    private static final int LINES = 1;
    
    private static final String LOG_TAG = "TextViewCompatBase";
    
    private static Field sMaxModeField;
    
    private static boolean sMaxModeFieldFetched;
    
    private static Field sMaximumField;
    
    private static boolean sMaximumFieldFetched;
    
    private static Field sMinModeField;
    
    private static boolean sMinModeFieldFetched;
    
    private static Field sMinimumField;
    
    private static boolean sMinimumFieldFetched;
    
    private static Field retrieveField(String param1String) {
      Field field = null;
      try {
        Field field1 = TextView.class.getDeclaredField(param1String);
        field = field1;
        field1.setAccessible(true);
        field = field1;
      } catch (NoSuchFieldException noSuchFieldException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not retrieve ");
        stringBuilder.append(param1String);
        stringBuilder.append(" field.");
        Log.e("TextViewCompatBase", stringBuilder.toString());
      } 
      return field;
    }
    
    private static int retrieveIntFromField(Field param1Field, TextView param1TextView) {
      try {
        return param1Field.getInt(param1TextView);
      } catch (IllegalAccessException illegalAccessException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not retrieve value of ");
        stringBuilder.append(param1Field.getName());
        stringBuilder.append(" field.");
        Log.d("TextViewCompatBase", stringBuilder.toString());
        return -1;
      } 
    }
    
    public int getAutoSizeMaxTextSize(TextView param1TextView) {
      return (param1TextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)param1TextView).getAutoSizeMaxTextSize() : -1;
    }
    
    public int getAutoSizeMinTextSize(TextView param1TextView) {
      return (param1TextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)param1TextView).getAutoSizeMinTextSize() : -1;
    }
    
    public int getAutoSizeStepGranularity(TextView param1TextView) {
      return (param1TextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)param1TextView).getAutoSizeStepGranularity() : -1;
    }
    
    public int[] getAutoSizeTextAvailableSizes(TextView param1TextView) {
      return (param1TextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)param1TextView).getAutoSizeTextAvailableSizes() : new int[0];
    }
    
    public int getAutoSizeTextType(TextView param1TextView) {
      return (param1TextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)param1TextView).getAutoSizeTextType() : 0;
    }
    
    public Drawable[] getCompoundDrawablesRelative(@NonNull TextView param1TextView) {
      return param1TextView.getCompoundDrawables();
    }
    
    public int getMaxLines(TextView param1TextView) {
      if (!sMaxModeFieldFetched) {
        sMaxModeField = retrieveField("mMaxMode");
        sMaxModeFieldFetched = true;
      } 
      Field field = sMaxModeField;
      if (field != null && retrieveIntFromField(field, param1TextView) == 1) {
        if (!sMaximumFieldFetched) {
          sMaximumField = retrieveField("mMaximum");
          sMaximumFieldFetched = true;
        } 
        field = sMaximumField;
        if (field != null)
          return retrieveIntFromField(field, param1TextView); 
      } 
      return -1;
    }
    
    public int getMinLines(TextView param1TextView) {
      if (!sMinModeFieldFetched) {
        sMinModeField = retrieveField("mMinMode");
        sMinModeFieldFetched = true;
      } 
      Field field = sMinModeField;
      if (field != null && retrieveIntFromField(field, param1TextView) == 1) {
        if (!sMinimumFieldFetched) {
          sMinimumField = retrieveField("mMinimum");
          sMinimumFieldFetched = true;
        } 
        field = sMinimumField;
        if (field != null)
          return retrieveIntFromField(field, param1TextView); 
      } 
      return -1;
    }
    
    public void setAutoSizeTextTypeUniformWithConfiguration(TextView param1TextView, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws IllegalArgumentException {
      if (param1TextView instanceof AutoSizeableTextView)
        ((AutoSizeableTextView)param1TextView).setAutoSizeTextTypeUniformWithConfiguration(param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    public void setAutoSizeTextTypeUniformWithPresetSizes(TextView param1TextView, @NonNull int[] param1ArrayOfint, int param1Int) throws IllegalArgumentException {
      if (param1TextView instanceof AutoSizeableTextView)
        ((AutoSizeableTextView)param1TextView).setAutoSizeTextTypeUniformWithPresetSizes(param1ArrayOfint, param1Int); 
    }
    
    public void setAutoSizeTextTypeWithDefaults(TextView param1TextView, int param1Int) {
      if (param1TextView instanceof AutoSizeableTextView)
        ((AutoSizeableTextView)param1TextView).setAutoSizeTextTypeWithDefaults(param1Int); 
    }
    
    public void setCompoundDrawablesRelative(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      param1TextView.setCompoundDrawables(param1Drawable1, param1Drawable2, param1Drawable3, param1Drawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @DrawableRes int param1Int1, @DrawableRes int param1Int2, @DrawableRes int param1Int3, @DrawableRes int param1Int4) {
      param1TextView.setCompoundDrawablesWithIntrinsicBounds(param1Int1, param1Int2, param1Int3, param1Int4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView param1TextView, @Nullable Drawable param1Drawable1, @Nullable Drawable param1Drawable2, @Nullable Drawable param1Drawable3, @Nullable Drawable param1Drawable4) {
      param1TextView.setCompoundDrawablesWithIntrinsicBounds(param1Drawable1, param1Drawable2, param1Drawable3, param1Drawable4);
    }
    
    public void setTextAppearance(TextView param1TextView, @StyleRes int param1Int) {
      param1TextView.setTextAppearance(param1TextView.getContext(), param1Int);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/TextViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */