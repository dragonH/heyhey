package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzk {
  private static final Object zzaqd = new Object();
  
  private final zzr zznfv;
  
  zzk(zzr paramzzr) {
    this.zznfv = paramzzr;
  }
  
  @Nullable
  final String zzcgc() {
    synchronized (zzaqd) {
      String str = this.zznfv.zzhul.getString("topic_operaion_queue", null);
      if (str != null) {
        String[] arrayOfString = str.split(",");
        if (arrayOfString.length > 1 && !TextUtils.isEmpty(arrayOfString[1]))
          return arrayOfString[1]; 
      } 
      return null;
    } 
  }
  
  final void zzqw(String paramString) {
    synchronized (zzaqd) {
      String str = this.zznfv.zzhul.getString("topic_operaion_queue", "");
      int i = String.valueOf(str).length();
      int j = ",".length();
      int k = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + j + k);
      stringBuilder.append(str);
      stringBuilder.append(",");
      stringBuilder.append(paramString);
      paramString = stringBuilder.toString();
      this.zznfv.zzhul.edit().putString("topic_operaion_queue", paramString).apply();
      return;
    } 
  }
  
  final boolean zzra(String paramString) {
    synchronized (zzaqd) {
      String str1 = this.zznfv.zzhul.getString("topic_operaion_queue", "");
      String str2 = String.valueOf(paramString);
      if (str2.length() != 0) {
        str2 = ",".concat(str2);
      } else {
        str2 = new String(",");
      } 
      if (str1.startsWith(str2)) {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {
          paramString = ",".concat(paramString);
        } else {
          paramString = new String(",");
        } 
        paramString = str1.substring(paramString.length());
        this.zznfv.zzhul.edit().putString("topic_operaion_queue", paramString).apply();
        return true;
      } 
      return false;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/iid/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */