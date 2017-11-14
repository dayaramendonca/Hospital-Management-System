package com.admit.hospitalapp;

import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_ID;
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


public class AddInvestigationActivity extends ActionBarActivity  implements OnClickListener{


	private EditText et_cdoc, et_idoc, et_irep;
	private Button  submit;
	
	String ptid;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String ADD_INVESTIGATION_URL = "http://www.digitechlab.in/hospitallogin/add_investigation.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_investigation_report);
		
		
		  Intent intent = getIntent();
	        if (null != intent){
	        	ptid = intent.getStringExtra(TAG_PATIENT_ID);
	        }
		
		
		et_cdoc = (EditText)findViewById(R.id.invest02);
		et_idoc = (EditText)findViewById(R.id.invest03);
		et_irep = (EditText)findViewById(R.id.invest04);

		submit = (Button)findViewById(R.id.invest_now_button);
		submit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
				new DischargePatient().execute();
		
	}
	
	class DischargePatient extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddInvestigationActivity.this);
            pDialog.setMessage("Generating Investigation Report..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String cdoc = et_cdoc.getText().toString();
            String idoc = et_idoc.getText().toString();
            String irep = et_irep.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ptid", ptid));
                params.add(new BasicNameValuePair("cdoc", cdoc));
                params.add(new BasicNameValuePair("idoc", idoc));
                params.add(new BasicNameValuePair("irep", irep));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                		ADD_INVESTIGATION_URL, "POST", params);
 
                // full json response
                Log.d("Discharge attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Discharge Created!", json.toString());              	
                	finish();
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Discharge Failure!", json.getString(TAG_MESSAGE));
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
            	Toast.makeText(AddInvestigationActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
            finish();
 
        }
		
	}
	
	
	
	
}
