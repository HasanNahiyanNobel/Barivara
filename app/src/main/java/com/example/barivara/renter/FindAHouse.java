package com.example.barivara.renter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class FindAHouse extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_find_a_house);

		final TextView txtVw = findViewById(R.id.placeName);

		PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
				getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        /*AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setCountry("IN")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        autocompleteFragment.setFilter(filter);*/
		autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
			@Override
			public void onPlaceSelected(Place place) {
				txtVw.setText(place.getName());
			}
			@Override
			public void onError(Status status) {
				txtVw.setText(status.toString());
			}
		});
	}
}