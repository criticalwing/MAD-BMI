package ie.cit.patrickrobertson.BMI;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends Activity{

	Button history, enter, email, wiki;
	MediaPlayer click;
	ResultsManager rm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
    	click = MediaPlayer.create(Welcome.this, R.raw.click_sound);
		history = (Button) findViewById(R.id.bShowHistory);
		email = (Button) findViewById(R.id.bEmail);
		enter = (Button) findViewById(R.id.bNewEntry);
		wiki = (Button) findViewById(R.id.bWiki);
		rm = new ResultsManager(this);
		
		history.setOnClickListener(new View.OnClickListener() {
			//play sound effect

			public void onClick(View v) {
				click.start();
				Intent history = new Intent("ie.cit.patrickrobertson.RESULTSHISTORYSCREEN");
				startActivity(history);
			}
		});
		
		enter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				click.start();
				Intent enter = new Intent("ie.cit.patrickrobertson.CALCULATEBMI");
				startActivity(enter);
			}
		});
	
		email.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String output = "My BMI History\n";
				for(Result x : rm.getResults()){
					output = output.concat(x.toString()).concat("-BMI = ").concat(String.valueOf(x.getBMI()).concat("\n"));
				}
				
				Intent intent = new Intent(Intent.ACTION_SEND);

				String[] recipients = new String[]{"enterYourEmailAddress", "",};

				
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL, recipients);
				intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
				intent.putExtra(Intent.EXTRA_TEXT, output);

				startActivity(Intent.createChooser(intent, "Send Email"));
				
			}
		});
		
		wiki.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String url = "http://http://en.wikipedia.org/wiki/Body_mass_index";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				
			}
		});
		
	
	}
	

}
