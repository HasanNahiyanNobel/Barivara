package com.example.barivara.renter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.example.barivara.SimpleLoginActivity;

public class MainPage extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_main_page);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(this, SimpleLoginActivity.class);
		startActivity(intent);
	}

	public void findAHouse(View view) {
		Intent intent = new Intent(this,FindAHouse.class);
		startActivity(intent);
	}
}
