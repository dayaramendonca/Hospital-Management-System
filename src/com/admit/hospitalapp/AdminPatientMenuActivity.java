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


public class AdminPatientMenuActivity extends ActionBarActivity {
	
    String patient_id, patient_name;
    String role_type;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patient_menu);

        Intent intent = getIntent();
        if (null != intent){
		patient_id = intent.getStringExtra(TAG_PATIENT_ID);
		patient_name = intent.getStringExtra(TAG_PATIENT_NAME);
        }
        
        
        Button patient_prescription = (Button)findViewById(R.id.pres_adpm);
        Button patient_investigation = (Button)findViewById(R.id.inv_adpm);
        Button patient_payment = (Button)findViewById(R.id.pay_adpm);
        Button patient_status = (Button)findViewById(R.id.stat_adpm);
        Button patient_personal = (Button)findViewById(R.id.ButtonPtPi);
        
        t1 = (TextView)findViewById(R.id.textViewcm1);

        t1.setText("Patient Details : "+patient_name);
        
        patient_prescription.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent i = new Intent ("com.admit.hospitalapp.ADMPRESCRIPTIONACTIVITY");
        	i.putExtra(TAG_PATIENT_ID, patient_id);
			startActivity(i);
        }
    });
        
        patient_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.PATIENTSTATUSUPDATEACTIVITY");
            	i.putExtra(TAG_PATIENT_ID, patient_id);
    			startActivity(i);
            }
        });
        
        patient_investigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ADMINVESTIGATIONACTIVITY");
            	i.putExtra(TAG_PATIENT_ID, patient_id);
    			startActivity(i);
            }
        }); 
        
        
        patient_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.SPPAYMENTACTIVITY");
            	i.putExtra(TAG_PATIENT_ID, patient_id);
    			startActivity(i);
            }
        });
        
        
        patient_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.PATIENTINFOACTIVITY");
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
