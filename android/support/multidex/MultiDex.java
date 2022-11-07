package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.zip.ZipFile;

public final class MultiDex {
  private static final boolean IS_VM_MULTIDEX_CAPABLE;
  
  private static final int MAX_SUPPORTED_SDK_VERSION = 20;
  
  private static final int MIN_SDK_VERSION = 4;
  
  private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
  
  private static final String SECONDARY_FOLDER_NAME;
  
  static final String TAG = "MultiDex";
  
  private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
  
  private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
  
  private static final Set<String> installedApk = new HashSet<String>();
  
  static {
    IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
  }
  
  private static boolean checkValidZipFiles(List<File> paramList) {
    Iterator<File> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      if (!MultiDexExtractor.verifyZipFile(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  private static void clearOldDexDir(Context paramContext) throws Exception {
    File file = new File(paramContext.getFilesDir(), "secondary-dexes");
    if (file.isDirectory()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Clearing old secondary dex dir (");
      stringBuilder.append(file.getPath());
      stringBuilder.append(").");
      Log.i("MultiDex", stringBuilder.toString());
      File[] arrayOfFile = file.listFiles();
      if (arrayOfFile == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to list secondary dex dir content (");
        stringBuilder.append(file.getPath());
        stringBuilder.append(").");
        Log.w("MultiDex", stringBuilder.toString());
        return;
      } 
      int i = arrayOfFile.length;
      for (byte b = 0; b < i; b++) {
        File file1 = arrayOfFile[b];
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Trying to delete old file ");
        stringBuilder1.append(file1.getPath());
        stringBuilder1.append(" of size ");
        stringBuilder1.append(file1.length());
        Log.i("MultiDex", stringBuilder1.toString());
        if (!file1.delete()) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Failed to delete old file ");
          stringBuilder1.append(file1.getPath());
          Log.w("MultiDex", stringBuilder1.toString());
        } else {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Deleted old file ");
          stringBuilder1.append(file1.getPath());
          Log.i("MultiDex", stringBuilder1.toString());
        } 
      } 
      if (!file.delete()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to delete secondary dex dir ");
        stringBuilder.append(file.getPath());
        Log.w("MultiDex", stringBuilder.toString());
      } else {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Deleted old secondary dex dir ");
        stringBuilder.append(file.getPath());
        Log.i("MultiDex", stringBuilder.toString());
      } 
    } 
  }
  
  private static void expandFieldArray(Object paramObject, String paramString, Object[] paramArrayOfObject) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Field field = findField(paramObject, paramString);
    Object[] arrayOfObject1 = (Object[])field.get(paramObject);
    Object[] arrayOfObject2 = (Object[])Array.newInstance(arrayOfObject1.getClass().getComponentType(), arrayOfObject1.length + paramArrayOfObject.length);
    System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, arrayOfObject1.length);
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject2, arrayOfObject1.length, paramArrayOfObject.length);
    field.set(paramObject, arrayOfObject2);
  }
  
  private static Field findField(Object paramObject, String paramString) throws NoSuchFieldException {
    Class<?> clazz = paramObject.getClass();
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(paramString);
        if (!field.isAccessible())
          field.setAccessible(true); 
        return field;
      } catch (NoSuchFieldException noSuchFieldException) {
        clazz = clazz.getSuperclass();
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Field ");
    stringBuilder.append(paramString);
    stringBuilder.append(" not found in ");
    stringBuilder.append(paramObject.getClass());
    paramObject = new NoSuchFieldException(stringBuilder.toString());
    throw paramObject;
  }
  
  private static Method findMethod(Object paramObject, String paramString, Class<?>... paramVarArgs) throws NoSuchMethodException {
    Class<?> clazz = paramObject.getClass();
    while (clazz != null) {
      try {
        Method method = clazz.getDeclaredMethod(paramString, paramVarArgs);
        if (!method.isAccessible())
          method.setAccessible(true); 
        return method;
      } catch (NoSuchMethodException noSuchMethodException) {
        clazz = clazz.getSuperclass();
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Method ");
    stringBuilder.append(paramString);
    stringBuilder.append(" with parameters ");
    stringBuilder.append(Arrays.asList(paramVarArgs));
    stringBuilder.append(" not found in ");
    stringBuilder.append(paramObject.getClass());
    paramObject = new NoSuchMethodException(stringBuilder.toString());
    throw paramObject;
  }
  
  private static ApplicationInfo getApplicationInfo(Context paramContext) throws PackageManager.NameNotFoundException {
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      return (packageManager == null || str == null) ? null : packageManager.getApplicationInfo(str, 128);
    } catch (RuntimeException runtimeException) {
      Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", runtimeException);
      return null;
    } 
  }
  
  public static void install(Context paramContext) {
    Log.i("MultiDex", "install");
    if (IS_VM_MULTIDEX_CAPABLE) {
      Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
      return;
    } 
    int i = Build.VERSION.SDK_INT;
    try {
      ApplicationInfo applicationInfo = getApplicationInfo(paramContext);
      if (applicationInfo == null)
        return; 
      synchronized (installedApk) {
        String str = applicationInfo.sourceDir;
        if (null.contains(str))
          return; 
        null.add(str);
        if (i > 20) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("MultiDex is not guaranteed to work in SDK version ");
          stringBuilder.append(i);
          stringBuilder.append(": SDK version higher than ");
          stringBuilder.append(20);
          stringBuilder.append(" should be backed by ");
          stringBuilder.append("runtime with built-in multidex capabilty but it's not the ");
          stringBuilder.append("case here: java.vm.version=\"");
          stringBuilder.append(System.getProperty("java.vm.version"));
          stringBuilder.append("\"");
          Log.w("MultiDex", stringBuilder.toString());
        } 
        try {
          File file;
          ClassLoader classLoader = paramContext.getClassLoader();
          if (classLoader == null) {
            Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
            return;
          } 
          try {
            clearOldDexDir(paramContext);
          } finally {
            Exception exception = null;
          } 
          this(applicationInfo.dataDir, SECONDARY_FOLDER_NAME);
          List<File> list = MultiDexExtractor.load(paramContext, applicationInfo, file, false);
          if (checkValidZipFiles(list)) {
            installSecondaryDexes(classLoader, file, list);
          } else {
            Log.w("MultiDex", "Files were not valid zip files.  Forcing a reload.");
            List<File> list1 = MultiDexExtractor.load(paramContext, applicationInfo, file, true);
            if (checkValidZipFiles(list1)) {
              installSecondaryDexes(classLoader, file, list1);
            } else {
              RuntimeException runtimeException = new RuntimeException();
              this("Zip files were not valid.");
              throw runtimeException;
            } 
          } 
          Log.i("MultiDex", "install done");
          return;
        } catch (RuntimeException runtimeException) {
          Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", runtimeException);
          return;
        } 
      } 
    } catch (Exception exception) {
      Log.e("MultiDex", "Multidex installation failure", exception);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Multi dex installation failed (");
      stringBuilder.append(exception.getMessage());
      stringBuilder.append(").");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  private static void installSecondaryDexes(ClassLoader paramClassLoader, File paramFile, List<File> paramList) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
    if (!paramList.isEmpty())
      if (Build.VERSION.SDK_INT >= 19) {
        V19.install(paramClassLoader, paramList, paramFile);
      } else {
        V14.install(paramClassLoader, paramList, paramFile);
      }  
  }
  
  static boolean isVMMultidexCapable(String paramString) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: iload_1
    //   3: istore_2
    //   4: aload_0
    //   5: ifnull -> 78
    //   8: ldc_w '(\d+)\.(\d+)(\.\d+)?'
    //   11: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   14: aload_0
    //   15: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   18: astore_3
    //   19: iload_1
    //   20: istore_2
    //   21: aload_3
    //   22: invokevirtual matches : ()Z
    //   25: ifeq -> 78
    //   28: aload_3
    //   29: iconst_1
    //   30: invokevirtual group : (I)Ljava/lang/String;
    //   33: invokestatic parseInt : (Ljava/lang/String;)I
    //   36: istore #4
    //   38: aload_3
    //   39: iconst_2
    //   40: invokevirtual group : (I)Ljava/lang/String;
    //   43: invokestatic parseInt : (Ljava/lang/String;)I
    //   46: istore #5
    //   48: iload #4
    //   50: iconst_2
    //   51: if_icmpgt -> 70
    //   54: iload_1
    //   55: istore_2
    //   56: iload #4
    //   58: iconst_2
    //   59: if_icmpne -> 78
    //   62: iload_1
    //   63: istore_2
    //   64: iload #5
    //   66: iconst_1
    //   67: if_icmplt -> 78
    //   70: iconst_1
    //   71: istore_2
    //   72: goto -> 78
    //   75: astore_3
    //   76: iload_1
    //   77: istore_2
    //   78: new java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial <init> : ()V
    //   85: astore_3
    //   86: aload_3
    //   87: ldc_w 'VM with version '
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_3
    //   95: aload_0
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: iload_2
    //   101: ifeq -> 111
    //   104: ldc_w ' has multidex support'
    //   107: astore_0
    //   108: goto -> 115
    //   111: ldc_w ' does not have multidex support'
    //   114: astore_0
    //   115: aload_3
    //   116: aload_0
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: ldc 'MultiDex'
    //   123: aload_3
    //   124: invokevirtual toString : ()Ljava/lang/String;
    //   127: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   130: pop
    //   131: iload_2
    //   132: ireturn
    // Exception table:
    //   from	to	target	type
    //   28	48	75	java/lang/NumberFormatException
  }
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("code_cache");
    stringBuilder.append(File.separator);
    stringBuilder.append("secondary-dexes");
    SECONDARY_FOLDER_NAME = stringBuilder.toString();
  }
  
  private static final class V14 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List, File param1File) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
      Object object = MultiDex.findField(param1ClassLoader, "pathList").get(param1ClassLoader);
      MultiDex.expandFieldArray(object, "dexElements", makeDexElements(object, new ArrayList<File>(param1List), param1File));
    }
    
    private static Object[] makeDexElements(Object param1Object, ArrayList<File> param1ArrayList, File param1File) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      return (Object[])MultiDex.findMethod(param1Object, "makeDexElements", new Class[] { ArrayList.class, File.class }).invoke(param1Object, new Object[] { param1ArrayList, param1File });
    }
  }
  
  private static final class V19 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List, File param1File) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
      Object object = MultiDex.findField(param1ClassLoader, "pathList").get(param1ClassLoader);
      ArrayList<IOException> arrayList = new ArrayList();
      MultiDex.expandFieldArray(object, "dexElements", makeDexElements(object, new ArrayList<File>(param1List), param1File, arrayList));
      if (arrayList.size() > 0) {
        IOException[] arrayOfIOException;
        Iterator<IOException> iterator = arrayList.iterator();
        while (iterator.hasNext())
          Log.w("MultiDex", "Exception in makeDexElement", iterator.next()); 
        Field field = MultiDex.findField(param1ClassLoader, "dexElementsSuppressedExceptions");
        object = field.get(param1ClassLoader);
        if (object == null) {
          arrayOfIOException = arrayList.<IOException>toArray(new IOException[arrayList.size()]);
        } else {
          arrayOfIOException = new IOException[arrayList.size() + object.length];
          arrayList.toArray(arrayOfIOException);
          System.arraycopy(object, 0, arrayOfIOException, arrayList.size(), object.length);
        } 
        field.set(param1ClassLoader, arrayOfIOException);
      } 
    }
    
    private static Object[] makeDexElements(Object param1Object, ArrayList<File> param1ArrayList, File param1File, ArrayList<IOException> param1ArrayList1) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      return (Object[])MultiDex.findMethod(param1Object, "makeDexElements", new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(param1Object, new Object[] { param1ArrayList, param1File, param1ArrayList1 });
    }
  }
  
  private static final class V4 {
    private static void install(ClassLoader param1ClassLoader, List<File> param1List) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
      int i = param1List.size();
      Field field = MultiDex.findField(param1ClassLoader, "path");
      StringBuilder stringBuilder = new StringBuilder((String)field.get(param1ClassLoader));
      String[] arrayOfString = new String[i];
      File[] arrayOfFile = new File[i];
      ZipFile[] arrayOfZipFile = new ZipFile[i];
      DexFile[] arrayOfDexFile = new DexFile[i];
      ListIterator<File> listIterator = param1List.listIterator();
      while (listIterator.hasNext()) {
        File file = listIterator.next();
        String str = file.getAbsolutePath();
        stringBuilder.append(':');
        stringBuilder.append(str);
        i = listIterator.previousIndex();
        arrayOfString[i] = str;
        arrayOfFile[i] = file;
        arrayOfZipFile[i] = new ZipFile(file);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(".dex");
        arrayOfDexFile[i] = DexFile.loadDex(str, stringBuilder1.toString(), 0);
      } 
      field.set(param1ClassLoader, stringBuilder.toString());
      MultiDex.expandFieldArray(param1ClassLoader, "mPaths", (Object[])arrayOfString);
      MultiDex.expandFieldArray(param1ClassLoader, "mFiles", (Object[])arrayOfFile);
      MultiDex.expandFieldArray(param1ClassLoader, "mZips", (Object[])arrayOfZipFile);
      MultiDex.expandFieldArray(param1ClassLoader, "mDexs", (Object[])arrayOfDexFile);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/multidex/MultiDex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */