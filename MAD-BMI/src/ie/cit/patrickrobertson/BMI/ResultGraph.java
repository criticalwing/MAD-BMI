package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ResultGraph extends Activity {

	Button showList, deleteHistory, home;
	MediaPlayer click;
	ResultsManager rm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rm = new ResultsManager(this);
		layoutSetup();
		buttonSetup();
		createGraph();

	}

	private void buttonSetup() {

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
		
		showList.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent list = new Intent("ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
				startActivity(list);

			}
		});
		
	}

	private void createGraph() {
		int graphsize = 400;
		LinearLayout layout = (LinearLayout) findViewById(R.id.graphCanvas);
		Graph graph = new Graph(this, rm, graphsize, 50, 50);
		Bitmap result = Bitmap.createBitmap(graphsize, graphsize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(result);
		graph.draw(canvas);
		graph.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		layout.addView(graph);

	}


	private void layoutSetup() {
		setContentBasedOnLayout();
		showList = (Button) findViewById(R.id.bGraphtoList);
		deleteHistory = (Button) findViewById(R.id.bGraphDelHistory);
		home = (Button) findViewById(R.id.bGraphHome);
		click = MediaPlayer.create(ResultGraph.this, R.raw.click_sound);

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

	private void setContentBasedOnLayout()
	{
	    WindowManager winMan = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	    
	    if (winMan != null)
	    {
	        int orientation = winMan.getDefaultDisplay().getOrientation();
	        
	        if (orientation == 0) {
	            // Portrait
	            setContentView(R.layout.history_graph);
	        }
	        else if (orientation == 1) {
	            // Landscape
	            setContentView(R.layout.land_history_graph);
	        }            
	    }
	}
	
	
}
