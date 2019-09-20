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

import java.util.List;

import Database.DBHandlerFeeManagement;
import Model.FeeDTO;

public class SearchFeeDetails extends Fragment {
    EditText studentID, searchMonth, searchYear, UpStName,UpMonth,UpAmount,UpYear,id;
    Button btnSearch, btnDelete,btnUpdate;
    DBHandlerFeeManagement db;
    Spinner spinner2;
    String spinnerType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.search_fee_details,container,false);
        db = new DBHandlerFeeManagement(getActivity());
        studentID = v.findViewById(R.id.studentId);
        searchMonth = v.findViewById(R.id.month);
        searchYear = v.findViewById(R.id.year);

        UpStName = v.findViewById(R.id.updateName);
        UpMonth = v.findViewById(R.id.updateMonth);
        UpAmount = v.findViewById(R.id.updateAmount);
        UpYear = v.findViewById(R.id.updateYear);
        //UpType = v.findViewById(R.id.updateSpinner);

        String [] values =
                {"Full","Half","Free",};
        Spinner spinner = (Spinner) v.findViewById(R.id.updateSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner2 = v.findViewById(R.id.updateSpinner);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSearch = v.findViewById(R.id.btnSearch);
        btnUpdate = v.findViewById(R.id.btnUpdate);
        btnDelete = v.findViewById(R.id.btnDelete);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentid = studentID.getText().toString();
                String month = searchMonth.getText().toString();
                String year = searchYear.getText().toString();

                List<FeeDTO> l1 = db.searchFeeDetails(studentid,month,year);

                if(l1 ==null){
                    Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
                }

                FeeDTO SearchValues = new FeeDTO();
                for (FeeDTO e: l1){

                    SearchValues.setStudentId(e.getStudentId());
                    SearchValues.setStudentName(e.getStudentName());
                    SearchValues.setMonth(e.getMonth());
                    SearchValues.setAmount(e.getAmount());
                    SearchValues.setType(e.getType());
                }


                UpStName.setText(SearchValues.getStudentName());
                int y = SearchValues.getYear();
                UpYear.setText(" " + y );
                UpMonth.setText(SearchValues.getMonth());
                double amount = SearchValues.getAmount();
                UpAmount.setText(" " + amount);
//                UpType.setText(SearchValues.getType());


                String []values=new String[3];
                System.out.println("searchValues.getStudent_Center()"+SearchValues.getType());
                if(SearchValues.getType()==null){
                    values[0]="Full";
                    values[1]="Half";
                    values[2]="Free";
                }else{

                    if(SearchValues.getType().equals("Full")){

                        values[0]="Full";
                        values[1]="Half";
                        values[2]="Free";
                    }

                    if(SearchValues.getType().equals("Half")){

                        values[0]="Half";
                        values[1]="Full";
                        values[2]="Free";
                    }

                    if(SearchValues.getType().equals("Free")){

                        values[0]="Free";
                        values[1]="Full";
                        values[2]="Half";
                    }



                }



                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, values);
                adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner2.setAdapter(adapter1);
            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String UpdateStName = UpStName.getText().toString();
                    String UpdateYear = UpYear.getText().toString();
                    String UpdateMonth = UpMonth.getText().toString();
                    String UpdateAmount = UpMonth.getText().toString();

                    FeeDTO f = new FeeDTO();
                    f.setStudentName(UpdateStName);
                    f.setYear(Integer.parseInt(UpdateYear));
                    f.setMonth(UpdateMonth);
                    f.setAmount(Double.parseDouble(UpdateAmount));
                    f.setType(spinnerType);

                    boolean status =  db.updateFeeDetails(f);

                    if(status){
                        Toast.makeText(getActivity(),"Update Success",Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getActivity(),"Update Fail",Toast.LENGTH_LONG).show();


                    }

                }

        });




//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String deleteId =
//                int result = db.deleteFeeDetails(studentID.getText().toString());
//
//                if(result == 1){
//                    Toast.makeText(getActivity(),"Deleting Success",Toast.LENGTH_LONG).show();
//
//                }else{
//                    Toast.makeText(getActivity(),"Deleting  false",Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//        });


        return v;


    }
}
