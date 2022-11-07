package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

class AppCompatViewInflater {
  private static final String LOG_TAG = "AppCompatViewInflater";
  
  private static final String[] sClassPrefixList;
  
  private static final Map<String, Constructor<? extends View>> sConstructorMap;
  
  private static final Class<?>[] sConstructorSignature = new Class[] { Context.class, AttributeSet.class };
  
  private static final int[] sOnClickAttrs = new int[] { 16843375 };
  
  private final Object[] mConstructorArgs = new Object[2];
  
  static {
    sClassPrefixList = new String[] { "android.widget.", "android.view.", "android.webkit." };
    sConstructorMap = (Map<String, Constructor<? extends View>>)new ArrayMap();
  }
  
  private void checkOnClickListener(View paramView, AttributeSet paramAttributeSet) {
    Context context = paramView.getContext();
    if (context instanceof ContextWrapper && ViewCompat.hasOnClickListeners(paramView)) {
      TypedArray typedArray = context.obtainStyledAttributes(paramAttributeSet, sOnClickAttrs);
      String str = typedArray.getString(0);
      if (str != null)
        paramView.setOnClickListener(new DeclaredOnClickListener(paramView, str)); 
      typedArray.recycle();
    } 
  }
  
  private View createView(Context paramContext, String paramString1, String paramString2) throws ClassNotFoundException, InflateException {
    Map<String, Constructor<? extends View>> map = sConstructorMap;
    Constructor constructor = map.get(paramString1);
    Constructor<? extends View> constructor1 = constructor;
    if (constructor == null)
      try {
        String str;
        ClassLoader classLoader = paramContext.getClassLoader();
        if (paramString2 != null) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(paramString2);
          stringBuilder.append(paramString1);
          str = stringBuilder.toString();
        } else {
          str = paramString1;
        } 
        constructor1 = classLoader.loadClass(str).<View>asSubclass(View.class).getConstructor(sConstructorSignature);
        map.put(paramString1, constructor1);
        constructor1.setAccessible(true);
        return constructor1.newInstance(this.mConstructorArgs);
      } catch (Exception exception) {
        return null;
      }  
    constructor1.setAccessible(true);
    return constructor1.newInstance(this.mConstructorArgs);
  }
  
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet) {
    String str = paramString;
    if (paramString.equals("view"))
      str = paramAttributeSet.getAttributeValue(null, "class"); 
    try {
      Object[] arrayOfObject1;
      Object[] arrayOfObject2 = this.mConstructorArgs;
      arrayOfObject2[0] = paramContext;
      arrayOfObject2[1] = paramAttributeSet;
      if (-1 == str.indexOf('.')) {
        byte b = 0;
        while (true) {
          String[] arrayOfString = sClassPrefixList;
          if (b < arrayOfString.length) {
            View view = createView(paramContext, str, arrayOfString[b]);
            if (view != null)
              return view; 
            b++;
            continue;
          } 
          return null;
        } 
      } 
      return createView((Context)arrayOfObject1, str, null);
    } catch (Exception exception) {
      return null;
    } finally {
      Object[] arrayOfObject = this.mConstructorArgs;
      arrayOfObject[0] = null;
      arrayOfObject[1] = null;
    } 
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2) {
    int i;
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    if (paramBoolean1) {
      i = typedArray.getResourceId(R.styleable.View_android_theme, 0);
    } else {
      i = 0;
    } 
    int j = i;
    if (paramBoolean2) {
      j = i;
      if (!i) {
        i = typedArray.getResourceId(R.styleable.View_theme, 0);
        j = i;
        if (i != 0) {
          Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
          j = i;
        } 
      } 
    } 
    typedArray.recycle();
    Context context = paramContext;
    if (j != 0) {
      if (paramContext instanceof ContextThemeWrapper) {
        context = paramContext;
        return (Context)((((ContextThemeWrapper)paramContext).getThemeResId() != j) ? new ContextThemeWrapper(paramContext, j) : context);
      } 
    } else {
      return context;
    } 
    return (Context)new ContextThemeWrapper(paramContext, j);
  }
  
  public final View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    // Byte code:
    //   0: iload #5
    //   2: ifeq -> 18
    //   5: aload_1
    //   6: ifnull -> 18
    //   9: aload_1
    //   10: invokevirtual getContext : ()Landroid/content/Context;
    //   13: astore #9
    //   15: goto -> 21
    //   18: aload_3
    //   19: astore #9
    //   21: iload #6
    //   23: ifne -> 34
    //   26: aload #9
    //   28: astore_1
    //   29: iload #7
    //   31: ifeq -> 46
    //   34: aload #9
    //   36: aload #4
    //   38: iload #6
    //   40: iload #7
    //   42: invokestatic themifyContext : (Landroid/content/Context;Landroid/util/AttributeSet;ZZ)Landroid/content/Context;
    //   45: astore_1
    //   46: aload_1
    //   47: astore #9
    //   49: iload #8
    //   51: ifeq -> 60
    //   54: aload_1
    //   55: invokestatic wrap : (Landroid/content/Context;)Landroid/content/Context;
    //   58: astore #9
    //   60: aconst_null
    //   61: astore_1
    //   62: aload_2
    //   63: invokevirtual hashCode : ()I
    //   66: pop
    //   67: iconst_m1
    //   68: istore #10
    //   70: aload_2
    //   71: invokevirtual hashCode : ()I
    //   74: lookupswitch default -> 188, -1946472170 -> 414, -1455429095 -> 396, -1346021293 -> 378, -938935918 -> 360, -937446323 -> 342, -658531749 -> 324, -339785223 -> 305, 776382189 -> 286, 1125864064 -> 267, 1413872058 -> 248, 1601505219 -> 229, 1666676343 -> 210, 2001146706 -> 191
    //   188: goto -> 429
    //   191: aload_2
    //   192: ldc 'Button'
    //   194: invokevirtual equals : (Ljava/lang/Object;)Z
    //   197: ifne -> 203
    //   200: goto -> 429
    //   203: bipush #12
    //   205: istore #10
    //   207: goto -> 429
    //   210: aload_2
    //   211: ldc 'EditText'
    //   213: invokevirtual equals : (Ljava/lang/Object;)Z
    //   216: ifne -> 222
    //   219: goto -> 429
    //   222: bipush #11
    //   224: istore #10
    //   226: goto -> 429
    //   229: aload_2
    //   230: ldc 'CheckBox'
    //   232: invokevirtual equals : (Ljava/lang/Object;)Z
    //   235: ifne -> 241
    //   238: goto -> 429
    //   241: bipush #10
    //   243: istore #10
    //   245: goto -> 429
    //   248: aload_2
    //   249: ldc 'AutoCompleteTextView'
    //   251: invokevirtual equals : (Ljava/lang/Object;)Z
    //   254: ifne -> 260
    //   257: goto -> 429
    //   260: bipush #9
    //   262: istore #10
    //   264: goto -> 429
    //   267: aload_2
    //   268: ldc 'ImageView'
    //   270: invokevirtual equals : (Ljava/lang/Object;)Z
    //   273: ifne -> 279
    //   276: goto -> 429
    //   279: bipush #8
    //   281: istore #10
    //   283: goto -> 429
    //   286: aload_2
    //   287: ldc 'RadioButton'
    //   289: invokevirtual equals : (Ljava/lang/Object;)Z
    //   292: ifne -> 298
    //   295: goto -> 429
    //   298: bipush #7
    //   300: istore #10
    //   302: goto -> 429
    //   305: aload_2
    //   306: ldc 'Spinner'
    //   308: invokevirtual equals : (Ljava/lang/Object;)Z
    //   311: ifne -> 317
    //   314: goto -> 429
    //   317: bipush #6
    //   319: istore #10
    //   321: goto -> 429
    //   324: aload_2
    //   325: ldc 'SeekBar'
    //   327: invokevirtual equals : (Ljava/lang/Object;)Z
    //   330: ifne -> 336
    //   333: goto -> 429
    //   336: iconst_5
    //   337: istore #10
    //   339: goto -> 429
    //   342: aload_2
    //   343: ldc 'ImageButton'
    //   345: invokevirtual equals : (Ljava/lang/Object;)Z
    //   348: ifne -> 354
    //   351: goto -> 429
    //   354: iconst_4
    //   355: istore #10
    //   357: goto -> 429
    //   360: aload_2
    //   361: ldc 'TextView'
    //   363: invokevirtual equals : (Ljava/lang/Object;)Z
    //   366: ifne -> 372
    //   369: goto -> 429
    //   372: iconst_3
    //   373: istore #10
    //   375: goto -> 429
    //   378: aload_2
    //   379: ldc 'MultiAutoCompleteTextView'
    //   381: invokevirtual equals : (Ljava/lang/Object;)Z
    //   384: ifne -> 390
    //   387: goto -> 429
    //   390: iconst_2
    //   391: istore #10
    //   393: goto -> 429
    //   396: aload_2
    //   397: ldc 'CheckedTextView'
    //   399: invokevirtual equals : (Ljava/lang/Object;)Z
    //   402: ifne -> 408
    //   405: goto -> 429
    //   408: iconst_1
    //   409: istore #10
    //   411: goto -> 429
    //   414: aload_2
    //   415: ldc 'RatingBar'
    //   417: invokevirtual equals : (Ljava/lang/Object;)Z
    //   420: ifne -> 426
    //   423: goto -> 429
    //   426: iconst_0
    //   427: istore #10
    //   429: iload #10
    //   431: tableswitch default -> 496, 0 -> 679, 1 -> 664, 2 -> 649, 3 -> 634, 4 -> 619, 5 -> 604, 6 -> 589, 7 -> 574, 8 -> 559, 9 -> 544, 10 -> 529, 11 -> 514, 12 -> 499
    //   496: goto -> 691
    //   499: new android/support/v7/widget/AppCompatButton
    //   502: dup
    //   503: aload #9
    //   505: aload #4
    //   507: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   510: astore_1
    //   511: goto -> 691
    //   514: new android/support/v7/widget/AppCompatEditText
    //   517: dup
    //   518: aload #9
    //   520: aload #4
    //   522: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   525: astore_1
    //   526: goto -> 691
    //   529: new android/support/v7/widget/AppCompatCheckBox
    //   532: dup
    //   533: aload #9
    //   535: aload #4
    //   537: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   540: astore_1
    //   541: goto -> 691
    //   544: new android/support/v7/widget/AppCompatAutoCompleteTextView
    //   547: dup
    //   548: aload #9
    //   550: aload #4
    //   552: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   555: astore_1
    //   556: goto -> 691
    //   559: new android/support/v7/widget/AppCompatImageView
    //   562: dup
    //   563: aload #9
    //   565: aload #4
    //   567: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   570: astore_1
    //   571: goto -> 691
    //   574: new android/support/v7/widget/AppCompatRadioButton
    //   577: dup
    //   578: aload #9
    //   580: aload #4
    //   582: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   585: astore_1
    //   586: goto -> 691
    //   589: new android/support/v7/widget/AppCompatSpinner
    //   592: dup
    //   593: aload #9
    //   595: aload #4
    //   597: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   600: astore_1
    //   601: goto -> 691
    //   604: new android/support/v7/widget/AppCompatSeekBar
    //   607: dup
    //   608: aload #9
    //   610: aload #4
    //   612: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   615: astore_1
    //   616: goto -> 691
    //   619: new android/support/v7/widget/AppCompatImageButton
    //   622: dup
    //   623: aload #9
    //   625: aload #4
    //   627: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   630: astore_1
    //   631: goto -> 691
    //   634: new android/support/v7/widget/AppCompatTextView
    //   637: dup
    //   638: aload #9
    //   640: aload #4
    //   642: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   645: astore_1
    //   646: goto -> 691
    //   649: new android/support/v7/widget/AppCompatMultiAutoCompleteTextView
    //   652: dup
    //   653: aload #9
    //   655: aload #4
    //   657: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   660: astore_1
    //   661: goto -> 691
    //   664: new android/support/v7/widget/AppCompatCheckedTextView
    //   667: dup
    //   668: aload #9
    //   670: aload #4
    //   672: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   675: astore_1
    //   676: goto -> 691
    //   679: new android/support/v7/widget/AppCompatRatingBar
    //   682: dup
    //   683: aload #9
    //   685: aload #4
    //   687: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
    //   690: astore_1
    //   691: aload_1
    //   692: astore #11
    //   694: aload_1
    //   695: ifnonnull -> 718
    //   698: aload_1
    //   699: astore #11
    //   701: aload_3
    //   702: aload #9
    //   704: if_acmpeq -> 718
    //   707: aload_0
    //   708: aload #9
    //   710: aload_2
    //   711: aload #4
    //   713: invokespecial createViewFromTag : (Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;)Landroid/view/View;
    //   716: astore #11
    //   718: aload #11
    //   720: ifnull -> 731
    //   723: aload_0
    //   724: aload #11
    //   726: aload #4
    //   728: invokespecial checkOnClickListener : (Landroid/view/View;Landroid/util/AttributeSet;)V
    //   731: aload #11
    //   733: areturn
  }
  
  private static class DeclaredOnClickListener implements View.OnClickListener {
    private final View mHostView;
    
    private final String mMethodName;
    
    private Context mResolvedContext;
    
    private Method mResolvedMethod;
    
    public DeclaredOnClickListener(@NonNull View param1View, @NonNull String param1String) {
      this.mHostView = param1View;
      this.mMethodName = param1String;
    }
    
    @NonNull
    private void resolveMethod(@Nullable Context param1Context, @NonNull String param1String) {
      String str;
      while (param1Context != null) {
        try {
          if (!param1Context.isRestricted()) {
            Method method = param1Context.getClass().getMethod(this.mMethodName, new Class[] { View.class });
            if (method != null) {
              this.mResolvedMethod = method;
              this.mResolvedContext = param1Context;
              return;
            } 
          } 
        } catch (NoSuchMethodException noSuchMethodException) {}
        if (param1Context instanceof ContextWrapper) {
          param1Context = ((ContextWrapper)param1Context).getBaseContext();
          continue;
        } 
        param1Context = null;
      } 
      int i = this.mHostView.getId();
      if (i == -1) {
        str = "";
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(" with id '");
        stringBuilder1.append(this.mHostView.getContext().getResources().getResourceEntryName(i));
        stringBuilder1.append("'");
        str = stringBuilder1.toString();
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not find method ");
      stringBuilder.append(this.mMethodName);
      stringBuilder.append("(View) in a parent or ancestor Context for android:onClick ");
      stringBuilder.append("attribute defined on view ");
      stringBuilder.append(this.mHostView.getClass());
      stringBuilder.append(str);
      IllegalStateException illegalStateException = new IllegalStateException(stringBuilder.toString());
      throw illegalStateException;
    }
    
    public void onClick(@NonNull View param1View) {
      if (this.mResolvedMethod == null)
        resolveMethod(this.mHostView.getContext(), this.mMethodName); 
      try {
        this.mResolvedMethod.invoke(this.mResolvedContext, new Object[] { param1View });
        return;
      } catch (IllegalAccessException illegalAccessException) {
        throw new IllegalStateException("Could not execute non-public method for android:onClick", illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        throw new IllegalStateException("Could not execute method for android:onClick", invocationTargetException);
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/app/AppCompatViewInflater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */