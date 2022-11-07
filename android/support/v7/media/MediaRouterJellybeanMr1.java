package android.support.v7.media;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(17)
final class MediaRouterJellybeanMr1 {
  private static final String TAG = "MediaRouterJellybeanMr1";
  
  public static Object createCallback(Callback paramCallback) {
    return new CallbackProxy<Callback>(paramCallback);
  }
  
  public static final class ActiveScanWorkaround implements Runnable {
    private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
    
    private boolean mActivelyScanningWifiDisplays;
    
    private final DisplayManager mDisplayManager;
    
    private final Handler mHandler;
    
    private Method mScanWifiDisplaysMethod;
    
    public ActiveScanWorkaround(Context param1Context, Handler param1Handler) {
      if (Build.VERSION.SDK_INT == 17) {
        this.mDisplayManager = (DisplayManager)param1Context.getSystemService("display");
        this.mHandler = param1Handler;
        try {
          this.mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays", new Class[0]);
        } catch (NoSuchMethodException noSuchMethodException) {}
        return;
      } 
      throw new UnsupportedOperationException();
    }
    
    public void run() {
      if (this.mActivelyScanningWifiDisplays) {
        try {
          this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager, new Object[0]);
        } catch (IllegalAccessException illegalAccessException) {
          Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
          Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", invocationTargetException);
        } 
        this.mHandler.postDelayed(this, 15000L);
      } 
    }
    
    public void setActiveScanRouteTypes(int param1Int) {
      if ((param1Int & 0x2) != 0) {
        if (!this.mActivelyScanningWifiDisplays)
          if (this.mScanWifiDisplaysMethod != null) {
            this.mActivelyScanningWifiDisplays = true;
            this.mHandler.post(this);
          } else {
            Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
          }  
      } else if (this.mActivelyScanningWifiDisplays) {
        this.mActivelyScanningWifiDisplays = false;
        this.mHandler.removeCallbacks(this);
      } 
    }
  }
  
  public static interface Callback extends MediaRouterJellybean.Callback {
    void onRoutePresentationDisplayChanged(Object param1Object);
  }
  
  static class CallbackProxy<T extends Callback> extends MediaRouterJellybean.CallbackProxy<T> {
    public CallbackProxy(T param1T) {
      super(param1T);
    }
    
    public void onRoutePresentationDisplayChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      ((MediaRouterJellybeanMr1.Callback)this.mCallback).onRoutePresentationDisplayChanged(param1RouteInfo);
    }
  }
  
  public static final class IsConnectingWorkaround {
    private Method mGetStatusCodeMethod;
    
    private int mStatusConnecting;
    
    public IsConnectingWorkaround() {
      if (Build.VERSION.SDK_INT == 17) {
        try {
          this.mStatusConnecting = MediaRouter.RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
          this.mGetStatusCodeMethod = MediaRouter.RouteInfo.class.getMethod("getStatusCode", new Class[0]);
        } catch (NoSuchFieldException|NoSuchMethodException|IllegalAccessException noSuchFieldException) {}
        return;
      } 
      throw new UnsupportedOperationException();
    }
    
    public boolean isConnecting(Object param1Object) {
      param1Object = param1Object;
      Method method = this.mGetStatusCodeMethod;
      boolean bool1 = false;
      boolean bool2 = bool1;
      if (method != null)
        try {
          int i = ((Integer)method.invoke(param1Object, new Object[0])).intValue();
          int j = this.mStatusConnecting;
          bool2 = bool1;
          if (i == j)
            bool2 = true; 
        } catch (IllegalAccessException|InvocationTargetException illegalAccessException) {
          bool2 = bool1;
        }  
      return bool2;
    }
  }
  
  public static final class RouteInfo {
    public static Display getPresentationDisplay(Object param1Object) {
      try {
        return ((MediaRouter.RouteInfo)param1Object).getPresentationDisplay();
      } catch (NoSuchMethodError noSuchMethodError) {
        Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", noSuchMethodError);
        return null;
      } 
    }
    
    public static boolean isEnabled(Object param1Object) {
      return ((MediaRouter.RouteInfo)param1Object).isEnabled();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouterJellybeanMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */