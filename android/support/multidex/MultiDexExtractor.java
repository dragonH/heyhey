package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class MultiDexExtractor {
  private static final int BUFFER_SIZE = 16384;
  
  private static final String DEX_PREFIX = "classes";
  
  private static final String DEX_SUFFIX = ".dex";
  
  private static final String EXTRACTED_NAME_EXT = ".classes";
  
  private static final String EXTRACTED_SUFFIX = ".zip";
  
  private static final String KEY_CRC = "crc";
  
  private static final String KEY_DEX_NUMBER = "dex.number";
  
  private static final String KEY_TIME_STAMP = "timestamp";
  
  private static final int MAX_EXTRACT_ATTEMPTS = 3;
  
  private static final long NO_VALUE = -1L;
  
  private static final String PREFS_FILE = "multidex.version";
  
  private static final String TAG = "MultiDex";
  
  private static Method sApplyMethod;
  
  static {
    try {
      sApplyMethod = SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
    } catch (NoSuchMethodException noSuchMethodException) {
      sApplyMethod = null;
    } 
  }
  
  private static void apply(SharedPreferences.Editor paramEditor) {
    Method method = sApplyMethod;
    if (method != null)
      try {
        method.invoke(paramEditor, new Object[0]);
        return;
      } catch (InvocationTargetException|IllegalAccessException invocationTargetException) {} 
    paramEditor.commit();
  }
  
  private static void closeQuietly(Closeable paramCloseable) {
    try {
      paramCloseable.close();
    } catch (IOException iOException) {
      Log.w("MultiDex", "Failed to close resource", iOException);
    } 
  }
  
  private static void extract(ZipFile paramZipFile, ZipEntry paramZipEntry, File paramFile, String paramString) throws IOException, FileNotFoundException {
    InputStream inputStream = paramZipFile.getInputStream(paramZipEntry);
    File file = File.createTempFile(paramString, ".zip", paramFile.getParentFile());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Extracting ");
    stringBuilder.append(file.getPath());
    Log.i("MultiDex", stringBuilder.toString());
    try {
      StringBuilder stringBuilder1;
      ZipOutputStream zipOutputStream = new ZipOutputStream();
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream();
      FileOutputStream fileOutputStream = new FileOutputStream();
      this(file);
      this(fileOutputStream);
    } finally {
      closeQuietly(inputStream);
      file.delete();
    } 
  }
  
  private static SharedPreferences getMultiDexPreferences(Context paramContext) {
    return paramContext.getSharedPreferences("multidex.version", 4);
  }
  
  private static long getTimeStamp(File paramFile) {
    long l1 = paramFile.lastModified();
    long l2 = l1;
    if (l1 == -1L)
      l2 = l1 - 1L; 
    return l2;
  }
  
  private static long getZipCrc(File paramFile) throws IOException {
    long l1 = ZipUtil.getZipCrc(paramFile);
    long l2 = l1;
    if (l1 == -1L)
      l2 = l1 - 1L; 
    return l2;
  }
  
  private static boolean isModified(Context paramContext, File paramFile, long paramLong) {
    SharedPreferences sharedPreferences = getMultiDexPreferences(paramContext);
    return (sharedPreferences.getLong("timestamp", -1L) != getTimeStamp(paramFile) || sharedPreferences.getLong("crc", -1L) != paramLong);
  }
  
  static List<File> load(Context paramContext, ApplicationInfo paramApplicationInfo, File paramFile, boolean paramBoolean) throws IOException {
    List<File> list;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("MultiDexExtractor.load(");
    stringBuilder2.append(paramApplicationInfo.sourceDir);
    stringBuilder2.append(", ");
    stringBuilder2.append(paramBoolean);
    stringBuilder2.append(")");
    Log.i("MultiDex", stringBuilder2.toString());
    File file = new File(paramApplicationInfo.sourceDir);
    long l = getZipCrc(file);
    if (!paramBoolean && !isModified(paramContext, file, l)) {
      try {
        List<File> list1 = loadExistingExtractions(paramContext, file, paramFile);
        list = list1;
      } catch (IOException iOException) {
        Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", iOException);
        List<File> list1 = performExtractions(file, paramFile);
        putStoredApkInfo((Context)list, getTimeStamp(file), l, list1.size() + 1);
        list = list1;
      } 
    } else {
      Log.i("MultiDex", "Detected that extraction must be performed.");
      List<File> list1 = performExtractions(file, paramFile);
      putStoredApkInfo((Context)list, getTimeStamp(file), l, list1.size() + 1);
      list = list1;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("load found ");
    stringBuilder1.append(list.size());
    stringBuilder1.append(" secondary dex files");
    Log.i("MultiDex", stringBuilder1.toString());
    return list;
  }
  
  private static List<File> loadExistingExtractions(Context paramContext, File paramFile1, File paramFile2) throws IOException {
    Log.i("MultiDex", "loading existing secondary dex files");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramFile1.getName());
    stringBuilder.append(".classes");
    String str = stringBuilder.toString();
    int i = getMultiDexPreferences(paramContext).getInt("dex.number", 1);
    ArrayList<File> arrayList = new ArrayList(i);
    byte b = 2;
    while (b <= i) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append(b);
      stringBuilder1.append(".zip");
      File file = new File(paramFile2, stringBuilder1.toString());
      if (file.isFile()) {
        arrayList.add(file);
        if (verifyZipFile(file)) {
          b++;
          continue;
        } 
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Invalid zip file: ");
        stringBuilder3.append(file);
        Log.i("MultiDex", stringBuilder3.toString());
        throw new IOException("Invalid ZIP file.");
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Missing extracted secondary dex file '");
      stringBuilder2.append(file.getPath());
      stringBuilder2.append("'");
      throw new IOException(stringBuilder2.toString());
    } 
    return arrayList;
  }
  
  private static void mkdirChecked(File paramFile) throws IOException {
    paramFile.mkdir();
    if (!paramFile.isDirectory()) {
      File file = paramFile.getParentFile();
      if (file == null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to create dir ");
        stringBuilder1.append(paramFile.getPath());
        stringBuilder1.append(". Parent file is null.");
        Log.e("MultiDex", stringBuilder1.toString());
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to create dir ");
        stringBuilder1.append(paramFile.getPath());
        stringBuilder1.append(". parent file is a dir ");
        stringBuilder1.append(file.isDirectory());
        stringBuilder1.append(", a file ");
        stringBuilder1.append(file.isFile());
        stringBuilder1.append(", exists ");
        stringBuilder1.append(file.exists());
        stringBuilder1.append(", readable ");
        stringBuilder1.append(file.canRead());
        stringBuilder1.append(", writable ");
        stringBuilder1.append(file.canWrite());
        Log.e("MultiDex", stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to create cache directory ");
      stringBuilder.append(paramFile.getPath());
      throw new IOException(stringBuilder.toString());
    } 
  }
  
  private static List<File> performExtractions(File paramFile1, File paramFile2) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramFile1.getName());
    stringBuilder.append(".classes");
    String str = stringBuilder.toString();
    prepareDexDir(paramFile2, str);
    ArrayList<File> arrayList = new ArrayList();
    ZipFile zipFile = new ZipFile(paramFile1);
    byte b = 2;
    try {
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("classes");
      stringBuilder1.append(2);
      stringBuilder1.append(".dex");
      ZipEntry zipEntry = zipFile.getEntry(stringBuilder1.toString());
      while (zipEntry != null) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(str);
        stringBuilder.append(b);
        stringBuilder.append(".zip");
        String str1 = stringBuilder.toString();
        File file = new File();
        this(paramFile2, str1);
        arrayList.add(file);
        StringBuilder stringBuilder3 = new StringBuilder();
        this();
        stringBuilder3.append("Extraction is needed for file ");
        stringBuilder3.append(file);
        Log.i("MultiDex", stringBuilder3.toString());
        int i = 0;
        boolean bool = false;
        while (i < 3 && !bool) {
          String str2;
          int j = i + 1;
          extract(zipFile, zipEntry, file, str);
          boolean bool1 = verifyZipFile(file);
          StringBuilder stringBuilder4 = new StringBuilder();
          this();
          stringBuilder4.append("Extraction ");
          if (bool1) {
            str2 = "success";
          } else {
            str2 = "failed";
          } 
          stringBuilder4.append(str2);
          stringBuilder4.append(" - length ");
          stringBuilder4.append(file.getAbsolutePath());
          stringBuilder4.append(": ");
          stringBuilder4.append(file.length());
          Log.i("MultiDex", stringBuilder4.toString());
          i = j;
          bool = bool1;
          if (!bool1) {
            file.delete();
            i = j;
            bool = bool1;
            if (file.exists()) {
              StringBuilder stringBuilder5 = new StringBuilder();
              this();
              stringBuilder5.append("Failed to delete corrupted secondary dex '");
              stringBuilder5.append(file.getPath());
              stringBuilder5.append("'");
              Log.w("MultiDex", stringBuilder5.toString());
              i = j;
              bool = bool1;
            } 
          } 
        } 
        if (bool) {
          b++;
          StringBuilder stringBuilder4 = new StringBuilder();
          this();
          stringBuilder4.append("classes");
          stringBuilder4.append(b);
          stringBuilder4.append(".dex");
          ZipEntry zipEntry1 = zipFile.getEntry(stringBuilder4.toString());
          continue;
        } 
        IOException iOException = new IOException();
        StringBuilder stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append("Could not create zip file ");
        stringBuilder2.append(file.getAbsolutePath());
        stringBuilder2.append(" for secondary dex (");
        stringBuilder2.append(b);
        stringBuilder2.append(")");
        this(stringBuilder2.toString());
        throw iOException;
      } 
      return arrayList;
    } finally {
      try {
        zipFile.close();
      } catch (IOException iOException) {
        Log.w("MultiDex", "Failed to close resource", iOException);
      } 
    } 
  }
  
  private static void prepareDexDir(File paramFile, final String extractedFilePrefix) throws IOException {
    StringBuilder stringBuilder;
    mkdirChecked(paramFile.getParentFile());
    mkdirChecked(paramFile);
    File[] arrayOfFile = paramFile.listFiles(new FileFilter() {
          public boolean accept(File param1File) {
            return param1File.getName().startsWith(extractedFilePrefix) ^ true;
          }
        });
    if (arrayOfFile == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to list secondary dex dir content (");
      stringBuilder.append(paramFile.getPath());
      stringBuilder.append(").");
      Log.w("MultiDex", stringBuilder.toString());
      return;
    } 
    int i = stringBuilder.length;
    for (byte b = 0; b < i; b++) {
      StringBuilder stringBuilder1 = stringBuilder[b];
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Trying to delete old file ");
      stringBuilder2.append(stringBuilder1.getPath());
      stringBuilder2.append(" of size ");
      stringBuilder2.append(stringBuilder1.length());
      Log.i("MultiDex", stringBuilder2.toString());
      if (!stringBuilder1.delete()) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Failed to delete old file ");
        stringBuilder2.append(stringBuilder1.getPath());
        Log.w("MultiDex", stringBuilder2.toString());
      } else {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Deleted old file ");
        stringBuilder2.append(stringBuilder1.getPath());
        Log.i("MultiDex", stringBuilder2.toString());
      } 
    } 
  }
  
  private static void putStoredApkInfo(Context paramContext, long paramLong1, long paramLong2, int paramInt) {
    SharedPreferences.Editor editor = getMultiDexPreferences(paramContext).edit();
    editor.putLong("timestamp", paramLong1);
    editor.putLong("crc", paramLong2);
    editor.putInt("dex.number", paramInt);
    apply(editor);
  }
  
  static boolean verifyZipFile(File paramFile) {
    try {
      ZipFile zipFile = new ZipFile();
      this(paramFile);
      try {
        zipFile.close();
        return true;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Failed to close zip file: ");
        stringBuilder.append(paramFile.getAbsolutePath());
        Log.w("MultiDex", stringBuilder.toString());
      } 
    } catch (ZipException zipException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("File ");
      stringBuilder.append(paramFile.getAbsolutePath());
      stringBuilder.append(" is not a valid zip file.");
      Log.w("MultiDex", stringBuilder.toString(), zipException);
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Got an IOException trying to open zip file: ");
      stringBuilder.append(paramFile.getAbsolutePath());
      Log.w("MultiDex", stringBuilder.toString(), iOException);
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/multidex/MultiDexExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */