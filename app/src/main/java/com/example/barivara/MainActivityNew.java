package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityNew extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_new);
	}

	public void goToLandlordMain (View view) {
		Intent intent = new Intent(this, com.example.barivara.landlord.MainPage.class);
		startActivity(intent);
	}

	public void goToRenterMain (View view) {
		Intent intent = new Intent(this, com.example.barivara.renter.MainPage.class);
		startActivity(intent);
	}
}
