package android.support.v7.media;

import android.media.MediaRouter;
import android.support.annotation.RequiresApi;

@RequiresApi(18)
final class MediaRouterJellybeanMr2 {
  public static void addCallback(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2) {
    ((MediaRouter)paramObject1).addCallback(paramInt1, (MediaRouter.Callback)paramObject2, paramInt2);
  }
  
  public static Object getDefaultRoute(Object paramObject) {
    return ((MediaRouter)paramObject).getDefaultRoute();
  }
  
  public static final class RouteInfo {
    public static CharSequence getDescription(Object param1Object) {
      return ((MediaRouter.RouteInfo)param1Object).getDescription();
    }
    
    public static boolean isConnecting(Object param1Object) {
      return ((MediaRouter.RouteInfo)param1Object).isConnecting();
    }
  }
  
  public static final class UserRouteInfo {
    public static void setDescription(Object param1Object, CharSequence param1CharSequence) {
      ((MediaRouter.UserRouteInfo)param1Object).setDescription(param1CharSequence);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouterJellybeanMr2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */