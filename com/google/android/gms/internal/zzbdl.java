package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzbdl {
  protected static <O, I> I zza(zzbdm<I, O> paramzzbdm, Object paramObject) {
    return (I)((zzbdm.zzc(paramzzbdm) != null) ? (Object)paramzzbdm.convertBack((O)paramObject) : paramObject);
  }
  
  private static void zza(StringBuilder paramStringBuilder, zzbdm paramzzbdm, Object paramObject) {
    String str;
    int i = paramzzbdm.zzfwq;
    if (i == 11) {
      str = ((zzbdl)paramzzbdm.zzfww.cast(paramObject)).toString();
    } else if (i == 7) {
      str = "\"";
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzo.zzgl((String)paramObject));
    } else {
      paramStringBuilder.append(paramObject);
      return;
    } 
    paramStringBuilder.append(str);
  }
  
  private static void zza(StringBuilder paramStringBuilder, zzbdm paramzzbdm, ArrayList<Object> paramArrayList) {
    paramStringBuilder.append("[");
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++) {
      if (b > 0)
        paramStringBuilder.append(","); 
      Object object = paramArrayList.get(b);
      if (object != null)
        zza(paramStringBuilder, paramzzbdm, object); 
    } 
    paramStringBuilder.append("]");
  }
  
  public String toString() {
    String str;
    Map<String, zzbdm<?, ?>> map = zzzz();
    StringBuilder stringBuilder = new StringBuilder(100);
    for (String str1 : map.keySet()) {
      zzbdm<Object, ?> zzbdm = (zzbdm)map.get(str1);
      if (zza(zzbdm)) {
        ArrayList arrayList = (ArrayList)zza(zzbdm, zzb(zzbdm));
        if (stringBuilder.length() == 0) {
          str = "{";
        } else {
          str = ",";
        } 
        stringBuilder.append(str);
        stringBuilder.append("\"");
        stringBuilder.append(str1);
        stringBuilder.append("\":");
        if (arrayList == null) {
          stringBuilder.append("null");
          continue;
        } 
        switch (zzbdm.zzfws) {
          default:
            if (zzbdm.zzfwr) {
              zza(stringBuilder, zzbdm, arrayList);
              continue;
            } 
            break;
          case 10:
            zzp.zza(stringBuilder, (HashMap)arrayList);
            continue;
          case 9:
            stringBuilder.append("\"");
            str = zzb.zzk((byte[])arrayList);
            stringBuilder.append(str);
            stringBuilder.append("\"");
            continue;
          case 8:
            stringBuilder.append("\"");
            str = zzb.zzj((byte[])arrayList);
            stringBuilder.append(str);
            stringBuilder.append("\"");
            continue;
        } 
        zza(stringBuilder, zzbdm, arrayList);
      } 
    } 
    if (stringBuilder.length() > 0) {
      str = "}";
    } else {
      str = "{}";
    } 
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  protected boolean zza(zzbdm paramzzbdm) {
    if (paramzzbdm.zzfws == 11) {
      if (paramzzbdm.zzfwt)
        throw new UnsupportedOperationException("Concrete type arrays not supported"); 
      throw new UnsupportedOperationException("Concrete types not supported");
    } 
    return zzgj(paramzzbdm.zzfwu);
  }
  
  protected Object zzb(zzbdm paramzzbdm) {
    String str = paramzzbdm.zzfwu;
    if (paramzzbdm.zzfww != null) {
      zzgi(str);
      zzbp.zza(true, "Concrete field shouldn't be value object: %s", new Object[] { paramzzbdm.zzfwu });
      try {
        char c = Character.toUpperCase(str.charAt(0));
        str = str.substring(1);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 4);
        stringBuilder.append("get");
        stringBuilder.append(c);
        stringBuilder.append(str);
        String str1 = stringBuilder.toString();
        return getClass().getMethod(str1, new Class[0]).invoke(this, new Object[0]);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      } 
    } 
    return zzgi(str);
  }
  
  protected abstract Object zzgi(String paramString);
  
  protected abstract boolean zzgj(String paramString);
  
  public abstract Map<String, zzbdm<?, ?>> zzzz();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */