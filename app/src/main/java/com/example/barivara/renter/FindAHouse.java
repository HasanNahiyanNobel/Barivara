package com.example.barivara.renter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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
	TableLayout tableInScroll;
	AutoCompleteTextView autoCompleteTextView;
	ArrayList<House> houseArrayList = new ArrayList<>();
	ArrayList<String> placeNameList = new ArrayList<>();

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renter_find_a_house);

		tableInScroll = findViewById(R.id.tableInScroll);

		getListOfHouses();

		autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, placeNameList);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setAdapter(arrayAdapter);

		/**
		 * Taken from https://stackoverflow.com/a/41671078/6606776
		 */
		autoCompleteTextView.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
			View view = getCurrentFocus();
			if (view != null) {
				InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				showHousesInSearchedPlace(autoCompleteTextView.getText().toString());
			}
		});
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
					if (!placeNameList.contains(house.getZilla())) {
						placeNameList.add(house.getZilla());
					}
					if (!placeNameList.contains(house.getUpazilla()+", "+house.getZilla())) {
						placeNameList.add(house.getUpazilla()+", "+house.getZilla());
					}
					if (!placeNameList.contains(house.getElaka()+" ("+house.getUpazilla()+", "+house.getZilla()+")")) {
						placeNameList.add(house.getElaka() + " (" + house.getUpazilla() + ", " + house.getZilla() + ")");
					}

					houseArrayList.add(house);
				}
			}
			@Override
			public void onFailure(Call<List<House>> call, Throwable t) {
				Toast.makeText(FindAHouse.this, getString(R.string.connection_failure_toast), Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void showHousesInSearchedPlace(String placeName) {
		tableInScroll.removeAllViews();

		for (int i=0; i<houseArrayList.size(); i++) {
			if (placeName.equals(houseArrayList.get(i).getZilla())
			|| placeName.equals(houseArrayList.get(i).getUpazilla()+", "+houseArrayList.get(i).getZilla())
			|| placeName.equals(houseArrayList.get(i).getElaka()+" ("+houseArrayList.get(i).getUpazilla()+", "+houseArrayList.get(i).getZilla()+")")) {
				TextView textViewTemp = new TextView(this);
				textViewTemp.setText(houseArrayList.get(i).getBarivara()
						+"\n"
						+houseArrayList.get(i).getElaka()+", "+houseArrayList.get(i).getUpazilla()+", "+houseArrayList.get(i).getZilla());
				textViewTemp.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.default_font_path)));
				/*textViewTemp.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));*/

				Button buttonTemp = new Button(this);
				buttonTemp.setText(getString(R.string.bistarito));
				/*buttonTemp.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));*/


				TableRow tableRowTemp = new TableRow(this);
				tableRowTemp.setLayoutParams(new TableLayout.LayoutParams(
						TableLayout.LayoutParams.MATCH_PARENT,
						TableLayout.LayoutParams.WRAP_CONTENT));
				tableRowTemp.addView(textViewTemp);
				tableRowTemp.addView(buttonTemp);

				tableInScroll.addView(tableRowTemp);
			}
		}
	}
}
