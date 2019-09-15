package com.example.barivara;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * The following functions and a class are taken from http://hmkcode.com/android-send-json-data-to-server/
 * 1. public boolean checkNetworkConnection()
 * 2. private String HttpPost(String myUrl) throws IOException, JSONException
 * 3. private JSONObject buildJsonObject() throws JSONException
 * 4. private class HTTPAsyncTask extends AsyncTask<String, Void, String>
 * 5. private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException
 */

public class RegistrationActivity extends AppCompatActivity {
	EditText nameEditText, emailEditText, passwordEditText;
	TextView tvIsConnected, tvResult;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		tvIsConnected = findViewById(R.id.tvIsConnected);
		tvResult = findViewById(R.id.tvResult);
	}

	public void register(View view) {
		nameEditText = findViewById(R.id.editText5);
		emailEditText = findViewById(R.id.editText7);
		passwordEditText = findViewById(R.id.editText8);

		if(checkNetworkConnection())
			new HTTPAsyncTask().execute("http://192.168.0.109:8000/users/"); //TODO Change this string to variable.
		else
			Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show(); //TODO Change this string to variable too!

		/*Toast.makeText(this, nameEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, emailEditText.getText(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, passwordEditText.getText(), Toast.LENGTH_SHORT).show();*/
		Toast.makeText(this, getString(R.string.registration_successful_toast), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	public boolean checkNetworkConnection() {
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		boolean isConnected = false;

		if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
			// show "Connected" & type of network "WIFI or MOBILE"
			tvIsConnected.setText("Connected "+networkInfo.getTypeName());
			// change background color to red
			tvIsConnected.setBackgroundColor(0xFF7CCC26);
		}
		else {
			// show "Not Connected"
			tvIsConnected.setText("Not Connected");
			// change background color to green
			tvIsConnected.setBackgroundColor(0xFFFF0000);
		}

		return isConnected;
	}

	private String HttpPost(String myUrl) throws IOException, JSONException {
		String result = "";
		URL url = new URL(myUrl);

		// 1. create HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		// 2. build JSON object
		JSONObject jsonObject = buildJsonObject();

		// 3. add JSON content to POST request body
		setPostRequestContent(conn, jsonObject);

		// 4. make POST request to the given URL
		conn.connect();

		// 5. return response message
		return conn.getResponseMessage()+"";

	}

	private JSONObject buildJsonObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("name", nameEditText.getText().toString());
		jsonObject.accumulate("email",  emailEditText.getText().toString());
		jsonObject.accumulate("password",  passwordEditText.getText().toString());
		return jsonObject;
	}

	private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(jsonObject.toString());
		Log.i(MainActivity.class.toString(), jsonObject.toString());
		writer.flush();
		writer.close();
		os.close();
	}

	private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			// params comes from the execute() call: params[0] is the url.
			try {
				try {
					return HttpPost(urls[0]);
				} catch (JSONException e) {
					e.printStackTrace();
					return "Error!";
				}
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			tvResult.setText(result);
		}
	}
}
