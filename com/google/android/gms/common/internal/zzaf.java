package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzaf {
  private static final Object zzfut = new Object();
  
  private static zzaf zzfuu;
  
  public static zzaf zzce(Context paramContext) {
    synchronized (zzfut) {
      if (zzfuu == null) {
        zzah zzah = new zzah();
        this(paramContext.getApplicationContext());
        zzfuu = zzah;
      } 
      return zzfuu;
    } 
  }
  
  public final void zza(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3) {
    zzb(new zzag(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  public final boolean zza(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString) {
    return zza(new zzag(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract boolean zza(zzag paramzzag, ServiceConnection paramServiceConnection, String paramString);
  
  public final void zzb(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString) {
    zzb(new zzag(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract void zzb(zzag paramzzag, ServiceConnection paramServiceConnection, String paramString);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */