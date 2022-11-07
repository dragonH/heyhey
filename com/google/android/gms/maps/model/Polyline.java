package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import java.util.List;

public final class Polyline {
  private final IPolylineDelegate zzijv;
  
  public Polyline(IPolylineDelegate paramIPolylineDelegate) {
    this.zzijv = (IPolylineDelegate)zzbp.zzu(paramIPolylineDelegate);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Polyline))
      return false; 
    try {
      return this.zzijv.equalsRemote(((Polyline)paramObject).zzijv);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getColor() {
    try {
      return this.zzijv.getColor();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @NonNull
  public final Cap getEndCap() {
    try {
      return this.zzijv.getEndCap().zzatr();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getId() {
    try {
      return this.zzijv.getId();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getJointType() {
    try {
      return this.zzijv.getJointType();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Nullable
  public final List<PatternItem> getPattern() {
    try {
      return PatternItem.zzad(this.zzijv.getPattern());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final List<LatLng> getPoints() {
    try {
      return this.zzijv.getPoints();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @NonNull
  public final Cap getStartCap() {
    try {
      return this.zzijv.getStartCap().zzatr();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Nullable
  public final Object getTag() {
    try {
      return zzn.zzx(this.zzijv.getTag());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getWidth() {
    try {
      return this.zzijv.getWidth();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getZIndex() {
    try {
      return this.zzijv.getZIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzijv.hashCodeRemote();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isClickable() {
    try {
      return this.zzijv.isClickable();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isGeodesic() {
    try {
      return this.zzijv.isGeodesic();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isVisible() {
    try {
      return this.zzijv.isVisible();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void remove() {
    try {
      this.zzijv.remove();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setClickable(boolean paramBoolean) {
    try {
      this.zzijv.setClickable(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setColor(int paramInt) {
    try {
      this.zzijv.setColor(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setEndCap(@NonNull Cap paramCap) {
    zzbp.zzb(paramCap, "endCap must not be null");
    try {
      this.zzijv.setEndCap(paramCap);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setGeodesic(boolean paramBoolean) {
    try {
      this.zzijv.setGeodesic(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setJointType(int paramInt) {
    try {
      this.zzijv.setJointType(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setPattern(@Nullable List<PatternItem> paramList) {
    try {
      this.zzijv.setPattern(paramList);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setPoints(List<LatLng> paramList) {
    try {
      this.zzijv.setPoints(paramList);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setStartCap(@NonNull Cap paramCap) {
    zzbp.zzb(paramCap, "startCap must not be null");
    try {
      this.zzijv.setStartCap(paramCap);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTag(@Nullable Object paramObject) {
    try {
      this.zzijv.setTag(zzn.zzw(paramObject));
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setVisible(boolean paramBoolean) {
    try {
      this.zzijv.setVisible(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setWidth(float paramFloat) {
    try {
      this.zzijv.setWidth(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZIndex(float paramFloat) {
    try {
      this.zzijv.setZIndex(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/Polyline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */