package com.example.barivara.landlord;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.R;

public class MyHouses extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_my_houses);
	}

	public void imageClicked(View view) {
		Toast.makeText(this, "ছবিতে চাপ পড়সে!!", Toast.LENGTH_SHORT).show();
	}
}
