package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
	private ListView listView;
	int backButtonCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		backButtonCount = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//listView = (ListView) findViewById(R.id.pagination_list);

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl("http://192.168.0.108:8000/")
				.addConverterFactory(GsonConverterFactory.create());

		Retrofit retrofit = builder.build();

		String type_ob = "Houses";

		HouseClient houseClient = retrofit.create(HouseClient.class);

		Call<List<House>> callListHouse = HouseClient.houses_list();
	}

	@Override
	public void onBackPressed() {
		if(backButtonCount>=1) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		else {
			Toast.makeText(this, getResources().getString(R.string.appCloseMessage), Toast.LENGTH_SHORT).show();
			backButtonCount++;
		}
	}

	public void loginOrRegister (View view) {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
