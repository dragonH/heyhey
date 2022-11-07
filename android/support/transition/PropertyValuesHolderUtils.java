package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

class PropertyValuesHolderUtils {
  private static final PropertyValuesHolderUtilsImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      IMPL = new PropertyValuesHolderUtilsApi21();
    } else {
      IMPL = new PropertyValuesHolderUtilsApi14();
    } 
  }
  
  static PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath) {
    return IMPL.ofPointF(paramProperty, paramPath);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/PropertyValuesHolderUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */