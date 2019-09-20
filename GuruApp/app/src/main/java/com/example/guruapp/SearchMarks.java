package com.example.guruapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

import Database.DBHelper;
import Model.ExamMarkDTO;

public class SearchMarks extends Fragment {


    EditText txtExamId,txtStudentId,txtupdateExamId,updateStudentId,updateExamMarks;
    Button btnSearch;
    DBHelper db;
    Spinner spinner;
    String examCenterValue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search_marks,container,false);

        String [] values =
                {"Galle","Mathara","Hambanthota",};
        Spinner spinner = (Spinner) v.findViewById(R.id.updateCenterSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        db = new DBHelper(getActivity());
        txtExamId = v.findViewById(R.id.searchExamId);
        txtStudentId = v.findViewById(R.id.searchStudentId);
        btnSearch = v.findViewById(R.id.btnSearch);
        txtupdateExamId = v.findViewById(R.id.updateExamId);
       updateStudentId = v.findViewById(R.id.updateStudentId);
        updateExamMarks = v.findViewById(R.id.updateExamMarks);


        spinner = v.findViewById(R.id.updateCenterSpinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                examCenterValue = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentId = txtStudentId.getText().toString();
                String examId = txtExamId.getText().toString();

               List<ExamMarkDTO> d1 = db.searchMarksDetails(examId,studentId);

                System.out.println("Null"+d1);
               if(d1.size()==0){

                   StyleableToast.makeText(getActivity(), "Marks Not Found ",R.style.mytoast).show();

               }
               ExamMarkDTO searchValues = new ExamMarkDTO();
                for (ExamMarkDTO e: d1
                     ) {

                    searchValues.setExam_ID(e.getExam_ID());
                    searchValues.setStudent_Marks(e.getStudent_Marks());
                    searchValues.setStudent_Center(e.getStudent_Center());
                    searchValues.setStudent_Id(e.getStudent_Id());
                }

                txtupdateExamId.setText(searchValues.getExam_ID());
                updateStudentId.setText(searchValues.getStudent_Id());
                double maks = searchValues.getStudent_Marks();
                updateExamMarks.setText(" "+maks);


            }
        });










        return v;
    }


}
