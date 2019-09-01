package com.example.barivara.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.HomeActivity;
import com.example.barivara.R;

public class MainPage extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_main_page);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	public void goToMyHouses(View view) {
		Intent intent = new Intent(this,MyHouses.class);
		startActivity(intent);
	}

	public void addANewHouse (View view) {
		Intent intent = new Intent(this, HouseAddress.class);
		startActivity(intent);
	}
}