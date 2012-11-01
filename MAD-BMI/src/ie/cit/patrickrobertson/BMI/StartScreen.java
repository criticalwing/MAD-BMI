package ie.cit.patrickrobertson.BMI;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StartScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_screen, menu);
        return true;
    }
}
