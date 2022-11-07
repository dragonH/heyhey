package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

final class zzh extends Handler {
  public zzh(zzd paramzzd, Looper paramLooper) {
    super(paramLooper);
  }
  
  private static void zza(Message paramMessage) {
    ((zzi)paramMessage.obj).unregister();
  }
  
  private static boolean zzb(Message paramMessage) {
    int i = paramMessage.what;
    return (i == 2 || i == 1 || i == 7);
  }
  
  public final void handleMessage(Message paramMessage) {
    ConnectionResult connectionResult;
    if (this.zzftl.zzfti.get() != paramMessage.arg1) {
      if (zzb(paramMessage))
        zza(paramMessage); 
      return;
    } 
    int i = paramMessage.what;
    if ((i == 1 || i == 7 || i == 4 || i == 5) && !this.zzftl.isConnecting()) {
      zza(paramMessage);
      return;
    } 
    i = paramMessage.what;
    PendingIntent pendingIntent = null;
    if (i == 4) {
      zzd.zza(this.zzftl, new ConnectionResult(paramMessage.arg2));
      if (zzd.zzb(this.zzftl) && !zzd.zzc(this.zzftl)) {
        zzd.zza(this.zzftl, 3, (IInterface)null);
        return;
      } 
      if (zzd.zzd(this.zzftl) != null) {
        connectionResult = zzd.zzd(this.zzftl);
      } else {
        connectionResult = new ConnectionResult(8);
      } 
      this.zzftl.zzfsx.zzf(connectionResult);
      this.zzftl.onConnectionFailed(connectionResult);
      return;
    } 
    if (i == 5) {
      if (zzd.zzd(this.zzftl) != null) {
        connectionResult = zzd.zzd(this.zzftl);
      } else {
        connectionResult = new ConnectionResult(8);
      } 
      this.zzftl.zzfsx.zzf(connectionResult);
      this.zzftl.onConnectionFailed(connectionResult);
      return;
    } 
    if (i == 3) {
      Object object = ((Message)connectionResult).obj;
      if (object instanceof PendingIntent)
        pendingIntent = (PendingIntent)object; 
      connectionResult = new ConnectionResult(((Message)connectionResult).arg2, pendingIntent);
      this.zzftl.zzfsx.zzf(connectionResult);
      this.zzftl.onConnectionFailed(connectionResult);
      return;
    } 
    if (i == 6) {
      zzd.zza(this.zzftl, 5, (IInterface)null);
      if (zzd.zze(this.zzftl) != null)
        zzd.zze(this.zzftl).onConnectionSuspended(((Message)connectionResult).arg2); 
      this.zzftl.onConnectionSuspended(((Message)connectionResult).arg2);
      zzd.zza(this.zzftl, 5, 1, (IInterface)null);
      return;
    } 
    if (i == 2 && !this.zzftl.isConnected()) {
      zza((Message)connectionResult);
      return;
    } 
    if (zzb((Message)connectionResult)) {
      ((zzi)((Message)connectionResult).obj).zzajo();
      return;
    } 
    i = ((Message)connectionResult).what;
    StringBuilder stringBuilder = new StringBuilder(45);
    stringBuilder.append("Don't know how to handle message: ");
    stringBuilder.append(i);
    Log.wtf("GmsClient", stringBuilder.toString(), new Exception());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */