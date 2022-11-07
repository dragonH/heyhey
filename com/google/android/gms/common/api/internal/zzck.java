package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzbp;

final class zzck extends Handler {
  public zzck(zzcj paramzzcj, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    zzbp.zzbh(bool);
    this.zzfow.zzb((zzcm)paramMessage.obj);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */