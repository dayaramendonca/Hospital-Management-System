package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.PHARM_ROLE_TYPE;
import static com.admit.hospitalapp.HospitalActivity.PHARMACIST_ID;
import static com.admit.hospitalapp.HospitalActivity.PHARMACIST_NAME;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import in.digitechlab.hospitalapp.R;


public class PharmacistActivity extends ActionBarActivity {
	
    String pharmacist_id, pharm_role;
    
    String pharmacist_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist);

        Intent intent = getIntent();
        if (null != intent){
        pharmacist_id = intent.getStringExtra(PHARMACIST_ID);
        pharmacist_name = intent.getStringExtra(PHARMACIST_NAME);
        pharm_role = intent.getStringExtra(PHARM_ROLE_TYPE);
        }
        
        welcome_name = (TextView) findViewById(R.id.textViewp1);
        
        welcome_name.setText("WELCOME!\n" + pharmacist_name);
        
        
        
        Button pharmacist_patients = (Button)findViewById(R.id.pharmacist_all_patients);
        Button pharmacist_status = (Button)findViewById(R.id.pharmacist_status);
        Button pharmacist_notice = (Button)findViewById(R.id.Buttonp1);
        
        
        pharmacist_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ALLPATIENTSACTIVITY");
            	i.putExtra(PHARMACIST_ID, pharmacist_id);
            	i.putExtra(PHARM_ROLE_TYPE, pharm_role);
    			startActivity(i);
            }
        });
        
        pharmacist_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.STAFFSTATUSACTIVITY");
            	i.putExtra(PHARMACIST_ID, pharmacist_id);
    			startActivity(i);
            }
        }); 
        
        
        pharmacist_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ADMINSTAFFNOTICEACTIVITY");
            	i.putExtra(PHARMACIST_ID, pharmacist_id);
    			startActivity(i);
            }
        });
        
        
        
        
        
        //Toast.makeText(this, "CONGRATULATIONS!" + patient_id, Toast.LENGTH_LONG).show();
        
        
//        int patient_id  = 0;
//
//        try {
//            patient_id = Integer.parseInt(patient_id_string);
//        } catch(NumberFormatException nfe) {
//           System.out.println("Could not parse " + nfe);
//        } 
        
       
        
        
        
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
