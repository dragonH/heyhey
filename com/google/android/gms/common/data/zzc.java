package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import java.util.Arrays;

public class zzc {
  protected final DataHolder zzflf;
  
  protected int zzfqh;
  
  private int zzfqi;
  
  public zzc(DataHolder paramDataHolder, int paramInt) {
    this.zzflf = (DataHolder)zzbp.zzu(paramDataHolder);
    zzbv(paramInt);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof zzc) {
      paramObject = paramObject;
      if (zzbf.equal(Integer.valueOf(((zzc)paramObject).zzfqh), Integer.valueOf(this.zzfqh)) && zzbf.equal(Integer.valueOf(((zzc)paramObject).zzfqi), Integer.valueOf(this.zzfqi)) && ((zzc)paramObject).zzflf == this.zzflf)
        return true; 
    } 
    return false;
  }
  
  protected final boolean getBoolean(String paramString) {
    return this.zzflf.zze(paramString, this.zzfqh, this.zzfqi);
  }
  
  protected final byte[] getByteArray(String paramString) {
    return this.zzflf.zzg(paramString, this.zzfqh, this.zzfqi);
  }
  
  protected final float getFloat(String paramString) {
    return this.zzflf.zzf(paramString, this.zzfqh, this.zzfqi);
  }
  
  protected final int getInteger(String paramString) {
    return this.zzflf.zzc(paramString, this.zzfqh, this.zzfqi);
  }
  
  protected final long getLong(String paramString) {
    return this.zzflf.zzb(paramString, this.zzfqh, this.zzfqi);
  }
  
  protected final String getString(String paramString) {
    return this.zzflf.zzd(paramString, this.zzfqh, this.zzfqi);
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzfqh), Integer.valueOf(this.zzfqi), this.zzflf });
  }
  
  public boolean isDataValid() {
    return !this.zzflf.isClosed();
  }
  
  protected final void zza(String paramString, CharArrayBuffer paramCharArrayBuffer) {
    this.zzflf.zza(paramString, this.zzfqh, this.zzfqi, paramCharArrayBuffer);
  }
  
  protected final void zzbv(int paramInt) {
    boolean bool;
    if (paramInt >= 0 && paramInt < this.zzflf.zzfqq) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzbg(bool);
    this.zzfqh = paramInt;
    this.zzfqi = this.zzflf.zzbx(paramInt);
  }
  
  public final boolean zzfu(String paramString) {
    return this.zzflf.zzfu(paramString);
  }
  
  protected final Uri zzfv(String paramString) {
    paramString = this.zzflf.zzd(paramString, this.zzfqh, this.zzfqi);
    return (paramString == null) ? null : Uri.parse(paramString);
  }
  
  protected final boolean zzfw(String paramString) {
    return this.zzflf.zzh(paramString, this.zzfqh, this.zzfqi);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */