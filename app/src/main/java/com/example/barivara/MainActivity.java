package com.example.barivara;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the login activity. Don't know why, refactoring it's name causes problem.
 * So I just let it have it's old name, MainActivity.
 */

public class MainActivity extends AppCompatActivity {
	int backButtonCount;
	EditText emailEditText, passwordEditText;

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
			Toast.makeText(this, getResources().getString(R.string.app_close_toast), Toast.LENGTH_SHORT).show();
			backButtonCount++;
		}
	}

	public void login(View view) {
		emailEditText = findViewById(R.id.editText2);
		passwordEditText = findViewById(R.id.editText3);
		/*Toast.makeText(this, emailEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, passwordEditText.getText(), Toast.LENGTH_SHORT).show();*/

		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}
}
