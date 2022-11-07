package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzeve<FieldDescriptorType extends zzevg<FieldDescriptorType>> {
  private static final zzeve zzoob = new zzeve(true);
  
  private boolean zzkff;
  
  private final zzewx<FieldDescriptorType, Object> zzonz;
  
  private boolean zzooa = false;
  
  private zzeve() {
    this.zzonz = zzewx.zzku(16);
  }
  
  private zzeve(boolean paramBoolean) {
    zzewx<zzevg, Object> zzewx1 = zzewx.zzku(0);
    this.zzonz = (zzewx)zzewx1;
    if (!this.zzkff) {
      zzewx1.zzbhs();
      this.zzkff = true;
    } 
  }
  
  static int zza(zzexu paramzzexu, int paramInt, Object paramObject) {
    paramInt = zzeuy.zzkb(paramInt);
    int i = paramInt;
    if (paramzzexu == zzexu.zzosh) {
      zzevu.zzg((zzewl)paramObject);
      i = paramInt << 1;
    } 
    switch (zzevf.zzood[paramzzexu.ordinal()]) {
      default:
        throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      case 18:
        if (paramObject instanceof zzevv) {
          paramInt = ((zzevv)paramObject).zzhk();
        } else {
          paramInt = ((Integer)paramObject).intValue();
        } 
        paramInt = zzeuy.zzkh(paramInt);
        return i + paramInt;
      case 17:
        paramInt = zzeuy.zzcw(((Long)paramObject).longValue());
        return i + paramInt;
      case 16:
        paramInt = zzeuy.zzke(((Integer)paramObject).intValue());
        return i + paramInt;
      case 15:
        paramInt = zzeuy.zzcy(((Long)paramObject).longValue());
        return i + paramInt;
      case 14:
        paramInt = zzeuy.zzkg(((Integer)paramObject).intValue());
        return i + paramInt;
      case 13:
        paramInt = zzeuy.zzkd(((Integer)paramObject).intValue());
        return i + paramInt;
      case 12:
        if (paramObject instanceof zzeuk) {
          paramInt = zzeuy.zzan((zzeuk)paramObject);
          return i + paramInt;
        } 
        paramInt = zzeuy.zzbd((byte[])paramObject);
        return i + paramInt;
      case 11:
        if (paramObject instanceof zzeuk) {
          paramInt = zzeuy.zzan((zzeuk)paramObject);
          return i + paramInt;
        } 
        paramInt = zzeuy.zztk((String)paramObject);
        return i + paramInt;
      case 10:
        if (paramObject instanceof zzewb) {
          paramInt = zzeuy.zza((zzewb)paramObject);
        } else {
          paramInt = zzeuy.zze((zzewl)paramObject);
        } 
        return i + paramInt;
      case 9:
        paramInt = zzeuy.zzf((zzewl)paramObject);
        return i + paramInt;
      case 8:
        paramInt = zzeuy.zzcy(((Boolean)paramObject).booleanValue());
        return i + paramInt;
      case 7:
        paramInt = zzeuy.zzkf(((Integer)paramObject).intValue());
        return i + paramInt;
      case 6:
        paramInt = zzeuy.zzcx(((Long)paramObject).longValue());
        return i + paramInt;
      case 5:
        paramInt = zzeuy.zzkc(((Integer)paramObject).intValue());
        return i + paramInt;
      case 4:
        paramInt = zzeuy.zzcv(((Long)paramObject).longValue());
        return i + paramInt;
      case 3:
        paramInt = zzeuy.zzcu(((Long)paramObject).longValue());
        return i + paramInt;
      case 2:
        paramInt = zzeuy.zzf(((Float)paramObject).floatValue());
        return i + paramInt;
      case 1:
        break;
    } 
    paramInt = zzeuy.zzm(((Double)paramObject).doubleValue());
    return i + paramInt;
  }
  
  public static Object zza(zzeut paramzzeut, zzexu paramzzexu, boolean paramBoolean) throws IOException {
    zzeya zzeya = zzeya.zzotf;
    switch (zzext.zzood[paramzzexu.ordinal()]) {
      default:
        throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      case 18:
        throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
      case 17:
        throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
      case 16:
        throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
      case 15:
        return zzeya.zzb(paramzzeut);
      case 14:
        return Long.valueOf(paramzzeut.zzctb());
      case 13:
        return Integer.valueOf(paramzzeut.zzcta());
      case 12:
        return Long.valueOf(paramzzeut.zzcsz());
      case 11:
        return Integer.valueOf(paramzzeut.zzcsy());
      case 10:
        return Integer.valueOf(paramzzeut.zzcsw());
      case 9:
        return paramzzeut.zzcsv();
      case 8:
        return Boolean.valueOf(paramzzeut.zzcst());
      case 7:
        return Integer.valueOf(paramzzeut.zzcss());
      case 6:
        return Long.valueOf(paramzzeut.zzcsr());
      case 5:
        return Integer.valueOf(paramzzeut.zzcsq());
      case 4:
        return Long.valueOf(paramzzeut.zzcso());
      case 3:
        return Long.valueOf(paramzzeut.zzcsp());
      case 2:
        return Float.valueOf(paramzzeut.readFloat());
      case 1:
        break;
    } 
    return Double.valueOf(paramzzeut.readDouble());
  }
  
  static void zza(zzeuy paramzzeuy, zzexu paramzzexu, int paramInt, Object paramObject) throws IOException {
    zzewl zzewl;
    byte[] arrayOfByte;
    if (paramzzexu == zzexu.zzosh) {
      zzewl = (zzewl)paramObject;
      zzevu.zzg(zzewl);
      paramzzeuy.zzw(paramInt, 3);
      zzewl.zza(paramzzeuy);
      paramzzeuy.zzw(paramInt, 4);
      return;
    } 
    paramzzeuy.zzw(paramInt, zzewl.zzcvy());
    switch (zzevf.zzood[zzewl.ordinal()]) {
      default:
        return;
      case 18:
        if (paramObject instanceof zzevv) {
          paramzzeuy.zzjx(((zzevv)paramObject).zzhk());
          return;
        } 
        paramzzeuy.zzjx(((Integer)paramObject).intValue());
      case 17:
        paramzzeuy.zzcs(((Long)paramObject).longValue());
        return;
      case 16:
        paramzzeuy.zzjz(((Integer)paramObject).intValue());
        return;
      case 15:
        paramzzeuy.zzct(((Long)paramObject).longValue());
        return;
      case 14:
        paramzzeuy.zzka(((Integer)paramObject).intValue());
        return;
      case 13:
        paramzzeuy.zzjy(((Integer)paramObject).intValue());
        return;
      case 12:
        if (paramObject instanceof zzeuk) {
          paramzzeuy.zzam((zzeuk)paramObject);
          return;
        } 
        arrayOfByte = (byte[])paramObject;
        paramzzeuy.zzh(arrayOfByte, 0, arrayOfByte.length);
        return;
      case 11:
        if (paramObject instanceof zzeuk) {
          paramzzeuy.zzam((zzeuk)paramObject);
          return;
        } 
        paramzzeuy.zztj((String)paramObject);
        return;
      case 10:
        paramzzeuy.zzd((zzewl)paramObject);
        return;
      case 9:
        ((zzewl)paramObject).zza(paramzzeuy);
        return;
      case 8:
        paramzzeuy.write((byte)((Boolean)paramObject).booleanValue());
        return;
      case 7:
        paramzzeuy.zzka(((Integer)paramObject).intValue());
        return;
      case 6:
        paramzzeuy.zzct(((Long)paramObject).longValue());
        return;
      case 5:
        paramzzeuy.zzjx(((Integer)paramObject).intValue());
        return;
      case 4:
        paramzzeuy.zzcr(((Long)paramObject).longValue());
        return;
      case 3:
        paramzzeuy.zzcr(((Long)paramObject).longValue());
        return;
      case 2:
        paramzzeuy.zzka(Float.floatToRawIntBits(((Float)paramObject).floatValue()));
        return;
      case 1:
        break;
    } 
    paramzzeuy.zzct(Double.doubleToRawLongBits(((Double)paramObject).doubleValue()));
  }
  
  private void zza(FieldDescriptorType paramFieldDescriptorType, Object paramObject) {
    if (paramFieldDescriptorType.zzctx()) {
      if (paramObject instanceof List) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll((List)paramObject);
        int i = arrayList.size();
        byte b = 0;
        while (b < i) {
          paramObject = arrayList.get(b);
          b++;
          zza(paramFieldDescriptorType.zzctw(), paramObject);
        } 
        paramObject = arrayList;
      } else {
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      } 
    } else {
      zza(paramFieldDescriptorType.zzctw(), paramObject);
    } 
    if (paramObject instanceof zzewb)
      this.zzooa = true; 
    this.zzonz.zza(paramFieldDescriptorType, paramObject);
  }
  
  private static void zza(zzexu paramzzexu, Object paramObject) {
    zzevu.zzu(paramObject);
    int i = zzevf.zzooc[paramzzexu.zzcvx().ordinal()];
    boolean bool1 = true;
    boolean bool2 = false;
    switch (i) {
      case 9:
        bool2 = bool1;
        if (!(paramObject instanceof zzewl)) {
          if (paramObject instanceof zzewb) {
            bool2 = bool1;
            break;
          } 
        } else {
          break;
        } 
        bool2 = false;
        break;
      case 8:
        bool2 = bool1;
        if (!(paramObject instanceof Integer)) {
          if (paramObject instanceof zzevv) {
            bool2 = bool1;
            break;
          } 
        } else {
          break;
        } 
        bool2 = false;
        break;
      case 7:
        bool2 = bool1;
        if (!(paramObject instanceof zzeuk)) {
          if (paramObject instanceof byte[]) {
            bool2 = bool1;
            break;
          } 
        } else {
          break;
        } 
        bool2 = false;
        break;
      case 6:
        bool2 = paramObject instanceof String;
        break;
      case 5:
        bool2 = paramObject instanceof Boolean;
        break;
      case 4:
        bool2 = paramObject instanceof Double;
        break;
      case 3:
        bool2 = paramObject instanceof Float;
        break;
      case 2:
        bool2 = paramObject instanceof Long;
        break;
      case 1:
        bool2 = paramObject instanceof Integer;
        break;
    } 
    if (bool2)
      return; 
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    throw illegalArgumentException;
  }
  
  public static <T extends zzevg<T>> zzeve<T> zzctv() {
    return new zzeve<T>();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzeve))
      return false; 
    paramObject = paramObject;
    return this.zzonz.equals(((zzeve)paramObject).zzonz);
  }
  
  public final int hashCode() {
    return this.zzonz.hashCode();
  }
  
  public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
    return this.zzooa ? new zzewe<FieldDescriptorType>(this.zzonz.entrySet().iterator()) : this.zzonz.entrySet().iterator();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */