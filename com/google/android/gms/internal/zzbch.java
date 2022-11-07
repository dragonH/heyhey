package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

final class zzbch extends Drawable.ConstantState {
  int mChangingConfigurations;
  
  int zzfsn;
  
  zzbch(zzbch paramzzbch) {
    if (paramzzbch != null) {
      this.mChangingConfigurations = paramzzbch.mChangingConfigurations;
      this.zzfsn = paramzzbch.zzfsn;
    } 
  }
  
  public final int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public final Drawable newDrawable() {
    return new zzbcd(this);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */