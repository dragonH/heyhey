package android.support.v7.media;

import android.os.Bundle;

public final class MediaRouteDiscoveryRequest {
  private static final String KEY_ACTIVE_SCAN = "activeScan";
  
  private static final String KEY_SELECTOR = "selector";
  
  private final Bundle mBundle;
  
  private MediaRouteSelector mSelector;
  
  private MediaRouteDiscoveryRequest(Bundle paramBundle) {
    this.mBundle = paramBundle;
  }
  
  public MediaRouteDiscoveryRequest(MediaRouteSelector paramMediaRouteSelector, boolean paramBoolean) {
    if (paramMediaRouteSelector != null) {
      Bundle bundle = new Bundle();
      this.mBundle = bundle;
      this.mSelector = paramMediaRouteSelector;
      bundle.putBundle("selector", paramMediaRouteSelector.asBundle());
      bundle.putBoolean("activeScan", paramBoolean);
      return;
    } 
    throw new IllegalArgumentException("selector must not be null");
  }
  
  private void ensureSelector() {
    if (this.mSelector == null) {
      MediaRouteSelector mediaRouteSelector = MediaRouteSelector.fromBundle(this.mBundle.getBundle("selector"));
      this.mSelector = mediaRouteSelector;
      if (mediaRouteSelector == null)
        this.mSelector = MediaRouteSelector.EMPTY; 
    } 
  }
  
  public static MediaRouteDiscoveryRequest fromBundle(Bundle paramBundle) {
    if (paramBundle != null) {
      MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = new MediaRouteDiscoveryRequest(paramBundle);
    } else {
      paramBundle = null;
    } 
    return (MediaRouteDiscoveryRequest)paramBundle;
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof MediaRouteDiscoveryRequest;
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (bool) {
      paramObject = paramObject;
      bool2 = bool1;
      if (getSelector().equals(paramObject.getSelector())) {
        bool2 = bool1;
        if (isActiveScan() == paramObject.isActiveScan())
          bool2 = true; 
      } 
    } 
    return bool2;
  }
  
  public MediaRouteSelector getSelector() {
    ensureSelector();
    return this.mSelector;
  }
  
  public int hashCode() {
    return getSelector().hashCode() ^ isActiveScan();
  }
  
  public boolean isActiveScan() {
    return this.mBundle.getBoolean("activeScan");
  }
  
  public boolean isValid() {
    ensureSelector();
    return this.mSelector.isValid();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DiscoveryRequest{ selector=");
    stringBuilder.append(getSelector());
    stringBuilder.append(", activeScan=");
    stringBuilder.append(isActiveScan());
    stringBuilder.append(", isValid=");
    stringBuilder.append(isValid());
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteDiscoveryRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */