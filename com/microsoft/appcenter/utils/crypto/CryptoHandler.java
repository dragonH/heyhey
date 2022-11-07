package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import java.security.KeyStore;

interface CryptoHandler {
  byte[] decrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception;
  
  byte[] encrypt(CryptoUtils.ICryptoFactory paramICryptoFactory, int paramInt, KeyStore.Entry paramEntry, byte[] paramArrayOfbyte) throws Exception;
  
  void generateKey(CryptoUtils.ICryptoFactory paramICryptoFactory, String paramString, Context paramContext) throws Exception;
  
  String getAlgorithm();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/crypto/CryptoHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */