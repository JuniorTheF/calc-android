package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.math.BigDecimal;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button one = findViewById(R.id.btn1);
        one.setOnClickListener(this);
        Button two = findViewById(R.id.btn2);
        two.setOnClickListener(this);
        Button three = findViewById(R.id.btn3);
        three.setOnClickListener(this);
        Button four = findViewById(R.id.btn4);
        four.setOnClickListener(this);
        Button five = findViewById(R.id.btn5);
        five.setOnClickListener(this);
        Button six = findViewById(R.id.btn6);
        six.setOnClickListener(this);
        Button seven = findViewById(R.id.btn7);
        seven.setOnClickListener(this);
        Button eight = findViewById(R.id.btn8);
        eight.setOnClickListener(this);
        Button nine = findViewById(R.id.btn9);
        nine.setOnClickListener(this);
        Button zero = findViewById(R.id.btn0);
        zero.setOnClickListener(this);
        Button plus = findViewById(R.id.btnplus);
        plus.setOnClickListener(this);
        Button minus = findViewById(R.id.btnminus);
        minus.setOnClickListener(this);
        Button divide = findViewById(R.id.btndivide);
        divide.setOnClickListener(this);
        Button multiply = findViewById(R.id.btnmultiply);
        multiply.setOnClickListener(this);
        Button percent = findViewById(R.id.btnpercent);
        percent.setOnClickListener(this);
        Button del = findViewById(R.id.btndel);
        del.setOnClickListener(this);
        Button AC = findViewById(R.id.btnAC);
        AC.setOnClickListener(this);
        Button equals = findViewById(R.id.btnequals);
        equals.setOnClickListener(this);
        Button dot = findViewById(R.id.btndot);
        dot.setOnClickListener(this);
        ImageView git = findViewById(R.id.btnneg);
        git.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JuniorTheF"))));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        String flag = "";
        TextView textView = findViewById(R.id.textView);
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        switch (view.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                textView.append(buttonText);
                break;
            case R.id.btnplus:
            case R.id.btnminus:
            case R.id.btnmultiply:
            case R.id.btndivide:
            case R.id.btndot:
            case R.id.btnpercent:
                flag = "operation";
                sanityCheck(flag, view);
                break;
            case R.id.btndel:
                if (textView.getText().toString().length() != 0) {
                    textView.setText(textView.getText().toString().substring(0, textView.getText().toString().length()-1));
                }
                break;
            case R.id.btnAC:
                if (textView.getText().toString().length() != 0) {
                    textView.setText(getString(R.string._text));
                }
                break;
            case R.id.btnequals:
                flag = "equals";
                sanityCheck(flag, view);
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sanityCheck(String event, View view) {
        TextView textView = findViewById(R.id.textView);
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        if (event.equals("equals")) {
            if ((textView.getText().toString().contains("-") || textView.getText().toString().contains("+") || textView.getText().toString().contains("*") || textView.getText().toString().contains("/") || textView.getText().toString().contains("^")) && (!textView.getText().toString().endsWith("-") && !textView.getText().toString().endsWith("+") && !textView.getText().toString().endsWith("*") && !textView.getText().toString().endsWith("/") && !textView.getText().toString().endsWith("^"))) {
            solve(createNumbers());
        }
            else {
                textView.setText("incorrect equation");
            }
        }
        else {
           if ((textView.getText().toString().endsWith("-") || textView.getText().toString().endsWith("+") || textView.getText().toString().endsWith("*") || textView.getText().toString().endsWith("/") || textView.getText().toString().endsWith(".") || textView.getText().toString().endsWith("^"))) {
               textView.setText(textView.getText().toString().substring(0, textView.getText().toString().length()-1) + buttonText);
           }
           else {
               textView.append(buttonText);
           }
        }

    }

    public ArrayList<Object> createNumbers() {
        TextView textView = findViewById(R.id.textView);
        String temp = textView.getText().toString();
        ArrayList<Double> digits = new ArrayList<>();
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        double intnumber = 0;
        ArrayList<Object> equation = new ArrayList<>();
        for (char k : letters.toCharArray()) {
            if (temp.contains(String.valueOf(k))) {
                equation.add("char error");
                return equation;
            }
        }
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '-' && temp.charAt(i) != '+' && temp.charAt(i) != '*' && temp.charAt(i) != '/' && temp.charAt(i) != '^' && temp.charAt(i) != '.') {
                System.out.println(temp);
                digits.add(Double.parseDouble(String.valueOf(temp.charAt(i))));
                        if (i + 1 == temp.length()) {
                            Double[] ardigits = digits.toArray(new Double[0]);
                            System.out.println("digits" + Arrays.toString(ardigits));
                            if (ardigits[0] == 0 && ardigits.length > 1) {
                                int counter = 0;
                                while (ardigits[counter] == 0) {
                                    counter+=1;
                                }
                                for (int q = 0; q < ardigits.length; q++) {
                                    ardigits[q] = ardigits[q] * Math.pow(0.1, counter);
                                }
                            }
                            for (int k = 0; k < ardigits.length; k++) {
                                intnumber = intnumber * 10 + ardigits[k];
                            }
                                equation.add(intnumber);
                                intnumber = 0;
                                digits.clear();
                        }
                    }
            else {
                Double[] ardigits = digits.toArray(new Double[0]);
                System.out.println("digits" + Arrays.toString(ardigits));
                if (ardigits.length > 1 && ardigits[0] == 0) {
                    int counter = 0;
                    while (ardigits[counter] == 0) {
                        counter+=1;
                    }
                    for (int q = 0; q < ardigits.length; q++) {
                        ardigits[q] = ardigits[q] * Math.pow(0.1, counter);
                    }
                }
                for (int p = 0; p < ardigits.length; p++) {
                    intnumber = intnumber * 10 + ardigits[p];
                }
                equation.add(intnumber);
                intnumber = 0;
                digits.clear();
                if (temp.charAt(i) == '+' || temp.charAt(i) == '-' || temp.charAt(i) == '*' ||temp.charAt(i) == '/' || temp.charAt(i) == '^' || temp.charAt(i) == '.') {
                    equation.add(temp.charAt(i));
                }
        }
    }
        return equation;
}

@RequiresApi(api = Build.VERSION_CODES.N)
public void solve(ArrayList<Object> numbers) {
        TextView textView = findViewById(R.id.textView);
        System.out.println("eq " + numbers);
        if (numbers.contains("char error")) {
            textView.setText("char error");
            return;
        }
        while (numbers.contains('.')) {
            int dot = numbers.indexOf('.');
//            double number = (((double) numbers.get(dot-1) + Math.pow(0.1, numbers.get(dot+1).toString().length()) * (double) numbers.get(dot+1)));
            double number = (((double) numbers.get(dot-1) + (double) numbers.get(dot+1)*0.1));
            numbers.set(dot-1, number);
            numbers.remove(dot);
            numbers.remove(dot);
        }
        System.out.println("dot " + numbers);

        while (numbers.contains('^')) {
            int pow = numbers.indexOf('^');
            BigDecimal number = BigDecimal.valueOf(Math.pow(Double.parseDouble(numbers.get(pow-1).toString()), Double.parseDouble(numbers.get(pow+1).toString())));
            numbers.set(pow-1, number);
            numbers.remove(pow);
            numbers.remove(pow);
        }
        System.out.println("pow " + numbers);

    while (numbers.contains('*') || numbers.contains('/')) {
        System.out.println("muldiv1 " + numbers);
        int mult = numbers.indexOf('*');
        int div = numbers.indexOf('/');
        if (div == -1 || (mult < div && mult != -1)) {
            BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numbers.get(mult-1).toString()) * (Double.parseDouble(numbers.get(mult+1).toString())));
            numbers.set(mult-1, number);
            numbers.remove(mult);
            numbers.remove(mult);
        }
        else if (mult > div || mult == -1) {
            System.out.println("muldiv2 " + numbers);
            if (Double.parseDouble(numbers.get(div+1).toString()) != 0.0) {
                System.out.println("muldiv3 " + numbers);
                BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numbers.get(div-1).toString()) / (Double.parseDouble(numbers.get(div+1).toString())));
                numbers.set(div-1, number);
                numbers.remove(div);
                numbers.remove(div);
            }
            else {
                System.out.println("muldiv4 " + numbers);
                textView.setText("incorrect equation");
                return;
            }
        }
    }
    System.out.println("muldiv " + numbers);

    while (numbers.contains('+') || numbers.contains('-')) {
        int plus = numbers.indexOf('+');
        int minus = numbers.indexOf('-');
        if (plus == -1 || (minus < plus && minus != -1) ) {
            BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numbers.get(minus-1).toString()) - (Double.parseDouble(numbers.get(minus+1).toString())));
            numbers.set(minus-1, number);
            numbers.remove(minus);
            numbers.remove(minus);
        }
        else if (minus > plus || minus == -1) {
            BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numbers.get(plus-1).toString()) + (Double.parseDouble(numbers.get(plus+1).toString())));
            numbers.set(plus-1, number);
            numbers.remove(plus);
            numbers.remove(plus);
        }
    }
    System.out.println("minplus " + numbers);
    BigDecimal result = (BigDecimal) numbers.get(0);
    System.out.println(BigDecimal.valueOf(result.intValue()));
    int res = result.intValue();
    if (result.compareTo(BigDecimal.valueOf(res)) == 0) {
        int intresult = result.intValue();
        textView.setText(getString(R.string._text));
        textView.append(String.valueOf(intresult));
    }
    else {
        textView.setText(getString(R.string._text));
        textView.append(String.valueOf((result.floatValue())));
    }

}
}
