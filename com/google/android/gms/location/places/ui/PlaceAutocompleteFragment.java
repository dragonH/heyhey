package com.google.android.gms.location.places.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

@TargetApi(12)
public class PlaceAutocompleteFragment extends Fragment {
  private View zziep;
  
  private View zzieq;
  
  private EditText zzier;
  
  private boolean zzies;
  
  @Nullable
  private LatLngBounds zziet;
  
  @Nullable
  private AutocompleteFilter zzieu;
  
  @Nullable
  private PlaceSelectionListener zziev;
  
  private final void zzati() {
    byte b;
    boolean bool = this.zzier.getText().toString().isEmpty();
    View view = this.zzieq;
    if ((bool ^ true) != 0) {
      b = 0;
    } else {
      b = 8;
    } 
    view.setVisibility(b);
  }
  
  private final void zzatj() {
    int i;
    try {
      PlaceAutocomplete.IntentBuilder intentBuilder = new PlaceAutocomplete.IntentBuilder();
      this(2);
      Intent intent = intentBuilder.setBoundsBias(this.zziet).setFilter(this.zzieu).zzih(this.zzier.getText().toString()).zzdu(1).build(getActivity());
      this.zzies = true;
      startActivityForResult(intent, 30421);
      i = -1;
    } catch (GooglePlayServicesRepairableException googlePlayServicesRepairableException) {
      i = googlePlayServicesRepairableException.getConnectionStatusCode();
      Log.e("Places", "Could not open autocomplete activity", (Throwable)googlePlayServicesRepairableException);
    } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {
      i = googlePlayServicesNotAvailableException.errorCode;
    } 
    if (i != -1)
      GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 30422); 
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    this.zzies = false;
    if (paramInt1 == 30421)
      if (paramInt2 == -1) {
        Place place = PlaceAutocomplete.getPlace((Context)getActivity(), paramIntent);
        PlaceSelectionListener placeSelectionListener = this.zziev;
        if (placeSelectionListener != null)
          placeSelectionListener.onPlaceSelected(place); 
        setText(place.getName().toString());
      } else if (paramInt2 == 2) {
        Status status = PlaceAutocomplete.getStatus((Context)getActivity(), paramIntent);
        PlaceSelectionListener placeSelectionListener = this.zziev;
        if (placeSelectionListener != null)
          placeSelectionListener.onError(status); 
      }  
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = paramLayoutInflater.inflate(R.layout.place_autocomplete_fragment, paramViewGroup, false);
    this.zziep = view.findViewById(R.id.place_autocomplete_search_button);
    this.zzieq = view.findViewById(R.id.place_autocomplete_clear_button);
    this.zzier = (EditText)view.findViewById(R.id.place_autocomplete_search_input);
    zzc zzc = new zzc(this);
    this.zziep.setOnClickListener(zzc);
    this.zzier.setOnClickListener(zzc);
    this.zzieq.setOnClickListener(new zzd(this));
    zzati();
    return view;
  }
  
  public void onDestroyView() {
    this.zziep = null;
    this.zzieq = null;
    this.zzier = null;
    super.onDestroyView();
  }
  
  public void setBoundsBias(@Nullable LatLngBounds paramLatLngBounds) {
    this.zziet = paramLatLngBounds;
  }
  
  public void setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter) {
    this.zzieu = paramAutocompleteFilter;
  }
  
  public void setHint(CharSequence paramCharSequence) {
    this.zzier.setHint(paramCharSequence);
    this.zziep.setContentDescription(paramCharSequence);
  }
  
  public void setOnPlaceSelectedListener(PlaceSelectionListener paramPlaceSelectionListener) {
    this.zziev = paramPlaceSelectionListener;
  }
  
  public void setText(CharSequence paramCharSequence) {
    this.zzier.setText(paramCharSequence);
    zzati();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/PlaceAutocompleteFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */