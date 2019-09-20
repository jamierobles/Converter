package com.example.converter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.math.BigDecimal;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    boolean cel;
    boolean far;

    private TextView history;
    private EditText inputVal;
    private TextView outputVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView view = (TextView) findViewById(R.id.history);
        view.setMovementMethod(new ScrollingMovementMethod());

        history=(TextView) findViewById(R.id.history);
        inputVal=(EditText)findViewById(R.id.input1);
        outputVal=(TextView)findViewById(R.id.output);
    }

    public void radioClicked2(View v) {
        far = true;
        cel = false;

        final String[] farCel = {"Fahrenheit to Celsius"};
        final TextView inputText = (TextView) findViewById(R.id.inputDisplay);
        RadioButton farCelB = (RadioButton) findViewById(R.id.radioButton1);
        inputText.setText("Fahrenheit Degrees:");

        final TextView outputText = (TextView) findViewById(R.id.outputDisplay);
        RadioButton celFarB = (RadioButton) findViewById(R.id.radioButton1);
        outputText.setText("Celsius Degrees:");

    }

    public void radioClicked(View v) {
        cel = true;
        far = false;
        final String[] celFar = {"Celcius to Fahrenheit"};
        final TextView inputText = (TextView) findViewById(R.id.inputDisplay);
        RadioButton celFarB = (RadioButton) findViewById(R.id.radioButton2);
        inputText.setText("Celcius Degrees:");

        final TextView outputText = (TextView) findViewById(R.id.outputDisplay);
        RadioButton farCelB = (RadioButton) findViewById(R.id.radioButton2);
        outputText.setText("Fahrenheit Degrees:");

    }
    public float round(double number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();}

    public void onClick1(View v) {
        final TextView history1 = (TextView) findViewById(R.id.history);

        if (cel) {
            //final TextView input = (TextView) findViewById(R.id.input1);
            //final TextView output = (TextView) findViewById(R.id.output);
            String incel = inputVal.getText().toString();
            float celsius = Float.parseFloat(incel);
            String oldData = history1.getText().toString();
            outputVal.setText(String.valueOf(round((celsius * 1.8) + 32, 1)));


            String newHist2 = outputVal.getText().toString();
            history1.setText(incel + "C==> " + newHist2 + "F" + "\n" + oldData);
            inputVal.setText("");
        } else {
            //final TextView input = (TextView) findViewById(R.id.input1);
            //final TextView output = (TextView) findViewById(R.id.output);
            String infar = inputVal.getText().toString();
            float fahrenheit = Float.parseFloat(infar);

            String oldData = history1.getText().toString();
            outputVal.setText(String.valueOf(round((fahrenheit - 32) / 1.8, 1)));

            String newHist = outputVal.getText().toString();
            history1.setText(infar + "F==> " + newHist + "C" + "\n" + oldData);
            inputVal.setText("");

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("HISTORY", history.getText().toString());
        outState.putString("INPUT", inputVal.getText().toString());
        outState.putString("OUTPUT", outputVal.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        history.setText(savedInstanceState.getString("HISTORY"));
        inputVal.setText(savedInstanceState.getString("INPUT"));
        outputVal.setText(savedInstanceState.getString("OUTPUT"));
    }


    public void onClick2(View v){
        final TextView clear = (TextView) findViewById(R.id.history);
        clear.setText("");
    }
}
