package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
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

public class NavigationView extends ScrimInsetsFrameLayout {
  private static final int[] CHECKED_STATE_SET = new int[] { 16842912 };
  
  private static final int[] DISABLED_STATE_SET = new int[] { -16842910 };
  
  private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
  
  OnNavigationItemSelectedListener mListener;
  
  private int mMaxWidth;
  
  private final NavigationMenu mMenu;
  
  private MenuInflater mMenuInflater;
  
  private final NavigationMenuPresenter mPresenter;
  
  public NavigationView(Context paramContext) {
    this(paramContext, null);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    ColorStateList colorStateList1;
    ColorStateList colorStateList2;
    boolean bool;
    NavigationMenuPresenter navigationMenuPresenter = new NavigationMenuPresenter();
    this.mPresenter = navigationMenuPresenter;
    ThemeUtils.checkAppCompatTheme(paramContext);
    NavigationMenu navigationMenu = new NavigationMenu(paramContext);
    this.mMenu = navigationMenu;
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.NavigationView, paramInt, R.style.Widget_Design_NavigationView);
    ViewCompat.setBackground((View)this, tintTypedArray.getDrawable(R.styleable.NavigationView_android_background));
    paramInt = R.styleable.NavigationView_elevation;
    if (tintTypedArray.hasValue(paramInt))
      ViewCompat.setElevation((View)this, tintTypedArray.getDimensionPixelSize(paramInt, 0)); 
    ViewCompat.setFitsSystemWindows((View)this, tintTypedArray.getBoolean(R.styleable.NavigationView_android_fitsSystemWindows, false));
    this.mMaxWidth = tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_android_maxWidth, 0);
    paramInt = R.styleable.NavigationView_itemIconTint;
    if (tintTypedArray.hasValue(paramInt)) {
      colorStateList2 = tintTypedArray.getColorStateList(paramInt);
    } else {
      colorStateList2 = createDefaultColorStateList(16842808);
    } 
    paramInt = R.styleable.NavigationView_itemTextAppearance;
    if (tintTypedArray.hasValue(paramInt)) {
      paramInt = tintTypedArray.getResourceId(paramInt, 0);
      bool = true;
    } else {
      paramInt = 0;
      bool = false;
    } 
    paramAttributeSet = null;
    int i = R.styleable.NavigationView_itemTextColor;
    if (tintTypedArray.hasValue(i))
      colorStateList1 = tintTypedArray.getColorStateList(i); 
    ColorStateList colorStateList3 = colorStateList1;
    if (!bool) {
      colorStateList3 = colorStateList1;
      if (colorStateList1 == null)
        colorStateList3 = createDefaultColorStateList(16842806); 
    } 
    Drawable drawable = tintTypedArray.getDrawable(R.styleable.NavigationView_itemBackground);
    navigationMenu.setCallback(new MenuBuilder.Callback() {
          public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
            boolean bool;
            NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = NavigationView.this.mListener;
            if (onNavigationItemSelectedListener != null && onNavigationItemSelectedListener.onNavigationItemSelected(param1MenuItem)) {
              bool = true;
            } else {
              bool = false;
            } 
            return bool;
          }
          
          public void onMenuModeChange(MenuBuilder param1MenuBuilder) {}
        });
    navigationMenuPresenter.setId(1);
    navigationMenuPresenter.initForMenu(paramContext, (MenuBuilder)navigationMenu);
    navigationMenuPresenter.setItemIconTintList(colorStateList2);
    if (bool)
      navigationMenuPresenter.setItemTextAppearance(paramInt); 
    navigationMenuPresenter.setItemTextColor(colorStateList3);
    navigationMenuPresenter.setItemBackground(drawable);
    navigationMenu.addMenuPresenter((MenuPresenter)navigationMenuPresenter);
    addView((View)navigationMenuPresenter.getMenuView((ViewGroup)this));
    paramInt = R.styleable.NavigationView_menu;
    if (tintTypedArray.hasValue(paramInt))
      inflateMenu(tintTypedArray.getResourceId(paramInt, 0)); 
    paramInt = R.styleable.NavigationView_headerLayout;
    if (tintTypedArray.hasValue(paramInt))
      inflateHeaderView(tintTypedArray.getResourceId(paramInt, 0)); 
    tintTypedArray.recycle();
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt) {
    TypedValue typedValue = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, typedValue, true))
      return null; 
    ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
    if (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true))
      return null; 
    paramInt = typedValue.data;
    int i = colorStateList.getDefaultColor();
    int[] arrayOfInt2 = DISABLED_STATE_SET;
    int[] arrayOfInt3 = CHECKED_STATE_SET;
    int[] arrayOfInt1 = FrameLayout.EMPTY_STATE_SET;
    int j = colorStateList.getColorForState(arrayOfInt2, i);
    return new ColorStateList(new int[][] { arrayOfInt2, arrayOfInt3, arrayOfInt1 }, new int[] { j, paramInt, i });
  }
  
  private MenuInflater getMenuInflater() {
    if (this.mMenuInflater == null)
      this.mMenuInflater = (MenuInflater)new SupportMenuInflater(getContext()); 
    return this.mMenuInflater;
  }
  
  public void addHeaderView(@NonNull View paramView) {
    this.mPresenter.addHeaderView(paramView);
  }
  
  public int getHeaderCount() {
    return this.mPresenter.getHeaderCount();
  }
  
  public View getHeaderView(int paramInt) {
    return this.mPresenter.getHeaderView(paramInt);
  }
  
  @Nullable
  public Drawable getItemBackground() {
    return this.mPresenter.getItemBackground();
  }
  
  @Nullable
  public ColorStateList getItemIconTintList() {
    return this.mPresenter.getItemTintList();
  }
  
  @Nullable
  public ColorStateList getItemTextColor() {
    return this.mPresenter.getItemTextColor();
  }
  
  public Menu getMenu() {
    return (Menu)this.mMenu;
  }
  
  public View inflateHeaderView(@LayoutRes int paramInt) {
    return this.mPresenter.inflateHeaderView(paramInt);
  }
  
  public void inflateMenu(int paramInt) {
    this.mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, (Menu)this.mMenu);
    this.mPresenter.setUpdateSuspended(false);
    this.mPresenter.updateMenuView(false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  protected void onInsetsChanged(WindowInsetsCompat paramWindowInsetsCompat) {
    this.mPresenter.dispatchApplyWindowInsets(paramWindowInsetsCompat);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getMode(paramInt1);
    if (i != Integer.MIN_VALUE) {
      if (i == 0)
        paramInt1 = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824); 
    } else {
      paramInt1 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(paramInt1), this.mMaxWidth), 1073741824);
    } 
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    this.mMenu.restorePresenterStates(savedState.menuState);
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    Bundle bundle = new Bundle();
    savedState.menuState = bundle;
    this.mMenu.savePresenterStates(bundle);
    return (Parcelable)savedState;
  }
  
  public void removeHeaderView(@NonNull View paramView) {
    this.mPresenter.removeHeaderView(paramView);
  }
  
  public void setCheckedItem(@IdRes int paramInt) {
    MenuItem menuItem = this.mMenu.findItem(paramInt);
    if (menuItem != null)
      this.mPresenter.setCheckedItem((MenuItemImpl)menuItem); 
  }
  
  public void setItemBackground(@Nullable Drawable paramDrawable) {
    this.mPresenter.setItemBackground(paramDrawable);
  }
  
  public void setItemBackgroundResource(@DrawableRes int paramInt) {
    setItemBackground(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList) {
    this.mPresenter.setItemIconTintList(paramColorStateList);
  }
  
  public void setItemTextAppearance(@StyleRes int paramInt) {
    this.mPresenter.setItemTextAppearance(paramInt);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList) {
    this.mPresenter.setItemTextColor(paramColorStateList);
  }
  
  public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener paramOnNavigationItemSelectedListener) {
    this.mListener = paramOnNavigationItemSelectedListener;
  }
  
  public static interface OnNavigationItemSelectedListener {
    boolean onNavigationItemSelected(@NonNull MenuItem param1MenuItem);
  }
  
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public NavigationView.SavedState createFromParcel(Parcel param2Parcel) {
          return new NavigationView.SavedState(param2Parcel, null);
        }
        
        public NavigationView.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new NavigationView.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public NavigationView.SavedState[] newArray(int param2Int) {
          return new NavigationView.SavedState[param2Int];
        }
      };
    
    public Bundle menuState;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.menuState = param1Parcel.readBundle(param1ClassLoader);
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(@NonNull Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeBundle(this.menuState);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public NavigationView.SavedState createFromParcel(Parcel param1Parcel) {
      return new NavigationView.SavedState(param1Parcel, null);
    }
    
    public NavigationView.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new NavigationView.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public NavigationView.SavedState[] newArray(int param1Int) {
      return new NavigationView.SavedState[param1Int];
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/NavigationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */