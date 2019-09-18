package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RentAmount extends AppCompatActivity {
	EditText barivataEditText;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_rent_amount);
		initializeViews();
	}

	public void finishAddingHouse(View view) {
		/**
		 * Finishes the proccess of adding a house, and goes to Landlord's main page.
		 */
		HouseAddress.newHouse.setBarivara(Integer.parseInt(barivataEditText.getText().toString()));

		Intent intent = new Intent(this, MainPage.class);
		startActivity(intent);
	}

	private void initializeViews() {
		barivataEditText = findViewById(R.id.editText);
	}
}
