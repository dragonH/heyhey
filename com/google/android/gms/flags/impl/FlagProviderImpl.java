package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbvm;

@DynamiteApi
public class FlagProviderImpl extends zzbvm {
  private boolean zzaqf = false;
  
  private SharedPreferences zzbfl;
  
  public boolean getBooleanFlagValue(String paramString, boolean paramBoolean, int paramInt) {
    return !this.zzaqf ? paramBoolean : zzb.zza(this.zzbfl, paramString, Boolean.valueOf(paramBoolean)).booleanValue();
  }
  
  public int getIntFlagValue(String paramString, int paramInt1, int paramInt2) {
    return !this.zzaqf ? paramInt1 : zzd.zza(this.zzbfl, paramString, Integer.valueOf(paramInt1)).intValue();
  }
  
  public long getLongFlagValue(String paramString, long paramLong, int paramInt) {
    return !this.zzaqf ? paramLong : zzf.zza(this.zzbfl, paramString, Long.valueOf(paramLong)).longValue();
  }
  
  public String getStringFlagValue(String paramString1, String paramString2, int paramInt) {
    return !this.zzaqf ? paramString2 : zzh.zza(this.zzbfl, paramString1, paramString2);
  }
  
  public void init(IObjectWrapper paramIObjectWrapper) {
    Context context = (Context)zzn.zzx(paramIObjectWrapper);
    if (this.zzaqf)
      return; 
    try {
      this.zzbfl = zzj.zzcy(context.createPackageContext("com.google.android.gms", 0));
      this.zzaqf = true;
      return;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
    
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        str = "Could not retrieve sdk flags, continuing with defaults: ".concat(str);
      } else {
        str = new String("Could not retrieve sdk flags, continuing with defaults: ");
      } 
      Log.w("FlagProviderImpl", str);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/flags/impl/FlagProviderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */