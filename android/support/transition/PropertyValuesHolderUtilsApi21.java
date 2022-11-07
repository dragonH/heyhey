package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(21)
class PropertyValuesHolderUtilsApi21 implements PropertyValuesHolderUtilsImpl {
  public PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath) {
    return PropertyValuesHolder.ofObject(paramProperty, null, paramPath);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/PropertyValuesHolderUtilsApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */