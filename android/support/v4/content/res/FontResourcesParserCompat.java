package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.support.v4.provider.FontRequest;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FontResourcesParserCompat {
  private static final int DEFAULT_TIMEOUT_MILLIS = 500;
  
  public static final int FETCH_STRATEGY_ASYNC = 1;
  
  public static final int FETCH_STRATEGY_BLOCKING = 0;
  
  public static final int INFINITE_TIMEOUT_VALUE = -1;
  
  private static final int ITALIC = 1;
  
  private static final int NORMAL_WEIGHT = 400;
  
  @Nullable
  public static FamilyResourceEntry parse(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    int i;
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return readFamilies(paramXmlPullParser, paramResources); 
    XmlPullParserException xmlPullParserException = new XmlPullParserException("No start tag found");
    throw xmlPullParserException;
  }
  
  public static List<List<byte[]>> readCerts(Resources paramResources, @ArrayRes int paramInt) {
    List<?> list = null;
    ArrayList<List<byte[]>> arrayList = null;
    if (paramInt != 0) {
      TypedArray typedArray = paramResources.obtainTypedArray(paramInt);
      list = arrayList;
      if (typedArray.length() > 0) {
        boolean bool;
        arrayList = new ArrayList();
        if (typedArray.getResourceId(0, 0) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool) {
          paramInt = 0;
          while (true) {
            list = arrayList;
            if (paramInt < typedArray.length()) {
              arrayList.add(toByteArrayList(paramResources.getStringArray(typedArray.getResourceId(paramInt, 0))));
              paramInt++;
              continue;
            } 
            break;
          } 
        } else {
          arrayList.add(toByteArrayList(paramResources.getStringArray(paramInt)));
          list = arrayList;
        } 
      } 
      typedArray.recycle();
    } 
    if (list == null)
      list = Collections.emptyList(); 
    return (List)list;
  }
  
  @Nullable
  private static FamilyResourceEntry readFamilies(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    paramXmlPullParser.require(2, null, "font-family");
    if (paramXmlPullParser.getName().equals("font-family"))
      return readFamily(paramXmlPullParser, paramResources); 
    skip(paramXmlPullParser);
    return null;
  }
  
  @Nullable
  private static FamilyResourceEntry readFamily(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamily);
    String str1 = typedArray.getString(R.styleable.FontFamily_fontProviderAuthority);
    String str2 = typedArray.getString(R.styleable.FontFamily_fontProviderPackage);
    String str3 = typedArray.getString(R.styleable.FontFamily_fontProviderQuery);
    int i = typedArray.getResourceId(R.styleable.FontFamily_fontProviderCerts, 0);
    int j = typedArray.getInteger(R.styleable.FontFamily_fontProviderFetchStrategy, 1);
    int k = typedArray.getInteger(R.styleable.FontFamily_fontProviderFetchTimeout, 500);
    typedArray.recycle();
    if (str1 != null && str2 != null && str3 != null) {
      while (paramXmlPullParser.next() != 3)
        skip(paramXmlPullParser); 
      return new ProviderResourceEntry(new FontRequest(str1, str2, str3, readCerts(paramResources, i)), j, k);
    } 
    ArrayList<FontFileResourceEntry> arrayList = new ArrayList();
    while (paramXmlPullParser.next() != 3) {
      if (paramXmlPullParser.getEventType() != 2)
        continue; 
      if (paramXmlPullParser.getName().equals("font")) {
        arrayList.add(readFont(paramXmlPullParser, paramResources));
        continue;
      } 
      skip(paramXmlPullParser);
    } 
    return arrayList.isEmpty() ? null : new FontFamilyFilesResourceEntry(arrayList.<FontFileResourceEntry>toArray(new FontFileResourceEntry[arrayList.size()]));
  }
  
  private static FontFileResourceEntry readFont(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamilyFont);
    int i = typedArray.getInt(R.styleable.FontFamilyFont_fontWeight, 400);
    int j = typedArray.getInt(R.styleable.FontFamilyFont_fontStyle, 0);
    boolean bool = true;
    if (1 != j)
      bool = false; 
    j = R.styleable.FontFamilyFont_font;
    int k = typedArray.getResourceId(j, 0);
    String str = typedArray.getString(j);
    typedArray.recycle();
    while (paramXmlPullParser.next() != 3)
      skip(paramXmlPullParser); 
    return new FontFileResourceEntry(str, i, bool, k);
  }
  
  private static void skip(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    for (byte b = 1; b; b++) {
      int i = paramXmlPullParser.next();
      if (i != 2) {
        if (i != 3)
          continue; 
        b--;
        continue;
      } 
    } 
  }
  
  private static List<byte[]> toByteArrayList(String[] paramArrayOfString) {
    ArrayList<byte[]> arrayList = new ArrayList();
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(Base64.decode(paramArrayOfString[b], 0)); 
    return (List<byte[]>)arrayList;
  }
  
  public static interface FamilyResourceEntry {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FetchStrategy {}
  
  public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
    @NonNull
    private final FontResourcesParserCompat.FontFileResourceEntry[] mEntries;
    
    public FontFamilyFilesResourceEntry(@NonNull FontResourcesParserCompat.FontFileResourceEntry[] param1ArrayOfFontFileResourceEntry) {
      this.mEntries = param1ArrayOfFontFileResourceEntry;
    }
    
    @NonNull
    public FontResourcesParserCompat.FontFileResourceEntry[] getEntries() {
      return this.mEntries;
    }
  }
  
  public static final class FontFileResourceEntry {
    @NonNull
    private final String mFileName;
    
    private boolean mItalic;
    
    private int mResourceId;
    
    private int mWeight;
    
    public FontFileResourceEntry(@NonNull String param1String, int param1Int1, boolean param1Boolean, int param1Int2) {
      this.mFileName = param1String;
      this.mWeight = param1Int1;
      this.mItalic = param1Boolean;
      this.mResourceId = param1Int2;
    }
    
    @NonNull
    public String getFileName() {
      return this.mFileName;
    }
    
    public int getResourceId() {
      return this.mResourceId;
    }
    
    public int getWeight() {
      return this.mWeight;
    }
    
    public boolean isItalic() {
      return this.mItalic;
    }
  }
  
  public static final class ProviderResourceEntry implements FamilyResourceEntry {
    @NonNull
    private final FontRequest mRequest;
    
    private final int mStrategy;
    
    private final int mTimeoutMs;
    
    public ProviderResourceEntry(@NonNull FontRequest param1FontRequest, int param1Int1, int param1Int2) {
      this.mRequest = param1FontRequest;
      this.mStrategy = param1Int1;
      this.mTimeoutMs = param1Int2;
    }
    
    public int getFetchStrategy() {
      return this.mStrategy;
    }
    
    @NonNull
    public FontRequest getRequest() {
      return this.mRequest;
    }
    
    public int getTimeout() {
      return this.mTimeoutMs;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/res/FontResourcesParserCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */