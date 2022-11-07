package com.microsoft.windowsazure.messaging;

import java.net.URI;

public class ConnectionString {
  public static String createUsingSharedAccessKey(URI paramURI, String paramString1, String paramString2) {
    if (paramURI != null) {
      if (!Utils.isNullOrWhiteSpace(paramString1)) {
        if (!Utils.isNullOrWhiteSpace(paramString2))
          return String.format("Endpoint=%s;SharedAccessKeyName=%s;SharedAccessKey=%s", new Object[] { paramURI.toString(), paramString1, paramString2 }); 
        throw new IllegalArgumentException("accessSecret");
      } 
      throw new IllegalArgumentException("keyName");
    } 
    throw new IllegalArgumentException("endPoint");
  }
  
  public static String createUsingSharedAccessKeyWithFullAccess(URI paramURI, String paramString) {
    if (!Utils.isNullOrWhiteSpace(paramString))
      return createUsingSharedAccessKey(paramURI, "DefaultFullSharedAccessSignature", paramString); 
    throw new IllegalArgumentException("fullAccessSecret");
  }
  
  public static String createUsingSharedAccessKeyWithListenAccess(URI paramURI, String paramString) {
    if (!Utils.isNullOrWhiteSpace(paramString))
      return createUsingSharedAccessKey(paramURI, "DefaultListenSharedAccessSignature", paramString); 
    throw new IllegalArgumentException("listenAccessSecret");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/ConnectionString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */