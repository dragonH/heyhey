package android.support.v7.graphics;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.TimingLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

final class ColorCutQuantizer {
  static final int COMPONENT_BLUE = -1;
  
  static final int COMPONENT_GREEN = -2;
  
  static final int COMPONENT_RED = -3;
  
  private static final String LOG_TAG = "ColorCutQuantizer";
  
  private static final boolean LOG_TIMINGS = false;
  
  private static final int QUANTIZE_WORD_MASK = 31;
  
  private static final int QUANTIZE_WORD_WIDTH = 5;
  
  private static final Comparator<Vbox> VBOX_COMPARATOR_VOLUME = new Comparator<Vbox>() {
      public int compare(ColorCutQuantizer.Vbox param1Vbox1, ColorCutQuantizer.Vbox param1Vbox2) {
        return param1Vbox2.getVolume() - param1Vbox1.getVolume();
      }
    };
  
  final int[] mColors;
  
  final Palette.Filter[] mFilters;
  
  final int[] mHistogram;
  
  final List<Palette.Swatch> mQuantizedColors;
  
  private final float[] mTempHsl = new float[3];
  
  final TimingLogger mTimingLogger = null;
  
  ColorCutQuantizer(int[] paramArrayOfint, int paramInt, Palette.Filter[] paramArrayOfFilter) {
    this.mFilters = paramArrayOfFilter;
    int[] arrayOfInt = new int[32768];
    this.mHistogram = arrayOfInt;
    boolean bool = false;
    int i;
    for (i = 0; i < paramArrayOfint.length; i++) {
      int k = quantizeFromRgb888(paramArrayOfint[i]);
      paramArrayOfint[i] = k;
      arrayOfInt[k] = arrayOfInt[k] + 1;
    } 
    int j = 0;
    for (i = 0; j < 32768; i = k) {
      if (arrayOfInt[j] > 0 && shouldIgnoreColor(j))
        arrayOfInt[j] = 0; 
      int k = i;
      if (arrayOfInt[j] > 0)
        k = i + 1; 
      j++;
    } 
    paramArrayOfint = new int[i];
    this.mColors = paramArrayOfint;
    byte b = 0;
    for (j = 0; b < '耀'; j = k) {
      int k = j;
      if (arrayOfInt[b] > 0) {
        paramArrayOfint[j] = b;
        k = j + 1;
      } 
      b++;
    } 
    if (i <= paramInt) {
      this.mQuantizedColors = new ArrayList<Palette.Swatch>();
      for (paramInt = bool; paramInt < i; paramInt++) {
        j = paramArrayOfint[paramInt];
        this.mQuantizedColors.add(new Palette.Swatch(approximateToRgb888(j), arrayOfInt[j]));
      } 
    } else {
      this.mQuantizedColors = quantizePixels(paramInt);
    } 
  }
  
  private static int approximateToRgb888(int paramInt) {
    return approximateToRgb888(quantizedRed(paramInt), quantizedGreen(paramInt), quantizedBlue(paramInt));
  }
  
  static int approximateToRgb888(int paramInt1, int paramInt2, int paramInt3) {
    return Color.rgb(modifyWordWidth(paramInt1, 5, 8), modifyWordWidth(paramInt2, 5, 8), modifyWordWidth(paramInt3, 5, 8));
  }
  
  private List<Palette.Swatch> generateAverageColors(Collection<Vbox> paramCollection) {
    ArrayList<Palette.Swatch> arrayList = new ArrayList(paramCollection.size());
    Iterator<Vbox> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      Palette.Swatch swatch = ((Vbox)iterator.next()).getAverageColor();
      if (!shouldIgnoreColor(swatch))
        arrayList.add(swatch); 
    } 
    return arrayList;
  }
  
  static void modifySignificantOctet(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2;
    if (paramInt1 != -2) {
      if (paramInt1 == -1)
        while (paramInt2 <= paramInt3) {
          paramInt1 = paramArrayOfint[paramInt2];
          i = quantizedBlue(paramInt1);
          int j = quantizedGreen(paramInt1);
          paramArrayOfint[paramInt2] = quantizedRed(paramInt1) | i << 10 | j << 5;
          paramInt2++;
        }  
    } else {
      while (i <= paramInt3) {
        int j = paramArrayOfint[i];
        paramInt1 = quantizedGreen(j);
        paramInt2 = quantizedRed(j);
        paramArrayOfint[i] = quantizedBlue(j) | paramInt1 << 10 | paramInt2 << 5;
        i++;
      } 
    } 
  }
  
  private static int modifyWordWidth(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 > paramInt2) {
      paramInt1 <<= paramInt3 - paramInt2;
    } else {
      paramInt1 >>= paramInt2 - paramInt3;
    } 
    return paramInt1 & (1 << paramInt3) - 1;
  }
  
  private static int quantizeFromRgb888(int paramInt) {
    int i = modifyWordWidth(Color.red(paramInt), 8, 5);
    int j = modifyWordWidth(Color.green(paramInt), 8, 5);
    return modifyWordWidth(Color.blue(paramInt), 8, 5) | i << 10 | j << 5;
  }
  
  private List<Palette.Swatch> quantizePixels(int paramInt) {
    PriorityQueue<Vbox> priorityQueue = new PriorityQueue<Vbox>(paramInt, VBOX_COMPARATOR_VOLUME);
    priorityQueue.offer(new Vbox(0, this.mColors.length - 1));
    splitBoxes(priorityQueue, paramInt);
    return generateAverageColors(priorityQueue);
  }
  
  static int quantizedBlue(int paramInt) {
    return paramInt & 0x1F;
  }
  
  static int quantizedGreen(int paramInt) {
    return paramInt >> 5 & 0x1F;
  }
  
  static int quantizedRed(int paramInt) {
    return paramInt >> 10 & 0x1F;
  }
  
  private boolean shouldIgnoreColor(int paramInt) {
    paramInt = approximateToRgb888(paramInt);
    ColorUtils.colorToHSL(paramInt, this.mTempHsl);
    return shouldIgnoreColor(paramInt, this.mTempHsl);
  }
  
  private boolean shouldIgnoreColor(int paramInt, float[] paramArrayOffloat) {
    Palette.Filter[] arrayOfFilter = this.mFilters;
    if (arrayOfFilter != null && arrayOfFilter.length > 0) {
      int i = arrayOfFilter.length;
      for (byte b = 0; b < i; b++) {
        if (!this.mFilters[b].isAllowed(paramInt, paramArrayOffloat))
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean shouldIgnoreColor(Palette.Swatch paramSwatch) {
    return shouldIgnoreColor(paramSwatch.getRgb(), paramSwatch.getHsl());
  }
  
  private void splitBoxes(PriorityQueue<Vbox> paramPriorityQueue, int paramInt) {
    while (paramPriorityQueue.size() < paramInt) {
      Vbox vbox = paramPriorityQueue.poll();
      if (vbox != null && vbox.canSplit()) {
        paramPriorityQueue.offer(vbox.splitBox());
        paramPriorityQueue.offer(vbox);
      } 
    } 
  }
  
  List<Palette.Swatch> getQuantizedColors() {
    return this.mQuantizedColors;
  }
  
  private class Vbox {
    private int mLowerIndex;
    
    private int mMaxBlue;
    
    private int mMaxGreen;
    
    private int mMaxRed;
    
    private int mMinBlue;
    
    private int mMinGreen;
    
    private int mMinRed;
    
    private int mPopulation;
    
    private int mUpperIndex;
    
    Vbox(int param1Int1, int param1Int2) {
      this.mLowerIndex = param1Int1;
      this.mUpperIndex = param1Int2;
      fitBox();
    }
    
    final boolean canSplit() {
      int i = getColorCount();
      boolean bool = true;
      if (i <= 1)
        bool = false; 
      return bool;
    }
    
    final int findSplitPoint() {
      int i = getLongestColorDimension();
      ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
      int[] arrayOfInt2 = colorCutQuantizer.mColors;
      int[] arrayOfInt1 = colorCutQuantizer.mHistogram;
      ColorCutQuantizer.modifySignificantOctet(arrayOfInt2, i, this.mLowerIndex, this.mUpperIndex);
      Arrays.sort(arrayOfInt2, this.mLowerIndex, this.mUpperIndex + 1);
      ColorCutQuantizer.modifySignificantOctet(arrayOfInt2, i, this.mLowerIndex, this.mUpperIndex);
      int j = this.mPopulation / 2;
      int k = this.mLowerIndex;
      i = 0;
      while (true) {
        int m = this.mUpperIndex;
        if (k <= m) {
          i += arrayOfInt1[arrayOfInt2[k]];
          if (i >= j)
            return Math.min(m - 1, k); 
          k++;
          continue;
        } 
        return this.mLowerIndex;
      } 
    }
    
    final void fitBox() {
      ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
      int[] arrayOfInt2 = colorCutQuantizer.mColors;
      int[] arrayOfInt1 = colorCutQuantizer.mHistogram;
      int i = this.mLowerIndex;
      int j = Integer.MAX_VALUE;
      int k = Integer.MAX_VALUE;
      int m = Integer.MAX_VALUE;
      int n = Integer.MIN_VALUE;
      int i1 = Integer.MIN_VALUE;
      int i2 = Integer.MIN_VALUE;
      int i3;
      for (i3 = 0; i <= this.mUpperIndex; i3 = i5) {
        int i4 = arrayOfInt2[i];
        int i5 = i3 + arrayOfInt1[i4];
        int i6 = ColorCutQuantizer.quantizedRed(i4);
        int i7 = ColorCutQuantizer.quantizedGreen(i4);
        i4 = ColorCutQuantizer.quantizedBlue(i4);
        i3 = n;
        if (i6 > n)
          i3 = i6; 
        n = j;
        if (i6 < j)
          n = i6; 
        i6 = i1;
        if (i7 > i1)
          i6 = i7; 
        i1 = k;
        if (i7 < k)
          i1 = i7; 
        i7 = i2;
        if (i4 > i2)
          i7 = i4; 
        i2 = m;
        if (i4 < m)
          i2 = i4; 
        i++;
        j = n;
        k = i1;
        m = i2;
        n = i3;
        i1 = i6;
        i2 = i7;
      } 
      this.mMinRed = j;
      this.mMaxRed = n;
      this.mMinGreen = k;
      this.mMaxGreen = i1;
      this.mMinBlue = m;
      this.mMaxBlue = i2;
      this.mPopulation = i3;
    }
    
    final Palette.Swatch getAverageColor() {
      ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
      int[] arrayOfInt2 = colorCutQuantizer.mColors;
      int[] arrayOfInt1 = colorCutQuantizer.mHistogram;
      int i = this.mLowerIndex;
      int j = 0;
      int k = 0;
      int m = 0;
      int n = 0;
      while (i <= this.mUpperIndex) {
        int i1 = arrayOfInt2[i];
        int i2 = arrayOfInt1[i1];
        k += i2;
        j += ColorCutQuantizer.quantizedRed(i1) * i2;
        m += ColorCutQuantizer.quantizedGreen(i1) * i2;
        n += i2 * ColorCutQuantizer.quantizedBlue(i1);
        i++;
      } 
      float f1 = j;
      float f2 = k;
      return new Palette.Swatch(ColorCutQuantizer.approximateToRgb888(Math.round(f1 / f2), Math.round(m / f2), Math.round(n / f2)), k);
    }
    
    final int getColorCount() {
      return this.mUpperIndex + 1 - this.mLowerIndex;
    }
    
    final int getLongestColorDimension() {
      int i = this.mMaxRed - this.mMinRed;
      int j = this.mMaxGreen - this.mMinGreen;
      int k = this.mMaxBlue - this.mMinBlue;
      return (i >= j && i >= k) ? -3 : ((j >= i && j >= k) ? -2 : -1);
    }
    
    final int getVolume() {
      return (this.mMaxRed - this.mMinRed + 1) * (this.mMaxGreen - this.mMinGreen + 1) * (this.mMaxBlue - this.mMinBlue + 1);
    }
    
    final Vbox splitBox() {
      if (canSplit()) {
        int i = findSplitPoint();
        Vbox vbox = new Vbox(i + 1, this.mUpperIndex);
        this.mUpperIndex = i;
        fitBox();
        return vbox;
      } 
      throw new IllegalStateException("Can not split a box with only 1 color");
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/graphics/ColorCutQuantizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */