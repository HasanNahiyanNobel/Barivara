package com.example.barivara.renter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

public class MainPage extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_main_page);
	}

	public void findAHouse(View view) {
		Intent intent = new Intent(this,FindAHouse.class);
		startActivity(intent);
	}
}
