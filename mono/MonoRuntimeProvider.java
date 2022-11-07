package mono;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

public class MonoRuntimeProvider extends ContentProvider {
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   4: astore_3
    //   5: getstatic android/os/Build$VERSION.SDK_INT : I
    //   8: bipush #21
    //   10: if_icmplt -> 63
    //   13: aload_3
    //   14: getfield splitPublicSourceDirs : [Ljava/lang/String;
    //   17: astore #4
    //   19: aload #4
    //   21: ifnull -> 63
    //   24: aload #4
    //   26: arraylength
    //   27: ifle -> 63
    //   30: aload #4
    //   32: arraylength
    //   33: iconst_1
    //   34: iadd
    //   35: anewarray java/lang/String
    //   38: astore #5
    //   40: aload #5
    //   42: iconst_0
    //   43: aload_3
    //   44: getfield sourceDir : Ljava/lang/String;
    //   47: aastore
    //   48: aload #4
    //   50: iconst_0
    //   51: aload #5
    //   53: iconst_1
    //   54: aload #4
    //   56: arraylength
    //   57: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   60: goto -> 66
    //   63: aconst_null
    //   64: astore #5
    //   66: aload #5
    //   68: astore #4
    //   70: aload #5
    //   72: ifnonnull -> 89
    //   75: iconst_1
    //   76: anewarray java/lang/String
    //   79: astore #4
    //   81: aload #4
    //   83: iconst_0
    //   84: aload_3
    //   85: getfield sourceDir : Ljava/lang/String;
    //   88: aastore
    //   89: aload_1
    //   90: aload_3
    //   91: aload #4
    //   93: invokestatic LoadApplication : (Landroid/content/Context;Landroid/content/pm/ApplicationInfo;[Ljava/lang/String;)V
    //   96: aload_0
    //   97: aload_1
    //   98: aload_2
    //   99: invokespecial attachInfo : (Landroid/content/Context;Landroid/content/pm/ProviderInfo;)V
    //   102: return
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public String getType(Uri paramUri) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public boolean onCreate() {
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    throw new RuntimeException("This operation is not supported.");
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    throw new RuntimeException("This operation is not supported.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/MonoRuntimeProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */