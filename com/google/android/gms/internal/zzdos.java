package com.google.android.gms.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public abstract class zzdos<K, V> implements Iterable<Map.Entry<K, V>> {
  public abstract boolean containsKey(K paramK);
  
  public boolean equals(Object<Map.Entry<K, V>> paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzdos))
      return false; 
    zzdos zzdos1 = (zzdos)paramObject;
    if (!getComparator().equals(zzdos1.getComparator()))
      return false; 
    if (size() != zzdos1.size())
      return false; 
    paramObject = (Object<Map.Entry<K, V>>)iterator();
    Iterator iterator = zzdos1.iterator();
    while (paramObject.hasNext()) {
      if (!((Map.Entry)paramObject.next()).equals(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public abstract V get(K paramK);
  
  public abstract Comparator<K> getComparator();
  
  public int hashCode() {
    int i = getComparator().hashCode();
    Iterator<Map.Entry<K, V>> iterator = iterator();
    while (iterator.hasNext())
      i = i * 31 + ((Map.Entry)iterator.next()).hashCode(); 
    return i;
  }
  
  public abstract int indexOf(K paramK);
  
  public abstract boolean isEmpty();
  
  public abstract Iterator<Map.Entry<K, V>> iterator();
  
  public abstract int size();
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getClass().getSimpleName());
    stringBuilder.append("{");
    Iterator<Map.Entry<K, V>> iterator = iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      if (bool) {
        bool = false;
      } else {
        stringBuilder.append(", ");
      } 
      stringBuilder.append("(");
      stringBuilder.append(entry.getKey());
      stringBuilder.append("=>");
      stringBuilder.append(entry.getValue());
      stringBuilder.append(")");
    } 
    stringBuilder.append("};");
    return stringBuilder.toString();
  }
  
  public abstract void zza(zzdpd<K, V> paramzzdpd);
  
  public abstract zzdos<K, V> zzay(K paramK);
  
  public abstract Iterator<Map.Entry<K, V>> zzaz(K paramK);
  
  public abstract K zzba(K paramK);
  
  public abstract K zzbqd();
  
  public abstract K zzbqe();
  
  public abstract Iterator<Map.Entry<K, V>> zzbqf();
  
  public abstract zzdos<K, V> zzg(K paramK, V paramV);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */