package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Iterator;

public final class zzp {
  public static void zza(StringBuilder paramStringBuilder, HashMap<String, String> paramHashMap) {
    paramStringBuilder.append("{");
    Iterator<String> iterator = paramHashMap.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      String str1 = iterator.next();
      if (!bool) {
        paramStringBuilder.append(",");
      } else {
        bool = false;
      } 
      String str2 = paramHashMap.get(str1);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(str1);
      paramStringBuilder.append("\":");
      if (str2 == null) {
        paramStringBuilder.append("null");
        continue;
      } 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(str2);
      paramStringBuilder.append("\"");
    } 
    paramStringBuilder.append("}");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */