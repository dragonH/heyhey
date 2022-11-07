package android.support.v7.media;

import android.media.MediaRouter;
import android.support.annotation.RequiresApi;

@RequiresApi(24)
final class MediaRouterApi24 {
  public static final class RouteInfo {
    public static int getDeviceType(Object param1Object) {
      return ((MediaRouter.RouteInfo)param1Object).getDeviceType();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouterApi24.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */