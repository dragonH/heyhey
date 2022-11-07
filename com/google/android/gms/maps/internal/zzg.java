package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zze;
import com.google.android.gms.maps.model.internal.zzh;
import com.google.android.gms.maps.model.internal.zzj;
import com.google.android.gms.maps.model.internal.zzk;
import com.google.android.gms.maps.model.internal.zzp;
import com.google.android.gms.maps.model.internal.zzq;
import com.google.android.gms.maps.model.internal.zzs;
import com.google.android.gms.maps.model.internal.zzt;
import com.google.android.gms.maps.model.internal.zzw;
import com.google.android.gms.maps.model.internal.zzx;

public final class zzg extends zzeb implements IGoogleMapDelegate {
  zzg(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
  }
  
  public final zzd addCircle(CircleOptions paramCircleOptions) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramCircleOptions);
    parcel = zza(35, parcel);
    zzd zzd = zze.zzbd(parcel.readStrongBinder());
    parcel.recycle();
    return zzd;
  }
  
  public final com.google.android.gms.maps.model.internal.zzg addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramGroundOverlayOptions);
    Parcel parcel1 = zza(12, parcel2);
    com.google.android.gms.maps.model.internal.zzg zzg1 = zzh.zzbe(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzg1;
  }
  
  public final zzp addMarker(MarkerOptions paramMarkerOptions) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramMarkerOptions);
    Parcel parcel1 = zza(11, parcel2);
    zzp zzp = zzq.zzbh(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzp;
  }
  
  public final zzs addPolygon(PolygonOptions paramPolygonOptions) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramPolygonOptions);
    Parcel parcel1 = zza(10, parcel2);
    zzs zzs = zzt.zzbi(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzs;
  }
  
  public final IPolylineDelegate addPolyline(PolylineOptions paramPolylineOptions) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramPolylineOptions);
    Parcel parcel1 = zza(9, parcel2);
    IPolylineDelegate iPolylineDelegate = IPolylineDelegate.zza.zzbj(parcel1.readStrongBinder());
    parcel1.recycle();
    return iPolylineDelegate;
  }
  
  public final zzw addTileOverlay(TileOverlayOptions paramTileOverlayOptions) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramTileOverlayOptions);
    parcel = zza(13, parcel);
    zzw zzw = zzx.zzbk(parcel.readStrongBinder());
    parcel.recycle();
    return zzw;
  }
  
  public final void animateCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(5, parcel);
  }
  
  public final void animateCameraWithCallback(IObjectWrapper paramIObjectWrapper, zzc paramzzc) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel, paramzzc);
    zzb(6, parcel);
  }
  
  public final void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramzzc) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel.writeInt(paramInt);
    zzed.zza(parcel, paramzzc);
    zzb(7, parcel);
  }
  
  public final void clear() throws RemoteException {
    zzb(14, zzax());
  }
  
  public final CameraPosition getCameraPosition() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    CameraPosition cameraPosition = (CameraPosition)zzed.zza(parcel, CameraPosition.CREATOR);
    parcel.recycle();
    return cameraPosition;
  }
  
  public final zzj getFocusedBuilding() throws RemoteException {
    Parcel parcel = zza(44, zzax());
    zzj zzj = zzk.zzbf(parcel.readStrongBinder());
    parcel.recycle();
    return zzj;
  }
  
  public final void getMapAsync(zzap paramzzap) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzap);
    zzb(53, parcel);
  }
  
  public final int getMapType() throws RemoteException {
    Parcel parcel = zza(15, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final float getMaxZoomLevel() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getMinZoomLevel() throws RemoteException {
    Parcel parcel = zza(3, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final Location getMyLocation() throws RemoteException {
    Parcel parcel = zza(23, zzax());
    Location location = (Location)zzed.zza(parcel, Location.CREATOR);
    parcel.recycle();
    return location;
  }
  
  public final IProjectionDelegate getProjection() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zza(26, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
      if (iInterface instanceof IProjectionDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbr(iBinder);
      } 
    } 
    parcel.recycle();
    return (IProjectionDelegate)iInterface;
  }
  
  public final IUiSettingsDelegate getUiSettings() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zza(25, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
      if (iInterface instanceof IUiSettingsDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbx(iBinder);
      } 
    } 
    parcel.recycle();
    return (IUiSettingsDelegate)iInterface;
  }
  
  public final boolean isBuildingsEnabled() throws RemoteException {
    Parcel parcel = zza(40, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorEnabled() throws RemoteException {
    Parcel parcel = zza(19, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationEnabled() throws RemoteException {
    Parcel parcel = zza(21, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isTrafficEnabled() throws RemoteException {
    Parcel parcel = zza(17, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void moveCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(4, parcel);
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(54, parcel);
  }
  
  public final void onDestroy() throws RemoteException {
    zzb(57, zzax());
  }
  
  public final void onEnterAmbient(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(81, parcel);
  }
  
  public final void onExitAmbient() throws RemoteException {
    zzb(82, zzax());
  }
  
  public final void onLowMemory() throws RemoteException {
    zzb(58, zzax());
  }
  
  public final void onPause() throws RemoteException {
    zzb(56, zzax());
  }
  
  public final void onResume() throws RemoteException {
    zzb(55, zzax());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    parcel = zza(60, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
  
  public final void onStart() throws RemoteException {
    zzb(101, zzax());
  }
  
  public final void onStop() throws RemoteException {
    zzb(102, zzax());
  }
  
  public final void resetMinMaxZoomPreference() throws RemoteException {
    zzb(94, zzax());
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(41, parcel);
  }
  
  public final void setContentDescription(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzb(61, parcel);
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    parcel = zza(20, parcel);
    paramBoolean = zzed.zza(parcel);
    parcel.recycle();
    return paramBoolean;
  }
  
  public final void setInfoWindowAdapter(zzh paramzzh) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzh);
    zzb(33, parcel);
  }
  
  public final void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLngBounds);
    zzb(95, parcel);
  }
  
  public final void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramILocationSourceDelegate);
    zzb(24, parcel);
  }
  
  public final boolean setMapStyle(MapStyleOptions paramMapStyleOptions) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramMapStyleOptions);
    Parcel parcel1 = zza(91, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void setMapType(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(16, parcel);
  }
  
  public final void setMaxZoomPreference(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(93, parcel);
  }
  
  public final void setMinZoomPreference(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(92, parcel);
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(22, parcel);
  }
  
  public final void setOnCameraChangeListener(zzl paramzzl) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzl);
    zzb(27, parcel);
  }
  
  public final void setOnCameraIdleListener(zzn paramzzn) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzn);
    zzb(99, parcel);
  }
  
  public final void setOnCameraMoveCanceledListener(zzp paramzzp) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzp);
    zzb(98, parcel);
  }
  
  public final void setOnCameraMoveListener(zzr paramzzr) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzr);
    zzb(97, parcel);
  }
  
  public final void setOnCameraMoveStartedListener(zzt paramzzt) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzt);
    zzb(96, parcel);
  }
  
  public final void setOnCircleClickListener(zzv paramzzv) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzv);
    zzb(89, parcel);
  }
  
  public final void setOnGroundOverlayClickListener(zzx paramzzx) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzx);
    zzb(83, parcel);
  }
  
  public final void setOnIndoorStateChangeListener(zzz paramzzz) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzz);
    zzb(45, parcel);
  }
  
  public final void setOnInfoWindowClickListener(zzab paramzzab) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzab);
    zzb(32, parcel);
  }
  
  public final void setOnInfoWindowCloseListener(zzad paramzzad) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzad);
    zzb(86, parcel);
  }
  
  public final void setOnInfoWindowLongClickListener(zzaf paramzzaf) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzaf);
    zzb(84, parcel);
  }
  
  public final void setOnMapClickListener(zzaj paramzzaj) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzaj);
    zzb(28, parcel);
  }
  
  public final void setOnMapLoadedCallback(zzal paramzzal) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzal);
    zzb(42, parcel);
  }
  
  public final void setOnMapLongClickListener(zzan paramzzan) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzan);
    zzb(29, parcel);
  }
  
  public final void setOnMarkerClickListener(zzar paramzzar) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzar);
    zzb(30, parcel);
  }
  
  public final void setOnMarkerDragListener(zzat paramzzat) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzat);
    zzb(31, parcel);
  }
  
  public final void setOnMyLocationButtonClickListener(zzav paramzzav) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzav);
    zzb(37, parcel);
  }
  
  public final void setOnMyLocationChangeListener(zzax paramzzax) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzax);
    zzb(36, parcel);
  }
  
  public final void setOnMyLocationClickListener(zzaz paramzzaz) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzaz);
    zzb(107, parcel);
  }
  
  public final void setOnPoiClickListener(zzbb paramzzbb) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbb);
    zzb(80, parcel);
  }
  
  public final void setOnPolygonClickListener(zzbd paramzzbd) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbd);
    zzb(85, parcel);
  }
  
  public final void setOnPolylineClickListener(zzbf paramzzbf) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbf);
    zzb(87, parcel);
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt1);
    parcel.writeInt(paramInt2);
    parcel.writeInt(paramInt3);
    parcel.writeInt(paramInt4);
    zzb(39, parcel);
  }
  
  public final void setTrafficEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(18, parcel);
  }
  
  public final void setWatermarkEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(51, parcel);
  }
  
  public final void snapshot(zzbs paramzzbs, IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbs);
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(38, parcel);
  }
  
  public final void snapshotForTest(zzbs paramzzbs) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbs);
    zzb(71, parcel);
  }
  
  public final void stopAnimation() throws RemoteException {
    zzb(8, zzax());
  }
  
  public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
    Parcel parcel = zza(59, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */