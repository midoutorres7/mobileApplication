package com.playstore.sqlite1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectFragment newInstance(String param1, String param2) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Spinner spnPatient , spnMed;
    Button btnTest;
    dbContact db;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_select_med_pat, container, false);
/*

        spnPatient = (Spinner) v.findViewById(R.id.spinPatient);
        spnMed = (Spinner) v.findViewById(R.id.spinMed);

        db = new dbContact(getContext());

        bundle = new Bundle();

////////////////////////////////Patient///////////////////////////////////////////////////////
        ArrayList<Contact> values = db.getAllContacts();
        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(getContext() , android.R.layout.simple_spinner_dropdown_item , values);
        //SpinnerAdapterPatient adapter = new SpinnerAdapterPatient(this , R.layout.item_design_spinner , values);
        spnPatient.setAdapter(adapter);
        spnPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) parent.getItemAtPosition(position);
                bundle.putInt("patientId" , selectedContact.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/////////////////////////////Medicaments/////////////////////////////////////////////////////
        ArrayList<Medicam> medicams = db.getAllMedicaments();
        ArrayAdapter<Medicam> MedAdapter = new ArrayAdapter<>(getContext() , android.R.layout.simple_spinner_dropdown_item , medicams);
        //MedicamAdapter MedAdapter = new MedicamAdapter(this , R.layout.item_design2 , medicams);
        spnMed.setAdapter(MedAdapter);
        spnMed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Medicam medicam = (Medicam) parent.getItemAtPosition(position);
                bundle.putInt("medId" , medicam.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnTest = (Button) v.findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , Calculate.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


*/
        return v;
    }
}