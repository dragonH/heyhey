package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbcj;

public abstract class zza {
  final zzb zzfrp;
  
  private int zzfrq = 0;
  
  protected int zzfrr = 0;
  
  private boolean zzfrs = false;
  
  private boolean zzfrt = true;
  
  private boolean zzfru = false;
  
  private boolean zzfrv = true;
  
  public zza(Uri paramUri, int paramInt) {
    this.zzfrp = new zzb(paramUri);
    this.zzfrr = paramInt;
  }
  
  final void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean) {
    zzc.zzr(paramBitmap);
    zza((Drawable)new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }
  
  final void zza(Context paramContext, zzbcj paramzzbcj) {
    if (this.zzfrv)
      zza(null, false, true, false); 
  }
  
  final void zza(Context paramContext, zzbcj paramzzbcj, boolean paramBoolean) {
    int i = this.zzfrr;
    if (i != 0) {
      Drawable drawable = paramContext.getResources().getDrawable(i);
    } else {
      paramContext = null;
    } 
    zza((Drawable)paramContext, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected final boolean zzc(boolean paramBoolean1, boolean paramBoolean2) {
    return (this.zzfrt && !paramBoolean2 && !paramBoolean1);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */