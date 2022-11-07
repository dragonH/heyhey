package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.zzc;
import java.util.Collections;
import java.util.List;

public final class zza {
  private static final Object zzfut = new Object();
  
  private static volatile zza zzfxj;
  
  private static boolean zzfxk = false;
  
  private final List<String> zzfxl;
  
  private final List<String> zzfxm;
  
  private final List<String> zzfxn;
  
  private final List<String> zzfxo;
  
  private zza() {
    List<String> list = Collections.EMPTY_LIST;
    this.zzfxl = list;
    this.zzfxm = list;
    this.zzfxn = list;
    this.zzfxo = list;
  }
  
  public static zza zzaky() {
    if (zzfxj == null)
      synchronized (zzfut) {
        if (zzfxj == null) {
          zza zza1 = new zza();
          this();
          zzfxj = zza1;
        } 
      }  
    return zzfxj;
  }
  
  public final boolean zza(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  public final boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    boolean bool;
    ComponentName componentName = paramIntent.getComponent();
    if (componentName == null) {
      bool = false;
    } else {
      bool = zzc.zzab(paramContext, componentName.getPackageName());
    } 
    if (bool) {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
      return false;
    } 
    return paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/stats/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */