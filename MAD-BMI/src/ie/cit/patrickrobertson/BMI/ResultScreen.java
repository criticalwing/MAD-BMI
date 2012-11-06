package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ResultScreen extends Activity {

	Result result;
	String backColour;
	TextView resultDate;
	TextView resultFigure;
	Button saveResult, home;
	TableLayout resultTable;
	ResultsManager rm;
	MediaPlayer click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Retrieve the saved state
		super.onCreate(savedInstanceState);
		// assign the layout elements
		setupLayout();
		if (savedInstanceState != null) {
			result = (Result) savedInstanceState.getSerializable("result");
		} else {
			Bundle bundle = getIntent().getExtras();
			result = new Result(bundle.getDouble("height"),
					bundle.getDouble("weight"));
		}
		setResultDisplay();
	}

	private void setSaveButton() {

		saveResult.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// play sound
				click.start();
				// append current result to text file;
				rm = new ResultsManager(getApplicationContext(), result
						.toString());
				Intent history = new Intent(
						"ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
				startActivity(history);
			}
		});

		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent home = new Intent("ie.cit.patrickrobertson.WELCOME");
				startActivity(home);

			}
		});
	}

	private void setResultDisplay() {

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

		setContentBasedOnLayout();
		resultDate = (TextView) findViewById(R.id.resultDate);
		resultFigure = (TextView) findViewById(R.id.resultFigure);
		saveResult = (Button) findViewById(R.id.bSave);
		home = (Button) findViewById(R.id.bResultHome);
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

	private void setContentBasedOnLayout() {
		WindowManager winMan = (WindowManager) getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);

		if (winMan != null) {
			int orientation = winMan.getDefaultDisplay().getOrientation();

			if (orientation == 0) {
				// Portrait
				setContentView(R.layout.result_screen);
			} else if (orientation == 1) {
				// Landscape
				setContentView(R.layout.land_result_screen);
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("result", result);
		outState.putDouble("height", result.getHeight());
		outState.putDouble("weight", result.getWeight());
	}

}
