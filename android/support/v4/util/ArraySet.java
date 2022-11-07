package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet<E> implements Collection<E>, Set<E> {
  private static final int BASE_SIZE = 4;
  
  private static final int CACHE_SIZE = 10;
  
  private static final boolean DEBUG = false;
  
  private static final int[] INT = new int[0];
  
  private static final Object[] OBJECT = new Object[0];
  
  private static final String TAG = "ArraySet";
  
  static Object[] sBaseCache;
  
  static int sBaseCacheSize;
  
  static Object[] sTwiceBaseCache;
  
  static int sTwiceBaseCacheSize;
  
  Object[] mArray;
  
  MapCollections<E, E> mCollections;
  
  int[] mHashes;
  
  final boolean mIdentityHashCode;
  
  int mSize;
  
  public ArraySet() {
    this(0, false);
  }
  
  public ArraySet(int paramInt) {
    this(paramInt, false);
  }
  
  public ArraySet(int paramInt, boolean paramBoolean) {
    this.mIdentityHashCode = paramBoolean;
    if (paramInt == 0) {
      this.mHashes = INT;
      this.mArray = OBJECT;
    } else {
      allocArrays(paramInt);
    } 
    this.mSize = 0;
  }
  
  public ArraySet(ArraySet<E> paramArraySet) {
    this();
    if (paramArraySet != null)
      addAll(paramArraySet); 
  }
  
  public ArraySet(Collection<E> paramCollection) {
    this();
    if (paramCollection != null)
      addAll(paramCollection); 
  }
  
  private void allocArrays(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: bipush #8
    //   3: if_icmpne -> 73
    //   6: ldc android/support/v4/util/ArraySet
    //   8: monitorenter
    //   9: getstatic android/support/v4/util/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
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
    //   28: putstatic android/support/v4/util/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
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
    //   49: getstatic android/support/v4/util/ArraySet.sTwiceBaseCacheSize : I
    //   52: iconst_1
    //   53: isub
    //   54: putstatic android/support/v4/util/ArraySet.sTwiceBaseCacheSize : I
    //   57: ldc android/support/v4/util/ArraySet
    //   59: monitorexit
    //   60: return
    //   61: ldc android/support/v4/util/ArraySet
    //   63: monitorexit
    //   64: goto -> 145
    //   67: astore_2
    //   68: ldc android/support/v4/util/ArraySet
    //   70: monitorexit
    //   71: aload_2
    //   72: athrow
    //   73: iload_1
    //   74: iconst_4
    //   75: if_icmpne -> 145
    //   78: ldc android/support/v4/util/ArraySet
    //   80: monitorenter
    //   81: getstatic android/support/v4/util/ArraySet.sBaseCache : [Ljava/lang/Object;
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
    //   100: putstatic android/support/v4/util/ArraySet.sBaseCache : [Ljava/lang/Object;
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
    //   121: getstatic android/support/v4/util/ArraySet.sBaseCacheSize : I
    //   124: iconst_1
    //   125: isub
    //   126: putstatic android/support/v4/util/ArraySet.sBaseCacheSize : I
    //   129: ldc android/support/v4/util/ArraySet
    //   131: monitorexit
    //   132: return
    //   133: ldc android/support/v4/util/ArraySet
    //   135: monitorexit
    //   136: goto -> 145
    //   139: astore_2
    //   140: ldc android/support/v4/util/ArraySet
    //   142: monitorexit
    //   143: aload_2
    //   144: athrow
    //   145: aload_0
    //   146: iload_1
    //   147: newarray int
    //   149: putfield mHashes : [I
    //   152: aload_0
    //   153: iload_1
    //   154: anewarray java/lang/Object
    //   157: putfield mArray : [Ljava/lang/Object;
    //   160: return
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
  
  private static void freeArrays(int[] paramArrayOfint, Object[] paramArrayOfObject, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: bipush #8
    //   4: if_icmpne -> 70
    //   7: ldc android/support/v4/util/ArraySet
    //   9: monitorenter
    //   10: getstatic android/support/v4/util/ArraySet.sTwiceBaseCacheSize : I
    //   13: bipush #10
    //   15: if_icmpge -> 58
    //   18: aload_1
    //   19: iconst_0
    //   20: getstatic android/support/v4/util/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   23: aastore
    //   24: aload_1
    //   25: iconst_1
    //   26: aload_0
    //   27: aastore
    //   28: iinc #2, -1
    //   31: iload_2
    //   32: iconst_2
    //   33: if_icmplt -> 46
    //   36: aload_1
    //   37: iload_2
    //   38: aconst_null
    //   39: aastore
    //   40: iinc #2, -1
    //   43: goto -> 31
    //   46: aload_1
    //   47: putstatic android/support/v4/util/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   50: getstatic android/support/v4/util/ArraySet.sTwiceBaseCacheSize : I
    //   53: iconst_1
    //   54: iadd
    //   55: putstatic android/support/v4/util/ArraySet.sTwiceBaseCacheSize : I
    //   58: ldc android/support/v4/util/ArraySet
    //   60: monitorexit
    //   61: goto -> 139
    //   64: astore_0
    //   65: ldc android/support/v4/util/ArraySet
    //   67: monitorexit
    //   68: aload_0
    //   69: athrow
    //   70: aload_0
    //   71: arraylength
    //   72: iconst_4
    //   73: if_icmpne -> 139
    //   76: ldc android/support/v4/util/ArraySet
    //   78: monitorenter
    //   79: getstatic android/support/v4/util/ArraySet.sBaseCacheSize : I
    //   82: bipush #10
    //   84: if_icmpge -> 127
    //   87: aload_1
    //   88: iconst_0
    //   89: getstatic android/support/v4/util/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   92: aastore
    //   93: aload_1
    //   94: iconst_1
    //   95: aload_0
    //   96: aastore
    //   97: iinc #2, -1
    //   100: iload_2
    //   101: iconst_2
    //   102: if_icmplt -> 115
    //   105: aload_1
    //   106: iload_2
    //   107: aconst_null
    //   108: aastore
    //   109: iinc #2, -1
    //   112: goto -> 100
    //   115: aload_1
    //   116: putstatic android/support/v4/util/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   119: getstatic android/support/v4/util/ArraySet.sBaseCacheSize : I
    //   122: iconst_1
    //   123: iadd
    //   124: putstatic android/support/v4/util/ArraySet.sBaseCacheSize : I
    //   127: ldc android/support/v4/util/ArraySet
    //   129: monitorexit
    //   130: goto -> 139
    //   133: astore_0
    //   134: ldc android/support/v4/util/ArraySet
    //   136: monitorexit
    //   137: aload_0
    //   138: athrow
    //   139: return
    // Exception table:
    //   from	to	target	type
    //   10	24	64	finally
    //   46	58	64	finally
    //   58	61	64	finally
    //   65	68	64	finally
    //   79	93	133	finally
    //   115	127	133	finally
    //   127	130	133	finally
    //   134	137	133	finally
  }
  
  private MapCollections<E, E> getCollection() {
    if (this.mCollections == null)
      this.mCollections = new MapCollections<E, E>() {
          protected void colClear() {
            ArraySet.this.clear();
          }
          
          protected Object colGetEntry(int param1Int1, int param1Int2) {
            return ArraySet.this.mArray[param1Int1];
          }
          
          protected Map<E, E> colGetMap() {
            throw new UnsupportedOperationException("not a map");
          }
          
          protected int colGetSize() {
            return ArraySet.this.mSize;
          }
          
          protected int colIndexOfKey(Object param1Object) {
            return ArraySet.this.indexOf(param1Object);
          }
          
          protected int colIndexOfValue(Object param1Object) {
            return ArraySet.this.indexOf(param1Object);
          }
          
          protected void colPut(E param1E1, E param1E2) {
            ArraySet.this.add(param1E1);
          }
          
          protected void colRemoveAt(int param1Int) {
            ArraySet.this.removeAt(param1Int);
          }
          
          protected E colSetValue(int param1Int, E param1E) {
            throw new UnsupportedOperationException("not a map");
          }
        }; 
    return this.mCollections;
  }
  
  private int indexOf(Object paramObject, int paramInt) {
    int i = this.mSize;
    if (i == 0)
      return -1; 
    int j = ContainerHelpers.binarySearch(this.mHashes, i, paramInt);
    if (j < 0)
      return j; 
    if (paramObject.equals(this.mArray[j]))
      return j; 
    int k;
    for (k = j + 1; k < i && this.mHashes[k] == paramInt; k++) {
      if (paramObject.equals(this.mArray[k]))
        return k; 
    } 
    while (--j >= 0 && this.mHashes[j] == paramInt) {
      if (paramObject.equals(this.mArray[j]))
        return j; 
      j--;
    } 
    return k ^ 0xFFFFFFFF;
  }
  
  private int indexOfNull() {
    int i = this.mSize;
    if (i == 0)
      return -1; 
    int j = ContainerHelpers.binarySearch(this.mHashes, i, 0);
    if (j < 0)
      return j; 
    if (this.mArray[j] == null)
      return j; 
    int k;
    for (k = j + 1; k < i && this.mHashes[k] == 0; k++) {
      if (this.mArray[k] == null)
        return k; 
    } 
    while (--j >= 0 && this.mHashes[j] == 0) {
      if (this.mArray[j] == null)
        return j; 
      j--;
    } 
    return k ^ 0xFFFFFFFF;
  }
  
  public boolean add(E paramE) {
    int i;
    int j;
    if (paramE == null) {
      i = indexOfNull();
      j = 0;
    } else {
      int m;
      if (this.mIdentityHashCode) {
        m = System.identityHashCode(paramE);
      } else {
        m = paramE.hashCode();
      } 
      i = indexOf(paramE, m);
      j = m;
    } 
    if (i >= 0)
      return false; 
    i ^= 0xFFFFFFFF;
    int k = this.mSize;
    int[] arrayOfInt = this.mHashes;
    if (k >= arrayOfInt.length) {
      int m = 4;
      if (k >= 8) {
        m = (k >> 1) + k;
      } else if (k >= 4) {
        m = 8;
      } 
      Object[] arrayOfObject = this.mArray;
      allocArrays(m);
      int[] arrayOfInt1 = this.mHashes;
      if (arrayOfInt1.length > 0) {
        System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, arrayOfInt.length);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, arrayOfObject.length);
      } 
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
    } 
    k = this.mSize;
    if (i < k) {
      arrayOfInt = this.mHashes;
      int m = i + 1;
      System.arraycopy(arrayOfInt, i, arrayOfInt, m, k - i);
      Object[] arrayOfObject = this.mArray;
      System.arraycopy(arrayOfObject, i, arrayOfObject, m, this.mSize - i);
    } 
    this.mHashes[i] = j;
    this.mArray[i] = paramE;
    this.mSize++;
    return true;
  }
  
  public void addAll(ArraySet<? extends E> paramArraySet) {
    int i = paramArraySet.mSize;
    ensureCapacity(this.mSize + i);
    int j = this.mSize;
    byte b = 0;
    if (j == 0) {
      if (i > 0) {
        System.arraycopy(paramArraySet.mHashes, 0, this.mHashes, 0, i);
        System.arraycopy(paramArraySet.mArray, 0, this.mArray, 0, i);
        this.mSize = i;
      } 
    } else {
      while (b < i) {
        add(paramArraySet.valueAt(b));
        b++;
      } 
    } 
  }
  
  public boolean addAll(Collection<? extends E> paramCollection) {
    ensureCapacity(this.mSize + paramCollection.size());
    Iterator<? extends E> iterator = paramCollection.iterator();
    boolean bool;
    for (bool = false; iterator.hasNext(); bool |= add(iterator.next()));
    return bool;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void append(E paramE) {
    int j;
    int i = this.mSize;
    if (paramE == null) {
      j = 0;
    } else if (this.mIdentityHashCode) {
      j = System.identityHashCode(paramE);
    } else {
      j = paramE.hashCode();
    } 
    int[] arrayOfInt = this.mHashes;
    if (i < arrayOfInt.length) {
      if (i > 0 && arrayOfInt[i - 1] > j) {
        add(paramE);
        return;
      } 
      this.mSize = i + 1;
      arrayOfInt[i] = j;
      this.mArray[i] = paramE;
      return;
    } 
    throw new IllegalStateException("Array is full");
  }
  
  public void clear() {
    int i = this.mSize;
    if (i != 0) {
      freeArrays(this.mHashes, this.mArray, i);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    } 
  }
  
  public boolean contains(Object paramObject) {
    boolean bool;
    if (indexOf(paramObject) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean containsAll(Collection<?> paramCollection) {
    Iterator<?> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      if (!contains(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public void ensureCapacity(int paramInt) {
    int[] arrayOfInt = this.mHashes;
    if (arrayOfInt.length < paramInt) {
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      paramInt = this.mSize;
      if (paramInt > 0) {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, this.mSize);
      } 
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject instanceof Set) {
      paramObject = paramObject;
      if (size() != paramObject.size())
        return false; 
      byte b = 0;
      try {
        while (b < this.mSize) {
          boolean bool = paramObject.contains(valueAt(b));
          if (!bool)
            return false; 
          b++;
        } 
        return true;
      } catch (NullPointerException|ClassCastException nullPointerException) {}
    } 
    return false;
  }
  
  public int hashCode() {
    int[] arrayOfInt = this.mHashes;
    int i = this.mSize;
    byte b = 0;
    int j = 0;
    while (b < i) {
      j += arrayOfInt[b];
      b++;
    } 
    return j;
  }
  
  public int indexOf(Object paramObject) {
    int i;
    if (paramObject == null) {
      i = indexOfNull();
    } else {
      if (this.mIdentityHashCode) {
        i = System.identityHashCode(paramObject);
      } else {
        i = paramObject.hashCode();
      } 
      i = indexOf(paramObject, i);
    } 
    return i;
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
  
  public Iterator<E> iterator() {
    return getCollection().getKeySet().iterator();
  }
  
  public boolean remove(Object paramObject) {
    int i = indexOf(paramObject);
    if (i >= 0) {
      removeAt(i);
      return true;
    } 
    return false;
  }
  
  public boolean removeAll(ArraySet<? extends E> paramArraySet) {
    int i = paramArraySet.mSize;
    int j = this.mSize;
    boolean bool = false;
    for (byte b = 0; b < i; b++)
      remove(paramArraySet.valueAt(b)); 
    if (j != this.mSize)
      bool = true; 
    return bool;
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    Iterator<?> iterator = paramCollection.iterator();
    boolean bool;
    for (bool = false; iterator.hasNext(); bool |= remove(iterator.next()));
    return bool;
  }
  
  public E removeAt(int paramInt) {
    Object[] arrayOfObject = this.mArray;
    Object object = arrayOfObject[paramInt];
    int i = this.mSize;
    if (i <= 1) {
      freeArrays(this.mHashes, arrayOfObject, i);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    } else {
      int[] arrayOfInt = this.mHashes;
      int j = arrayOfInt.length;
      int k = 8;
      if (j > 8 && i < arrayOfInt.length / 3) {
        if (i > 8)
          k = i + (i >> 1); 
        allocArrays(k);
        this.mSize--;
        if (paramInt > 0) {
          System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
          System.arraycopy(arrayOfObject, 0, this.mArray, 0, paramInt);
        } 
        i = this.mSize;
        if (paramInt < i) {
          k = paramInt + 1;
          System.arraycopy(arrayOfInt, k, this.mHashes, paramInt, i - paramInt);
          System.arraycopy(arrayOfObject, k, this.mArray, paramInt, this.mSize - paramInt);
        } 
      } else {
        k = i - 1;
        this.mSize = k;
        if (paramInt < k) {
          i = paramInt + 1;
          System.arraycopy(arrayOfInt, i, arrayOfInt, paramInt, k - paramInt);
          Object[] arrayOfObject1 = this.mArray;
          System.arraycopy(arrayOfObject1, i, arrayOfObject1, paramInt, this.mSize - paramInt);
        } 
        this.mArray[this.mSize] = null;
      } 
    } 
    return (E)object;
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    int i = this.mSize - 1;
    boolean bool = false;
    while (i >= 0) {
      if (!paramCollection.contains(this.mArray[i])) {
        removeAt(i);
        bool = true;
      } 
      i--;
    } 
    return bool;
  }
  
  public int size() {
    return this.mSize;
  }
  
  public Object[] toArray() {
    int i = this.mSize;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(this.mArray, 0, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public <T> T[] toArray(T[] paramArrayOfT) {
    T[] arrayOfT = paramArrayOfT;
    if (paramArrayOfT.length < this.mSize)
      arrayOfT = (T[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), this.mSize); 
    System.arraycopy(this.mArray, 0, arrayOfT, 0, this.mSize);
    int i = arrayOfT.length;
    int j = this.mSize;
    if (i > j)
      arrayOfT[j] = null; 
    return arrayOfT;
  }
  
  public String toString() {
    if (isEmpty())
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder(this.mSize * 14);
    stringBuilder.append('{');
    for (byte b = 0; b < this.mSize; b++) {
      if (b > 0)
        stringBuilder.append(", "); 
      E e = valueAt(b);
      if (e != this) {
        stringBuilder.append(e);
      } else {
        stringBuilder.append("(this Set)");
      } 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public E valueAt(int paramInt) {
    return (E)this.mArray[paramInt];
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/util/ArraySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */