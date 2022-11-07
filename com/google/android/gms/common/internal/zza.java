package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

public final class zza extends zzan {
  public static Account zza(zzam paramzzam) {
    if (paramzzam != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Account account = paramzzam.getAccount();
        Binder.restoreCallingIdentity(l);
      } catch (RemoteException remoteException) {
        Log.w("AccountAccessor", "Remote account accessor probably died");
        Binder.restoreCallingIdentity(l);
        remoteException = null;
      } finally {}
      return (Account)paramzzam;
    } 
    paramzzam = null;
  }
  
  public final boolean equals(Object paramObject) {
    throw new NoSuchMethodError();
  }
  
  public final Account getAccount() {
    throw new NoSuchMethodError();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */