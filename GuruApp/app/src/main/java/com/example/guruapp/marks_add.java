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

import java.util.InputMismatchException;

import Database.DBHelper;
import Model.ExamMarkDTO;

public class marks_add extends Fragment {

    EditText txtExamId,txtStudentId,txtMarks;
    Spinner  spinnerValue;
    Button savebtn;
    View v;
    DBHelper db;
    String examCenterValue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




         v = inflater.inflate(R.layout.activity_marks_add,container,false);
         savebtn =v.findViewById(R.id.btnSaveG);
         txtExamId = v.findViewById(R.id.searchExamId);
         txtMarks = v.findViewById(R.id.txtExamMarksG);
         txtStudentId = v.findViewById(R.id.searchStudentId);
         spinnerValue = v.findViewById(R.id.txtExamCenterG);
         db = new DBHelper(this.getActivity());


        ////////////////////////////////////////////////////////

        String [] values =
                {"Galle","Mathara","Hambanthota",};
        Spinner spinner = (Spinner) v.findViewById(R.id.txtExamCenterG);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                examCenterValue = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //////////////////////////////////////////////////////////////////////////////////


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String examIdValues = txtExamId.getText().toString();
                String examMarksValues = txtMarks.getText().toString();
                String examStudentIdMarks = txtStudentId.getText().toString();

                if(examIdValues.length() != 0){

                    if(examMarksValues.length() != 0){

                        if(examStudentIdMarks.length() != 0){

                            try{


                                ExamMarkDTO dto = new ExamMarkDTO();

                                dto.setExam_ID(examIdValues);
                                dto.setStudent_Marks(Double.parseDouble(examMarksValues));
                                dto.setStudent_Center(examCenterValue);
                                dto.setStudent_Id(examStudentIdMarks);


                                boolean i =db.SaveMarkDetails(dto);

                                if(i){

                                    StyleableToast.makeText(getActivity(), "Marks Added SuccessFully",R.style.mytoastSuccess).show();

                                    txtExamId.setText(" ");
                                    txtMarks.setText(" ");
                                    txtStudentId.setText(" ");

                                }else{

                                    StyleableToast.makeText(getActivity(), "Marks Added Fail",R.style.mytoast).show();

                                }

                            }catch (InputMismatchException s){

                                StyleableToast.makeText(getActivity(), "Mark Is Invalid",R.style.mytoast).show();

                            }catch (NumberFormatException s){

                                StyleableToast.makeText(getActivity(), "Marks Is Invalid,It is Not A Number",R.style.mytoast).show();

                            }

                        }else{

                            StyleableToast.makeText(getActivity(), "Student Id Field Is Empty",R.style.mytoast).show();

                        }

                    }else{

                        StyleableToast.makeText(getActivity(), "Marks Field Is Empty",R.style.mytoast).show();

                    }
                }else{

                    StyleableToast.makeText(getActivity(), "Exam ID Field Is Empty",R.style.mytoast).show();


                }



            }
        });




        return v;


    }









}
