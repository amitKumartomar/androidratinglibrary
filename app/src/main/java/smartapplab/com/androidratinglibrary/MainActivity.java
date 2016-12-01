package smartapplab.com.androidratinglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import smartapplab.com.ratinglibrary.RatingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RatingDialog rating = new RatingDialog(this);
        rating.setShowStart(false);
        rating.showafter(1);
    }
}
