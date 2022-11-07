package com.google.android.gms.internal;

import java.util.Comparator;

public final class zzdpm<A extends Comparable<A>> implements Comparator<A> {
  private static zzdpm zzlte = new zzdpm();
  
  public static <T extends Comparable<T>> zzdpm<T> zze(Class<T> paramClass) {
    return zzlte;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdpm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */