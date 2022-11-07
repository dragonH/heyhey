package android.support.v4.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class RemoteInput extends RemoteInputCompatBase.RemoteInput {
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY = new RemoteInputCompatBase.RemoteInput.Factory() {
      public RemoteInput build(String param1String, CharSequence param1CharSequence, CharSequence[] param1ArrayOfCharSequence, boolean param1Boolean, Bundle param1Bundle, Set<String> param1Set) {
        return new RemoteInput(param1String, param1CharSequence, param1ArrayOfCharSequence, param1Boolean, param1Bundle, param1Set);
      }
      
      public RemoteInput[] newArray(int param1Int) {
        return new RemoteInput[param1Int];
      }
    };
  
  private static final Impl IMPL;
  
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  
  private static final String TAG = "RemoteInput";
  
  private final boolean mAllowFreeFormTextInput;
  
  private final Set<String> mAllowedDataTypes;
  
  private final CharSequence[] mChoices;
  
  private final Bundle mExtras;
  
  private final CharSequence mLabel;
  
  private final String mResultKey;
  
  RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle, Set<String> paramSet) {
    this.mResultKey = paramString;
    this.mLabel = paramCharSequence;
    this.mChoices = paramArrayOfCharSequence;
    this.mAllowFreeFormTextInput = paramBoolean;
    this.mExtras = paramBundle;
    this.mAllowedDataTypes = paramSet;
  }
  
  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap) {
    IMPL.addDataResultToIntent(paramRemoteInput, paramIntent, paramMap);
  }
  
  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle) {
    IMPL.addResultsToIntent(paramArrayOfRemoteInput, paramIntent, paramBundle);
  }
  
  public static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString) {
    return IMPL.getDataResultsFromIntent(paramIntent, paramString);
  }
  
  public static Bundle getResultsFromIntent(Intent paramIntent) {
    return IMPL.getResultsFromIntent(paramIntent);
  }
  
  public boolean getAllowFreeFormInput() {
    return this.mAllowFreeFormTextInput;
  }
  
  public Set<String> getAllowedDataTypes() {
    return this.mAllowedDataTypes;
  }
  
  public CharSequence[] getChoices() {
    return this.mChoices;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public String getResultKey() {
    return this.mResultKey;
  }
  
  public boolean isDataOnly() {
    boolean bool;
    if (!getAllowFreeFormInput() && (getChoices() == null || (getChoices()).length == 0) && getAllowedDataTypes() != null && !getAllowedDataTypes().isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  static {
    if (Build.VERSION.SDK_INT >= 20) {
      IMPL = new ImplApi20();
    } else {
      IMPL = new ImplJellybean();
    } 
  }
  
  public static final class Builder {
    private boolean mAllowFreeFormTextInput = true;
    
    private final Set<String> mAllowedDataTypes = new HashSet<String>();
    
    private CharSequence[] mChoices;
    
    private Bundle mExtras = new Bundle();
    
    private CharSequence mLabel;
    
    private final String mResultKey;
    
    public Builder(String param1String) {
      if (param1String != null) {
        this.mResultKey = param1String;
        return;
      } 
      throw new IllegalArgumentException("Result key can't be null");
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    public RemoteInput build() {
      return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowDataType(String param1String, boolean param1Boolean) {
      if (param1Boolean) {
        this.mAllowedDataTypes.add(param1String);
      } else {
        this.mAllowedDataTypes.remove(param1String);
      } 
      return this;
    }
    
    public Builder setAllowFreeFormInput(boolean param1Boolean) {
      this.mAllowFreeFormTextInput = param1Boolean;
      return this;
    }
    
    public Builder setChoices(CharSequence[] param1ArrayOfCharSequence) {
      this.mChoices = param1ArrayOfCharSequence;
      return this;
    }
    
    public Builder setLabel(CharSequence param1CharSequence) {
      this.mLabel = param1CharSequence;
      return this;
    }
  }
  
  static interface Impl {
    void addDataResultToIntent(RemoteInput param1RemoteInput, Intent param1Intent, Map<String, Uri> param1Map);
    
    void addResultsToIntent(RemoteInput[] param1ArrayOfRemoteInput, Intent param1Intent, Bundle param1Bundle);
    
    Map<String, Uri> getDataResultsFromIntent(Intent param1Intent, String param1String);
    
    Bundle getResultsFromIntent(Intent param1Intent);
  }
  
  @RequiresApi(20)
  static class ImplApi20 implements Impl {
    public void addDataResultToIntent(RemoteInput param1RemoteInput, Intent param1Intent, Map<String, Uri> param1Map) {
      RemoteInputCompatApi20.addDataResultToIntent(param1RemoteInput, param1Intent, param1Map);
    }
    
    public void addResultsToIntent(RemoteInput[] param1ArrayOfRemoteInput, Intent param1Intent, Bundle param1Bundle) {
      RemoteInputCompatApi20.addResultsToIntent((RemoteInputCompatBase.RemoteInput[])param1ArrayOfRemoteInput, param1Intent, param1Bundle);
    }
    
    public Map<String, Uri> getDataResultsFromIntent(Intent param1Intent, String param1String) {
      return RemoteInputCompatApi20.getDataResultsFromIntent(param1Intent, param1String);
    }
    
    public Bundle getResultsFromIntent(Intent param1Intent) {
      return RemoteInputCompatApi20.getResultsFromIntent(param1Intent);
    }
  }
  
  static class ImplBase implements Impl {
    public void addDataResultToIntent(RemoteInput param1RemoteInput, Intent param1Intent, Map<String, Uri> param1Map) {
      Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }
    
    public void addResultsToIntent(RemoteInput[] param1ArrayOfRemoteInput, Intent param1Intent, Bundle param1Bundle) {
      Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }
    
    public Map<String, Uri> getDataResultsFromIntent(Intent param1Intent, String param1String) {
      Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
      return null;
    }
    
    public Bundle getResultsFromIntent(Intent param1Intent) {
      Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
      return null;
    }
  }
  
  @RequiresApi(16)
  static class ImplJellybean implements Impl {
    public void addDataResultToIntent(RemoteInput param1RemoteInput, Intent param1Intent, Map<String, Uri> param1Map) {
      RemoteInputCompatJellybean.addDataResultToIntent(param1RemoteInput, param1Intent, param1Map);
    }
    
    public void addResultsToIntent(RemoteInput[] param1ArrayOfRemoteInput, Intent param1Intent, Bundle param1Bundle) {
      RemoteInputCompatJellybean.addResultsToIntent((RemoteInputCompatBase.RemoteInput[])param1ArrayOfRemoteInput, param1Intent, param1Bundle);
    }
    
    public Map<String, Uri> getDataResultsFromIntent(Intent param1Intent, String param1String) {
      return RemoteInputCompatJellybean.getDataResultsFromIntent(param1Intent, param1String);
    }
    
    public Bundle getResultsFromIntent(Intent param1Intent) {
      return RemoteInputCompatJellybean.getResultsFromIntent(param1Intent);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/RemoteInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */