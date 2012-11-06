package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends Activity{

	Button history, enter;
	MediaPlayer click;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
    	click = MediaPlayer.create(Welcome.this, R.raw.click_sound);
		history = (Button) findViewById(R.id.bShowHistory);
		enter = (Button) findViewById(R.id.bNewEntry);
		
		history.setOnClickListener(new View.OnClickListener() {
			//play sound effect

			public void onClick(View v) {
				click.start();
				Intent history = new Intent("ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
				startActivity(history);
			}
		});
		
		enter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent enter = new Intent("ie.cit.patrickrobertson.CALCULATEBMI");
				startActivity(enter);
			}
		});
	}
	

}
