package com.microsoft.appcenter.utils.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.RequiresApi;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

@RequiresApi(19)
class CryptoRsaHandler implements CryptoHandler {
  private CryptoUtils.ICipher getCipher(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt) throws Exception {
    String str;
    if (paramInt >= 23) {
      str = "AndroidKeyStoreBCWorkaround";
    } else {
      str = "AndroidOpenSSL";
    } 
    return paramICryptoFactory.getCipher("RSA/ECB/PKCS1Padding", str);
  }
  
  public byte[] decrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception {
    CryptoUtils.ICipher iCipher = getCipher(paramICryptoFactory, paramInt);
    iCipher.init(2, ((KeyStore.PrivateKeyEntry)paramEntry).getPrivateKey());
    return iCipher.doFinal(paramArrayOfbyte);
  }
  
  public byte[] encrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception {
    CryptoUtils.ICipher iCipher = getCipher(paramICryptoFactory, paramInt);
    X509Certificate x509Certificate = (X509Certificate)((KeyStore.PrivateKeyEntry)paramEntry).getCertificate();
    try {
      x509Certificate.checkValidity();
      iCipher.init(1, x509Certificate.getPublicKey());
      return iCipher.doFinal(paramArrayOfbyte);
    } catch (CertificateExpiredException certificateExpiredException) {
      throw new InvalidKeyException(certificateExpiredException);
    } 
  }
  
  @SuppressLint({"InlinedApi", "TrulyRandom"})
  public void generateKey(CryptoUtils.ICryptoFactory paramICryptoFactory, String paramString, Context paramContext) throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.add(1, 1);
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
    KeyPairGeneratorSpec.Builder builder = (new KeyPairGeneratorSpec.Builder(paramContext)).setAlias(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CN=");
    stringBuilder.append(paramString);
    keyPairGenerator.initialize((AlgorithmParameterSpec)builder.setSubject(new X500Principal(stringBuilder.toString())).setStartDate(new Date()).setEndDate(calendar.getTime()).setSerialNumber(BigInteger.TEN).setKeySize(2048).build());
    keyPairGenerator.generateKeyPair();
  }
  
  public String getAlgorithm() {
    return "RSA/ECB/PKCS1Padding/2048";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/crypto/CryptoRsaHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */