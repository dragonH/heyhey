package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

public class PatternPathMotion extends PathMotion {
  private Path mOriginalPatternPath;
  
  private final Path mPatternPath;
  
  private final Matrix mTempMatrix;
  
  public PatternPathMotion() {
    Path path = new Path();
    this.mPatternPath = path;
    this.mTempMatrix = new Matrix();
    path.lineTo(1.0F, 0.0F);
    this.mOriginalPatternPath = path;
  }
  
  public PatternPathMotion(Context paramContext, AttributeSet paramAttributeSet) {
    this.mPatternPath = new Path();
    this.mTempMatrix = new Matrix();
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, Styleable.PATTERN_PATH_MOTION);
    try {
      String str = TypedArrayUtils.getNamedString(typedArray, (XmlPullParser)paramAttributeSet, "patternPathData", 0);
      if (str != null) {
        setPatternPath(PathParser.createPathFromPathData(str));
        return;
      } 
      RuntimeException runtimeException = new RuntimeException();
      this("pathData must be supplied for patternPathMotion");
      throw runtimeException;
    } finally {
      typedArray.recycle();
    } 
  }
  
  public PatternPathMotion(Path paramPath) {
    this.mPatternPath = new Path();
    this.mTempMatrix = new Matrix();
    setPatternPath(paramPath);
  }
  
  private static float distance(float paramFloat1, float paramFloat2) {
    return (float)Math.sqrt((paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2));
  }
  
  public Path getPath(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    paramFloat3 -= paramFloat1;
    paramFloat4 -= paramFloat2;
    float f = distance(paramFloat3, paramFloat4);
    double d = Math.atan2(paramFloat4, paramFloat3);
    this.mTempMatrix.setScale(f, f);
    this.mTempMatrix.postRotate((float)Math.toDegrees(d));
    this.mTempMatrix.postTranslate(paramFloat1, paramFloat2);
    Path path = new Path();
    this.mPatternPath.transform(this.mTempMatrix, path);
    return path;
  }
  
  public Path getPatternPath() {
    return this.mOriginalPatternPath;
  }
  
  public void setPatternPath(Path paramPath) {
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    float f1 = pathMeasure.getLength();
    float[] arrayOfFloat = new float[2];
    pathMeasure.getPosTan(f1, arrayOfFloat, null);
    f1 = arrayOfFloat[0];
    float f2 = arrayOfFloat[1];
    pathMeasure.getPosTan(0.0F, arrayOfFloat, null);
    float f3 = arrayOfFloat[0];
    float f4 = arrayOfFloat[1];
    if (f3 != f1 || f4 != f2) {
      this.mTempMatrix.setTranslate(-f3, -f4);
      f1 -= f3;
      f4 = f2 - f4;
      f2 = 1.0F / distance(f1, f4);
      this.mTempMatrix.postScale(f2, f2);
      double d = Math.atan2(f4, f1);
      this.mTempMatrix.postRotate((float)Math.toDegrees(-d));
      paramPath.transform(this.mTempMatrix, this.mPatternPath);
      this.mOriginalPatternPath = paramPath;
      return;
    } 
    throw new IllegalArgumentException("pattern must not end at the starting point");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/PatternPathMotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */