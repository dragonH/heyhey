package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
  private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String PARCEL_FONT_RESULTS = "font_results";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
  
  private static final String TAG = "FontsContractCompat";
  
  private static final SelfDestructiveThread sBackgroundThread;
  
  private static final Comparator<byte[]> sByteArrayComparator;
  
  private static final Object sLock;
  
  @GuardedBy("sLock")
  private static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>> sPendingReplies;
  
  private static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);
  
  static {
    sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    sLock = new Object();
    sPendingReplies = new SimpleArrayMap();
    sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
          if (param1ArrayOfbyte1.length != param1ArrayOfbyte2.length)
            return param1ArrayOfbyte1.length - param1ArrayOfbyte2.length; 
          for (byte b = 0; b < param1ArrayOfbyte1.length; b++) {
            byte b1 = param1ArrayOfbyte1[b];
            byte b2 = param1ArrayOfbyte2[b];
            if (b1 != b2)
              return b1 - b2; 
          } 
          return 0;
        }
      };
  }
  
  public static Typeface buildTypeface(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontInfo[] paramArrayOfFontInfo) {
    return TypefaceCompat.createFromFontInfo(paramContext, paramCancellationSignal, paramArrayOfFontInfo, 0);
  }
  
  private static List<byte[]> convertToByteArrayList(Signature[] paramArrayOfSignature) {
    ArrayList<byte[]> arrayList = new ArrayList();
    for (byte b = 0; b < paramArrayOfSignature.length; b++)
      arrayList.add(paramArrayOfSignature[b].toByteArray()); 
    return (List<byte[]>)arrayList;
  }
  
  private static boolean equalsByteArrayList(List<byte[]> paramList1, List<byte[]> paramList2) {
    if (paramList1.size() != paramList2.size())
      return false; 
    for (byte b = 0; b < paramList1.size(); b++) {
      if (!Arrays.equals(paramList1.get(b), paramList2.get(b)))
        return false; 
    } 
    return true;
  }
  
  @NonNull
  public static FontFamilyResult fetchFonts(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontRequest paramFontRequest) throws PackageManager.NameNotFoundException {
    ProviderInfo providerInfo = getProvider(paramContext.getPackageManager(), paramFontRequest, paramContext.getResources());
    return (providerInfo == null) ? new FontFamilyResult(1, null) : new FontFamilyResult(0, getFontFromProvider(paramContext, paramFontRequest, providerInfo.authority, paramCancellationSignal));
  }
  
  private static List<List<byte[]>> getCertificates(FontRequest paramFontRequest, Resources paramResources) {
    return (paramFontRequest.getCertificates() != null) ? paramFontRequest.getCertificates() : FontResourcesParserCompat.readCerts(paramResources, paramFontRequest.getCertificatesArrayResId());
  }
  
  @NonNull
  @VisibleForTesting
  static FontInfo[] getFontFromProvider(Context paramContext, FontRequest paramFontRequest, String paramString, CancellationSignal paramCancellationSignal) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: new android/net/Uri$Builder
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: ldc 'content'
    //   18: invokevirtual scheme : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   21: aload_2
    //   22: invokevirtual authority : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   25: invokevirtual build : ()Landroid/net/Uri;
    //   28: astore #5
    //   30: new android/net/Uri$Builder
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: ldc 'content'
    //   39: invokevirtual scheme : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   42: aload_2
    //   43: invokevirtual authority : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   46: ldc 'file'
    //   48: invokevirtual appendPath : (Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   51: invokevirtual build : ()Landroid/net/Uri;
    //   54: astore #6
    //   56: getstatic android/os/Build$VERSION.SDK_INT : I
    //   59: istore #7
    //   61: iload #7
    //   63: bipush #16
    //   65: if_icmple -> 149
    //   68: aload_0
    //   69: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   72: astore_0
    //   73: aload_1
    //   74: invokevirtual getQuery : ()Ljava/lang/String;
    //   77: astore_1
    //   78: aload_0
    //   79: aload #5
    //   81: bipush #7
    //   83: anewarray java/lang/String
    //   86: dup
    //   87: iconst_0
    //   88: ldc_w '_id'
    //   91: aastore
    //   92: dup
    //   93: iconst_1
    //   94: ldc_w 'file_id'
    //   97: aastore
    //   98: dup
    //   99: iconst_2
    //   100: ldc_w 'font_ttc_index'
    //   103: aastore
    //   104: dup
    //   105: iconst_3
    //   106: ldc_w 'font_variation_settings'
    //   109: aastore
    //   110: dup
    //   111: iconst_4
    //   112: ldc_w 'font_weight'
    //   115: aastore
    //   116: dup
    //   117: iconst_5
    //   118: ldc_w 'font_italic'
    //   121: aastore
    //   122: dup
    //   123: bipush #6
    //   125: ldc_w 'result_code'
    //   128: aastore
    //   129: ldc_w 'query = ?'
    //   132: iconst_1
    //   133: anewarray java/lang/String
    //   136: dup
    //   137: iconst_0
    //   138: aload_1
    //   139: aastore
    //   140: aconst_null
    //   141: aload_3
    //   142: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   145: astore_0
    //   146: goto -> 226
    //   149: aload_0
    //   150: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   153: astore_0
    //   154: aload_1
    //   155: invokevirtual getQuery : ()Ljava/lang/String;
    //   158: astore_1
    //   159: aload_0
    //   160: aload #5
    //   162: bipush #7
    //   164: anewarray java/lang/String
    //   167: dup
    //   168: iconst_0
    //   169: ldc_w '_id'
    //   172: aastore
    //   173: dup
    //   174: iconst_1
    //   175: ldc_w 'file_id'
    //   178: aastore
    //   179: dup
    //   180: iconst_2
    //   181: ldc_w 'font_ttc_index'
    //   184: aastore
    //   185: dup
    //   186: iconst_3
    //   187: ldc_w 'font_variation_settings'
    //   190: aastore
    //   191: dup
    //   192: iconst_4
    //   193: ldc_w 'font_weight'
    //   196: aastore
    //   197: dup
    //   198: iconst_5
    //   199: ldc_w 'font_italic'
    //   202: aastore
    //   203: dup
    //   204: bipush #6
    //   206: ldc_w 'result_code'
    //   209: aastore
    //   210: ldc_w 'query = ?'
    //   213: iconst_1
    //   214: anewarray java/lang/String
    //   217: dup
    //   218: iconst_0
    //   219: aload_1
    //   220: aastore
    //   221: aconst_null
    //   222: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   225: astore_0
    //   226: aload_0
    //   227: ifnull -> 493
    //   230: aload_0
    //   231: invokeinterface getCount : ()I
    //   236: ifle -> 493
    //   239: aload_0
    //   240: ldc_w 'result_code'
    //   243: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   248: istore #8
    //   250: new java/util/ArrayList
    //   253: astore_2
    //   254: aload_2
    //   255: invokespecial <init> : ()V
    //   258: aload_0
    //   259: ldc_w '_id'
    //   262: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   267: istore #9
    //   269: aload_0
    //   270: ldc_w 'file_id'
    //   273: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   278: istore #10
    //   280: aload_0
    //   281: ldc_w 'font_ttc_index'
    //   284: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   289: istore #11
    //   291: aload_0
    //   292: ldc_w 'font_weight'
    //   295: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   300: istore #12
    //   302: aload_0
    //   303: ldc_w 'font_italic'
    //   306: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   311: istore #13
    //   313: aload_0
    //   314: invokeinterface moveToNext : ()Z
    //   319: ifeq -> 480
    //   322: iload #8
    //   324: iconst_m1
    //   325: if_icmpeq -> 341
    //   328: aload_0
    //   329: iload #8
    //   331: invokeinterface getInt : (I)I
    //   336: istore #7
    //   338: goto -> 344
    //   341: iconst_0
    //   342: istore #7
    //   344: iload #11
    //   346: iconst_m1
    //   347: if_icmpeq -> 363
    //   350: aload_0
    //   351: iload #11
    //   353: invokeinterface getInt : (I)I
    //   358: istore #14
    //   360: goto -> 366
    //   363: iconst_0
    //   364: istore #14
    //   366: iload #10
    //   368: iconst_m1
    //   369: if_icmpne -> 389
    //   372: aload #5
    //   374: aload_0
    //   375: iload #9
    //   377: invokeinterface getLong : (I)J
    //   382: invokestatic withAppendedId : (Landroid/net/Uri;J)Landroid/net/Uri;
    //   385: astore_1
    //   386: goto -> 403
    //   389: aload #6
    //   391: aload_0
    //   392: iload #10
    //   394: invokeinterface getLong : (I)J
    //   399: invokestatic withAppendedId : (Landroid/net/Uri;J)Landroid/net/Uri;
    //   402: astore_1
    //   403: iload #12
    //   405: iconst_m1
    //   406: if_icmpeq -> 422
    //   409: aload_0
    //   410: iload #12
    //   412: invokeinterface getInt : (I)I
    //   417: istore #15
    //   419: goto -> 427
    //   422: sipush #400
    //   425: istore #15
    //   427: iload #13
    //   429: iconst_m1
    //   430: if_icmpeq -> 451
    //   433: aload_0
    //   434: iload #13
    //   436: invokeinterface getInt : (I)I
    //   441: iconst_1
    //   442: if_icmpne -> 451
    //   445: iconst_1
    //   446: istore #16
    //   448: goto -> 454
    //   451: iconst_0
    //   452: istore #16
    //   454: new android/support/v4/provider/FontsContractCompat$FontInfo
    //   457: astore_3
    //   458: aload_3
    //   459: aload_1
    //   460: iload #14
    //   462: iload #15
    //   464: iload #16
    //   466: iload #7
    //   468: invokespecial <init> : (Landroid/net/Uri;IIZI)V
    //   471: aload_2
    //   472: aload_3
    //   473: invokevirtual add : (Ljava/lang/Object;)Z
    //   476: pop
    //   477: goto -> 313
    //   480: aload_2
    //   481: astore_1
    //   482: goto -> 496
    //   485: astore_2
    //   486: aload_0
    //   487: astore_1
    //   488: aload_2
    //   489: astore_0
    //   490: goto -> 521
    //   493: aload #4
    //   495: astore_1
    //   496: aload_0
    //   497: ifnull -> 506
    //   500: aload_0
    //   501: invokeinterface close : ()V
    //   506: aload_1
    //   507: iconst_0
    //   508: anewarray android/support/v4/provider/FontsContractCompat$FontInfo
    //   511: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   514: checkcast [Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   517: areturn
    //   518: astore_0
    //   519: aconst_null
    //   520: astore_1
    //   521: aload_1
    //   522: ifnull -> 531
    //   525: aload_1
    //   526: invokeinterface close : ()V
    //   531: goto -> 536
    //   534: aload_0
    //   535: athrow
    //   536: goto -> 534
    // Exception table:
    //   from	to	target	type
    //   56	61	518	finally
    //   68	146	518	finally
    //   149	226	518	finally
    //   230	313	485	finally
    //   313	322	485	finally
    //   328	338	485	finally
    //   350	360	485	finally
    //   372	386	485	finally
    //   389	403	485	finally
    //   409	419	485	finally
    //   433	445	485	finally
    //   454	477	485	finally
  }
  
  private static Typeface getFontInternal(Context paramContext, FontRequest paramFontRequest, int paramInt) {
    try {
      FontFamilyResult fontFamilyResult = fetchFonts(paramContext, null, paramFontRequest);
      if (fontFamilyResult.getStatusCode() == 0)
        return TypefaceCompat.createFromFontInfo(paramContext, null, fontFamilyResult.getFonts(), paramInt); 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return null;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFontSync(final Context context, final FontRequest request, @Nullable final TextView targetView, int paramInt1, int paramInt2, final int style) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(request.getIdentifier());
    stringBuilder.append("-");
    stringBuilder.append(style);
    final String id = stringBuilder.toString();
    Typeface typeface = (Typeface)sTypefaceCache.get(str);
    if (typeface != null)
      return typeface; 
    if (paramInt1 == 0) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0 && paramInt2 == -1)
      return getFontInternal(context, request, style); 
    null = new Callable<Typeface>() {
        public Typeface call() throws Exception {
          Typeface typeface = FontsContractCompat.getFontInternal(context, request, style);
          if (typeface != null)
            FontsContractCompat.sTypefaceCache.put(id, typeface); 
          return typeface;
        }
      };
    if (paramInt1 != 0)
      try {
        return sBackgroundThread.<Typeface>postAndWait(null, paramInt2);
      } catch (InterruptedException interruptedException) {
        return null;
      }  
    SelfDestructiveThread.ReplyCallback<Typeface> replyCallback = new SelfDestructiveThread.ReplyCallback<Typeface>() {
        public void onReply(Typeface param1Typeface) {
          if ((TextView)textViewWeak.get() != null)
            targetView.setTypeface(param1Typeface, style); 
        }
      };
    synchronized (sLock) {
      SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>> simpleArrayMap = sPendingReplies;
      if (simpleArrayMap.containsKey(str)) {
        ((ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>)simpleArrayMap.get(str)).add(replyCallback);
        return null;
      } 
      ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>> arrayList = new ArrayList();
      this();
      arrayList.add(replyCallback);
      simpleArrayMap.put(str, arrayList);
      sBackgroundThread.postAndReply(null, new SelfDestructiveThread.ReplyCallback<Typeface>() {
            public void onReply(Typeface param1Typeface) {
              synchronized (FontsContractCompat.sLock) {
                ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>> arrayList = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
                FontsContractCompat.sPendingReplies.remove(id);
                for (byte b = 0; b < arrayList.size(); b++)
                  ((SelfDestructiveThread.ReplyCallback<Typeface>)arrayList.get(b)).onReply(param1Typeface); 
                return;
              } 
            }
          });
      return null;
    } 
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  @VisibleForTesting
  public static ProviderInfo getProvider(@NonNull PackageManager paramPackageManager, @NonNull FontRequest paramFontRequest, @Nullable Resources paramResources) throws PackageManager.NameNotFoundException {
    String str = paramFontRequest.getProviderAuthority();
    byte b = 0;
    ProviderInfo providerInfo = paramPackageManager.resolveContentProvider(str, 0);
    if (providerInfo != null) {
      ArrayList<byte> arrayList;
      if (providerInfo.packageName.equals(paramFontRequest.getProviderPackage())) {
        List<byte[]> list = convertToByteArrayList((paramPackageManager.getPackageInfo(providerInfo.packageName, 64)).signatures);
        Collections.sort((List)list, (Comparator)sByteArrayComparator);
        List<List<byte[]>> list1 = getCertificates(paramFontRequest, paramResources);
        while (b < list1.size()) {
          arrayList = new ArrayList(list1.get(b));
          Collections.sort(arrayList, (Comparator)sByteArrayComparator);
          if (equalsByteArrayList(list, (List)arrayList))
            return providerInfo; 
          b++;
        } 
        return null;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Found content provider ");
      stringBuilder1.append(str);
      stringBuilder1.append(", but package was not ");
      stringBuilder1.append(arrayList.getProviderPackage());
      throw new PackageManager.NameNotFoundException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No package found for authority: ");
    stringBuilder.append(str);
    PackageManager.NameNotFoundException nameNotFoundException = new PackageManager.NameNotFoundException(stringBuilder.toString());
    throw nameNotFoundException;
  }
  
  @RequiresApi(19)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Map<Uri, ByteBuffer> prepareFontData(Context paramContext, FontInfo[] paramArrayOfFontInfo, CancellationSignal paramCancellationSignal) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = paramArrayOfFontInfo.length;
    for (byte b = 0; b < i; b++) {
      FontInfo fontInfo = paramArrayOfFontInfo[b];
      if (fontInfo.getResultCode() == 0) {
        Uri uri = fontInfo.getUri();
        if (!hashMap.containsKey(uri))
          hashMap.put(uri, TypefaceCompatUtil.mmap(paramContext, paramCancellationSignal, uri)); 
      } 
    } 
    return (Map)Collections.unmodifiableMap(hashMap);
  }
  
  public static void requestFont(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final FontRequestCallback callback, @NonNull Handler paramHandler) {
    paramHandler.post(new Runnable() {
          public void run() {
            try {
              FontsContractCompat.FontFamilyResult fontFamilyResult = FontsContractCompat.fetchFonts(context, null, request);
              if (fontFamilyResult.getStatusCode() != 0) {
                int k = fontFamilyResult.getStatusCode();
                if (k != 1) {
                  if (k != 2) {
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                    return;
                  } 
                  callerThreadHandler.post(new Runnable() {
                        public void run() {
                          callback.onTypefaceRequestFailed(-3);
                        }
                      });
                  return;
                } 
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(-2);
                      }
                    });
                return;
              } 
              FontsContractCompat.FontInfo[] arrayOfFontInfo = fontFamilyResult.getFonts();
              if (arrayOfFontInfo == null || arrayOfFontInfo.length == 0) {
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(1);
                      }
                    });
                return;
              } 
              int j = arrayOfFontInfo.length;
              for (final int resultCode = 0; i < j; i++) {
                FontsContractCompat.FontInfo fontInfo = arrayOfFontInfo[i];
                if (fontInfo.getResultCode() != 0) {
                  i = fontInfo.getResultCode();
                  if (i < 0) {
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(-3);
                          }
                        });
                  } else {
                    callerThreadHandler.post(new Runnable() {
                          public void run() {
                            callback.onTypefaceRequestFailed(resultCode);
                          }
                        });
                  } 
                  return;
                } 
              } 
              final Typeface typeface = FontsContractCompat.buildTypeface(context, null, arrayOfFontInfo);
              if (typeface == null) {
                callerThreadHandler.post(new Runnable() {
                      public void run() {
                        callback.onTypefaceRequestFailed(-3);
                      }
                    });
                return;
              } 
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRetrieved(typeface);
                    }
                  });
              return;
            } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
              callerThreadHandler.post(new Runnable() {
                    public void run() {
                      callback.onTypefaceRequestFailed(-1);
                    }
                  });
              return;
            } 
          }
        });
  }
  
  public static final class Columns implements BaseColumns {
    public static final String FILE_ID = "file_id";
    
    public static final String ITALIC = "font_italic";
    
    public static final String RESULT_CODE = "result_code";
    
    public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
    
    public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
    
    public static final int RESULT_CODE_MALFORMED_QUERY = 3;
    
    public static final int RESULT_CODE_OK = 0;
    
    public static final String TTC_INDEX = "font_ttc_index";
    
    public static final String VARIATION_SETTINGS = "font_variation_settings";
    
    public static final String WEIGHT = "font_weight";
  }
  
  public static class FontFamilyResult {
    public static final int STATUS_OK = 0;
    
    public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
    
    public static final int STATUS_WRONG_CERTIFICATES = 1;
    
    private final FontsContractCompat.FontInfo[] mFonts;
    
    private final int mStatusCode;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontFamilyResult(int param1Int, @Nullable FontsContractCompat.FontInfo[] param1ArrayOfFontInfo) {
      this.mStatusCode = param1Int;
      this.mFonts = param1ArrayOfFontInfo;
    }
    
    public FontsContractCompat.FontInfo[] getFonts() {
      return this.mFonts;
    }
    
    public int getStatusCode() {
      return this.mStatusCode;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static @interface FontResultStatus {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static @interface FontResultStatus {}
  
  public static class FontInfo {
    private final boolean mItalic;
    
    private final int mResultCode;
    
    private final int mTtcIndex;
    
    private final Uri mUri;
    
    private final int mWeight;
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontInfo(@NonNull Uri param1Uri, @IntRange(from = 0L) int param1Int1, @IntRange(from = 1L, to = 1000L) int param1Int2, boolean param1Boolean, int param1Int3) {
      this.mUri = (Uri)Preconditions.checkNotNull(param1Uri);
      this.mTtcIndex = param1Int1;
      this.mWeight = param1Int2;
      this.mItalic = param1Boolean;
      this.mResultCode = param1Int3;
    }
    
    public int getResultCode() {
      return this.mResultCode;
    }
    
    @IntRange(from = 0L)
    public int getTtcIndex() {
      return this.mTtcIndex;
    }
    
    @NonNull
    public Uri getUri() {
      return this.mUri;
    }
    
    @IntRange(from = 1L, to = 1000L)
    public int getWeight() {
      return this.mWeight;
    }
    
    public boolean isItalic() {
      return this.mItalic;
    }
  }
  
  public static class FontRequestCallback {
    public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
    
    public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
    
    public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
    
    public static final int FAIL_REASON_MALFORMED_QUERY = 3;
    
    public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
    
    public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
    
    public void onTypefaceRequestFailed(int param1Int) {}
    
    public void onTypefaceRetrieved(Typeface param1Typeface) {}
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static @interface FontRequestFailReason {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static @interface FontRequestFailReason {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/provider/FontsContractCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */