package com.example.guruapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoast.StyleableToast;

import Database.DBHandlerFeeManagement;
import Model.ExamMarkDTO;
import Model.FeeDTO;

public class AddFeeDetails extends Fragment {

//        //toast message-------------------------------
//
//            super.onCreate(savedInstanceState);
//
//            Context context =  getApplicationContext();
//            CharSequence text = "Hello toast!";
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, text,
//                    duration); toast.show();

        EditText userID,userName, month,amount,year;
        Spinner spinner1;
        String userIDUI, userNameUI,monthUI,amountUI,yearUI,type;
        DBHandlerFeeManagement db;

        Button btnSave;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.add_fee_details,container,false);
        userID = v.findViewById(R.id.editText);
        userName = v.findViewById(R.id.editText11);
        year = v.findViewById(R.id.editText1);
        month = v.findViewById(R.id.editText3);
        amount = v.findViewById(R.id.updateAmount);
        spinner1 = v.findViewById(R.id.addSpinner);
        db = new DBHandlerFeeManagement(this.getActivity());

        String [] values =
                {"Full","Half","Free",};
        Spinner spinner = (Spinner) v.findViewById(R.id.addSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);




//        btnSave = v.findViewById(R.id.btnSave)

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSave = v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userIDUI = userID.getText().toString();
                userNameUI = userName.getText().toString();

                monthUI = month.getText().toString();
                amountUI = amount.getText().toString();
                yearUI = year.getText().toString();


            if(userID.length() != 0) {
                if (userNameUI.length() != 0) {
                    if (yearUI.length() != 0) {
                        if (yearUI.length() == 4) {
                            if (monthUI.length() != 0) {
                                if (amountUI.length() != 0) {
                                    if (type.length() != 0) {

                                        FeeDTO dto = new FeeDTO();
                                        dto.setStudentId(userIDUI);
                                        dto.setStudentName(userNameUI);
                                        dto.setYear(Integer.parseInt(yearUI));
                                        dto.setAmount(Double.parseDouble(amountUI));
                                        dto.setMonth(monthUI);
                                        dto.setType(type);

                                        boolean f = db.SaveFeeDetails(dto);
                                        if (f) {
                                            StyleableToast.makeText(getActivity(), " Added SuccessFully", R.style.mytoastSuccess).show();
                                        } else {
                                            StyleableToast.makeText(getActivity(), "Added Fail", R.style.mytoast).show();
                                        }
                                    } else {
                                        StyleableToast.makeText(getActivity(), "Empty Type", R.style.mytoast).show();
                                    }
                                } else {
                                    StyleableToast.makeText(getActivity(), "Empty Amount", R.style.mytoast).show();

                if(userNameUI.length() != 0){
                    if(userNameUI.length() != 0){
                        if(monthUI.length() != 0){
                            if(amountUI.length() != 0){

                                FeeDTO dto = new FeeDTO();
                                dto.setStudentId(userIDUI);
                                dto.setStudentName(userNameUI);
                                dto.setYear(Integer.parseInt(yearUI));
                                dto.setAmount(Double.parseDouble(amountUI));
                                dto.setMonth(monthUI);
                                dto.setType(type);

                                boolean f =db.SaveFeeDetails(dto);
                                if(f){
                                    StyleableToast.makeText(getActivity(), " Added SuccessFully",R.style.mytoastSuccess).show();

                                }else{

                                    StyleableToast.makeText(getActivity(), "Added Fail",R.style.mytoast).show();

                                }
                            } else {
                                StyleableToast.makeText(getActivity(), "Empty Month", R.style.mytoast).show();
                            }
                        } else {
                            StyleableToast.makeText(getActivity(), "Please enter a valid Year", R.style.mytoast).show();
                        }
                    } else {
                        StyleableToast.makeText(getActivity(), "Empty Year", R.style.mytoast).show();
                    }
                } else {
                    StyleableToast.makeText(getActivity(), "Empty Name", R.style.mytoast).show();
                }
            }else {
                StyleableToast.makeText(getActivity(), "Empty StudentID", R.style.mytoast).show();
            }
            }
            });




        return v;


        }
    }


