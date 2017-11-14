package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_ID;
//import static com.admit.hospitalapp.HospitalActivity.CONSULTING_DOCTOR_NAME;
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


public class ConsultingDoctorAdmissionsActivity extends ActionBarActivity {
	
    String consulting_doctor_id;
    
    //String consulting_doctor_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting_doctor_admissions);

        Intent intent = getIntent();
        if (null != intent){
        consulting_doctor_id = intent.getStringExtra(CONSULTING_DOCTOR_ID);
  //      consulting_doctor_name = intent.getStringExtra(CONSULTING_DOCTOR_NAME);
        }
        

        Button all_cadmissions = (Button)findViewById(R.id.cdoctor_past_adms);
        Button all_padmissions = (Button)findViewById(R.id.cdoctor_all_adms);
        Button my_admissions = (Button)findViewById(R.id.cdoctor_adms);
        
        
        all_cadmissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.CURRENTADMISSIONSACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
    			startActivity(i);
            }
        });
        
        all_padmissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.HISTORYADMISSIONSACTIVITY");
            	i.putExtra(CONSULTING_DOCTOR_ID, consulting_doctor_id);
    			startActivity(i);
            }
        }); 
        
        my_admissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.DOCTORADMISSIONSACTIVITY");
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
