package android.support.v7.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MediaRouteControllerDialog extends AlertDialog {
  static final int BUTTON_DISCONNECT_RES_ID = 16908314;
  
  private static final int BUTTON_NEUTRAL_RES_ID = 16908315;
  
  static final int BUTTON_STOP_RES_ID = 16908313;
  
  static final int CONNECTION_TIMEOUT_MILLIS;
  
  static final boolean DEBUG = Log.isLoggable("MediaRouteCtrlDialog", 3);
  
  static final String TAG = "MediaRouteCtrlDialog";
  
  static final int VOLUME_UPDATE_DELAY_MILLIS = 500;
  
  private Interpolator mAccelerateDecelerateInterpolator;
  
  final AccessibilityManager mAccessibilityManager;
  
  int mArtIconBackgroundColor;
  
  Bitmap mArtIconBitmap;
  
  boolean mArtIconIsLoaded;
  
  Bitmap mArtIconLoadedBitmap;
  
  Uri mArtIconUri;
  
  private ImageView mArtView;
  
  private boolean mAttachedToWindow;
  
  private final MediaRouterCallback mCallback;
  
  private ImageButton mCloseButton;
  
  Context mContext = getContext();
  
  MediaControllerCallback mControllerCallback = new MediaControllerCallback();
  
  private boolean mCreated;
  
  private FrameLayout mCustomControlLayout;
  
  private View mCustomControlView;
  
  FrameLayout mDefaultControlLayout;
  
  MediaDescriptionCompat mDescription;
  
  private LinearLayout mDialogAreaLayout;
  
  private int mDialogContentWidth;
  
  private Button mDisconnectButton;
  
  private View mDividerView;
  
  private FrameLayout mExpandableAreaLayout;
  
  private Interpolator mFastOutSlowInInterpolator;
  
  FetchArtTask mFetchArtTask;
  
  private MediaRouteExpandCollapseButton mGroupExpandCollapseButton;
  
  int mGroupListAnimationDurationMs;
  
  Runnable mGroupListFadeInAnimation = new Runnable() {
      public void run() {
        MediaRouteControllerDialog.this.startGroupListFadeInAnimation();
      }
    };
  
  private int mGroupListFadeInDurationMs;
  
  private int mGroupListFadeOutDurationMs;
  
  private List<MediaRouter.RouteInfo> mGroupMemberRoutes;
  
  Set<MediaRouter.RouteInfo> mGroupMemberRoutesAdded;
  
  Set<MediaRouter.RouteInfo> mGroupMemberRoutesAnimatingWithBitmap;
  
  private Set<MediaRouter.RouteInfo> mGroupMemberRoutesRemoved;
  
  boolean mHasPendingUpdate;
  
  private Interpolator mInterpolator;
  
  boolean mIsGroupExpanded;
  
  boolean mIsGroupListAnimating;
  
  boolean mIsGroupListAnimationPending;
  
  private Interpolator mLinearOutSlowInInterpolator;
  
  MediaControllerCompat mMediaController;
  
  private LinearLayout mMediaMainControlLayout;
  
  boolean mPendingUpdateAnimationNeeded;
  
  private ImageButton mPlaybackControlButton;
  
  private RelativeLayout mPlaybackControlLayout;
  
  final MediaRouter.RouteInfo mRoute;
  
  MediaRouter.RouteInfo mRouteInVolumeSliderTouched;
  
  private TextView mRouteNameTextView;
  
  final MediaRouter mRouter;
  
  PlaybackStateCompat mState;
  
  private Button mStopCastingButton;
  
  private TextView mSubtitleView;
  
  private TextView mTitleView;
  
  VolumeChangeListener mVolumeChangeListener;
  
  private boolean mVolumeControlEnabled = true;
  
  private LinearLayout mVolumeControlLayout;
  
  VolumeGroupAdapter mVolumeGroupAdapter;
  
  OverlayListView mVolumeGroupList;
  
  private int mVolumeGroupListItemHeight;
  
  private int mVolumeGroupListItemIconSize;
  
  private int mVolumeGroupListMaxHeight;
  
  private final int mVolumeGroupListPaddingTop;
  
  SeekBar mVolumeSlider;
  
  Map<MediaRouter.RouteInfo, SeekBar> mVolumeSliderMap;
  
  static {
    CONNECTION_TIMEOUT_MILLIS = (int)TimeUnit.SECONDS.toMillis(30L);
  }
  
  public MediaRouteControllerDialog(Context paramContext) {
    this(paramContext, 0);
  }
  
  public MediaRouteControllerDialog(Context paramContext, int paramInt) {
    super(context, i);
    MediaRouter mediaRouter = MediaRouter.getInstance(this.mContext);
    this.mRouter = mediaRouter;
    this.mCallback = new MediaRouterCallback();
    this.mRoute = mediaRouter.getSelectedRoute();
    setMediaSession(mediaRouter.getMediaSessionToken());
    this.mVolumeGroupListPaddingTop = this.mContext.getResources().getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_padding_top);
    this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
    if (Build.VERSION.SDK_INT >= 21) {
      this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(paramContext, R.interpolator.mr_linear_out_slow_in);
      this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(paramContext, R.interpolator.mr_fast_out_slow_in);
    } 
    this.mAccelerateDecelerateInterpolator = (Interpolator)new AccelerateDecelerateInterpolator();
  }
  
  private void animateGroupListItems(final Map<MediaRouter.RouteInfo, Rect> previousRouteBoundMap, final Map<MediaRouter.RouteInfo, BitmapDrawable> previousRouteBitmapMap) {
    this.mVolumeGroupList.setEnabled(false);
    this.mVolumeGroupList.requestLayout();
    this.mIsGroupListAnimating = true;
    this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog.this.animateGroupListItemsInternal(previousRouteBoundMap, previousRouteBitmapMap);
          }
        });
  }
  
  private void animateLayoutHeight(final View view, final int endValue) {
    Animation animation = new Animation() {
        protected void applyTransformation(float param1Float, Transformation param1Transformation) {
          int i = startValue;
          int j = (int)((i - endValue) * param1Float);
          MediaRouteControllerDialog.setLayoutHeight(view, i - j);
        }
      };
    animation.setDuration(this.mGroupListAnimationDurationMs);
    if (Build.VERSION.SDK_INT >= 21)
      animation.setInterpolator(this.mInterpolator); 
    view.startAnimation(animation);
  }
  
  private boolean canShowPlaybackControlLayout() {
    boolean bool;
    if (this.mCustomControlView == null && (this.mDescription != null || this.mState != null)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void fadeInAddedRoutes() {
    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        public void onAnimationEnd(Animation param1Animation) {
          MediaRouteControllerDialog.this.finishAnimation(true);
        }
        
        public void onAnimationRepeat(Animation param1Animation) {}
        
        public void onAnimationStart(Animation param1Animation) {}
      };
    int i = this.mVolumeGroupList.getFirstVisiblePosition();
    byte b = 0;
    boolean bool;
    for (bool = false; b < this.mVolumeGroupList.getChildCount(); bool = bool1) {
      View view = this.mVolumeGroupList.getChildAt(b);
      MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(i + b);
      boolean bool1 = bool;
      if (this.mGroupMemberRoutesAdded.contains(routeInfo)) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 1.0F);
        alphaAnimation.setDuration(this.mGroupListFadeInDurationMs);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
        bool1 = bool;
        if (!bool) {
          alphaAnimation.setAnimationListener(animationListener);
          bool1 = true;
        } 
        view.clearAnimation();
        view.startAnimation((Animation)alphaAnimation);
      } 
      b++;
    } 
  }
  
  private MediaRouter.RouteGroup getGroup() {
    MediaRouter.RouteInfo routeInfo = this.mRoute;
    return (routeInfo instanceof MediaRouter.RouteGroup) ? (MediaRouter.RouteGroup)routeInfo : null;
  }
  
  private static int getLayoutHeight(View paramView) {
    return (paramView.getLayoutParams()).height;
  }
  
  private int getMainControllerHeight(boolean paramBoolean) {
    int i = 0;
    if (paramBoolean || this.mVolumeControlLayout.getVisibility() == 0) {
      int j = 0 + this.mMediaMainControlLayout.getPaddingTop() + this.mMediaMainControlLayout.getPaddingBottom();
      i = j;
      if (paramBoolean)
        i = j + this.mPlaybackControlLayout.getMeasuredHeight(); 
      j = i;
      if (this.mVolumeControlLayout.getVisibility() == 0)
        j = i + this.mVolumeControlLayout.getMeasuredHeight(); 
      i = j;
      if (paramBoolean) {
        i = j;
        if (this.mVolumeControlLayout.getVisibility() == 0)
          i = j + this.mDividerView.getMeasuredHeight(); 
      } 
    } 
    return i;
  }
  
  private boolean isBitmapRecycled(Bitmap paramBitmap) {
    boolean bool;
    if (paramBitmap != null && paramBitmap.isRecycled()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isIconChanged() {
    Bitmap bitmap1;
    Bitmap bitmap2;
    Uri uri2;
    MediaDescriptionCompat mediaDescriptionCompat1 = this.mDescription;
    Uri uri1 = null;
    if (mediaDescriptionCompat1 == null) {
      mediaDescriptionCompat1 = null;
    } else {
      bitmap1 = mediaDescriptionCompat1.getIconBitmap();
    } 
    MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
    if (mediaDescriptionCompat2 != null)
      uri1 = mediaDescriptionCompat2.getIconUri(); 
    FetchArtTask fetchArtTask1 = this.mFetchArtTask;
    if (fetchArtTask1 == null) {
      bitmap2 = this.mArtIconBitmap;
    } else {
      bitmap2 = bitmap2.getIconBitmap();
    } 
    FetchArtTask fetchArtTask2 = this.mFetchArtTask;
    if (fetchArtTask2 == null) {
      uri2 = this.mArtIconUri;
    } else {
      uri2 = uri2.getIconUri();
    } 
    return (bitmap2 != bitmap1) ? true : ((bitmap2 == null && !uriEquals(uri2, uri1)));
  }
  
  private boolean isPauseActionSupported() {
    boolean bool;
    if ((this.mState.getActions() & 0x202L) != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isPlayActionSupported() {
    boolean bool;
    if ((this.mState.getActions() & 0x204L) != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isStopActionSupported() {
    boolean bool;
    if ((this.mState.getActions() & 0x1L) != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void rebuildVolumeGroupList(boolean paramBoolean) {
    List<MediaRouter.RouteInfo> list;
    if (getGroup() == null) {
      list = null;
    } else {
      list = getGroup().getRoutes();
    } 
    if (list == null) {
      this.mGroupMemberRoutes.clear();
      this.mVolumeGroupAdapter.notifyDataSetChanged();
    } else if (MediaRouteDialogHelper.listUnorderedEquals(this.mGroupMemberRoutes, list)) {
      this.mVolumeGroupAdapter.notifyDataSetChanged();
    } else {
      Map map1;
      Map map2;
      if (paramBoolean) {
        map1 = MediaRouteDialogHelper.getItemBoundMap(this.mVolumeGroupList, this.mVolumeGroupAdapter);
      } else {
        map1 = null;
      } 
      if (paramBoolean) {
        map2 = MediaRouteDialogHelper.getItemBitmapMap(this.mContext, this.mVolumeGroupList, this.mVolumeGroupAdapter);
      } else {
        map2 = null;
      } 
      this.mGroupMemberRoutesAdded = MediaRouteDialogHelper.getItemsAdded(this.mGroupMemberRoutes, list);
      this.mGroupMemberRoutesRemoved = MediaRouteDialogHelper.getItemsRemoved(this.mGroupMemberRoutes, list);
      this.mGroupMemberRoutes.addAll(0, this.mGroupMemberRoutesAdded);
      this.mGroupMemberRoutes.removeAll(this.mGroupMemberRoutesRemoved);
      this.mVolumeGroupAdapter.notifyDataSetChanged();
      if (paramBoolean && this.mIsGroupExpanded && this.mGroupMemberRoutesAdded.size() + this.mGroupMemberRoutesRemoved.size() > 0) {
        animateGroupListItems(map1, map2);
      } else {
        this.mGroupMemberRoutesAdded = null;
        this.mGroupMemberRoutesRemoved = null;
      } 
    } 
  }
  
  static void setLayoutHeight(View paramView, int paramInt) {
    ViewGroup.LayoutParams layoutParams = paramView.getLayoutParams();
    layoutParams.height = paramInt;
    paramView.setLayoutParams(layoutParams);
  }
  
  private void setMediaSession(MediaSessionCompat.Token paramToken) {
    MediaMetadataCompat mediaMetadataCompat;
    MediaDescriptionCompat mediaDescriptionCompat;
    PlaybackStateCompat playbackStateCompat;
    MediaControllerCompat mediaControllerCompat3 = this.mMediaController;
    MediaControllerCompat mediaControllerCompat4 = null;
    if (mediaControllerCompat3 != null) {
      mediaControllerCompat3.unregisterCallback(this.mControllerCallback);
      this.mMediaController = null;
    } 
    if (paramToken == null)
      return; 
    if (!this.mAttachedToWindow)
      return; 
    try {
      mediaControllerCompat3 = new MediaControllerCompat();
      this(this.mContext, paramToken);
      this.mMediaController = mediaControllerCompat3;
    } catch (RemoteException remoteException) {
      Log.e("MediaRouteCtrlDialog", "Error creating media controller in setMediaSession.", (Throwable)remoteException);
    } 
    MediaControllerCompat mediaControllerCompat2 = this.mMediaController;
    if (mediaControllerCompat2 != null)
      mediaControllerCompat2.registerCallback(this.mControllerCallback); 
    mediaControllerCompat2 = this.mMediaController;
    if (mediaControllerCompat2 == null) {
      mediaControllerCompat2 = null;
    } else {
      mediaMetadataCompat = mediaControllerCompat2.getMetadata();
    } 
    if (mediaMetadataCompat == null) {
      mediaMetadataCompat = null;
    } else {
      mediaDescriptionCompat = mediaMetadataCompat.getDescription();
    } 
    this.mDescription = mediaDescriptionCompat;
    MediaControllerCompat mediaControllerCompat1 = this.mMediaController;
    if (mediaControllerCompat1 == null) {
      mediaControllerCompat1 = mediaControllerCompat4;
    } else {
      playbackStateCompat = mediaControllerCompat1.getPlaybackState();
    } 
    this.mState = playbackStateCompat;
    updateArtIconIfNeeded();
    update(false);
  }
  
  private void updateMediaControlVisibility(boolean paramBoolean) {
    View view = this.mDividerView;
    int i = this.mVolumeControlLayout.getVisibility();
    boolean bool = false;
    if (i == 0 && paramBoolean) {
      i = 0;
    } else {
      i = 8;
    } 
    view.setVisibility(i);
    LinearLayout linearLayout = this.mMediaMainControlLayout;
    i = bool;
    if (this.mVolumeControlLayout.getVisibility() == 8) {
      i = bool;
      if (!paramBoolean)
        i = 8; 
    } 
    linearLayout.setVisibility(i);
  }
  
  private void updatePlaybackControlLayout() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial canShowPlaybackControlLayout : ()Z
    //   4: ifeq -> 459
    //   7: aload_0
    //   8: getfield mDescription : Landroid/support/v4/media/MediaDescriptionCompat;
    //   11: astore_1
    //   12: aconst_null
    //   13: astore_2
    //   14: aload_1
    //   15: ifnonnull -> 23
    //   18: aconst_null
    //   19: astore_1
    //   20: goto -> 28
    //   23: aload_1
    //   24: invokevirtual getTitle : ()Ljava/lang/CharSequence;
    //   27: astore_1
    //   28: aload_1
    //   29: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   32: istore_3
    //   33: iconst_1
    //   34: istore #4
    //   36: iload_3
    //   37: iconst_1
    //   38: ixor
    //   39: istore #5
    //   41: aload_0
    //   42: getfield mDescription : Landroid/support/v4/media/MediaDescriptionCompat;
    //   45: astore #6
    //   47: aload #6
    //   49: ifnonnull -> 55
    //   52: goto -> 61
    //   55: aload #6
    //   57: invokevirtual getSubtitle : ()Ljava/lang/CharSequence;
    //   60: astore_2
    //   61: aload_2
    //   62: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   65: iconst_1
    //   66: ixor
    //   67: istore #7
    //   69: aload_0
    //   70: getfield mRoute : Landroid/support/v7/media/MediaRouter$RouteInfo;
    //   73: invokevirtual getPresentationDisplayId : ()I
    //   76: istore #8
    //   78: iconst_0
    //   79: istore #9
    //   81: iload #8
    //   83: iconst_m1
    //   84: if_icmpeq -> 106
    //   87: aload_0
    //   88: getfield mTitleView : Landroid/widget/TextView;
    //   91: getstatic android/support/v7/mediarouter/R$string.mr_controller_casting_screen : I
    //   94: invokevirtual setText : (I)V
    //   97: iconst_1
    //   98: istore #8
    //   100: iconst_0
    //   101: istore #5
    //   103: goto -> 217
    //   106: aload_0
    //   107: getfield mState : Landroid/support/v4/media/session/PlaybackStateCompat;
    //   110: astore #6
    //   112: aload #6
    //   114: ifnull -> 204
    //   117: aload #6
    //   119: invokevirtual getState : ()I
    //   122: ifne -> 128
    //   125: goto -> 204
    //   128: iload #5
    //   130: ifne -> 151
    //   133: iload #7
    //   135: ifne -> 151
    //   138: aload_0
    //   139: getfield mTitleView : Landroid/widget/TextView;
    //   142: getstatic android/support/v7/mediarouter/R$string.mr_controller_no_info_available : I
    //   145: invokevirtual setText : (I)V
    //   148: goto -> 97
    //   151: iload #5
    //   153: ifeq -> 170
    //   156: aload_0
    //   157: getfield mTitleView : Landroid/widget/TextView;
    //   160: aload_1
    //   161: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   164: iconst_1
    //   165: istore #5
    //   167: goto -> 173
    //   170: iconst_0
    //   171: istore #5
    //   173: iload #5
    //   175: istore #8
    //   177: iload #7
    //   179: ifeq -> 100
    //   182: aload_0
    //   183: getfield mSubtitleView : Landroid/widget/TextView;
    //   186: aload_2
    //   187: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   190: iconst_1
    //   191: istore #7
    //   193: iload #5
    //   195: istore #8
    //   197: iload #7
    //   199: istore #5
    //   201: goto -> 217
    //   204: aload_0
    //   205: getfield mTitleView : Landroid/widget/TextView;
    //   208: getstatic android/support/v7/mediarouter/R$string.mr_controller_no_media_selected : I
    //   211: invokevirtual setText : (I)V
    //   214: goto -> 97
    //   217: aload_0
    //   218: getfield mTitleView : Landroid/widget/TextView;
    //   221: astore_1
    //   222: iload #8
    //   224: ifeq -> 233
    //   227: iconst_0
    //   228: istore #8
    //   230: goto -> 237
    //   233: bipush #8
    //   235: istore #8
    //   237: aload_1
    //   238: iload #8
    //   240: invokevirtual setVisibility : (I)V
    //   243: aload_0
    //   244: getfield mSubtitleView : Landroid/widget/TextView;
    //   247: astore_1
    //   248: iload #5
    //   250: ifeq -> 259
    //   253: iconst_0
    //   254: istore #5
    //   256: goto -> 263
    //   259: bipush #8
    //   261: istore #5
    //   263: aload_1
    //   264: iload #5
    //   266: invokevirtual setVisibility : (I)V
    //   269: aload_0
    //   270: getfield mState : Landroid/support/v4/media/session/PlaybackStateCompat;
    //   273: astore_1
    //   274: aload_1
    //   275: ifnull -> 459
    //   278: aload_1
    //   279: invokevirtual getState : ()I
    //   282: bipush #6
    //   284: if_icmpeq -> 307
    //   287: aload_0
    //   288: getfield mState : Landroid/support/v4/media/session/PlaybackStateCompat;
    //   291: invokevirtual getState : ()I
    //   294: iconst_3
    //   295: if_icmpne -> 301
    //   298: goto -> 307
    //   301: iconst_0
    //   302: istore #5
    //   304: goto -> 310
    //   307: iconst_1
    //   308: istore #5
    //   310: aload_0
    //   311: getfield mPlaybackControlButton : Landroid/widget/ImageButton;
    //   314: invokevirtual getContext : ()Landroid/content/Context;
    //   317: astore_2
    //   318: iload #5
    //   320: ifeq -> 343
    //   323: aload_0
    //   324: invokespecial isPauseActionSupported : ()Z
    //   327: ifeq -> 343
    //   330: getstatic android/support/v7/mediarouter/R$attr.mediaRoutePauseDrawable : I
    //   333: istore #5
    //   335: getstatic android/support/v7/mediarouter/R$string.mr_controller_pause : I
    //   338: istore #8
    //   340: goto -> 402
    //   343: iload #5
    //   345: ifeq -> 368
    //   348: aload_0
    //   349: invokespecial isStopActionSupported : ()Z
    //   352: ifeq -> 368
    //   355: getstatic android/support/v7/mediarouter/R$attr.mediaRouteStopDrawable : I
    //   358: istore #5
    //   360: getstatic android/support/v7/mediarouter/R$string.mr_controller_stop : I
    //   363: istore #8
    //   365: goto -> 402
    //   368: iload #5
    //   370: ifne -> 393
    //   373: aload_0
    //   374: invokespecial isPlayActionSupported : ()Z
    //   377: ifeq -> 393
    //   380: getstatic android/support/v7/mediarouter/R$attr.mediaRoutePlayDrawable : I
    //   383: istore #5
    //   385: getstatic android/support/v7/mediarouter/R$string.mr_controller_play : I
    //   388: istore #8
    //   390: goto -> 402
    //   393: iconst_0
    //   394: istore #5
    //   396: iconst_0
    //   397: istore #8
    //   399: iconst_0
    //   400: istore #4
    //   402: aload_0
    //   403: getfield mPlaybackControlButton : Landroid/widget/ImageButton;
    //   406: astore_1
    //   407: iload #4
    //   409: ifeq -> 415
    //   412: goto -> 419
    //   415: bipush #8
    //   417: istore #9
    //   419: aload_1
    //   420: iload #9
    //   422: invokevirtual setVisibility : (I)V
    //   425: iload #4
    //   427: ifeq -> 459
    //   430: aload_0
    //   431: getfield mPlaybackControlButton : Landroid/widget/ImageButton;
    //   434: aload_2
    //   435: iload #5
    //   437: invokestatic getThemeResource : (Landroid/content/Context;I)I
    //   440: invokevirtual setImageResource : (I)V
    //   443: aload_0
    //   444: getfield mPlaybackControlButton : Landroid/widget/ImageButton;
    //   447: aload_2
    //   448: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   451: iload #8
    //   453: invokevirtual getText : (I)Ljava/lang/CharSequence;
    //   456: invokevirtual setContentDescription : (Ljava/lang/CharSequence;)V
    //   459: return
  }
  
  private void updateVolumeControlLayout() {
    boolean bool = isVolumeControlAvailable(this.mRoute);
    byte b = 8;
    if (bool) {
      if (this.mVolumeControlLayout.getVisibility() == 8) {
        this.mVolumeControlLayout.setVisibility(0);
        this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
        this.mVolumeSlider.setProgress(this.mRoute.getVolume());
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = this.mGroupExpandCollapseButton;
        if (getGroup() != null)
          b = 0; 
        mediaRouteExpandCollapseButton.setVisibility(b);
      } 
    } else {
      this.mVolumeControlLayout.setVisibility(8);
    } 
  }
  
  private static boolean uriEquals(Uri paramUri1, Uri paramUri2) {
    return (paramUri1 != null && paramUri1.equals(paramUri2)) ? true : ((paramUri1 == null && paramUri2 == null));
  }
  
  void animateGroupListItemsInternal(Map<MediaRouter.RouteInfo, Rect> paramMap, Map<MediaRouter.RouteInfo, BitmapDrawable> paramMap1) {
    Set<MediaRouter.RouteInfo> set = this.mGroupMemberRoutesAdded;
    if (set != null && this.mGroupMemberRoutesRemoved != null) {
      int i = set.size() - this.mGroupMemberRoutesRemoved.size();
      Animation.AnimationListener animationListener = new Animation.AnimationListener() {
          public void onAnimationEnd(Animation param1Animation) {}
          
          public void onAnimationRepeat(Animation param1Animation) {}
          
          public void onAnimationStart(Animation param1Animation) {
            MediaRouteControllerDialog.this.mVolumeGroupList.startAnimationAll();
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.mVolumeGroupList.postDelayed(mediaRouteControllerDialog.mGroupListFadeInAnimation, mediaRouteControllerDialog.mGroupListAnimationDurationMs);
          }
        };
      int j = this.mVolumeGroupList.getFirstVisiblePosition();
      byte b = 0;
      int k;
      for (k = 0; b < this.mVolumeGroupList.getChildCount(); k = n) {
        View view = this.mVolumeGroupList.getChildAt(b);
        final MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(j + b);
        Rect rect = paramMap.get(routeInfo);
        int m = view.getTop();
        if (rect != null) {
          n = rect.top;
        } else {
          n = this.mVolumeGroupListItemHeight * i + m;
        } 
        AnimationSet animationSet = new AnimationSet(true);
        Set<MediaRouter.RouteInfo> set1 = this.mGroupMemberRoutesAdded;
        int i1 = n;
        if (set1 != null) {
          i1 = n;
          if (set1.contains(routeInfo)) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 0.0F);
            alphaAnimation.setDuration(this.mGroupListFadeInDurationMs);
            animationSet.addAnimation((Animation)alphaAnimation);
            i1 = m;
          } 
        } 
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0F, 0.0F, (i1 - m), 0.0F);
        translateAnimation.setDuration(this.mGroupListAnimationDurationMs);
        animationSet.addAnimation((Animation)translateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setFillEnabled(true);
        animationSet.setInterpolator(this.mInterpolator);
        int n = k;
        if (!k) {
          animationSet.setAnimationListener(animationListener);
          n = 1;
        } 
        view.clearAnimation();
        view.startAnimation((Animation)animationSet);
        paramMap.remove(routeInfo);
        paramMap1.remove(routeInfo);
        b++;
      } 
      for (Map.Entry<MediaRouter.RouteInfo, BitmapDrawable> entry : paramMap1.entrySet()) {
        OverlayListView.OverlayObject overlayObject;
        final MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)entry.getKey();
        BitmapDrawable bitmapDrawable = (BitmapDrawable)entry.getValue();
        Rect rect = paramMap.get(routeInfo);
        if (this.mGroupMemberRoutesRemoved.contains(routeInfo)) {
          overlayObject = (new OverlayListView.OverlayObject(bitmapDrawable, rect)).setAlphaAnimation(1.0F, 0.0F).setDuration(this.mGroupListFadeOutDurationMs).setInterpolator(this.mInterpolator);
        } else {
          int m = this.mVolumeGroupListItemHeight;
          overlayObject = (new OverlayListView.OverlayObject((BitmapDrawable)overlayObject, rect)).setTranslateYAnimation(m * i).setDuration(this.mGroupListAnimationDurationMs).setInterpolator(this.mInterpolator).setAnimationEndListener(new OverlayListView.OverlayObject.OnAnimationEndListener() {
                public void onAnimationEnd() {
                  MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.remove(route);
                  MediaRouteControllerDialog.this.mVolumeGroupAdapter.notifyDataSetChanged();
                }
              });
          this.mGroupMemberRoutesAnimatingWithBitmap.add(routeInfo);
        } 
        this.mVolumeGroupList.addOverlayObject(overlayObject);
      } 
    } 
  }
  
  void clearGroupListAnimation(boolean paramBoolean) {
    int i = this.mVolumeGroupList.getFirstVisiblePosition();
    for (byte b = 0; b < this.mVolumeGroupList.getChildCount(); b++) {
      View view = this.mVolumeGroupList.getChildAt(b);
      MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(i + b);
      if (paramBoolean) {
        Set<MediaRouter.RouteInfo> set = this.mGroupMemberRoutesAdded;
        if (set != null && set.contains(routeInfo))
          continue; 
      } 
      ((LinearLayout)view.findViewById(R.id.volume_item_container)).setVisibility(0);
      AnimationSet animationSet = new AnimationSet(true);
      AlphaAnimation alphaAnimation = new AlphaAnimation(1.0F, 1.0F);
      alphaAnimation.setDuration(0L);
      animationSet.addAnimation((Animation)alphaAnimation);
      (new TranslateAnimation(0.0F, 0.0F, 0.0F, 0.0F)).setDuration(0L);
      animationSet.setFillAfter(true);
      animationSet.setFillEnabled(true);
      view.clearAnimation();
      view.startAnimation((Animation)animationSet);
      continue;
    } 
    this.mVolumeGroupList.stopAnimationAll();
    if (!paramBoolean)
      finishAnimation(false); 
  }
  
  void clearLoadedBitmap() {
    this.mArtIconIsLoaded = false;
    this.mArtIconLoadedBitmap = null;
    this.mArtIconBackgroundColor = 0;
  }
  
  void finishAnimation(boolean paramBoolean) {
    this.mGroupMemberRoutesAdded = null;
    this.mGroupMemberRoutesRemoved = null;
    this.mIsGroupListAnimating = false;
    if (this.mIsGroupListAnimationPending) {
      this.mIsGroupListAnimationPending = false;
      updateLayoutHeight(paramBoolean);
    } 
    this.mVolumeGroupList.setEnabled(true);
  }
  
  int getDesiredArtHeight(int paramInt1, int paramInt2) {
    return (paramInt1 >= paramInt2) ? (int)(this.mDialogContentWidth * paramInt2 / paramInt1 + 0.5F) : (int)(this.mDialogContentWidth * 9.0F / 16.0F + 0.5F);
  }
  
  public View getMediaControlView() {
    return this.mCustomControlView;
  }
  
  public MediaSessionCompat.Token getMediaSession() {
    MediaSessionCompat.Token token;
    MediaControllerCompat mediaControllerCompat = this.mMediaController;
    if (mediaControllerCompat == null) {
      mediaControllerCompat = null;
    } else {
      token = mediaControllerCompat.getSessionToken();
    } 
    return token;
  }
  
  public MediaRouter.RouteInfo getRoute() {
    return this.mRoute;
  }
  
  boolean isVolumeControlAvailable(MediaRouter.RouteInfo paramRouteInfo) {
    boolean bool = this.mVolumeControlEnabled;
    boolean bool1 = true;
    if (!bool || paramRouteInfo.getVolumeHandling() != 1)
      bool1 = false; 
    return bool1;
  }
  
  public boolean isVolumeControlEnabled() {
    return this.mVolumeControlEnabled;
  }
  
  void loadInterpolator() {
    if (Build.VERSION.SDK_INT >= 21) {
      Interpolator interpolator;
      if (this.mIsGroupExpanded) {
        interpolator = this.mLinearOutSlowInInterpolator;
      } else {
        interpolator = this.mFastOutSlowInInterpolator;
      } 
      this.mInterpolator = interpolator;
    } else {
      this.mInterpolator = this.mAccelerateDecelerateInterpolator;
    } 
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mAttachedToWindow = true;
    this.mRouter.addCallback(MediaRouteSelector.EMPTY, this.mCallback, 2);
    setMediaSession(this.mRouter.getMediaSessionToken());
  }
  
  protected void onCreate(Bundle paramBundle) {
    boolean bool;
    super.onCreate(paramBundle);
    getWindow().setBackgroundDrawableResource(17170445);
    setContentView(R.layout.mr_controller_material_dialog_b);
    findViewById(16908315).setVisibility(8);
    ClickListener clickListener = new ClickListener();
    FrameLayout frameLayout = findViewById(R.id.mr_expandable_area);
    this.mExpandableAreaLayout = frameLayout;
    frameLayout.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            MediaRouteControllerDialog.this.dismiss();
          }
        });
    LinearLayout linearLayout3 = findViewById(R.id.mr_dialog_area);
    this.mDialogAreaLayout = linearLayout3;
    linearLayout3.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {}
        });
    int i = MediaRouterThemeHelper.getButtonTextColor(this.mContext);
    Button button = findViewById(16908314);
    this.mDisconnectButton = button;
    button.setText(R.string.mr_controller_disconnect);
    this.mDisconnectButton.setTextColor(i);
    this.mDisconnectButton.setOnClickListener(clickListener);
    button = findViewById(16908313);
    this.mStopCastingButton = button;
    button.setText(R.string.mr_controller_stop_casting);
    this.mStopCastingButton.setTextColor(i);
    this.mStopCastingButton.setOnClickListener(clickListener);
    this.mRouteNameTextView = findViewById(R.id.mr_name);
    ImageButton imageButton2 = findViewById(R.id.mr_close);
    this.mCloseButton = imageButton2;
    imageButton2.setOnClickListener(clickListener);
    this.mCustomControlLayout = findViewById(R.id.mr_custom_control);
    this.mDefaultControlLayout = findViewById(R.id.mr_default_control);
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View param1View) {
          MediaControllerCompat mediaControllerCompat = MediaRouteControllerDialog.this.mMediaController;
          if (mediaControllerCompat != null) {
            PendingIntent pendingIntent = mediaControllerCompat.getSessionActivity();
            if (pendingIntent != null)
              try {
                pendingIntent.send();
                MediaRouteControllerDialog.this.dismiss();
              } catch (android.app.PendingIntent.CanceledException canceledException) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(pendingIntent);
                stringBuilder.append(" was not sent, it had been canceled.");
                Log.e("MediaRouteCtrlDialog", stringBuilder.toString());
              }  
          } 
        }
      };
    ImageView imageView = findViewById(R.id.mr_art);
    this.mArtView = imageView;
    imageView.setOnClickListener(onClickListener);
    findViewById(R.id.mr_control_title_container).setOnClickListener(onClickListener);
    this.mMediaMainControlLayout = findViewById(R.id.mr_media_main_control);
    this.mDividerView = findViewById(R.id.mr_control_divider);
    this.mPlaybackControlLayout = findViewById(R.id.mr_playback_control);
    this.mTitleView = findViewById(R.id.mr_control_title);
    this.mSubtitleView = findViewById(R.id.mr_control_subtitle);
    ImageButton imageButton1 = findViewById(R.id.mr_control_playback_ctrl);
    this.mPlaybackControlButton = imageButton1;
    imageButton1.setOnClickListener(clickListener);
    LinearLayout linearLayout2 = findViewById(R.id.mr_volume_control);
    this.mVolumeControlLayout = linearLayout2;
    linearLayout2.setVisibility(8);
    SeekBar seekBar = findViewById(R.id.mr_volume_slider);
    this.mVolumeSlider = seekBar;
    seekBar.setTag(this.mRoute);
    VolumeChangeListener volumeChangeListener = new VolumeChangeListener();
    this.mVolumeChangeListener = volumeChangeListener;
    this.mVolumeSlider.setOnSeekBarChangeListener(volumeChangeListener);
    this.mVolumeGroupList = findViewById(R.id.mr_volume_group_list);
    this.mGroupMemberRoutes = new ArrayList<MediaRouter.RouteInfo>();
    VolumeGroupAdapter volumeGroupAdapter = new VolumeGroupAdapter(this.mVolumeGroupList.getContext(), this.mGroupMemberRoutes);
    this.mVolumeGroupAdapter = volumeGroupAdapter;
    this.mVolumeGroupList.setAdapter((ListAdapter)volumeGroupAdapter);
    this.mGroupMemberRoutesAnimatingWithBitmap = new HashSet<MediaRouter.RouteInfo>();
    Context context = this.mContext;
    LinearLayout linearLayout1 = this.mMediaMainControlLayout;
    OverlayListView overlayListView = this.mVolumeGroupList;
    if (getGroup() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    MediaRouterThemeHelper.setMediaControlsBackgroundColor(context, (View)linearLayout1, (View)overlayListView, bool);
    MediaRouterThemeHelper.setVolumeSliderColor(this.mContext, (MediaRouteVolumeSlider)this.mVolumeSlider, (View)this.mMediaMainControlLayout);
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    this.mVolumeSliderMap = (Map)hashMap;
    hashMap.put(this.mRoute, this.mVolumeSlider);
    MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = findViewById(R.id.mr_group_expand_collapse);
    this.mGroupExpandCollapseButton = mediaRouteExpandCollapseButton;
    mediaRouteExpandCollapseButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            int i = mediaRouteControllerDialog.mIsGroupExpanded ^ true;
            mediaRouteControllerDialog.mIsGroupExpanded = i;
            if (i != 0)
              mediaRouteControllerDialog.mVolumeGroupList.setVisibility(0); 
            MediaRouteControllerDialog.this.loadInterpolator();
            MediaRouteControllerDialog.this.updateLayoutHeight(true);
          }
        });
    loadInterpolator();
    this.mGroupListAnimationDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_animation_duration_ms);
    this.mGroupListFadeInDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_in_duration_ms);
    this.mGroupListFadeOutDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_out_duration_ms);
    View view = onCreateMediaControlView(paramBundle);
    this.mCustomControlView = view;
    if (view != null) {
      this.mCustomControlLayout.addView(view);
      this.mCustomControlLayout.setVisibility(0);
    } 
    this.mCreated = true;
    updateLayout();
  }
  
  public View onCreateMediaControlView(Bundle paramBundle) {
    return null;
  }
  
  public void onDetachedFromWindow() {
    this.mRouter.removeCallback(this.mCallback);
    setMediaSession(null);
    this.mAttachedToWindow = false;
    super.onDetachedFromWindow();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    MediaRouter.RouteInfo routeInfo;
    if (paramInt == 25 || paramInt == 24) {
      routeInfo = this.mRoute;
      if (paramInt == 25) {
        paramInt = -1;
      } else {
        paramInt = 1;
      } 
      routeInfo.requestUpdateVolume(paramInt);
      return true;
    } 
    return super.onKeyDown(paramInt, (KeyEvent)routeInfo);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    return (paramInt == 25 || paramInt == 24) ? true : super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void setVolumeControlEnabled(boolean paramBoolean) {
    if (this.mVolumeControlEnabled != paramBoolean) {
      this.mVolumeControlEnabled = paramBoolean;
      if (this.mCreated)
        update(false); 
    } 
  }
  
  void startGroupListFadeInAnimation() {
    clearGroupListAnimation(true);
    this.mVolumeGroupList.requestLayout();
    this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog.this.startGroupListFadeInAnimationInternal();
          }
        });
  }
  
  void startGroupListFadeInAnimationInternal() {
    Set<MediaRouter.RouteInfo> set = this.mGroupMemberRoutesAdded;
    if (set != null && set.size() != 0) {
      fadeInAddedRoutes();
    } else {
      finishAnimation(true);
    } 
  }
  
  void update(boolean paramBoolean) {
    if (this.mRouteInVolumeSliderTouched != null) {
      this.mHasPendingUpdate = true;
      this.mPendingUpdateAnimationNeeded = paramBoolean | this.mPendingUpdateAnimationNeeded;
      return;
    } 
    byte b = 0;
    this.mHasPendingUpdate = false;
    this.mPendingUpdateAnimationNeeded = false;
    if (!this.mRoute.isSelected() || this.mRoute.isDefaultOrBluetooth()) {
      dismiss();
      return;
    } 
    if (!this.mCreated)
      return; 
    this.mRouteNameTextView.setText(this.mRoute.getName());
    Button button = this.mDisconnectButton;
    if (!this.mRoute.canDisconnect())
      b = 8; 
    button.setVisibility(b);
    if (this.mCustomControlView == null && this.mArtIconIsLoaded) {
      if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Can't set artwork image with recycled bitmap: ");
        stringBuilder.append(this.mArtIconLoadedBitmap);
        Log.w("MediaRouteCtrlDialog", stringBuilder.toString());
      } else {
        this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
        this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
      } 
      clearLoadedBitmap();
    } 
    updateVolumeControlLayout();
    updatePlaybackControlLayout();
    updateLayoutHeight(paramBoolean);
  }
  
  void updateArtIconIfNeeded() {
    if (this.mCustomControlView == null && isIconChanged()) {
      FetchArtTask fetchArtTask = this.mFetchArtTask;
      if (fetchArtTask != null)
        fetchArtTask.cancel(true); 
      fetchArtTask = new FetchArtTask();
      this.mFetchArtTask = fetchArtTask;
      fetchArtTask.execute((Object[])new Void[0]);
    } 
  }
  
  void updateLayout() {
    int i = MediaRouteDialogHelper.getDialogWidth(this.mContext);
    getWindow().setLayout(i, -2);
    View view = getWindow().getDecorView();
    this.mDialogContentWidth = i - view.getPaddingLeft() - view.getPaddingRight();
    Resources resources = this.mContext.getResources();
    this.mVolumeGroupListItemIconSize = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_icon_size);
    this.mVolumeGroupListItemHeight = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_height);
    this.mVolumeGroupListMaxHeight = resources.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_max_height);
    this.mArtIconBitmap = null;
    this.mArtIconUri = null;
    updateArtIconIfNeeded();
    update(false);
  }
  
  void updateLayoutHeight(final boolean animate) {
    this.mDefaultControlLayout.requestLayout();
    this.mDefaultControlLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mDefaultControlLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            if (mediaRouteControllerDialog.mIsGroupListAnimating) {
              mediaRouteControllerDialog.mIsGroupListAnimationPending = true;
            } else {
              mediaRouteControllerDialog.updateLayoutHeightInternal(animate);
            } 
          }
        });
  }
  
  void updateLayoutHeightInternal(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   4: invokestatic getLayoutHeight : (Landroid/view/View;)I
    //   7: istore_2
    //   8: aload_0
    //   9: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   12: iconst_m1
    //   13: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   16: aload_0
    //   17: aload_0
    //   18: invokespecial canShowPlaybackControlLayout : ()Z
    //   21: invokespecial updateMediaControlVisibility : (Z)V
    //   24: aload_0
    //   25: invokevirtual getWindow : ()Landroid/view/Window;
    //   28: invokevirtual getDecorView : ()Landroid/view/View;
    //   31: astore_3
    //   32: aload_0
    //   33: invokevirtual getWindow : ()Landroid/view/Window;
    //   36: invokevirtual getAttributes : ()Landroid/view/WindowManager$LayoutParams;
    //   39: getfield width : I
    //   42: ldc_w 1073741824
    //   45: invokestatic makeMeasureSpec : (II)I
    //   48: istore #4
    //   50: iconst_0
    //   51: istore #5
    //   53: aload_3
    //   54: iload #4
    //   56: iconst_0
    //   57: invokevirtual measure : (II)V
    //   60: aload_0
    //   61: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   64: iload_2
    //   65: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   68: aload_0
    //   69: getfield mCustomControlView : Landroid/view/View;
    //   72: ifnonnull -> 165
    //   75: aload_0
    //   76: getfield mArtView : Landroid/widget/ImageView;
    //   79: invokevirtual getDrawable : ()Landroid/graphics/drawable/Drawable;
    //   82: instanceof android/graphics/drawable/BitmapDrawable
    //   85: ifeq -> 165
    //   88: aload_0
    //   89: getfield mArtView : Landroid/widget/ImageView;
    //   92: invokevirtual getDrawable : ()Landroid/graphics/drawable/Drawable;
    //   95: checkcast android/graphics/drawable/BitmapDrawable
    //   98: invokevirtual getBitmap : ()Landroid/graphics/Bitmap;
    //   101: astore #6
    //   103: aload #6
    //   105: ifnull -> 165
    //   108: aload_0
    //   109: aload #6
    //   111: invokevirtual getWidth : ()I
    //   114: aload #6
    //   116: invokevirtual getHeight : ()I
    //   119: invokevirtual getDesiredArtHeight : (II)I
    //   122: istore_2
    //   123: aload_0
    //   124: getfield mArtView : Landroid/widget/ImageView;
    //   127: astore #7
    //   129: aload #6
    //   131: invokevirtual getWidth : ()I
    //   134: aload #6
    //   136: invokevirtual getHeight : ()I
    //   139: if_icmplt -> 150
    //   142: getstatic android/widget/ImageView$ScaleType.FIT_XY : Landroid/widget/ImageView$ScaleType;
    //   145: astore #6
    //   147: goto -> 155
    //   150: getstatic android/widget/ImageView$ScaleType.FIT_CENTER : Landroid/widget/ImageView$ScaleType;
    //   153: astore #6
    //   155: aload #7
    //   157: aload #6
    //   159: invokevirtual setScaleType : (Landroid/widget/ImageView$ScaleType;)V
    //   162: goto -> 167
    //   165: iconst_0
    //   166: istore_2
    //   167: aload_0
    //   168: aload_0
    //   169: invokespecial canShowPlaybackControlLayout : ()Z
    //   172: invokespecial getMainControllerHeight : (Z)I
    //   175: istore #8
    //   177: aload_0
    //   178: getfield mGroupMemberRoutes : Ljava/util/List;
    //   181: invokeinterface size : ()I
    //   186: istore #9
    //   188: aload_0
    //   189: invokespecial getGroup : ()Landroid/support/v7/media/MediaRouter$RouteGroup;
    //   192: ifnonnull -> 201
    //   195: iconst_0
    //   196: istore #4
    //   198: goto -> 220
    //   201: aload_0
    //   202: getfield mVolumeGroupListItemHeight : I
    //   205: aload_0
    //   206: invokespecial getGroup : ()Landroid/support/v7/media/MediaRouter$RouteGroup;
    //   209: invokevirtual getRoutes : ()Ljava/util/List;
    //   212: invokeinterface size : ()I
    //   217: imul
    //   218: istore #4
    //   220: iload #4
    //   222: istore #10
    //   224: iload #9
    //   226: ifle -> 238
    //   229: iload #4
    //   231: aload_0
    //   232: getfield mVolumeGroupListPaddingTop : I
    //   235: iadd
    //   236: istore #10
    //   238: iload #10
    //   240: aload_0
    //   241: getfield mVolumeGroupListMaxHeight : I
    //   244: invokestatic min : (II)I
    //   247: istore #4
    //   249: aload_0
    //   250: getfield mIsGroupExpanded : Z
    //   253: ifeq -> 259
    //   256: goto -> 262
    //   259: iconst_0
    //   260: istore #4
    //   262: iload_2
    //   263: iload #4
    //   265: invokestatic max : (II)I
    //   268: iload #8
    //   270: iadd
    //   271: istore #10
    //   273: new android/graphics/Rect
    //   276: dup
    //   277: invokespecial <init> : ()V
    //   280: astore #6
    //   282: aload_3
    //   283: aload #6
    //   285: invokevirtual getWindowVisibleDisplayFrame : (Landroid/graphics/Rect;)V
    //   288: aload_0
    //   289: getfield mDialogAreaLayout : Landroid/widget/LinearLayout;
    //   292: invokevirtual getMeasuredHeight : ()I
    //   295: istore #11
    //   297: aload_0
    //   298: getfield mDefaultControlLayout : Landroid/widget/FrameLayout;
    //   301: invokevirtual getMeasuredHeight : ()I
    //   304: istore #9
    //   306: aload #6
    //   308: invokevirtual height : ()I
    //   311: iload #11
    //   313: iload #9
    //   315: isub
    //   316: isub
    //   317: istore #9
    //   319: aload_0
    //   320: getfield mCustomControlView : Landroid/view/View;
    //   323: ifnonnull -> 356
    //   326: iload_2
    //   327: ifle -> 356
    //   330: iload #10
    //   332: iload #9
    //   334: if_icmpgt -> 356
    //   337: aload_0
    //   338: getfield mArtView : Landroid/widget/ImageView;
    //   341: iconst_0
    //   342: invokevirtual setVisibility : (I)V
    //   345: aload_0
    //   346: getfield mArtView : Landroid/widget/ImageView;
    //   349: iload_2
    //   350: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   353: goto -> 399
    //   356: aload_0
    //   357: getfield mVolumeGroupList : Landroid/support/v7/app/OverlayListView;
    //   360: invokestatic getLayoutHeight : (Landroid/view/View;)I
    //   363: aload_0
    //   364: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   367: invokevirtual getMeasuredHeight : ()I
    //   370: iadd
    //   371: aload_0
    //   372: getfield mDefaultControlLayout : Landroid/widget/FrameLayout;
    //   375: invokevirtual getMeasuredHeight : ()I
    //   378: if_icmplt -> 390
    //   381: aload_0
    //   382: getfield mArtView : Landroid/widget/ImageView;
    //   385: bipush #8
    //   387: invokevirtual setVisibility : (I)V
    //   390: iload #4
    //   392: iload #8
    //   394: iadd
    //   395: istore #10
    //   397: iconst_0
    //   398: istore_2
    //   399: aload_0
    //   400: invokespecial canShowPlaybackControlLayout : ()Z
    //   403: ifeq -> 424
    //   406: iload #10
    //   408: iload #9
    //   410: if_icmpgt -> 424
    //   413: aload_0
    //   414: getfield mPlaybackControlLayout : Landroid/widget/RelativeLayout;
    //   417: iconst_0
    //   418: invokevirtual setVisibility : (I)V
    //   421: goto -> 433
    //   424: aload_0
    //   425: getfield mPlaybackControlLayout : Landroid/widget/RelativeLayout;
    //   428: bipush #8
    //   430: invokevirtual setVisibility : (I)V
    //   433: aload_0
    //   434: getfield mPlaybackControlLayout : Landroid/widget/RelativeLayout;
    //   437: invokevirtual getVisibility : ()I
    //   440: ifne -> 449
    //   443: iconst_1
    //   444: istore #12
    //   446: goto -> 452
    //   449: iconst_0
    //   450: istore #12
    //   452: aload_0
    //   453: iload #12
    //   455: invokespecial updateMediaControlVisibility : (Z)V
    //   458: iload #5
    //   460: istore #12
    //   462: aload_0
    //   463: getfield mPlaybackControlLayout : Landroid/widget/RelativeLayout;
    //   466: invokevirtual getVisibility : ()I
    //   469: ifne -> 475
    //   472: iconst_1
    //   473: istore #12
    //   475: aload_0
    //   476: iload #12
    //   478: invokespecial getMainControllerHeight : (Z)I
    //   481: istore #10
    //   483: iload_2
    //   484: iload #4
    //   486: invokestatic max : (II)I
    //   489: iload #10
    //   491: iadd
    //   492: istore_2
    //   493: iload_2
    //   494: iload #9
    //   496: if_icmple -> 514
    //   499: iload #4
    //   501: iload_2
    //   502: iload #9
    //   504: isub
    //   505: isub
    //   506: istore #4
    //   508: iload #9
    //   510: istore_2
    //   511: goto -> 514
    //   514: aload_0
    //   515: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   518: invokevirtual clearAnimation : ()V
    //   521: aload_0
    //   522: getfield mVolumeGroupList : Landroid/support/v7/app/OverlayListView;
    //   525: invokevirtual clearAnimation : ()V
    //   528: aload_0
    //   529: getfield mDefaultControlLayout : Landroid/widget/FrameLayout;
    //   532: invokevirtual clearAnimation : ()V
    //   535: iload_1
    //   536: ifeq -> 571
    //   539: aload_0
    //   540: aload_0
    //   541: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   544: iload #10
    //   546: invokespecial animateLayoutHeight : (Landroid/view/View;I)V
    //   549: aload_0
    //   550: aload_0
    //   551: getfield mVolumeGroupList : Landroid/support/v7/app/OverlayListView;
    //   554: iload #4
    //   556: invokespecial animateLayoutHeight : (Landroid/view/View;I)V
    //   559: aload_0
    //   560: aload_0
    //   561: getfield mDefaultControlLayout : Landroid/widget/FrameLayout;
    //   564: iload_2
    //   565: invokespecial animateLayoutHeight : (Landroid/view/View;I)V
    //   568: goto -> 597
    //   571: aload_0
    //   572: getfield mMediaMainControlLayout : Landroid/widget/LinearLayout;
    //   575: iload #10
    //   577: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   580: aload_0
    //   581: getfield mVolumeGroupList : Landroid/support/v7/app/OverlayListView;
    //   584: iload #4
    //   586: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   589: aload_0
    //   590: getfield mDefaultControlLayout : Landroid/widget/FrameLayout;
    //   593: iload_2
    //   594: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   597: aload_0
    //   598: getfield mExpandableAreaLayout : Landroid/widget/FrameLayout;
    //   601: aload #6
    //   603: invokevirtual height : ()I
    //   606: invokestatic setLayoutHeight : (Landroid/view/View;I)V
    //   609: aload_0
    //   610: iload_1
    //   611: invokespecial rebuildVolumeGroupList : (Z)V
    //   614: return
  }
  
  void updateVolumeGroupItemHeight(View paramView) {
    setLayoutHeight(paramView.findViewById(R.id.volume_item_container), this.mVolumeGroupListItemHeight);
    View view = paramView.findViewById(R.id.mr_volume_item_icon);
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    int i = this.mVolumeGroupListItemIconSize;
    layoutParams.width = i;
    layoutParams.height = i;
    view.setLayoutParams(layoutParams);
  }
  
  private final class ClickListener implements View.OnClickListener {
    public void onClick(View param1View) {
      int i = param1View.getId();
      int j = 1;
      boolean bool = true;
      if (i == 16908313 || i == 16908314) {
        if (MediaRouteControllerDialog.this.mRoute.isSelected()) {
          MediaRouter mediaRouter = MediaRouteControllerDialog.this.mRouter;
          if (i == 16908313)
            j = 2; 
          mediaRouter.unselect(j);
        } 
        MediaRouteControllerDialog.this.dismiss();
        return;
      } 
      if (i == R.id.mr_control_playback_ctrl) {
        MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
        if (mediaRouteControllerDialog.mMediaController != null) {
          PlaybackStateCompat playbackStateCompat = mediaRouteControllerDialog.mState;
          if (playbackStateCompat != null) {
            j = playbackStateCompat.getState();
            i = 0;
            if (j != 3)
              bool = false; 
            if (bool && MediaRouteControllerDialog.this.isPauseActionSupported()) {
              MediaRouteControllerDialog.this.mMediaController.getTransportControls().pause();
              j = R.string.mr_controller_pause;
            } else if (bool && MediaRouteControllerDialog.this.isStopActionSupported()) {
              MediaRouteControllerDialog.this.mMediaController.getTransportControls().stop();
              j = R.string.mr_controller_stop;
            } else {
              j = i;
              if (!bool) {
                j = i;
                if (MediaRouteControllerDialog.this.isPlayActionSupported()) {
                  MediaRouteControllerDialog.this.mMediaController.getTransportControls().play();
                  j = R.string.mr_controller_play;
                } 
              } 
            } 
            AccessibilityManager accessibilityManager = MediaRouteControllerDialog.this.mAccessibilityManager;
            if (accessibilityManager != null && accessibilityManager.isEnabled() && j != 0) {
              AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain(16384);
              accessibilityEvent.setPackageName(MediaRouteControllerDialog.this.mContext.getPackageName());
              accessibilityEvent.setClassName(ClickListener.class.getName());
              accessibilityEvent.getText().add(MediaRouteControllerDialog.this.mContext.getString(j));
              MediaRouteControllerDialog.this.mAccessibilityManager.sendAccessibilityEvent(accessibilityEvent);
            } 
          } 
        } 
      } else if (i == R.id.mr_close) {
        MediaRouteControllerDialog.this.dismiss();
      } 
    }
  }
  
  private class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
    private static final long SHOW_ANIM_TIME_THRESHOLD_MILLIS = 120L;
    
    private int mBackgroundColor;
    
    private final Bitmap mIconBitmap;
    
    private final Uri mIconUri;
    
    private long mStartTimeMillis;
    
    FetchArtTask() {
      Uri uri;
      Bitmap bitmap1;
      MediaDescriptionCompat mediaDescriptionCompat2 = MediaRouteControllerDialog.this.mDescription;
      MediaDescriptionCompat mediaDescriptionCompat3 = null;
      if (mediaDescriptionCompat2 == null) {
        mediaDescriptionCompat2 = null;
      } else {
        bitmap1 = mediaDescriptionCompat2.getIconBitmap();
      } 
      Bitmap bitmap2 = bitmap1;
      if (MediaRouteControllerDialog.this.isBitmapRecycled(bitmap1)) {
        Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
        bitmap2 = null;
      } 
      this.mIconBitmap = bitmap2;
      MediaDescriptionCompat mediaDescriptionCompat1 = MediaRouteControllerDialog.this.mDescription;
      if (mediaDescriptionCompat1 == null) {
        mediaDescriptionCompat1 = mediaDescriptionCompat3;
      } else {
        uri = mediaDescriptionCompat1.getIconUri();
      } 
      this.mIconUri = uri;
    }
    
    private InputStream openInputStreamByScheme(Uri param1Uri) throws IOException {
      InputStream inputStream;
      String str = param1Uri.getScheme().toLowerCase();
      if ("android.resource".equals(str) || "content".equals(str) || "file".equals(str)) {
        inputStream = MediaRouteControllerDialog.this.mContext.getContentResolver().openInputStream(param1Uri);
      } else {
        URLConnection uRLConnection = (new URL(inputStream.toString())).openConnection();
        int i = MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS;
        uRLConnection.setConnectTimeout(i);
        uRLConnection.setReadTimeout(i);
        inputStream = uRLConnection.getInputStream();
      } 
      if (inputStream == null) {
        inputStream = null;
      } else {
        inputStream = new BufferedInputStream(inputStream);
      } 
      return inputStream;
    }
    
    protected Bitmap doInBackground(Void... param1VarArgs) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mIconBitmap : Landroid/graphics/Bitmap;
      //   4: astore_1
      //   5: iconst_0
      //   6: istore_2
      //   7: aconst_null
      //   8: astore_3
      //   9: aload_1
      //   10: ifnull -> 16
      //   13: goto -> 554
      //   16: aload_0
      //   17: getfield mIconUri : Landroid/net/Uri;
      //   20: astore_1
      //   21: aload_1
      //   22: ifnull -> 552
      //   25: aload_0
      //   26: aload_1
      //   27: invokespecial openInputStreamByScheme : (Landroid/net/Uri;)Ljava/io/InputStream;
      //   30: astore #4
      //   32: aload #4
      //   34: ifnonnull -> 118
      //   37: aload #4
      //   39: astore_3
      //   40: aload #4
      //   42: astore_1
      //   43: new java/lang/StringBuilder
      //   46: astore #5
      //   48: aload #4
      //   50: astore_3
      //   51: aload #4
      //   53: astore_1
      //   54: aload #5
      //   56: invokespecial <init> : ()V
      //   59: aload #4
      //   61: astore_3
      //   62: aload #4
      //   64: astore_1
      //   65: aload #5
      //   67: ldc 'Unable to open: '
      //   69: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   72: pop
      //   73: aload #4
      //   75: astore_3
      //   76: aload #4
      //   78: astore_1
      //   79: aload #5
      //   81: aload_0
      //   82: getfield mIconUri : Landroid/net/Uri;
      //   85: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   88: pop
      //   89: aload #4
      //   91: astore_3
      //   92: aload #4
      //   94: astore_1
      //   95: ldc 'MediaRouteCtrlDialog'
      //   97: aload #5
      //   99: invokevirtual toString : ()Ljava/lang/String;
      //   102: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   105: pop
      //   106: aload #4
      //   108: ifnull -> 116
      //   111: aload #4
      //   113: invokevirtual close : ()V
      //   116: aconst_null
      //   117: areturn
      //   118: aload #4
      //   120: astore_3
      //   121: aload #4
      //   123: astore_1
      //   124: new android/graphics/BitmapFactory$Options
      //   127: astore #6
      //   129: aload #4
      //   131: astore_3
      //   132: aload #4
      //   134: astore_1
      //   135: aload #6
      //   137: invokespecial <init> : ()V
      //   140: aload #4
      //   142: astore_3
      //   143: aload #4
      //   145: astore_1
      //   146: aload #6
      //   148: iconst_1
      //   149: putfield inJustDecodeBounds : Z
      //   152: aload #4
      //   154: astore_3
      //   155: aload #4
      //   157: astore_1
      //   158: aload #4
      //   160: aconst_null
      //   161: aload #6
      //   163: invokestatic decodeStream : (Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   166: pop
      //   167: aload #4
      //   169: astore_3
      //   170: aload #4
      //   172: astore_1
      //   173: aload #6
      //   175: getfield outWidth : I
      //   178: ifeq -> 449
      //   181: aload #4
      //   183: astore_3
      //   184: aload #4
      //   186: astore_1
      //   187: aload #6
      //   189: getfield outHeight : I
      //   192: istore #7
      //   194: iload #7
      //   196: ifne -> 202
      //   199: goto -> 449
      //   202: aload #4
      //   204: astore_1
      //   205: aload #4
      //   207: invokevirtual reset : ()V
      //   210: goto -> 331
      //   213: astore_1
      //   214: aload #4
      //   216: astore_3
      //   217: aload #4
      //   219: astore_1
      //   220: aload #4
      //   222: invokevirtual close : ()V
      //   225: aload #4
      //   227: astore_3
      //   228: aload #4
      //   230: astore_1
      //   231: aload_0
      //   232: aload_0
      //   233: getfield mIconUri : Landroid/net/Uri;
      //   236: invokespecial openInputStreamByScheme : (Landroid/net/Uri;)Ljava/io/InputStream;
      //   239: astore #5
      //   241: aload #5
      //   243: astore #4
      //   245: aload #5
      //   247: ifnonnull -> 331
      //   250: aload #5
      //   252: astore_3
      //   253: aload #5
      //   255: astore_1
      //   256: new java/lang/StringBuilder
      //   259: astore #4
      //   261: aload #5
      //   263: astore_3
      //   264: aload #5
      //   266: astore_1
      //   267: aload #4
      //   269: invokespecial <init> : ()V
      //   272: aload #5
      //   274: astore_3
      //   275: aload #5
      //   277: astore_1
      //   278: aload #4
      //   280: ldc 'Unable to open: '
      //   282: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   285: pop
      //   286: aload #5
      //   288: astore_3
      //   289: aload #5
      //   291: astore_1
      //   292: aload #4
      //   294: aload_0
      //   295: getfield mIconUri : Landroid/net/Uri;
      //   298: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   301: pop
      //   302: aload #5
      //   304: astore_3
      //   305: aload #5
      //   307: astore_1
      //   308: ldc 'MediaRouteCtrlDialog'
      //   310: aload #4
      //   312: invokevirtual toString : ()Ljava/lang/String;
      //   315: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   318: pop
      //   319: aload #5
      //   321: ifnull -> 329
      //   324: aload #5
      //   326: invokevirtual close : ()V
      //   329: aconst_null
      //   330: areturn
      //   331: aload #4
      //   333: astore_3
      //   334: aload #4
      //   336: astore_1
      //   337: aload #6
      //   339: iconst_0
      //   340: putfield inJustDecodeBounds : Z
      //   343: aload #4
      //   345: astore_3
      //   346: aload #4
      //   348: astore_1
      //   349: aload_0
      //   350: getfield this$0 : Landroid/support/v7/app/MediaRouteControllerDialog;
      //   353: aload #6
      //   355: getfield outWidth : I
      //   358: aload #6
      //   360: getfield outHeight : I
      //   363: invokevirtual getDesiredArtHeight : (II)I
      //   366: istore #7
      //   368: aload #4
      //   370: astore_3
      //   371: aload #4
      //   373: astore_1
      //   374: aload #6
      //   376: iconst_1
      //   377: aload #6
      //   379: getfield outHeight : I
      //   382: iload #7
      //   384: idiv
      //   385: invokestatic highestOneBit : (I)I
      //   388: invokestatic max : (II)I
      //   391: putfield inSampleSize : I
      //   394: aload #4
      //   396: astore_3
      //   397: aload #4
      //   399: astore_1
      //   400: aload_0
      //   401: invokevirtual isCancelled : ()Z
      //   404: istore #8
      //   406: iload #8
      //   408: ifeq -> 418
      //   411: aload #4
      //   413: invokevirtual close : ()V
      //   416: aconst_null
      //   417: areturn
      //   418: aload #4
      //   420: astore_3
      //   421: aload #4
      //   423: astore_1
      //   424: aload #4
      //   426: aconst_null
      //   427: aload #6
      //   429: invokestatic decodeStream : (Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   432: astore #5
      //   434: aload #4
      //   436: invokevirtual close : ()V
      //   439: goto -> 443
      //   442: astore_1
      //   443: aload #5
      //   445: astore_1
      //   446: goto -> 554
      //   449: aload #4
      //   451: invokevirtual close : ()V
      //   454: aconst_null
      //   455: areturn
      //   456: astore #4
      //   458: goto -> 469
      //   461: astore_1
      //   462: goto -> 542
      //   465: astore #4
      //   467: aconst_null
      //   468: astore_3
      //   469: aload_3
      //   470: astore_1
      //   471: new java/lang/StringBuilder
      //   474: astore #5
      //   476: aload_3
      //   477: astore_1
      //   478: aload #5
      //   480: invokespecial <init> : ()V
      //   483: aload_3
      //   484: astore_1
      //   485: aload #5
      //   487: ldc 'Unable to open: '
      //   489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   492: pop
      //   493: aload_3
      //   494: astore_1
      //   495: aload #5
      //   497: aload_0
      //   498: getfield mIconUri : Landroid/net/Uri;
      //   501: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   504: pop
      //   505: aload_3
      //   506: astore_1
      //   507: ldc 'MediaRouteCtrlDialog'
      //   509: aload #5
      //   511: invokevirtual toString : ()Ljava/lang/String;
      //   514: aload #4
      //   516: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   519: pop
      //   520: aload_3
      //   521: ifnull -> 552
      //   524: aload_3
      //   525: invokevirtual close : ()V
      //   528: goto -> 552
      //   531: astore_1
      //   532: goto -> 552
      //   535: astore #4
      //   537: aload_1
      //   538: astore_3
      //   539: aload #4
      //   541: astore_1
      //   542: aload_3
      //   543: ifnull -> 550
      //   546: aload_3
      //   547: invokevirtual close : ()V
      //   550: aload_1
      //   551: athrow
      //   552: aconst_null
      //   553: astore_1
      //   554: aload_0
      //   555: getfield this$0 : Landroid/support/v7/app/MediaRouteControllerDialog;
      //   558: aload_1
      //   559: invokestatic access$300 : (Landroid/support/v7/app/MediaRouteControllerDialog;Landroid/graphics/Bitmap;)Z
      //   562: ifeq -> 598
      //   565: new java/lang/StringBuilder
      //   568: dup
      //   569: invokespecial <init> : ()V
      //   572: astore_3
      //   573: aload_3
      //   574: ldc 'Can't use recycled bitmap: '
      //   576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   579: pop
      //   580: aload_3
      //   581: aload_1
      //   582: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   585: pop
      //   586: ldc 'MediaRouteCtrlDialog'
      //   588: aload_3
      //   589: invokevirtual toString : ()Ljava/lang/String;
      //   592: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   595: pop
      //   596: aconst_null
      //   597: areturn
      //   598: aload_1
      //   599: ifnull -> 666
      //   602: aload_1
      //   603: invokevirtual getWidth : ()I
      //   606: aload_1
      //   607: invokevirtual getHeight : ()I
      //   610: if_icmpge -> 666
      //   613: new android/support/v7/graphics/Palette$Builder
      //   616: dup
      //   617: aload_1
      //   618: invokespecial <init> : (Landroid/graphics/Bitmap;)V
      //   621: iconst_1
      //   622: invokevirtual maximumColorCount : (I)Landroid/support/v7/graphics/Palette$Builder;
      //   625: invokevirtual generate : ()Landroid/support/v7/graphics/Palette;
      //   628: astore_3
      //   629: aload_3
      //   630: invokevirtual getSwatches : ()Ljava/util/List;
      //   633: invokeinterface isEmpty : ()Z
      //   638: ifeq -> 644
      //   641: goto -> 661
      //   644: aload_3
      //   645: invokevirtual getSwatches : ()Ljava/util/List;
      //   648: iconst_0
      //   649: invokeinterface get : (I)Ljava/lang/Object;
      //   654: checkcast android/support/v7/graphics/Palette$Swatch
      //   657: invokevirtual getRgb : ()I
      //   660: istore_2
      //   661: aload_0
      //   662: iload_2
      //   663: putfield mBackgroundColor : I
      //   666: aload_1
      //   667: areturn
      //   668: astore_1
      //   669: goto -> 116
      //   672: astore_1
      //   673: goto -> 329
      //   676: astore_1
      //   677: goto -> 416
      //   680: astore_1
      //   681: goto -> 454
      //   684: astore_3
      //   685: goto -> 550
      // Exception table:
      //   from	to	target	type
      //   25	32	465	java/io/IOException
      //   25	32	461	finally
      //   43	48	456	java/io/IOException
      //   43	48	535	finally
      //   54	59	456	java/io/IOException
      //   54	59	535	finally
      //   65	73	456	java/io/IOException
      //   65	73	535	finally
      //   79	89	456	java/io/IOException
      //   79	89	535	finally
      //   95	106	456	java/io/IOException
      //   95	106	535	finally
      //   111	116	668	java/io/IOException
      //   124	129	456	java/io/IOException
      //   124	129	535	finally
      //   135	140	456	java/io/IOException
      //   135	140	535	finally
      //   146	152	456	java/io/IOException
      //   146	152	535	finally
      //   158	167	456	java/io/IOException
      //   158	167	535	finally
      //   173	181	456	java/io/IOException
      //   173	181	535	finally
      //   187	194	456	java/io/IOException
      //   187	194	535	finally
      //   205	210	213	java/io/IOException
      //   205	210	535	finally
      //   220	225	456	java/io/IOException
      //   220	225	535	finally
      //   231	241	456	java/io/IOException
      //   231	241	535	finally
      //   256	261	456	java/io/IOException
      //   256	261	535	finally
      //   267	272	456	java/io/IOException
      //   267	272	535	finally
      //   278	286	456	java/io/IOException
      //   278	286	535	finally
      //   292	302	456	java/io/IOException
      //   292	302	535	finally
      //   308	319	456	java/io/IOException
      //   308	319	535	finally
      //   324	329	672	java/io/IOException
      //   337	343	456	java/io/IOException
      //   337	343	535	finally
      //   349	368	456	java/io/IOException
      //   349	368	535	finally
      //   374	394	456	java/io/IOException
      //   374	394	535	finally
      //   400	406	456	java/io/IOException
      //   400	406	535	finally
      //   411	416	676	java/io/IOException
      //   424	434	456	java/io/IOException
      //   424	434	535	finally
      //   434	439	442	java/io/IOException
      //   449	454	680	java/io/IOException
      //   471	476	535	finally
      //   478	483	535	finally
      //   485	493	535	finally
      //   495	505	535	finally
      //   507	520	535	finally
      //   524	528	531	java/io/IOException
      //   546	550	684	java/io/IOException
    }
    
    public Bitmap getIconBitmap() {
      return this.mIconBitmap;
    }
    
    public Uri getIconUri() {
      return this.mIconUri;
    }
    
    protected void onPostExecute(Bitmap param1Bitmap) {
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      mediaRouteControllerDialog.mFetchArtTask = null;
      if (!ObjectsCompat.equals(mediaRouteControllerDialog.mArtIconBitmap, this.mIconBitmap) || !ObjectsCompat.equals(MediaRouteControllerDialog.this.mArtIconUri, this.mIconUri)) {
        mediaRouteControllerDialog = MediaRouteControllerDialog.this;
        mediaRouteControllerDialog.mArtIconBitmap = this.mIconBitmap;
        mediaRouteControllerDialog.mArtIconLoadedBitmap = param1Bitmap;
        mediaRouteControllerDialog.mArtIconUri = this.mIconUri;
        mediaRouteControllerDialog.mArtIconBackgroundColor = this.mBackgroundColor;
        boolean bool = true;
        mediaRouteControllerDialog.mArtIconIsLoaded = true;
        long l1 = SystemClock.uptimeMillis();
        long l2 = this.mStartTimeMillis;
        MediaRouteControllerDialog mediaRouteControllerDialog1 = MediaRouteControllerDialog.this;
        if (l1 - l2 <= 120L)
          bool = false; 
        mediaRouteControllerDialog1.update(bool);
      } 
    }
    
    protected void onPreExecute() {
      this.mStartTimeMillis = SystemClock.uptimeMillis();
      MediaRouteControllerDialog.this.clearLoadedBitmap();
    }
  }
  
  private final class MediaControllerCallback extends MediaControllerCompat.Callback {
    public void onMetadataChanged(MediaMetadataCompat param1MediaMetadataCompat) {
      MediaDescriptionCompat mediaDescriptionCompat;
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      if (param1MediaMetadataCompat == null) {
        param1MediaMetadataCompat = null;
      } else {
        mediaDescriptionCompat = param1MediaMetadataCompat.getDescription();
      } 
      mediaRouteControllerDialog.mDescription = mediaDescriptionCompat;
      MediaRouteControllerDialog.this.updateArtIconIfNeeded();
      MediaRouteControllerDialog.this.update(false);
    }
    
    public void onPlaybackStateChanged(PlaybackStateCompat param1PlaybackStateCompat) {
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      mediaRouteControllerDialog.mState = param1PlaybackStateCompat;
      mediaRouteControllerDialog.update(false);
    }
    
    public void onSessionDestroyed() {
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      MediaControllerCompat mediaControllerCompat = mediaRouteControllerDialog.mMediaController;
      if (mediaControllerCompat != null) {
        mediaControllerCompat.unregisterCallback(mediaRouteControllerDialog.mControllerCallback);
        MediaRouteControllerDialog.this.mMediaController = null;
      } 
    }
  }
  
  private final class MediaRouterCallback extends MediaRouter.Callback {
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteControllerDialog.this.update(true);
    }
    
    public void onRouteUnselected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteControllerDialog.this.update(false);
    }
    
    public void onRouteVolumeChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      SeekBar seekBar = MediaRouteControllerDialog.this.mVolumeSliderMap.get(param1RouteInfo);
      int i = param1RouteInfo.getVolume();
      if (MediaRouteControllerDialog.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRouteVolumeChanged(), route.getVolume:");
        stringBuilder.append(i);
        Log.d("MediaRouteCtrlDialog", stringBuilder.toString());
      } 
      if (seekBar != null && MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != param1RouteInfo)
        seekBar.setProgress(i); 
    }
  }
  
  private class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
    private final Runnable mStopTrackingTouch = new Runnable() {
        public void run() {
          MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
          if (mediaRouteControllerDialog.mRouteInVolumeSliderTouched != null) {
            mediaRouteControllerDialog.mRouteInVolumeSliderTouched = null;
            if (mediaRouteControllerDialog.mHasPendingUpdate)
              mediaRouteControllerDialog.update(mediaRouteControllerDialog.mPendingUpdateAnimationNeeded); 
          } 
        }
      };
    
    public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)param1SeekBar.getTag();
        if (MediaRouteControllerDialog.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(");
          stringBuilder.append(param1Int);
          stringBuilder.append(")");
          Log.d("MediaRouteCtrlDialog", stringBuilder.toString());
        } 
        routeInfo.requestSetVolume(param1Int);
      } 
    }
    
    public void onStartTrackingTouch(SeekBar param1SeekBar) {
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      if (mediaRouteControllerDialog.mRouteInVolumeSliderTouched != null)
        mediaRouteControllerDialog.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch); 
      MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = (MediaRouter.RouteInfo)param1SeekBar.getTag();
    }
    
    public void onStopTrackingTouch(SeekBar param1SeekBar) {
      MediaRouteControllerDialog.this.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 500L);
    }
  }
  
  class null implements Runnable {
    public void run() {
      MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
      if (mediaRouteControllerDialog.mRouteInVolumeSliderTouched != null) {
        mediaRouteControllerDialog.mRouteInVolumeSliderTouched = null;
        if (mediaRouteControllerDialog.mHasPendingUpdate)
          mediaRouteControllerDialog.update(mediaRouteControllerDialog.mPendingUpdateAnimationNeeded); 
      } 
    }
  }
  
  private class VolumeGroupAdapter extends ArrayAdapter<MediaRouter.RouteInfo> {
    final float mDisabledAlpha;
    
    public VolumeGroupAdapter(Context param1Context, List<MediaRouter.RouteInfo> param1List) {
      super(param1Context, 0, param1List);
      this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(param1Context);
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      boolean bool = false;
      if (param1View == null) {
        param1View = LayoutInflater.from(param1ViewGroup.getContext()).inflate(R.layout.mr_controller_volume_item, param1ViewGroup, false);
      } else {
        MediaRouteControllerDialog.this.updateVolumeGroupItemHeight(param1View);
      } 
      MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)getItem(param1Int);
      if (routeInfo != null) {
        boolean bool1 = routeInfo.isEnabled();
        TextView textView = (TextView)param1View.findViewById(R.id.mr_name);
        textView.setEnabled(bool1);
        textView.setText(routeInfo.getName());
        MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider)param1View.findViewById(R.id.mr_volume_slider);
        MediaRouterThemeHelper.setVolumeSliderColor(param1ViewGroup.getContext(), mediaRouteVolumeSlider, (View)MediaRouteControllerDialog.this.mVolumeGroupList);
        mediaRouteVolumeSlider.setTag(routeInfo);
        MediaRouteControllerDialog.this.mVolumeSliderMap.put(routeInfo, mediaRouteVolumeSlider);
        mediaRouteVolumeSlider.setHideThumb(bool1 ^ true);
        mediaRouteVolumeSlider.setEnabled(bool1);
        if (bool1)
          if (MediaRouteControllerDialog.this.isVolumeControlAvailable(routeInfo)) {
            mediaRouteVolumeSlider.setMax(routeInfo.getVolumeMax());
            mediaRouteVolumeSlider.setProgress(routeInfo.getVolume());
            mediaRouteVolumeSlider.setOnSeekBarChangeListener(MediaRouteControllerDialog.this.mVolumeChangeListener);
          } else {
            mediaRouteVolumeSlider.setMax(100);
            mediaRouteVolumeSlider.setProgress(100);
            mediaRouteVolumeSlider.setEnabled(false);
          }  
        ImageView imageView = (ImageView)param1View.findViewById(R.id.mr_volume_item_icon);
        if (bool1) {
          param1Int = 255;
        } else {
          param1Int = (int)(this.mDisabledAlpha * 255.0F);
        } 
        imageView.setAlpha(param1Int);
        LinearLayout linearLayout = (LinearLayout)param1View.findViewById(R.id.volume_item_container);
        param1Int = bool;
        if (MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.contains(routeInfo))
          param1Int = 4; 
        linearLayout.setVisibility(param1Int);
        Set<MediaRouter.RouteInfo> set = MediaRouteControllerDialog.this.mGroupMemberRoutesAdded;
        if (set != null && set.contains(routeInfo)) {
          AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 0.0F);
          alphaAnimation.setDuration(0L);
          alphaAnimation.setFillEnabled(true);
          alphaAnimation.setFillAfter(true);
          param1View.clearAnimation();
          param1View.startAnimation((Animation)alphaAnimation);
        } 
      } 
      return param1View;
    }
    
    public boolean isEnabled(int param1Int) {
      return false;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteControllerDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */