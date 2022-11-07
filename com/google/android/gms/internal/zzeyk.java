package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zzeyk implements Cloneable {
  private Object value;
  
  private zzeyi<?, ?> zzotr;
  
  private List<zzeyp> zzots = new ArrayList<zzeyp>();
  
  private final byte[] toByteArray() throws IOException {
    byte[] arrayOfByte = new byte[zzn()];
    zza(zzeyf.zzbf(arrayOfByte));
    return arrayOfByte;
  }
  
  private zzeyk zzcwb() {
    zzeyk zzeyk1 = new zzeyk();
    try {
      zzeyk1.zzotr = this.zzotr;
      List<zzeyp> list = this.zzots;
      if (list == null) {
        zzeyk1.zzots = null;
      } else {
        zzeyk1.zzots.addAll(list);
      } 
      Object object = this.value;
      if (object != null) {
        if (object instanceof zzeyn) {
          object = ((zzeyn)object).clone();
        } else if (object instanceof byte[]) {
          object = ((byte[])object).clone();
        } else {
          boolean bool = object instanceof byte[][];
          boolean bool1 = false;
          byte b = 0;
          if (bool) {
            object = object;
            byte[][] arrayOfByte = new byte[object.length][];
            zzeyk1.value = arrayOfByte;
            while (b < object.length) {
              arrayOfByte[b] = (byte[])object[b].clone();
              b++;
            } 
          } else {
            if (object instanceof boolean[]) {
              object = ((boolean[])object).clone();
            } else if (object instanceof int[]) {
              object = ((int[])object).clone();
            } else if (object instanceof long[]) {
              object = ((long[])object).clone();
            } else if (object instanceof float[]) {
              object = ((float[])object).clone();
            } else if (object instanceof double[]) {
              object = ((double[])object).clone();
            } else {
              if (object instanceof zzeyn[]) {
                object = object;
                zzeyn[] arrayOfZzeyn = new zzeyn[object.length];
                zzeyk1.value = arrayOfZzeyn;
                for (b = bool1; b < object.length; b++)
                  arrayOfZzeyn[b] = (zzeyn)object[b].clone(); 
              } 
              return zzeyk1;
            } 
            zzeyk1.value = object;
          } 
          return zzeyk1;
        } 
      } else {
        return zzeyk1;
      } 
      zzeyk1.value = object;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      AssertionError assertionError = new AssertionError(cloneNotSupportedException);
      throw assertionError;
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzeyk))
      return false; 
    paramObject = paramObject;
    if (this.value != null && ((zzeyk)paramObject).value != null) {
      zzeyi<?, ?> zzeyi1 = this.zzotr;
      if (zzeyi1 != ((zzeyk)paramObject).zzotr)
        return false; 
      if (!zzeyi1.zzmlw.isArray())
        return this.value.equals(((zzeyk)paramObject).value); 
      Object object = this.value;
      return (object instanceof byte[]) ? Arrays.equals((byte[])object, (byte[])((zzeyk)paramObject).value) : ((object instanceof int[]) ? Arrays.equals((int[])object, (int[])((zzeyk)paramObject).value) : ((object instanceof long[]) ? Arrays.equals((long[])object, (long[])((zzeyk)paramObject).value) : ((object instanceof float[]) ? Arrays.equals((float[])object, (float[])((zzeyk)paramObject).value) : ((object instanceof double[]) ? Arrays.equals((double[])object, (double[])((zzeyk)paramObject).value) : ((object instanceof boolean[]) ? Arrays.equals((boolean[])object, (boolean[])((zzeyk)paramObject).value) : Arrays.deepEquals((Object[])object, (Object[])((zzeyk)paramObject).value))))));
    } 
    List<zzeyp> list = this.zzots;
    if (list != null) {
      List<zzeyp> list1 = ((zzeyk)paramObject).zzots;
      if (list1 != null)
        return list.equals(list1); 
    } 
    try {
      return Arrays.equals(toByteArray(), paramObject.toByteArray());
    } catch (IOException iOException) {
      throw new IllegalStateException(iOException);
    } 
  }
  
  public final int hashCode() {
    try {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    } catch (IOException iOException) {
      throw new IllegalStateException(iOException);
    } 
  }
  
  final void zza(zzeyf paramzzeyf) throws IOException {
    zzeyp = (zzeyp)this.value;
    if (zzeyp != null) {
      zzeyi<?, ?> zzeyi1 = this.zzotr;
      if (zzeyi1.zzotm) {
        int i = Array.getLength(zzeyp);
        for (byte b = 0; b < i; b++) {
          Object object = Array.get(zzeyp, b);
          if (object != null)
            zzeyi1.zza(object, paramzzeyf); 
        } 
        return;
      } 
      zzeyi1.zza(zzeyp, paramzzeyf);
      return;
    } 
    for (zzeyp zzeyp : this.zzots) {
      paramzzeyf.zzlc(zzeyp.tag);
      paramzzeyf.zzbh(zzeyp.bytes);
    } 
  }
  
  final void zza(zzeyp paramzzeyp) {
    this.zzots.add(paramzzeyp);
  }
  
  final <T> T zzb(zzeyi<?, T> paramzzeyi) {
    if (this.value != null) {
      if (!this.zzotr.equals(paramzzeyi))
        throw new IllegalStateException("Tried to getExtension with a different Extension."); 
    } else {
      this.zzotr = paramzzeyi;
      this.value = paramzzeyi.zzbn(this.zzots);
      this.zzots = null;
    } 
    return (T)this.value;
  }
  
  final int zzn() {
    int i;
    Object<zzeyp> object = (Object<zzeyp>)this.value;
    byte b = 0;
    if (object != null) {
      zzeyi<?, ?> zzeyi1 = this.zzotr;
      if (zzeyi1.zzotm) {
        int j = Array.getLength(object);
        int k = 0;
        while (true) {
          i = k;
          if (b < j) {
            i = k;
            if (Array.get(object, b) != null)
              i = k + zzeyi1.zzcg(Array.get(object, b)); 
            b++;
            k = i;
            continue;
          } 
          break;
        } 
      } else {
        i = zzeyi1.zzcg(object);
      } 
    } else {
      object = (Object<zzeyp>)this.zzots.iterator();
      int j = 0;
      while (true) {
        i = j;
        if (object.hasNext()) {
          zzeyp zzeyp = object.next();
          j += zzeyf.zzld(zzeyp.tag) + 0 + zzeyp.bytes.length;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */