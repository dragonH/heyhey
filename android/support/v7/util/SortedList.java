package android.support.v7.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList<T> {
  private static final int CAPACITY_GROWTH = 10;
  
  private static final int DELETION = 2;
  
  private static final int INSERTION = 1;
  
  public static final int INVALID_POSITION = -1;
  
  private static final int LOOKUP = 4;
  
  private static final int MIN_CAPACITY = 10;
  
  private BatchedCallback mBatchedCallback;
  
  private Callback mCallback;
  
  T[] mData;
  
  private int mMergedSize;
  
  private T[] mOldData;
  
  private int mOldDataSize;
  
  private int mOldDataStart;
  
  private int mSize;
  
  private final Class<T> mTClass;
  
  public SortedList(Class<T> paramClass, Callback<T> paramCallback) {
    this(paramClass, paramCallback, 10);
  }
  
  public SortedList(Class<T> paramClass, Callback<T> paramCallback, int paramInt) {
    this.mTClass = paramClass;
    this.mData = (T[])Array.newInstance(paramClass, paramInt);
    this.mCallback = paramCallback;
    this.mSize = 0;
  }
  
  private int add(T paramT, boolean paramBoolean) {
    int j;
    int i = findIndexOf(paramT, this.mData, 0, this.mSize, 1);
    if (i == -1) {
      j = 0;
    } else {
      j = i;
      if (i < this.mSize) {
        T t = this.mData[i];
        j = i;
        if (this.mCallback.areItemsTheSame(t, paramT)) {
          if (this.mCallback.areContentsTheSame(t, paramT)) {
            this.mData[i] = paramT;
            return i;
          } 
          this.mData[i] = paramT;
          this.mCallback.onChanged(i, 1);
          return i;
        } 
      } 
    } 
    addToData(j, paramT);
    if (paramBoolean)
      this.mCallback.onInserted(j, 1); 
    return j;
  }
  
  private void addAllInternal(T[] paramArrayOfT) {
    int i = this.mCallback instanceof BatchedCallback ^ true;
    if (i != 0)
      beginBatchedUpdates(); 
    this.mOldData = this.mData;
    this.mOldDataStart = 0;
    this.mOldDataSize = this.mSize;
    Arrays.sort(paramArrayOfT, this.mCallback);
    int j = deduplicate(paramArrayOfT);
    if (this.mSize == 0) {
      this.mData = paramArrayOfT;
      this.mSize = j;
      this.mMergedSize = j;
      this.mCallback.onInserted(0, j);
    } else {
      merge(paramArrayOfT, j);
    } 
    this.mOldData = null;
    if (i != 0)
      endBatchedUpdates(); 
  }
  
  private void addToData(int paramInt, T paramT) {
    int i = this.mSize;
    if (paramInt <= i) {
      T[] arrayOfT = this.mData;
      if (i == arrayOfT.length) {
        arrayOfT = (T[])Array.newInstance(this.mTClass, arrayOfT.length + 10);
        System.arraycopy(this.mData, 0, arrayOfT, 0, paramInt);
        arrayOfT[paramInt] = paramT;
        System.arraycopy(this.mData, paramInt, arrayOfT, paramInt + 1, this.mSize - paramInt);
        this.mData = arrayOfT;
      } else {
        System.arraycopy(arrayOfT, paramInt, arrayOfT, paramInt + 1, i - paramInt);
        this.mData[paramInt] = paramT;
      } 
      this.mSize++;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cannot add item to ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" because size is ");
    stringBuilder.append(this.mSize);
    throw new IndexOutOfBoundsException(stringBuilder.toString());
  }
  
  private int deduplicate(T[] paramArrayOfT) {
    if (paramArrayOfT.length != 0) {
      int i = 0;
      byte b = 1;
      int j = 1;
      while (b < paramArrayOfT.length) {
        T t = paramArrayOfT[b];
        int k = this.mCallback.compare(paramArrayOfT[i], t);
        if (k <= 0) {
          if (k == 0) {
            k = findSameItem(t, paramArrayOfT, i, j);
            if (k != -1) {
              paramArrayOfT[k] = t;
            } else {
              if (j != b)
                paramArrayOfT[j] = t; 
              j++;
            } 
          } else {
            if (j != b)
              paramArrayOfT[j] = t; 
            k = j + 1;
            i = j;
            j = k;
          } 
          b++;
          continue;
        } 
        throw new IllegalArgumentException("Input must be sorted in ascending order.");
      } 
      return j;
    } 
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Input array must be non-empty");
    throw illegalArgumentException;
  }
  
  private int findIndexOf(T paramT, T[] paramArrayOfT, int paramInt1, int paramInt2, int paramInt3) {
    while (paramInt1 < paramInt2) {
      int i = (paramInt1 + paramInt2) / 2;
      T t = paramArrayOfT[i];
      int j = this.mCallback.compare(t, paramT);
      if (j < 0) {
        paramInt1 = i + 1;
        continue;
      } 
      if (j == 0) {
        if (this.mCallback.areItemsTheSame(t, paramT))
          return i; 
        paramInt1 = linearEqualitySearch(paramT, i, paramInt1, paramInt2);
        if (paramInt3 == 1) {
          if (paramInt1 == -1)
            paramInt1 = i; 
          return paramInt1;
        } 
        return paramInt1;
      } 
      paramInt2 = i;
    } 
    if (paramInt3 != 1)
      paramInt1 = -1; 
    return paramInt1;
  }
  
  private int findSameItem(T paramT, T[] paramArrayOfT, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      if (this.mCallback.areItemsTheSame(paramArrayOfT[paramInt1], paramT))
        return paramInt1; 
      paramInt1++;
    } 
    return -1;
  }
  
  private int linearEqualitySearch(T paramT, int paramInt1, int paramInt2, int paramInt3) {
    int j;
    int i = paramInt1 - 1;
    while (true) {
      j = paramInt1;
      if (i >= paramInt2) {
        T t = this.mData[i];
        if (this.mCallback.compare(t, paramT) != 0) {
          j = paramInt1;
          break;
        } 
        if (this.mCallback.areItemsTheSame(t, paramT))
          return i; 
        i--;
        continue;
      } 
      break;
    } 
    while (true) {
      paramInt1 = j + 1;
      if (paramInt1 < paramInt3) {
        T t = this.mData[paramInt1];
        if (this.mCallback.compare(t, paramT) != 0)
          break; 
        j = paramInt1;
        if (this.mCallback.areItemsTheSame(t, paramT))
          return paramInt1; 
        continue;
      } 
      break;
    } 
    return -1;
  }
  
  private void merge(T[] paramArrayOfT, int paramInt) {
    int i = this.mSize;
    this.mData = (T[])Array.newInstance(this.mTClass, i + paramInt + 10);
    i = 0;
    this.mMergedSize = 0;
    while (true) {
      int j = this.mOldDataStart;
      int k = this.mOldDataSize;
      if (j < k || i < paramInt)
        if (j == k) {
          paramInt -= i;
          System.arraycopy(paramArrayOfT, i, this.mData, this.mMergedSize, paramInt);
          i = this.mMergedSize + paramInt;
          this.mMergedSize = i;
          this.mSize += paramInt;
          this.mCallback.onInserted(i - paramInt, paramInt);
        } else {
          T[] arrayOfT1;
          if (i == paramInt) {
            paramInt = k - j;
            System.arraycopy(this.mOldData, j, this.mData, this.mMergedSize, paramInt);
            this.mMergedSize += paramInt;
            return;
          } 
          T t1 = this.mOldData[j];
          T t2 = paramArrayOfT[i];
          j = this.mCallback.compare(t1, t2);
          if (j > 0) {
            arrayOfT1 = this.mData;
            k = this.mMergedSize;
            j = k + 1;
            this.mMergedSize = j;
            arrayOfT1[k] = t2;
            this.mSize++;
            i++;
            this.mCallback.onInserted(j - 1, 1);
            continue;
          } 
          if (j == 0 && this.mCallback.areItemsTheSame(arrayOfT1, (T[])t2)) {
            T[] arrayOfT = this.mData;
            j = this.mMergedSize;
            this.mMergedSize = j + 1;
            arrayOfT[j] = t2;
            j = i + 1;
            this.mOldDataStart++;
            i = j;
            if (!this.mCallback.areContentsTheSame(arrayOfT1, (T[])t2)) {
              this.mCallback.onChanged(this.mMergedSize - 1, 1);
              i = j;
            } 
            continue;
          } 
          T[] arrayOfT2 = this.mData;
          j = this.mMergedSize;
          this.mMergedSize = j + 1;
          arrayOfT2[j] = (T)arrayOfT1;
          this.mOldDataStart++;
          continue;
        }  
      return;
    } 
  }
  
  private boolean remove(T paramT, boolean paramBoolean) {
    int i = findIndexOf(paramT, this.mData, 0, this.mSize, 2);
    if (i == -1)
      return false; 
    removeItemAtIndex(i, paramBoolean);
    return true;
  }
  
  private void removeItemAtIndex(int paramInt, boolean paramBoolean) {
    T[] arrayOfT = this.mData;
    System.arraycopy(arrayOfT, paramInt + 1, arrayOfT, paramInt, this.mSize - paramInt - 1);
    int i = this.mSize - 1;
    this.mSize = i;
    this.mData[i] = null;
    if (paramBoolean)
      this.mCallback.onRemoved(paramInt, 1); 
  }
  
  private void throwIfMerging() {
    if (this.mOldData == null)
      return; 
    throw new IllegalStateException("Cannot call this method from within addAll");
  }
  
  public int add(T paramT) {
    throwIfMerging();
    return add(paramT, true);
  }
  
  public void addAll(Collection<T> paramCollection) {
    addAll(paramCollection.toArray((T[])Array.newInstance(this.mTClass, paramCollection.size())), true);
  }
  
  public void addAll(T... paramVarArgs) {
    addAll(paramVarArgs, false);
  }
  
  public void addAll(T[] paramArrayOfT, boolean paramBoolean) {
    throwIfMerging();
    if (paramArrayOfT.length == 0)
      return; 
    if (paramBoolean) {
      addAllInternal(paramArrayOfT);
    } else {
      Object[] arrayOfObject = (Object[])Array.newInstance(this.mTClass, paramArrayOfT.length);
      System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, paramArrayOfT.length);
      addAllInternal((T[])arrayOfObject);
    } 
  }
  
  public void beginBatchedUpdates() {
    throwIfMerging();
    Callback<?> callback = this.mCallback;
    if (callback instanceof BatchedCallback)
      return; 
    if (this.mBatchedCallback == null)
      this.mBatchedCallback = new BatchedCallback(callback); 
    this.mCallback = this.mBatchedCallback;
  }
  
  public void clear() {
    throwIfMerging();
    int i = this.mSize;
    if (i == 0)
      return; 
    Arrays.fill((Object[])this.mData, 0, i, (Object)null);
    this.mSize = 0;
    this.mCallback.onRemoved(0, i);
  }
  
  public void endBatchedUpdates() {
    throwIfMerging();
    Callback callback = this.mCallback;
    if (callback instanceof BatchedCallback)
      ((BatchedCallback)callback).dispatchLastEvent(); 
    callback = this.mCallback;
    BatchedCallback batchedCallback = this.mBatchedCallback;
    if (callback == batchedCallback)
      this.mCallback = batchedCallback.mWrappedCallback; 
  }
  
  public T get(int paramInt) throws IndexOutOfBoundsException {
    if (paramInt < this.mSize && paramInt >= 0) {
      T[] arrayOfT = this.mOldData;
      if (arrayOfT != null) {
        int i = this.mMergedSize;
        if (paramInt >= i)
          return arrayOfT[paramInt - i + this.mOldDataStart]; 
      } 
      return this.mData[paramInt];
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Asked to get item at ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" but size is ");
    stringBuilder.append(this.mSize);
    throw new IndexOutOfBoundsException(stringBuilder.toString());
  }
  
  public int indexOf(T paramT) {
    if (this.mOldData != null) {
      int i = findIndexOf(paramT, this.mData, 0, this.mMergedSize, 4);
      if (i != -1)
        return i; 
      i = findIndexOf(paramT, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
      return (i != -1) ? (i - this.mOldDataStart + this.mMergedSize) : -1;
    } 
    return findIndexOf(paramT, this.mData, 0, this.mSize, 4);
  }
  
  public void recalculatePositionOfItemAt(int paramInt) {
    throwIfMerging();
    T t = get(paramInt);
    removeItemAtIndex(paramInt, false);
    int i = add(t, false);
    if (paramInt != i)
      this.mCallback.onMoved(paramInt, i); 
  }
  
  public boolean remove(T paramT) {
    throwIfMerging();
    return remove(paramT, true);
  }
  
  public T removeItemAt(int paramInt) {
    throwIfMerging();
    T t = get(paramInt);
    removeItemAtIndex(paramInt, true);
    return t;
  }
  
  public int size() {
    return this.mSize;
  }
  
  public void updateItemAt(int paramInt, T paramT) {
    throwIfMerging();
    T t = get(paramInt);
    if (t == paramT || !this.mCallback.areContentsTheSame(t, paramT)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (t != paramT && this.mCallback.compare(t, paramT) == 0) {
      this.mData[paramInt] = paramT;
      if (i)
        this.mCallback.onChanged(paramInt, 1); 
      return;
    } 
    if (i)
      this.mCallback.onChanged(paramInt, 1); 
    removeItemAtIndex(paramInt, false);
    int i = add(paramT, false);
    if (paramInt != i)
      this.mCallback.onMoved(paramInt, i); 
  }
  
  public static class BatchedCallback<T2> extends Callback<T2> {
    private final BatchingListUpdateCallback mBatchingListUpdateCallback;
    
    final SortedList.Callback<T2> mWrappedCallback;
    
    public BatchedCallback(SortedList.Callback<T2> param1Callback) {
      this.mWrappedCallback = param1Callback;
      this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(param1Callback);
    }
    
    public boolean areContentsTheSame(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.areContentsTheSame(param1T21, param1T22);
    }
    
    public boolean areItemsTheSame(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.areItemsTheSame(param1T21, param1T22);
    }
    
    public int compare(T2 param1T21, T2 param1T22) {
      return this.mWrappedCallback.compare(param1T21, param1T22);
    }
    
    public void dispatchLastEvent() {
      this.mBatchingListUpdateCallback.dispatchLastEvent();
    }
    
    public void onChanged(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onChanged(param1Int1, param1Int2, null);
    }
    
    public void onInserted(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onInserted(param1Int1, param1Int2);
    }
    
    public void onMoved(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onMoved(param1Int1, param1Int2);
    }
    
    public void onRemoved(int param1Int1, int param1Int2) {
      this.mBatchingListUpdateCallback.onRemoved(param1Int1, param1Int2);
    }
  }
  
  public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
    public abstract boolean areContentsTheSame(T2 param1T21, T2 param1T22);
    
    public abstract boolean areItemsTheSame(T2 param1T21, T2 param1T22);
    
    public abstract int compare(T2 param1T21, T2 param1T22);
    
    public abstract void onChanged(int param1Int1, int param1Int2);
    
    public void onChanged(int param1Int1, int param1Int2, Object param1Object) {
      onChanged(param1Int1, param1Int2);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/util/SortedList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */