package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

public final class zzdow<K, V> implements Iterator<Map.Entry<K, V>> {
  private final Stack<zzdpf<K, V>> zzlsk;
  
  private final boolean zzlsl;
  
  zzdow(zzdpb<K, V> paramzzdpb, K paramK, Comparator<K> paramComparator, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: new java/util/Stack
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: putfield zzlsk : Ljava/util/Stack;
    //   15: aload_0
    //   16: iload #4
    //   18: putfield zzlsl : Z
    //   21: aload_1
    //   22: invokeinterface isEmpty : ()Z
    //   27: ifne -> 146
    //   30: aload_2
    //   31: ifnull -> 75
    //   34: aload_1
    //   35: invokeinterface getKey : ()Ljava/lang/Object;
    //   40: astore #5
    //   42: iload #4
    //   44: ifeq -> 61
    //   47: aload_3
    //   48: aload_2
    //   49: aload #5
    //   51: invokeinterface compare : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   56: istore #6
    //   58: goto -> 78
    //   61: aload_3
    //   62: aload #5
    //   64: aload_2
    //   65: invokeinterface compare : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   70: istore #6
    //   72: goto -> 78
    //   75: iconst_1
    //   76: istore #6
    //   78: iload #6
    //   80: ifge -> 98
    //   83: iload #4
    //   85: ifne -> 136
    //   88: aload_1
    //   89: invokeinterface zzbqn : ()Lcom/google/android/gms/internal/zzdpb;
    //   94: astore_1
    //   95: goto -> 21
    //   98: iload #6
    //   100: ifne -> 116
    //   103: aload_0
    //   104: getfield zzlsk : Ljava/util/Stack;
    //   107: aload_1
    //   108: checkcast com/google/android/gms/internal/zzdpf
    //   111: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: return
    //   116: aload_0
    //   117: getfield zzlsk : Ljava/util/Stack;
    //   120: aload_1
    //   121: checkcast com/google/android/gms/internal/zzdpf
    //   124: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   127: pop
    //   128: iload #4
    //   130: ifeq -> 136
    //   133: goto -> 88
    //   136: aload_1
    //   137: invokeinterface zzbqm : ()Lcom/google/android/gms/internal/zzdpb;
    //   142: astore_1
    //   143: goto -> 21
    //   146: return
  }
  
  private final Map.Entry<K, V> next() {
    try {
      zzdpb zzdpb = this.zzlsk.pop();
      AbstractMap.SimpleEntry<Object, Object> simpleEntry = new AbstractMap.SimpleEntry<Object, Object>();
      this((K)zzdpb.getKey(), (V)zzdpb.getValue());
      if (this.zzlsl) {
        for (zzdpb = zzdpb.zzbqm(); !zzdpb.isEmpty(); zzdpb = zzdpb.zzbqn())
          this.zzlsk.push((zzdpf<K, V>)zzdpb); 
      } else {
        for (zzdpb = zzdpb.zzbqn(); !zzdpb.isEmpty(); zzdpb = zzdpb.zzbqm())
          this.zzlsk.push((zzdpf<K, V>)zzdpb); 
      } 
      return (Map.Entry)simpleEntry;
    } catch (EmptyStackException emptyStackException) {
      NoSuchElementException noSuchElementException = new NoSuchElementException();
      throw noSuchElementException;
    } 
  }
  
  public final boolean hasNext() {
    return (this.zzlsk.size() > 0);
  }
  
  public final void remove() {
    throw new UnsupportedOperationException("remove called on immutable collection");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */