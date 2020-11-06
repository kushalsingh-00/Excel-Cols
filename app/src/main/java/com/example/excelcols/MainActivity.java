package com.example.excelcols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;

import com.example.excelcols.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.getPickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPicker();
            }
        });

    }

    public void numberPicker()
    {
        b.numberPicker.setMinValue(Integer.parseInt(b.setMin.getText().toString()));
        b.numberPicker.setMaxValue(Integer.parseInt(b.setMax.getText().toString()));

        b.numberPicker2.setMinValue(Integer.parseInt(b.setMin.getText().toString()));
        b.numberPicker2.setMaxValue(Integer.parseInt(b.setMax.getText().toString()));

        b.showCnumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    b.numberPicker2.setFormatter(new NumberPicker.Formatter() {
                        @Override
                        public String format(int value) {
                            return b.numberPicker.getValue()+"-"+excelcolumn(value);
                        }
                    });
                }
                else
                    b.numberPicker2.setFormatter(new NumberPicker.Formatter() {
                        @Override
                        public String format(int value) {
                            return excelcolumn(value);
                        }
                    });
            }
        });

        b.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                b.numberPicker2.setValue(newVal);
            }
        });

        b.numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                b.numberPicker.setValue(newVal );
            }
        });
    }

    private String excelcolumn(int input) {
        StringBuilder b=new StringBuilder();
        int rem;
        int mod;
        if(input<=26) {
            if (input == 0)
                System.out.println("please enter value greater than 0");
            else {
                char c= (char) (64+input);
                b.append(c);
            }
        }
        else {
            rem = input % 26;
            mod = input / 26;

            char cb='Z';
            if(mod<=26)
            {
                char ca;
                ca = (char) (mod + 64);
                b.append(ca);
            }
            else {
                for (int i = mod; i>26; i=i/26) {
                    b.append("Z");
                }
                char temp= (char) (mod/26+64);
                b.append(temp);
            }
            if(rem!=0) {
                cb = (char) (rem + 64);
            }
            b.append(cb);
        }

        return b.toString();
    }
}