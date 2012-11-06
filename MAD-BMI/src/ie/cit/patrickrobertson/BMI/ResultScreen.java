package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ResultScreen extends Activity {

	Result result;
	String backColour;
	TextView resultDate;
	TextView resultFigure;
	Button saveResult;
	TableLayout resultTable;
	ResultsManager rm;
	MediaPlayer click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Retrieve the saved state
		super.onCreate(savedInstanceState);
		// assign the layout elements
		setupLayout();
		setResultDisplay();

	}

	private void setSaveButton() {

		saveResult.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//play sound
				click.start();
				//append current result to text file;
				rm = new ResultsManager(getApplicationContext(),result.toString());
				Intent history = new Intent("ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
				startActivity(history);
			}
		});
		
	}

	private void setResultDisplay() {
		Bundle bundle = getIntent().getExtras();
		result = new Result(bundle.getDouble("height"),
				bundle.getDouble("weight"));
		resultDate.setText(result.getDate());
		resultFigure.setText(String.valueOf(result.getBMI()));
		resultTable.setBackgroundResource(getDrawableImage(result
				.getBackColour()));

	}

	private int getDrawableImage(int input) {
		switch (input) {

		case 0:
			return R.drawable.green_result;
		case 1:
			return R.drawable.green_result;
		case 2:
			return R.drawable.orange_result;
		case 3:
			return R.drawable.red_result;
		case 4:
			return R.drawable.purple_result;
		default:
			return R.drawable.green_result;

		}

	}

	private void setupLayout() {

		setContentView(R.layout.result_screen);
		resultDate = (TextView) findViewById(R.id.resultDate);
		resultFigure = (TextView) findViewById(R.id.resultFigure);
		saveResult = (Button) findViewById(R.id.bSave);
		resultTable = (TableLayout) findViewById(R.id.result_table);
		click = MediaPlayer.create(ResultScreen.this, R.raw.click_sound);
		setSaveButton();
	}

	public void alertButton(String message) {

		new AlertDialog.Builder(this).setTitle("Error").setMessage(message)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();

	}

}
