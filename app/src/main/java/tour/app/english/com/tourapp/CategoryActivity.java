package tour.app.english.com.tourapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    TextView textview, OxText;

    LinearLayout questionlayout, checklaout;
    Button airport, accomodation, restaurant, checkButton;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        checklaout = findViewById(R.id.check_layout);

        OxText = findViewById(R.id.oxtext);

        textview = findViewById(R.id.inputtext);

        fm = getSupportFragmentManager();

        questionlayout = (LinearLayout) findViewById(R.id.layout_infragment);
        airport = findViewById(R.id.airport);
        airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionlayout.setVisibility(View.GONE);
                fm.beginTransaction().replace(
                        R.id.layout_fragment, new Fragment_airport()).commit();
            }
        });

        accomodation = findViewById(R.id.hotel);
        accomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionlayout.setVisibility(View.GONE);
                fm.beginTransaction().replace(
                        R.id.layout_fragment, new Fragment_accomodation()).commit();

            }
        });

        restaurant = findViewById(R.id.restaurant);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionlayout.setVisibility(View.GONE);
                fm.beginTransaction().replace(
                        R.id.layout_fragment, new Fragment_restaurant()).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.startlayout);
        new AlertDialog.Builder(this)
                .setTitle("프로그램 종료")
                .setMessage("프로그램을 종료하시겠습니까?")
                .setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setPositiveButton("아니오", null).show();
    }
}

