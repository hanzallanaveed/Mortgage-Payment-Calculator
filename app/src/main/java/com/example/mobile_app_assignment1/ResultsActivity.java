package com.example.mobile_app_assignment1;

// Import libraries and classes required
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

// ResultsActivity class definition
public class ResultsActivity extends AppCompatActivity {

    private double monthlyEmi; // Member variable to store calculated EMI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results); // Set layout for this activity
        setTitle("EMI Calculator"); // Set title of the activity

        // Retrieve data passed from MainActivity through intent
        Intent intent = getIntent();
        double principalAmount = intent.getDoubleExtra("principalAmount", 0);
        double interestRate = intent.getDoubleExtra("interestRate", 0);
        int amortizationPeriod = intent.getIntExtra("amortizationPeriod", 0);

        // Calculate EMI using the provided data
        monthlyEmi = calculateEmi(principalAmount, interestRate, amortizationPeriod);

        // Initialize TextView and display calculated EMI
        TextView paymentAmountTextView = findViewById(R.id.tvPaymentAmount);
        paymentAmountTextView.setText("$" + String.format("%.2f", monthlyEmi) + " / Month");
    }

    // Calculate EMI based on principal, interest rate, and amortization period
    public static double calculateEmi(double principalAmount, double interestRate, int amortizationPeriod) {
        double monthlyInterestRate = (interestRate / 12) / 100; // Calculate monthly interest rate
        int totalMonths = amortizationPeriod * 12; // Calculate total number of monthly installments

        // Calculate monthly EMI using the formula
        double emi = principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths) /
                (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);

        return emi; // Return the calculated EMI
    }
}
