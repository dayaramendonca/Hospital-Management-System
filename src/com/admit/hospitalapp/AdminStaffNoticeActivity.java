package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_ID;
import static com.admit.hospitalapp.HospitalActivity.INVESTIGATING_DOCTOR_ID;
import static com.admit.hospitalapp.HospitalActivity.PHARMACIST_ID;
import static com.admit.hospitalapp.StaffStatusActivity.UPDATED_STATUS;
import in.digitechlab.hospitalapp.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.admit.hospitalapp.HospitalActivity.AttemptLogin;
import com.admit.hospitalapp.StaffStatusActivity.FetchStatus;
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


public class AdminStaffNoticeActivity extends ActionBarActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String STATUS_URL = "http://www.digitechlab.in/hospitallogin/get_admin_notice.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	//private static final String P_STATUS = "patient_status";
	
	String patient_id_here;	
	TextView  notice_status;
	
	String consulting_doctor_id, investigating_doctor_id, pharmacist_id, updated_status;
	//String consulting_doctor_name, investigating_doctor_name, pharmacist_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_admin_notice);
		
        Intent intent = getIntent();
        if (null != intent){
        consulting_doctor_id = intent.getStringExtra(CONSULTING_DOCTOR_ID);
        investigating_doctor_id = intent.getStringExtra(INVESTIGATING_DOCTOR_ID);
        pharmacist_id = intent.getStringExtra(PHARMACIST_ID);
        updated_status = intent.getStringExtra(UPDATED_STATUS);
        }

	
	Button okay_button = (Button) findViewById(R.id.anotice_status);
	
	
	okay_button.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	        	finish();

	        }
	        });
	
	
	
	notice_status = (TextView) findViewById(R.id.textViewan1);


    new FetchStatus().execute();
		
	}
	
	class FetchStatus extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AdminStaffNoticeActivity.this);
			pDialog.setMessage("Fetching Notice...");
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
				
				if(consulting_doctor_id!="0")
				params.add(new BasicNameValuePair("userid", consulting_doctor_id));
				
				else if(investigating_doctor_id!="0")
				params.add(new BasicNameValuePair("userid", investigating_doctor_id));
				
				else if(pharmacist_id!="0")
				params.add(new BasicNameValuePair("userid", pharmacist_id));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(STATUS_URL, "POST",
						params);

				// check your log for json response
				Log.d("Status attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					Log.d("Noice Found!", json.toString());
					// save user data
					 // String response_patient_status = json.getString(P_STATUS);
                    //Toast.makeText(PStatusActivity.this, response_patient_status, Toast.LENGTH_LONG).show();
					//response_status.setText(P_STATUS);
					
					return json.getString(TAG_MESSAGE);
					
				} else {
					Log.d("Notice Not Found!", json.getString(TAG_MESSAGE));
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
			
			notice_status.setText(s);
			
			pDialog.dismiss();

		}

	}
	

}
