package android.support.v7.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class MediaRouter {
  public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
  
  public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
  
  public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
  
  public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
  
  public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
  
  public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
  
  static final boolean DEBUG = Log.isLoggable("MediaRouter", 3);
  
  static final String TAG = "MediaRouter";
  
  public static final int UNSELECT_REASON_DISCONNECTED = 1;
  
  public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
  
  public static final int UNSELECT_REASON_STOPPED = 2;
  
  public static final int UNSELECT_REASON_UNKNOWN = 0;
  
  static GlobalMediaRouter sGlobal;
  
  final ArrayList<CallbackRecord> mCallbackRecords = new ArrayList<CallbackRecord>();
  
  final Context mContext;
  
  private MediaRouter(Context paramContext) {
    this.mContext = paramContext;
  }
  
  static void checkCallingThread() {
    if (Looper.myLooper() == Looper.getMainLooper())
      return; 
    throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
  }
  
  static <T> boolean equal(T paramT1, T paramT2) {
    return (paramT1 == paramT2 || (paramT1 != null && paramT2 != null && paramT1.equals(paramT2)));
  }
  
  private int findCallbackRecord(Callback paramCallback) {
    int i = this.mCallbackRecords.size();
    for (byte b = 0; b < i; b++) {
      if (((CallbackRecord)this.mCallbackRecords.get(b)).mCallback == paramCallback)
        return b; 
    } 
    return -1;
  }
  
  public static MediaRouter getInstance(@NonNull Context paramContext) {
    if (paramContext != null) {
      checkCallingThread();
      if (sGlobal == null) {
        GlobalMediaRouter globalMediaRouter = new GlobalMediaRouter(paramContext.getApplicationContext());
        sGlobal = globalMediaRouter;
        globalMediaRouter.start();
      } 
      return sGlobal.getRouter(paramContext);
    } 
    throw new IllegalArgumentException("context must not be null");
  }
  
  public void addCallback(MediaRouteSelector paramMediaRouteSelector, Callback paramCallback) {
    addCallback(paramMediaRouteSelector, paramCallback, 0);
  }
  
  public void addCallback(@NonNull MediaRouteSelector paramMediaRouteSelector, @NonNull Callback paramCallback, int paramInt) {
    if (paramMediaRouteSelector != null) {
      if (paramCallback != null) {
        CallbackRecord callbackRecord;
        checkCallingThread();
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("addCallback: selector=");
          stringBuilder.append(paramMediaRouteSelector);
          stringBuilder.append(", callback=");
          stringBuilder.append(paramCallback);
          stringBuilder.append(", flags=");
          stringBuilder.append(Integer.toHexString(paramInt));
          Log.d("MediaRouter", stringBuilder.toString());
        } 
        int i = findCallbackRecord(paramCallback);
        if (i < 0) {
          callbackRecord = new CallbackRecord(this, paramCallback);
          this.mCallbackRecords.add(callbackRecord);
        } else {
          callbackRecord = this.mCallbackRecords.get(i);
        } 
        i = 0;
        int j = callbackRecord.mFlags;
        boolean bool = true;
        if (((j ^ 0xFFFFFFFF) & paramInt) != 0) {
          callbackRecord.mFlags = j | paramInt;
          i = 1;
        } 
        if (!callbackRecord.mSelector.contains(paramMediaRouteSelector)) {
          callbackRecord.mSelector = (new MediaRouteSelector.Builder(callbackRecord.mSelector)).addSelector(paramMediaRouteSelector).build();
          paramInt = bool;
        } else {
          paramInt = i;
        } 
        if (paramInt != 0)
          sGlobal.updateDiscoveryRequest(); 
        return;
      } 
      throw new IllegalArgumentException("callback must not be null");
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  public void addProvider(@NonNull MediaRouteProvider paramMediaRouteProvider) {
    if (paramMediaRouteProvider != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addProvider: ");
        stringBuilder.append(paramMediaRouteProvider);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      sGlobal.addProvider(paramMediaRouteProvider);
      return;
    } 
    throw new IllegalArgumentException("providerInstance must not be null");
  }
  
  public void addRemoteControlClient(@NonNull Object paramObject) {
    if (paramObject != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addRemoteControlClient: ");
        stringBuilder.append(paramObject);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      sGlobal.addRemoteControlClient(paramObject);
      return;
    } 
    throw new IllegalArgumentException("remoteControlClient must not be null");
  }
  
  public RouteInfo getBluetoothRoute() {
    checkCallingThread();
    return sGlobal.getBluetoothRoute();
  }
  
  @NonNull
  public RouteInfo getDefaultRoute() {
    checkCallingThread();
    return sGlobal.getDefaultRoute();
  }
  
  public MediaSessionCompat.Token getMediaSessionToken() {
    return sGlobal.getMediaSessionToken();
  }
  
  public List<ProviderInfo> getProviders() {
    checkCallingThread();
    return sGlobal.getProviders();
  }
  
  public List<RouteInfo> getRoutes() {
    checkCallingThread();
    return sGlobal.getRoutes();
  }
  
  @NonNull
  public RouteInfo getSelectedRoute() {
    checkCallingThread();
    return sGlobal.getSelectedRoute();
  }
  
  public boolean isRouteAvailable(@NonNull MediaRouteSelector paramMediaRouteSelector, int paramInt) {
    if (paramMediaRouteSelector != null) {
      checkCallingThread();
      return sGlobal.isRouteAvailable(paramMediaRouteSelector, paramInt);
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  public void removeCallback(@NonNull Callback paramCallback) {
    if (paramCallback != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("removeCallback: callback=");
        stringBuilder.append(paramCallback);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      int i = findCallbackRecord(paramCallback);
      if (i >= 0) {
        this.mCallbackRecords.remove(i);
        sGlobal.updateDiscoveryRequest();
      } 
      return;
    } 
    throw new IllegalArgumentException("callback must not be null");
  }
  
  public void removeProvider(@NonNull MediaRouteProvider paramMediaRouteProvider) {
    if (paramMediaRouteProvider != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("removeProvider: ");
        stringBuilder.append(paramMediaRouteProvider);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      sGlobal.removeProvider(paramMediaRouteProvider);
      return;
    } 
    throw new IllegalArgumentException("providerInstance must not be null");
  }
  
  public void removeRemoteControlClient(@NonNull Object paramObject) {
    if (paramObject != null) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("removeRemoteControlClient: ");
        stringBuilder.append(paramObject);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      sGlobal.removeRemoteControlClient(paramObject);
      return;
    } 
    throw new IllegalArgumentException("remoteControlClient must not be null");
  }
  
  public void selectRoute(@NonNull RouteInfo paramRouteInfo) {
    if (paramRouteInfo != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("selectRoute: ");
        stringBuilder.append(paramRouteInfo);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      sGlobal.selectRoute(paramRouteInfo);
      return;
    } 
    throw new IllegalArgumentException("route must not be null");
  }
  
  public void setMediaSession(Object paramObject) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("addMediaSession: ");
      stringBuilder.append(paramObject);
      Log.d("MediaRouter", stringBuilder.toString());
    } 
    sGlobal.setMediaSession(paramObject);
  }
  
  public void setMediaSessionCompat(MediaSessionCompat paramMediaSessionCompat) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("addMediaSessionCompat: ");
      stringBuilder.append(paramMediaSessionCompat);
      Log.d("MediaRouter", stringBuilder.toString());
    } 
    sGlobal.setMediaSessionCompat(paramMediaSessionCompat);
  }
  
  public void unselect(int paramInt) {
    if (paramInt >= 0 && paramInt <= 3) {
      checkCallingThread();
      RouteInfo routeInfo = sGlobal.chooseFallbackRoute();
      if (sGlobal.getSelectedRoute() != routeInfo) {
        sGlobal.selectRoute(routeInfo, paramInt);
      } else {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        globalMediaRouter.selectRoute(globalMediaRouter.getDefaultRoute(), paramInt);
      } 
      return;
    } 
    throw new IllegalArgumentException("Unsupported reason to unselect route");
  }
  
  @NonNull
  public RouteInfo updateSelectedRoute(@NonNull MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      checkCallingThread();
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateSelectedRoute: ");
        stringBuilder.append(paramMediaRouteSelector);
        Log.d("MediaRouter", stringBuilder.toString());
      } 
      RouteInfo routeInfo2 = sGlobal.getSelectedRoute();
      RouteInfo routeInfo1 = routeInfo2;
      if (!routeInfo2.isDefaultOrBluetooth()) {
        routeInfo1 = routeInfo2;
        if (!routeInfo2.matchesSelector(paramMediaRouteSelector)) {
          routeInfo1 = sGlobal.chooseFallbackRoute();
          sGlobal.selectRoute(routeInfo1);
        } 
      } 
      return routeInfo1;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  public static abstract class Callback {
    public void onProviderAdded(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {}
    
    public void onProviderChanged(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {}
    
    public void onProviderRemoved(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {}
    
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRoutePresentationDisplayChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRouteSelected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRouteUnselected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
    
    public void onRouteUnselected(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo, int param1Int) {
      onRouteUnselected(param1MediaRouter, param1RouteInfo);
    }
    
    public void onRouteVolumeChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface CallbackFlags {}
  
  private static final class CallbackRecord {
    public final MediaRouter.Callback mCallback;
    
    public int mFlags;
    
    public final MediaRouter mRouter;
    
    public MediaRouteSelector mSelector;
    
    public CallbackRecord(MediaRouter param1MediaRouter, MediaRouter.Callback param1Callback) {
      this.mRouter = param1MediaRouter;
      this.mCallback = param1Callback;
      this.mSelector = MediaRouteSelector.EMPTY;
    }
    
    public boolean filterRouteEvent(MediaRouter.RouteInfo param1RouteInfo) {
      return ((this.mFlags & 0x2) != 0 || param1RouteInfo.matchesSelector(this.mSelector));
    }
  }
  
  public static abstract class ControlRequestCallback {
    public void onError(String param1String, Bundle param1Bundle) {}
    
    public void onResult(Bundle param1Bundle) {}
  }
  
  private static final class GlobalMediaRouter implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
    final Context mApplicationContext;
    
    private MediaRouter.RouteInfo mBluetoothRoute;
    
    final CallbackHandler mCallbackHandler = new CallbackHandler();
    
    private MediaSessionCompat mCompatSession;
    
    private MediaRouter.RouteInfo mDefaultRoute;
    
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    
    private final DisplayManagerCompat mDisplayManager;
    
    private final boolean mLowRam;
    
    private MediaSessionRecord mMediaSession;
    
    final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
    
    private final ProviderCallback mProviderCallback = new ProviderCallback();
    
    private final ArrayList<MediaRouter.ProviderInfo> mProviders = new ArrayList<MediaRouter.ProviderInfo>();
    
    MediaSessionCompat mRccMediaSession;
    
    private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
    
    private final ArrayList<RemoteControlClientRecord> mRemoteControlClients = new ArrayList<RemoteControlClientRecord>();
    
    private final Map<String, MediaRouteProvider.RouteController> mRouteControllerMap = new HashMap<String, MediaRouteProvider.RouteController>();
    
    final ArrayList<WeakReference<MediaRouter>> mRouters = new ArrayList<WeakReference<MediaRouter>>();
    
    private final ArrayList<MediaRouter.RouteInfo> mRoutes = new ArrayList<MediaRouter.RouteInfo>();
    
    MediaRouter.RouteInfo mSelectedRoute;
    
    private MediaRouteProvider.RouteController mSelectedRouteController;
    
    private MediaSessionCompat.OnActiveChangeListener mSessionActiveListener = new MediaSessionCompat.OnActiveChangeListener() {
        public void onActiveChanged() {
          MediaSessionCompat mediaSessionCompat = MediaRouter.GlobalMediaRouter.this.mRccMediaSession;
          if (mediaSessionCompat != null)
            if (mediaSessionCompat.isActive()) {
              MediaRouter.GlobalMediaRouter globalMediaRouter = MediaRouter.GlobalMediaRouter.this;
              globalMediaRouter.addRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
            } else {
              MediaRouter.GlobalMediaRouter globalMediaRouter = MediaRouter.GlobalMediaRouter.this;
              globalMediaRouter.removeRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
            }  
        }
      };
    
    final SystemMediaRouteProvider mSystemProvider;
    
    private final Map<Pair<String, String>, String> mUniqueIdMap = new HashMap<Pair<String, String>, String>();
    
    GlobalMediaRouter(Context param1Context) {
      this.mApplicationContext = param1Context;
      this.mDisplayManager = DisplayManagerCompat.getInstance(param1Context);
      this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager)param1Context.getSystemService("activity"));
      this.mSystemProvider = SystemMediaRouteProvider.obtain(param1Context, this);
    }
    
    private String assignRouteUniqueId(MediaRouter.ProviderInfo param1ProviderInfo, String param1String) {
      String str1 = param1ProviderInfo.getComponentName().flattenToShortString();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str1);
      stringBuilder1.append(":");
      stringBuilder1.append(param1String);
      String str2 = stringBuilder1.toString();
      if (findRouteByUniqueId(str2) < 0) {
        this.mUniqueIdMap.put(new Pair(str1, param1String), str2);
        return str2;
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Either ");
      stringBuilder2.append(param1String);
      stringBuilder2.append(" isn't unique in ");
      stringBuilder2.append(str1);
      stringBuilder2.append(" or we're trying to assign a unique ID for an already added route");
      Log.w("MediaRouter", stringBuilder2.toString());
      for (byte b = 2;; b++) {
        String str = String.format(Locale.US, "%s_%d", new Object[] { str2, Integer.valueOf(b) });
        if (findRouteByUniqueId(str) < 0) {
          this.mUniqueIdMap.put(new Pair(str1, param1String), str);
          return str;
        } 
      } 
    }
    
    private int findProviderInfo(MediaRouteProvider param1MediaRouteProvider) {
      int i = this.mProviders.size();
      for (byte b = 0; b < i; b++) {
        if ((this.mProviders.get(b)).mProviderInstance == param1MediaRouteProvider)
          return b; 
      } 
      return -1;
    }
    
    private int findRemoteControlClientRecord(Object param1Object) {
      int i = this.mRemoteControlClients.size();
      for (byte b = 0; b < i; b++) {
        if (((RemoteControlClientRecord)this.mRemoteControlClients.get(b)).getRemoteControlClient() == param1Object)
          return b; 
      } 
      return -1;
    }
    
    private int findRouteByUniqueId(String param1String) {
      int i = this.mRoutes.size();
      for (byte b = 0; b < i; b++) {
        if ((this.mRoutes.get(b)).mUniqueId.equals(param1String))
          return b; 
      } 
      return -1;
    }
    
    private String getUniqueId(MediaRouter.ProviderInfo param1ProviderInfo, String param1String) {
      String str = param1ProviderInfo.getComponentName().flattenToShortString();
      return this.mUniqueIdMap.get(new Pair(str, param1String));
    }
    
    private boolean isSystemDefaultRoute(MediaRouter.RouteInfo param1RouteInfo) {
      boolean bool;
      if (param1RouteInfo.getProviderInstance() == this.mSystemProvider && param1RouteInfo.mDescriptorId.equals("DEFAULT_ROUTE")) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private boolean isSystemLiveAudioOnlyRoute(MediaRouter.RouteInfo param1RouteInfo) {
      boolean bool;
      if (param1RouteInfo.getProviderInstance() == this.mSystemProvider && param1RouteInfo.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !param1RouteInfo.supportsControlCategory("android.media.intent.category.LIVE_VIDEO")) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void setMediaSessionRecord(MediaSessionRecord param1MediaSessionRecord) {
      MediaSessionRecord mediaSessionRecord = this.mMediaSession;
      if (mediaSessionRecord != null)
        mediaSessionRecord.clearVolumeHandling(); 
      this.mMediaSession = param1MediaSessionRecord;
      if (param1MediaSessionRecord != null)
        updatePlaybackInfoFromSelectedRoute(); 
    }
    
    private void setSelectedRouteInternal(@NonNull MediaRouter.RouteInfo param1RouteInfo, int param1Int) {
      if (MediaRouter.sGlobal == null || (this.mBluetoothRoute != null && param1RouteInfo.isDefault())) {
        StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b = 3; b < arrayOfStackTraceElement.length; b++) {
          StackTraceElement stackTraceElement = arrayOfStackTraceElement[b];
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(stackTraceElement.getClassName());
          stringBuilder.append(".");
          stringBuilder.append(stackTraceElement.getMethodName());
          stringBuilder.append(":");
          stringBuilder.append(stackTraceElement.getLineNumber());
          stringBuffer.append(stringBuilder.toString());
          stringBuffer.append("  ");
        } 
        if (MediaRouter.sGlobal == null) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("setSelectedRouteInternal is called while sGlobal is null: pkgName=");
          stringBuilder.append(this.mApplicationContext.getPackageName());
          stringBuilder.append(", callers=");
          stringBuilder.append(stringBuffer.toString());
          Log.w("MediaRouter", stringBuilder.toString());
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Default route is selected while a BT route is available: pkgName=");
          stringBuilder.append(this.mApplicationContext.getPackageName());
          stringBuilder.append(", callers=");
          stringBuilder.append(stringBuffer.toString());
          Log.w("MediaRouter", stringBuilder.toString());
        } 
      } 
      MediaRouter.RouteInfo routeInfo = this.mSelectedRoute;
      if (routeInfo != param1RouteInfo) {
        if (routeInfo != null) {
          if (MediaRouter.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Route unselected: ");
            stringBuilder.append(this.mSelectedRoute);
            stringBuilder.append(" reason: ");
            stringBuilder.append(param1Int);
            Log.d("MediaRouter", stringBuilder.toString());
          } 
          this.mCallbackHandler.post(263, this.mSelectedRoute, param1Int);
          routeController1 = this.mSelectedRouteController;
          if (routeController1 != null) {
            routeController1.onUnselect(param1Int);
            this.mSelectedRouteController.onRelease();
            this.mSelectedRouteController = null;
          } 
          if (!this.mRouteControllerMap.isEmpty()) {
            for (MediaRouteProvider.RouteController routeController1 : this.mRouteControllerMap.values()) {
              routeController1.onUnselect(param1Int);
              routeController1.onRelease();
            } 
            this.mRouteControllerMap.clear();
          } 
        } 
        this.mSelectedRoute = param1RouteInfo;
        MediaRouteProvider.RouteController routeController = param1RouteInfo.getProviderInstance().onCreateRouteController(param1RouteInfo.mDescriptorId);
        this.mSelectedRouteController = routeController;
        if (routeController != null)
          routeController.onSelect(); 
        if (MediaRouter.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Route selected: ");
          stringBuilder.append(this.mSelectedRoute);
          Log.d("MediaRouter", stringBuilder.toString());
        } 
        this.mCallbackHandler.post(262, this.mSelectedRoute);
        MediaRouter.RouteInfo routeInfo1 = this.mSelectedRoute;
        if (routeInfo1 instanceof MediaRouter.RouteGroup) {
          List<MediaRouter.RouteInfo> list = ((MediaRouter.RouteGroup)routeInfo1).getRoutes();
          this.mRouteControllerMap.clear();
          for (MediaRouter.RouteInfo routeInfo2 : list) {
            MediaRouteProvider.RouteController routeController1 = routeInfo2.getProviderInstance().onCreateRouteController(routeInfo2.mDescriptorId, this.mSelectedRoute.mDescriptorId);
            routeController1.onSelect();
            this.mRouteControllerMap.put(routeInfo2.mDescriptorId, routeController1);
          } 
        } 
        updatePlaybackInfoFromSelectedRoute();
      } 
    }
    
    private void updatePlaybackInfoFromSelectedRoute() {
      MediaRouter.RouteInfo routeInfo = this.mSelectedRoute;
      if (routeInfo != null) {
        this.mPlaybackInfo.volume = routeInfo.getVolume();
        this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
        this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
        this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
        this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
        int i = this.mRemoteControlClients.size();
        boolean bool = false;
        byte b;
        for (b = 0; b < i; b++)
          ((RemoteControlClientRecord)this.mRemoteControlClients.get(b)).updatePlaybackInfo(); 
        if (this.mMediaSession != null) {
          if (this.mSelectedRoute == getDefaultRoute() || this.mSelectedRoute == getBluetoothRoute()) {
            this.mMediaSession.clearVolumeHandling();
            return;
          } 
          RemoteControlClientCompat.PlaybackInfo playbackInfo = this.mPlaybackInfo;
          b = bool;
          if (playbackInfo.volumeHandling == 1)
            b = 2; 
          this.mMediaSession.configureVolume(b, playbackInfo.volumeMax, playbackInfo.volume);
        } 
      } else {
        MediaSessionRecord mediaSessionRecord = this.mMediaSession;
        if (mediaSessionRecord != null)
          mediaSessionRecord.clearVolumeHandling(); 
      } 
    }
    
    private void updateProviderContents(MediaRouter.ProviderInfo param1ProviderInfo, MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      if (param1ProviderInfo.updateDescriptor(param1MediaRouteProviderDescriptor)) {
        if (param1MediaRouteProviderDescriptor != null) {
          byte b;
          boolean bool;
          if (param1MediaRouteProviderDescriptor.isValid()) {
            List<MediaRouteDescriptor> list = param1MediaRouteProviderDescriptor.getRoutes();
            int j = list.size();
            ArrayList<Pair> arrayList1 = new ArrayList();
            ArrayList<Pair> arrayList2 = new ArrayList();
            byte b1 = 0;
            b = 0;
            bool = false;
            while (b1 < j) {
              StringBuilder stringBuilder;
              MediaRouteDescriptor mediaRouteDescriptor = list.get(b1);
              String str = mediaRouteDescriptor.getId();
              int k = param1ProviderInfo.findRouteByDescriptorId(str);
              if (k < 0) {
                MediaRouter.RouteInfo routeInfo1;
                boolean bool3;
                String str1 = assignRouteUniqueId(param1ProviderInfo, str);
                if (mediaRouteDescriptor.getGroupMemberIds() != null) {
                  bool3 = true;
                } else {
                  bool3 = false;
                } 
                if (bool3) {
                  routeInfo1 = new MediaRouter.RouteGroup(param1ProviderInfo, str, str1);
                } else {
                  routeInfo1 = new MediaRouter.RouteInfo(param1ProviderInfo, (String)routeInfo1, str1);
                } 
                param1ProviderInfo.mRoutes.add(b, routeInfo1);
                this.mRoutes.add(routeInfo1);
                if (bool3) {
                  arrayList1.add(new Pair(routeInfo1, mediaRouteDescriptor));
                } else {
                  routeInfo1.maybeUpdateDescriptor(mediaRouteDescriptor);
                  if (MediaRouter.DEBUG) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Route added: ");
                    stringBuilder.append(routeInfo1);
                    Log.d("MediaRouter", stringBuilder.toString());
                  } 
                  this.mCallbackHandler.post(257, routeInfo1);
                } 
                b++;
                continue;
              } 
              if (k < b) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Ignoring route descriptor with duplicate id: ");
                stringBuilder1.append(stringBuilder);
                Log.w("MediaRouter", stringBuilder1.toString());
                continue;
              } 
              MediaRouter.RouteInfo routeInfo = param1ProviderInfo.mRoutes.get(k);
              List<?> list1 = param1ProviderInfo.mRoutes;
              int m = b + 1;
              Collections.swap(list1, k, b);
              if (routeInfo instanceof MediaRouter.RouteGroup) {
                arrayList2.add(new Pair(routeInfo, stringBuilder));
              } else if (updateRouteDescriptorAndNotify(routeInfo, (MediaRouteDescriptor)stringBuilder) != 0 && routeInfo == this.mSelectedRoute) {
                b = m;
                bool = true;
                continue;
              } 
              b = m;
              continue;
              b1++;
            } 
            for (Pair pair1 : arrayList1) {
              MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)pair1.first;
              routeInfo.maybeUpdateDescriptor((MediaRouteDescriptor)pair1.second);
              if (MediaRouter.DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Route added: ");
                stringBuilder.append(routeInfo);
                Log.d("MediaRouter", stringBuilder.toString());
              } 
              this.mCallbackHandler.post(257, routeInfo);
            } 
            for (Pair pair : arrayList2) {
              MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo)pair.first;
              if (updateRouteDescriptorAndNotify(routeInfo, (MediaRouteDescriptor)pair.second) != 0 && routeInfo == this.mSelectedRoute)
                bool = true; 
            } 
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring invalid provider descriptor: ");
            stringBuilder.append(pair);
            Log.w("MediaRouter", stringBuilder.toString());
            bool = false;
            b = 0;
          } 
          int i;
          for (i = param1ProviderInfo.mRoutes.size() - 1; i >= b; i--) {
            MediaRouter.RouteInfo routeInfo = param1ProviderInfo.mRoutes.get(i);
            routeInfo.maybeUpdateDescriptor(null);
            this.mRoutes.remove(routeInfo);
          } 
          updateSelectedRouteIfNeeded(bool);
          for (i = param1ProviderInfo.mRoutes.size() - 1; i >= b; i--) {
            MediaRouter.RouteInfo routeInfo = param1ProviderInfo.mRoutes.remove(i);
            if (MediaRouter.DEBUG) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Route removed: ");
              stringBuilder.append(routeInfo);
              Log.d("MediaRouter", stringBuilder.toString());
            } 
            this.mCallbackHandler.post(258, routeInfo);
          } 
          if (MediaRouter.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Provider changed: ");
            stringBuilder.append(param1ProviderInfo);
            Log.d("MediaRouter", stringBuilder.toString());
          } 
          this.mCallbackHandler.post(515, param1ProviderInfo);
          return;
        } 
      } else {
        return;
      } 
      boolean bool2 = false;
      boolean bool1 = false;
    }
    
    private int updateRouteDescriptorAndNotify(MediaRouter.RouteInfo param1RouteInfo, MediaRouteDescriptor param1MediaRouteDescriptor) {
      int i = param1RouteInfo.maybeUpdateDescriptor(param1MediaRouteDescriptor);
      if (i != 0) {
        if ((i & 0x1) != 0) {
          if (MediaRouter.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Route changed: ");
            stringBuilder.append(param1RouteInfo);
            Log.d("MediaRouter", stringBuilder.toString());
          } 
          this.mCallbackHandler.post(259, param1RouteInfo);
        } 
        if ((i & 0x2) != 0) {
          if (MediaRouter.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Route volume changed: ");
            stringBuilder.append(param1RouteInfo);
            Log.d("MediaRouter", stringBuilder.toString());
          } 
          this.mCallbackHandler.post(260, param1RouteInfo);
        } 
        if ((i & 0x4) != 0) {
          if (MediaRouter.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Route presentation display changed: ");
            stringBuilder.append(param1RouteInfo);
            Log.d("MediaRouter", stringBuilder.toString());
          } 
          this.mCallbackHandler.post(261, param1RouteInfo);
        } 
      } 
      return i;
    }
    
    private void updateSelectedRouteIfNeeded(boolean param1Boolean) {
      routeInfo = this.mDefaultRoute;
      if (routeInfo != null && !routeInfo.isSelectable()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Clearing the default route because it is no longer selectable: ");
        stringBuilder.append(this.mDefaultRoute);
        Log.i("MediaRouter", stringBuilder.toString());
        this.mDefaultRoute = null;
      } 
      if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
        Iterator<MediaRouter.RouteInfo> iterator = this.mRoutes.iterator();
        while (iterator.hasNext()) {
          MediaRouter.RouteInfo routeInfo1 = iterator.next();
          if (isSystemDefaultRoute(routeInfo1) && routeInfo1.isSelectable()) {
            this.mDefaultRoute = routeInfo1;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Found default route: ");
            stringBuilder.append(this.mDefaultRoute);
            Log.i("MediaRouter", stringBuilder.toString());
            break;
          } 
        } 
      } 
      routeInfo = this.mBluetoothRoute;
      if (routeInfo != null && !routeInfo.isSelectable()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Clearing the bluetooth route because it is no longer selectable: ");
        stringBuilder.append(this.mBluetoothRoute);
        Log.i("MediaRouter", stringBuilder.toString());
        this.mBluetoothRoute = null;
      } 
      if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty())
        for (MediaRouter.RouteInfo routeInfo : this.mRoutes) {
          if (isSystemLiveAudioOnlyRoute(routeInfo) && routeInfo.isSelectable()) {
            this.mBluetoothRoute = routeInfo;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Found bluetooth route: ");
            stringBuilder.append(this.mBluetoothRoute);
            Log.i("MediaRouter", stringBuilder.toString());
            break;
          } 
        }  
      routeInfo = this.mSelectedRoute;
      if (routeInfo == null || !routeInfo.isSelectable()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unselecting the current route because it is no longer selectable: ");
        stringBuilder.append(this.mSelectedRoute);
        Log.i("MediaRouter", stringBuilder.toString());
        setSelectedRouteInternal(chooseFallbackRoute(), 0);
        return;
      } 
      if (param1Boolean) {
        routeInfo = this.mSelectedRoute;
        if (routeInfo instanceof MediaRouter.RouteGroup) {
          List<MediaRouter.RouteInfo> list = ((MediaRouter.RouteGroup)routeInfo).getRoutes();
          HashSet<String> hashSet = new HashSet();
          null = list.iterator();
          while (null.hasNext())
            hashSet.add((null.next()).mDescriptorId); 
          null = this.mRouteControllerMap.entrySet().iterator();
          while (null.hasNext()) {
            Map.Entry entry = (Map.Entry)null.next();
            if (!hashSet.contains(entry.getKey())) {
              MediaRouteProvider.RouteController routeController = (MediaRouteProvider.RouteController)entry.getValue();
              routeController.onUnselect();
              routeController.onRelease();
              null.remove();
            } 
          } 
          for (MediaRouter.RouteInfo routeInfo1 : list) {
            if (!this.mRouteControllerMap.containsKey(routeInfo1.mDescriptorId)) {
              MediaRouteProvider.RouteController routeController = routeInfo1.getProviderInstance().onCreateRouteController(routeInfo1.mDescriptorId, this.mSelectedRoute.mDescriptorId);
              routeController.onSelect();
              this.mRouteControllerMap.put(routeInfo1.mDescriptorId, routeController);
            } 
          } 
        } 
        updatePlaybackInfoFromSelectedRoute();
      } 
    }
    
    public void addProvider(MediaRouteProvider param1MediaRouteProvider) {
      if (findProviderInfo(param1MediaRouteProvider) < 0) {
        MediaRouter.ProviderInfo providerInfo = new MediaRouter.ProviderInfo(param1MediaRouteProvider);
        this.mProviders.add(providerInfo);
        if (MediaRouter.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Provider added: ");
          stringBuilder.append(providerInfo);
          Log.d("MediaRouter", stringBuilder.toString());
        } 
        this.mCallbackHandler.post(513, providerInfo);
        updateProviderContents(providerInfo, param1MediaRouteProvider.getDescriptor());
        param1MediaRouteProvider.setCallback(this.mProviderCallback);
        param1MediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
      } 
    }
    
    public void addRemoteControlClient(Object param1Object) {
      if (findRemoteControlClientRecord(param1Object) < 0) {
        param1Object = new RemoteControlClientRecord(param1Object);
        this.mRemoteControlClients.add(param1Object);
      } 
    }
    
    MediaRouter.RouteInfo chooseFallbackRoute() {
      for (MediaRouter.RouteInfo routeInfo : this.mRoutes) {
        if (routeInfo != this.mDefaultRoute && isSystemLiveAudioOnlyRoute(routeInfo) && routeInfo.isSelectable())
          return routeInfo; 
      } 
      return this.mDefaultRoute;
    }
    
    MediaRouter.RouteInfo getBluetoothRoute() {
      return this.mBluetoothRoute;
    }
    
    public ContentResolver getContentResolver() {
      return this.mApplicationContext.getContentResolver();
    }
    
    @NonNull
    MediaRouter.RouteInfo getDefaultRoute() {
      MediaRouter.RouteInfo routeInfo = this.mDefaultRoute;
      if (routeInfo != null)
        return routeInfo; 
      throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
    }
    
    public Display getDisplay(int param1Int) {
      return this.mDisplayManager.getDisplay(param1Int);
    }
    
    public MediaSessionCompat.Token getMediaSessionToken() {
      MediaSessionRecord mediaSessionRecord = this.mMediaSession;
      if (mediaSessionRecord != null)
        return mediaSessionRecord.getToken(); 
      MediaSessionCompat mediaSessionCompat = this.mCompatSession;
      return (mediaSessionCompat != null) ? mediaSessionCompat.getSessionToken() : null;
    }
    
    public Context getProviderContext(String param1String) {
      if (param1String.equals("android"))
        return this.mApplicationContext; 
      try {
        return this.mApplicationContext.createPackageContext(param1String, 4);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return null;
      } 
    }
    
    List<MediaRouter.ProviderInfo> getProviders() {
      return this.mProviders;
    }
    
    public MediaRouter.RouteInfo getRoute(String param1String) {
      for (MediaRouter.RouteInfo routeInfo : this.mRoutes) {
        if (routeInfo.mUniqueId.equals(param1String))
          return routeInfo; 
      } 
      return null;
    }
    
    public MediaRouter getRouter(Context param1Context) {
      int i = this.mRouters.size();
      while (--i >= 0) {
        MediaRouter mediaRouter1 = ((WeakReference<MediaRouter>)this.mRouters.get(i)).get();
        if (mediaRouter1 == null) {
          this.mRouters.remove(i);
          continue;
        } 
        if (mediaRouter1.mContext == param1Context)
          return mediaRouter1; 
      } 
      MediaRouter mediaRouter = new MediaRouter(param1Context);
      this.mRouters.add(new WeakReference<MediaRouter>(mediaRouter));
      return mediaRouter;
    }
    
    public List<MediaRouter.RouteInfo> getRoutes() {
      return this.mRoutes;
    }
    
    @NonNull
    MediaRouter.RouteInfo getSelectedRoute() {
      MediaRouter.RouteInfo routeInfo = this.mSelectedRoute;
      if (routeInfo != null)
        return routeInfo; 
      throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
    }
    
    public boolean isRouteAvailable(MediaRouteSelector param1MediaRouteSelector, int param1Int) {
      if (param1MediaRouteSelector.isEmpty())
        return false; 
      if ((param1Int & 0x2) == 0 && this.mLowRam)
        return true; 
      int i = this.mRoutes.size();
      for (byte b = 0; b < i; b++) {
        MediaRouter.RouteInfo routeInfo = this.mRoutes.get(b);
        if (((param1Int & 0x1) == 0 || !routeInfo.isDefaultOrBluetooth()) && routeInfo.matchesSelector(param1MediaRouteSelector))
          return true; 
      } 
      return false;
    }
    
    public void onSystemRouteSelectedByDescriptorId(String param1String) {
      this.mCallbackHandler.removeMessages(262);
      int i = findProviderInfo(this.mSystemProvider);
      if (i >= 0) {
        MediaRouter.ProviderInfo providerInfo = this.mProviders.get(i);
        i = providerInfo.findRouteByDescriptorId(param1String);
        if (i >= 0)
          ((MediaRouter.RouteInfo)providerInfo.mRoutes.get(i)).select(); 
      } 
    }
    
    public void removeProvider(MediaRouteProvider param1MediaRouteProvider) {
      int i = findProviderInfo(param1MediaRouteProvider);
      if (i >= 0) {
        param1MediaRouteProvider.setCallback(null);
        param1MediaRouteProvider.setDiscoveryRequest(null);
        MediaRouter.ProviderInfo providerInfo = this.mProviders.get(i);
        updateProviderContents(providerInfo, null);
        if (MediaRouter.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Provider removed: ");
          stringBuilder.append(providerInfo);
          Log.d("MediaRouter", stringBuilder.toString());
        } 
        this.mCallbackHandler.post(514, providerInfo);
        this.mProviders.remove(i);
      } 
    }
    
    public void removeRemoteControlClient(Object param1Object) {
      int i = findRemoteControlClientRecord(param1Object);
      if (i >= 0)
        ((RemoteControlClientRecord)this.mRemoteControlClients.remove(i)).disconnect(); 
    }
    
    public void requestSetVolume(MediaRouter.RouteInfo param1RouteInfo, int param1Int) {
      if (param1RouteInfo == this.mSelectedRoute) {
        MediaRouteProvider.RouteController routeController = this.mSelectedRouteController;
        if (routeController != null) {
          routeController.onSetVolume(param1Int);
          return;
        } 
      } 
      if (!this.mRouteControllerMap.isEmpty()) {
        MediaRouteProvider.RouteController routeController = this.mRouteControllerMap.get(param1RouteInfo.mDescriptorId);
        if (routeController != null)
          routeController.onSetVolume(param1Int); 
      } 
    }
    
    public void requestUpdateVolume(MediaRouter.RouteInfo param1RouteInfo, int param1Int) {
      if (param1RouteInfo == this.mSelectedRoute) {
        MediaRouteProvider.RouteController routeController = this.mSelectedRouteController;
        if (routeController != null)
          routeController.onUpdateVolume(param1Int); 
      } 
    }
    
    void selectRoute(@NonNull MediaRouter.RouteInfo param1RouteInfo) {
      selectRoute(param1RouteInfo, 3);
    }
    
    void selectRoute(@NonNull MediaRouter.RouteInfo param1RouteInfo, int param1Int) {
      if (!this.mRoutes.contains(param1RouteInfo)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring attempt to select removed route: ");
        stringBuilder.append(param1RouteInfo);
        Log.w("MediaRouter", stringBuilder.toString());
        return;
      } 
      if (!param1RouteInfo.mEnabled) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring attempt to select disabled route: ");
        stringBuilder.append(param1RouteInfo);
        Log.w("MediaRouter", stringBuilder.toString());
        return;
      } 
      setSelectedRouteInternal(param1RouteInfo, param1Int);
    }
    
    public void sendControlRequest(MediaRouter.RouteInfo param1RouteInfo, Intent param1Intent, MediaRouter.ControlRequestCallback param1ControlRequestCallback) {
      if (param1RouteInfo == this.mSelectedRoute) {
        MediaRouteProvider.RouteController routeController = this.mSelectedRouteController;
        if (routeController != null && routeController.onControlRequest(param1Intent, param1ControlRequestCallback))
          return; 
      } 
      if (param1ControlRequestCallback != null)
        param1ControlRequestCallback.onError(null, null); 
    }
    
    public void setMediaSession(Object param1Object) {
      if (param1Object != null) {
        param1Object = new MediaSessionRecord(param1Object);
      } else {
        param1Object = null;
      } 
      setMediaSessionRecord((MediaSessionRecord)param1Object);
    }
    
    public void setMediaSessionCompat(MediaSessionCompat param1MediaSessionCompat) {
      this.mCompatSession = param1MediaSessionCompat;
      if (Build.VERSION.SDK_INT >= 21) {
        if (param1MediaSessionCompat != null) {
          MediaSessionRecord mediaSessionRecord = new MediaSessionRecord(param1MediaSessionCompat);
        } else {
          param1MediaSessionCompat = null;
        } 
        setMediaSessionRecord((MediaSessionRecord)param1MediaSessionCompat);
      } else {
        MediaSessionCompat mediaSessionCompat = this.mRccMediaSession;
        if (mediaSessionCompat != null) {
          removeRemoteControlClient(mediaSessionCompat.getRemoteControlClient());
          this.mRccMediaSession.removeOnActiveChangeListener(this.mSessionActiveListener);
        } 
        this.mRccMediaSession = param1MediaSessionCompat;
        if (param1MediaSessionCompat != null) {
          param1MediaSessionCompat.addOnActiveChangeListener(this.mSessionActiveListener);
          if (param1MediaSessionCompat.isActive())
            addRemoteControlClient(param1MediaSessionCompat.getRemoteControlClient()); 
        } 
      } 
    }
    
    public void start() {
      addProvider(this.mSystemProvider);
      RegisteredMediaRouteProviderWatcher registeredMediaRouteProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
      this.mRegisteredProviderWatcher = registeredMediaRouteProviderWatcher;
      registeredMediaRouteProviderWatcher.start();
    }
    
    public void updateDiscoveryRequest() {
      MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
      int i = this.mRouters.size();
      byte b = 0;
      int j = 0;
      boolean bool = false;
      label54: while (true) {
        MediaRouteSelector mediaRouteSelector;
        int k = i - 1;
        if (k >= 0) {
          MediaRouter mediaRouter = ((WeakReference<MediaRouter>)this.mRouters.get(k)).get();
          if (mediaRouter == null) {
            this.mRouters.remove(k);
            i = k;
            continue;
          } 
          int m = mediaRouter.mCallbackRecords.size();
          byte b2 = 0;
          boolean bool1 = bool;
          int n = j;
          while (true) {
            i = k;
            j = n;
            bool = bool1;
            if (b2 < m) {
              MediaRouter.CallbackRecord callbackRecord = mediaRouter.mCallbackRecords.get(b2);
              builder.addSelector(callbackRecord.mSelector);
              i = callbackRecord.mFlags;
              if ((i & 0x1) != 0) {
                n = 1;
                bool1 = true;
              } 
              j = n;
              if ((i & 0x4) != 0) {
                j = n;
                if (!this.mLowRam)
                  j = 1; 
              } 
              n = j;
              if ((i & 0x8) != 0)
                n = 1; 
              b2++;
              continue;
            } 
            continue label54;
          } 
          break;
        } 
        if (j) {
          mediaRouteSelector = builder.build();
        } else {
          mediaRouteSelector = MediaRouteSelector.EMPTY;
        } 
        MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mDiscoveryRequest;
        if (mediaRouteDiscoveryRequest != null && mediaRouteDiscoveryRequest.getSelector().equals(mediaRouteSelector) && this.mDiscoveryRequest.isActiveScan() == bool)
          return; 
        if (mediaRouteSelector.isEmpty() && !bool) {
          if (this.mDiscoveryRequest == null)
            return; 
          this.mDiscoveryRequest = null;
        } else {
          this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(mediaRouteSelector, bool);
        } 
        if (MediaRouter.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Updated discovery request: ");
          stringBuilder.append(this.mDiscoveryRequest);
          Log.d("MediaRouter", stringBuilder.toString());
        } 
        if (j && !bool && this.mLowRam)
          Log.i("MediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY."); 
        j = this.mProviders.size();
        for (byte b1 = b; b1 < j; b1++)
          (this.mProviders.get(b1)).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest); 
        return;
      } 
    }
    
    void updateProviderDescriptor(MediaRouteProvider param1MediaRouteProvider, MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      int i = findProviderInfo(param1MediaRouteProvider);
      if (i >= 0)
        updateProviderContents(this.mProviders.get(i), param1MediaRouteProviderDescriptor); 
    }
    
    private final class CallbackHandler extends Handler {
      public static final int MSG_PROVIDER_ADDED = 513;
      
      public static final int MSG_PROVIDER_CHANGED = 515;
      
      public static final int MSG_PROVIDER_REMOVED = 514;
      
      public static final int MSG_ROUTE_ADDED = 257;
      
      public static final int MSG_ROUTE_CHANGED = 259;
      
      public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
      
      public static final int MSG_ROUTE_REMOVED = 258;
      
      public static final int MSG_ROUTE_SELECTED = 262;
      
      public static final int MSG_ROUTE_UNSELECTED = 263;
      
      public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
      
      private static final int MSG_TYPE_MASK = 65280;
      
      private static final int MSG_TYPE_PROVIDER = 512;
      
      private static final int MSG_TYPE_ROUTE = 256;
      
      private final ArrayList<MediaRouter.CallbackRecord> mTempCallbackRecords = new ArrayList<MediaRouter.CallbackRecord>();
      
      private void invokeCallback(MediaRouter.CallbackRecord param2CallbackRecord, int param2Int1, Object param2Object, int param2Int2) {
        MediaRouter.ProviderInfo providerInfo;
        MediaRouter mediaRouter = param2CallbackRecord.mRouter;
        MediaRouter.Callback callback = param2CallbackRecord.mCallback;
        int i = 0xFF00 & param2Int1;
        if (i != 256) {
          if (i != 512);
          providerInfo = (MediaRouter.ProviderInfo)param2Object;
          switch (param2Int1) {
            default:
              return;
            case 515:
              callback.onProviderChanged(mediaRouter, providerInfo);
            case 514:
              callback.onProviderRemoved(mediaRouter, providerInfo);
            case 513:
              break;
          } 
          callback.onProviderAdded(mediaRouter, providerInfo);
        } 
        param2Object = param2Object;
        if (!providerInfo.filterRouteEvent((MediaRouter.RouteInfo)param2Object));
        switch (param2Int1) {
          default:
          
          case 263:
            callback.onRouteUnselected(mediaRouter, (MediaRouter.RouteInfo)param2Object, param2Int2);
          case 262:
            callback.onRouteSelected(mediaRouter, (MediaRouter.RouteInfo)param2Object);
          case 261:
            callback.onRoutePresentationDisplayChanged(mediaRouter, (MediaRouter.RouteInfo)param2Object);
          case 260:
            callback.onRouteVolumeChanged(mediaRouter, (MediaRouter.RouteInfo)param2Object);
          case 259:
            callback.onRouteChanged(mediaRouter, (MediaRouter.RouteInfo)param2Object);
          case 258:
            callback.onRouteRemoved(mediaRouter, (MediaRouter.RouteInfo)param2Object);
          case 257:
            break;
        } 
        callback.onRouteAdded(mediaRouter, (MediaRouter.RouteInfo)param2Object);
      }
      
      private void syncWithSystemProvider(int param2Int, Object param2Object) {
        if (param2Int != 262) {
          switch (param2Int) {
            default:
              return;
            case 259:
              MediaRouter.GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((MediaRouter.RouteInfo)param2Object);
            case 258:
              MediaRouter.GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((MediaRouter.RouteInfo)param2Object);
            case 257:
              break;
          } 
          MediaRouter.GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((MediaRouter.RouteInfo)param2Object);
        } 
        MediaRouter.GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected((MediaRouter.RouteInfo)param2Object);
      }
      
      public void handleMessage(Message param2Message) {
        int i = param2Message.what;
        Object object = param2Message.obj;
        int j = param2Message.arg1;
        if (i == 259 && MediaRouter.GlobalMediaRouter.this.getSelectedRoute().getId().equals(((MediaRouter.RouteInfo)object).getId()))
          MediaRouter.GlobalMediaRouter.this.updateSelectedRouteIfNeeded(true); 
        syncWithSystemProvider(i, object);
        try {
          int k = MediaRouter.GlobalMediaRouter.this.mRouters.size();
          while (--k >= 0) {
            MediaRouter mediaRouter = ((WeakReference<MediaRouter>)MediaRouter.GlobalMediaRouter.this.mRouters.get(k)).get();
            if (mediaRouter == null) {
              MediaRouter.GlobalMediaRouter.this.mRouters.remove(k);
              continue;
            } 
            this.mTempCallbackRecords.addAll(mediaRouter.mCallbackRecords);
          } 
          int m = this.mTempCallbackRecords.size();
          for (k = 0; k < m; k++)
            invokeCallback(this.mTempCallbackRecords.get(k), i, object, j); 
          return;
        } finally {
          this.mTempCallbackRecords.clear();
        } 
      }
      
      public void post(int param2Int, Object param2Object) {
        obtainMessage(param2Int, param2Object).sendToTarget();
      }
      
      public void post(int param2Int1, Object param2Object, int param2Int2) {
        param2Object = obtainMessage(param2Int1, param2Object);
        ((Message)param2Object).arg1 = param2Int2;
        param2Object.sendToTarget();
      }
    }
    
    private final class MediaSessionRecord {
      private int mControlType;
      
      private int mMaxVolume;
      
      private final MediaSessionCompat mMsCompat;
      
      private VolumeProviderCompat mVpCompat;
      
      public MediaSessionRecord(MediaSessionCompat param2MediaSessionCompat) {
        this.mMsCompat = param2MediaSessionCompat;
      }
      
      public MediaSessionRecord(Object param2Object) {
        this.mMsCompat = MediaSessionCompat.fromMediaSession(MediaRouter.GlobalMediaRouter.this.mApplicationContext, param2Object);
      }
      
      public void clearVolumeHandling() {
        this.mMsCompat.setPlaybackToLocal(MediaRouter.GlobalMediaRouter.this.mPlaybackInfo.playbackStream);
        this.mVpCompat = null;
      }
      
      public void configureVolume(int param2Int1, int param2Int2, int param2Int3) {
        VolumeProviderCompat volumeProviderCompat = this.mVpCompat;
        if (volumeProviderCompat != null && param2Int1 == this.mControlType && param2Int2 == this.mMaxVolume) {
          volumeProviderCompat.setCurrentVolume(param2Int3);
        } else {
          volumeProviderCompat = new VolumeProviderCompat(param2Int1, param2Int2, param2Int3) {
              public void onAdjustVolume(final int direction) {
                MediaRouter.GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                      public void run() {
                        MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
                        if (routeInfo != null)
                          routeInfo.requestUpdateVolume(direction); 
                      }
                    });
              }
              
              public void onSetVolumeTo(final int volume) {
                MediaRouter.GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                      public void run() {
                        MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
                        if (routeInfo != null)
                          routeInfo.requestSetVolume(volume); 
                      }
                    });
              }
            };
          this.mVpCompat = volumeProviderCompat;
          this.mMsCompat.setPlaybackToRemote(volumeProviderCompat);
        } 
      }
      
      public MediaSessionCompat.Token getToken() {
        return this.mMsCompat.getSessionToken();
      }
    }
    
    class null extends VolumeProviderCompat {
      null(int param2Int1, int param2Int2, int param2Int3) {
        super(param2Int1, param2Int2, param2Int3);
      }
      
      public void onAdjustVolume(final int direction) {
        MediaRouter.GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
              public void run() {
                MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
                if (routeInfo != null)
                  routeInfo.requestUpdateVolume(direction); 
              }
            });
      }
      
      public void onSetVolumeTo(final int volume) {
        MediaRouter.GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
              public void run() {
                MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
                if (routeInfo != null)
                  routeInfo.requestSetVolume(volume); 
              }
            });
      }
    }
    
    class null implements Runnable {
      public void run() {
        MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
        if (routeInfo != null)
          routeInfo.requestSetVolume(volume); 
      }
    }
    
    class null implements Runnable {
      public void run() {
        MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
        if (routeInfo != null)
          routeInfo.requestUpdateVolume(direction); 
      }
    }
    
    private final class ProviderCallback extends MediaRouteProvider.Callback {
      public void onDescriptorChanged(MediaRouteProvider param2MediaRouteProvider, MediaRouteProviderDescriptor param2MediaRouteProviderDescriptor) {
        MediaRouter.GlobalMediaRouter.this.updateProviderDescriptor(param2MediaRouteProvider, param2MediaRouteProviderDescriptor);
      }
    }
    
    private final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
      private boolean mDisconnected;
      
      private final RemoteControlClientCompat mRccCompat;
      
      public RemoteControlClientRecord(Object param2Object) {
        RemoteControlClientCompat remoteControlClientCompat = RemoteControlClientCompat.obtain(MediaRouter.GlobalMediaRouter.this.mApplicationContext, param2Object);
        this.mRccCompat = remoteControlClientCompat;
        remoteControlClientCompat.setVolumeCallback(this);
        updatePlaybackInfo();
      }
      
      public void disconnect() {
        this.mDisconnected = true;
        this.mRccCompat.setVolumeCallback(null);
      }
      
      public Object getRemoteControlClient() {
        return this.mRccCompat.getRemoteControlClient();
      }
      
      public void onVolumeSetRequest(int param2Int) {
        if (!this.mDisconnected) {
          MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
          if (routeInfo != null)
            routeInfo.requestSetVolume(param2Int); 
        } 
      }
      
      public void onVolumeUpdateRequest(int param2Int) {
        if (!this.mDisconnected) {
          MediaRouter.RouteInfo routeInfo = MediaRouter.GlobalMediaRouter.this.mSelectedRoute;
          if (routeInfo != null)
            routeInfo.requestUpdateVolume(param2Int); 
        } 
      }
      
      public void updatePlaybackInfo() {
        this.mRccCompat.setPlaybackInfo(MediaRouter.GlobalMediaRouter.this.mPlaybackInfo);
      }
    }
  }
  
  class null implements MediaSessionCompat.OnActiveChangeListener {
    public void onActiveChanged() {
      MediaSessionCompat mediaSessionCompat = this.this$0.mRccMediaSession;
      if (mediaSessionCompat != null)
        if (mediaSessionCompat.isActive()) {
          MediaRouter.GlobalMediaRouter globalMediaRouter = this.this$0;
          globalMediaRouter.addRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
        } else {
          MediaRouter.GlobalMediaRouter globalMediaRouter = this.this$0;
          globalMediaRouter.removeRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
        }  
    }
  }
  
  private final class CallbackHandler extends Handler {
    public static final int MSG_PROVIDER_ADDED = 513;
    
    public static final int MSG_PROVIDER_CHANGED = 515;
    
    public static final int MSG_PROVIDER_REMOVED = 514;
    
    public static final int MSG_ROUTE_ADDED = 257;
    
    public static final int MSG_ROUTE_CHANGED = 259;
    
    public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
    
    public static final int MSG_ROUTE_REMOVED = 258;
    
    public static final int MSG_ROUTE_SELECTED = 262;
    
    public static final int MSG_ROUTE_UNSELECTED = 263;
    
    public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
    
    private static final int MSG_TYPE_MASK = 65280;
    
    private static final int MSG_TYPE_PROVIDER = 512;
    
    private static final int MSG_TYPE_ROUTE = 256;
    
    private final ArrayList<MediaRouter.CallbackRecord> mTempCallbackRecords = new ArrayList<MediaRouter.CallbackRecord>();
    
    private void invokeCallback(MediaRouter.CallbackRecord param1CallbackRecord, int param1Int1, Object param1Object, int param1Int2) {
      MediaRouter.ProviderInfo providerInfo;
      MediaRouter mediaRouter = param1CallbackRecord.mRouter;
      MediaRouter.Callback callback = param1CallbackRecord.mCallback;
      int i = 0xFF00 & param1Int1;
      if (i != 256) {
        if (i != 512);
        providerInfo = (MediaRouter.ProviderInfo)param1Object;
        switch (param1Int1) {
          default:
            return;
          case 515:
            callback.onProviderChanged(mediaRouter, providerInfo);
          case 514:
            callback.onProviderRemoved(mediaRouter, providerInfo);
          case 513:
            break;
        } 
        callback.onProviderAdded(mediaRouter, providerInfo);
      } 
      param1Object = param1Object;
      if (!providerInfo.filterRouteEvent((MediaRouter.RouteInfo)param1Object));
      switch (param1Int1) {
        default:
        
        case 263:
          callback.onRouteUnselected(mediaRouter, (MediaRouter.RouteInfo)param1Object, param1Int2);
        case 262:
          callback.onRouteSelected(mediaRouter, (MediaRouter.RouteInfo)param1Object);
        case 261:
          callback.onRoutePresentationDisplayChanged(mediaRouter, (MediaRouter.RouteInfo)param1Object);
        case 260:
          callback.onRouteVolumeChanged(mediaRouter, (MediaRouter.RouteInfo)param1Object);
        case 259:
          callback.onRouteChanged(mediaRouter, (MediaRouter.RouteInfo)param1Object);
        case 258:
          callback.onRouteRemoved(mediaRouter, (MediaRouter.RouteInfo)param1Object);
        case 257:
          break;
      } 
      callback.onRouteAdded(mediaRouter, (MediaRouter.RouteInfo)param1Object);
    }
    
    private void syncWithSystemProvider(int param1Int, Object param1Object) {
      if (param1Int != 262) {
        switch (param1Int) {
          default:
            return;
          case 259:
            this.this$0.mSystemProvider.onSyncRouteChanged((MediaRouter.RouteInfo)param1Object);
          case 258:
            this.this$0.mSystemProvider.onSyncRouteRemoved((MediaRouter.RouteInfo)param1Object);
          case 257:
            break;
        } 
        this.this$0.mSystemProvider.onSyncRouteAdded((MediaRouter.RouteInfo)param1Object);
      } 
      this.this$0.mSystemProvider.onSyncRouteSelected((MediaRouter.RouteInfo)param1Object);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      Object object = param1Message.obj;
      int j = param1Message.arg1;
      if (i == 259 && this.this$0.getSelectedRoute().getId().equals(((MediaRouter.RouteInfo)object).getId()))
        this.this$0.updateSelectedRouteIfNeeded(true); 
      syncWithSystemProvider(i, object);
      try {
        int k = this.this$0.mRouters.size();
        while (--k >= 0) {
          MediaRouter mediaRouter = ((WeakReference<MediaRouter>)this.this$0.mRouters.get(k)).get();
          if (mediaRouter == null) {
            this.this$0.mRouters.remove(k);
            continue;
          } 
          this.mTempCallbackRecords.addAll(mediaRouter.mCallbackRecords);
        } 
        int m = this.mTempCallbackRecords.size();
        for (k = 0; k < m; k++)
          invokeCallback(this.mTempCallbackRecords.get(k), i, object, j); 
        return;
      } finally {
        this.mTempCallbackRecords.clear();
      } 
    }
    
    public void post(int param1Int, Object param1Object) {
      obtainMessage(param1Int, param1Object).sendToTarget();
    }
    
    public void post(int param1Int1, Object param1Object, int param1Int2) {
      param1Object = obtainMessage(param1Int1, param1Object);
      ((Message)param1Object).arg1 = param1Int2;
      param1Object.sendToTarget();
    }
  }
  
  private final class MediaSessionRecord {
    private int mControlType;
    
    private int mMaxVolume;
    
    private final MediaSessionCompat mMsCompat;
    
    private VolumeProviderCompat mVpCompat;
    
    public MediaSessionRecord(MediaSessionCompat param1MediaSessionCompat) {
      this.mMsCompat = param1MediaSessionCompat;
    }
    
    public MediaSessionRecord(Object param1Object) {
      this.mMsCompat = MediaSessionCompat.fromMediaSession(((MediaRouter.GlobalMediaRouter)MediaRouter.this).mApplicationContext, param1Object);
    }
    
    public void clearVolumeHandling() {
      this.mMsCompat.setPlaybackToLocal(this.this$0.mPlaybackInfo.playbackStream);
      this.mVpCompat = null;
    }
    
    public void configureVolume(int param1Int1, int param1Int2, int param1Int3) {
      VolumeProviderCompat volumeProviderCompat = this.mVpCompat;
      if (volumeProviderCompat != null && param1Int1 == this.mControlType && param1Int2 == this.mMaxVolume) {
        volumeProviderCompat.setCurrentVolume(param1Int3);
      } else {
        volumeProviderCompat = new VolumeProviderCompat(param1Int1, param1Int2, param1Int3) {
            public void onAdjustVolume(final int direction) {
              this.this$1.this$0.mCallbackHandler.post(new Runnable() {
                    public void run() {
                      MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
                      if (routeInfo != null)
                        routeInfo.requestUpdateVolume(direction); 
                    }
                  });
            }
            
            public void onSetVolumeTo(final int volume) {
              this.this$1.this$0.mCallbackHandler.post(new Runnable() {
                    public void run() {
                      MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
                      if (routeInfo != null)
                        routeInfo.requestSetVolume(volume); 
                    }
                  });
            }
          };
        this.mVpCompat = volumeProviderCompat;
        this.mMsCompat.setPlaybackToRemote(volumeProviderCompat);
      } 
    }
    
    public MediaSessionCompat.Token getToken() {
      return this.mMsCompat.getSessionToken();
    }
  }
  
  class null extends VolumeProviderCompat {
    null(int param1Int1, int param1Int2, int param1Int3) {
      super(param1Int1, param1Int2, param1Int3);
    }
    
    public void onAdjustVolume(final int direction) {
      this.this$1.this$0.mCallbackHandler.post(new Runnable() {
            public void run() {
              MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
              if (routeInfo != null)
                routeInfo.requestUpdateVolume(direction); 
            }
          });
    }
    
    public void onSetVolumeTo(final int volume) {
      this.this$1.this$0.mCallbackHandler.post(new Runnable() {
            public void run() {
              MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
              if (routeInfo != null)
                routeInfo.requestSetVolume(volume); 
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
      if (routeInfo != null)
        routeInfo.requestSetVolume(volume); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      MediaRouter.RouteInfo routeInfo = this.this$2.this$1.this$0.mSelectedRoute;
      if (routeInfo != null)
        routeInfo.requestUpdateVolume(direction); 
    }
  }
  
  private final class ProviderCallback extends MediaRouteProvider.Callback {
    public void onDescriptorChanged(MediaRouteProvider param1MediaRouteProvider, MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      this.this$0.updateProviderDescriptor(param1MediaRouteProvider, param1MediaRouteProviderDescriptor);
    }
  }
  
  private final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
    private boolean mDisconnected;
    
    private final RemoteControlClientCompat mRccCompat;
    
    public RemoteControlClientRecord(Object param1Object) {
      RemoteControlClientCompat remoteControlClientCompat = RemoteControlClientCompat.obtain(((MediaRouter.GlobalMediaRouter)MediaRouter.this).mApplicationContext, param1Object);
      this.mRccCompat = remoteControlClientCompat;
      remoteControlClientCompat.setVolumeCallback(this);
      updatePlaybackInfo();
    }
    
    public void disconnect() {
      this.mDisconnected = true;
      this.mRccCompat.setVolumeCallback(null);
    }
    
    public Object getRemoteControlClient() {
      return this.mRccCompat.getRemoteControlClient();
    }
    
    public void onVolumeSetRequest(int param1Int) {
      if (!this.mDisconnected) {
        MediaRouter.RouteInfo routeInfo = this.this$0.mSelectedRoute;
        if (routeInfo != null)
          routeInfo.requestSetVolume(param1Int); 
      } 
    }
    
    public void onVolumeUpdateRequest(int param1Int) {
      if (!this.mDisconnected) {
        MediaRouter.RouteInfo routeInfo = this.this$0.mSelectedRoute;
        if (routeInfo != null)
          routeInfo.requestUpdateVolume(param1Int); 
      } 
    }
    
    public void updatePlaybackInfo() {
      this.mRccCompat.setPlaybackInfo(this.this$0.mPlaybackInfo);
    }
  }
  
  public static final class ProviderInfo {
    private MediaRouteProviderDescriptor mDescriptor;
    
    private final MediaRouteProvider.ProviderMetadata mMetadata;
    
    private final MediaRouteProvider mProviderInstance;
    
    private Resources mResources;
    
    private boolean mResourcesNotAvailable;
    
    private final List<MediaRouter.RouteInfo> mRoutes = new ArrayList<MediaRouter.RouteInfo>();
    
    ProviderInfo(MediaRouteProvider param1MediaRouteProvider) {
      this.mProviderInstance = param1MediaRouteProvider;
      this.mMetadata = param1MediaRouteProvider.getMetadata();
    }
    
    int findRouteByDescriptorId(String param1String) {
      int i = this.mRoutes.size();
      for (byte b = 0; b < i; b++) {
        if ((this.mRoutes.get(b)).mDescriptorId.equals(param1String))
          return b; 
      } 
      return -1;
    }
    
    public ComponentName getComponentName() {
      return this.mMetadata.getComponentName();
    }
    
    public String getPackageName() {
      return this.mMetadata.getPackageName();
    }
    
    public MediaRouteProvider getProviderInstance() {
      MediaRouter.checkCallingThread();
      return this.mProviderInstance;
    }
    
    Resources getResources() {
      if (this.mResources == null && !this.mResourcesNotAvailable) {
        String str = getPackageName();
        Context context = MediaRouter.sGlobal.getProviderContext(str);
        if (context != null) {
          this.mResources = context.getResources();
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to obtain resources for route provider package: ");
          stringBuilder.append(str);
          Log.w("MediaRouter", stringBuilder.toString());
          this.mResourcesNotAvailable = true;
        } 
      } 
      return this.mResources;
    }
    
    public List<MediaRouter.RouteInfo> getRoutes() {
      MediaRouter.checkCallingThread();
      return this.mRoutes;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("MediaRouter.RouteProviderInfo{ packageName=");
      stringBuilder.append(getPackageName());
      stringBuilder.append(" }");
      return stringBuilder.toString();
    }
    
    boolean updateDescriptor(MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      if (this.mDescriptor != param1MediaRouteProviderDescriptor) {
        this.mDescriptor = param1MediaRouteProviderDescriptor;
        return true;
      } 
      return false;
    }
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class RouteGroup extends RouteInfo {
    private List<MediaRouter.RouteInfo> mRoutes = new ArrayList<MediaRouter.RouteInfo>();
    
    RouteGroup(MediaRouter.ProviderInfo param1ProviderInfo, String param1String1, String param1String2) {
      super(param1ProviderInfo, param1String1, param1String2);
    }
    
    public MediaRouter.RouteInfo getRouteAt(int param1Int) {
      return this.mRoutes.get(param1Int);
    }
    
    public int getRouteCount() {
      return this.mRoutes.size();
    }
    
    public List<MediaRouter.RouteInfo> getRoutes() {
      return this.mRoutes;
    }
    
    int maybeUpdateDescriptor(MediaRouteDescriptor param1MediaRouteDescriptor) {
      MediaRouteDescriptor mediaRouteDescriptor = this.mDescriptor;
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = bool1;
      if (mediaRouteDescriptor != param1MediaRouteDescriptor) {
        this.mDescriptor = param1MediaRouteDescriptor;
        bool3 = bool1;
        if (param1MediaRouteDescriptor != null) {
          List<String> list = param1MediaRouteDescriptor.getGroupMemberIds();
          ArrayList<MediaRouter.RouteInfo> arrayList = new ArrayList();
          if (list.size() != this.mRoutes.size())
            bool2 = true; 
          for (String str : list) {
            str = MediaRouter.sGlobal.getUniqueId(getProvider(), str);
            MediaRouter.RouteInfo routeInfo = MediaRouter.sGlobal.getRoute(str);
            if (routeInfo != null) {
              arrayList.add(routeInfo);
              if (!bool2 && !this.mRoutes.contains(routeInfo))
                bool2 = true; 
            } 
          } 
          bool3 = bool2;
          if (bool2) {
            this.mRoutes = arrayList;
            bool3 = bool2;
          } 
        } 
      } 
      return updateDescriptor(param1MediaRouteDescriptor) | bool3;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(super.toString());
      stringBuilder.append('[');
      int i = this.mRoutes.size();
      for (byte b = 0; b < i; b++) {
        if (b > 0)
          stringBuilder.append(", "); 
        stringBuilder.append(this.mRoutes.get(b));
      } 
      stringBuilder.append(']');
      return stringBuilder.toString();
    }
  }
  
  public static class RouteInfo {
    static final int CHANGE_GENERAL = 1;
    
    static final int CHANGE_PRESENTATION_DISPLAY = 4;
    
    static final int CHANGE_VOLUME = 2;
    
    public static final int CONNECTION_STATE_CONNECTED = 2;
    
    public static final int CONNECTION_STATE_CONNECTING = 1;
    
    public static final int CONNECTION_STATE_DISCONNECTED = 0;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int DEVICE_TYPE_BLUETOOTH = 3;
    
    public static final int DEVICE_TYPE_SPEAKER = 2;
    
    public static final int DEVICE_TYPE_TV = 1;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    
    public static final int PLAYBACK_TYPE_LOCAL = 0;
    
    public static final int PLAYBACK_TYPE_REMOTE = 1;
    
    public static final int PLAYBACK_VOLUME_FIXED = 0;
    
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
    
    static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
    
    private boolean mCanDisconnect;
    
    private boolean mConnecting;
    
    private int mConnectionState;
    
    private final ArrayList<IntentFilter> mControlFilters = new ArrayList<IntentFilter>();
    
    private String mDescription;
    
    MediaRouteDescriptor mDescriptor;
    
    private final String mDescriptorId;
    
    private int mDeviceType;
    
    private boolean mEnabled;
    
    private Bundle mExtras;
    
    private Uri mIconUri;
    
    private String mName;
    
    private int mPlaybackStream;
    
    private int mPlaybackType;
    
    private Display mPresentationDisplay;
    
    private int mPresentationDisplayId = -1;
    
    private final MediaRouter.ProviderInfo mProvider;
    
    private IntentSender mSettingsIntent;
    
    private final String mUniqueId;
    
    private int mVolume;
    
    private int mVolumeHandling;
    
    private int mVolumeMax;
    
    RouteInfo(MediaRouter.ProviderInfo param1ProviderInfo, String param1String1, String param1String2) {
      this.mProvider = param1ProviderInfo;
      this.mDescriptorId = param1String1;
      this.mUniqueId = param1String2;
    }
    
    private static boolean isSystemMediaRouteProvider(RouteInfo param1RouteInfo) {
      return TextUtils.equals(param1RouteInfo.getProviderInstance().getMetadata().getPackageName(), "android");
    }
    
    public boolean canDisconnect() {
      return this.mCanDisconnect;
    }
    
    public int getConnectionState() {
      return this.mConnectionState;
    }
    
    public List<IntentFilter> getControlFilters() {
      return this.mControlFilters;
    }
    
    @Nullable
    public String getDescription() {
      return this.mDescription;
    }
    
    String getDescriptorId() {
      return this.mDescriptorId;
    }
    
    public int getDeviceType() {
      return this.mDeviceType;
    }
    
    @Nullable
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Uri getIconUri() {
      return this.mIconUri;
    }
    
    @NonNull
    public String getId() {
      return this.mUniqueId;
    }
    
    public String getName() {
      return this.mName;
    }
    
    public int getPlaybackStream() {
      return this.mPlaybackStream;
    }
    
    public int getPlaybackType() {
      return this.mPlaybackType;
    }
    
    @Nullable
    public Display getPresentationDisplay() {
      MediaRouter.checkCallingThread();
      int i = this.mPresentationDisplayId;
      if (i >= 0 && this.mPresentationDisplay == null)
        this.mPresentationDisplay = MediaRouter.sGlobal.getDisplay(i); 
      return this.mPresentationDisplay;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getPresentationDisplayId() {
      return this.mPresentationDisplayId;
    }
    
    public MediaRouter.ProviderInfo getProvider() {
      return this.mProvider;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaRouteProvider getProviderInstance() {
      return this.mProvider.getProviderInstance();
    }
    
    @Nullable
    public IntentSender getSettingsIntent() {
      return this.mSettingsIntent;
    }
    
    public int getVolume() {
      return this.mVolume;
    }
    
    public int getVolumeHandling() {
      return this.mVolumeHandling;
    }
    
    public int getVolumeMax() {
      return this.mVolumeMax;
    }
    
    public boolean isBluetooth() {
      boolean bool;
      MediaRouter.checkCallingThread();
      if (MediaRouter.sGlobal.getBluetoothRoute() == this) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isConnecting() {
      return this.mConnecting;
    }
    
    public boolean isDefault() {
      boolean bool;
      MediaRouter.checkCallingThread();
      if (MediaRouter.sGlobal.getDefaultRoute() == this) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isDefaultOrBluetooth() {
      boolean bool = isDefault();
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (!bool)
        if (this.mDeviceType == 3) {
          bool2 = bool1;
        } else if (isSystemMediaRouteProvider(this) && supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !supportsControlCategory("android.media.intent.category.LIVE_VIDEO")) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    }
    
    public boolean isDeviceSpeaker() {
      boolean bool;
      int i = Resources.getSystem().getIdentifier("default_audio_route_name", "string", "android");
      if (isDefault() && Resources.getSystem().getText(i).equals(this.mName)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEnabled() {
      return this.mEnabled;
    }
    
    boolean isSelectable() {
      boolean bool;
      if (this.mDescriptor != null && this.mEnabled) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isSelected() {
      boolean bool;
      MediaRouter.checkCallingThread();
      if (MediaRouter.sGlobal.getSelectedRoute() == this) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean matchesSelector(@NonNull MediaRouteSelector param1MediaRouteSelector) {
      if (param1MediaRouteSelector != null) {
        MediaRouter.checkCallingThread();
        return param1MediaRouteSelector.matchesControlFilters(this.mControlFilters);
      } 
      throw new IllegalArgumentException("selector must not be null");
    }
    
    int maybeUpdateDescriptor(MediaRouteDescriptor param1MediaRouteDescriptor) {
      boolean bool;
      if (this.mDescriptor != param1MediaRouteDescriptor) {
        bool = updateDescriptor(param1MediaRouteDescriptor);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void requestSetVolume(int param1Int) {
      MediaRouter.checkCallingThread();
      MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, param1Int)));
    }
    
    public void requestUpdateVolume(int param1Int) {
      MediaRouter.checkCallingThread();
      if (param1Int != 0)
        MediaRouter.sGlobal.requestUpdateVolume(this, param1Int); 
    }
    
    public void select() {
      MediaRouter.checkCallingThread();
      MediaRouter.sGlobal.selectRoute(this);
    }
    
    public void sendControlRequest(@NonNull Intent param1Intent, @Nullable MediaRouter.ControlRequestCallback param1ControlRequestCallback) {
      if (param1Intent != null) {
        MediaRouter.checkCallingThread();
        MediaRouter.sGlobal.sendControlRequest(this, param1Intent, param1ControlRequestCallback);
        return;
      } 
      throw new IllegalArgumentException("intent must not be null");
    }
    
    public boolean supportsControlAction(@NonNull String param1String1, @NonNull String param1String2) {
      if (param1String1 != null) {
        if (param1String2 != null) {
          MediaRouter.checkCallingThread();
          int i = this.mControlFilters.size();
          for (byte b = 0; b < i; b++) {
            IntentFilter intentFilter = this.mControlFilters.get(b);
            if (intentFilter.hasCategory(param1String1) && intentFilter.hasAction(param1String2))
              return true; 
          } 
          return false;
        } 
        throw new IllegalArgumentException("action must not be null");
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("category must not be null");
      throw illegalArgumentException;
    }
    
    public boolean supportsControlCategory(@NonNull String param1String) {
      if (param1String != null) {
        MediaRouter.checkCallingThread();
        int i = this.mControlFilters.size();
        for (byte b = 0; b < i; b++) {
          if (((IntentFilter)this.mControlFilters.get(b)).hasCategory(param1String))
            return true; 
        } 
        return false;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("category must not be null");
      throw illegalArgumentException;
    }
    
    public boolean supportsControlRequest(@NonNull Intent param1Intent) {
      if (param1Intent != null) {
        MediaRouter.checkCallingThread();
        ContentResolver contentResolver = MediaRouter.sGlobal.getContentResolver();
        int i = this.mControlFilters.size();
        for (byte b = 0; b < i; b++) {
          if (((IntentFilter)this.mControlFilters.get(b)).match(contentResolver, param1Intent, true, "MediaRouter") >= 0)
            return true; 
        } 
        return false;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("intent must not be null");
      throw illegalArgumentException;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("MediaRouter.RouteInfo{ uniqueId=");
      stringBuilder.append(this.mUniqueId);
      stringBuilder.append(", name=");
      stringBuilder.append(this.mName);
      stringBuilder.append(", description=");
      stringBuilder.append(this.mDescription);
      stringBuilder.append(", iconUri=");
      stringBuilder.append(this.mIconUri);
      stringBuilder.append(", enabled=");
      stringBuilder.append(this.mEnabled);
      stringBuilder.append(", connecting=");
      stringBuilder.append(this.mConnecting);
      stringBuilder.append(", connectionState=");
      stringBuilder.append(this.mConnectionState);
      stringBuilder.append(", canDisconnect=");
      stringBuilder.append(this.mCanDisconnect);
      stringBuilder.append(", playbackType=");
      stringBuilder.append(this.mPlaybackType);
      stringBuilder.append(", playbackStream=");
      stringBuilder.append(this.mPlaybackStream);
      stringBuilder.append(", deviceType=");
      stringBuilder.append(this.mDeviceType);
      stringBuilder.append(", volumeHandling=");
      stringBuilder.append(this.mVolumeHandling);
      stringBuilder.append(", volume=");
      stringBuilder.append(this.mVolume);
      stringBuilder.append(", volumeMax=");
      stringBuilder.append(this.mVolumeMax);
      stringBuilder.append(", presentationDisplayId=");
      stringBuilder.append(this.mPresentationDisplayId);
      stringBuilder.append(", extras=");
      stringBuilder.append(this.mExtras);
      stringBuilder.append(", settingsIntent=");
      stringBuilder.append(this.mSettingsIntent);
      stringBuilder.append(", providerPackageName=");
      stringBuilder.append(this.mProvider.getPackageName());
      stringBuilder.append(" }");
      return stringBuilder.toString();
    }
    
    int updateDescriptor(MediaRouteDescriptor param1MediaRouteDescriptor) {
      this.mDescriptor = param1MediaRouteDescriptor;
      int i = 0;
      int j = 0;
      if (param1MediaRouteDescriptor != null) {
        i = j;
        if (!MediaRouter.equal(this.mName, param1MediaRouteDescriptor.getName())) {
          this.mName = param1MediaRouteDescriptor.getName();
          i = 1;
        } 
        j = i;
        if (!MediaRouter.equal(this.mDescription, param1MediaRouteDescriptor.getDescription())) {
          this.mDescription = param1MediaRouteDescriptor.getDescription();
          j = i | 0x1;
        } 
        i = j;
        if (!MediaRouter.equal(this.mIconUri, param1MediaRouteDescriptor.getIconUri())) {
          this.mIconUri = param1MediaRouteDescriptor.getIconUri();
          i = j | 0x1;
        } 
        j = i;
        if (this.mEnabled != param1MediaRouteDescriptor.isEnabled()) {
          this.mEnabled = param1MediaRouteDescriptor.isEnabled();
          j = i | 0x1;
        } 
        i = j;
        if (this.mConnecting != param1MediaRouteDescriptor.isConnecting()) {
          this.mConnecting = param1MediaRouteDescriptor.isConnecting();
          i = j | 0x1;
        } 
        int k = i;
        if (this.mConnectionState != param1MediaRouteDescriptor.getConnectionState()) {
          this.mConnectionState = param1MediaRouteDescriptor.getConnectionState();
          k = i | 0x1;
        } 
        j = k;
        if (!this.mControlFilters.equals(param1MediaRouteDescriptor.getControlFilters())) {
          this.mControlFilters.clear();
          this.mControlFilters.addAll(param1MediaRouteDescriptor.getControlFilters());
          j = k | 0x1;
        } 
        i = j;
        if (this.mPlaybackType != param1MediaRouteDescriptor.getPlaybackType()) {
          this.mPlaybackType = param1MediaRouteDescriptor.getPlaybackType();
          i = j | 0x1;
        } 
        j = i;
        if (this.mPlaybackStream != param1MediaRouteDescriptor.getPlaybackStream()) {
          this.mPlaybackStream = param1MediaRouteDescriptor.getPlaybackStream();
          j = i | 0x1;
        } 
        i = j;
        if (this.mDeviceType != param1MediaRouteDescriptor.getDeviceType()) {
          this.mDeviceType = param1MediaRouteDescriptor.getDeviceType();
          i = j | 0x1;
        } 
        j = i;
        if (this.mVolumeHandling != param1MediaRouteDescriptor.getVolumeHandling()) {
          this.mVolumeHandling = param1MediaRouteDescriptor.getVolumeHandling();
          j = i | 0x3;
        } 
        i = j;
        if (this.mVolume != param1MediaRouteDescriptor.getVolume()) {
          this.mVolume = param1MediaRouteDescriptor.getVolume();
          i = j | 0x3;
        } 
        j = i;
        if (this.mVolumeMax != param1MediaRouteDescriptor.getVolumeMax()) {
          this.mVolumeMax = param1MediaRouteDescriptor.getVolumeMax();
          j = i | 0x3;
        } 
        k = j;
        if (this.mPresentationDisplayId != param1MediaRouteDescriptor.getPresentationDisplayId()) {
          this.mPresentationDisplayId = param1MediaRouteDescriptor.getPresentationDisplayId();
          this.mPresentationDisplay = null;
          k = j | 0x5;
        } 
        i = k;
        if (!MediaRouter.equal(this.mExtras, param1MediaRouteDescriptor.getExtras())) {
          this.mExtras = param1MediaRouteDescriptor.getExtras();
          i = k | 0x1;
        } 
        j = i;
        if (!MediaRouter.equal(this.mSettingsIntent, param1MediaRouteDescriptor.getSettingsActivity())) {
          this.mSettingsIntent = param1MediaRouteDescriptor.getSettingsActivity();
          j = i | 0x1;
        } 
        i = j;
        if (this.mCanDisconnect != param1MediaRouteDescriptor.canDisconnectAndKeepPlaying()) {
          this.mCanDisconnect = param1MediaRouteDescriptor.canDisconnectAndKeepPlaying();
          i = j | 0x5;
        } 
      } 
      return i;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    private static @interface ConnectionState {}
    
    @Retention(RetentionPolicy.SOURCE)
    private static @interface DeviceType {}
    
    @Retention(RetentionPolicy.SOURCE)
    private static @interface PlaybackType {}
    
    @Retention(RetentionPolicy.SOURCE)
    private static @interface PlaybackVolume {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ConnectionState {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface DeviceType {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface PlaybackType {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface PlaybackVolume {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */