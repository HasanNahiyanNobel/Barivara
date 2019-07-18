package com.example.barivara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandlordHouseAddress extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);
		listenButtons();
	}

	public void listenButtons() {
		final Context CONTEXT = this;
		Button button = (Button) findViewById(R.id.button_ok);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CONTEXT, LandlordHouseAttributes.class);
				startActivity(intent);
			}
		});
	}
}
