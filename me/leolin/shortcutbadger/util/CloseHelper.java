package me.leolin.shortcutbadger.util;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

public class CloseHelper {
  public static void close(Cursor paramCursor) {
    if (paramCursor != null && !paramCursor.isClosed())
      paramCursor.close(); 
  }
  
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/util/CloseHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */