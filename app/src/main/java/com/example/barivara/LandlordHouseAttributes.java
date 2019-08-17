package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LandlordHouseAttributes extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);
	}

	public void goToRentAmount(View view) {
		Intent intent = new Intent(this,LandlordRentAmount.class);
		startActivity(intent);
	}
}
