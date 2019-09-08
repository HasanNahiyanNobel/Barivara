package com.example.barivara.renter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.example.barivara.api.House;
import com.example.barivara.api.HouseClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindAHouse extends AppCompatActivity {
	AutoCompleteTextView autoCompleteTextView;
	String[] placeName = {"মদনপুর", "আক্কেলপুর", "ময়মনসিংহ", "ফুলবাড়িয়া", "আনন্দনগর"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_find_a_house);

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl("http://192.168.0.108:8000/")
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.build();
		HouseClient houseClient = retrofit.create(HouseClient.class);
		Call<List<House>> houseListCall = houseClient.houseAll();
		houseListCall.enqueue(new Callback<List<House>>() {
			@Override
			public void onResponse(Call<List<House>> call, Response<List<House>> response) {
				List<House> houseList = response.body();

				for(House house: houseList){
					Toast.makeText(FindAHouse.this, house.getElaka(), Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(FindAHouse.this, "error :(", Toast.LENGTH_SHORT).show();
			}
		});

		autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, placeName);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setAdapter(arrayAdapter);
	}
}
