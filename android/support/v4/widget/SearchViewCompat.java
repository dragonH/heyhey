package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;

@Deprecated
public final class SearchViewCompat {
  private SearchViewCompat(Context paramContext) {}
  
  private static void checkIfLegalArg(View paramView) {
    if (paramView != null) {
      if (paramView instanceof SearchView)
        return; 
      throw new IllegalArgumentException("searchView must be an instance of android.widget.SearchView");
    } 
    throw new IllegalArgumentException("searchView must be non-null");
  }
  
  @Deprecated
  public static CharSequence getQuery(View paramView) {
    checkIfLegalArg(paramView);
    return ((SearchView)paramView).getQuery();
  }
  
  @Deprecated
  public static boolean isIconified(View paramView) {
    checkIfLegalArg(paramView);
    return ((SearchView)paramView).isIconified();
  }
  
  @Deprecated
  public static boolean isQueryRefinementEnabled(View paramView) {
    checkIfLegalArg(paramView);
    return ((SearchView)paramView).isQueryRefinementEnabled();
  }
  
  @Deprecated
  public static boolean isSubmitButtonEnabled(View paramView) {
    checkIfLegalArg(paramView);
    return ((SearchView)paramView).isSubmitButtonEnabled();
  }
  
  private static SearchView.OnCloseListener newOnCloseListener(final OnCloseListener listener) {
    return new SearchView.OnCloseListener() {
        public boolean onClose() {
          return listener.onClose();
        }
      };
  }
  
  private static SearchView.OnQueryTextListener newOnQueryTextListener(final OnQueryTextListener listener) {
    return new SearchView.OnQueryTextListener() {
        public boolean onQueryTextChange(String param1String) {
          return listener.onQueryTextChange(param1String);
        }
        
        public boolean onQueryTextSubmit(String param1String) {
          return listener.onQueryTextSubmit(param1String);
        }
      };
  }
  
  @Deprecated
  public static View newSearchView(Context paramContext) {
    return (View)new SearchView(paramContext);
  }
  
  @Deprecated
  public static void setIconified(View paramView, boolean paramBoolean) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setIconified(paramBoolean);
  }
  
  @Deprecated
  public static void setImeOptions(View paramView, int paramInt) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setImeOptions(paramInt);
  }
  
  @Deprecated
  public static void setInputType(View paramView, int paramInt) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setInputType(paramInt);
  }
  
  @Deprecated
  public static void setMaxWidth(View paramView, int paramInt) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setMaxWidth(paramInt);
  }
  
  @Deprecated
  public static void setOnCloseListener(View paramView, OnCloseListener paramOnCloseListener) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setOnCloseListener(newOnCloseListener(paramOnCloseListener));
  }
  
  @Deprecated
  public static void setOnQueryTextListener(View paramView, OnQueryTextListener paramOnQueryTextListener) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setOnQueryTextListener(newOnQueryTextListener(paramOnQueryTextListener));
  }
  
  @Deprecated
  public static void setQuery(View paramView, CharSequence paramCharSequence, boolean paramBoolean) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setQuery(paramCharSequence, paramBoolean);
  }
  
  @Deprecated
  public static void setQueryHint(View paramView, CharSequence paramCharSequence) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setQueryHint(paramCharSequence);
  }
  
  @Deprecated
  public static void setQueryRefinementEnabled(View paramView, boolean paramBoolean) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setQueryRefinementEnabled(paramBoolean);
  }
  
  @Deprecated
  public static void setSearchableInfo(View paramView, ComponentName paramComponentName) {
    checkIfLegalArg(paramView);
    SearchManager searchManager = (SearchManager)paramView.getContext().getSystemService("search");
    ((SearchView)paramView).setSearchableInfo(searchManager.getSearchableInfo(paramComponentName));
  }
  
  @Deprecated
  public static void setSubmitButtonEnabled(View paramView, boolean paramBoolean) {
    checkIfLegalArg(paramView);
    ((SearchView)paramView).setSubmitButtonEnabled(paramBoolean);
  }
  
  @Deprecated
  public static interface OnCloseListener {
    boolean onClose();
  }
  
  @Deprecated
  public static abstract class OnCloseListenerCompat implements OnCloseListener {
    public boolean onClose() {
      return false;
    }
  }
  
  @Deprecated
  public static interface OnQueryTextListener {
    boolean onQueryTextChange(String param1String);
    
    boolean onQueryTextSubmit(String param1String);
  }
  
  @Deprecated
  public static abstract class OnQueryTextListenerCompat implements OnQueryTextListener {
    public boolean onQueryTextChange(String param1String) {
      return false;
    }
    
    public boolean onQueryTextSubmit(String param1String) {
      return false;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/widget/SearchViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */