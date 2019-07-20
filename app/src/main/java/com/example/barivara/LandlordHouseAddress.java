package com.example.barivara;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandlordHouseAddress extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);
	}

	private AutoCompleteTextView mSearchText;
	private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;

	private void init() {

	}
}
