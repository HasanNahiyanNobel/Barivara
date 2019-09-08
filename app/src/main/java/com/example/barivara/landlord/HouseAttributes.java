package com.example.barivara.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

public class HouseAttributes extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_house_attributes);

		/*Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl("http://192.168.0.108:8000/")
				//    .baseUrl("https://api.github.com/")
				.addConverterFactory(GsonConverterFactory.create());

		Retrofit retrofit = builder.build();

		HouseClient homeClient = retrofit.create(HouseClient.class);

		Call<List<House>> call_home = homeClient.houseAll();

		call_home.enqueue(new Callback<List<House>>() {
			@Override
			public void onResponse(Call<List<House>> call, Response<List<House>> response) {
				List<House> repos = response.body();

				for(House rep: repos){
					Toast.makeText(HouseAttributes.this, rep.getBariwalar_nam(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(HouseAttributes.this, "error :(", Toast.LENGTH_SHORT).show();
			}
		});*/
	}

	public void goToRentAmount(View view) {
		Intent intent = new Intent(this, RentAmount.class);
		startActivity(intent);
	}
}
