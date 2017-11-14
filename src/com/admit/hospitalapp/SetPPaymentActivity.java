package com.admit.hospitalapp;

import in.digitechlab.hospitalapp.R;
import java.util.ArrayList;
import java.util.List;
import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_ID;
import static com.admit.hospitalapp.SPPaymentActivity.PAYMENT_ITEM;
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


public class SetPPaymentActivity extends ActionBarActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String STATUS_URL = "http://www.digitechlab.in/hospitallogin/payment_status.php";
	private static final String SET_AMOUNT_URL = "http://www.digitechlab.in/hospitallogin/set_amount.php";
	private static final String CONFIRM_PAYMENT_URL = "http://www.digitechlab.in/hospitallogin/payment_confirm.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	//private static final String P_STATUS = "patient_status";
	
    String pat_id, pay_type, tx_amount;	
	TextView pay_status;
	EditText ed_amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_set_ppayment);
		
		Intent intent = getIntent();
        if (null != intent){
        pat_id = intent.getStringExtra(TAG_PATIENT_ID);
        pay_type = intent.getStringExtra(PAYMENT_ITEM);
        }
		
		 Button submit_button = (Button) findViewById(R.id.ppayment_submit);
		 Button confirm_button = (Button) findViewById(R.id.btnconf);
		 ed_amount = (EditText)findViewById(R.id.EditViewspp9);
		 
		 submit_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	
		        	 {
		        		 
		        		new SubmitAmount().execute();
		        		
		        	 }
		        }
		        });

		 
		 confirm_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	
		        	 {
		        		 
		        		new ConfirmPayment().execute();
		        		
		        	 }
		        }
		        });
		 
		 
		
		 pay_status = (TextView) findViewById(R.id.textViewspp9);
		//response_status.setText(P_STATUS);


        new FetchStatus().execute();
		
	}
	
	class FetchStatus extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SetPPaymentActivity.this);
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
				params.add(new BasicNameValuePair("userid", pat_id));
				params.add(new BasicNameValuePair("itemid", pay_type));

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
			
			pay_status.setText(s);
			
			pDialog.dismiss();

		}

	}
	
	
	
    class SubmitAmount extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SetPPaymentActivity.this);
			pDialog.setMessage("Creating Invoice...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			tx_amount = ed_amount.getText().toString();

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();

				params.add(new BasicNameValuePair("userid", pat_id));
				params.add(new BasicNameValuePair("itemid", pay_type));
				params.add(new BasicNameValuePair("amount", tx_amount));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(SET_AMOUNT_URL, "POST",
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
            	Toast.makeText(SetPPaymentActivity.this, "Invoice Created!", Toast.LENGTH_LONG).show();
            }

    	    finish();

		}

	}
	
	
	
    class ConfirmPayment extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SetPPaymentActivity.this);
			pDialog.setMessage("Confirming Payment...");
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

				params.add(new BasicNameValuePair("userid", pat_id));
				params.add(new BasicNameValuePair("itemid", pay_type));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(CONFIRM_PAYMENT_URL, "POST",
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
            	Toast.makeText(SetPPaymentActivity.this, "Payment Confirmed!", Toast.LENGTH_LONG).show();
            }

    	    finish();

		}

	}
    
    
	

}
