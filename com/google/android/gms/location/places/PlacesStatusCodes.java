package com.google.android.gms.location.places;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbp;

public class PlacesStatusCodes extends CommonStatusCodes {
  public static final int ACCESS_NOT_CONFIGURED = 9003;
  
  public static final int DEVICE_RATE_LIMIT_EXCEEDED = 9006;
  
  public static final int INVALID_APP = 9008;
  
  public static final int INVALID_ARGUMENT = 9004;
  
  public static final int KEY_EXPIRED = 9007;
  
  public static final int KEY_INVALID = 9002;
  
  public static final int RATE_LIMIT_EXCEEDED = 9005;
  
  public static final int USAGE_LIMIT_EXCEEDED = 9001;
  
  public static String getStatusCodeString(int paramInt) {
    if (paramInt != 9051) {
      if (paramInt != 9150) {
        if (paramInt != 9101) {
          if (paramInt != 9102) {
            if (paramInt != 9201) {
              if (paramInt != 9202) {
                switch (paramInt) {
                  default:
                    return CommonStatusCodes.getStatusCodeString(paramInt);
                  case 9008:
                    return "PLACES_API_INVALID_APP";
                  case 9007:
                    return "PLACES_API_KEY_EXPIRED";
                  case 9006:
                    return "PLACES_API_DEVICE_RATE_LIMIT_EXCEEDED";
                  case 9005:
                    return "PLACES_API_RATE_LIMIT_EXCEEDED";
                  case 9004:
                    return "PLACES_API_INVALID_ARGUMENT";
                  case 9003:
                    return "PLACES_API_ACCESS_NOT_CONFIGURED";
                  case 9002:
                    return "PLACES_API_KEY_INVALID";
                  case 9001:
                    return "PLACES_API_USAGE_LIMIT_EXCEEDED";
                  case 9000:
                    break;
                } 
                return "PLACES_API_QUOTA_FAILED";
              } 
              return "PLACES_API_PERSONALIZED_DATA_ACCESS_REJECTED";
            } 
            return "PLACES_API_PERSONALIZED_DATA_ACCESS_APPROVED";
          } 
          return "NEARBY_ALERTS_NOT_AVAILABLE";
        } 
        return "PLACE_PROXIMITY_UNKNOWN";
      } 
      return "PLACEFENCING_NOT_AVAILABLE";
    } 
    return "PLACE_ALIAS_NOT_FOUND";
  }
  
  public static Status zzcl(int paramInt) {
    String str = getStatusCodeString(paramInt);
    zzbp.zzu(str);
    return new Status(paramInt, str);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacesStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */