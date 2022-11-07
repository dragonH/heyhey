package android.support.v7.media;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ObjectsCompat;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public abstract class MediaRouteProviderService extends Service {
  static final boolean DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
  
  static final int PRIVATE_MSG_CLIENT_DIED = 1;
  
  public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
  
  static final String TAG = "MediaRouteProviderSrv";
  
  private final ArrayList<ClientRecord> mClients = new ArrayList<ClientRecord>();
  
  private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
  
  final PrivateHandler mPrivateHandler;
  
  MediaRouteProvider mProvider;
  
  private final ProviderCallback mProviderCallback;
  
  private final ReceiveHandler mReceiveHandler;
  
  private final Messenger mReceiveMessenger;
  
  public MediaRouteProviderService() {
    ReceiveHandler receiveHandler = new ReceiveHandler(this);
    this.mReceiveHandler = receiveHandler;
    this.mReceiveMessenger = new Messenger(receiveHandler);
    this.mPrivateHandler = new PrivateHandler();
    this.mProviderCallback = new ProviderCallback();
  }
  
  @VisibleForTesting
  static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor paramMediaRouteProviderDescriptor, int paramInt) {
    if (paramMediaRouteProviderDescriptor == null)
      return null; 
    MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder(paramMediaRouteProviderDescriptor);
    builder.setRoutes(null);
    for (MediaRouteDescriptor mediaRouteDescriptor : paramMediaRouteProviderDescriptor.getRoutes()) {
      if (paramInt >= mediaRouteDescriptor.getMinClientVersion() && paramInt <= mediaRouteDescriptor.getMaxClientVersion())
        builder.addRoute(mediaRouteDescriptor); 
    } 
    return builder.build().asBundle();
  }
  
  private ClientRecord getClient(Messenger paramMessenger) {
    int i = findClient(paramMessenger);
    if (i >= 0) {
      ClientRecord clientRecord = this.mClients.get(i);
    } else {
      paramMessenger = null;
    } 
    return (ClientRecord)paramMessenger;
  }
  
  static String getClientId(Messenger paramMessenger) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Client connection ");
    stringBuilder.append(paramMessenger.getBinder().toString());
    return stringBuilder.toString();
  }
  
  static void sendGenericFailure(Messenger paramMessenger, int paramInt) {
    if (paramInt != 0)
      sendReply(paramMessenger, 0, paramInt, 0, null, null); 
  }
  
  private static void sendGenericSuccess(Messenger paramMessenger, int paramInt) {
    if (paramInt != 0)
      sendReply(paramMessenger, 1, paramInt, 0, null, null); 
  }
  
  static void sendReply(Messenger paramMessenger, int paramInt1, int paramInt2, int paramInt3, Object paramObject, Bundle paramBundle) {
    Message message = Message.obtain();
    message.what = paramInt1;
    message.arg1 = paramInt2;
    message.arg2 = paramInt3;
    message.obj = paramObject;
    message.setData(paramBundle);
    try {
      paramMessenger.send(message);
    } catch (DeadObjectException deadObjectException) {
    
    } catch (RemoteException remoteException) {
      paramObject = new StringBuilder();
      paramObject.append("Could not send message to ");
      paramObject.append(getClientId((Messenger)deadObjectException));
      Log.e("MediaRouteProviderSrv", paramObject.toString(), (Throwable)remoteException);
    } 
  }
  
  int findClient(Messenger paramMessenger) {
    int i = this.mClients.size();
    for (byte b = 0; b < i; b++) {
      if (((ClientRecord)this.mClients.get(b)).hasMessenger(paramMessenger))
        return b; 
    } 
    return -1;
  }
  
  public MediaRouteProvider getMediaRouteProvider() {
    return this.mProvider;
  }
  
  public IBinder onBind(Intent paramIntent) {
    if (paramIntent.getAction().equals("android.media.MediaRouteProviderService")) {
      if (this.mProvider == null) {
        MediaRouteProvider mediaRouteProvider = onCreateMediaRouteProvider();
        if (mediaRouteProvider != null) {
          String str = mediaRouteProvider.getMetadata().getPackageName();
          if (str.equals(getPackageName())) {
            this.mProvider = mediaRouteProvider;
            mediaRouteProvider.setCallback(this.mProviderCallback);
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: ");
            stringBuilder.append(str);
            stringBuilder.append(".  Service package name: ");
            stringBuilder.append(getPackageName());
            stringBuilder.append(".");
            throw new IllegalStateException(stringBuilder.toString());
          } 
        } 
      } 
      if (this.mProvider != null)
        return this.mReceiveMessenger.getBinder(); 
    } 
    return null;
  }
  
  void onBinderDied(Messenger paramMessenger) {
    int i = findClient(paramMessenger);
    if (i >= 0) {
      ClientRecord clientRecord = this.mClients.remove(i);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Binder died");
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
      clientRecord.dispose();
    } 
  }
  
  public abstract MediaRouteProvider onCreateMediaRouteProvider();
  
  boolean onCreateRouteController(Messenger paramMessenger, int paramInt1, int paramInt2, String paramString1, String paramString2) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null && clientRecord.createRouteController(paramString1, paramString2, paramInt2)) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Route controller created, controllerId=");
        stringBuilder.append(paramInt2);
        stringBuilder.append(", routeId=");
        stringBuilder.append(paramString1);
        stringBuilder.append(", routeGroupId=");
        stringBuilder.append(paramString2);
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
      sendGenericSuccess(paramMessenger, paramInt1);
      return true;
    } 
    return false;
  }
  
  boolean onRegisterClient(Messenger paramMessenger, int paramInt1, int paramInt2) {
    if (paramInt2 >= 1 && findClient(paramMessenger) < 0) {
      ClientRecord clientRecord = new ClientRecord(paramMessenger, paramInt2);
      if (clientRecord.register()) {
        this.mClients.add(clientRecord);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(clientRecord);
          stringBuilder.append(": Registered, version=");
          stringBuilder.append(paramInt2);
          Log.d("MediaRouteProviderSrv", stringBuilder.toString());
        } 
        if (paramInt1 != 0)
          sendReply(paramMessenger, 2, paramInt1, 1, createDescriptorBundleForClientVersion(this.mProvider.getDescriptor(), clientRecord.mVersion), null); 
        return true;
      } 
    } 
    return false;
  }
  
  boolean onReleaseRouteController(Messenger paramMessenger, int paramInt1, int paramInt2) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null && clientRecord.releaseRouteController(paramInt2)) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Route controller released");
        stringBuilder.append(", controllerId=");
        stringBuilder.append(paramInt2);
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
      sendGenericSuccess(paramMessenger, paramInt1);
      return true;
    } 
    return false;
  }
  
  boolean onRouteControlRequest(final Messenger messenger, final int requestId, final int controllerId, final Intent intent) {
    final ClientRecord client = getClient(messenger);
    if (clientRecord != null) {
      MediaRouteProvider.RouteController routeController = clientRecord.getRouteController(controllerId);
      if (routeController != null) {
        MediaRouter.ControlRequestCallback controlRequestCallback = null;
        if (requestId != 0)
          controlRequestCallback = new MediaRouter.ControlRequestCallback() {
              public void onError(String param1String, Bundle param1Bundle) {
                if (MediaRouteProviderService.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append(client);
                  stringBuilder.append(": Route control request failed");
                  stringBuilder.append(", controllerId=");
                  stringBuilder.append(controllerId);
                  stringBuilder.append(", intent=");
                  stringBuilder.append(intent);
                  stringBuilder.append(", error=");
                  stringBuilder.append(param1String);
                  stringBuilder.append(", data=");
                  stringBuilder.append(param1Bundle);
                  Log.d("MediaRouteProviderSrv", stringBuilder.toString());
                } 
                if (MediaRouteProviderService.this.findClient(messenger) >= 0)
                  if (param1String != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("error", param1String);
                    MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, param1Bundle, bundle);
                  } else {
                    MediaRouteProviderService.sendReply(messenger, 4, requestId, 0, param1Bundle, null);
                  }  
              }
              
              public void onResult(Bundle param1Bundle) {
                if (MediaRouteProviderService.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append(client);
                  stringBuilder.append(": Route control request succeeded");
                  stringBuilder.append(", controllerId=");
                  stringBuilder.append(controllerId);
                  stringBuilder.append(", intent=");
                  stringBuilder.append(intent);
                  stringBuilder.append(", data=");
                  stringBuilder.append(param1Bundle);
                  Log.d("MediaRouteProviderSrv", stringBuilder.toString());
                } 
                if (MediaRouteProviderService.this.findClient(messenger) >= 0)
                  MediaRouteProviderService.sendReply(messenger, 3, requestId, 0, param1Bundle, null); 
              }
            }; 
        if (routeController.onControlRequest(intent, controlRequestCallback)) {
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(clientRecord);
            stringBuilder.append(": Route control request delivered");
            stringBuilder.append(", controllerId=");
            stringBuilder.append(controllerId);
            stringBuilder.append(", intent=");
            stringBuilder.append(intent);
            Log.d("MediaRouteProviderSrv", stringBuilder.toString());
          } 
          return true;
        } 
      } 
    } 
    return false;
  }
  
  boolean onSelectRoute(Messenger paramMessenger, int paramInt1, int paramInt2) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null) {
      MediaRouteProvider.RouteController routeController = clientRecord.getRouteController(paramInt2);
      if (routeController != null) {
        routeController.onSelect();
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(clientRecord);
          stringBuilder.append(": Route selected");
          stringBuilder.append(", controllerId=");
          stringBuilder.append(paramInt2);
          Log.d("MediaRouteProviderSrv", stringBuilder.toString());
        } 
        sendGenericSuccess(paramMessenger, paramInt1);
        return true;
      } 
    } 
    return false;
  }
  
  boolean onSetDiscoveryRequest(Messenger paramMessenger, int paramInt, MediaRouteDiscoveryRequest paramMediaRouteDiscoveryRequest) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null) {
      boolean bool = clientRecord.setDiscoveryRequest(paramMediaRouteDiscoveryRequest);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Set discovery request, request=");
        stringBuilder.append(paramMediaRouteDiscoveryRequest);
        stringBuilder.append(", actuallyChanged=");
        stringBuilder.append(bool);
        stringBuilder.append(", compositeDiscoveryRequest=");
        stringBuilder.append(this.mCompositeDiscoveryRequest);
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
      sendGenericSuccess(paramMessenger, paramInt);
      return true;
    } 
    return false;
  }
  
  boolean onSetRouteVolume(Messenger paramMessenger, int paramInt1, int paramInt2, int paramInt3) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null) {
      MediaRouteProvider.RouteController routeController = clientRecord.getRouteController(paramInt2);
      if (routeController != null) {
        routeController.onSetVolume(paramInt3);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(clientRecord);
          stringBuilder.append(": Route volume changed");
          stringBuilder.append(", controllerId=");
          stringBuilder.append(paramInt2);
          stringBuilder.append(", volume=");
          stringBuilder.append(paramInt3);
          Log.d("MediaRouteProviderSrv", stringBuilder.toString());
        } 
        sendGenericSuccess(paramMessenger, paramInt1);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean onUnbind(Intent paramIntent) {
    MediaRouteProvider mediaRouteProvider = this.mProvider;
    if (mediaRouteProvider != null)
      mediaRouteProvider.setCallback(null); 
    return super.onUnbind(paramIntent);
  }
  
  boolean onUnregisterClient(Messenger paramMessenger, int paramInt) {
    int i = findClient(paramMessenger);
    if (i >= 0) {
      ClientRecord clientRecord = this.mClients.remove(i);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Unregistered");
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
      clientRecord.dispose();
      sendGenericSuccess(paramMessenger, paramInt);
      return true;
    } 
    return false;
  }
  
  boolean onUnselectRoute(Messenger paramMessenger, int paramInt1, int paramInt2, int paramInt3) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null) {
      MediaRouteProvider.RouteController routeController = clientRecord.getRouteController(paramInt2);
      if (routeController != null) {
        routeController.onUnselect(paramInt3);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(clientRecord);
          stringBuilder.append(": Route unselected");
          stringBuilder.append(", controllerId=");
          stringBuilder.append(paramInt2);
          Log.d("MediaRouteProviderSrv", stringBuilder.toString());
        } 
        sendGenericSuccess(paramMessenger, paramInt1);
        return true;
      } 
    } 
    return false;
  }
  
  boolean onUpdateRouteVolume(Messenger paramMessenger, int paramInt1, int paramInt2, int paramInt3) {
    ClientRecord clientRecord = getClient(paramMessenger);
    if (clientRecord != null) {
      MediaRouteProvider.RouteController routeController = clientRecord.getRouteController(paramInt2);
      if (routeController != null) {
        routeController.onUpdateVolume(paramInt3);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(clientRecord);
          stringBuilder.append(": Route volume updated");
          stringBuilder.append(", controllerId=");
          stringBuilder.append(paramInt2);
          stringBuilder.append(", delta=");
          stringBuilder.append(paramInt3);
          Log.d("MediaRouteProviderSrv", stringBuilder.toString());
        } 
        sendGenericSuccess(paramMessenger, paramInt1);
        return true;
      } 
    } 
    return false;
  }
  
  void sendDescriptorChanged(MediaRouteProviderDescriptor paramMediaRouteProviderDescriptor) {
    int i = this.mClients.size();
    for (byte b = 0; b < i; b++) {
      ClientRecord clientRecord = this.mClients.get(b);
      sendReply(clientRecord.mMessenger, 5, 0, 0, createDescriptorBundleForClientVersion(paramMediaRouteProviderDescriptor, clientRecord.mVersion), null);
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientRecord);
        stringBuilder.append(": Sent descriptor change event, descriptor=");
        stringBuilder.append(paramMediaRouteProviderDescriptor);
        Log.d("MediaRouteProviderSrv", stringBuilder.toString());
      } 
    } 
  }
  
  boolean updateCompositeDiscoveryRequest() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mClients : Ljava/util/ArrayList;
    //   4: invokevirtual size : ()I
    //   7: istore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: aconst_null
    //   11: astore_3
    //   12: iconst_0
    //   13: istore #4
    //   15: iconst_0
    //   16: istore #5
    //   18: iload #4
    //   20: iload_1
    //   21: if_icmpge -> 159
    //   24: aload_0
    //   25: getfield mClients : Ljava/util/ArrayList;
    //   28: iload #4
    //   30: invokevirtual get : (I)Ljava/lang/Object;
    //   33: checkcast android/support/v7/media/MediaRouteProviderService$ClientRecord
    //   36: getfield mDiscoveryRequest : Landroid/support/v7/media/MediaRouteDiscoveryRequest;
    //   39: astore #6
    //   41: aload_2
    //   42: astore #7
    //   44: aload_3
    //   45: astore #8
    //   47: iload #5
    //   49: istore #9
    //   51: aload #6
    //   53: ifnull -> 143
    //   56: aload #6
    //   58: invokevirtual getSelector : ()Landroid/support/v7/media/MediaRouteSelector;
    //   61: invokevirtual isEmpty : ()Z
    //   64: ifeq -> 85
    //   67: aload_2
    //   68: astore #7
    //   70: aload_3
    //   71: astore #8
    //   73: iload #5
    //   75: istore #9
    //   77: aload #6
    //   79: invokevirtual isActiveScan : ()Z
    //   82: ifeq -> 143
    //   85: iload #5
    //   87: aload #6
    //   89: invokevirtual isActiveScan : ()Z
    //   92: ior
    //   93: istore #9
    //   95: aload_3
    //   96: ifnonnull -> 109
    //   99: aload #6
    //   101: astore #8
    //   103: aload_2
    //   104: astore #7
    //   106: goto -> 143
    //   109: aload_2
    //   110: astore #7
    //   112: aload_2
    //   113: ifnonnull -> 129
    //   116: new android/support/v7/media/MediaRouteSelector$Builder
    //   119: dup
    //   120: aload_3
    //   121: invokevirtual getSelector : ()Landroid/support/v7/media/MediaRouteSelector;
    //   124: invokespecial <init> : (Landroid/support/v7/media/MediaRouteSelector;)V
    //   127: astore #7
    //   129: aload #7
    //   131: aload #6
    //   133: invokevirtual getSelector : ()Landroid/support/v7/media/MediaRouteSelector;
    //   136: invokevirtual addSelector : (Landroid/support/v7/media/MediaRouteSelector;)Landroid/support/v7/media/MediaRouteSelector$Builder;
    //   139: pop
    //   140: aload_3
    //   141: astore #8
    //   143: iinc #4, 1
    //   146: aload #7
    //   148: astore_2
    //   149: aload #8
    //   151: astore_3
    //   152: iload #9
    //   154: istore #5
    //   156: goto -> 18
    //   159: aload_2
    //   160: ifnull -> 177
    //   163: new android/support/v7/media/MediaRouteDiscoveryRequest
    //   166: dup
    //   167: aload_2
    //   168: invokevirtual build : ()Landroid/support/v7/media/MediaRouteSelector;
    //   171: iload #5
    //   173: invokespecial <init> : (Landroid/support/v7/media/MediaRouteSelector;Z)V
    //   176: astore_3
    //   177: aload_0
    //   178: getfield mCompositeDiscoveryRequest : Landroid/support/v7/media/MediaRouteDiscoveryRequest;
    //   181: aload_3
    //   182: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   185: ifne -> 203
    //   188: aload_0
    //   189: aload_3
    //   190: putfield mCompositeDiscoveryRequest : Landroid/support/v7/media/MediaRouteDiscoveryRequest;
    //   193: aload_0
    //   194: getfield mProvider : Landroid/support/v7/media/MediaRouteProvider;
    //   197: aload_3
    //   198: invokevirtual setDiscoveryRequest : (Landroid/support/v7/media/MediaRouteDiscoveryRequest;)V
    //   201: iconst_1
    //   202: ireturn
    //   203: iconst_0
    //   204: ireturn
  }
  
  private final class ClientRecord implements IBinder.DeathRecipient {
    private final SparseArray<MediaRouteProvider.RouteController> mControllers = new SparseArray();
    
    public MediaRouteDiscoveryRequest mDiscoveryRequest;
    
    public final Messenger mMessenger;
    
    public final int mVersion;
    
    public ClientRecord(Messenger param1Messenger, int param1Int) {
      this.mMessenger = param1Messenger;
      this.mVersion = param1Int;
    }
    
    public void binderDied() {
      MediaRouteProviderService.this.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
    }
    
    public boolean createRouteController(String param1String1, String param1String2, int param1Int) {
      if (this.mControllers.indexOfKey(param1Int) < 0) {
        MediaRouteProvider.RouteController routeController;
        if (param1String2 == null) {
          routeController = MediaRouteProviderService.this.mProvider.onCreateRouteController(param1String1);
        } else {
          routeController = MediaRouteProviderService.this.mProvider.onCreateRouteController((String)routeController, param1String2);
        } 
        if (routeController != null) {
          this.mControllers.put(param1Int, routeController);
          return true;
        } 
      } 
      return false;
    }
    
    public void dispose() {
      int i = this.mControllers.size();
      for (byte b = 0; b < i; b++)
        ((MediaRouteProvider.RouteController)this.mControllers.valueAt(b)).onRelease(); 
      this.mControllers.clear();
      this.mMessenger.getBinder().unlinkToDeath(this, 0);
      setDiscoveryRequest(null);
    }
    
    public MediaRouteProvider.RouteController getRouteController(int param1Int) {
      return (MediaRouteProvider.RouteController)this.mControllers.get(param1Int);
    }
    
    public boolean hasMessenger(Messenger param1Messenger) {
      boolean bool;
      if (this.mMessenger.getBinder() == param1Messenger.getBinder()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean register() {
      try {
        this.mMessenger.getBinder().linkToDeath(this, 0);
        return true;
      } catch (RemoteException remoteException) {
        binderDied();
        return false;
      } 
    }
    
    public boolean releaseRouteController(int param1Int) {
      MediaRouteProvider.RouteController routeController = (MediaRouteProvider.RouteController)this.mControllers.get(param1Int);
      if (routeController != null) {
        this.mControllers.remove(param1Int);
        routeController.onRelease();
        return true;
      } 
      return false;
    }
    
    public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest param1MediaRouteDiscoveryRequest) {
      if (!ObjectsCompat.equals(this.mDiscoveryRequest, param1MediaRouteDiscoveryRequest)) {
        this.mDiscoveryRequest = param1MediaRouteDiscoveryRequest;
        return MediaRouteProviderService.this.updateCompositeDiscoveryRequest();
      } 
      return false;
    }
    
    public String toString() {
      return MediaRouteProviderService.getClientId(this.mMessenger);
    }
  }
  
  private final class PrivateHandler extends Handler {
    public void handleMessage(Message param1Message) {
      if (param1Message.what == 1)
        MediaRouteProviderService.this.onBinderDied((Messenger)param1Message.obj); 
    }
  }
  
  private final class ProviderCallback extends MediaRouteProvider.Callback {
    public void onDescriptorChanged(MediaRouteProvider param1MediaRouteProvider, MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      MediaRouteProviderService.this.sendDescriptorChanged(param1MediaRouteProviderDescriptor);
    }
  }
  
  private static final class ReceiveHandler extends Handler {
    private final WeakReference<MediaRouteProviderService> mServiceRef;
    
    public ReceiveHandler(MediaRouteProviderService param1MediaRouteProviderService) {
      this.mServiceRef = new WeakReference<MediaRouteProviderService>(param1MediaRouteProviderService);
    }
    
    private boolean processMessage(int param1Int1, Messenger param1Messenger, int param1Int2, int param1Int3, Object param1Object, Bundle param1Bundle) {
      MediaRouteProviderService mediaRouteProviderService = this.mServiceRef.get();
      boolean bool = false;
      if (mediaRouteProviderService != null) {
        String str;
        switch (param1Int1) {
          default:
            return false;
          case 10:
            if (param1Object == null || param1Object instanceof Bundle) {
              param1Object = MediaRouteDiscoveryRequest.fromBundle((Bundle)param1Object);
              if (param1Object == null || !param1Object.isValid())
                param1Object = null; 
              return mediaRouteProviderService.onSetDiscoveryRequest(param1Messenger, param1Int2, (MediaRouteDiscoveryRequest)param1Object);
            } 
          case 9:
            if (param1Object instanceof Intent)
              return mediaRouteProviderService.onRouteControlRequest(param1Messenger, param1Int2, param1Int3, (Intent)param1Object); 
          case 8:
            param1Int1 = param1Bundle.getInt("volume", 0);
            if (param1Int1 != 0)
              return mediaRouteProviderService.onUpdateRouteVolume(param1Messenger, param1Int2, param1Int3, param1Int1); 
          case 7:
            param1Int1 = param1Bundle.getInt("volume", -1);
            if (param1Int1 >= 0)
              return mediaRouteProviderService.onSetRouteVolume(param1Messenger, param1Int2, param1Int3, param1Int1); 
          case 6:
            if (param1Bundle == null) {
              param1Int1 = bool;
            } else {
              param1Int1 = param1Bundle.getInt("unselectReason", 0);
            } 
            return mediaRouteProviderService.onUnselectRoute(param1Messenger, param1Int2, param1Int3, param1Int1);
          case 5:
            return mediaRouteProviderService.onSelectRoute(param1Messenger, param1Int2, param1Int3);
          case 4:
            return mediaRouteProviderService.onReleaseRouteController(param1Messenger, param1Int2, param1Int3);
          case 3:
            param1Object = param1Bundle.getString("routeId");
            str = param1Bundle.getString("routeGroupId");
            if (param1Object != null)
              return mediaRouteProviderService.onCreateRouteController(param1Messenger, param1Int2, param1Int3, (String)param1Object, str); 
          case 2:
            return mediaRouteProviderService.onUnregisterClient(param1Messenger, param1Int2);
          case 1:
            break;
        } 
        return mediaRouteProviderService.onRegisterClient(param1Messenger, param1Int2, param1Int3);
      } 
    }
    
    public void handleMessage(Message param1Message) {
      Messenger messenger = param1Message.replyTo;
      if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
        int i = param1Message.what;
        int j = param1Message.arg1;
        int k = param1Message.arg2;
        Object object = param1Message.obj;
        Bundle bundle = param1Message.peekData();
        if (!processMessage(i, messenger, j, k, object, bundle)) {
          if (MediaRouteProviderService.DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MediaRouteProviderService.getClientId(messenger));
            stringBuilder.append(": Message failed, what=");
            stringBuilder.append(i);
            stringBuilder.append(", requestId=");
            stringBuilder.append(j);
            stringBuilder.append(", arg=");
            stringBuilder.append(k);
            stringBuilder.append(", obj=");
            stringBuilder.append(object);
            stringBuilder.append(", data=");
            stringBuilder.append(bundle);
            Log.d("MediaRouteProviderSrv", stringBuilder.toString());
          } 
          MediaRouteProviderService.sendGenericFailure(messenger, j);
        } 
      } else if (MediaRouteProviderService.DEBUG) {
        Log.d("MediaRouteProviderSrv", "Ignoring message without valid reply messenger.");
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteProviderService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */