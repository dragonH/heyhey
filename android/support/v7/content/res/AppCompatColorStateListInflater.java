package android.support.v7.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class AppCompatColorStateListInflater {
  private static final int DEFAULT_COLOR = -65536;
  
  @NonNull
  public static ColorStateList createFromXml(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return createFromXmlInner(paramResources, paramXmlPullParser, attributeSet, paramTheme); 
    XmlPullParserException xmlPullParserException = new XmlPullParserException("No start tag found");
    throw xmlPullParserException;
  }
  
  @NonNull
  private static ColorStateList createFromXmlInner(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    String str = paramXmlPullParser.getName();
    if (str.equals("selector"))
      return inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramXmlPullParser.getPositionDescription());
    stringBuilder.append(": invalid color state list tag ");
    stringBuilder.append(str);
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  private static ColorStateList inflate(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    int[][] arrayOfInt3 = new int[20][];
    int[] arrayOfInt4 = new int[20];
    byte b = 0;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorStateListItem);
          int m = typedArray.getColor(R.styleable.ColorStateListItem_android_color, -65281);
          float f = 1.0F;
          j = R.styleable.ColorStateListItem_android_alpha;
          if (typedArray.hasValue(j)) {
            f = typedArray.getFloat(j, 1.0F);
          } else {
            j = R.styleable.ColorStateListItem_alpha;
            if (typedArray.hasValue(j))
              f = typedArray.getFloat(j, 1.0F); 
          } 
          typedArray.recycle();
          int n = paramAttributeSet.getAttributeCount();
          int[] arrayOfInt = new int[n];
          j = 0;
          for (k = 0; j < n; k = i2) {
            int i1 = paramAttributeSet.getAttributeNameResource(j);
            int i2 = k;
            if (i1 != 16843173) {
              i2 = k;
              if (i1 != 16843551) {
                i2 = k;
                if (i1 != R.attr.alpha) {
                  if (paramAttributeSet.getAttributeBooleanValue(j, false)) {
                    i2 = i1;
                  } else {
                    i2 = -i1;
                  } 
                  arrayOfInt[k] = i2;
                  i2 = k + 1;
                } 
              } 
            } 
            j++;
          } 
          arrayOfInt = StateSet.trimStateSet(arrayOfInt, k);
          j = modulateColorAlpha(m, f);
          if (b)
            k = arrayOfInt.length; 
          arrayOfInt4 = GrowingArrayUtils.append(arrayOfInt4, b, j);
          arrayOfInt3 = GrowingArrayUtils.<int[]>append(arrayOfInt3, b, arrayOfInt);
          b++;
          continue;
        } 
      } 
      break;
    } 
    int[] arrayOfInt2 = new int[b];
    int[][] arrayOfInt1 = new int[b][];
    System.arraycopy(arrayOfInt4, 0, arrayOfInt2, 0, b);
    System.arraycopy(arrayOfInt3, 0, arrayOfInt1, 0, b);
    return new ColorStateList(arrayOfInt1, arrayOfInt2);
  }
  
  private static int modulateColorAlpha(int paramInt, float paramFloat) {
    return ColorUtils.setAlphaComponent(paramInt, Math.round(Color.alpha(paramInt) * paramFloat));
  }
  
  private static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    TypedArray typedArray;
    if (paramTheme == null) {
      typedArray = paramResources.obtainAttributes(paramAttributeSet, paramArrayOfint);
    } else {
      typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
    } 
    return typedArray;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/content/res/AppCompatColorStateListInflater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */