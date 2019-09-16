package com.example.barivara.renter;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
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
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FindAHouse extends AppCompatActivity {
	TextView.OnEditorActionListener listenerTextView3 = new TextView.OnEditorActionListener() {
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId==EditorInfo.IME_NULL && event.getAction()==KeyEvent.ACTION_DOWN) {
				Toast.makeText(FindAHouse.this, "Ghau!", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
	};
	TextView textView3;
	AutoCompleteTextView autoCompleteTextView;
	ArrayList<String> placeName = new ArrayList<>();

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

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
					placeName.add(house.getElaka()+" ("+house.getUpazilla()+", "+house.getZilla()+")");
				}
			}
			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(FindAHouse.this, getString(R.string.connection_failure_toast), Toast.LENGTH_SHORT).show();
			}
		});

		autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,placeName);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setAdapter(arrayAdapter);

		textView3 = findViewById(R.id.textView17);
		textView3.setOnEditorActionListener(listenerTextView3);
	}
}
