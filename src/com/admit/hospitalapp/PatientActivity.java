package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.PATIENT_IDT;
import static com.admit.hospitalapp.HospitalActivity.PATIENT_NAME;
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


public class PatientActivity extends ActionBarActivity {
	
    String patient_id;
    String patient_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Intent intent = getIntent();
        if (null != intent){
		patient_id = intent.getStringExtra(PATIENT_IDT);
        patient_name = intent.getStringExtra(PATIENT_NAME);
        }
        
        welcome_name = (TextView) findViewById(R.id.textViewpt1);
        
        welcome_name.setText("WELCOME! \n" + patient_name + "\n\n Your Token No: " + patient_id);
        
        //Toast.makeText(this, "CONGRATULATIONS!" + patient_id, Toast.LENGTH_LONG).show();
        
        
//        int patient_id  = 0;
//
//        try {
//            patient_id = Integer.parseInt(patient_id_string);
//        } catch(NumberFormatException nfe) {
//           System.out.println("Could not parse " + nfe);
//        } 
        
        
        Button patient_prescription = (Button)findViewById(R.id.patient_prescrip);
        Button patient_status = (Button)findViewById(R.id.patient_stat);
        Button patient_investigation = (Button)findViewById(R.id.patient_invest);
        Button patient_payment = (Button)findViewById(R.id.patient_pay);


        patient_prescription.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent i = new Intent ("com.admit.hospitalapp.PRESCRIPTIONACTIVITY");
        	i.putExtra(PATIENT_IDT, patient_id);
			startActivity(i);
        }
    });
        
        patient_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.PATIENTSTATUSACTIVITY");
            	i.putExtra(PATIENT_IDT, patient_id);
    			startActivity(i);
            }
        });
        
        patient_investigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.INVESTIGATIONACTIVITY");
            	i.putExtra(PATIENT_IDT, patient_id);
    			startActivity(i);
            }
        }); 
        
        
        patient_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.PPAYMENTACTIVITY");
            	i.putExtra(PATIENT_IDT, patient_id);
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
