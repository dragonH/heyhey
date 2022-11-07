package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
public final class DataHolder extends zzbck implements Closeable {
  public static final Parcelable.Creator<DataHolder> CREATOR = new zzf();
  
  private static final zza zzfqs = new zze(new String[0], null);
  
  private boolean mClosed = false;
  
  private int zzdxs;
  
  private final int zzfac;
  
  private final String[] zzfql;
  
  private Bundle zzfqm;
  
  private final CursorWindow[] zzfqn;
  
  private final Bundle zzfqo;
  
  private int[] zzfqp;
  
  int zzfqq;
  
  private boolean zzfqr = true;
  
  DataHolder(int paramInt1, String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt2, Bundle paramBundle) {
    this.zzdxs = paramInt1;
    this.zzfql = paramArrayOfString;
    this.zzfqn = paramArrayOfCursorWindow;
    this.zzfac = paramInt2;
    this.zzfqo = paramBundle;
  }
  
  private DataHolder(zza paramzza, int paramInt, Bundle paramBundle) {
    this(zza.zza(paramzza), zza(paramzza, -1), paramInt, (Bundle)null);
  }
  
  private DataHolder(String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt, Bundle paramBundle) {
    this.zzdxs = 1;
    this.zzfql = (String[])zzbp.zzu(paramArrayOfString);
    this.zzfqn = (CursorWindow[])zzbp.zzu(paramArrayOfCursorWindow);
    this.zzfac = paramInt;
    this.zzfqo = paramBundle;
    zzaiv();
  }
  
  public static zza zza(String[] paramArrayOfString) {
    return new zza(paramArrayOfString, null, null);
  }
  
  private static CursorWindow[] zza(zza paramzza, int paramInt) {
    paramInt = (zza.zza(paramzza)).length;
    boolean bool = false;
    if (paramInt == 0)
      return new CursorWindow[0]; 
    ArrayList<Map> arrayList = zza.zzb(paramzza);
    int i = arrayList.size();
    CursorWindow cursorWindow = new CursorWindow(false);
    ArrayList<CursorWindow> arrayList1 = new ArrayList();
    arrayList1.add(cursorWindow);
    cursorWindow.setNumColumns((zza.zza(paramzza)).length);
    paramInt = 0;
    int j = 0;
    while (paramInt < i) {
      try {
        IllegalArgumentException illegalArgumentException;
        CursorWindow cursorWindow1;
        String str;
        boolean bool1 = cursorWindow.allocRow();
        if (!bool1) {
          StringBuilder stringBuilder = new StringBuilder();
          this(72);
          stringBuilder.append("Allocating additional cursor window for large data set (row ");
          stringBuilder.append(paramInt);
          stringBuilder.append(")");
          Log.d("DataHolder", stringBuilder.toString());
          CursorWindow cursorWindow2 = new CursorWindow();
          this(false);
          cursorWindow2.setStartPosition(paramInt);
          cursorWindow2.setNumColumns((zza.zza(paramzza)).length);
          arrayList1.add(cursorWindow2);
          cursorWindow1 = cursorWindow2;
          if (!cursorWindow2.allocRow()) {
            Log.e("DataHolder", "Unable to allocate row to hold data.");
            arrayList1.remove(cursorWindow2);
            return arrayList1.<CursorWindow>toArray(new CursorWindow[arrayList1.size()]);
          } 
        } 
        Map map = arrayList.get(paramInt);
        byte b = 0;
        bool1 = true;
        while (true) {
          if (b < (zza.zza(paramzza)).length && bool1) {
            String str1 = zza.zza(paramzza)[b];
            Object object = map.get(str1);
            if (object == null) {
              bool1 = cursorWindow1.putNull(paramInt, b);
            } else if (object instanceof String) {
              bool1 = cursorWindow1.putString((String)object, paramInt, b);
            } else {
              long l;
              if (object instanceof Long) {
                l = ((Long)object).longValue();
              } else {
                if (object instanceof Integer) {
                  bool1 = cursorWindow1.putLong(((Integer)object).intValue(), paramInt, b);
                } else {
                  if (object instanceof Boolean) {
                    if (((Boolean)object).booleanValue()) {
                      l = 1L;
                    } else {
                      l = 0L;
                    } 
                  } else {
                    if (object instanceof byte[]) {
                      bool1 = cursorWindow1.putBlob((byte[])object, paramInt, b);
                    } else if (object instanceof Double) {
                      bool1 = cursorWindow1.putDouble(((Double)object).doubleValue(), paramInt, b);
                    } else if (object instanceof Float) {
                      bool1 = cursorWindow1.putDouble(((Float)object).floatValue(), paramInt, b);
                    } else {
                      illegalArgumentException = new IllegalArgumentException();
                      str = String.valueOf(object);
                      paramInt = String.valueOf(str1).length();
                      j = str.length();
                      StringBuilder stringBuilder = new StringBuilder();
                      this(paramInt + 32 + j);
                      stringBuilder.append("Unsupported object for column ");
                      stringBuilder.append(str1);
                      stringBuilder.append(": ");
                      stringBuilder.append(str);
                      this(stringBuilder.toString());
                      throw illegalArgumentException;
                    } 
                    b++;
                  } 
                  bool1 = str.putLong(l, paramInt, b);
                } 
                b++;
              } 
              bool1 = str.putLong(l, paramInt, b);
            } 
          } else {
            break;
          } 
          b++;
        } 
        if (!bool1) {
          if (j == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            this(74);
            stringBuilder.append("Couldn't populate window data for row ");
            stringBuilder.append(paramInt);
            stringBuilder.append(" - allocating new window.");
            Log.d("DataHolder", stringBuilder.toString());
            str.freeLastRow();
            CursorWindow cursorWindow2 = new CursorWindow();
            this(false);
            cursorWindow2.setStartPosition(paramInt);
            cursorWindow2.setNumColumns((zza.zza((zza)illegalArgumentException)).length);
            arrayList1.add(cursorWindow2);
            paramInt--;
            j = 1;
          } else {
            zzb zzb = new zzb();
            this("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
            throw zzb;
          } 
        } else {
          j = 0;
        } 
        paramInt++;
      } catch (RuntimeException runtimeException) {
        j = arrayList1.size();
        for (paramInt = bool; paramInt < j; paramInt++)
          ((CursorWindow)arrayList1.get(paramInt)).close(); 
        throw runtimeException;
      } 
    } 
    return arrayList1.<CursorWindow>toArray(new CursorWindow[arrayList1.size()]);
  }
  
  public static DataHolder zzby(int paramInt) {
    return new DataHolder(zzfqs, paramInt, null);
  }
  
  private final void zzh(String paramString, int paramInt) {
    Bundle bundle = this.zzfqm;
    if (bundle == null || !bundle.containsKey(paramString)) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "No such column: ".concat(paramString);
      } else {
        paramString = new String("No such column: ");
      } 
      throw new IllegalArgumentException(paramString);
    } 
    if (!isClosed()) {
      if (paramInt >= 0 && paramInt < this.zzfqq)
        return; 
      throw new CursorIndexOutOfBoundsException(paramInt, this.zzfqq);
    } 
    throw new IllegalArgumentException("Buffer is closed.");
  }
  
  public final void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mClosed : Z
    //   6: ifne -> 39
    //   9: aload_0
    //   10: iconst_1
    //   11: putfield mClosed : Z
    //   14: iconst_0
    //   15: istore_1
    //   16: aload_0
    //   17: getfield zzfqn : [Landroid/database/CursorWindow;
    //   20: astore_2
    //   21: iload_1
    //   22: aload_2
    //   23: arraylength
    //   24: if_icmpge -> 39
    //   27: aload_2
    //   28: iload_1
    //   29: aaload
    //   30: invokevirtual close : ()V
    //   33: iinc #1, 1
    //   36: goto -> 16
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: astore_2
    //   43: aload_0
    //   44: monitorexit
    //   45: goto -> 50
    //   48: aload_2
    //   49: athrow
    //   50: goto -> 48
    // Exception table:
    //   from	to	target	type
    //   2	14	42	finally
    //   16	33	42	finally
    //   39	41	42	finally
    //   43	45	42	finally
  }
  
  protected final void finalize() throws Throwable {
    try {
      if (this.zzfqr && this.zzfqn.length > 0 && !isClosed()) {
        close();
        String str = toString();
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 178);
        stringBuilder.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
        stringBuilder.append(str);
        stringBuilder.append(")");
        Log.e("DataBuffer", stringBuilder.toString());
      } 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public final int getCount() {
    return this.zzfqq;
  }
  
  public final int getStatusCode() {
    return this.zzfac;
  }
  
  public final boolean isClosed() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mClosed : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzfql, false);
    zzbcn.zza(paramParcel, 2, (Parcelable[])this.zzfqn, paramInt, false);
    zzbcn.zzc(paramParcel, 3, this.zzfac);
    zzbcn.zza(paramParcel, 4, this.zzfqo, false);
    zzbcn.zzc(paramParcel, 1000, this.zzdxs);
    zzbcn.zzai(paramParcel, i);
    if ((paramInt & 0x1) != 0)
      close(); 
  }
  
  public final void zza(String paramString, int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer) {
    zzh(paramString, paramInt1);
    this.zzfqn[paramInt2].copyStringToBuffer(paramInt1, this.zzfqm.getInt(paramString), paramCharArrayBuffer);
  }
  
  public final Bundle zzafi() {
    return this.zzfqo;
  }
  
  public final void zzaiv() {
    this.zzfqm = new Bundle();
    int i = 0;
    int j = 0;
    while (true) {
      String[] arrayOfString = this.zzfql;
      if (j < arrayOfString.length) {
        this.zzfqm.putInt(arrayOfString[j], j);
        j++;
        continue;
      } 
      this.zzfqp = new int[this.zzfqn.length];
      int k = 0;
      j = i;
      while (true) {
        CursorWindow[] arrayOfCursorWindow = this.zzfqn;
        if (j < arrayOfCursorWindow.length) {
          this.zzfqp[j] = k;
          i = arrayOfCursorWindow[j].getStartPosition();
          k += this.zzfqn[j].getNumRows() - k - i;
          j++;
          continue;
        } 
        this.zzfqq = k;
        return;
      } 
      break;
    } 
  }
  
  public final long zzb(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].getLong(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public final int zzbx(int paramInt) {
    boolean bool;
    int[] arrayOfInt;
    int i;
    byte b = 0;
    if (paramInt >= 0 && paramInt < this.zzfqq) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzbg(bool);
    while (true) {
      arrayOfInt = this.zzfqp;
      i = b;
      if (b < arrayOfInt.length) {
        if (paramInt < arrayOfInt[b]) {
          i = b - 1;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    paramInt = i;
    if (i == arrayOfInt.length)
      paramInt = i - 1; 
    return paramInt;
  }
  
  public final int zzc(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].getInt(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public final String zzd(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].getString(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public final boolean zze(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return (Long.valueOf(this.zzfqn[paramInt2].getLong(paramInt1, this.zzfqm.getInt(paramString))).longValue() == 1L);
  }
  
  public final float zzf(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].getFloat(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public final boolean zzfu(String paramString) {
    return this.zzfqm.containsKey(paramString);
  }
  
  public final byte[] zzg(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].getBlob(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public final boolean zzh(String paramString, int paramInt1, int paramInt2) {
    zzh(paramString, paramInt1);
    return this.zzfqn[paramInt2].isNull(paramInt1, this.zzfqm.getInt(paramString));
  }
  
  public static class zza {
    private final String[] zzfql;
    
    private final ArrayList<HashMap<String, Object>> zzfqt;
    
    private final String zzfqu;
    
    private final HashMap<Object, Integer> zzfqv;
    
    private boolean zzfqw;
    
    private String zzfqx;
    
    private zza(String[] param1ArrayOfString, String param1String) {
      this.zzfql = (String[])zzbp.zzu(param1ArrayOfString);
      this.zzfqt = new ArrayList<HashMap<String, Object>>();
      this.zzfqu = param1String;
      this.zzfqv = new HashMap<Object, Integer>();
      this.zzfqw = false;
      this.zzfqx = null;
    }
    
    public zza zza(ContentValues param1ContentValues) {
      zzc.zzr(param1ContentValues);
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>(param1ContentValues.size());
      for (Map.Entry entry : param1ContentValues.valueSet())
        hashMap.put(entry.getKey(), entry.getValue()); 
      return zza((HashMap)hashMap);
    }
    
    public zza zza(HashMap<String, Object> param1HashMap) {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic zzr : (Ljava/lang/Object;)V
      //   4: aload_0
      //   5: getfield zzfqu : Ljava/lang/String;
      //   8: astore_2
      //   9: aload_2
      //   10: ifnonnull -> 18
      //   13: iconst_m1
      //   14: istore_3
      //   15: goto -> 77
      //   18: aload_1
      //   19: aload_2
      //   20: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   23: astore_2
      //   24: aload_2
      //   25: ifnonnull -> 31
      //   28: goto -> 13
      //   31: aload_0
      //   32: getfield zzfqv : Ljava/util/HashMap;
      //   35: aload_2
      //   36: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   39: checkcast java/lang/Integer
      //   42: astore #4
      //   44: aload #4
      //   46: ifnonnull -> 71
      //   49: aload_0
      //   50: getfield zzfqv : Ljava/util/HashMap;
      //   53: aload_2
      //   54: aload_0
      //   55: getfield zzfqt : Ljava/util/ArrayList;
      //   58: invokevirtual size : ()I
      //   61: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   64: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   67: pop
      //   68: goto -> 13
      //   71: aload #4
      //   73: invokevirtual intValue : ()I
      //   76: istore_3
      //   77: iload_3
      //   78: iconst_m1
      //   79: if_icmpne -> 94
      //   82: aload_0
      //   83: getfield zzfqt : Ljava/util/ArrayList;
      //   86: aload_1
      //   87: invokevirtual add : (Ljava/lang/Object;)Z
      //   90: pop
      //   91: goto -> 112
      //   94: aload_0
      //   95: getfield zzfqt : Ljava/util/ArrayList;
      //   98: iload_3
      //   99: invokevirtual remove : (I)Ljava/lang/Object;
      //   102: pop
      //   103: aload_0
      //   104: getfield zzfqt : Ljava/util/ArrayList;
      //   107: iload_3
      //   108: aload_1
      //   109: invokevirtual add : (ILjava/lang/Object;)V
      //   112: aload_0
      //   113: iconst_0
      //   114: putfield zzfqw : Z
      //   117: aload_0
      //   118: areturn
    }
    
    public final DataHolder zzbz(int param1Int) {
      return new DataHolder(this, 0, null, null);
    }
  }
  
  public static final class zzb extends RuntimeException {
    public zzb(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/DataHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */