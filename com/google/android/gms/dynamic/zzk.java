package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzk extends IInterface {
  Bundle getArguments() throws RemoteException;
  
  int getId() throws RemoteException;
  
  boolean getRetainInstance() throws RemoteException;
  
  String getTag() throws RemoteException;
  
  int getTargetRequestCode() throws RemoteException;
  
  boolean getUserVisibleHint() throws RemoteException;
  
  IObjectWrapper getView() throws RemoteException;
  
  boolean isAdded() throws RemoteException;
  
  boolean isDetached() throws RemoteException;
  
  boolean isHidden() throws RemoteException;
  
  boolean isInLayout() throws RemoteException;
  
  boolean isRemoving() throws RemoteException;
  
  boolean isResumed() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void setHasOptionsMenu(boolean paramBoolean) throws RemoteException;
  
  void setMenuVisibility(boolean paramBoolean) throws RemoteException;
  
  void setRetainInstance(boolean paramBoolean) throws RemoteException;
  
  void setUserVisibleHint(boolean paramBoolean) throws RemoteException;
  
  void startActivity(Intent paramIntent) throws RemoteException;
  
  void startActivityForResult(Intent paramIntent, int paramInt) throws RemoteException;
  
  IObjectWrapper zzaoc() throws RemoteException;
  
  zzk zzaod() throws RemoteException;
  
  IObjectWrapper zzaoe() throws RemoteException;
  
  zzk zzaof() throws RemoteException;
  
  void zzv(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void zzw(IObjectWrapper paramIObjectWrapper) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */