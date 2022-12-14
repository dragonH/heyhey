package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class MenuWrapperFactory {
  public static Menu wrapSupportMenu(Context paramContext, SupportMenu paramSupportMenu) {
    return new MenuWrapperICS(paramContext, paramSupportMenu);
  }
  
  public static MenuItem wrapSupportMenuItem(Context paramContext, SupportMenuItem paramSupportMenuItem) {
    return new MenuItemWrapperJB(paramContext, paramSupportMenuItem);
  }
  
  public static SubMenu wrapSupportSubMenu(Context paramContext, SupportSubMenu paramSupportSubMenu) {
    return new SubMenuWrapperICS(paramContext, paramSupportSubMenu);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/view/menu/MenuWrapperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */