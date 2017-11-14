package com.admit.hospitalapp;

import in.digitechlab.hospitalapp.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.admit.hospitalapp.CurrentAdmissionsActivity.TAG_ADMISSION_ID;


public class SingleAdmissionActivity extends ActionBarActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String SINGLE_ADM_URL = "http://www.digitechlab.in/hospitallogin/single_admission.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	//private static final String P_STATUS = "patient_status";
	
    String admission_id_here;	
	TextView admission_details;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_single_admission);
		
		Intent intent = getIntent();
        if (null != intent){
        admission_id_here = intent.getStringExtra(TAG_ADMISSION_ID);
        }
		
		 Button ok_button = (Button) findViewById(R.id.ok_adadmission);
		 
		 ok_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	
		        	 {
		        		 
		        		finish();
		        		
		        	 }
		        }
		        });

		
	    admission_details = (TextView) findViewById(R.id.textViewsada1);
		//response_status.setText(P_STATUS);


        new FetchStatus().execute();
		
	}
	
	class FetchStatus extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SingleAdmissionActivity.this);
			pDialog.setMessage("Fetching Details...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("userid", admission_id_here));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(SINGLE_ADM_URL, "POST",
						params);

				// check your log for json response
				Log.d("Status attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					Log.d("Status Found!", json.toString());
					// save user data
					 // String response_patient_status = json.getString(P_STATUS);
                    //Toast.makeText(PStatusActivity.this, response_patient_status, Toast.LENGTH_LONG).show();
					//response_status.setText(P_STATUS);
					
					return json.getString(TAG_MESSAGE);
					
				} else {
					Log.d("Status Not Found!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		protected void onPostExecute(String s) {
			// dismiss the dialog once product deleted
			
			super.onPostExecute(s);
			
			admission_details.setText(s);
			
			pDialog.dismiss();

		}

	}
	
}
