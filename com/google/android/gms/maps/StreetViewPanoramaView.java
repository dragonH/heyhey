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
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
  private final zzb zzihw;
  
  public StreetViewPanoramaView(Context paramContext) {
    super(paramContext);
    this.zzihw = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.zzihw = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzihw = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions) {
    super(paramContext);
    this.zzihw = new zzb((ViewGroup)this, paramContext, paramStreetViewPanoramaOptions);
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback) {
    zzbp.zzfy("getStreetViewPanoramaAsync() must be called on the main thread");
    this.zzihw.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      this.zzihw.onCreate(paramBundle);
      if (this.zzihw.zzaob() == null)
        com.google.android.gms.dynamic.zza.zzb(this); 
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public final void onDestroy() {
    this.zzihw.onDestroy();
  }
  
  public final void onLowMemory() {
    this.zzihw.onLowMemory();
  }
  
  public final void onPause() {
    this.zzihw.onPause();
  }
  
  public final void onResume() {
    this.zzihw.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    this.zzihw.onSaveInstanceState(paramBundle);
  }
  
  static final class zza implements StreetViewLifecycleDelegate {
    private final ViewGroup zzigz;
    
    private final IStreetViewPanoramaViewDelegate zzihx;
    
    private View zzihy;
    
    public zza(ViewGroup param1ViewGroup, IStreetViewPanoramaViewDelegate param1IStreetViewPanoramaViewDelegate) {
      this.zzihx = (IStreetViewPanoramaViewDelegate)zzbp.zzu(param1IStreetViewPanoramaViewDelegate);
      this.zzigz = (ViewGroup)zzbp.zzu(param1ViewGroup);
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      try {
        IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate = this.zzihx;
        zzaj zzaj = new zzaj();
        this(this, param1OnStreetViewPanoramaReadyCallback);
        iStreetViewPanoramaViewDelegate.getStreetViewPanoramaAsync((zzbp)zzaj);
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
        this.zzihx.onCreate(bundle);
        zzby.zzd(bundle, param1Bundle);
        this.zzihy = (View)zzn.zzx(this.zzihx.getView());
        this.zzigz.removeAllViews();
        this.zzigz.addView(this.zzihy);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onDestroy() {
      try {
        this.zzihx.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onLowMemory() {
      try {
        this.zzihx.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzihx.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzihx.onResume();
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
        this.zzihx.onSaveInstanceState(bundle);
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
    private zzo<StreetViewPanoramaView.zza> zzigw;
    
    private final ViewGroup zzihc;
    
    private final Context zzihd;
    
    private final List<OnStreetViewPanoramaReadyCallback> zziho = new ArrayList<OnStreetViewPanoramaReadyCallback>();
    
    private final StreetViewPanoramaOptions zzihz;
    
    zzb(ViewGroup param1ViewGroup, Context param1Context, StreetViewPanoramaOptions param1StreetViewPanoramaOptions) {
      this.zzihc = param1ViewGroup;
      this.zzihd = param1Context;
      this.zzihz = param1StreetViewPanoramaOptions;
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      if (zzaob() != null) {
        ((StreetViewPanoramaView.zza)zzaob()).getStreetViewPanoramaAsync(param1OnStreetViewPanoramaReadyCallback);
        return;
      } 
      this.zziho.add(param1OnStreetViewPanoramaReadyCallback);
    }
    
    protected final void zza(zzo<StreetViewPanoramaView.zza> param1zzo) {
      this.zzigw = param1zzo;
      if (param1zzo != null && zzaob() == null)
        try {
          IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate = zzbz.zzdj(this.zzihd).zza(zzn.zzw(this.zzihd), this.zzihz);
          param1zzo = this.zzigw;
          StreetViewPanoramaView.zza zza1 = new StreetViewPanoramaView.zza();
          this(this.zzihc, iStreetViewPanoramaViewDelegate);
          param1zzo.zza((LifecycleDelegate)zza1);
          for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zziho)
            ((StreetViewPanoramaView.zza)zzaob()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback); 
          this.zziho.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/StreetViewPanoramaView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */