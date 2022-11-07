package android.support.v7.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;

public class MediaRouteDiscoveryFragment extends Fragment {
  private final String ARGUMENT_SELECTOR = "selector";
  
  private MediaRouter.Callback mCallback;
  
  private MediaRouter mRouter;
  
  private MediaRouteSelector mSelector;
  
  private void ensureRouteSelector() {
    if (this.mSelector == null) {
      Bundle bundle = getArguments();
      if (bundle != null)
        this.mSelector = MediaRouteSelector.fromBundle(bundle.getBundle("selector")); 
      if (this.mSelector == null)
        this.mSelector = MediaRouteSelector.EMPTY; 
    } 
  }
  
  private void ensureRouter() {
    if (this.mRouter == null)
      this.mRouter = MediaRouter.getInstance(getContext()); 
  }
  
  public MediaRouter getMediaRouter() {
    ensureRouter();
    return this.mRouter;
  }
  
  public MediaRouteSelector getRouteSelector() {
    ensureRouteSelector();
    return this.mSelector;
  }
  
  public MediaRouter.Callback onCreateCallback() {
    return new MediaRouter.Callback() {
      
      };
  }
  
  public int onPrepareCallbackFlags() {
    return 4;
  }
  
  public void onStart() {
    super.onStart();
    ensureRouteSelector();
    ensureRouter();
    MediaRouter.Callback callback = onCreateCallback();
    this.mCallback = callback;
    if (callback != null)
      this.mRouter.addCallback(this.mSelector, callback, onPrepareCallbackFlags()); 
  }
  
  public void onStop() {
    MediaRouter.Callback callback = this.mCallback;
    if (callback != null) {
      this.mRouter.removeCallback(callback);
      this.mCallback = null;
    } 
    super.onStop();
  }
  
  public void setRouteSelector(MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      ensureRouteSelector();
      if (!this.mSelector.equals(paramMediaRouteSelector)) {
        this.mSelector = paramMediaRouteSelector;
        Bundle bundle1 = getArguments();
        Bundle bundle2 = bundle1;
        if (bundle1 == null)
          bundle2 = new Bundle(); 
        bundle2.putBundle("selector", paramMediaRouteSelector.asBundle());
        setArguments(bundle2);
        MediaRouter.Callback callback = this.mCallback;
        if (callback != null) {
          this.mRouter.removeCallback(callback);
          this.mRouter.addCallback(this.mSelector, this.mCallback, onPrepareCallbackFlags());
        } 
      } 
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/MediaRouteDiscoveryFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */