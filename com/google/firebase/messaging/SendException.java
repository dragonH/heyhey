package com.google.firebase.messaging;

import java.util.Locale;

public final class SendException extends Exception {
  public static final int ERROR_INVALID_PARAMETERS = 1;
  
  public static final int ERROR_SIZE = 2;
  
  public static final int ERROR_TOO_MANY_MESSAGES = 4;
  
  public static final int ERROR_TTL_EXCEEDED = 3;
  
  public static final int ERROR_UNKNOWN = 0;
  
  private final int mErrorCode;
  
  SendException(String paramString) {
    super(paramString);
    byte b = 4;
    if (paramString != null) {
      paramString = paramString.toLowerCase(Locale.US);
      paramString.hashCode();
      byte b1 = -1;
      switch (paramString.hashCode()) {
        case -95047692:
          if (!paramString.equals("missing_to"))
            break; 
          b1 = 4;
          break;
        case -617027085:
          if (!paramString.equals("messagetoobig"))
            break; 
          b1 = 3;
          break;
        case -920906446:
          if (!paramString.equals("invalid_parameters"))
            break; 
          b1 = 2;
          break;
        case -1290953729:
          if (!paramString.equals("toomanymessages"))
            break; 
          b1 = 1;
          break;
        case -1743242157:
          if (!paramString.equals("service_not_available"))
            break; 
          b1 = 0;
          break;
      } 
      switch (b1) {
        default:
          b = 0;
          break;
        case 3:
          b = 2;
          break;
        case 2:
        case 4:
          b = 1;
          break;
        case 0:
          b = 3;
          break;
        case 1:
          break;
      } 
      this.mErrorCode = b;
      return;
    } 
  }
  
  public final int getErrorCode() {
    return this.mErrorCode;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/firebase/messaging/SendException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */