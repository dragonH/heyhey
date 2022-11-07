package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;
import java.util.Map;

public final class zzbdm<I, O> extends zzbck {
  public static final zzbdp CREATOR = new zzbdp();
  
  private final int zzdxs;
  
  protected final int zzfwq;
  
  protected final boolean zzfwr;
  
  protected final int zzfws;
  
  protected final boolean zzfwt;
  
  protected final String zzfwu;
  
  protected final int zzfwv;
  
  protected final Class<? extends zzbdl> zzfww;
  
  private String zzfwx;
  
  private zzbdr zzfwy;
  
  private zzbdn<I, O> zzfwz;
  
  zzbdm(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, zzbdf paramzzbdf) {
    this.zzdxs = paramInt1;
    this.zzfwq = paramInt2;
    this.zzfwr = paramBoolean1;
    this.zzfws = paramInt3;
    this.zzfwt = paramBoolean2;
    this.zzfwu = paramString1;
    this.zzfwv = paramInt4;
    if (paramString2 == null) {
      this.zzfww = null;
      this.zzfwx = null;
    } else {
      this.zzfww = (Class)zzbdw.class;
      this.zzfwx = paramString2;
    } 
    if (paramzzbdf == null) {
      this.zzfwz = null;
      return;
    } 
    this.zzfwz = (zzbdn)paramzzbdf.zzakp();
  }
  
  private zzbdm(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends zzbdl> paramClass, zzbdn<I, O> paramzzbdn) {
    this.zzdxs = 1;
    this.zzfwq = paramInt1;
    this.zzfwr = paramBoolean1;
    this.zzfws = paramInt2;
    this.zzfwt = paramBoolean2;
    this.zzfwu = paramString;
    this.zzfwv = paramInt3;
    this.zzfww = paramClass;
    if (paramClass == null) {
      paramString = null;
    } else {
      paramString = paramClass.getCanonicalName();
    } 
    this.zzfwx = paramString;
    this.zzfwz = paramzzbdn;
  }
  
  public static zzbdm zza(String paramString, int paramInt, zzbdn<?, ?> paramzzbdn, boolean paramBoolean) {
    return new zzbdm<Object, Object>(7, false, 0, false, paramString, paramInt, null, paramzzbdn);
  }
  
  public static <T extends zzbdl> zzbdm<T, T> zza(String paramString, int paramInt, Class<T> paramClass) {
    return new zzbdm<T, T>(11, false, 11, false, paramString, paramInt, paramClass, null);
  }
  
  private String zzakr() {
    String str1 = this.zzfwx;
    String str2 = str1;
    if (str1 == null)
      str2 = null; 
    return str2;
  }
  
  public static <T extends zzbdl> zzbdm<ArrayList<T>, ArrayList<T>> zzb(String paramString, int paramInt, Class<T> paramClass) {
    return new zzbdm<ArrayList<T>, ArrayList<T>>(11, true, 11, true, paramString, paramInt, paramClass, null);
  }
  
  public static zzbdm<Integer, Integer> zzj(String paramString, int paramInt) {
    return new zzbdm<Integer, Integer>(0, false, 0, false, paramString, paramInt, null, null);
  }
  
  public static zzbdm<Boolean, Boolean> zzk(String paramString, int paramInt) {
    return new zzbdm<Boolean, Boolean>(6, false, 6, false, paramString, paramInt, null, null);
  }
  
  public static zzbdm<String, String> zzl(String paramString, int paramInt) {
    return new zzbdm<String, String>(7, false, 7, false, paramString, paramInt, null, null);
  }
  
  public static zzbdm<ArrayList<String>, ArrayList<String>> zzm(String paramString, int paramInt) {
    return new zzbdm<ArrayList<String>, ArrayList<String>>(7, true, 7, true, paramString, paramInt, null, null);
  }
  
  public static zzbdm<byte[], byte[]> zzn(String paramString, int paramInt) {
    return (zzbdm)new zzbdm<byte, byte>(8, false, 8, false, paramString, 4, null, null);
  }
  
  public final I convertBack(O paramO) {
    return this.zzfwz.convertBack(paramO);
  }
  
  public final String toString() {
    zzbh zzbh = zzbf.zzt(this).zzg("versionCode", Integer.valueOf(this.zzdxs)).zzg("typeIn", Integer.valueOf(this.zzfwq)).zzg("typeInArray", Boolean.valueOf(this.zzfwr)).zzg("typeOut", Integer.valueOf(this.zzfws)).zzg("typeOutArray", Boolean.valueOf(this.zzfwt)).zzg("outputFieldName", this.zzfwu).zzg("safeParcelFieldId", Integer.valueOf(this.zzfwv)).zzg("concreteTypeName", zzakr());
    Class<? extends zzbdl> clazz = this.zzfww;
    if (clazz != null)
      zzbh.zzg("concreteType.class", clazz.getCanonicalName()); 
    zzbdn<I, O> zzbdn1 = this.zzfwz;
    if (zzbdn1 != null)
      zzbh.zzg("converterName", zzbdn1.getClass().getCanonicalName()); 
    return zzbh.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    zzbdf zzbdf;
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zzc(paramParcel, 2, this.zzfwq);
    zzbcn.zza(paramParcel, 3, this.zzfwr);
    zzbcn.zzc(paramParcel, 4, this.zzfws);
    zzbcn.zza(paramParcel, 5, this.zzfwt);
    zzbcn.zza(paramParcel, 6, this.zzfwu, false);
    zzbcn.zzc(paramParcel, 7, this.zzfwv);
    zzbcn.zza(paramParcel, 8, zzakr(), false);
    zzbdn<I, O> zzbdn1 = this.zzfwz;
    if (zzbdn1 == null) {
      zzbdn1 = null;
    } else {
      zzbdf = zzbdf.zza(zzbdn1);
    } 
    zzbcn.zza(paramParcel, 9, zzbdf, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final void zza(zzbdr paramzzbdr) {
    this.zzfwy = paramzzbdr;
  }
  
  public final int zzakq() {
    return this.zzfwv;
  }
  
  public final boolean zzaks() {
    return (this.zzfwz != null);
  }
  
  public final Map<String, zzbdm<?, ?>> zzakt() {
    zzbp.zzu(this.zzfwx);
    zzbp.zzu(this.zzfwy);
    return this.zzfwy.zzgk(this.zzfwx);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */