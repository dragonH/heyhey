package android.support.v7.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteProviderDescriptor {
  private static final String KEY_ROUTES = "routes";
  
  private final Bundle mBundle;
  
  private List<MediaRouteDescriptor> mRoutes;
  
  private MediaRouteProviderDescriptor(Bundle paramBundle, List<MediaRouteDescriptor> paramList) {
    this.mBundle = paramBundle;
    this.mRoutes = paramList;
  }
  
  private void ensureRoutes() {
    if (this.mRoutes == null) {
      ArrayList<Bundle> arrayList = this.mBundle.getParcelableArrayList("routes");
      if (arrayList == null || arrayList.isEmpty()) {
        this.mRoutes = Collections.emptyList();
        return;
      } 
      int i = arrayList.size();
      this.mRoutes = new ArrayList<MediaRouteDescriptor>(i);
      for (byte b = 0; b < i; b++)
        this.mRoutes.add(MediaRouteDescriptor.fromBundle(arrayList.get(b))); 
    } 
  }
  
  public static MediaRouteProviderDescriptor fromBundle(Bundle paramBundle) {
    MediaRouteProviderDescriptor mediaRouteProviderDescriptor = null;
    if (paramBundle != null)
      mediaRouteProviderDescriptor = new MediaRouteProviderDescriptor(paramBundle, null); 
    return mediaRouteProviderDescriptor;
  }
  
  public Bundle asBundle() {
    return this.mBundle;
  }
  
  public List<MediaRouteDescriptor> getRoutes() {
    ensureRoutes();
    return this.mRoutes;
  }
  
  public boolean isValid() {
    ensureRoutes();
    int i = this.mRoutes.size();
    for (byte b = 0; b < i; b++) {
      MediaRouteDescriptor mediaRouteDescriptor = this.mRoutes.get(b);
      if (mediaRouteDescriptor == null || !mediaRouteDescriptor.isValid())
        return false; 
    } 
    return true;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MediaRouteProviderDescriptor{ ");
    stringBuilder.append("routes=");
    stringBuilder.append(Arrays.toString(getRoutes().toArray()));
    stringBuilder.append(", isValid=");
    stringBuilder.append(isValid());
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public static final class Builder {
    private final Bundle mBundle;
    
    private ArrayList<MediaRouteDescriptor> mRoutes;
    
    public Builder() {
      this.mBundle = new Bundle();
    }
    
    public Builder(MediaRouteProviderDescriptor param1MediaRouteProviderDescriptor) {
      if (param1MediaRouteProviderDescriptor != null) {
        this.mBundle = new Bundle(param1MediaRouteProviderDescriptor.mBundle);
        param1MediaRouteProviderDescriptor.ensureRoutes();
        if (!param1MediaRouteProviderDescriptor.mRoutes.isEmpty())
          this.mRoutes = new ArrayList<MediaRouteDescriptor>(param1MediaRouteProviderDescriptor.mRoutes); 
        return;
      } 
      throw new IllegalArgumentException("descriptor must not be null");
    }
    
    public Builder addRoute(MediaRouteDescriptor param1MediaRouteDescriptor) {
      if (param1MediaRouteDescriptor != null) {
        ArrayList<MediaRouteDescriptor> arrayList = this.mRoutes;
        if (arrayList == null) {
          this.mRoutes = new ArrayList<MediaRouteDescriptor>();
        } else if (arrayList.contains(param1MediaRouteDescriptor)) {
          throw new IllegalArgumentException("route descriptor already added");
        } 
        this.mRoutes.add(param1MediaRouteDescriptor);
        return this;
      } 
      throw new IllegalArgumentException("route must not be null");
    }
    
    public Builder addRoutes(Collection<MediaRouteDescriptor> param1Collection) {
      if (param1Collection != null) {
        if (!param1Collection.isEmpty()) {
          Iterator<MediaRouteDescriptor> iterator = param1Collection.iterator();
          while (iterator.hasNext())
            addRoute(iterator.next()); 
        } 
        return this;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException("routes must not be null");
      throw illegalArgumentException;
    }
    
    public MediaRouteProviderDescriptor build() {
      ArrayList<MediaRouteDescriptor> arrayList = this.mRoutes;
      if (arrayList != null) {
        int i = arrayList.size();
        arrayList = new ArrayList<MediaRouteDescriptor>(i);
        for (byte b = 0; b < i; b++)
          arrayList.add(((MediaRouteDescriptor)this.mRoutes.get(b)).asBundle()); 
        this.mBundle.putParcelableArrayList("routes", arrayList);
      } 
      return new MediaRouteProviderDescriptor(this.mBundle, this.mRoutes);
    }
    
    Builder setRoutes(Collection<MediaRouteDescriptor> param1Collection) {
      if (param1Collection == null || param1Collection.isEmpty()) {
        this.mRoutes = null;
        this.mBundle.remove("routes");
        return this;
      } 
      this.mRoutes = new ArrayList<MediaRouteDescriptor>(param1Collection);
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/media/MediaRouteProviderDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */