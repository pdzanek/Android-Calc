package com.example.calc_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BasicCalc extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button button00,button01,button02,
            button10,button11,button12,button13,
            button20,button21,button22,button23,
            button30,button31,button32,button33,
            button40,button41,button42,button43;
    String textViewString="";
    String operation="";
    double operand1=0, operand2=0,result;
    long lastButton01pressed=System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calc);
        textView = findViewById(R.id.textView);
        button00 = findViewById(R.id.button00);
        button00.setOnClickListener(this);
        button01 = findViewById(R.id.button01);
        button01.setOnClickListener(this);
        button02 = findViewById(R.id.button02);
        button02.setOnClickListener(this);

        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(this);
        button11 = findViewById(R.id.button11);
        button11.setOnClickListener(this);
        button12 = findViewById(R.id.button12);
        button12.setOnClickListener(this);
        button13 = findViewById(R.id.button13);
        button13.setOnClickListener(this);

        button20 = findViewById(R.id.button20);
        button20.setOnClickListener(this);
        button21 = findViewById(R.id.button21);
        button21.setOnClickListener(this);
        button22 = findViewById(R.id.button22);
        button22.setOnClickListener(this);
        button23 = findViewById(R.id.button23);
        button23.setOnClickListener(this);

        button30 = findViewById(R.id.button30);
        button30.setOnClickListener(this);
        button31 = findViewById(R.id.button31);
        button31.setOnClickListener(this);
        button32 = findViewById(R.id.button32);
        button32.setOnClickListener(this);
        button33 = findViewById(R.id.button33);
        button33.setOnClickListener(this);

        button40 = findViewById(R.id.button40);
        button40.setOnClickListener(this);
        button41 = findViewById(R.id.button41);
        button41.setOnClickListener(this);
        button42 = findViewById(R.id.button42);
        button42.setOnClickListener(this);
        button43 = findViewById(R.id.button43);
        button43.setOnClickListener(this);

        if(savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
            if(textViewString.equals(""))
                textView.setText(operand1+"");
            else
            textView.setText(textViewString);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("operand1", operand1);
        savedInstanceState.putDouble("operand2", operand2);
        savedInstanceState.putString("operation",operation);
        savedInstanceState.putString("textViewString",textViewString);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand1 = savedInstanceState.getDouble("operand1");
        operand2 = savedInstanceState.getDouble("operand2");
        textViewString = savedInstanceState.getString("textViewString");
        operation = savedInstanceState.getString("operation");
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        String buttonText = b.getText().toString();
        switch (v.getId()) {
            case R.id.button00:
                if(textViewString.length()>0) {
                    if(textViewString.equals("Błąd")){
                        textViewString=0+"";
                        textView.setText(textViewString);
                    }
                    else{
                        textViewString = textViewString.substring(0, textViewString.length() - 1);
                        textView.setText(textViewString);
                    }
                }
                break;
            case R.id.button01:
                    if(lastButton01pressed<1000){
                        operation="";
                        operand1=0;
                        operand2=0;
                        textViewString="";
                    }
                    else {
                        lastButton01pressed = System.currentTimeMillis();
                        textViewString = "";
                        textView.setText(textViewString);
                    }
                break;
            case R.id.button10:
            case R.id.button11:
            case R.id.button12:
            case R.id.button20:
            case R.id.button21:
            case R.id.button22:
            case R.id.button30:
            case R.id.button31:
            case R.id.button32:
            case R.id.button40:
                    if(textViewString.equals("Błąd")) textViewString="";
                    textViewString+=buttonText;
                    textView.setText(textViewString);
                break;
            case R.id.button41:
                if(textViewString.equals("Błąd")) textViewString="0.";
                else {
                    if(textViewString.contains(".")) {
                        Toast.makeText(this, "Wprowadzona liczba posiada już separator dziesiętny!",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        textViewString += buttonText;
                        textView.setText(textViewString);
                    }
                }
                break;
            case R.id.button42: //+
            case R.id.button13: // /
            case R.id.button23: //*
            case R.id.button33: //-
                if((operation.equals(""))) {
                    operation = buttonText;
                    if(textViewString.equals("") || textViewString.equals("Błąd")) operand1=0;
                    else operand1 = Double.parseDouble(textViewString);
                    textViewString = "";
                }
                else{
                    operation = buttonText;
                }

                break;
            case R.id.button43: //=
                if(operation!="") {
                    if (!(textViewString.equals(""))) {
                        operand2 = Double.parseDouble(textViewString);
                        if (operation.equals("/") && operand2 == 0) {
                            operand1 = 0;
                            operand2 = 0;
                            operation = "";
                            textViewString = "Błąd";
                            textView.setText(textViewString);
                            Toast.makeText(this, "Wykryto próbę dzielenia przez zero!",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            result = calculate(operand1, operand2, operation);
                            operand1 = 0;
                            operand2 = 0;
                            operation = "";
                            textViewString = result + "";
                            textView.setText(textViewString);
                        }
                    }
                }
                break;
            case R.id.button02:
                if(!textViewString.equals("")) {
                    if (textViewString.charAt(0) == '-')
                        textViewString = textViewString.substring(1);
                    else textViewString = "-" + textViewString;
                    textView.setText(textViewString);
                }
                break;
            default:
                break;
        }
    }
    public double calculate(double operand1, double operand2, String operation)
    {
        switch(operation){
            case "+":
                return operand1+operand2;
            case "*":
                return operand1*operand2;
            case "-":
                return operand1-operand2;
            case "/":
                return operand1/operand2;
            default:
                return 1;
        }
    }
}
