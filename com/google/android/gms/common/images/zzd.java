package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzc;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public final class zzd extends zza {
  private WeakReference<ImageManager.OnImageLoadedListener> zzfrx;
  
  public zzd(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri) {
    super(paramUri, 0);
    zzc.zzr(paramOnImageLoadedListener);
    this.zzfrx = new WeakReference<ImageManager.OnImageLoadedListener>(paramOnImageLoadedListener);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzd))
      return false; 
    if (this == paramObject)
      return true; 
    zzd zzd1 = (zzd)paramObject;
    paramObject = this.zzfrx.get();
    ImageManager.OnImageLoadedListener onImageLoadedListener = zzd1.zzfrx.get();
    return (onImageLoadedListener != null && paramObject != null && zzbf.equal(onImageLoadedListener, paramObject) && zzbf.equal(zzd1.zzfrp, this.zzfrp));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzfrp });
  }
  
  protected final void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (!paramBoolean2) {
      ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzfrx.get();
      if (onImageLoadedListener != null)
        onImageLoadedListener.onImageLoaded(this.zzfrp.uri, paramDrawable, paramBoolean3); 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */