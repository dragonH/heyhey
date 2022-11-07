package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R;
import android.support.v7.mediarouter.R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class MediaRouterThemeHelper {
  static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
  
  static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
  
  private static final float MIN_CONTRAST = 3.0F;
  
  static int createThemeForDialog(Context paramContext, int paramInt) {
    int i = getThemeResource(paramContext, R.attr.mediaRouteTheme);
    if (i != 0) {
      paramInt = i;
    } else {
      paramInt = getStyledRouterThemeId(paramContext, paramInt);
    } 
    return paramInt;
  }
  
  static Context createThemedContext(Context paramContext, int paramInt) {
    ContextThemeWrapper contextThemeWrapper1;
    ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(paramContext, getStyledRouterThemeId(paramContext, paramInt));
    paramInt = getThemeResource(paramContext, R.attr.mediaRouteTheme);
    if (paramInt == 0) {
      contextThemeWrapper1 = contextThemeWrapper2;
    } else {
      contextThemeWrapper1 = new ContextThemeWrapper((Context)contextThemeWrapper2, paramInt);
    } 
    return (Context)contextThemeWrapper1;
  }
  
  public static int getAlertDialogResolvedTheme(Context paramContext, int paramInt) {
    if (paramInt >= 16777216)
      return paramInt; 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
    return typedValue.resourceId;
  }
  
  public static int getButtonTextColor(Context paramContext) {
    int i = getThemeColor(paramContext, 0, R.attr.colorPrimary);
    return (ColorUtils.calculateContrast(i, getThemeColor(paramContext, 0, 16842801)) < 3.0D) ? getThemeColor(paramContext, 0, R.attr.colorAccent) : i;
  }
  
  public static int getControllerColor(Context paramContext, int paramInt) {
    return (ColorUtils.calculateContrast(-1, getThemeColor(paramContext, paramInt, R.attr.colorPrimary)) >= 3.0D) ? -1 : -570425344;
  }
  
  public static float getDisabledAlpha(Context paramContext) {
    float f;
    TypedValue typedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(16842803, typedValue, true)) {
      f = typedValue.getFloat();
    } else {
      f = 0.5F;
    } 
    return f;
  }
  
  private static int getStyledRouterThemeId(Context paramContext, int paramInt) {
    if (isLightTheme(paramContext)) {
      if (getControllerColor(paramContext, paramInt) == -570425344) {
        paramInt = R.style.Theme_MediaRouter_Light;
      } else {
        paramInt = R.style.Theme_MediaRouter_Light_DarkControlPanel;
      } 
    } else if (getControllerColor(paramContext, paramInt) == -570425344) {
      paramInt = R.style.Theme_MediaRouter_LightControlPanel;
    } else {
      paramInt = R.style.Theme_MediaRouter;
    } 
    return paramInt;
  }
  
  private static int getThemeColor(Context paramContext, int paramInt1, int paramInt2) {
    if (paramInt1 != 0) {
      TypedArray typedArray = paramContext.obtainStyledAttributes(paramInt1, new int[] { paramInt2 });
      paramInt1 = typedArray.getColor(0, 0);
      typedArray.recycle();
      if (paramInt1 != 0)
        return paramInt1; 
    } 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt2, typedValue, true);
    return (typedValue.resourceId != 0) ? paramContext.getResources().getColor(typedValue.resourceId) : typedValue.data;
  }
  
  public static int getThemeResource(Context paramContext, int paramInt) {
    TypedValue typedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(paramInt, typedValue, true)) {
      paramInt = typedValue.resourceId;
    } else {
      paramInt = 0;
    } 
    return paramInt;
  }
  
  private static boolean isLightTheme(Context paramContext) {
    TypedValue typedValue = new TypedValue();
    Resources.Theme theme = paramContext.getTheme();
    int i = R.attr.isLightTheme;
    boolean bool = true;
    if (!theme.resolveAttribute(i, typedValue, true) || typedValue.data == 0)
      bool = false; 
    return bool;
  }
  
  public static void setMediaControlsBackgroundColor(Context paramContext, View paramView1, View paramView2, boolean paramBoolean) {
    int i = getThemeColor(paramContext, 0, R.attr.colorPrimary);
    int j = getThemeColor(paramContext, 0, R.attr.colorPrimaryDark);
    int k = i;
    int m = j;
    if (paramBoolean) {
      k = i;
      m = j;
      if (getControllerColor(paramContext, 0) == -570425344) {
        m = i;
        k = -1;
      } 
    } 
    paramView1.setBackgroundColor(k);
    paramView2.setBackgroundColor(m);
    paramView1.setTag(Integer.valueOf(k));
    paramView2.setTag(Integer.valueOf(m));
  }
  
  public static void setVolumeSliderColor(Context paramContext, MediaRouteVolumeSlider paramMediaRouteVolumeSlider, View paramView) {
    int i = getControllerColor(paramContext, 0);
    int j = i;
    if (Color.alpha(i) != 255)
      j = ColorUtils.compositeColors(i, ((Integer)paramView.getTag()).intValue()); 
    paramMediaRouteVolumeSlider.setColor(j);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ControllerColorType {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouterThemeHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */