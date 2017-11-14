package com.admit.hospitalapp;

import static com.admit.hospitalapp.HospitalActivity.SUBADMIN_ID;
import static com.admit.hospitalapp.HospitalActivity.SUBADMIN_NAME;
import static com.admit.hospitalapp.HospitalActivity.SUBADMIN_ROLE_TYPE;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import in.digitechlab.hospitalapp.R;


public class SubAdminActivity extends ActionBarActivity {
	
    String consulting_doctor_id, subad_role;
    
    String consulting_doctor_name;
    
    TextView welcome_name;
    
    public static final String TAG_ROLE_TYPE_ID = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subadmin);
        
        
        Intent intent = getIntent();
        if (null != intent){
        consulting_doctor_id = intent.getStringExtra(SUBADMIN_ID);
        consulting_doctor_name = intent.getStringExtra(SUBADMIN_NAME);
        subad_role = intent.getStringExtra(SUBADMIN_ROLE_TYPE);

        }
        
        welcome_name = (TextView) findViewById(R.id.sa_textViewad11);
        
        welcome_name.setText("WELCOME!\n" + consulting_doctor_name);
        
       
        Button all_patients = (Button)findViewById(R.id.sa_all_patients);
        Button all_staffs = (Button)findViewById(R.id.sa_all_staffs);
        Button new_patient = (Button)findViewById(R.id.sa_new_patient);
        Button new_staff = (Button)findViewById(R.id.sa_new_staff);
        Button emergency_button = (Button)findViewById(R.id.sa_emergency_info);
        Button admission_button = (Button)findViewById(R.id.sa_all_admissions);
        Button paymnets_button = (Button)findViewById(R.id.sa_Buttonad01);
        Button status_button = (Button)findViewById(R.id.stat_Buttonad02);
        Button appointment_button = (Button)findViewById(R.id.sa_Buttonad03);


            all_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent ("com.admit.hospitalapp.ALLPATIENTSACTIVITY");
            	i.putExtra(SUBADMIN_ROLE_TYPE, subad_role);
				startActivity(i);
            }
     
        });
            
            
          all_staffs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.ALLSTAFFSACTIVITY");
                	i.putExtra(SUBADMIN_ROLE_TYPE, subad_role);
    				startActivity(i);
                }
            });
            
            
          new_patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i = new Intent ("com.admit.hospitalapp.ADDPATIENTACTIVITY");
                	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
    				startActivity(i);
                }
            });
          
          
          new_staff.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ADDSTAFFACTIVITY");
            	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
  				startActivity(i);
              }
          });
          
          
          emergency_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.EMERGENCYACTIVITY");
            	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
  				startActivity(i);
              }
          });
          
          admission_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ADMISSIONACTIVITY");
            	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
  				startActivity(i);
              }
          });
          
          appointment_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ALLAPPOINTMENTSACTIVITY");
            	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
  				startActivity(i);
              }
          });
          
          status_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.STAFFSTATUSACTIVITY");
            	i.putExtra(SUBADMIN_ID, consulting_doctor_id);
  				startActivity(i);
              }
          });
          
          paymnets_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              	Intent i = new Intent ("com.admit.hospitalapp.ALLTRANSACTIONSACTIVITY");
            	//i.putExtra(SUBADMIN_ID, consulting_doctor_id);
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
