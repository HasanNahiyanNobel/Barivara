package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void goToLandlordMain (View view) {
		Intent intent = new Intent(this,LandlordMainPage.class);
		startActivity(intent);
	}

	public void goToRenterMain (View view) {
		Intent intent = new Intent(this,RenterMainPage.class);
		startActivity(intent);
	}
}
