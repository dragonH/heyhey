package mono.android.app;

import android.app.IntentService;
import java.util.ArrayList;

public abstract class IntentService extends IntentService {
  ArrayList refList;
  
  public IntentService() {
    this(null);
  }
  
  public IntentService(String paramString) {
    super(paramString);
  }
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/IntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */