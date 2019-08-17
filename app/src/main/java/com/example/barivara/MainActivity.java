package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.renter.MainPage;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void goToLandlordMain (View view) {
		Intent intent = new Intent(this, com.example.barivara.landlord.MainPage.class);
		startActivity(intent);
	}

	public void goToRenterMain (View view) {
		Intent intent = new Intent(this, MainPage.class);
		startActivity(intent);
	}
}
