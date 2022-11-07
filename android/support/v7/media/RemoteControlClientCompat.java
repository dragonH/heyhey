package android.support.v7.media;

import android.content.Context;
import android.support.annotation.RequiresApi;
import java.lang.ref.WeakReference;

abstract class RemoteControlClientCompat {
  protected final Context mContext;
  
  protected final Object mRcc;
  
  protected VolumeCallback mVolumeCallback;
  
  protected RemoteControlClientCompat(Context paramContext, Object paramObject) {
    this.mContext = paramContext;
    this.mRcc = paramObject;
  }
  
  public static RemoteControlClientCompat obtain(Context paramContext, Object paramObject) {
    return new JellybeanImpl(paramContext, paramObject);
  }
  
  public Object getRemoteControlClient() {
    return this.mRcc;
  }
  
  public void setPlaybackInfo(PlaybackInfo paramPlaybackInfo) {}
  
  public void setVolumeCallback(VolumeCallback paramVolumeCallback) {
    this.mVolumeCallback = paramVolumeCallback;
  }
  
  @RequiresApi(16)
  static class JellybeanImpl extends RemoteControlClientCompat {
    private boolean mRegistered;
    
    private final Object mRouterObj;
    
    private final Object mUserRouteCategoryObj;
    
    private final Object mUserRouteObj;
    
    public JellybeanImpl(Context param1Context, Object param1Object) {
      super(param1Context, param1Object);
      param1Object = MediaRouterJellybean.getMediaRouter(param1Context);
      this.mRouterObj = param1Object;
      Object object = MediaRouterJellybean.createRouteCategory(param1Object, "", false);
      this.mUserRouteCategoryObj = object;
      this.mUserRouteObj = MediaRouterJellybean.createUserRoute(param1Object, object);
    }
    
    public void setPlaybackInfo(RemoteControlClientCompat.PlaybackInfo param1PlaybackInfo) {
      MediaRouterJellybean.UserRouteInfo.setVolume(this.mUserRouteObj, param1PlaybackInfo.volume);
      MediaRouterJellybean.UserRouteInfo.setVolumeMax(this.mUserRouteObj, param1PlaybackInfo.volumeMax);
      MediaRouterJellybean.UserRouteInfo.setVolumeHandling(this.mUserRouteObj, param1PlaybackInfo.volumeHandling);
      MediaRouterJellybean.UserRouteInfo.setPlaybackStream(this.mUserRouteObj, param1PlaybackInfo.playbackStream);
      MediaRouterJellybean.UserRouteInfo.setPlaybackType(this.mUserRouteObj, param1PlaybackInfo.playbackType);
      if (!this.mRegistered) {
        this.mRegistered = true;
        MediaRouterJellybean.UserRouteInfo.setVolumeCallback(this.mUserRouteObj, MediaRouterJellybean.createVolumeCallback(new VolumeCallbackWrapper(this)));
        MediaRouterJellybean.UserRouteInfo.setRemoteControlClient(this.mUserRouteObj, this.mRcc);
      } 
    }
    
    private static final class VolumeCallbackWrapper implements MediaRouterJellybean.VolumeCallback {
      private final WeakReference<RemoteControlClientCompat.JellybeanImpl> mImplWeak;
      
      public VolumeCallbackWrapper(RemoteControlClientCompat.JellybeanImpl param2JellybeanImpl) {
        this.mImplWeak = new WeakReference<RemoteControlClientCompat.JellybeanImpl>(param2JellybeanImpl);
      }
      
      public void onVolumeSetRequest(Object param2Object, int param2Int) {
        param2Object = this.mImplWeak.get();
        if (param2Object != null) {
          param2Object = ((RemoteControlClientCompat)param2Object).mVolumeCallback;
          if (param2Object != null)
            param2Object.onVolumeSetRequest(param2Int); 
        } 
      }
      
      public void onVolumeUpdateRequest(Object param2Object, int param2Int) {
        param2Object = this.mImplWeak.get();
        if (param2Object != null) {
          param2Object = ((RemoteControlClientCompat)param2Object).mVolumeCallback;
          if (param2Object != null)
            param2Object.onVolumeUpdateRequest(param2Int); 
        } 
      }
    }
  }
  
  private static final class VolumeCallbackWrapper implements MediaRouterJellybean.VolumeCallback {
    private final WeakReference<RemoteControlClientCompat.JellybeanImpl> mImplWeak;
    
    public VolumeCallbackWrapper(RemoteControlClientCompat.JellybeanImpl param1JellybeanImpl) {
      this.mImplWeak = new WeakReference<RemoteControlClientCompat.JellybeanImpl>(param1JellybeanImpl);
    }
    
    public void onVolumeSetRequest(Object param1Object, int param1Int) {
      param1Object = this.mImplWeak.get();
      if (param1Object != null) {
        param1Object = ((RemoteControlClientCompat)param1Object).mVolumeCallback;
        if (param1Object != null)
          param1Object.onVolumeSetRequest(param1Int); 
      } 
    }
    
    public void onVolumeUpdateRequest(Object param1Object, int param1Int) {
      param1Object = this.mImplWeak.get();
      if (param1Object != null) {
        param1Object = ((RemoteControlClientCompat)param1Object).mVolumeCallback;
        if (param1Object != null)
          param1Object.onVolumeUpdateRequest(param1Int); 
      } 
    }
  }
  
  static class LegacyImpl extends RemoteControlClientCompat {
    public LegacyImpl(Context param1Context, Object param1Object) {
      super(param1Context, param1Object);
    }
  }
  
  public static final class PlaybackInfo {
    public int playbackStream = 3;
    
    public int playbackType = 1;
    
    public int volume;
    
    public int volumeHandling = 0;
    
    public int volumeMax;
  }
  
  public static interface VolumeCallback {
    void onVolumeSetRequest(int param1Int);
    
    void onVolumeUpdateRequest(int param1Int);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/RemoteControlClientCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */