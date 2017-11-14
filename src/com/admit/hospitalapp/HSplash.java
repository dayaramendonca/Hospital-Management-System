package com.admit.hospitalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import in.digitechlab.hospitalapp.R;

public class HSplash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hsplash);
		
		//Toast.makeText(getApplicationContext(),"Please Switch On The Robot",
        		// Toast.LENGTH_LONG).show();
		
		Thread t = new Thread(){
			
		public void run(){
		
			try{
				sleep(4000);
			}catch (InterruptedException e){
		}
			finally{
				Intent i = new Intent ("com.admit.hospitalapp.HOSPITALACTIVITY");
				startActivity(i);
			}
	}
};
t.start();

}
	
	   @SuppressWarnings("deprecation")
	   @Override
	      protected void onDestroy() {
	   	   super.onDestroy();
	   	   System.runFinalizersOnExit(true);
	      }
}