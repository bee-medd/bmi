package com.plantfloor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Currently just showing a toast confirmation.
            Toast.makeText(this, "Request submitted: " + mat + " x" + qty, Toast.LENGTH_LONG).show();
            finish(); // Close the activity
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
