package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzo {
  private static final Pattern zzfzd = Pattern.compile("\\\\.");
  
  private static final Pattern zzfze = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static boolean zzc(Object paramObject1, Object paramObject2) {
    if (paramObject1 == null && paramObject2 == null)
      return true; 
    if (paramObject1 == null || paramObject2 == null)
      return false; 
    if (paramObject1 instanceof JSONObject && paramObject2 instanceof JSONObject) {
      paramObject1 = paramObject1;
      JSONObject jSONObject = (JSONObject)paramObject2;
      if (paramObject1.length() != jSONObject.length())
        return false; 
      Iterator<String> iterator = paramObject1.keys();
      while (true) {
        if (iterator.hasNext()) {
          paramObject2 = iterator.next();
          if (!jSONObject.has((String)paramObject2))
            return false; 
          try {
            boolean bool = zzc(paramObject1.get((String)paramObject2), jSONObject.get((String)paramObject2));
            if (!bool)
              return false; 
          } catch (JSONException null) {
            return false;
          } 
          continue;
        } 
        return true;
      } 
    } 
    if (jSONException instanceof JSONArray && paramObject2 instanceof JSONArray) {
      JSONArray jSONArray = (JSONArray)jSONException;
      paramObject2 = paramObject2;
      if (jSONArray.length() != paramObject2.length())
        return false; 
      byte b = 0;
      while (b < jSONArray.length()) {
        try {
          boolean bool = zzc(jSONArray.get(b), paramObject2.get(b));
          if (!bool)
            return false; 
          b++;
        } catch (JSONException jSONException) {
          return false;
        } 
      } 
      return true;
    } 
    return jSONException.equals(paramObject2);
  }
  
  public static String zzgl(String paramString) {
    String str = paramString;
    if (!TextUtils.isEmpty(paramString)) {
      StringBuffer stringBuffer;
      Matcher matcher = zzfze.matcher(paramString);
      str = null;
      while (true) {
        StringBuffer stringBuffer1;
        if (matcher.find()) {
          String str1 = str;
          if (str == null)
            stringBuffer1 = new StringBuffer(); 
          char c = matcher.group().charAt(0);
          if (c != '\f') {
            if (c != '\r') {
              if (c != '"') {
                if (c != '/') {
                  if (c != '\\') {
                    StringBuffer stringBuffer4;
                    String str4;
                    StringBuffer stringBuffer3;
                    String str3;
                    StringBuffer stringBuffer2;
                    switch (c) {
                      default:
                        stringBuffer4 = stringBuffer1;
                        continue;
                      case '\n':
                        str4 = "\\\\n";
                        matcher.appendReplacement(stringBuffer1, str4);
                        stringBuffer3 = stringBuffer1;
                        continue;
                      case '\t':
                        str3 = "\\\\t";
                        matcher.appendReplacement(stringBuffer1, str3);
                        stringBuffer2 = stringBuffer1;
                        continue;
                      case '\b':
                        break;
                    } 
                    String str2 = "\\\\b";
                  } else {
                    str = "\\\\\\\\";
                  } 
                } else {
                  str = "\\\\/";
                } 
              } else {
                str = "\\\\\\\"";
              } 
            } else {
              str = "\\\\r";
            } 
          } else {
            str = "\\\\f";
          } 
        } else {
          break;
        } 
        matcher.appendReplacement(stringBuffer1, str);
        stringBuffer = stringBuffer1;
      } 
      if (stringBuffer == null)
        return paramString; 
      matcher.appendTail(stringBuffer);
      str = stringBuffer.toString();
    } 
    return str;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */