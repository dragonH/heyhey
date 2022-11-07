package android.support.v7.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ObjectsCompat;

public abstract class MediaRouteProvider {
  static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
  
  static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
  
  private Callback mCallback;
  
  private final Context mContext;
  
  private MediaRouteProviderDescriptor mDescriptor;
  
  private MediaRouteDiscoveryRequest mDiscoveryRequest;
  
  private final ProviderHandler mHandler = new ProviderHandler();
  
  private final ProviderMetadata mMetadata;
  
  private boolean mPendingDescriptorChange;
  
  private boolean mPendingDiscoveryRequestChange;
  
  public MediaRouteProvider(@NonNull Context paramContext) {
    this(paramContext, null);
  }
  
  MediaRouteProvider(Context paramContext, ProviderMetadata paramProviderMetadata) {
    if (paramContext != null) {
      this.mContext = paramContext;
      if (paramProviderMetadata == null) {
        this.mMetadata = new ProviderMetadata(new ComponentName(paramContext, getClass()));
      } else {
        this.mMetadata = paramProviderMetadata;
      } 
      return;
    } 
    throw new IllegalArgumentException("context must not be null");
  }
  
  void deliverDescriptorChanged() {
    this.mPendingDescriptorChange = false;
    Callback callback = this.mCallback;
    if (callback != null)
      callback.onDescriptorChanged(this, this.mDescriptor); 
  }
  
  void deliverDiscoveryRequestChanged() {
    this.mPendingDiscoveryRequestChange = false;
    onDiscoveryRequestChanged(this.mDiscoveryRequest);
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  @Nullable
  public final MediaRouteProviderDescriptor getDescriptor() {
    return this.mDescriptor;
  }
  
  @Nullable
  public final MediaRouteDiscoveryRequest getDiscoveryRequest() {
    return this.mDiscoveryRequest;
  }
  
  public final Handler getHandler() {
    return this.mHandler;
  }
  
  public final ProviderMetadata getMetadata() {
    return this.mMetadata;
  }
  
  @Nullable
  public RouteController onCreateRouteController(@NonNull String paramString) {
    if (paramString != null)
      return null; 
    throw new IllegalArgumentException("routeId cannot be null");
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public RouteController onCreateRouteController(@NonNull String paramString1, @NonNull String paramString2) {
    if (paramString1 != null) {
      if (paramString2 != null)
        return onCreateRouteController(paramString1); 
      throw new IllegalArgumentException("routeGroupId cannot be null");
    } 
    throw new IllegalArgumentException("routeId cannot be null");
  }
  
  public void onDiscoveryRequestChanged(@Nullable MediaRouteDiscoveryRequest paramMediaRouteDiscoveryRequest) {}
  
  public final void setCallback(@Nullable Callback paramCallback) {
    MediaRouter.checkCallingThread();
    this.mCallback = paramCallback;
  }
  
  public final void setDescriptor(@Nullable MediaRouteProviderDescriptor paramMediaRouteProviderDescriptor) {
    MediaRouter.checkCallingThread();
    if (this.mDescriptor != paramMediaRouteProviderDescriptor) {
      this.mDescriptor = paramMediaRouteProviderDescriptor;
      if (!this.mPendingDescriptorChange) {
        this.mPendingDescriptorChange = true;
        this.mHandler.sendEmptyMessage(1);
      } 
    } 
  }
  
  public final void setDiscoveryRequest(MediaRouteDiscoveryRequest paramMediaRouteDiscoveryRequest) {
    MediaRouter.checkCallingThread();
    if (ObjectsCompat.equals(this.mDiscoveryRequest, paramMediaRouteDiscoveryRequest))
      return; 
    this.mDiscoveryRequest = paramMediaRouteDiscoveryRequest;
    if (!this.mPendingDiscoveryRequestChange) {
      this.mPendingDiscoveryRequestChange = true;
      this.mHandler.sendEmptyMessage(2);
    } 
  }
  
  public static abstract class Callback {
    public void onDescriptorChanged(@NonNull MediaRouteProvider param1MediaRouteProvider, @Nullable MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {}
  }
  
  private final class ProviderHandler extends Handler {
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i == 2)
          MediaRouteProvider.this.deliverDiscoveryRequestChanged(); 
      } else {
        MediaRouteProvider.this.deliverDescriptorChanged();
      } 
    }
  }
  
  public static final class ProviderMetadata {
    private final ComponentName mComponentName;
    
    ProviderMetadata(ComponentName param1ComponentName) {
      if (param1ComponentName != null) {
        this.mComponentName = param1ComponentName;
        return;
      } 
      throw new IllegalArgumentException("componentName must not be null");
    }
    
    public ComponentName getComponentName() {
      return this.mComponentName;
    }
    
    public String getPackageName() {
      return this.mComponentName.getPackageName();
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ProviderMetadata{ componentName=");
      stringBuilder.append(this.mComponentName.flattenToShortString());
      stringBuilder.append(" }");
      return stringBuilder.toString();
    }
  }
  
  public static abstract class RouteController {
    public boolean onControlRequest(Intent param1Intent, @Nullable MediaRouter.ControlRequestCallback param1ControlRequestCallback) {
      return false;
    }
    
    public void onRelease() {}
    
    public void onSelect() {}
    
    public void onSetVolume(int param1Int) {}
    
    public void onUnselect() {}
    
    public void onUnselect(int param1Int) {
      onUnselect();
    }
    
    public void onUpdateVolume(int param1Int) {}
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */