package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.ADMIN_ID;
import static com.admit.hospitalapp.HospitalActivity.ADMIN_NAME;
import static com.admit.hospitalapp.HospitalActivity.ADMIN_ROLE_TYPE;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import in.digitechlab.hospitalapp.R;


public class AdminActivity extends ActionBarActivity {
	
	
    String consulting_doctor_id, admin_role;
    
    String consulting_doctor_name;
    
    TextView welcome_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        
        
        Intent intent = getIntent();
        if (null != intent){
        consulting_doctor_id = intent.getStringExtra(ADMIN_ID);
        consulting_doctor_name = intent.getStringExtra(ADMIN_NAME);
        admin_role = intent.getStringExtra(ADMIN_ROLE_TYPE);
        }
        
        welcome_name = (TextView) findViewById(R.id.textViewad11ad);
        
        welcome_name.setText("WELCOME!\n" + consulting_doctor_name);
        
        
        Button all_patients = (Button)findViewById(R.id.all_patients);
        Button all_staffs = (Button)findViewById(R.id.all_staffs);
        Button new_patient = (Button)findViewById(R.id.new_patient);
        Button new_staff = (Button)findViewById(R.id.new_staff);
        Button emergency_button = (Button)findViewById(R.id.emergency_info);
        Button admission_button = (Button)findViewById(R.id.all_admissions);
        Button paymnets_button = (Button)findViewById(R.id.Buttonad01);
        Button notice_button = (Button)findViewById(R.id.Buttonad02);
        Button appointment_button = (Button)findViewById(R.id.Buttonad03);


            all_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ALLPATIENTSACTIVITY");
            	i.putExtra(ADMIN_ROLE_TYPE, admin_role);
				startActivity(i);
            }
     
        });
            
            
            all_staffs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.ALLSTAFFSACTIVITY");
                	i.putExtra(ADMIN_ROLE_TYPE, admin_role);
    				startActivity(i);
                }
            });
            
            
          new_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.ADDPATIENTACTIVITY");
    				startActivity(i);
                }
            });
          
          
          new_staff.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ADDSTAFFACTIVITY");
  				startActivity(i);
              }
          });
          
          
          emergency_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.EMERGENCYACTIVITY");
  				startActivity(i);
              }
          });
          
          admission_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ADMISSIONACTIVITY");
  				startActivity(i);
              }
          });
          
          appointment_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ALLAPPOINTMENTSACTIVITY");
  				startActivity(i);
              }
          });
          
          notice_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ADDNOTICEACTIVITY");
  				startActivity(i);
              }
          });
          
          paymnets_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ALLTRANSACTIONSACTIVITY");
  				startActivity(i);
              }
          });
            
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
 
}
