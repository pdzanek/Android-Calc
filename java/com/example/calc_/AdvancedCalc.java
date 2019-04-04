package com.example.calc_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedCalc extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button button00,button01,button02,
            button10,button11,button12,button13,
            button20,button21,button22,button23,
            button30,button31,button32,button33,
            button40,button41,button42,button43,
            button50,button51,button52,button53,
            button60,button61,button62,button63;
    String textViewString="";
    String operation="";
    double operand1=0, operand2=0;
    Double result;
    long lastButton01pressed=System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);
        initComponents();
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
            if (textViewString.equals(""))
                textView.setText(operand1 + "");
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
        savedInstanceState.putDouble("result", result);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand1 = savedInstanceState.getDouble("operand1");
        operand2 = savedInstanceState.getDouble("operand2");
        textViewString = savedInstanceState.getString("textViewString");
        operation = savedInstanceState.getString("operation");
        result = savedInstanceState.getDouble("result");
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
                    result=null;
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
            case R.id.button62: //x^y
            case R.id.button33: //-
                if(operation.equals("")){
                    if(!(textViewString.equals(""))){
                        if(textViewString=="Błąd")
                            operand1=0;
                        else
                            operand1=Double.parseDouble(textViewString);
                        operation=buttonText;
                        textViewString="";
                    }
                }
                else{
                    if(!(textViewString.equals(""))) {
                        if(textViewString.equals("Błąd")) {
                            operand2 = 0;
                        }
                        else {
                            operand2 = Double.parseDouble(textViewString);
                        }
                        if(operand2==0 && operation.equals("/")) {
                            result=null;
                            operand1 = 0;
                            operand2 = 0;
                            operation = "";
                            textViewString = "Błąd";
                            textView.setText(textViewString);
                            Toast.makeText(this, "Wykryto próbę dzielenia przez zero!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            calculate(operand1, operand2, operation);
                            operation = buttonText;
                            textViewString = "";
                            operand2 = 0;
                            operand1 = result;
                            textView.setText(result + "");
                        }
                    }
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
                            calculate(operand1, operand2, operation);
                            operand1 = 0;
                            operand2 = 0;
                            operation = "";
                            textViewString = result + "";
                            textView.setText(textViewString);
                            result=null;
                        }
                    }
                }
                break;
            case R.id.button50:
                if(!textViewString.equals("")){
                    if(!textViewString.equals("Błąd")) {
                        double temp = Math.sin(Double.parseDouble(textViewString));
                        textViewString = temp + "";
                    }else textViewString=0+"";
                    textView.setText(textViewString);
                }
                break;
            case R.id.button51:
                if(!textViewString.equals("")){
                    if(!textViewString.equals("Błąd")) {
                        double temp = Math.cos(Double.parseDouble(textViewString));
                        textViewString = temp + "";
                    }else textViewString=0+"";
                    textView.setText(textViewString);
                }
                break;
            case R.id.button52:
                if(!textViewString.equals("")){
                    if(!textViewString.equals("Błąd")) {
                        double temp = Math.tan(Double.parseDouble(textViewString));
                        textViewString = temp + "";
                    }else textViewString=0+"";
                    textView.setText(textViewString);
                }
                break;
            case R.id.button53:
                if(!textViewString.equals("")) {
                    if (!textViewString.equals("Błąd")) {
                        double temp = Double.parseDouble((textViewString));
                        if (temp > 0) {
                            temp = Math.log(Double.parseDouble(textViewString));
                            textViewString = temp + "";

                        } else {
                            textViewString = "Błąd";
                        }
                        textView.setText(textViewString);
                    } else textViewString = 0 + "";
                    textView.setText(textViewString);
                }
                break;
            case R.id.button60:
                if(!textViewString.equals("")) {
                    if (!textViewString.equals("Błąd")) {
                        double temp = Double.parseDouble(textViewString);
                        if (temp >= 0) {
                            temp = Math.sqrt(temp);
                            textViewString = temp + "";
                        } else {
                            Toast.makeText(this, "Liczba pierwiastkowana powinna być nieujemna",
                                    Toast.LENGTH_LONG).show();
                            textViewString = "Błąd";
                        }
                        textView.setText(textViewString);
                    } else {
                        textViewString = "Błąd";
                    }
                    textView.setText(textViewString);
                }
                break;
            case R.id.button61:
                if(!textViewString.equals("")){
                    if(!textViewString.equals("Błąd")) {
                        double temp = Math.pow(Double.parseDouble(textViewString), 2);
                        textViewString = temp + "";
                    }
                    else textViewString = 0 + "";
                }
                textView.setText(textViewString);
                break;
            case R.id.button63:
                if(!textViewString.equals("")){
                    if(!textViewString.equals("Błąd")) {
                    double temp = Double.parseDouble(textViewString);
                    if(temp>=0) {
                        temp = Math.log10(temp);
                        textViewString = temp + "";
                    }
                    else{
                        Toast.makeText(this, "Liczba logarytmowana powinna być nieujemna",
                                Toast.LENGTH_LONG).show();
                        textViewString="Błąd";
                    }
                    textView.setText(textViewString);
                    } else textViewString = 0 + "";
                    textView.setText(textViewString);
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
                result= operand1+operand2;
                break;
            case "*":
                result= operand1*operand2;
                break;
            case "-":
                result= operand1-operand2;
                break;
            case "/":
                result= operand1/operand2;
                break;
            case "x^y":
                result=Math.pow(operand1,operand2);
                break;
            default:
                break;
        }
        return result;
    }
    private void initComponents(){
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

        button50 = findViewById(R.id.button50);
        button50.setOnClickListener(this);
        button51 = findViewById(R.id.button51);
        button51.setOnClickListener(this);
        button52 = findViewById(R.id.button52);
        button52.setOnClickListener(this);
        button53 = findViewById(R.id.button53);
        button53.setOnClickListener(this);

        button60 = findViewById(R.id.button60);
        button60.setOnClickListener(this);
        button61 = findViewById(R.id.button61);
        button61.setOnClickListener(this);
        button62 = findViewById(R.id.button62);
        button62.setOnClickListener(this);
        button63 = findViewById(R.id.button63);
        button63.setOnClickListener(this);
    }
}
