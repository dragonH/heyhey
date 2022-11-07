package android.support.v4.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
  private final GestureDetectorCompatImpl mImpl;
  
  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener) {
    this(paramContext, paramOnGestureListener, null);
  }
  
  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler) {
    if (Build.VERSION.SDK_INT > 17) {
      this.mImpl = new GestureDetectorCompatImplJellybeanMr2(paramContext, paramOnGestureListener, paramHandler);
    } else {
      this.mImpl = new GestureDetectorCompatImplBase(paramContext, paramOnGestureListener, paramHandler);
    } 
  }
  
  public boolean isLongpressEnabled() {
    return this.mImpl.isLongpressEnabled();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return this.mImpl.onTouchEvent(paramMotionEvent);
  }
  
  public void setIsLongpressEnabled(boolean paramBoolean) {
    this.mImpl.setIsLongpressEnabled(paramBoolean);
  }
  
  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener) {
    this.mImpl.setOnDoubleTapListener(paramOnDoubleTapListener);
  }
  
  static interface GestureDetectorCompatImpl {
    boolean isLongpressEnabled();
    
    boolean onTouchEvent(MotionEvent param1MotionEvent);
    
    void setIsLongpressEnabled(boolean param1Boolean);
    
    void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener);
  }
  
  static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    
    private static final int LONG_PRESS = 2;
    
    private static final int SHOW_PRESS = 1;
    
    private static final int TAP = 3;
    
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    
    private boolean mAlwaysInBiggerTapRegion;
    
    private boolean mAlwaysInTapRegion;
    
    MotionEvent mCurrentDownEvent;
    
    boolean mDeferConfirmSingleTap;
    
    GestureDetector.OnDoubleTapListener mDoubleTapListener;
    
    private int mDoubleTapSlopSquare;
    
    private float mDownFocusX;
    
    private float mDownFocusY;
    
    private final Handler mHandler;
    
    private boolean mInLongPress;
    
    private boolean mIsDoubleTapping;
    
    private boolean mIsLongpressEnabled;
    
    private float mLastFocusX;
    
    private float mLastFocusY;
    
    final GestureDetector.OnGestureListener mListener;
    
    private int mMaximumFlingVelocity;
    
    private int mMinimumFlingVelocity;
    
    private MotionEvent mPreviousUpEvent;
    
    boolean mStillDown;
    
    private int mTouchSlopSquare;
    
    private VelocityTracker mVelocityTracker;
    
    static {
    
    }
    
    public GestureDetectorCompatImplBase(Context param1Context, GestureDetector.OnGestureListener param1OnGestureListener, Handler param1Handler) {
      if (param1Handler != null) {
        this.mHandler = new GestureHandler(param1Handler);
      } else {
        this.mHandler = new GestureHandler();
      } 
      this.mListener = param1OnGestureListener;
      if (param1OnGestureListener instanceof GestureDetector.OnDoubleTapListener)
        setOnDoubleTapListener((GestureDetector.OnDoubleTapListener)param1OnGestureListener); 
      init(param1Context);
    }
    
    private void cancel() {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
      this.mIsDoubleTapping = false;
      this.mStillDown = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false; 
    }
    
    private void cancelTaps() {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mIsDoubleTapping = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false; 
    }
    
    private void init(Context param1Context) {
      if (param1Context != null) {
        if (this.mListener != null) {
          this.mIsLongpressEnabled = true;
          ViewConfiguration viewConfiguration = ViewConfiguration.get(param1Context);
          int i = viewConfiguration.getScaledTouchSlop();
          int j = viewConfiguration.getScaledDoubleTapSlop();
          this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
          this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
          this.mTouchSlopSquare = i * i;
          this.mDoubleTapSlopSquare = j * j;
          return;
        } 
        throw new IllegalArgumentException("OnGestureListener must not be null");
      } 
      throw new IllegalArgumentException("Context must not be null");
    }
    
    private boolean isConsideredDoubleTap(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, MotionEvent param1MotionEvent3) {
      boolean bool = this.mAlwaysInBiggerTapRegion;
      boolean bool1 = false;
      if (!bool)
        return false; 
      if (param1MotionEvent3.getEventTime() - param1MotionEvent2.getEventTime() > DOUBLE_TAP_TIMEOUT)
        return false; 
      int i = (int)param1MotionEvent1.getX() - (int)param1MotionEvent3.getX();
      int j = (int)param1MotionEvent1.getY() - (int)param1MotionEvent3.getY();
      if (i * i + j * j < this.mDoubleTapSlopSquare)
        bool1 = true; 
      return bool1;
    }
    
    void dispatchLongPress() {
      this.mHandler.removeMessages(3);
      this.mDeferConfirmSingleTap = false;
      this.mInLongPress = true;
      this.mListener.onLongPress(this.mCurrentDownEvent);
    }
    
    public boolean isLongpressEnabled() {
      return this.mIsLongpressEnabled;
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual getAction : ()I
      //   4: istore_2
      //   5: aload_0
      //   6: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   9: ifnonnull -> 19
      //   12: aload_0
      //   13: invokestatic obtain : ()Landroid/view/VelocityTracker;
      //   16: putfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   19: aload_0
      //   20: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   23: aload_1
      //   24: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
      //   27: iload_2
      //   28: sipush #255
      //   31: iand
      //   32: istore_3
      //   33: iconst_0
      //   34: istore #4
      //   36: iload_3
      //   37: bipush #6
      //   39: if_icmpne -> 47
      //   42: iconst_1
      //   43: istore_2
      //   44: goto -> 49
      //   47: iconst_0
      //   48: istore_2
      //   49: iload_2
      //   50: ifeq -> 62
      //   53: aload_1
      //   54: invokevirtual getActionIndex : ()I
      //   57: istore #5
      //   59: goto -> 65
      //   62: iconst_m1
      //   63: istore #5
      //   65: aload_1
      //   66: invokevirtual getPointerCount : ()I
      //   69: istore #6
      //   71: iconst_0
      //   72: istore #7
      //   74: fconst_0
      //   75: fstore #8
      //   77: fconst_0
      //   78: fstore #9
      //   80: iload #7
      //   82: iload #6
      //   84: if_icmpge -> 125
      //   87: iload #5
      //   89: iload #7
      //   91: if_icmpne -> 97
      //   94: goto -> 119
      //   97: fload #8
      //   99: aload_1
      //   100: iload #7
      //   102: invokevirtual getX : (I)F
      //   105: fadd
      //   106: fstore #8
      //   108: fload #9
      //   110: aload_1
      //   111: iload #7
      //   113: invokevirtual getY : (I)F
      //   116: fadd
      //   117: fstore #9
      //   119: iinc #7, 1
      //   122: goto -> 80
      //   125: iload_2
      //   126: ifeq -> 137
      //   129: iload #6
      //   131: iconst_1
      //   132: isub
      //   133: istore_2
      //   134: goto -> 140
      //   137: iload #6
      //   139: istore_2
      //   140: iload_2
      //   141: i2f
      //   142: fstore #10
      //   144: fload #8
      //   146: fload #10
      //   148: fdiv
      //   149: fstore #8
      //   151: fload #9
      //   153: fload #10
      //   155: fdiv
      //   156: fstore #11
      //   158: iload_3
      //   159: ifeq -> 912
      //   162: iload_3
      //   163: iconst_1
      //   164: if_icmpeq -> 641
      //   167: iload_3
      //   168: iconst_2
      //   169: if_icmpeq -> 391
      //   172: iload_3
      //   173: iconst_3
      //   174: if_icmpeq -> 380
      //   177: iload_3
      //   178: iconst_5
      //   179: if_icmpeq -> 345
      //   182: iload_3
      //   183: bipush #6
      //   185: if_icmpeq -> 195
      //   188: iload #4
      //   190: istore #12
      //   192: goto -> 1180
      //   195: aload_0
      //   196: fload #8
      //   198: putfield mLastFocusX : F
      //   201: aload_0
      //   202: fload #8
      //   204: putfield mDownFocusX : F
      //   207: aload_0
      //   208: fload #11
      //   210: putfield mLastFocusY : F
      //   213: aload_0
      //   214: fload #11
      //   216: putfield mDownFocusY : F
      //   219: aload_0
      //   220: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   223: sipush #1000
      //   226: aload_0
      //   227: getfield mMaximumFlingVelocity : I
      //   230: i2f
      //   231: invokevirtual computeCurrentVelocity : (IF)V
      //   234: aload_1
      //   235: invokevirtual getActionIndex : ()I
      //   238: istore #5
      //   240: aload_1
      //   241: iload #5
      //   243: invokevirtual getPointerId : (I)I
      //   246: istore_2
      //   247: aload_0
      //   248: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   251: iload_2
      //   252: invokevirtual getXVelocity : (I)F
      //   255: fstore #8
      //   257: aload_0
      //   258: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   261: iload_2
      //   262: invokevirtual getYVelocity : (I)F
      //   265: fstore #9
      //   267: iconst_0
      //   268: istore_2
      //   269: iload #4
      //   271: istore #12
      //   273: iload_2
      //   274: iload #6
      //   276: if_icmpge -> 1180
      //   279: iload_2
      //   280: iload #5
      //   282: if_icmpne -> 288
      //   285: goto -> 339
      //   288: aload_1
      //   289: iload_2
      //   290: invokevirtual getPointerId : (I)I
      //   293: istore #7
      //   295: aload_0
      //   296: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   299: iload #7
      //   301: invokevirtual getXVelocity : (I)F
      //   304: fload #8
      //   306: fmul
      //   307: aload_0
      //   308: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   311: iload #7
      //   313: invokevirtual getYVelocity : (I)F
      //   316: fload #9
      //   318: fmul
      //   319: fadd
      //   320: fconst_0
      //   321: fcmpg
      //   322: ifge -> 339
      //   325: aload_0
      //   326: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   329: invokevirtual clear : ()V
      //   332: iload #4
      //   334: istore #12
      //   336: goto -> 1180
      //   339: iinc #2, 1
      //   342: goto -> 269
      //   345: aload_0
      //   346: fload #8
      //   348: putfield mLastFocusX : F
      //   351: aload_0
      //   352: fload #8
      //   354: putfield mDownFocusX : F
      //   357: aload_0
      //   358: fload #11
      //   360: putfield mLastFocusY : F
      //   363: aload_0
      //   364: fload #11
      //   366: putfield mDownFocusY : F
      //   369: aload_0
      //   370: invokespecial cancelTaps : ()V
      //   373: iload #4
      //   375: istore #12
      //   377: goto -> 1180
      //   380: aload_0
      //   381: invokespecial cancel : ()V
      //   384: iload #4
      //   386: istore #12
      //   388: goto -> 1180
      //   391: aload_0
      //   392: getfield mInLongPress : Z
      //   395: ifeq -> 405
      //   398: iload #4
      //   400: istore #12
      //   402: goto -> 1180
      //   405: aload_0
      //   406: getfield mLastFocusX : F
      //   409: fload #8
      //   411: fsub
      //   412: fstore #10
      //   414: aload_0
      //   415: getfield mLastFocusY : F
      //   418: fload #11
      //   420: fsub
      //   421: fstore #9
      //   423: aload_0
      //   424: getfield mIsDoubleTapping : Z
      //   427: ifeq -> 447
      //   430: iconst_0
      //   431: aload_0
      //   432: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   435: aload_1
      //   436: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   441: ior
      //   442: istore #12
      //   444: goto -> 1180
      //   447: aload_0
      //   448: getfield mAlwaysInTapRegion : Z
      //   451: ifeq -> 582
      //   454: fload #8
      //   456: aload_0
      //   457: getfield mDownFocusX : F
      //   460: fsub
      //   461: f2i
      //   462: istore #5
      //   464: fload #11
      //   466: aload_0
      //   467: getfield mDownFocusY : F
      //   470: fsub
      //   471: f2i
      //   472: istore_2
      //   473: iload #5
      //   475: iload #5
      //   477: imul
      //   478: iload_2
      //   479: iload_2
      //   480: imul
      //   481: iadd
      //   482: istore_2
      //   483: iload_2
      //   484: aload_0
      //   485: getfield mTouchSlopSquare : I
      //   488: if_icmple -> 555
      //   491: aload_0
      //   492: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   495: aload_0
      //   496: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   499: aload_1
      //   500: fload #10
      //   502: fload #9
      //   504: invokeinterface onScroll : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   509: istore #4
      //   511: aload_0
      //   512: fload #8
      //   514: putfield mLastFocusX : F
      //   517: aload_0
      //   518: fload #11
      //   520: putfield mLastFocusY : F
      //   523: aload_0
      //   524: iconst_0
      //   525: putfield mAlwaysInTapRegion : Z
      //   528: aload_0
      //   529: getfield mHandler : Landroid/os/Handler;
      //   532: iconst_3
      //   533: invokevirtual removeMessages : (I)V
      //   536: aload_0
      //   537: getfield mHandler : Landroid/os/Handler;
      //   540: iconst_1
      //   541: invokevirtual removeMessages : (I)V
      //   544: aload_0
      //   545: getfield mHandler : Landroid/os/Handler;
      //   548: iconst_2
      //   549: invokevirtual removeMessages : (I)V
      //   552: goto -> 558
      //   555: iconst_0
      //   556: istore #4
      //   558: iload #4
      //   560: istore #12
      //   562: iload_2
      //   563: aload_0
      //   564: getfield mTouchSlopSquare : I
      //   567: if_icmple -> 909
      //   570: aload_0
      //   571: iconst_0
      //   572: putfield mAlwaysInBiggerTapRegion : Z
      //   575: iload #4
      //   577: istore #12
      //   579: goto -> 909
      //   582: fload #10
      //   584: invokestatic abs : (F)F
      //   587: fconst_1
      //   588: fcmpl
      //   589: ifge -> 606
      //   592: iload #4
      //   594: istore #12
      //   596: fload #9
      //   598: invokestatic abs : (F)F
      //   601: fconst_1
      //   602: fcmpl
      //   603: iflt -> 1180
      //   606: aload_0
      //   607: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   610: aload_0
      //   611: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   614: aload_1
      //   615: fload #10
      //   617: fload #9
      //   619: invokeinterface onScroll : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   624: istore #12
      //   626: aload_0
      //   627: fload #8
      //   629: putfield mLastFocusX : F
      //   632: aload_0
      //   633: fload #11
      //   635: putfield mLastFocusY : F
      //   638: goto -> 1180
      //   641: aload_0
      //   642: iconst_0
      //   643: putfield mStillDown : Z
      //   646: aload_1
      //   647: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
      //   650: astore #13
      //   652: aload_0
      //   653: getfield mIsDoubleTapping : Z
      //   656: ifeq -> 676
      //   659: aload_0
      //   660: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   663: aload_1
      //   664: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   669: iconst_0
      //   670: ior
      //   671: istore #12
      //   673: goto -> 846
      //   676: aload_0
      //   677: getfield mInLongPress : Z
      //   680: ifeq -> 699
      //   683: aload_0
      //   684: getfield mHandler : Landroid/os/Handler;
      //   687: iconst_3
      //   688: invokevirtual removeMessages : (I)V
      //   691: aload_0
      //   692: iconst_0
      //   693: putfield mInLongPress : Z
      //   696: goto -> 820
      //   699: aload_0
      //   700: getfield mAlwaysInTapRegion : Z
      //   703: ifeq -> 748
      //   706: aload_0
      //   707: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   710: aload_1
      //   711: invokeinterface onSingleTapUp : (Landroid/view/MotionEvent;)Z
      //   716: istore #12
      //   718: aload_0
      //   719: getfield mDeferConfirmSingleTap : Z
      //   722: ifeq -> 745
      //   725: aload_0
      //   726: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   729: astore #14
      //   731: aload #14
      //   733: ifnull -> 745
      //   736: aload #14
      //   738: aload_1
      //   739: invokeinterface onSingleTapConfirmed : (Landroid/view/MotionEvent;)Z
      //   744: pop
      //   745: goto -> 846
      //   748: aload_0
      //   749: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   752: astore #14
      //   754: aload_1
      //   755: iconst_0
      //   756: invokevirtual getPointerId : (I)I
      //   759: istore_2
      //   760: aload #14
      //   762: sipush #1000
      //   765: aload_0
      //   766: getfield mMaximumFlingVelocity : I
      //   769: i2f
      //   770: invokevirtual computeCurrentVelocity : (IF)V
      //   773: aload #14
      //   775: iload_2
      //   776: invokevirtual getYVelocity : (I)F
      //   779: fstore #8
      //   781: aload #14
      //   783: iload_2
      //   784: invokevirtual getXVelocity : (I)F
      //   787: fstore #9
      //   789: fload #8
      //   791: invokestatic abs : (F)F
      //   794: aload_0
      //   795: getfield mMinimumFlingVelocity : I
      //   798: i2f
      //   799: fcmpl
      //   800: ifgt -> 826
      //   803: fload #9
      //   805: invokestatic abs : (F)F
      //   808: aload_0
      //   809: getfield mMinimumFlingVelocity : I
      //   812: i2f
      //   813: fcmpl
      //   814: ifle -> 820
      //   817: goto -> 826
      //   820: iconst_0
      //   821: istore #12
      //   823: goto -> 846
      //   826: aload_0
      //   827: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   830: aload_0
      //   831: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   834: aload_1
      //   835: fload #9
      //   837: fload #8
      //   839: invokeinterface onFling : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   844: istore #12
      //   846: aload_0
      //   847: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   850: astore_1
      //   851: aload_1
      //   852: ifnull -> 859
      //   855: aload_1
      //   856: invokevirtual recycle : ()V
      //   859: aload_0
      //   860: aload #13
      //   862: putfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   865: aload_0
      //   866: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   869: astore_1
      //   870: aload_1
      //   871: ifnull -> 883
      //   874: aload_1
      //   875: invokevirtual recycle : ()V
      //   878: aload_0
      //   879: aconst_null
      //   880: putfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   883: aload_0
      //   884: iconst_0
      //   885: putfield mIsDoubleTapping : Z
      //   888: aload_0
      //   889: iconst_0
      //   890: putfield mDeferConfirmSingleTap : Z
      //   893: aload_0
      //   894: getfield mHandler : Landroid/os/Handler;
      //   897: iconst_1
      //   898: invokevirtual removeMessages : (I)V
      //   901: aload_0
      //   902: getfield mHandler : Landroid/os/Handler;
      //   905: iconst_2
      //   906: invokevirtual removeMessages : (I)V
      //   909: goto -> 1180
      //   912: aload_0
      //   913: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   916: ifnull -> 1029
      //   919: aload_0
      //   920: getfield mHandler : Landroid/os/Handler;
      //   923: iconst_3
      //   924: invokevirtual hasMessages : (I)Z
      //   927: istore #12
      //   929: iload #12
      //   931: ifeq -> 942
      //   934: aload_0
      //   935: getfield mHandler : Landroid/os/Handler;
      //   938: iconst_3
      //   939: invokevirtual removeMessages : (I)V
      //   942: aload_0
      //   943: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   946: astore #14
      //   948: aload #14
      //   950: ifnull -> 1016
      //   953: aload_0
      //   954: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   957: astore #13
      //   959: aload #13
      //   961: ifnull -> 1016
      //   964: iload #12
      //   966: ifeq -> 1016
      //   969: aload_0
      //   970: aload #14
      //   972: aload #13
      //   974: aload_1
      //   975: invokespecial isConsideredDoubleTap : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;Landroid/view/MotionEvent;)Z
      //   978: ifeq -> 1016
      //   981: aload_0
      //   982: iconst_1
      //   983: putfield mIsDoubleTapping : Z
      //   986: aload_0
      //   987: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   990: aload_0
      //   991: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   994: invokeinterface onDoubleTap : (Landroid/view/MotionEvent;)Z
      //   999: iconst_0
      //   1000: ior
      //   1001: aload_0
      //   1002: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   1005: aload_1
      //   1006: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   1011: ior
      //   1012: istore_2
      //   1013: goto -> 1031
      //   1016: aload_0
      //   1017: getfield mHandler : Landroid/os/Handler;
      //   1020: iconst_3
      //   1021: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT : I
      //   1024: i2l
      //   1025: invokevirtual sendEmptyMessageDelayed : (IJ)Z
      //   1028: pop
      //   1029: iconst_0
      //   1030: istore_2
      //   1031: aload_0
      //   1032: fload #8
      //   1034: putfield mLastFocusX : F
      //   1037: aload_0
      //   1038: fload #8
      //   1040: putfield mDownFocusX : F
      //   1043: aload_0
      //   1044: fload #11
      //   1046: putfield mLastFocusY : F
      //   1049: aload_0
      //   1050: fload #11
      //   1052: putfield mDownFocusY : F
      //   1055: aload_0
      //   1056: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1059: astore #13
      //   1061: aload #13
      //   1063: ifnull -> 1071
      //   1066: aload #13
      //   1068: invokevirtual recycle : ()V
      //   1071: aload_0
      //   1072: aload_1
      //   1073: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
      //   1076: putfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1079: aload_0
      //   1080: iconst_1
      //   1081: putfield mAlwaysInTapRegion : Z
      //   1084: aload_0
      //   1085: iconst_1
      //   1086: putfield mAlwaysInBiggerTapRegion : Z
      //   1089: aload_0
      //   1090: iconst_1
      //   1091: putfield mStillDown : Z
      //   1094: aload_0
      //   1095: iconst_0
      //   1096: putfield mInLongPress : Z
      //   1099: aload_0
      //   1100: iconst_0
      //   1101: putfield mDeferConfirmSingleTap : Z
      //   1104: aload_0
      //   1105: getfield mIsLongpressEnabled : Z
      //   1108: ifeq -> 1145
      //   1111: aload_0
      //   1112: getfield mHandler : Landroid/os/Handler;
      //   1115: iconst_2
      //   1116: invokevirtual removeMessages : (I)V
      //   1119: aload_0
      //   1120: getfield mHandler : Landroid/os/Handler;
      //   1123: iconst_2
      //   1124: aload_0
      //   1125: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1128: invokevirtual getDownTime : ()J
      //   1131: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT : I
      //   1134: i2l
      //   1135: ladd
      //   1136: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT : I
      //   1139: i2l
      //   1140: ladd
      //   1141: invokevirtual sendEmptyMessageAtTime : (IJ)Z
      //   1144: pop
      //   1145: aload_0
      //   1146: getfield mHandler : Landroid/os/Handler;
      //   1149: iconst_1
      //   1150: aload_0
      //   1151: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1154: invokevirtual getDownTime : ()J
      //   1157: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT : I
      //   1160: i2l
      //   1161: ladd
      //   1162: invokevirtual sendEmptyMessageAtTime : (IJ)Z
      //   1165: pop
      //   1166: iload_2
      //   1167: aload_0
      //   1168: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   1171: aload_1
      //   1172: invokeinterface onDown : (Landroid/view/MotionEvent;)Z
      //   1177: ior
      //   1178: istore #12
      //   1180: iload #12
      //   1182: ireturn
    }
    
    public void setIsLongpressEnabled(boolean param1Boolean) {
      this.mIsLongpressEnabled = param1Boolean;
    }
    
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener) {
      this.mDoubleTapListener = param1OnDoubleTapListener;
    }
    
    private class GestureHandler extends Handler {
      GestureHandler() {}
      
      GestureHandler(Handler param2Handler) {
        super(param2Handler.getLooper());
      }
      
      public void handleMessage(Message param2Message) {
        int i = param2Message.what;
        if (i != 1) {
          if (i != 2) {
            GestureDetectorCompat.GestureDetectorCompatImplBase gestureDetectorCompatImplBase;
            if (i == 3) {
              gestureDetectorCompatImplBase = GestureDetectorCompat.GestureDetectorCompatImplBase.this;
              GestureDetector.OnDoubleTapListener onDoubleTapListener = gestureDetectorCompatImplBase.mDoubleTapListener;
              if (onDoubleTapListener != null)
                if (!gestureDetectorCompatImplBase.mStillDown) {
                  onDoubleTapListener.onSingleTapConfirmed(gestureDetectorCompatImplBase.mCurrentDownEvent);
                } else {
                  gestureDetectorCompatImplBase.mDeferConfirmSingleTap = true;
                }  
            } else {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unknown message ");
              stringBuilder.append(gestureDetectorCompatImplBase);
              throw new RuntimeException(stringBuilder.toString());
            } 
          } else {
            GestureDetectorCompat.GestureDetectorCompatImplBase.this.dispatchLongPress();
          } 
        } else {
          GestureDetectorCompat.GestureDetectorCompatImplBase gestureDetectorCompatImplBase = GestureDetectorCompat.GestureDetectorCompatImplBase.this;
          gestureDetectorCompatImplBase.mListener.onShowPress(gestureDetectorCompatImplBase.mCurrentDownEvent);
        } 
      }
    }
  }
  
  private class GestureHandler extends Handler {
    GestureHandler() {}
    
    GestureHandler(Handler param1Handler) {
      super(param1Handler.getLooper());
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          GestureDetectorCompat.GestureDetectorCompatImplBase gestureDetectorCompatImplBase;
          if (i == 3) {
            gestureDetectorCompatImplBase = this.this$0;
            GestureDetector.OnDoubleTapListener onDoubleTapListener = gestureDetectorCompatImplBase.mDoubleTapListener;
            if (onDoubleTapListener != null)
              if (!gestureDetectorCompatImplBase.mStillDown) {
                onDoubleTapListener.onSingleTapConfirmed(gestureDetectorCompatImplBase.mCurrentDownEvent);
              } else {
                gestureDetectorCompatImplBase.mDeferConfirmSingleTap = true;
              }  
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown message ");
            stringBuilder.append(gestureDetectorCompatImplBase);
            throw new RuntimeException(stringBuilder.toString());
          } 
        } else {
          this.this$0.dispatchLongPress();
        } 
      } else {
        GestureDetectorCompat.GestureDetectorCompatImplBase gestureDetectorCompatImplBase = this.this$0;
        gestureDetectorCompatImplBase.mListener.onShowPress(gestureDetectorCompatImplBase.mCurrentDownEvent);
      } 
    }
  }
  
  static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
    private final GestureDetector mDetector;
    
    public GestureDetectorCompatImplJellybeanMr2(Context param1Context, GestureDetector.OnGestureListener param1OnGestureListener, Handler param1Handler) {
      this.mDetector = new GestureDetector(param1Context, param1OnGestureListener, param1Handler);
    }
    
    public boolean isLongpressEnabled() {
      return this.mDetector.isLongpressEnabled();
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      return this.mDetector.onTouchEvent(param1MotionEvent);
    }
    
    public void setIsLongpressEnabled(boolean param1Boolean) {
      this.mDetector.setIsLongpressEnabled(param1Boolean);
    }
    
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener) {
      this.mDetector.setOnDoubleTapListener(param1OnDoubleTapListener);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/GestureDetectorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */