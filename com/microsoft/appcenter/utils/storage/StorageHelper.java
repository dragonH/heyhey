package com.microsoft.appcenter.utils.storage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StorageHelper {
  private static final String PREFERENCES_NAME = "AppCenter";
  
  @SuppressLint({"StaticFieldLeak"})
  private static Context sContext;
  
  private static SharedPreferences sSharedPreferences;
  
  public static void initialize(Context paramContext) {
    // Byte code:
    //   0: ldc com/microsoft/appcenter/utils/storage/StorageHelper
    //   2: monitorenter
    //   3: getstatic com/microsoft/appcenter/utils/storage/StorageHelper.sContext : Landroid/content/Context;
    //   6: ifnonnull -> 23
    //   9: aload_0
    //   10: putstatic com/microsoft/appcenter/utils/storage/StorageHelper.sContext : Landroid/content/Context;
    //   13: aload_0
    //   14: ldc 'AppCenter'
    //   16: iconst_0
    //   17: invokevirtual getSharedPreferences : (Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   20: putstatic com/microsoft/appcenter/utils/storage/StorageHelper.sSharedPreferences : Landroid/content/SharedPreferences;
    //   23: ldc com/microsoft/appcenter/utils/storage/StorageHelper
    //   25: monitorexit
    //   26: return
    //   27: astore_0
    //   28: ldc com/microsoft/appcenter/utils/storage/StorageHelper
    //   30: monitorexit
    //   31: aload_0
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   3	23	27	finally
  }
  
  public static class DatabaseStorage implements Closeable {
    private final DatabaseManager mDatabaseManager;
    
    private DatabaseStorage(@NonNull DatabaseManager param1DatabaseManager) {
      this.mDatabaseManager = param1DatabaseManager;
    }
    
    public static DatabaseStorage getDatabaseStorage(@NonNull String param1String1, @NonNull String param1String2, @IntRange(from = 1L) int param1Int1, @NonNull ContentValues param1ContentValues, @IntRange(from = 0L) int param1Int2, final DatabaseErrorListener listener) {
      return new DatabaseStorage(new DatabaseManager(StorageHelper.sContext, param1String1, param1String2, param1Int1, param1ContentValues, param1Int2, new DatabaseManager.ErrorListener() {
              public void onError(String param2String, RuntimeException param2RuntimeException) {
                listener.onError(param2String, param2RuntimeException);
              }
            }));
    }
    
    public static DatabaseStorage getDatabaseStorage(@NonNull String param1String1, @NonNull String param1String2, @IntRange(from = 1L) int param1Int, @NonNull ContentValues param1ContentValues, @NonNull DatabaseErrorListener param1DatabaseErrorListener) {
      return getDatabaseStorage(param1String1, param1String2, param1Int, param1ContentValues, 0, param1DatabaseErrorListener);
    }
    
    public void clear() {
      this.mDatabaseManager.clear();
    }
    
    public void close() throws IOException {
      this.mDatabaseManager.close();
    }
    
    public void delete(@IntRange(from = 0L) long param1Long) {
      this.mDatabaseManager.delete(param1Long);
    }
    
    public void delete(@Nullable String param1String, @Nullable Object param1Object) {
      this.mDatabaseManager.delete(param1String, param1Object);
    }
    
    public void delete(@NonNull List<Long> param1List) {
      this.mDatabaseManager.delete(param1List);
    }
    
    public ContentValues get(@IntRange(from = 0L) long param1Long) {
      return this.mDatabaseManager.get(param1Long);
    }
    
    public ContentValues get(@Nullable String param1String, @Nullable Object param1Object) {
      return this.mDatabaseManager.get(param1String, param1Object);
    }
    
    @VisibleForTesting
    String[] getColumnNames() {
      return this.mDatabaseManager.getCursor(null, null).getColumnNames();
    }
    
    public DatabaseScanner getScanner() {
      return getScanner(null, null);
    }
    
    public DatabaseScanner getScanner(@Nullable String param1String, @Nullable Object param1Object) {
      return new DatabaseScanner(this.mDatabaseManager.getScanner(param1String, param1Object));
    }
    
    public long put(@NonNull ContentValues param1ContentValues) {
      return this.mDatabaseManager.put(param1ContentValues);
    }
    
    public long size() {
      return this.mDatabaseManager.getRowCount();
    }
    
    public boolean update(@IntRange(from = 0L) long param1Long, @NonNull ContentValues param1ContentValues) {
      return this.mDatabaseManager.update(param1Long, param1ContentValues);
    }
    
    public static interface DatabaseErrorListener {
      void onError(String param2String, RuntimeException param2RuntimeException);
    }
    
    public static class DatabaseScanner implements Iterable<ContentValues>, Closeable {
      private final DatabaseManager.Scanner mScanner;
      
      private DatabaseScanner(DatabaseManager.Scanner param2Scanner) {
        this.mScanner = param2Scanner;
      }
      
      public void close() {
        this.mScanner.close();
      }
      
      public int getCount() {
        return this.mScanner.getCount();
      }
      
      public Iterator<ContentValues> iterator() {
        return this.mScanner.iterator();
      }
    }
  }
  
  static final class null implements DatabaseManager.ErrorListener {
    public void onError(String param1String, RuntimeException param1RuntimeException) {
      listener.onError(param1String, param1RuntimeException);
    }
  }
  
  public static interface DatabaseErrorListener {
    void onError(String param1String, RuntimeException param1RuntimeException);
  }
  
  public static class DatabaseScanner implements Iterable<ContentValues>, Closeable {
    private final DatabaseManager.Scanner mScanner;
    
    private DatabaseScanner(DatabaseManager.Scanner param1Scanner) {
      this.mScanner = param1Scanner;
    }
    
    public void close() {
      this.mScanner.close();
    }
    
    public int getCount() {
      return this.mScanner.getCount();
    }
    
    public Iterator<ContentValues> iterator() {
      return this.mScanner.iterator();
    }
  }
  
  public static class InternalStorage {
    public static boolean delete(@NonNull File param1File) {
      return param1File.delete();
    }
    
    public static boolean delete(@NonNull String param1String) {
      return delete(new File(param1String));
    }
    
    @NonNull
    public static String[] getFilenames(@NonNull String param1String, @Nullable FilenameFilter param1FilenameFilter) {
      File file = new File(param1String);
      return file.exists() ? file.list(param1FilenameFilter) : new String[0];
    }
    
    @Nullable
    public static File lastModifiedFile(@NonNull File param1File, @Nullable FilenameFilter param1FilenameFilter) {
      boolean bool = param1File.exists();
      File file1 = null;
      File file2 = null;
      File file3 = file1;
      if (bool) {
        File[] arrayOfFile = param1File.listFiles(param1FilenameFilter);
        long l = 0L;
        file3 = file1;
        if (arrayOfFile != null) {
          int i = arrayOfFile.length;
          byte b = 0;
          param1File = file2;
          while (true) {
            file3 = param1File;
            if (b < i) {
              File file = arrayOfFile[b];
              long l1 = l;
              if (file.lastModified() > l) {
                l1 = file.lastModified();
                param1File = file;
              } 
              b++;
              l = l1;
              continue;
            } 
            break;
          } 
        } 
      } 
      return file3;
    }
    
    @Nullable
    public static File lastModifiedFile(@NonNull String param1String, @Nullable FilenameFilter param1FilenameFilter) {
      return lastModifiedFile(new File(param1String), param1FilenameFilter);
    }
    
    public static void mkdir(@NonNull String param1String) {
      (new File(param1String)).mkdirs();
    }
    
    public static String read(@NonNull File param1File) {
      try {
        BufferedReader bufferedReader = new BufferedReader();
        null = new FileReader();
        this(param1File);
        this(null);
        try {
          String str = System.getProperty("line.separator");
          StringBuilder stringBuilder = new StringBuilder();
          this();
          while (true) {
            String str1 = bufferedReader.readLine();
            if (str1 != null) {
              stringBuilder.append(str1);
              stringBuilder.append(str);
              continue;
            } 
            return stringBuilder.toString();
          } 
        } finally {
          bufferedReader.close();
        } 
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not read file ");
        stringBuilder.append(param1File.getAbsolutePath());
        AppCenterLog.error("AppCenter", stringBuilder.toString(), iOException);
        return null;
      } 
    }
    
    public static String read(@NonNull String param1String) {
      return read(new File(param1String));
    }
    
    public static <T extends java.io.Serializable> T readObject(@NonNull File param1File) throws IOException, ClassNotFoundException {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(param1File));
      try {
        return (T)objectInputStream.readObject();
      } finally {
        objectInputStream.close();
      } 
    }
    
    public static void write(@NonNull File param1File, @NonNull String param1String) throws IOException {
      if (TextUtils.isEmpty(param1String) || TextUtils.getTrimmedLength(param1String) <= 0)
        return; 
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(param1File));
      try {
        bufferedWriter.write(param1String);
        return;
      } finally {
        bufferedWriter.close();
      } 
    }
    
    public static void write(@NonNull String param1String1, @NonNull String param1String2) throws IOException {
      write(new File(param1String1), param1String2);
    }
    
    public static <T extends java.io.Serializable> void writeObject(@NonNull File param1File, @NonNull T param1T) throws IOException {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(param1File));
      try {
        objectOutputStream.writeObject(param1T);
        return;
      } finally {
        objectOutputStream.close();
      } 
    }
  }
  
  public static class PreferencesStorage {
    public static void clear() {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.clear();
      editor.apply();
    }
    
    public static boolean getBoolean(@NonNull String param1String) {
      return getBoolean(param1String, false);
    }
    
    public static boolean getBoolean(@NonNull String param1String, boolean param1Boolean) {
      return StorageHelper.sSharedPreferences.getBoolean(param1String, param1Boolean);
    }
    
    public static float getFloat(@NonNull String param1String) {
      return getFloat(param1String, 0.0F);
    }
    
    public static float getFloat(@NonNull String param1String, float param1Float) {
      return StorageHelper.sSharedPreferences.getFloat(param1String, param1Float);
    }
    
    public static int getInt(@NonNull String param1String) {
      return getInt(param1String, 0);
    }
    
    public static int getInt(@NonNull String param1String, int param1Int) {
      return StorageHelper.sSharedPreferences.getInt(param1String, param1Int);
    }
    
    public static long getLong(@NonNull String param1String) {
      return getLong(param1String, 0L);
    }
    
    public static long getLong(@NonNull String param1String, long param1Long) {
      return StorageHelper.sSharedPreferences.getLong(param1String, param1Long);
    }
    
    public static String getString(@NonNull String param1String) {
      return getString(param1String, null);
    }
    
    public static String getString(@NonNull String param1String1, String param1String2) {
      return StorageHelper.sSharedPreferences.getString(param1String1, param1String2);
    }
    
    public static Set<String> getStringSet(@NonNull String param1String) {
      return getStringSet(param1String, null);
    }
    
    public static Set<String> getStringSet(@NonNull String param1String, Set<String> param1Set) {
      return StorageHelper.sSharedPreferences.getStringSet(param1String, param1Set);
    }
    
    public static void putBoolean(@NonNull String param1String, boolean param1Boolean) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putBoolean(param1String, param1Boolean);
      editor.apply();
    }
    
    public static void putFloat(@NonNull String param1String, float param1Float) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putFloat(param1String, param1Float);
      editor.apply();
    }
    
    public static void putInt(@NonNull String param1String, int param1Int) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putInt(param1String, param1Int);
      editor.apply();
    }
    
    public static void putLong(@NonNull String param1String, long param1Long) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putLong(param1String, param1Long);
      editor.apply();
    }
    
    public static void putString(@NonNull String param1String1, String param1String2) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putString(param1String1, param1String2);
      editor.apply();
    }
    
    public static void putStringSet(@NonNull String param1String, Set<String> param1Set) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.putStringSet(param1String, param1Set);
      editor.apply();
    }
    
    public static void remove(@NonNull String param1String) {
      SharedPreferences.Editor editor = StorageHelper.sSharedPreferences.edit();
      editor.remove(param1String);
      editor.apply();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/storage/StorageHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */