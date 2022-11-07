package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.support.annotation.RequiresApi;
import java.security.KeyStore;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import javax.crypto.spec.IvParameterSpec;

@RequiresApi(23)
class CryptoAesHandler implements CryptoHandler {
  public byte[] decrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception {
    CryptoUtils.ICipher iCipher = paramICryptoFactory.getCipher("AES/CBC/PKCS7Padding", "AndroidKeyStoreBCWorkaround");
    paramInt = iCipher.getBlockSize();
    IvParameterSpec ivParameterSpec = new IvParameterSpec(paramArrayOfbyte, 0, paramInt);
    iCipher.init(2, ((KeyStore.SecretKeyEntry)paramEntry).getSecretKey(), ivParameterSpec);
    return iCipher.doFinal(paramArrayOfbyte, paramInt, paramArrayOfbyte.length - paramInt);
  }
  
  public byte[] encrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception {
    CryptoUtils.ICipher iCipher = paramICryptoFactory.getCipher("AES/CBC/PKCS7Padding", "AndroidKeyStoreBCWorkaround");
    iCipher.init(1, ((KeyStore.SecretKeyEntry)paramEntry).getSecretKey());
    byte[] arrayOfByte1 = iCipher.getIV();
    paramArrayOfbyte = iCipher.doFinal(paramArrayOfbyte);
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfbyte.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(paramArrayOfbyte, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfbyte.length);
    return arrayOfByte2;
  }
  
  public void generateKey(CryptoUtils.ICryptoFactory paramICryptoFactory, String paramString, Context paramContext) throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.add(1, 1);
    CryptoUtils.IKeyGenerator iKeyGenerator = paramICryptoFactory.getKeyGenerator("AES", "AndroidKeyStore");
    iKeyGenerator.init((AlgorithmParameterSpec)(new KeyGenParameterSpec.Builder(paramString, 3)).setBlockModes(new String[] { "CBC" }).setEncryptionPaddings(new String[] { "PKCS7Padding" }).setKeySize(256).setKeyValidityForOriginationEnd(calendar.getTime()).build());
    iKeyGenerator.generateKey();
  }
  
  public String getAlgorithm() {
    return "AES/CBC/PKCS7Padding/256";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/crypto/CryptoAesHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */