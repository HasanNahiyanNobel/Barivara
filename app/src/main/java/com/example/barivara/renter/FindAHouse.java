package com.example.barivara.renter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
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

public class FindAHouse extends AppCompatActivity {
	AutoCompleteTextView autoCompleteTextView;
	TextView textView1 = findViewById(R.id.textView10);
	ArrayList<String> placeName = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_find_a_house);

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
					//Toast.makeText(FindAHouse.this, house.getElaka(), Toast.LENGTH_SHORT).show();
					placeName.add(house.getElaka()+" ("+house.getUpazilla()+", "+house.getZilla()+")");
				}
			}
			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(FindAHouse.this, "error :(", Toast.LENGTH_SHORT).show();
			}
		});

		autoCompleteTextView = findViewById(R.id.autoCompleteTextView); //TODO Declare this globally, perhaps?
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,placeName);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setAdapter(arrayAdapter);
		//Toast.makeText(this, "before here", Toast.LENGTH_SHORT).show();
		/*if (placeName.size()>0) {
			Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
			textView1.setText(placeName.get(0));
		}*/
	}
}
