package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
  private final zzb zzigy;
  
  public MapView(Context paramContext) {
    super(paramContext);
    this.zzigy = new zzb((ViewGroup)this, paramContext, null);
    setClickable(true);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.zzigy = new zzb((ViewGroup)this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    setClickable(true);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzigy = new zzb((ViewGroup)this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    setClickable(true);
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions) {
    super(paramContext);
    this.zzigy = new zzb((ViewGroup)this, paramContext, paramGoogleMapOptions);
    setClickable(true);
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback) {
    zzbp.zzfy("getMapAsync() must be called on the main thread");
    this.zzigy.getMapAsync(paramOnMapReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      this.zzigy.onCreate(paramBundle);
      if (this.zzigy.zzaob() == null)
        com.google.android.gms.dynamic.zza.zzb(this); 
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public final void onDestroy() {
    this.zzigy.onDestroy();
  }
  
  public final void onEnterAmbient(Bundle paramBundle) {
    zzbp.zzfy("onEnterAmbient() must be called on the main thread");
    zzb zzb1 = this.zzigy;
    if (zzb1.zzaob() != null)
      ((zza)zzb1.zzaob()).onEnterAmbient(paramBundle); 
  }
  
  public final void onExitAmbient() {
    zzbp.zzfy("onExitAmbient() must be called on the main thread");
    zzb zzb1 = this.zzigy;
    if (zzb1.zzaob() != null)
      ((zza)zzb1.zzaob()).onExitAmbient(); 
  }
  
  public final void onLowMemory() {
    this.zzigy.onLowMemory();
  }
  
  public final void onPause() {
    this.zzigy.onPause();
  }
  
  public final void onResume() {
    this.zzigy.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    this.zzigy.onSaveInstanceState(paramBundle);
  }
  
  public final void onStart() {
    this.zzigy.onStart();
  }
  
  public final void onStop() {
    this.zzigy.onStop();
  }
  
  static final class zza implements MapLifecycleDelegate {
    private final ViewGroup zzigz;
    
    private final IMapViewDelegate zziha;
    
    private View zzihb;
    
    public zza(ViewGroup param1ViewGroup, IMapViewDelegate param1IMapViewDelegate) {
      this.zziha = (IMapViewDelegate)zzbp.zzu(param1IMapViewDelegate);
      this.zzigz = (ViewGroup)zzbp.zzu(param1ViewGroup);
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      try {
        IMapViewDelegate iMapViewDelegate = this.zziha;
        zzac zzac = new zzac();
        this(this, param1OnMapReadyCallback);
        iMapViewDelegate.getMapAsync((zzap)zzac);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onCreate(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle);
        this.zziha.onCreate(bundle);
        zzby.zzd(bundle, param1Bundle);
        this.zzihb = (View)zzn.zzx(this.zziha.getView());
        this.zzigz.removeAllViews();
        this.zzigz.addView(this.zzihb);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public final void onDestroy() {
      try {
        this.zziha.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public final void onEnterAmbient(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle);
        this.zziha.onEnterAmbient(bundle);
        zzby.zzd(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onExitAmbient() {
      try {
        this.zziha.onExitAmbient();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public final void onLowMemory() {
      try {
        this.zziha.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zziha.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zziha.onResume();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onSaveInstanceState(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle);
        this.zziha.onSaveInstanceState(bundle);
        zzby.zzd(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zziha.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zziha.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  static final class zzb extends com.google.android.gms.dynamic.zza<zza> {
    private zzo<MapView.zza> zzigw;
    
    private final List<OnMapReadyCallback> zzigx = new ArrayList<OnMapReadyCallback>();
    
    private final ViewGroup zzihc;
    
    private final Context zzihd;
    
    private final GoogleMapOptions zzihe;
    
    zzb(ViewGroup param1ViewGroup, Context param1Context, GoogleMapOptions param1GoogleMapOptions) {
      this.zzihc = param1ViewGroup;
      this.zzihd = param1Context;
      this.zzihe = param1GoogleMapOptions;
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      if (zzaob() != null) {
        ((MapView.zza)zzaob()).getMapAsync(param1OnMapReadyCallback);
        return;
      } 
      this.zzigx.add(param1OnMapReadyCallback);
    }
    
    protected final void zza(zzo<MapView.zza> param1zzo) {
      this.zzigw = param1zzo;
      if (param1zzo != null && zzaob() == null)
        try {
          MapsInitializer.initialize(this.zzihd);
          IMapViewDelegate iMapViewDelegate = zzbz.zzdj(this.zzihd).zza(zzn.zzw(this.zzihd), this.zzihe);
          if (iMapViewDelegate == null)
            return; 
          zzo<MapView.zza> zzo1 = this.zzigw;
          MapView.zza zza1 = new MapView.zza();
          this(this.zzihc, iMapViewDelegate);
          zzo1.zza((LifecycleDelegate)zza1);
          for (OnMapReadyCallback onMapReadyCallback : this.zzigx)
            ((MapView.zza)zzaob()).getMapAsync(onMapReadyCallback); 
          this.zzigx.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/MapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */