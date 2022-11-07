package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class zzn<T> extends IObjectWrapper.zza {
  private final T mWrappedObject;
  
  private zzn(T paramT) {
    this.mWrappedObject = paramT;
  }
  
  public static <T> IObjectWrapper zzw(T paramT) {
    return new zzn<T>(paramT);
  }
  
  public static <T> T zzx(IObjectWrapper paramIObjectWrapper) {
    Field field;
    if (paramIObjectWrapper instanceof zzn)
      return ((zzn)paramIObjectWrapper).mWrappedObject; 
    IBinder iBinder = paramIObjectWrapper.asBinder();
    Field[] arrayOfField = iBinder.getClass().getDeclaredFields();
    paramIObjectWrapper = null;
    int i = arrayOfField.length;
    byte b = 0;
    int j;
    for (j = 0; b < i; j = m) {
      Field field1 = arrayOfField[b];
      int m = j;
      if (!field1.isSynthetic()) {
        m = j + 1;
        field = field1;
      } 
      b++;
    } 
    if (j == 1) {
      if (!field.isAccessible()) {
        field.setAccessible(true);
        try {
          return (T)field.get(iBinder);
        } catch (NullPointerException nullPointerException) {
          throw new IllegalArgumentException("Binder object is null.", nullPointerException);
        } catch (IllegalAccessException illegalAccessException) {
          throw new IllegalArgumentException("Could not access the field in remoteBinder.", illegalAccessException);
        } 
      } 
      throw new IllegalArgumentException("IObjectWrapper declared field not private!");
    } 
    int k = arrayOfField.length;
    StringBuilder stringBuilder = new StringBuilder(64);
    stringBuilder.append("Unexpected number of IObjectWrapper declared fields: ");
    stringBuilder.append(k);
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException(stringBuilder.toString());
    throw illegalArgumentException;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamic/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */