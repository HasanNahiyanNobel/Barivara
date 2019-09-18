package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HouseAttributes extends AppCompatActivity {
	ToggleButton toggleButton;
	EditText shobarGhorEditText, bathroomEditText;
	CheckBox khabarGhorCheckBox, rannaghorCheckBox;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);

		initializeViews();
	}

	public void goToRentAmount(View view) {
		if (toggleButton.getText().toString().equals(getString(R.string.toggle_button_mess))) {
			HouseAddress.newHouse.setFamily_basha(false);
		}
		else {
			HouseAddress.newHouse.setFamily_basha(true);
		}
		HouseAddress.newHouse.setShobar_ghorer_shongkhya(Integer.parseInt(shobarGhorEditText.getText().toString()));
		HouseAddress.newHouse.setBathroomer_shongkhya(Integer.parseInt(bathroomEditText.getText().toString()));
		HouseAddress.newHouse.setKhabar_ghor(khabarGhorCheckBox.isChecked());
		HouseAddress.newHouse.setRannaghor(rannaghorCheckBox.isChecked());

		Intent intent = new Intent(this, RentAmount.class);
		startActivity(intent);
	}

	private void initializeViews() {
		toggleButton = findViewById(R.id.toggleButton2);
		shobarGhorEditText = findViewById(R.id.editText4);
		bathroomEditText = findViewById(R.id.editText6);
		khabarGhorCheckBox = findViewById(R.id.checkBox3);
		rannaghorCheckBox = findViewById(R.id.checkBox4);
	}
}
