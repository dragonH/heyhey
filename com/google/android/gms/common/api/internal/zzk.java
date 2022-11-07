package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzk implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
  private static final zzk zzfis = new zzk();
  
  private final ArrayList<zzl> mListeners = new ArrayList<zzl>();
  
  private boolean zzdoe = false;
  
  private final AtomicBoolean zzfit = new AtomicBoolean();
  
  private final AtomicBoolean zzfiu = new AtomicBoolean();
  
  public static void zza(Application paramApplication) {
    synchronized (zzfis) {
      if (!null.zzdoe) {
        paramApplication.registerActivityLifecycleCallbacks(null);
        paramApplication.registerComponentCallbacks((ComponentCallbacks)null);
        null.zzdoe = true;
      } 
      return;
    } 
  }
  
  public static zzk zzafz() {
    return zzfis;
  }
  
  private final void zzbe(boolean paramBoolean) {
    synchronized (zzfis) {
      ArrayList<zzl> arrayList = this.mListeners;
      int i = arrayList.size();
      byte b = 0;
      while (b < i) {
        zzl zzl = (zzl)arrayList.get(b);
        b++;
        ((zzl)zzl).zzbe(paramBoolean);
      } 
      return;
    } 
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    boolean bool = this.zzfit.compareAndSet(true, false);
    this.zzfiu.set(true);
    if (bool)
      zzbe(false); 
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {}
  
  public final void onActivityPaused(Activity paramActivity) {}
  
  public final void onActivityResumed(Activity paramActivity) {
    boolean bool = this.zzfit.compareAndSet(true, false);
    this.zzfiu.set(true);
    if (bool)
      zzbe(false); 
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
  
  public final void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public final void onLowMemory() {}
  
  public final void onTrimMemory(int paramInt) {
    if (paramInt == 20 && this.zzfit.compareAndSet(false, true)) {
      this.zzfiu.set(true);
      zzbe(true);
    } 
  }
  
  public final void zza(zzl paramzzl) {
    synchronized (zzfis) {
      this.mListeners.add(paramzzl);
      return;
    } 
  }
  
  public final boolean zzaga() {
    return this.zzfit.get();
  }
  
  @TargetApi(16)
  public final boolean zzbd(boolean paramBoolean) {
    if (!this.zzfiu.get())
      if (zzq.zzale()) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        if (!this.zzfiu.getAndSet(true) && runningAppProcessInfo.importance > 100)
          this.zzfit.set(true); 
      } else {
        return true;
      }  
    return this.zzfit.get();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */