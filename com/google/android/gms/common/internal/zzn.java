package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzn extends zze {
  private IBinder zzftp;
  
  @BinderThread
  public zzn(zzd paramzzd, int paramInt, IBinder paramIBinder, Bundle paramBundle) {
    super(paramzzd, paramInt, paramBundle);
    this.zzftp = paramIBinder;
  }
  
  protected final boolean zzajn() {
    try {
      String str = this.zzftp.getInterfaceDescriptor();
      if (!this.zzftl.zzhd().equals(str)) {
        String str1 = this.zzftl.zzhd();
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 34 + String.valueOf(str).length());
        stringBuilder.append("service descriptor mismatch: ");
        stringBuilder.append(str1);
        stringBuilder.append(" vs. ");
        stringBuilder.append(str);
        Log.e("GmsClient", stringBuilder.toString());
        return false;
      } 
      IInterface iInterface = (IInterface)this.zzftl.zzd(this.zzftp);
      if (iInterface != null && (zzd.zza(this.zzftl, 2, 4, iInterface) || zzd.zza(this.zzftl, 3, 4, iInterface))) {
        zzd.zza(this.zzftl, (ConnectionResult)null);
        Bundle bundle = this.zzftl.zzaeh();
        if (zzd.zze(this.zzftl) != null)
          zzd.zze(this.zzftl).onConnected(bundle); 
        return true;
      } 
      return false;
    } catch (RemoteException remoteException) {
      Log.w("GmsClient", "service probably died");
      return false;
    } 
  }
  
  protected final void zzj(ConnectionResult paramConnectionResult) {
    if (zzd.zzg(this.zzftl) != null)
      zzd.zzg(this.zzftl).onConnectionFailed(paramConnectionResult); 
    this.zzftl.onConnectionFailed(paramConnectionResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */