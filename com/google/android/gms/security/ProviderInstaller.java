package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.zzo;
import java.lang.reflect.Method;

public class ProviderInstaller {
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  
  private static final Object zzaqd;
  
  private static final zze zzjnj = zze.zzaex();
  
  private static Method zzjnk;
  
  static {
    zzaqd = new Object();
    zzjnk = null;
  }
  
  public static void installIfNeeded(Context paramContext) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    zzbp.zzb(paramContext, "Context must not be null");
    zze.zzbu(paramContext);
    paramContext = zzo.getRemoteContext(paramContext);
    if (paramContext != null) {
      Object object = zzaqd;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        if (zzjnk == null)
          zzjnk = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] { Context.class }); 
        zzjnk.invoke(null, new Object[] { paramContext });
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
        return;
      } catch (Exception exception) {
        String str = String.valueOf(exception.getMessage());
        if (str.length() != 0) {
          str = "Failed to install provider: ".concat(str);
        } else {
          str = new String("Failed to install provider: ");
        } 
        Log.e("ProviderInstaller", str);
        GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException = new GooglePlayServicesNotAvailableException();
        this(8);
        throw googlePlayServicesNotAvailableException;
      } finally {}
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      throw paramContext;
    } 
    Log.e("ProviderInstaller", "Failed to get remote context");
    throw new GooglePlayServicesNotAvailableException(8);
  }
  
  public static void installIfNeededAsync(Context paramContext, ProviderInstallListener paramProviderInstallListener) {
    zzbp.zzb(paramContext, "Context must not be null");
    zzbp.zzb(paramProviderInstallListener, "Listener must not be null");
    zzbp.zzfy("Must be called on the UI thread");
    (new zza(paramContext, paramProviderInstallListener)).execute((Object[])new Void[0]);
  }
  
  public static interface ProviderInstallListener {
    void onProviderInstallFailed(int param1Int, Intent param1Intent);
    
    void onProviderInstalled();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/security/ProviderInstaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */