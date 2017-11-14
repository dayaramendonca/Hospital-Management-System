package com.admit.hospitalapp;

import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_ID;
import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_ID;
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


public class AddPrescriptionActivity extends ActionBarActivity  implements OnClickListener{


	private EditText et1, et2, et3, et4, et5, et6, et7, et8;
	private Button  submit;
	
	String ptid, docid="5";
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String ADD_PRESCRIPTION_URL = "http://www.digitechlab.in/hospitallogin/add_prescription.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_prescription);
		
		
        Intent intent = getIntent();
        if (null != intent){
        	ptid = intent.getStringExtra(TAG_PATIENT_ID);
        	docid = intent.getStringExtra(CONSULTING_DOCTOR_ID);
        	
        }
		
		
		et1 = (EditText)findViewById(R.id.pcdEditText07);
		et2 = (EditText)findViewById(R.id.pt_ph_field);
		et3 = (EditText)findViewById(R.id.pmpEditText01);
		et4 = (EditText)findViewById(R.id.pdaEditText02);
		et5 = (EditText)findViewById(R.id.pgcEditText04);
		et6 = (EditText)findViewById(R.id.pinv1EditText05);
		et7 = (EditText)findViewById(R.id.pinv2EditText06);
		et8 = (EditText)findViewById(R.id.pinv3EditText03);

		submit = (Button)findViewById(R.id.prct_sbmt_button);
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
            pDialog = new ProgressDialog(AddPrescriptionActivity.this);
            pDialog.setMessage("Creating Prescription...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String cmdl = et1.getText().toString();
            String pth = et2.getText().toString();
            String pres = et3.getText().toString();
            String docad = et4.getText().toString();
            String gc = et5.getText().toString();
            String inv1 = et6.getText().toString();
            String inv2 = et7.getText().toString();
            String inv3 = et8.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ptid", ptid));
                params.add(new BasicNameValuePair("docid", docid));
                params.add(new BasicNameValuePair("cmdl", cmdl));
                params.add(new BasicNameValuePair("pth", pth));
                params.add(new BasicNameValuePair("pres", pres));
                params.add(new BasicNameValuePair("docad", docad));
                params.add(new BasicNameValuePair("gc", gc));
                params.add(new BasicNameValuePair("inv1", inv1));
                params.add(new BasicNameValuePair("inv2", inv2));
                params.add(new BasicNameValuePair("inv3", inv3));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                		ADD_PRESCRIPTION_URL, "POST", params);
 
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
            	Toast.makeText(AddPrescriptionActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
            finish();
 
        }
		
	}
	
	
	
	
}
