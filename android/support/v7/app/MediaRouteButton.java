package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R;
import android.support.v7.widget.TooltipCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class MediaRouteButton extends View {
  private static final int[] CHECKABLE_STATE_SET;
  
  private static final int[] CHECKED_STATE_SET;
  
  private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
  
  private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
  
  private static final String TAG = "MediaRouteButton";
  
  private static final SparseArray<Drawable.ConstantState> sRemoteIndicatorCache = new SparseArray(2);
  
  private boolean mAttachedToWindow;
  
  private ColorStateList mButtonTint;
  
  private final MediaRouterCallback mCallback;
  
  private MediaRouteDialogFactory mDialogFactory = MediaRouteDialogFactory.getDefault();
  
  private boolean mIsConnecting;
  
  private int mMinHeight;
  
  private int mMinWidth;
  
  private boolean mRemoteActive;
  
  private Drawable mRemoteIndicator;
  
  private RemoteIndicatorLoader mRemoteIndicatorLoader;
  
  private final MediaRouter mRouter;
  
  private MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;
  
  static {
    CHECKED_STATE_SET = new int[] { 16842912 };
    CHECKABLE_STATE_SET = new int[] { 16842911 };
  }
  
  public MediaRouteButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public MediaRouteButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.mediaRouteButtonStyle);
  }
  
  public MediaRouteButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(MediaRouterThemeHelper.createThemedContext(paramContext, paramInt), paramAttributeSet, paramInt);
    paramContext = getContext();
    this.mRouter = MediaRouter.getInstance(paramContext);
    this.mCallback = new MediaRouterCallback();
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MediaRouteButton, paramInt, 0);
    this.mButtonTint = typedArray.getColorStateList(R.styleable.MediaRouteButton_mediaRouteButtonTint);
    this.mMinWidth = typedArray.getDimensionPixelSize(R.styleable.MediaRouteButton_android_minWidth, 0);
    this.mMinHeight = typedArray.getDimensionPixelSize(R.styleable.MediaRouteButton_android_minHeight, 0);
    paramInt = typedArray.getResourceId(R.styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
    typedArray.recycle();
    if (paramInt != 0) {
      Drawable.ConstantState constantState = (Drawable.ConstantState)sRemoteIndicatorCache.get(paramInt);
      if (constantState != null) {
        setRemoteIndicatorDrawable(constantState.newDrawable());
      } else {
        RemoteIndicatorLoader remoteIndicatorLoader = new RemoteIndicatorLoader(paramInt);
        this.mRemoteIndicatorLoader = remoteIndicatorLoader;
        remoteIndicatorLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      } 
    } 
    updateContentDescription();
    setClickable(true);
  }
  
  private Activity getActivity() {
    for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
      if (context instanceof Activity)
        return (Activity)context; 
    } 
    return null;
  }
  
  private FragmentManager getFragmentManager() {
    Activity activity = getActivity();
    return (activity instanceof FragmentActivity) ? ((FragmentActivity)activity).getSupportFragmentManager() : null;
  }
  
  private void updateContentDescription() {
    int i;
    if (this.mIsConnecting) {
      i = R.string.mr_cast_button_connecting;
    } else if (this.mRemoteActive) {
      i = R.string.mr_cast_button_connected;
    } else {
      i = R.string.mr_cast_button_disconnected;
    } 
    setContentDescription(getContext().getString(i));
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (this.mRemoteIndicator != null) {
      int[] arrayOfInt = getDrawableState();
      this.mRemoteIndicator.setState(arrayOfInt);
      invalidate();
    } 
  }
  
  @NonNull
  public MediaRouteDialogFactory getDialogFactory() {
    return this.mDialogFactory;
  }
  
  @NonNull
  public MediaRouteSelector getRouteSelector() {
    return this.mSelector;
  }
  
  public void jumpDrawablesToCurrentState() {
    if (getBackground() != null)
      DrawableCompat.jumpToCurrentState(getBackground()); 
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null)
      DrawableCompat.jumpToCurrentState(drawable); 
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mAttachedToWindow = true;
    if (!this.mSelector.isEmpty())
      this.mRouter.addCallback(this.mSelector, this.mCallback); 
    refreshRoute();
  }
  
  protected int[] onCreateDrawableState(int paramInt) {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (this.mIsConnecting) {
      View.mergeDrawableStates(arrayOfInt, CHECKABLE_STATE_SET);
    } else if (this.mRemoteActive) {
      View.mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    } 
    return arrayOfInt;
  }
  
  public void onDetachedFromWindow() {
    this.mAttachedToWindow = false;
    if (!this.mSelector.isEmpty())
      this.mRouter.removeCallback(this.mCallback); 
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mRemoteIndicator != null) {
      int i = getPaddingLeft();
      int j = getWidth();
      int k = getPaddingRight();
      int m = getPaddingTop();
      int n = getHeight();
      int i1 = getPaddingBottom();
      int i2 = this.mRemoteIndicator.getIntrinsicWidth();
      int i3 = this.mRemoteIndicator.getIntrinsicHeight();
      k = i + (j - k - i - i2) / 2;
      m += (n - i1 - m - i3) / 2;
      this.mRemoteIndicator.setBounds(k, m, i2 + k, i3 + m);
      this.mRemoteIndicator.draw(paramCanvas);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    int k = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int n = this.mMinWidth;
    Drawable drawable = this.mRemoteIndicator;
    paramInt2 = 0;
    if (drawable != null) {
      paramInt1 = drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight();
    } else {
      paramInt1 = 0;
    } 
    n = Math.max(n, paramInt1);
    int i1 = this.mMinHeight;
    drawable = this.mRemoteIndicator;
    paramInt1 = paramInt2;
    if (drawable != null)
      paramInt1 = drawable.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(); 
    i1 = Math.max(i1, paramInt1);
    if (k != Integer.MIN_VALUE) {
      paramInt1 = i;
      if (k != 1073741824)
        paramInt1 = n; 
    } else {
      paramInt1 = Math.min(i, n);
    } 
    if (m != Integer.MIN_VALUE) {
      paramInt2 = j;
      if (m != 1073741824)
        paramInt2 = i1; 
    } else {
      paramInt2 = Math.min(j, i1);
    } 
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public boolean performClick() {
    boolean bool = super.performClick();
    boolean bool1 = false;
    if (!bool)
      playSoundEffect(0); 
    if (showDialog() || bool)
      bool1 = true; 
    return bool1;
  }
  
  void refreshRoute() {
    boolean bool2;
    MediaRouter.RouteInfo routeInfo = this.mRouter.getSelectedRoute();
    boolean bool1 = routeInfo.isDefaultOrBluetooth();
    boolean bool = false;
    if (!bool1 && routeInfo.matchesSelector(this.mSelector)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1 && routeInfo.isConnecting()) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (this.mRemoteActive != bool1) {
      this.mRemoteActive = bool1;
      bool = true;
    } 
    if (this.mIsConnecting != bool2) {
      this.mIsConnecting = bool2;
      bool = true;
    } 
    if (bool) {
      updateContentDescription();
      refreshDrawableState();
    } 
    if (this.mAttachedToWindow)
      setEnabled(this.mRouter.isRouteAvailable(this.mSelector, 1)); 
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null && drawable.getCurrent() instanceof AnimationDrawable) {
      AnimationDrawable animationDrawable = (AnimationDrawable)this.mRemoteIndicator.getCurrent();
      if (this.mAttachedToWindow) {
        if ((bool || bool2) && !animationDrawable.isRunning())
          animationDrawable.start(); 
      } else if (bool1 && !bool2) {
        if (animationDrawable.isRunning())
          animationDrawable.stop(); 
        animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
      } 
    } 
  }
  
  void setCheatSheetEnabled(boolean paramBoolean) {
    CharSequence charSequence;
    if (paramBoolean) {
      charSequence = getContext().getString(R.string.mr_button_content_description);
    } else {
      charSequence = null;
    } 
    TooltipCompat.setTooltipText(this, charSequence);
  }
  
  public void setDialogFactory(@NonNull MediaRouteDialogFactory paramMediaRouteDialogFactory) {
    if (paramMediaRouteDialogFactory != null) {
      this.mDialogFactory = paramMediaRouteDialogFactory;
      return;
    } 
    throw new IllegalArgumentException("factory must not be null");
  }
  
  public void setRemoteIndicatorDrawable(Drawable paramDrawable) {
    RemoteIndicatorLoader remoteIndicatorLoader = this.mRemoteIndicatorLoader;
    if (remoteIndicatorLoader != null)
      remoteIndicatorLoader.cancel(false); 
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null) {
      drawable.setCallback(null);
      unscheduleDrawable(this.mRemoteIndicator);
    } 
    drawable = paramDrawable;
    if (paramDrawable != null) {
      boolean bool;
      drawable = paramDrawable;
      if (this.mButtonTint != null) {
        drawable = DrawableCompat.wrap(paramDrawable.mutate());
        DrawableCompat.setTintList(drawable, this.mButtonTint);
      } 
      drawable.setCallback((Drawable.Callback)this);
      drawable.setState(getDrawableState());
      if (getVisibility() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      drawable.setVisible(bool, false);
    } 
    this.mRemoteIndicator = drawable;
    refreshDrawableState();
    if (this.mAttachedToWindow) {
      paramDrawable = this.mRemoteIndicator;
      if (paramDrawable != null && paramDrawable.getCurrent() instanceof AnimationDrawable) {
        AnimationDrawable animationDrawable = (AnimationDrawable)this.mRemoteIndicator.getCurrent();
        if (this.mIsConnecting) {
          if (!animationDrawable.isRunning())
            animationDrawable.start(); 
        } else if (this.mRemoteActive) {
          if (animationDrawable.isRunning())
            animationDrawable.stop(); 
          animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
        } 
      } 
    } 
  }
  
  public void setRouteSelector(MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      if (!this.mSelector.equals(paramMediaRouteSelector)) {
        if (this.mAttachedToWindow) {
          if (!this.mSelector.isEmpty())
            this.mRouter.removeCallback(this.mCallback); 
          if (!paramMediaRouteSelector.isEmpty())
            this.mRouter.addCallback(paramMediaRouteSelector, this.mCallback); 
        } 
        this.mSelector = paramMediaRouteSelector;
        refreshRoute();
      } 
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  public void setVisibility(int paramInt) {
    super.setVisibility(paramInt);
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null) {
      boolean bool;
      if (getVisibility() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      drawable.setVisible(bool, false);
    } 
  }
  
  public boolean showDialog() {
    if (!this.mAttachedToWindow)
      return false; 
    FragmentManager fragmentManager = getFragmentManager();
    if (fragmentManager != null) {
      MediaRouter.RouteInfo routeInfo = this.mRouter.getSelectedRoute();
      if (routeInfo.isDefaultOrBluetooth() || !routeInfo.matchesSelector(this.mSelector)) {
        if (fragmentManager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
          Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
          return false;
        } 
        MediaRouteChooserDialogFragment mediaRouteChooserDialogFragment = this.mDialogFactory.onCreateChooserDialogFragment();
        mediaRouteChooserDialogFragment.setRouteSelector(this.mSelector);
        mediaRouteChooserDialogFragment.show(fragmentManager, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
        return true;
      } 
      if (fragmentManager.findFragmentByTag("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
        Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
        return false;
      } 
      this.mDialogFactory.onCreateControllerDialogFragment().show(fragmentManager, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
      return true;
    } 
    throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mRemoteIndicator);
  }
  
  private final class MediaRouterCallback extends MediaRouter.Callback {
    public void onProviderAdded(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onProviderChanged(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onProviderRemoved(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteSelected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteUnselected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
  }
  
  private final class RemoteIndicatorLoader extends AsyncTask<Void, Void, Drawable> {
    private final int mResId;
    
    RemoteIndicatorLoader(int param1Int) {
      this.mResId = param1Int;
    }
    
    private void cacheAndReset(Drawable param1Drawable) {
      if (param1Drawable != null)
        MediaRouteButton.sRemoteIndicatorCache.put(this.mResId, param1Drawable.getConstantState()); 
      MediaRouteButton.access$102(MediaRouteButton.this, null);
    }
    
    protected Drawable doInBackground(Void... param1VarArgs) {
      return MediaRouteButton.this.getContext().getResources().getDrawable(this.mResId);
    }
    
    protected void onCancelled(Drawable param1Drawable) {
      cacheAndReset(param1Drawable);
    }
    
    protected void onPostExecute(Drawable param1Drawable) {
      cacheAndReset(param1Drawable);
      MediaRouteButton.this.setRemoteIndicatorDrawable(param1Drawable);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */