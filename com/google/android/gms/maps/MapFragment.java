package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class MapFragment extends Fragment {
  private final zzb zzigt = new zzb(this);
  
  public static MapFragment newInstance() {
    return new MapFragment();
  }
  
  public static MapFragment newInstance(GoogleMapOptions paramGoogleMapOptions) {
    MapFragment mapFragment = new MapFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("MapOptions", (Parcelable)paramGoogleMapOptions);
    mapFragment.setArguments(bundle);
    return mapFragment;
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback) {
    zzbp.zzfy("getMapAsync must be called on the main thread.");
    this.zzigt.getMapAsync(paramOnMapReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(MapFragment.class.getClassLoader()); 
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity) {
    super.onAttach(paramActivity);
    zzb.zza(this.zzigt, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzigt.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = this.zzigt.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    view.setClickable(true);
    return view;
  }
  
  public void onDestroy() {
    this.zzigt.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.zzigt.onDestroyView();
    super.onDestroyView();
  }
  
  public final void onEnterAmbient(Bundle paramBundle) {
    zzbp.zzfy("onEnterAmbient must be called on the main thread.");
    zzb zzb1 = this.zzigt;
    if (zzb1.zzaob() != null)
      ((zza)zzb1.zzaob()).onEnterAmbient(paramBundle); 
  }
  
  public final void onExitAmbient() {
    zzbp.zzfy("onExitAmbient must be called on the main thread.");
    zzb zzb1 = this.zzigt;
    if (zzb1.zzaob() != null)
      ((zza)zzb1.zzaob()).onExitAmbient(); 
  }
  
  @SuppressLint({"NewApi"})
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(this.zzigt, paramActivity);
      GoogleMapOptions googleMapOptions = GoogleMapOptions.createFromAttributes((Context)paramActivity, paramAttributeSet);
      Bundle bundle = new Bundle();
      this();
      bundle.putParcelable("MapOptions", (Parcelable)googleMapOptions);
      this.zzigt.onInflate(paramActivity, bundle, paramBundle);
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onLowMemory() {
    this.zzigt.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause() {
    this.zzigt.onPause();
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    this.zzigt.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(MapFragment.class.getClassLoader()); 
    super.onSaveInstanceState(paramBundle);
    this.zzigt.onSaveInstanceState(paramBundle);
  }
  
  public void onStart() {
    super.onStart();
    this.zzigt.onStart();
  }
  
  public void onStop() {
    this.zzigt.onStop();
    super.onStop();
  }
  
  public void setArguments(Bundle paramBundle) {
    super.setArguments(paramBundle);
  }
  
  static final class zza implements MapLifecycleDelegate {
    private final Fragment zzgpe;
    
    private final IMapFragmentDelegate zzigu;
    
    public zza(Fragment param1Fragment, IMapFragmentDelegate param1IMapFragmentDelegate) {
      this.zzigu = (IMapFragmentDelegate)zzbp.zzu(param1IMapFragmentDelegate);
      this.zzgpe = (Fragment)zzbp.zzu(param1Fragment);
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      try {
        IMapFragmentDelegate iMapFragmentDelegate = this.zzigu;
        zzab zzab = new zzab();
        this(this, param1OnMapReadyCallback);
        iMapFragmentDelegate.getMapAsync((zzap)zzab);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onCreate(Bundle param1Bundle) {
      try {
        Bundle bundle1 = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle1);
        Bundle bundle2 = this.zzgpe.getArguments();
        if (bundle2 != null && bundle2.containsKey("MapOptions"))
          zzby.zza(bundle1, "MapOptions", bundle2.getParcelable("MapOptions")); 
        this.zzigu.onCreate(bundle1);
        zzby.zzd(bundle1, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle);
        IObjectWrapper iObjectWrapper = this.zzigu.onCreateView(zzn.zzw(param1LayoutInflater), zzn.zzw(param1ViewGroup), bundle);
        zzby.zzd(bundle, param1Bundle);
        return (View)zzn.zzx(iObjectWrapper);
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroy() {
      try {
        this.zzigu.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      try {
        this.zzigu.onDestroyView();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onEnterAmbient(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle, bundle);
        this.zzigu.onEnterAmbient(bundle);
        zzby.zzd(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onExitAmbient() {
      try {
        this.zzigu.onExitAmbient();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      GoogleMapOptions googleMapOptions = (GoogleMapOptions)param1Bundle1.getParcelable("MapOptions");
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zzd(param1Bundle2, bundle);
        this.zzigu.onInflate(zzn.zzw(param1Activity), googleMapOptions, bundle);
        zzby.zzd(bundle, param1Bundle2);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onLowMemory() {
      try {
        this.zzigu.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzigu.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzigu.onResume();
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
        this.zzigu.onSaveInstanceState(bundle);
        zzby.zzd(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zzigu.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zzigu.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  static final class zzb extends com.google.android.gms.dynamic.zza<zza> {
    private Activity mActivity;
    
    private final Fragment zzgpe;
    
    private zzo<MapFragment.zza> zzigw;
    
    private final List<OnMapReadyCallback> zzigx = new ArrayList<OnMapReadyCallback>();
    
    zzb(Fragment param1Fragment) {
      this.zzgpe = param1Fragment;
    }
    
    private final void setActivity(Activity param1Activity) {
      this.mActivity = param1Activity;
      zzatn();
    }
    
    private final void zzatn() {
      if (this.mActivity != null && this.zzigw != null && zzaob() == null)
        try {
          MapsInitializer.initialize((Context)this.mActivity);
          IMapFragmentDelegate iMapFragmentDelegate = zzbz.zzdj((Context)this.mActivity).zzaa(zzn.zzw(this.mActivity));
          if (iMapFragmentDelegate == null)
            return; 
          zzo<MapFragment.zza> zzo1 = this.zzigw;
          MapFragment.zza zza1 = new MapFragment.zza();
          this(this.zzgpe, iMapFragmentDelegate);
          zzo1.zza((LifecycleDelegate)zza1);
          for (OnMapReadyCallback onMapReadyCallback : this.zzigx)
            ((MapFragment.zza)zzaob()).getMapAsync(onMapReadyCallback); 
          this.zzigx.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      if (zzaob() != null) {
        ((MapFragment.zza)zzaob()).getMapAsync(param1OnMapReadyCallback);
        return;
      } 
      this.zzigx.add(param1OnMapReadyCallback);
    }
    
    protected final void zza(zzo<MapFragment.zza> param1zzo) {
      this.zzigw = param1zzo;
      zzatn();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/MapFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */