package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbp;
import java.util.Set;

public final class zzs {
  public static String[] zzc(Set<Scope> paramSet) {
    zzbp.zzb(paramSet, "scopes can't be null.");
    Scope[] arrayOfScope = paramSet.<Scope>toArray(new Scope[paramSet.size()]);
    zzbp.zzb(arrayOfScope, "scopes can't be null.");
    String[] arrayOfString = new String[arrayOfScope.length];
    for (byte b = 0; b < arrayOfScope.length; b++)
      arrayOfString[b] = arrayOfScope[b].zzaft(); 
    return arrayOfString;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */