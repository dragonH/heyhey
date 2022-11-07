package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class zzevh<MessageType extends zzevh<MessageType, BuilderType>, BuilderType extends zzevi<MessageType, BuilderType>> extends zzeuc<MessageType, BuilderType> {
  protected zzexl zzooe = zzexl.zzcvp();
  
  protected int zzoof = -1;
  
  protected static <T extends zzevh<T, ?>> T zza(T paramT, zzeuk paramzzeuk) throws zzevz {
    paramT = zza(paramT, paramzzeuk, zzevd.zzctu());
    boolean bool = true;
    if (paramT != null) {
      boolean bool1;
      if (paramT.zza(zzevp.zzooo, Boolean.TRUE, (Object)null) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (!bool1)
        throw (new zzexk(paramT)).zzcvo().zzh(paramT); 
    } 
    if (paramT != null) {
      boolean bool1;
      if (paramT.zza(zzevp.zzooo, Boolean.TRUE, (Object)null) != null) {
        bool1 = bool;
      } else {
        bool1 = false;
      } 
      if (!bool1)
        throw (new zzexk(paramT)).zzcvo().zzh(paramT); 
    } 
    return paramT;
  }
  
  private static <T extends zzevh<T, ?>> T zza(T paramT, zzeuk paramzzeuk, zzevd paramzzevd) throws zzevz {
    try {
      zzeut zzeut = paramzzeuk.zzcsg();
      paramT = zza(paramT, zzeut, paramzzevd);
      try {
        zzeut.zzjk(0);
        return paramT;
      } catch (zzevz zzevz) {
        throw zzevz.zzh(paramT);
      } 
    } catch (zzevz zzevz) {
      throw zzevz;
    } 
  }
  
  static <T extends zzevh<T, ?>> T zza(T paramT, zzeut paramzzeut, zzevd paramzzevd) throws zzevz {
    zzevh zzevh1 = (zzevh)paramT.zza(zzevp.zzoos, (Object)null, (Object)null);
    try {
      zzevh1.zza(zzevp.zzooq, paramzzeut, paramzzevd);
      zzevh1.zza(zzevp.zzoor, (Object)null, (Object)null);
      zzevh1.zzooe.zzbhs();
      return (T)zzevh1;
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof zzevz)
        throw (zzevz)runtimeException.getCause(); 
      throw runtimeException;
    } 
  }
  
  protected static <T extends zzevh<T, ?>> T zza(T paramT, byte[] paramArrayOfbyte) throws zzevz {
    paramT = zza(paramT, paramArrayOfbyte, zzevd.zzctu());
    if (paramT != null) {
      boolean bool;
      if (paramT.zza(zzevp.zzooo, Boolean.TRUE, (Object)null) != null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!bool)
        throw (new zzexk(paramT)).zzcvo().zzh(paramT); 
    } 
    return paramT;
  }
  
  private static <T extends zzevh<T, ?>> T zza(T paramT, byte[] paramArrayOfbyte, zzevd paramzzevd) throws zzevz {
    try {
      zzeut zzeut = zzeut.zzbb(paramArrayOfbyte);
      paramT = zza(paramT, zzeut, paramzzevd);
      try {
        zzeut.zzjk(0);
        return paramT;
      } catch (zzevz zzevz) {
        throw zzevz.zzh(paramT);
      } 
    } catch (zzevz zzevz) {
      throw zzevz;
    } 
  }
  
  static Object zza(Method paramMethod, Object paramObject, Object... paramVarArgs) {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (!(throwable instanceof RuntimeException)) {
        if (throwable instanceof Error)
          throw (Error)throwable; 
        throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable);
      } 
      throw (RuntimeException)throwable;
    } 
  }
  
  protected static zzevx zzctz() {
    return zzevt.zzcul();
  }
  
  protected static <E> zzevy<E> zzcua() {
    return zzewr.zzcva();
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!((zzevh)zza(zzevp.zzoou, (Object)null, (Object)null)).getClass().isInstance(paramObject))
      return false; 
    try {
      zzevk zzevk = zzevk.zzooj;
      paramObject = paramObject;
      zza(zzevp.zzoop, zzevk, paramObject);
      this.zzooe = zzevk.zza(this.zzooe, ((zzevh)paramObject).zzooe);
      return true;
    } catch (zzevl zzevl) {
      return false;
    } 
  }
  
  public int hashCode() {
    int i = this.zzomr;
    if (i != 0)
      return i; 
    zzevn zzevn = new zzevn();
    zza(zzevp.zzoop, zzevn, this);
    zzexl zzexl1 = this.zzooe;
    this.zzooe = zzevn.zza(zzexl1, zzexl1);
    i = zzevn.zzoom;
    this.zzomr = i;
    return i;
  }
  
  public final boolean isInitialized() {
    return (zza(zzevp.zzooo, Boolean.TRUE, (Object)null) != null);
  }
  
  public String toString() {
    return zzewo.zza(this, super.toString());
  }
  
  protected abstract Object zza(int paramInt, Object paramObject1, Object paramObject2);
  
  protected final boolean zza(int paramInt, zzeut paramzzeut) throws IOException {
    if ((paramInt & 0x7) == 4)
      return false; 
    if (this.zzooe == zzexl.zzcvp())
      this.zzooe = zzexl.zzcvq(); 
    return this.zzooe.zzb(paramInt, paramzzeut);
  }
  
  public final zzewp<MessageType> zzcty() {
    return (zzewp<MessageType>)zza(zzevp.zzoov, (Object)null, (Object)null);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzevh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */