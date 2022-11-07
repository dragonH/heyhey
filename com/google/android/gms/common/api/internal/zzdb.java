package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzdb extends Fragment implements zzcg {
  private static WeakHashMap<FragmentActivity, WeakReference<zzdb>> zzfop = new WeakHashMap<FragmentActivity, WeakReference<zzdb>>();
  
  private int zzbyz = 0;
  
  private Map<String, LifecycleCallback> zzfoq = (Map<String, LifecycleCallback>)new ArrayMap();
  
  private Bundle zzfor;
  
  public static zzdb zza(FragmentActivity paramFragmentActivity) {
    WeakReference<zzdb> weakReference = zzfop.get(paramFragmentActivity);
    if (weakReference != null) {
      zzdb zzdb1 = weakReference.get();
      if (zzdb1 != null)
        return zzdb1; 
    } 
    try {
      zzdb zzdb2 = (zzdb)paramFragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
      if (zzdb2 != null) {
        zzdb zzdb3 = zzdb2;
        if (zzdb2.isRemoving()) {
          zzdb3 = new zzdb();
          paramFragmentActivity.getSupportFragmentManager().beginTransaction().add(zzdb3, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
          zzfop.put(paramFragmentActivity, new WeakReference<zzdb>(zzdb3));
          return zzdb3;
        } 
        zzfop.put(paramFragmentActivity, new WeakReference<zzdb>(zzdb3));
        return zzdb3;
      } 
      zzdb zzdb1 = new zzdb();
      paramFragmentActivity.getSupportFragmentManager().beginTransaction().add(zzdb1, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
      zzfop.put(paramFragmentActivity, new WeakReference<zzdb>(zzdb1));
      return zzdb1;
    } catch (ClassCastException classCastException) {
      throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", classCastException);
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent); 
  }
  
  public final void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzbyz = 1;
    this.zzfor = paramBundle;
    for (Map.Entry<String, LifecycleCallback> entry : this.zzfoq.entrySet()) {
      LifecycleCallback lifecycleCallback = (LifecycleCallback)entry.getValue();
      if (paramBundle != null) {
        Bundle bundle = paramBundle.getBundle((String)entry.getKey());
      } else {
        entry = null;
      } 
      lifecycleCallback.onCreate((Bundle)entry);
    } 
  }
  
  public final void onDestroy() {
    super.onDestroy();
    this.zzbyz = 5;
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onDestroy(); 
  }
  
  public final void onResume() {
    super.onResume();
    this.zzbyz = 3;
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onResume(); 
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null)
      return; 
    for (Map.Entry<String, LifecycleCallback> entry : this.zzfoq.entrySet()) {
      Bundle bundle = new Bundle();
      ((LifecycleCallback)entry.getValue()).onSaveInstanceState(bundle);
      paramBundle.putBundle((String)entry.getKey(), bundle);
    } 
  }
  
  public final void onStart() {
    super.onStart();
    this.zzbyz = 2;
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onStart(); 
  }
  
  public final void onStop() {
    super.onStop();
    this.zzbyz = 4;
    Iterator<LifecycleCallback> iterator = this.zzfoq.values().iterator();
    while (iterator.hasNext())
      ((LifecycleCallback)iterator.next()).onStop(); 
  }
  
  public final <T extends LifecycleCallback> T zza(String paramString, Class<T> paramClass) {
    return paramClass.cast(this.zzfoq.get(paramString));
  }
  
  public final void zza(String paramString, @NonNull LifecycleCallback paramLifecycleCallback) {
    if (!this.zzfoq.containsKey(paramString)) {
      this.zzfoq.put(paramString, paramLifecycleCallback);
      if (this.zzbyz > 0)
        (new Handler(Looper.getMainLooper())).post(new zzdc(this, paramLifecycleCallback, paramString)); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 59);
    stringBuilder.append("LifecycleCallback with tag ");
    stringBuilder.append(paramString);
    stringBuilder.append(" already added to this fragment.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */