package android.support.v7.app;

import android.support.annotation.NonNull;

public class MediaRouteDialogFactory {
  private static final MediaRouteDialogFactory sDefault = new MediaRouteDialogFactory();
  
  @NonNull
  public static MediaRouteDialogFactory getDefault() {
    return sDefault;
  }
  
  @NonNull
  public MediaRouteChooserDialogFragment onCreateChooserDialogFragment() {
    return new MediaRouteChooserDialogFragment();
  }
  
  @NonNull
  public MediaRouteControllerDialogFragment onCreateControllerDialogFragment() {
    return new MediaRouteControllerDialogFragment();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteDialogFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */