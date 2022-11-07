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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
  private final zzb zzihl = new zzb(this);
  
  public static StreetViewPanoramaFragment newInstance() {
    return new StreetViewPanoramaFragment();
  }
  
  public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions paramStreetViewPanoramaOptions) {
    StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("StreetViewPanoramaOptions", (Parcelable)paramStreetViewPanoramaOptions);
    streetViewPanoramaFragment.setArguments(bundle);
    return streetViewPanoramaFragment;
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback) {
    zzbp.zzfy("getStreetViewPanoramaAsync() must be called on the main thread");
    this.zzihl.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader()); 
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity) {
    super.onAttach(paramActivity);
    zzb.zza(this.zzihl, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzihl.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return this.zzihl.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  public void onDestroy() {
    this.zzihl.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.zzihl.onDestroyView();
    super.onDestroyView();
  }
  
  @SuppressLint({"NewApi"})
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(this.zzihl, paramActivity);
      Bundle bundle = new Bundle();
      this();
      this.zzihl.onInflate(paramActivity, bundle, paramBundle);
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onLowMemory() {
    this.zzihl.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause() {
    this.zzihl.onPause();
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    this.zzihl.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader()); 
    super.onSaveInstanceState(paramBundle);
    this.zzihl.onSaveInstanceState(paramBundle);
  }
  
  public void setArguments(Bundle paramBundle) {
    super.setArguments(paramBundle);
  }
  
  static final class zza implements StreetViewLifecycleDelegate {
    private final Fragment zzgpe;
    
    private final IStreetViewPanoramaFragmentDelegate zzihm;
    
    public zza(Fragment param1Fragment, IStreetViewPanoramaFragmentDelegate param1IStreetViewPanoramaFragmentDelegate) {
      this.zzihm = (IStreetViewPanoramaFragmentDelegate)zzbp.zzu(param1IStreetViewPanoramaFragmentDelegate);
      this.zzgpe = (Fragment)zzbp.zzu(param1Fragment);
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      try {
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate = this.zzihm;
        zzah zzah = new zzah();
        this(this, param1OnStreetViewPanoramaReadyCallback);
        iStreetViewPanoramaFragmentDelegate.getStreetViewPanoramaAsync((zzbp)zzah);
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
        if (bundle2 != null && bundle2.containsKey("StreetViewPanoramaOptions"))
          zzby.zza(bundle1, "StreetViewPanoramaOptions", bundle2.getParcelable("StreetViewPanoramaOptions")); 
        this.zzihm.onCreate(bundle1);
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
        IObjectWrapper iObjectWrapper = this.zzihm.onCreateView(zzn.zzw(param1LayoutInflater), zzn.zzw(param1ViewGroup), bundle);
        zzby.zzd(bundle, param1Bundle);
        return (View)zzn.zzx(iObjectWrapper);
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroy() {
      try {
        this.zzihm.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      try {
        this.zzihm.onDestroyView();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      try {
        param1Bundle1 = new Bundle();
        this();
        zzby.zzd(param1Bundle2, param1Bundle1);
        this.zzihm.onInflate(zzn.zzw(param1Activity), null, param1Bundle1);
        zzby.zzd(param1Bundle1, param1Bundle2);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onLowMemory() {
      try {
        this.zzihm.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzihm.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzihm.onResume();
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
        this.zzihm.onSaveInstanceState(bundle);
        zzby.zzd(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {}
    
    public final void onStop() {}
  }
  
  static final class zzb extends com.google.android.gms.dynamic.zza<zza> {
    private Activity mActivity;
    
    private final Fragment zzgpe;
    
    private zzo<StreetViewPanoramaFragment.zza> zzigw;
    
    private final List<OnStreetViewPanoramaReadyCallback> zziho = new ArrayList<OnStreetViewPanoramaReadyCallback>();
    
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
          IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate = zzbz.zzdj((Context)this.mActivity).zzab(zzn.zzw(this.mActivity));
          zzo<StreetViewPanoramaFragment.zza> zzo1 = this.zzigw;
          StreetViewPanoramaFragment.zza zza1 = new StreetViewPanoramaFragment.zza();
          this(this.zzgpe, iStreetViewPanoramaFragmentDelegate);
          zzo1.zza((LifecycleDelegate)zza1);
          for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zziho)
            ((StreetViewPanoramaFragment.zza)zzaob()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback); 
          this.zziho.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      if (zzaob() != null) {
        ((StreetViewPanoramaFragment.zza)zzaob()).getStreetViewPanoramaAsync(param1OnStreetViewPanoramaReadyCallback);
        return;
      } 
      this.zziho.add(param1OnStreetViewPanoramaReadyCallback);
    }
    
    protected final void zza(zzo<StreetViewPanoramaFragment.zza> param1zzo) {
      this.zzigw = param1zzo;
      zzatn();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/StreetViewPanoramaFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */