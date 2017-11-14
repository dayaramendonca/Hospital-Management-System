package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_ID;
import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_NAME;
import static com.admit.hospitalapp.HospitalActivity.CDOCTOR_ROLE_TYPE;
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


public class ConsultingDoctorActivity extends ActionBarActivity {
	
    String consulting_doctor_id, cdoctor_role;
    
    String consulting_doctor_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting_doctor);

        Intent intent = getIntent();
        if (null != intent){
        consulting_doctor_id = intent.getStringExtra(CONSULTING_DOCTOR_ID);
        consulting_doctor_name = intent.getStringExtra(CONSULTING_DOCTOR_NAME);
        cdoctor_role = intent.getStringExtra(CDOCTOR_ROLE_TYPE);
        }
        
        welcome_name = (TextView) findViewById(R.id.textViewc1);
        
        welcome_name.setText("WELCOME!\n" + consulting_doctor_name);
        
        
        Button cdoctor_patients = (Button)findViewById(R.id.cdoctor_all_patients);
        Button cdoctor_status = (Button)findViewById(R.id.cdoctor_status);
        Button cdoctor_notice = (Button)findViewById(R.id.Buttonc1);
        Button cdoctor_admissions = (Button)findViewById(R.id.Buttonad91);
        
        
        cdoctor_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ALLPATIENTSACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
            	i.putExtra(CDOCTOR_ROLE_TYPE, cdoctor_role);
    			startActivity(i);
            }
        });
        
        cdoctor_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.STAFFSTATUSACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
    			startActivity(i);
            }
        }); 
        
        
        cdoctor_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ADMINSTAFFNOTICEACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
    			startActivity(i);
            }
        });
        
        cdoctor_admissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.CONSULTINGDOCTORADMISSIONSACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
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
