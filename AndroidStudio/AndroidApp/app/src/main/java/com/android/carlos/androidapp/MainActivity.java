package com.android.carlos.androidapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText n1Txt, n2Txt;
    TextView rTxt;
    Spinner op;
    ArrayAdapter<CharSequence> adapter;
    double num1, num2, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1Txt = (EditText)findViewById(R.id.editTextN1);
        n2Txt = (EditText) findViewById(R.id.editTextN2);
        rTxt = (TextView)findViewById(R.id.result);
        op = (Spinner)findViewById(R.id.operators);
        adapter = ArrayAdapter.createFromResource(this,R.array.operators,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        op.setAdapter(adapter);

        final Button calcBtn = (Button)findViewById(R.id.btnCalc);
        final Button clrBtn = (Button)findViewById(R.id.btnClear);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n2Txt.getText().toString().equals("0") && (op.toString().equals("/") || op.toString().equals("%"))) {
                    rTxt.setText(0);
                    Toast.makeText(getApplicationContext(), "No se puede dividir entre 0!", Toast.LENGTH_SHORT).show();
                }
                rTxt.setText(res+"");
                Toast.makeText(getApplicationContext(), "Su resultado ha sido calculado!", Toast.LENGTH_SHORT).show();
            }
        });

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1Txt.setText(0);
                n2Txt.setText(0);
                rTxt.setText(0);
            }
        });

        n1Txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcBtn.setEnabled(!(n1Txt.getText().toString().trim().isEmpty() || n2Txt.getText().toString().trim().isEmpty()));
                clrBtn.setEnabled(!n1Txt.getText().toString().trim().isEmpty());
                n2Txt.setEnabled(!n1Txt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onButtonClick(View v){
        num1 = Double.parseDouble(n1Txt.getText().toString());
        num2 = Double.parseDouble(n2Txt.getText().toString());

        if(op.toString().equals("/")){
            res = num1 + num2;
        }
        else if (op.toString().equals("%")){
            res = num1 % num2;
        }
        else if (op.toString().equals("+")){
            res = num1 + num2;
        }
        else if (op.toString().equals("-")){
            res = num1 - num2;
        }
        else if (op.toString().equals("*")){
            res = num1 * num2;
        }
        else if (op.toString().equals("^")){
            res = num1;
            for(int x = 0; x < num2; x++){
                res *=num1;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
