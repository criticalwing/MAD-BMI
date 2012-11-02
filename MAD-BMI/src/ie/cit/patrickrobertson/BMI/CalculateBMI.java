package ie.cit.patrickrobertson.BMI;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CalculateBMI extends Activity {
	
	TextView title;
	Button submit;
	ToggleButton entryType;
	EditText getHeightText;
	EditText getWeightText;
	MediaPlayer click;
	MediaPlayer slide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_bmi_screen);
        title = (TextView)findViewById(R.id.titleDisplay);
        submit = (Button)findViewById(R.id.bSubmit);
        entryType = (ToggleButton)findViewById(R.id.metricOrImperial);
        getHeightText = (EditText)findViewById(R.id.heightEnter);
        getWeightText = (EditText)findViewById(R.id.weightEnter);
    	click = MediaPlayer.create(CalculateBMI.this, R.raw.click_sound);
    	slide = MediaPlayer.create(CalculateBMI.this, R.raw.slide_sound);
    	
        
        submit.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				click.start();
				getHeightText.setText("done something");
				try {
					Class ourClass = Class.forName("ie.cit.patrickrobertson.Result");
					Intent ourIntent = new Intent(CalculateBMI.this, ourClass);
					startActivity(ourIntent);
				} catch (ClassNotFoundException cNFE) {
					cNFE.printStackTrace();
				}
			}
		});
        
        entryType.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide.start();
				if(entryType.isChecked()){
					getHeightText.setHint("Enter Height - cm");
					getWeightText.setHint("Enter Weight - kg");
				}else{
					getHeightText.setHint("Enter Height - Feet'Inches\"");
					getWeightText.setHint("Enter Weight - stone lb");
				}
				
			}
		});
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// save stuff
		outState.putCharSequence("height", getHeightText.getText().toString());
		outState.putCharSequence("weight", getWeightText.getText().toString());
		outState.putBoolean("entryType", entryType.isChecked());
		
	}
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_screen, menu);
        return true;
    }
}
