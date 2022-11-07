package android.support.v7.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ActionProvider;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

public class MediaRouteActionProvider extends ActionProvider {
  private static final String TAG = "MediaRouteActionProvider";
  
  private MediaRouteButton mButton;
  
  private final MediaRouterCallback mCallback;
  
  private MediaRouteDialogFactory mDialogFactory = MediaRouteDialogFactory.getDefault();
  
  private final MediaRouter mRouter;
  
  private MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;
  
  public MediaRouteActionProvider(Context paramContext) {
    super(paramContext);
    this.mRouter = MediaRouter.getInstance(paramContext);
    this.mCallback = new MediaRouterCallback(this);
  }
  
  @NonNull
  public MediaRouteDialogFactory getDialogFactory() {
    return this.mDialogFactory;
  }
  
  @Nullable
  public MediaRouteButton getMediaRouteButton() {
    return this.mButton;
  }
  
  @NonNull
  public MediaRouteSelector getRouteSelector() {
    return this.mSelector;
  }
  
  public boolean isVisible() {
    return this.mRouter.isRouteAvailable(this.mSelector, 1);
  }
  
  public View onCreateActionView() {
    if (this.mButton != null)
      Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item..."); 
    MediaRouteButton mediaRouteButton = onCreateMediaRouteButton();
    this.mButton = mediaRouteButton;
    mediaRouteButton.setCheatSheetEnabled(true);
    this.mButton.setRouteSelector(this.mSelector);
    this.mButton.setDialogFactory(this.mDialogFactory);
    this.mButton.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
    return this.mButton;
  }
  
  public MediaRouteButton onCreateMediaRouteButton() {
    return new MediaRouteButton(getContext());
  }
  
  public boolean onPerformDefaultAction() {
    MediaRouteButton mediaRouteButton = this.mButton;
    return (mediaRouteButton != null) ? mediaRouteButton.showDialog() : false;
  }
  
  public boolean overridesItemVisibility() {
    return true;
  }
  
  void refreshRoute() {
    refreshVisibility();
  }
  
  public void setDialogFactory(@NonNull MediaRouteDialogFactory paramMediaRouteDialogFactory) {
    if (paramMediaRouteDialogFactory != null) {
      if (this.mDialogFactory != paramMediaRouteDialogFactory) {
        this.mDialogFactory = paramMediaRouteDialogFactory;
        MediaRouteButton mediaRouteButton = this.mButton;
        if (mediaRouteButton != null)
          mediaRouteButton.setDialogFactory(paramMediaRouteDialogFactory); 
      } 
      return;
    } 
    throw new IllegalArgumentException("factory must not be null");
  }
  
  public void setRouteSelector(@NonNull MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      if (!this.mSelector.equals(paramMediaRouteSelector)) {
        if (!this.mSelector.isEmpty())
          this.mRouter.removeCallback(this.mCallback); 
        if (!paramMediaRouteSelector.isEmpty())
          this.mRouter.addCallback(paramMediaRouteSelector, this.mCallback); 
        this.mSelector = paramMediaRouteSelector;
        refreshRoute();
        MediaRouteButton mediaRouteButton = this.mButton;
        if (mediaRouteButton != null)
          mediaRouteButton.setRouteSelector(paramMediaRouteSelector); 
      } 
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  private static final class MediaRouterCallback extends MediaRouter.Callback {
    private final WeakReference<MediaRouteActionProvider> mProviderWeak;
    
    public MediaRouterCallback(MediaRouteActionProvider param1MediaRouteActionProvider) {
      this.mProviderWeak = new WeakReference<MediaRouteActionProvider>(param1MediaRouteActionProvider);
    }
    
    private void refreshRoute(MediaRouter param1MediaRouter) {
      MediaRouteActionProvider mediaRouteActionProvider = this.mProviderWeak.get();
      if (mediaRouteActionProvider != null) {
        mediaRouteActionProvider.refreshRoute();
      } else {
        param1MediaRouter.removeCallback(this);
      } 
    }
    
    public void onProviderAdded(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onProviderChanged(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onProviderRemoved(MediaRouter param1MediaRouter, MediaRouter.ProviderInfo param1ProviderInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteActionProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */