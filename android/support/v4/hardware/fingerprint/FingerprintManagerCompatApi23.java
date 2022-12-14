package android.support.v4.hardware.fingerprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@RequiresApi(23)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class FingerprintManagerCompatApi23 {
  @SuppressLint({"MissingPermission"})
  static void authenticate(Context paramContext, CryptoObject paramCryptoObject, int paramInt, Object paramObject, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler) {
    FingerprintManager fingerprintManager = getFingerprintManagerOrNull(paramContext);
    if (fingerprintManager != null)
      fingerprintManager.authenticate(wrapCryptoObject(paramCryptoObject), (CancellationSignal)paramObject, paramInt, wrapCallback(paramAuthenticationCallback), paramHandler); 
  }
  
  private static FingerprintManager getFingerprintManagerOrNull(Context paramContext) {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? (FingerprintManager)paramContext.getSystemService(FingerprintManager.class) : null;
  }
  
  @SuppressLint({"MissingPermission"})
  static boolean hasEnrolledFingerprints(Context paramContext) {
    boolean bool;
    FingerprintManager fingerprintManager = getFingerprintManagerOrNull(paramContext);
    if (fingerprintManager != null && fingerprintManager.hasEnrolledFingerprints()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SuppressLint({"MissingPermission"})
  static boolean isHardwareDetected(Context paramContext) {
    boolean bool;
    FingerprintManager fingerprintManager = getFingerprintManagerOrNull(paramContext);
    if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject paramCryptoObject) {
    CryptoObject cryptoObject = null;
    if (paramCryptoObject == null)
      return null; 
    if (paramCryptoObject.getCipher() != null)
      return new CryptoObject(paramCryptoObject.getCipher()); 
    if (paramCryptoObject.getSignature() != null)
      return new CryptoObject(paramCryptoObject.getSignature()); 
    if (paramCryptoObject.getMac() != null)
      cryptoObject = new CryptoObject(paramCryptoObject.getMac()); 
    return cryptoObject;
  }
  
  private static FingerprintManager.AuthenticationCallback wrapCallback(final AuthenticationCallback callback) {
    return new FingerprintManager.AuthenticationCallback() {
        public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {
          callback.onAuthenticationError(param1Int, param1CharSequence);
        }
        
        public void onAuthenticationFailed() {
          callback.onAuthenticationFailed();
        }
        
        public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {
          callback.onAuthenticationHelp(param1Int, param1CharSequence);
        }
        
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult param1AuthenticationResult) {
          callback.onAuthenticationSucceeded(new FingerprintManagerCompatApi23.AuthenticationResultInternal(FingerprintManagerCompatApi23.unwrapCryptoObject(param1AuthenticationResult.getCryptoObject())));
        }
      };
  }
  
  private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject paramCryptoObject) {
    FingerprintManager.CryptoObject cryptoObject = null;
    if (paramCryptoObject == null)
      return null; 
    if (paramCryptoObject.getCipher() != null)
      return new FingerprintManager.CryptoObject(paramCryptoObject.getCipher()); 
    if (paramCryptoObject.getSignature() != null)
      return new FingerprintManager.CryptoObject(paramCryptoObject.getSignature()); 
    if (paramCryptoObject.getMac() != null)
      cryptoObject = new FingerprintManager.CryptoObject(paramCryptoObject.getMac()); 
    return cryptoObject;
  }
  
  public static abstract class AuthenticationCallback {
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal param1AuthenticationResultInternal) {}
  }
  
  public static final class AuthenticationResultInternal {
    private FingerprintManagerCompatApi23.CryptoObject mCryptoObject;
    
    public AuthenticationResultInternal(FingerprintManagerCompatApi23.CryptoObject param1CryptoObject) {
      this.mCryptoObject = param1CryptoObject;
    }
    
    public FingerprintManagerCompatApi23.CryptoObject getCryptoObject() {
      return this.mCryptoObject;
    }
  }
  
  public static class CryptoObject {
    private final Cipher mCipher;
    
    private final Mac mMac;
    
    private final Signature mSignature;
    
    public CryptoObject(Signature param1Signature) {
      this.mSignature = param1Signature;
      this.mCipher = null;
      this.mMac = null;
    }
    
    public CryptoObject(Cipher param1Cipher) {
      this.mCipher = param1Cipher;
      this.mSignature = null;
      this.mMac = null;
    }
    
    public CryptoObject(Mac param1Mac) {
      this.mMac = param1Mac;
      this.mCipher = null;
      this.mSignature = null;
    }
    
    public Cipher getCipher() {
      return this.mCipher;
    }
    
    public Mac getMac() {
      return this.mMac;
    }
    
    public Signature getSignature() {
      return this.mSignature;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/hardware/fingerprint/FingerprintManagerCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */