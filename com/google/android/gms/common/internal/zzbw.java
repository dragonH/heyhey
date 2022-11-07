package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

public final class zzbw extends zzp<zzbc> {
  private static final zzbw zzfwe = new zzbw();
  
  private zzbw() {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View zzc(Context paramContext, int paramInt1, int paramInt2) throws zzq {
    return zzfwe.zzd(paramContext, paramInt1, paramInt2);
  }
  
  private final View zzd(Context paramContext, int paramInt1, int paramInt2) throws zzq {
    try {
      zzbu zzbu = new zzbu();
      this(paramInt1, paramInt2, null);
      IObjectWrapper iObjectWrapper = zzn.zzw(paramContext);
      return (View)zzn.zzx(((zzbc)zzcu(paramContext)).zza(iObjectWrapper, zzbu));
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder(64);
      stringBuilder.append("Could not get button with size ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" and color ");
      stringBuilder.append(paramInt2);
      throw new zzq(stringBuilder.toString(), exception);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */