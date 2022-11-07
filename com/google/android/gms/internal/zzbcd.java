package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class zzbcd extends Drawable implements Drawable.Callback {
  private int mFrom;
  
  private long zzdqt;
  
  private boolean zzfrt = true;
  
  private int zzfry = 0;
  
  private int zzfrz;
  
  private int zzfsa = 255;
  
  private int zzfsb;
  
  private int zzfsc = 0;
  
  private boolean zzfsd;
  
  private zzbch zzfse;
  
  private Drawable zzfsf;
  
  private Drawable zzfsg;
  
  private boolean zzfsh;
  
  private boolean zzfsi;
  
  private boolean zzfsj;
  
  private int zzfsk;
  
  public zzbcd(Drawable paramDrawable1, Drawable paramDrawable2) {
    this(null);
    Drawable drawable2 = paramDrawable1;
    if (paramDrawable1 == null)
      drawable2 = zzbcf.zzajb(); 
    this.zzfsf = drawable2;
    drawable2.setCallback(this);
    zzbch zzbch1 = this.zzfse;
    int i = zzbch1.zzfsn;
    zzbch1.zzfsn = drawable2.getChangingConfigurations() | i;
    Drawable drawable1 = paramDrawable2;
    if (paramDrawable2 == null)
      drawable1 = zzbcf.zzajb(); 
    this.zzfsg = drawable1;
    drawable1.setCallback(this);
    zzbch zzbch2 = this.zzfse;
    i = zzbch2.zzfsn;
    zzbch2.zzfsn = drawable1.getChangingConfigurations() | i;
  }
  
  zzbcd(zzbch paramzzbch) {
    this.zzfse = new zzbch(paramzzbch);
  }
  
  private final boolean canConstantState() {
    if (!this.zzfsh) {
      boolean bool;
      if (this.zzfsf.getConstantState() != null && this.zzfsg.getConstantState() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.zzfsi = bool;
      this.zzfsh = true;
    } 
    return this.zzfsi;
  }
  
  public final void draw(Canvas paramCanvas) {
    int i = this.zzfry;
    int j = 0;
    int k = 1;
    if (i != 1) {
      if (i == 2 && this.zzdqt >= 0L) {
        float f = (float)(SystemClock.uptimeMillis() - this.zzdqt) / this.zzfsb;
        if (f >= 1.0F) {
          j = k;
        } else {
          j = 0;
        } 
        if (j)
          this.zzfry = 0; 
        f = Math.min(f, 1.0F);
        this.zzfsc = (int)(this.zzfrz * f + 0.0F);
      } else {
        j = 1;
      } 
    } else {
      this.zzdqt = SystemClock.uptimeMillis();
      this.zzfry = 2;
    } 
    k = this.zzfsc;
    boolean bool = this.zzfrt;
    Drawable drawable1 = this.zzfsf;
    Drawable drawable2 = this.zzfsg;
    if (j != 0) {
      if (!bool || k == 0)
        drawable1.draw(paramCanvas); 
      j = this.zzfsa;
      if (k == j) {
        drawable2.setAlpha(j);
        drawable2.draw(paramCanvas);
      } 
      return;
    } 
    if (bool)
      drawable1.setAlpha(this.zzfsa - k); 
    drawable1.draw(paramCanvas);
    if (bool)
      drawable1.setAlpha(this.zzfsa); 
    if (k > 0) {
      drawable2.setAlpha(k);
      drawable2.draw(paramCanvas);
      drawable2.setAlpha(this.zzfsa);
    } 
    invalidateSelf();
  }
  
  public final int getChangingConfigurations() {
    int i = super.getChangingConfigurations();
    zzbch zzbch1 = this.zzfse;
    return i | zzbch1.mChangingConfigurations | zzbch1.zzfsn;
  }
  
  public final Drawable.ConstantState getConstantState() {
    if (canConstantState()) {
      this.zzfse.mChangingConfigurations = getChangingConfigurations();
      return this.zzfse;
    } 
    return null;
  }
  
  public final int getIntrinsicHeight() {
    return Math.max(this.zzfsf.getIntrinsicHeight(), this.zzfsg.getIntrinsicHeight());
  }
  
  public final int getIntrinsicWidth() {
    return Math.max(this.zzfsf.getIntrinsicWidth(), this.zzfsg.getIntrinsicWidth());
  }
  
  public final int getOpacity() {
    if (!this.zzfsj) {
      this.zzfsk = Drawable.resolveOpacity(this.zzfsf.getOpacity(), this.zzfsg.getOpacity());
      this.zzfsj = true;
    } 
    return this.zzfsk;
  }
  
  public final void invalidateDrawable(Drawable paramDrawable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.invalidateDrawable(this); 
  }
  
  public final Drawable mutate() {
    if (!this.zzfsd && super.mutate() == this)
      if (canConstantState()) {
        this.zzfsf.mutate();
        this.zzfsg.mutate();
        this.zzfsd = true;
      } else {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }  
    return this;
  }
  
  protected final void onBoundsChange(Rect paramRect) {
    this.zzfsf.setBounds(paramRect);
    this.zzfsg.setBounds(paramRect);
  }
  
  public final void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  public final void setAlpha(int paramInt) {
    if (this.zzfsc == this.zzfsa)
      this.zzfsc = paramInt; 
    this.zzfsa = paramInt;
    invalidateSelf();
  }
  
  public final void setColorFilter(ColorFilter paramColorFilter) {
    this.zzfsf.setColorFilter(paramColorFilter);
    this.zzfsg.setColorFilter(paramColorFilter);
  }
  
  public final void startTransition(int paramInt) {
    this.mFrom = 0;
    this.zzfrz = this.zzfsa;
    this.zzfsc = 0;
    this.zzfsb = 250;
    this.zzfry = 1;
    invalidateSelf();
  }
  
  public final void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.unscheduleDrawable(this, paramRunnable); 
  }
  
  public final Drawable zzaja() {
    return this.zzfsg;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbcd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */