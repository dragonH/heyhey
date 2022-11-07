package com.google.android.gms.internal;

import java.util.AbstractList;
import java.util.Collection;

abstract class zzeug<E> extends AbstractList<E> implements zzevy<E> {
  private boolean zzomt = true;
  
  public void add(int paramInt, E paramE) {
    zzcsd();
    super.add(paramInt, paramE);
  }
  
  public boolean add(E paramE) {
    zzcsd();
    return super.add(paramE);
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection) {
    zzcsd();
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection<? extends E> paramCollection) {
    zzcsd();
    return super.addAll(paramCollection);
  }
  
  public void clear() {
    zzcsd();
    super.clear();
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof java.util.List))
      return false; 
    if (!(paramObject instanceof java.util.RandomAccess))
      return super.equals(paramObject); 
    paramObject = paramObject;
    int i = size();
    if (i != paramObject.size())
      return false; 
    for (byte b = 0; b < i; b++) {
      if (!get(b).equals(paramObject.get(b)))
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    int i = size();
    int j = 1;
    for (byte b = 0; b < i; b++)
      j = j * 31 + get(b).hashCode(); 
    return j;
  }
  
  public E remove(int paramInt) {
    zzcsd();
    return super.remove(paramInt);
  }
  
  public boolean remove(Object paramObject) {
    zzcsd();
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    zzcsd();
    return super.removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    zzcsd();
    return super.retainAll(paramCollection);
  }
  
  public E set(int paramInt, E paramE) {
    zzcsd();
    return super.set(paramInt, paramE);
  }
  
  public final void zzbhs() {
    this.zzomt = false;
  }
  
  public final boolean zzcsc() {
    return this.zzomt;
  }
  
  protected final void zzcsd() {
    if (this.zzomt)
      return; 
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */