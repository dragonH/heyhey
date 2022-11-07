package mono.android.incrementaldeployment;

import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class IncrementalClassLoader extends ClassLoader {
  private final DelegateClassLoader delegateClassLoader;
  
  public IncrementalClassLoader(ClassLoader paramClassLoader, String paramString1, File paramFile, String paramString2, List<String> paramList) {
    super(paramClassLoader.getParent());
    this.delegateClassLoader = createDelegateClassLoader(paramFile, paramString2, paramList, paramClassLoader);
  }
  
  private static DelegateClassLoader createDelegateClassLoader(File paramFile, String paramString, List<String> paramList, ClassLoader paramClassLoader) {
    StringBuilder stringBuilder2 = new StringBuilder();
    Iterator<String> iterator = paramList.iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (bool) {
        bool = false;
      } else {
        stringBuilder2.append(File.pathSeparator);
      } 
      stringBuilder2.append(str);
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Incremental dex path is ");
    stringBuilder1.append(stringBuilder2);
    Log.v("IncrementalClassLoader", stringBuilder1.toString());
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Native lib dir is ");
    stringBuilder1.append(paramString);
    Log.v("IncrementalClassLoader", stringBuilder1.toString());
    return new DelegateClassLoader(stringBuilder2.toString(), paramFile, paramString, paramClassLoader);
  }
  
  public static void inject(ClassLoader paramClassLoader, String paramString1, File paramFile, String paramString2, List<String> paramList) {
    setParent(paramClassLoader, new IncrementalClassLoader(paramClassLoader, paramString1, paramFile, paramString2, paramList));
  }
  
  private static void setParent(ClassLoader paramClassLoader1, ClassLoader paramClassLoader2) {
    try {
      Field field = ClassLoader.class.getDeclaredField("parent");
      field.setAccessible(true);
      field.set(paramClassLoader1, paramClassLoader2);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new RuntimeException(illegalArgumentException);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException(illegalAccessException);
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new RuntimeException(noSuchFieldException);
    } 
  }
  
  public Class<?> findClass(String paramString) throws ClassNotFoundException {
    return this.delegateClassLoader.findClass(paramString);
  }
  
  private static class DelegateClassLoader extends BaseDexClassLoader {
    private DelegateClassLoader(String param1String1, File param1File, String param1String2, ClassLoader param1ClassLoader) {
      super(param1String1, param1File, param1String2, param1ClassLoader);
    }
    
    public Class<?> findClass(String param1String) throws ClassNotFoundException {
      return super.findClass(param1String);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/incrementaldeployment/IncrementalClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */