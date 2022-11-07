package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BottomNavigationView extends FrameLayout {
  private static final int[] CHECKED_STATE_SET = new int[] { 16842912 };
  
  private static final int[] DISABLED_STATE_SET = new int[] { -16842910 };
  
  private static final int MENU_PRESENTER_ID = 1;
  
  private final MenuBuilder mMenu;
  
  private MenuInflater mMenuInflater;
  
  private final BottomNavigationMenuView mMenuView;
  
  private final BottomNavigationPresenter mPresenter;
  
  private OnNavigationItemReselectedListener mReselectedListener;
  
  private OnNavigationItemSelectedListener mSelectedListener;
  
  public BottomNavigationView(Context paramContext) {
    this(paramContext, null);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
    this.mPresenter = bottomNavigationPresenter;
    ThemeUtils.checkAppCompatTheme(paramContext);
    BottomNavigationMenu bottomNavigationMenu = new BottomNavigationMenu(paramContext);
    this.mMenu = (MenuBuilder)bottomNavigationMenu;
    BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(paramContext);
    this.mMenuView = bottomNavigationMenuView;
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
    layoutParams.gravity = 17;
    bottomNavigationMenuView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    bottomNavigationPresenter.setBottomNavigationMenuView(bottomNavigationMenuView);
    bottomNavigationPresenter.setId(1);
    bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
    bottomNavigationMenu.addMenuPresenter((MenuPresenter)bottomNavigationPresenter);
    bottomNavigationPresenter.initForMenu(getContext(), (MenuBuilder)bottomNavigationMenu);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.BottomNavigationView, paramInt, R.style.Widget_Design_BottomNavigationView);
    paramInt = R.styleable.BottomNavigationView_itemIconTint;
    if (tintTypedArray.hasValue(paramInt)) {
      bottomNavigationMenuView.setIconTintList(tintTypedArray.getColorStateList(paramInt));
    } else {
      bottomNavigationMenuView.setIconTintList(createDefaultColorStateList(16842808));
    } 
    paramInt = R.styleable.BottomNavigationView_itemTextColor;
    if (tintTypedArray.hasValue(paramInt)) {
      bottomNavigationMenuView.setItemTextColor(tintTypedArray.getColorStateList(paramInt));
    } else {
      bottomNavigationMenuView.setItemTextColor(createDefaultColorStateList(16842808));
    } 
    paramInt = R.styleable.BottomNavigationView_elevation;
    if (tintTypedArray.hasValue(paramInt))
      ViewCompat.setElevation((View)this, tintTypedArray.getDimensionPixelSize(paramInt, 0)); 
    bottomNavigationMenuView.setItemBackgroundRes(tintTypedArray.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0));
    paramInt = R.styleable.BottomNavigationView_menu;
    if (tintTypedArray.hasValue(paramInt))
      inflateMenu(tintTypedArray.getResourceId(paramInt, 0)); 
    tintTypedArray.recycle();
    addView((View)bottomNavigationMenuView, (ViewGroup.LayoutParams)layoutParams);
    if (Build.VERSION.SDK_INT < 21)
      addCompatibilityTopDivider(paramContext); 
    bottomNavigationMenu.setCallback(new MenuBuilder.Callback() {
          public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
            BottomNavigationView.OnNavigationItemReselectedListener onNavigationItemReselectedListener = BottomNavigationView.this.mReselectedListener;
            boolean bool = true;
            if (onNavigationItemReselectedListener != null && param1MenuItem.getItemId() == BottomNavigationView.this.getSelectedItemId()) {
              BottomNavigationView.this.mReselectedListener.onNavigationItemReselected(param1MenuItem);
              return true;
            } 
            if (BottomNavigationView.this.mSelectedListener == null || BottomNavigationView.this.mSelectedListener.onNavigationItemSelected(param1MenuItem))
              bool = false; 
            return bool;
          }
          
          public void onMenuModeChange(MenuBuilder param1MenuBuilder) {}
        });
  }
  
  private void addCompatibilityTopDivider(Context paramContext) {
    View view = new View(paramContext);
    view.setBackgroundColor(ContextCompat.getColor(paramContext, R.color.design_bottom_navigation_shadow_color));
    view.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_shadow_height)));
    addView(view);
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt) {
    TypedValue typedValue = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, typedValue, true))
      return null; 
    ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
    if (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true))
      return null; 
    int i = typedValue.data;
    paramInt = colorStateList.getDefaultColor();
    int[] arrayOfInt2 = DISABLED_STATE_SET;
    int[] arrayOfInt3 = CHECKED_STATE_SET;
    int[] arrayOfInt1 = FrameLayout.EMPTY_STATE_SET;
    int j = colorStateList.getColorForState(arrayOfInt2, paramInt);
    return new ColorStateList(new int[][] { arrayOfInt2, arrayOfInt3, arrayOfInt1 }, new int[] { j, i, paramInt });
  }
  
  private MenuInflater getMenuInflater() {
    if (this.mMenuInflater == null)
      this.mMenuInflater = (MenuInflater)new SupportMenuInflater(getContext()); 
    return this.mMenuInflater;
  }
  
  @DrawableRes
  public int getItemBackgroundResource() {
    return this.mMenuView.getItemBackgroundRes();
  }
  
  @Nullable
  public ColorStateList getItemIconTintList() {
    return this.mMenuView.getIconTintList();
  }
  
  @Nullable
  public ColorStateList getItemTextColor() {
    return this.mMenuView.getItemTextColor();
  }
  
  public int getMaxItemCount() {
    return 5;
  }
  
  @NonNull
  public Menu getMenu() {
    return (Menu)this.mMenu;
  }
  
  @IdRes
  public int getSelectedItemId() {
    return this.mMenuView.getSelectedItemId();
  }
  
  public void inflateMenu(int paramInt) {
    this.mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, (Menu)this.mMenu);
    this.mPresenter.setUpdateSuspended(false);
    this.mPresenter.updateMenuView(true);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    this.mMenu.restorePresenterStates(savedState.menuPresenterState);
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    Bundle bundle = new Bundle();
    savedState.menuPresenterState = bundle;
    this.mMenu.savePresenterStates(bundle);
    return (Parcelable)savedState;
  }
  
  public void setItemBackgroundResource(@DrawableRes int paramInt) {
    this.mMenuView.setItemBackgroundRes(paramInt);
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList) {
    this.mMenuView.setIconTintList(paramColorStateList);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList) {
    this.mMenuView.setItemTextColor(paramColorStateList);
  }
  
  public void setOnNavigationItemReselectedListener(@Nullable OnNavigationItemReselectedListener paramOnNavigationItemReselectedListener) {
    this.mReselectedListener = paramOnNavigationItemReselectedListener;
  }
  
  public void setOnNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener paramOnNavigationItemSelectedListener) {
    this.mSelectedListener = paramOnNavigationItemSelectedListener;
  }
  
  public void setSelectedItemId(@IdRes int paramInt) {
    MenuItem menuItem = this.mMenu.findItem(paramInt);
    if (menuItem != null && !this.mMenu.performItemAction(menuItem, (MenuPresenter)this.mPresenter, 0))
      menuItem.setChecked(true); 
  }
  
  public static interface OnNavigationItemReselectedListener {
    void onNavigationItemReselected(@NonNull MenuItem param1MenuItem);
  }
  
  public static interface OnNavigationItemSelectedListener {
    boolean onNavigationItemSelected(@NonNull MenuItem param1MenuItem);
  }
  
  static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public BottomNavigationView.SavedState createFromParcel(Parcel param2Parcel) {
          return new BottomNavigationView.SavedState(param2Parcel, null);
        }
        
        public BottomNavigationView.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new BottomNavigationView.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public BottomNavigationView.SavedState[] newArray(int param2Int) {
          return new BottomNavigationView.SavedState[param2Int];
        }
      };
    
    Bundle menuPresenterState;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      readFromParcel(param1Parcel, param1ClassLoader);
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    private void readFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      this.menuPresenterState = param1Parcel.readBundle(param1ClassLoader);
    }
    
    public void writeToParcel(@NonNull Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeBundle(this.menuPresenterState);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public BottomNavigationView.SavedState createFromParcel(Parcel param1Parcel) {
      return new BottomNavigationView.SavedState(param1Parcel, null);
    }
    
    public BottomNavigationView.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new BottomNavigationView.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public BottomNavigationView.SavedState[] newArray(int param1Int) {
      return new BottomNavigationView.SavedState[param1Int];
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/BottomNavigationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */