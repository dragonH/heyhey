package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzj extends zzl {
  private Fragment zzgpe;
  
  private zzj(Fragment paramFragment) {
    this.zzgpe = paramFragment;
  }
  
  public static zzj zza(Fragment paramFragment) {
    return (paramFragment != null) ? new zzj(paramFragment) : null;
  }
  
  public final Bundle getArguments() {
    return this.zzgpe.getArguments();
  }
  
  public final int getId() {
    return this.zzgpe.getId();
  }
  
  public final boolean getRetainInstance() {
    return this.zzgpe.getRetainInstance();
  }
  
  public final String getTag() {
    return this.zzgpe.getTag();
  }
  
  public final int getTargetRequestCode() {
    return this.zzgpe.getTargetRequestCode();
  }
  
  public final boolean getUserVisibleHint() {
    return this.zzgpe.getUserVisibleHint();
  }
  
  public final IObjectWrapper getView() {
    return zzn.zzw(this.zzgpe.getView());
  }
  
  public final boolean isAdded() {
    return this.zzgpe.isAdded();
  }
  
  public final boolean isDetached() {
    return this.zzgpe.isDetached();
  }
  
  public final boolean isHidden() {
    return this.zzgpe.isHidden();
  }
  
  public final boolean isInLayout() {
    return this.zzgpe.isInLayout();
  }
  
  public final boolean isRemoving() {
    return this.zzgpe.isRemoving();
  }
  
  public final boolean isResumed() {
    return this.zzgpe.isResumed();
  }
  
  public final boolean isVisible() {
    return this.zzgpe.isVisible();
  }
  
  public final void setHasOptionsMenu(boolean paramBoolean) {
    this.zzgpe.setHasOptionsMenu(paramBoolean);
  }
  
  public final void setMenuVisibility(boolean paramBoolean) {
    this.zzgpe.setMenuVisibility(paramBoolean);
  }
  
  public final void setRetainInstance(boolean paramBoolean) {
    this.zzgpe.setRetainInstance(paramBoolean);
  }
  
  public final void setUserVisibleHint(boolean paramBoolean) {
    this.zzgpe.setUserVisibleHint(paramBoolean);
  }
  
  public final void startActivity(Intent paramIntent) {
    this.zzgpe.startActivity(paramIntent);
  }
  
  public final void startActivityForResult(Intent paramIntent, int paramInt) {
    this.zzgpe.startActivityForResult(paramIntent, paramInt);
  }
  
  public final IObjectWrapper zzaoc() {
    return zzn.zzw(this.zzgpe.getActivity());
  }
  
  public final zzk zzaod() {
    return zza(this.zzgpe.getParentFragment());
  }
  
  public final IObjectWrapper zzaoe() {
    return zzn.zzw(this.zzgpe.getResources());
  }
  
  public final zzk zzaof() {
    return zza(this.zzgpe.getTargetFragment());
  }
  
  public final void zzv(IObjectWrapper paramIObjectWrapper) {
    View view = zzn.<View>zzx(paramIObjectWrapper);
    this.zzgpe.registerForContextMenu(view);
  }
  
  public final void zzw(IObjectWrapper paramIObjectWrapper) {
    View view = zzn.<View>zzx(paramIObjectWrapper);
    this.zzgpe.unregisterForContextMenu(view);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */