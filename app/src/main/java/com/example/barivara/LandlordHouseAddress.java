package com.example.barivara;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandlordHouseAddress extends AppCompatActivity {
	AutoCompleteTextView actvDistricts, actvUpazila, actvArea;

	private String[] nameOfDivisions;

	private static String[] nameOfDistricts = new String[]{"ঢাকা","নারায়ণগঞ্জ","গাজীপুর","মুন্সীগঞ্জ","মানিকগঞ্জ","নরসিংদী","ফরিদপুর","মাদারীপুর","গোপালগঞ্জ","রাজবাড়ী","শরীয়তপুর","টাঙ্গাইল","কিশোরগঞ্জ"};

	private static String[] nameOfUpazilas = new String[]{"ঢাকা","ধামরাই","দোহার","কেরানীগঞ্জ","নবাবগঞ্জ","সাভার"};

	private static String[] nameOfAreas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);

		actvDistricts = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> districtsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfDistricts);
		actvDistricts.setAdapter(districtsArrayAdapter);
		actvDistricts.setThreshold(1);

		actvUpazila = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
		ArrayAdapter<String> upazilasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfUpazilas);
		actvUpazila.setAdapter(upazilasArrayAdapter);
		actvUpazila.setThreshold(1);

		nameOfAreas = new String[]{"মোহাম্মাদপুর","খিলগাঁও","উত্তরা","পলাশী"};

		actvArea = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
		ArrayAdapter<String> areasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, nameOfAreas);
		actvArea.setAdapter(areasArrayAdapter);
		actvArea.setThreshold(1);
	}

	public void showDropdownOfDistricts(View view) {
		actvDistricts.showDropDown();
	}

	public void showDropdownOfUpazilas(View view) {
		actvUpazila.showDropDown();
	}

	public void showDropdownOfAreas(View view) {
		actvArea.showDropDown();
	}
}
