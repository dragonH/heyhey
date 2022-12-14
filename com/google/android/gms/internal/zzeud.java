package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class zzeud<MessageType extends zzeuc<MessageType, BuilderType>, BuilderType extends zzeud<MessageType, BuilderType>> implements zzewm {
  protected static <T> void zza(Iterable<T> paramIterable, List<? super T> paramList) {
    String str;
    zzevu.zzu(paramIterable);
    if (paramIterable instanceof zzewg) {
      List<?> list = ((zzewg)paramIterable).zzcuw();
      paramIterable = (zzewg)paramList;
      int i = paramList.size();
      Iterator<?> iterator = list.iterator();
      while (iterator.hasNext()) {
        list = (List<?>)iterator.next();
        if (list == null) {
          int j = paramIterable.size();
          StringBuilder stringBuilder = new StringBuilder(37);
          stringBuilder.append("Element at index ");
          stringBuilder.append(j - i);
          stringBuilder.append(" is null.");
          str = stringBuilder.toString();
          for (j = paramIterable.size() - 1; j >= i; j--)
            paramIterable.remove(j); 
          throw new NullPointerException(str);
        } 
        if (!(list instanceof zzeuk))
          paramIterable.add((T)list); 
      } 
      return;
    } 
    if (paramIterable instanceof zzewq) {
      str.addAll((Collection)paramIterable);
      return;
    } 
    zzb(paramIterable, (List<? super T>)str);
  }
  
  private static <T> void zzb(Iterable<T> paramIterable, List<? super T> paramList) {
    if (paramList instanceof ArrayList && paramIterable instanceof Collection)
      ((ArrayList)paramList).ensureCapacity(paramList.size() + ((Collection)paramIterable).size()); 
    int i = paramList.size();
    Iterator<T> iterator = paramIterable.iterator();
    while (iterator.hasNext()) {
      T t = iterator.next();
      if (t == null) {
        int j = paramList.size();
        StringBuilder stringBuilder = new StringBuilder(37);
        stringBuilder.append("Element at index ");
        stringBuilder.append(j - i);
        stringBuilder.append(" is null.");
        String str = stringBuilder.toString();
        for (j = paramList.size() - 1; j >= i; j--)
          paramList.remove(j); 
        throw new NullPointerException(str);
      } 
      paramList.add(t);
    } 
  }
  
  protected abstract BuilderType zza(MessageType paramMessageType);
  
  public abstract BuilderType zza(zzeut paramzzeut, zzevd paramzzevd) throws IOException;
  
  public abstract BuilderType zzcsb();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */