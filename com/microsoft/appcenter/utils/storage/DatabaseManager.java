package com.microsoft.appcenter.utils.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DatabaseManager implements Closeable {
  public static final String PRIMARY_KEY = "oid";
  
  private static final String PRIMARY_KEY_SELECTION = "oid = ?";
  
  private final Context mContext;
  
  private final String mDatabase;
  
  private final ErrorListener mErrorListener;
  
  private Map<Long, ContentValues> mIMDB;
  
  private long mIMDBAutoInc;
  
  private final int mMaxNumberOfRecords;
  
  private SQLiteOpenHelper mSQLiteOpenHelper;
  
  private final ContentValues mSchema;
  
  private final String mTable;
  
  DatabaseManager(Context paramContext, String paramString1, String paramString2, int paramInt1, ContentValues paramContentValues, int paramInt2, ErrorListener paramErrorListener) {
    this.mContext = paramContext;
    this.mDatabase = paramString1;
    this.mTable = paramString2;
    this.mSchema = paramContentValues;
    this.mMaxNumberOfRecords = paramInt2;
    this.mErrorListener = paramErrorListener;
    this.mSQLiteOpenHelper = new SQLiteOpenHelper(paramContext, paramString1, null, paramInt1) {
        public void onCreate(SQLiteDatabase param1SQLiteDatabase) {
          StringBuilder stringBuilder = new StringBuilder("CREATE TABLE `");
          stringBuilder.append(DatabaseManager.this.mTable);
          stringBuilder.append("` (oid INTEGER PRIMARY KEY AUTOINCREMENT");
          for (Map.Entry entry : DatabaseManager.this.mSchema.valueSet()) {
            stringBuilder.append(", `");
            stringBuilder.append((String)entry.getKey());
            stringBuilder.append("` ");
            entry = (Map.Entry)entry.getValue();
            if (entry instanceof Double || entry instanceof Float) {
              stringBuilder.append("REAL");
              continue;
            } 
            if (entry instanceof Number || entry instanceof Boolean) {
              stringBuilder.append("INTEGER");
              continue;
            } 
            if (entry instanceof byte[]) {
              stringBuilder.append("BLOB");
              continue;
            } 
            stringBuilder.append("TEXT");
          } 
          stringBuilder.append(");");
          param1SQLiteDatabase.execSQL(stringBuilder.toString());
        }
        
        public void onUpgrade(SQLiteDatabase param1SQLiteDatabase, int param1Int1, int param1Int2) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("DROP TABLE `");
          stringBuilder.append(DatabaseManager.this.mTable);
          stringBuilder.append("`");
          param1SQLiteDatabase.execSQL(stringBuilder.toString());
          onCreate(param1SQLiteDatabase);
        }
      };
  }
  
  DatabaseManager(Context paramContext, String paramString1, String paramString2, int paramInt, ContentValues paramContentValues, ErrorListener paramErrorListener) {
    this(paramContext, paramString1, paramString2, paramInt, paramContentValues, 0, paramErrorListener);
  }
  
  private static ContentValues buildValues(Cursor paramCursor, ContentValues paramContentValues) {
    ContentValues contentValues = new ContentValues();
    for (byte b = 0; b < paramCursor.getColumnCount(); b++) {
      if (!paramCursor.isNull(b)) {
        String str = paramCursor.getColumnName(b);
        if (str.equals("oid")) {
          contentValues.put(str, Long.valueOf(paramCursor.getLong(b)));
        } else {
          Object object = paramContentValues.get(str);
          if (object instanceof byte[]) {
            contentValues.put(str, paramCursor.getBlob(b));
          } else if (object instanceof Double) {
            contentValues.put(str, Double.valueOf(paramCursor.getDouble(b)));
          } else if (object instanceof Float) {
            contentValues.put(str, Float.valueOf(paramCursor.getFloat(b)));
          } else if (object instanceof Integer) {
            contentValues.put(str, Integer.valueOf(paramCursor.getInt(b)));
          } else if (object instanceof Long) {
            contentValues.put(str, Long.valueOf(paramCursor.getLong(b)));
          } else if (object instanceof Short) {
            contentValues.put(str, Short.valueOf(paramCursor.getShort(b)));
          } else if (object instanceof Boolean) {
            int i = paramCursor.getInt(b);
            boolean bool = true;
            if (i != 1)
              bool = false; 
            contentValues.put(str, Boolean.valueOf(bool));
          } else {
            contentValues.put(str, paramCursor.getString(b));
          } 
        } 
      } 
    } 
    return contentValues;
  }
  
  public void clear() {
    Map<Long, ContentValues> map = this.mIMDB;
    if (map == null) {
      try {
        getDatabase().delete(this.mTable, null, null);
      } catch (RuntimeException runtimeException) {
        switchToInMemory("clear", runtimeException);
      } 
    } else {
      runtimeException.clear();
    } 
  }
  
  public void close() throws IOException {
    Map<Long, ContentValues> map = this.mIMDB;
    if (map == null) {
      try {
        getDatabase().close();
      } catch (RuntimeException runtimeException) {
        switchToInMemory("close", runtimeException);
      } 
    } else {
      runtimeException.clear();
      this.mIMDB = null;
    } 
  }
  
  public void delete(@IntRange(from = 0L) long paramLong) {
    delete("oid", Long.valueOf(paramLong));
  }
  
  public void delete(@Nullable String paramString, @Nullable Object paramObject) {
    if (this.mIMDB == null) {
      try {
        SQLiteDatabase sQLiteDatabase = getDatabase();
        String str = this.mTable;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(paramString);
        stringBuilder.append(" = ?");
        sQLiteDatabase.delete(str, stringBuilder.toString(), new String[] { String.valueOf(paramObject) });
      } catch (RuntimeException runtimeException) {
        switchToInMemory("delete", runtimeException);
      } 
    } else if ("oid".equals(runtimeException)) {
      if (paramObject != null && paramObject instanceof Number) {
        this.mIMDB.remove(Long.valueOf(((Number)paramObject).longValue()));
      } else {
        throw new IllegalArgumentException("Primary key should be a number type and cannot be null");
      } 
    } else {
      Iterator<Map.Entry> iterator = this.mIMDB.entrySet().iterator();
      while (iterator.hasNext()) {
        Object object = ((ContentValues)((Map.Entry)iterator.next()).getValue()).get((String)runtimeException);
        if (object != null && object.equals(paramObject))
          iterator.remove(); 
      } 
    } 
  }
  
  public void delete(@NonNull List<Long> paramList) {
    if (paramList.size() <= 0)
      return; 
    if (this.mIMDB == null) {
      try {
        SQLiteDatabase sQLiteDatabase = getDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(this.mTable);
        stringBuilder.append(" WHERE ");
        stringBuilder.append("oid");
        stringBuilder.append(" IN (%s);");
        sQLiteDatabase.execSQL(String.format(stringBuilder.toString(), new Object[] { TextUtils.join(", ", paramList) }));
      } catch (RuntimeException runtimeException) {
        switchToInMemory("delete", runtimeException);
      } 
    } else {
      for (Long long_ : runtimeException)
        this.mIMDB.remove(long_); 
    } 
  }
  
  public ContentValues get(@IntRange(from = 0L) long paramLong) {
    return get("oid", Long.valueOf(paramLong));
  }
  
  public ContentValues get(@Nullable String paramString, @Nullable Object paramObject) {
    if (this.mIMDB == null) {
      try {
        paramObject = getCursor(paramString, paramObject);
        if (paramObject.moveToFirst()) {
          ContentValues contentValues = buildValues((Cursor)paramObject, this.mSchema);
        } else {
          paramString = null;
        } 
        paramObject.close();
        return (ContentValues)paramString;
      } catch (RuntimeException runtimeException) {
        switchToInMemory("get", runtimeException);
      } 
    } else {
      if ("oid".equals(runtimeException)) {
        if (paramObject != null && paramObject instanceof Number)
          return this.mIMDB.get(Long.valueOf(((Number)paramObject).longValue())); 
        throw new IllegalArgumentException("Primary key should be a number type and cannot be null");
      } 
      for (ContentValues contentValues : this.mIMDB.values()) {
        Object object = contentValues.get((String)runtimeException);
        if (object != null && object.equals(paramObject))
          return contentValues; 
      } 
    } 
    return null;
  }
  
  Cursor getCursor(String paramString, Object paramObject) throws RuntimeException {
    SQLiteQueryBuilder sQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
    sQLiteQueryBuilder.setTables(this.mTable);
    if (paramString != null)
      if (paramObject == null) {
        paramObject = new StringBuilder();
        paramObject.append(paramString);
        paramObject.append(" IS NULL");
        sQLiteQueryBuilder.appendWhere(paramObject.toString());
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" = ?");
        sQLiteQueryBuilder.appendWhere(stringBuilder.toString());
        String[] arrayOfString = { String.valueOf(paramObject.toString()) };
        return sQLiteQueryBuilder.query(getDatabase(), null, null, arrayOfString, null, null, "oid");
      }  
    paramString = null;
    return sQLiteQueryBuilder.query(getDatabase(), null, null, (String[])paramString, null, null, "oid");
  }
  
  @VisibleForTesting
  SQLiteDatabase getDatabase() throws RuntimeException {
    try {
      return this.mSQLiteOpenHelper.getWritableDatabase();
    } catch (RuntimeException runtimeException) {
      this.mContext.deleteDatabase(this.mDatabase);
      return this.mSQLiteOpenHelper.getWritableDatabase();
    } 
  }
  
  final long getRowCount() {
    if (this.mIMDB == null)
      try {
        return DatabaseUtils.queryNumEntries(getDatabase(), this.mTable);
      } catch (RuntimeException runtimeException) {
        switchToInMemory("count", runtimeException);
      }  
    return this.mIMDB.size();
  }
  
  Scanner getScanner(String paramString, Object paramObject) {
    return new Scanner(paramString, paramObject);
  }
  
  public long put(@NonNull ContentValues paramContentValues) {
    if (this.mIMDB == null)
      try {
        long l1 = getDatabase().insertOrThrow(this.mTable, null, paramContentValues);
        if (this.mMaxNumberOfRecords < getRowCount() && this.mMaxNumberOfRecords > 0) {
          Cursor cursor = getCursor(null, null);
          cursor.moveToNext();
          delete(cursor.getLong(0));
          cursor.close();
        } 
        return l1;
      } catch (RuntimeException runtimeException) {
        switchToInMemory("put", runtimeException);
      }  
    paramContentValues.put("oid", Long.valueOf(this.mIMDBAutoInc));
    this.mIMDB.put(Long.valueOf(this.mIMDBAutoInc), paramContentValues);
    long l = this.mIMDBAutoInc;
    this.mIMDBAutoInc = 1L + l;
    return l;
  }
  
  @VisibleForTesting
  void setSQLiteOpenHelper(@NonNull SQLiteOpenHelper paramSQLiteOpenHelper) {
    this.mSQLiteOpenHelper.close();
    this.mSQLiteOpenHelper = paramSQLiteOpenHelper;
  }
  
  @VisibleForTesting
  void switchToInMemory(String paramString, RuntimeException paramRuntimeException) {
    this.mIMDB = new LinkedHashMap<Long, ContentValues>() {
        protected boolean removeEldestEntry(Map.Entry<Long, ContentValues> param1Entry) {
          boolean bool;
          if (DatabaseManager.this.mMaxNumberOfRecords < size() && DatabaseManager.this.mMaxNumberOfRecords > 0) {
            bool = true;
          } else {
            bool = false;
          } 
          return bool;
        }
      };
    ErrorListener errorListener = this.mErrorListener;
    if (errorListener != null)
      errorListener.onError(paramString, paramRuntimeException); 
  }
  
  public boolean update(@IntRange(from = 0L) long paramLong, @NonNull ContentValues paramContentValues) {
    Map<Long, ContentValues> map = this.mIMDB;
    boolean bool = false;
    if (map == null)
      try {
        int i = getDatabase().update(this.mTable, paramContentValues, "oid = ?", new String[] { String.valueOf(paramLong) });
        if (i > 0)
          bool = true; 
        return bool;
      } catch (RuntimeException runtimeException) {
        switchToInMemory("update", runtimeException);
      }  
    ContentValues contentValues = this.mIMDB.get(Long.valueOf(paramLong));
    if (contentValues == null)
      return false; 
    contentValues.putAll(paramContentValues);
    return true;
  }
  
  static interface ErrorListener {
    void onError(String param1String, RuntimeException param1RuntimeException);
  }
  
  class Scanner implements Iterable<ContentValues>, Closeable {
    private Cursor cursor;
    
    private final String key;
    
    private final Object value;
    
    private Scanner(String param1String, Object param1Object) {
      this.key = param1String;
      this.value = param1Object;
    }
    
    public void close() {
      Cursor cursor = this.cursor;
      if (cursor != null)
        try {
          cursor.close();
          this.cursor = null;
        } catch (RuntimeException runtimeException) {
          DatabaseManager.this.switchToInMemory("scan.close", runtimeException);
        }  
    }
    
    public int getCount() {
      if (DatabaseManager.this.mIMDB == null)
        try {
          if (this.cursor == null)
            this.cursor = DatabaseManager.this.getCursor(this.key, this.value); 
          return this.cursor.getCount();
        } catch (RuntimeException runtimeException) {
          DatabaseManager.this.switchToInMemory("scan.count", runtimeException);
        }  
      byte b = 0;
      Iterator<ContentValues> iterator = iterator();
      while (iterator.hasNext()) {
        b++;
        iterator.next();
      } 
      return b;
    }
    
    public Iterator<ContentValues> iterator() {
      if (DatabaseManager.this.mIMDB == null)
        try {
          close();
          this.cursor = DatabaseManager.this.getCursor(this.key, this.value);
          return new Iterator<ContentValues>() {
              Boolean hasNext;
              
              public boolean hasNext() {
                if (this.hasNext == null)
                  try {
                    this.hasNext = Boolean.valueOf(DatabaseManager.Scanner.this.cursor.moveToNext());
                  } catch (RuntimeException runtimeException) {
                    this.hasNext = Boolean.FALSE;
                    try {
                      DatabaseManager.Scanner.this.cursor.close();
                    } catch (RuntimeException runtimeException1) {
                      AppCenterLog.warn("AppCenter", "Closing cursor failed", runtimeException1);
                    } 
                    DatabaseManager.Scanner.access$502(DatabaseManager.Scanner.this, null);
                    DatabaseManager.this.switchToInMemory("scan.hasNext", runtimeException);
                  }  
                return this.hasNext.booleanValue();
              }
              
              public ContentValues next() {
                if (hasNext()) {
                  this.hasNext = null;
                  return DatabaseManager.buildValues(DatabaseManager.Scanner.this.cursor, DatabaseManager.this.mSchema);
                } 
                throw new NoSuchElementException();
              }
              
              public void remove() {
                throw new UnsupportedOperationException();
              }
            };
        } catch (RuntimeException runtimeException) {
          DatabaseManager.this.switchToInMemory("scan.iterator", runtimeException);
        }  
      return new Iterator<ContentValues>() {
          boolean advanced;
          
          final Iterator<ContentValues> iterator = DatabaseManager.this.mIMDB.values().iterator();
          
          ContentValues next;
          
          public boolean hasNext() {
            boolean bool = this.advanced;
            boolean bool1 = true;
            if (!bool) {
              this.next = null;
              while (this.iterator.hasNext()) {
                ContentValues contentValues = this.iterator.next();
                Object object = contentValues.get(DatabaseManager.Scanner.this.key);
                if (DatabaseManager.Scanner.this.key == null || (DatabaseManager.Scanner.this.value != null && DatabaseManager.Scanner.this.value.equals(object)) || (DatabaseManager.Scanner.this.value == null && object == null)) {
                  this.next = contentValues;
                  break;
                } 
              } 
              this.advanced = true;
            } 
            if (this.next == null)
              bool1 = false; 
            return bool1;
          }
          
          public ContentValues next() {
            if (hasNext()) {
              this.advanced = false;
              return this.next;
            } 
            throw new NoSuchElementException();
          }
          
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
    }
  }
  
  class null implements Iterator<ContentValues> {
    Boolean hasNext;
    
    public boolean hasNext() {
      if (this.hasNext == null)
        try {
          this.hasNext = Boolean.valueOf(this.this$1.cursor.moveToNext());
        } catch (RuntimeException runtimeException) {
          this.hasNext = Boolean.FALSE;
          try {
            this.this$1.cursor.close();
          } catch (RuntimeException runtimeException1) {
            AppCenterLog.warn("AppCenter", "Closing cursor failed", runtimeException1);
          } 
          DatabaseManager.Scanner.access$502(this.this$1, null);
          DatabaseManager.this.switchToInMemory("scan.hasNext", runtimeException);
        }  
      return this.hasNext.booleanValue();
    }
    
    public ContentValues next() {
      if (hasNext()) {
        this.hasNext = null;
        return DatabaseManager.buildValues(this.this$1.cursor, DatabaseManager.this.mSchema);
      } 
      throw new NoSuchElementException();
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  class null implements Iterator<ContentValues> {
    boolean advanced;
    
    final Iterator<ContentValues> iterator = DatabaseManager.this.mIMDB.values().iterator();
    
    ContentValues next;
    
    public boolean hasNext() {
      boolean bool = this.advanced;
      boolean bool1 = true;
      if (!bool) {
        this.next = null;
        while (this.iterator.hasNext()) {
          ContentValues contentValues = this.iterator.next();
          Object object = contentValues.get(this.this$1.key);
          if (this.this$1.key == null || (this.this$1.value != null && this.this$1.value.equals(object)) || (this.this$1.value == null && object == null)) {
            this.next = contentValues;
            break;
          } 
        } 
        this.advanced = true;
      } 
      if (this.next == null)
        bool1 = false; 
      return bool1;
    }
    
    public ContentValues next() {
      if (hasNext()) {
        this.advanced = false;
        return this.next;
      } 
      throw new NoSuchElementException();
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/storage/DatabaseManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */