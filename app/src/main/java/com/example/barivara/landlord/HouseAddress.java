package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.example.barivara.api.House;
import com.example.barivara.api.HouseClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HouseAddress extends AppCompatActivity {
	AutoCompleteTextView actvDistricts, actvUpazila, actvArea;

	private ArrayList<String> nameOfDivisions = new ArrayList<>();
	private ArrayList<String> nameOfDistricts = new ArrayList<>();
	private ArrayList<String> nameOfUpazilas = new ArrayList<>();
	private ArrayList<String> nameOfElakas = new ArrayList<>();

	private ArrayList<House> houseArrayList = new ArrayList<>();

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_address);

		//getLocationsFromFile("districts.txt",nameOfDistricts);
		getListOfHouses();

		actvDistricts = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> districtsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfDistricts);
		actvDistricts.setAdapter(districtsArrayAdapter);
		actvDistricts.setThreshold(1);

		//getLocationsFromFile("upazilas.txt",nameOfUpazilas);

		actvUpazila = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
		ArrayAdapter<String> upazilasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,nameOfUpazilas);
		actvUpazila.setAdapter(upazilasArrayAdapter);
		actvUpazila.setThreshold(1);

		//nameOfElakas.addAll(Arrays.asList("মোহাম্মাদপুর","খিলগাঁও","উত্তরা","পলাশী","শাঁখারী বাজার"));
		//getLocationsFromFile("districts.txt",nameOfElakas);

		actvArea = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
		ArrayAdapter<String> areasArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, nameOfElakas);
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

	private void getListOfHouses() {
		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(getString(R.string.server_and_port))
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.build();
		HouseClient houseClient = retrofit.create(HouseClient.class);
		Call<List<House>> houseListCall = houseClient.houseAll();
		houseListCall.enqueue(new Callback<List<House>>() {
			@Override
			public void onResponse(Call<List<House>> call, Response<List<House>> response) {
				List<House> houseList = response.body();
				for(House house : houseList){
					if (!nameOfDistricts.contains(house.getZilla())) {
						nameOfDistricts.add(house.getZilla());
					}
					if (!nameOfUpazilas.contains(house.getUpazilla())) {
						nameOfUpazilas.add(house.getUpazilla());
					}
					if (!nameOfElakas.contains(house.getElaka())) {
						nameOfElakas.add(house.getElaka());
					}
					houseArrayList.add(house);
				}
			}
			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(HouseAddress.this, getString(R.string.connection_failure_toast), Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void goToHouseAttributes(View view) {
		Intent intent = new Intent(this, HouseAttributes.class);
		startActivity(intent);
	}
}
