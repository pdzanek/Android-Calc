package com.example.calc_;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                Intent basicCalc = new Intent(this,BasicCalc.class);
                startActivity(basicCalc);
                break;
            case R.id.button2:
                Intent advancedCalc = new Intent(this,AdvancedCalc.class);
                startActivity(advancedCalc);
                break;
            case R.id.button3:
                Intent aboutCalc = new Intent(this, About.class);
                startActivity(aboutCalc);
                break;
            case R.id.button4:
                finishAffinity();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
