package com.google.android.gms.internal;

import java.util.Map;

final class zzexc implements Comparable<zzexc>, Map.Entry<Object, Object> {
  private Object value;
  
  private final Object zzoqp;
  
  zzexc(zzewx paramzzewx, Object paramObject1, Object paramObject2) {
    this.zzoqp = paramObject1;
    this.value = paramObject2;
  }
  
  zzexc(zzewx paramzzewx, Map.Entry<Object, Object> paramEntry) {
    this(paramzzewx, paramEntry.getKey(), paramEntry.getValue());
  }
  
  private static boolean equals(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? ((paramObject2 == null)) : paramObject1.equals(paramObject2);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Map.Entry))
      return false; 
    paramObject = paramObject;
    return (equals(this.zzoqp, paramObject.getKey()) && equals(this.value, paramObject.getValue()));
  }
  
  public final Object getValue() {
    return this.value;
  }
  
  public final int hashCode() {
    int j;
    Object object = this.zzoqp;
    int i = 0;
    if (object == null) {
      j = 0;
    } else {
      j = object.hashCode();
    } 
    object = this.value;
    if (object != null)
      i = object.hashCode(); 
    return j ^ i;
  }
  
  public final Object setValue(Object paramObject) {
    zzewx.zza(this.zzoqq);
    Object object = this.value;
    this.value = paramObject;
    return object;
  }
  
  public final String toString() {
    String str1 = String.valueOf(this.zzoqp);
    String str2 = String.valueOf(this.value);
    StringBuilder stringBuilder = new StringBuilder(str1.length() + 1 + str2.length());
    stringBuilder.append(str1);
    stringBuilder.append("=");
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */