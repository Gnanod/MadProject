package com.example.guruapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

import Database.DBHelper1;
import Model.StudentDTO;

public class UpdateStudent extends Fragment {

    EditText stuId,stuName,stuNic,stuPhone,stuEmail,id;
    Button btnSearch,btnUpdate,btnDelete;

    DBHelper1 db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_student_details,container,false);

        db = new DBHelper1(getActivity());

        stuId = v.findViewById(R.id.editText2);
        stuName = v.findViewById(R.id.name);
        stuNic = v.findViewById(R.id.editText7);
        stuPhone = v.findViewById(R.id.editText9);
        stuEmail = v.findViewById(R.id.editText14);
        id = v.findViewById(R.id.editText2);


        btnSearch = v.findViewById(R.id.button);
        btnUpdate = v.findViewById(R.id.button4);
        btnDelete = v.findViewById(R.id.button2);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = stuId.getText().toString();

                List<StudentDTO> Ld = db.searchStudent(id);

                if (Ld.size() == 0) {
                    StyleableToast.makeText(getActivity(), "Not Found", R.style.mytoast).show();
                } else {
                    StudentDTO values = new StudentDTO();

                    for (StudentDTO d : Ld) {
                        values.setStudentId(d.getStudentId());
                        values.setStudentName(d.getStudentName());
                        values.setStudentNic(d.getStudentNic());
                        values.setStudentPhone(d.getStudentPhone());
                        values.setStudentEmail(d.getStudentEmail());
                    }

                    stuName.setText(values.getStudentName());
                    stuNic.setText(values.getStudentNic());
                    stuPhone.setText(values.getStudentPhone());
                    stuEmail.setText(values.getStudentEmail());

                }
            }
        });

        final EditText edId,edName,edNic,edPhone,edMail;

        edId = v.findViewById(R.id.editText2);
        edName = v.findViewById(R.id.name);
        edNic = v.findViewById(R.id.editText7);
        edPhone = v.findViewById(R.id.editText9);
        edMail = v.findViewById(R.id.editText14);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upId = edId.getText().toString();
                String upName = edName.getText().toString();
                String upNic = edNic.getText().toString();
                String upPhone = edPhone.getText().toString();
                String upEmail = edMail.getText().toString();

                StudentDTO updto = new StudentDTO();

                updto.setStudentId(upId);
                updto.setStudentName(upName);
                updto.setStudentNic(upNic);
                updto.setStudentPhone(upPhone);
                updto.setStudentEmail(upEmail);

                boolean res = db.updateStudent(updto);

                if (res) {
                    StyleableToast.makeText(getActivity(), "Successfully Updated", R.style.mytoastSuccess).show();
                } else {
                    StyleableToast.makeText(getActivity(), "Updated Fail", R.style.mytoastSuccess).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int result = db.deleteStudent(id.getText().toString());

                if (result > 0) {
                    StyleableToast.makeText(getActivity(), "Deleted Successfully", R.style.mytoastSuccess).show();
                } else {
                    StyleableToast.makeText(getActivity(), "Deleted Fail", R.style.mytoastSuccess).show();
                }
            }
        });

        return  v;
    }
}
