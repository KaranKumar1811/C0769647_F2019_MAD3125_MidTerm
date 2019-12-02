package com.example.c0769647_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;

import android.text.InputType;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText sin,firstName,lastName,dob,grossIncome,rrsp;
    RadioGroup genderRG;
    Button calTaxBtn;
    String gender;
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sin=findViewById(R.id.edtSin);
        firstName=findViewById(R.id.edtfirstName);
        lastName=findViewById(R.id.edtLastName);
        dob=findViewById(R.id.edtdob);
        grossIncome=findViewById(R.id.edtgross);
        rrsp=findViewById(R.id.edtrrsp);
        genderRG=findViewById(R.id.genderRG);
        calTaxBtn=findViewById(R.id.btnCalculate);


//
//        String strsin = sin.getText().toString();
//
//        if(TextUtils.) {
//            sin.setError("Your message");
//            return;
//        }


        final Calendar myCalendar = Calendar.getInstance();


        dob.setInputType(InputType.TYPE_NULL);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);



                SimpleDateFormat sm=new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

                String dobtext=sm.format(myCalendar.getTime());




                dob.setText(dobtext);
                int curYear=Calendar.getInstance().get(Calendar.YEAR);
                //System.out.println(curYear);
                age=curYear-year;
                //System.out.println(age);
            }

        };

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });








        calTaxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (genderRG.getCheckedRadioButtonId()){
                    case R.id.maleRb:
                    {
                        gender="Male";
                        break;
                    }
                    case R.id.femaleRb:{
                        gender="Female";
                        break;
                    }
                    case R.id.othersRb:{
                        gender="Others";
                        break;
                    }

                }



                if(sin.getText().toString().length() != 11 ||
                        firstName.getText().toString().length()==0 ||
                        lastName.getText().toString().length()==0 ||
                        dob.getText().toString().length()==0 ||
                        grossIncome.getText().toString().length()==0 ||
                        rrsp.getText().toString().length()==0 ||
                        age<18

                )
                {
//                    sin.setError("Sin Incorrect");
//                    firstName.setError("This Field Cannot Be Empty");
//                    lastName.setError("This Field Cannot Be Empty");
//                    dob.setError("This Field Cannot Be Empty");
//                    grossIncome.setError("This Field Cannot Be Empty");
//                    rrsp.setError("This Field Cannot Be Empty");
                    if (sin.getText().toString().length() != 11) {
                        sin.setError("Sin Incorrect");

                    }
                    if (firstName.getText().toString().length() == 0) {
                        firstName.setError("This Field Cannot Be Empty");

                    }
                    if (lastName.getText().toString().length() == 0) {
                        lastName.setError("This Field Cannot Be Empty");

                    }
                    if (dob.getText().toString().length() == 0) {
                        dob.setError("This Field Cannot Be Empty");

                    }
                    if (age < 18) {
                        dob.setError("Not Eligible For filing tax");

                    }

                    if (grossIncome.getText().toString().length() == 0) {
                        grossIncome.setError("This Field Cannot Be Empty");

                    }
                    if (rrsp.getText().toString().length() == 0) {
                        rrsp.setError("This Field Cannot Be Empty");
                    }

                }
                else
                    {
                        System.out.println("Age"+age);

                    Intent i = new Intent(MainActivity.this, DisplayDataActivity.class);
                    CRACustomer cDetail = new CRACustomer();
                    cDetail.setSinNumber(sin.getText().toString());
                    cDetail.setFirstName(firstName.getText().toString());
                    cDetail.setLastName(lastName.getText().toString());
                    cDetail.setDateOfBirth(dob.getText().toString());
                    cDetail.setGrossIncome(Double.parseDouble(grossIncome.getText().toString()));
                    cDetail.setRrsp(Double.parseDouble((rrsp.getText().toString())));
                    cDetail.setGender(gender);
                    cDetail.setAge(Integer.toString(age));


                    i.putExtra("data", cDetail);

                    startActivity(i);
                }

            }
        });




    }
}