package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzeyo {
  private static void zza(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2) throws IllegalAccessException, InvocationTargetException {
    if (paramObject != null) {
      if (paramObject instanceof zzeyn) {
        int i = paramStringBuffer1.length();
        if (paramString != null) {
          paramStringBuffer2.append(paramStringBuffer1);
          paramStringBuffer2.append(zzto(paramString));
          paramStringBuffer2.append(" <\n");
          paramStringBuffer1.append("  ");
        } 
        Class<?> clazz = paramObject.getClass();
        for (Field field : clazz.getFields()) {
          int k = field.getModifiers();
          String str = field.getName();
          if (!"cachedSize".equals(str) && (k & 0x1) == 1 && (k & 0x8) != 8 && !str.startsWith("_") && !str.endsWith("_")) {
            Class<?> clazz1 = field.getType();
            Object object = field.get(paramObject);
            if (clazz1.isArray() && clazz1.getComponentType() != byte.class) {
              if (object == null) {
                k = 0;
              } else {
                k = Array.getLength(object);
              } 
              for (byte b1 = 0; b1 < k; b1++)
                zza(str, Array.get(object, b1), paramStringBuffer1, paramStringBuffer2); 
            } else {
              zza(str, object, paramStringBuffer1, paramStringBuffer2);
            } 
          } 
        } 
        Method[] arrayOfMethod = clazz.getMethods();
        int j = arrayOfMethod.length;
        byte b = 0;
        while (true) {
          if (b < j) {
            String str = arrayOfMethod[b].getName();
            if (str.startsWith("set")) {
              String str1 = str.substring(3);
              try {
                str = String.valueOf(str1);
                if (str.length() != 0) {
                  str = "has".concat(str);
                } else {
                  str = new String("has");
                } 
                Method method = clazz.getMethod(str, new Class[0]);
                if (((Boolean)method.invoke(paramObject, new Object[0])).booleanValue()) {
                  String str2 = String.valueOf(str1);
                  if (str2.length() != 0) {
                    str2 = "get".concat(str2);
                  } else {
                    str2 = new String("get");
                  } 
                  Method method1 = clazz.getMethod(str2, new Class[0]);
                  zza(str1, method1.invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
                } 
              } catch (NoSuchMethodException noSuchMethodException) {}
            } 
            b++;
            continue;
          } 
          if (paramString != null) {
            paramStringBuffer1.setLength(i);
            paramStringBuffer2.append(paramStringBuffer1);
            paramStringBuffer2.append(">\n");
          } 
          return;
        } 
      } 
      paramString = zzto(paramString);
      paramStringBuffer2.append(paramStringBuffer1);
      paramStringBuffer2.append(paramString);
      paramStringBuffer2.append(": ");
      if (paramObject instanceof String) {
        paramObject = paramObject;
        Object object = paramObject;
        if (!paramObject.startsWith("http")) {
          object = paramObject;
          if (paramObject.length() > 200)
            object = String.valueOf(paramObject.substring(0, 200)).concat("[...]"); 
        } 
        object = zzgl((String)object);
        paramStringBuffer2.append("\"");
        paramStringBuffer2.append((String)object);
        paramStringBuffer2.append("\"");
      } else if (paramObject instanceof byte[]) {
        zza((byte[])paramObject, paramStringBuffer2);
      } else {
        paramStringBuffer2.append(paramObject);
      } 
      paramStringBuffer2.append("\n");
    } 
  }
  
  private static void zza(byte[] paramArrayOfbyte, StringBuffer paramStringBuffer) {
    if (paramArrayOfbyte == null) {
      paramStringBuffer.append("\"\"");
      return;
    } 
    paramStringBuffer.append('"');
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      int i = paramArrayOfbyte[b] & 0xFF;
      if (i == 92 || i == 34) {
        paramStringBuffer.append('\\');
      } else if (i < 32 || i >= 127) {
        paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(i) }));
        continue;
      } 
      paramStringBuffer.append((char)i);
      continue;
    } 
    paramStringBuffer.append('"');
  }
  
  public static <T extends zzeyn> String zzd(T paramT) {
    if (paramT == null)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    try {
      StringBuffer stringBuffer1 = new StringBuffer();
      this();
      zza(null, paramT, stringBuffer1, stringBuffer);
      return stringBuffer.toString();
    } catch (IllegalAccessException illegalAccessException) {
      String str = String.valueOf(illegalAccessException.getMessage());
      return (str.length() != 0) ? "Error printing proto: ".concat(str) : new String("Error printing proto: ");
    } catch (InvocationTargetException invocationTargetException) {
      String str = String.valueOf(invocationTargetException.getMessage());
      return (str.length() != 0) ? "Error printing proto: ".concat(str) : new String("Error printing proto: ");
    } 
  }
  
  private static String zzgl(String paramString) {
    int i = paramString.length();
    StringBuilder stringBuilder = new StringBuilder(i);
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (c >= ' ' && c <= '~' && c != '"' && c != '\'') {
        stringBuilder.append(c);
      } else {
        stringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
      } 
    } 
    return stringBuilder.toString();
  }
  
  private static String zzto(String paramString) {
    // Byte code:
    //   0: new java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: iconst_0
    //   9: istore_2
    //   10: iload_2
    //   11: aload_0
    //   12: invokevirtual length : ()I
    //   15: if_icmpge -> 74
    //   18: aload_0
    //   19: iload_2
    //   20: invokevirtual charAt : (I)C
    //   23: istore_3
    //   24: iload_2
    //   25: ifne -> 48
    //   28: iload_3
    //   29: invokestatic toLowerCase : (C)C
    //   32: istore #4
    //   34: iload #4
    //   36: istore #5
    //   38: aload_1
    //   39: iload #5
    //   41: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   44: pop
    //   45: goto -> 68
    //   48: iload_3
    //   49: istore #5
    //   51: iload_3
    //   52: invokestatic isUpperCase : (C)Z
    //   55: ifeq -> 38
    //   58: aload_1
    //   59: bipush #95
    //   61: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   64: pop
    //   65: goto -> 28
    //   68: iinc #2, 1
    //   71: goto -> 10
    //   74: aload_1
    //   75: invokevirtual toString : ()Ljava/lang/String;
    //   78: areturn
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeyo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */