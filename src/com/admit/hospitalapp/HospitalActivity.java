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
import android.content.ComponentName;
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
import android.widget.Toast;


public class HospitalActivity extends ActionBarActivity {
	
	
	private EditText pass, user;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String LOGIN_URL = "http://www.digitechlab.in/hospitallogin/login.php";
	
	private static final String TAG_SUCCESS = "success";
	
	private static final String TAG_MESSAGE = "message";
	
	public static final String ACTIVE_USER_ID="user_id";
	
	public static final String ROLE_TYPE_ID="role_type_id";
	
	public static final String USER_FULL_NAME="full_name";
	
	public static final String PATIENT_IDT = "0";
	
	public static final String CONSULTING_DOCTOR_ID = "0";
	
	public static final String INVESTIGATING_DOCTOR_ID = "0";
	
	public static final String PHARMACIST_ID = "0";
	
	public static final String CONSULTING_DOCTOR_NAME = "mnp";
	
	public static final String INVESTIGATING_DOCTOR_NAME = "pqr";
	
	public static final String PHARMACIST_NAME = "abc";
	
	public static final String PATIENT_NAME = "pvc";
	
	public static final String CDOCTOR_ROLE_TYPE = "qtz";
	
	public static final String IDOCTOR_ROLE_TYPE = "ytq";
	
	public static final String PHARM_ROLE_TYPE = "yyu";
	
	public static final String ADMIN_ID = "0";
	
	public static final String ADMIN_NAME = "aii";
	
	public static final String ADMIN_ROLE_TYPE = "qii";
	
	public static final String SUBADMIN_ID = "0";
	
	public static final String SUBADMIN_NAME = "bii";
	
	public static final String SUBADMIN_ROLE_TYPE = "rii";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        
        pass = (EditText) findViewById(R.id.password_field);
        		
        user = (EditText) findViewById(R.id.username_field);
        
        Button submit_login = (Button) findViewById(R.id.login);
        
        submit_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	new AttemptLogin().execute();
        }
        });
        

    	Button patient_panel = (Button)findViewById(R.id.patient_request);

        patient_panel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent i = new Intent ("com.admit.hospitalapp.ADDAPPOINTMENTACTIVITY");
			startActivity(i);
        }
    });
        
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	class AttemptLogin extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(HospitalActivity.this);
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String username = user.getText().toString();
			String password = pass.getText().toString();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						params);

				// check your log for json response
				Log.d("Login attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				String user_id = json.getString(ACTIVE_USER_ID);
				int role_type_id = json.getInt(ROLE_TYPE_ID);
				String user_full_name = json.getString(USER_FULL_NAME);
				
				if (success == 1) {
					Log.d("Login Successful!", json.toString());
					// save user data
					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(HospitalActivity.this);
					Editor edit = sp.edit();
					edit.putString("username", username);
					edit.commit();
					
					if(role_type_id == 6) {
					
					Bundle bund = new Bundle();

					bund.putString(PATIENT_IDT, user_id);
					bund.putString(PATIENT_NAME, user_full_name);

					Intent intent = new Intent();

					intent.putExtras(bund);

					intent.setComponent(new ComponentName
					(HospitalActivity.this, PatientActivity.class));	

					startActivity(intent);

					} else if (role_type_id == 3) {
							
					Bundle bund = new Bundle();

					bund.putString(CONSULTING_DOCTOR_ID, user_id);
					bund.putString(CONSULTING_DOCTOR_NAME, user_full_name);
					bund.putString(CDOCTOR_ROLE_TYPE, "3");


					Intent intent = new Intent();

					intent.putExtras(bund);

					intent.setComponent(new ComponentName
					(HospitalActivity.this, ConsultingDoctorActivity.class));	

					startActivity(intent);
						
					} else if (role_type_id == 4) {
						
					Bundle bund = new Bundle();

					bund.putString(INVESTIGATING_DOCTOR_ID, user_id);
					bund.putString(INVESTIGATING_DOCTOR_NAME, user_full_name);
					bund.putString(IDOCTOR_ROLE_TYPE, "4");


					Intent intent = new Intent();

					intent.putExtras(bund);

					intent.setComponent(new ComponentName
					(HospitalActivity.this, InvestigatingDoctorActivity.class));	

					startActivity(intent);
						
					} else if (role_type_id == 5) {
						
					Bundle bund = new Bundle();

					bund.putString(PHARMACIST_ID, user_id);
					bund.putString(PHARMACIST_NAME, user_full_name);
					bund.putString(PHARM_ROLE_TYPE, "5");


					Intent intent = new Intent();

					intent.putExtras(bund);

					intent.setComponent(new ComponentName
					(HospitalActivity.this, PharmacistActivity.class));	

					startActivity(intent);
						
					} else if (role_type_id == 1) {
						
						Bundle bund = new Bundle();

						bund.putString(ADMIN_ID, user_id);
						bund.putString(ADMIN_NAME, user_full_name);
						bund.putString(ADMIN_ROLE_TYPE, "1");


						Intent intent = new Intent();

						intent.putExtras(bund);

						intent.setComponent(new ComponentName
						(HospitalActivity.this, AdminActivity.class));	

						startActivity(intent);
							
						} else if (role_type_id == 2) {
						
						Bundle bund = new Bundle();

						bund.putString(SUBADMIN_ID, user_id);
						bund.putString(SUBADMIN_NAME, user_full_name);
						bund.putString(SUBADMIN_ROLE_TYPE, "2");


						Intent intent = new Intent();

						intent.putExtras(bund);

						intent.setComponent(new ComponentName
						(HospitalActivity.this, SubAdminActivity.class));	

						startActivity(intent);
							
						}
					
					return json.getString(TAG_MESSAGE);
					
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
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
			if (file_url != null) {
				Toast.makeText(HospitalActivity.this, file_url, Toast.LENGTH_LONG).show();
			}

		}

	}
}
