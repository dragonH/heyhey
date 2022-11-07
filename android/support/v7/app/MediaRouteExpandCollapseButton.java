package android.support.v7.app;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.mediarouter.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

class MediaRouteExpandCollapseButton extends ImageButton {
  final AnimationDrawable mCollapseAnimationDrawable;
  
  final String mCollapseGroupDescription;
  
  final AnimationDrawable mExpandAnimationDrawable;
  
  final String mExpandGroupDescription;
  
  boolean mIsGroupExpanded;
  
  View.OnClickListener mListener;
  
  public MediaRouteExpandCollapseButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public MediaRouteExpandCollapseButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MediaRouteExpandCollapseButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    AnimationDrawable animationDrawable1 = (AnimationDrawable)ContextCompat.getDrawable(paramContext, R.drawable.mr_group_expand);
    this.mExpandAnimationDrawable = animationDrawable1;
    AnimationDrawable animationDrawable2 = (AnimationDrawable)ContextCompat.getDrawable(paramContext, R.drawable.mr_group_collapse);
    this.mCollapseAnimationDrawable = animationDrawable2;
    PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(MediaRouterThemeHelper.getControllerColor(paramContext, paramInt), PorterDuff.Mode.SRC_IN);
    animationDrawable1.setColorFilter((ColorFilter)porterDuffColorFilter);
    animationDrawable2.setColorFilter((ColorFilter)porterDuffColorFilter);
    String str = paramContext.getString(R.string.mr_controller_expand_group);
    this.mExpandGroupDescription = str;
    this.mCollapseGroupDescription = paramContext.getString(R.string.mr_controller_collapse_group);
    setImageDrawable(animationDrawable1.getFrame(0));
    setContentDescription(str);
    super.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
            int i = mediaRouteExpandCollapseButton.mIsGroupExpanded ^ true;
            mediaRouteExpandCollapseButton.mIsGroupExpanded = i;
            if (i != 0) {
              mediaRouteExpandCollapseButton.setImageDrawable((Drawable)mediaRouteExpandCollapseButton.mExpandAnimationDrawable);
              MediaRouteExpandCollapseButton.this.mExpandAnimationDrawable.start();
              mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
              mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.mCollapseGroupDescription);
            } else {
              mediaRouteExpandCollapseButton.setImageDrawable((Drawable)mediaRouteExpandCollapseButton.mCollapseAnimationDrawable);
              MediaRouteExpandCollapseButton.this.mCollapseAnimationDrawable.start();
              mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
              mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.mExpandGroupDescription);
            } 
            View.OnClickListener onClickListener = MediaRouteExpandCollapseButton.this.mListener;
            if (onClickListener != null)
              onClickListener.onClick(param1View); 
          }
        });
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener) {
    this.mListener = paramOnClickListener;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteExpandCollapseButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */