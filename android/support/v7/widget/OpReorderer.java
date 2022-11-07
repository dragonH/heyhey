package android.support.v7.widget;

import java.util.List;

class OpReorderer {
  final Callback mCallback;
  
  OpReorderer(Callback paramCallback) {
    this.mCallback = paramCallback;
  }
  
  private int getLastMoveOutOfOrder(List<AdapterHelper.UpdateOp> paramList) {
    int i = paramList.size() - 1;
    for (boolean bool = false; i >= 0; bool = bool1) {
      boolean bool1;
      if (((AdapterHelper.UpdateOp)paramList.get(i)).cmd == 8) {
        bool1 = bool;
        if (bool)
          return i; 
      } else {
        bool1 = true;
      } 
      i--;
    } 
    return -1;
  }
  
  private void swapMoveAdd(List<AdapterHelper.UpdateOp> paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2) {
    int i = paramUpdateOp1.itemCount;
    int j = paramUpdateOp2.positionStart;
    if (i < j) {
      k = -1;
    } else {
      k = 0;
    } 
    int m = paramUpdateOp1.positionStart;
    int n = k;
    if (m < j)
      n = k + 1; 
    if (j <= m)
      paramUpdateOp1.positionStart = m + paramUpdateOp2.itemCount; 
    int k = paramUpdateOp2.positionStart;
    if (k <= i)
      paramUpdateOp1.itemCount = i + paramUpdateOp2.itemCount; 
    paramUpdateOp2.positionStart = k + n;
    paramList.set(paramInt1, paramUpdateOp2);
    paramList.set(paramInt2, paramUpdateOp1);
  }
  
  private void swapMoveOp(List<AdapterHelper.UpdateOp> paramList, int paramInt1, int paramInt2) {
    AdapterHelper.UpdateOp updateOp1 = paramList.get(paramInt1);
    AdapterHelper.UpdateOp updateOp2 = paramList.get(paramInt2);
    int i = updateOp2.cmd;
    if (i != 1) {
      if (i != 2) {
        if (i == 4)
          swapMoveUpdate(paramList, paramInt1, updateOp1, paramInt2, updateOp2); 
      } else {
        swapMoveRemove(paramList, paramInt1, updateOp1, paramInt2, updateOp2);
      } 
    } else {
      swapMoveAdd(paramList, paramInt1, updateOp1, paramInt2, updateOp2);
    } 
  }
  
  void reorderOps(List<AdapterHelper.UpdateOp> paramList) {
    while (true) {
      int i = getLastMoveOutOfOrder(paramList);
      if (i != -1) {
        swapMoveOp(paramList, i, i + 1);
        continue;
      } 
      break;
    } 
  }
  
  void swapMoveRemove(List<AdapterHelper.UpdateOp> paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2) {
    int i = paramUpdateOp1.positionStart;
    int j = paramUpdateOp1.itemCount;
    boolean bool = false;
    if (i < j) {
      if (paramUpdateOp2.positionStart == i && paramUpdateOp2.itemCount == j - i) {
        i = 0;
      } else {
        i = 0;
        int m = paramUpdateOp2.positionStart;
      } 
    } else if (paramUpdateOp2.positionStart == j + 1 && paramUpdateOp2.itemCount == i - j) {
      i = 1;
    } else {
      i = 1;
      int m = paramUpdateOp2.positionStart;
    } 
    bool = true;
    int k = paramUpdateOp2.positionStart;
  }
  
  void swapMoveUpdate(List<AdapterHelper.UpdateOp> paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2) {
    // Byte code:
    //   0: aload_3
    //   1: getfield itemCount : I
    //   4: istore #6
    //   6: aload #5
    //   8: getfield positionStart : I
    //   11: istore #7
    //   13: aconst_null
    //   14: astore #8
    //   16: iload #6
    //   18: iload #7
    //   20: if_icmpge -> 35
    //   23: aload #5
    //   25: iload #7
    //   27: iconst_1
    //   28: isub
    //   29: putfield positionStart : I
    //   32: goto -> 86
    //   35: aload #5
    //   37: getfield itemCount : I
    //   40: istore #9
    //   42: iload #6
    //   44: iload #7
    //   46: iload #9
    //   48: iadd
    //   49: if_icmpge -> 86
    //   52: aload #5
    //   54: iload #9
    //   56: iconst_1
    //   57: isub
    //   58: putfield itemCount : I
    //   61: aload_0
    //   62: getfield mCallback : Landroid/support/v7/widget/OpReorderer$Callback;
    //   65: iconst_4
    //   66: aload_3
    //   67: getfield positionStart : I
    //   70: iconst_1
    //   71: aload #5
    //   73: getfield payload : Ljava/lang/Object;
    //   76: invokeinterface obtainUpdateOp : (IIILjava/lang/Object;)Landroid/support/v7/widget/AdapterHelper$UpdateOp;
    //   81: astore #10
    //   83: goto -> 89
    //   86: aconst_null
    //   87: astore #10
    //   89: aload_3
    //   90: getfield positionStart : I
    //   93: istore #9
    //   95: aload #5
    //   97: getfield positionStart : I
    //   100: istore #7
    //   102: iload #9
    //   104: iload #7
    //   106: if_icmpgt -> 121
    //   109: aload #5
    //   111: iload #7
    //   113: iconst_1
    //   114: iadd
    //   115: putfield positionStart : I
    //   118: goto -> 184
    //   121: aload #5
    //   123: getfield itemCount : I
    //   126: istore #6
    //   128: iload #9
    //   130: iload #7
    //   132: iload #6
    //   134: iadd
    //   135: if_icmpge -> 184
    //   138: iload #7
    //   140: iload #6
    //   142: iadd
    //   143: iload #9
    //   145: isub
    //   146: istore #6
    //   148: aload_0
    //   149: getfield mCallback : Landroid/support/v7/widget/OpReorderer$Callback;
    //   152: iconst_4
    //   153: iload #9
    //   155: iconst_1
    //   156: iadd
    //   157: iload #6
    //   159: aload #5
    //   161: getfield payload : Ljava/lang/Object;
    //   164: invokeinterface obtainUpdateOp : (IIILjava/lang/Object;)Landroid/support/v7/widget/AdapterHelper$UpdateOp;
    //   169: astore #8
    //   171: aload #5
    //   173: aload #5
    //   175: getfield itemCount : I
    //   178: iload #6
    //   180: isub
    //   181: putfield itemCount : I
    //   184: aload_1
    //   185: iload #4
    //   187: aload_3
    //   188: invokeinterface set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   193: pop
    //   194: aload #5
    //   196: getfield itemCount : I
    //   199: ifle -> 215
    //   202: aload_1
    //   203: iload_2
    //   204: aload #5
    //   206: invokeinterface set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   211: pop
    //   212: goto -> 234
    //   215: aload_1
    //   216: iload_2
    //   217: invokeinterface remove : (I)Ljava/lang/Object;
    //   222: pop
    //   223: aload_0
    //   224: getfield mCallback : Landroid/support/v7/widget/OpReorderer$Callback;
    //   227: aload #5
    //   229: invokeinterface recycleUpdateOp : (Landroid/support/v7/widget/AdapterHelper$UpdateOp;)V
    //   234: aload #10
    //   236: ifnull -> 248
    //   239: aload_1
    //   240: iload_2
    //   241: aload #10
    //   243: invokeinterface add : (ILjava/lang/Object;)V
    //   248: aload #8
    //   250: ifnull -> 262
    //   253: aload_1
    //   254: iload_2
    //   255: aload #8
    //   257: invokeinterface add : (ILjava/lang/Object;)V
    //   262: return
  }
  
  static interface Callback {
    AdapterHelper.UpdateOp obtainUpdateOp(int param1Int1, int param1Int2, int param1Int3, Object param1Object);
    
    void recycleUpdateOp(AdapterHelper.UpdateOp param1UpdateOp);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/OpReorderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */