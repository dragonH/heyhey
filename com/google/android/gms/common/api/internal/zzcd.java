package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public interface zzcd {
  ConnectionResult blockingConnect();
  
  ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  void connect();
  
  void disconnect();
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @Nullable
  ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  boolean isConnected();
  
  boolean isConnecting();
  
  boolean zza(zzcv paramzzcv);
  
  void zzafp();
  
  void zzagi();
  
  <A extends Api.zzb, R extends com.google.android.gms.common.api.Result, T extends zzm<R, A>> T zzd(@NonNull T paramT);
  
  <A extends Api.zzb, T extends zzm<? extends com.google.android.gms.common.api.Result, A>> T zze(@NonNull T paramT);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */