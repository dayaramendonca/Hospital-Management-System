package com.admit.hospitalapp;

import static com.admit.hospitalapp.AllStaffsActivity.TAG_STAFF_ID;
//import static com.admit.hospitalapp.AllStaffsActivity.TAG_ROLE_TYPE;
import static com.admit.hospitalapp.AllStaffsActivity.TAG_STAFF_NAME;
import in.digitechlab.hospitalapp.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.admit.hospitalapp.StaffInfoActivity.FetchStatus;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AdminStaffMenuActivity extends ActionBarActivity {
	
    String staff_id, staff_name;
    String role_type;
    
    TextView t1;
    
	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	
	private static final String STAFF_FIRE_URL = "http://www.digitechlab.in/hospitallogin/staff_fire.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_staff_menu);

        Intent intent = getIntent();
        if (null != intent){
		staff_id = intent.getStringExtra(TAG_STAFF_ID);
		staff_name = intent.getStringExtra(TAG_STAFF_NAME);
 //       role_type = intent.getStringExtra(TAG_ROLE_TYPE);
        }   
        
        Button staff_info = (Button)findViewById(R.id.staff_ai);
        Button staff_fire = (Button)findViewById(R.id.staff_fi);

        t1 = (TextView)findViewById(R.id.textViewsm2);

        t1.setText("Staff Details : "+staff_name);
        
        staff_info.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent i = new Intent ("com.admit.hospitalapp.STAFFINFOACTIVITY");
        	i.putExtra(TAG_STAFF_ID, staff_id);
			startActivity(i);
        }
      });
        
        staff_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	
            	new FireStaff().execute();
            	
            	
         }
      });
        
        
    }
    
    
	class FireStaff extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AdminStaffMenuActivity.this);
			pDialog.setMessage("Firing Staff...");
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
				params.add(new BasicNameValuePair("userid", staff_id));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(STAFF_FIRE_URL, "POST",
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
			
            pDialog.dismiss();
            if (s!= null){
            	Toast.makeText(AdminStaffMenuActivity.this, s, Toast.LENGTH_LONG).show();
            }

		}

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
}
