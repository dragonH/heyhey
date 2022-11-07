package android.support.v7.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ObjectsCompat;
import android.util.Log;
import java.util.Iterator;

public class RemotePlaybackClient {
  static final boolean DEBUG = Log.isLoggable("RemotePlaybackClient", 3);
  
  static final String TAG = "RemotePlaybackClient";
  
  private final ActionReceiver mActionReceiver;
  
  private final Context mContext;
  
  private final PendingIntent mItemStatusPendingIntent;
  
  private final PendingIntent mMessagePendingIntent;
  
  OnMessageReceivedListener mOnMessageReceivedListener;
  
  private final MediaRouter.RouteInfo mRoute;
  
  private boolean mRouteSupportsMessaging;
  
  private boolean mRouteSupportsQueuing;
  
  private boolean mRouteSupportsRemotePlayback;
  
  private boolean mRouteSupportsSessionManagement;
  
  String mSessionId;
  
  private final PendingIntent mSessionStatusPendingIntent;
  
  StatusCallback mStatusCallback;
  
  public RemotePlaybackClient(Context paramContext, MediaRouter.RouteInfo paramRouteInfo) {
    if (paramContext != null) {
      if (paramRouteInfo != null) {
        this.mContext = paramContext;
        this.mRoute = paramRouteInfo;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
        intentFilter.addAction("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
        intentFilter.addAction("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED");
        ActionReceiver actionReceiver = new ActionReceiver();
        this.mActionReceiver = actionReceiver;
        paramContext.registerReceiver(actionReceiver, intentFilter);
        Intent intent = new Intent("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED");
        intent.setPackage(paramContext.getPackageName());
        this.mItemStatusPendingIntent = PendingIntent.getBroadcast(paramContext, 0, intent, 0);
        intent = new Intent("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED");
        intent.setPackage(paramContext.getPackageName());
        this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(paramContext, 0, intent, 0);
        intent = new Intent("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED");
        intent.setPackage(paramContext.getPackageName());
        this.mMessagePendingIntent = PendingIntent.getBroadcast(paramContext, 0, intent, 0);
        detectFeatures();
        return;
      } 
      throw new IllegalArgumentException("route must not be null");
    } 
    throw new IllegalArgumentException("context must not be null");
  }
  
  static String bundleToString(Bundle paramBundle) {
    if (paramBundle != null) {
      paramBundle.size();
      return paramBundle.toString();
    } 
    return "null";
  }
  
  private void detectFeatures() {
    boolean bool = routeSupportsAction("android.media.intent.action.PLAY");
    boolean bool1 = true;
    if (bool && routeSupportsAction("android.media.intent.action.SEEK") && routeSupportsAction("android.media.intent.action.GET_STATUS") && routeSupportsAction("android.media.intent.action.PAUSE") && routeSupportsAction("android.media.intent.action.RESUME") && routeSupportsAction("android.media.intent.action.STOP")) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mRouteSupportsRemotePlayback = bool;
    if (bool && routeSupportsAction("android.media.intent.action.ENQUEUE") && routeSupportsAction("android.media.intent.action.REMOVE")) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mRouteSupportsQueuing = bool;
    if (this.mRouteSupportsRemotePlayback && routeSupportsAction("android.media.intent.action.START_SESSION") && routeSupportsAction("android.media.intent.action.GET_SESSION_STATUS") && routeSupportsAction("android.media.intent.action.END_SESSION")) {
      bool = bool1;
    } else {
      bool = false;
    } 
    this.mRouteSupportsSessionManagement = bool;
    this.mRouteSupportsMessaging = doesRouteSupportMessaging();
  }
  
  private boolean doesRouteSupportMessaging() {
    Iterator<IntentFilter> iterator = this.mRoute.getControlFilters().iterator();
    while (iterator.hasNext()) {
      if (((IntentFilter)iterator.next()).hasAction("android.media.intent.action.SEND_MESSAGE"))
        return true; 
    } 
    return false;
  }
  
  static String inferMissingResult(String paramString1, String paramString2) {
    return (paramString2 == null) ? paramString1 : ((paramString1 == null || paramString1.equals(paramString2)) ? paramString2 : null);
  }
  
  private static void logRequest(Intent paramIntent) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Sending request: ");
      stringBuilder.append(paramIntent);
      Log.d("RemotePlaybackClient", stringBuilder.toString());
    } 
  }
  
  private void performItemAction(final Intent intent, final String sessionId, final String itemId, Bundle paramBundle, final ItemActionCallback callback) {
    intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
    if (sessionId != null)
      intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId); 
    if (itemId != null)
      intent.putExtra("android.media.intent.extra.ITEM_ID", itemId); 
    if (paramBundle != null)
      intent.putExtras(paramBundle); 
    logRequest(intent);
    this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
          public void onError(String param1String, Bundle param1Bundle) {
            RemotePlaybackClient.this.handleError(intent, callback, param1String, param1Bundle);
          }
          
          public void onResult(Bundle param1Bundle) {
            if (param1Bundle != null) {
              String str1 = RemotePlaybackClient.inferMissingResult(sessionId, param1Bundle.getString("android.media.intent.extra.SESSION_ID"));
              MediaSessionStatus mediaSessionStatus = MediaSessionStatus.fromBundle(param1Bundle.getBundle("android.media.intent.extra.SESSION_STATUS"));
              String str2 = RemotePlaybackClient.inferMissingResult(itemId, param1Bundle.getString("android.media.intent.extra.ITEM_ID"));
              MediaItemStatus mediaItemStatus = MediaItemStatus.fromBundle(param1Bundle.getBundle("android.media.intent.extra.ITEM_STATUS"));
              RemotePlaybackClient.this.adoptSession(str1);
              if (str1 != null && str2 != null && mediaItemStatus != null) {
                if (RemotePlaybackClient.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Received result from ");
                  stringBuilder.append(intent.getAction());
                  stringBuilder.append(": data=");
                  stringBuilder.append(RemotePlaybackClient.bundleToString(param1Bundle));
                  stringBuilder.append(", sessionId=");
                  stringBuilder.append(str1);
                  stringBuilder.append(", sessionStatus=");
                  stringBuilder.append(mediaSessionStatus);
                  stringBuilder.append(", itemId=");
                  stringBuilder.append(str2);
                  stringBuilder.append(", itemStatus=");
                  stringBuilder.append(mediaItemStatus);
                  Log.d("RemotePlaybackClient", stringBuilder.toString());
                } 
                callback.onResult(param1Bundle, str1, mediaSessionStatus, str2, mediaItemStatus);
                return;
              } 
            } 
            RemotePlaybackClient.this.handleInvalidResult(intent, callback, param1Bundle);
          }
        });
  }
  
  private void performSessionAction(final Intent intent, final String sessionId, Bundle paramBundle, final SessionActionCallback callback) {
    intent.addCategory("android.media.intent.category.REMOTE_PLAYBACK");
    if (sessionId != null)
      intent.putExtra("android.media.intent.extra.SESSION_ID", sessionId); 
    if (paramBundle != null)
      intent.putExtras(paramBundle); 
    logRequest(intent);
    this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() {
          public void onError(String param1String, Bundle param1Bundle) {
            RemotePlaybackClient.this.handleError(intent, callback, param1String, param1Bundle);
          }
          
          public void onResult(Bundle param1Bundle) {
            if (param1Bundle != null) {
              String str = RemotePlaybackClient.inferMissingResult(sessionId, param1Bundle.getString("android.media.intent.extra.SESSION_ID"));
              MediaSessionStatus mediaSessionStatus = MediaSessionStatus.fromBundle(param1Bundle.getBundle("android.media.intent.extra.SESSION_STATUS"));
              RemotePlaybackClient.this.adoptSession(str);
              if (str != null) {
                if (RemotePlaybackClient.DEBUG) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Received result from ");
                  stringBuilder.append(intent.getAction());
                  stringBuilder.append(": data=");
                  stringBuilder.append(RemotePlaybackClient.bundleToString(param1Bundle));
                  stringBuilder.append(", sessionId=");
                  stringBuilder.append(str);
                  stringBuilder.append(", sessionStatus=");
                  stringBuilder.append(mediaSessionStatus);
                  Log.d("RemotePlaybackClient", stringBuilder.toString());
                } 
                try {
                  callback.onResult(param1Bundle, str, mediaSessionStatus);
                  return;
                } finally {
                  if (intent.getAction().equals("android.media.intent.action.END_SESSION") && str.equals(RemotePlaybackClient.this.mSessionId))
                    RemotePlaybackClient.this.setSessionId(null); 
                } 
              } 
            } 
            RemotePlaybackClient.this.handleInvalidResult(intent, callback, param1Bundle);
          }
        });
  }
  
  private void playOrEnqueue(Uri paramUri, String paramString1, Bundle paramBundle1, long paramLong, Bundle paramBundle2, ItemActionCallback paramItemActionCallback, String paramString2) {
    if (paramUri != null) {
      throwIfRemotePlaybackNotSupported();
      if (paramString2.equals("android.media.intent.action.ENQUEUE"))
        throwIfQueuingNotSupported(); 
      Intent intent = new Intent(paramString2);
      intent.setDataAndType(paramUri, paramString1);
      intent.putExtra("android.media.intent.extra.ITEM_STATUS_UPDATE_RECEIVER", (Parcelable)this.mItemStatusPendingIntent);
      if (paramBundle1 != null)
        intent.putExtra("android.media.intent.extra.ITEM_METADATA", paramBundle1); 
      if (paramLong != 0L)
        intent.putExtra("android.media.intent.extra.ITEM_POSITION", paramLong); 
      performItemAction(intent, this.mSessionId, null, paramBundle2, paramItemActionCallback);
      return;
    } 
    throw new IllegalArgumentException("contentUri must not be null");
  }
  
  private boolean routeSupportsAction(String paramString) {
    return this.mRoute.supportsControlAction("android.media.intent.category.REMOTE_PLAYBACK", paramString);
  }
  
  private void throwIfMessageNotSupported() {
    if (this.mRouteSupportsMessaging)
      return; 
    throw new UnsupportedOperationException("The route does not support message.");
  }
  
  private void throwIfNoCurrentSession() {
    if (this.mSessionId != null)
      return; 
    throw new IllegalStateException("There is no current session.");
  }
  
  private void throwIfQueuingNotSupported() {
    if (this.mRouteSupportsQueuing)
      return; 
    throw new UnsupportedOperationException("The route does not support queuing.");
  }
  
  private void throwIfRemotePlaybackNotSupported() {
    if (this.mRouteSupportsRemotePlayback)
      return; 
    throw new UnsupportedOperationException("The route does not support remote playback.");
  }
  
  private void throwIfSessionManagementNotSupported() {
    if (this.mRouteSupportsSessionManagement)
      return; 
    throw new UnsupportedOperationException("The route does not support session management.");
  }
  
  void adoptSession(String paramString) {
    if (paramString != null)
      setSessionId(paramString); 
  }
  
  public void endSession(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfSessionManagementNotSupported();
    throwIfNoCurrentSession();
    performSessionAction(new Intent("android.media.intent.action.END_SESSION"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public void enqueue(Uri paramUri, String paramString, Bundle paramBundle1, long paramLong, Bundle paramBundle2, ItemActionCallback paramItemActionCallback) {
    playOrEnqueue(paramUri, paramString, paramBundle1, paramLong, paramBundle2, paramItemActionCallback, "android.media.intent.action.ENQUEUE");
  }
  
  public String getSessionId() {
    return this.mSessionId;
  }
  
  public void getSessionStatus(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfSessionManagementNotSupported();
    throwIfNoCurrentSession();
    performSessionAction(new Intent("android.media.intent.action.GET_SESSION_STATUS"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public void getStatus(String paramString, Bundle paramBundle, ItemActionCallback paramItemActionCallback) {
    if (paramString != null) {
      throwIfNoCurrentSession();
      performItemAction(new Intent("android.media.intent.action.GET_STATUS"), this.mSessionId, paramString, paramBundle, paramItemActionCallback);
      return;
    } 
    throw new IllegalArgumentException("itemId must not be null");
  }
  
  void handleError(Intent paramIntent, ActionCallback paramActionCallback, String paramString, Bundle paramBundle) {
    int i = 0;
    if (paramBundle != null)
      i = paramBundle.getInt("android.media.intent.extra.ERROR_CODE", 0); 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Received error from ");
      stringBuilder.append(paramIntent.getAction());
      stringBuilder.append(": error=");
      stringBuilder.append(paramString);
      stringBuilder.append(", code=");
      stringBuilder.append(i);
      stringBuilder.append(", data=");
      stringBuilder.append(bundleToString(paramBundle));
      Log.w("RemotePlaybackClient", stringBuilder.toString());
    } 
    paramActionCallback.onError(paramString, i, paramBundle);
  }
  
  void handleInvalidResult(Intent paramIntent, ActionCallback paramActionCallback, Bundle paramBundle) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Received invalid result data from ");
    stringBuilder.append(paramIntent.getAction());
    stringBuilder.append(": data=");
    stringBuilder.append(bundleToString(paramBundle));
    Log.w("RemotePlaybackClient", stringBuilder.toString());
    paramActionCallback.onError(null, 0, paramBundle);
  }
  
  public boolean hasSession() {
    boolean bool;
    if (this.mSessionId != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isMessagingSupported() {
    return this.mRouteSupportsMessaging;
  }
  
  public boolean isQueuingSupported() {
    return this.mRouteSupportsQueuing;
  }
  
  public boolean isRemotePlaybackSupported() {
    return this.mRouteSupportsRemotePlayback;
  }
  
  public boolean isSessionManagementSupported() {
    return this.mRouteSupportsSessionManagement;
  }
  
  public void pause(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfNoCurrentSession();
    performSessionAction(new Intent("android.media.intent.action.PAUSE"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public void play(Uri paramUri, String paramString, Bundle paramBundle1, long paramLong, Bundle paramBundle2, ItemActionCallback paramItemActionCallback) {
    playOrEnqueue(paramUri, paramString, paramBundle1, paramLong, paramBundle2, paramItemActionCallback, "android.media.intent.action.PLAY");
  }
  
  public void release() {
    this.mContext.unregisterReceiver(this.mActionReceiver);
  }
  
  public void remove(String paramString, Bundle paramBundle, ItemActionCallback paramItemActionCallback) {
    if (paramString != null) {
      throwIfQueuingNotSupported();
      throwIfNoCurrentSession();
      performItemAction(new Intent("android.media.intent.action.REMOVE"), this.mSessionId, paramString, paramBundle, paramItemActionCallback);
      return;
    } 
    throw new IllegalArgumentException("itemId must not be null");
  }
  
  public void resume(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfNoCurrentSession();
    performSessionAction(new Intent("android.media.intent.action.RESUME"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public void seek(String paramString, long paramLong, Bundle paramBundle, ItemActionCallback paramItemActionCallback) {
    if (paramString != null) {
      throwIfNoCurrentSession();
      Intent intent = new Intent("android.media.intent.action.SEEK");
      intent.putExtra("android.media.intent.extra.ITEM_POSITION", paramLong);
      performItemAction(intent, this.mSessionId, paramString, paramBundle, paramItemActionCallback);
      return;
    } 
    throw new IllegalArgumentException("itemId must not be null");
  }
  
  public void sendMessage(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfNoCurrentSession();
    throwIfMessageNotSupported();
    performSessionAction(new Intent("android.media.intent.action.SEND_MESSAGE"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public void setOnMessageReceivedListener(OnMessageReceivedListener paramOnMessageReceivedListener) {
    this.mOnMessageReceivedListener = paramOnMessageReceivedListener;
  }
  
  public void setSessionId(String paramString) {
    if (!ObjectsCompat.equals(this.mSessionId, paramString)) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Session id is now: ");
        stringBuilder.append(paramString);
        Log.d("RemotePlaybackClient", stringBuilder.toString());
      } 
      this.mSessionId = paramString;
      StatusCallback statusCallback = this.mStatusCallback;
      if (statusCallback != null)
        statusCallback.onSessionChanged(paramString); 
    } 
  }
  
  public void setStatusCallback(StatusCallback paramStatusCallback) {
    this.mStatusCallback = paramStatusCallback;
  }
  
  public void startSession(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfSessionManagementNotSupported();
    Intent intent = new Intent("android.media.intent.action.START_SESSION");
    intent.putExtra("android.media.intent.extra.SESSION_STATUS_UPDATE_RECEIVER", (Parcelable)this.mSessionStatusPendingIntent);
    if (this.mRouteSupportsMessaging)
      intent.putExtra("android.media.intent.extra.MESSAGE_RECEIVER", (Parcelable)this.mMessagePendingIntent); 
    performSessionAction(intent, null, paramBundle, paramSessionActionCallback);
  }
  
  public void stop(Bundle paramBundle, SessionActionCallback paramSessionActionCallback) {
    throwIfNoCurrentSession();
    performSessionAction(new Intent("android.media.intent.action.STOP"), this.mSessionId, paramBundle, paramSessionActionCallback);
  }
  
  public static abstract class ActionCallback {
    public void onError(String param1String, int param1Int, Bundle param1Bundle) {}
  }
  
  private final class ActionReceiver extends BroadcastReceiver {
    public static final String ACTION_ITEM_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED";
    
    public static final String ACTION_MESSAGE_RECEIVED = "android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED";
    
    public static final String ACTION_SESSION_STATUS_CHANGED = "android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED";
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      StringBuilder stringBuilder;
      MediaItemStatus mediaItemStatus;
      String str1 = param1Intent.getStringExtra("android.media.intent.extra.SESSION_ID");
      if (str1 == null || !str1.equals(RemotePlaybackClient.this.mSessionId)) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Discarding spurious status callback with missing or invalid session id: sessionId=");
        stringBuilder.append(str1);
        Log.w("RemotePlaybackClient", stringBuilder.toString());
        return;
      } 
      MediaSessionStatus mediaSessionStatus = MediaSessionStatus.fromBundle(stringBuilder.getBundleExtra("android.media.intent.extra.SESSION_STATUS"));
      String str2 = stringBuilder.getAction();
      if (str2.equals("android.support.v7.media.actions.ACTION_ITEM_STATUS_CHANGED")) {
        String str = stringBuilder.getStringExtra("android.media.intent.extra.ITEM_ID");
        if (str == null) {
          Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item id.");
          return;
        } 
        mediaItemStatus = MediaItemStatus.fromBundle(stringBuilder.getBundleExtra("android.media.intent.extra.ITEM_STATUS"));
        if (mediaItemStatus == null) {
          Log.w("RemotePlaybackClient", "Discarding spurious status callback with missing item status.");
          return;
        } 
        if (RemotePlaybackClient.DEBUG) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Received item status callback: sessionId=");
          stringBuilder1.append(str1);
          stringBuilder1.append(", sessionStatus=");
          stringBuilder1.append(mediaSessionStatus);
          stringBuilder1.append(", itemId=");
          stringBuilder1.append(str);
          stringBuilder1.append(", itemStatus=");
          stringBuilder1.append(mediaItemStatus);
          Log.d("RemotePlaybackClient", stringBuilder1.toString());
        } 
        RemotePlaybackClient.StatusCallback statusCallback = RemotePlaybackClient.this.mStatusCallback;
        if (statusCallback != null)
          statusCallback.onItemStatusChanged(stringBuilder.getExtras(), str1, mediaSessionStatus, str, mediaItemStatus); 
      } else {
        RemotePlaybackClient.StatusCallback statusCallback;
        if (mediaItemStatus.equals("android.support.v7.media.actions.ACTION_SESSION_STATUS_CHANGED")) {
          if (mediaSessionStatus == null) {
            Log.w("RemotePlaybackClient", "Discarding spurious media status callback with missing session status.");
            return;
          } 
          if (RemotePlaybackClient.DEBUG) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Received session status callback: sessionId=");
            stringBuilder1.append(str1);
            stringBuilder1.append(", sessionStatus=");
            stringBuilder1.append(mediaSessionStatus);
            Log.d("RemotePlaybackClient", stringBuilder1.toString());
          } 
          statusCallback = RemotePlaybackClient.this.mStatusCallback;
          if (statusCallback != null)
            statusCallback.onSessionStatusChanged(stringBuilder.getExtras(), str1, mediaSessionStatus); 
        } else if (statusCallback.equals("android.support.v7.media.actions.ACTION_MESSAGE_RECEIVED")) {
          if (RemotePlaybackClient.DEBUG) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Received message callback: sessionId=");
            stringBuilder1.append(str1);
            Log.d("RemotePlaybackClient", stringBuilder1.toString());
          } 
          RemotePlaybackClient.OnMessageReceivedListener onMessageReceivedListener = RemotePlaybackClient.this.mOnMessageReceivedListener;
          if (onMessageReceivedListener != null)
            onMessageReceivedListener.onMessageReceived(str1, stringBuilder.getBundleExtra("android.media.intent.extra.MESSAGE")); 
        } 
      } 
    }
  }
  
  public static abstract class ItemActionCallback extends ActionCallback {
    public void onResult(Bundle param1Bundle, String param1String1, MediaSessionStatus param1MediaSessionStatus, String param1String2, MediaItemStatus param1MediaItemStatus) {}
  }
  
  public static interface OnMessageReceivedListener {
    void onMessageReceived(String param1String, Bundle param1Bundle);
  }
  
  public static abstract class SessionActionCallback extends ActionCallback {
    public void onResult(Bundle param1Bundle, String param1String, MediaSessionStatus param1MediaSessionStatus) {}
  }
  
  public static abstract class StatusCallback {
    public void onItemStatusChanged(Bundle param1Bundle, String param1String1, MediaSessionStatus param1MediaSessionStatus, String param1String2, MediaItemStatus param1MediaItemStatus) {}
    
    public void onSessionChanged(String param1String) {}
    
    public void onSessionStatusChanged(Bundle param1Bundle, String param1String, MediaSessionStatus param1MediaSessionStatus) {}
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/RemotePlaybackClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */