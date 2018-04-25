package tour.app.english.com.tourapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

    //프로그레스바 코드
    ProgressBar progressBar;
    int i;
    Handler handler;
    TextView txt;
    AlertDialog.Builder dialog;
    //


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

        //프로그레스바 코드
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt = (TextView) findViewById(R.id.txt);
        dialog = new AlertDialog.Builder(this);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                txt.setText(msg.arg1 + "초");
                if (msg.arg1 == 20) {
                    dialog.setCancelable(false);
                    dialog.setMessage("Time Over!");
                    dialog.setPositiveButton("열심히 공부합시다!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressBar.setProgress(0);
                            txt.setText("다시 도전하세요!");
                            dialogInterface.dismiss();
                        }
                    });
                    //dialog.show();

                }
            }
        };

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 0; i <= 20; i++) {
                    progressBar.setProgress(i);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();


        //정답확인 하기
        checkButton=findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
                checklaout.setVisibility(View.VISIBLE);
                if(input_text.equals(answer1)||input_text.equals(answer2)){
                    OxText.setText("정답입니다.");
                }else {
                    OxText.setText("틀렸습니다.");
                }
            }
        });
        //다시하기
        final Button retryButton =findViewById(R.id.quastion_retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        final Button anotherButton =findViewById(R.id.quastion_list_button);
        anotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
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
