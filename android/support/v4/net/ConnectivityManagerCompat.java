package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
  private static final ConnectivityManagerCompatImpl IMPL;
  
  public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
  
  public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
  
  public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;
  
  static {
    if (Build.VERSION.SDK_INT >= 24) {
      IMPL = new ConnectivityManagerCompatApi24Impl();
    } else {
      IMPL = new ConnectivityManagerCompatApi16Impl();
    } 
  }
  
  public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager paramConnectivityManager, Intent paramIntent) {
    NetworkInfo networkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    return (networkInfo != null) ? paramConnectivityManager.getNetworkInfo(networkInfo.getType()) : null;
  }
  
  public static int getRestrictBackgroundStatus(ConnectivityManager paramConnectivityManager) {
    return IMPL.getRestrictBackgroundStatus(paramConnectivityManager);
  }
  
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager) {
    return IMPL.isActiveNetworkMetered(paramConnectivityManager);
  }
  
  @RequiresApi(16)
  static class ConnectivityManagerCompatApi16Impl extends ConnectivityManagerCompatBaseImpl {
    public boolean isActiveNetworkMetered(ConnectivityManager param1ConnectivityManager) {
      return param1ConnectivityManager.isActiveNetworkMetered();
    }
  }
  
  @RequiresApi(24)
  static class ConnectivityManagerCompatApi24Impl extends ConnectivityManagerCompatApi16Impl {
    public int getRestrictBackgroundStatus(ConnectivityManager param1ConnectivityManager) {
      return param1ConnectivityManager.getRestrictBackgroundStatus();
    }
  }
  
  static class ConnectivityManagerCompatBaseImpl implements ConnectivityManagerCompatImpl {
    public int getRestrictBackgroundStatus(ConnectivityManager param1ConnectivityManager) {
      return 3;
    }
    
    public boolean isActiveNetworkMetered(ConnectivityManager param1ConnectivityManager) {
      NetworkInfo networkInfo = param1ConnectivityManager.getActiveNetworkInfo();
      if (networkInfo == null)
        return true; 
      int i = networkInfo.getType();
      return (i != 1 && i != 7 && i != 9);
    }
  }
  
  static interface ConnectivityManagerCompatImpl {
    int getRestrictBackgroundStatus(ConnectivityManager param1ConnectivityManager);
    
    boolean isActiveNetworkMetered(ConnectivityManager param1ConnectivityManager);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface RestrictBackgroundStatus {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/net/ConnectivityManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */