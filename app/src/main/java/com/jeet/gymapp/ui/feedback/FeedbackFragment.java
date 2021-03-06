package com.jeet.gymapp.ui.feedback;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jeet.gymapp.R;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);

        Button b;
        //EditText title,desc;
        //RatingBar r;

        b = root.findViewById(R.id.btnsubmit);
        final EditText title = root.findViewById(R.id.oex);
        final EditText desc = root.findViewById(R.id.desc);
        final RatingBar r = root.findViewById(R.id.ratingBar);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> data = new HashMap<>();
                data.put("title",title.getText().toString());
                data.put("description",desc.getText().toString());
                data.put("rating",r.getRating());
                db.collection("ratings")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Firestore","Data Saved Successfully");
                                Toast.makeText(getActivity().getApplicationContext(),"Feedback Sent Successfully",Toast.LENGTH_LONG).show();
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                fm.popBackStack();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Firestore","Failed to save Data");
                                Toast.makeText(getActivity().getApplicationContext(),"Failed to send Feedback",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        return root;
    }
}