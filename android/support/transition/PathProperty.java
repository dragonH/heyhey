package android.support.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty<T> extends Property<T, Float> {
  private float mCurrentFraction;
  
  private final float mPathLength;
  
  private final PathMeasure mPathMeasure;
  
  private final PointF mPointF = new PointF();
  
  private final float[] mPosition = new float[2];
  
  private final Property<T, PointF> mProperty;
  
  PathProperty(Property<T, PointF> paramProperty, Path paramPath) {
    super(Float.class, paramProperty.getName());
    this.mProperty = paramProperty;
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    this.mPathMeasure = pathMeasure;
    this.mPathLength = pathMeasure.getLength();
  }
  
  public Float get(T paramT) {
    return Float.valueOf(this.mCurrentFraction);
  }
  
  public void set(T paramT, Float paramFloat) {
    this.mCurrentFraction = paramFloat.floatValue();
    this.mPathMeasure.getPosTan(this.mPathLength * paramFloat.floatValue(), this.mPosition, null);
    PointF pointF = this.mPointF;
    float[] arrayOfFloat = this.mPosition;
    pointF.x = arrayOfFloat[0];
    pointF.y = arrayOfFloat[1];
    this.mProperty.set(paramT, pointF);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/PathProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */