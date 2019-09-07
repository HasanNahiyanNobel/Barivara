package com.example.barivara.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;
import com.example.barivara.api.Home;
import com.example.barivara.api.HomeClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HouseAttributes extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl("http://192.168.0.108:8000/")
				//    .baseUrl("https://api.github.com/")
				.addConverterFactory(GsonConverterFactory.create());

		Retrofit retrofit = builder.build();

		HomeClient homeClient = retrofit.create(HomeClient.class);

		Call<List<Home>> call_home = homeClient.homeAll();

		call_home.enqueue(new Callback<List<Home>>() {
			@Override
			public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {
				List<Home> repos = response.body();

				for(Home rep: repos){
					Toast.makeText(HouseAttributes.this, rep.getBariwalar_nam(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<List<Home>> call, Throwable t) {
				Toast.makeText(HouseAttributes.this, "error :(", Toast.LENGTH_SHORT).show();
			}
		});


	}

	public void goToRentAmount(View view) {
		Intent intent = new Intent(this, RentAmount.class);
		startActivity(intent);
	}
}
