package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap<K, V> {
  private static final int BASE_SIZE = 4;
  
  private static final int CACHE_SIZE = 10;
  
  private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
  
  private static final boolean DEBUG = false;
  
  private static final String TAG = "ArrayMap";
  
  static Object[] mBaseCache;
  
  static int mBaseCacheSize;
  
  static Object[] mTwiceBaseCache;
  
  static int mTwiceBaseCacheSize;
  
  Object[] mArray;
  
  int[] mHashes;
  
  int mSize;
  
  public SimpleArrayMap() {
    this.mHashes = ContainerHelpers.EMPTY_INTS;
    this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    this.mSize = 0;
  }
  
  public SimpleArrayMap(int paramInt) {
    if (paramInt == 0) {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    } else {
      allocArrays(paramInt);
    } 
    this.mSize = 0;
  }
  
  public SimpleArrayMap(SimpleArrayMap<K, V> paramSimpleArrayMap) {
    this();
    if (paramSimpleArrayMap != null)
      putAll(paramSimpleArrayMap); 
  }
  
  private void allocArrays(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: bipush #8
    //   3: if_icmpne -> 73
    //   6: ldc android/support/v4/util/ArrayMap
    //   8: monitorenter
    //   9: getstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCache : [Ljava/lang/Object;
    //   12: astore_2
    //   13: aload_2
    //   14: ifnull -> 61
    //   17: aload_0
    //   18: aload_2
    //   19: putfield mArray : [Ljava/lang/Object;
    //   22: aload_2
    //   23: iconst_0
    //   24: aaload
    //   25: checkcast [Ljava/lang/Object;
    //   28: putstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCache : [Ljava/lang/Object;
    //   31: aload_0
    //   32: aload_2
    //   33: iconst_1
    //   34: aaload
    //   35: checkcast [I
    //   38: putfield mHashes : [I
    //   41: aload_2
    //   42: iconst_1
    //   43: aconst_null
    //   44: aastore
    //   45: aload_2
    //   46: iconst_0
    //   47: aconst_null
    //   48: aastore
    //   49: getstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCacheSize : I
    //   52: iconst_1
    //   53: isub
    //   54: putstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCacheSize : I
    //   57: ldc android/support/v4/util/ArrayMap
    //   59: monitorexit
    //   60: return
    //   61: ldc android/support/v4/util/ArrayMap
    //   63: monitorexit
    //   64: goto -> 145
    //   67: astore_2
    //   68: ldc android/support/v4/util/ArrayMap
    //   70: monitorexit
    //   71: aload_2
    //   72: athrow
    //   73: iload_1
    //   74: iconst_4
    //   75: if_icmpne -> 145
    //   78: ldc android/support/v4/util/ArrayMap
    //   80: monitorenter
    //   81: getstatic android/support/v4/util/SimpleArrayMap.mBaseCache : [Ljava/lang/Object;
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull -> 133
    //   89: aload_0
    //   90: aload_2
    //   91: putfield mArray : [Ljava/lang/Object;
    //   94: aload_2
    //   95: iconst_0
    //   96: aaload
    //   97: checkcast [Ljava/lang/Object;
    //   100: putstatic android/support/v4/util/SimpleArrayMap.mBaseCache : [Ljava/lang/Object;
    //   103: aload_0
    //   104: aload_2
    //   105: iconst_1
    //   106: aaload
    //   107: checkcast [I
    //   110: putfield mHashes : [I
    //   113: aload_2
    //   114: iconst_1
    //   115: aconst_null
    //   116: aastore
    //   117: aload_2
    //   118: iconst_0
    //   119: aconst_null
    //   120: aastore
    //   121: getstatic android/support/v4/util/SimpleArrayMap.mBaseCacheSize : I
    //   124: iconst_1
    //   125: isub
    //   126: putstatic android/support/v4/util/SimpleArrayMap.mBaseCacheSize : I
    //   129: ldc android/support/v4/util/ArrayMap
    //   131: monitorexit
    //   132: return
    //   133: ldc android/support/v4/util/ArrayMap
    //   135: monitorexit
    //   136: goto -> 145
    //   139: astore_2
    //   140: ldc android/support/v4/util/ArrayMap
    //   142: monitorexit
    //   143: aload_2
    //   144: athrow
    //   145: aload_0
    //   146: iload_1
    //   147: newarray int
    //   149: putfield mHashes : [I
    //   152: aload_0
    //   153: iload_1
    //   154: iconst_1
    //   155: ishl
    //   156: anewarray java/lang/Object
    //   159: putfield mArray : [Ljava/lang/Object;
    //   162: return
    // Exception table:
    //   from	to	target	type
    //   9	13	67	finally
    //   17	41	67	finally
    //   49	60	67	finally
    //   61	64	67	finally
    //   68	71	67	finally
    //   81	85	139	finally
    //   89	113	139	finally
    //   121	132	139	finally
    //   133	136	139	finally
    //   140	143	139	finally
  }
  
  private static int binarySearchHashes(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    try {
      return ContainerHelpers.binarySearch(paramArrayOfint, paramInt1, paramInt2);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      throw new ConcurrentModificationException();
    } 
  }
  
  private static void freeArrays(int[] paramArrayOfint, Object[] paramArrayOfObject, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: bipush #8
    //   4: if_icmpne -> 73
    //   7: ldc android/support/v4/util/ArrayMap
    //   9: monitorenter
    //   10: getstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCacheSize : I
    //   13: bipush #10
    //   15: if_icmpge -> 61
    //   18: aload_1
    //   19: iconst_0
    //   20: getstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCache : [Ljava/lang/Object;
    //   23: aastore
    //   24: aload_1
    //   25: iconst_1
    //   26: aload_0
    //   27: aastore
    //   28: iload_2
    //   29: iconst_1
    //   30: ishl
    //   31: iconst_1
    //   32: isub
    //   33: istore_2
    //   34: iload_2
    //   35: iconst_2
    //   36: if_icmplt -> 49
    //   39: aload_1
    //   40: iload_2
    //   41: aconst_null
    //   42: aastore
    //   43: iinc #2, -1
    //   46: goto -> 34
    //   49: aload_1
    //   50: putstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCache : [Ljava/lang/Object;
    //   53: getstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCacheSize : I
    //   56: iconst_1
    //   57: iadd
    //   58: putstatic android/support/v4/util/SimpleArrayMap.mTwiceBaseCacheSize : I
    //   61: ldc android/support/v4/util/ArrayMap
    //   63: monitorexit
    //   64: goto -> 145
    //   67: astore_0
    //   68: ldc android/support/v4/util/ArrayMap
    //   70: monitorexit
    //   71: aload_0
    //   72: athrow
    //   73: aload_0
    //   74: arraylength
    //   75: iconst_4
    //   76: if_icmpne -> 145
    //   79: ldc android/support/v4/util/ArrayMap
    //   81: monitorenter
    //   82: getstatic android/support/v4/util/SimpleArrayMap.mBaseCacheSize : I
    //   85: bipush #10
    //   87: if_icmpge -> 133
    //   90: aload_1
    //   91: iconst_0
    //   92: getstatic android/support/v4/util/SimpleArrayMap.mBaseCache : [Ljava/lang/Object;
    //   95: aastore
    //   96: aload_1
    //   97: iconst_1
    //   98: aload_0
    //   99: aastore
    //   100: iload_2
    //   101: iconst_1
    //   102: ishl
    //   103: iconst_1
    //   104: isub
    //   105: istore_2
    //   106: iload_2
    //   107: iconst_2
    //   108: if_icmplt -> 121
    //   111: aload_1
    //   112: iload_2
    //   113: aconst_null
    //   114: aastore
    //   115: iinc #2, -1
    //   118: goto -> 106
    //   121: aload_1
    //   122: putstatic android/support/v4/util/SimpleArrayMap.mBaseCache : [Ljava/lang/Object;
    //   125: getstatic android/support/v4/util/SimpleArrayMap.mBaseCacheSize : I
    //   128: iconst_1
    //   129: iadd
    //   130: putstatic android/support/v4/util/SimpleArrayMap.mBaseCacheSize : I
    //   133: ldc android/support/v4/util/ArrayMap
    //   135: monitorexit
    //   136: goto -> 145
    //   139: astore_0
    //   140: ldc android/support/v4/util/ArrayMap
    //   142: monitorexit
    //   143: aload_0
    //   144: athrow
    //   145: return
    // Exception table:
    //   from	to	target	type
    //   10	24	67	finally
    //   49	61	67	finally
    //   61	64	67	finally
    //   68	71	67	finally
    //   82	96	139	finally
    //   121	133	139	finally
    //   133	136	139	finally
    //   140	143	139	finally
  }
  
  public void clear() {
    int i = this.mSize;
    if (i > 0) {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
      freeArrays(arrayOfInt, arrayOfObject, i);
    } 
    if (this.mSize <= 0)
      return; 
    throw new ConcurrentModificationException();
  }
  
  public boolean containsKey(Object paramObject) {
    boolean bool;
    if (indexOfKey(paramObject) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean containsValue(Object paramObject) {
    boolean bool;
    if (indexOfValue(paramObject) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void ensureCapacity(int paramInt) {
    int i = this.mSize;
    int[] arrayOfInt = this.mHashes;
    if (arrayOfInt.length < paramInt) {
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      if (this.mSize > 0) {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, i);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, i << 1);
      } 
      freeArrays(arrayOfInt, arrayOfObject, i);
    } 
    if (this.mSize == i)
      return; 
    throw new ConcurrentModificationException();
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject instanceof SimpleArrayMap) {
      SimpleArrayMap simpleArrayMap = (SimpleArrayMap)paramObject;
      if (size() != simpleArrayMap.size())
        return false; 
      byte b = 0;
      try {
        while (b < this.mSize) {
          K k = keyAt(b);
          V v = valueAt(b);
          paramObject = simpleArrayMap.get(k);
          if (v == null) {
            if (paramObject != null || !simpleArrayMap.containsKey(k))
              return false; 
          } else {
            boolean bool = v.equals(paramObject);
            if (!bool)
              return false; 
          } 
          b++;
        } 
        return true;
      } catch (NullPointerException|ClassCastException nullPointerException) {
        return false;
      } 
    } 
    if (nullPointerException instanceof Map) {
      Map map = (Map)nullPointerException;
      if (size() != map.size())
        return false; 
      byte b = 0;
      try {
        while (b < this.mSize) {
          K k = keyAt(b);
          nullPointerException = (NullPointerException)valueAt(b);
          Object object = map.get(k);
          if (nullPointerException == null) {
            if (object != null || !map.containsKey(k))
              return false; 
          } else {
            boolean bool = nullPointerException.equals(object);
            if (!bool)
              return false; 
          } 
          b++;
        } 
        return true;
      } catch (NullPointerException|ClassCastException nullPointerException1) {}
    } 
    return false;
  }
  
  public V get(Object paramObject) {
    int i = indexOfKey(paramObject);
    if (i >= 0) {
      paramObject = this.mArray[(i << 1) + 1];
    } else {
      paramObject = null;
    } 
    return (V)paramObject;
  }
  
  public int hashCode() {
    int[] arrayOfInt = this.mHashes;
    Object[] arrayOfObject = this.mArray;
    int i = this.mSize;
    boolean bool = true;
    byte b = 0;
    int j = 0;
    while (b < i) {
      int m;
      Object object = arrayOfObject[bool];
      int k = arrayOfInt[b];
      if (object == null) {
        m = 0;
      } else {
        m = object.hashCode();
      } 
      j += m ^ k;
      b++;
      bool += true;
    } 
    return j;
  }
  
  int indexOf(Object paramObject, int paramInt) {
    int i = this.mSize;
    if (i == 0)
      return -1; 
    int j = binarySearchHashes(this.mHashes, i, paramInt);
    if (j < 0)
      return j; 
    if (paramObject.equals(this.mArray[j << 1]))
      return j; 
    int k;
    for (k = j + 1; k < i && this.mHashes[k] == paramInt; k++) {
      if (paramObject.equals(this.mArray[k << 1]))
        return k; 
    } 
    for (i = j - 1; i >= 0 && this.mHashes[i] == paramInt; i--) {
      if (paramObject.equals(this.mArray[i << 1]))
        return i; 
    } 
    return k ^ 0xFFFFFFFF;
  }
  
  public int indexOfKey(Object paramObject) {
    int i;
    if (paramObject == null) {
      i = indexOfNull();
    } else {
      i = indexOf(paramObject, paramObject.hashCode());
    } 
    return i;
  }
  
  int indexOfNull() {
    int i = this.mSize;
    if (i == 0)
      return -1; 
    int j = binarySearchHashes(this.mHashes, i, 0);
    if (j < 0)
      return j; 
    if (this.mArray[j << 1] == null)
      return j; 
    int k;
    for (k = j + 1; k < i && this.mHashes[k] == 0; k++) {
      if (this.mArray[k << 1] == null)
        return k; 
    } 
    while (--j >= 0 && this.mHashes[j] == 0) {
      if (this.mArray[j << 1] == null)
        return j; 
      j--;
    } 
    return k ^ 0xFFFFFFFF;
  }
  
  int indexOfValue(Object paramObject) {
    int i = this.mSize * 2;
    Object[] arrayOfObject = this.mArray;
    if (paramObject == null) {
      for (byte b = 1; b < i; b += 2) {
        if (arrayOfObject[b] == null)
          return b >> 1; 
      } 
    } else {
      for (byte b = 1; b < i; b += 2) {
        if (paramObject.equals(arrayOfObject[b]))
          return b >> 1; 
      } 
    } 
    return -1;
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.mSize <= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public K keyAt(int paramInt) {
    return (K)this.mArray[paramInt << 1];
  }
  
  public V put(K paramK, V paramV) {
    int k;
    int i = this.mSize;
    if (paramK == null) {
      j = indexOfNull();
      k = 0;
    } else {
      k = paramK.hashCode();
      j = indexOf(paramK, k);
    } 
    if (j >= 0) {
      j = (j << 1) + 1;
      Object[] arrayOfObject = this.mArray;
      paramK = (K)arrayOfObject[j];
      arrayOfObject[j] = paramV;
      return (V)paramK;
    } 
    int m = j ^ 0xFFFFFFFF;
    int[] arrayOfInt = this.mHashes;
    if (i >= arrayOfInt.length) {
      j = 4;
      if (i >= 8) {
        j = (i >> 1) + i;
      } else if (i >= 4) {
        j = 8;
      } 
      Object[] arrayOfObject = this.mArray;
      allocArrays(j);
      if (i == this.mSize) {
        int[] arrayOfInt1 = this.mHashes;
        if (arrayOfInt1.length > 0) {
          System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, arrayOfInt.length);
          System.arraycopy(arrayOfObject, 0, this.mArray, 0, arrayOfObject.length);
        } 
        freeArrays(arrayOfInt, arrayOfObject, i);
      } else {
        throw new ConcurrentModificationException();
      } 
    } 
    if (m < i) {
      arrayOfInt = this.mHashes;
      j = m + 1;
      System.arraycopy(arrayOfInt, m, arrayOfInt, j, i - m);
      Object[] arrayOfObject = this.mArray;
      System.arraycopy(arrayOfObject, m << 1, arrayOfObject, j << 1, this.mSize - m << 1);
    } 
    int j = this.mSize;
    if (i == j) {
      arrayOfInt = this.mHashes;
      if (m < arrayOfInt.length) {
        arrayOfInt[m] = k;
        Object[] arrayOfObject = this.mArray;
        k = m << 1;
        arrayOfObject[k] = paramK;
        arrayOfObject[k + 1] = paramV;
        this.mSize = j + 1;
        return null;
      } 
    } 
    throw new ConcurrentModificationException();
  }
  
  public void putAll(SimpleArrayMap<? extends K, ? extends V> paramSimpleArrayMap) {
    int i = paramSimpleArrayMap.mSize;
    ensureCapacity(this.mSize + i);
    int j = this.mSize;
    byte b = 0;
    if (j == 0) {
      if (i > 0) {
        System.arraycopy(paramSimpleArrayMap.mHashes, 0, this.mHashes, 0, i);
        System.arraycopy(paramSimpleArrayMap.mArray, 0, this.mArray, 0, i << 1);
        this.mSize = i;
      } 
    } else {
      while (b < i) {
        put(paramSimpleArrayMap.keyAt(b), paramSimpleArrayMap.valueAt(b));
        b++;
      } 
    } 
  }
  
  public V remove(Object paramObject) {
    int i = indexOfKey(paramObject);
    return (i >= 0) ? removeAt(i) : null;
  }
  
  public V removeAt(int paramInt) {
    Object[] arrayOfObject = this.mArray;
    int i = paramInt << 1;
    Object object = arrayOfObject[i + 1];
    int j = this.mSize;
    int k = 0;
    if (j <= 1) {
      freeArrays(this.mHashes, arrayOfObject, j);
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      paramInt = k;
    } else {
      int m = j - 1;
      int[] arrayOfInt = this.mHashes;
      int n = arrayOfInt.length;
      k = 8;
      if (n > 8 && j < arrayOfInt.length / 3) {
        if (j > 8)
          k = j + (j >> 1); 
        allocArrays(k);
        if (j == this.mSize) {
          if (paramInt > 0) {
            System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
            System.arraycopy(arrayOfObject, 0, this.mArray, 0, i);
          } 
          if (paramInt < m) {
            n = paramInt + 1;
            int[] arrayOfInt1 = this.mHashes;
            k = m - paramInt;
            System.arraycopy(arrayOfInt, n, arrayOfInt1, paramInt, k);
            System.arraycopy(arrayOfObject, n << 1, this.mArray, i, k << 1);
          } 
        } else {
          throw new ConcurrentModificationException();
        } 
      } else {
        if (paramInt < m) {
          k = paramInt + 1;
          n = m - paramInt;
          System.arraycopy(arrayOfInt, k, arrayOfInt, paramInt, n);
          Object[] arrayOfObject2 = this.mArray;
          System.arraycopy(arrayOfObject2, k << 1, arrayOfObject2, i, n << 1);
        } 
        Object[] arrayOfObject1 = this.mArray;
        paramInt = m << 1;
        arrayOfObject1[paramInt] = null;
        arrayOfObject1[paramInt + 1] = null;
      } 
      paramInt = m;
    } 
    if (j == this.mSize) {
      this.mSize = paramInt;
      return (V)object;
    } 
    throw new ConcurrentModificationException();
  }
  
  public V setValueAt(int paramInt, V paramV) {
    paramInt = (paramInt << 1) + 1;
    Object[] arrayOfObject = this.mArray;
    Object object = arrayOfObject[paramInt];
    arrayOfObject[paramInt] = paramV;
    return (V)object;
  }
  
  public int size() {
    return this.mSize;
  }
  
  public String toString() {
    if (isEmpty())
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
    stringBuilder.append('{');
    for (byte b = 0; b < this.mSize; b++) {
      if (b > 0)
        stringBuilder.append(", "); 
      V v = (V)keyAt(b);
      if (v != this) {
        stringBuilder.append(v);
      } else {
        stringBuilder.append("(this Map)");
      } 
      stringBuilder.append('=');
      v = valueAt(b);
      if (v != this) {
        stringBuilder.append(v);
      } else {
        stringBuilder.append("(this Map)");
      } 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public V valueAt(int paramInt) {
    return (V)this.mArray[(paramInt << 1) + 1];
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/util/SimpleArrayMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */