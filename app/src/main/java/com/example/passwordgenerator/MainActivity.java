/*
*This app was created by Vincent Delle
*Date 10/15/2021
* Version 1.0
* This was created out of boredom and learning purposes
**/

package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String alphaNumValues= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*()_+{}[]|/?";
    private static final String digitValue= "1234567890";

    Button genbutton;
    Button rst;
    RadioGroup rgroup;
    EditText editText;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgroup = findViewById(R.id.rGroup);
        genbutton = findViewById(R.id.generatebutton);
        editText = findViewById(R.id.editTextNumber);
        display = findViewById(R.id.passwordDisplay);
        rst = findViewById(R.id.reset);

        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = editText.getText().toString().trim();
                genbutton.setEnabled(!value.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        genbutton.setOnClickListener(v -> {
            int checkedId = rgroup.getCheckedRadioButtonId();
            String value = editText.getText().toString().trim();

            if(checkedId == -1){
                Message.message(getApplicationContext(), "Please select Password Type");
            }
            else if(value.isEmpty()){
                Message.message(getApplicationContext(), "Please Enter the number of characters you want");
            }
            else{
                findRadioButton(checkedId);
                genbutton.setVisibility(View.GONE);
                rst.setVisibility(View.VISIBLE);
                editText.setEnabled(false);
                for(int i = 0; i < rgroup.getChildCount(); i++){
                    rgroup.getChildAt(i).setEnabled(false);
                }
            }
        });

        rst.setOnClickListener(v -> {
            editText.getText().clear();
            editText.setEnabled(true);
            rgroup.clearCheck();
            display.setText("");
            genbutton.setEnabled(false);
            genbutton.setVisibility(View.VISIBLE);
            rst.setVisibility(View.GONE);
            for(int i = 0; i < rgroup.getChildCount(); i++){
                rgroup.getChildAt(i).setEnabled(true);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void findRadioButton(int checkedId) {
        Random rand = new Random();
        int temp;
        StringBuilder newPassword= new StringBuilder();
        switch(checkedId){
            case R.id.digits:
                //Message.message(getApplicationContext(),"Digit Selected");
                for(int i=0; i<Integer.parseInt(editText.getText().toString()); i++){
                    temp= rand.nextInt(digitValue.length());
                    newPassword.append(digitValue.charAt(temp));
                }
                display.setText(newPassword);

                break;

            case R.id.alphaNumeric:
                //Message.message(getApplicationContext(),"AlphaNumeric Selected");
                for(int i=0; i<Integer.parseInt(editText.getText().toString()); i++){
                    temp= rand.nextInt(alphaNumValues.length());
                    newPassword.append(alphaNumValues.charAt(temp));
                }
                display.setText(newPassword);
                break;
        }
    }
}