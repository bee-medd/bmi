package com.plantfloor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle, tvDate, tvStatus, tvCounter;
    private Button btnRefresh, btnAction, btnRequest;
    private CardView cardStatus;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
        updateDateTime();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        tvStatus = findViewById(R.id.tvStatus);
        tvCounter = findViewById(R.id.tvCounter);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnAction = findViewById(R.id.btnAction);
        btnRequest = findViewById(R.id.btnRequest);
        cardStatus = findViewById(R.id.cardStatus);
    }

    private void setupListeners() {
        btnRefresh.setOnClickListener(v -> {
            updateDateTime();
            Toast.makeText(this, "🔄 Refreshed", Toast.LENGTH_SHORT).show();
        });

        btnAction.setOnClickListener(v -> {
            counter++;
            tvCounter.setText(String.valueOf(counter));
            updateStatus();
            Toast.makeText(this, "✅ Action performed", Toast.LENGTH_SHORT).show();
        });

        btnRequest.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RequestActivity.class));
        });
    }

    private void updateDateTime() {
        String date = new SimpleDateFormat("EEEE, MMM d, yyyy  HH:mm", Locale.US).format(new Date());
        tvDate.setText(date);
    }

    private void updateStatus() {
        if (counter == 0) {
            tvStatus.setText("Standby");
            cardStatus.setCardBackgroundColor(getColor(android.R.color.darker_gray));
        } else if (counter < 5) {
            tvStatus.setText("Active");
            cardStatus.setCardBackgroundColor(getColor(android.R.color.holo_orange_light));
        } else {
            tvStatus.setText("Production");
            cardStatus.setCardBackgroundColor(getColor(android.R.color.holo_green_light));
        }
    }
}
