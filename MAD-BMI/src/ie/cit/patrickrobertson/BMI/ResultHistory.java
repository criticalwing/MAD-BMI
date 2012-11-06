package ie.cit.patrickrobertson.BMI;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class ResultHistory extends Activity {

	ArrayList<Result> results;
	Button showGraph, deleteHistory, home;
	TableLayout resultsTable;
	ResultsManager rm;
	MediaPlayer click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupLayout();
		rm = new ResultsManager(getApplicationContext());
		results = rm.getResults();
		generateTable();
		setButtonClicks();
	}

	private void setButtonClicks() {

		deleteHistory.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				deleteAlertButton();


			}
		});

		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent home = new Intent("ie.cit.patrickrobertson.WELCOME");
				startActivity(home);

			}
		});
		
		showGraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent graph = new Intent("ie.cit.patrickrobertson.RESULTSGRAPHSCREEN");
				startActivity(graph);

			}
		});
	}

	private void generateTable() {
		for (Result x : results) {
			/* Find Tablelayout defined in main.xml */
			TableLayout tl = (TableLayout) findViewById(R.id.result_history_table);
			/* Create a new row to be added. */
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 110));
			// Textview for date
			TextView date = new TextView(this);
			date.setText(String.valueOf(x.getDate()));
			date.setTextSize(25);
			date.setTextColor(android.graphics.Color.WHITE);
			date.setPadding(25, 10, 10, 10);
			date.setHeight(90);
			date.setGravity(Gravity.CENTER_VERTICAL);
			// Textview for BMI
			TextView bmi = new TextView(this);
			bmi.setText(String.valueOf(x.getBMI()));
			bmi.setTextSize(35);
			bmi.setTextColor(android.graphics.Color.WHITE);
			bmi.setPadding(35, 10, 10, 10);
			bmi.setHeight(90);
			bmi.setGravity(Gravity.CENTER_VERTICAL);
			/* Add Textviews to row. */
			tr.addView(date);
			tr.addView(bmi);
			// set background of row
			tr.setBackgroundResource(getDrawableImage(x.getBackColour()));
			/* Add row to TableLayout. */
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		}

	}

	private void setupLayout() {
		setContentView(R.layout.result_history);
		resultsTable = (TableLayout) findViewById(R.id.result_history_table);
		showGraph = (Button) findViewById(R.id.bGraph);
		deleteHistory = (Button) findViewById(R.id.bDelHistory);
		home = (Button) findViewById(R.id.bReturn);
		click = MediaPlayer.create(ResultHistory.this, R.raw.slide_sound);
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


	private void deleteAlertButton() {

		new AlertDialog.Builder(this)
				.setTitle("Delete History")
				.setMessage("Are you sure, no going back?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								rm.deleteHistory();
								Intent history = new Intent("ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
								startActivity(history);
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				}).show();

	}

}
