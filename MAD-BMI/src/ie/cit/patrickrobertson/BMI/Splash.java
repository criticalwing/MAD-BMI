package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);	
				}catch(InterruptedException iE){
					iE.printStackTrace();
				}finally{
					Intent welcome = new Intent("ie.cit.patrickrobertson.WELCOME");
					startActivity(welcome);
				}
			}
		};
		timer.start();
	}

}
