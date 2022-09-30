package com.example.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView equationView = findViewById(R.id.equation_et);
        TextView resultView = findViewById(R.id.result_et);

        Button[] numButtons;
        for(int i=0;i<10;i++){
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id",
                    this.getPackageName()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String t = (String) ((Button)view).getText();
                    equationView.setText(equationView.getText()+t);
                }
            });
        }

        Button radix = findViewById(R.id.radix_btn);
        radix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String equation = (String) equationView.getText();
                int len = equation.length();
                char last = equation.charAt(len-1);
                if (last != '.'){
                    equationView.setText(equation+".");
                }
            }
        });

        Button add = findViewById(R.id.plus_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"+");
            }
        });
        Button sub = findViewById(R.id.sub_btn);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"-");
            }
        });
        Button mult = findViewById(R.id.mult_btn);
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"*");
            }
        });
        Button div = findViewById(R.id.div_btn);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"/");
            }
        });
        Button mod = findViewById(R.id.mod_btn);
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"%");
            }
        });
        Button exp = findViewById(R.id.exp_btn);
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText(equationView.getText()+"^");
            }
        });

        Button ac = findViewById(R.id.ac_btn);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationView.setText("");
                resultView.setText("");
            }
        });
        Button del = findViewById(R.id.del_btn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String equation = (String) equationView.getText();
                int len = equation.length();

                if (len <= 1){
                    equationView.setText("");
                }
                else {
                    String lastRemoved = equation.substring(0, len-1);
                    equationView.setText(lastRemoved);
                }
            }
        });

        Button equal = findViewById(R.id.equal_btn);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(equationView.getText().equals("")){
                    equationView.setText("0");
                }

                String data = equationView.getText().toString();
                String operators[]=data.split("[0-9.]+");
                String operands[]=data.split("[+*%^/-]");

                if(operands.length==0 || operators.length==0){
                    equationView.setText("");
                    return;
                }
                if(operators.length==1){
                    equationView.setText(operators[0]);
                    return;
                }

                Double result = Double.parseDouble(operands[0]);
                for(int i=1, j=1;i<operands.length;i++,j++){
                    if(operators[j].equals("+")){
                        result += Double.parseDouble(operands[i]);
                    }
                    else if(operators[j].equals("-")){
                        result -= Double.parseDouble(operands[i]);
                    }
                    else if(operators[j].equals("*")){
                        result *= Double.parseDouble(operands[i]);
                    }
                    else if(operators[j].equals("/")){
                        result /= (float) Double.parseDouble(operands[i]);
                    }
                    else if (operators[j].equals("%")){
                        result = result % Integer.parseInt(operands[i]);
                    }
                    else if (operators[j].equals("^")){
                        result = (Double) Math.pow(result, Integer.parseInt(operands[i]));
                    }
                }
                resultView.setText(String.format("= %f",result));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);

        MenuItem itemSwitch = menu.findItem(R.id.themeSwitch);
        itemSwitch.setActionView(R.layout.use_switch);
        Switch themeSwitch = (Switch) menu.findItem(R.id.themeSwitch).getActionView().findViewById(R.id.action_switch);

        themeSwitch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(themeSwitch.isChecked()){
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                     Toast.makeText(getBaseContext(), "Theme changed to dark mode.", Toast.LENGTH_LONG).show();
                     // compoundButton.setChecked(true);
                     // themeSwitch.setChecked(true);
                     // themeSwitch.setOnCheckedChangeListener(this);
                     themeSwitch.setChecked(true);
                 }
                 else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(getBaseContext(), "Theme changed to light mode.", Toast.LENGTH_LONG).show();
                    themeSwitch.setChecked(false);
                }
             }
         });
//        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    Toast.makeText(getBaseContext(), "Theme changed to dark mode.", Toast.LENGTH_LONG).show();
//                   // compoundButton.setChecked(true);
//                    compoundButton.setChecked(true);
//                    //themeSwitch.is
//                }
//                else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    Toast.makeText(getBaseContext(), "Theme changed to light mode.", Toast.LENGTH_LONG).show();
//                    themeSwitch.setChecked(isChecked);
//                }
//            }
//        });
        return true;
    }
}