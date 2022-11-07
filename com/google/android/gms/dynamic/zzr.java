package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzr extends zzl {
  private Fragment zzgph;
  
  private zzr(Fragment paramFragment) {
    this.zzgph = paramFragment;
  }
  
  public static zzr zza(Fragment paramFragment) {
    return (paramFragment != null) ? new zzr(paramFragment) : null;
  }
  
  public final Bundle getArguments() {
    return this.zzgph.getArguments();
  }
  
  public final int getId() {
    return this.zzgph.getId();
  }
  
  public final boolean getRetainInstance() {
    return this.zzgph.getRetainInstance();
  }
  
  public final String getTag() {
    return this.zzgph.getTag();
  }
  
  public final int getTargetRequestCode() {
    return this.zzgph.getTargetRequestCode();
  }
  
  public final boolean getUserVisibleHint() {
    return this.zzgph.getUserVisibleHint();
  }
  
  public final IObjectWrapper getView() {
    return zzn.zzw(this.zzgph.getView());
  }
  
  public final boolean isAdded() {
    return this.zzgph.isAdded();
  }
  
  public final boolean isDetached() {
    return this.zzgph.isDetached();
  }
  
  public final boolean isHidden() {
    return this.zzgph.isHidden();
  }
  
  public final boolean isInLayout() {
    return this.zzgph.isInLayout();
  }
  
  public final boolean isRemoving() {
    return this.zzgph.isRemoving();
  }
  
  public final boolean isResumed() {
    return this.zzgph.isResumed();
  }
  
  public final boolean isVisible() {
    return this.zzgph.isVisible();
  }
  
  public final void setHasOptionsMenu(boolean paramBoolean) {
    this.zzgph.setHasOptionsMenu(paramBoolean);
  }
  
  public final void setMenuVisibility(boolean paramBoolean) {
    this.zzgph.setMenuVisibility(paramBoolean);
  }
  
  public final void setRetainInstance(boolean paramBoolean) {
    this.zzgph.setRetainInstance(paramBoolean);
  }
  
  public final void setUserVisibleHint(boolean paramBoolean) {
    this.zzgph.setUserVisibleHint(paramBoolean);
  }
  
  public final void startActivity(Intent paramIntent) {
    this.zzgph.startActivity(paramIntent);
  }
  
  public final void startActivityForResult(Intent paramIntent, int paramInt) {
    this.zzgph.startActivityForResult(paramIntent, paramInt);
  }
  
  public final IObjectWrapper zzaoc() {
    return zzn.zzw(this.zzgph.getActivity());
  }
  
  public final zzk zzaod() {
    return zza(this.zzgph.getParentFragment());
  }
  
  public final IObjectWrapper zzaoe() {
    return zzn.zzw(this.zzgph.getResources());
  }
  
  public final zzk zzaof() {
    return zza(this.zzgph.getTargetFragment());
  }
  
  public final void zzv(IObjectWrapper paramIObjectWrapper) {
    View view = zzn.<View>zzx(paramIObjectWrapper);
    this.zzgph.registerForContextMenu(view);
  }
  
  public final void zzw(IObjectWrapper paramIObjectWrapper) {
    View view = zzn.<View>zzx(paramIObjectWrapper);
    this.zzgph.unregisterForContextMenu(view);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */