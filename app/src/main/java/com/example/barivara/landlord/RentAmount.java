package com.example.barivara.landlord;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barivara.MainActivity;
import com.example.barivara.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RentAmount extends AppCompatActivity {
	EditText barivataEditText;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landlord_rent_amount);
		initializeViews();
	}

	public void finishAddingHouse(View view) {
		/**
		 * Finishes the process of adding a house, and goes to Landlord's main page.
		 */
		HouseAddress.newHouse.setBarivara(Integer.parseInt(barivataEditText.getText().toString()));

		new HTTPAsyncTask().execute(getString(R.string.server_and_port)+getString(R.string.server_house_data));

		Intent intent = new Intent(this, MainPage.class);
		startActivity(intent);
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
		/*// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			tvResult.setText(result);
		}*/
	}

	private String HttpPost(String myUrl) throws IOException, JSONException {
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
		jsonObject.accumulate("bariwalar_nam", HouseAddress.newHouse.getBariwalar_nam());
		jsonObject.accumulate("bariwalar_reg_id", HouseAddress.newHouse.getBariwalar_reg_id());
		jsonObject.accumulate("zilla", HouseAddress.newHouse.getZilla());
		jsonObject.accumulate("upazilla", HouseAddress.newHouse.getUpazilla());
		jsonObject.accumulate("elaka", HouseAddress.newHouse.getElaka());
		jsonObject.accumulate("family_basha", HouseAddress.newHouse.isFamily_basha());
		jsonObject.accumulate("shobar_ghorer_shongkhya", HouseAddress.newHouse.getShobar_ghorer_shongkhya());
		jsonObject.accumulate("bathroomer_shongkhya", HouseAddress.newHouse.getBathroomer_shongkhya());
		jsonObject.accumulate("khabar_ghor", HouseAddress.newHouse.isKhabar_ghor());
		jsonObject.accumulate("rannaghor", HouseAddress.newHouse.isRannaghor());
		jsonObject.accumulate("barivara", HouseAddress.newHouse.getBarivara());
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

	private void initializeViews() {
		barivataEditText = findViewById(R.id.editText);
	}
}
