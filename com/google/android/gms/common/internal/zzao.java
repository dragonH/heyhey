package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzao extends zzeb implements zzam {
  zzao(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.common.internal.IAccountAccessor");
  }
  
  public final Account getAccount() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    Account account = (Account)zzed.zza(parcel, Account.CREATOR);
    parcel.recycle();
    return account;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */