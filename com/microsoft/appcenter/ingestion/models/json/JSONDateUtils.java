package com.microsoft.appcenter.ingestion.models.json;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;

public final class JSONDateUtils {
  private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
      protected DateFormat initialValue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
      }
    };
  
  private static void checkNull(Object paramObject) throws JSONException {
    if (paramObject != null)
      return; 
    throw new JSONException("date cannot be null");
  }
  
  public static Date toDate(String paramString) throws JSONException {
    checkNull(paramString);
    try {
      return ((DateFormat)DATE_FORMAT.get()).parse(paramString);
    } catch (ParseException parseException) {
      throw new JSONException(parseException.getMessage());
    } 
  }
  
  public static String toString(Date paramDate) throws JSONException {
    checkNull(paramDate);
    return ((DateFormat)DATE_FORMAT.get()).format(paramDate);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/json/JSONDateUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */