package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.example.barivara.api.House;
import com.example.barivara.api.HouseClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HouseAddress extends AppCompatActivity {
	SharedPreferences sharedPreferences;

	AutoCompleteTextView actvZillas, actvUpazila, actvElaka;

	public static House newHouse = new House();

	private ArrayList<String> nameOfZillas = new ArrayList<>();
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

		sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);

		getListOfHouses();

		initializeAutoCompleteTextViews();
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
					if (!nameOfZillas.contains(house.getZilla())) {
						nameOfZillas.add(house.getZilla());
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

	private void initializeAutoCompleteTextViews() {
		actvZillas = findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter<String> zillasArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameOfZillas);
		actvZillas.setAdapter(zillasArrayAdapter);
		actvZillas.setThreshold(1);

		actvUpazila = findViewById(R.id.autoCompleteTextView2);
		ArrayAdapter<String> upazilasArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameOfUpazilas);
		actvUpazila.setAdapter(upazilasArrayAdapter);
		actvUpazila.setThreshold(1);

		actvElaka = findViewById(R.id.autoCompleteTextView3);
		ArrayAdapter<String> elakasArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameOfElakas);
		actvElaka.setAdapter(elakasArrayAdapter);
		actvElaka.setThreshold(1);
	}

	public void showDropdownOfZillas(View view) {
		actvZillas.showDropDown();
	}

	public void showDropdownOfUpazilas(View view) {
		actvUpazila.showDropDown();
	}

	public void showDropdownOfElakas(View view) {
		actvElaka.showDropDown();
	}

	public void goToHouseAttributes(View view) {
		newHouse.setBariwalar_reg_id(sharedPreferences.getInt("userID",-1));
		newHouse.setBariwalar_nam(sharedPreferences.getString("userName",""));
		newHouse.setZilla(actvZillas.getText().toString());
		newHouse.setUpazilla(actvUpazila.getText().toString());
		newHouse.setElaka(actvElaka.getText().toString());

		Intent intent = new Intent(this, HouseAttributes.class);
		startActivity(intent);
	}
}
