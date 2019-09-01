package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	int backButtonCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		backButtonCount = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
