package com.microsoft.appcenter.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.VisibleForTesting;
import java.io.Closeable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NetworkStateHelper implements Closeable {
  @SuppressLint({"StaticFieldLeak"})
  private static NetworkStateHelper sSharedInstance;
  
  private final ConnectivityManager mConnectivityManager;
  
  private final ConnectivityReceiver mConnectivityReceiver;
  
  private final Context mContext;
  
  private final Set<Listener> mListeners = new HashSet<Listener>();
  
  private String mNetworkType;
  
  @VisibleForTesting
  NetworkStateHelper(Context paramContext) {
    this.mContext = paramContext.getApplicationContext();
    this.mConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();
    this.mConnectivityReceiver = connectivityReceiver;
    updateNetworkType();
    paramContext.registerReceiver(connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public static NetworkStateHelper getSharedInstance(Context paramContext) {
    if (sSharedInstance == null)
      sSharedInstance = new NetworkStateHelper(paramContext); 
    return sSharedInstance;
  }
  
  private void notifyNetworkStateUpdated(boolean paramBoolean) {
    Iterator<Listener> iterator = this.mListeners.iterator();
    while (iterator.hasNext())
      ((Listener)iterator.next()).onNetworkStateUpdated(paramBoolean); 
  }
  
  private void updateNetworkType() {
    try {
      NetworkInfo networkInfo = this.mConnectivityManager.getActiveNetworkInfo();
    } catch (RuntimeException runtimeException) {
      runtimeException = null;
      AppCenterLog.error("AppCenter", "Could not get network info and thus stuck in disconnected state, please check you declared android.permission.ACCESS_NETWORK_STATE");
    } 
    updateNetworkType((NetworkInfo)runtimeException);
  }
  
  private void updateNetworkType(NetworkInfo paramNetworkInfo) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Active network info=");
    stringBuilder.append(paramNetworkInfo);
    AppCenterLog.debug("AppCenter", stringBuilder.toString());
    if (paramNetworkInfo != null && paramNetworkInfo.isConnected()) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramNetworkInfo.getTypeName());
      stringBuilder.append(paramNetworkInfo.getSubtypeName());
      this.mNetworkType = stringBuilder.toString();
    } else {
      this.mNetworkType = null;
    } 
  }
  
  public void addListener(Listener paramListener) {
    this.mListeners.add(paramListener);
  }
  
  public void close() {
    this.mContext.unregisterReceiver(this.mConnectivityReceiver);
  }
  
  public boolean isNetworkConnected() {
    boolean bool;
    if (this.mNetworkType != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void removeListener(Listener paramListener) {
    this.mListeners.remove(paramListener);
  }
  
  private class ConnectivityReceiver extends BroadcastReceiver {
    private ConnectivityReceiver() {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      String str1 = NetworkStateHelper.this.mNetworkType;
      NetworkInfo networkInfo = (NetworkInfo)param1Intent.getParcelableExtra("networkInfo");
      NetworkStateHelper.this.updateNetworkType(networkInfo);
      boolean bool = true;
      String str2 = NetworkStateHelper.this.mNetworkType;
      if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
        bool = false; 
      if (bool) {
        boolean bool1 = NetworkStateHelper.this.isNetworkConnected();
        if (bool1 && str1 != null)
          NetworkStateHelper.this.notifyNetworkStateUpdated(false); 
        NetworkStateHelper.this.notifyNetworkStateUpdated(bool1);
      } 
    }
  }
  
  public static interface Listener {
    void onNetworkStateUpdated(boolean param1Boolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/NetworkStateHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */