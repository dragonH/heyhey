package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zze {
  public static <K, V> Map<K, V> zza(K paramK1, V paramV1, K paramK2, V paramV2) {
    Map<?, ?> map = zzh(2, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <K, V> Map<K, V> zza(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6) {
    Map<?, ?> map = zzh(6, false);
    map.put(paramK1, paramV1);
    map.put(paramK2, paramV2);
    map.put(paramK3, paramV3);
    map.put(paramK4, paramV4);
    map.put(paramK5, paramV5);
    map.put(paramK6, paramV6);
    return Collections.unmodifiableMap((Map)map);
  }
  
  public static <T> Set<T> zza(T paramT1, T paramT2, T paramT3) {
    Set<?> set = zzg(3, false);
    set.add(paramT1);
    set.add(paramT2);
    set.add(paramT3);
    return Collections.unmodifiableSet((Set)set);
  }
  
  public static <T> Set<T> zzb(T... paramVarArgs) {
    int i = paramVarArgs.length;
    if (i != 0) {
      Set<?> set;
      if (i != 1) {
        T t1;
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              Set<?> set2 = zzg(paramVarArgs.length, false);
              Collections.addAll(set2, (Object[])paramVarArgs);
              return Collections.unmodifiableSet((Set)set2);
            } 
            T t4 = paramVarArgs[0];
            T t5 = paramVarArgs[1];
            T t6 = paramVarArgs[2];
            t1 = paramVarArgs[3];
            Set<?> set1 = zzg(4, false);
            set1.add(t4);
            set1.add(t5);
            set1.add(t6);
            set1.add(t1);
            return Collections.unmodifiableSet((Set)set1);
          } 
          return zza(t1[0], t1[1], t1[2]);
        } 
        T t2 = t1[0];
        T t3 = t1[1];
        set = zzg(2, false);
        set.add(t2);
        set.add(t3);
        return Collections.unmodifiableSet((Set)set);
      } 
      return Collections.singleton((T)set[0]);
    } 
    return Collections.emptySet();
  }
  
  private static <T> Set<T> zzg(int paramInt, boolean paramBoolean) {
    return (Set<T>)((paramInt <= 256) ? new ArraySet(paramInt) : new HashSet<T>(paramInt, 1.0F));
  }
  
  private static <K, V> Map<K, V> zzh(int paramInt, boolean paramBoolean) {
    return (Map<K, V>)((paramInt <= 256) ? new ArrayMap(paramInt) : new HashMap<K, V>(paramInt, 1.0F));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */