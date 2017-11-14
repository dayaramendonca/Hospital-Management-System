package com.admit.hospitalapp;

import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_ID;
import static com.admit.hospitalapp.AllPatientsActivity.TAG_PATIENT_NAME;
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


public class PharmPatientMenuActivity extends ActionBarActivity {
	
    String patient_id;
    String patient_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrm_patient_menu);

        Intent intent = getIntent();
        if (null != intent){
		patient_id = intent.getStringExtra(TAG_PATIENT_ID);
        patient_name = intent.getStringExtra(TAG_PATIENT_NAME);
        }
        
        welcome_name = (TextView) findViewById(R.id.textViewc1);
        
        welcome_name.setText("Details Of : " + patient_name);
        
        //Toast.makeText(this, "CONGRATULATIONS!" + patient_id, Toast.LENGTH_LONG).show();
        
        
//        int patient_id  = 0;
//
//        try {
//            patient_id = Integer.parseInt(patient_id_string);
//        } catch(NumberFormatException nfe) {
//           System.out.println("Could not parse " + nfe);
//        } 
        
        
        Button pres = (Button)findViewById(R.id.ph_all_patients);
        Button grep = (Button)findViewById(R.id.ph_report);
        Button ptst = (Button)findViewById(R.id.ph_pstatus);


        pres.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent i = new Intent ("com.admit.hospitalapp.ADMPRESCRIPTIONACTIVITY");
        	i.putExtra(TAG_PATIENT_ID, patient_id);
			startActivity(i);
        }
    });
        
        grep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ADDPHARMREPORTACTIVITY");
            	i.putExtra(TAG_PATIENT_ID, patient_id);
    			startActivity(i);
            }
        });
        
        ptst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.PATIENTSTATUSUPDATEACTIVITY");
            	i.putExtra(TAG_PATIENT_ID, patient_id);
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
}
