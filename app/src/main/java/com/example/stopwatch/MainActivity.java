package com.example.stopwatch;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private TextView timerText;
    private Button startButton, stopButton, resetButton;
    private Handler handler = new Handler();
    private int seconds = 0;
    private boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerText = findViewById(R.id.timer_text);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        resetButton = findViewById(R.id.reset_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }
    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    seconds++;
                    updateTimerText();
                    handler.postDelayed(this, 1000);
                }
            }, 1000);
        }
    }
    private void stopTimer() {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
    }
    private void resetTimer() {
        stopTimer();
        seconds = 0;
        updateTimerText();
    }
    private void updateTimerText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerText.setText(time);
    }
}
