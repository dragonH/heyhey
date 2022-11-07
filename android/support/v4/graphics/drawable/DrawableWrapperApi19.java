package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class DrawableWrapperApi19 extends DrawableWrapperApi14 {
  DrawableWrapperApi19(Drawable paramDrawable) {
    super(paramDrawable);
  }
  
  DrawableWrapperApi19(DrawableWrapperApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources) {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public boolean isAutoMirrored() {
    return this.mDrawable.isAutoMirrored();
  }
  
  @NonNull
  DrawableWrapperApi14.DrawableWrapperState mutateConstantState() {
    return new DrawableWrapperStateKitKat(this.mState, null);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    this.mDrawable.setAutoMirrored(paramBoolean);
  }
  
  private static class DrawableWrapperStateKitKat extends DrawableWrapperApi14.DrawableWrapperState {
    DrawableWrapperStateKitKat(@Nullable DrawableWrapperApi14.DrawableWrapperState param1DrawableWrapperState, @Nullable Resources param1Resources) {
      super(param1DrawableWrapperState, param1Resources);
    }
    
    public Drawable newDrawable(@Nullable Resources param1Resources) {
      return new DrawableWrapperApi19(this, param1Resources);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/graphics/drawable/DrawableWrapperApi19.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */