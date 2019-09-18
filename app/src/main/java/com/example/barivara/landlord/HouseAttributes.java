package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HouseAttributes extends AppCompatActivity {
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);

		Toast.makeText(this, HouseAddress.newHouse.getElaka(), Toast.LENGTH_SHORT).show();
	}

	public void goToRentAmount(View view) {
		Intent intent = new Intent(this, RentAmount.class);
		startActivity(intent);
	}
}
