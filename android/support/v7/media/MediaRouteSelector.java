package android.support.v7.media;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteSelector {
  public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), null);
  
  static final String KEY_CONTROL_CATEGORIES = "controlCategories";
  
  private final Bundle mBundle;
  
  List<String> mControlCategories;
  
  MediaRouteSelector(Bundle paramBundle, List<String> paramList) {
    this.mBundle = paramBundle;
    this.mControlCategories = paramList;
  }
  
  public static MediaRouteSelector fromBundle(@Nullable Bundle paramBundle) {
    MediaRouteSelector mediaRouteSelector = null;
    if (paramBundle != null)
      mediaRouteSelector = new MediaRouteSelector(paramBundle, null); 
    return mediaRouteSelector;
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public boolean contains(MediaRouteSelector paramMediaRouteSelector) {
    if (paramMediaRouteSelector != null) {
      ensureControlCategories();
      paramMediaRouteSelector.ensureControlCategories();
      return this.mControlCategories.containsAll(paramMediaRouteSelector.mControlCategories);
    } 
    return false;
  }
  
  void ensureControlCategories() {
    if (this.mControlCategories == null) {
      ArrayList<String> arrayList = this.mBundle.getStringArrayList("controlCategories");
      this.mControlCategories = arrayList;
      if (arrayList == null || arrayList.isEmpty())
        this.mControlCategories = Collections.emptyList(); 
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof MediaRouteSelector) {
      paramObject = paramObject;
      ensureControlCategories();
      paramObject.ensureControlCategories();
      return this.mControlCategories.equals(((MediaRouteSelector)paramObject).mControlCategories);
    } 
    return false;
  }
  
  public List<String> getControlCategories() {
    ensureControlCategories();
    return this.mControlCategories;
  }
  
  public boolean hasControlCategory(String paramString) {
    if (paramString != null) {
      ensureControlCategories();
      int i = this.mControlCategories.size();
      for (byte b = 0; b < i; b++) {
        if (((String)this.mControlCategories.get(b)).equals(paramString))
          return true; 
      } 
    } 
    return false;
  }
  
  public int hashCode() {
    ensureControlCategories();
    return this.mControlCategories.hashCode();
  }
  
  public boolean isEmpty() {
    ensureControlCategories();
    return this.mControlCategories.isEmpty();
  }
  
  public boolean isValid() {
    ensureControlCategories();
    return !this.mControlCategories.contains(null);
  }
  
  public boolean matchesControlFilters(List<IntentFilter> paramList) {
    if (paramList != null) {
      ensureControlCategories();
      int i = this.mControlCategories.size();
      if (i != 0) {
        int j = paramList.size();
        for (byte b = 0; b < j; b++) {
          IntentFilter intentFilter = paramList.get(b);
          if (intentFilter != null)
            for (byte b1 = 0; b1 < i; b1++) {
              if (intentFilter.hasCategory(this.mControlCategories.get(b1)))
                return true; 
            }  
        } 
      } 
    } 
    return false;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MediaRouteSelector{ ");
    stringBuilder.append("controlCategories=");
    stringBuilder.append(Arrays.toString(getControlCategories().toArray()));
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public static final class Builder {
    private ArrayList<String> mControlCategories;
    
    public Builder() {}
    
    public Builder(@NonNull MediaRouteSelector param1MediaRouteSelector) {
      if (param1MediaRouteSelector != null) {
        param1MediaRouteSelector.ensureControlCategories();
        if (!param1MediaRouteSelector.mControlCategories.isEmpty())
          this.mControlCategories = new ArrayList<String>(param1MediaRouteSelector.mControlCategories); 
        return;
      } 
      throw new IllegalArgumentException("selector must not be null");
    }
    
    @NonNull
    public Builder addControlCategories(@NonNull Collection<String> param1Collection) {
      if (param1Collection != null) {
        if (!param1Collection.isEmpty()) {
          Iterator<String> iterator = param1Collection.iterator();
          while (iterator.hasNext())
            addControlCategory(iterator.next()); 
        } 
        return this;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("categories must not be null");
      throw illegalArgumentException;
    }
    
    @NonNull
    public Builder addControlCategory(@NonNull String param1String) {
      if (param1String != null) {
        if (this.mControlCategories == null)
          this.mControlCategories = new ArrayList<String>(); 
        if (!this.mControlCategories.contains(param1String))
          this.mControlCategories.add(param1String); 
        return this;
      } 
      throw new IllegalArgumentException("category must not be null");
    }
    
    @NonNull
    public Builder addSelector(@NonNull MediaRouteSelector param1MediaRouteSelector) {
      if (param1MediaRouteSelector != null) {
        addControlCategories(param1MediaRouteSelector.getControlCategories());
        return this;
      } 
      throw new IllegalArgumentException("selector must not be null");
    }
    
    @NonNull
    public MediaRouteSelector build() {
      if (this.mControlCategories == null)
        return MediaRouteSelector.EMPTY; 
      Bundle bundle = new Bundle();
      bundle.putStringArrayList("controlCategories", this.mControlCategories);
      return new MediaRouteSelector(bundle, this.mControlCategories);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */