package com.microsoft.appcenter.utils.crypto;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Base64;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class CryptoUtils {
  @VisibleForTesting
  static final ICryptoFactory DEFAULT_CRYPTO_FACTORY = new ICryptoFactory() {
      public CryptoUtils.ICipher getCipher(String param1String1, String param1String2) throws Exception {
        return new CryptoUtils.ICipher() {
            public byte[] doFinal(byte[] param2ArrayOfbyte) throws Exception {
              return cipher.doFinal(param2ArrayOfbyte);
            }
            
            public byte[] doFinal(byte[] param2ArrayOfbyte, int param2Int1, int param2Int2) throws Exception {
              return cipher.doFinal(param2ArrayOfbyte, param2Int1, param2Int2);
            }
            
            public String getAlgorithm() {
              return cipher.getAlgorithm();
            }
            
            public int getBlockSize() {
              return cipher.getBlockSize();
            }
            
            public byte[] getIV() {
              return cipher.getIV();
            }
            
            public String getProvider() {
              return cipher.getProvider().getName();
            }
            
            public void init(int param2Int, Key param2Key) throws Exception {
              cipher.init(param2Int, param2Key);
            }
            
            public void init(int param2Int, Key param2Key, AlgorithmParameterSpec param2AlgorithmParameterSpec) throws Exception {
              cipher.init(param2Int, param2Key, param2AlgorithmParameterSpec);
            }
          };
      }
      
      public CryptoUtils.IKeyGenerator getKeyGenerator(String param1String1, String param1String2) throws Exception {
        return new CryptoUtils.IKeyGenerator() {
            public void generateKey() {
              keyGenerator.generateKey();
            }
            
            public void init(AlgorithmParameterSpec param2AlgorithmParameterSpec) throws Exception {
              keyGenerator.init(param2AlgorithmParameterSpec);
            }
          };
      }
    };
  
  @SuppressLint({"StaticFieldLeak"})
  private static CryptoUtils sInstance;
  
  private final int mApiLevel;
  
  private final Context mContext;
  
  private final ICryptoFactory mCryptoFactory;
  
  private final Map<String, CryptoHandlerEntry> mCryptoHandlers;
  
  private final KeyStore mKeyStore;
  
  private CryptoUtils(@NonNull Context paramContext) {
    this(paramContext, DEFAULT_CRYPTO_FACTORY, Build.VERSION.SDK_INT);
  }
  
  @TargetApi(23)
  @VisibleForTesting
  CryptoUtils(@NonNull Context paramContext, @NonNull ICryptoFactory paramICryptoFactory, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: new java/util/LinkedHashMap
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: putfield mCryptoHandlers : Ljava/util/Map;
    //   15: aload_0
    //   16: aload_1
    //   17: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   20: putfield mContext : Landroid/content/Context;
    //   23: aload_0
    //   24: aload_2
    //   25: putfield mCryptoFactory : Lcom/microsoft/appcenter/utils/crypto/CryptoUtils$ICryptoFactory;
    //   28: aload_0
    //   29: iload_3
    //   30: putfield mApiLevel : I
    //   33: aconst_null
    //   34: astore_1
    //   35: aconst_null
    //   36: astore_2
    //   37: iload_3
    //   38: bipush #19
    //   40: if_icmplt -> 65
    //   43: ldc 'AndroidKeyStore'
    //   45: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/KeyStore;
    //   48: astore_1
    //   49: aload_1
    //   50: aconst_null
    //   51: invokevirtual load : (Ljava/security/KeyStore$LoadStoreParameter;)V
    //   54: goto -> 65
    //   57: astore_2
    //   58: ldc 'AppCenter'
    //   60: ldc 'Cannot use secure keystore on this device.'
    //   62: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   65: aload_0
    //   66: aload_1
    //   67: putfield mKeyStore : Ljava/security/KeyStore;
    //   70: aload_1
    //   71: ifnull -> 104
    //   74: iload_3
    //   75: bipush #23
    //   77: if_icmplt -> 104
    //   80: new com/microsoft/appcenter/utils/crypto/CryptoAesHandler
    //   83: astore_2
    //   84: aload_2
    //   85: invokespecial <init> : ()V
    //   88: aload_0
    //   89: aload_2
    //   90: invokespecial registerHandler : (Lcom/microsoft/appcenter/utils/crypto/CryptoHandler;)V
    //   93: goto -> 104
    //   96: astore_2
    //   97: ldc 'AppCenter'
    //   99: ldc 'Cannot use modern encryption on this device.'
    //   101: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload_1
    //   105: ifnull -> 132
    //   108: new com/microsoft/appcenter/utils/crypto/CryptoRsaHandler
    //   111: astore_1
    //   112: aload_1
    //   113: invokespecial <init> : ()V
    //   116: aload_0
    //   117: aload_1
    //   118: invokespecial registerHandler : (Lcom/microsoft/appcenter/utils/crypto/CryptoHandler;)V
    //   121: goto -> 132
    //   124: astore_1
    //   125: ldc 'AppCenter'
    //   127: ldc 'Cannot use old encryption on this device.'
    //   129: invokestatic error : (Ljava/lang/String;Ljava/lang/String;)V
    //   132: new com/microsoft/appcenter/utils/crypto/CryptoNoOpHandler
    //   135: dup
    //   136: invokespecial <init> : ()V
    //   139: astore_1
    //   140: aload_0
    //   141: getfield mCryptoHandlers : Ljava/util/Map;
    //   144: aload_1
    //   145: invokevirtual getAlgorithm : ()Ljava/lang/String;
    //   148: new com/microsoft/appcenter/utils/crypto/CryptoUtils$CryptoHandlerEntry
    //   151: dup
    //   152: iconst_0
    //   153: aload_1
    //   154: invokespecial <init> : (ILcom/microsoft/appcenter/utils/crypto/CryptoHandler;)V
    //   157: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   162: pop
    //   163: return
    //   164: astore_1
    //   165: aload_2
    //   166: astore_1
    //   167: goto -> 58
    // Exception table:
    //   from	to	target	type
    //   43	49	164	java/lang/Exception
    //   49	54	57	java/lang/Exception
    //   80	93	96	java/lang/Exception
    //   108	121	124	java/lang/Exception
  }
  
  @NonNull
  private String getAlias(@NonNull CryptoHandler paramCryptoHandler, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("appcenter.");
    stringBuilder.append(paramInt);
    stringBuilder.append(".");
    stringBuilder.append(paramCryptoHandler.getAlgorithm());
    return stringBuilder.toString();
  }
  
  public static CryptoUtils getInstance(@NonNull Context paramContext) {
    if (sInstance == null)
      sInstance = new CryptoUtils(paramContext); 
    return sInstance;
  }
  
  @Nullable
  private KeyStore.Entry getKeyStoreEntry(@NonNull CryptoHandlerEntry paramCryptoHandlerEntry) throws Exception {
    if (this.mKeyStore == null)
      return null; 
    String str = getAlias(paramCryptoHandlerEntry.mCryptoHandler, paramCryptoHandlerEntry.mAliasIndex);
    return this.mKeyStore.getEntry(str, null);
  }
  
  private void registerHandler(@NonNull CryptoHandler paramCryptoHandler) throws Exception {
    boolean bool1 = false;
    String str1 = getAlias(paramCryptoHandler, 0);
    String str2 = getAlias(paramCryptoHandler, 1);
    Date date1 = this.mKeyStore.getCreationDate(str1);
    Date date2 = this.mKeyStore.getCreationDate(str2);
    boolean bool2 = bool1;
    String str3 = str1;
    if (date2 != null) {
      bool2 = bool1;
      str3 = str1;
      if (date2.after(date1)) {
        str3 = str2;
        bool2 = true;
      } 
    } 
    if (this.mCryptoHandlers.isEmpty() && !this.mKeyStore.containsAlias(str3)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Creating alias: ");
      stringBuilder1.append(str3);
      AppCenterLog.debug("AppCenter", stringBuilder1.toString());
      paramCryptoHandler.generateKey(this.mCryptoFactory, str3, this.mContext);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Using ");
    stringBuilder.append(str3);
    AppCenterLog.debug("AppCenter", stringBuilder.toString());
    this.mCryptoHandlers.put(paramCryptoHandler.getAlgorithm(), new CryptoHandlerEntry(bool2, paramCryptoHandler));
  }
  
  @NonNull
  public DecryptedData decrypt(@Nullable String paramString) {
    if (paramString == null)
      return new DecryptedData(null, null); 
    String[] arrayOfString = paramString.split(":");
    if (arrayOfString.length == 2) {
      entry = (KeyStore.Entry)this.mCryptoHandlers.get(arrayOfString[0]);
    } else {
      entry = null;
    } 
    if (entry == null) {
      cryptoHandler = null;
    } else {
      cryptoHandler = ((CryptoHandlerEntry)entry).mCryptoHandler;
    } 
    if (cryptoHandler != null)
      try {
        entry = getKeyStoreEntry((CryptoHandlerEntry)entry);
        byte[] arrayOfByte = cryptoHandler.decrypt(this.mCryptoFactory, this.mApiLevel, entry, Base64.decode(arrayOfString[1], 0));
        String str = new String();
        this(arrayOfByte, "UTF-8");
        if (cryptoHandler != ((CryptoHandlerEntry)this.mCryptoHandlers.values().iterator().next()).mCryptoHandler) {
          String str1 = encrypt(str);
        } else {
          cryptoHandler = null;
        } 
        return new DecryptedData(str, (String)cryptoHandler);
      } catch (Exception exception) {
        AppCenterLog.error("AppCenter", "Failed to decrypt data.");
        return new DecryptedData(paramString, null);
      }  
    IllegalStateException illegalStateException = new IllegalStateException();
    this("Could not find crypto handler that was used for the specified data.");
    KeyStore.Entry entry;
    CryptoHandler cryptoHandler;
    throw illegalStateException;
  }
  
  @Nullable
  public String encrypt(@Nullable String paramString) {
    if (paramString == null)
      return null; 
    try {
      CryptoHandlerEntry cryptoHandlerEntry = this.mCryptoHandlers.values().iterator().next();
      CryptoHandler cryptoHandler = cryptoHandlerEntry.mCryptoHandler;
      try {
        KeyStore.Entry entry = getKeyStoreEntry(cryptoHandlerEntry);
        null = Base64.encodeToString(cryptoHandler.encrypt(this.mCryptoFactory, this.mApiLevel, entry, paramString.getBytes("UTF-8")), 0);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(cryptoHandler.getAlgorithm());
        stringBuilder.append(":");
        stringBuilder.append(null);
        return stringBuilder.toString();
      } catch (InvalidKeyException invalidKeyException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Alias expired: ");
        stringBuilder.append(cryptoHandlerEntry.mAliasIndex);
        AppCenterLog.debug("AppCenter", stringBuilder.toString());
        int i = cryptoHandlerEntry.mAliasIndex ^ 0x1;
        cryptoHandlerEntry.mAliasIndex = i;
        String str = getAlias(cryptoHandler, i);
        if (this.mKeyStore.containsAlias(str)) {
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Deleting alias: ");
          stringBuilder.append(str);
          AppCenterLog.debug("AppCenter", stringBuilder.toString());
          this.mKeyStore.deleteEntry(str);
        } 
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Creating alias: ");
        stringBuilder.append(str);
        AppCenterLog.debug("AppCenter", stringBuilder.toString());
        cryptoHandler.generateKey(this.mCryptoFactory, str, this.mContext);
        return encrypt(paramString);
      } 
    } catch (Exception exception) {
      AppCenterLog.error("AppCenter", "Failed to encrypt data.");
      return paramString;
    } 
  }
  
  @VisibleForTesting
  ICryptoFactory getCryptoFactory() {
    return this.mCryptoFactory;
  }
  
  private static class CryptoHandlerEntry {
    int mAliasIndex;
    
    final CryptoHandler mCryptoHandler;
    
    CryptoHandlerEntry(int param1Int, CryptoHandler param1CryptoHandler) {
      this.mAliasIndex = param1Int;
      this.mCryptoHandler = param1CryptoHandler;
    }
  }
  
  public static class DecryptedData {
    final String mDecryptedData;
    
    final String mNewEncryptedData;
    
    @VisibleForTesting
    public DecryptedData(String param1String1, String param1String2) {
      this.mDecryptedData = param1String1;
      this.mNewEncryptedData = param1String2;
    }
    
    public String getDecryptedData() {
      return this.mDecryptedData;
    }
    
    public String getNewEncryptedData() {
      return this.mNewEncryptedData;
    }
  }
  
  static interface ICipher {
    byte[] doFinal(byte[] param1ArrayOfbyte) throws Exception;
    
    byte[] doFinal(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws Exception;
    
    @VisibleForTesting
    String getAlgorithm();
    
    int getBlockSize();
    
    byte[] getIV();
    
    @VisibleForTesting
    String getProvider();
    
    void init(int param1Int, Key param1Key) throws Exception;
    
    void init(int param1Int, Key param1Key, AlgorithmParameterSpec param1AlgorithmParameterSpec) throws Exception;
  }
  
  static interface ICryptoFactory {
    CryptoUtils.ICipher getCipher(String param1String1, String param1String2) throws Exception;
    
    CryptoUtils.IKeyGenerator getKeyGenerator(String param1String1, String param1String2) throws Exception;
  }
  
  static interface IKeyGenerator {
    void generateKey();
    
    void init(AlgorithmParameterSpec param1AlgorithmParameterSpec) throws Exception;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/crypto/CryptoUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */