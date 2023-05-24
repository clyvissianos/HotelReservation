package com.example.hotelreservationsexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button reservationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.customColor));
        }

        progressBar = findViewById(R.id.progressBar);
        reservationBtn = findViewById(R.id.reservationBtn);

        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation();

            }
        });
    }

    private void makeReservation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress <= 100) {
                    final int currentProgress = progress;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(currentProgress);
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Your reservation is booked!", Toast.LENGTH_SHORT).show();
                        progressBar.setProgress(0);
                    }
                });
            }
        }).start();


    }
}