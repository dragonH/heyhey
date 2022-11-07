package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzg<T> extends AbstractDataBuffer<T> {
  private boolean zzfqy = false;
  
  private ArrayList<Integer> zzfqz;
  
  protected zzg(DataHolder paramDataHolder) {
    super(paramDataHolder);
  }
  
  private final void zzaix() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzfqy : Z
    //   6: ifne -> 237
    //   9: aload_0
    //   10: getfield zzflf : Lcom/google/android/gms/common/data/DataHolder;
    //   13: getfield zzfqq : I
    //   16: istore_1
    //   17: new java/util/ArrayList
    //   20: astore_2
    //   21: aload_2
    //   22: invokespecial <init> : ()V
    //   25: aload_0
    //   26: aload_2
    //   27: putfield zzfqz : Ljava/util/ArrayList;
    //   30: iload_1
    //   31: ifle -> 232
    //   34: aload_2
    //   35: iconst_0
    //   36: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   39: invokevirtual add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: aload_0
    //   44: invokevirtual zzaiw : ()Ljava/lang/String;
    //   47: astore_3
    //   48: aload_0
    //   49: getfield zzflf : Lcom/google/android/gms/common/data/DataHolder;
    //   52: iconst_0
    //   53: invokevirtual zzbx : (I)I
    //   56: istore #4
    //   58: aload_0
    //   59: getfield zzflf : Lcom/google/android/gms/common/data/DataHolder;
    //   62: aload_3
    //   63: iconst_0
    //   64: iload #4
    //   66: invokevirtual zzd : (Ljava/lang/String;II)Ljava/lang/String;
    //   69: astore #5
    //   71: iconst_1
    //   72: istore #4
    //   74: iload #4
    //   76: iload_1
    //   77: if_icmpge -> 232
    //   80: aload_0
    //   81: getfield zzflf : Lcom/google/android/gms/common/data/DataHolder;
    //   84: iload #4
    //   86: invokevirtual zzbx : (I)I
    //   89: istore #6
    //   91: aload_0
    //   92: getfield zzflf : Lcom/google/android/gms/common/data/DataHolder;
    //   95: aload_3
    //   96: iload #4
    //   98: iload #6
    //   100: invokevirtual zzd : (Ljava/lang/String;II)Ljava/lang/String;
    //   103: astore #7
    //   105: aload #7
    //   107: ifnull -> 148
    //   110: aload #5
    //   112: astore_2
    //   113: aload #7
    //   115: aload #5
    //   117: invokevirtual equals : (Ljava/lang/Object;)Z
    //   120: ifne -> 139
    //   123: aload_0
    //   124: getfield zzfqz : Ljava/util/ArrayList;
    //   127: iload #4
    //   129: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   132: invokevirtual add : (Ljava/lang/Object;)Z
    //   135: pop
    //   136: aload #7
    //   138: astore_2
    //   139: iinc #4, 1
    //   142: aload_2
    //   143: astore #5
    //   145: goto -> 74
    //   148: new java/lang/NullPointerException
    //   151: astore_2
    //   152: aload_3
    //   153: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   156: invokevirtual length : ()I
    //   159: istore_1
    //   160: new java/lang/StringBuilder
    //   163: astore #5
    //   165: aload #5
    //   167: iload_1
    //   168: bipush #78
    //   170: iadd
    //   171: invokespecial <init> : (I)V
    //   174: aload #5
    //   176: ldc 'Missing value for markerColumn: '
    //   178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: aload #5
    //   184: aload_3
    //   185: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload #5
    //   191: ldc ', at row: '
    //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: aload #5
    //   199: iload #4
    //   201: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload #5
    //   207: ldc ', for window: '
    //   209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload #5
    //   215: iload #6
    //   217: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   220: pop
    //   221: aload_2
    //   222: aload #5
    //   224: invokevirtual toString : ()Ljava/lang/String;
    //   227: invokespecial <init> : (Ljava/lang/String;)V
    //   230: aload_2
    //   231: athrow
    //   232: aload_0
    //   233: iconst_1
    //   234: putfield zzfqy : Z
    //   237: aload_0
    //   238: monitorexit
    //   239: return
    //   240: astore_2
    //   241: aload_0
    //   242: monitorexit
    //   243: goto -> 248
    //   246: aload_2
    //   247: athrow
    //   248: goto -> 246
    // Exception table:
    //   from	to	target	type
    //   2	30	240	finally
    //   34	71	240	finally
    //   80	105	240	finally
    //   113	136	240	finally
    //   148	232	240	finally
    //   232	237	240	finally
    //   237	239	240	finally
    //   241	243	240	finally
  }
  
  private final int zzca(int paramInt) {
    if (paramInt >= 0 && paramInt < this.zzfqz.size())
      return ((Integer)this.zzfqz.get(paramInt)).intValue(); 
    StringBuilder stringBuilder = new StringBuilder(53);
    stringBuilder.append("Position ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is out of bounds for this buffer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final T get(int paramInt) {
    zzaix();
    int i = zzca(paramInt);
    if (paramInt < 0 || paramInt == this.zzfqz.size()) {
      j = 0;
      return zzk(i, j);
    } 
    if (paramInt == this.zzfqz.size() - 1) {
      j = this.zzflf.zzfqq;
    } else {
      j = ((Integer)this.zzfqz.get(paramInt + 1)).intValue();
    } 
    int k = j - ((Integer)this.zzfqz.get(paramInt)).intValue();
    int j = k;
    if (k == 1) {
      paramInt = zzca(paramInt);
      this.zzflf.zzbx(paramInt);
      j = k;
    } 
    return zzk(i, j);
  }
  
  public int getCount() {
    zzaix();
    return this.zzfqz.size();
  }
  
  protected abstract String zzaiw();
  
  protected abstract T zzk(int paramInt1, int paramInt2);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/data/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */