package com.example.guruapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import java.util.List;

import Database.DBHelper;
import Model.ExamMarkDTO;


public class new_activity_search_update_marks extends Fragment {

    EditText txtExamId,txtStudentId,txtupdateExamId,updateStudentId,updateExamMarks,id;
    Button btnSearch,btnDeleteG,btnUpdateG;
    DBHelper db;
    Spinner spinner;
    String examCenterValue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_new_activity_search_update_marks, container, false);

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
        btnDeleteG = v.findViewById(R.id.btnDeleteG);
        btnUpdateG = v.findViewById(R.id.btnUpdateG);


        id = v.findViewById(R.id.Id);
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

                if(studentId.length()!=0 && examId.length()!=0){
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
                        searchValues.setMarkId(e.getMarkId());
                    }

                    txtupdateExamId.setText(searchValues.getExam_ID());
                    updateStudentId.setText(searchValues.getStudent_Id());
                    double maks = searchValues.getStudent_Marks();
                    updateExamMarks.setText(" "+maks);
                    id.setText(" "+searchValues.getMarkId());
                }else{

                    StyleableToast.makeText(getActivity(), "Input Fields Are Empty",R.style.mytoastSuccess).show();


                }





            }
        });


        btnUpdateG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });


       btnDeleteG.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String deleteId= id.getText().toString();

               int result = db.deleteMarks(deleteId);

               if(result>0){

                   StyleableToast.makeText(getActivity(), "Deleted  SuccessFully",R.style.mytoastSuccess).show();

                   id.setText(" ");
                   txtupdateExamId.setText(" ");
                   updateStudentId.setText(" ");
                   updateExamMarks.setText(" ");



               }else{

                   StyleableToast.makeText(getActivity(), "Deleted  Fail",R.style.mytoastSuccess).show();

               }

           }
       });

        final EditText idTextObject,updateStudentIdObject,updateExamIdObject,updateExamMarksObject;

        idTextObject = v.findViewById(R.id.Id);
        updateStudentIdObject = v.findViewById(R.id.updateStudentId);
        updateExamIdObject = v.findViewById(R.id.updateExamId);
        updateExamMarksObject = v.findViewById(R.id.updateExamMarks);


       btnUpdateG.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              String updateId = idTextObject.getText().toString();
              String updateStudentIds = updateStudentIdObject.getText().toString();
              String updateExamId = updateExamIdObject.getText().toString();
              String updateExamMarkss = updateExamMarksObject.getText().toString();






              if(updateId.length() != 0){
                  if(updateStudentIds.length() !=0){
                      if(updateExamId.length() !=0){
                          if(updateExamMarkss.length() !=0){

                              try{


                                  ExamMarkDTO e = new ExamMarkDTO();
                                  e.setMarkId(Integer.parseInt(updateId.trim()));
                                  e.setStudent_Id(updateStudentIds);
                                  e.setExam_ID(updateExamId);
                                  e.setStudent_Marks(Double.parseDouble(updateExamMarkss));


                                  boolean i = db.updateMarks(e);

                                  if(i){

                                      StyleableToast.makeText(getActivity(), "Updated  SuccessFully",R.style.mytoastSuccess).show();

                                      id.setText(" ");
                                      txtupdateExamId.setText(" ");
                                      updateStudentId.setText(" ");
                                      updateExamMarks.setText(" ");


                                  }else{

                                      StyleableToast.makeText(getActivity(), "Deleted  Fail",R.style.mytoastSuccess).show();

                                  }

                              }catch (InputMismatchException s){

                                  StyleableToast.makeText(getActivity(), "Mark Is Invalid",R.style.mytoast).show();

                              }catch (NumberFormatException s){

                                  StyleableToast.makeText(getActivity(), "Marks Is Invalid,It is Not A Number",R.style.mytoast).show();

                              }


                          }else{

                              StyleableToast.makeText(getActivity(), "Student Marks Field Is Empty",R.style.mytoast).show();

                          }

                      }else{

                          StyleableToast.makeText(getActivity(), "Exam Id Field Is Empty",R.style.mytoast).show();

                      }
                  }else{

                      StyleableToast.makeText(getActivity(), "Student Id Field Is Empty",R.style.mytoast).show();

                  }
              }else{

                  StyleableToast.makeText(getActivity(), "Id Field Is Empty",R.style.mytoast).show();

              }




           }
       });
        return v;






    }

//inflater.inflate(R.layout.fragment_new_activity_search_update_marks, container, false);
}




