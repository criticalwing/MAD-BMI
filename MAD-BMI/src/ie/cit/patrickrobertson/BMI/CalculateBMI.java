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
	Button submit, home;
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
		setup();
		buttonSetup();
	}

	private void buttonSetup() {
		submit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// play sound effect
				click.start();
				// check if the entry is valid
				if (!validateInput()) {
					if (entryType.isChecked()) {
						alertButton("Your must enter Height in cm and weight in kgs");
					} else {
						alertButton("Your must enter Height in Feet & inches and weight in lbs");
					}
					// if entry is valid set values and launch result activity
				} else {
					// set metric height and weight values
					setHeightAndWeight();
					goToResults();
				}
			}

			private void goToResults() {
				Bundle output = new Bundle();
				output.putDouble("height", height);
				output.putDouble("weight", weight);
				Intent result = new Intent(CalculateBMI.this,
						ResultScreen.class);
				result.putExtras(output);
				startActivity(result);
			}

		});

		entryType.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				slide.start();
				if (entryType.isChecked()) {
					imperialEnter.setVisibility(View.GONE);
					metricEnter.setVisibility(View.VISIBLE);
				} else {
					imperialEnter.setVisibility(View.VISIBLE);
					metricEnter.setVisibility(View.GONE);
				}

			}
		});

		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent home = new Intent("ie.cit.patrickrobertson.WELCOME");
				startActivity(home);

			}
		});

	}

	public boolean validateInput() {

		if (entryType.isChecked()) {
			if (getHeightText.getText().length() > 0
					&& getWeightText.getText().length() > 0) {
				return true;
			}
		} else {
			if (getHeightFeet.getText().length() > 0
					&& getHeightInches.getText().length() > 0
					&& getImpWeightText.getText().length() > 0) {
				return true;
			}
		}
		return false;

	}

	private void setHeightAndWeight() {
		// if metric assign values
		if (entryType.isChecked()) {
			height = Double.valueOf(getHeightText.getText().toString());
			weight = Double.valueOf(getWeightText.getText().toString());
			// if imperial convert to metric and assign
		} else {
			height = (Double.valueOf(getHeightFeet.getText().toString()) + (Integer
					.valueOf(getHeightInches.getText().toString()) / 12)) * 30.48;
			weight = (Double.valueOf(getImpWeightText.getText().toString()) * 0.453592);
		}

	}

	public void alertButton(String message) {

		new AlertDialog.Builder(this).setTitle("Error").setMessage(message)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}

	private void setup() {
		submit = (Button) findViewById(R.id.bSubmit);
		home = (Button) findViewById(R.id.bHome);
		entryType = (ToggleButton) findViewById(R.id.metricOrImperial);
		getHeightText = (EditText) findViewById(R.id.heightEnter);
		getWeightText = (EditText) findViewById(R.id.weightEnter);
		getHeightFeet = (EditText) findViewById(R.id.heightFeetEnter);
		getImpWeightText = (EditText) findViewById(R.id.weightImpEnter);
		getHeightInches = (EditText) findViewById(R.id.heightInchesEnter);
		imperialEnter = (LinearLayout) findViewById(R.id.imperialEnter);
		metricEnter = (LinearLayout) findViewById(R.id.metricEnter);
		click = MediaPlayer.create(CalculateBMI.this, R.raw.click_sound);
		slide = MediaPlayer.create(CalculateBMI.this, R.raw.slide_sound);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start_screen, menu);
		return true;
	}
}
