package com.plantfloor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RequestActivity extends AppCompatActivity {

    private EditText etDepartment, etMaterial, etQuantity;
    private Button btnSubmit, btnCancel;
    private ProgressBar pbSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        etDepartment = findViewById(R.id.etDepartment);
        etMaterial = findViewById(R.id.etMaterial);
        etQuantity = findViewById(R.id.etQuantity);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        pbSubmit = findViewById(R.id.pbSubmit);

        btnSubmit.setOnClickListener(v -> {
            String dept = etDepartment.getText().toString().trim();
            String mat = etMaterial.getText().toString().trim();
            String qty = etQuantity.getText().toString().trim();

            if (dept.isEmpty() || mat.isEmpty() || qty.isEmpty()) {
                Toast.makeText(this, "❌ Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int quantity = Integer.parseInt(qty);
                if (quantity <= 0) {
                    Toast.makeText(this, "❌ Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "❌ Invalid quantity format", Toast.LENGTH_SHORT).show();
                return;
            }

            showConfirmationDialog(dept, mat, qty);
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    private void showConfirmationDialog(String dept, String mat, String qty) {
        new AlertDialog.Builder(this)
            .setTitle("Validate Request")
            .setMessage("Request Details:

Department: " + dept + "
Material: " + mat + "
Quantity: " + qty + "

Confirm submission to Warehouse?")
            .setPositiveButton("Confirm", (dialog, which) -> processSubmission())
            .setNegativeButton("Modify", (dialog, which) -> dialog.dismiss())
            .show();
    }

    private void processSubmission() {
        // UI State: Loading
        btnSubmit.setEnabled(false);
        pbSubmit.setVisibility(View.VISIBLE);

        // Simulate server processing delay (1.5s)
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            pbSubmit.setVisibility(View.GONE);
            btnSubmit.setEnabled(true);
            
            // Generate a professional Request ID
            String requestId = "REQ-" + (1000 + new Random().nextInt(9000)) + "-" + System.currentTimeMillis() % 1000;
            
            new AlertDialog.Builder(this)
                .setTitle("Request Submitted")
                .setMessage("Your request has been successfully logged.

Reference ID: " + requestId + "
Status: Pending Approval")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
        }, 1500);
    }
}
