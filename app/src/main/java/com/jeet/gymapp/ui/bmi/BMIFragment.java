package com.jeet.gymapp.ui.bmi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.jeet.gymapp.R;

public class BMIFragment extends Fragment /*implements AdapterView.OnItemSelectedListener */{


    EditText height,weight,age;
    TextView tv,tr;
    Spinner gender;
    Button btnbmi;
    String[] users = { "Male", "Female" };
    String genderstr;
    Double bmi;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bmi, container, false);


        //MAIN CODE
        /*
        final LayoutInflater factory = getLayoutInflater();
        final View fragRef = factory.inflate(R.layout.fragment_bmi,null);
        */
        height = root.findViewById(R.id.height);
        weight = root.findViewById(R.id.weight);
        age = root.findViewById(R.id.age);
        btnbmi = root.findViewById(R.id.btnbmi);
        tv = root.findViewById(R.id.bmival);
        tr = root.findViewById(R.id.bmirating);

        gender = root.findViewById(R.id.gender);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderstr = users[i];            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Log.d("DEBUG","CLICKED");
        btnbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","CLICKED");
                if(height.getText()==null || weight.getText()==null || age.getText()==null || genderstr==null){
                    Toast.makeText(getActivity().getApplicationContext(), "All fields must be filled", Toast.LENGTH_LONG).show();
                }else {
                    int ag, gen;
                    double hei, wei;
                    if (genderstr.equals("Male")) {
                        gen = 1;
                    } else if (genderstr.equals("Female")) {
                        gen = 2;
                    }

                    String prheight = height.getText().toString();
                    String prweight = weight.getText().toString();
                    String prage = age.getText().toString();
                    if(prheight.equals("")||prweight.equals("")||prage.equals("")){
                        Toast.makeText(getActivity().getApplicationContext(), "All fields must be filled", Toast.LENGTH_LONG).show();
                    }else{
                        hei = Double.parseDouble(prheight);
                        wei = Double.parseDouble(prweight);
                        hei = hei / 100;
                        ag = Integer.parseInt(prage);

                        bmi = (wei / ((hei * hei)));

                        String rate = "";
                        if(bmi<16)
                            rate = "Underweight";
                        else if(bmi < 25)
                            rate = "Healthy";
                        else if(bmi < 30)
                            rate = "Overweight";
                        else
                            rate = "Obese";

                        tv.setText("BMI Score : " + bmi.toString().subSequence(0,4));
                        tr.setText("Category : "+rate);
//                        Toast.makeText(getActivity().getApplicationContext(), "Height - "+hei+" Weight- "+wei+"\nBMI is " + bmi +"Gen "+genderstr, Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


    return root;
    }
    /*
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}