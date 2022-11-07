package com.google.android.gms.common.images;

public final class Size {
  private final int zzakq;
  
  private final int zzakr;
  
  public Size(int paramInt1, int paramInt2) {
    this.zzakq = paramInt1;
    this.zzakr = paramInt2;
  }
  
  public static Size parseSize(String paramString) throws NumberFormatException {
    if (paramString != null) {
      int i = paramString.indexOf('*');
      int j = i;
      if (i < 0)
        j = paramString.indexOf('x'); 
      if (j >= 0)
        try {
          return new Size(Integer.parseInt(paramString.substring(0, j)), Integer.parseInt(paramString.substring(j + 1)));
        } catch (NumberFormatException numberFormatException) {
          throw zzfx(paramString);
        }  
      throw zzfx(paramString);
    } 
    throw new IllegalArgumentException("string must not be null");
  }
  
  private static NumberFormatException zzfx(String paramString) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 16);
    stringBuilder.append("Invalid Size: \"");
    stringBuilder.append(paramString);
    stringBuilder.append("\"");
    throw new NumberFormatException(stringBuilder.toString());
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof Size) {
      paramObject = paramObject;
      if (this.zzakq == ((Size)paramObject).zzakq && this.zzakr == ((Size)paramObject).zzakr)
        return true; 
    } 
    return false;
  }
  
  public final int getHeight() {
    return this.zzakr;
  }
  
  public final int getWidth() {
    return this.zzakq;
  }
  
  public final int hashCode() {
    int i = this.zzakr;
    int j = this.zzakq;
    return i ^ (j >>> 16 | j << 16);
  }
  
  public final String toString() {
    int i = this.zzakq;
    int j = this.zzakr;
    StringBuilder stringBuilder = new StringBuilder(23);
    stringBuilder.append(i);
    stringBuilder.append("x");
    stringBuilder.append(j);
    return stringBuilder.toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/images/Size.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */