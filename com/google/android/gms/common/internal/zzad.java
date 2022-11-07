package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzad implements Handler.Callback {
  private final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final zzae zzfum;
  
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzfun = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
  
  private ArrayList<GoogleApiClient.ConnectionCallbacks> zzfuo = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
  
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzfup = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
  
  private volatile boolean zzfuq = false;
  
  private final AtomicInteger zzfur = new AtomicInteger(0);
  
  private boolean zzfus = false;
  
  public zzad(Looper paramLooper, zzae paramzzae) {
    this.zzfum = paramzzae;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public final boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i == 1) {
      null = (GoogleApiClient.ConnectionCallbacks)paramMessage.obj;
      synchronized (this.mLock) {
        if (this.zzfuq && this.zzfum.isConnected() && this.zzfun.contains(null))
          null.onConnected(this.zzfum.zzaeh()); 
        return true;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder(45);
    stringBuilder.append("Don't know how to handle message: ");
    stringBuilder.append(i);
    Log.wtf("GmsClientEvents", stringBuilder.toString(), new Exception());
    return false;
  }
  
  public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    zzbp.zzu(paramConnectionCallbacks);
    synchronized (this.mLock) {
      return this.zzfun.contains(paramConnectionCallbacks);
    } 
  }
  
  public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    zzbp.zzu(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      return this.zzfup.contains(paramOnConnectionFailedListener);
    } 
  }
  
  public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    zzbp.zzu(paramConnectionCallbacks);
    synchronized (this.mLock) {
      if (this.zzfun.contains(paramConnectionCallbacks)) {
        String str = String.valueOf(paramConnectionCallbacks);
        int i = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 62);
        stringBuilder.append("registerConnectionCallbacks(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else {
        this.zzfun.add(paramConnectionCallbacks);
      } 
      if (this.zzfum.isConnected()) {
        null = this.mHandler;
        null.sendMessage(null.obtainMessage(1, paramConnectionCallbacks));
      } 
      return;
    } 
  }
  
  public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    zzbp.zzu(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      String str;
      if (this.zzfup.contains(paramOnConnectionFailedListener)) {
        str = String.valueOf(paramOnConnectionFailedListener);
        int i = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 67);
        stringBuilder.append("registerConnectionFailedListener(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else {
        this.zzfup.add(str);
      } 
      return;
    } 
  }
  
  public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    zzbp.zzu(paramConnectionCallbacks);
    synchronized (this.mLock) {
      StringBuilder stringBuilder;
      if (!this.zzfun.remove(paramConnectionCallbacks)) {
        String str = String.valueOf(paramConnectionCallbacks);
        int i = str.length();
        stringBuilder = new StringBuilder();
        this(i + 52);
        stringBuilder.append("unregisterConnectionCallbacks(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" not found");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else if (this.zzfus) {
        this.zzfuo.add(stringBuilder);
      } 
      return;
    } 
  }
  
  public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    zzbp.zzu(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      if (!this.zzfup.remove(paramOnConnectionFailedListener)) {
        String str = String.valueOf(paramOnConnectionFailedListener);
        int i = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 57);
        stringBuilder.append("unregisterConnectionFailedListener(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" not found");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } 
      return;
    } 
  }
  
  public final void zzake() {
    this.zzfuq = false;
    this.zzfur.incrementAndGet();
  }
  
  public final void zzakf() {
    this.zzfuq = true;
  }
  
  public final void zzce(int paramInt) {
    boolean bool;
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      this.zzfus = true;
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zzfun);
      int i = this.zzfur.get();
      int j = arrayList.size();
      int k = 0;
      while (k < j) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(k);
        int m = k + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zzfuq && this.zzfur.get() == i) {
          k = m;
          if (this.zzfun.contains(connectionCallbacks)) {
            connectionCallbacks.onConnectionSuspended(paramInt);
            k = m;
          } 
        } 
      } 
      this.zzfuo.clear();
      this.zzfus = false;
      return;
    } 
  }
  
  public final void zzk(Bundle paramBundle) {
    boolean bool2;
    Looper looper1 = Looper.myLooper();
    Looper looper2 = this.mHandler.getLooper();
    boolean bool1 = true;
    if (looper1 == looper2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "onConnectionSuccess must only be called on the Handler thread");
    synchronized (this.mLock) {
      if (!this.zzfus) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      zzbp.zzbg(bool2);
      this.mHandler.removeMessages(1);
      this.zzfus = true;
      if (this.zzfuo.size() == 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      zzbp.zzbg(bool2);
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zzfun);
      int i = this.zzfur.get();
      int j = arrayList.size();
      int k = 0;
      while (k < j) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(k);
        int m = k + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zzfuq && this.zzfum.isConnected() && this.zzfur.get() == i) {
          k = m;
          if (!this.zzfuo.contains(connectionCallbacks)) {
            connectionCallbacks.onConnected(paramBundle);
            k = m;
          } 
        } 
      } 
      this.zzfuo.clear();
      this.zzfus = false;
      return;
    } 
  }
  
  public final void zzk(ConnectionResult paramConnectionResult) {
    boolean bool;
    Looper looper1 = Looper.myLooper();
    Looper looper2 = this.mHandler.getLooper();
    int i = 0;
    if (looper1 == looper2) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "onConnectionFailure must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zzfup);
      int j = this.zzfur.get();
      int k = arrayList.size();
      while (i < k) {
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)arrayList.get(i);
        int m = i + 1;
        onConnectionFailedListener = onConnectionFailedListener;
        if (!this.zzfuq || this.zzfur.get() != j)
          return; 
        i = m;
        if (this.zzfup.contains(onConnectionFailedListener)) {
          onConnectionFailedListener.onConnectionFailed(paramConnectionResult);
          i = m;
        } 
      } 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */