package com.google.android.gms.internal;

final class zzexf {
  static String zzaq(zzeuk paramzzeuk) {
    zzexg zzexg = new zzexg(paramzzeuk);
    StringBuilder stringBuilder = new StringBuilder(zzexg.size());
    for (byte b = 0; b < zzexg.size(); b++) {
      byte b1 = zzexg.zzji(b);
      if (b1 != 34) {
        if (b1 != 39) {
          if (b1 != 92) {
            String str;
            int i;
            switch (b1) {
              default:
                if (b1 < 32 || b1 > 126) {
                  stringBuilder.append('\\');
                  stringBuilder.append((char)((b1 >>> 6 & 0x3) + 48));
                  stringBuilder.append((char)((b1 >>> 3 & 0x7) + 48));
                  i = (b1 & 0x7) + 48;
                } 
                stringBuilder.append((char)i);
                break;
              case 13:
                str = "\\r";
                stringBuilder.append(str);
              case 12:
                str = "\\f";
                stringBuilder.append(str);
              case 11:
                str = "\\v";
                stringBuilder.append(str);
              case 10:
                str = "\\n";
                stringBuilder.append(str);
              case 9:
                str = "\\t";
                stringBuilder.append(str);
              case 8:
                str = "\\b";
                stringBuilder.append(str);
              case 7:
                str = "\\a";
                stringBuilder.append(str);
            } 
          } else {
            String str = "\\\\";
            stringBuilder.append(str);
          } 
        } else {
          String str = "\\'";
          stringBuilder.append(str);
        } 
      } else {
        String str = "\\\"";
        stringBuilder.append(str);
      } 
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzexf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */