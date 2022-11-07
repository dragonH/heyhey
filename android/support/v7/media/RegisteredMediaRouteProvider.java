package android.support.v7.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

final class RegisteredMediaRouteProvider extends MediaRouteProvider implements ServiceConnection {
  static final boolean DEBUG = Log.isLoggable("MediaRouteProviderProxy", 3);
  
  static final String TAG = "MediaRouteProviderProxy";
  
  private Connection mActiveConnection;
  
  private boolean mBound;
  
  private final ComponentName mComponentName;
  
  private boolean mConnectionReady;
  
  private final ArrayList<Controller> mControllers = new ArrayList<Controller>();
  
  final PrivateHandler mPrivateHandler;
  
  private boolean mStarted;
  
  public RegisteredMediaRouteProvider(Context paramContext, ComponentName paramComponentName) {
    super(paramContext, new MediaRouteProvider.ProviderMetadata(paramComponentName));
    this.mComponentName = paramComponentName;
    this.mPrivateHandler = new PrivateHandler();
  }
  
  private void attachControllersToConnection() {
    int i = this.mControllers.size();
    for (byte b = 0; b < i; b++)
      ((Controller)this.mControllers.get(b)).attachConnection(this.mActiveConnection); 
  }
  
  private void bind() {
    if (!this.mBound) {
      boolean bool = DEBUG;
      if (bool) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Binding");
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      Intent intent = new Intent("android.media.MediaRouteProviderService");
      intent.setComponent(this.mComponentName);
      try {
        boolean bool1 = getContext().bindService(intent, this, 1);
        this.mBound = bool1;
        if (!bool1 && bool) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(this);
          stringBuilder.append(": Bind failed");
          Log.d("MediaRouteProviderProxy", stringBuilder.toString());
        } 
      } catch (SecurityException securityException) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(this);
          stringBuilder.append(": Bind failed");
          Log.d("MediaRouteProviderProxy", stringBuilder.toString(), securityException);
        } 
      } 
    } 
  }
  
  private MediaRouteProvider.RouteController createRouteController(String paramString1, String paramString2) {
    MediaRouteProviderDescriptor mediaRouteProviderDescriptor = getDescriptor();
    if (mediaRouteProviderDescriptor != null) {
      List<MediaRouteDescriptor> list = mediaRouteProviderDescriptor.getRoutes();
      int i = list.size();
      for (byte b = 0; b < i; b++) {
        if (((MediaRouteDescriptor)list.get(b)).getId().equals(paramString1)) {
          Controller controller = new Controller(paramString1, paramString2);
          this.mControllers.add(controller);
          if (this.mConnectionReady)
            controller.attachConnection(this.mActiveConnection); 
          updateBinding();
          return controller;
        } 
      } 
    } 
    return null;
  }
  
  private void detachControllersFromConnection() {
    int i = this.mControllers.size();
    for (byte b = 0; b < i; b++)
      ((Controller)this.mControllers.get(b)).detachConnection(); 
  }
  
  private void disconnect() {
    if (this.mActiveConnection != null) {
      setDescriptor(null);
      this.mConnectionReady = false;
      detachControllersFromConnection();
      this.mActiveConnection.dispose();
      this.mActiveConnection = null;
    } 
  }
  
  private boolean shouldBind() {
    if (this.mStarted) {
      if (getDiscoveryRequest() != null)
        return true; 
      if (!this.mControllers.isEmpty())
        return true; 
    } 
    return false;
  }
  
  private void unbind() {
    if (this.mBound) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Unbinding");
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      this.mBound = false;
      disconnect();
      getContext().unbindService(this);
    } 
  }
  
  private void updateBinding() {
    if (shouldBind()) {
      bind();
    } else {
      unbind();
    } 
  }
  
  public boolean hasComponentName(String paramString1, String paramString2) {
    boolean bool;
    if (this.mComponentName.getPackageName().equals(paramString1) && this.mComponentName.getClassName().equals(paramString2)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void onConnectionDescriptorChanged(Connection paramConnection, MediaRouteProviderDescriptor paramMediaRouteProviderDescriptor) {
    if (this.mActiveConnection == paramConnection) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Descriptor changed, descriptor=");
        stringBuilder.append(paramMediaRouteProviderDescriptor);
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      setDescriptor(paramMediaRouteProviderDescriptor);
    } 
  }
  
  void onConnectionDied(Connection paramConnection) {
    if (this.mActiveConnection == paramConnection) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Service connection died");
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      disconnect();
    } 
  }
  
  void onConnectionError(Connection paramConnection, String paramString) {
    if (this.mActiveConnection == paramConnection) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Service connection error - ");
        stringBuilder.append(paramString);
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      unbind();
    } 
  }
  
  void onConnectionReady(Connection paramConnection) {
    if (this.mActiveConnection == paramConnection) {
      this.mConnectionReady = true;
      attachControllersToConnection();
      MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = getDiscoveryRequest();
      if (mediaRouteDiscoveryRequest != null)
        this.mActiveConnection.setDiscoveryRequest(mediaRouteDiscoveryRequest); 
    } 
  }
  
  void onControllerReleased(Controller paramController) {
    this.mControllers.remove(paramController);
    paramController.detachConnection();
    updateBinding();
  }
  
  public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String paramString) {
    if (paramString != null)
      return createRouteController(paramString, null); 
    throw new IllegalArgumentException("routeId cannot be null");
  }
  
  public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String paramString1, @NonNull String paramString2) {
    if (paramString1 != null) {
      if (paramString2 != null)
        return createRouteController(paramString1, paramString2); 
      throw new IllegalArgumentException("routeGroupId cannot be null");
    } 
    throw new IllegalArgumentException("routeId cannot be null");
  }
  
  public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest paramMediaRouteDiscoveryRequest) {
    if (this.mConnectionReady)
      this.mActiveConnection.setDiscoveryRequest(paramMediaRouteDiscoveryRequest); 
    updateBinding();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    boolean bool = DEBUG;
    if (bool) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this);
      stringBuilder.append(": Connected");
      Log.d("MediaRouteProviderProxy", stringBuilder.toString());
    } 
    if (this.mBound) {
      disconnect();
      if (paramIBinder != null) {
        Messenger messenger = new Messenger(paramIBinder);
      } else {
        paramComponentName = null;
      } 
      if (MediaRouteProviderProtocol.isValidRemoteMessenger((Messenger)paramComponentName)) {
        Connection connection = new Connection((Messenger)paramComponentName);
        if (connection.register()) {
          this.mActiveConnection = connection;
        } else if (bool) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(this);
          stringBuilder.append(": Registration failed");
          Log.d("MediaRouteProviderProxy", stringBuilder.toString());
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Service returned invalid messenger binder");
        Log.e("MediaRouteProviderProxy", stringBuilder.toString());
      } 
    } 
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this);
      stringBuilder.append(": Service disconnected");
      Log.d("MediaRouteProviderProxy", stringBuilder.toString());
    } 
    disconnect();
  }
  
  public void rebindIfDisconnected() {
    if (this.mActiveConnection == null && shouldBind()) {
      unbind();
      bind();
    } 
  }
  
  public void start() {
    if (!this.mStarted) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Starting");
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      this.mStarted = true;
      updateBinding();
    } 
  }
  
  public void stop() {
    if (this.mStarted) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this);
        stringBuilder.append(": Stopping");
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
      this.mStarted = false;
      updateBinding();
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Service connection ");
    stringBuilder.append(this.mComponentName.flattenToShortString());
    return stringBuilder.toString();
  }
  
  private final class Connection implements IBinder.DeathRecipient {
    private int mNextControllerId = 1;
    
    private int mNextRequestId = 1;
    
    private final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks = new SparseArray();
    
    private int mPendingRegisterRequestId;
    
    private final RegisteredMediaRouteProvider.ReceiveHandler mReceiveHandler;
    
    private final Messenger mReceiveMessenger;
    
    private final Messenger mServiceMessenger;
    
    private int mServiceVersion;
    
    public Connection(Messenger param1Messenger) {
      this.mServiceMessenger = param1Messenger;
      RegisteredMediaRouteProvider.ReceiveHandler receiveHandler = new RegisteredMediaRouteProvider.ReceiveHandler(this);
      this.mReceiveHandler = receiveHandler;
      this.mReceiveMessenger = new Messenger(receiveHandler);
    }
    
    private boolean sendRequest(int param1Int1, int param1Int2, int param1Int3, Object param1Object, Bundle param1Bundle) {
      Message message = Message.obtain();
      message.what = param1Int1;
      message.arg1 = param1Int2;
      message.arg2 = param1Int3;
      message.obj = param1Object;
      message.setData(param1Bundle);
      message.replyTo = this.mReceiveMessenger;
      try {
        this.mServiceMessenger.send(message);
        return true;
      } catch (DeadObjectException deadObjectException) {
      
      } catch (RemoteException remoteException) {
        if (param1Int1 != 2)
          Log.e("MediaRouteProviderProxy", "Could not send message to service.", (Throwable)remoteException); 
      } 
      return false;
    }
    
    public void binderDied() {
      RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() {
            public void run() {
              RegisteredMediaRouteProvider.Connection connection = RegisteredMediaRouteProvider.Connection.this;
              RegisteredMediaRouteProvider.this.onConnectionDied(connection);
            }
          });
    }
    
    public int createRouteController(String param1String1, String param1String2) {
      int i = this.mNextControllerId;
      this.mNextControllerId = i + 1;
      Bundle bundle = new Bundle();
      bundle.putString("routeId", param1String1);
      bundle.putString("routeGroupId", param1String2);
      int j = this.mNextRequestId;
      this.mNextRequestId = j + 1;
      sendRequest(3, j, i, null, bundle);
      return i;
    }
    
    public void dispose() {
      sendRequest(2, 0, 0, null, null);
      this.mReceiveHandler.dispose();
      this.mServiceMessenger.getBinder().unlinkToDeath(this, 0);
      RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() {
            public void run() {
              RegisteredMediaRouteProvider.Connection.this.failPendingCallbacks();
            }
          });
    }
    
    void failPendingCallbacks() {
      for (byte b = 0; b < this.mPendingCallbacks.size(); b++)
        ((MediaRouter.ControlRequestCallback)this.mPendingCallbacks.valueAt(b)).onError(null, null); 
      this.mPendingCallbacks.clear();
    }
    
    public boolean onControlRequestFailed(int param1Int, String param1String, Bundle param1Bundle) {
      MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(param1Int);
      if (controlRequestCallback != null) {
        this.mPendingCallbacks.remove(param1Int);
        controlRequestCallback.onError(param1String, param1Bundle);
        return true;
      } 
      return false;
    }
    
    public boolean onControlRequestSucceeded(int param1Int, Bundle param1Bundle) {
      MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(param1Int);
      if (controlRequestCallback != null) {
        this.mPendingCallbacks.remove(param1Int);
        controlRequestCallback.onResult(param1Bundle);
        return true;
      } 
      return false;
    }
    
    public boolean onDescriptorChanged(Bundle param1Bundle) {
      if (this.mServiceVersion != 0) {
        RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(param1Bundle));
        return true;
      } 
      return false;
    }
    
    public boolean onGenericFailure(int param1Int) {
      if (param1Int == this.mPendingRegisterRequestId) {
        this.mPendingRegisterRequestId = 0;
        RegisteredMediaRouteProvider.this.onConnectionError(this, "Registration failed");
      } 
      MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(param1Int);
      if (controlRequestCallback != null) {
        this.mPendingCallbacks.remove(param1Int);
        controlRequestCallback.onError(null, null);
      } 
      return true;
    }
    
    public boolean onGenericSuccess(int param1Int) {
      return true;
    }
    
    public boolean onRegistered(int param1Int1, int param1Int2, Bundle param1Bundle) {
      if (this.mServiceVersion == 0 && param1Int1 == this.mPendingRegisterRequestId && param1Int2 >= 1) {
        this.mPendingRegisterRequestId = 0;
        this.mServiceVersion = param1Int2;
        RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(param1Bundle));
        RegisteredMediaRouteProvider.this.onConnectionReady(this);
        return true;
      } 
      return false;
    }
    
    public boolean register() {
      int i = this.mNextRequestId;
      this.mNextRequestId = i + 1;
      this.mPendingRegisterRequestId = i;
      if (!sendRequest(1, i, 2, null, null))
        return false; 
      try {
        this.mServiceMessenger.getBinder().linkToDeath(this, 0);
        return true;
      } catch (RemoteException remoteException) {
        binderDied();
        return false;
      } 
    }
    
    public void releaseRouteController(int param1Int) {
      int i = this.mNextRequestId;
      this.mNextRequestId = i + 1;
      sendRequest(4, i, param1Int, null, null);
    }
    
    public void selectRoute(int param1Int) {
      int i = this.mNextRequestId;
      this.mNextRequestId = i + 1;
      sendRequest(5, i, param1Int, null, null);
    }
    
    public boolean sendControlRequest(int param1Int, Intent param1Intent, MediaRouter.ControlRequestCallback param1ControlRequestCallback) {
      int i = this.mNextRequestId;
      this.mNextRequestId = i + 1;
      if (sendRequest(9, i, param1Int, param1Intent, null)) {
        if (param1ControlRequestCallback != null)
          this.mPendingCallbacks.put(i, param1ControlRequestCallback); 
        return true;
      } 
      return false;
    }
    
    public void setDiscoveryRequest(MediaRouteDiscoveryRequest param1MediaRouteDiscoveryRequest) {
      int i = this.mNextRequestId;
      this.mNextRequestId = i + 1;
      if (param1MediaRouteDiscoveryRequest != null) {
        Bundle bundle = param1MediaRouteDiscoveryRequest.asBundle();
      } else {
        param1MediaRouteDiscoveryRequest = null;
      } 
      sendRequest(10, i, 0, param1MediaRouteDiscoveryRequest, null);
    }
    
    public void setVolume(int param1Int1, int param1Int2) {
      Bundle bundle = new Bundle();
      bundle.putInt("volume", param1Int2);
      param1Int2 = this.mNextRequestId;
      this.mNextRequestId = param1Int2 + 1;
      sendRequest(7, param1Int2, param1Int1, null, bundle);
    }
    
    public void unselectRoute(int param1Int1, int param1Int2) {
      Bundle bundle = new Bundle();
      bundle.putInt("unselectReason", param1Int2);
      param1Int2 = this.mNextRequestId;
      this.mNextRequestId = param1Int2 + 1;
      sendRequest(6, param1Int2, param1Int1, null, bundle);
    }
    
    public void updateVolume(int param1Int1, int param1Int2) {
      Bundle bundle = new Bundle();
      bundle.putInt("volume", param1Int2);
      param1Int2 = this.mNextRequestId;
      this.mNextRequestId = param1Int2 + 1;
      sendRequest(8, param1Int2, param1Int1, null, bundle);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.failPendingCallbacks();
    }
  }
  
  class null implements Runnable {
    public void run() {
      RegisteredMediaRouteProvider.Connection connection = this.this$1;
      RegisteredMediaRouteProvider.this.onConnectionDied(connection);
    }
  }
  
  private final class Controller extends MediaRouteProvider.RouteController {
    private RegisteredMediaRouteProvider.Connection mConnection;
    
    private int mControllerId;
    
    private int mPendingSetVolume = -1;
    
    private int mPendingUpdateVolumeDelta;
    
    private final String mRouteGroupId;
    
    private final String mRouteId;
    
    private boolean mSelected;
    
    public Controller(String param1String1, String param1String2) {
      this.mRouteId = param1String1;
      this.mRouteGroupId = param1String2;
    }
    
    public void attachConnection(RegisteredMediaRouteProvider.Connection param1Connection) {
      this.mConnection = param1Connection;
      int i = param1Connection.createRouteController(this.mRouteId, this.mRouteGroupId);
      this.mControllerId = i;
      if (this.mSelected) {
        param1Connection.selectRoute(i);
        i = this.mPendingSetVolume;
        if (i >= 0) {
          param1Connection.setVolume(this.mControllerId, i);
          this.mPendingSetVolume = -1;
        } 
        i = this.mPendingUpdateVolumeDelta;
        if (i != 0) {
          param1Connection.updateVolume(this.mControllerId, i);
          this.mPendingUpdateVolumeDelta = 0;
        } 
      } 
    }
    
    public void detachConnection() {
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      if (connection != null) {
        connection.releaseRouteController(this.mControllerId);
        this.mConnection = null;
        this.mControllerId = 0;
      } 
    }
    
    public boolean onControlRequest(Intent param1Intent, MediaRouter.ControlRequestCallback param1ControlRequestCallback) {
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      return (connection != null) ? connection.sendControlRequest(this.mControllerId, param1Intent, param1ControlRequestCallback) : false;
    }
    
    public void onRelease() {
      RegisteredMediaRouteProvider.this.onControllerReleased(this);
    }
    
    public void onSelect() {
      this.mSelected = true;
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      if (connection != null)
        connection.selectRoute(this.mControllerId); 
    }
    
    public void onSetVolume(int param1Int) {
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      if (connection != null) {
        connection.setVolume(this.mControllerId, param1Int);
      } else {
        this.mPendingSetVolume = param1Int;
        this.mPendingUpdateVolumeDelta = 0;
      } 
    }
    
    public void onUnselect() {
      onUnselect(0);
    }
    
    public void onUnselect(int param1Int) {
      this.mSelected = false;
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      if (connection != null)
        connection.unselectRoute(this.mControllerId, param1Int); 
    }
    
    public void onUpdateVolume(int param1Int) {
      RegisteredMediaRouteProvider.Connection connection = this.mConnection;
      if (connection != null) {
        connection.updateVolume(this.mControllerId, param1Int);
      } else {
        this.mPendingUpdateVolumeDelta += param1Int;
      } 
    }
  }
  
  private static final class PrivateHandler extends Handler {}
  
  private static final class ReceiveHandler extends Handler {
    private final WeakReference<RegisteredMediaRouteProvider.Connection> mConnectionRef;
    
    public ReceiveHandler(RegisteredMediaRouteProvider.Connection param1Connection) {
      this.mConnectionRef = new WeakReference<RegisteredMediaRouteProvider.Connection>(param1Connection);
    }
    
    private boolean processMessage(RegisteredMediaRouteProvider.Connection param1Connection, int param1Int1, int param1Int2, int param1Int3, Object param1Object, Bundle param1Bundle) {
      if (param1Int1 != 0) {
        if (param1Int1 != 1) {
          if (param1Int1 != 2) {
            if (param1Int1 != 3) {
              if (param1Int1 != 4) {
                if (param1Int1 == 5 && (param1Object == null || param1Object instanceof Bundle))
                  return param1Connection.onDescriptorChanged((Bundle)param1Object); 
              } else if (param1Object == null || param1Object instanceof Bundle) {
                String str;
                if (param1Bundle == null) {
                  param1Bundle = null;
                } else {
                  str = param1Bundle.getString("error");
                } 
                return param1Connection.onControlRequestFailed(param1Int2, str, (Bundle)param1Object);
              } 
            } else if (param1Object == null || param1Object instanceof Bundle) {
              return param1Connection.onControlRequestSucceeded(param1Int2, (Bundle)param1Object);
            } 
          } else if (param1Object == null || param1Object instanceof Bundle) {
            return param1Connection.onRegistered(param1Int2, param1Int3, (Bundle)param1Object);
          } 
          return false;
        } 
        param1Connection.onGenericSuccess(param1Int2);
        return true;
      } 
      param1Connection.onGenericFailure(param1Int2);
      return true;
    }
    
    public void dispose() {
      this.mConnectionRef.clear();
    }
    
    public void handleMessage(Message param1Message) {
      RegisteredMediaRouteProvider.Connection connection = this.mConnectionRef.get();
      if (connection != null && !processMessage(connection, param1Message.what, param1Message.arg1, param1Message.arg2, param1Message.obj, param1Message.peekData()) && RegisteredMediaRouteProvider.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unhandled message from server: ");
        stringBuilder.append(param1Message);
        Log.d("MediaRouteProviderProxy", stringBuilder.toString());
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/RegisteredMediaRouteProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */