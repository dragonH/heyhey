package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopup;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
  private static final String TAG = "ActionMenuPresenter";
  
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  
  ActionButtonSubmenu mActionButtonPopup;
  
  private int mActionItemWidthLimit;
  
  private boolean mExpandedActionViewsExclusive;
  
  private int mMaxItems;
  
  private boolean mMaxItemsSet;
  
  private int mMinCellSize;
  
  int mOpenSubMenuId;
  
  OverflowMenuButton mOverflowButton;
  
  OverflowPopup mOverflowPopup;
  
  private Drawable mPendingOverflowIcon;
  
  private boolean mPendingOverflowIconSet;
  
  private ActionMenuPopupCallback mPopupCallback;
  
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  
  OpenOverflowRunnable mPostedOpenRunnable;
  
  private boolean mReserveOverflow;
  
  private boolean mReserveOverflowSet;
  
  private View mScrapActionButtonView;
  
  private boolean mStrictWidthLimit;
  
  private int mWidthLimit;
  
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext) {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem) {
    ViewGroup viewGroup = (ViewGroup)this.mMenuView;
    if (viewGroup == null)
      return null; 
    int i = viewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = viewGroup.getChildAt(b);
      if (view instanceof MenuView.ItemView && ((MenuView.ItemView)view).getItemData() == paramMenuItem)
        return view; 
    } 
    return null;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView) {
    paramItemView.initialize(paramMenuItemImpl, 0);
    ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
    ActionMenuItemView actionMenuItemView = (ActionMenuItemView)paramItemView;
    actionMenuItemView.setItemInvoker(actionMenuView);
    if (this.mPopupCallback == null)
      this.mPopupCallback = new ActionMenuPopupCallback(); 
    actionMenuItemView.setPopupCallback(this.mPopupCallback);
  }
  
  public boolean dismissPopupMenus() {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt) {
    return (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton) ? false : super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: aload_1
    //   3: getfield mMenu : Landroid/support/v7/view/menu/MenuBuilder;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 24
    //   11: aload_2
    //   12: invokevirtual getVisibleItems : ()Ljava/util/ArrayList;
    //   15: astore_2
    //   16: aload_2
    //   17: invokevirtual size : ()I
    //   20: istore_3
    //   21: goto -> 28
    //   24: aconst_null
    //   25: astore_2
    //   26: iconst_0
    //   27: istore_3
    //   28: aload_1
    //   29: getfield mMaxItems : I
    //   32: istore #4
    //   34: aload_1
    //   35: getfield mActionItemWidthLimit : I
    //   38: istore #5
    //   40: iconst_0
    //   41: iconst_0
    //   42: invokestatic makeMeasureSpec : (II)I
    //   45: istore #6
    //   47: aload_1
    //   48: getfield mMenuView : Landroid/support/v7/view/menu/MenuView;
    //   51: checkcast android/view/ViewGroup
    //   54: astore #7
    //   56: iconst_0
    //   57: istore #8
    //   59: iconst_0
    //   60: istore #9
    //   62: iconst_0
    //   63: istore #10
    //   65: iconst_0
    //   66: istore #11
    //   68: iload #8
    //   70: iload_3
    //   71: if_icmpge -> 152
    //   74: aload_2
    //   75: iload #8
    //   77: invokevirtual get : (I)Ljava/lang/Object;
    //   80: checkcast android/support/v7/view/menu/MenuItemImpl
    //   83: astore #12
    //   85: aload #12
    //   87: invokevirtual requiresActionButton : ()Z
    //   90: ifeq -> 99
    //   93: iinc #10, 1
    //   96: goto -> 116
    //   99: aload #12
    //   101: invokevirtual requestsActionButton : ()Z
    //   104: ifeq -> 113
    //   107: iinc #11, 1
    //   110: goto -> 116
    //   113: iconst_1
    //   114: istore #9
    //   116: iload #4
    //   118: istore #13
    //   120: aload_1
    //   121: getfield mExpandedActionViewsExclusive : Z
    //   124: ifeq -> 142
    //   127: iload #4
    //   129: istore #13
    //   131: aload #12
    //   133: invokevirtual isActionViewExpanded : ()Z
    //   136: ifeq -> 142
    //   139: iconst_0
    //   140: istore #13
    //   142: iinc #8, 1
    //   145: iload #13
    //   147: istore #4
    //   149: goto -> 68
    //   152: iload #4
    //   154: istore #8
    //   156: aload_1
    //   157: getfield mReserveOverflow : Z
    //   160: ifeq -> 188
    //   163: iload #9
    //   165: ifne -> 182
    //   168: iload #4
    //   170: istore #8
    //   172: iload #11
    //   174: iload #10
    //   176: iadd
    //   177: iload #4
    //   179: if_icmple -> 188
    //   182: iload #4
    //   184: iconst_1
    //   185: isub
    //   186: istore #8
    //   188: iload #8
    //   190: iload #10
    //   192: isub
    //   193: istore #4
    //   195: aload_1
    //   196: getfield mActionButtonGroups : Landroid/util/SparseBooleanArray;
    //   199: astore #12
    //   201: aload #12
    //   203: invokevirtual clear : ()V
    //   206: aload_1
    //   207: getfield mStrictWidthLimit : Z
    //   210: ifeq -> 242
    //   213: aload_1
    //   214: getfield mMinCellSize : I
    //   217: istore #10
    //   219: iload #5
    //   221: iload #10
    //   223: idiv
    //   224: istore #11
    //   226: iload #10
    //   228: iload #5
    //   230: iload #10
    //   232: irem
    //   233: iload #11
    //   235: idiv
    //   236: iadd
    //   237: istore #9
    //   239: goto -> 248
    //   242: iconst_0
    //   243: istore #9
    //   245: iconst_0
    //   246: istore #11
    //   248: iconst_0
    //   249: istore #13
    //   251: iconst_0
    //   252: istore #10
    //   254: iload #5
    //   256: istore #8
    //   258: iload_3
    //   259: istore #5
    //   261: aload_0
    //   262: astore_1
    //   263: iload #13
    //   265: iload #5
    //   267: if_icmpge -> 766
    //   270: aload_2
    //   271: iload #13
    //   273: invokevirtual get : (I)Ljava/lang/Object;
    //   276: checkcast android/support/v7/view/menu/MenuItemImpl
    //   279: astore #14
    //   281: aload #14
    //   283: invokevirtual requiresActionButton : ()Z
    //   286: ifeq -> 409
    //   289: aload_1
    //   290: aload #14
    //   292: aload_1
    //   293: getfield mScrapActionButtonView : Landroid/view/View;
    //   296: aload #7
    //   298: invokevirtual getItemView : (Landroid/support/v7/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   301: astore #15
    //   303: aload_1
    //   304: getfield mScrapActionButtonView : Landroid/view/View;
    //   307: ifnonnull -> 316
    //   310: aload_1
    //   311: aload #15
    //   313: putfield mScrapActionButtonView : Landroid/view/View;
    //   316: aload_1
    //   317: getfield mStrictWidthLimit : Z
    //   320: ifeq -> 343
    //   323: iload #11
    //   325: aload #15
    //   327: iload #9
    //   329: iload #11
    //   331: iload #6
    //   333: iconst_0
    //   334: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   337: isub
    //   338: istore #11
    //   340: goto -> 352
    //   343: aload #15
    //   345: iload #6
    //   347: iload #6
    //   349: invokevirtual measure : (II)V
    //   352: aload #15
    //   354: invokevirtual getMeasuredWidth : ()I
    //   357: istore #16
    //   359: iload #8
    //   361: iload #16
    //   363: isub
    //   364: istore #8
    //   366: iload #10
    //   368: istore_3
    //   369: iload #10
    //   371: ifne -> 377
    //   374: iload #16
    //   376: istore_3
    //   377: aload #14
    //   379: invokevirtual getGroupId : ()I
    //   382: istore #10
    //   384: iload #10
    //   386: ifeq -> 397
    //   389: aload #12
    //   391: iload #10
    //   393: iconst_1
    //   394: invokevirtual put : (IZ)V
    //   397: aload #14
    //   399: iconst_1
    //   400: invokevirtual setIsActionButton : (Z)V
    //   403: iload_3
    //   404: istore #10
    //   406: goto -> 760
    //   409: aload #14
    //   411: invokevirtual requestsActionButton : ()Z
    //   414: ifeq -> 754
    //   417: aload #14
    //   419: invokevirtual getGroupId : ()I
    //   422: istore #17
    //   424: aload #12
    //   426: iload #17
    //   428: invokevirtual get : (I)Z
    //   431: istore #18
    //   433: iload #4
    //   435: ifgt -> 443
    //   438: iload #18
    //   440: ifeq -> 466
    //   443: iload #8
    //   445: ifle -> 466
    //   448: aload_1
    //   449: getfield mStrictWidthLimit : Z
    //   452: ifeq -> 460
    //   455: iload #11
    //   457: ifle -> 466
    //   460: iconst_1
    //   461: istore #19
    //   463: goto -> 469
    //   466: iconst_0
    //   467: istore #19
    //   469: iload #19
    //   471: istore #20
    //   473: iload #19
    //   475: ifeq -> 627
    //   478: aload_1
    //   479: aload #14
    //   481: aload_1
    //   482: getfield mScrapActionButtonView : Landroid/view/View;
    //   485: aload #7
    //   487: invokevirtual getItemView : (Landroid/support/v7/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   490: astore #15
    //   492: aload_1
    //   493: getfield mScrapActionButtonView : Landroid/view/View;
    //   496: ifnonnull -> 505
    //   499: aload_1
    //   500: aload #15
    //   502: putfield mScrapActionButtonView : Landroid/view/View;
    //   505: aload_1
    //   506: getfield mStrictWidthLimit : Z
    //   509: ifeq -> 549
    //   512: aload #15
    //   514: iload #9
    //   516: iload #11
    //   518: iload #6
    //   520: iconst_0
    //   521: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   524: istore #16
    //   526: iload #11
    //   528: iload #16
    //   530: isub
    //   531: istore_3
    //   532: iload_3
    //   533: istore #11
    //   535: iload #16
    //   537: ifne -> 558
    //   540: iconst_0
    //   541: istore #20
    //   543: iload_3
    //   544: istore #11
    //   546: goto -> 558
    //   549: aload #15
    //   551: iload #6
    //   553: iload #6
    //   555: invokevirtual measure : (II)V
    //   558: aload #15
    //   560: invokevirtual getMeasuredWidth : ()I
    //   563: istore #16
    //   565: iload #8
    //   567: iload #16
    //   569: isub
    //   570: istore #8
    //   572: iload #10
    //   574: istore_3
    //   575: iload #10
    //   577: ifne -> 583
    //   580: iload #16
    //   582: istore_3
    //   583: aload_1
    //   584: getfield mStrictWidthLimit : Z
    //   587: ifeq -> 598
    //   590: iload #8
    //   592: iflt -> 611
    //   595: goto -> 605
    //   598: iload #8
    //   600: iload_3
    //   601: iadd
    //   602: ifle -> 611
    //   605: iconst_1
    //   606: istore #10
    //   608: goto -> 614
    //   611: iconst_0
    //   612: istore #10
    //   614: iload #20
    //   616: iload #10
    //   618: iand
    //   619: istore #19
    //   621: iload_3
    //   622: istore #10
    //   624: goto -> 627
    //   627: iload #19
    //   629: ifeq -> 651
    //   632: iload #17
    //   634: ifeq -> 651
    //   637: aload #12
    //   639: iload #17
    //   641: iconst_1
    //   642: invokevirtual put : (IZ)V
    //   645: iload #4
    //   647: istore_3
    //   648: goto -> 731
    //   651: iload #4
    //   653: istore_3
    //   654: iload #18
    //   656: ifeq -> 731
    //   659: aload #12
    //   661: iload #17
    //   663: iconst_0
    //   664: invokevirtual put : (IZ)V
    //   667: iconst_0
    //   668: istore #16
    //   670: iload #4
    //   672: istore_3
    //   673: iload #16
    //   675: iload #13
    //   677: if_icmpge -> 731
    //   680: aload_2
    //   681: iload #16
    //   683: invokevirtual get : (I)Ljava/lang/Object;
    //   686: checkcast android/support/v7/view/menu/MenuItemImpl
    //   689: astore_1
    //   690: iload #4
    //   692: istore_3
    //   693: aload_1
    //   694: invokevirtual getGroupId : ()I
    //   697: iload #17
    //   699: if_icmpne -> 722
    //   702: iload #4
    //   704: istore_3
    //   705: aload_1
    //   706: invokevirtual isActionButton : ()Z
    //   709: ifeq -> 717
    //   712: iload #4
    //   714: iconst_1
    //   715: iadd
    //   716: istore_3
    //   717: aload_1
    //   718: iconst_0
    //   719: invokevirtual setIsActionButton : (Z)V
    //   722: iinc #16, 1
    //   725: iload_3
    //   726: istore #4
    //   728: goto -> 670
    //   731: iload_3
    //   732: istore #4
    //   734: iload #19
    //   736: ifeq -> 744
    //   739: iload_3
    //   740: iconst_1
    //   741: isub
    //   742: istore #4
    //   744: aload #14
    //   746: iload #19
    //   748: invokevirtual setIsActionButton : (Z)V
    //   751: goto -> 406
    //   754: aload #14
    //   756: iconst_0
    //   757: invokevirtual setIsActionButton : (Z)V
    //   760: iinc #13, 1
    //   763: goto -> 261
    //   766: iconst_1
    //   767: ireturn
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup) {
    boolean bool;
    View view = paramMenuItemImpl.getActionView();
    if (view == null || paramMenuItemImpl.hasCollapsibleActionView())
      view = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup); 
    if (paramMenuItemImpl.isActionViewExpanded()) {
      bool = true;
    } else {
      bool = false;
    } 
    view.setVisibility(bool);
    ActionMenuView actionMenuView = (ActionMenuView)paramViewGroup;
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (!actionMenuView.checkLayoutParams(layoutParams))
      view.setLayoutParams((ViewGroup.LayoutParams)actionMenuView.generateLayoutParams(layoutParams)); 
    return view;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup) {
    MenuView menuView2 = this.mMenuView;
    MenuView menuView1 = super.getMenuView(paramViewGroup);
    if (menuView2 != menuView1)
      ((ActionMenuView)menuView1).setPresenter(this); 
    return menuView1;
  }
  
  public Drawable getOverflowIcon() {
    OverflowMenuButton overflowMenuButton = this.mOverflowButton;
    return (overflowMenuButton != null) ? overflowMenuButton.getDrawable() : (this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null);
  }
  
  public boolean hideOverflowMenu() {
    OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
    if (openOverflowRunnable != null) {
      MenuView menuView = this.mMenuView;
      if (menuView != null) {
        ((View)menuView).removeCallbacks(openOverflowRunnable);
        this.mPostedOpenRunnable = null;
        return true;
      } 
    } 
    OverflowPopup overflowPopup = this.mOverflowPopup;
    if (overflowPopup != null) {
      overflowPopup.dismiss();
      return true;
    } 
    return false;
  }
  
  public boolean hideSubMenus() {
    ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
    if (actionButtonSubmenu != null) {
      actionButtonSubmenu.dismiss();
      return true;
    } 
    return false;
  }
  
  public void initForMenu(@NonNull Context paramContext, @Nullable MenuBuilder paramMenuBuilder) {
    super.initForMenu(paramContext, paramMenuBuilder);
    Resources resources = paramContext.getResources();
    ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(paramContext);
    if (!this.mReserveOverflowSet)
      this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton(); 
    if (!this.mWidthLimitSet)
      this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit(); 
    if (!this.mMaxItemsSet)
      this.mMaxItems = actionBarPolicy.getMaxActionButtons(); 
    int i = this.mWidthLimit;
    if (this.mReserveOverflow) {
      if (this.mOverflowButton == null) {
        OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
        this.mOverflowButton = overflowMenuButton;
        if (this.mPendingOverflowIconSet) {
          overflowMenuButton.setImageDrawable(this.mPendingOverflowIcon);
          this.mPendingOverflowIcon = null;
          this.mPendingOverflowIconSet = false;
        } 
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      } 
      i -= this.mOverflowButton.getMeasuredWidth();
    } else {
      this.mOverflowButton = null;
    } 
    this.mActionItemWidthLimit = i;
    this.mMinCellSize = (int)((resources.getDisplayMetrics()).density * 56.0F);
    this.mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending() {
    return (this.mPostedOpenRunnable != null || isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing() {
    boolean bool;
    OverflowPopup overflowPopup = this.mOverflowPopup;
    if (overflowPopup != null && overflowPopup.isShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOverflowReserved() {
    return this.mReserveOverflow;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (!this.mMaxItemsSet)
      this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons(); 
    MenuBuilder menuBuilder = this.mMenu;
    if (menuBuilder != null)
      menuBuilder.onItemsChanged(true); 
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState))
      return; 
    int i = ((SavedState)paramParcelable).openSubMenuId;
    if (i > 0) {
      MenuItem menuItem = this.mMenu.findItem(i);
      if (menuItem != null)
        onSubMenuSelected((SubMenuBuilder)menuItem.getSubMenu()); 
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState();
    savedState.openSubMenuId = this.mOpenSubMenuId;
    return savedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder) {
    boolean bool = paramSubMenuBuilder.hasVisibleItems();
    boolean bool1 = false;
    if (!bool)
      return false; 
    SubMenuBuilder subMenuBuilder;
    for (subMenuBuilder = paramSubMenuBuilder; subMenuBuilder.getParentMenu() != this.mMenu; subMenuBuilder = (SubMenuBuilder)subMenuBuilder.getParentMenu());
    View view = findViewForItem(subMenuBuilder.getItem());
    if (view == null)
      return false; 
    this.mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    int i = paramSubMenuBuilder.size();
    byte b = 0;
    while (true) {
      bool = bool1;
      if (b < i) {
        MenuItem menuItem = paramSubMenuBuilder.getItem(b);
        if (menuItem.isVisible() && menuItem.getIcon() != null) {
          bool = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder, view);
    this.mActionButtonPopup = actionButtonSubmenu;
    actionButtonSubmenu.setForceShowIcon(bool);
    this.mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean) {
    if (paramBoolean) {
      super.onSubMenuSelected(null);
    } else {
      MenuBuilder menuBuilder = this.mMenu;
      if (menuBuilder != null)
        menuBuilder.close(false); 
    } 
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setItemLimit(int paramInt) {
    this.mMaxItems = paramInt;
    this.mMaxItemsSet = true;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView) {
    this.mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(this.mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable) {
    OverflowMenuButton overflowMenuButton = this.mOverflowButton;
    if (overflowMenuButton != null) {
      overflowMenuButton.setImageDrawable(paramDrawable);
    } else {
      this.mPendingOverflowIconSet = true;
      this.mPendingOverflowIcon = paramDrawable;
    } 
  }
  
  public void setReserveOverflow(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean) {
    this.mWidthLimit = paramInt;
    this.mStrictWidthLimit = paramBoolean;
    this.mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl) {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu() {
    if (this.mReserveOverflow && !isOverflowMenuShowing()) {
      MenuBuilder menuBuilder = this.mMenu;
      if (menuBuilder != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !menuBuilder.getNonActionItems().isEmpty()) {
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, (View)this.mOverflowButton, true));
        this.mPostedOpenRunnable = openOverflowRunnable;
        ((View)this.mMenuView).post(openOverflowRunnable);
        super.onSubMenuSelected(null);
        return true;
      } 
    } 
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean) {
    super.updateMenuView(paramBoolean);
    ((View)this.mMenuView).requestLayout();
    MenuBuilder<MenuItemImpl> menuBuilder = this.mMenu;
    byte b = 0;
    if (menuBuilder != null) {
      ArrayList<MenuItemImpl> arrayList = menuBuilder.getActionItems();
      int j = arrayList.size();
      for (byte b1 = 0; b1 < j; b1++) {
        ActionProvider actionProvider = ((MenuItemImpl)arrayList.get(b1)).getSupportActionProvider();
        if (actionProvider != null)
          actionProvider.setSubUiVisibilityListener(this); 
      } 
    } 
    menuBuilder = this.mMenu;
    if (menuBuilder != null) {
      ArrayList arrayList = menuBuilder.getNonActionItems();
    } else {
      menuBuilder = null;
    } 
    int i = b;
    if (this.mReserveOverflow) {
      i = b;
      if (menuBuilder != null) {
        int j = menuBuilder.size();
        if (j == 1) {
          i = ((MenuItemImpl)menuBuilder.get(0)).isActionViewExpanded() ^ true;
        } else {
          i = b;
          if (j > 0)
            i = 1; 
        } 
      } 
    } 
    if (i != 0) {
      if (this.mOverflowButton == null)
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext); 
      ViewGroup viewGroup = (ViewGroup)this.mOverflowButton.getParent();
      if (viewGroup != this.mMenuView) {
        if (viewGroup != null)
          viewGroup.removeView((View)this.mOverflowButton); 
        viewGroup = (ActionMenuView)this.mMenuView;
        viewGroup.addView((View)this.mOverflowButton, (ViewGroup.LayoutParams)viewGroup.generateOverflowButtonLayoutParams());
      } 
    } else {
      OverflowMenuButton overflowMenuButton = this.mOverflowButton;
      if (overflowMenuButton != null) {
        ViewParent viewParent = overflowMenuButton.getParent();
        MenuView menuView = this.mMenuView;
        if (viewParent == menuView)
          ((ViewGroup)menuView).removeView((View)this.mOverflowButton); 
      } 
    } 
    ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
  }
  
  private class ActionButtonSubmenu extends MenuPopupHelper {
    public ActionButtonSubmenu(Context param1Context, SubMenuBuilder param1SubMenuBuilder, View param1View) {
      super(param1Context, (MenuBuilder)param1SubMenuBuilder, param1View, false, R.attr.actionOverflowMenuStyle);
      if (!((MenuItemImpl)param1SubMenuBuilder.getItem()).isActionButton()) {
        View view;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton2 = ActionMenuPresenter.this.mOverflowButton;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton1 = overflowMenuButton2;
        if (overflowMenuButton2 == null)
          view = (View)ActionMenuPresenter.this.mMenuView; 
        setAnchorView(view);
      } 
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
      actionMenuPresenter.mActionButtonPopup = null;
      actionMenuPresenter.mOpenSubMenuId = 0;
      super.onDismiss();
    }
  }
  
  private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
    public ShowableListMenu getPopup() {
      ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.mActionButtonPopup;
      if (actionButtonSubmenu != null) {
        MenuPopup menuPopup = actionButtonSubmenu.getPopup();
      } else {
        actionButtonSubmenu = null;
      } 
      return (ShowableListMenu)actionButtonSubmenu;
    }
  }
  
  private class OpenOverflowRunnable implements Runnable {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup param1OverflowPopup) {
      this.mPopup = param1OverflowPopup;
    }
    
    public void run() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.changeMenuMode(); 
      View view = (View)ActionMenuPresenter.this.mMenuView;
      if (view != null && view.getWindowToken() != null && this.mPopup.tryShow())
        ActionMenuPresenter.this.mOverflowPopup = this.mPopup; 
      ActionMenuPresenter.this.mPostedOpenRunnable = null;
    }
  }
  
  private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
    private final float[] mTempPts = new float[2];
    
    public OverflowMenuButton(Context param1Context) {
      super(param1Context, null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      TooltipCompat.setTooltipText((View)this, getContentDescription());
      setOnTouchListener(new ForwardingListener((View)this) {
            public ShowableListMenu getPopup() {
              ActionMenuPresenter.OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
              return (ShowableListMenu)((overflowPopup == null) ? null : overflowPopup.getPopup());
            }
            
            public boolean onForwardingStarted() {
              ActionMenuPresenter.this.showOverflowMenu();
              return true;
            }
            
            public boolean onForwardingStopped() {
              ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
              if (actionMenuPresenter.mPostedOpenRunnable != null)
                return false; 
              actionMenuPresenter.hideOverflowMenu();
              return true;
            }
          });
    }
    
    public boolean needsDividerAfter() {
      return false;
    }
    
    public boolean needsDividerBefore() {
      return false;
    }
    
    public boolean performClick() {
      if (super.performClick())
        return true; 
      playSoundEffect(0);
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      boolean bool = super.setFrame(param1Int1, param1Int2, param1Int3, param1Int4);
      Drawable drawable1 = getDrawable();
      Drawable drawable2 = getBackground();
      if (drawable1 != null && drawable2 != null) {
        int i = getWidth();
        param1Int4 = getHeight();
        param1Int1 = Math.max(i, param1Int4) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        param1Int2 = getPaddingTop();
        param1Int3 = getPaddingBottom();
        k = (i + j - k) / 2;
        param1Int2 = (param1Int4 + param1Int2 - param1Int3) / 2;
        DrawableCompat.setHotspotBounds(drawable2, k - param1Int1, param1Int2 - param1Int1, k + param1Int1, param1Int2 + param1Int1);
      } 
      return bool;
    }
  }
  
  class null extends ForwardingListener {
    null(View param1View) {
      super(param1View);
    }
    
    public ShowableListMenu getPopup() {
      ActionMenuPresenter.OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
      return (ShowableListMenu)((overflowPopup == null) ? null : overflowPopup.getPopup());
    }
    
    public boolean onForwardingStarted() {
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    public boolean onForwardingStopped() {
      ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
      if (actionMenuPresenter.mPostedOpenRunnable != null)
        return false; 
      actionMenuPresenter.hideOverflowMenu();
      return true;
    }
  }
  
  private class OverflowPopup extends MenuPopupHelper {
    public OverflowPopup(Context param1Context, MenuBuilder param1MenuBuilder, View param1View, boolean param1Boolean) {
      super(param1Context, param1MenuBuilder, param1View, param1Boolean, R.attr.actionOverflowMenuStyle);
      setGravity(8388613);
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.close(); 
      ActionMenuPresenter.this.mOverflowPopup = null;
      super.onDismiss();
    }
  }
  
  private class PopupPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      if (param1MenuBuilder instanceof SubMenuBuilder)
        param1MenuBuilder.getRootMenu().close(false); 
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        callback.onCloseMenu(param1MenuBuilder, param1Boolean); 
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      boolean bool = false;
      if (param1MenuBuilder == null)
        return false; 
      ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)param1MenuBuilder).getItem().getItemId();
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        bool = callback.onOpenSubMenu(param1MenuBuilder); 
      return bool;
    }
  }
  
  private static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
        public ActionMenuPresenter.SavedState createFromParcel(Parcel param2Parcel) {
          return new ActionMenuPresenter.SavedState(param2Parcel);
        }
        
        public ActionMenuPresenter.SavedState[] newArray(int param2Int) {
          return new ActionMenuPresenter.SavedState[param2Int];
        }
      };
    
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel param1Parcel) {
      this.openSubMenuId = param1Parcel.readInt();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.openSubMenuId);
    }
  }
  
  static final class null implements Parcelable.Creator<SavedState> {
    public ActionMenuPresenter.SavedState createFromParcel(Parcel param1Parcel) {
      return new ActionMenuPresenter.SavedState(param1Parcel);
    }
    
    public ActionMenuPresenter.SavedState[] newArray(int param1Int) {
      return new ActionMenuPresenter.SavedState[param1Int];
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/ActionMenuPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */