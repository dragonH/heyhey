package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.internal.zzbed;

public class zzp {
  private static zzp zzfgc;
  
  private final Context mContext;
  
  private zzp(Context paramContext) {
    this.mContext = paramContext.getApplicationContext();
  }
  
  static zzg zza(PackageInfo paramPackageInfo, zzg... paramVarArgs) {
    Signature[] arrayOfSignature2 = paramPackageInfo.signatures;
    if (arrayOfSignature2 == null)
      return null; 
    if (arrayOfSignature2.length != 1) {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return null;
    } 
    Signature[] arrayOfSignature1 = paramPackageInfo.signatures;
    byte b = 0;
    zzh zzh = new zzh(arrayOfSignature1[0].toByteArray());
    while (b < paramVarArgs.length) {
      if (paramVarArgs[b].equals(zzh))
        return paramVarArgs[b]; 
      b++;
    } 
    return null;
  }
  
  private static boolean zza(PackageInfo paramPackageInfo, boolean paramBoolean) {
    if (paramPackageInfo != null && paramPackageInfo.signatures != null) {
      zzg zzg;
      if (paramBoolean) {
        zzg = zza(paramPackageInfo, zzj.zzffs);
      } else {
        zzg = zza((PackageInfo)zzg, new zzg[] { zzj.zzffs[0] });
      } 
      if (zzg != null)
        return true; 
    } 
    return false;
  }
  
  private static boolean zzb(PackageInfo paramPackageInfo, boolean paramBoolean) {
    boolean bool;
    if (paramPackageInfo.signatures.length != 1) {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return false;
    } 
    zzh zzh = new zzh(paramPackageInfo.signatures[0].toByteArray());
    String str = paramPackageInfo.packageName;
    if (paramBoolean) {
      bool = zzf.zzb(str, zzh);
    } else {
      bool = zzf.zza(str, zzh);
    } 
    if (!bool) {
      StringBuilder stringBuilder = new StringBuilder(27);
      stringBuilder.append("Cert not in list. atk=");
      stringBuilder.append(paramBoolean);
      Log.d("GoogleSignatureVerifier", stringBuilder.toString());
    } 
    return bool;
  }
  
  public static zzp zzbz(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic zzu : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: ldc com/google/android/gms/common/zzp
    //   7: monitorenter
    //   8: getstatic com/google/android/gms/common/zzp.zzfgc : Lcom/google/android/gms/common/zzp;
    //   11: ifnonnull -> 31
    //   14: aload_0
    //   15: invokestatic zzbx : (Landroid/content/Context;)V
    //   18: new com/google/android/gms/common/zzp
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: invokespecial <init> : (Landroid/content/Context;)V
    //   27: aload_1
    //   28: putstatic com/google/android/gms/common/zzp.zzfgc : Lcom/google/android/gms/common/zzp;
    //   31: ldc com/google/android/gms/common/zzp
    //   33: monitorexit
    //   34: getstatic com/google/android/gms/common/zzp.zzfgc : Lcom/google/android/gms/common/zzp;
    //   37: areturn
    //   38: astore_0
    //   39: ldc com/google/android/gms/common/zzp
    //   41: monitorexit
    //   42: aload_0
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   8	31	38	finally
    //   31	34	38	finally
    //   39	42	38	finally
  }
  
  private final boolean zzfs(String paramString) {
    try {
      PackageInfo packageInfo = zzbed.zzcr(this.mContext).getPackageInfo(paramString, 64);
      if (packageInfo == null)
        return false; 
      if (zzo.zzby(this.mContext))
        return zzb(packageInfo, true); 
      boolean bool = zzb(packageInfo, false);
      if (!bool && zzb(packageInfo, true))
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build."); 
      return bool;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return false;
    } 
  }
  
  @Deprecated
  public final boolean zza(PackageManager paramPackageManager, PackageInfo paramPackageInfo) {
    if (paramPackageInfo != null) {
      if (zza(paramPackageInfo, false))
        return true; 
      if (zza(paramPackageInfo, true)) {
        if (zzo.zzby(this.mContext))
          return true; 
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
      } 
    } 
    return false;
  }
  
  public final boolean zzbo(int paramInt) {
    String[] arrayOfString = zzbed.zzcr(this.mContext).getPackagesForUid(paramInt);
    if (arrayOfString != null && arrayOfString.length != 0) {
      int i = arrayOfString.length;
      for (paramInt = 0; paramInt < i; paramInt++) {
        if (zzfs(arrayOfString[paramInt]))
          return true; 
      } 
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */