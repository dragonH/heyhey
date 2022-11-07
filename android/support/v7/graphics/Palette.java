package android.support.v7.graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Palette {
  static final int DEFAULT_CALCULATE_NUMBER_COLORS = 16;
  
  static final Filter DEFAULT_FILTER = new Filter() {
      private static final float BLACK_MAX_LIGHTNESS = 0.05F;
      
      private static final float WHITE_MIN_LIGHTNESS = 0.95F;
      
      private boolean isBlack(float[] param1ArrayOffloat) {
        boolean bool;
        if (param1ArrayOffloat[2] <= 0.05F) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      private boolean isNearRedILine(float[] param1ArrayOffloat) {
        boolean bool1 = false;
        float f = param1ArrayOffloat[0];
        boolean bool2 = bool1;
        if (f >= 10.0F) {
          bool2 = bool1;
          if (f <= 37.0F) {
            bool2 = bool1;
            if (param1ArrayOffloat[1] <= 0.82F)
              bool2 = true; 
          } 
        } 
        return bool2;
      }
      
      private boolean isWhite(float[] param1ArrayOffloat) {
        boolean bool;
        if (param1ArrayOffloat[2] >= 0.95F) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean isAllowed(int param1Int, float[] param1ArrayOffloat) {
        boolean bool;
        if (!isWhite(param1ArrayOffloat) && !isBlack(param1ArrayOffloat) && !isNearRedILine(param1ArrayOffloat)) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
    };
  
  static final int DEFAULT_RESIZE_BITMAP_AREA = 12544;
  
  static final String LOG_TAG = "Palette";
  
  static final boolean LOG_TIMINGS = false;
  
  static final float MIN_CONTRAST_BODY_TEXT = 4.5F;
  
  static final float MIN_CONTRAST_TITLE_TEXT = 3.0F;
  
  private final Swatch mDominantSwatch;
  
  private final Map<Target, Swatch> mSelectedSwatches;
  
  private final List<Swatch> mSwatches;
  
  private final List<Target> mTargets;
  
  private final SparseBooleanArray mUsedColors;
  
  Palette(List<Swatch> paramList, List<Target> paramList1) {
    this.mSwatches = paramList;
    this.mTargets = paramList1;
    this.mUsedColors = new SparseBooleanArray();
    this.mSelectedSwatches = (Map<Target, Swatch>)new ArrayMap();
    this.mDominantSwatch = findDominantSwatch();
  }
  
  private static float[] copyHslValues(Swatch paramSwatch) {
    float[] arrayOfFloat = new float[3];
    System.arraycopy(paramSwatch.getHsl(), 0, arrayOfFloat, 0, 3);
    return arrayOfFloat;
  }
  
  private Swatch findDominantSwatch() {
    int i = this.mSwatches.size();
    int j = Integer.MIN_VALUE;
    Swatch swatch = null;
    byte b = 0;
    while (b < i) {
      Swatch swatch1 = this.mSwatches.get(b);
      int k = j;
      if (swatch1.getPopulation() > j) {
        k = swatch1.getPopulation();
        swatch = swatch1;
      } 
      b++;
      j = k;
    } 
    return swatch;
  }
  
  public static Builder from(Bitmap paramBitmap) {
    return new Builder(paramBitmap);
  }
  
  public static Palette from(List<Swatch> paramList) {
    return (new Builder(paramList)).generate();
  }
  
  @Deprecated
  public static Palette generate(Bitmap paramBitmap) {
    return from(paramBitmap).generate();
  }
  
  @Deprecated
  public static Palette generate(Bitmap paramBitmap, int paramInt) {
    return from(paramBitmap).maximumColorCount(paramInt).generate();
  }
  
  @Deprecated
  public static AsyncTask<Bitmap, Void, Palette> generateAsync(Bitmap paramBitmap, int paramInt, PaletteAsyncListener paramPaletteAsyncListener) {
    return from(paramBitmap).maximumColorCount(paramInt).generate(paramPaletteAsyncListener);
  }
  
  @Deprecated
  public static AsyncTask<Bitmap, Void, Palette> generateAsync(Bitmap paramBitmap, PaletteAsyncListener paramPaletteAsyncListener) {
    return from(paramBitmap).generate(paramPaletteAsyncListener);
  }
  
  private float generateScore(Swatch paramSwatch, Target paramTarget) {
    boolean bool;
    float f3;
    float[] arrayOfFloat = paramSwatch.getHsl();
    Swatch swatch = this.mDominantSwatch;
    if (swatch != null) {
      bool = swatch.getPopulation();
    } else {
      bool = true;
    } 
    float f1 = paramTarget.getSaturationWeight();
    float f2 = 0.0F;
    if (f1 > 0.0F) {
      f1 = paramTarget.getSaturationWeight() * (1.0F - Math.abs(arrayOfFloat[1] - paramTarget.getTargetSaturation()));
    } else {
      f1 = 0.0F;
    } 
    if (paramTarget.getLightnessWeight() > 0.0F) {
      f3 = paramTarget.getLightnessWeight() * (1.0F - Math.abs(arrayOfFloat[2] - paramTarget.getTargetLightness()));
    } else {
      f3 = 0.0F;
    } 
    if (paramTarget.getPopulationWeight() > 0.0F)
      f2 = paramTarget.getPopulationWeight() * paramSwatch.getPopulation() / bool; 
    return f1 + f3 + f2;
  }
  
  private Swatch generateScoredTarget(Target paramTarget) {
    Swatch swatch = getMaxScoredSwatchForTarget(paramTarget);
    if (swatch != null && paramTarget.isExclusive())
      this.mUsedColors.append(swatch.getRgb(), true); 
    return swatch;
  }
  
  private Swatch getMaxScoredSwatchForTarget(Target paramTarget) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSwatches : Ljava/util/List;
    //   4: invokeinterface size : ()I
    //   9: istore_2
    //   10: fconst_0
    //   11: fstore_3
    //   12: aconst_null
    //   13: astore #4
    //   15: iconst_0
    //   16: istore #5
    //   18: iload #5
    //   20: iload_2
    //   21: if_icmpge -> 106
    //   24: aload_0
    //   25: getfield mSwatches : Ljava/util/List;
    //   28: iload #5
    //   30: invokeinterface get : (I)Ljava/lang/Object;
    //   35: checkcast android/support/v7/graphics/Palette$Swatch
    //   38: astore #6
    //   40: fload_3
    //   41: fstore #7
    //   43: aload #4
    //   45: astore #8
    //   47: aload_0
    //   48: aload #6
    //   50: aload_1
    //   51: invokespecial shouldBeScoredForTarget : (Landroid/support/v7/graphics/Palette$Swatch;Landroid/support/v7/graphics/Target;)Z
    //   54: ifeq -> 93
    //   57: aload_0
    //   58: aload #6
    //   60: aload_1
    //   61: invokespecial generateScore : (Landroid/support/v7/graphics/Palette$Swatch;Landroid/support/v7/graphics/Target;)F
    //   64: fstore #9
    //   66: aload #4
    //   68: ifnull -> 85
    //   71: fload_3
    //   72: fstore #7
    //   74: aload #4
    //   76: astore #8
    //   78: fload #9
    //   80: fload_3
    //   81: fcmpl
    //   82: ifle -> 93
    //   85: aload #6
    //   87: astore #8
    //   89: fload #9
    //   91: fstore #7
    //   93: iinc #5, 1
    //   96: fload #7
    //   98: fstore_3
    //   99: aload #8
    //   101: astore #4
    //   103: goto -> 18
    //   106: aload #4
    //   108: areturn
  }
  
  private boolean shouldBeScoredForTarget(Swatch paramSwatch, Target paramTarget) {
    float[] arrayOfFloat = paramSwatch.getHsl();
    boolean bool = true;
    if (arrayOfFloat[1] < paramTarget.getMinimumSaturation() || arrayOfFloat[1] > paramTarget.getMaximumSaturation() || arrayOfFloat[2] < paramTarget.getMinimumLightness() || arrayOfFloat[2] > paramTarget.getMaximumLightness() || this.mUsedColors.get(paramSwatch.getRgb()))
      bool = false; 
    return bool;
  }
  
  void generate() {
    int i = this.mTargets.size();
    for (byte b = 0; b < i; b++) {
      Target target = this.mTargets.get(b);
      target.normalizeWeights();
      this.mSelectedSwatches.put(target, generateScoredTarget(target));
    } 
    this.mUsedColors.clear();
  }
  
  @ColorInt
  public int getColorForTarget(@NonNull Target paramTarget, @ColorInt int paramInt) {
    Swatch swatch = getSwatchForTarget(paramTarget);
    if (swatch != null)
      paramInt = swatch.getRgb(); 
    return paramInt;
  }
  
  @ColorInt
  public int getDarkMutedColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.DARK_MUTED, paramInt);
  }
  
  @Nullable
  public Swatch getDarkMutedSwatch() {
    return getSwatchForTarget(Target.DARK_MUTED);
  }
  
  @ColorInt
  public int getDarkVibrantColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.DARK_VIBRANT, paramInt);
  }
  
  @Nullable
  public Swatch getDarkVibrantSwatch() {
    return getSwatchForTarget(Target.DARK_VIBRANT);
  }
  
  @ColorInt
  public int getDominantColor(@ColorInt int paramInt) {
    Swatch swatch = this.mDominantSwatch;
    if (swatch != null)
      paramInt = swatch.getRgb(); 
    return paramInt;
  }
  
  @Nullable
  public Swatch getDominantSwatch() {
    return this.mDominantSwatch;
  }
  
  @ColorInt
  public int getLightMutedColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.LIGHT_MUTED, paramInt);
  }
  
  @Nullable
  public Swatch getLightMutedSwatch() {
    return getSwatchForTarget(Target.LIGHT_MUTED);
  }
  
  @ColorInt
  public int getLightVibrantColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.LIGHT_VIBRANT, paramInt);
  }
  
  @Nullable
  public Swatch getLightVibrantSwatch() {
    return getSwatchForTarget(Target.LIGHT_VIBRANT);
  }
  
  @ColorInt
  public int getMutedColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.MUTED, paramInt);
  }
  
  @Nullable
  public Swatch getMutedSwatch() {
    return getSwatchForTarget(Target.MUTED);
  }
  
  @Nullable
  public Swatch getSwatchForTarget(@NonNull Target paramTarget) {
    return this.mSelectedSwatches.get(paramTarget);
  }
  
  @NonNull
  public List<Swatch> getSwatches() {
    return Collections.unmodifiableList(this.mSwatches);
  }
  
  @NonNull
  public List<Target> getTargets() {
    return Collections.unmodifiableList(this.mTargets);
  }
  
  @ColorInt
  public int getVibrantColor(@ColorInt int paramInt) {
    return getColorForTarget(Target.VIBRANT, paramInt);
  }
  
  @Nullable
  public Swatch getVibrantSwatch() {
    return getSwatchForTarget(Target.VIBRANT);
  }
  
  public static final class Builder {
    private final Bitmap mBitmap;
    
    private final List<Palette.Filter> mFilters;
    
    private int mMaxColors;
    
    private Rect mRegion;
    
    private int mResizeArea;
    
    private int mResizeMaxDimension;
    
    private final List<Palette.Swatch> mSwatches;
    
    private final List<Target> mTargets;
    
    public Builder(Bitmap param1Bitmap) {
      ArrayList<Target> arrayList = new ArrayList();
      this.mTargets = arrayList;
      this.mMaxColors = 16;
      this.mResizeArea = 12544;
      this.mResizeMaxDimension = -1;
      ArrayList<Palette.Filter> arrayList1 = new ArrayList();
      this.mFilters = arrayList1;
      if (param1Bitmap != null && !param1Bitmap.isRecycled()) {
        arrayList1.add(Palette.DEFAULT_FILTER);
        this.mBitmap = param1Bitmap;
        this.mSwatches = null;
        arrayList.add(Target.LIGHT_VIBRANT);
        arrayList.add(Target.VIBRANT);
        arrayList.add(Target.DARK_VIBRANT);
        arrayList.add(Target.LIGHT_MUTED);
        arrayList.add(Target.MUTED);
        arrayList.add(Target.DARK_MUTED);
        return;
      } 
      throw new IllegalArgumentException("Bitmap is not valid");
    }
    
    public Builder(List<Palette.Swatch> param1List) {
      this.mTargets = new ArrayList<Target>();
      this.mMaxColors = 16;
      this.mResizeArea = 12544;
      this.mResizeMaxDimension = -1;
      ArrayList<Palette.Filter> arrayList = new ArrayList();
      this.mFilters = arrayList;
      if (param1List != null && !param1List.isEmpty()) {
        arrayList.add(Palette.DEFAULT_FILTER);
        this.mSwatches = param1List;
        this.mBitmap = null;
        return;
      } 
      throw new IllegalArgumentException("List of Swatches is not valid");
    }
    
    private int[] getPixelsFromBitmap(Bitmap param1Bitmap) {
      int i = param1Bitmap.getWidth();
      int j = param1Bitmap.getHeight();
      int[] arrayOfInt1 = new int[i * j];
      param1Bitmap.getPixels(arrayOfInt1, 0, i, 0, 0, i, j);
      Rect rect = this.mRegion;
      if (rect == null)
        return arrayOfInt1; 
      int k = rect.width();
      int m = this.mRegion.height();
      int[] arrayOfInt2 = new int[k * m];
      for (j = 0; j < m; j++) {
        rect = this.mRegion;
        System.arraycopy(arrayOfInt1, (rect.top + j) * i + rect.left, arrayOfInt2, j * k, k);
      } 
      return arrayOfInt2;
    }
    
    private Bitmap scaleBitmapDown(Bitmap param1Bitmap) {
      double d2;
      int i = this.mResizeArea;
      double d1 = -1.0D;
      if (i > 0) {
        i = param1Bitmap.getWidth() * param1Bitmap.getHeight();
        int j = this.mResizeArea;
        d2 = d1;
        if (i > j) {
          d1 = j;
          d2 = i;
          Double.isNaN(d1);
          Double.isNaN(d2);
          d2 = Math.sqrt(d1 / d2);
        } 
      } else {
        d2 = d1;
        if (this.mResizeMaxDimension > 0) {
          i = Math.max(param1Bitmap.getWidth(), param1Bitmap.getHeight());
          int j = this.mResizeMaxDimension;
          d2 = d1;
          if (i > j) {
            d1 = j;
            d2 = i;
            Double.isNaN(d1);
            Double.isNaN(d2);
            d2 = d1 / d2;
          } 
        } 
      } 
      if (d2 <= 0.0D)
        return param1Bitmap; 
      d1 = param1Bitmap.getWidth();
      Double.isNaN(d1);
      i = (int)Math.ceil(d1 * d2);
      d1 = param1Bitmap.getHeight();
      Double.isNaN(d1);
      return Bitmap.createScaledBitmap(param1Bitmap, i, (int)Math.ceil(d1 * d2), false);
    }
    
    @NonNull
    public Builder addFilter(Palette.Filter param1Filter) {
      if (param1Filter != null)
        this.mFilters.add(param1Filter); 
      return this;
    }
    
    @NonNull
    public Builder addTarget(@NonNull Target param1Target) {
      if (!this.mTargets.contains(param1Target))
        this.mTargets.add(param1Target); 
      return this;
    }
    
    @NonNull
    public Builder clearFilters() {
      this.mFilters.clear();
      return this;
    }
    
    @NonNull
    public Builder clearRegion() {
      this.mRegion = null;
      return this;
    }
    
    @NonNull
    public Builder clearTargets() {
      List<Target> list = this.mTargets;
      if (list != null)
        list.clear(); 
      return this;
    }
    
    @NonNull
    public AsyncTask<Bitmap, Void, Palette> generate(final Palette.PaletteAsyncListener listener) {
      if (listener != null)
        return (new AsyncTask<Bitmap, Void, Palette>() {
            protected Palette doInBackground(Bitmap... param2VarArgs) {
              try {
                return Palette.Builder.this.generate();
              } catch (Exception exception) {
                Log.e("Palette", "Exception thrown during async generate", exception);
                return null;
              } 
            }
            
            protected void onPostExecute(Palette param2Palette) {
              listener.onGenerated(param2Palette);
            }
          }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Bitmap[] { this.mBitmap }); 
      throw new IllegalArgumentException("listener can not be null");
    }
    
    @NonNull
    public Palette generate() {
      List<Palette.Swatch> list;
      Bitmap bitmap = this.mBitmap;
      if (bitmap != null) {
        Palette.Filter[] arrayOfFilter;
        Bitmap bitmap1 = scaleBitmapDown(bitmap);
        Rect rect = this.mRegion;
        if (bitmap1 != this.mBitmap && rect != null) {
          double d1 = bitmap1.getWidth();
          double d2 = this.mBitmap.getWidth();
          Double.isNaN(d1);
          Double.isNaN(d2);
          d1 /= d2;
          d2 = rect.left;
          Double.isNaN(d2);
          rect.left = (int)Math.floor(d2 * d1);
          d2 = rect.top;
          Double.isNaN(d2);
          rect.top = (int)Math.floor(d2 * d1);
          d2 = rect.right;
          Double.isNaN(d2);
          rect.right = Math.min((int)Math.ceil(d2 * d1), bitmap1.getWidth());
          d2 = rect.bottom;
          Double.isNaN(d2);
          rect.bottom = Math.min((int)Math.ceil(d2 * d1), bitmap1.getHeight());
        } 
        int[] arrayOfInt = getPixelsFromBitmap(bitmap1);
        int i = this.mMaxColors;
        if (this.mFilters.isEmpty()) {
          rect = null;
        } else {
          List<Palette.Filter> list1 = this.mFilters;
          arrayOfFilter = list1.<Palette.Filter>toArray(new Palette.Filter[list1.size()]);
        } 
        ColorCutQuantizer colorCutQuantizer = new ColorCutQuantizer(arrayOfInt, i, arrayOfFilter);
        if (bitmap1 != this.mBitmap)
          bitmap1.recycle(); 
        list = colorCutQuantizer.getQuantizedColors();
      } else {
        list = this.mSwatches;
      } 
      Palette palette = new Palette(list, this.mTargets);
      palette.generate();
      return palette;
    }
    
    @NonNull
    public Builder maximumColorCount(int param1Int) {
      this.mMaxColors = param1Int;
      return this;
    }
    
    @NonNull
    public Builder resizeBitmapArea(int param1Int) {
      this.mResizeArea = param1Int;
      this.mResizeMaxDimension = -1;
      return this;
    }
    
    @Deprecated
    @NonNull
    public Builder resizeBitmapSize(int param1Int) {
      this.mResizeMaxDimension = param1Int;
      this.mResizeArea = -1;
      return this;
    }
    
    @NonNull
    public Builder setRegion(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      if (this.mBitmap != null) {
        if (this.mRegion == null)
          this.mRegion = new Rect(); 
        this.mRegion.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
        if (!this.mRegion.intersect(param1Int1, param1Int2, param1Int3, param1Int4))
          throw new IllegalArgumentException("The given region must intersect with the Bitmap's dimensions."); 
      } 
      return this;
    }
  }
  
  class null extends AsyncTask<Bitmap, Void, Palette> {
    protected Palette doInBackground(Bitmap... param1VarArgs) {
      try {
        return this.this$0.generate();
      } catch (Exception exception) {
        Log.e("Palette", "Exception thrown during async generate", exception);
        return null;
      } 
    }
    
    protected void onPostExecute(Palette param1Palette) {
      listener.onGenerated(param1Palette);
    }
  }
  
  public static interface Filter {
    boolean isAllowed(int param1Int, float[] param1ArrayOffloat);
  }
  
  public static interface PaletteAsyncListener {
    void onGenerated(Palette param1Palette);
  }
  
  public static final class Swatch {
    private final int mBlue;
    
    private int mBodyTextColor;
    
    private boolean mGeneratedTextColors;
    
    private final int mGreen;
    
    private float[] mHsl;
    
    private final int mPopulation;
    
    private final int mRed;
    
    private final int mRgb;
    
    private int mTitleTextColor;
    
    public Swatch(@ColorInt int param1Int1, int param1Int2) {
      this.mRed = Color.red(param1Int1);
      this.mGreen = Color.green(param1Int1);
      this.mBlue = Color.blue(param1Int1);
      this.mRgb = param1Int1;
      this.mPopulation = param1Int2;
    }
    
    Swatch(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      this.mRed = param1Int1;
      this.mGreen = param1Int2;
      this.mBlue = param1Int3;
      this.mRgb = Color.rgb(param1Int1, param1Int2, param1Int3);
      this.mPopulation = param1Int4;
    }
    
    Swatch(float[] param1ArrayOffloat, int param1Int) {
      this(ColorUtils.HSLToColor(param1ArrayOffloat), param1Int);
      this.mHsl = param1ArrayOffloat;
    }
    
    private void ensureTextColorsGenerated() {
      if (!this.mGeneratedTextColors) {
        int i = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 4.5F);
        int j = ColorUtils.calculateMinimumAlpha(-1, this.mRgb, 3.0F);
        if (i != -1 && j != -1) {
          this.mBodyTextColor = ColorUtils.setAlphaComponent(-1, i);
          this.mTitleTextColor = ColorUtils.setAlphaComponent(-1, j);
          this.mGeneratedTextColors = true;
          return;
        } 
        int k = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 4.5F);
        int m = ColorUtils.calculateMinimumAlpha(-16777216, this.mRgb, 3.0F);
        if (k != -1 && m != -1) {
          this.mBodyTextColor = ColorUtils.setAlphaComponent(-16777216, k);
          this.mTitleTextColor = ColorUtils.setAlphaComponent(-16777216, m);
          this.mGeneratedTextColors = true;
          return;
        } 
        if (i != -1) {
          k = ColorUtils.setAlphaComponent(-1, i);
        } else {
          k = ColorUtils.setAlphaComponent(-16777216, k);
        } 
        this.mBodyTextColor = k;
        if (j != -1) {
          k = ColorUtils.setAlphaComponent(-1, j);
        } else {
          k = ColorUtils.setAlphaComponent(-16777216, m);
        } 
        this.mTitleTextColor = k;
        this.mGeneratedTextColors = true;
      } 
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || Swatch.class != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (this.mPopulation != ((Swatch)param1Object).mPopulation || this.mRgb != ((Swatch)param1Object).mRgb)
        bool = false; 
      return bool;
    }
    
    @ColorInt
    public int getBodyTextColor() {
      ensureTextColorsGenerated();
      return this.mBodyTextColor;
    }
    
    public float[] getHsl() {
      if (this.mHsl == null)
        this.mHsl = new float[3]; 
      ColorUtils.RGBToHSL(this.mRed, this.mGreen, this.mBlue, this.mHsl);
      return this.mHsl;
    }
    
    public int getPopulation() {
      return this.mPopulation;
    }
    
    @ColorInt
    public int getRgb() {
      return this.mRgb;
    }
    
    @ColorInt
    public int getTitleTextColor() {
      ensureTextColorsGenerated();
      return this.mTitleTextColor;
    }
    
    public int hashCode() {
      return this.mRgb * 31 + this.mPopulation;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(Swatch.class.getSimpleName());
      stringBuilder.append(" [RGB: #");
      stringBuilder.append(Integer.toHexString(getRgb()));
      stringBuilder.append(']');
      stringBuilder.append(" [HSL: ");
      stringBuilder.append(Arrays.toString(getHsl()));
      stringBuilder.append(']');
      stringBuilder.append(" [Population: ");
      stringBuilder.append(this.mPopulation);
      stringBuilder.append(']');
      stringBuilder.append(" [Title Text: #");
      stringBuilder.append(Integer.toHexString(getTitleTextColor()));
      stringBuilder.append(']');
      stringBuilder.append(" [Body Text: #");
      stringBuilder.append(Integer.toHexString(getBodyTextColor()));
      stringBuilder.append(']');
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/graphics/Palette.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */