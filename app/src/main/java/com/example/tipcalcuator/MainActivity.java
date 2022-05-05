package com.example.tipcalcuator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener
{
    private double billAmount;
    private double percent;

    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

   private EditText amountEditText;
    private SeekBar percentSeekBar;

    private static  NumberFormat currencyFormat;
    private static  NumberFormat percentFormat;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init()
    {
        billAmount = 0.0;
        percent = 0.15;

        currencyFormat = NumberFormat.getCurrencyInstance();
        percentFormat = NumberFormat.getPercentInstance();

        amountTextView = findViewById(R.id.amountTextView);
        percentTextView =  findViewById(R.id.percentTextView);
        tipTextView =  findViewById(R.id.tipTextView);
        totalTextView =  findViewById(R.id.totalTextView);

        amountEditText =  findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(this);


        percentSeekBar =  findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(amountTextView.getWindowToken(), 0);
    }



    private void calculate()
    {
        try{
            billAmount = Double.parseDouble(amountEditText.getText().toString());
        }
        catch (NumberFormatException e){
            billAmount =0.0;
        }


        double tip=billAmount*percent;
        double total = billAmount +tip;

        //tipTextView.setText(String.valueOf(tip));
        tipTextView.setText(currencyFormat.format(tip));
        //totalTextView.setText(String.valueOf(total));
        totalTextView.setText(currencyFormat.format(total));
    }
    //**** methods from TextWatcher 3 functions .....
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {



    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
        amountTextView.setText( currencyFormat.format(billAmount) );
       // amountTextView.setText(amountEditText.getText().toString());

        calculate();
    }

    @Override
    public void afterTextChanged(Editable editable)
    {

    }



    //**** methods from SeekBar 3 methods ....
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

        percent = progress / 100.0;
       // percentTextView.setText(String.valueOf(percent));
        percentTextView.setText(percentFormat.format(percent));
        calculate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {


    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        hideKeyboard();
    }
}