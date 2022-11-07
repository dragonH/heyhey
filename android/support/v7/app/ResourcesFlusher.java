package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
  private static final String TAG = "ResourcesFlusher";
  
  private static Field sDrawableCacheField;
  
  private static boolean sDrawableCacheFieldFetched;
  
  private static Field sResourcesImplField;
  
  private static boolean sResourcesImplFieldFetched;
  
  private static Class sThemedResourceCacheClazz;
  
  private static boolean sThemedResourceCacheClazzFetched;
  
  private static Field sThemedResourceCache_mUnthemedEntriesField;
  
  private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
  
  static boolean flush(@NonNull Resources paramResources) {
    int i = Build.VERSION.SDK_INT;
    return (i >= 24) ? flushNougats(paramResources) : ((i >= 23) ? flushMarshmallows(paramResources) : ((i >= 21) ? flushLollipops(paramResources) : false));
  }
  
  @RequiresApi(21)
  private static boolean flushLollipops(@NonNull Resources paramResources) {
    if (!sDrawableCacheFieldFetched) {
      try {
        Field field1 = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField = field1;
        field1.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException);
      } 
      sDrawableCacheFieldFetched = true;
    } 
    Field field = sDrawableCacheField;
    if (field != null) {
      IllegalAccessException illegalAccessException2 = null;
      try {
        Map map = (Map)field.get(paramResources);
      } catch (IllegalAccessException illegalAccessException1) {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException1);
        illegalAccessException1 = illegalAccessException2;
      } 
      if (illegalAccessException1 != null) {
        illegalAccessException1.clear();
        return true;
      } 
    } 
    return false;
  }
  
  @RequiresApi(23)
  private static boolean flushMarshmallows(@NonNull Resources paramResources) {
    boolean bool = sDrawableCacheFieldFetched;
    boolean bool1 = true;
    if (!bool) {
      try {
        Field field1 = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField = field1;
        field1.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException);
      } 
      sDrawableCacheFieldFetched = true;
    } 
    Object object1 = null;
    Field field = sDrawableCacheField;
    Object object = object1;
    if (field != null)
      try {
        object = field.get(paramResources);
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException);
        object = object1;
      }  
    if (object == null)
      return false; 
    if (!flushThemedResourcesCache(object))
      bool1 = false; 
    return bool1;
  }
  
  @RequiresApi(24)
  private static boolean flushNougats(@NonNull Resources paramResources) {
    boolean bool = sResourcesImplFieldFetched;
    boolean bool1 = true;
    if (!bool) {
      try {
        Field field = Resources.class.getDeclaredField("mResourcesImpl");
        sResourcesImplField = field;
        field.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", noSuchFieldException);
      } 
      sResourcesImplFieldFetched = true;
    } 
    Field field1 = sResourcesImplField;
    if (field1 == null)
      return false; 
    Field field2 = null;
    try {
      object = field1.get(paramResources);
    } catch (IllegalAccessException object) {
      Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", (Throwable)object);
      object = null;
    } 
    if (object == null)
      return false; 
    if (!sDrawableCacheFieldFetched) {
      try {
        field1 = object.getClass().getDeclaredField("mDrawableCache");
        sDrawableCacheField = field1;
        field1.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", noSuchFieldException);
      } 
      sDrawableCacheFieldFetched = true;
    } 
    Field field3 = sDrawableCacheField;
    field1 = field2;
    if (field3 != null)
      try {
        Object object1 = field3.get(object);
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", illegalAccessException);
        field1 = field2;
      }  
    if (field1 == null || !flushThemedResourcesCache(field1))
      bool1 = false; 
    return bool1;
  }
  
  @RequiresApi(16)
  private static boolean flushThemedResourcesCache(@NonNull Object paramObject) {
    Class clazz1;
    if (!sThemedResourceCacheClazzFetched) {
      try {
        sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
      } catch (ClassNotFoundException classNotFoundException) {
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", classNotFoundException);
      } 
      sThemedResourceCacheClazzFetched = true;
    } 
    Class clazz2 = sThemedResourceCacheClazz;
    if (clazz2 == null)
      return false; 
    if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
      try {
        Field field1 = clazz2.getDeclaredField("mUnthemedEntries");
        sThemedResourceCache_mUnthemedEntriesField = field1;
        field1.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", noSuchFieldException);
      } 
      sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
    } 
    Field field = sThemedResourceCache_mUnthemedEntriesField;
    if (field == null)
      return false; 
    clazz2 = null;
    try {
      paramObject = field.get(paramObject);
    } catch (IllegalAccessException illegalAccessException) {
      Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", illegalAccessException);
      clazz1 = clazz2;
    } 
    if (clazz1 != null) {
      clazz1.clear();
      return true;
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/ResourcesFlusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */