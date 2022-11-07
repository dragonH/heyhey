package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AccessibilityNodeInfoCompat {
  public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
  
  public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
  
  public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
  
  public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
  
  public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
  
  public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
  
  public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
  
  public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
  
  public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
  
  public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
  
  public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
  
  public static final int ACTION_CLEAR_FOCUS = 2;
  
  public static final int ACTION_CLEAR_SELECTION = 8;
  
  public static final int ACTION_CLICK = 16;
  
  public static final int ACTION_COLLAPSE = 524288;
  
  public static final int ACTION_COPY = 16384;
  
  public static final int ACTION_CUT = 65536;
  
  public static final int ACTION_DISMISS = 1048576;
  
  public static final int ACTION_EXPAND = 262144;
  
  public static final int ACTION_FOCUS = 1;
  
  public static final int ACTION_LONG_CLICK = 32;
  
  public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
  
  public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
  
  public static final int ACTION_PASTE = 32768;
  
  public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
  
  public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
  
  public static final int ACTION_SCROLL_BACKWARD = 8192;
  
  public static final int ACTION_SCROLL_FORWARD = 4096;
  
  public static final int ACTION_SELECT = 4;
  
  public static final int ACTION_SET_SELECTION = 131072;
  
  public static final int ACTION_SET_TEXT = 2097152;
  
  public static final int FOCUS_ACCESSIBILITY = 2;
  
  public static final int FOCUS_INPUT = 1;
  
  static final AccessibilityNodeInfoBaseImpl IMPL;
  
  public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
  
  public static final int MOVEMENT_GRANULARITY_LINE = 4;
  
  public static final int MOVEMENT_GRANULARITY_PAGE = 16;
  
  public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
  
  public static final int MOVEMENT_GRANULARITY_WORD = 2;
  
  private final AccessibilityNodeInfo mInfo;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int mParentVirtualDescendantId = -1;
  
  static {
    int i = Build.VERSION.SDK_INT;
    if (i >= 24) {
      IMPL = new AccessibilityNodeInfoApi24Impl();
    } else if (i >= 23) {
      IMPL = new AccessibilityNodeInfoApi23Impl();
    } else if (i >= 22) {
      IMPL = new AccessibilityNodeInfoApi22Impl();
    } else if (i >= 21) {
      IMPL = new AccessibilityNodeInfoApi21Impl();
    } else if (i >= 19) {
      IMPL = new AccessibilityNodeInfoApi19Impl();
    } else if (i >= 18) {
      IMPL = new AccessibilityNodeInfoApi18Impl();
    } else if (i >= 17) {
      IMPL = new AccessibilityNodeInfoApi17Impl();
    } else {
      IMPL = new AccessibilityNodeInfoApi16Impl();
    } 
  }
  
  private AccessibilityNodeInfoCompat(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    this.mInfo = paramAccessibilityNodeInfo;
  }
  
  @Deprecated
  public AccessibilityNodeInfoCompat(Object paramObject) {
    this.mInfo = (AccessibilityNodeInfo)paramObject;
  }
  
  private static String getActionSymbolicName(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        switch (paramInt) {
          default:
            return "ACTION_UNKNOWN";
          case 131072:
            return "ACTION_SET_SELECTION";
          case 65536:
            return "ACTION_CUT";
          case 32768:
            return "ACTION_PASTE";
          case 16384:
            return "ACTION_COPY";
          case 8192:
            return "ACTION_SCROLL_BACKWARD";
          case 4096:
            return "ACTION_SCROLL_FORWARD";
          case 2048:
            return "ACTION_PREVIOUS_HTML_ELEMENT";
          case 1024:
            return "ACTION_NEXT_HTML_ELEMENT";
          case 512:
            return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
          case 256:
            return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
          case 128:
            return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
          case 64:
            return "ACTION_ACCESSIBILITY_FOCUS";
          case 32:
            return "ACTION_LONG_CLICK";
          case 16:
            return "ACTION_CLICK";
          case 8:
            return "ACTION_CLEAR_SELECTION";
          case 4:
            break;
        } 
        return "ACTION_SELECT";
      } 
      return "ACTION_CLEAR_FOCUS";
    } 
    return "ACTION_FOCUS";
  }
  
  public static AccessibilityNodeInfoCompat obtain() {
    return wrap(AccessibilityNodeInfo.obtain());
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
    return wrap(AccessibilityNodeInfo.obtain(paramAccessibilityNodeInfoCompat.mInfo));
  }
  
  public static AccessibilityNodeInfoCompat obtain(View paramView) {
    return wrap(AccessibilityNodeInfo.obtain(paramView));
  }
  
  public static AccessibilityNodeInfoCompat obtain(View paramView, int paramInt) {
    return wrapNonNullInstance(IMPL.obtain(paramView, paramInt));
  }
  
  public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    return new AccessibilityNodeInfoCompat(paramAccessibilityNodeInfo);
  }
  
  static AccessibilityNodeInfoCompat wrapNonNullInstance(Object paramObject) {
    return (paramObject != null) ? new AccessibilityNodeInfoCompat(paramObject) : null;
  }
  
  public void addAction(int paramInt) {
    this.mInfo.addAction(paramInt);
  }
  
  public void addAction(AccessibilityActionCompat paramAccessibilityActionCompat) {
    IMPL.addAction(this.mInfo, paramAccessibilityActionCompat.mAction);
  }
  
  public void addChild(View paramView) {
    this.mInfo.addChild(paramView);
  }
  
  public void addChild(View paramView, int paramInt) {
    IMPL.addChild(this.mInfo, paramView, paramInt);
  }
  
  public boolean canOpenPopup() {
    return IMPL.canOpenPopup(this.mInfo);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
    if (accessibilityNodeInfo == null) {
      if (((AccessibilityNodeInfoCompat)paramObject).mInfo != null)
        return false; 
    } else if (!accessibilityNodeInfo.equals(((AccessibilityNodeInfoCompat)paramObject).mInfo)) {
      return false;
    } 
    return true;
  }
  
  public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String paramString) {
    ArrayList<AccessibilityNodeInfoCompat> arrayList = new ArrayList();
    List<AccessibilityNodeInfo> list = this.mInfo.findAccessibilityNodeInfosByText(paramString);
    int i = list.size();
    for (byte b = 0; b < i; b++)
      arrayList.add(wrap(list.get(b))); 
    return arrayList;
  }
  
  public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String paramString) {
    List<AccessibilityNodeInfo> list = IMPL.findAccessibilityNodeInfosByViewId(this.mInfo, paramString);
    if (list != null) {
      ArrayList<AccessibilityNodeInfoCompat> arrayList = new ArrayList();
      Iterator<AccessibilityNodeInfo> iterator = list.iterator();
      while (iterator.hasNext())
        arrayList.add(wrap(iterator.next())); 
      return arrayList;
    } 
    return Collections.emptyList();
  }
  
  public AccessibilityNodeInfoCompat findFocus(int paramInt) {
    return wrapNonNullInstance(IMPL.findFocus(this.mInfo, paramInt));
  }
  
  public AccessibilityNodeInfoCompat focusSearch(int paramInt) {
    return wrapNonNullInstance(IMPL.focusSearch(this.mInfo, paramInt));
  }
  
  public List<AccessibilityActionCompat> getActionList() {
    List<Object> list = IMPL.getActionList(this.mInfo);
    if (list != null) {
      ArrayList<AccessibilityActionCompat> arrayList = new ArrayList();
      int i = list.size();
      for (byte b = 0; b < i; b++)
        arrayList.add(new AccessibilityActionCompat(list.get(b))); 
      return arrayList;
    } 
    return Collections.emptyList();
  }
  
  public int getActions() {
    return this.mInfo.getActions();
  }
  
  public void getBoundsInParent(Rect paramRect) {
    this.mInfo.getBoundsInParent(paramRect);
  }
  
  public void getBoundsInScreen(Rect paramRect) {
    this.mInfo.getBoundsInScreen(paramRect);
  }
  
  public AccessibilityNodeInfoCompat getChild(int paramInt) {
    return wrapNonNullInstance(this.mInfo.getChild(paramInt));
  }
  
  public int getChildCount() {
    return this.mInfo.getChildCount();
  }
  
  public CharSequence getClassName() {
    return this.mInfo.getClassName();
  }
  
  public CollectionInfoCompat getCollectionInfo() {
    Object object = IMPL.getCollectionInfo(this.mInfo);
    return (object == null) ? null : new CollectionInfoCompat(object);
  }
  
  public CollectionItemInfoCompat getCollectionItemInfo() {
    Object object = IMPL.getCollectionItemInfo(this.mInfo);
    return (object == null) ? null : new CollectionItemInfoCompat(object);
  }
  
  public CharSequence getContentDescription() {
    return this.mInfo.getContentDescription();
  }
  
  public int getDrawingOrder() {
    return IMPL.getDrawingOrder(this.mInfo);
  }
  
  public CharSequence getError() {
    return IMPL.getError(this.mInfo);
  }
  
  public Bundle getExtras() {
    return IMPL.getExtras(this.mInfo);
  }
  
  @Deprecated
  public Object getInfo() {
    return this.mInfo;
  }
  
  public int getInputType() {
    return IMPL.getInputType(this.mInfo);
  }
  
  public AccessibilityNodeInfoCompat getLabelFor() {
    return wrapNonNullInstance(IMPL.getLabelFor(this.mInfo));
  }
  
  public AccessibilityNodeInfoCompat getLabeledBy() {
    return wrapNonNullInstance(IMPL.getLabeledBy(this.mInfo));
  }
  
  public int getLiveRegion() {
    return IMPL.getLiveRegion(this.mInfo);
  }
  
  public int getMaxTextLength() {
    return IMPL.getMaxTextLength(this.mInfo);
  }
  
  public int getMovementGranularities() {
    return IMPL.getMovementGranularities(this.mInfo);
  }
  
  public CharSequence getPackageName() {
    return this.mInfo.getPackageName();
  }
  
  public AccessibilityNodeInfoCompat getParent() {
    return wrapNonNullInstance(this.mInfo.getParent());
  }
  
  public RangeInfoCompat getRangeInfo() {
    Object object = IMPL.getRangeInfo(this.mInfo);
    return (object == null) ? null : new RangeInfoCompat(object);
  }
  
  @Nullable
  public CharSequence getRoleDescription() {
    return IMPL.getRoleDescription(this.mInfo);
  }
  
  public CharSequence getText() {
    return this.mInfo.getText();
  }
  
  public int getTextSelectionEnd() {
    return IMPL.getTextSelectionEnd(this.mInfo);
  }
  
  public int getTextSelectionStart() {
    return IMPL.getTextSelectionStart(this.mInfo);
  }
  
  public AccessibilityNodeInfoCompat getTraversalAfter() {
    return wrapNonNullInstance(IMPL.getTraversalAfter(this.mInfo));
  }
  
  public AccessibilityNodeInfoCompat getTraversalBefore() {
    return wrapNonNullInstance(IMPL.getTraversalBefore(this.mInfo));
  }
  
  public String getViewIdResourceName() {
    return IMPL.getViewIdResourceName(this.mInfo);
  }
  
  public AccessibilityWindowInfoCompat getWindow() {
    return AccessibilityWindowInfoCompat.wrapNonNullInstance(IMPL.getWindow(this.mInfo));
  }
  
  public int getWindowId() {
    return this.mInfo.getWindowId();
  }
  
  public int hashCode() {
    int i;
    AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
    if (accessibilityNodeInfo == null) {
      i = 0;
    } else {
      i = accessibilityNodeInfo.hashCode();
    } 
    return i;
  }
  
  public boolean isAccessibilityFocused() {
    return IMPL.isAccessibilityFocused(this.mInfo);
  }
  
  public boolean isCheckable() {
    return this.mInfo.isCheckable();
  }
  
  public boolean isChecked() {
    return this.mInfo.isChecked();
  }
  
  public boolean isClickable() {
    return this.mInfo.isClickable();
  }
  
  public boolean isContentInvalid() {
    return IMPL.isContentInvalid(this.mInfo);
  }
  
  public boolean isContextClickable() {
    return IMPL.isContextClickable(this.mInfo);
  }
  
  public boolean isDismissable() {
    return IMPL.isDismissable(this.mInfo);
  }
  
  public boolean isEditable() {
    return IMPL.isEditable(this.mInfo);
  }
  
  public boolean isEnabled() {
    return this.mInfo.isEnabled();
  }
  
  public boolean isFocusable() {
    return this.mInfo.isFocusable();
  }
  
  public boolean isFocused() {
    return this.mInfo.isFocused();
  }
  
  public boolean isImportantForAccessibility() {
    return IMPL.isImportantForAccessibility(this.mInfo);
  }
  
  public boolean isLongClickable() {
    return this.mInfo.isLongClickable();
  }
  
  public boolean isMultiLine() {
    return IMPL.isMultiLine(this.mInfo);
  }
  
  public boolean isPassword() {
    return this.mInfo.isPassword();
  }
  
  public boolean isScrollable() {
    return this.mInfo.isScrollable();
  }
  
  public boolean isSelected() {
    return this.mInfo.isSelected();
  }
  
  public boolean isVisibleToUser() {
    return IMPL.isVisibleToUser(this.mInfo);
  }
  
  public boolean performAction(int paramInt) {
    return this.mInfo.performAction(paramInt);
  }
  
  public boolean performAction(int paramInt, Bundle paramBundle) {
    return IMPL.performAction(this.mInfo, paramInt, paramBundle);
  }
  
  public void recycle() {
    this.mInfo.recycle();
  }
  
  public boolean refresh() {
    return IMPL.refresh(this.mInfo);
  }
  
  public boolean removeAction(AccessibilityActionCompat paramAccessibilityActionCompat) {
    return IMPL.removeAction(this.mInfo, paramAccessibilityActionCompat.mAction);
  }
  
  public boolean removeChild(View paramView) {
    return IMPL.removeChild(this.mInfo, paramView);
  }
  
  public boolean removeChild(View paramView, int paramInt) {
    return IMPL.removeChild(this.mInfo, paramView, paramInt);
  }
  
  public void setAccessibilityFocused(boolean paramBoolean) {
    IMPL.setAccessibilityFocused(this.mInfo, paramBoolean);
  }
  
  public void setBoundsInParent(Rect paramRect) {
    this.mInfo.setBoundsInParent(paramRect);
  }
  
  public void setBoundsInScreen(Rect paramRect) {
    this.mInfo.setBoundsInScreen(paramRect);
  }
  
  public void setCanOpenPopup(boolean paramBoolean) {
    IMPL.setCanOpenPopup(this.mInfo, paramBoolean);
  }
  
  public void setCheckable(boolean paramBoolean) {
    this.mInfo.setCheckable(paramBoolean);
  }
  
  public void setChecked(boolean paramBoolean) {
    this.mInfo.setChecked(paramBoolean);
  }
  
  public void setClassName(CharSequence paramCharSequence) {
    this.mInfo.setClassName(paramCharSequence);
  }
  
  public void setClickable(boolean paramBoolean) {
    this.mInfo.setClickable(paramBoolean);
  }
  
  public void setCollectionInfo(Object paramObject) {
    IMPL.setCollectionInfo(this.mInfo, ((CollectionInfoCompat)paramObject).mInfo);
  }
  
  public void setCollectionItemInfo(Object paramObject) {
    IMPL.setCollectionItemInfo(this.mInfo, ((CollectionItemInfoCompat)paramObject).mInfo);
  }
  
  public void setContentDescription(CharSequence paramCharSequence) {
    this.mInfo.setContentDescription(paramCharSequence);
  }
  
  public void setContentInvalid(boolean paramBoolean) {
    IMPL.setContentInvalid(this.mInfo, paramBoolean);
  }
  
  public void setContextClickable(boolean paramBoolean) {
    IMPL.setContextClickable(this.mInfo, paramBoolean);
  }
  
  public void setDismissable(boolean paramBoolean) {
    IMPL.setDismissable(this.mInfo, paramBoolean);
  }
  
  public void setDrawingOrder(int paramInt) {
    IMPL.setDrawingOrder(this.mInfo, paramInt);
  }
  
  public void setEditable(boolean paramBoolean) {
    IMPL.setEditable(this.mInfo, paramBoolean);
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.mInfo.setEnabled(paramBoolean);
  }
  
  public void setError(CharSequence paramCharSequence) {
    IMPL.setError(this.mInfo, paramCharSequence);
  }
  
  public void setFocusable(boolean paramBoolean) {
    this.mInfo.setFocusable(paramBoolean);
  }
  
  public void setFocused(boolean paramBoolean) {
    this.mInfo.setFocused(paramBoolean);
  }
  
  public void setImportantForAccessibility(boolean paramBoolean) {
    IMPL.setImportantForAccessibility(this.mInfo, paramBoolean);
  }
  
  public void setInputType(int paramInt) {
    IMPL.setInputType(this.mInfo, paramInt);
  }
  
  public void setLabelFor(View paramView) {
    IMPL.setLabelFor(this.mInfo, paramView);
  }
  
  public void setLabelFor(View paramView, int paramInt) {
    IMPL.setLabelFor(this.mInfo, paramView, paramInt);
  }
  
  public void setLabeledBy(View paramView) {
    IMPL.setLabeledBy(this.mInfo, paramView);
  }
  
  public void setLabeledBy(View paramView, int paramInt) {
    IMPL.setLabeledBy(this.mInfo, paramView, paramInt);
  }
  
  public void setLiveRegion(int paramInt) {
    IMPL.setLiveRegion(this.mInfo, paramInt);
  }
  
  public void setLongClickable(boolean paramBoolean) {
    this.mInfo.setLongClickable(paramBoolean);
  }
  
  public void setMaxTextLength(int paramInt) {
    IMPL.setMaxTextLength(this.mInfo, paramInt);
  }
  
  public void setMovementGranularities(int paramInt) {
    IMPL.setMovementGranularities(this.mInfo, paramInt);
  }
  
  public void setMultiLine(boolean paramBoolean) {
    IMPL.setMultiLine(this.mInfo, paramBoolean);
  }
  
  public void setPackageName(CharSequence paramCharSequence) {
    this.mInfo.setPackageName(paramCharSequence);
  }
  
  public void setParent(View paramView) {
    this.mInfo.setParent(paramView);
  }
  
  public void setParent(View paramView, int paramInt) {
    this.mParentVirtualDescendantId = paramInt;
    IMPL.setParent(this.mInfo, paramView, paramInt);
  }
  
  public void setPassword(boolean paramBoolean) {
    this.mInfo.setPassword(paramBoolean);
  }
  
  public void setRangeInfo(RangeInfoCompat paramRangeInfoCompat) {
    IMPL.setRangeInfo(this.mInfo, paramRangeInfoCompat.mInfo);
  }
  
  public void setRoleDescription(@Nullable CharSequence paramCharSequence) {
    IMPL.setRoleDescription(this.mInfo, paramCharSequence);
  }
  
  public void setScrollable(boolean paramBoolean) {
    this.mInfo.setScrollable(paramBoolean);
  }
  
  public void setSelected(boolean paramBoolean) {
    this.mInfo.setSelected(paramBoolean);
  }
  
  public void setSource(View paramView) {
    this.mInfo.setSource(paramView);
  }
  
  public void setSource(View paramView, int paramInt) {
    IMPL.setSource(this.mInfo, paramView, paramInt);
  }
  
  public void setText(CharSequence paramCharSequence) {
    this.mInfo.setText(paramCharSequence);
  }
  
  public void setTextSelection(int paramInt1, int paramInt2) {
    IMPL.setTextSelection(this.mInfo, paramInt1, paramInt2);
  }
  
  public void setTraversalAfter(View paramView) {
    IMPL.setTraversalAfter(this.mInfo, paramView);
  }
  
  public void setTraversalAfter(View paramView, int paramInt) {
    IMPL.setTraversalAfter(this.mInfo, paramView, paramInt);
  }
  
  public void setTraversalBefore(View paramView) {
    IMPL.setTraversalBefore(this.mInfo, paramView);
  }
  
  public void setTraversalBefore(View paramView, int paramInt) {
    IMPL.setTraversalBefore(this.mInfo, paramView, paramInt);
  }
  
  public void setViewIdResourceName(String paramString) {
    IMPL.setViewIdResourceName(this.mInfo, paramString);
  }
  
  public void setVisibleToUser(boolean paramBoolean) {
    IMPL.setVisibleToUser(this.mInfo, paramBoolean);
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(super.toString());
    Rect rect = new Rect();
    getBoundsInParent(rect);
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("; boundsInParent: ");
    stringBuilder3.append(rect);
    stringBuilder1.append(stringBuilder3.toString());
    getBoundsInScreen(rect);
    stringBuilder3 = new StringBuilder();
    stringBuilder3.append("; boundsInScreen: ");
    stringBuilder3.append(rect);
    stringBuilder1.append(stringBuilder3.toString());
    stringBuilder1.append("; packageName: ");
    stringBuilder1.append(getPackageName());
    stringBuilder1.append("; className: ");
    stringBuilder1.append(getClassName());
    stringBuilder1.append("; text: ");
    stringBuilder1.append(getText());
    stringBuilder1.append("; contentDescription: ");
    stringBuilder1.append(getContentDescription());
    stringBuilder1.append("; viewId: ");
    stringBuilder1.append(getViewIdResourceName());
    stringBuilder1.append("; checkable: ");
    stringBuilder1.append(isCheckable());
    stringBuilder1.append("; checked: ");
    stringBuilder1.append(isChecked());
    stringBuilder1.append("; focusable: ");
    stringBuilder1.append(isFocusable());
    stringBuilder1.append("; focused: ");
    stringBuilder1.append(isFocused());
    stringBuilder1.append("; selected: ");
    stringBuilder1.append(isSelected());
    stringBuilder1.append("; clickable: ");
    stringBuilder1.append(isClickable());
    stringBuilder1.append("; longClickable: ");
    stringBuilder1.append(isLongClickable());
    stringBuilder1.append("; enabled: ");
    stringBuilder1.append(isEnabled());
    stringBuilder1.append("; password: ");
    stringBuilder1.append(isPassword());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("; scrollable: ");
    stringBuilder2.append(isScrollable());
    stringBuilder1.append(stringBuilder2.toString());
    stringBuilder1.append("; [");
    int i = getActions();
    while (i != 0) {
      int j = 1 << Integer.numberOfTrailingZeros(i);
      int k = i & (j ^ 0xFFFFFFFF);
      stringBuilder1.append(getActionSymbolicName(j));
      i = k;
      if (k != 0) {
        stringBuilder1.append(", ");
        i = k;
      } 
    } 
    stringBuilder1.append("]");
    return stringBuilder1.toString();
  }
  
  public AccessibilityNodeInfo unwrap() {
    return this.mInfo;
  }
  
  public static class AccessibilityActionCompat {
    public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
    
    public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    
    public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
    
    public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
    
    public static final AccessibilityActionCompat ACTION_CLICK;
    
    public static final AccessibilityActionCompat ACTION_COLLAPSE;
    
    public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
    
    public static final AccessibilityActionCompat ACTION_COPY;
    
    public static final AccessibilityActionCompat ACTION_CUT;
    
    public static final AccessibilityActionCompat ACTION_DISMISS;
    
    public static final AccessibilityActionCompat ACTION_EXPAND;
    
    public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
    
    public static final AccessibilityActionCompat ACTION_LONG_CLICK;
    
    public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
    
    public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
    
    public static final AccessibilityActionCompat ACTION_PASTE;
    
    public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
    
    public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
    
    public static final AccessibilityActionCompat ACTION_SCROLL_UP;
    
    public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
    
    public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
    
    public static final AccessibilityActionCompat ACTION_SET_SELECTION;
    
    public static final AccessibilityActionCompat ACTION_SET_TEXT;
    
    public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
    
    final Object mAction;
    
    static {
      ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
      ACTION_CLICK = new AccessibilityActionCompat(16, null);
      ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
      ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
      ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
      ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
      ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
      ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
      ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
      ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
      ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
      ACTION_COPY = new AccessibilityActionCompat(16384, null);
      ACTION_PASTE = new AccessibilityActionCompat(32768, null);
      ACTION_CUT = new AccessibilityActionCompat(65536, null);
      ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
      ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
      ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
      ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
      ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
      AccessibilityNodeInfoCompat.AccessibilityNodeInfoBaseImpl accessibilityNodeInfoBaseImpl = AccessibilityNodeInfoCompat.IMPL;
      ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionShowOnScreen());
      ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionScrollToPosition());
      ACTION_SCROLL_UP = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionScrollUp());
      ACTION_SCROLL_LEFT = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionScrollLeft());
      ACTION_SCROLL_DOWN = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionScrollDown());
      ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionScrollRight());
      ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionContextClick());
      ACTION_SET_PROGRESS = new AccessibilityActionCompat(accessibilityNodeInfoBaseImpl.getActionSetProgress());
    }
    
    public AccessibilityActionCompat(int param1Int, CharSequence param1CharSequence) {
      this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(param1Int, param1CharSequence));
    }
    
    AccessibilityActionCompat(Object param1Object) {
      this.mAction = param1Object;
    }
    
    public int getId() {
      return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(this.mAction);
    }
    
    public CharSequence getLabel() {
      return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(this.mAction);
    }
  }
  
  @RequiresApi(16)
  static class AccessibilityNodeInfoApi16Impl extends AccessibilityNodeInfoBaseImpl {
    public void addChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.addChild(param1View, param1Int);
    }
    
    public Object findFocus(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      return param1AccessibilityNodeInfo.findFocus(param1Int);
    }
    
    public Object focusSearch(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      return param1AccessibilityNodeInfo.focusSearch(param1Int);
    }
    
    public int getMovementGranularities(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getMovementGranularities();
    }
    
    public boolean isAccessibilityFocused(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isAccessibilityFocused();
    }
    
    public boolean isVisibleToUser(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isVisibleToUser();
    }
    
    public AccessibilityNodeInfo obtain(View param1View, int param1Int) {
      return AccessibilityNodeInfo.obtain(param1View, param1Int);
    }
    
    public boolean performAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int, Bundle param1Bundle) {
      return param1AccessibilityNodeInfo.performAction(param1Int, param1Bundle);
    }
    
    public void setAccessibilityFocused(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setAccessibilityFocused(param1Boolean);
    }
    
    public void setMovementGranularities(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      param1AccessibilityNodeInfo.setMovementGranularities(param1Int);
    }
    
    public void setParent(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setParent(param1View, param1Int);
    }
    
    public void setSource(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setSource(param1View, param1Int);
    }
    
    public void setVisibleToUser(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setVisibleToUser(param1Boolean);
    }
  }
  
  @RequiresApi(17)
  static class AccessibilityNodeInfoApi17Impl extends AccessibilityNodeInfoApi16Impl {
    public Object getLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getLabelFor();
    }
    
    public Object getLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getLabeledBy();
    }
    
    public void setLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      param1AccessibilityNodeInfo.setLabelFor(param1View);
    }
    
    public void setLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setLabelFor(param1View, param1Int);
    }
    
    public void setLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      param1AccessibilityNodeInfo.setLabeledBy(param1View);
    }
    
    public void setLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setLabeledBy(param1View, param1Int);
    }
  }
  
  @RequiresApi(18)
  static class AccessibilityNodeInfoApi18Impl extends AccessibilityNodeInfoApi17Impl {
    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo param1AccessibilityNodeInfo, String param1String) {
      return param1AccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(param1String);
    }
    
    public int getTextSelectionEnd(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getTextSelectionEnd();
    }
    
    public int getTextSelectionStart(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getTextSelectionStart();
    }
    
    public String getViewIdResourceName(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getViewIdResourceName();
    }
    
    public boolean isEditable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isEditable();
    }
    
    public boolean refresh(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.refresh();
    }
    
    public void setEditable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setEditable(param1Boolean);
    }
    
    public void setTextSelection(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int1, int param1Int2) {
      param1AccessibilityNodeInfo.setTextSelection(param1Int1, param1Int2);
    }
    
    public void setViewIdResourceName(AccessibilityNodeInfo param1AccessibilityNodeInfo, String param1String) {
      param1AccessibilityNodeInfo.setViewIdResourceName(param1String);
    }
  }
  
  @RequiresApi(19)
  static class AccessibilityNodeInfoApi19Impl extends AccessibilityNodeInfoApi18Impl {
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    
    public boolean canOpenPopup(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.canOpenPopup();
    }
    
    public Object getCollectionInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getCollectionInfo();
    }
    
    public int getCollectionInfoColumnCount(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionInfo)param1Object).getColumnCount();
    }
    
    public int getCollectionInfoRowCount(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionInfo)param1Object).getRowCount();
    }
    
    public int getCollectionItemColumnIndex(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).getColumnIndex();
    }
    
    public int getCollectionItemColumnSpan(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).getColumnSpan();
    }
    
    public Object getCollectionItemInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getCollectionItemInfo();
    }
    
    public int getCollectionItemRowIndex(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).getRowIndex();
    }
    
    public int getCollectionItemRowSpan(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).getRowSpan();
    }
    
    public Bundle getExtras(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getExtras();
    }
    
    public int getInputType(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getInputType();
    }
    
    public int getLiveRegion(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getLiveRegion();
    }
    
    public Object getRangeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getRangeInfo();
    }
    
    public CharSequence getRoleDescription(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return getExtras(param1AccessibilityNodeInfo).getCharSequence("AccessibilityNodeInfo.roleDescription");
    }
    
    public boolean isCollectionInfoHierarchical(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionInfo)param1Object).isHierarchical();
    }
    
    public boolean isCollectionItemHeading(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).isHeading();
    }
    
    public boolean isContentInvalid(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isContentInvalid();
    }
    
    public boolean isDismissable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isDismissable();
    }
    
    public boolean isMultiLine(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isMultiLine();
    }
    
    public Object obtainCollectionInfo(int param1Int1, int param1Int2, boolean param1Boolean) {
      return AccessibilityNodeInfo.CollectionInfo.obtain(param1Int1, param1Int2, param1Boolean);
    }
    
    public Object obtainCollectionInfo(int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
      return AccessibilityNodeInfo.CollectionInfo.obtain(param1Int1, param1Int2, param1Boolean);
    }
    
    public Object obtainCollectionItemInfo(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) {
      return AccessibilityNodeInfo.CollectionItemInfo.obtain(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean);
    }
    
    public Object obtainCollectionItemInfo(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2) {
      return AccessibilityNodeInfo.CollectionItemInfo.obtain(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean1);
    }
    
    public Object obtainRangeInfo(int param1Int, float param1Float1, float param1Float2, float param1Float3) {
      return AccessibilityNodeInfo.RangeInfo.obtain(param1Int, param1Float1, param1Float2, param1Float3);
    }
    
    public void setCanOpenPopup(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setCanOpenPopup(param1Boolean);
    }
    
    public void setCollectionInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      param1AccessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo)param1Object);
    }
    
    public void setCollectionItemInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      param1AccessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo)param1Object);
    }
    
    public void setContentInvalid(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setContentInvalid(param1Boolean);
    }
    
    public void setDismissable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setDismissable(param1Boolean);
    }
    
    public void setInputType(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      param1AccessibilityNodeInfo.setInputType(param1Int);
    }
    
    public void setLiveRegion(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      param1AccessibilityNodeInfo.setLiveRegion(param1Int);
    }
    
    public void setMultiLine(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setMultiLine(param1Boolean);
    }
    
    public void setRangeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      param1AccessibilityNodeInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo)param1Object);
    }
    
    public void setRoleDescription(AccessibilityNodeInfo param1AccessibilityNodeInfo, CharSequence param1CharSequence) {
      getExtras(param1AccessibilityNodeInfo).putCharSequence("AccessibilityNodeInfo.roleDescription", param1CharSequence);
    }
  }
  
  @RequiresApi(21)
  static class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoApi19Impl {
    public void addAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      param1AccessibilityNodeInfo.addAction((AccessibilityNodeInfo.AccessibilityAction)param1Object);
    }
    
    public int getAccessibilityActionId(Object param1Object) {
      return ((AccessibilityNodeInfo.AccessibilityAction)param1Object).getId();
    }
    
    public CharSequence getAccessibilityActionLabel(Object param1Object) {
      return ((AccessibilityNodeInfo.AccessibilityAction)param1Object).getLabel();
    }
    
    public List<Object> getActionList(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getActionList();
    }
    
    public int getCollectionInfoSelectionMode(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionInfo)param1Object).getSelectionMode();
    }
    
    public CharSequence getError(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getError();
    }
    
    public int getMaxTextLength(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getMaxTextLength();
    }
    
    public Object getWindow(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getWindow();
    }
    
    public boolean isCollectionItemSelected(Object param1Object) {
      return ((AccessibilityNodeInfo.CollectionItemInfo)param1Object).isSelected();
    }
    
    public Object newAccessibilityAction(int param1Int, CharSequence param1CharSequence) {
      return new AccessibilityNodeInfo.AccessibilityAction(param1Int, param1CharSequence);
    }
    
    public Object obtainCollectionInfo(int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
      return AccessibilityNodeInfo.CollectionInfo.obtain(param1Int1, param1Int2, param1Boolean, param1Int3);
    }
    
    public Object obtainCollectionItemInfo(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2) {
      return AccessibilityNodeInfo.CollectionItemInfo.obtain(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean1, param1Boolean2);
    }
    
    public boolean removeAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      return param1AccessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction)param1Object);
    }
    
    public boolean removeChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      return param1AccessibilityNodeInfo.removeChild(param1View);
    }
    
    public boolean removeChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      return param1AccessibilityNodeInfo.removeChild(param1View, param1Int);
    }
    
    public void setError(AccessibilityNodeInfo param1AccessibilityNodeInfo, CharSequence param1CharSequence) {
      param1AccessibilityNodeInfo.setError(param1CharSequence);
    }
    
    public void setMaxTextLength(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      param1AccessibilityNodeInfo.setMaxTextLength(param1Int);
    }
  }
  
  @RequiresApi(22)
  static class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl {
    public Object getTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getTraversalAfter();
    }
    
    public Object getTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getTraversalBefore();
    }
    
    public void setTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      param1AccessibilityNodeInfo.setTraversalAfter(param1View);
    }
    
    public void setTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setTraversalAfter(param1View, param1Int);
    }
    
    public void setTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      param1AccessibilityNodeInfo.setTraversalBefore(param1View);
    }
    
    public void setTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      param1AccessibilityNodeInfo.setTraversalBefore(param1View, param1Int);
    }
  }
  
  @RequiresApi(23)
  static class AccessibilityNodeInfoApi23Impl extends AccessibilityNodeInfoApi22Impl {
    public Object getActionContextClick() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
    }
    
    public Object getActionScrollDown() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
    }
    
    public Object getActionScrollLeft() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
    }
    
    public Object getActionScrollRight() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
    }
    
    public Object getActionScrollToPosition() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
    }
    
    public Object getActionScrollUp() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
    }
    
    public Object getActionShowOnScreen() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
    }
    
    public boolean isContextClickable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isContextClickable();
    }
    
    public void setContextClickable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setContextClickable(param1Boolean);
    }
  }
  
  @RequiresApi(24)
  static class AccessibilityNodeInfoApi24Impl extends AccessibilityNodeInfoApi23Impl {
    public Object getActionSetProgress() {
      return AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
    }
    
    public int getDrawingOrder(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.getDrawingOrder();
    }
    
    public boolean isImportantForAccessibility(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return param1AccessibilityNodeInfo.isImportantForAccessibility();
    }
    
    public void setDrawingOrder(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      param1AccessibilityNodeInfo.setDrawingOrder(param1Int);
    }
    
    public void setImportantForAccessibility(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {
      param1AccessibilityNodeInfo.setImportantForAccessibility(param1Boolean);
    }
  }
  
  static class AccessibilityNodeInfoBaseImpl {
    public void addAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {}
    
    public void addChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public boolean canOpenPopup(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId(AccessibilityNodeInfo param1AccessibilityNodeInfo, String param1String) {
      return Collections.emptyList();
    }
    
    public Object findFocus(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      return null;
    }
    
    public Object focusSearch(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {
      return null;
    }
    
    public int getAccessibilityActionId(Object param1Object) {
      return 0;
    }
    
    public CharSequence getAccessibilityActionLabel(Object param1Object) {
      return null;
    }
    
    public Object getActionContextClick() {
      return null;
    }
    
    public List<Object> getActionList(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public Object getActionScrollDown() {
      return null;
    }
    
    public Object getActionScrollLeft() {
      return null;
    }
    
    public Object getActionScrollRight() {
      return null;
    }
    
    public Object getActionScrollToPosition() {
      return null;
    }
    
    public Object getActionScrollUp() {
      return null;
    }
    
    public Object getActionSetProgress() {
      return null;
    }
    
    public Object getActionShowOnScreen() {
      return null;
    }
    
    public Object getCollectionInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public int getCollectionInfoColumnCount(Object param1Object) {
      return 0;
    }
    
    public int getCollectionInfoRowCount(Object param1Object) {
      return 0;
    }
    
    public int getCollectionInfoSelectionMode(Object param1Object) {
      return 0;
    }
    
    public int getCollectionItemColumnIndex(Object param1Object) {
      return 0;
    }
    
    public int getCollectionItemColumnSpan(Object param1Object) {
      return 0;
    }
    
    public Object getCollectionItemInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public int getCollectionItemRowIndex(Object param1Object) {
      return 0;
    }
    
    public int getCollectionItemRowSpan(Object param1Object) {
      return 0;
    }
    
    public int getDrawingOrder(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return 0;
    }
    
    public CharSequence getError(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public Bundle getExtras(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return new Bundle();
    }
    
    public int getInputType(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return 0;
    }
    
    public Object getLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public Object getLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public int getLiveRegion(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return 0;
    }
    
    public int getMaxTextLength(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return -1;
    }
    
    public int getMovementGranularities(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return 0;
    }
    
    public Object getRangeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public CharSequence getRoleDescription(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public int getTextSelectionEnd(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return -1;
    }
    
    public int getTextSelectionStart(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return -1;
    }
    
    public Object getTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public Object getTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public String getViewIdResourceName(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public Object getWindow(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return null;
    }
    
    public boolean isAccessibilityFocused(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isCollectionInfoHierarchical(Object param1Object) {
      return false;
    }
    
    public boolean isCollectionItemHeading(Object param1Object) {
      return false;
    }
    
    public boolean isCollectionItemSelected(Object param1Object) {
      return false;
    }
    
    public boolean isContentInvalid(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isContextClickable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isDismissable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isEditable(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isImportantForAccessibility(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return true;
    }
    
    public boolean isMultiLine(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean isVisibleToUser(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public Object newAccessibilityAction(int param1Int, CharSequence param1CharSequence) {
      return null;
    }
    
    public AccessibilityNodeInfo obtain(View param1View, int param1Int) {
      return null;
    }
    
    public Object obtainCollectionInfo(int param1Int1, int param1Int2, boolean param1Boolean) {
      return null;
    }
    
    public Object obtainCollectionInfo(int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
      return null;
    }
    
    public Object obtainCollectionItemInfo(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) {
      return null;
    }
    
    public Object obtainCollectionItemInfo(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2) {
      return null;
    }
    
    public Object obtainRangeInfo(int param1Int, float param1Float1, float param1Float2, float param1Float3) {
      return null;
    }
    
    public boolean performAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int, Bundle param1Bundle) {
      return false;
    }
    
    public boolean refresh(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      return false;
    }
    
    public boolean removeAction(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {
      return false;
    }
    
    public boolean removeChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {
      return false;
    }
    
    public boolean removeChild(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {
      return false;
    }
    
    public void setAccessibilityFocused(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setCanOpenPopup(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setCollectionInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {}
    
    public void setCollectionItemInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {}
    
    public void setContentInvalid(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setContextClickable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setDismissable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setDrawingOrder(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {}
    
    public void setEditable(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setError(AccessibilityNodeInfo param1AccessibilityNodeInfo, CharSequence param1CharSequence) {}
    
    public void setImportantForAccessibility(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setInputType(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {}
    
    public void setLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {}
    
    public void setLabelFor(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {}
    
    public void setLabeledBy(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setLiveRegion(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {}
    
    public void setMaxTextLength(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {}
    
    public void setMovementGranularities(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int) {}
    
    public void setMultiLine(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
    
    public void setParent(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setRangeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo, Object param1Object) {}
    
    public void setRoleDescription(AccessibilityNodeInfo param1AccessibilityNodeInfo, CharSequence param1CharSequence) {}
    
    public void setSource(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setTextSelection(AccessibilityNodeInfo param1AccessibilityNodeInfo, int param1Int1, int param1Int2) {}
    
    public void setTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {}
    
    public void setTraversalAfter(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View) {}
    
    public void setTraversalBefore(AccessibilityNodeInfo param1AccessibilityNodeInfo, View param1View, int param1Int) {}
    
    public void setViewIdResourceName(AccessibilityNodeInfo param1AccessibilityNodeInfo, String param1String) {}
    
    public void setVisibleToUser(AccessibilityNodeInfo param1AccessibilityNodeInfo, boolean param1Boolean) {}
  }
  
  public static class CollectionInfoCompat {
    public static final int SELECTION_MODE_MULTIPLE = 2;
    
    public static final int SELECTION_MODE_NONE = 0;
    
    public static final int SELECTION_MODE_SINGLE = 1;
    
    final Object mInfo;
    
    CollectionInfoCompat(Object param1Object) {
      this.mInfo = param1Object;
    }
    
    public static CollectionInfoCompat obtain(int param1Int1, int param1Int2, boolean param1Boolean) {
      return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(param1Int1, param1Int2, param1Boolean));
    }
    
    public static CollectionInfoCompat obtain(int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
      return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(param1Int1, param1Int2, param1Boolean, param1Int3));
    }
    
    public int getColumnCount() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(this.mInfo);
    }
    
    public int getRowCount() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(this.mInfo);
    }
    
    public int getSelectionMode() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoSelectionMode(this.mInfo);
    }
    
    public boolean isHierarchical() {
      return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(this.mInfo);
    }
  }
  
  public static class CollectionItemInfoCompat {
    final Object mInfo;
    
    CollectionItemInfoCompat(Object param1Object) {
      this.mInfo = param1Object;
    }
    
    public static CollectionItemInfoCompat obtain(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) {
      return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean));
    }
    
    public static CollectionItemInfoCompat obtain(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2) {
      return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean1, param1Boolean2));
    }
    
    public int getColumnIndex() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(this.mInfo);
    }
    
    public int getColumnSpan() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(this.mInfo);
    }
    
    public int getRowIndex() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(this.mInfo);
    }
    
    public int getRowSpan() {
      return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(this.mInfo);
    }
    
    public boolean isHeading() {
      return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(this.mInfo);
    }
    
    public boolean isSelected() {
      return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(this.mInfo);
    }
  }
  
  public static class RangeInfoCompat {
    public static final int RANGE_TYPE_FLOAT = 1;
    
    public static final int RANGE_TYPE_INT = 0;
    
    public static final int RANGE_TYPE_PERCENT = 2;
    
    final Object mInfo;
    
    RangeInfoCompat(Object param1Object) {
      this.mInfo = param1Object;
    }
    
    public static RangeInfoCompat obtain(int param1Int, float param1Float1, float param1Float2, float param1Float3) {
      return new RangeInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainRangeInfo(param1Int, param1Float1, param1Float2, param1Float3));
    }
    
    public float getCurrent() {
      return AccessibilityNodeInfoCompatKitKat.RangeInfo.getCurrent(this.mInfo);
    }
    
    public float getMax() {
      return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMax(this.mInfo);
    }
    
    public float getMin() {
      return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMin(this.mInfo);
    }
    
    public int getType() {
      return AccessibilityNodeInfoCompatKitKat.RangeInfo.getType(this.mInfo);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/accessibility/AccessibilityNodeInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */