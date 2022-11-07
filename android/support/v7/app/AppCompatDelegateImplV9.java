package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

@RequiresApi(14)
class AppCompatDelegateImplV9 extends AppCompatDelegateImplBase implements MenuBuilder.Callback, LayoutInflater.Factory2 {
  private static final boolean IS_PRE_LOLLIPOP;
  
  private ActionMenuPresenterCallback mActionMenuPresenterCallback;
  
  ActionMode mActionMode;
  
  PopupWindow mActionModePopup;
  
  ActionBarContextView mActionModeView;
  
  private AppCompatViewInflater mAppCompatViewInflater;
  
  private boolean mClosingActionMenu;
  
  private DecorContentParent mDecorContentParent;
  
  private boolean mEnableDefaultActionBarUp;
  
  ViewPropertyAnimatorCompat mFadeAnim = null;
  
  private boolean mFeatureIndeterminateProgress;
  
  private boolean mFeatureProgress;
  
  int mInvalidatePanelMenuFeatures;
  
  boolean mInvalidatePanelMenuPosted;
  
  private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
      public void run() {
        AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
        if ((appCompatDelegateImplV9.mInvalidatePanelMenuFeatures & 0x1) != 0)
          appCompatDelegateImplV9.doInvalidatePanelMenu(0); 
        appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
        if ((appCompatDelegateImplV9.mInvalidatePanelMenuFeatures & 0x1000) != 0)
          appCompatDelegateImplV9.doInvalidatePanelMenu(108); 
        appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
        appCompatDelegateImplV9.mInvalidatePanelMenuPosted = false;
        appCompatDelegateImplV9.mInvalidatePanelMenuFeatures = 0;
      }
    };
  
  private boolean mLongPressBackDown;
  
  private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
  
  private PanelFeatureState[] mPanels;
  
  private PanelFeatureState mPreparedPanel;
  
  Runnable mShowActionModePopup;
  
  private View mStatusGuard;
  
  private ViewGroup mSubDecor;
  
  private boolean mSubDecorInstalled;
  
  private Rect mTempRect1;
  
  private Rect mTempRect2;
  
  private TextView mTitleView;
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT < 21) {
      bool = true;
    } else {
      bool = false;
    } 
    IS_PRE_LOLLIPOP = bool;
  }
  
  AppCompatDelegateImplV9(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback) {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  private void applyFixedSizeWindow() {
    ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
    View view = this.mWindow.getDecorView();
    contentFrameLayout.setDecorPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
    int i = R.styleable.AppCompatTheme_windowFixedWidthMajor;
    if (typedArray.hasValue(i))
      typedArray.getValue(i, contentFrameLayout.getFixedWidthMajor()); 
    i = R.styleable.AppCompatTheme_windowFixedWidthMinor;
    if (typedArray.hasValue(i))
      typedArray.getValue(i, contentFrameLayout.getFixedWidthMinor()); 
    i = R.styleable.AppCompatTheme_windowFixedHeightMajor;
    if (typedArray.hasValue(i))
      typedArray.getValue(i, contentFrameLayout.getFixedHeightMajor()); 
    i = R.styleable.AppCompatTheme_windowFixedHeightMinor;
    if (typedArray.hasValue(i))
      typedArray.getValue(i, contentFrameLayout.getFixedHeightMinor()); 
    typedArray.recycle();
    contentFrameLayout.requestLayout();
  }
  
  private ViewGroup createSubDecor() {
    StringBuilder stringBuilder;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    int i = R.styleable.AppCompatTheme_windowActionBar;
    if (typedArray.hasValue(i)) {
      ViewGroup viewGroup;
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
        requestWindowFeature(1);
      } else if (typedArray.getBoolean(i, false)) {
        requestWindowFeature(108);
      } 
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false))
        requestWindowFeature(109); 
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false))
        requestWindowFeature(10); 
      this.mIsFloating = typedArray.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
      typedArray.recycle();
      this.mWindow.getDecorView();
      LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
      if (!this.mWindowNoTitle) {
        if (this.mIsFloating) {
          viewGroup = (ViewGroup)layoutInflater.inflate(R.layout.abc_dialog_title_material, null);
          this.mOverlayActionBar = false;
          this.mHasActionBar = false;
        } else if (this.mHasActionBar) {
          Context context;
          TypedValue typedValue = new TypedValue();
          this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
          if (typedValue.resourceId != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
          } else {
            context = this.mContext;
          } 
          ViewGroup viewGroup1 = (ViewGroup)LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, null);
          DecorContentParent decorContentParent = (DecorContentParent)viewGroup1.findViewById(R.id.decor_content_parent);
          this.mDecorContentParent = decorContentParent;
          decorContentParent.setWindowCallback(getWindowCallback());
          if (this.mOverlayActionBar)
            this.mDecorContentParent.initFeature(109); 
          if (this.mFeatureProgress)
            this.mDecorContentParent.initFeature(2); 
          viewGroup = viewGroup1;
          if (this.mFeatureIndeterminateProgress) {
            this.mDecorContentParent.initFeature(5);
            viewGroup = viewGroup1;
          } 
        } else {
          layoutInflater = null;
        } 
      } else {
        if (this.mOverlayActionMode) {
          viewGroup = (ViewGroup)layoutInflater.inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
        } else {
          viewGroup = (ViewGroup)viewGroup.inflate(R.layout.abc_screen_simple, null);
        } 
        if (Build.VERSION.SDK_INT >= 21) {
          ViewCompat.setOnApplyWindowInsetsListener((View)viewGroup, new OnApplyWindowInsetsListener() {
                public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
                  int i = param1WindowInsetsCompat.getSystemWindowInsetTop();
                  int j = AppCompatDelegateImplV9.this.updateStatusGuard(i);
                  WindowInsetsCompat windowInsetsCompat = param1WindowInsetsCompat;
                  if (i != j)
                    windowInsetsCompat = param1WindowInsetsCompat.replaceSystemWindowInsets(param1WindowInsetsCompat.getSystemWindowInsetLeft(), j, param1WindowInsetsCompat.getSystemWindowInsetRight(), param1WindowInsetsCompat.getSystemWindowInsetBottom()); 
                  return ViewCompat.onApplyWindowInsets(param1View, windowInsetsCompat);
                }
              });
        } else {
          ((FitWindowsViewGroup)viewGroup).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                public void onFitSystemWindows(Rect param1Rect) {
                  param1Rect.top = AppCompatDelegateImplV9.this.updateStatusGuard(param1Rect.top);
                }
              });
        } 
      } 
      if (viewGroup != null) {
        if (this.mDecorContentParent == null)
          this.mTitleView = (TextView)viewGroup.findViewById(R.id.title); 
        ViewUtils.makeOptionalFitsSystemWindows((View)viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout)viewGroup.findViewById(R.id.action_bar_activity_content);
        ViewGroup viewGroup1 = (ViewGroup)this.mWindow.findViewById(16908290);
        if (viewGroup1 != null) {
          while (viewGroup1.getChildCount() > 0) {
            View view = viewGroup1.getChildAt(0);
            viewGroup1.removeViewAt(0);
            contentFrameLayout.addView(view);
          } 
          viewGroup1.setId(-1);
          contentFrameLayout.setId(16908290);
          if (viewGroup1 instanceof FrameLayout)
            ((FrameLayout)viewGroup1).setForeground(null); 
        } 
        this.mWindow.setContentView((View)viewGroup);
        contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
              public void onAttachedFromWindow() {}
              
              public void onDetachedFromWindow() {
                AppCompatDelegateImplV9.this.dismissPopups();
              }
            });
        return viewGroup;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append("AppCompat does not support the current theme features: { windowActionBar: ");
      stringBuilder.append(this.mHasActionBar);
      stringBuilder.append(", windowActionBarOverlay: ");
      stringBuilder.append(this.mOverlayActionBar);
      stringBuilder.append(", android:windowIsFloating: ");
      stringBuilder.append(this.mIsFloating);
      stringBuilder.append(", windowActionModeOverlay: ");
      stringBuilder.append(this.mOverlayActionMode);
      stringBuilder.append(", windowNoTitle: ");
      stringBuilder.append(this.mWindowNoTitle);
      stringBuilder.append(" }");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    stringBuilder.recycle();
    IllegalStateException illegalStateException = new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    throw illegalStateException;
  }
  
  private void ensureSubDecor() {
    if (!this.mSubDecorInstalled) {
      this.mSubDecor = createSubDecor();
      CharSequence charSequence = getTitle();
      if (!TextUtils.isEmpty(charSequence))
        onTitleChanged(charSequence); 
      applyFixedSizeWindow();
      onSubDecorInstalled(this.mSubDecor);
      this.mSubDecorInstalled = true;
      PanelFeatureState panelFeatureState = getPanelState(0, false);
      if (!isDestroyed() && (panelFeatureState == null || panelFeatureState.menu == null))
        invalidatePanelMenu(108); 
    } 
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState) {
    View view = paramPanelFeatureState.createdPanelView;
    boolean bool = true;
    if (view != null) {
      paramPanelFeatureState.shownPanelView = view;
      return true;
    } 
    if (paramPanelFeatureState.menu == null)
      return false; 
    if (this.mPanelMenuPresenterCallback == null)
      this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(); 
    view = (View)paramPanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
    paramPanelFeatureState.shownPanelView = view;
    if (view == null)
      bool = false; 
    return bool;
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState) {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    paramPanelFeatureState.decorView = (ViewGroup)new ListMenuDecorView(paramPanelFeatureState.listPresenterContext);
    paramPanelFeatureState.gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mContext : Landroid/content/Context;
    //   4: astore_2
    //   5: aload_1
    //   6: getfield featureId : I
    //   9: istore_3
    //   10: iload_3
    //   11: ifeq -> 23
    //   14: aload_2
    //   15: astore #4
    //   17: iload_3
    //   18: bipush #108
    //   20: if_icmpne -> 202
    //   23: aload_2
    //   24: astore #4
    //   26: aload_0
    //   27: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   30: ifnull -> 202
    //   33: new android/util/TypedValue
    //   36: dup
    //   37: invokespecial <init> : ()V
    //   40: astore #5
    //   42: aload_2
    //   43: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   46: astore #6
    //   48: aload #6
    //   50: getstatic android/support/v7/appcompat/R$attr.actionBarTheme : I
    //   53: aload #5
    //   55: iconst_1
    //   56: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   59: pop
    //   60: aconst_null
    //   61: astore #4
    //   63: aload #5
    //   65: getfield resourceId : I
    //   68: ifeq -> 113
    //   71: aload_2
    //   72: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   75: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   78: astore #4
    //   80: aload #4
    //   82: aload #6
    //   84: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   87: aload #4
    //   89: aload #5
    //   91: getfield resourceId : I
    //   94: iconst_1
    //   95: invokevirtual applyStyle : (IZ)V
    //   98: aload #4
    //   100: getstatic android/support/v7/appcompat/R$attr.actionBarWidgetTheme : I
    //   103: aload #5
    //   105: iconst_1
    //   106: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   109: pop
    //   110: goto -> 125
    //   113: aload #6
    //   115: getstatic android/support/v7/appcompat/R$attr.actionBarWidgetTheme : I
    //   118: aload #5
    //   120: iconst_1
    //   121: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   124: pop
    //   125: aload #4
    //   127: astore #7
    //   129: aload #5
    //   131: getfield resourceId : I
    //   134: ifeq -> 173
    //   137: aload #4
    //   139: astore #7
    //   141: aload #4
    //   143: ifnonnull -> 162
    //   146: aload_2
    //   147: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   150: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   153: astore #7
    //   155: aload #7
    //   157: aload #6
    //   159: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   162: aload #7
    //   164: aload #5
    //   166: getfield resourceId : I
    //   169: iconst_1
    //   170: invokevirtual applyStyle : (IZ)V
    //   173: aload_2
    //   174: astore #4
    //   176: aload #7
    //   178: ifnull -> 202
    //   181: new android/support/v7/view/ContextThemeWrapper
    //   184: dup
    //   185: aload_2
    //   186: iconst_0
    //   187: invokespecial <init> : (Landroid/content/Context;I)V
    //   190: astore #4
    //   192: aload #4
    //   194: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   197: aload #7
    //   199: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   202: new android/support/v7/view/menu/MenuBuilder
    //   205: dup
    //   206: aload #4
    //   208: invokespecial <init> : (Landroid/content/Context;)V
    //   211: astore #4
    //   213: aload #4
    //   215: aload_0
    //   216: invokevirtual setCallback : (Landroid/support/v7/view/menu/MenuBuilder$Callback;)V
    //   219: aload_1
    //   220: aload #4
    //   222: invokevirtual setMenu : (Landroid/support/v7/view/menu/MenuBuilder;)V
    //   225: iconst_1
    //   226: ireturn
  }
  
  private void invalidatePanelMenu(int paramInt) {
    this.mInvalidatePanelMenuFeatures = 1 << paramInt | this.mInvalidatePanelMenuFeatures;
    if (!this.mInvalidatePanelMenuPosted) {
      ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
      this.mInvalidatePanelMenuPosted = true;
    } 
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getRepeatCount() == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (!panelFeatureState.isOpen)
        return preparePanel(panelFeatureState, paramKeyEvent); 
    } 
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mActionMode : Landroid/support/v7/view/ActionMode;
    //   4: ifnull -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: iconst_1
    //   10: istore_3
    //   11: aload_0
    //   12: iload_1
    //   13: iconst_1
    //   14: invokevirtual getPanelState : (IZ)Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;
    //   17: astore #4
    //   19: iload_1
    //   20: ifne -> 114
    //   23: aload_0
    //   24: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   27: astore #5
    //   29: aload #5
    //   31: ifnull -> 114
    //   34: aload #5
    //   36: invokeinterface canShowOverflowMenu : ()Z
    //   41: ifeq -> 114
    //   44: aload_0
    //   45: getfield mContext : Landroid/content/Context;
    //   48: invokestatic get : (Landroid/content/Context;)Landroid/view/ViewConfiguration;
    //   51: invokevirtual hasPermanentMenuKey : ()Z
    //   54: ifne -> 114
    //   57: aload_0
    //   58: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   61: invokeinterface isOverflowMenuShowing : ()Z
    //   66: ifne -> 100
    //   69: aload_0
    //   70: invokevirtual isDestroyed : ()Z
    //   73: ifne -> 192
    //   76: aload_0
    //   77: aload #4
    //   79: aload_2
    //   80: invokespecial preparePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   83: ifeq -> 192
    //   86: aload_0
    //   87: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   90: invokeinterface showOverflowMenu : ()Z
    //   95: istore #6
    //   97: goto -> 205
    //   100: aload_0
    //   101: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   104: invokeinterface hideOverflowMenu : ()Z
    //   109: istore #6
    //   111: goto -> 205
    //   114: aload #4
    //   116: getfield isOpen : Z
    //   119: istore #6
    //   121: iload #6
    //   123: ifne -> 198
    //   126: aload #4
    //   128: getfield isHandled : Z
    //   131: ifeq -> 137
    //   134: goto -> 198
    //   137: aload #4
    //   139: getfield isPrepared : Z
    //   142: ifeq -> 192
    //   145: aload #4
    //   147: getfield refreshMenuContent : Z
    //   150: ifeq -> 171
    //   153: aload #4
    //   155: iconst_0
    //   156: putfield isPrepared : Z
    //   159: aload_0
    //   160: aload #4
    //   162: aload_2
    //   163: invokespecial preparePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   166: istore #6
    //   168: goto -> 174
    //   171: iconst_1
    //   172: istore #6
    //   174: iload #6
    //   176: ifeq -> 192
    //   179: aload_0
    //   180: aload #4
    //   182: aload_2
    //   183: invokespecial openPanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Landroid/view/KeyEvent;)V
    //   186: iload_3
    //   187: istore #6
    //   189: goto -> 205
    //   192: iconst_0
    //   193: istore #6
    //   195: goto -> 205
    //   198: aload_0
    //   199: aload #4
    //   201: iconst_1
    //   202: invokevirtual closePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Z)V
    //   205: iload #6
    //   207: ifeq -> 246
    //   210: aload_0
    //   211: getfield mContext : Landroid/content/Context;
    //   214: ldc_w 'audio'
    //   217: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   220: checkcast android/media/AudioManager
    //   223: astore_2
    //   224: aload_2
    //   225: ifnull -> 236
    //   228: aload_2
    //   229: iconst_0
    //   230: invokevirtual playSoundEffect : (I)V
    //   233: goto -> 246
    //   236: ldc_w 'AppCompatDelegate'
    //   239: ldc_w 'Couldn't get audio manager'
    //   242: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   245: pop
    //   246: iload #6
    //   248: ireturn
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_1
    //   1: getfield isOpen : Z
    //   4: ifne -> 432
    //   7: aload_0
    //   8: invokevirtual isDestroyed : ()Z
    //   11: ifeq -> 17
    //   14: goto -> 432
    //   17: aload_1
    //   18: getfield featureId : I
    //   21: ifne -> 87
    //   24: aload_0
    //   25: getfield mContext : Landroid/content/Context;
    //   28: astore_3
    //   29: aload_3
    //   30: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   33: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   36: getfield screenLayout : I
    //   39: bipush #15
    //   41: iand
    //   42: iconst_4
    //   43: if_icmpne -> 52
    //   46: iconst_1
    //   47: istore #4
    //   49: goto -> 55
    //   52: iconst_0
    //   53: istore #4
    //   55: aload_3
    //   56: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   59: getfield targetSdkVersion : I
    //   62: bipush #11
    //   64: if_icmplt -> 73
    //   67: iconst_1
    //   68: istore #5
    //   70: goto -> 76
    //   73: iconst_0
    //   74: istore #5
    //   76: iload #4
    //   78: ifeq -> 87
    //   81: iload #5
    //   83: ifeq -> 87
    //   86: return
    //   87: aload_0
    //   88: invokevirtual getWindowCallback : ()Landroid/view/Window$Callback;
    //   91: astore_3
    //   92: aload_3
    //   93: ifnull -> 120
    //   96: aload_3
    //   97: aload_1
    //   98: getfield featureId : I
    //   101: aload_1
    //   102: getfield menu : Landroid/support/v7/view/menu/MenuBuilder;
    //   105: invokeinterface onMenuOpened : (ILandroid/view/Menu;)Z
    //   110: ifne -> 120
    //   113: aload_0
    //   114: aload_1
    //   115: iconst_1
    //   116: invokevirtual closePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Z)V
    //   119: return
    //   120: aload_0
    //   121: getfield mContext : Landroid/content/Context;
    //   124: ldc_w 'window'
    //   127: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   130: checkcast android/view/WindowManager
    //   133: astore #6
    //   135: aload #6
    //   137: ifnonnull -> 141
    //   140: return
    //   141: aload_0
    //   142: aload_1
    //   143: aload_2
    //   144: invokespecial preparePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   147: ifne -> 151
    //   150: return
    //   151: aload_1
    //   152: getfield decorView : Landroid/view/ViewGroup;
    //   155: astore_2
    //   156: aload_2
    //   157: ifnull -> 202
    //   160: aload_1
    //   161: getfield refreshDecorView : Z
    //   164: ifeq -> 170
    //   167: goto -> 202
    //   170: aload_1
    //   171: getfield createdPanelView : Landroid/view/View;
    //   174: astore_2
    //   175: aload_2
    //   176: ifnull -> 362
    //   179: aload_2
    //   180: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   183: astore_2
    //   184: aload_2
    //   185: ifnull -> 362
    //   188: aload_2
    //   189: getfield width : I
    //   192: iconst_m1
    //   193: if_icmpne -> 362
    //   196: iconst_m1
    //   197: istore #4
    //   199: goto -> 366
    //   202: aload_2
    //   203: ifnonnull -> 222
    //   206: aload_0
    //   207: aload_1
    //   208: invokespecial initializePanelDecor : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;)Z
    //   211: ifeq -> 221
    //   214: aload_1
    //   215: getfield decorView : Landroid/view/ViewGroup;
    //   218: ifnonnull -> 243
    //   221: return
    //   222: aload_1
    //   223: getfield refreshDecorView : Z
    //   226: ifeq -> 243
    //   229: aload_2
    //   230: invokevirtual getChildCount : ()I
    //   233: ifle -> 243
    //   236: aload_1
    //   237: getfield decorView : Landroid/view/ViewGroup;
    //   240: invokevirtual removeAllViews : ()V
    //   243: aload_0
    //   244: aload_1
    //   245: invokespecial initializePanelContent : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;)Z
    //   248: ifeq -> 432
    //   251: aload_1
    //   252: invokevirtual hasPanelItems : ()Z
    //   255: ifne -> 261
    //   258: goto -> 432
    //   261: aload_1
    //   262: getfield shownPanelView : Landroid/view/View;
    //   265: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   268: astore_3
    //   269: aload_3
    //   270: astore_2
    //   271: aload_3
    //   272: ifnonnull -> 287
    //   275: new android/view/ViewGroup$LayoutParams
    //   278: dup
    //   279: bipush #-2
    //   281: bipush #-2
    //   283: invokespecial <init> : (II)V
    //   286: astore_2
    //   287: aload_1
    //   288: getfield background : I
    //   291: istore #4
    //   293: aload_1
    //   294: getfield decorView : Landroid/view/ViewGroup;
    //   297: iload #4
    //   299: invokevirtual setBackgroundResource : (I)V
    //   302: aload_1
    //   303: getfield shownPanelView : Landroid/view/View;
    //   306: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   309: astore_3
    //   310: aload_3
    //   311: ifnull -> 332
    //   314: aload_3
    //   315: instanceof android/view/ViewGroup
    //   318: ifeq -> 332
    //   321: aload_3
    //   322: checkcast android/view/ViewGroup
    //   325: aload_1
    //   326: getfield shownPanelView : Landroid/view/View;
    //   329: invokevirtual removeView : (Landroid/view/View;)V
    //   332: aload_1
    //   333: getfield decorView : Landroid/view/ViewGroup;
    //   336: aload_1
    //   337: getfield shownPanelView : Landroid/view/View;
    //   340: aload_2
    //   341: invokevirtual addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   344: aload_1
    //   345: getfield shownPanelView : Landroid/view/View;
    //   348: invokevirtual hasFocus : ()Z
    //   351: ifne -> 362
    //   354: aload_1
    //   355: getfield shownPanelView : Landroid/view/View;
    //   358: invokevirtual requestFocus : ()Z
    //   361: pop
    //   362: bipush #-2
    //   364: istore #4
    //   366: aload_1
    //   367: iconst_0
    //   368: putfield isHandled : Z
    //   371: new android/view/WindowManager$LayoutParams
    //   374: dup
    //   375: iload #4
    //   377: bipush #-2
    //   379: aload_1
    //   380: getfield x : I
    //   383: aload_1
    //   384: getfield y : I
    //   387: sipush #1002
    //   390: ldc_w 8519680
    //   393: bipush #-3
    //   395: invokespecial <init> : (IIIIIII)V
    //   398: astore_2
    //   399: aload_2
    //   400: aload_1
    //   401: getfield gravity : I
    //   404: putfield gravity : I
    //   407: aload_2
    //   408: aload_1
    //   409: getfield windowAnimations : I
    //   412: putfield windowAnimations : I
    //   415: aload #6
    //   417: aload_1
    //   418: getfield decorView : Landroid/view/ViewGroup;
    //   421: aload_2
    //   422: invokeinterface addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   427: aload_1
    //   428: iconst_1
    //   429: putfield isOpen : Z
    //   432: return
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual isSystem : ()Z
    //   4: istore #5
    //   6: iconst_0
    //   7: istore #6
    //   9: iload #5
    //   11: ifeq -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: getfield isPrepared : Z
    //   20: ifne -> 36
    //   23: iload #6
    //   25: istore #5
    //   27: aload_0
    //   28: aload_1
    //   29: aload_3
    //   30: invokespecial preparePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   33: ifeq -> 62
    //   36: aload_1
    //   37: getfield menu : Landroid/support/v7/view/menu/MenuBuilder;
    //   40: astore #7
    //   42: iload #6
    //   44: istore #5
    //   46: aload #7
    //   48: ifnull -> 62
    //   51: aload #7
    //   53: iload_2
    //   54: aload_3
    //   55: iload #4
    //   57: invokevirtual performShortcut : (ILandroid/view/KeyEvent;I)Z
    //   60: istore #5
    //   62: iload #5
    //   64: ifeq -> 87
    //   67: iload #4
    //   69: iconst_1
    //   70: iand
    //   71: ifne -> 87
    //   74: aload_0
    //   75: getfield mDecorContentParent : Landroid/support/v7/widget/DecorContentParent;
    //   78: ifnonnull -> 87
    //   81: aload_0
    //   82: aload_1
    //   83: iconst_1
    //   84: invokevirtual closePanel : (Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;Z)V
    //   87: iload #5
    //   89: ireturn
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    DecorContentParent decorContentParent;
    if (isDestroyed())
      return false; 
    if (paramPanelFeatureState.isPrepared)
      return true; 
    PanelFeatureState panelFeatureState = this.mPreparedPanel;
    if (panelFeatureState != null && panelFeatureState != paramPanelFeatureState)
      closePanel(panelFeatureState, false); 
    Window.Callback callback = getWindowCallback();
    if (callback != null)
      paramPanelFeatureState.createdPanelView = callback.onCreatePanelView(paramPanelFeatureState.featureId); 
    int i = paramPanelFeatureState.featureId;
    if (i == 0 || i == 108) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0) {
      DecorContentParent decorContentParent1 = this.mDecorContentParent;
      if (decorContentParent1 != null)
        decorContentParent1.setMenuPrepared(); 
    } 
    if (paramPanelFeatureState.createdPanelView == null && (i == 0 || !(peekSupportActionBar() instanceof ToolbarActionBar))) {
      DecorContentParent decorContentParent1;
      boolean bool;
      MenuBuilder menuBuilder = paramPanelFeatureState.menu;
      if (menuBuilder == null || paramPanelFeatureState.refreshMenuContent) {
        if (menuBuilder == null && (!initializePanelMenu(paramPanelFeatureState) || paramPanelFeatureState.menu == null))
          return false; 
        if (i != 0 && this.mDecorContentParent != null) {
          if (this.mActionMenuPresenterCallback == null)
            this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(); 
          this.mDecorContentParent.setMenu((Menu)paramPanelFeatureState.menu, this.mActionMenuPresenterCallback);
        } 
        paramPanelFeatureState.menu.stopDispatchingItemsChanged();
        if (!callback.onCreatePanelMenu(paramPanelFeatureState.featureId, (Menu)paramPanelFeatureState.menu)) {
          paramPanelFeatureState.setMenu(null);
          if (i != 0) {
            decorContentParent = this.mDecorContentParent;
            if (decorContentParent != null)
              decorContentParent.setMenu(null, this.mActionMenuPresenterCallback); 
          } 
          return false;
        } 
        ((PanelFeatureState)decorContentParent).refreshMenuContent = false;
      } 
      ((PanelFeatureState)decorContentParent).menu.stopDispatchingItemsChanged();
      Bundle bundle = ((PanelFeatureState)decorContentParent).frozenActionViewState;
      if (bundle != null) {
        ((PanelFeatureState)decorContentParent).menu.restoreActionViewStates(bundle);
        ((PanelFeatureState)decorContentParent).frozenActionViewState = null;
      } 
      if (!callback.onPreparePanel(0, ((PanelFeatureState)decorContentParent).createdPanelView, (Menu)((PanelFeatureState)decorContentParent).menu)) {
        if (i != 0) {
          decorContentParent1 = this.mDecorContentParent;
          if (decorContentParent1 != null)
            decorContentParent1.setMenu(null, this.mActionMenuPresenterCallback); 
        } 
        ((PanelFeatureState)decorContentParent).menu.startDispatchingItemsChanged();
        return false;
      } 
      if (decorContentParent1 != null) {
        i = decorContentParent1.getDeviceId();
      } else {
        i = -1;
      } 
      if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
        bool = true;
      } else {
        bool = false;
      } 
      ((PanelFeatureState)decorContentParent).qwertyMode = bool;
      ((PanelFeatureState)decorContentParent).menu.setQwertyMode(bool);
      ((PanelFeatureState)decorContentParent).menu.startDispatchingItemsChanged();
    } 
    ((PanelFeatureState)decorContentParent).isPrepared = true;
    ((PanelFeatureState)decorContentParent).isHandled = false;
    this.mPreparedPanel = (PanelFeatureState)decorContentParent;
    return true;
  }
  
  private void reopenMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null && decorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
      Window.Callback callback = getWindowCallback();
      if (!this.mDecorContentParent.isOverflowMenuShowing() || !paramBoolean) {
        if (callback != null && !isDestroyed()) {
          if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 0x1) != 0) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuRunnable.run();
          } 
          PanelFeatureState panelFeatureState1 = getPanelState(0, true);
          MenuBuilder menuBuilder = panelFeatureState1.menu;
          if (menuBuilder != null && !panelFeatureState1.refreshMenuContent && callback.onPreparePanel(0, panelFeatureState1.createdPanelView, (Menu)menuBuilder)) {
            callback.onMenuOpened(108, (Menu)panelFeatureState1.menu);
            this.mDecorContentParent.showOverflowMenu();
          } 
        } 
        return;
      } 
      this.mDecorContentParent.hideOverflowMenu();
      if (!isDestroyed())
        callback.onPanelClosed(108, (Menu)(getPanelState(0, true)).menu); 
      return;
    } 
    PanelFeatureState panelFeatureState = getPanelState(0, true);
    panelFeatureState.refreshDecorView = true;
    closePanel(panelFeatureState, false);
    openPanel(panelFeatureState, null);
  }
  
  private int sanitizeWindowFeatureId(int paramInt) {
    if (paramInt == 8) {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
      return 108;
    } 
    int i = paramInt;
    if (paramInt == 9) {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
      i = 109;
    } 
    return i;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent) {
    if (paramViewParent == null)
      return false; 
    View view = this.mWindow.getDecorView();
    while (true) {
      if (paramViewParent == null)
        return true; 
      if (paramViewParent == view || !(paramViewParent instanceof View) || ViewCompat.isAttachedToWindow((View)paramViewParent))
        break; 
      paramViewParent = paramViewParent.getParent();
    } 
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled() {
    if (!this.mSubDecorInstalled)
      return; 
    throw new AndroidRuntimeException("Window feature must be requested before adding content");
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  View callActivityOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    Window.Callback callback = this.mOriginalWindowCallback;
    if (callback instanceof LayoutInflater.Factory) {
      View view = ((LayoutInflater.Factory)callback).onCreateView(paramString, paramContext, paramAttributeSet);
      if (view != null)
        return view; 
    } 
    return null;
  }
  
  void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu) {
    MenuBuilder menuBuilder;
    PanelFeatureState panelFeatureState = paramPanelFeatureState;
    Menu menu = paramMenu;
    if (paramMenu == null) {
      PanelFeatureState panelFeatureState1 = paramPanelFeatureState;
      if (paramPanelFeatureState == null) {
        panelFeatureState1 = paramPanelFeatureState;
        if (paramInt >= 0) {
          PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
          panelFeatureState1 = paramPanelFeatureState;
          if (paramInt < arrayOfPanelFeatureState.length)
            panelFeatureState1 = arrayOfPanelFeatureState[paramInt]; 
        } 
      } 
      panelFeatureState = panelFeatureState1;
      menu = paramMenu;
      if (panelFeatureState1 != null) {
        menuBuilder = panelFeatureState1.menu;
        panelFeatureState = panelFeatureState1;
      } 
    } 
    if (panelFeatureState != null && !panelFeatureState.isOpen)
      return; 
    if (!isDestroyed())
      this.mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)menuBuilder); 
  }
  
  void checkCloseActionMenu(MenuBuilder paramMenuBuilder) {
    if (this.mClosingActionMenu)
      return; 
    this.mClosingActionMenu = true;
    this.mDecorContentParent.dismissPopups();
    Window.Callback callback = getWindowCallback();
    if (callback != null && !isDestroyed())
      callback.onPanelClosed(108, (Menu)paramMenuBuilder); 
    this.mClosingActionMenu = false;
  }
  
  void closePanel(int paramInt) {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean) {
    if (paramBoolean && paramPanelFeatureState.featureId == 0) {
      DecorContentParent decorContentParent = this.mDecorContentParent;
      if (decorContentParent != null && decorContentParent.isOverflowMenuShowing()) {
        checkCloseActionMenu(paramPanelFeatureState.menu);
        return;
      } 
    } 
    WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
    if (windowManager != null && paramPanelFeatureState.isOpen) {
      ViewGroup viewGroup = paramPanelFeatureState.decorView;
      if (viewGroup != null) {
        windowManager.removeView((View)viewGroup);
        if (paramBoolean)
          callOnPanelClosed(paramPanelFeatureState.featureId, paramPanelFeatureState, null); 
      } 
    } 
    paramPanelFeatureState.isPrepared = false;
    paramPanelFeatureState.isHandled = false;
    paramPanelFeatureState.isOpen = false;
    paramPanelFeatureState.shownPanelView = null;
    paramPanelFeatureState.refreshDecorView = true;
    if (this.mPreparedPanel == paramPanelFeatureState)
      this.mPreparedPanel = null; 
  }
  
  public View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet) {
    if (this.mAppCompatViewInflater == null)
      this.mAppCompatViewInflater = new AppCompatViewInflater(); 
    boolean bool1 = IS_PRE_LOLLIPOP;
    boolean bool2 = false;
    if (bool1) {
      if (paramAttributeSet instanceof XmlPullParser) {
        if (((XmlPullParser)paramAttributeSet).getDepth() > 1)
          bool2 = true; 
      } else {
        bool2 = shouldInheritContext((ViewParent)paramView);
      } 
    } else {
      bool2 = false;
    } 
    return this.mAppCompatViewInflater.createView(paramView, paramString, paramContext, paramAttributeSet, bool2, bool1, true, VectorEnabledTintResources.shouldBeUsed());
  }
  
  void dismissPopups() {
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null)
      decorContentParent.dismissPopups(); 
    if (this.mActionModePopup != null) {
      this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
      if (this.mActionModePopup.isShowing())
        try {
          this.mActionModePopup.dismiss();
        } catch (IllegalArgumentException illegalArgumentException) {} 
      this.mActionModePopup = null;
    } 
    endOnGoingFadeAnimation();
    PanelFeatureState panelFeatureState = getPanelState(0, false);
    if (panelFeatureState != null) {
      MenuBuilder menuBuilder = panelFeatureState.menu;
      if (menuBuilder != null)
        menuBuilder.close(); 
    } 
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    boolean bool1;
    int i = paramKeyEvent.getKeyCode();
    boolean bool = true;
    if (i == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent))
      return true; 
    i = paramKeyEvent.getKeyCode();
    if (paramKeyEvent.getAction() != 0)
      bool = false; 
    if (bool) {
      bool1 = onKeyDown(i, paramKeyEvent);
    } else {
      bool1 = onKeyUp(i, paramKeyEvent);
    } 
    return bool1;
  }
  
  void doInvalidatePanelMenu(int paramInt) {
    PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
    if (panelFeatureState.menu != null) {
      Bundle bundle = new Bundle();
      panelFeatureState.menu.saveActionViewStates(bundle);
      if (bundle.size() > 0)
        panelFeatureState.frozenActionViewState = bundle; 
      panelFeatureState.menu.stopDispatchingItemsChanged();
      panelFeatureState.menu.clear();
    } 
    panelFeatureState.refreshMenuContent = true;
    panelFeatureState.refreshDecorView = true;
    if ((paramInt == 108 || paramInt == 0) && this.mDecorContentParent != null) {
      PanelFeatureState panelFeatureState1 = getPanelState(0, false);
      if (panelFeatureState1 != null) {
        panelFeatureState1.isPrepared = false;
        preparePanel(panelFeatureState1, null);
      } 
    } 
  }
  
  void endOnGoingFadeAnimation() {
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mFadeAnim;
    if (viewPropertyAnimatorCompat != null)
      viewPropertyAnimatorCompat.cancel(); 
  }
  
  PanelFeatureState findMenuPanel(Menu paramMenu) {
    byte b2;
    PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
    byte b1 = 0;
    if (arrayOfPanelFeatureState != null) {
      b2 = arrayOfPanelFeatureState.length;
    } else {
      b2 = 0;
    } 
    while (b1 < b2) {
      PanelFeatureState panelFeatureState = arrayOfPanelFeatureState[b1];
      if (panelFeatureState != null && panelFeatureState.menu == paramMenu)
        return panelFeatureState; 
      b1++;
    } 
    return null;
  }
  
  @Nullable
  public <T extends View> T findViewById(@IdRes int paramInt) {
    ensureSubDecor();
    return (T)this.mWindow.findViewById(paramInt);
  }
  
  protected PanelFeatureState getPanelState(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPanels : [Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnull -> 18
    //   9: aload_3
    //   10: astore #4
    //   12: aload_3
    //   13: arraylength
    //   14: iload_1
    //   15: if_icmpgt -> 46
    //   18: iload_1
    //   19: iconst_1
    //   20: iadd
    //   21: anewarray android/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState
    //   24: astore #4
    //   26: aload_3
    //   27: ifnull -> 40
    //   30: aload_3
    //   31: iconst_0
    //   32: aload #4
    //   34: iconst_0
    //   35: aload_3
    //   36: arraylength
    //   37: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   40: aload_0
    //   41: aload #4
    //   43: putfield mPanels : [Landroid/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState;
    //   46: aload #4
    //   48: iload_1
    //   49: aaload
    //   50: astore #5
    //   52: aload #5
    //   54: astore_3
    //   55: aload #5
    //   57: ifnonnull -> 74
    //   60: new android/support/v7/app/AppCompatDelegateImplV9$PanelFeatureState
    //   63: dup
    //   64: iload_1
    //   65: invokespecial <init> : (I)V
    //   68: astore_3
    //   69: aload #4
    //   71: iload_1
    //   72: aload_3
    //   73: aastore
    //   74: aload_3
    //   75: areturn
  }
  
  ViewGroup getSubDecor() {
    return this.mSubDecor;
  }
  
  public boolean hasWindowFeature(int paramInt) {
    paramInt = sanitizeWindowFeatureId(paramInt);
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 5) ? ((paramInt != 10) ? ((paramInt != 108) ? ((paramInt != 109) ? false : this.mOverlayActionBar) : this.mHasActionBar) : this.mOverlayActionMode) : this.mFeatureIndeterminateProgress) : this.mFeatureProgress) : this.mWindowNoTitle;
  }
  
  public void initWindowDecorActionBar() {
    ensureSubDecor();
    if (this.mHasActionBar && this.mActionBar == null) {
      Window.Callback callback = this.mOriginalWindowCallback;
      if (callback instanceof Activity) {
        this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
      } else if (callback instanceof Dialog) {
        this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
      } 
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null)
        actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp); 
    } 
  }
  
  public void installViewFactory() {
    LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
    if (layoutInflater.getFactory() == null) {
      LayoutInflaterCompat.setFactory2(layoutInflater, this);
    } else if (!(layoutInflater.getFactory2() instanceof AppCompatDelegateImplV9)) {
      Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
    } 
  }
  
  public void invalidateOptionsMenu() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && actionBar.invalidateOptionsMenu())
      return; 
    invalidatePanelMenu(0);
  }
  
  boolean onBackPressed() {
    ActionMode actionMode = this.mActionMode;
    if (actionMode != null) {
      actionMode.finish();
      return true;
    } 
    ActionBar actionBar = getSupportActionBar();
    return (actionBar != null && actionBar.collapseActionView());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (this.mHasActionBar && this.mSubDecorInstalled) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.onConfigurationChanged(paramConfiguration); 
    } 
    AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
    applyDayNight();
  }
  
  public void onCreate(Bundle paramBundle) {
    Window.Callback callback = this.mOriginalWindowCallback;
    if (callback instanceof Activity && NavUtils.getParentActivityName((Activity)callback) != null) {
      ActionBar actionBar = peekSupportActionBar();
      if (actionBar == null) {
        this.mEnableDefaultActionBarUp = true;
      } else {
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
      } 
    } 
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    View view = callActivityOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    return (view != null) ? view : createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void onDestroy() {
    if (this.mInvalidatePanelMenuPosted)
      this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable); 
    super.onDestroy();
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.onDestroy(); 
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = true;
    if (paramInt != 4) {
      if (paramInt == 82) {
        onKeyDownPanel(0, paramKeyEvent);
        return true;
      } 
    } else {
      if ((paramKeyEvent.getFlags() & 0x80) == 0)
        bool = false; 
      this.mLongPressBackDown = bool;
    } 
    return false;
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    PanelFeatureState panelFeatureState1;
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && actionBar.onKeyShortcut(paramInt, paramKeyEvent))
      return true; 
    PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
    if (panelFeatureState2 != null && performPanelShortcut(panelFeatureState2, paramKeyEvent.getKeyCode(), paramKeyEvent, 1)) {
      panelFeatureState1 = this.mPreparedPanel;
      if (panelFeatureState1 != null)
        panelFeatureState1.isHandled = true; 
      return true;
    } 
    if (this.mPreparedPanel == null) {
      panelFeatureState2 = getPanelState(0, true);
      preparePanel(panelFeatureState2, (KeyEvent)panelFeatureState1);
      boolean bool = performPanelShortcut(panelFeatureState2, panelFeatureState1.getKeyCode(), (KeyEvent)panelFeatureState1, 1);
      panelFeatureState2.isPrepared = false;
      if (bool)
        return true; 
    } 
    return false;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt != 4) {
      if (paramInt == 82) {
        onKeyUpPanel(0, paramKeyEvent);
        return true;
      } 
    } else {
      boolean bool = this.mLongPressBackDown;
      this.mLongPressBackDown = false;
      PanelFeatureState panelFeatureState = getPanelState(0, false);
      if (panelFeatureState != null && panelFeatureState.isOpen) {
        if (!bool)
          closePanel(panelFeatureState, true); 
        return true;
      } 
      if (onBackPressed())
        return true; 
    } 
    return false;
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem) {
    Window.Callback callback = getWindowCallback();
    if (callback != null && !isDestroyed()) {
      PanelFeatureState panelFeatureState = findMenuPanel((Menu)paramMenuBuilder.getRootMenu());
      if (panelFeatureState != null)
        return callback.onMenuItemSelected(panelFeatureState.featureId, paramMenuItem); 
    } 
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {
    reopenMenu(paramMenuBuilder, true);
  }
  
  boolean onMenuOpened(int paramInt, Menu paramMenu) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(true); 
      return true;
    } 
    return false;
  }
  
  void onPanelClosed(int paramInt, Menu paramMenu) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(false); 
    } else if (paramInt == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (panelFeatureState.isOpen)
        closePanel(panelFeatureState, false); 
    } 
  }
  
  public void onPostCreate(Bundle paramBundle) {
    ensureSubDecor();
  }
  
  public void onPostResume() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(true); 
  }
  
  public void onStop() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(false); 
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  void onTitleChanged(CharSequence paramCharSequence) {
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null) {
      decorContentParent.setWindowTitle(paramCharSequence);
    } else if (peekSupportActionBar() != null) {
      peekSupportActionBar().setWindowTitle(paramCharSequence);
    } else {
      TextView textView = this.mTitleView;
      if (textView != null)
        textView.setText(paramCharSequence); 
    } 
  }
  
  public boolean requestWindowFeature(int paramInt) {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if (this.mWindowNoTitle && paramInt == 108)
      return false; 
    if (this.mHasActionBar && paramInt == 1)
      this.mHasActionBar = false; 
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 5) {
          if (paramInt != 10) {
            if (paramInt != 108) {
              if (paramInt != 109)
                return this.mWindow.requestFeature(paramInt); 
              throwFeatureRequestIfSubDecorInstalled();
              this.mOverlayActionBar = true;
              return true;
            } 
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
          } 
          throwFeatureRequestIfSubDecorInstalled();
          this.mOverlayActionMode = true;
          return true;
        } 
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureIndeterminateProgress = true;
        return true;
      } 
      throwFeatureRequestIfSubDecorInstalled();
      this.mFeatureProgress = true;
      return true;
    } 
    throwFeatureRequestIfSubDecorInstalled();
    this.mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    LayoutInflater.from(this.mContext).inflate(paramInt, viewGroup);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setSupportActionBar(Toolbar paramToolbar) {
    if (!(this.mOriginalWindowCallback instanceof Activity))
      return; 
    ActionBar actionBar = getSupportActionBar();
    if (!(actionBar instanceof WindowDecorActionBar)) {
      this.mMenuInflater = null;
      if (actionBar != null)
        actionBar.onDestroy(); 
      if (paramToolbar != null) {
        ToolbarActionBar toolbarActionBar = new ToolbarActionBar(paramToolbar, ((Activity)this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
        this.mActionBar = toolbarActionBar;
        this.mWindow.setCallback(toolbarActionBar.getWrappedWindowCallback());
      } else {
        this.mActionBar = null;
        this.mWindow.setCallback(this.mAppCompatWindowCallback);
      } 
      invalidateOptionsMenu();
      return;
    } 
    throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
  }
  
  final boolean shouldAnimateActionModeView() {
    if (this.mSubDecorInstalled) {
      ViewGroup viewGroup = this.mSubDecor;
      if (viewGroup != null && ViewCompat.isLaidOut((View)viewGroup))
        return true; 
    } 
    return false;
  }
  
  public ActionMode startSupportActionMode(@NonNull ActionMode.Callback paramCallback) {
    if (paramCallback != null) {
      ActionMode actionMode = this.mActionMode;
      if (actionMode != null)
        actionMode.finish(); 
      paramCallback = new ActionModeCallbackWrapperV9(paramCallback);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        ActionMode actionMode1 = actionBar.startActionMode(paramCallback);
        this.mActionMode = actionMode1;
        if (actionMode1 != null) {
          AppCompatCallback appCompatCallback = this.mAppCompatCallback;
          if (appCompatCallback != null)
            appCompatCallback.onSupportActionModeStarted(actionMode1); 
        } 
      } 
      if (this.mActionMode == null)
        this.mActionMode = startSupportActionModeFromWindow(paramCallback); 
      return this.mActionMode;
    } 
    throw new IllegalArgumentException("ActionMode callback can not be null.");
  }
  
  ActionMode startSupportActionModeFromWindow(@NonNull ActionMode.Callback paramCallback) {
    endOnGoingFadeAnimation();
    ActionMode actionMode = this.mActionMode;
    if (actionMode != null)
      actionMode.finish(); 
    ActionMode.Callback callback = paramCallback;
    if (!(paramCallback instanceof ActionModeCallbackWrapperV9))
      callback = new ActionModeCallbackWrapperV9(paramCallback); 
    if (this.mAppCompatCallback != null && !isDestroyed()) {
      try {
        ActionMode actionMode2 = this.mAppCompatCallback.onWindowStartingSupportActionMode(callback);
      } catch (AbstractMethodError abstractMethodError) {
        abstractMethodError = null;
      } 
      if (abstractMethodError != null) {
        this.mActionMode = (ActionMode)abstractMethodError;
      } else {
        ActionBarContextView actionBarContextView = this.mActionModeView;
        boolean bool = true;
        if (actionBarContextView == null)
          if (this.mIsFloating) {
            Context context;
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = this.mContext.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
              Resources.Theme theme1 = this.mContext.getResources().newTheme();
              theme1.setTo(theme);
              theme1.applyStyle(typedValue.resourceId, true);
              ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, 0);
              contextThemeWrapper.getTheme().setTo(theme1);
            } else {
              context = this.mContext;
            } 
            this.mActionModeView = new ActionBarContextView(context);
            PopupWindow popupWindow = new PopupWindow(context, null, R.attr.actionModePopupWindowStyle);
            this.mActionModePopup = popupWindow;
            PopupWindowCompat.setWindowLayoutType(popupWindow, 2);
            this.mActionModePopup.setContentView((View)this.mActionModeView);
            this.mActionModePopup.setWidth(-1);
            context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
            int i = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            this.mActionModeView.setContentHeight(i);
            this.mActionModePopup.setHeight(-2);
            this.mShowActionModePopup = new Runnable() {
                public void run() {
                  AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
                  appCompatDelegateImplV9.mActionModePopup.showAtLocation((View)appCompatDelegateImplV9.mActionModeView, 55, 0, 0);
                  AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                  if (AppCompatDelegateImplV9.this.shouldAnimateActionModeView()) {
                    AppCompatDelegateImplV9.this.mActionModeView.setAlpha(0.0F);
                    appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
                    appCompatDelegateImplV9.mFadeAnim = ViewCompat.animate((View)appCompatDelegateImplV9.mActionModeView).alpha(1.0F);
                    AppCompatDelegateImplV9.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
                          public void onAnimationEnd(View param2View) {
                            AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                            AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                            AppCompatDelegateImplV9.this.mFadeAnim = null;
                          }
                          
                          public void onAnimationStart(View param2View) {
                            AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                          }
                        });
                  } else {
                    AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                    AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                  } 
                }
              };
          } else {
            ViewStubCompat viewStubCompat = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
            if (viewStubCompat != null) {
              viewStubCompat.setLayoutInflater(LayoutInflater.from(getActionBarThemedContext()));
              this.mActionModeView = (ActionBarContextView)viewStubCompat.inflate();
            } 
          }  
        if (this.mActionModeView != null) {
          endOnGoingFadeAnimation();
          this.mActionModeView.killMode();
          Context context = this.mActionModeView.getContext();
          ActionBarContextView actionBarContextView1 = this.mActionModeView;
          if (this.mActionModePopup != null)
            bool = false; 
          StandaloneActionMode standaloneActionMode = new StandaloneActionMode(context, actionBarContextView1, callback, bool);
          if (callback.onCreateActionMode((ActionMode)standaloneActionMode, standaloneActionMode.getMenu())) {
            standaloneActionMode.invalidate();
            this.mActionModeView.initForMode((ActionMode)standaloneActionMode);
            this.mActionMode = (ActionMode)standaloneActionMode;
            if (shouldAnimateActionModeView()) {
              this.mActionModeView.setAlpha(0.0F);
              ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mActionModeView).alpha(1.0F);
              this.mFadeAnim = viewPropertyAnimatorCompat;
              viewPropertyAnimatorCompat.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View param1View) {
                      AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                      AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                      AppCompatDelegateImplV9.this.mFadeAnim = null;
                    }
                    
                    public void onAnimationStart(View param1View) {
                      AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                      AppCompatDelegateImplV9.this.mActionModeView.sendAccessibilityEvent(32);
                      if (AppCompatDelegateImplV9.this.mActionModeView.getParent() instanceof View)
                        ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent()); 
                    }
                  });
            } else {
              this.mActionModeView.setAlpha(1.0F);
              this.mActionModeView.setVisibility(0);
              this.mActionModeView.sendAccessibilityEvent(32);
              if (this.mActionModeView.getParent() instanceof View)
                ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent()); 
            } 
            if (this.mActionModePopup != null)
              this.mWindow.getDecorView().post(this.mShowActionModePopup); 
          } else {
            this.mActionMode = null;
          } 
        } 
      } 
      ActionMode actionMode1 = this.mActionMode;
      if (actionMode1 != null) {
        AppCompatCallback appCompatCallback = this.mAppCompatCallback;
        if (appCompatCallback != null)
          appCompatCallback.onSupportActionModeStarted(actionMode1); 
      } 
      return this.mActionMode;
    } 
    paramCallback = null;
  }
  
  int updateStatusGuard(int paramInt) {
    int i;
    boolean bool2;
    ActionBarContextView actionBarContextView = this.mActionModeView;
    boolean bool1 = false;
    if (actionBarContextView != null && actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
      boolean bool3;
      ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
      boolean bool = this.mActionModeView.isShown();
      i = 1;
      int j = 1;
      if (bool) {
        if (this.mTempRect1 == null) {
          this.mTempRect1 = new Rect();
          this.mTempRect2 = new Rect();
        } 
        Rect rect1 = this.mTempRect1;
        Rect rect2 = this.mTempRect2;
        rect1.set(0, paramInt, 0, 0);
        ViewUtils.computeFitSystemWindows((View)this.mSubDecor, rect1, rect2);
        if (rect2.top == 0) {
          bool3 = paramInt;
        } else {
          bool3 = false;
        } 
        if (marginLayoutParams.topMargin != bool3) {
          marginLayoutParams.topMargin = paramInt;
          View view1 = this.mStatusGuard;
          if (view1 == null) {
            view1 = new View(this.mContext);
            this.mStatusGuard = view1;
            view1.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
            this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
          } else {
            ViewGroup.LayoutParams layoutParams = view1.getLayoutParams();
            if (layoutParams.height != paramInt) {
              layoutParams.height = paramInt;
              this.mStatusGuard.setLayoutParams(layoutParams);
            } 
          } 
          bool3 = true;
        } else {
          bool3 = false;
        } 
        if (this.mStatusGuard == null)
          j = 0; 
        i = paramInt;
        if (!this.mOverlayActionMode) {
          i = paramInt;
          if (j)
            i = 0; 
        } 
        paramInt = bool3;
        bool3 = j;
        j = paramInt;
        paramInt = i;
      } else if (marginLayoutParams.topMargin != 0) {
        marginLayoutParams.topMargin = 0;
        bool3 = false;
        j = i;
      } else {
        bool3 = false;
        j = 0;
      } 
      bool2 = bool3;
      i = paramInt;
      if (j != 0) {
        this.mActionModeView.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
        bool2 = bool3;
        i = paramInt;
      } 
    } else {
      bool2 = false;
      i = paramInt;
    } 
    View view = this.mStatusGuard;
    if (view != null) {
      if (bool2) {
        paramInt = bool1;
      } else {
        paramInt = 8;
      } 
      view.setVisibility(paramInt);
    } 
    return i;
  }
  
  private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      AppCompatDelegateImplV9.this.checkCloseActionMenu(param1MenuBuilder);
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      Window.Callback callback = AppCompatDelegateImplV9.this.getWindowCallback();
      if (callback != null)
        callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV9(ActionMode.Callback param1Callback) {
      this.mWrapped = param1Callback;
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      return this.mWrapped.onActionItemClicked(param1ActionMode, param1MenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onCreateActionMode(param1ActionMode, param1Menu);
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      this.mWrapped.onDestroyActionMode(param1ActionMode);
      AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
      if (appCompatDelegateImplV9.mActionModePopup != null)
        appCompatDelegateImplV9.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV9.this.mShowActionModePopup); 
      appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
      if (appCompatDelegateImplV9.mActionModeView != null) {
        appCompatDelegateImplV9.endOnGoingFadeAnimation();
        appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
        appCompatDelegateImplV9.mFadeAnim = ViewCompat.animate((View)appCompatDelegateImplV9.mActionModeView).alpha(0.0F);
        AppCompatDelegateImplV9.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
              public void onAnimationEnd(View param2View) {
                AppCompatDelegateImplV9.this.mActionModeView.setVisibility(8);
                AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
                PopupWindow popupWindow = appCompatDelegateImplV9.mActionModePopup;
                if (popupWindow != null) {
                  popupWindow.dismiss();
                } else if (appCompatDelegateImplV9.mActionModeView.getParent() instanceof View) {
                  ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
                } 
                AppCompatDelegateImplV9.this.mActionModeView.removeAllViews();
                AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                AppCompatDelegateImplV9.this.mFadeAnim = null;
              }
            });
      } 
      appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
      AppCompatCallback appCompatCallback = appCompatDelegateImplV9.mAppCompatCallback;
      if (appCompatCallback != null)
        appCompatCallback.onSupportActionModeFinished(appCompatDelegateImplV9.mActionMode); 
      AppCompatDelegateImplV9.this.mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onPrepareActionMode(param1ActionMode, param1Menu);
    }
  }
  
  class null extends ViewPropertyAnimatorListenerAdapter {
    public void onAnimationEnd(View param1View) {
      AppCompatDelegateImplV9.this.mActionModeView.setVisibility(8);
      AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
      PopupWindow popupWindow = appCompatDelegateImplV9.mActionModePopup;
      if (popupWindow != null) {
        popupWindow.dismiss();
      } else if (appCompatDelegateImplV9.mActionModeView.getParent() instanceof View) {
        ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
      } 
      AppCompatDelegateImplV9.this.mActionModeView.removeAllViews();
      AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
      AppCompatDelegateImplV9.this.mFadeAnim = null;
    }
  }
  
  private class ListMenuDecorView extends ContentFrameLayout {
    public ListMenuDecorView(Context param1Context) {
      super(param1Context);
    }
    
    private boolean isOutOfBounds(int param1Int1, int param1Int2) {
      return (param1Int1 < -5 || param1Int2 < -5 || param1Int1 > getWidth() + 5 || param1Int2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent param1KeyEvent) {
      return (AppCompatDelegateImplV9.this.dispatchKeyEvent(param1KeyEvent) || super.dispatchKeyEvent(param1KeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent param1MotionEvent) {
      if (param1MotionEvent.getAction() == 0 && isOutOfBounds((int)param1MotionEvent.getX(), (int)param1MotionEvent.getY())) {
        AppCompatDelegateImplV9.this.closePanel(0);
        return true;
      } 
      return super.onInterceptTouchEvent(param1MotionEvent);
    }
    
    public void setBackgroundResource(int param1Int) {
      setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), param1Int));
    }
  }
  
  protected static final class PanelFeatureState {
    int background;
    
    View createdPanelView;
    
    ViewGroup decorView;
    
    int featureId;
    
    Bundle frozenActionViewState;
    
    Bundle frozenMenuState;
    
    int gravity;
    
    boolean isHandled;
    
    boolean isOpen;
    
    boolean isPrepared;
    
    ListMenuPresenter listMenuPresenter;
    
    Context listPresenterContext;
    
    MenuBuilder menu;
    
    public boolean qwertyMode;
    
    boolean refreshDecorView;
    
    boolean refreshMenuContent;
    
    View shownPanelView;
    
    boolean wasLastOpen;
    
    int windowAnimations;
    
    int x;
    
    int y;
    
    PanelFeatureState(int param1Int) {
      this.featureId = param1Int;
      this.refreshDecorView = false;
    }
    
    void applyFrozenState() {
      MenuBuilder menuBuilder = this.menu;
      if (menuBuilder != null) {
        Bundle bundle = this.frozenMenuState;
        if (bundle != null) {
          menuBuilder.restorePresenterStates(bundle);
          this.frozenMenuState = null;
        } 
      } 
    }
    
    public void clearMenuPresenters() {
      MenuBuilder menuBuilder = this.menu;
      if (menuBuilder != null)
        menuBuilder.removeMenuPresenter((MenuPresenter)this.listMenuPresenter); 
      this.listMenuPresenter = null;
    }
    
    MenuView getListMenuView(MenuPresenter.Callback param1Callback) {
      if (this.menu == null)
        return null; 
      if (this.listMenuPresenter == null) {
        ListMenuPresenter listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
        this.listMenuPresenter = listMenuPresenter;
        listMenuPresenter.setCallback(param1Callback);
        this.menu.addMenuPresenter((MenuPresenter)this.listMenuPresenter);
      } 
      return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems() {
      View view = this.shownPanelView;
      boolean bool = false;
      if (view == null)
        return false; 
      if (this.createdPanelView != null)
        return true; 
      if (this.listMenuPresenter.getAdapter().getCount() > 0)
        bool = true; 
      return bool;
    }
    
    void onRestoreInstanceState(Parcelable param1Parcelable) {
      param1Parcelable = param1Parcelable;
      this.featureId = ((SavedState)param1Parcelable).featureId;
      this.wasLastOpen = ((SavedState)param1Parcelable).isOpen;
      this.frozenMenuState = ((SavedState)param1Parcelable).menuState;
      this.shownPanelView = null;
      this.decorView = null;
    }
    
    Parcelable onSaveInstanceState() {
      SavedState savedState = new SavedState();
      savedState.featureId = this.featureId;
      savedState.isOpen = this.isOpen;
      if (this.menu != null) {
        Bundle bundle = new Bundle();
        savedState.menuState = bundle;
        this.menu.savePresenterStates(bundle);
      } 
      return savedState;
    }
    
    void setMenu(MenuBuilder param1MenuBuilder) {
      MenuBuilder menuBuilder = this.menu;
      if (param1MenuBuilder == menuBuilder)
        return; 
      if (menuBuilder != null)
        menuBuilder.removeMenuPresenter((MenuPresenter)this.listMenuPresenter); 
      this.menu = param1MenuBuilder;
      if (param1MenuBuilder != null) {
        ListMenuPresenter listMenuPresenter = this.listMenuPresenter;
        if (listMenuPresenter != null)
          param1MenuBuilder.addMenuPresenter((MenuPresenter)listMenuPresenter); 
      } 
    }
    
    void setStyle(Context param1Context) {
      TypedValue typedValue = new TypedValue();
      Resources.Theme theme = param1Context.getResources().newTheme();
      theme.setTo(param1Context.getTheme());
      theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
      int i = typedValue.resourceId;
      if (i != 0)
        theme.applyStyle(i, true); 
      theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
      i = typedValue.resourceId;
      if (i != 0) {
        theme.applyStyle(i, true);
      } else {
        theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
      } 
      ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(param1Context, 0);
      contextThemeWrapper.getTheme().setTo(theme);
      this.listPresenterContext = (Context)contextThemeWrapper;
      TypedArray typedArray = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
      this.background = typedArray.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
      this.windowAnimations = typedArray.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
      typedArray.recycle();
    }
    
    private static class SavedState implements Parcelable {
      public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
          public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel) {
            return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param3Parcel, null);
          }
          
          public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
            return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param3Parcel, param3ClassLoader);
          }
          
          public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int param3Int) {
            return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[param3Int];
          }
        };
      
      int featureId;
      
      boolean isOpen;
      
      Bundle menuState;
      
      static SavedState readFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        SavedState savedState = new SavedState();
        savedState.featureId = param2Parcel.readInt();
        int i = param2Parcel.readInt();
        boolean bool = true;
        if (i != 1)
          bool = false; 
        savedState.isOpen = bool;
        if (bool)
          savedState.menuState = param2Parcel.readBundle(param2ClassLoader); 
        return savedState;
      }
      
      public int describeContents() {
        return 0;
      }
      
      public void writeToParcel(Parcel param2Parcel, int param2Int) {
        param2Parcel.writeInt(this.featureId);
        param2Parcel.writeInt(this.isOpen);
        if (this.isOpen)
          param2Parcel.writeBundle(this.menuState); 
      }
    }
    
    static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
      public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param2Parcel) {
        return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param2Parcel, null);
      }
      
      public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param2Parcel, param2ClassLoader);
      }
      
      public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int param2Int) {
        return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[param2Int];
      }
    }
  }
  
  private static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel) {
          return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param3Parcel, null);
        }
        
        public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param3Parcel, ClassLoader param3ClassLoader) {
          return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param3Parcel, param3ClassLoader);
        }
        
        public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int param3Int) {
          return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[param3Int];
        }
      };
    
    int featureId;
    
    boolean isOpen;
    
    Bundle menuState;
    
    static SavedState readFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      SavedState savedState = new SavedState();
      savedState.featureId = param1Parcel.readInt();
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      savedState.isOpen = bool;
      if (bool)
        savedState.menuState = param1Parcel.readBundle(param1ClassLoader); 
      return savedState;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.featureId);
      param1Parcel.writeInt(this.isOpen);
      if (this.isOpen)
        param1Parcel.writeBundle(this.menuState); 
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<PanelFeatureState.SavedState> {
    public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param1Parcel) {
      return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param1Parcel, null);
    }
    
    public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(param1Parcel, param1ClassLoader);
    }
    
    public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int param1Int) {
      return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[param1Int];
    }
  }
  
  private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      boolean bool;
      MenuBuilder menuBuilder = param1MenuBuilder.getRootMenu();
      if (menuBuilder != param1MenuBuilder) {
        bool = true;
      } else {
        bool = false;
      } 
      AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
      if (bool)
        param1MenuBuilder = menuBuilder; 
      AppCompatDelegateImplV9.PanelFeatureState panelFeatureState = appCompatDelegateImplV9.findMenuPanel((Menu)param1MenuBuilder);
      if (panelFeatureState != null)
        if (bool) {
          AppCompatDelegateImplV9.this.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu)menuBuilder);
          AppCompatDelegateImplV9.this.closePanel(panelFeatureState, true);
        } else {
          AppCompatDelegateImplV9.this.closePanel(panelFeatureState, param1Boolean);
        }  
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      if (param1MenuBuilder == null) {
        AppCompatDelegateImplV9 appCompatDelegateImplV9 = AppCompatDelegateImplV9.this;
        if (appCompatDelegateImplV9.mHasActionBar) {
          Window.Callback callback = appCompatDelegateImplV9.getWindowCallback();
          if (callback != null && !AppCompatDelegateImplV9.this.isDestroyed())
            callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
        } 
      } 
      return true;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/AppCompatDelegateImplV9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */