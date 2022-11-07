package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbn extends Handler {
  zzbn(zzbl paramzzbl, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        stringBuilder = new StringBuilder(31);
        stringBuilder.append("Unknown message id: ");
        stringBuilder.append(i);
        Log.w("GACStateManager", stringBuilder.toString());
        return;
      } 
      throw (RuntimeException)stringBuilder.obj;
    } 
    ((zzbm)((Message)stringBuilder).obj).zzc(this.zzfni);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */