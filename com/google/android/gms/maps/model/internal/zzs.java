package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface zzs extends IInterface {
  int getFillColor() throws RemoteException;
  
  List getHoles() throws RemoteException;
  
  String getId() throws RemoteException;
  
  List<LatLng> getPoints() throws RemoteException;
  
  int getStrokeColor() throws RemoteException;
  
  int getStrokeJointType() throws RemoteException;
  
  List<PatternItem> getStrokePattern() throws RemoteException;
  
  float getStrokeWidth() throws RemoteException;
  
  IObjectWrapper getTag() throws RemoteException;
  
  float getZIndex() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean isClickable() throws RemoteException;
  
  boolean isGeodesic() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setClickable(boolean paramBoolean) throws RemoteException;
  
  void setFillColor(int paramInt) throws RemoteException;
  
  void setGeodesic(boolean paramBoolean) throws RemoteException;
  
  void setHoles(List paramList) throws RemoteException;
  
  void setPoints(List<LatLng> paramList) throws RemoteException;
  
  void setStrokeColor(int paramInt) throws RemoteException;
  
  void setStrokeJointType(int paramInt) throws RemoteException;
  
  void setStrokePattern(List<PatternItem> paramList) throws RemoteException;
  
  void setStrokeWidth(float paramFloat) throws RemoteException;
  
  void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void setVisible(boolean paramBoolean) throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  boolean zzb(zzs paramzzs) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */