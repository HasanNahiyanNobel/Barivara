package com.example.barivara.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

public class HouseAttributes extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);
	}

	public void goToRentAmount(View view) {
		Intent intent = new Intent(this, RentAmount.class);
		startActivity(intent);
	}
}
