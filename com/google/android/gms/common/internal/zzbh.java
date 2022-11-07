package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzbh {
  private final Object zzdef;
  
  private final List<String> zzfvq;
  
  private zzbh(Object paramObject) {
    this.zzdef = zzbp.zzu(paramObject);
    this.zzfvq = new ArrayList<String>();
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder(100);
    stringBuilder.append(this.zzdef.getClass().getSimpleName());
    stringBuilder.append('{');
    int i = this.zzfvq.size();
    for (byte b = 0; b < i; b++) {
      stringBuilder.append(this.zzfvq.get(b));
      if (b < i - 1)
        stringBuilder.append(", "); 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public final zzbh zzg(String paramString, Object paramObject) {
    List<String> list = this.zzfvq;
    paramString = zzbp.<String>zzu(paramString);
    paramObject = String.valueOf(paramObject);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 1 + paramObject.length());
    stringBuilder.append(paramString);
    stringBuilder.append("=");
    stringBuilder.append((String)paramObject);
    list.add(stringBuilder.toString());
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */