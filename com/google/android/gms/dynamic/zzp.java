package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.zzo;

public abstract class zzp<T> {
  private final String zzgpf;
  
  private T zzgpg;
  
  protected zzp(String paramString) {
    this.zzgpf = paramString;
  }
  
  protected final T zzcu(Context paramContext) throws zzq {
    if (this.zzgpg == null) {
      zzbp.zzu(paramContext);
      paramContext = zzo.getRemoteContext(paramContext);
      if (paramContext != null) {
        ClassLoader classLoader = paramContext.getClassLoader();
        try {
          this.zzgpg = zze((IBinder)classLoader.loadClass(this.zzgpf).newInstance());
        } catch (ClassNotFoundException classNotFoundException) {
          throw new zzq("Could not load creator class.", classNotFoundException);
        } catch (InstantiationException instantiationException) {
          throw new zzq("Could not instantiate creator.", instantiationException);
        } catch (IllegalAccessException illegalAccessException) {
          throw new zzq("Could not access creator.", illegalAccessException);
        } 
      } else {
        throw new zzq("Could not get remote context.");
      } 
    } 
    return this.zzgpg;
  }
  
  protected abstract T zze(IBinder paramIBinder);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */