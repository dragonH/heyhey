package android.support.v7.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.mediarouter.R;
import android.view.Display;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

abstract class SystemMediaRouteProvider extends MediaRouteProvider {
  public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";
  
  public static final String PACKAGE_NAME = "android";
  
  private static final String TAG = "SystemMediaRouteProvider";
  
  protected SystemMediaRouteProvider(Context paramContext) {
    super(paramContext, new MediaRouteProvider.ProviderMetadata(new ComponentName("android", SystemMediaRouteProvider.class.getName())));
  }
  
  public static SystemMediaRouteProvider obtain(Context paramContext, SyncCallback paramSyncCallback) {
    int i = Build.VERSION.SDK_INT;
    return (i >= 24) ? new Api24Impl(paramContext, paramSyncCallback) : ((i >= 18) ? new JellybeanMr2Impl(paramContext, paramSyncCallback) : ((i >= 17) ? new JellybeanMr1Impl(paramContext, paramSyncCallback) : new JellybeanImpl(paramContext, paramSyncCallback)));
  }
  
  protected Object getDefaultRoute() {
    return null;
  }
  
  protected Object getSystemRoute(MediaRouter.RouteInfo paramRouteInfo) {
    return null;
  }
  
  public void onSyncRouteAdded(MediaRouter.RouteInfo paramRouteInfo) {}
  
  public void onSyncRouteChanged(MediaRouter.RouteInfo paramRouteInfo) {}
  
  public void onSyncRouteRemoved(MediaRouter.RouteInfo paramRouteInfo) {}
  
  public void onSyncRouteSelected(MediaRouter.RouteInfo paramRouteInfo) {}
  
  @RequiresApi(24)
  private static class Api24Impl extends JellybeanMr2Impl {
    public Api24Impl(Context param1Context, SystemMediaRouteProvider.SyncCallback param1SyncCallback) {
      super(param1Context, param1SyncCallback);
    }
    
    protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord param1SystemRouteRecord, MediaRouteDescriptor.Builder param1Builder) {
      super.onBuildSystemRouteDescriptor(param1SystemRouteRecord, param1Builder);
      param1Builder.setDeviceType(MediaRouterApi24.RouteInfo.getDeviceType(param1SystemRouteRecord.mRouteObj));
    }
  }
  
  @RequiresApi(16)
  static class JellybeanImpl extends SystemMediaRouteProvider implements MediaRouterJellybean.Callback, MediaRouterJellybean.VolumeCallback {
    private static final ArrayList<IntentFilter> LIVE_AUDIO_CONTROL_FILTERS;
    
    private static final ArrayList<IntentFilter> LIVE_VIDEO_CONTROL_FILTERS;
    
    protected boolean mActiveScan;
    
    protected final Object mCallbackObj;
    
    protected boolean mCallbackRegistered;
    
    private MediaRouterJellybean.GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;
    
    protected int mRouteTypes;
    
    protected final Object mRouterObj;
    
    private MediaRouterJellybean.SelectRouteWorkaround mSelectRouteWorkaround;
    
    private final SystemMediaRouteProvider.SyncCallback mSyncCallback;
    
    protected final ArrayList<SystemRouteRecord> mSystemRouteRecords = new ArrayList<SystemRouteRecord>();
    
    protected final Object mUserRouteCategoryObj;
    
    protected final ArrayList<UserRouteRecord> mUserRouteRecords = new ArrayList<UserRouteRecord>();
    
    protected final Object mVolumeCallbackObj;
    
    static {
      IntentFilter intentFilter1 = new IntentFilter();
      intentFilter1.addCategory("android.media.intent.category.LIVE_AUDIO");
      ArrayList<IntentFilter> arrayList2 = new ArrayList();
      LIVE_AUDIO_CONTROL_FILTERS = arrayList2;
      arrayList2.add(intentFilter1);
      IntentFilter intentFilter2 = new IntentFilter();
      intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
      ArrayList<IntentFilter> arrayList1 = new ArrayList();
      LIVE_VIDEO_CONTROL_FILTERS = arrayList1;
      arrayList1.add(intentFilter2);
    }
    
    public JellybeanImpl(Context param1Context, SystemMediaRouteProvider.SyncCallback param1SyncCallback) {
      super(param1Context);
      this.mSyncCallback = param1SyncCallback;
      Object object = MediaRouterJellybean.getMediaRouter(param1Context);
      this.mRouterObj = object;
      this.mCallbackObj = createCallbackObj();
      this.mVolumeCallbackObj = createVolumeCallbackObj();
      this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(object, param1Context.getResources().getString(R.string.mr_user_route_category_name), false);
      updateSystemRoutes();
    }
    
    private boolean addSystemRouteNoPublish(Object param1Object) {
      if (getUserRouteRecord(param1Object) == null && findSystemRouteRecord(param1Object) < 0) {
        param1Object = new SystemRouteRecord(param1Object, assignRouteId(param1Object));
        updateSystemRouteDescriptor((SystemRouteRecord)param1Object);
        this.mSystemRouteRecords.add(param1Object);
        return true;
      } 
      return false;
    }
    
    private String assignRouteId(Object param1Object) {
      if (getDefaultRoute() == param1Object) {
        b = 1;
      } else {
        b = 0;
      } 
      if (b) {
        param1Object = "DEFAULT_ROUTE";
      } else {
        param1Object = String.format(Locale.US, "ROUTE_%08x", new Object[] { Integer.valueOf(getRouteName(param1Object).hashCode()) });
      } 
      if (findSystemRouteRecordByDescriptorId((String)param1Object) < 0)
        return (String)param1Object; 
      for (byte b = 2;; b++) {
        String str = String.format(Locale.US, "%s_%d", new Object[] { param1Object, Integer.valueOf(b) });
        if (findSystemRouteRecordByDescriptorId(str) < 0)
          return str; 
      } 
    }
    
    private void updateSystemRoutes() {
      updateCallback();
      Iterator iterator = MediaRouterJellybean.getRoutes(this.mRouterObj).iterator();
      boolean bool;
      for (bool = false; iterator.hasNext(); bool |= addSystemRouteNoPublish(iterator.next()));
      if (bool)
        publishRoutes(); 
    }
    
    protected Object createCallbackObj() {
      return MediaRouterJellybean.createCallback(this);
    }
    
    protected Object createVolumeCallbackObj() {
      return MediaRouterJellybean.createVolumeCallback(this);
    }
    
    protected int findSystemRouteRecord(Object param1Object) {
      int i = this.mSystemRouteRecords.size();
      for (byte b = 0; b < i; b++) {
        if (((SystemRouteRecord)this.mSystemRouteRecords.get(b)).mRouteObj == param1Object)
          return b; 
      } 
      return -1;
    }
    
    protected int findSystemRouteRecordByDescriptorId(String param1String) {
      int i = this.mSystemRouteRecords.size();
      for (byte b = 0; b < i; b++) {
        if (((SystemRouteRecord)this.mSystemRouteRecords.get(b)).mRouteDescriptorId.equals(param1String))
          return b; 
      } 
      return -1;
    }
    
    protected int findUserRouteRecord(MediaRouter.RouteInfo param1RouteInfo) {
      int i = this.mUserRouteRecords.size();
      for (byte b = 0; b < i; b++) {
        if (((UserRouteRecord)this.mUserRouteRecords.get(b)).mRoute == param1RouteInfo)
          return b; 
      } 
      return -1;
    }
    
    protected Object getDefaultRoute() {
      if (this.mGetDefaultRouteWorkaround == null)
        this.mGetDefaultRouteWorkaround = new MediaRouterJellybean.GetDefaultRouteWorkaround(); 
      return this.mGetDefaultRouteWorkaround.getDefaultRoute(this.mRouterObj);
    }
    
    protected String getRouteName(Object param1Object) {
      param1Object = MediaRouterJellybean.RouteInfo.getName(param1Object, getContext());
      if (param1Object != null) {
        param1Object = param1Object.toString();
      } else {
        param1Object = "";
      } 
      return (String)param1Object;
    }
    
    protected Object getSystemRoute(MediaRouter.RouteInfo param1RouteInfo) {
      if (param1RouteInfo == null)
        return null; 
      int i = findSystemRouteRecordByDescriptorId(param1RouteInfo.getDescriptorId());
      return (i >= 0) ? ((SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteObj : null;
    }
    
    protected UserRouteRecord getUserRouteRecord(Object param1Object) {
      param1Object = MediaRouterJellybean.RouteInfo.getTag(param1Object);
      if (param1Object instanceof UserRouteRecord) {
        param1Object = param1Object;
      } else {
        param1Object = null;
      } 
      return (UserRouteRecord)param1Object;
    }
    
    protected void onBuildSystemRouteDescriptor(SystemRouteRecord param1SystemRouteRecord, MediaRouteDescriptor.Builder param1Builder) {
      int i = MediaRouterJellybean.RouteInfo.getSupportedTypes(param1SystemRouteRecord.mRouteObj);
      if ((i & 0x1) != 0)
        param1Builder.addControlFilters(LIVE_AUDIO_CONTROL_FILTERS); 
      if ((i & 0x2) != 0)
        param1Builder.addControlFilters(LIVE_VIDEO_CONTROL_FILTERS); 
      param1Builder.setPlaybackType(MediaRouterJellybean.RouteInfo.getPlaybackType(param1SystemRouteRecord.mRouteObj));
      param1Builder.setPlaybackStream(MediaRouterJellybean.RouteInfo.getPlaybackStream(param1SystemRouteRecord.mRouteObj));
      param1Builder.setVolume(MediaRouterJellybean.RouteInfo.getVolume(param1SystemRouteRecord.mRouteObj));
      param1Builder.setVolumeMax(MediaRouterJellybean.RouteInfo.getVolumeMax(param1SystemRouteRecord.mRouteObj));
      param1Builder.setVolumeHandling(MediaRouterJellybean.RouteInfo.getVolumeHandling(param1SystemRouteRecord.mRouteObj));
    }
    
    public MediaRouteProvider.RouteController onCreateRouteController(String param1String) {
      int i = findSystemRouteRecordByDescriptorId(param1String);
      return (i >= 0) ? new SystemRouteController(((SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteObj) : null;
    }
    
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest param1MediaRouteDiscoveryRequest) {
      boolean bool;
      int i = 0;
      byte b = 0;
      if (param1MediaRouteDiscoveryRequest != null) {
        List<String> list = param1MediaRouteDiscoveryRequest.getSelector().getControlCategories();
        int j = list.size();
        i = 0;
        while (b < j) {
          String str = list.get(b);
          if (str.equals("android.media.intent.category.LIVE_AUDIO")) {
            i |= 0x1;
          } else if (str.equals("android.media.intent.category.LIVE_VIDEO")) {
            i |= 0x2;
          } else {
            i |= 0x800000;
          } 
          b++;
        } 
        bool = param1MediaRouteDiscoveryRequest.isActiveScan();
      } else {
        bool = false;
      } 
      if (this.mRouteTypes != i || this.mActiveScan != bool) {
        this.mRouteTypes = i;
        this.mActiveScan = bool;
        updateSystemRoutes();
      } 
    }
    
    public void onRouteAdded(Object param1Object) {
      if (addSystemRouteNoPublish(param1Object))
        publishRoutes(); 
    }
    
    public void onRouteChanged(Object param1Object) {
      if (getUserRouteRecord(param1Object) == null) {
        int i = findSystemRouteRecord(param1Object);
        if (i >= 0) {
          updateSystemRouteDescriptor(this.mSystemRouteRecords.get(i));
          publishRoutes();
        } 
      } 
    }
    
    public void onRouteGrouped(Object param1Object1, Object param1Object2, int param1Int) {}
    
    public void onRouteRemoved(Object param1Object) {
      if (getUserRouteRecord(param1Object) == null) {
        int i = findSystemRouteRecord(param1Object);
        if (i >= 0) {
          this.mSystemRouteRecords.remove(i);
          publishRoutes();
        } 
      } 
    }
    
    public void onRouteSelected(int param1Int, Object param1Object) {
      if (param1Object != MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611))
        return; 
      UserRouteRecord userRouteRecord = getUserRouteRecord(param1Object);
      if (userRouteRecord != null) {
        userRouteRecord.mRoute.select();
      } else {
        param1Int = findSystemRouteRecord(param1Object);
        if (param1Int >= 0) {
          param1Object = this.mSystemRouteRecords.get(param1Int);
          this.mSyncCallback.onSystemRouteSelectedByDescriptorId(((SystemRouteRecord)param1Object).mRouteDescriptorId);
        } 
      } 
    }
    
    public void onRouteUngrouped(Object param1Object1, Object param1Object2) {}
    
    public void onRouteUnselected(int param1Int, Object param1Object) {}
    
    public void onRouteVolumeChanged(Object param1Object) {
      if (getUserRouteRecord(param1Object) == null) {
        int i = findSystemRouteRecord(param1Object);
        if (i >= 0) {
          SystemRouteRecord systemRouteRecord = this.mSystemRouteRecords.get(i);
          i = MediaRouterJellybean.RouteInfo.getVolume(param1Object);
          if (i != systemRouteRecord.mRouteDescriptor.getVolume()) {
            systemRouteRecord.mRouteDescriptor = (new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptor)).setVolume(i).build();
            publishRoutes();
          } 
        } 
      } 
    }
    
    public void onSyncRouteAdded(MediaRouter.RouteInfo param1RouteInfo) {
      UserRouteRecord userRouteRecord;
      if (param1RouteInfo.getProviderInstance() != this) {
        Object object = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
        userRouteRecord = new UserRouteRecord(param1RouteInfo, object);
        MediaRouterJellybean.RouteInfo.setTag(object, userRouteRecord);
        MediaRouterJellybean.UserRouteInfo.setVolumeCallback(object, this.mVolumeCallbackObj);
        updateUserRouteProperties(userRouteRecord);
        this.mUserRouteRecords.add(userRouteRecord);
        MediaRouterJellybean.addUserRoute(this.mRouterObj, object);
      } else {
        int i = findSystemRouteRecord(MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611));
        if (i >= 0 && ((SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteDescriptorId.equals(userRouteRecord.getDescriptorId()))
          userRouteRecord.select(); 
      } 
    }
    
    public void onSyncRouteChanged(MediaRouter.RouteInfo param1RouteInfo) {
      if (param1RouteInfo.getProviderInstance() != this) {
        int i = findUserRouteRecord(param1RouteInfo);
        if (i >= 0)
          updateUserRouteProperties(this.mUserRouteRecords.get(i)); 
      } 
    }
    
    public void onSyncRouteRemoved(MediaRouter.RouteInfo param1RouteInfo) {
      if (param1RouteInfo.getProviderInstance() != this) {
        int i = findUserRouteRecord(param1RouteInfo);
        if (i >= 0) {
          UserRouteRecord userRouteRecord = this.mUserRouteRecords.remove(i);
          MediaRouterJellybean.RouteInfo.setTag(userRouteRecord.mRouteObj, null);
          MediaRouterJellybean.UserRouteInfo.setVolumeCallback(userRouteRecord.mRouteObj, null);
          MediaRouterJellybean.removeUserRoute(this.mRouterObj, userRouteRecord.mRouteObj);
        } 
      } 
    }
    
    public void onSyncRouteSelected(MediaRouter.RouteInfo param1RouteInfo) {
      if (!param1RouteInfo.isSelected())
        return; 
      if (param1RouteInfo.getProviderInstance() != this) {
        int i = findUserRouteRecord(param1RouteInfo);
        if (i >= 0)
          selectRoute(((UserRouteRecord)this.mUserRouteRecords.get(i)).mRouteObj); 
      } else {
        int i = findSystemRouteRecordByDescriptorId(param1RouteInfo.getDescriptorId());
        if (i >= 0)
          selectRoute(((SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteObj); 
      } 
    }
    
    public void onVolumeSetRequest(Object param1Object, int param1Int) {
      param1Object = getUserRouteRecord(param1Object);
      if (param1Object != null)
        ((UserRouteRecord)param1Object).mRoute.requestSetVolume(param1Int); 
    }
    
    public void onVolumeUpdateRequest(Object param1Object, int param1Int) {
      param1Object = getUserRouteRecord(param1Object);
      if (param1Object != null)
        ((UserRouteRecord)param1Object).mRoute.requestUpdateVolume(param1Int); 
    }
    
    protected void publishRoutes() {
      MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder();
      int i = this.mSystemRouteRecords.size();
      for (byte b = 0; b < i; b++)
        builder.addRoute(((SystemRouteRecord)this.mSystemRouteRecords.get(b)).mRouteDescriptor); 
      setDescriptor(builder.build());
    }
    
    protected void selectRoute(Object param1Object) {
      if (this.mSelectRouteWorkaround == null)
        this.mSelectRouteWorkaround = new MediaRouterJellybean.SelectRouteWorkaround(); 
      this.mSelectRouteWorkaround.selectRoute(this.mRouterObj, 8388611, param1Object);
    }
    
    protected void updateCallback() {
      if (this.mCallbackRegistered) {
        this.mCallbackRegistered = false;
        MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
      } 
      int i = this.mRouteTypes;
      if (i != 0) {
        this.mCallbackRegistered = true;
        MediaRouterJellybean.addCallback(this.mRouterObj, i, this.mCallbackObj);
      } 
    }
    
    protected void updateSystemRouteDescriptor(SystemRouteRecord param1SystemRouteRecord) {
      MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(param1SystemRouteRecord.mRouteDescriptorId, getRouteName(param1SystemRouteRecord.mRouteObj));
      onBuildSystemRouteDescriptor(param1SystemRouteRecord, builder);
      param1SystemRouteRecord.mRouteDescriptor = builder.build();
    }
    
    protected void updateUserRouteProperties(UserRouteRecord param1UserRouteRecord) {
      MediaRouterJellybean.UserRouteInfo.setName(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getName());
      MediaRouterJellybean.UserRouteInfo.setPlaybackType(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getPlaybackType());
      MediaRouterJellybean.UserRouteInfo.setPlaybackStream(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getPlaybackStream());
      MediaRouterJellybean.UserRouteInfo.setVolume(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getVolume());
      MediaRouterJellybean.UserRouteInfo.setVolumeMax(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getVolumeMax());
      MediaRouterJellybean.UserRouteInfo.setVolumeHandling(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getVolumeHandling());
    }
    
    protected static final class SystemRouteController extends MediaRouteProvider.RouteController {
      private final Object mRouteObj;
      
      public SystemRouteController(Object param2Object) {
        this.mRouteObj = param2Object;
      }
      
      public void onSetVolume(int param2Int) {
        MediaRouterJellybean.RouteInfo.requestSetVolume(this.mRouteObj, param2Int);
      }
      
      public void onUpdateVolume(int param2Int) {
        MediaRouterJellybean.RouteInfo.requestUpdateVolume(this.mRouteObj, param2Int);
      }
    }
    
    protected static final class SystemRouteRecord {
      public MediaRouteDescriptor mRouteDescriptor;
      
      public final String mRouteDescriptorId;
      
      public final Object mRouteObj;
      
      public SystemRouteRecord(Object param2Object, String param2String) {
        this.mRouteObj = param2Object;
        this.mRouteDescriptorId = param2String;
      }
    }
    
    protected static final class UserRouteRecord {
      public final MediaRouter.RouteInfo mRoute;
      
      public final Object mRouteObj;
      
      public UserRouteRecord(MediaRouter.RouteInfo param2RouteInfo, Object param2Object) {
        this.mRoute = param2RouteInfo;
        this.mRouteObj = param2Object;
      }
    }
  }
  
  protected static final class SystemRouteController extends MediaRouteProvider.RouteController {
    private final Object mRouteObj;
    
    public SystemRouteController(Object param1Object) {
      this.mRouteObj = param1Object;
    }
    
    public void onSetVolume(int param1Int) {
      MediaRouterJellybean.RouteInfo.requestSetVolume(this.mRouteObj, param1Int);
    }
    
    public void onUpdateVolume(int param1Int) {
      MediaRouterJellybean.RouteInfo.requestUpdateVolume(this.mRouteObj, param1Int);
    }
  }
  
  protected static final class SystemRouteRecord {
    public MediaRouteDescriptor mRouteDescriptor;
    
    public final String mRouteDescriptorId;
    
    public final Object mRouteObj;
    
    public SystemRouteRecord(Object param1Object, String param1String) {
      this.mRouteObj = param1Object;
      this.mRouteDescriptorId = param1String;
    }
  }
  
  protected static final class UserRouteRecord {
    public final MediaRouter.RouteInfo mRoute;
    
    public final Object mRouteObj;
    
    public UserRouteRecord(MediaRouter.RouteInfo param1RouteInfo, Object param1Object) {
      this.mRoute = param1RouteInfo;
      this.mRouteObj = param1Object;
    }
  }
  
  @RequiresApi(17)
  private static class JellybeanMr1Impl extends JellybeanImpl implements MediaRouterJellybeanMr1.Callback {
    private MediaRouterJellybeanMr1.ActiveScanWorkaround mActiveScanWorkaround;
    
    private MediaRouterJellybeanMr1.IsConnectingWorkaround mIsConnectingWorkaround;
    
    public JellybeanMr1Impl(Context param1Context, SystemMediaRouteProvider.SyncCallback param1SyncCallback) {
      super(param1Context, param1SyncCallback);
    }
    
    protected Object createCallbackObj() {
      return MediaRouterJellybeanMr1.createCallback(this);
    }
    
    protected boolean isConnecting(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord param1SystemRouteRecord) {
      if (this.mIsConnectingWorkaround == null)
        this.mIsConnectingWorkaround = new MediaRouterJellybeanMr1.IsConnectingWorkaround(); 
      return this.mIsConnectingWorkaround.isConnecting(param1SystemRouteRecord.mRouteObj);
    }
    
    protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord param1SystemRouteRecord, MediaRouteDescriptor.Builder param1Builder) {
      super.onBuildSystemRouteDescriptor(param1SystemRouteRecord, param1Builder);
      if (!MediaRouterJellybeanMr1.RouteInfo.isEnabled(param1SystemRouteRecord.mRouteObj))
        param1Builder.setEnabled(false); 
      if (isConnecting(param1SystemRouteRecord))
        param1Builder.setConnecting(true); 
      Display display = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(param1SystemRouteRecord.mRouteObj);
      if (display != null)
        param1Builder.setPresentationDisplayId(display.getDisplayId()); 
    }
    
    public void onRoutePresentationDisplayChanged(Object param1Object) {
      int i = findSystemRouteRecord(param1Object);
      if (i >= 0) {
        SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord systemRouteRecord = this.mSystemRouteRecords.get(i);
        param1Object = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(param1Object);
        if (param1Object != null) {
          i = param1Object.getDisplayId();
        } else {
          i = -1;
        } 
        if (i != systemRouteRecord.mRouteDescriptor.getPresentationDisplayId()) {
          systemRouteRecord.mRouteDescriptor = (new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptor)).setPresentationDisplayId(i).build();
          publishRoutes();
        } 
      } 
    }
    
    protected void updateCallback() {
      boolean bool;
      super.updateCallback();
      if (this.mActiveScanWorkaround == null)
        this.mActiveScanWorkaround = new MediaRouterJellybeanMr1.ActiveScanWorkaround(getContext(), getHandler()); 
      MediaRouterJellybeanMr1.ActiveScanWorkaround activeScanWorkaround = this.mActiveScanWorkaround;
      if (this.mActiveScan) {
        bool = this.mRouteTypes;
      } else {
        bool = false;
      } 
      activeScanWorkaround.setActiveScanRouteTypes(bool);
    }
  }
  
  @RequiresApi(18)
  private static class JellybeanMr2Impl extends JellybeanMr1Impl {
    public JellybeanMr2Impl(Context param1Context, SystemMediaRouteProvider.SyncCallback param1SyncCallback) {
      super(param1Context, param1SyncCallback);
    }
    
    protected Object getDefaultRoute() {
      return MediaRouterJellybeanMr2.getDefaultRoute(this.mRouterObj);
    }
    
    protected boolean isConnecting(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord param1SystemRouteRecord) {
      return MediaRouterJellybeanMr2.RouteInfo.isConnecting(param1SystemRouteRecord.mRouteObj);
    }
    
    protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord param1SystemRouteRecord, MediaRouteDescriptor.Builder param1Builder) {
      super.onBuildSystemRouteDescriptor(param1SystemRouteRecord, param1Builder);
      CharSequence charSequence = MediaRouterJellybeanMr2.RouteInfo.getDescription(param1SystemRouteRecord.mRouteObj);
      if (charSequence != null)
        param1Builder.setDescription(charSequence.toString()); 
    }
    
    protected void selectRoute(Object param1Object) {
      MediaRouterJellybean.selectRoute(this.mRouterObj, 8388611, param1Object);
    }
    
    protected void updateCallback() {
      if (this.mCallbackRegistered)
        MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj); 
      this.mCallbackRegistered = true;
      MediaRouterJellybeanMr2.addCallback(this.mRouterObj, this.mRouteTypes, this.mCallbackObj, this.mActiveScan | 0x2);
    }
    
    protected void updateUserRouteProperties(SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord param1UserRouteRecord) {
      super.updateUserRouteProperties(param1UserRouteRecord);
      MediaRouterJellybeanMr2.UserRouteInfo.setDescription(param1UserRouteRecord.mRouteObj, param1UserRouteRecord.mRoute.getDescription());
    }
  }
  
  static class LegacyImpl extends SystemMediaRouteProvider {
    private static final ArrayList<IntentFilter> CONTROL_FILTERS;
    
    static final int PLAYBACK_STREAM = 3;
    
    final AudioManager mAudioManager;
    
    int mLastReportedVolume = -1;
    
    private final VolumeChangeReceiver mVolumeChangeReceiver;
    
    static {
      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
      intentFilter.addCategory("android.media.intent.category.LIVE_VIDEO");
      ArrayList<IntentFilter> arrayList = new ArrayList();
      CONTROL_FILTERS = arrayList;
      arrayList.add(intentFilter);
    }
    
    public LegacyImpl(Context param1Context) {
      super(param1Context);
      this.mAudioManager = (AudioManager)param1Context.getSystemService("audio");
      VolumeChangeReceiver volumeChangeReceiver = new VolumeChangeReceiver();
      this.mVolumeChangeReceiver = volumeChangeReceiver;
      param1Context.registerReceiver(volumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
      publishRoutes();
    }
    
    public MediaRouteProvider.RouteController onCreateRouteController(String param1String) {
      return param1String.equals("DEFAULT_ROUTE") ? new DefaultRouteController() : null;
    }
    
    void publishRoutes() {
      Resources resources = getContext().getResources();
      int i = this.mAudioManager.getStreamMaxVolume(3);
      this.mLastReportedVolume = this.mAudioManager.getStreamVolume(3);
      MediaRouteDescriptor mediaRouteDescriptor = (new MediaRouteDescriptor.Builder("DEFAULT_ROUTE", resources.getString(R.string.mr_system_route_name))).addControlFilters(CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(i).setVolume(this.mLastReportedVolume).build();
      setDescriptor((new MediaRouteProviderDescriptor.Builder()).addRoute(mediaRouteDescriptor).build());
    }
    
    final class DefaultRouteController extends MediaRouteProvider.RouteController {
      public void onSetVolume(int param2Int) {
        SystemMediaRouteProvider.LegacyImpl.this.mAudioManager.setStreamVolume(3, param2Int, 0);
        SystemMediaRouteProvider.LegacyImpl.this.publishRoutes();
      }
      
      public void onUpdateVolume(int param2Int) {
        int i = SystemMediaRouteProvider.LegacyImpl.this.mAudioManager.getStreamVolume(3);
        if (Math.min(SystemMediaRouteProvider.LegacyImpl.this.mAudioManager.getStreamMaxVolume(3), Math.max(0, param2Int + i)) != i)
          SystemMediaRouteProvider.LegacyImpl.this.mAudioManager.setStreamVolume(3, i, 0); 
        SystemMediaRouteProvider.LegacyImpl.this.publishRoutes();
      }
    }
    
    final class VolumeChangeReceiver extends BroadcastReceiver {
      public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
      
      public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
      
      public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
      
      public void onReceive(Context param2Context, Intent param2Intent) {
        if (param2Intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && param2Intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3) {
          int i = param2Intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
          if (i >= 0) {
            SystemMediaRouteProvider.LegacyImpl legacyImpl = SystemMediaRouteProvider.LegacyImpl.this;
            if (i != legacyImpl.mLastReportedVolume)
              legacyImpl.publishRoutes(); 
          } 
        } 
      }
    }
  }
  
  final class DefaultRouteController extends MediaRouteProvider.RouteController {
    public void onSetVolume(int param1Int) {
      this.this$0.mAudioManager.setStreamVolume(3, param1Int, 0);
      this.this$0.publishRoutes();
    }
    
    public void onUpdateVolume(int param1Int) {
      int i = this.this$0.mAudioManager.getStreamVolume(3);
      if (Math.min(this.this$0.mAudioManager.getStreamMaxVolume(3), Math.max(0, param1Int + i)) != i)
        this.this$0.mAudioManager.setStreamVolume(3, i, 0); 
      this.this$0.publishRoutes();
    }
  }
  
  final class VolumeChangeReceiver extends BroadcastReceiver {
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    
    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      if (param1Intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && param1Intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3) {
        int i = param1Intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
        if (i >= 0) {
          SystemMediaRouteProvider.LegacyImpl legacyImpl = this.this$0;
          if (i != legacyImpl.mLastReportedVolume)
            legacyImpl.publishRoutes(); 
        } 
      } 
    }
  }
  
  public static interface SyncCallback {
    void onSystemRouteSelectedByDescriptorId(String param1String);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/SystemMediaRouteProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */