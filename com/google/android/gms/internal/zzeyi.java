package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzeyi<M extends zzeyh<M>, T> {
  public final int tag;
  
  private int type;
  
  protected final Class<T> zzmlw;
  
  private zzevh<?, ?> zzoog;
  
  protected final boolean zzotm;
  
  private zzeyi(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean) {
    this(11, paramClass, null, paramInt2, false);
  }
  
  private zzeyi(int paramInt1, Class<T> paramClass, zzevh<?, ?> paramzzevh, int paramInt2, boolean paramBoolean) {
    this.type = paramInt1;
    this.zzmlw = paramClass;
    this.tag = paramInt2;
    this.zzotm = false;
    this.zzoog = null;
  }
  
  public static <M extends zzeyh<M>, T extends zzeyn> zzeyi<M, T> zza(int paramInt, Class<T> paramClass, long paramLong) {
    return new zzeyi<M, T>(11, paramClass, (int)paramLong, false);
  }
  
  private final Object zzb(zzeye paramzzeye) {
    Class<T> clazz;
    StringBuilder stringBuilder;
    if (this.zzotm) {
      clazz = (Class)this.zzmlw.getComponentType();
    } else {
      clazz = this.zzmlw;
    } 
    try {
      IllegalArgumentException illegalArgumentException;
      int i = this.type;
      if (i != 10) {
        if (i == 11) {
          zzeyn zzeyn1 = (zzeyn)clazz.newInstance();
          paramzzeye.zza(zzeyn1);
          return zzeyn1;
        } 
        illegalArgumentException = new IllegalArgumentException();
        i = this.type;
        StringBuilder stringBuilder1 = new StringBuilder();
        this(24);
        stringBuilder1.append("Unknown type ");
        stringBuilder1.append(i);
        this(stringBuilder1.toString());
        throw illegalArgumentException;
      } 
      zzeyn zzeyn = (zzeyn)clazz.newInstance();
      illegalArgumentException.zza(zzeyn, this.tag >>> 3);
      return zzeyn;
    } catch (InstantiationException instantiationException) {
      String str = String.valueOf(clazz);
      stringBuilder = new StringBuilder(str.length() + 33);
      stringBuilder.append("Error creating instance of class ");
      stringBuilder.append(str);
      throw new IllegalArgumentException(stringBuilder.toString(), instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      String str = String.valueOf(stringBuilder);
      stringBuilder = new StringBuilder(str.length() + 33);
      stringBuilder.append("Error creating instance of class ");
      stringBuilder.append(str);
      throw new IllegalArgumentException(stringBuilder.toString(), illegalAccessException);
    } catch (IOException iOException) {
      throw new IllegalArgumentException("Error reading extension field", iOException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzeyi))
      return false; 
    paramObject = paramObject;
    return (this.type == ((zzeyi)paramObject).type && this.zzmlw == ((zzeyi)paramObject).zzmlw && this.tag == ((zzeyi)paramObject).tag && this.zzotm == ((zzeyi)paramObject).zzotm);
  }
  
  public final int hashCode() {
    return (((this.type + 1147) * 31 + this.zzmlw.hashCode()) * 31 + this.tag) * 31 + this.zzotm;
  }
  
  protected final void zza(Object paramObject, zzeyf paramzzeyf) {
    try {
      StringBuilder stringBuilder;
      paramzzeyf.zzlc(this.tag);
      int i = this.type;
      if (i != 10) {
        if (i == 11) {
          paramzzeyf.zzb((zzeyn)paramObject);
          return;
        } 
        paramObject = new IllegalArgumentException();
        i = this.type;
        stringBuilder = new StringBuilder();
        this(24);
        stringBuilder.append("Unknown type ");
        stringBuilder.append(i);
        super(stringBuilder.toString());
        throw paramObject;
      } 
      i = this.tag;
      ((zzeyn)paramObject).zza((zzeyf)stringBuilder);
      stringBuilder.zzw(i >>> 3, 4);
      return;
    } catch (IOException iOException) {
      throw new IllegalStateException(iOException);
    } 
  }
  
  final T zzbn(List<zzeyp> paramList) {
    Class<T> clazz;
    if (paramList == null)
      return null; 
    if (this.zzotm) {
      ArrayList<Object> arrayList = new ArrayList();
      boolean bool = false;
      byte b;
      for (b = 0; b < paramList.size(); b++) {
        byte[] arrayOfByte = ((zzeyp)paramList.get(b)).bytes;
        if (arrayOfByte.length != 0)
          arrayList.add(zzb(zzeye.zzbe(arrayOfByte))); 
      } 
      int i = arrayList.size();
      if (i == 0)
        return null; 
      clazz = this.zzmlw;
      clazz = (Class<T>)clazz.cast(Array.newInstance(clazz.getComponentType(), i));
      for (b = bool; b < i; b++)
        Array.set(clazz, b, arrayList.get(b)); 
      return (T)clazz;
    } 
    if (clazz.isEmpty())
      return null; 
    zzeyp zzeyp = (zzeyp)clazz.get(clazz.size() - 1);
    return this.zzmlw.cast(zzb(zzeye.zzbe(zzeyp.bytes)));
  }
  
  protected final int zzcg(Object paramObject) {
    int i = this.tag >>> 3;
    int j = this.type;
    if (j != 10) {
      if (j == 11)
        return zzeyf.zzb(i, (zzeyn)paramObject); 
      j = this.type;
      paramObject = new StringBuilder(24);
      paramObject.append("Unknown type ");
      paramObject.append(j);
      throw new IllegalArgumentException(paramObject.toString());
    } 
    paramObject = paramObject;
    return (zzeyf.zzkb(i) << 1) + paramObject.zzhi();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */