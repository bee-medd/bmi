package com.plantfloor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity {

    private EditText etDepartment, etMaterial, etQuantity;
    private Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        etDepartment = findViewById(R.id.etDepartment);
        etMaterial = findViewById(R.id.etMaterial);
        etQuantity = findViewById(R.id.etQuantity);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(v -> {
            String dept = etDepartment.getText().toString().trim();
            String mat = etMaterial.getText().toString().trim();
            String qty = etQuantity.getText().toString().trim();

            if (dept.isEmpty() || mat.isEmpty() || qty.isEmpty()) {
                Toast.makeText(this, "❌ Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            showConfirmationDialog(dept, mat, qty);
        });

        btnCancel.setOnClickListener(v -> finish());
    }

    private void showConfirmationDialog(String dept, String mat, String qty) {
        new AlertDialog.Builder(this)
            .setTitle("Confirm Request")
            .setMessage("You are requesting " + qty + " units of " + mat + " for " + dept + ".\n\nDo you want to proceed?")
            .setPositiveButton("Yes, Submit", (dialog, which) -> {
                Toast.makeText(this, "✅ Request submitted successfully!", Toast.LENGTH_LONG).show();
                finish();
            })
            .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
            .show();
    }
}
