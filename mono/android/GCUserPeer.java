package mono.android;

import java.util.ArrayList;

class GCUserPeer implements IGCUserPeer {
  private ArrayList refList = null;
  
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/GCUserPeer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */