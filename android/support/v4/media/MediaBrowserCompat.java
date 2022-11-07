package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class MediaBrowserCompat {
  public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
  
  public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
  
  static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
  
  public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
  
  public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
  
  public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
  
  public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
  
  static final String TAG = "MediaBrowserCompat";
  
  private final MediaBrowserImpl mImpl;
  
  public MediaBrowserCompat(Context paramContext, ComponentName paramComponentName, ConnectionCallback paramConnectionCallback, Bundle paramBundle) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 26) {
      this.mImpl = new MediaBrowserImplApi24(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    } else if (i >= 23) {
      this.mImpl = new MediaBrowserImplApi23(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    } else if (i >= 21) {
      this.mImpl = new MediaBrowserImplApi21(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    } else {
      this.mImpl = new MediaBrowserImplBase(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
    } 
  }
  
  public void connect() {
    this.mImpl.connect();
  }
  
  public void disconnect() {
    this.mImpl.disconnect();
  }
  
  @Nullable
  public Bundle getExtras() {
    return this.mImpl.getExtras();
  }
  
  public void getItem(@NonNull String paramString, @NonNull ItemCallback paramItemCallback) {
    this.mImpl.getItem(paramString, paramItemCallback);
  }
  
  @NonNull
  public String getRoot() {
    return this.mImpl.getRoot();
  }
  
  @NonNull
  public ComponentName getServiceComponent() {
    return this.mImpl.getServiceComponent();
  }
  
  @NonNull
  public MediaSessionCompat.Token getSessionToken() {
    return this.mImpl.getSessionToken();
  }
  
  public boolean isConnected() {
    return this.mImpl.isConnected();
  }
  
  public void search(@NonNull String paramString, Bundle paramBundle, @NonNull SearchCallback paramSearchCallback) {
    if (!TextUtils.isEmpty(paramString)) {
      if (paramSearchCallback != null) {
        this.mImpl.search(paramString, paramBundle, paramSearchCallback);
        return;
      } 
      throw new IllegalArgumentException("callback cannot be null");
    } 
    throw new IllegalArgumentException("query cannot be empty");
  }
  
  public void sendCustomAction(@NonNull String paramString, Bundle paramBundle, @Nullable CustomActionCallback paramCustomActionCallback) {
    if (!TextUtils.isEmpty(paramString)) {
      this.mImpl.sendCustomAction(paramString, paramBundle, paramCustomActionCallback);
      return;
    } 
    throw new IllegalArgumentException("action cannot be empty");
  }
  
  public void subscribe(@NonNull String paramString, @NonNull Bundle paramBundle, @NonNull SubscriptionCallback paramSubscriptionCallback) {
    if (!TextUtils.isEmpty(paramString)) {
      if (paramSubscriptionCallback != null) {
        if (paramBundle != null) {
          this.mImpl.subscribe(paramString, paramBundle, paramSubscriptionCallback);
          return;
        } 
        throw new IllegalArgumentException("options are null");
      } 
      throw new IllegalArgumentException("callback is null");
    } 
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void subscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback) {
    if (!TextUtils.isEmpty(paramString)) {
      if (paramSubscriptionCallback != null) {
        this.mImpl.subscribe(paramString, null, paramSubscriptionCallback);
        return;
      } 
      throw new IllegalArgumentException("callback is null");
    } 
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void unsubscribe(@NonNull String paramString) {
    if (!TextUtils.isEmpty(paramString)) {
      this.mImpl.unsubscribe(paramString, null);
      return;
    } 
    throw new IllegalArgumentException("parentId is empty");
  }
  
  public void unsubscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback) {
    if (!TextUtils.isEmpty(paramString)) {
      if (paramSubscriptionCallback != null) {
        this.mImpl.unsubscribe(paramString, paramSubscriptionCallback);
        return;
      } 
      throw new IllegalArgumentException("callback is null");
    } 
    throw new IllegalArgumentException("parentId is empty");
  }
  
  private static class CallbackHandler extends Handler {
    private final WeakReference<MediaBrowserCompat.MediaBrowserServiceCallbackImpl> mCallbackImplRef;
    
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl param1MediaBrowserServiceCallbackImpl) {
      this.mCallbackImplRef = new WeakReference<MediaBrowserCompat.MediaBrowserServiceCallbackImpl>(param1MediaBrowserServiceCallbackImpl);
    }
    
    public void handleMessage(Message param1Message) {
      WeakReference<Messenger> weakReference = this.mCallbacksMessengerRef;
      if (weakReference != null && weakReference.get() != null && this.mCallbackImplRef.get() != null) {
        Bundle bundle = param1Message.getData();
        bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        MediaBrowserCompat.MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl = this.mCallbackImplRef.get();
        Messenger messenger = this.mCallbacksMessengerRef.get();
        try {
          StringBuilder stringBuilder;
          int i = param1Message.what;
          if (i != 1) {
            if (i != 2) {
              if (i != 3) {
                stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Unhandled message: ");
                stringBuilder.append(param1Message);
                stringBuilder.append("\n  Client version: ");
                stringBuilder.append(1);
                stringBuilder.append("\n  Service version: ");
                stringBuilder.append(param1Message.arg1);
                Log.w("MediaBrowserCompat", stringBuilder.toString());
              } else {
                mediaBrowserServiceCallbackImpl.onLoadChildren(messenger, stringBuilder.getString("data_media_item_id"), stringBuilder.getParcelableArrayList("data_media_item_list"), stringBuilder.getBundle("data_options"));
              } 
            } else {
              mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger);
            } 
          } else {
            mediaBrowserServiceCallbackImpl.onServiceConnected(messenger, stringBuilder.getString("data_media_item_id"), (MediaSessionCompat.Token)stringBuilder.getParcelable("data_media_session_token"), stringBuilder.getBundle("data_root_hints"));
          } 
        } catch (BadParcelableException badParcelableException) {
          Log.e("MediaBrowserCompat", "Could not unparcel the data.");
          if (param1Message.what == 1)
            mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger); 
        } 
      } 
    }
    
    void setCallbacksMessenger(Messenger param1Messenger) {
      this.mCallbacksMessengerRef = new WeakReference<Messenger>(param1Messenger);
    }
  }
  
  public static class ConnectionCallback {
    ConnectionCallbackInternal mConnectionCallbackInternal;
    
    final Object mConnectionCallbackObj;
    
    public ConnectionCallback() {
      if (Build.VERSION.SDK_INT >= 21) {
        this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
      } else {
        this.mConnectionCallbackObj = null;
      } 
    }
    
    public void onConnected() {}
    
    public void onConnectionFailed() {}
    
    public void onConnectionSuspended() {}
    
    void setInternalConnectionCallback(ConnectionCallbackInternal param1ConnectionCallbackInternal) {
      this.mConnectionCallbackInternal = param1ConnectionCallbackInternal;
    }
    
    static interface ConnectionCallbackInternal {
      void onConnected();
      
      void onConnectionFailed();
      
      void onConnectionSuspended();
    }
    
    private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
      public void onConnected() {
        MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal;
        if (connectionCallbackInternal != null)
          connectionCallbackInternal.onConnected(); 
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
      }
      
      public void onConnectionFailed() {
        MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal;
        if (connectionCallbackInternal != null)
          connectionCallbackInternal.onConnectionFailed(); 
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
      }
      
      public void onConnectionSuspended() {
        MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal;
        if (connectionCallbackInternal != null)
          connectionCallbackInternal.onConnectionSuspended(); 
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
      }
    }
  }
  
  static interface ConnectionCallbackInternal {
    void onConnected();
    
    void onConnectionFailed();
    
    void onConnectionSuspended();
  }
  
  private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
    public void onConnected() {
      MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = this.this$0.mConnectionCallbackInternal;
      if (connectionCallbackInternal != null)
        connectionCallbackInternal.onConnected(); 
      this.this$0.onConnected();
    }
    
    public void onConnectionFailed() {
      MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = this.this$0.mConnectionCallbackInternal;
      if (connectionCallbackInternal != null)
        connectionCallbackInternal.onConnectionFailed(); 
      this.this$0.onConnectionFailed();
    }
    
    public void onConnectionSuspended() {
      MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal connectionCallbackInternal = this.this$0.mConnectionCallbackInternal;
      if (connectionCallbackInternal != null)
        connectionCallbackInternal.onConnectionSuspended(); 
      this.this$0.onConnectionSuspended();
    }
  }
  
  public static abstract class CustomActionCallback {
    public void onError(String param1String, Bundle param1Bundle1, Bundle param1Bundle2) {}
    
    public void onProgressUpdate(String param1String, Bundle param1Bundle1, Bundle param1Bundle2) {}
    
    public void onResult(String param1String, Bundle param1Bundle1, Bundle param1Bundle2) {}
  }
  
  private static class CustomActionResultReceiver extends ResultReceiver {
    private final String mAction;
    
    private final MediaBrowserCompat.CustomActionCallback mCallback;
    
    private final Bundle mExtras;
    
    CustomActionResultReceiver(String param1String, Bundle param1Bundle, MediaBrowserCompat.CustomActionCallback param1CustomActionCallback, Handler param1Handler) {
      super(param1Handler);
      this.mAction = param1String;
      this.mExtras = param1Bundle;
      this.mCallback = param1CustomActionCallback;
    }
    
    protected void onReceiveResult(int param1Int, Bundle param1Bundle) {
      StringBuilder stringBuilder;
      MediaBrowserCompat.CustomActionCallback customActionCallback = this.mCallback;
      if (customActionCallback == null)
        return; 
      if (param1Int != -1) {
        if (param1Int != 0) {
          if (param1Int != 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown result code: ");
            stringBuilder.append(param1Int);
            stringBuilder.append(" (extras=");
            stringBuilder.append(this.mExtras);
            stringBuilder.append(", resultData=");
            stringBuilder.append(param1Bundle);
            stringBuilder.append(")");
            Log.w("MediaBrowserCompat", stringBuilder.toString());
          } else {
            stringBuilder.onProgressUpdate(this.mAction, this.mExtras, param1Bundle);
          } 
        } else {
          stringBuilder.onResult(this.mAction, this.mExtras, param1Bundle);
        } 
      } else {
        stringBuilder.onError(this.mAction, this.mExtras, param1Bundle);
      } 
    }
  }
  
  public static abstract class ItemCallback {
    final Object mItemCallbackObj;
    
    public ItemCallback() {
      if (Build.VERSION.SDK_INT >= 23) {
        this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
      } else {
        this.mItemCallbackObj = null;
      } 
    }
    
    public void onError(@NonNull String param1String) {}
    
    public void onItemLoaded(MediaBrowserCompat.MediaItem param1MediaItem) {}
    
    private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
      public void onError(@NonNull String param2String) {
        MediaBrowserCompat.ItemCallback.this.onError(param2String);
      }
      
      public void onItemLoaded(Parcel param2Parcel) {
        if (param2Parcel == null) {
          MediaBrowserCompat.ItemCallback.this.onItemLoaded(null);
        } else {
          param2Parcel.setDataPosition(0);
          MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(param2Parcel);
          param2Parcel.recycle();
          MediaBrowserCompat.ItemCallback.this.onItemLoaded(mediaItem);
        } 
      }
    }
  }
  
  private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
    public void onError(@NonNull String param1String) {
      this.this$0.onError(param1String);
    }
    
    public void onItemLoaded(Parcel param1Parcel) {
      if (param1Parcel == null) {
        this.this$0.onItemLoaded(null);
      } else {
        param1Parcel.setDataPosition(0);
        MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(param1Parcel);
        param1Parcel.recycle();
        this.this$0.onItemLoaded(mediaItem);
      } 
    }
  }
  
  private static class ItemReceiver extends ResultReceiver {
    private final MediaBrowserCompat.ItemCallback mCallback;
    
    private final String mMediaId;
    
    ItemReceiver(String param1String, MediaBrowserCompat.ItemCallback param1ItemCallback, Handler param1Handler) {
      super(param1Handler);
      this.mMediaId = param1String;
      this.mCallback = param1ItemCallback;
    }
    
    protected void onReceiveResult(int param1Int, Bundle param1Bundle) {
      if (param1Bundle != null)
        param1Bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader()); 
      if (param1Int != 0 || param1Bundle == null || !param1Bundle.containsKey("media_item")) {
        this.mCallback.onError(this.mMediaId);
        return;
      } 
      Parcelable parcelable = param1Bundle.getParcelable("media_item");
      if (parcelable == null || parcelable instanceof MediaBrowserCompat.MediaItem) {
        this.mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)parcelable);
        return;
      } 
      this.mCallback.onError(this.mMediaId);
    }
  }
  
  static interface MediaBrowserImpl {
    void connect();
    
    void disconnect();
    
    @Nullable
    Bundle getExtras();
    
    void getItem(@NonNull String param1String, @NonNull MediaBrowserCompat.ItemCallback param1ItemCallback);
    
    @NonNull
    String getRoot();
    
    ComponentName getServiceComponent();
    
    @NonNull
    MediaSessionCompat.Token getSessionToken();
    
    boolean isConnected();
    
    void search(@NonNull String param1String, Bundle param1Bundle, @NonNull MediaBrowserCompat.SearchCallback param1SearchCallback);
    
    void sendCustomAction(@NonNull String param1String, Bundle param1Bundle, @Nullable MediaBrowserCompat.CustomActionCallback param1CustomActionCallback);
    
    void subscribe(@NonNull String param1String, Bundle param1Bundle, @NonNull MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback);
    
    void unsubscribe(@NonNull String param1String, MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback);
  }
  
  @RequiresApi(21)
  static class MediaBrowserImplApi21 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {
    protected final Object mBrowserObj;
    
    protected Messenger mCallbacksMessenger;
    
    final Context mContext;
    
    protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    
    private MediaSessionCompat.Token mMediaSessionToken;
    
    protected final Bundle mRootHints;
    
    protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplApi21(Context param1Context, ComponentName param1ComponentName, MediaBrowserCompat.ConnectionCallback param1ConnectionCallback, Bundle param1Bundle) {
      this.mContext = param1Context;
      Bundle bundle = param1Bundle;
      if (param1Bundle == null)
        bundle = new Bundle(); 
      bundle.putInt("extra_client_version", 1);
      param1Bundle = new Bundle(bundle);
      this.mRootHints = param1Bundle;
      param1ConnectionCallback.setInternalConnectionCallback(this);
      this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(param1Context, param1ComponentName, param1ConnectionCallback.mConnectionCallbackObj, param1Bundle);
    }
    
    public void connect() {
      MediaBrowserCompatApi21.connect(this.mBrowserObj);
    }
    
    public void disconnect() {
      MediaBrowserCompat.ServiceBinderWrapper serviceBinderWrapper = this.mServiceBinderWrapper;
      if (serviceBinderWrapper != null) {
        Messenger messenger = this.mCallbacksMessenger;
        if (messenger != null)
          try {
            serviceBinderWrapper.unregisterCallbackMessenger(messenger);
          } catch (RemoteException remoteException) {
            Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
          }  
      } 
      MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
    }
    
    @Nullable
    public Bundle getExtras() {
      return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
    }
    
    public void getItem(@NonNull final String mediaId, @NonNull final MediaBrowserCompat.ItemCallback cb) {
      if (!TextUtils.isEmpty(mediaId)) {
        if (cb != null) {
          if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post(new Runnable() {
                  public void run() {
                    cb.onError(mediaId);
                  }
                });
            return;
          } 
          if (this.mServiceBinderWrapper == null) {
            this.mHandler.post(new Runnable() {
                  public void run() {
                    cb.onError(mediaId);
                  }
                });
            return;
          } 
          MediaBrowserCompat.ItemReceiver itemReceiver = new MediaBrowserCompat.ItemReceiver(mediaId, cb, this.mHandler);
          try {
            this.mServiceBinderWrapper.getMediaItem(mediaId, itemReceiver, this.mCallbacksMessenger);
          } catch (RemoteException remoteException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Remote error getting media item: ");
            stringBuilder.append(mediaId);
            Log.i("MediaBrowserCompat", stringBuilder.toString());
            this.mHandler.post(new Runnable() {
                  public void run() {
                    cb.onError(mediaId);
                  }
                });
          } 
          return;
        } 
        throw new IllegalArgumentException("cb is null");
      } 
      throw new IllegalArgumentException("mediaId is empty");
    }
    
    @NonNull
    public String getRoot() {
      return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
    }
    
    public ComponentName getServiceComponent() {
      return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
      if (this.mMediaSessionToken == null)
        this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj)); 
      return this.mMediaSessionToken;
    }
    
    public boolean isConnected() {
      return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
    }
    
    public void onConnected() {
      Bundle bundle = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
      if (bundle == null)
        return; 
      IBinder iBinder = BundleCompat.getBinder(bundle, "extra_messenger");
      if (iBinder != null) {
        this.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(iBinder, this.mRootHints);
        Messenger messenger = new Messenger(this.mHandler);
        this.mCallbacksMessenger = messenger;
        this.mHandler.setCallbacksMessenger(messenger);
        try {
          this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
        } 
      } 
      IMediaSession iMediaSession = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "extra_session_binder"));
      if (iMediaSession != null)
        this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), iMediaSession); 
    }
    
    public void onConnectionFailed() {}
    
    public void onConnectionFailed(Messenger param1Messenger) {}
    
    public void onConnectionSuspended() {
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
      this.mMediaSessionToken = null;
      this.mHandler.setCallbacksMessenger(null);
    }
    
    public void onLoadChildren(Messenger param1Messenger, String param1String, List<MediaBrowserCompat.MediaItem> param1List, Bundle param1Bundle) {
      StringBuilder stringBuilder;
      if (this.mCallbacksMessenger != param1Messenger)
        return; 
      MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      if (subscription == null) {
        if (MediaBrowserCompat.DEBUG) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onLoadChildren for id that isn't subscribed id=");
          stringBuilder.append(param1String);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
        } 
        return;
      } 
      MediaBrowserCompat.SubscriptionCallback subscriptionCallback = stringBuilder.getCallback(this.mContext, param1Bundle);
      if (subscriptionCallback != null)
        if (param1Bundle == null) {
          if (param1List == null) {
            subscriptionCallback.onError(param1String);
          } else {
            subscriptionCallback.onChildrenLoaded(param1String, param1List);
          } 
        } else if (param1List == null) {
          subscriptionCallback.onError(param1String, param1Bundle);
        } else {
          subscriptionCallback.onChildrenLoaded(param1String, param1List, param1Bundle);
        }  
    }
    
    public void onServiceConnected(Messenger param1Messenger, String param1String, MediaSessionCompat.Token param1Token, Bundle param1Bundle) {}
    
    public void search(@NonNull final String query, final Bundle extras, @NonNull final MediaBrowserCompat.SearchCallback callback) {
      if (isConnected()) {
        if (this.mServiceBinderWrapper == null) {
          Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
          this.mHandler.post(new Runnable() {
                public void run() {
                  callback.onError(query, extras);
                }
              });
          return;
        } 
        MediaBrowserCompat.SearchResultReceiver searchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(query, extras, callback, this.mHandler);
        try {
          this.mServiceBinderWrapper.search(query, extras, searchResultReceiver, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Remote error searching items with query: ");
          stringBuilder.append(query);
          Log.i("MediaBrowserCompat", stringBuilder.toString(), (Throwable)remoteException);
          this.mHandler.post(new Runnable() {
                public void run() {
                  callback.onError(query, extras);
                }
              });
        } 
        return;
      } 
      throw new IllegalStateException("search() called while not connected");
    }
    
    public void sendCustomAction(@NonNull final String action, final Bundle extras, @Nullable final MediaBrowserCompat.CustomActionCallback callback) {
      if (isConnected()) {
        if (this.mServiceBinderWrapper == null) {
          Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
          if (callback != null)
            this.mHandler.post(new Runnable() {
                  public void run() {
                    callback.onError(action, extras, null);
                  }
                }); 
        } 
        MediaBrowserCompat.CustomActionResultReceiver customActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(action, extras, callback, this.mHandler);
        try {
          this.mServiceBinderWrapper.sendCustomAction(action, extras, customActionResultReceiver, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Remote error sending a custom action: action=");
          stringBuilder1.append(action);
          stringBuilder1.append(", extras=");
          stringBuilder1.append(extras);
          Log.i("MediaBrowserCompat", stringBuilder1.toString(), (Throwable)remoteException);
          if (callback != null)
            this.mHandler.post(new Runnable() {
                  public void run() {
                    callback.onError(action, extras, null);
                  }
                }); 
        } 
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot send a custom action (");
      stringBuilder.append(action);
      stringBuilder.append(") with ");
      stringBuilder.append("extras ");
      stringBuilder.append(extras);
      stringBuilder.append(" because the browser is not connected to the ");
      stringBuilder.append("service.");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public void subscribe(@NonNull String param1String, Bundle param1Bundle, @NonNull MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      MediaBrowserCompat.Subscription subscription1 = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      MediaBrowserCompat.Subscription subscription2 = subscription1;
      if (subscription1 == null) {
        subscription2 = new MediaBrowserCompat.Subscription();
        this.mSubscriptions.put(param1String, subscription2);
      } 
      param1SubscriptionCallback.setSubscription(subscription2);
      if (param1Bundle == null) {
        param1Bundle = null;
      } else {
        param1Bundle = new Bundle(param1Bundle);
      } 
      subscription2.putCallback(this.mContext, param1Bundle, param1SubscriptionCallback);
      MediaBrowserCompat.ServiceBinderWrapper serviceBinderWrapper = this.mServiceBinderWrapper;
      if (serviceBinderWrapper == null) {
        MediaBrowserCompatApi21.subscribe(this.mBrowserObj, param1String, param1SubscriptionCallback.mSubscriptionCallbackObj);
      } else {
        try {
          serviceBinderWrapper.addSubscription(param1String, param1SubscriptionCallback.mToken, param1Bundle, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Remote error subscribing media item: ");
          stringBuilder.append(param1String);
          Log.i("MediaBrowserCompat", stringBuilder.toString());
        } 
      } 
    }
    
    public void unsubscribe(@NonNull String param1String, MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      List<Bundle> list;
      MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      if (subscription == null)
        return; 
      MediaBrowserCompat.ServiceBinderWrapper serviceBinderWrapper = this.mServiceBinderWrapper;
      if (serviceBinderWrapper == null) {
        if (param1SubscriptionCallback == null) {
          MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, param1String);
        } else {
          List<MediaBrowserCompat.SubscriptionCallback> list1 = subscription.getCallbacks();
          list = subscription.getOptionsList();
          for (int i = list1.size() - 1; i >= 0; i--) {
            if (list1.get(i) == param1SubscriptionCallback) {
              list1.remove(i);
              list.remove(i);
            } 
          } 
          if (list1.size() == 0)
            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, param1String); 
        } 
      } else if (param1SubscriptionCallback == null) {
        try {
          list.removeSubscription(param1String, null, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("removeSubscription failed with RemoteException parentId=");
          stringBuilder.append(param1String);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
        } 
      } else {
        list = (List)subscription.getCallbacks();
        List<Bundle> list1 = subscription.getOptionsList();
        for (int i = list.size() - 1; i >= 0; i--) {
          if (list.get(i) == param1SubscriptionCallback) {
            this.mServiceBinderWrapper.removeSubscription(param1String, param1SubscriptionCallback.mToken, this.mCallbacksMessenger);
            list.remove(i);
            list1.remove(i);
          } 
        } 
      } 
      if (subscription.isEmpty() || param1SubscriptionCallback == null)
        this.mSubscriptions.remove(param1String); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      cb.onError(mediaId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      cb.onError(mediaId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      cb.onError(mediaId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(query, extras);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(query, extras);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(action, extras, null);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(action, extras, null);
    }
  }
  
  @RequiresApi(23)
  static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
    public MediaBrowserImplApi23(Context param1Context, ComponentName param1ComponentName, MediaBrowserCompat.ConnectionCallback param1ConnectionCallback, Bundle param1Bundle) {
      super(param1Context, param1ComponentName, param1ConnectionCallback, param1Bundle);
    }
    
    public void getItem(@NonNull String param1String, @NonNull MediaBrowserCompat.ItemCallback param1ItemCallback) {
      if (this.mServiceBinderWrapper == null) {
        MediaBrowserCompatApi23.getItem(this.mBrowserObj, param1String, param1ItemCallback.mItemCallbackObj);
      } else {
        super.getItem(param1String, param1ItemCallback);
      } 
    }
  }
  
  @RequiresApi(26)
  static class MediaBrowserImplApi24 extends MediaBrowserImplApi23 {
    public MediaBrowserImplApi24(Context param1Context, ComponentName param1ComponentName, MediaBrowserCompat.ConnectionCallback param1ConnectionCallback, Bundle param1Bundle) {
      super(param1Context, param1ComponentName, param1ConnectionCallback, param1Bundle);
    }
    
    public void subscribe(@NonNull String param1String, @NonNull Bundle param1Bundle, @NonNull MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      if (param1Bundle == null) {
        MediaBrowserCompatApi21.subscribe(this.mBrowserObj, param1String, param1SubscriptionCallback.mSubscriptionCallbackObj);
      } else {
        MediaBrowserCompatApi24.subscribe(this.mBrowserObj, param1String, param1Bundle, param1SubscriptionCallback.mSubscriptionCallbackObj);
      } 
    }
    
    public void unsubscribe(@NonNull String param1String, MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      if (param1SubscriptionCallback == null) {
        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, param1String);
      } else {
        MediaBrowserCompatApi24.unsubscribe(this.mBrowserObj, param1String, param1SubscriptionCallback.mSubscriptionCallbackObj);
      } 
    }
  }
  
  static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
    static final int CONNECT_STATE_CONNECTED = 3;
    
    static final int CONNECT_STATE_CONNECTING = 2;
    
    static final int CONNECT_STATE_DISCONNECTED = 1;
    
    static final int CONNECT_STATE_DISCONNECTING = 0;
    
    static final int CONNECT_STATE_SUSPENDED = 4;
    
    final MediaBrowserCompat.ConnectionCallback mCallback;
    
    Messenger mCallbacksMessenger;
    
    final Context mContext;
    
    private Bundle mExtras;
    
    final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    
    private MediaSessionCompat.Token mMediaSessionToken;
    
    final Bundle mRootHints;
    
    private String mRootId;
    
    MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    
    final ComponentName mServiceComponent;
    
    MediaServiceConnection mServiceConnection;
    
    int mState = 1;
    
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplBase(Context param1Context, ComponentName param1ComponentName, MediaBrowserCompat.ConnectionCallback param1ConnectionCallback, Bundle param1Bundle) {
      if (param1Context != null) {
        if (param1ComponentName != null) {
          if (param1ConnectionCallback != null) {
            Bundle bundle;
            this.mContext = param1Context;
            this.mServiceComponent = param1ComponentName;
            this.mCallback = param1ConnectionCallback;
            if (param1Bundle == null) {
              param1Context = null;
            } else {
              bundle = new Bundle(param1Bundle);
            } 
            this.mRootHints = bundle;
            return;
          } 
          throw new IllegalArgumentException("connection callback must not be null");
        } 
        throw new IllegalArgumentException("service component must not be null");
      } 
      throw new IllegalArgumentException("context must not be null");
    }
    
    private static String getStateLabel(int param1Int) {
      if (param1Int != 0) {
        if (param1Int != 1) {
          if (param1Int != 2) {
            if (param1Int != 3) {
              if (param1Int != 4) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UNKNOWN/");
                stringBuilder.append(param1Int);
                return stringBuilder.toString();
              } 
              return "CONNECT_STATE_SUSPENDED";
            } 
            return "CONNECT_STATE_CONNECTED";
          } 
          return "CONNECT_STATE_CONNECTING";
        } 
        return "CONNECT_STATE_DISCONNECTED";
      } 
      return "CONNECT_STATE_DISCONNECTING";
    }
    
    private boolean isCurrent(Messenger param1Messenger, String param1String) {
      if (this.mCallbacksMessenger == param1Messenger) {
        int j = this.mState;
        if (j != 0 && j != 1)
          return true; 
      } 
      int i = this.mState;
      if (i != 0 && i != 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append(" for ");
        stringBuilder.append(this.mServiceComponent);
        stringBuilder.append(" with mCallbacksMessenger=");
        stringBuilder.append(this.mCallbacksMessenger);
        stringBuilder.append(" this=");
        stringBuilder.append(this);
        Log.i("MediaBrowserCompat", stringBuilder.toString());
      } 
      return false;
    }
    
    public void connect() {
      int i = this.mState;
      if (i == 0 || i == 1) {
        this.mState = 2;
        this.mHandler.post(new Runnable() {
              public void run() {
                MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                if (mediaBrowserImplBase.mState == 0)
                  return; 
                mediaBrowserImplBase.mState = 2;
                if (!MediaBrowserCompat.DEBUG || mediaBrowserImplBase.mServiceConnection == null) {
                  if (mediaBrowserImplBase.mServiceBinderWrapper == null) {
                    if (mediaBrowserImplBase.mCallbacksMessenger == null) {
                      boolean bool2;
                      Intent intent = new Intent("android.media.browse.MediaBrowserService");
                      intent.setComponent(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
                      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase1 = MediaBrowserCompat.MediaBrowserImplBase.this;
                      mediaBrowserImplBase1.mServiceConnection = new MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection();
                      boolean bool1 = false;
                      try {
                        mediaBrowserImplBase1 = MediaBrowserCompat.MediaBrowserImplBase.this;
                        bool2 = mediaBrowserImplBase1.mContext.bindService(intent, mediaBrowserImplBase1.mServiceConnection, 1);
                      } catch (Exception exception) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Failed binding to service ");
                        stringBuilder3.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
                        Log.e("MediaBrowserCompat", stringBuilder3.toString());
                        bool2 = bool1;
                      } 
                      if (!bool2) {
                        MediaBrowserCompat.MediaBrowserImplBase.this.forceCloseConnection();
                        MediaBrowserCompat.MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                      } 
                      if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "connect...");
                        MediaBrowserCompat.MediaBrowserImplBase.this.dump();
                      } 
                      return;
                    } 
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("mCallbacksMessenger should be null. Instead it is ");
                    stringBuilder2.append(MediaBrowserCompat.MediaBrowserImplBase.this.mCallbacksMessenger);
                    throw new RuntimeException(stringBuilder2.toString());
                  } 
                  StringBuilder stringBuilder1 = new StringBuilder();
                  stringBuilder1.append("mServiceBinderWrapper should be null. Instead it is ");
                  stringBuilder1.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceBinderWrapper);
                  throw new RuntimeException(stringBuilder1.toString());
                } 
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mServiceConnection should be null. Instead it is ");
                stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
                throw new RuntimeException(stringBuilder.toString());
              }
            });
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("connect() called while neigther disconnecting nor disconnected (state=");
      stringBuilder.append(getStateLabel(this.mState));
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public void disconnect() {
      this.mState = 0;
      this.mHandler.post(new Runnable() {
            public void run() {
              MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
              Messenger messenger = mediaBrowserImplBase.mCallbacksMessenger;
              if (messenger != null)
                try {
                  mediaBrowserImplBase.mServiceBinderWrapper.disconnect(messenger);
                } catch (RemoteException remoteException) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("RemoteException during connect for ");
                  stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
                  Log.w("MediaBrowserCompat", stringBuilder.toString());
                }  
              mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
              int i = mediaBrowserImplBase.mState;
              mediaBrowserImplBase.forceCloseConnection();
              if (i != 0)
                MediaBrowserCompat.MediaBrowserImplBase.this.mState = i; 
              if (MediaBrowserCompat.DEBUG) {
                Log.d("MediaBrowserCompat", "disconnect...");
                MediaBrowserCompat.MediaBrowserImplBase.this.dump();
              } 
            }
          });
    }
    
    void dump() {
      Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  mServiceComponent=");
      stringBuilder.append(this.mServiceComponent);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mCallback=");
      stringBuilder.append(this.mCallback);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mRootHints=");
      stringBuilder.append(this.mRootHints);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mState=");
      stringBuilder.append(getStateLabel(this.mState));
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mServiceConnection=");
      stringBuilder.append(this.mServiceConnection);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mServiceBinderWrapper=");
      stringBuilder.append(this.mServiceBinderWrapper);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mCallbacksMessenger=");
      stringBuilder.append(this.mCallbacksMessenger);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mRootId=");
      stringBuilder.append(this.mRootId);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("  mMediaSessionToken=");
      stringBuilder.append(this.mMediaSessionToken);
      Log.d("MediaBrowserCompat", stringBuilder.toString());
    }
    
    void forceCloseConnection() {
      MediaServiceConnection mediaServiceConnection = this.mServiceConnection;
      if (mediaServiceConnection != null)
        this.mContext.unbindService(mediaServiceConnection); 
      this.mState = 1;
      this.mServiceConnection = null;
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
      this.mHandler.setCallbacksMessenger(null);
      this.mRootId = null;
      this.mMediaSessionToken = null;
    }
    
    @Nullable
    public Bundle getExtras() {
      if (isConnected())
        return this.mExtras; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getExtras() called while not connected (state=");
      stringBuilder.append(getStateLabel(this.mState));
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public void getItem(@NonNull final String mediaId, @NonNull final MediaBrowserCompat.ItemCallback cb) {
      if (!TextUtils.isEmpty(mediaId)) {
        if (cb != null) {
          if (!isConnected()) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post(new Runnable() {
                  public void run() {
                    cb.onError(mediaId);
                  }
                });
            return;
          } 
          MediaBrowserCompat.ItemReceiver itemReceiver = new MediaBrowserCompat.ItemReceiver(mediaId, cb, this.mHandler);
          try {
            this.mServiceBinderWrapper.getMediaItem(mediaId, itemReceiver, this.mCallbacksMessenger);
          } catch (RemoteException remoteException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Remote error getting media item: ");
            stringBuilder.append(mediaId);
            Log.i("MediaBrowserCompat", stringBuilder.toString());
            this.mHandler.post(new Runnable() {
                  public void run() {
                    cb.onError(mediaId);
                  }
                });
          } 
          return;
        } 
        throw new IllegalArgumentException("cb is null");
      } 
      throw new IllegalArgumentException("mediaId is empty");
    }
    
    @NonNull
    public String getRoot() {
      if (isConnected())
        return this.mRootId; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getRoot() called while not connected(state=");
      stringBuilder.append(getStateLabel(this.mState));
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    @NonNull
    public ComponentName getServiceComponent() {
      if (isConnected())
        return this.mServiceComponent; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getServiceComponent() called while not connected (state=");
      stringBuilder.append(this.mState);
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
      if (isConnected())
        return this.mMediaSessionToken; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getSessionToken() called while not connected(state=");
      stringBuilder.append(this.mState);
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public boolean isConnected() {
      boolean bool;
      if (this.mState == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onConnectionFailed(Messenger param1Messenger) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onConnectFailed for ");
      stringBuilder.append(this.mServiceComponent);
      Log.e("MediaBrowserCompat", stringBuilder.toString());
      if (!isCurrent(param1Messenger, "onConnectFailed"))
        return; 
      if (this.mState != 2) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("onConnect from service while mState=");
        stringBuilder1.append(getStateLabel(this.mState));
        stringBuilder1.append("... ignoring");
        Log.w("MediaBrowserCompat", stringBuilder1.toString());
        return;
      } 
      forceCloseConnection();
      this.mCallback.onConnectionFailed();
    }
    
    public void onLoadChildren(Messenger param1Messenger, String param1String, List<MediaBrowserCompat.MediaItem> param1List, Bundle param1Bundle) {
      StringBuilder stringBuilder;
      if (!isCurrent(param1Messenger, "onLoadChildren"))
        return; 
      boolean bool = MediaBrowserCompat.DEBUG;
      if (bool) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("onLoadChildren for ");
        stringBuilder.append(this.mServiceComponent);
        stringBuilder.append(" id=");
        stringBuilder.append(param1String);
        Log.d("MediaBrowserCompat", stringBuilder.toString());
      } 
      MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      if (subscription == null) {
        if (bool) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onLoadChildren for id that isn't subscribed id=");
          stringBuilder.append(param1String);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
        } 
        return;
      } 
      MediaBrowserCompat.SubscriptionCallback subscriptionCallback = stringBuilder.getCallback(this.mContext, param1Bundle);
      if (subscriptionCallback != null)
        if (param1Bundle == null) {
          if (param1List == null) {
            subscriptionCallback.onError(param1String);
          } else {
            subscriptionCallback.onChildrenLoaded(param1String, param1List);
          } 
        } else if (param1List == null) {
          subscriptionCallback.onError(param1String, param1Bundle);
        } else {
          subscriptionCallback.onChildrenLoaded(param1String, param1List, param1Bundle);
        }  
    }
    
    public void onServiceConnected(Messenger param1Messenger, String param1String, MediaSessionCompat.Token param1Token, Bundle param1Bundle) {
      if (!isCurrent(param1Messenger, "onConnect"))
        return; 
      if (this.mState != 2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnect from service while mState=");
        stringBuilder.append(getStateLabel(this.mState));
        stringBuilder.append("... ignoring");
        Log.w("MediaBrowserCompat", stringBuilder.toString());
        return;
      } 
      this.mRootId = param1String;
      this.mMediaSessionToken = param1Token;
      this.mExtras = param1Bundle;
      this.mState = 3;
      if (MediaBrowserCompat.DEBUG) {
        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
        dump();
      } 
      this.mCallback.onConnected();
      try {
        for (Map.Entry entry : this.mSubscriptions.entrySet()) {
          String str = (String)entry.getKey();
          MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)entry.getValue();
          List<MediaBrowserCompat.SubscriptionCallback> list = subscription.getCallbacks();
          List<Bundle> list1 = subscription.getOptionsList();
          for (byte b = 0; b < list.size(); b++)
            this.mServiceBinderWrapper.addSubscription(str, (list.get(b)).mToken, list1.get(b), this.mCallbacksMessenger); 
        } 
      } catch (RemoteException remoteException) {
        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
      } 
    }
    
    public void search(@NonNull final String query, final Bundle extras, @NonNull final MediaBrowserCompat.SearchCallback callback) {
      if (isConnected()) {
        MediaBrowserCompat.SearchResultReceiver searchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(query, extras, callback, this.mHandler);
        try {
          this.mServiceBinderWrapper.search(query, extras, searchResultReceiver, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Remote error searching items with query: ");
          stringBuilder1.append(query);
          Log.i("MediaBrowserCompat", stringBuilder1.toString(), (Throwable)remoteException);
          this.mHandler.post(new Runnable() {
                public void run() {
                  callback.onError(query, extras);
                }
              });
        } 
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("search() called while not connected (state=");
      stringBuilder.append(getStateLabel(this.mState));
      stringBuilder.append(")");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public void sendCustomAction(@NonNull final String action, final Bundle extras, @Nullable final MediaBrowserCompat.CustomActionCallback callback) {
      if (isConnected()) {
        MediaBrowserCompat.CustomActionResultReceiver customActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(action, extras, callback, this.mHandler);
        try {
          this.mServiceBinderWrapper.sendCustomAction(action, extras, customActionResultReceiver, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Remote error sending a custom action: action=");
          stringBuilder1.append(action);
          stringBuilder1.append(", extras=");
          stringBuilder1.append(extras);
          Log.i("MediaBrowserCompat", stringBuilder1.toString(), (Throwable)remoteException);
          if (callback != null)
            this.mHandler.post(new Runnable() {
                  public void run() {
                    callback.onError(action, extras, null);
                  }
                }); 
        } 
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot send a custom action (");
      stringBuilder.append(action);
      stringBuilder.append(") with ");
      stringBuilder.append("extras ");
      stringBuilder.append(extras);
      stringBuilder.append(" because the browser is not connected to the ");
      stringBuilder.append("service.");
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public void subscribe(@NonNull String param1String, Bundle param1Bundle, @NonNull MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      MediaBrowserCompat.Subscription subscription1 = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      MediaBrowserCompat.Subscription subscription2 = subscription1;
      if (subscription1 == null) {
        subscription2 = new MediaBrowserCompat.Subscription();
        this.mSubscriptions.put(param1String, subscription2);
      } 
      if (param1Bundle == null) {
        param1Bundle = null;
      } else {
        param1Bundle = new Bundle(param1Bundle);
      } 
      subscription2.putCallback(this.mContext, param1Bundle, param1SubscriptionCallback);
      if (isConnected())
        try {
          this.mServiceBinderWrapper.addSubscription(param1String, param1SubscriptionCallback.mToken, param1Bundle, this.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("addSubscription failed with RemoteException parentId=");
          stringBuilder.append(param1String);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
        }  
    }
    
    public void unsubscribe(@NonNull String param1String, MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      MediaBrowserCompat.Subscription subscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(param1String);
      if (subscription == null)
        return; 
      if (param1SubscriptionCallback == null) {
        try {
          if (isConnected())
            this.mServiceBinderWrapper.removeSubscription(param1String, null, this.mCallbacksMessenger); 
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("removeSubscription failed with RemoteException parentId=");
          stringBuilder.append(param1String);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
        } 
      } else {
        List<MediaBrowserCompat.SubscriptionCallback> list = subscription.getCallbacks();
        List<Bundle> list1 = subscription.getOptionsList();
        for (int i = list.size() - 1; i >= 0; i--) {
          if (list.get(i) == param1SubscriptionCallback) {
            if (isConnected())
              this.mServiceBinderWrapper.removeSubscription(param1String, param1SubscriptionCallback.mToken, this.mCallbacksMessenger); 
            list.remove(i);
            list1.remove(i);
          } 
        } 
      } 
      if (subscription.isEmpty() || param1SubscriptionCallback == null)
        this.mSubscriptions.remove(param1String); 
    }
    
    private class MediaServiceConnection implements ServiceConnection {
      private void postOrRun(Runnable param2Runnable) {
        if (Thread.currentThread() == MediaBrowserCompat.MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
          param2Runnable.run();
        } else {
          MediaBrowserCompat.MediaBrowserImplBase.this.mHandler.post(param2Runnable);
        } 
      }
      
      boolean isCurrent(String param2String) {
        MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        if (mediaBrowserImplBase.mServiceConnection == this) {
          int j = mediaBrowserImplBase.mState;
          if (j != 0 && j != 1)
            return true; 
        } 
        int i = mediaBrowserImplBase.mState;
        if (i != 0 && i != 1) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(param2String);
          stringBuilder.append(" for ");
          stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
          stringBuilder.append(" with mServiceConnection=");
          stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
          stringBuilder.append(" this=");
          stringBuilder.append(this);
          Log.i("MediaBrowserCompat", stringBuilder.toString());
        } 
        return false;
      }
      
      public void onServiceConnected(final ComponentName name, final IBinder binder) {
        postOrRun(new Runnable() {
              public void run() {
                boolean bool = MediaBrowserCompat.DEBUG;
                if (bool) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("MediaServiceConnection.onServiceConnected name=");
                  stringBuilder.append(name);
                  stringBuilder.append(" binder=");
                  stringBuilder.append(binder);
                  Log.d("MediaBrowserCompat", stringBuilder.toString());
                  MediaBrowserCompat.MediaBrowserImplBase.this.dump();
                } 
                if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceConnected"))
                  return; 
                MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                mediaBrowserImplBase.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, mediaBrowserImplBase.mRootHints);
                MediaBrowserCompat.MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger(MediaBrowserCompat.MediaBrowserImplBase.this.mHandler);
                mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                mediaBrowserImplBase.mHandler.setCallbacksMessenger(mediaBrowserImplBase.mCallbacksMessenger);
                MediaBrowserCompat.MediaBrowserImplBase.this.mState = 2;
                if (bool) {
                  try {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    MediaBrowserCompat.MediaBrowserImplBase.this.dump();
                    mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                    mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
                  } catch (RemoteException remoteException) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("RemoteException during connect for ");
                    stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
                    Log.w("MediaBrowserCompat", stringBuilder.toString());
                    if (MediaBrowserCompat.DEBUG) {
                      Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                      MediaBrowserCompat.MediaBrowserImplBase.this.dump();
                    } 
                  } 
                  return;
                } 
                mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
              }
            });
      }
      
      public void onServiceDisconnected(final ComponentName name) {
        postOrRun(new Runnable() {
              public void run() {
                if (MediaBrowserCompat.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
                  stringBuilder.append(name);
                  stringBuilder.append(" this=");
                  stringBuilder.append(this);
                  stringBuilder.append(" mServiceConnection=");
                  stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
                  Log.d("MediaBrowserCompat", stringBuilder.toString());
                  MediaBrowserCompat.MediaBrowserImplBase.this.dump();
                } 
                if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceDisconnected"))
                  return; 
                MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                mediaBrowserImplBase.mServiceBinderWrapper = null;
                mediaBrowserImplBase.mCallbacksMessenger = null;
                mediaBrowserImplBase.mHandler.setCallbacksMessenger(null);
                mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
                mediaBrowserImplBase.mState = 4;
                mediaBrowserImplBase.mCallback.onConnectionSuspended();
              }
            });
      }
    }
    
    class null implements Runnable {
      public void run() {
        boolean bool = MediaBrowserCompat.DEBUG;
        if (bool) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("MediaServiceConnection.onServiceConnected name=");
          stringBuilder.append(name);
          stringBuilder.append(" binder=");
          stringBuilder.append(binder);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
          MediaBrowserCompat.MediaBrowserImplBase.this.dump();
        } 
        if (!this.this$1.isCurrent("onServiceConnected"))
          return; 
        MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        mediaBrowserImplBase.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, mediaBrowserImplBase.mRootHints);
        MediaBrowserCompat.MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger(MediaBrowserCompat.MediaBrowserImplBase.this.mHandler);
        mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        mediaBrowserImplBase.mHandler.setCallbacksMessenger(mediaBrowserImplBase.mCallbacksMessenger);
        MediaBrowserCompat.MediaBrowserImplBase.this.mState = 2;
        if (bool) {
          try {
            Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
            MediaBrowserCompat.MediaBrowserImplBase.this.dump();
            mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
            mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
          } catch (RemoteException remoteException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RemoteException during connect for ");
            stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceComponent);
            Log.w("MediaBrowserCompat", stringBuilder.toString());
            if (MediaBrowserCompat.DEBUG) {
              Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
              MediaBrowserCompat.MediaBrowserImplBase.this.dump();
            } 
          } 
          return;
        } 
        mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
      }
    }
    
    class null implements Runnable {
      public void run() {
        if (MediaBrowserCompat.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
          stringBuilder.append(name);
          stringBuilder.append(" this=");
          stringBuilder.append(this);
          stringBuilder.append(" mServiceConnection=");
          stringBuilder.append(MediaBrowserCompat.MediaBrowserImplBase.this.mServiceConnection);
          Log.d("MediaBrowserCompat", stringBuilder.toString());
          MediaBrowserCompat.MediaBrowserImplBase.this.dump();
        } 
        if (!this.this$1.isCurrent("onServiceDisconnected"))
          return; 
        MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        mediaBrowserImplBase.mServiceBinderWrapper = null;
        mediaBrowserImplBase.mCallbacksMessenger = null;
        mediaBrowserImplBase.mHandler.setCallbacksMessenger(null);
        mediaBrowserImplBase = MediaBrowserCompat.MediaBrowserImplBase.this;
        mediaBrowserImplBase.mState = 4;
        mediaBrowserImplBase.mCallback.onConnectionSuspended();
      }
    }
  }
  
  class null implements Runnable {
    public void run() {
      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$0;
      if (mediaBrowserImplBase.mState == 0)
        return; 
      mediaBrowserImplBase.mState = 2;
      if (!MediaBrowserCompat.DEBUG || mediaBrowserImplBase.mServiceConnection == null) {
        if (mediaBrowserImplBase.mServiceBinderWrapper == null) {
          if (mediaBrowserImplBase.mCallbacksMessenger == null) {
            boolean bool2;
            Intent intent = new Intent("android.media.browse.MediaBrowserService");
            intent.setComponent(this.this$0.mServiceComponent);
            MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase1 = this.this$0;
            mediaBrowserImplBase1.mServiceConnection = new MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection();
            boolean bool1 = false;
            try {
              mediaBrowserImplBase1 = this.this$0;
              bool2 = mediaBrowserImplBase1.mContext.bindService(intent, mediaBrowserImplBase1.mServiceConnection, 1);
            } catch (Exception exception) {
              StringBuilder stringBuilder3 = new StringBuilder();
              stringBuilder3.append("Failed binding to service ");
              stringBuilder3.append(this.this$0.mServiceComponent);
              Log.e("MediaBrowserCompat", stringBuilder3.toString());
              bool2 = bool1;
            } 
            if (!bool2) {
              this.this$0.forceCloseConnection();
              this.this$0.mCallback.onConnectionFailed();
            } 
            if (MediaBrowserCompat.DEBUG) {
              Log.d("MediaBrowserCompat", "connect...");
              this.this$0.dump();
            } 
            return;
          } 
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("mCallbacksMessenger should be null. Instead it is ");
          stringBuilder2.append(this.this$0.mCallbacksMessenger);
          throw new RuntimeException(stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("mServiceBinderWrapper should be null. Instead it is ");
        stringBuilder1.append(this.this$0.mServiceBinderWrapper);
        throw new RuntimeException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("mServiceConnection should be null. Instead it is ");
      stringBuilder.append(this.this$0.mServiceConnection);
      throw new RuntimeException(stringBuilder.toString());
    }
  }
  
  class null implements Runnable {
    public void run() {
      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$0;
      Messenger messenger = mediaBrowserImplBase.mCallbacksMessenger;
      if (messenger != null)
        try {
          mediaBrowserImplBase.mServiceBinderWrapper.disconnect(messenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("RemoteException during connect for ");
          stringBuilder.append(this.this$0.mServiceComponent);
          Log.w("MediaBrowserCompat", stringBuilder.toString());
        }  
      mediaBrowserImplBase = this.this$0;
      int i = mediaBrowserImplBase.mState;
      mediaBrowserImplBase.forceCloseConnection();
      if (i != 0)
        this.this$0.mState = i; 
      if (MediaBrowserCompat.DEBUG) {
        Log.d("MediaBrowserCompat", "disconnect...");
        this.this$0.dump();
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      cb.onError(mediaId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      cb.onError(mediaId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(query, extras);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onError(action, extras, null);
    }
  }
  
  private class MediaServiceConnection implements ServiceConnection {
    private void postOrRun(Runnable param1Runnable) {
      if (Thread.currentThread() == this.this$0.mHandler.getLooper().getThread()) {
        param1Runnable.run();
      } else {
        this.this$0.mHandler.post(param1Runnable);
      } 
    }
    
    boolean isCurrent(String param1String) {
      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$0;
      if (mediaBrowserImplBase.mServiceConnection == this) {
        int j = mediaBrowserImplBase.mState;
        if (j != 0 && j != 1)
          return true; 
      } 
      int i = mediaBrowserImplBase.mState;
      if (i != 0 && i != 1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append(" for ");
        stringBuilder.append(this.this$0.mServiceComponent);
        stringBuilder.append(" with mServiceConnection=");
        stringBuilder.append(this.this$0.mServiceConnection);
        stringBuilder.append(" this=");
        stringBuilder.append(this);
        Log.i("MediaBrowserCompat", stringBuilder.toString());
      } 
      return false;
    }
    
    public void onServiceConnected(final ComponentName name, final IBinder binder) {
      postOrRun(new Runnable() {
            public void run() {
              boolean bool = MediaBrowserCompat.DEBUG;
              if (bool) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MediaServiceConnection.onServiceConnected name=");
                stringBuilder.append(name);
                stringBuilder.append(" binder=");
                stringBuilder.append(binder);
                Log.d("MediaBrowserCompat", stringBuilder.toString());
                this.this$1.this$0.dump();
              } 
              if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceConnected"))
                return; 
              MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$1.this$0;
              mediaBrowserImplBase.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, mediaBrowserImplBase.mRootHints);
              this.this$1.this$0.mCallbacksMessenger = new Messenger(this.this$1.this$0.mHandler);
              mediaBrowserImplBase = this.this$1.this$0;
              mediaBrowserImplBase.mHandler.setCallbacksMessenger(mediaBrowserImplBase.mCallbacksMessenger);
              this.this$1.this$0.mState = 2;
              if (bool) {
                try {
                  Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                  this.this$1.this$0.dump();
                  mediaBrowserImplBase = this.this$1.this$0;
                  mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
                } catch (RemoteException remoteException) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("RemoteException during connect for ");
                  stringBuilder.append(this.this$1.this$0.mServiceComponent);
                  Log.w("MediaBrowserCompat", stringBuilder.toString());
                  if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    this.this$1.this$0.dump();
                  } 
                } 
                return;
              } 
              mediaBrowserImplBase = this.this$1.this$0;
              mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
            }
          });
    }
    
    public void onServiceDisconnected(final ComponentName name) {
      postOrRun(new Runnable() {
            public void run() {
              if (MediaBrowserCompat.DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
                stringBuilder.append(name);
                stringBuilder.append(" this=");
                stringBuilder.append(this);
                stringBuilder.append(" mServiceConnection=");
                stringBuilder.append(this.this$1.this$0.mServiceConnection);
                Log.d("MediaBrowserCompat", stringBuilder.toString());
                this.this$1.this$0.dump();
              } 
              if (!MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.this.isCurrent("onServiceDisconnected"))
                return; 
              MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$1.this$0;
              mediaBrowserImplBase.mServiceBinderWrapper = null;
              mediaBrowserImplBase.mCallbacksMessenger = null;
              mediaBrowserImplBase.mHandler.setCallbacksMessenger(null);
              mediaBrowserImplBase = this.this$1.this$0;
              mediaBrowserImplBase.mState = 4;
              mediaBrowserImplBase.mCallback.onConnectionSuspended();
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      boolean bool = MediaBrowserCompat.DEBUG;
      if (bool) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MediaServiceConnection.onServiceConnected name=");
        stringBuilder.append(name);
        stringBuilder.append(" binder=");
        stringBuilder.append(binder);
        Log.d("MediaBrowserCompat", stringBuilder.toString());
        this.this$1.this$0.dump();
      } 
      if (!this.this$1.isCurrent("onServiceConnected"))
        return; 
      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$1.this$0;
      mediaBrowserImplBase.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(binder, mediaBrowserImplBase.mRootHints);
      this.this$1.this$0.mCallbacksMessenger = new Messenger(this.this$1.this$0.mHandler);
      mediaBrowserImplBase = this.this$1.this$0;
      mediaBrowserImplBase.mHandler.setCallbacksMessenger(mediaBrowserImplBase.mCallbacksMessenger);
      this.this$1.this$0.mState = 2;
      if (bool) {
        try {
          Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
          this.this$1.this$0.dump();
          mediaBrowserImplBase = this.this$1.this$0;
          mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("RemoteException during connect for ");
          stringBuilder.append(this.this$1.this$0.mServiceComponent);
          Log.w("MediaBrowserCompat", stringBuilder.toString());
          if (MediaBrowserCompat.DEBUG) {
            Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
            this.this$1.this$0.dump();
          } 
        } 
        return;
      } 
      mediaBrowserImplBase = this.this$1.this$0;
      mediaBrowserImplBase.mServiceBinderWrapper.connect(mediaBrowserImplBase.mContext, mediaBrowserImplBase.mCallbacksMessenger);
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (MediaBrowserCompat.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
        stringBuilder.append(name);
        stringBuilder.append(" this=");
        stringBuilder.append(this);
        stringBuilder.append(" mServiceConnection=");
        stringBuilder.append(this.this$1.this$0.mServiceConnection);
        Log.d("MediaBrowserCompat", stringBuilder.toString());
        this.this$1.this$0.dump();
      } 
      if (!this.this$1.isCurrent("onServiceDisconnected"))
        return; 
      MediaBrowserCompat.MediaBrowserImplBase mediaBrowserImplBase = this.this$1.this$0;
      mediaBrowserImplBase.mServiceBinderWrapper = null;
      mediaBrowserImplBase.mCallbacksMessenger = null;
      mediaBrowserImplBase.mHandler.setCallbacksMessenger(null);
      mediaBrowserImplBase = this.this$1.this$0;
      mediaBrowserImplBase.mState = 4;
      mediaBrowserImplBase.mCallback.onConnectionSuspended();
    }
  }
  
  static interface MediaBrowserServiceCallbackImpl {
    void onConnectionFailed(Messenger param1Messenger);
    
    void onLoadChildren(Messenger param1Messenger, String param1String, List param1List, Bundle param1Bundle);
    
    void onServiceConnected(Messenger param1Messenger, String param1String, MediaSessionCompat.Token param1Token, Bundle param1Bundle);
  }
  
  public static class MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() {
        public MediaBrowserCompat.MediaItem createFromParcel(Parcel param2Parcel) {
          return new MediaBrowserCompat.MediaItem(param2Parcel);
        }
        
        public MediaBrowserCompat.MediaItem[] newArray(int param2Int) {
          return new MediaBrowserCompat.MediaItem[param2Int];
        }
      };
    
    public static final int FLAG_BROWSABLE = 1;
    
    public static final int FLAG_PLAYABLE = 2;
    
    private final MediaDescriptionCompat mDescription;
    
    private final int mFlags;
    
    MediaItem(Parcel param1Parcel) {
      this.mFlags = param1Parcel.readInt();
      this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(param1Parcel);
    }
    
    public MediaItem(@NonNull MediaDescriptionCompat param1MediaDescriptionCompat, int param1Int) {
      if (param1MediaDescriptionCompat != null) {
        if (!TextUtils.isEmpty(param1MediaDescriptionCompat.getMediaId())) {
          this.mFlags = param1Int;
          this.mDescription = param1MediaDescriptionCompat;
          return;
        } 
        throw new IllegalArgumentException("description must have a non-empty media id");
      } 
      throw new IllegalArgumentException("description cannot be null");
    }
    
    public static MediaItem fromMediaItem(Object param1Object) {
      if (param1Object == null || Build.VERSION.SDK_INT < 21)
        return null; 
      int i = MediaBrowserCompatApi21.MediaItem.getFlags(param1Object);
      return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(param1Object)), i);
    }
    
    public static List<MediaItem> fromMediaItemList(List<?> param1List) {
      if (param1List == null || Build.VERSION.SDK_INT < 21)
        return null; 
      ArrayList<MediaItem> arrayList = new ArrayList(param1List.size());
      Iterator<?> iterator = param1List.iterator();
      while (iterator.hasNext())
        arrayList.add(fromMediaItem(iterator.next())); 
      return arrayList;
    }
    
    public int describeContents() {
      return 0;
    }
    
    @NonNull
    public MediaDescriptionCompat getDescription() {
      return this.mDescription;
    }
    
    public int getFlags() {
      return this.mFlags;
    }
    
    @Nullable
    public String getMediaId() {
      return this.mDescription.getMediaId();
    }
    
    public boolean isBrowsable() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean isPlayable() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder("MediaItem{");
      stringBuilder.append("mFlags=");
      stringBuilder.append(this.mFlags);
      stringBuilder.append(", mDescription=");
      stringBuilder.append(this.mDescription);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mFlags);
      this.mDescription.writeToParcel(param1Parcel, param1Int);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface Flags {}
  }
  
  static final class null implements Parcelable.Creator<MediaItem> {
    public MediaBrowserCompat.MediaItem createFromParcel(Parcel param1Parcel) {
      return new MediaBrowserCompat.MediaItem(param1Parcel);
    }
    
    public MediaBrowserCompat.MediaItem[] newArray(int param1Int) {
      return new MediaBrowserCompat.MediaItem[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface Flags {}
  
  public static abstract class SearchCallback {
    public void onError(@NonNull String param1String, Bundle param1Bundle) {}
    
    public void onSearchResult(@NonNull String param1String, Bundle param1Bundle, @NonNull List<MediaBrowserCompat.MediaItem> param1List) {}
  }
  
  private static class SearchResultReceiver extends ResultReceiver {
    private final MediaBrowserCompat.SearchCallback mCallback;
    
    private final Bundle mExtras;
    
    private final String mQuery;
    
    SearchResultReceiver(String param1String, Bundle param1Bundle, MediaBrowserCompat.SearchCallback param1SearchCallback, Handler param1Handler) {
      super(param1Handler);
      this.mQuery = param1String;
      this.mExtras = param1Bundle;
      this.mCallback = param1SearchCallback;
    }
    
    protected void onReceiveResult(int param1Int, Bundle param1Bundle) {
      ArrayList<MediaBrowserCompat.MediaItem> arrayList;
      if (param1Bundle != null)
        param1Bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader()); 
      if (param1Int != 0 || param1Bundle == null || !param1Bundle.containsKey("search_results")) {
        this.mCallback.onError(this.mQuery, this.mExtras);
        return;
      } 
      Parcelable[] arrayOfParcelable = param1Bundle.getParcelableArray("search_results");
      param1Bundle = null;
      if (arrayOfParcelable != null) {
        ArrayList<MediaBrowserCompat.MediaItem> arrayList1 = new ArrayList();
        int i = arrayOfParcelable.length;
        param1Int = 0;
        while (true) {
          arrayList = arrayList1;
          if (param1Int < i) {
            arrayList1.add((MediaBrowserCompat.MediaItem)arrayOfParcelable[param1Int]);
            param1Int++;
            continue;
          } 
          break;
        } 
      } 
      this.mCallback.onSearchResult(this.mQuery, this.mExtras, arrayList);
    }
  }
  
  private static class ServiceBinderWrapper {
    private Messenger mMessenger;
    
    private Bundle mRootHints;
    
    public ServiceBinderWrapper(IBinder param1IBinder, Bundle param1Bundle) {
      this.mMessenger = new Messenger(param1IBinder);
      this.mRootHints = param1Bundle;
    }
    
    private void sendRequest(int param1Int, Bundle param1Bundle, Messenger param1Messenger) throws RemoteException {
      Message message = Message.obtain();
      message.what = param1Int;
      message.arg1 = 1;
      message.setData(param1Bundle);
      message.replyTo = param1Messenger;
      this.mMessenger.send(message);
    }
    
    void addSubscription(String param1String, IBinder param1IBinder, Bundle param1Bundle, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_media_item_id", param1String);
      BundleCompat.putBinder(bundle, "data_callback_token", param1IBinder);
      bundle.putBundle("data_options", param1Bundle);
      sendRequest(3, bundle, param1Messenger);
    }
    
    void connect(Context param1Context, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_package_name", param1Context.getPackageName());
      bundle.putBundle("data_root_hints", this.mRootHints);
      sendRequest(1, bundle, param1Messenger);
    }
    
    void disconnect(Messenger param1Messenger) throws RemoteException {
      sendRequest(2, null, param1Messenger);
    }
    
    void getMediaItem(String param1String, ResultReceiver param1ResultReceiver, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_media_item_id", param1String);
      bundle.putParcelable("data_result_receiver", (Parcelable)param1ResultReceiver);
      sendRequest(5, bundle, param1Messenger);
    }
    
    void registerCallbackMessenger(Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putBundle("data_root_hints", this.mRootHints);
      sendRequest(6, bundle, param1Messenger);
    }
    
    void removeSubscription(String param1String, IBinder param1IBinder, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_media_item_id", param1String);
      BundleCompat.putBinder(bundle, "data_callback_token", param1IBinder);
      sendRequest(4, bundle, param1Messenger);
    }
    
    void search(String param1String, Bundle param1Bundle, ResultReceiver param1ResultReceiver, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_search_query", param1String);
      bundle.putBundle("data_search_extras", param1Bundle);
      bundle.putParcelable("data_result_receiver", (Parcelable)param1ResultReceiver);
      sendRequest(8, bundle, param1Messenger);
    }
    
    void sendCustomAction(String param1String, Bundle param1Bundle, ResultReceiver param1ResultReceiver, Messenger param1Messenger) throws RemoteException {
      Bundle bundle = new Bundle();
      bundle.putString("data_custom_action", param1String);
      bundle.putBundle("data_custom_action_extras", param1Bundle);
      bundle.putParcelable("data_result_receiver", (Parcelable)param1ResultReceiver);
      sendRequest(9, bundle, param1Messenger);
    }
    
    void unregisterCallbackMessenger(Messenger param1Messenger) throws RemoteException {
      sendRequest(7, null, param1Messenger);
    }
  }
  
  private static class Subscription {
    private final List<MediaBrowserCompat.SubscriptionCallback> mCallbacks = new ArrayList<MediaBrowserCompat.SubscriptionCallback>();
    
    private final List<Bundle> mOptionsList = new ArrayList<Bundle>();
    
    public MediaBrowserCompat.SubscriptionCallback getCallback(Context param1Context, Bundle param1Bundle) {
      if (param1Bundle != null)
        param1Bundle.setClassLoader(param1Context.getClassLoader()); 
      for (byte b = 0; b < this.mOptionsList.size(); b++) {
        if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(b), param1Bundle))
          return this.mCallbacks.get(b); 
      } 
      return null;
    }
    
    public List<MediaBrowserCompat.SubscriptionCallback> getCallbacks() {
      return this.mCallbacks;
    }
    
    public List<Bundle> getOptionsList() {
      return this.mOptionsList;
    }
    
    public boolean isEmpty() {
      return this.mCallbacks.isEmpty();
    }
    
    public void putCallback(Context param1Context, Bundle param1Bundle, MediaBrowserCompat.SubscriptionCallback param1SubscriptionCallback) {
      if (param1Bundle != null)
        param1Bundle.setClassLoader(param1Context.getClassLoader()); 
      for (byte b = 0; b < this.mOptionsList.size(); b++) {
        if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(b), param1Bundle)) {
          this.mCallbacks.set(b, param1SubscriptionCallback);
          return;
        } 
      } 
      this.mCallbacks.add(param1SubscriptionCallback);
      this.mOptionsList.add(param1Bundle);
    }
  }
  
  public static abstract class SubscriptionCallback {
    private final Object mSubscriptionCallbackObj;
    
    WeakReference<MediaBrowserCompat.Subscription> mSubscriptionRef;
    
    private final IBinder mToken;
    
    public SubscriptionCallback() {
      int i = Build.VERSION.SDK_INT;
      if (i >= 26) {
        this.mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback(new StubApi24());
        this.mToken = null;
      } else if (i >= 21) {
        this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
        this.mToken = (IBinder)new Binder();
      } else {
        this.mSubscriptionCallbackObj = null;
        this.mToken = (IBinder)new Binder();
      } 
    }
    
    private void setSubscription(MediaBrowserCompat.Subscription param1Subscription) {
      this.mSubscriptionRef = new WeakReference<MediaBrowserCompat.Subscription>(param1Subscription);
    }
    
    public void onChildrenLoaded(@NonNull String param1String, @NonNull List<MediaBrowserCompat.MediaItem> param1List) {}
    
    public void onChildrenLoaded(@NonNull String param1String, @NonNull List<MediaBrowserCompat.MediaItem> param1List, @NonNull Bundle param1Bundle) {}
    
    public void onError(@NonNull String param1String) {}
    
    public void onError(@NonNull String param1String, @NonNull Bundle param1Bundle) {}
    
    private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
      List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> param2List, Bundle param2Bundle) {
        if (param2List == null)
          return null; 
        int i = param2Bundle.getInt("android.media.browse.extra.PAGE", -1);
        int j = param2Bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if (i == -1 && j == -1)
          return param2List; 
        int k = j * i;
        int m = k + j;
        if (i < 0 || j < 1 || k >= param2List.size())
          return Collections.EMPTY_LIST; 
        j = m;
        if (m > param2List.size())
          j = param2List.size(); 
        return param2List.subList(k, j);
      }
      
      public void onChildrenLoaded(@NonNull String param2String, List<?> param2List) {
        MediaBrowserCompat.Subscription subscription;
        WeakReference<MediaBrowserCompat.Subscription> weakReference = MediaBrowserCompat.SubscriptionCallback.this.mSubscriptionRef;
        if (weakReference == null) {
          weakReference = null;
        } else {
          subscription = weakReference.get();
        } 
        if (subscription == null) {
          MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(param2String, MediaBrowserCompat.MediaItem.fromMediaItemList(param2List));
        } else {
          List<MediaBrowserCompat.MediaItem> list1 = MediaBrowserCompat.MediaItem.fromMediaItemList(param2List);
          param2List = subscription.getCallbacks();
          List<Bundle> list = subscription.getOptionsList();
          for (byte b = 0; b < param2List.size(); b++) {
            Bundle bundle = list.get(b);
            if (bundle == null) {
              MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(param2String, list1);
            } else {
              MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(param2String, applyOptions(list1, bundle), bundle);
            } 
          } 
        } 
      }
      
      public void onError(@NonNull String param2String) {
        MediaBrowserCompat.SubscriptionCallback.this.onError(param2String);
      }
    }
    
    private class StubApi24 extends StubApi21 implements MediaBrowserCompatApi24.SubscriptionCallback {
      public void onChildrenLoaded(@NonNull String param2String, List<?> param2List, @NonNull Bundle param2Bundle) {
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(param2String, MediaBrowserCompat.MediaItem.fromMediaItemList(param2List), param2Bundle);
      }
      
      public void onError(@NonNull String param2String, @NonNull Bundle param2Bundle) {
        MediaBrowserCompat.SubscriptionCallback.this.onError(param2String, param2Bundle);
      }
    }
  }
  
  private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
    List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> param1List, Bundle param1Bundle) {
      if (param1List == null)
        return null; 
      int i = param1Bundle.getInt("android.media.browse.extra.PAGE", -1);
      int j = param1Bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      if (i == -1 && j == -1)
        return param1List; 
      int k = j * i;
      int m = k + j;
      if (i < 0 || j < 1 || k >= param1List.size())
        return Collections.EMPTY_LIST; 
      j = m;
      if (m > param1List.size())
        j = param1List.size(); 
      return param1List.subList(k, j);
    }
    
    public void onChildrenLoaded(@NonNull String param1String, List<?> param1List) {
      MediaBrowserCompat.Subscription subscription;
      WeakReference<MediaBrowserCompat.Subscription> weakReference = this.this$0.mSubscriptionRef;
      if (weakReference == null) {
        weakReference = null;
      } else {
        subscription = weakReference.get();
      } 
      if (subscription == null) {
        this.this$0.onChildrenLoaded(param1String, MediaBrowserCompat.MediaItem.fromMediaItemList(param1List));
      } else {
        List<MediaBrowserCompat.MediaItem> list1 = MediaBrowserCompat.MediaItem.fromMediaItemList(param1List);
        param1List = subscription.getCallbacks();
        List<Bundle> list = subscription.getOptionsList();
        for (byte b = 0; b < param1List.size(); b++) {
          Bundle bundle = list.get(b);
          if (bundle == null) {
            this.this$0.onChildrenLoaded(param1String, list1);
          } else {
            this.this$0.onChildrenLoaded(param1String, applyOptions(list1, bundle), bundle);
          } 
        } 
      } 
    }
    
    public void onError(@NonNull String param1String) {
      this.this$0.onError(param1String);
    }
  }
  
  private class StubApi24 extends SubscriptionCallback.StubApi21 implements MediaBrowserCompatApi24.SubscriptionCallback {
    StubApi24() {
      super((MediaBrowserCompat.SubscriptionCallback)MediaBrowserCompat.this);
    }
    
    public void onChildrenLoaded(@NonNull String param1String, List<?> param1List, @NonNull Bundle param1Bundle) {
      this.this$0.onChildrenLoaded(param1String, MediaBrowserCompat.MediaItem.fromMediaItemList(param1List), param1Bundle);
    }
    
    public void onError(@NonNull String param1String, @NonNull Bundle param1Bundle) {
      this.this$0.onError(param1String, param1Bundle);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/media/MediaBrowserCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */