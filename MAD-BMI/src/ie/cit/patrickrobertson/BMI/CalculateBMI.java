package ie.cit.patrickrobertson.BMI;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CalculateBMI extends Activity {
	
	TextView title;
	Button submit;
	ToggleButton entryType;
	EditText getHeightText;
	EditText getWeightText;
	EditText getHeightFeet;
	EditText getHeightInches;
	EditText getImpWeightText;
	LinearLayout imperialEnter;
	LinearLayout metricEnter;
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
    	getHeightFeet = (EditText)findViewById(R.id.heightFeetEnter);
        getImpWeightText = (EditText)findViewById(R.id.weightEnter);
    	getHeightInches = (EditText)findViewById(R.id.heightInchesEnter);
    	imperialEnter = (LinearLayout)findViewById(R.id.imperialEnter);
    	metricEnter = (LinearLayout)findViewById(R.id.metricEnter);
    	click = MediaPlayer.create(CalculateBMI.this, R.raw.click_sound);
    	slide = MediaPlayer.create(CalculateBMI.this, R.raw.slide_sound);
    	
        
        submit.setOnClickListener(new View.OnClickListener() {
        	
			
			public void onClick(View v) {
				click.start();
				if(getHeightText.getText()==null||getHeightText.getText()==null){
					if(entryType.isChecked()){
						getHeightText.setHint("You must enter a value in cm");
						getWeightText.setHint("You must enter a value in kg");
					}else{
						getHeightText.setHint("Must be Feet'Inches\"");
						getWeightText.setHint("Enter Weight - stone lb");
					}
					
				}else{
					try {
						Class ourClass = Class.forName("ie.cit.patrickrobertson.Result");
						Intent ourIntent = new Intent(CalculateBMI.this, ourClass);
						startActivity(ourIntent);
					} catch (ClassNotFoundException cNFE) {
						cNFE.printStackTrace();
					}
				}
			}
		});
        
        entryType.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				slide.start();
				if(entryType.isChecked()){
					imperialEnter.setVisibility(View.GONE);
					metricEnter.setVisibility(View.VISIBLE);
				}else{
					imperialEnter.setVisibility(View.VISIBLE);
					metricEnter.setVisibility(View.GONE);
				}
				
			}
		});
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public boolean validateInput(String input){

		
		return true;
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
