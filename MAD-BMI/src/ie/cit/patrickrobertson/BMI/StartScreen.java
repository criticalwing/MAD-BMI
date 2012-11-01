package ie.cit.patrickrobertson.BMI;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends Activity {
	
	int counter;
	Button add;
	Button sub;
	TextView display;
	MediaPlayer click;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        counter = 0;
        add = (Button)findViewById(R.id.bAdd);
        sub = (Button) findViewById(R.id.bSub);
        display = (TextView)findViewById(R.id.tvDisplay);
    	click = MediaPlayer.create(StartScreen.this, R.raw.click_sound);
    	
        
        add.setOnClickListener(new View.OnClickListener() {

        	
			@Override
			public void onClick(View v) {
				click.start();
				counter++;
				display.setText("Your Total is " + counter);
			}
		});
       
        sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click.start();
				counter--;
				display.setText("Your Total is " + counter);
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_screen, menu);
        return true;
    }
}
