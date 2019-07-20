package com.example.barivara;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandlordHouseAddress extends AppCompatActivity {
	String upazila;

	AutoCompleteTextView actv1;

	private String[] nameOfDivisions;

	private static String[] nameOfDistricts = new String[]{"ঢাকা","নারায়ণগঞ্জ","গাজীপুর","মুন্সীগঞ্জ","মানিকগঞ্জ","নরসিংদী","ফরিদপুর","মাদারীপুর","গোপালগঞ্জ","রাজবাড়ী","শরীয়তপুর","টাঙ্গাইল","কিশোরগঞ্জ"};

	private static String[] nameOfUpazilas = new String[]{"ঢাকা","ধামরাই","দোহার","কেরানীগঞ্জ","নবাবগঞ্জ","সাভার"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);

		actv1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> districtsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfUpazilas);
		actv1.setAdapter(districtsArrayAdapter);
		actv1.setThreshold(1);

		upazila = actv1.getText().toString();

		final TextView tv = (TextView) findViewById(R.id.textView);
		tv.setText(upazila);
	}

	public void showDropdownUpazilas(View view) {
		actv1.showDropDown();
	}
}
