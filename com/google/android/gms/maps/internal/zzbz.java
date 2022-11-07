package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbz {
  private static final String TAG = "zzbz";
  
  private static Context zziid;
  
  private static zze zziie;
  
  private static <T> T zza(ClassLoader paramClassLoader, String paramString) {
    try {
      return (T)zzd(((ClassLoader)zzbp.zzu(paramClassLoader)).loadClass(paramString));
    } catch (ClassNotFoundException classNotFoundException) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Unable to find dynamic class ".concat(str);
      } else {
        str = new String("Unable to find dynamic class ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  private static <T> T zzd(Class<?> paramClass) {
    String str;
    try {
      return (T)paramClass.newInstance();
    } catch (InstantiationException instantiationException) {
      str = paramClass.getName();
      if (str.length() != 0) {
        str = "Unable to instantiate the dynamic class ".concat(str);
      } else {
        str = new String("Unable to instantiate the dynamic class ");
      } 
      throw new IllegalStateException(str);
    } catch (IllegalAccessException illegalAccessException) {
      str = str.getName();
      if (str.length() != 0) {
        str = "Unable to call the default constructor of ".concat(str);
      } else {
        str = new String("Unable to call the default constructor of ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  public static zze zzdj(Context paramContext) throws GooglePlayServicesNotAvailableException {
    zzbp.zzu(paramContext);
    zze zze1 = zziie;
    if (zze1 != null)
      return zze1; 
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    if (i == 0) {
      IInterface iInterface;
      Log.i(TAG, "Making Creator dynamically");
      IBinder iBinder = zza(zzdk(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
      if (iBinder == null) {
        zze1 = null;
      } else {
        iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        if (iInterface instanceof zze) {
          iInterface = iInterface;
        } else {
          iInterface = new zzf(iBinder);
        } 
      } 
      zziie = (zze)iInterface;
      try {
        iInterface.zzi(zzn.zzw(zzdk(paramContext).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        return zziie;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    } 
    throw new GooglePlayServicesNotAvailableException(i);
  }
  
  private static Context zzdk(Context paramContext) {
    Context context = zziid;
    if (context != null)
      return context; 
    paramContext = zzdl(paramContext);
    zziid = paramContext;
    return paramContext;
  }
  
  private static Context zzdl(Context paramContext) {
    try {
      return DynamiteModule.zza(paramContext, DynamiteModule.zzgpo, "com.google.android.gms.maps_dynamite").zzaog();
    } finally {
      Exception exception = null;
      Log.e(TAG, "Failed to load maps module, use legacy", exception);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */