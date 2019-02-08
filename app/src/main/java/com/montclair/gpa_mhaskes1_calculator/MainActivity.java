package com.montclair.gpa_mhaskes1_calculator;

import android.app.Notification;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    EditText grade1,grade2,grade3,grade4,grade5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade1 = findViewById(R.id.grade1);
        grade2 = findViewById(R.id.grade2);
        grade3 = findViewById(R.id.grade3);
        grade4 = findViewById(R.id.grade4);
        grade5 = findViewById(R.id.grade5);

        grade1.addTextChangedListener(gpaTextWatcher);
        grade2.addTextChangedListener(gpaTextWatcher);
        grade3.addTextChangedListener(gpaTextWatcher);
        grade4.addTextChangedListener(gpaTextWatcher);
        grade5.addTextChangedListener(gpaTextWatcher);

    };


    boolean allValidField = false;

    public void calculateGPA (View view){
        // validate content
        allValidField = true;

        validateEmptyField(grade1);
        validateEmptyField(grade2);
        validateEmptyField(grade3);
        validateEmptyField(grade4);
        validateEmptyField(grade5);

        TextView result = findViewById(R.id.result);

        if(!allValidField){
            result.setText("Please enter value in RED field(s) above" );
            Toast.makeText(this, "Please enter value in RED fields ", Toast.LENGTH_LONG).show();
            return;
        }

        double res = getGPAScore();

        //TODO: update output text/label


        result.setText("Your GPA is " + res);
        ((Button)findViewById(R.id.button)).setText("");

        grade1.setText("0");
        grade2.setText("0");
        grade3.setText("0");
        grade4.setText("0");
        grade5.setText("0");

        updateBackground(res);

        Toast.makeText(this, "Calculated GPA Successfully:: " + res, Toast.LENGTH_LONG).show();


    }

    private void validateEmptyField(EditText e) {
        if(e.getText().toString().trim().isEmpty()){
            e.setBackgroundColor(Color.RED);
            allValidField = false;
        } else {
            e.setBackgroundColor(Color.WHITE);
        }
    }

    private double getGPAScore() {
        //TODO: read all text baxes

        String g1 = grade1.getText().toString();
        String g2 = grade2.getText().toString();
        String g3 = grade3.getText().toString();
        String g4 = grade4.getText().toString();
        String g5 = grade5.getText().toString();


        int i1 = Integer.valueOf(g1);
        int i2 = Integer.valueOf(g2);
        int i3 = Integer.valueOf(g3);
        int i4 = Integer.valueOf(g4);
        int i5 = Integer.valueOf(g5);
        TextView textView;

        // TODO: calculate GPA

        return (double) ((i1 + i2 + i3 + i4 +i5)/5);
    }

    private void updateBackground(double res) {
        if (res<60){
            findViewById(R.id.table).setBackgroundColor(Color.RED);
        }else if (res > 60 && res < 80){
            findViewById(R.id.table).setBackgroundColor(Color.YELLOW);
        } else {
            findViewById(R.id.table).setBackgroundColor(Color.GREEN);
        }
    }

    private TextWatcher gpaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            return;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        };

        @Override
        public void afterTextChanged(Editable s) {
            if(((Button)findViewById(R.id.button)).getText().length() == 0){
                ((Button)findViewById(R.id.button)).setText("Calculate GPA");
            };

            validateEmptyField(grade1);
            validateEmptyField(grade2);
            validateEmptyField(grade3);
            validateEmptyField(grade4);
            validateEmptyField(grade5);

            return;
        }


    };

}
