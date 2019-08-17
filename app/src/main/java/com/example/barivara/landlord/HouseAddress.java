package com.example.barivara.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class HouseAddress extends AppCompatActivity {
	AutoCompleteTextView actvDistricts, actvUpazila, actvArea;

	private ArrayList<String> nameOfDivisions = new ArrayList<String>();

	private ArrayList<String> nameOfDistricts = new ArrayList<String>();

	private ArrayList<String> nameOfUpazilas = new ArrayList<String>();

	private ArrayList<String> nameOfAreas = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);

		getLocationsFromFile("districts.txt",nameOfDistricts);

		actvDistricts = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> districtsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfDistricts);
		actvDistricts.setAdapter(districtsArrayAdapter);
		actvDistricts.setThreshold(1);

		getLocationsFromFile("upazilas.txt",nameOfUpazilas);

		actvUpazila = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
		ArrayAdapter<String> upazilasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfUpazilas);
		actvUpazila.setAdapter(upazilasArrayAdapter);
		actvUpazila.setThreshold(1);

		nameOfAreas.addAll(Arrays.asList("মোহাম্মাদপুর","খিলগাঁও","উত্তরা","পলাশী","শাঁখারী বাজার"));
		//getLocationsFromFile("districts.txt",nameOfAreas);

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

	private String getLocationsFromFile(String fileName, ArrayList<String> nameOfLocations) {
		String returnString = "";
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
			String aLine;
			StringBuilder stringBuilder = new StringBuilder();
			while ((aLine=bufferedReader.readLine())!=null) {
				stringBuilder.append(aLine);
				nameOfLocations.add(aLine);
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

	public void goToHouseAttributes(View view) {
		Intent intent = new Intent(this, HouseAttributes.class);
		startActivity(intent);
	}
}
