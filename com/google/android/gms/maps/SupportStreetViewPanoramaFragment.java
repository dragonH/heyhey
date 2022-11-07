package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
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

public class SupportStreetViewPanoramaFragment extends Fragment {
  private final zzb zziib = new zzb(this);
  
  public static SupportStreetViewPanoramaFragment newInstance() {
    return new SupportStreetViewPanoramaFragment();
  }
  
  public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions paramStreetViewPanoramaOptions) {
    SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("StreetViewPanoramaOptions", (Parcelable)paramStreetViewPanoramaOptions);
    supportStreetViewPanoramaFragment.setArguments(bundle);
    return supportStreetViewPanoramaFragment;
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback) {
    zzbp.zzfy("getStreetViewPanoramaAsync() must be called on the main thread");
    this.zziib.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader()); 
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity) {
    super.onAttach(paramActivity);
    zzb.zza(this.zziib, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zziib.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return this.zziib.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  public void onDestroy() {
    this.zziib.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.zziib.onDestroyView();
    super.onDestroyView();
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(this.zziib, paramActivity);
      Bundle bundle = new Bundle();
      this();
      this.zziib.onInflate(paramActivity, bundle, paramBundle);
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onLowMemory() {
    this.zziib.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause() {
    this.zziib.onPause();
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    this.zziib.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader()); 
    super.onSaveInstanceState(paramBundle);
    this.zziib.onSaveInstanceState(paramBundle);
  }
  
  public void setArguments(Bundle paramBundle) {
    super.setArguments(paramBundle);
  }
  
  static final class zza implements StreetViewLifecycleDelegate {
    private final Fragment zzgph;
    
    private final IStreetViewPanoramaFragmentDelegate zzihm;
    
    public zza(Fragment param1Fragment, IStreetViewPanoramaFragmentDelegate param1IStreetViewPanoramaFragmentDelegate) {
      this.zzihm = (IStreetViewPanoramaFragmentDelegate)zzbp.zzu(param1IStreetViewPanoramaFragmentDelegate);
      this.zzgph = (Fragment)zzbp.zzu(param1Fragment);
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      try {
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate = this.zzihm;
        zzal zzal = new zzal();
        this(this, param1OnStreetViewPanoramaReadyCallback);
        iStreetViewPanoramaFragmentDelegate.getStreetViewPanoramaAsync((zzbp)zzal);
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
        Bundle bundle2 = this.zzgph.getArguments();
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
    
    private final Fragment zzgph;
    
    private zzo<SupportStreetViewPanoramaFragment.zza> zzigw;
    
    private final List<OnStreetViewPanoramaReadyCallback> zziho = new ArrayList<OnStreetViewPanoramaReadyCallback>();
    
    zzb(Fragment param1Fragment) {
      this.zzgph = param1Fragment;
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
          zzo<SupportStreetViewPanoramaFragment.zza> zzo1 = this.zzigw;
          SupportStreetViewPanoramaFragment.zza zza1 = new SupportStreetViewPanoramaFragment.zza();
          this(this.zzgph, iStreetViewPanoramaFragmentDelegate);
          zzo1.zza((LifecycleDelegate)zza1);
          for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zziho)
            ((SupportStreetViewPanoramaFragment.zza)zzaob()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback); 
          this.zziho.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      if (zzaob() != null) {
        ((SupportStreetViewPanoramaFragment.zza)zzaob()).getStreetViewPanoramaAsync(param1OnStreetViewPanoramaReadyCallback);
        return;
      } 
      this.zziho.add(param1OnStreetViewPanoramaReadyCallback);
    }
    
    protected final void zza(zzo<SupportStreetViewPanoramaFragment.zza> param1zzo) {
      this.zzigw = param1zzo;
      zzatn();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/SupportStreetViewPanoramaFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */