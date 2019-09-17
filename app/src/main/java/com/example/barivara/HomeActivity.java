package com.example.barivara;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {
	int backButtonCount;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		backButtonCount = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
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
			Toast.makeText(this, getResources().getString(R.string.app_close_toast), Toast.LENGTH_SHORT).show();
			backButtonCount++;
		}
	}

	public void logout(View view) {
		SharedPreferences logoutSharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = logoutSharedPreferences.edit();
		editor.clear();
		editor.commit();
		finish();
	}

	public void goToLandlordMain(View view) {
		Intent intent = new Intent(this, com.example.barivara.landlord.MainPage.class);
		startActivity(intent);
	}

	public void goToRenterMain(View view) {
		Intent intent = new Intent(this, com.example.barivara.renter.MainPage.class);
		startActivity(intent);
	}
}
