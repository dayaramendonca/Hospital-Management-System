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


public class AddStaffActivity extends ActionBarActivity  implements OnClickListener{


	private EditText un, rt, nm, sx, add, ph, sp, ag, pw;
	private Button  submit;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String ADD_STAFF_URL = "http://www.digitechlab.in/hospitallogin/add_staff.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_staff);
		
		un = (EditText)findViewById(R.id.stf_username_field);
		rt = (EditText)findViewById(R.id.sEditText06);
		nm = (EditText)findViewById(R.id.sEditText07);
		sx = (EditText)findViewById(R.id.sEditText02);
		add = (EditText)findViewById(R.id.sEditText03);
		ph = (EditText)findViewById(R.id.sEditText04);
		sp = (EditText)findViewById(R.id.sEditText08);
		ag = (EditText)findViewById(R.id.sEditText05);
		pw = (EditText)findViewById(R.id.sEditText01);

		submit = (Button)findViewById(R.id.stf_register_button);
		submit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
				new AdmissionPatient().execute();
		
	}
	
	class AdmissionPatient extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddStaffActivity.this);
            pDialog.setMessage("Creating Staff...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String sun = un.getText().toString();
            String srt = rt.getText().toString();
            String snm = nm.getText().toString();
            String ssx = sx.getText().toString();
            String sadd = add.getText().toString();
            String sph = ph.getText().toString();
            String ssp = sp.getText().toString();
            String sag = ag.getText().toString();
            String spw = pw.getText().toString();
            
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("un", sun));
                params.add(new BasicNameValuePair("rt", srt));
                params.add(new BasicNameValuePair("nm", snm));
                params.add(new BasicNameValuePair("sx", ssx));
                params.add(new BasicNameValuePair("add", sadd));
                params.add(new BasicNameValuePair("ph", sph));
                params.add(new BasicNameValuePair("sp", ssp));
                params.add(new BasicNameValuePair("ag", sag));
                params.add(new BasicNameValuePair("pw", spw));
                
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                	ADD_STAFF_URL, "POST", params);
 
                // full json response
                Log.d("Admission attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Admission Created!", json.toString());              	
                	finish();
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Admission Failure!", json.getString(TAG_MESSAGE));
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
            	Toast.makeText(AddStaffActivity.this, "New Staff Registered!", Toast.LENGTH_LONG).show();
            }
            finish();
 
        }
		
	}
	
	
	
	
}
