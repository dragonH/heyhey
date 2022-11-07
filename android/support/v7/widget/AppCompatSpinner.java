package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
  private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
  
  private static final int MAX_ITEMS_MEASURED = 15;
  
  private static final int MODE_DIALOG = 0;
  
  private static final int MODE_DROPDOWN = 1;
  
  private static final int MODE_THEME = -1;
  
  private static final String TAG = "AppCompatSpinner";
  
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  
  private int mDropDownWidth;
  
  private ForwardingListener mForwardingListener;
  
  private DropdownPopup mPopup;
  
  private final Context mPopupContext;
  
  private final boolean mPopupSet;
  
  private SpinnerAdapter mTempAdapter;
  
  private final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public AppCompatSpinner(Context paramContext, int paramInt) {
    this(paramContext, null, R.attr.spinnerStyle, paramInt);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, Resources.Theme paramTheme) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new android/graphics/Rect
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: putfield mTempRect : Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic android/support/v7/appcompat/R$styleable.Spinner : [I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   28: astore #6
    //   30: aload_0
    //   31: new android/support/v7/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial <init> : (Landroid/view/View;)V
    //   39: putfield mBackgroundTintHelper : Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   42: aconst_null
    //   43: astore #7
    //   45: aload #5
    //   47: ifnull -> 67
    //   50: aload_0
    //   51: new android/support/v7/view/ContextThemeWrapper
    //   54: dup
    //   55: aload_1
    //   56: aload #5
    //   58: invokespecial <init> : (Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   61: putfield mPopupContext : Landroid/content/Context;
    //   64: goto -> 123
    //   67: aload #6
    //   69: getstatic android/support/v7/appcompat/R$styleable.Spinner_popupTheme : I
    //   72: iconst_0
    //   73: invokevirtual getResourceId : (II)I
    //   76: istore #8
    //   78: iload #8
    //   80: ifeq -> 100
    //   83: aload_0
    //   84: new android/support/v7/view/ContextThemeWrapper
    //   87: dup
    //   88: aload_1
    //   89: iload #8
    //   91: invokespecial <init> : (Landroid/content/Context;I)V
    //   94: putfield mPopupContext : Landroid/content/Context;
    //   97: goto -> 123
    //   100: getstatic android/os/Build$VERSION.SDK_INT : I
    //   103: bipush #23
    //   105: if_icmpge -> 114
    //   108: aload_1
    //   109: astore #5
    //   111: goto -> 117
    //   114: aconst_null
    //   115: astore #5
    //   117: aload_0
    //   118: aload #5
    //   120: putfield mPopupContext : Landroid/content/Context;
    //   123: aload_0
    //   124: getfield mPopupContext : Landroid/content/Context;
    //   127: ifnull -> 366
    //   130: iload #4
    //   132: istore #8
    //   134: iload #4
    //   136: iconst_m1
    //   137: if_icmpne -> 263
    //   140: aload_1
    //   141: aload_2
    //   142: getstatic android/support/v7/widget/AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE : [I
    //   145: iload_3
    //   146: iconst_0
    //   147: invokevirtual obtainStyledAttributes : (Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   150: astore #5
    //   152: iload #4
    //   154: istore #8
    //   156: aload #5
    //   158: astore #9
    //   160: aload #5
    //   162: astore #7
    //   164: aload #5
    //   166: iconst_0
    //   167: invokevirtual hasValue : (I)Z
    //   170: ifeq -> 190
    //   173: aload #5
    //   175: astore #7
    //   177: aload #5
    //   179: iconst_0
    //   180: iconst_0
    //   181: invokevirtual getInt : (II)I
    //   184: istore #8
    //   186: aload #5
    //   188: astore #9
    //   190: aload #9
    //   192: invokevirtual recycle : ()V
    //   195: goto -> 263
    //   198: astore #9
    //   200: goto -> 215
    //   203: astore_1
    //   204: aload #7
    //   206: astore_2
    //   207: goto -> 253
    //   210: astore #9
    //   212: aconst_null
    //   213: astore #5
    //   215: aload #5
    //   217: astore #7
    //   219: ldc 'AppCompatSpinner'
    //   221: ldc 'Could not read android:spinnerMode'
    //   223: aload #9
    //   225: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   228: pop
    //   229: iload #4
    //   231: istore #8
    //   233: aload #5
    //   235: ifnull -> 263
    //   238: iload #4
    //   240: istore #8
    //   242: aload #5
    //   244: astore #9
    //   246: goto -> 190
    //   249: astore_1
    //   250: aload #7
    //   252: astore_2
    //   253: aload_2
    //   254: ifnull -> 261
    //   257: aload_2
    //   258: invokevirtual recycle : ()V
    //   261: aload_1
    //   262: athrow
    //   263: iload #8
    //   265: iconst_1
    //   266: if_icmpne -> 366
    //   269: new android/support/v7/widget/AppCompatSpinner$DropdownPopup
    //   272: dup
    //   273: aload_0
    //   274: aload_0
    //   275: getfield mPopupContext : Landroid/content/Context;
    //   278: aload_2
    //   279: iload_3
    //   280: invokespecial <init> : (Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   283: astore #5
    //   285: aload_0
    //   286: getfield mPopupContext : Landroid/content/Context;
    //   289: aload_2
    //   290: getstatic android/support/v7/appcompat/R$styleable.Spinner : [I
    //   293: iload_3
    //   294: iconst_0
    //   295: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   298: astore #7
    //   300: aload_0
    //   301: aload #7
    //   303: getstatic android/support/v7/appcompat/R$styleable.Spinner_android_dropDownWidth : I
    //   306: bipush #-2
    //   308: invokevirtual getLayoutDimension : (II)I
    //   311: putfield mDropDownWidth : I
    //   314: aload #5
    //   316: aload #7
    //   318: getstatic android/support/v7/appcompat/R$styleable.Spinner_android_popupBackground : I
    //   321: invokevirtual getDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   324: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   327: aload #5
    //   329: aload #6
    //   331: getstatic android/support/v7/appcompat/R$styleable.Spinner_android_prompt : I
    //   334: invokevirtual getString : (I)Ljava/lang/String;
    //   337: invokevirtual setPromptText : (Ljava/lang/CharSequence;)V
    //   340: aload #7
    //   342: invokevirtual recycle : ()V
    //   345: aload_0
    //   346: aload #5
    //   348: putfield mPopup : Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
    //   351: aload_0
    //   352: new android/support/v7/widget/AppCompatSpinner$1
    //   355: dup
    //   356: aload_0
    //   357: aload_0
    //   358: aload #5
    //   360: invokespecial <init> : (Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
    //   363: putfield mForwardingListener : Landroid/support/v7/widget/ForwardingListener;
    //   366: aload #6
    //   368: getstatic android/support/v7/appcompat/R$styleable.Spinner_android_entries : I
    //   371: invokevirtual getTextArray : (I)[Ljava/lang/CharSequence;
    //   374: astore #5
    //   376: aload #5
    //   378: ifnull -> 406
    //   381: new android/widget/ArrayAdapter
    //   384: dup
    //   385: aload_1
    //   386: ldc 17367048
    //   388: aload #5
    //   390: invokespecial <init> : (Landroid/content/Context;I[Ljava/lang/Object;)V
    //   393: astore_1
    //   394: aload_1
    //   395: getstatic android/support/v7/appcompat/R$layout.support_simple_spinner_dropdown_item : I
    //   398: invokevirtual setDropDownViewResource : (I)V
    //   401: aload_0
    //   402: aload_1
    //   403: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   406: aload #6
    //   408: invokevirtual recycle : ()V
    //   411: aload_0
    //   412: iconst_1
    //   413: putfield mPopupSet : Z
    //   416: aload_0
    //   417: getfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   420: astore_1
    //   421: aload_1
    //   422: ifnull -> 435
    //   425: aload_0
    //   426: aload_1
    //   427: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   430: aload_0
    //   431: aconst_null
    //   432: putfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   435: aload_0
    //   436: getfield mBackgroundTintHelper : Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   439: aload_2
    //   440: iload_3
    //   441: invokevirtual loadFromAttributes : (Landroid/util/AttributeSet;I)V
    //   444: return
    // Exception table:
    //   from	to	target	type
    //   140	152	210	java/lang/Exception
    //   140	152	203	finally
    //   164	173	198	java/lang/Exception
    //   164	173	249	finally
    //   177	186	198	java/lang/Exception
    //   177	186	249	finally
    //   219	229	249	finally
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable) {
    int i = 0;
    if (paramSpinnerAdapter == null)
      return 0; 
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int k = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int m = Math.max(0, getSelectedItemPosition());
    int n = Math.min(paramSpinnerAdapter.getCount(), m + 15);
    int i1 = Math.max(0, m - 15 - n - m);
    View view = null;
    m = 0;
    while (i1 < n) {
      int i2 = paramSpinnerAdapter.getItemViewType(i1);
      int i3 = i;
      if (i2 != i) {
        view = null;
        i3 = i2;
      } 
      view = paramSpinnerAdapter.getView(i1, view, (ViewGroup)this);
      if (view.getLayoutParams() == null)
        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2)); 
      view.measure(j, k);
      m = Math.max(m, view.getMeasuredWidth());
      i1++;
      i = i3;
    } 
    i1 = m;
    if (paramDrawable != null) {
      paramDrawable.getPadding(this.mTempRect);
      Rect rect = this.mTempRect;
      i1 = m + rect.left + rect.right;
    } 
    return i1;
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.applySupportBackgroundTint(); 
  }
  
  public int getDropDownHorizontalOffset() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getHorizontalOffset() : super.getDropDownHorizontalOffset();
  }
  
  public int getDropDownVerticalOffset() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getVerticalOffset() : super.getDropDownVerticalOffset();
  }
  
  public int getDropDownWidth() {
    return (this.mPopup != null) ? this.mDropDownWidth : super.getDropDownWidth();
  }
  
  public Drawable getPopupBackground() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getBackground() : super.getPopupBackground();
  }
  
  public Context getPopupContext() {
    return (this.mPopup != null) ? this.mPopupContext : ((Build.VERSION.SDK_INT >= 23) ? super.getPopupContext() : null);
  }
  
  public CharSequence getPrompt() {
    CharSequence charSequence;
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      charSequence = dropdownPopup.getHintText();
    } else {
      charSequence = super.getPrompt();
    } 
    return charSequence;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      ColorStateList colorStateList = appCompatBackgroundHelper.getSupportBackgroundTintList();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (ColorStateList)appCompatBackgroundHelper;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      PorterDuff.Mode mode = appCompatBackgroundHelper.getSupportBackgroundTintMode();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (PorterDuff.Mode)appCompatBackgroundHelper;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null && dropdownPopup.isShowing())
      this.mPopup.dismiss(); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mPopup != null && View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight()); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    ForwardingListener forwardingListener = this.mForwardingListener;
    return (forwardingListener != null && forwardingListener.onTouch((View)this, paramMotionEvent)) ? true : super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick() {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      if (!dropdownPopup.isShowing())
        this.mPopup.show(); 
      return true;
    } 
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter) {
    if (!this.mPopupSet) {
      this.mTempAdapter = paramSpinnerAdapter;
      return;
    } 
    super.setAdapter(paramSpinnerAdapter);
    if (this.mPopup != null) {
      Context context1 = this.mPopupContext;
      Context context2 = context1;
      if (context1 == null)
        context2 = getContext(); 
      this.mPopup.setAdapter(new DropDownAdapter(paramSpinnerAdapter, context2.getTheme()));
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable); 
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt) {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundResource(paramInt); 
  }
  
  public void setDropDownHorizontalOffset(int paramInt) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setHorizontalOffset(paramInt);
    } else {
      super.setDropDownHorizontalOffset(paramInt);
    } 
  }
  
  public void setDropDownVerticalOffset(int paramInt) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setVerticalOffset(paramInt);
    } else {
      super.setDropDownVerticalOffset(paramInt);
    } 
  }
  
  public void setDropDownWidth(int paramInt) {
    if (this.mPopup != null) {
      this.mDropDownWidth = paramInt;
    } else {
      super.setDropDownWidth(paramInt);
    } 
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setBackgroundDrawable(paramDrawable);
    } else {
      super.setPopupBackgroundDrawable(paramDrawable);
    } 
  }
  
  public void setPopupBackgroundResource(@DrawableRes int paramInt) {
    setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setPromptText(paramCharSequence);
    } else {
      super.setPrompt(paramCharSequence);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode); 
  }
  
  private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
    private SpinnerAdapter mAdapter;
    
    private ListAdapter mListAdapter;
    
    public DropDownAdapter(@Nullable SpinnerAdapter param1SpinnerAdapter, @Nullable Resources.Theme param1Theme) {
      this.mAdapter = param1SpinnerAdapter;
      if (param1SpinnerAdapter instanceof ListAdapter)
        this.mListAdapter = (ListAdapter)param1SpinnerAdapter; 
      if (param1Theme != null) {
        ThemedSpinnerAdapter themedSpinnerAdapter;
        if (Build.VERSION.SDK_INT >= 23 && param1SpinnerAdapter instanceof ThemedSpinnerAdapter) {
          themedSpinnerAdapter = (ThemedSpinnerAdapter)param1SpinnerAdapter;
          if (themedSpinnerAdapter.getDropDownViewTheme() != param1Theme)
            themedSpinnerAdapter.setDropDownViewTheme(param1Theme); 
        } else if (themedSpinnerAdapter instanceof ThemedSpinnerAdapter) {
          ThemedSpinnerAdapter themedSpinnerAdapter1 = (ThemedSpinnerAdapter)themedSpinnerAdapter;
          if (themedSpinnerAdapter1.getDropDownViewTheme() == null)
            themedSpinnerAdapter1.setDropDownViewTheme(param1Theme); 
        } 
      } 
    }
    
    public boolean areAllItemsEnabled() {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.areAllItemsEnabled() : true;
    }
    
    public int getCount() {
      int i;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        i = 0;
      } else {
        i = spinnerAdapter.getCount();
      } 
      return i;
    }
    
    public View getDropDownView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        param1View = null;
      } else {
        param1View = spinnerAdapter.getDropDownView(param1Int, param1View, param1ViewGroup);
      } 
      return param1View;
    }
    
    public Object getItem(int param1Int) {
      Object object = this.mAdapter;
      if (object == null) {
        object = null;
      } else {
        object = object.getItem(param1Int);
      } 
      return object;
    }
    
    public long getItemId(int param1Int) {
      long l;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        l = -1L;
      } else {
        l = spinnerAdapter.getItemId(param1Int);
      } 
      return l;
    }
    
    public int getItemViewType(int param1Int) {
      return 0;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      return getDropDownView(param1Int, param1View, param1ViewGroup);
    }
    
    public int getViewTypeCount() {
      return 1;
    }
    
    public boolean hasStableIds() {
      boolean bool;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null && spinnerAdapter.hasStableIds()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEmpty() {
      boolean bool;
      if (getCount() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEnabled(int param1Int) {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.isEnabled(param1Int) : true;
    }
    
    public void registerDataSetObserver(DataSetObserver param1DataSetObserver) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null)
        spinnerAdapter.registerDataSetObserver(param1DataSetObserver); 
    }
    
    public void unregisterDataSetObserver(DataSetObserver param1DataSetObserver) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null)
        spinnerAdapter.unregisterDataSetObserver(param1DataSetObserver); 
    }
  }
  
  private class DropdownPopup extends ListPopupWindow {
    ListAdapter mAdapter;
    
    private CharSequence mHintText;
    
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context param1Context, AttributeSet param1AttributeSet, int param1Int) {
      super(param1Context, param1AttributeSet, param1Int);
      setAnchorView((View)AppCompatSpinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param2AdapterView, View param2View, int param2Int, long param2Long) {
              AppCompatSpinner.this.setSelection(param2Int);
              if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.DropdownPopup dropdownPopup = AppCompatSpinner.DropdownPopup.this;
                AppCompatSpinner.this.performItemClick(param2View, param2Int, dropdownPopup.mAdapter.getItemId(param2Int));
              } 
              AppCompatSpinner.DropdownPopup.this.dismiss();
            }
          });
    }
    
    void computeContentWidth() {
      Drawable drawable = getBackground();
      int i = 0;
      if (drawable != null) {
        drawable.getPadding(AppCompatSpinner.this.mTempRect);
        if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
          i = AppCompatSpinner.this.mTempRect.right;
        } else {
          i = -AppCompatSpinner.this.mTempRect.left;
        } 
      } else {
        Rect rect = AppCompatSpinner.this.mTempRect;
        AppCompatSpinner.this.mTempRect.right = 0;
        rect.left = 0;
      } 
      int j = AppCompatSpinner.this.getPaddingLeft();
      int k = AppCompatSpinner.this.getPaddingRight();
      int m = AppCompatSpinner.this.getWidth();
      if (AppCompatSpinner.this.mDropDownWidth == -2) {
        int n = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, getBackground());
        int i1 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics()).widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
        int i2 = n;
        if (n > i1)
          i2 = i1; 
        setContentWidth(Math.max(i2, m - j - k));
      } else if (AppCompatSpinner.this.mDropDownWidth == -1) {
        setContentWidth(m - j - k);
      } else {
        setContentWidth(AppCompatSpinner.this.mDropDownWidth);
      } 
      if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
        i += m - k - getWidth();
      } else {
        i += j;
      } 
      setHorizontalOffset(i);
    }
    
    public CharSequence getHintText() {
      return this.mHintText;
    }
    
    boolean isVisibleToUser(View param1View) {
      boolean bool;
      if (ViewCompat.isAttachedToWindow(param1View) && param1View.getGlobalVisibleRect(this.mVisibleRect)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setAdapter(ListAdapter param1ListAdapter) {
      super.setAdapter(param1ListAdapter);
      this.mAdapter = param1ListAdapter;
    }
    
    public void setPromptText(CharSequence param1CharSequence) {
      this.mHintText = param1CharSequence;
    }
    
    public void show() {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(AppCompatSpinner.this.getSelectedItemPosition());
      if (bool)
        return; 
      ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      if (viewTreeObserver != null) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
              AppCompatSpinner.DropdownPopup dropdownPopup = AppCompatSpinner.DropdownPopup.this;
              if (!dropdownPopup.isVisibleToUser((View)AppCompatSpinner.this)) {
                AppCompatSpinner.DropdownPopup.this.dismiss();
              } else {
                AppCompatSpinner.DropdownPopup.this.computeContentWidth();
                AppCompatSpinner.DropdownPopup.this.show();
              } 
            }
          };
        viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
              public void onDismiss() {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null)
                  viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
              }
            });
      } 
    }
  }
  
  class null implements AdapterView.OnItemClickListener {
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      AppCompatSpinner.this.setSelection(param1Int);
      if (AppCompatSpinner.this.getOnItemClickListener() != null) {
        AppCompatSpinner.DropdownPopup dropdownPopup = this.this$1;
        AppCompatSpinner.this.performItemClick(param1View, param1Int, dropdownPopup.mAdapter.getItemId(param1Int));
      } 
      this.this$1.dismiss();
    }
  }
  
  class null implements ViewTreeObserver.OnGlobalLayoutListener {
    public void onGlobalLayout() {
      AppCompatSpinner.DropdownPopup dropdownPopup = this.this$1;
      if (!dropdownPopup.isVisibleToUser((View)AppCompatSpinner.this)) {
        this.this$1.dismiss();
      } else {
        this.this$1.computeContentWidth();
        this.this$1.show();
      } 
    }
  }
  
  class null implements PopupWindow.OnDismissListener {
    public void onDismiss() {
      ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      if (viewTreeObserver != null)
        viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/AppCompatSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */