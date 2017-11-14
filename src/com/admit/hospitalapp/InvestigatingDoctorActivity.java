package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.INVESTIGATING_DOCTOR_ID;
import static com.admit.hospitalapp.HospitalActivity.INVESTIGATING_DOCTOR_NAME;
import static com.admit.hospitalapp.HospitalActivity.IDOCTOR_ROLE_TYPE;
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


public class InvestigatingDoctorActivity extends ActionBarActivity {
	
    String investigating_doctor_id, idoctor_role;
    
    String investigating_doctor_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigating_doctor);

        Intent intent = getIntent();
        if (null != intent){
        investigating_doctor_id = intent.getStringExtra(INVESTIGATING_DOCTOR_ID);
        investigating_doctor_name = intent.getStringExtra(INVESTIGATING_DOCTOR_NAME);
        idoctor_role = intent.getStringExtra(IDOCTOR_ROLE_TYPE);
        }
        welcome_name = (TextView) findViewById(R.id.textViewi1);
        
        welcome_name.setText("WELCOME!\n" + investigating_doctor_name);
        
        
        Button idoctor_patients = (Button)findViewById(R.id.idoctor_all_patients);
        Button idoctor_status = (Button)findViewById(R.id.idoctor_status);
        Button idoctor_notice = (Button)findViewById(R.id.Buttoni1);
        
        
        idoctor_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ALLPATIENTSACTIVITY");
            	i.putExtra(INVESTIGATING_DOCTOR_ID, investigating_doctor_id);
            	i.putExtra(IDOCTOR_ROLE_TYPE, idoctor_role);
    			startActivity(i);
            }
        });
        
        idoctor_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.STAFFSTATUSACTIVITY");
            	i.putExtra(INVESTIGATING_DOCTOR_ID, investigating_doctor_id);
    			startActivity(i);
            }
        }); 
        
        
        idoctor_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ADMINSTAFFNOTICEACTIVITY");
            	i.putExtra(INVESTIGATING_DOCTOR_ID, investigating_doctor_id);
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
