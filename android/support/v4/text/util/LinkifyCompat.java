package android.support.v4.text.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
  private static final Comparator<LinkSpec> COMPARATOR;
  
  private static final String[] EMPTY_STRING = new String[0];
  
  static {
    COMPARATOR = new Comparator<LinkSpec>() {
        public final int compare(LinkifyCompat.LinkSpec param1LinkSpec1, LinkifyCompat.LinkSpec param1LinkSpec2) {
          int i = param1LinkSpec1.start;
          int j = param1LinkSpec2.start;
          if (i < j)
            return -1; 
          if (i > j)
            return 1; 
          i = param1LinkSpec1.end;
          j = param1LinkSpec2.end;
          return (i < j) ? 1 : ((i > j) ? -1 : 0);
        }
      };
  }
  
  private static void addLinkMovementMethod(@NonNull TextView paramTextView) {
    MovementMethod movementMethod = paramTextView.getMovementMethod();
    if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && paramTextView.getLinksClickable())
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance()); 
  }
  
  public static final void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString) {
    if (Build.VERSION.SDK_INT >= 26) {
      Linkify.addLinks(paramTextView, paramPattern, paramString);
      return;
    } 
    addLinks(paramTextView, paramPattern, paramString, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
  }
  
  public static final void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    if (Build.VERSION.SDK_INT >= 26) {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
      return;
    } 
    addLinks(paramTextView, paramPattern, paramString, (String[])null, paramMatchFilter, paramTransformFilter);
  }
  
  public static final void addLinks(@NonNull TextView paramTextView, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    if (Build.VERSION.SDK_INT >= 26) {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
      return;
    } 
    SpannableString spannableString = SpannableString.valueOf(paramTextView.getText());
    if (addLinks((Spannable)spannableString, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter)) {
      paramTextView.setText((CharSequence)spannableString);
      addLinkMovementMethod(paramTextView);
    } 
  }
  
  public static final boolean addLinks(@NonNull Spannable paramSpannable, int paramInt) {
    if (Build.VERSION.SDK_INT >= 26)
      return Linkify.addLinks(paramSpannable, paramInt); 
    if (paramInt == 0)
      return false; 
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, paramSpannable.length(), URLSpan.class);
    for (int i = arrayOfURLSpan.length - 1; i >= 0; i--)
      paramSpannable.removeSpan(arrayOfURLSpan[i]); 
    if ((paramInt & 0x4) != 0)
      Linkify.addLinks(paramSpannable, 4); 
    ArrayList<LinkSpec> arrayList = new ArrayList();
    if ((paramInt & 0x1) != 0) {
      Pattern pattern = PatternsCompat.AUTOLINK_WEB_URL;
      Linkify.MatchFilter matchFilter = Linkify.sUrlMatchFilter;
      gatherLinks(arrayList, paramSpannable, pattern, new String[] { "http://", "https://", "rtsp://" }, matchFilter, null);
    } 
    if ((paramInt & 0x2) != 0)
      gatherLinks(arrayList, paramSpannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null); 
    if ((paramInt & 0x8) != 0)
      gatherMapLinks(arrayList, paramSpannable); 
    pruneOverlaps(arrayList, paramSpannable);
    if (arrayList.size() == 0)
      return false; 
    for (LinkSpec linkSpec : arrayList) {
      if (linkSpec.frameworkAddedSpan == null)
        applyLink(linkSpec.url, linkSpec.start, linkSpec.end, paramSpannable); 
    } 
    return true;
  }
  
  public static final boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString) {
    return (Build.VERSION.SDK_INT >= 26) ? Linkify.addLinks(paramSpannable, paramPattern, paramString) : addLinks(paramSpannable, paramPattern, paramString, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
  }
  
  public static final boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    return (Build.VERSION.SDK_INT >= 26) ? Linkify.addLinks(paramSpannable, paramPattern, paramString, paramMatchFilter, paramTransformFilter) : addLinks(paramSpannable, paramPattern, paramString, (String[])null, paramMatchFilter, paramTransformFilter);
  }
  
  public static final boolean addLinks(@NonNull Spannable paramSpannable, @NonNull Pattern paramPattern, @Nullable String paramString, @Nullable String[] paramArrayOfString, @Nullable Linkify.MatchFilter paramMatchFilter, @Nullable Linkify.TransformFilter paramTransformFilter) {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #26
    //   5: if_icmplt -> 20
    //   8: aload_0
    //   9: aload_1
    //   10: aload_2
    //   11: aload_3
    //   12: aload #4
    //   14: aload #5
    //   16: invokestatic addLinks : (Landroid/text/Spannable;Ljava/util/regex/Pattern;Ljava/lang/String;[Ljava/lang/String;Landroid/text/util/Linkify$MatchFilter;Landroid/text/util/Linkify$TransformFilter;)Z
    //   19: ireturn
    //   20: aload_2
    //   21: astore #6
    //   23: aload_2
    //   24: ifnonnull -> 31
    //   27: ldc ''
    //   29: astore #6
    //   31: aload_3
    //   32: ifnull -> 43
    //   35: aload_3
    //   36: astore_2
    //   37: aload_3
    //   38: arraylength
    //   39: iconst_1
    //   40: if_icmpge -> 47
    //   43: getstatic android/support/v4/text/util/LinkifyCompat.EMPTY_STRING : [Ljava/lang/String;
    //   46: astore_2
    //   47: aload_2
    //   48: arraylength
    //   49: iconst_1
    //   50: iadd
    //   51: anewarray java/lang/String
    //   54: astore #7
    //   56: aload #7
    //   58: iconst_0
    //   59: aload #6
    //   61: getstatic java/util/Locale.ROOT : Ljava/util/Locale;
    //   64: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   67: aastore
    //   68: iconst_0
    //   69: istore #8
    //   71: iload #8
    //   73: aload_2
    //   74: arraylength
    //   75: if_icmpge -> 113
    //   78: aload_2
    //   79: iload #8
    //   81: aaload
    //   82: astore_3
    //   83: iinc #8, 1
    //   86: aload_3
    //   87: ifnonnull -> 96
    //   90: ldc ''
    //   92: astore_3
    //   93: goto -> 104
    //   96: aload_3
    //   97: getstatic java/util/Locale.ROOT : Ljava/util/Locale;
    //   100: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   103: astore_3
    //   104: aload #7
    //   106: iload #8
    //   108: aload_3
    //   109: aastore
    //   110: goto -> 71
    //   113: aload_1
    //   114: aload_0
    //   115: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   118: astore_1
    //   119: iconst_0
    //   120: istore #9
    //   122: aload_1
    //   123: invokevirtual find : ()Z
    //   126: ifeq -> 198
    //   129: aload_1
    //   130: invokevirtual start : ()I
    //   133: istore #8
    //   135: aload_1
    //   136: invokevirtual end : ()I
    //   139: istore #10
    //   141: aload #4
    //   143: ifnull -> 163
    //   146: aload #4
    //   148: aload_0
    //   149: iload #8
    //   151: iload #10
    //   153: invokeinterface acceptMatch : (Ljava/lang/CharSequence;II)Z
    //   158: istore #11
    //   160: goto -> 166
    //   163: iconst_1
    //   164: istore #11
    //   166: iload #11
    //   168: ifeq -> 122
    //   171: aload_1
    //   172: iconst_0
    //   173: invokevirtual group : (I)Ljava/lang/String;
    //   176: aload #7
    //   178: aload_1
    //   179: aload #5
    //   181: invokestatic makeUrl : (Ljava/lang/String;[Ljava/lang/String;Ljava/util/regex/Matcher;Landroid/text/util/Linkify$TransformFilter;)Ljava/lang/String;
    //   184: iload #8
    //   186: iload #10
    //   188: aload_0
    //   189: invokestatic applyLink : (Ljava/lang/String;IILandroid/text/Spannable;)V
    //   192: iconst_1
    //   193: istore #9
    //   195: goto -> 122
    //   198: iload #9
    //   200: ireturn
  }
  
  public static final boolean addLinks(@NonNull TextView paramTextView, int paramInt) {
    if (Build.VERSION.SDK_INT >= 26)
      return Linkify.addLinks(paramTextView, paramInt); 
    if (paramInt == 0)
      return false; 
    CharSequence charSequence = paramTextView.getText();
    if (charSequence instanceof Spannable) {
      if (addLinks((Spannable)charSequence, paramInt)) {
        addLinkMovementMethod(paramTextView);
        return true;
      } 
      return false;
    } 
    SpannableString spannableString = SpannableString.valueOf(charSequence);
    if (addLinks((Spannable)spannableString, paramInt)) {
      addLinkMovementMethod(paramTextView);
      paramTextView.setText((CharSequence)spannableString);
      return true;
    } 
    return false;
  }
  
  private static void applyLink(String paramString, int paramInt1, int paramInt2, Spannable paramSpannable) {
    paramSpannable.setSpan(new URLSpan(paramString), paramInt1, paramInt2, 33);
  }
  
  private static void gatherLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable, Pattern paramPattern, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter) {
    Matcher matcher = paramPattern.matcher((CharSequence)paramSpannable);
    while (matcher.find()) {
      int i = matcher.start();
      int j = matcher.end();
      if (paramMatchFilter == null || paramMatchFilter.acceptMatch((CharSequence)paramSpannable, i, j)) {
        LinkSpec linkSpec = new LinkSpec();
        linkSpec.url = makeUrl(matcher.group(0), paramArrayOfString, matcher, paramTransformFilter);
        linkSpec.start = i;
        linkSpec.end = j;
        paramArrayList.add(linkSpec);
      } 
    } 
  }
  
  private static final void gatherMapLinks(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable) {
    String str = paramSpannable.toString();
    int i = 0;
    while (true) {
      try {
        String str1 = WebView.findAddress(str);
        if (str1 != null) {
          int j = str.indexOf(str1);
          if (j >= 0) {
            LinkSpec linkSpec = new LinkSpec();
            this();
            int k = str1.length() + j;
            linkSpec.start = j + i;
            i += k;
            linkSpec.end = i;
            str = str.substring(k);
            try {
              str1 = URLEncoder.encode(str1, "UTF-8");
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("geo:0,0?q=");
              stringBuilder.append(str1);
              linkSpec.url = stringBuilder.toString();
              paramArrayList.add(linkSpec);
            } catch (UnsupportedEncodingException unsupportedEncodingException) {}
            continue;
          } 
        } 
      } catch (UnsupportedOperationException unsupportedOperationException) {}
      return;
    } 
  }
  
  private static String makeUrl(@NonNull String paramString, @NonNull String[] paramArrayOfString, Matcher paramMatcher, @Nullable Linkify.TransformFilter paramTransformFilter) {
    int i;
    String str2 = paramString;
    if (paramTransformFilter != null)
      str2 = paramTransformFilter.transformUrl(paramMatcher, paramString); 
    byte b = 0;
    while (true) {
      i = paramArrayOfString.length;
      boolean bool = true;
      if (b < i) {
        paramString = paramArrayOfString[b];
        if (str2.regionMatches(true, 0, paramString, 0, paramString.length())) {
          String str = paramArrayOfString[b];
          i = bool;
          paramString = str2;
          if (!str2.regionMatches(false, 0, str, 0, str.length())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(paramArrayOfString[b]);
            stringBuilder.append(str2.substring(paramArrayOfString[b].length()));
            String str3 = stringBuilder.toString();
            i = bool;
          } 
          break;
        } 
        b++;
        continue;
      } 
      i = 0;
      paramString = str2;
      break;
    } 
    String str1 = paramString;
    if (i == 0) {
      str1 = paramString;
      if (paramArrayOfString.length > 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramArrayOfString[0]);
        stringBuilder.append(paramString);
        str1 = stringBuilder.toString();
      } 
    } 
    return str1;
  }
  
  private static final void pruneOverlaps(ArrayList<LinkSpec> paramArrayList, Spannable paramSpannable) {
    int i = paramSpannable.length();
    int j = 0;
    URLSpan[] arrayOfURLSpan = (URLSpan[])paramSpannable.getSpans(0, i, URLSpan.class);
    for (i = 0; i < arrayOfURLSpan.length; i++) {
      LinkSpec linkSpec = new LinkSpec();
      URLSpan uRLSpan = arrayOfURLSpan[i];
      linkSpec.frameworkAddedSpan = uRLSpan;
      linkSpec.start = paramSpannable.getSpanStart(uRLSpan);
      linkSpec.end = paramSpannable.getSpanEnd(arrayOfURLSpan[i]);
      paramArrayList.add(linkSpec);
    } 
    Collections.sort(paramArrayList, COMPARATOR);
    int k = paramArrayList.size();
    for (i = j; i < k - 1; i = m) {
      LinkSpec linkSpec1 = paramArrayList.get(i);
      int m = i + 1;
      LinkSpec linkSpec2 = paramArrayList.get(m);
      int n = linkSpec1.start;
      int i1 = linkSpec2.start;
      if (n <= i1) {
        j = linkSpec1.end;
        if (j > i1) {
          int i2 = linkSpec2.end;
          if (i2 <= j || j - n > i2 - i1) {
            j = m;
          } else if (j - n < i2 - i1) {
            j = i;
          } else {
            j = -1;
          } 
          if (j != -1) {
            URLSpan uRLSpan = ((LinkSpec)paramArrayList.get(j)).frameworkAddedSpan;
            if (uRLSpan != null)
              paramSpannable.removeSpan(uRLSpan); 
            paramArrayList.remove(j);
            k--;
            continue;
          } 
        } 
      } 
    } 
  }
  
  private static class LinkSpec {
    int end;
    
    URLSpan frameworkAddedSpan;
    
    int start;
    
    String url;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface LinkifyMask {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/text/util/LinkifyCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */