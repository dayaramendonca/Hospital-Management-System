package com.admit.hospitalapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import in.digitechlab.hospitalapp.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AdmissionActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.activity_admission);
		
        Button current_admissions = (Button)findViewById(R.id.admission_btn1);
        Button new_admission = (Button)findViewById(R.id.admission_btn2);
        Button discharge_patient = (Button)findViewById(R.id.admission_btn3);
        Button history_admissions = (Button)findViewById(R.id.admission_btn4);
		
        current_admissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.CURRENTADMISSIONSACTIVITY");
				startActivity(i);
            }
     
        });
            
            
        new_admission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.NEWADMISSIONACTIVITY");
    				startActivity(i);
                }
            });
            
            
        discharge_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.DISCHARGEACTIVITY");
    				startActivity(i);
                }
            });
          
          
        history_admissions.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.HISTORYADMISSIONSACTIVITY");
  				startActivity(i);
              }
          });
          
      
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
}