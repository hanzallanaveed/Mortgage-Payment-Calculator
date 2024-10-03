package com.example.mobile_app_assignment1;

// Import libraries and classes required
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Main activity class definition
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for this activity
        setTitle("EMI Calculator"); // Set the title of the activity

        // Initialize UI elements
        EditText principalEditText = findViewById(R.id.etPrincipalAmount);
        EditText interestEditText = findViewById(R.id.etInterestRate);
        EditText amortizationEditText = findViewById(R.id.etAmortizationPeriod);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button clearButton = findViewById(R.id.clearButton);

        // Set up the "Clear" button functionality
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear all input fields
                principalEditText.setText("");
                interestEditText.setText("");
                amortizationEditText.setText("");
            }
        });

        // Set up the "Calculate" button functionality
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Retrieve and parse input values
                    double principalAmount = Double.parseDouble(principalEditText.getText().toString());
                    double interestRate = Double.parseDouble(interestEditText.getText().toString());
                    int amortizationPeriod = Integer.parseInt(amortizationEditText.getText().toString());

                    // Validate input values
                    if (principalAmount <= 0 || interestRate <= 0 || amortizationPeriod <= 0) {
                        Toast.makeText(getApplicationContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                    } else {
                        // Create intent to start ResultsActivity with input data
                        Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                        intent.putExtra("principalAmount", principalAmount);
                        intent.putExtra("interestRate", interestRate);
                        intent.putExtra("amortizationPeriod", amortizationPeriod);
                        startActivity(intent); // Start ResultsActivity
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Toast.makeText(getApplicationContext(), "Please enter valid numbers in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
