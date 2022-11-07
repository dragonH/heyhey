package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.zzec;

public interface IObjectWrapper extends IInterface {
  public static class zza extends zzec implements IObjectWrapper {
    public zza() {
      attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
    }
    
    public static IObjectWrapper zzao(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
      return (iInterface instanceof IObjectWrapper) ? (IObjectWrapper)iInterface : new zzm(param1IBinder);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/IObjectWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */