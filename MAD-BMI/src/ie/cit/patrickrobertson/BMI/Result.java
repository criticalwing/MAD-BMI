package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity{
	
	TextView resultDate;
	TextView result;
	String backColour;
	Person person;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		person = new Person (savedInstanceState.getInt("height"),savedInstanceState.getInt("weight"));
		
		
	}

	
	
	
	
	
	
}
