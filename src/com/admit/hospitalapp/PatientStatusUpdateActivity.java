package com.admit.hospitalapp;

import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_ID;
import in.digitechlab.hospitalapp.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.admit.hospitalapp.HospitalActivity.AttemptLogin;
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
//import static com.admit.hospitalapp.HospitalActivity.PATIENT_IDT;


public class PatientStatusUpdateActivity extends ActionBarActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String STATUS_URL = "http://www.digitechlab.in/hospitallogin/patient_status.php";
	private static final String STATUS_UPDATE_URL = "http://www.digitechlab.in/hospitallogin/update_staff_status.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	//private static final String P_STATUS = "patient_status";
	
	public static final String UPDATED_STATUS = "0";
	
	String updated_status, patient_idd;	
	TextView  response_status;
	EditText update_status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_staff_status);
		
        Intent intent = getIntent();
        if (null != intent){
        patient_idd = intent.getStringExtra(TAG_PATIENT_ID);
        }
        
        update_status = (EditText)findViewById(R.id.textViewc5);
		
		Button update_button = (Button) findViewById(R.id.c_status);
		 
		update_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	
		        	 {
		        		updated_status = update_status.getText().toString();
		        		 
		        		new UpdateStatus().execute();
		        		
		        	 }
		        }
		        });
		
		response_status = (TextView) findViewById(R.id.TextViewc2);
		//response_status.setText(P_STATUS);


        new FetchStatus().execute();
		
	}
	
	class FetchStatus extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PatientStatusUpdateActivity.this);
			pDialog.setMessage("Fetching status...");
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

				params.add(new BasicNameValuePair("userid", patient_idd));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(STATUS_URL, "POST",
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
			
			response_status.setText(s);
			
			pDialog.dismiss();

		}

	}
	
	
    class UpdateStatus extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PatientStatusUpdateActivity.this);
			pDialog.setMessage("Updating status...");
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
				

				params.add(new BasicNameValuePair("userid", patient_idd));
				params.add(new BasicNameValuePair("status_updated", updated_status));

				
				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(STATUS_UPDATE_URL, "POST",
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

		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			
			pDialog.dismiss();
			
            if (file_url != null){
            	Toast.makeText(PatientStatusUpdateActivity.this, "Status Updated", Toast.LENGTH_LONG).show();
            }

    	    finish();

		}

	}
	
	

}
