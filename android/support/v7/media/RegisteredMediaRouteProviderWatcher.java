package android.support.v7.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class RegisteredMediaRouteProviderWatcher {
  private final Callback mCallback;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final PackageManager mPackageManager;
  
  private final ArrayList<RegisteredMediaRouteProvider> mProviders = new ArrayList<RegisteredMediaRouteProvider>();
  
  private boolean mRunning;
  
  private final BroadcastReceiver mScanPackagesReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        RegisteredMediaRouteProviderWatcher.this.scanPackages();
      }
    };
  
  private final Runnable mScanPackagesRunnable = new Runnable() {
      public void run() {
        RegisteredMediaRouteProviderWatcher.this.scanPackages();
      }
    };
  
  public RegisteredMediaRouteProviderWatcher(Context paramContext, Callback paramCallback) {
    this.mContext = paramContext;
    this.mCallback = paramCallback;
    this.mHandler = new Handler();
    this.mPackageManager = paramContext.getPackageManager();
  }
  
  private int findProvider(String paramString1, String paramString2) {
    int i = this.mProviders.size();
    for (byte b = 0; b < i; b++) {
      if (((RegisteredMediaRouteProvider)this.mProviders.get(b)).hasComponentName(paramString1, paramString2))
        return b; 
    } 
    return -1;
  }
  
  void scanPackages() {
    if (!this.mRunning)
      return; 
    Intent intent = new Intent("android.media.MediaRouteProviderService");
    PackageManager packageManager = this.mPackageManager;
    int i = 0;
    Iterator iterator = packageManager.queryIntentServices(intent, 0).iterator();
    while (iterator.hasNext()) {
      ServiceInfo serviceInfo = ((ResolveInfo)iterator.next()).serviceInfo;
      if (serviceInfo != null) {
        int j = findProvider(serviceInfo.packageName, serviceInfo.name);
        if (j < 0) {
          RegisteredMediaRouteProvider registeredMediaRouteProvider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
          registeredMediaRouteProvider.start();
          ArrayList<RegisteredMediaRouteProvider> arrayList = this.mProviders;
          int k = i + 1;
          arrayList.add(i, registeredMediaRouteProvider);
          this.mCallback.addProvider(registeredMediaRouteProvider);
          i = k;
          continue;
        } 
        if (j >= i) {
          RegisteredMediaRouteProvider registeredMediaRouteProvider = this.mProviders.get(j);
          registeredMediaRouteProvider.start();
          registeredMediaRouteProvider.rebindIfDisconnected();
          ArrayList<RegisteredMediaRouteProvider> arrayList = this.mProviders;
          int k = i + 1;
          Collections.swap(arrayList, j, i);
          i = k;
        } 
      } 
    } 
    if (i < this.mProviders.size())
      for (int j = this.mProviders.size() - 1; j >= i; j--) {
        RegisteredMediaRouteProvider registeredMediaRouteProvider = this.mProviders.get(j);
        this.mCallback.removeProvider(registeredMediaRouteProvider);
        this.mProviders.remove(registeredMediaRouteProvider);
        registeredMediaRouteProvider.stop();
      }  
  }
  
  public void start() {
    if (!this.mRunning) {
      this.mRunning = true;
      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
      intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
      intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
      intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
      intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
      intentFilter.addDataScheme("package");
      this.mContext.registerReceiver(this.mScanPackagesReceiver, intentFilter, null, this.mHandler);
      this.mHandler.post(this.mScanPackagesRunnable);
    } 
  }
  
  public void stop() {
    if (this.mRunning) {
      this.mRunning = false;
      this.mContext.unregisterReceiver(this.mScanPackagesReceiver);
      this.mHandler.removeCallbacks(this.mScanPackagesRunnable);
      for (int i = this.mProviders.size() - 1; i >= 0; i--)
        ((RegisteredMediaRouteProvider)this.mProviders.get(i)).stop(); 
    } 
  }
  
  public static interface Callback {
    void addProvider(MediaRouteProvider param1MediaRouteProvider);
    
    void removeProvider(MediaRouteProvider param1MediaRouteProvider);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/RegisteredMediaRouteProviderWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */