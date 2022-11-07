package android.support.v4.view.accessibility;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class AccessibilityRecordCompat {
  private static final AccessibilityRecordCompatBaseImpl IMPL = new AccessibilityRecordCompatApi16Impl();
  
  private final AccessibilityRecord mRecord;
  
  @Deprecated
  public AccessibilityRecordCompat(Object paramObject) {
    this.mRecord = (AccessibilityRecord)paramObject;
  }
  
  public static int getMaxScrollX(AccessibilityRecord paramAccessibilityRecord) {
    return IMPL.getMaxScrollX(paramAccessibilityRecord);
  }
  
  public static int getMaxScrollY(AccessibilityRecord paramAccessibilityRecord) {
    return IMPL.getMaxScrollY(paramAccessibilityRecord);
  }
  
  @Deprecated
  public static AccessibilityRecordCompat obtain() {
    return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
  }
  
  @Deprecated
  public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat paramAccessibilityRecordCompat) {
    return new AccessibilityRecordCompat(AccessibilityRecord.obtain(paramAccessibilityRecordCompat.mRecord));
  }
  
  public static void setMaxScrollX(AccessibilityRecord paramAccessibilityRecord, int paramInt) {
    IMPL.setMaxScrollX(paramAccessibilityRecord, paramInt);
  }
  
  public static void setMaxScrollY(AccessibilityRecord paramAccessibilityRecord, int paramInt) {
    IMPL.setMaxScrollY(paramAccessibilityRecord, paramInt);
  }
  
  public static void setSource(@NonNull AccessibilityRecord paramAccessibilityRecord, View paramView, int paramInt) {
    IMPL.setSource(paramAccessibilityRecord, paramView, paramInt);
  }
  
  @Deprecated
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    AccessibilityRecord accessibilityRecord = this.mRecord;
    if (accessibilityRecord == null) {
      if (((AccessibilityRecordCompat)paramObject).mRecord != null)
        return false; 
    } else if (!accessibilityRecord.equals(((AccessibilityRecordCompat)paramObject).mRecord)) {
      return false;
    } 
    return true;
  }
  
  @Deprecated
  public int getAddedCount() {
    return this.mRecord.getAddedCount();
  }
  
  @Deprecated
  public CharSequence getBeforeText() {
    return this.mRecord.getBeforeText();
  }
  
  @Deprecated
  public CharSequence getClassName() {
    return this.mRecord.getClassName();
  }
  
  @Deprecated
  public CharSequence getContentDescription() {
    return this.mRecord.getContentDescription();
  }
  
  @Deprecated
  public int getCurrentItemIndex() {
    return this.mRecord.getCurrentItemIndex();
  }
  
  @Deprecated
  public int getFromIndex() {
    return this.mRecord.getFromIndex();
  }
  
  @Deprecated
  public Object getImpl() {
    return this.mRecord;
  }
  
  @Deprecated
  public int getItemCount() {
    return this.mRecord.getItemCount();
  }
  
  @Deprecated
  public int getMaxScrollX() {
    return getMaxScrollX(this.mRecord);
  }
  
  @Deprecated
  public int getMaxScrollY() {
    return getMaxScrollY(this.mRecord);
  }
  
  @Deprecated
  public Parcelable getParcelableData() {
    return this.mRecord.getParcelableData();
  }
  
  @Deprecated
  public int getRemovedCount() {
    return this.mRecord.getRemovedCount();
  }
  
  @Deprecated
  public int getScrollX() {
    return this.mRecord.getScrollX();
  }
  
  @Deprecated
  public int getScrollY() {
    return this.mRecord.getScrollY();
  }
  
  @Deprecated
  public AccessibilityNodeInfoCompat getSource() {
    return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
  }
  
  @Deprecated
  public List<CharSequence> getText() {
    return this.mRecord.getText();
  }
  
  @Deprecated
  public int getToIndex() {
    return this.mRecord.getToIndex();
  }
  
  @Deprecated
  public int getWindowId() {
    return this.mRecord.getWindowId();
  }
  
  @Deprecated
  public int hashCode() {
    int i;
    AccessibilityRecord accessibilityRecord = this.mRecord;
    if (accessibilityRecord == null) {
      i = 0;
    } else {
      i = accessibilityRecord.hashCode();
    } 
    return i;
  }
  
  @Deprecated
  public boolean isChecked() {
    return this.mRecord.isChecked();
  }
  
  @Deprecated
  public boolean isEnabled() {
    return this.mRecord.isEnabled();
  }
  
  @Deprecated
  public boolean isFullScreen() {
    return this.mRecord.isFullScreen();
  }
  
  @Deprecated
  public boolean isPassword() {
    return this.mRecord.isPassword();
  }
  
  @Deprecated
  public boolean isScrollable() {
    return this.mRecord.isScrollable();
  }
  
  @Deprecated
  public void recycle() {
    this.mRecord.recycle();
  }
  
  @Deprecated
  public void setAddedCount(int paramInt) {
    this.mRecord.setAddedCount(paramInt);
  }
  
  @Deprecated
  public void setBeforeText(CharSequence paramCharSequence) {
    this.mRecord.setBeforeText(paramCharSequence);
  }
  
  @Deprecated
  public void setChecked(boolean paramBoolean) {
    this.mRecord.setChecked(paramBoolean);
  }
  
  @Deprecated
  public void setClassName(CharSequence paramCharSequence) {
    this.mRecord.setClassName(paramCharSequence);
  }
  
  @Deprecated
  public void setContentDescription(CharSequence paramCharSequence) {
    this.mRecord.setContentDescription(paramCharSequence);
  }
  
  @Deprecated
  public void setCurrentItemIndex(int paramInt) {
    this.mRecord.setCurrentItemIndex(paramInt);
  }
  
  @Deprecated
  public void setEnabled(boolean paramBoolean) {
    this.mRecord.setEnabled(paramBoolean);
  }
  
  @Deprecated
  public void setFromIndex(int paramInt) {
    this.mRecord.setFromIndex(paramInt);
  }
  
  @Deprecated
  public void setFullScreen(boolean paramBoolean) {
    this.mRecord.setFullScreen(paramBoolean);
  }
  
  @Deprecated
  public void setItemCount(int paramInt) {
    this.mRecord.setItemCount(paramInt);
  }
  
  @Deprecated
  public void setMaxScrollX(int paramInt) {
    setMaxScrollX(this.mRecord, paramInt);
  }
  
  @Deprecated
  public void setMaxScrollY(int paramInt) {
    setMaxScrollY(this.mRecord, paramInt);
  }
  
  @Deprecated
  public void setParcelableData(Parcelable paramParcelable) {
    this.mRecord.setParcelableData(paramParcelable);
  }
  
  @Deprecated
  public void setPassword(boolean paramBoolean) {
    this.mRecord.setPassword(paramBoolean);
  }
  
  @Deprecated
  public void setRemovedCount(int paramInt) {
    this.mRecord.setRemovedCount(paramInt);
  }
  
  @Deprecated
  public void setScrollX(int paramInt) {
    this.mRecord.setScrollX(paramInt);
  }
  
  @Deprecated
  public void setScrollY(int paramInt) {
    this.mRecord.setScrollY(paramInt);
  }
  
  @Deprecated
  public void setScrollable(boolean paramBoolean) {
    this.mRecord.setScrollable(paramBoolean);
  }
  
  @Deprecated
  public void setSource(View paramView) {
    this.mRecord.setSource(paramView);
  }
  
  @Deprecated
  public void setSource(View paramView, int paramInt) {
    setSource(this.mRecord, paramView, paramInt);
  }
  
  @Deprecated
  public void setToIndex(int paramInt) {
    this.mRecord.setToIndex(paramInt);
  }
  
  @RequiresApi(15)
  static class AccessibilityRecordCompatApi15Impl extends AccessibilityRecordCompatBaseImpl {
    public int getMaxScrollX(AccessibilityRecord param1AccessibilityRecord) {
      return param1AccessibilityRecord.getMaxScrollX();
    }
    
    public int getMaxScrollY(AccessibilityRecord param1AccessibilityRecord) {
      return param1AccessibilityRecord.getMaxScrollY();
    }
    
    public void setMaxScrollX(AccessibilityRecord param1AccessibilityRecord, int param1Int) {
      param1AccessibilityRecord.setMaxScrollX(param1Int);
    }
    
    public void setMaxScrollY(AccessibilityRecord param1AccessibilityRecord, int param1Int) {
      param1AccessibilityRecord.setMaxScrollY(param1Int);
    }
  }
  
  @RequiresApi(16)
  static class AccessibilityRecordCompatApi16Impl extends AccessibilityRecordCompatApi15Impl {
    public void setSource(AccessibilityRecord param1AccessibilityRecord, View param1View, int param1Int) {
      param1AccessibilityRecord.setSource(param1View, param1Int);
    }
  }
  
  static class AccessibilityRecordCompatBaseImpl {
    public int getMaxScrollX(AccessibilityRecord param1AccessibilityRecord) {
      return 0;
    }
    
    public int getMaxScrollY(AccessibilityRecord param1AccessibilityRecord) {
      return 0;
    }
    
    public void setMaxScrollX(AccessibilityRecord param1AccessibilityRecord, int param1Int) {}
    
    public void setMaxScrollY(AccessibilityRecord param1AccessibilityRecord, int param1Int) {}
    
    public void setSource(AccessibilityRecord param1AccessibilityRecord, View param1View, int param1Int) {}
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/accessibility/AccessibilityRecordCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */