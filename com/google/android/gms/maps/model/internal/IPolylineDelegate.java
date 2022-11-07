package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface IPolylineDelegate extends IInterface {
  boolean equalsRemote(IPolylineDelegate paramIPolylineDelegate) throws RemoteException;
  
  int getColor() throws RemoteException;
  
  Cap getEndCap() throws RemoteException;
  
  String getId() throws RemoteException;
  
  int getJointType() throws RemoteException;
  
  List<PatternItem> getPattern() throws RemoteException;
  
  List<LatLng> getPoints() throws RemoteException;
  
  Cap getStartCap() throws RemoteException;
  
  IObjectWrapper getTag() throws RemoteException;
  
  float getWidth() throws RemoteException;
  
  float getZIndex() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean isClickable() throws RemoteException;
  
  boolean isGeodesic() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setClickable(boolean paramBoolean) throws RemoteException;
  
  void setColor(int paramInt) throws RemoteException;
  
  void setEndCap(Cap paramCap) throws RemoteException;
  
  void setGeodesic(boolean paramBoolean) throws RemoteException;
  
  void setJointType(int paramInt) throws RemoteException;
  
  void setPattern(List<PatternItem> paramList) throws RemoteException;
  
  void setPoints(List<LatLng> paramList) throws RemoteException;
  
  void setStartCap(Cap paramCap) throws RemoteException;
  
  void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void setVisible(boolean paramBoolean) throws RemoteException;
  
  void setWidth(float paramFloat) throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  public static abstract class zza extends zzec implements IPolylineDelegate {
    public static IPolylineDelegate zzbj(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
      return (iInterface instanceof IPolylineDelegate) ? (IPolylineDelegate)iInterface : new zzv(param1IBinder);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      throw new NoSuchMethodError();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/IPolylineDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */