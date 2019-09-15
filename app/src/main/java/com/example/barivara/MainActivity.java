package com.example.barivara;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.api.User;
import com.example.barivara.api.UserClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * This is the login activity. Don't know why, refactoring it's name causes problem.
 * So I just let it have it's old name, MainActivity.
 */

public class MainActivity extends AppCompatActivity {
	int backButtonCount;
	EditText emailEditText, passwordEditText;
	ArrayList<String> userEmailData = new ArrayList<>();
	ArrayList<String> userPasswordData = new ArrayList<>();

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		backButtonCount = 0;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(getString(R.string.server_and_port))
				.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.build();
		UserClient userClient = retrofit.create(UserClient.class);
		Call<List<User>> userListCall = userClient.userAll();
		userListCall.enqueue(new Callback<List<User>>() {
			@Override
			public void onResponse(Call<List<User>> call, Response<List<User>> response) {
				List<User> userList = response.body();
				for(User user : userList){
					userEmailData.add(user.getEmail());
					userPasswordData.add(user.getPassword());
				}
			}
			@Override
			public void onFailure(Call<List<User>> call, Throwable t) {
				Toast.makeText(MainActivity.this, getString(R.string.connection_failure_toast), Toast.LENGTH_SHORT).show();
			}
		});
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
		emailEditText = findViewById(R.id.emailEditText);
		passwordEditText = findViewById(R.id.passwordEditText);

		isASuccessfulLogin(emailEditText.toString(), passwordEditText.getText().toString());

		/*if (isASuccessfulLogin(emailEditText.toString(), passwordEditText.getText().toString())) {
			Toast.makeText(this, "লগইন ঠিক আছে", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, RegistrationActivity.class);
			startActivity(intent);
		}
		else {
			Toast.makeText(this, "পাসওয়ার্ড বা ইমেইল টাইপ করতে ভুল হয়েছে!", Toast.LENGTH_SHORT).show();
		}*/
	}

	private boolean isASuccessfulLogin(String email, String password) {
		//Check whether email address matches password.
		if (userEmailData.size()>0) {
			Toast.makeText(MainActivity.this, userEmailData.get(0) + ", " + userEmailData.size(), Toast.LENGTH_SHORT).show();
		}
		/*for (int i=0; i<userEmailData.size(); i++) {
			if (email.equals(userEmailData.get(i)) && password.equals(userEmailData.get(i))) {
				return true;
			}
		}*/
		return false;
	}
}
