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

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        checklaout=findViewById(R.id.check_layout);
        checklaout.setVisibility(View.GONE);

        OxText=findViewById(R.id.oxtext);

        textview = findViewById(R.id.inputtext);

        fm = getSupportFragmentManager();

        questionlayout= (LinearLayout)findViewById(R.id.layout_infragment);

        createbutton();

        //정답확인 하기
        checkButton=findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklaout.setVisibility(View.VISIBLE);
                if(input_text.equals(answer1)||input_text.equals(answer2)){
                    OxText.setText("정답입니다.");
                }else {
                    OxText.setText("틀렸습니다.");
                }
            }
        });
    }
    //하단에 버튼 랜덤으로 뿌려주기
    public void createbutton(){

        QuestionData questionData = new QuestionData();

        questionTest = questionData.putQuestion(Category,questionData.putQuestiondata("1",question));


        List<String> savesplit;
        LinearLayout linearLayout = findViewById(R.id.buttonlayout);

        LinearLayout qbuttonlayout1 = findViewById(R.id.qbuttonLayout1);
        LinearLayout qbuttonlayout2 = findViewById(R.id.qbuttonLayout2);

        SplitSentence splitSentence = new SplitSentence();
        String inputquestion = questionTest.get("Airport").get(0).get("1");
        savesplit = splitSentence.splitString(inputquestion);

        Collections.shuffle(savesplit);

        for(int i=0;i<savesplit.size();i++){
            final Button btn = new Button(this);
            btn.setText(savesplit.get(i));

            if(i<4){
                qbuttonlayout1.addView(btn);
            }
            else{
                qbuttonlayout2.addView(btn);
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click == 0) {
                        input_text += btn.getText();
                        textview.setText(input_text);
                        click++;

                    } else {
                        if (!textview.getText().toString().contains(btn.getText().toString())) {
                            input_text += btn.getText();
                            textview.setText(input_text);
                        }
                    }
                }
            });
        }
    }
}
