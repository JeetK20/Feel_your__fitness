package com.jeet.gymapp.ui.calories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.jeet.gymapp.R;

public class CaloriesFragment extends Fragment {

    EditText height,weight,age;
    TextView tv,tp,tc;
    Spinner gender,exerciselevel;
    String genderstr;
    String calstr;
    String[] users = { "Male", "Female" };
    String[] exer = { "None", "Very Quiet" , "Light" , "Moderate" , "Hard" , "Non Stop"};
    int exeind;
    Double calories,bmr;
    Button add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calories, container, false);

        height = root.findViewById(R.id.cheight);
        weight = root.findViewById(R.id.cweight);
        age = root.findViewById(R.id.cage);

        tv = root.findViewById(R.id.calval);
        tp = root.findViewById(R.id.txtprt);
        tc = root.findViewById(R.id.txtcrb);


        gender = root.findViewById(R.id.calgender);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderstr = users[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        exerciselevel = root.findViewById(R.id.exerciselevel);
        exerciselevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calstr = exer[i];
                exeind = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add = root.findViewById(R.id.btncal);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(height.getText().toString().equals("") || weight.getText().toString().equals("") || age.getText().toString().equals("") || genderstr.equals("") || calstr.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "All fields must be filled", Toast.LENGTH_LONG).show();
                }else {
                    if(genderstr.equals("Male")){
                        bmr = 66 + (13.7*Double.parseDouble(weight.getText().toString())) + (5*Double.parseDouble(height.getText().toString())) - (6.8*Double.parseDouble(age.getText().toString()));
                    }else{
                        bmr = 655 + (9.6*Double.parseDouble(weight.getText().toString())) + (1.8*Double.parseDouble(height.getText().toString())) - (4.7*Double.parseDouble(age.getText().toString()));
                    }
                    switch (exeind){
                        case 0:
                            calories = bmr;
                            break;
                        case 1:
                            calories = bmr * 1.2;
                            break;
                        case 2:
                            calories = bmr * 1.375;
                            break;
                        case 3:
                            calories = bmr * 1.55;
                            break;
                        case 4:
                            calories = bmr * 1.725;
                            break;
                        case 5:
                            calories = bmr * 1.9;
                            break;
                    }
                }
                Double cp = calories*0.2;
                Double cb = calories*0.6;
                tv.setText("Daily Calories Intake : " + calories.toString().subSequence(0,4));
                tp.setText("Protein Intake: "+ cp.toString().subSequence(0,4));
                tc.setText("Carbohydrate Intake: "+ cb.toString().subSequence(0,4));
            }
        });
        return root;
    }
}