package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zze;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
  private T zzgou;
  
  private Bundle zzgov;
  
  private LinkedList<zzi> zzgow;
  
  private final zzo<T> zzgox = new zzb(this);
  
  private final void zza(Bundle paramBundle, zzi paramzzi) {
    T t = this.zzgou;
    if (t != null) {
      paramzzi.zzb((LifecycleDelegate)t);
      return;
    } 
    if (this.zzgow == null)
      this.zzgow = new LinkedList<zzi>(); 
    this.zzgow.add(paramzzi);
    if (paramBundle != null) {
      Bundle bundle = this.zzgov;
      if (bundle == null) {
        this.zzgov = (Bundle)paramBundle.clone();
      } else {
        bundle.putAll(paramBundle);
      } 
    } 
    zza(this.zzgox);
  }
  
  public static void zzb(FrameLayout paramFrameLayout) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    Context context = paramFrameLayout.getContext();
    int i = googleApiAvailability.isGooglePlayServicesAvailable(context);
    String str1 = zzt.zzi(context, i);
    String str2 = zzt.zzk(context, i);
    LinearLayout linearLayout = new LinearLayout(paramFrameLayout.getContext());
    linearLayout.setOrientation(1);
    linearLayout.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView((View)linearLayout);
    TextView textView = new TextView(paramFrameLayout.getContext());
    textView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    textView.setText(str1);
    linearLayout.addView((View)textView);
    Intent intent = zze.zza(context, i, null);
    if (intent != null) {
      Button button = new Button(context);
      button.setId(16908313);
      button.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
      button.setText(str2);
      linearLayout.addView((View)button);
      button.setOnClickListener(new zzf(context, intent));
    } 
  }
  
  private final void zzcv(int paramInt) {
    while (!this.zzgow.isEmpty() && ((zzi)this.zzgow.getLast()).getState() >= paramInt)
      this.zzgow.removeLast(); 
  }
  
  public final void onCreate(Bundle paramBundle) {
    zza(paramBundle, new zzd(this, paramBundle));
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    FrameLayout frameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zza(paramBundle, new zze(this, frameLayout, paramLayoutInflater, paramViewGroup, paramBundle));
    if (this.zzgou == null)
      zza(frameLayout); 
    return (View)frameLayout;
  }
  
  public final void onDestroy() {
    T t = this.zzgou;
    if (t != null) {
      t.onDestroy();
      return;
    } 
    zzcv(1);
  }
  
  public final void onDestroyView() {
    T t = this.zzgou;
    if (t != null) {
      t.onDestroyView();
      return;
    } 
    zzcv(2);
  }
  
  public final void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2) {
    zza(paramBundle2, new zzc(this, paramActivity, paramBundle1, paramBundle2));
  }
  
  public final void onLowMemory() {
    T t = this.zzgou;
    if (t != null)
      t.onLowMemory(); 
  }
  
  public final void onPause() {
    T t = this.zzgou;
    if (t != null) {
      t.onPause();
      return;
    } 
    zzcv(5);
  }
  
  public final void onResume() {
    zza((Bundle)null, new zzh(this));
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    T t = this.zzgou;
    if (t != null) {
      t.onSaveInstanceState(paramBundle);
      return;
    } 
    Bundle bundle = this.zzgov;
    if (bundle != null)
      paramBundle.putAll(bundle); 
  }
  
  public final void onStart() {
    zza((Bundle)null, new zzg(this));
  }
  
  public final void onStop() {
    T t = this.zzgou;
    if (t != null) {
      t.onStop();
      return;
    } 
    zzcv(4);
  }
  
  protected void zza(FrameLayout paramFrameLayout) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    Context context = paramFrameLayout.getContext();
    int i = googleApiAvailability.isGooglePlayServicesAvailable(context);
    String str1 = zzt.zzi(context, i);
    String str2 = zzt.zzk(context, i);
    LinearLayout linearLayout = new LinearLayout(paramFrameLayout.getContext());
    linearLayout.setOrientation(1);
    linearLayout.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView((View)linearLayout);
    TextView textView = new TextView(paramFrameLayout.getContext());
    textView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    textView.setText(str1);
    linearLayout.addView((View)textView);
    Intent intent = zze.zza(context, i, null);
    if (intent != null) {
      Button button = new Button(context);
      button.setId(16908313);
      button.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
      button.setText(str2);
      linearLayout.addView((View)button);
      button.setOnClickListener(new zzf(context, intent));
    } 
  }
  
  protected abstract void zza(zzo<T> paramzzo);
  
  public final T zzaob() {
    return this.zzgou;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */