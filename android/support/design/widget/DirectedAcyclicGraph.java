package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class DirectedAcyclicGraph<T> {
  private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap();
  
  private final Pools.Pool<ArrayList<T>> mListPool = (Pools.Pool<ArrayList<T>>)new Pools.SimplePool(10);
  
  private final ArrayList<T> mSortResult = new ArrayList<T>();
  
  private final HashSet<T> mSortTmpMarked = new HashSet<T>();
  
  private void dfs(T paramT, ArrayList<T> paramArrayList, HashSet<T> paramHashSet) {
    if (paramArrayList.contains(paramT))
      return; 
    if (!paramHashSet.contains(paramT)) {
      paramHashSet.add(paramT);
      ArrayList<T> arrayList = (ArrayList)this.mGraph.get(paramT);
      if (arrayList != null) {
        byte b = 0;
        int i = arrayList.size();
        while (b < i) {
          dfs(arrayList.get(b), paramArrayList, paramHashSet);
          b++;
        } 
      } 
      paramHashSet.remove(paramT);
      paramArrayList.add(paramT);
      return;
    } 
    RuntimeException runtimeException = new RuntimeException("This graph contains cyclic dependencies");
    throw runtimeException;
  }
  
  @NonNull
  private ArrayList<T> getEmptyList() {
    ArrayList<T> arrayList1 = (ArrayList)this.mListPool.acquire();
    ArrayList<T> arrayList2 = arrayList1;
    if (arrayList1 == null)
      arrayList2 = new ArrayList(); 
    return arrayList2;
  }
  
  private void poolList(@NonNull ArrayList<T> paramArrayList) {
    paramArrayList.clear();
    this.mListPool.release(paramArrayList);
  }
  
  void addEdge(@NonNull T paramT1, @NonNull T paramT2) {
    if (this.mGraph.containsKey(paramT1) && this.mGraph.containsKey(paramT2)) {
      ArrayList<T> arrayList1 = (ArrayList)this.mGraph.get(paramT1);
      ArrayList<T> arrayList2 = arrayList1;
      if (arrayList1 == null) {
        arrayList2 = getEmptyList();
        this.mGraph.put(paramT1, arrayList2);
      } 
      arrayList2.add(paramT2);
      return;
    } 
    throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
  }
  
  void addNode(@NonNull T paramT) {
    if (!this.mGraph.containsKey(paramT))
      this.mGraph.put(paramT, null); 
  }
  
  void clear() {
    int i = this.mGraph.size();
    for (byte b = 0; b < i; b++) {
      ArrayList<T> arrayList = (ArrayList)this.mGraph.valueAt(b);
      if (arrayList != null)
        poolList(arrayList); 
    } 
    this.mGraph.clear();
  }
  
  boolean contains(@NonNull T paramT) {
    return this.mGraph.containsKey(paramT);
  }
  
  @Nullable
  List getIncomingEdges(@NonNull T paramT) {
    return (List)this.mGraph.get(paramT);
  }
  
  @Nullable
  List<T> getOutgoingEdges(@NonNull T paramT) {
    int i = this.mGraph.size();
    ArrayList<Object> arrayList = null;
    byte b = 0;
    while (b < i) {
      ArrayList arrayList1 = (ArrayList)this.mGraph.valueAt(b);
      ArrayList<Object> arrayList2 = arrayList;
      if (arrayList1 != null) {
        arrayList2 = arrayList;
        if (arrayList1.contains(paramT)) {
          arrayList2 = arrayList;
          if (arrayList == null)
            arrayList2 = new ArrayList(); 
          arrayList2.add(this.mGraph.keyAt(b));
        } 
      } 
      b++;
      arrayList = arrayList2;
    } 
    return arrayList;
  }
  
  @NonNull
  ArrayList<T> getSortedList() {
    this.mSortResult.clear();
    this.mSortTmpMarked.clear();
    int i = this.mGraph.size();
    for (byte b = 0; b < i; b++)
      dfs((T)this.mGraph.keyAt(b), this.mSortResult, this.mSortTmpMarked); 
    return this.mSortResult;
  }
  
  boolean hasOutgoingEdges(@NonNull T paramT) {
    int i = this.mGraph.size();
    for (byte b = 0; b < i; b++) {
      ArrayList arrayList = (ArrayList)this.mGraph.valueAt(b);
      if (arrayList != null && arrayList.contains(paramT))
        return true; 
    } 
    return false;
  }
  
  int size() {
    return this.mGraph.size();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/DirectedAcyclicGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */