package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.R;
import com.google.android.gms.common.util.zzi;

public final class zzbx extends Button {
  public zzbx(Context paramContext) {
    this(paramContext, null);
  }
  
  private zzbx(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, null, 16842824);
  }
  
  private static int zzf(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 != 0) {
      if (paramInt1 != 1) {
        if (paramInt1 == 2)
          return paramInt4; 
        StringBuilder stringBuilder = new StringBuilder(33);
        stringBuilder.append("Unknown color scheme: ");
        stringBuilder.append(paramInt1);
        throw new IllegalStateException(stringBuilder.toString());
      } 
      return paramInt3;
    } 
    return paramInt2;
  }
  
  public final void zza(Resources paramResources, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder;
    setTypeface(Typeface.DEFAULT_BOLD);
    setTextSize(14.0F);
    int i = (int)((paramResources.getDisplayMetrics()).density * 48.0F + 0.5F);
    setMinHeight(i);
    setMinWidth(i);
    i = R.drawable.common_google_signin_btn_icon_dark;
    int j = R.drawable.common_google_signin_btn_icon_light;
    i = zzf(paramInt2, i, j, j);
    int k = R.drawable.common_google_signin_btn_text_dark;
    j = R.drawable.common_google_signin_btn_text_light;
    j = zzf(paramInt2, k, j, j);
    if (paramInt1 != 0 && paramInt1 != 1) {
      if (paramInt1 != 2) {
        stringBuilder = new StringBuilder(32);
        stringBuilder.append("Unknown button size: ");
        stringBuilder.append(paramInt1);
        throw new IllegalStateException(stringBuilder.toString());
      } 
    } else {
      i = j;
    } 
    Drawable drawable = DrawableCompat.wrap(stringBuilder.getDrawable(i));
    DrawableCompat.setTintList(drawable, stringBuilder.getColorStateList(R.color.common_google_signin_btn_tint));
    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);
    setBackgroundDrawable(drawable);
    j = R.color.common_google_signin_btn_text_dark;
    i = R.color.common_google_signin_btn_text_light;
    setTextColor(zzbp.<ColorStateList>zzu(stringBuilder.getColorStateList(zzf(paramInt2, j, i, i))));
    if (paramInt1 != 0) {
      if (paramInt1 != 1) {
        if (paramInt1 == 2) {
          setText(null);
        } else {
          stringBuilder = new StringBuilder(32);
          stringBuilder.append("Unknown button size: ");
          stringBuilder.append(paramInt1);
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } else {
        paramInt1 = R.string.common_signin_button_text_long;
        setText(stringBuilder.getString(paramInt1));
      } 
    } else {
      paramInt1 = R.string.common_signin_button_text;
      setText(stringBuilder.getString(paramInt1));
    } 
    setTransformationMethod(null);
    if (zzi.zzci(getContext()))
      setGravity(19); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */