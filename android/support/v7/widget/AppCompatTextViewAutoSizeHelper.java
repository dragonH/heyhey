package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

class AppCompatTextViewAutoSizeHelper {
  private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
  
  private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
  
  private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
  
  private static final String TAG = "ACTVAutoSizeHelper";
  
  private static final RectF TEMP_RECTF = new RectF();
  
  static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0F;
  
  private static final int VERY_WIDE = 1048576;
  
  private static Hashtable<String, Method> sTextViewMethodByNameCache = new Hashtable<String, Method>();
  
  private float mAutoSizeMaxTextSizeInPx = -1.0F;
  
  private float mAutoSizeMinTextSizeInPx = -1.0F;
  
  private float mAutoSizeStepGranularityInPx = -1.0F;
  
  private int[] mAutoSizeTextSizesInPx = new int[0];
  
  private int mAutoSizeTextType = 0;
  
  private final Context mContext;
  
  private boolean mHasPresetAutoSizeValues = false;
  
  private boolean mNeedsAutoSizeText = false;
  
  private TextPaint mTempTextPaint;
  
  private final TextView mTextView;
  
  AppCompatTextViewAutoSizeHelper(TextView paramTextView) {
    this.mTextView = paramTextView;
    this.mContext = paramTextView.getContext();
  }
  
  private int[] cleanupAutoSizePresetSizes(int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    if (i == 0)
      return paramArrayOfint; 
    Arrays.sort(paramArrayOfint);
    ArrayList<? extends Comparable<? super Integer>> arrayList = new ArrayList();
    boolean bool = false;
    byte b;
    for (b = 0; b < i; b++) {
      int j = paramArrayOfint[b];
      if (j > 0 && Collections.binarySearch(arrayList, Integer.valueOf(j)) < 0)
        arrayList.add(Integer.valueOf(j)); 
    } 
    if (i == arrayList.size())
      return paramArrayOfint; 
    i = arrayList.size();
    paramArrayOfint = new int[i];
    for (b = bool; b < i; b++)
      paramArrayOfint[b] = ((Integer)arrayList.get(b)).intValue(); 
    return paramArrayOfint;
  }
  
  private void clearAutoSizeConfiguration() {
    this.mAutoSizeTextType = 0;
    this.mAutoSizeMinTextSizeInPx = -1.0F;
    this.mAutoSizeMaxTextSizeInPx = -1.0F;
    this.mAutoSizeStepGranularityInPx = -1.0F;
    this.mAutoSizeTextSizesInPx = new int[0];
    this.mNeedsAutoSizeText = false;
  }
  
  @TargetApi(23)
  private StaticLayout createStaticLayoutForMeasuring(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt1, int paramInt2) {
    TextDirectionHeuristic textDirectionHeuristic = invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
    StaticLayout.Builder builder = StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), this.mTempTextPaint, paramInt1).setAlignment(paramAlignment).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency());
    paramInt1 = paramInt2;
    if (paramInt2 == -1)
      paramInt1 = Integer.MAX_VALUE; 
    return builder.setMaxLines(paramInt1).setTextDirection(textDirectionHeuristic).build();
  }
  
  @TargetApi(14)
  private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt) {
    float f1 = this.mTextView.getLineSpacingMultiplier();
    float f2 = this.mTextView.getLineSpacingExtra();
    boolean bool = this.mTextView.getIncludeFontPadding();
    return new StaticLayout(paramCharSequence, this.mTempTextPaint, paramInt, paramAlignment, f1, f2, bool);
  }
  
  private int findLargestTextSizeWhichFits(RectF paramRectF) {
    int i = this.mAutoSizeTextSizesInPx.length;
    if (i != 0) {
      int j = i - 1;
      i = 1;
      int k = 0;
      while (i <= j) {
        int m = (i + j) / 2;
        if (suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[m], paramRectF)) {
          k = i;
          i = m + 1;
          continue;
        } 
        k = m - 1;
        j = k;
      } 
      return this.mAutoSizeTextSizesInPx[k];
    } 
    IllegalStateException illegalStateException = new IllegalStateException("No available text sizes to choose from.");
    throw illegalStateException;
  }
  
  @Nullable
  private Method getTextViewMethod(@NonNull String paramString) {
    try {
      Method method1 = sTextViewMethodByNameCache.get(paramString);
      Method method2 = method1;
      if (method1 == null) {
        method1 = TextView.class.getDeclaredMethod(paramString, new Class[0]);
        method2 = method1;
        if (method1 != null) {
          method1.setAccessible(true);
          sTextViewMethodByNameCache.put(paramString, method1);
          method2 = method1;
        } 
      } 
      return method2;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to retrieve TextView#");
      stringBuilder.append(paramString);
      stringBuilder.append("() method");
      Log.w("ACTVAutoSizeHelper", stringBuilder.toString(), exception);
      return null;
    } 
  }
  
  private <T> T invokeAndReturnWithDefault(@NonNull Object paramObject, @NonNull String paramString, @NonNull T paramT) {
    try {
      paramObject = getTextViewMethod(paramString).invoke(paramObject, new Object[0]);
      paramT = (T)paramObject;
    } catch (Exception exception) {
      paramObject = new StringBuilder();
      super();
      paramObject.append("Failed to invoke TextView#");
      paramObject.append(paramString);
      paramObject.append("() method");
      Log.w("ACTVAutoSizeHelper", paramObject.toString(), exception);
    } finally {}
    return paramT;
  }
  
  private void setRawTextSize(float paramFloat) {
    if (paramFloat != this.mTextView.getPaint().getTextSize()) {
      boolean bool;
      this.mTextView.getPaint().setTextSize(paramFloat);
      if (Build.VERSION.SDK_INT >= 18) {
        bool = this.mTextView.isInLayout();
      } else {
        bool = false;
      } 
      if (this.mTextView.getLayout() != null) {
        this.mNeedsAutoSizeText = false;
        try {
          Method method = getTextViewMethod("nullLayouts");
          if (method != null)
            method.invoke(this.mTextView, new Object[0]); 
        } catch (Exception exception) {
          Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", exception);
        } 
        if (!bool) {
          this.mTextView.requestLayout();
        } else {
          this.mTextView.forceLayout();
        } 
        this.mTextView.invalidate();
      } 
    } 
  }
  
  private boolean setupAutoSizeText() {
    boolean bool = supportsAutoSizeText();
    byte b = 0;
    if (bool && this.mAutoSizeTextType == 1) {
      if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
        float f = Math.round(this.mAutoSizeMinTextSizeInPx);
        byte b1 = 1;
        while (Math.round(this.mAutoSizeStepGranularityInPx + f) <= Math.round(this.mAutoSizeMaxTextSizeInPx)) {
          b1++;
          f += this.mAutoSizeStepGranularityInPx;
        } 
        int[] arrayOfInt = new int[b1];
        f = this.mAutoSizeMinTextSizeInPx;
        while (b < b1) {
          arrayOfInt[b] = Math.round(f);
          f += this.mAutoSizeStepGranularityInPx;
          b++;
        } 
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      } 
      this.mNeedsAutoSizeText = true;
    } else {
      this.mNeedsAutoSizeText = false;
    } 
    return this.mNeedsAutoSizeText;
  }
  
  private void setupAutoSizeUniformPresetSizes(TypedArray paramTypedArray) {
    int i = paramTypedArray.length();
    int[] arrayOfInt = new int[i];
    if (i > 0) {
      for (byte b = 0; b < i; b++)
        arrayOfInt[b] = paramTypedArray.getDimensionPixelSize(b, -1); 
      this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      setupAutoSizeUniformPresetSizesConfiguration();
    } 
  }
  
  private boolean setupAutoSizeUniformPresetSizesConfiguration() {
    boolean bool;
    int[] arrayOfInt = this.mAutoSizeTextSizesInPx;
    int i = arrayOfInt.length;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mHasPresetAutoSizeValues = bool;
    if (bool) {
      this.mAutoSizeTextType = 1;
      this.mAutoSizeMinTextSizeInPx = arrayOfInt[0];
      this.mAutoSizeMaxTextSizeInPx = arrayOfInt[i - 1];
      this.mAutoSizeStepGranularityInPx = -1.0F;
    } 
    return bool;
  }
  
  private boolean suggestedSizeFitsInSpace(int paramInt, RectF paramRectF) {
    StaticLayout staticLayout;
    CharSequence charSequence = this.mTextView.getText();
    int i = Build.VERSION.SDK_INT;
    int j = this.mTextView.getMaxLines();
    TextPaint textPaint = this.mTempTextPaint;
    if (textPaint == null) {
      this.mTempTextPaint = new TextPaint();
    } else {
      textPaint.reset();
    } 
    this.mTempTextPaint.set(this.mTextView.getPaint());
    this.mTempTextPaint.setTextSize(paramInt);
    Layout.Alignment alignment = invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
    if (i >= 23) {
      staticLayout = createStaticLayoutForMeasuring(charSequence, alignment, Math.round(paramRectF.right), j);
    } else {
      staticLayout = createStaticLayoutForMeasuringPre23(charSequence, (Layout.Alignment)staticLayout, Math.round(paramRectF.right));
    } 
    return (j != -1 && (staticLayout.getLineCount() > j || staticLayout.getLineEnd(staticLayout.getLineCount() - 1) != charSequence.length())) ? false : (!(staticLayout.getHeight() > paramRectF.bottom));
  }
  
  private boolean supportsAutoSizeText() {
    return this.mTextView instanceof AppCompatEditText ^ true;
  }
  
  private void validateAndSetAutoSizeTextTypeUniformConfiguration(float paramFloat1, float paramFloat2, float paramFloat3) throws IllegalArgumentException {
    if (paramFloat1 > 0.0F) {
      if (paramFloat2 > paramFloat1) {
        if (paramFloat3 > 0.0F) {
          this.mAutoSizeTextType = 1;
          this.mAutoSizeMinTextSizeInPx = paramFloat1;
          this.mAutoSizeMaxTextSizeInPx = paramFloat2;
          this.mAutoSizeStepGranularityInPx = paramFloat3;
          this.mHasPresetAutoSizeValues = false;
          return;
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("The auto-size step granularity (");
        stringBuilder2.append(paramFloat3);
        stringBuilder2.append("px) is less or equal to (0px)");
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Maximum auto-size text size (");
      stringBuilder1.append(paramFloat2);
      stringBuilder1.append("px) is less or equal to minimum auto-size ");
      stringBuilder1.append("text size (");
      stringBuilder1.append(paramFloat1);
      stringBuilder1.append("px)");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Minimum auto-size text size (");
    stringBuilder.append(paramFloat1);
    stringBuilder.append("px) is less or equal to (0px)");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void autoSizeText() {
    if (!isAutoSizeEnabled())
      return; 
    if (this.mNeedsAutoSizeText) {
      int i;
      if (this.mTextView.getMeasuredHeight() <= 0 || this.mTextView.getMeasuredWidth() <= 0)
        return; 
      if (((Boolean)invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue()) {
        i = 1048576;
      } else {
        i = this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
      } 
      int j = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
      if (i <= 0 || j <= 0)
        return; 
      synchronized (TEMP_RECTF) {
        null.setEmpty();
        null.right = i;
        null.bottom = j;
        float f = findLargestTextSizeWhichFits(null);
        if (f != this.mTextView.getTextSize())
          setTextSizeInternal(0, f); 
      } 
    } 
    this.mNeedsAutoSizeText = true;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeMaxTextSize() {
    return Math.round(this.mAutoSizeMaxTextSizeInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeMinTextSize() {
    return Math.round(this.mAutoSizeMinTextSizeInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeStepGranularity() {
    return Math.round(this.mAutoSizeStepGranularityInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int[] getAutoSizeTextAvailableSizes() {
    return this.mAutoSizeTextSizesInPx;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeTextType() {
    return this.mAutoSizeTextType;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isAutoSizeEnabled() {
    boolean bool;
    if (supportsAutoSizeText() && this.mAutoSizeTextType != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    float f1;
    float f2;
    float f3;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    paramInt = R.styleable.AppCompatTextView_autoSizeTextType;
    if (typedArray.hasValue(paramInt))
      this.mAutoSizeTextType = typedArray.getInt(paramInt, 0); 
    paramInt = R.styleable.AppCompatTextView_autoSizeStepGranularity;
    if (typedArray.hasValue(paramInt)) {
      f1 = typedArray.getDimension(paramInt, -1.0F);
    } else {
      f1 = -1.0F;
    } 
    paramInt = R.styleable.AppCompatTextView_autoSizeMinTextSize;
    if (typedArray.hasValue(paramInt)) {
      f2 = typedArray.getDimension(paramInt, -1.0F);
    } else {
      f2 = -1.0F;
    } 
    paramInt = R.styleable.AppCompatTextView_autoSizeMaxTextSize;
    if (typedArray.hasValue(paramInt)) {
      f3 = typedArray.getDimension(paramInt, -1.0F);
    } else {
      f3 = -1.0F;
    } 
    paramInt = R.styleable.AppCompatTextView_autoSizePresetSizes;
    if (typedArray.hasValue(paramInt)) {
      paramInt = typedArray.getResourceId(paramInt, 0);
      if (paramInt > 0) {
        TypedArray typedArray1 = typedArray.getResources().obtainTypedArray(paramInt);
        setupAutoSizeUniformPresetSizes(typedArray1);
        typedArray1.recycle();
      } 
    } 
    typedArray.recycle();
    if (supportsAutoSizeText()) {
      if (this.mAutoSizeTextType == 1) {
        if (!this.mHasPresetAutoSizeValues) {
          DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
          float f = f2;
          if (f2 == -1.0F)
            f = TypedValue.applyDimension(2, 12.0F, displayMetrics); 
          f2 = f3;
          if (f3 == -1.0F)
            f2 = TypedValue.applyDimension(2, 112.0F, displayMetrics); 
          f3 = f1;
          if (f1 == -1.0F)
            f3 = 1.0F; 
          validateAndSetAutoSizeTextTypeUniformConfiguration(f, f2, f3);
        } 
        setupAutoSizeText();
      } 
    } else {
      this.mAutoSizeTextType = 0;
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    if (supportsAutoSizeText()) {
      DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
      validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(paramInt4, paramInt1, displayMetrics), TypedValue.applyDimension(paramInt4, paramInt2, displayMetrics), TypedValue.applyDimension(paramInt4, paramInt3, displayMetrics));
      if (setupAutoSizeText())
        autoSizeText(); 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    if (supportsAutoSizeText()) {
      int i = paramArrayOfint.length;
      byte b = 0;
      if (i > 0) {
        int[] arrayOfInt2;
        int[] arrayOfInt1 = new int[i];
        if (paramInt == 0) {
          arrayOfInt2 = Arrays.copyOf(paramArrayOfint, i);
        } else {
          DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
          while (true) {
            arrayOfInt2 = arrayOfInt1;
            if (b < i) {
              arrayOfInt1[b] = Math.round(TypedValue.applyDimension(paramInt, paramArrayOfint[b], displayMetrics));
              b++;
              continue;
            } 
            break;
          } 
        } 
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt2);
        if (!setupAutoSizeUniformPresetSizesConfiguration()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("None of the preset sizes is valid: ");
          stringBuilder.append(Arrays.toString(paramArrayOfint));
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        this.mHasPresetAutoSizeValues = false;
      } 
      if (setupAutoSizeText())
        autoSizeText(); 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeWithDefaults(int paramInt) {
    if (supportsAutoSizeText())
      if (paramInt != 0) {
        if (paramInt == 1) {
          DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
          validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0F, displayMetrics), TypedValue.applyDimension(2, 112.0F, displayMetrics), 1.0F);
          if (setupAutoSizeText())
            autoSizeText(); 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown auto-size text type: ");
          stringBuilder.append(paramInt);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        clearAutoSizeConfiguration();
      }  
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setTextSizeInternal(int paramInt, float paramFloat) {
    Resources resources;
    Context context = this.mContext;
    if (context == null) {
      resources = Resources.getSystem();
    } else {
      resources = resources.getResources();
    } 
    setRawTextSize(TypedValue.applyDimension(paramInt, paramFloat, resources.getDisplayMetrics()));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AppCompatTextViewAutoSizeHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */