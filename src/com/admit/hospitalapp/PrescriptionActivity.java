package com.admit.hospitalapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import in.digitechlab.hospitalapp.R;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import static com.admit.hospitalapp.HospitalActivity.PATIENT_IDT;

public class PrescriptionActivity extends ListActivity {

	private ProgressDialog pDialog;
	
	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String PRESCRIPTION_URL = "http://www.digitechlab.in/hospitallogin/prescription.php";

	private static final String TAG_SUCCESS = "success";
	
	private static final String TAG_MESSAGE = "message";
	
	private static final String TAG_POSTS = "prescriptions";
	private static final String TAG_PRESCRIPTION_ID = "prescription_id";
	private static final String TAG_PATIENT = "patient_id";
	private static final String TAG_DOCTOR = "doctor_id";
	private static final String TAG_COMPLAIN = "complain_details";
	private static final String TAG_HISTORY = "patient_history";
	private static final String TAG_PRESCRIPTION = "main_prescription";
	private static final String TAG_ADVICE = "doctors_advice";
	private static final String TAG_CHECKUP = "general_checkup";
	private static final String TAG_INVESTIGATION1 = "investigation1";
	private static final String TAG_INVESTIGATION2 = "investigation2";
	private static final String TAG_INVESTIGATION3 = "investigation3";
	private static final String TAG_TIME = "date_time";

	private JSONArray mPrescriptions = null;
	
	String patient_id_here;

	private ArrayList<HashMap<String, String>> mPrescriptionList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_prescription);
		
        Intent intent = getIntent();
        if (null != intent)
        patient_id_here = intent.getStringExtra(PATIENT_IDT);
        
        //Toast.makeText(this, "CONGRATULATIONS!" + patient_id_here, Toast.LENGTH_LONG).show();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// loading the comments via AsyncTask
		new LoadPrescription().execute();
	}

	public String updateJSONdata() {
		
		int success;

		mPrescriptionList = new ArrayList<HashMap<String, String>>();

		try {
		
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("patient_id", patient_id_here));

			Log.d("request!", "starting");
			// getting product details by making HTTP request
			JSONObject json = jsonParser.makeHttpRequest(PRESCRIPTION_URL, "POST",
					params);

			success = json.getInt(TAG_SUCCESS);
		
			if (success == 1) { 
			
			mPrescriptions = json.getJSONArray(TAG_POSTS);

			// looping through all posts according to the json object returned
			for (int i = 0; i < mPrescriptions.length(); i++) {
				JSONObject c = mPrescriptions.getJSONObject(i);

				// gets the content of each tag
				String prescription_id = c.getString(TAG_PRESCRIPTION_ID);
				String patient_id = c.getString(TAG_PATIENT);
				String doctor_id = c.getString(TAG_DOCTOR);
				String complain_details = c.getString(TAG_COMPLAIN);
				String patient_history = c.getString(TAG_HISTORY);
				String main_prescription = c.getString(TAG_PRESCRIPTION);
				String doctors_advice = c.getString(TAG_ADVICE);
				String general_checkup = c.getString(TAG_CHECKUP);
				String investigation1 = c.getString(TAG_INVESTIGATION1);
				String investigation2 = c.getString(TAG_INVESTIGATION2);
				String investigation3 = c.getString(TAG_INVESTIGATION3);
				String time_date = c.getString(TAG_TIME);

				HashMap<String, String> map = new HashMap<String, String>();

				map.put(TAG_PRESCRIPTION_ID, prescription_id);
				map.put(TAG_PATIENT, patient_id);
				map.put(TAG_DOCTOR, doctor_id);
				map.put(TAG_COMPLAIN, complain_details);
				map.put(TAG_HISTORY, patient_history);
				map.put(TAG_PRESCRIPTION, main_prescription);
				map.put(TAG_ADVICE, doctors_advice);
				map.put(TAG_CHECKUP, general_checkup);
				map.put(TAG_INVESTIGATION1, investigation1);
				map.put(TAG_INVESTIGATION2, investigation2);
				map.put(TAG_INVESTIGATION3, investigation3);
				map.put(TAG_TIME, time_date);

				mPrescriptionList.add(map);

			}
			
		  } else {
			  
		      //Toast.makeText(PrescriptionActivity.this, "PRESCRIPTION NOT FOUND!", Toast.LENGTH_LONG).show();
			  return json.getString(TAG_MESSAGE);
		      
		      
		  }

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Inserts the parsed data into the listview.
	 */
	private void updateList() {

		ListAdapter adapter = new SimpleAdapter(this, mPrescriptionList,
				R.layout.prescription_post, new String[] { TAG_PRESCRIPTION_ID, TAG_PATIENT, TAG_DOCTOR, TAG_COMPLAIN, TAG_HISTORY, TAG_PRESCRIPTION,
				TAG_ADVICE,	TAG_CHECKUP, TAG_INVESTIGATION1, TAG_INVESTIGATION2, TAG_INVESTIGATION3, TAG_TIME}, new int[] { R.id.textView15, R.id.textView13, R.id.textView14,
				R.id.textView2, R.id.textView4, R.id.textView5, R.id.textView7, R.id.textView8, R.id.textView10, R.id.textView11, R.id.textView12, R.id.textView37 });

		
		setListAdapter(adapter);

		ListView lv = getListView();	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
	}

	public class LoadPrescription extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PrescriptionActivity.this);
			pDialog.setMessage("Loading Prescription...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		

		@Override
		protected String doInBackground(String... args) {

           updateJSONdata();
			
			return null;
		}

		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result); 
			pDialog.dismiss();
			updateList();
		}
	}
}
