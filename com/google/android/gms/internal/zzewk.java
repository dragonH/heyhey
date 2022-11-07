package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzewk<K, V> extends LinkedHashMap<K, V> {
  private static final zzewk zzopq;
  
  private boolean zzomt = true;
  
  static {
    zzewk zzewk1 = new zzewk();
    zzopq = zzewk1;
    zzewk1.zzomt = false;
  }
  
  private zzewk() {}
  
  private zzewk(Map<K, V> paramMap) {
    super(paramMap);
  }
  
  private static int zzcf(Object paramObject) {
    if (paramObject instanceof byte[])
      return zzevu.hashCode((byte[])paramObject); 
    if (!(paramObject instanceof zzevv))
      return paramObject.hashCode(); 
    throw new UnsupportedOperationException();
  }
  
  public static <K, V> zzewk<K, V> zzcux() {
    return zzopq;
  }
  
  private final void zzcuz() {
    if (this.zzomt)
      return; 
    throw new UnsupportedOperationException();
  }
  
  public final void clear() {
    zzcuz();
    super.clear();
  }
  
  public final Set<Map.Entry<K, V>> entrySet() {
    return isEmpty() ? Collections.emptySet() : super.entrySet();
  }
  
  public final boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof java/util/Map
    //   4: ifeq -> 170
    //   7: aload_1
    //   8: checkcast java/util/Map
    //   11: astore_2
    //   12: aload_0
    //   13: aload_2
    //   14: if_acmpeq -> 162
    //   17: aload_0
    //   18: invokeinterface size : ()I
    //   23: aload_2
    //   24: invokeinterface size : ()I
    //   29: if_icmpeq -> 37
    //   32: iconst_0
    //   33: istore_3
    //   34: goto -> 164
    //   37: aload_0
    //   38: invokeinterface entrySet : ()Ljava/util/Set;
    //   43: invokeinterface iterator : ()Ljava/util/Iterator;
    //   48: astore #4
    //   50: aload #4
    //   52: invokeinterface hasNext : ()Z
    //   57: ifeq -> 162
    //   60: aload #4
    //   62: invokeinterface next : ()Ljava/lang/Object;
    //   67: checkcast java/util/Map$Entry
    //   70: astore #5
    //   72: aload_2
    //   73: aload #5
    //   75: invokeinterface getKey : ()Ljava/lang/Object;
    //   80: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   85: ifne -> 91
    //   88: goto -> 32
    //   91: aload #5
    //   93: invokeinterface getValue : ()Ljava/lang/Object;
    //   98: astore_1
    //   99: aload_2
    //   100: aload #5
    //   102: invokeinterface getKey : ()Ljava/lang/Object;
    //   107: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   112: astore #5
    //   114: aload_1
    //   115: instanceof [B
    //   118: ifeq -> 146
    //   121: aload #5
    //   123: instanceof [B
    //   126: ifeq -> 146
    //   129: aload_1
    //   130: checkcast [B
    //   133: aload #5
    //   135: checkcast [B
    //   138: invokestatic equals : ([B[B)Z
    //   141: istore #6
    //   143: goto -> 154
    //   146: aload_1
    //   147: aload #5
    //   149: invokevirtual equals : (Ljava/lang/Object;)Z
    //   152: istore #6
    //   154: iload #6
    //   156: ifne -> 50
    //   159: goto -> 32
    //   162: iconst_1
    //   163: istore_3
    //   164: iload_3
    //   165: ifeq -> 170
    //   168: iconst_1
    //   169: ireturn
    //   170: iconst_0
    //   171: ireturn
  }
  
  public final int hashCode() {
    Iterator<Map.Entry> iterator = super.entrySet().iterator();
    int i;
    for (i = 0; iterator.hasNext(); i += zzcf(entry.getValue()) ^ j) {
      Map.Entry entry = iterator.next();
      int j = zzcf(entry.getKey());
    } 
    return i;
  }
  
  public final boolean isMutable() {
    return this.zzomt;
  }
  
  public final V put(K paramK, V paramV) {
    zzcuz();
    zzevu.zzu(paramK);
    zzevu.zzu(paramV);
    return super.put(paramK, paramV);
  }
  
  public final void putAll(Map<? extends K, ? extends V> paramMap) {
    zzcuz();
    for (K k : paramMap.keySet()) {
      zzevu.zzu(k);
      zzevu.zzu(paramMap.get(k));
    } 
    super.putAll(paramMap);
  }
  
  public final V remove(Object paramObject) {
    zzcuz();
    return super.remove(paramObject);
  }
  
  public final void zza(zzewk<K, V> paramzzewk) {
    zzcuz();
    if (!paramzzewk.isEmpty())
      putAll(paramzzewk); 
  }
  
  public final void zzbhs() {
    this.zzomt = false;
  }
  
  public final zzewk<K, V> zzcuy() {
    return isEmpty() ? new zzewk() : new zzewk(this);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */