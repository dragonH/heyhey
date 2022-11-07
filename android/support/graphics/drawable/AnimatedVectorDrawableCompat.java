package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
  private static final String ANIMATED_VECTOR = "animated-vector";
  
  private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
  
  private static final String LOGTAG = "AnimatedVDCompat";
  
  private static final String TARGET = "target";
  
  private AnimatedVectorDrawableCompatState mAnimatedVectorState;
  
  private ArrayList<Animatable2Compat.AnimationCallback> mAnimationCallbacks = null;
  
  private Animator.AnimatorListener mAnimatorListener = null;
  
  private ArgbEvaluator mArgbEvaluator = null;
  
  AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
  
  final Drawable.Callback mCallback;
  
  private Context mContext;
  
  AnimatedVectorDrawableCompat() {
    this(null, null, null);
  }
  
  private AnimatedVectorDrawableCompat(@Nullable Context paramContext) {
    this(paramContext, null, null);
  }
  
  private AnimatedVectorDrawableCompat(@Nullable Context paramContext, @Nullable AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, @Nullable Resources paramResources) {
    Drawable.Callback callback = new Drawable.Callback() {
        public void invalidateDrawable(Drawable param1Drawable) {
          AnimatedVectorDrawableCompat.this.invalidateSelf();
        }
        
        public void scheduleDrawable(Drawable param1Drawable, Runnable param1Runnable, long param1Long) {
          AnimatedVectorDrawableCompat.this.scheduleSelf(param1Runnable, param1Long);
        }
        
        public void unscheduleDrawable(Drawable param1Drawable, Runnable param1Runnable) {
          AnimatedVectorDrawableCompat.this.unscheduleSelf(param1Runnable);
        }
      };
    this.mCallback = callback;
    this.mContext = paramContext;
    if (paramAnimatedVectorDrawableCompatState != null) {
      this.mAnimatedVectorState = paramAnimatedVectorDrawableCompatState;
    } else {
      this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(paramContext, paramAnimatedVectorDrawableCompatState, callback, paramResources);
    } 
  }
  
  public static void clearAnimationCallbacks(Drawable paramDrawable) {
    if (paramDrawable != null && paramDrawable instanceof android.graphics.drawable.Animatable)
      if (Build.VERSION.SDK_INT >= 24) {
        ((AnimatedVectorDrawable)paramDrawable).clearAnimationCallbacks();
      } else {
        ((AnimatedVectorDrawableCompat)paramDrawable).clearAnimationCallbacks();
      }  
  }
  
  @Nullable
  public static AnimatedVectorDrawableCompat create(@NonNull Context paramContext, @DrawableRes int paramInt) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= 24) {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(paramContext);
      drawable = ResourcesCompat.getDrawable(paramContext.getResources(), paramInt, paramContext.getTheme());
      animatedVectorDrawableCompat.mDelegateDrawable = drawable;
      drawable.setCallback(animatedVectorDrawableCompat.mCallback);
      animatedVectorDrawableCompat.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(animatedVectorDrawableCompat.mDelegateDrawable.getConstantState());
      return animatedVectorDrawableCompat;
    } 
    Resources resources = drawable.getResources();
    try {
      XmlResourceParser xmlResourceParser = resources.getXml(paramInt);
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
      while (true) {
        paramInt = xmlResourceParser.next();
        if (paramInt != 2 && paramInt != 1)
          continue; 
        break;
      } 
      if (paramInt == 2)
        return createFromXmlInner((Context)drawable, drawable.getResources(), (XmlPullParser)xmlResourceParser, attributeSet, drawable.getTheme()); 
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      this("No start tag found");
      throw xmlPullParserException;
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e("AnimatedVDCompat", "parser error", (Throwable)xmlPullParserException);
    } catch (IOException iOException) {
      Log.e("AnimatedVDCompat", "parser error", iOException);
    } 
    return null;
  }
  
  public static AnimatedVectorDrawableCompat createFromXmlInner(Context paramContext, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(paramContext);
    animatedVectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return animatedVectorDrawableCompat;
  }
  
  public static void registerAnimationCallback(Drawable paramDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback) {
    if (paramDrawable != null && paramAnimationCallback != null) {
      if (!(paramDrawable instanceof android.graphics.drawable.Animatable))
        return; 
      if (Build.VERSION.SDK_INT >= 24) {
        registerPlatformCallback((AnimatedVectorDrawable)paramDrawable, paramAnimationCallback);
      } else {
        ((AnimatedVectorDrawableCompat)paramDrawable).registerAnimationCallback(paramAnimationCallback);
      } 
    } 
  }
  
  @RequiresApi(23)
  private static void registerPlatformCallback(@NonNull AnimatedVectorDrawable paramAnimatedVectorDrawable, @NonNull Animatable2Compat.AnimationCallback paramAnimationCallback) {
    paramAnimatedVectorDrawable.registerAnimationCallback(paramAnimationCallback.getPlatformCallback());
  }
  
  private void removeAnimatorSetListener() {
    Animator.AnimatorListener animatorListener = this.mAnimatorListener;
    if (animatorListener != null) {
      this.mAnimatedVectorState.mAnimatorSet.removeListener(animatorListener);
      this.mAnimatorListener = null;
    } 
  }
  
  private void setupAnimatorsForTarget(String paramString, Animator paramAnimator) {
    paramAnimator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(paramString));
    if (Build.VERSION.SDK_INT < 21)
      setupColorAnimator(paramAnimator); 
    if (this.mAnimatedVectorState.mAnimators == null) {
      AnimatedVectorDrawableCompatState.access$002(this.mAnimatedVectorState, new ArrayList());
      this.mAnimatedVectorState.mTargetNameMap = new ArrayMap();
    } 
    this.mAnimatedVectorState.mAnimators.add(paramAnimator);
    this.mAnimatedVectorState.mTargetNameMap.put(paramAnimator, paramString);
  }
  
  private void setupColorAnimator(Animator paramAnimator) {
    if (paramAnimator instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)paramAnimator).getChildAnimations();
      if (arrayList != null)
        for (byte b = 0; b < arrayList.size(); b++)
          setupColorAnimator(arrayList.get(b));  
    } 
    if (paramAnimator instanceof ObjectAnimator) {
      ObjectAnimator objectAnimator = (ObjectAnimator)paramAnimator;
      String str = objectAnimator.getPropertyName();
      if ("fillColor".equals(str) || "strokeColor".equals(str)) {
        if (this.mArgbEvaluator == null)
          this.mArgbEvaluator = new ArgbEvaluator(); 
        objectAnimator.setEvaluator((TypeEvaluator)this.mArgbEvaluator);
      } 
    } 
  }
  
  public static boolean unregisterAnimationCallback(Drawable paramDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback) {
    return (paramDrawable == null || paramAnimationCallback == null) ? false : (!(paramDrawable instanceof android.graphics.drawable.Animatable) ? false : ((Build.VERSION.SDK_INT >= 24) ? unregisterPlatformCallback((AnimatedVectorDrawable)paramDrawable, paramAnimationCallback) : ((AnimatedVectorDrawableCompat)paramDrawable).unregisterAnimationCallback(paramAnimationCallback)));
  }
  
  @RequiresApi(23)
  private static boolean unregisterPlatformCallback(AnimatedVectorDrawable paramAnimatedVectorDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback) {
    return paramAnimatedVectorDrawable.unregisterAnimationCallback(paramAnimationCallback.getPlatformCallback());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      DrawableCompat.applyTheme(drawable, paramTheme); 
  }
  
  public boolean canApplyTheme() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.canApplyTheme(drawable) : false;
  }
  
  public void clearAnimationCallbacks() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      ((AnimatedVectorDrawable)drawable).clearAnimationCallbacks();
      return;
    } 
    removeAnimatorSetListener();
    ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.mAnimationCallbacks;
    if (arrayList == null)
      return; 
    arrayList.clear();
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.draw(paramCanvas);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.draw(paramCanvas);
    if (this.mAnimatedVectorState.mAnimatorSet.isStarted())
      invalidateSelf(); 
  }
  
  public int getAlpha() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.getAlpha(drawable) : this.mAnimatedVectorState.mVectorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getChangingConfigurations() : (super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations);
  }
  
  public Drawable.ConstantState getConstantState() {
    return (this.mDelegateDrawable != null && Build.VERSION.SDK_INT >= 24) ? new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState()) : null;
  }
  
  public int getIntrinsicHeight() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicHeight() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicWidth() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
  }
  
  public int getOpacity() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getOpacity() : this.mAnimatedVectorState.mVectorDrawable.getOpacity();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.inflate(drawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    } 
    int i = paramXmlPullParser.getEventType();
    int j = paramXmlPullParser.getDepth();
    while (i != 1 && (paramXmlPullParser.getDepth() >= j + 1 || i != 3)) {
      if (i == 2) {
        VectorDrawableCompat vectorDrawableCompat;
        String str = paramXmlPullParser.getName();
        if ("animated-vector".equals(str)) {
          TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
          i = typedArray.getResourceId(0, 0);
          if (i != 0) {
            VectorDrawableCompat vectorDrawableCompat1 = VectorDrawableCompat.create(paramResources, i, paramTheme);
            vectorDrawableCompat1.setAllowCaching(false);
            vectorDrawableCompat1.setCallback(this.mCallback);
            vectorDrawableCompat = this.mAnimatedVectorState.mVectorDrawable;
            if (vectorDrawableCompat != null)
              vectorDrawableCompat.setCallback(null); 
            this.mAnimatedVectorState.mVectorDrawable = vectorDrawableCompat1;
          } 
          typedArray.recycle();
        } else if ("target".equals(vectorDrawableCompat)) {
          TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
          String str1 = typedArray.getString(0);
          i = typedArray.getResourceId(1, 0);
          if (i != 0) {
            Context context = this.mContext;
            if (context != null) {
              setupAnimatorsForTarget(str1, AnimatorInflaterCompat.loadAnimator(context, i));
            } else {
              typedArray.recycle();
              throw new IllegalStateException("Context can't be null when inflating animators");
            } 
          } 
          typedArray.recycle();
        } 
      } 
      i = paramXmlPullParser.next();
    } 
    this.mAnimatedVectorState.setupAnimatorSet();
  }
  
  public boolean isAutoMirrored() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.isAutoMirrored(drawable) : this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
  }
  
  public boolean isRunning() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? ((AnimatedVectorDrawable)drawable).isRunning() : this.mAnimatedVectorState.mAnimatorSet.isRunning();
  }
  
  public boolean isStateful() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.isStateful() : this.mAnimatedVectorState.mVectorDrawable.isStateful();
  }
  
  public Drawable mutate() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      drawable.mutate(); 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setBounds(paramRect);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.setLevel(paramInt) : this.mAnimatedVectorState.mVectorDrawable.setLevel(paramInt);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.setState(paramArrayOfint) : this.mAnimatedVectorState.mVectorDrawable.setState(paramArrayOfint);
  }
  
  public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback paramAnimationCallback) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      registerPlatformCallback((AnimatedVectorDrawable)drawable, paramAnimationCallback);
      return;
    } 
    if (paramAnimationCallback == null)
      return; 
    if (this.mAnimationCallbacks == null)
      this.mAnimationCallbacks = new ArrayList<Animatable2Compat.AnimationCallback>(); 
    if (this.mAnimationCallbacks.contains(paramAnimationCallback))
      return; 
    this.mAnimationCallbacks.add(paramAnimationCallback);
    if (this.mAnimatorListener == null)
      this.mAnimatorListener = (Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            ArrayList<Animatable2Compat.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
            int i = arrayList.size();
            for (byte b = 0; b < i; b++)
              ((Animatable2Compat.AnimationCallback)arrayList.get(b)).onAnimationEnd(AnimatedVectorDrawableCompat.this); 
          }
          
          public void onAnimationStart(Animator param1Animator) {
            ArrayList<Animatable2Compat.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
            int i = arrayList.size();
            for (byte b = 0; b < i; b++)
              ((Animatable2Compat.AnimationCallback)arrayList.get(b)).onAnimationStart(AnimatedVectorDrawableCompat.this); 
          }
        }; 
    this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
  }
  
  public void setAlpha(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setAlpha(paramInt);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setAutoMirrored(drawable, paramBoolean);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(paramBoolean);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setColorFilter(paramColorFilter);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setTint(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTint(drawable, paramInt);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintList(drawable, paramColorStateList);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintMode(drawable, paramMode);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTintMode(paramMode);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      return drawable.setVisible(paramBoolean1, paramBoolean2); 
    this.mAnimatedVectorState.mVectorDrawable.setVisible(paramBoolean1, paramBoolean2);
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      ((AnimatedVectorDrawable)drawable).start();
      return;
    } 
    if (this.mAnimatedVectorState.mAnimatorSet.isStarted())
      return; 
    this.mAnimatedVectorState.mAnimatorSet.start();
    invalidateSelf();
  }
  
  public void stop() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      ((AnimatedVectorDrawable)drawable).stop();
      return;
    } 
    this.mAnimatedVectorState.mAnimatorSet.end();
  }
  
  public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback paramAnimationCallback) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      unregisterPlatformCallback((AnimatedVectorDrawable)drawable, paramAnimationCallback); 
    ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.mAnimationCallbacks;
    if (arrayList == null || paramAnimationCallback == null)
      return false; 
    boolean bool = arrayList.remove(paramAnimationCallback);
    if (this.mAnimationCallbacks.size() == 0)
      removeAnimatorSetListener(); 
    return bool;
  }
  
  private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
    AnimatorSet mAnimatorSet;
    
    private ArrayList<Animator> mAnimators;
    
    int mChangingConfigurations;
    
    ArrayMap<Animator, String> mTargetNameMap;
    
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompatState(Context param1Context, AnimatedVectorDrawableCompatState param1AnimatedVectorDrawableCompatState, Drawable.Callback param1Callback, Resources param1Resources) {
      if (param1AnimatedVectorDrawableCompatState != null) {
        this.mChangingConfigurations = param1AnimatedVectorDrawableCompatState.mChangingConfigurations;
        VectorDrawableCompat vectorDrawableCompat = param1AnimatedVectorDrawableCompatState.mVectorDrawable;
        byte b = 0;
        if (vectorDrawableCompat != null) {
          Drawable.ConstantState constantState = vectorDrawableCompat.getConstantState();
          if (param1Resources != null) {
            this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable(param1Resources);
          } else {
            this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable();
          } 
          VectorDrawableCompat vectorDrawableCompat1 = (VectorDrawableCompat)this.mVectorDrawable.mutate();
          this.mVectorDrawable = vectorDrawableCompat1;
          vectorDrawableCompat1.setCallback(param1Callback);
          this.mVectorDrawable.setBounds(param1AnimatedVectorDrawableCompatState.mVectorDrawable.getBounds());
          this.mVectorDrawable.setAllowCaching(false);
        } 
        ArrayList<Animator> arrayList = param1AnimatedVectorDrawableCompatState.mAnimators;
        if (arrayList != null) {
          int i = arrayList.size();
          this.mAnimators = new ArrayList<Animator>(i);
          this.mTargetNameMap = new ArrayMap(i);
          while (b < i) {
            Animator animator2 = param1AnimatedVectorDrawableCompatState.mAnimators.get(b);
            Animator animator1 = animator2.clone();
            String str = (String)param1AnimatedVectorDrawableCompatState.mTargetNameMap.get(animator2);
            animator1.setTarget(this.mVectorDrawable.getTargetByName(str));
            this.mAnimators.add(animator1);
            this.mTargetNameMap.put(animator1, str);
            b++;
          } 
          setupAnimatorSet();
        } 
      } 
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public void setupAnimatorSet() {
      if (this.mAnimatorSet == null)
        this.mAnimatorSet = new AnimatorSet(); 
      this.mAnimatorSet.playTogether(this.mAnimators);
    }
  }
  
  @RequiresApi(24)
  private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
    private final Drawable.ConstantState mDelegateState;
    
    public AnimatedVectorDrawableDelegateState(Drawable.ConstantState param1ConstantState) {
      this.mDelegateState = param1ConstantState;
    }
    
    public boolean canApplyTheme() {
      return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations() {
      return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable() {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      Drawable drawable = this.mDelegateState.newDrawable();
      animatedVectorDrawableCompat.mDelegateDrawable = drawable;
      drawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      Drawable drawable = this.mDelegateState.newDrawable(param1Resources);
      animatedVectorDrawableCompat.mDelegateDrawable = drawable;
      drawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources, Resources.Theme param1Theme) {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      Drawable drawable = this.mDelegateState.newDrawable(param1Resources, param1Theme);
      animatedVectorDrawableCompat.mDelegateDrawable = drawable;
      drawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/graphics/drawable/AnimatedVectorDrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */