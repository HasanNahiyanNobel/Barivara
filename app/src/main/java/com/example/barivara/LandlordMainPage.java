package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LandlordMainPage extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_main_page);
	}

	public void goToCurrentHouses (View view) {

	}

	public void addANewHouse (View view) {
		Intent intent = new Intent(this, LandlordHouseAddress_Old.class);
		startActivity(intent);
	}
}