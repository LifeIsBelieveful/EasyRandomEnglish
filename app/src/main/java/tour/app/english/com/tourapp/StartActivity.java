package tour.app.english.com.tourapp;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    String question_text,input_text = "";
    String Category = "Airport";
    String questionNumber = "1";
    String question = "I'd /like/ to book/ a flight/ to New York.";
    Map<String, List<Map<String, String>>> questionTest;
    int click = 0;
    TextView textview,OxText;

    String answer1 ="I'd like to book a flight to New York.";
    String answer2 ="wdasdasd";

    LinearLayout questionlayout,checklaout;
    Button airport,hotel,restaurant,checkButton;
    FragmentManager fm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        checklaout=findViewById(R.id.check_layout);

        OxText=findViewById(R.id.oxtext);

        textview = findViewById(R.id.inputtext);

        fm = getSupportFragmentManager();

        questionlayout= (LinearLayout)findViewById(R.id.layout_infragment);
        airport=findViewById(R.id.airport);
        airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionlayout.setVisibility(View.GONE);
                fm.beginTransaction().replace(
                        R.id.layout_fragment, new Fragment_air()).commit();
            }
        });

        hotel=findViewById(R.id.hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
