package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;

final class zzah extends zzaf implements Handler.Callback {
  private final Context mApplicationContext;
  
  private final Handler mHandler;
  
  private final HashMap<zzag, zzai> zzfuy = new HashMap<zzag, zzai>();
  
  private final zza zzfuz;
  
  private final long zzfva;
  
  private final long zzfvb;
  
  zzah(Context paramContext) {
    this.mApplicationContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(paramContext.getMainLooper(), this);
    this.zzfuz = zza.zzaky();
    this.zzfva = 5000L;
    this.zzfvb = 300000L;
  }
  
  public final boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 0) {
      if (i != 1)
        return false; 
      synchronized (this.zzfuy) {
        zzag zzag = (zzag)paramMessage.obj;
        zzai zzai = this.zzfuy.get(zzag);
        if (zzai != null && zzai.getState() == 3) {
          String str = String.valueOf(zzag);
          i = str.length();
          StringBuilder stringBuilder = new StringBuilder();
          this(i + 47);
          stringBuilder.append("Timeout waiting for ServiceConnection callback ");
          stringBuilder.append(str);
          str = stringBuilder.toString();
          Exception exception = new Exception();
          this();
          Log.wtf("GmsClientSupervisor", str, exception);
          ComponentName componentName2 = zzai.getComponentName();
          ComponentName componentName1 = componentName2;
          if (componentName2 == null)
            componentName1 = zzag.getComponentName(); 
          componentName2 = componentName1;
          if (componentName1 == null) {
            componentName2 = new ComponentName();
            this(zzag.getPackage(), "unknown");
          } 
          zzai.onServiceDisconnected(componentName2);
        } 
        return true;
      } 
    } 
    synchronized (this.zzfuy) {
      zzag zzag = (zzag)paramMessage.obj;
      zzai zzai = this.zzfuy.get(zzag);
      if (zzai != null && zzai.zzaki()) {
        if (zzai.isBound())
          zzai.zzgd("GmsClientSupervisor"); 
        this.zzfuy.remove(zzag);
      } 
      return true;
    } 
  }
  
  protected final boolean zza(zzag paramzzag, ServiceConnection paramServiceConnection, String paramString) {
    zzbp.zzb(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zzfuy) {
      zzai zzai1;
      String str;
      zzai zzai2 = this.zzfuy.get(paramzzag);
      if (zzai2 == null) {
        zzai2 = new zzai();
        this(this, paramzzag);
        zzai2.zza(paramServiceConnection, paramString);
        zzai2.zzgc(paramString);
        this.zzfuy.put(paramzzag, zzai2);
        zzai1 = zzai2;
      } else {
        this.mHandler.removeMessages(0, zzai1);
        if (!zzai2.zza(paramServiceConnection)) {
          zzai2.zza(paramServiceConnection, paramString);
          int j = zzai2.getState();
          if (j != 1) {
            if (j != 2) {
              zzai1 = zzai2;
            } else {
              zzai2.zzgc(paramString);
              zzai1 = zzai2;
            } 
          } else {
            paramServiceConnection.onServiceConnected(zzai2.getComponentName(), zzai2.getBinder());
            zzai1 = zzai2;
          } 
          return zzai1.isBound();
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        str = String.valueOf(zzai1);
        int i = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 81);
        stringBuilder.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
        stringBuilder.append(str);
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
      return str.isBound();
    } 
  }
  
  protected final void zzb(zzag paramzzag, ServiceConnection paramServiceConnection, String paramString) {
    zzbp.zzb(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zzfuy) {
      zzai zzai = this.zzfuy.get(paramzzag);
      if (zzai != null) {
        Message message;
        if (zzai.zza(paramServiceConnection)) {
          zzai.zzb(paramServiceConnection, paramString);
          if (zzai.zzaki()) {
            message = this.mHandler.obtainMessage(0, paramzzag);
            this.mHandler.sendMessageDelayed(message, this.zzfva);
          } 
          return;
        } 
        IllegalStateException illegalStateException1 = new IllegalStateException();
        paramString = String.valueOf(message);
        int j = paramString.length();
        stringBuilder = new StringBuilder();
        this(j + 76);
        stringBuilder.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
        stringBuilder.append(paramString);
        this(stringBuilder.toString());
        throw illegalStateException1;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      paramString = String.valueOf(stringBuilder);
      int i = paramString.length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 50);
      stringBuilder.append("Nonexistent connection status for service config: ");
      stringBuilder.append(paramString);
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */