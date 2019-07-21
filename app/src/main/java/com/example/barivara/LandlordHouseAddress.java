package com.example.barivara;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LandlordHouseAddress extends AppCompatActivity {
	AutoCompleteTextView actvDistricts, actvUpazila, actvArea;

	private ArrayList<String> nameOfDivisions = new ArrayList<String>();

	private ArrayList<String> nameOfDistricts = new ArrayList<String>();

	private ArrayList<String> nameOfUpazilas = new ArrayList<String>();

	private ArrayList<String> nameOfAreas = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);

		nameOfDistricts.addAll(Arrays.asList("ঢাকা","নারায়ণগঞ্জ","গাজীপুর","মুন্সীগঞ্জ","মানিকগঞ্জ","নরসিংদী","ফরিদপুর","মাদারীপুর","গোপালগঞ্জ","রাজবাড়ী","শরীয়তপুর","টাঙ্গাইল","কিশোরগঞ্জ"));

		actvDistricts = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> districtsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfDistricts);
		actvDistricts.setAdapter(districtsArrayAdapter);
		actvDistricts.setThreshold(1);

		nameOfUpazilas.addAll(Arrays.asList("ঢাকা","ধামরাই","দোহার","নবাবগঞ্জ","কেরানীগঞ্জ","সাভার"));

		actvUpazila = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
		ArrayAdapter<String> upazilasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfUpazilas);
		actvUpazila.setAdapter(upazilasArrayAdapter);
		actvUpazila.setThreshold(1);

		nameOfAreas.addAll(Arrays.asList("মোহাম্মাদপুর","খিলগাঁও","উত্তরা","পলাশী","শাঁখারী বাজার"));
		String stringFromFile = readFromFile(this);
		nameOfAreas.add(stringFromFile);

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

	private String readFromFile(Context context) {
		String returnString = "";
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("districts.txt")));
			String aLine;
			StringBuilder stringBuilder = new StringBuilder();
			while ((aLine=bufferedReader.readLine())!=null) {
				stringBuilder.append(aLine);
			}
			returnString = stringBuilder.toString();
		} catch (FileNotFoundException e) {
			Log.e("Locations of Bangladesh","File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("Locations of Bangladesh", "Can not read file: " + e.toString());
		} finally {
			if (bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					Log.e("Closing BufferedReader", "Can not close bufferedReader: " + e.toString());
				}
			}
		}
		return returnString;
	}
}
