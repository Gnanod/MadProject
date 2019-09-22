package com.example.guruapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.InputMismatchException;

import Database.DBHelper1;
import Model.ExamMarkDTO;
import Model.StudentDTO;

public class AddStudents extends Fragment {
    EditText stuId,stuName,stuNic,stuPhone,stuEmail;
    Button buttonAdd;
    DBHelper1 db;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_student_details, container, false);


        stuId = v.findViewById(R.id.editText2);
        stuName = v.findViewById(R.id.name);
        stuNic = v.findViewById(R.id.editText6);
        stuPhone = v.findViewById(R.id.editText9);
        stuEmail = v.findViewById(R.id.editText14);


        buttonAdd = v.findViewById(R.id.button4);

        db = new DBHelper1(this.getActivity());

        buttonAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String studentId = stuId.getText().toString();
                String studentName = stuName.getText().toString();
                String studentNic = stuNic.getText().toString();
                String studentPhone = stuPhone.getText().toString();
                String studentEmail = stuEmail.getText().toString();


                if (studentId.length() != 0) {

                    if (studentName.length() != 0) {

                        if (studentNic.length() != 0 & studentNic.length() == 10) {

                            if (studentPhone.length() != 0 & studentPhone.length() == 10) {

                                if (studentEmail.length() != 0) {


                                    try {

                                        StudentDTO dto = new StudentDTO();

                                        dto.setStudentId(studentId);
                                        dto.setStudentName(studentName);
                                        dto.setStudentNic(studentNic);
                                        dto.setStudentPhone(studentPhone);
                                        dto.setStudentEmail(studentEmail);

                                        boolean res = db.addStudents(dto);
                                        if (res) {

                                            StyleableToast.makeText(getActivity(), "Student Added Successfully", R.style.mytoastSuccess).show();
                                            stuId.setText(" ");
                                            stuName.setText(" ");
                                            stuNic.setText(" ");
                                            stuPhone.setText(" ");
                                            stuEmail.setText(" ");


                                        } else {
                                            StyleableToast.makeText(getActivity(), "Student Added Fail", R.style.mytoast).show();
                                        }


                                    } catch (InputMismatchException s) {
                                        StyleableToast.makeText(getActivity(), "Invalid Inputs",  R.style.mytoast).show();

                                    }
                                } else {
                                    StyleableToast.makeText(getActivity(), "Email Field in Empty", R.style.mytoast).show();
                                }
                            } else {
                                StyleableToast.makeText(getActivity(), "Phone Number is Invalid", R.style.mytoast).show();
                            }

                        } else {
                            StyleableToast.makeText(getActivity(), "Student NIC is Invalid", R.style.mytoast).show();
                        }
                    } else {
                        StyleableToast.makeText(getActivity(), "Student  Name Field is Empty", R.style.mytoast).show();

                    }
                } else {
                    StyleableToast.makeText(getActivity(), "Student Id Field is Empty", R.style.mytoast).show();
                }
            }

        });




        return v;

    }

}

