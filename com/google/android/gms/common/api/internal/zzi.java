package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbp;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzo {
  private final SparseArray<zza> zzfik = new SparseArray();
  
  private zzi(zzcg paramzzcg) {
    super(paramzzcg);
    this.zzfoo.zza("AutoManageHelper", this);
  }
  
  public static zzi zza(zzcf paramzzcf) {
    zzcg zzcg = LifecycleCallback.zzb(paramzzcf);
    zzi zzi1 = zzcg.<zzi>zza("AutoManageHelper", zzi.class);
    return (zzi1 != null) ? zzi1 : new zzi(zzcg);
  }
  
  @Nullable
  private final zza zzbq(int paramInt) {
    if (this.zzfik.size() <= paramInt)
      return null; 
    SparseArray<zza> sparseArray = this.zzfik;
    return (zza)sparseArray.get(sparseArray.keyAt(paramInt));
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    for (byte b = 0; b < this.zzfik.size(); b++) {
      zza zza = zzbq(b);
      if (zza != null) {
        paramPrintWriter.append(paramString).append("GoogleApiClient #").print(zza.zzfil);
        paramPrintWriter.println(":");
        zza.zzfim.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      } 
    } 
  }
  
  public final void onStart() {
    super.onStart();
    boolean bool = this.mStarted;
    String str = String.valueOf(this.zzfik);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 14);
    stringBuilder.append("onStart ");
    stringBuilder.append(bool);
    stringBuilder.append(" ");
    stringBuilder.append(str);
    Log.d("AutoManageHelper", stringBuilder.toString());
    if (this.zzfiw.get() == null)
      for (byte b = 0; b < this.zzfik.size(); b++) {
        zza zza = zzbq(b);
        if (zza != null)
          zza.zzfim.connect(); 
      }  
  }
  
  public final void onStop() {
    super.onStop();
    for (byte b = 0; b < this.zzfik.size(); b++) {
      zza zza = zzbq(b);
      if (zza != null)
        zza.zzfim.disconnect(); 
    } 
  }
  
  public final void zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    zzbp.zzb(paramGoogleApiClient, "GoogleApiClient instance cannot be null");
    if (this.zzfik.indexOfKey(paramInt) < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder1 = new StringBuilder(54);
    stringBuilder1.append("Already managing a GoogleApiClient with id ");
    stringBuilder1.append(paramInt);
    zzbp.zza(bool, stringBuilder1.toString());
    zzp zzp = this.zzfiw.get();
    boolean bool = this.mStarted;
    String str = String.valueOf(zzp);
    StringBuilder stringBuilder2 = new StringBuilder(str.length() + 49);
    stringBuilder2.append("starting AutoManage for client ");
    stringBuilder2.append(paramInt);
    stringBuilder2.append(" ");
    stringBuilder2.append(bool);
    stringBuilder2.append(" ");
    stringBuilder2.append(str);
    Log.d("AutoManageHelper", stringBuilder2.toString());
    paramOnConnectionFailedListener = new zza(this, paramInt, paramGoogleApiClient, paramOnConnectionFailedListener);
    this.zzfik.put(paramInt, paramOnConnectionFailedListener);
    if (this.mStarted && zzp == null) {
      String str1 = String.valueOf(paramGoogleApiClient);
      StringBuilder stringBuilder = new StringBuilder(str1.length() + 11);
      stringBuilder.append("connecting ");
      stringBuilder.append(str1);
      Log.d("AutoManageHelper", stringBuilder.toString());
      paramGoogleApiClient.connect();
    } 
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
    if (paramInt < 0) {
      Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
      return;
    } 
    zza zza = (zza)this.zzfik.get(paramInt);
    if (zza != null) {
      zzbp(paramInt);
      GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza.zzfin;
      if (onConnectionFailedListener != null)
        onConnectionFailedListener.onConnectionFailed(paramConnectionResult); 
    } 
  }
  
  protected final void zzafw() {
    for (byte b = 0; b < this.zzfik.size(); b++) {
      zza zza = zzbq(b);
      if (zza != null)
        zza.zzfim.connect(); 
    } 
  }
  
  public final void zzbp(int paramInt) {
    zza zza = (zza)this.zzfik.get(paramInt);
    this.zzfik.remove(paramInt);
    if (zza != null) {
      zza.zzfim.unregisterConnectionFailedListener(zza);
      zza.zzfim.disconnect();
    } 
  }
  
  final class zza implements GoogleApiClient.OnConnectionFailedListener {
    public final int zzfil;
    
    public final GoogleApiClient zzfim;
    
    public final GoogleApiClient.OnConnectionFailedListener zzfin;
    
    public zza(zzi this$0, int param1Int, GoogleApiClient param1GoogleApiClient, GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      this.zzfil = param1Int;
      this.zzfim = param1GoogleApiClient;
      this.zzfin = param1OnConnectionFailedListener;
      param1GoogleApiClient.registerConnectionFailedListener(this);
    }
    
    public final void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult) {
      String str = String.valueOf(param1ConnectionResult);
      StringBuilder stringBuilder = new StringBuilder(str.length() + 27);
      stringBuilder.append("beginFailureResolution for ");
      stringBuilder.append(str);
      Log.d("AutoManageHelper", stringBuilder.toString());
      this.zzfio.zzb(param1ConnectionResult, this.zzfil);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */