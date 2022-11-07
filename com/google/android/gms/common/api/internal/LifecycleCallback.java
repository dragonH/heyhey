package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LifecycleCallback {
  protected final zzcg zzfoo;
  
  protected LifecycleCallback(zzcg paramzzcg) {
    this.zzfoo = paramzzcg;
  }
  
  @Keep
  private static zzcg getChimeraLifecycleFragmentImpl(zzcf paramzzcf) {
    throw new IllegalStateException("Method not available in SDK.");
  }
  
  protected static zzcg zzb(zzcf paramzzcf) {
    if (paramzzcf.zzaig())
      return zzdb.zza(paramzzcf.zzaii()); 
    if (paramzzcf.isAndroid())
      return zzch.zzo(paramzzcf.zzaih()); 
    throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
  }
  
  public static zzcg zzn(Activity paramActivity) {
    return zzb(new zzcf(paramActivity));
  }
  
  @MainThread
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final Activity getActivity() {
    return this.zzfoo.zzaij();
  }
  
  @MainThread
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @MainThread
  public void onCreate(Bundle paramBundle) {}
  
  @MainThread
  public void onDestroy() {}
  
  @MainThread
  public void onResume() {}
  
  @MainThread
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  @MainThread
  public void onStart() {}
  
  @MainThread
  public void onStop() {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/LifecycleCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */