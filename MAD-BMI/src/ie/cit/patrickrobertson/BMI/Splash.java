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
					Intent openStartPoint = new Intent("ie.cit.patrickrobertson.STARTSCREEN");
					startActivity(openStartPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
