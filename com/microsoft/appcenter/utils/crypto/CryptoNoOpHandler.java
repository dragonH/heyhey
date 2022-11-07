package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import java.security.KeyStore;

class CryptoNoOpHandler implements CryptoHandler {
  public byte[] decrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) {
    return paramArrayOfbyte;
  }
  
  public byte[] encrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) {
    return paramArrayOfbyte;
  }
  
  public void generateKey(CryptoUtils.ICryptoFactory paramICryptoFactory, String paramString, Context paramContext) {}
  
  public String getAlgorithm() {
    return "None";
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/crypto/CryptoNoOpHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */