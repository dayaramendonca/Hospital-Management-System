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

public class InvestigationActivity extends ListActivity {

	private ProgressDialog pDialog;
	
	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String INVESTIGATION_URL = "http://www.digitechlab.in/hospitallogin/investigation.php";

	private static final String TAG_SUCCESS = "success";
	
	private static final String TAG_MESSAGE = "message";
	
	private static final String TAG_POSTS = "investigations";
	private static final String TAG_INVESTIGATION_ID = "investigation_id";
	private static final String TAG_PATIENT = "patient_id";
	// Consulting Doctors
	private static final String TAG_CONSULTING_DOCTOR = "cdoctor_id";
	// Investigating Doctors
	private static final String TAG_INVESTIGATING_DOCTOR = "idoctor_id";
	// Investigation Report
	private static final String TAG_REPORT = "report";
	
	private static final String TAG_TIME = "date_time";

	private JSONArray mInvestigations = null;
	
	String patient_id_here;

	private ArrayList<HashMap<String, String>> mInvestigationList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_investigation);
		
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
		new LoadInvestigation().execute();
	}

	public String updateJSONdata() {
		
		int success;

		mInvestigationList = new ArrayList<HashMap<String, String>>();

		try {
		
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("patient_id", patient_id_here));

			Log.d("request!", "starting");
			// getting product details by making HTTP request
			JSONObject json = jsonParser.makeHttpRequest(INVESTIGATION_URL, "POST",
					params);

			success = json.getInt(TAG_SUCCESS);
		
			if (success == 1) { 
			
			mInvestigations = json.getJSONArray(TAG_POSTS);

			// looping through all posts according to the json object returned
			for (int i = 0; i < mInvestigations.length(); i++) {
				JSONObject c = mInvestigations.getJSONObject(i);

				// gets the content of each tag
				String investigation_id = c.getString(TAG_INVESTIGATION_ID);
				String patient_id = c.getString(TAG_PATIENT);
				String cdoctor_id = c.getString(TAG_CONSULTING_DOCTOR);
				String idoctor_id = c.getString(TAG_INVESTIGATING_DOCTOR);
				String investigation_report = c.getString(TAG_REPORT);
				String time_date = c.getString(TAG_TIME);


				HashMap<String, String> map = new HashMap<String, String>();

				map.put(TAG_INVESTIGATION_ID, investigation_id);
				map.put(TAG_PATIENT, patient_id);
				map.put(TAG_CONSULTING_DOCTOR, cdoctor_id);
				map.put(TAG_INVESTIGATING_DOCTOR, idoctor_id);
				map.put(TAG_REPORT, investigation_report);
				map.put(TAG_TIME, time_date);

				mInvestigationList.add(map);

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

		ListAdapter adapter = new SimpleAdapter(this, mInvestigationList,
				R.layout.investigation_post, new String[] { TAG_INVESTIGATION_ID, TAG_PATIENT, TAG_CONSULTING_DOCTOR, TAG_INVESTIGATING_DOCTOR, TAG_REPORT,
				TAG_TIME}, new int[] { R.id.textView31, R.id.textView32, R.id.textView33,
				R.id.textView34, R.id.textView35, R.id.textView36});

		
		setListAdapter(adapter);

		ListView lv = getListView();	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
	}

	public class LoadInvestigation extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(InvestigationActivity.this);
			pDialog.setMessage("Loading Investigations...");
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
