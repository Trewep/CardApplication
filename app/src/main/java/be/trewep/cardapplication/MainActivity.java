package be.trewep.cardapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<Integer> number = new MutableLiveData<Integer>();


    Timer t = new Timer();
    private static int PERIOD = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number.setValue(0);

        GridLayout cardGridLayout = findViewById(R.id.gridlayout);

        for (int i = 1; i<=6; i++) {
            View cardView = getLayoutInflater().inflate(R.layout.number_card, cardGridLayout, false);
            TextView tv = cardView.findViewById(R.id.number_card_text);
            tv.setText(Integer.toString(i));
            cardGridLayout.addView(cardView);
            final int j = i;

            number.observe(this, number -> {
                tv.setText(Integer.toString(number * j));
            });
        }

        ProgressBar pb = findViewById(R.id.progress_bar);
        number.observe(this , number -> {
            pb.setProgress(number);
        });

    }

    public void add(View w)  {
        number.setValue(number.getValue() + 1);
    }

    public void startTimer(View w)  {
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                number.postValue(number.getValue() + 1);
            }
        }, 2000, PERIOD);
    }

}