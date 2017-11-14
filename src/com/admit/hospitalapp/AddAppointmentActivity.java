package com.admit.hospitalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import in.digitechlab.hospitalapp.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AddAppointmentActivity extends ActionBarActivity  implements OnClickListener{


	private EditText patient_name, patient_age, patient_sex, patient_phone, patient_problem;
	private Button  submit;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String ADD_APPOINTMENT_URL = "http://www.digitechlab.in/hospitallogin/add_appointment.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_request);
		
		patient_name = (EditText)findViewById(R.id.ETPr01);
		patient_age = (EditText)findViewById(R.id.ETPr04);
		patient_sex = (EditText)findViewById(R.id.ETPr02);
		patient_phone = (EditText)findViewById(R.id.ETPr03);
		patient_problem = (EditText)findViewById(R.id.ETPr05);

		submit = (Button)findViewById(R.id.ETPr01_button);
		submit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
				new AddAppointment().execute();
		
	}
	
	class AddAppointment extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddAppointmentActivity.this);
            pDialog.setMessage("Appointment Creating...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String pid = patient_name.getText().toString();
            String aid = patient_age.getText().toString();
            String sid = patient_sex.getText().toString();
            String hid = patient_phone.getText().toString();
            String rid = patient_problem.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", pid));
                params.add(new BasicNameValuePair("age", aid));
                params.add(new BasicNameValuePair("sex", sid));
                params.add(new BasicNameValuePair("phone", hid));
                params.add(new BasicNameValuePair("problem", rid));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                		ADD_APPOINTMENT_URL, "POST", params);
 
                // full json response
                Log.d("Admission attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Appointment Created!", json.toString());              	
                	finish();
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Appointment Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);
                	
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}
		/**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(AddAppointmentActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
            finish();
 
        }
		
	}
	
	
	
	
}
