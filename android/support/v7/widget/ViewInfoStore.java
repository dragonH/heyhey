package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools;

class ViewInfoStore {
  private static final boolean DEBUG = false;
  
  @VisibleForTesting
  final ArrayMap<RecyclerView.ViewHolder, InfoRecord> mLayoutHolderMap = new ArrayMap();
  
  @VisibleForTesting
  final LongSparseArray<RecyclerView.ViewHolder> mOldChangedHolders = new LongSparseArray();
  
  private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
    int i = this.mLayoutHolderMap.indexOfKey(paramViewHolder);
    if (i < 0)
      return null; 
    InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.valueAt(i);
    if (infoRecord != null) {
      int j = infoRecord.flags;
      if ((j & paramInt) != 0) {
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        j = (paramInt ^ 0xFFFFFFFF) & j;
        infoRecord.flags = j;
        if (paramInt == 4) {
          itemHolderInfo = infoRecord.preInfo;
        } else if (paramInt == 8) {
          itemHolderInfo = infoRecord.postInfo;
        } else {
          throw new IllegalArgumentException("Must provide flag PRE or POST");
        } 
        if ((j & 0xC) == 0) {
          this.mLayoutHolderMap.removeAt(i);
          InfoRecord.recycle(infoRecord);
        } 
        return itemHolderInfo;
      } 
    } 
    return null;
  }
  
  void addToAppearedInPreLayoutHolders(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo) {
    InfoRecord infoRecord1 = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    InfoRecord infoRecord2 = infoRecord1;
    if (infoRecord1 == null) {
      infoRecord2 = InfoRecord.obtain();
      this.mLayoutHolderMap.put(paramViewHolder, infoRecord2);
    } 
    infoRecord2.flags |= 0x2;
    infoRecord2.preInfo = paramItemHolderInfo;
  }
  
  void addToDisappearedInLayout(RecyclerView.ViewHolder paramViewHolder) {
    InfoRecord infoRecord1 = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    InfoRecord infoRecord2 = infoRecord1;
    if (infoRecord1 == null) {
      infoRecord2 = InfoRecord.obtain();
      this.mLayoutHolderMap.put(paramViewHolder, infoRecord2);
    } 
    infoRecord2.flags |= 0x1;
  }
  
  void addToOldChangeHolders(long paramLong, RecyclerView.ViewHolder paramViewHolder) {
    this.mOldChangedHolders.put(paramLong, paramViewHolder);
  }
  
  void addToPostLayout(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo) {
    InfoRecord infoRecord1 = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    InfoRecord infoRecord2 = infoRecord1;
    if (infoRecord1 == null) {
      infoRecord2 = InfoRecord.obtain();
      this.mLayoutHolderMap.put(paramViewHolder, infoRecord2);
    } 
    infoRecord2.postInfo = paramItemHolderInfo;
    infoRecord2.flags |= 0x8;
  }
  
  void addToPreLayout(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo) {
    InfoRecord infoRecord1 = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    InfoRecord infoRecord2 = infoRecord1;
    if (infoRecord1 == null) {
      infoRecord2 = InfoRecord.obtain();
      this.mLayoutHolderMap.put(paramViewHolder, infoRecord2);
    } 
    infoRecord2.preInfo = paramItemHolderInfo;
    infoRecord2.flags |= 0x4;
  }
  
  void clear() {
    this.mLayoutHolderMap.clear();
    this.mOldChangedHolders.clear();
  }
  
  RecyclerView.ViewHolder getFromOldChangeHolders(long paramLong) {
    return (RecyclerView.ViewHolder)this.mOldChangedHolders.get(paramLong);
  }
  
  boolean isDisappearing(RecyclerView.ViewHolder paramViewHolder) {
    InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    boolean bool = true;
    if (infoRecord == null || (infoRecord.flags & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  boolean isInPreLayout(RecyclerView.ViewHolder paramViewHolder) {
    boolean bool;
    InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    if (infoRecord != null && (infoRecord.flags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void onDetach() {
    InfoRecord.drainCache();
  }
  
  public void onViewDetached(RecyclerView.ViewHolder paramViewHolder) {
    removeFromDisappearedInLayout(paramViewHolder);
  }
  
  @Nullable
  RecyclerView.ItemAnimator.ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder paramViewHolder) {
    return popFromLayoutStep(paramViewHolder, 8);
  }
  
  @Nullable
  RecyclerView.ItemAnimator.ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder paramViewHolder) {
    return popFromLayoutStep(paramViewHolder, 4);
  }
  
  void process(ProcessCallback paramProcessCallback) {
    for (int i = this.mLayoutHolderMap.size() - 1; i >= 0; i--) {
      RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)this.mLayoutHolderMap.keyAt(i);
      InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.removeAt(i);
      int j = infoRecord.flags;
      if ((j & 0x3) == 3) {
        paramProcessCallback.unused(viewHolder);
      } else if ((j & 0x1) != 0) {
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo = infoRecord.preInfo;
        if (itemHolderInfo == null) {
          paramProcessCallback.unused(viewHolder);
        } else {
          paramProcessCallback.processDisappeared(viewHolder, itemHolderInfo, infoRecord.postInfo);
        } 
      } else if ((j & 0xE) == 14) {
        paramProcessCallback.processAppeared(viewHolder, infoRecord.preInfo, infoRecord.postInfo);
      } else if ((j & 0xC) == 12) {
        paramProcessCallback.processPersistent(viewHolder, infoRecord.preInfo, infoRecord.postInfo);
      } else if ((j & 0x4) != 0) {
        paramProcessCallback.processDisappeared(viewHolder, infoRecord.preInfo, null);
      } else if ((j & 0x8) != 0) {
        paramProcessCallback.processAppeared(viewHolder, infoRecord.preInfo, infoRecord.postInfo);
      } 
      InfoRecord.recycle(infoRecord);
    } 
  }
  
  void removeFromDisappearedInLayout(RecyclerView.ViewHolder paramViewHolder) {
    InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.get(paramViewHolder);
    if (infoRecord == null)
      return; 
    infoRecord.flags &= 0xFFFFFFFE;
  }
  
  void removeViewHolder(RecyclerView.ViewHolder paramViewHolder) {
    for (int i = this.mOldChangedHolders.size() - 1; i >= 0; i--) {
      if (paramViewHolder == this.mOldChangedHolders.valueAt(i)) {
        this.mOldChangedHolders.removeAt(i);
        break;
      } 
    } 
    InfoRecord infoRecord = (InfoRecord)this.mLayoutHolderMap.remove(paramViewHolder);
    if (infoRecord != null)
      InfoRecord.recycle(infoRecord); 
  }
  
  static class InfoRecord {
    static final int FLAG_APPEAR = 2;
    
    static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
    
    static final int FLAG_APPEAR_PRE_AND_POST = 14;
    
    static final int FLAG_DISAPPEARED = 1;
    
    static final int FLAG_POST = 8;
    
    static final int FLAG_PRE = 4;
    
    static final int FLAG_PRE_AND_POST = 12;
    
    static Pools.Pool<InfoRecord> sPool = (Pools.Pool<InfoRecord>)new Pools.SimplePool(20);
    
    int flags;
    
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
    
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo preInfo;
    
    static void drainCache() {
      while (sPool.acquire() != null);
    }
    
    static InfoRecord obtain() {
      InfoRecord infoRecord1 = (InfoRecord)sPool.acquire();
      InfoRecord infoRecord2 = infoRecord1;
      if (infoRecord1 == null)
        infoRecord2 = new InfoRecord(); 
      return infoRecord2;
    }
    
    static void recycle(InfoRecord param1InfoRecord) {
      param1InfoRecord.flags = 0;
      param1InfoRecord.preInfo = null;
      param1InfoRecord.postInfo = null;
      sPool.release(param1InfoRecord);
    }
  }
  
  static interface ProcessCallback {
    void processAppeared(RecyclerView.ViewHolder param1ViewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2);
    
    void processDisappeared(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2);
    
    void processPersistent(RecyclerView.ViewHolder param1ViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo param1ItemHolderInfo2);
    
    void unused(RecyclerView.ViewHolder param1ViewHolder);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/ViewInfoStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */