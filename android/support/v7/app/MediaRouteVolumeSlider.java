package android.support.v7.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;

class MediaRouteVolumeSlider extends AppCompatSeekBar {
  private static final String TAG = "MediaRouteVolumeSlider";
  
  private int mColor;
  
  private final float mDisabledAlpha;
  
  private boolean mHideThumb;
  
  private Drawable mThumb;
  
  public MediaRouteVolumeSlider(Context paramContext) {
    this(paramContext, null);
  }
  
  public MediaRouteVolumeSlider(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.seekBarStyle);
  }
  
  public MediaRouteVolumeSlider(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(paramContext);
  }
  
  protected void drawableStateChanged() {
    int i;
    super.drawableStateChanged();
    if (isEnabled()) {
      i = 255;
    } else {
      i = (int)(this.mDisabledAlpha * 255.0F);
    } 
    this.mThumb.setColorFilter(this.mColor, PorterDuff.Mode.SRC_IN);
    this.mThumb.setAlpha(i);
    getProgressDrawable().setColorFilter(this.mColor, PorterDuff.Mode.SRC_IN);
    getProgressDrawable().setAlpha(i);
  }
  
  public void setColor(int paramInt) {
    if (this.mColor == paramInt)
      return; 
    if (Color.alpha(paramInt) != 255) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Volume slider color cannot be translucent: #");
      stringBuilder.append(Integer.toHexString(paramInt));
      Log.e("MediaRouteVolumeSlider", stringBuilder.toString());
    } 
    this.mColor = paramInt;
  }
  
  public void setHideThumb(boolean paramBoolean) {
    Drawable drawable;
    if (this.mHideThumb == paramBoolean)
      return; 
    this.mHideThumb = paramBoolean;
    if (paramBoolean) {
      drawable = null;
    } else {
      drawable = this.mThumb;
    } 
    super.setThumb(drawable);
  }
  
  public void setThumb(Drawable paramDrawable) {
    this.mThumb = paramDrawable;
    if (this.mHideThumb)
      paramDrawable = null; 
    super.setThumb(paramDrawable);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteVolumeSlider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */