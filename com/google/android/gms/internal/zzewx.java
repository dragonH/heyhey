package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzewx<K extends Comparable<K>, V> extends AbstractMap<K, V> {
  private boolean zzkff;
  
  private final int zzoqi;
  
  private List<zzexc> zzoqj;
  
  private Map<K, V> zzoqk;
  
  private volatile zzexe zzoql;
  
  private Map<K, V> zzoqm;
  
  private zzewx(int paramInt) {
    this.zzoqi = paramInt;
    this.zzoqj = Collections.emptyList();
    this.zzoqk = Collections.emptyMap();
    this.zzoqm = Collections.emptyMap();
  }
  
  private final int zza(K paramK) {
    int i = this.zzoqj.size() - 1;
    if (i >= 0) {
      int k = paramK.compareTo((Comparable)((zzexc)this.zzoqj.get(i)).getKey());
      if (k > 0)
        return -(i + 2); 
      if (k == 0)
        return i; 
    } 
    int j = 0;
    while (j <= i) {
      int k = (j + i) / 2;
      int m = paramK.compareTo((Comparable)((zzexc)this.zzoqj.get(k)).getKey());
      if (m < 0) {
        i = k - 1;
        continue;
      } 
      if (m > 0) {
        j = k + 1;
        continue;
      } 
      return k;
    } 
    return -(j + 1);
  }
  
  private final void zzcvg() {
    if (!this.zzkff)
      return; 
    throw new UnsupportedOperationException();
  }
  
  private final SortedMap<K, V> zzcvh() {
    zzcvg();
    if (this.zzoqk.isEmpty() && !(this.zzoqk instanceof TreeMap)) {
      TreeMap<Object, Object> treeMap1 = new TreeMap<Object, Object>();
      this.zzoqk = (Map)treeMap1;
      TreeMap<Object, Object> treeMap2 = treeMap1;
      this.zzoqm = (Map)treeMap1.descendingMap();
    } 
    return (SortedMap<K, V>)this.zzoqk;
  }
  
  static <FieldDescriptorType extends zzevg<FieldDescriptorType>> zzewx<FieldDescriptorType, Object> zzku(int paramInt) {
    return new zzewy(paramInt);
  }
  
  private final V zzkw(int paramInt) {
    zzcvg();
    Object object = ((zzexc)this.zzoqj.remove(paramInt)).getValue();
    if (!this.zzoqk.isEmpty()) {
      Iterator<Map.Entry<Object, Object>> iterator = zzcvh().entrySet().iterator();
      this.zzoqj.add(new zzexc(this, iterator.next()));
      iterator.remove();
    } 
    return (V)object;
  }
  
  public void clear() {
    zzcvg();
    if (!this.zzoqj.isEmpty())
      this.zzoqj.clear(); 
    if (!this.zzoqk.isEmpty())
      this.zzoqk.clear(); 
  }
  
  public boolean containsKey(Object paramObject) {
    paramObject = paramObject;
    return (zza((K)paramObject) >= 0 || this.zzoqk.containsKey(paramObject));
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    if (this.zzoql == null)
      this.zzoql = new zzexe(this, null); 
    return this.zzoql;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzewx))
      return super.equals(paramObject); 
    paramObject = paramObject;
    int i = size();
    if (i != paramObject.size())
      return false; 
    int j = zzcve();
    if (j != paramObject.zzcve())
      return entrySet().equals(paramObject.entrySet()); 
    for (byte b = 0; b < j; b++) {
      if (!zzkv(b).equals(paramObject.zzkv(b)))
        return false; 
    } 
    return (j != i) ? this.zzoqk.equals(((zzewx)paramObject).zzoqk) : true;
  }
  
  public V get(Object paramObject) {
    paramObject = paramObject;
    int i = zza((K)paramObject);
    return (V)((i >= 0) ? ((zzexc)this.zzoqj.get(i)).getValue() : (Object)this.zzoqk.get(paramObject));
  }
  
  public int hashCode() {
    int i = zzcve();
    int j = 0;
    int k = 0;
    while (j < i) {
      k += ((zzexc)this.zzoqj.get(j)).hashCode();
      j++;
    } 
    j = k;
    if (this.zzoqk.size() > 0)
      j = k + this.zzoqk.hashCode(); 
    return j;
  }
  
  public final boolean isImmutable() {
    return this.zzkff;
  }
  
  public V remove(Object paramObject) {
    zzcvg();
    paramObject = paramObject;
    int i = zza((K)paramObject);
    return (i >= 0) ? zzkw(i) : (this.zzoqk.isEmpty() ? null : this.zzoqk.remove(paramObject));
  }
  
  public int size() {
    return this.zzoqj.size() + this.zzoqk.size();
  }
  
  public final V zza(K paramK, V paramV) {
    zzcvg();
    int i = zza(paramK);
    if (i >= 0)
      return (V)((zzexc)this.zzoqj.get(i)).setValue(paramV); 
    zzcvg();
    if (this.zzoqj.isEmpty() && !(this.zzoqj instanceof ArrayList))
      this.zzoqj = new ArrayList<zzexc>(this.zzoqi); 
    i = -(i + 1);
    if (i >= this.zzoqi)
      return zzcvh().put(paramK, paramV); 
    int j = this.zzoqj.size();
    int k = this.zzoqi;
    if (j == k) {
      zzexc zzexc = this.zzoqj.remove(k - 1);
      zzcvh().put((K)zzexc.getKey(), (V)zzexc.getValue());
    } 
    this.zzoqj.add(i, new zzexc(this, paramK, paramV));
    return null;
  }
  
  public void zzbhs() {
    if (!this.zzkff) {
      Map<?, ?> map;
      if (this.zzoqk.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.zzoqk);
      } 
      this.zzoqk = (Map)map;
      if (this.zzoqm.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.zzoqm);
      } 
      this.zzoqm = (Map)map;
      this.zzkff = true;
    } 
  }
  
  public final int zzcve() {
    return this.zzoqj.size();
  }
  
  public final Iterable<Map.Entry<K, V>> zzcvf() {
    return this.zzoqk.isEmpty() ? zzewz.zzcvi() : this.zzoqk.entrySet();
  }
  
  public final Map.Entry<K, V> zzkv(int paramInt) {
    return this.zzoqj.get(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */