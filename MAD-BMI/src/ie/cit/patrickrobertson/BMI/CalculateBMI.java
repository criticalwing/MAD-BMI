package ie.cit.patrickrobertson.BMI;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	double height;
	double weight;

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
				//play sound effect
				click.start();
				//check if teh entry is valid
				if(!validateInput()){
						if(entryType.isChecked()){
							alertButton("Your must enter Height in cm and weight in kgs");
						}else{
							alertButton("Your must enter Height in Feet & inches and weight in lbs");
						}
				//if entry is valid set values and launch result activity
				}else{
					setHeightAndWeight();					
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

	public boolean validateInput(){
		
		if(entryType.isChecked()){
			if(getHeightText.getText()==null||getWeightText.getText()==null){
				return false;
			}
		}else{
			if(getHeightFeet.getText()==null||getImpWeightText.getText()==null||getHeightInches.getText()==null){
				return false;
			}
		}
		return false;
	}
	

	private void setHeightAndWeight() {
		//if metric assign values
		if(entryType.isChecked()){
			height = Integer.valueOf(getHeightText.getText().toString());
			weight = Integer.valueOf(getWeightText.getText().toString());
		//if imperial convert to metric and assign
		}else{
			height = (Integer.valueOf(getHeightFeet.getText().toString())+(Integer.valueOf(
						getHeightInches.getText().toString())/12))*30.48;
			weight = (Integer.valueOf(getImpWeightText.getText().toString())*0.453592);
		}
		
	}
	
	
	public void alertButton(String message){
		
		new AlertDialog.Builder(this)
	    .setTitle("Error")
	    .setMessage(message)
	    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	dialog.dismiss();
	        }
	     })
	     .show();
		
		
	}
	
	@Override
 	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// save stuff
		outState.putDouble("height", height);
		outState.putDouble("weight", weight);
		
	}
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_screen, menu);
        return true;
    }
}
