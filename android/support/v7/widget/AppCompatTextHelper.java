package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(9)
class AppCompatTextHelper {
  @NonNull
  private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
  
  private TintInfo mDrawableBottomTint;
  
  private TintInfo mDrawableLeftTint;
  
  private TintInfo mDrawableRightTint;
  
  private TintInfo mDrawableTopTint;
  
  private Typeface mFontTypeface;
  
  private int mStyle = 0;
  
  final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView) {
    this.mView = paramTextView;
    this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(paramTextView);
  }
  
  static AppCompatTextHelper create(TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 17) ? new AppCompatTextHelperV17(paramTextView) : new AppCompatTextHelper(paramTextView);
  }
  
  protected static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt) {
    ColorStateList colorStateList = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (colorStateList != null) {
      TintInfo tintInfo = new TintInfo();
      tintInfo.mHasTintList = true;
      tintInfo.mTintList = colorStateList;
      return tintInfo;
    } 
    return null;
  }
  
  private void setTextSizeInternal(int paramInt, float paramFloat) {
    this.mAutoSizeTextHelper.setTextSizeInternal(paramInt, paramFloat);
  }
  
  private void updateTypefaceAndStyle(Context paramContext, TintTypedArray paramTintTypedArray) {
    this.mStyle = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
    int i = R.styleable.TextAppearance_android_fontFamily;
    if (paramTintTypedArray.hasValue(i) || paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
      this.mFontTypeface = null;
      if (!paramTintTypedArray.hasValue(i))
        i = R.styleable.TextAppearance_fontFamily; 
      if (!paramContext.isRestricted())
        try {
          this.mFontTypeface = paramTintTypedArray.getFont(i, this.mStyle, this.mView);
        } catch (UnsupportedOperationException|android.content.res.Resources.NotFoundException unsupportedOperationException) {} 
      if (this.mFontTypeface == null)
        this.mFontTypeface = Typeface.create(paramTintTypedArray.getString(i), this.mStyle); 
    } 
  }
  
  final void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo) {
    if (paramDrawable != null && paramTintInfo != null)
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, this.mView.getDrawableState()); 
  }
  
  void applyCompoundDrawablesTints() {
    if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], this.mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], this.mDrawableBottomTint);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void autoSizeText() {
    this.mAutoSizeTextHelper.autoSizeText();
  }
  
  int getAutoSizeMaxTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
  }
  
  int getAutoSizeMinTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
  }
  
  int getAutoSizeStepGranularity() {
    return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
  }
  
  int[] getAutoSizeTextAvailableSizes() {
    return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
  }
  
  int getAutoSizeTextType() {
    return this.mAutoSizeTextHelper.getAutoSizeTextType();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isAutoSizeEnabled() {
    return this.mAutoSizeTextHelper.isAutoSizeEnabled();
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mView : Landroid/widget/TextView;
    //   4: invokevirtual getContext : ()Landroid/content/Context;
    //   7: astore_3
    //   8: invokestatic get : ()Landroid/support/v7/widget/AppCompatDrawableManager;
    //   11: astore #4
    //   13: aload_3
    //   14: aload_1
    //   15: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper : [I
    //   18: iload_2
    //   19: iconst_0
    //   20: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   23: astore #5
    //   25: aload #5
    //   27: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper_android_textAppearance : I
    //   30: iconst_m1
    //   31: invokevirtual getResourceId : (II)I
    //   34: istore #6
    //   36: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper_android_drawableLeft : I
    //   39: istore #7
    //   41: aload #5
    //   43: iload #7
    //   45: invokevirtual hasValue : (I)Z
    //   48: ifeq -> 69
    //   51: aload_0
    //   52: aload_3
    //   53: aload #4
    //   55: aload #5
    //   57: iload #7
    //   59: iconst_0
    //   60: invokevirtual getResourceId : (II)I
    //   63: invokestatic createTintInfo : (Landroid/content/Context;Landroid/support/v7/widget/AppCompatDrawableManager;I)Landroid/support/v7/widget/TintInfo;
    //   66: putfield mDrawableLeftTint : Landroid/support/v7/widget/TintInfo;
    //   69: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper_android_drawableTop : I
    //   72: istore #7
    //   74: aload #5
    //   76: iload #7
    //   78: invokevirtual hasValue : (I)Z
    //   81: ifeq -> 102
    //   84: aload_0
    //   85: aload_3
    //   86: aload #4
    //   88: aload #5
    //   90: iload #7
    //   92: iconst_0
    //   93: invokevirtual getResourceId : (II)I
    //   96: invokestatic createTintInfo : (Landroid/content/Context;Landroid/support/v7/widget/AppCompatDrawableManager;I)Landroid/support/v7/widget/TintInfo;
    //   99: putfield mDrawableTopTint : Landroid/support/v7/widget/TintInfo;
    //   102: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper_android_drawableRight : I
    //   105: istore #7
    //   107: aload #5
    //   109: iload #7
    //   111: invokevirtual hasValue : (I)Z
    //   114: ifeq -> 135
    //   117: aload_0
    //   118: aload_3
    //   119: aload #4
    //   121: aload #5
    //   123: iload #7
    //   125: iconst_0
    //   126: invokevirtual getResourceId : (II)I
    //   129: invokestatic createTintInfo : (Landroid/content/Context;Landroid/support/v7/widget/AppCompatDrawableManager;I)Landroid/support/v7/widget/TintInfo;
    //   132: putfield mDrawableRightTint : Landroid/support/v7/widget/TintInfo;
    //   135: getstatic android/support/v7/appcompat/R$styleable.AppCompatTextHelper_android_drawableBottom : I
    //   138: istore #7
    //   140: aload #5
    //   142: iload #7
    //   144: invokevirtual hasValue : (I)Z
    //   147: ifeq -> 168
    //   150: aload_0
    //   151: aload_3
    //   152: aload #4
    //   154: aload #5
    //   156: iload #7
    //   158: iconst_0
    //   159: invokevirtual getResourceId : (II)I
    //   162: invokestatic createTintInfo : (Landroid/content/Context;Landroid/support/v7/widget/AppCompatDrawableManager;I)Landroid/support/v7/widget/TintInfo;
    //   165: putfield mDrawableBottomTint : Landroid/support/v7/widget/TintInfo;
    //   168: aload #5
    //   170: invokevirtual recycle : ()V
    //   173: aload_0
    //   174: getfield mView : Landroid/widget/TextView;
    //   177: invokevirtual getTransformationMethod : ()Landroid/text/method/TransformationMethod;
    //   180: instanceof android/text/method/PasswordTransformationMethod
    //   183: istore #8
    //   185: iconst_1
    //   186: istore #7
    //   188: aconst_null
    //   189: astore #4
    //   191: aconst_null
    //   192: astore #9
    //   194: aconst_null
    //   195: astore #10
    //   197: iload #6
    //   199: iconst_m1
    //   200: if_icmpeq -> 373
    //   203: aload_3
    //   204: iload #6
    //   206: getstatic android/support/v7/appcompat/R$styleable.TextAppearance : [I
    //   209: invokestatic obtainStyledAttributes : (Landroid/content/Context;I[I)Landroid/support/v7/widget/TintTypedArray;
    //   212: astore #9
    //   214: iload #8
    //   216: ifne -> 250
    //   219: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_textAllCaps : I
    //   222: istore #6
    //   224: aload #9
    //   226: iload #6
    //   228: invokevirtual hasValue : (I)Z
    //   231: ifeq -> 250
    //   234: aload #9
    //   236: iload #6
    //   238: iconst_0
    //   239: invokevirtual getBoolean : (IZ)Z
    //   242: istore #11
    //   244: iconst_1
    //   245: istore #6
    //   247: goto -> 256
    //   250: iconst_0
    //   251: istore #11
    //   253: iconst_0
    //   254: istore #6
    //   256: aload_0
    //   257: aload_3
    //   258: aload #9
    //   260: invokespecial updateTypefaceAndStyle : (Landroid/content/Context;Landroid/support/v7/widget/TintTypedArray;)V
    //   263: getstatic android/os/Build$VERSION.SDK_INT : I
    //   266: bipush #23
    //   268: if_icmpge -> 358
    //   271: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColor : I
    //   274: istore #12
    //   276: aload #9
    //   278: iload #12
    //   280: invokevirtual hasValue : (I)Z
    //   283: ifeq -> 298
    //   286: aload #9
    //   288: iload #12
    //   290: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   293: astore #4
    //   295: goto -> 301
    //   298: aconst_null
    //   299: astore #4
    //   301: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColorHint : I
    //   304: istore #12
    //   306: aload #9
    //   308: iload #12
    //   310: invokevirtual hasValue : (I)Z
    //   313: ifeq -> 328
    //   316: aload #9
    //   318: iload #12
    //   320: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   323: astore #5
    //   325: goto -> 331
    //   328: aconst_null
    //   329: astore #5
    //   331: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColorLink : I
    //   334: istore #12
    //   336: aload #9
    //   338: iload #12
    //   340: invokevirtual hasValue : (I)Z
    //   343: ifeq -> 355
    //   346: aload #9
    //   348: iload #12
    //   350: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   353: astore #10
    //   355: goto -> 365
    //   358: aconst_null
    //   359: astore #10
    //   361: aload #10
    //   363: astore #5
    //   365: aload #9
    //   367: invokevirtual recycle : ()V
    //   370: goto -> 390
    //   373: aconst_null
    //   374: astore #10
    //   376: aload #10
    //   378: astore #5
    //   380: iconst_0
    //   381: istore #11
    //   383: iconst_0
    //   384: istore #6
    //   386: aload #9
    //   388: astore #4
    //   390: aload_3
    //   391: aload_1
    //   392: getstatic android/support/v7/appcompat/R$styleable.TextAppearance : [I
    //   395: iload_2
    //   396: iconst_0
    //   397: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   400: astore #13
    //   402: iload #8
    //   404: ifne -> 439
    //   407: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_textAllCaps : I
    //   410: istore #12
    //   412: aload #13
    //   414: iload #12
    //   416: invokevirtual hasValue : (I)Z
    //   419: ifeq -> 439
    //   422: aload #13
    //   424: iload #12
    //   426: iconst_0
    //   427: invokevirtual getBoolean : (IZ)Z
    //   430: istore #11
    //   432: iload #7
    //   434: istore #6
    //   436: goto -> 439
    //   439: getstatic android/os/Build$VERSION.SDK_INT : I
    //   442: istore #7
    //   444: aload #4
    //   446: astore #14
    //   448: aload #10
    //   450: astore #15
    //   452: aload #5
    //   454: astore #9
    //   456: iload #7
    //   458: bipush #23
    //   460: if_icmpge -> 555
    //   463: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColor : I
    //   466: istore #12
    //   468: aload #13
    //   470: iload #12
    //   472: invokevirtual hasValue : (I)Z
    //   475: ifeq -> 487
    //   478: aload #13
    //   480: iload #12
    //   482: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   485: astore #4
    //   487: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColorHint : I
    //   490: istore #12
    //   492: aload #13
    //   494: iload #12
    //   496: invokevirtual hasValue : (I)Z
    //   499: ifeq -> 511
    //   502: aload #13
    //   504: iload #12
    //   506: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   509: astore #5
    //   511: getstatic android/support/v7/appcompat/R$styleable.TextAppearance_android_textColorLink : I
    //   514: istore #12
    //   516: aload #4
    //   518: astore #14
    //   520: aload #10
    //   522: astore #15
    //   524: aload #5
    //   526: astore #9
    //   528: aload #13
    //   530: iload #12
    //   532: invokevirtual hasValue : (I)Z
    //   535: ifeq -> 555
    //   538: aload #13
    //   540: iload #12
    //   542: invokevirtual getColorStateList : (I)Landroid/content/res/ColorStateList;
    //   545: astore #15
    //   547: aload #5
    //   549: astore #9
    //   551: aload #4
    //   553: astore #14
    //   555: aload_0
    //   556: aload_3
    //   557: aload #13
    //   559: invokespecial updateTypefaceAndStyle : (Landroid/content/Context;Landroid/support/v7/widget/TintTypedArray;)V
    //   562: aload #13
    //   564: invokevirtual recycle : ()V
    //   567: aload #14
    //   569: ifnull -> 581
    //   572: aload_0
    //   573: getfield mView : Landroid/widget/TextView;
    //   576: aload #14
    //   578: invokevirtual setTextColor : (Landroid/content/res/ColorStateList;)V
    //   581: aload #9
    //   583: ifnull -> 595
    //   586: aload_0
    //   587: getfield mView : Landroid/widget/TextView;
    //   590: aload #9
    //   592: invokevirtual setHintTextColor : (Landroid/content/res/ColorStateList;)V
    //   595: aload #15
    //   597: ifnull -> 609
    //   600: aload_0
    //   601: getfield mView : Landroid/widget/TextView;
    //   604: aload #15
    //   606: invokevirtual setLinkTextColor : (Landroid/content/res/ColorStateList;)V
    //   609: iload #8
    //   611: ifne -> 625
    //   614: iload #6
    //   616: ifeq -> 625
    //   619: aload_0
    //   620: iload #11
    //   622: invokevirtual setAllCaps : (Z)V
    //   625: aload_0
    //   626: getfield mFontTypeface : Landroid/graphics/Typeface;
    //   629: astore #5
    //   631: aload #5
    //   633: ifnull -> 649
    //   636: aload_0
    //   637: getfield mView : Landroid/widget/TextView;
    //   640: aload #5
    //   642: aload_0
    //   643: getfield mStyle : I
    //   646: invokevirtual setTypeface : (Landroid/graphics/Typeface;I)V
    //   649: aload_0
    //   650: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   653: aload_1
    //   654: iload_2
    //   655: invokevirtual loadFromAttributes : (Landroid/util/AttributeSet;I)V
    //   658: iload #7
    //   660: bipush #26
    //   662: if_icmplt -> 744
    //   665: aload_0
    //   666: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   669: invokevirtual getAutoSizeTextType : ()I
    //   672: ifeq -> 744
    //   675: aload_0
    //   676: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   679: invokevirtual getAutoSizeTextAvailableSizes : ()[I
    //   682: astore_1
    //   683: aload_1
    //   684: arraylength
    //   685: ifle -> 744
    //   688: aload_0
    //   689: getfield mView : Landroid/widget/TextView;
    //   692: invokevirtual getAutoSizeStepGranularity : ()I
    //   695: i2f
    //   696: ldc_w -1.0
    //   699: fcmpl
    //   700: ifeq -> 735
    //   703: aload_0
    //   704: getfield mView : Landroid/widget/TextView;
    //   707: aload_0
    //   708: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   711: invokevirtual getAutoSizeMinTextSize : ()I
    //   714: aload_0
    //   715: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   718: invokevirtual getAutoSizeMaxTextSize : ()I
    //   721: aload_0
    //   722: getfield mAutoSizeTextHelper : Landroid/support/v7/widget/AppCompatTextViewAutoSizeHelper;
    //   725: invokevirtual getAutoSizeStepGranularity : ()I
    //   728: iconst_0
    //   729: invokevirtual setAutoSizeTextTypeUniformWithConfiguration : (IIII)V
    //   732: goto -> 744
    //   735: aload_0
    //   736: getfield mView : Landroid/widget/TextView;
    //   739: aload_1
    //   740: iconst_0
    //   741: invokevirtual setAutoSizeTextTypeUniformWithPresetSizes : ([II)V
    //   744: return
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (Build.VERSION.SDK_INT < 26)
      autoSizeText(); 
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt) {
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    paramInt = R.styleable.TextAppearance_textAllCaps;
    if (tintTypedArray.hasValue(paramInt))
      setAllCaps(tintTypedArray.getBoolean(paramInt, false)); 
    if (Build.VERSION.SDK_INT < 23) {
      paramInt = R.styleable.TextAppearance_android_textColor;
      if (tintTypedArray.hasValue(paramInt)) {
        ColorStateList colorStateList = tintTypedArray.getColorStateList(paramInt);
        if (colorStateList != null)
          this.mView.setTextColor(colorStateList); 
      } 
    } 
    updateTypefaceAndStyle(paramContext, tintTypedArray);
    tintTypedArray.recycle();
    Typeface typeface = this.mFontTypeface;
    if (typeface != null)
      this.mView.setTypeface(typeface, this.mStyle); 
  }
  
  void setAllCaps(boolean paramBoolean) {
    this.mView.setAllCaps(paramBoolean);
  }
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
  }
  
  void setAutoSizeTextTypeWithDefaults(int paramInt) {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setTextSize(int paramInt, float paramFloat) {
    if (Build.VERSION.SDK_INT < 26 && !isAutoSizeEnabled())
      setTextSizeInternal(paramInt, paramFloat); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AppCompatTextHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */