package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;

public abstract class StatsEvent extends zzbck implements ReflectedParcelable {
  public abstract int getEventType();
  
  public abstract long getTimeMillis();
  
  public String toString() {
    long l1 = getTimeMillis();
    int i = getEventType();
    long l2 = zzakz();
    String str = zzala();
    StringBuilder stringBuilder = new StringBuilder("\t".length() + 51 + "\t".length() + String.valueOf(str).length());
    stringBuilder.append(l1);
    stringBuilder.append("\t");
    stringBuilder.append(i);
    stringBuilder.append("\t");
    stringBuilder.append(l2);
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public abstract long zzakz();
  
  public abstract String zzala();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/stats/StatsEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */