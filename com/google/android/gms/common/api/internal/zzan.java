package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzan extends GoogleApiClient {
  private final String zzflg;
  
  public zzan(String paramString) {
    this.zzflg = paramString;
  }
  
  public ConnectionResult blockingConnect() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void connect() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void disconnect() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public boolean isConnected() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public boolean isConnecting() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void reconnect() {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzflg);
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzflg);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */