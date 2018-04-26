package tour.app.english.com.tourapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {

    String input_text = "";
    String Category;
    String question;
    Map<String, List<Map<String, String>>> questionTest;
    int click = 0;
    TextView textview, OxText;

    String answer1;
    String answer2 = "wdasdasd";

    LinearLayout questionlayout, checklaout;
    Button airport, hotel, restaurant, checkButton;
    FragmentManager fm;

    //프로그레스바 코드
    ProgressBar progressBar;
    TextView timertxt;
    BackgroundTask task;
    int value;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent getIntent = getIntent();

        Category = getIntent.getStringExtra("Category");
        question = getIntent.getStringExtra("Question");
        answer1 = getIntent.getStringExtra("Answercheck");

        checklaout = findViewById(R.id.check_layout);
        checklaout.setVisibility(View.GONE);

        OxText = findViewById(R.id.oxtext);

        textview = findViewById(R.id.inputtext);

        fm = getSupportFragmentManager();

        questionlayout = (LinearLayout) findViewById(R.id.layout_infragment);

        createbutton();

        ImageView timeoutimage = findViewById(R.id.timeoutimage);
        timeoutimage.setVisibility(View.GONE);

//        ImageView backgroundimg = findViewById(R.id.background);
//        backgroundimg.setVisibility(View.VISIBLE);

        //프로그레스바 코드
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        timertxt = (TextView) findViewById(R.id.txt);

        task = new BackgroundTask();
        task.execute(100);

        //정답확인 하기
        checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task.cancel(true);
                checklaout.setVisibility(View.VISIBLE);
                if (input_text.equals(answer1) || input_text.equals(answer2)) {
                    OxText.setText("정답입니다.");
                } else {
                    OxText.setText("틀렸습니다.");
                }
            }
        });
        //다시하기
        final Button retryButton = findViewById(R.id.quastion_retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        final Button anotherButton = findViewById(R.id.quastion_list_button);
        anotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
            }
        });
    }

    //하단에 버튼 랜덤으로 뿌려주기
    public void createbutton() {


        List<String> savesplit;
        LinearLayout linearLayout = findViewById(R.id.buttonlayout);

        LinearLayout qbuttonlayout1 = findViewById(R.id.qbuttonLayout1);
        LinearLayout qbuttonlayout2 = findViewById(R.id.qbuttonLayout2);

        SplitSentence splitSentence = new SplitSentence();
        String inputquestion = question;
        savesplit = splitSentence.splitString(inputquestion);

        Collections.shuffle(savesplit);

        for (int i = 0; i < savesplit.size(); i++) {
            final Button btn = new Button(this);
            btn.setText(savesplit.get(i));

            if (i < 4) {
                qbuttonlayout1.addView(btn);
            } else {
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

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

        ImageView timeoutimage = findViewById(R.id.timeoutimage);
//        ImageView backgroundimg = findViewById(R.id.background);


        SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        int SoundId = soundPool.load(QuestionActivity.this, R.raw.timeoutsound, 1);

        LinearLayout fragmentlayout = findViewById(R.id.layout_fragment_question);

        @Override
        protected void onPreExecute() {
            value = 10;
            progressBar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false) {
                value--;
                if (value <= 0) {
                    break;
                } else {
                    publishProgress(value);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
            timertxt.setText(values[0].toString() + "초");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);
            timertxt.setText("시간초과!");
//            backgroundimg.setVisibility(View.GONE);
            timeoutimage.setVisibility(View.VISIBLE);
            soundPool.play(SoundId, 3f, 3f, 0, 0, 1f);
            LinearLayout linearLayout = findViewById(R.id.timerlayout);
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
            builder.setTitle("시간초과 알림창");
            builder.setMessage("조금 더 분발하세요");
            builder.setPositiveButton("메인화면", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    soundPool.stop(SoundId);
                    moveTaskToBack(true);

                    Intent intent = new Intent(QuestionActivity.this, CategoryActivity.class);
                    startActivity(intent);

                }

            }).setNegativeButton("다시 볼래요", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }).show();
        }
    }
}