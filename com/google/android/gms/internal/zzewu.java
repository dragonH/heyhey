package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Stack;

final class zzewu {
  private final Stack<zzeuk> zzopz = new Stack<zzeuk>();
  
  private zzewu() {}
  
  private final void zzao(zzeuk paramzzeuk) {
    while (true) {
      if (paramzzeuk.zzcsi()) {
        int i = zzkt(paramzzeuk.size());
        int j = zzews.zzcvb()[i + 1];
        if (this.zzopz.isEmpty() || ((zzeuk)this.zzopz.peek()).size() >= j) {
          this.zzopz.push(paramzzeuk);
          return;
        } 
        j = zzews.zzcvb()[i];
        zzeuk zzeuk1;
        for (zzeuk1 = this.zzopz.pop(); !this.zzopz.isEmpty() && ((zzeuk)this.zzopz.peek()).size() < j; zzeuk1 = new zzews(this.zzopz.pop(), zzeuk1, null));
        paramzzeuk = new zzews(zzeuk1, paramzzeuk, null);
        while (!this.zzopz.isEmpty()) {
          j = zzkt(paramzzeuk.size());
          j = zzews.zzcvb()[j + 1];
          if (((zzeuk)this.zzopz.peek()).size() < j)
            paramzzeuk = new zzews(this.zzopz.pop(), paramzzeuk, null); 
        } 
        this.zzopz.push(paramzzeuk);
        return;
      } 
      if (paramzzeuk instanceof zzews) {
        paramzzeuk = paramzzeuk;
        zzao(zzews.zza((zzews)paramzzeuk));
        paramzzeuk = zzews.zzb((zzews)paramzzeuk);
        continue;
      } 
      String str = String.valueOf(paramzzeuk.getClass());
      StringBuilder stringBuilder = new StringBuilder(str.length() + 49);
      stringBuilder.append("Has a new type of ByteString been created? Found ");
      stringBuilder.append(str);
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(stringBuilder.toString());
      throw illegalArgumentException;
    } 
  }
  
  private final zzeuk zzc(zzeuk paramzzeuk1, zzeuk paramzzeuk2) {
    zzao(paramzzeuk1);
    zzao(paramzzeuk2);
    for (paramzzeuk1 = this.zzopz.pop(); !this.zzopz.isEmpty(); paramzzeuk1 = new zzews(this.zzopz.pop(), paramzzeuk1, null));
    return paramzzeuk1;
  }
  
  private static int zzkt(int paramInt) {
    int i = Arrays.binarySearch(zzews.zzcvb(), paramInt);
    paramInt = i;
    if (i < 0)
      paramInt = -(i + 1) - 1; 
    return paramInt;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzewu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */