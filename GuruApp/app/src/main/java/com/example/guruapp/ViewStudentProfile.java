package com.example.guruapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

import Database.DBHelper1;
import Model.StudentDTO;


public class ViewStudentProfile extends Fragment {

    String studentId;

    EditText studNic, studId, studName, studPhn, studEmail;
    DBHelper1 db;
    RadioButton rd1,rd2;

    View v;


    public ViewStudentProfile(String id){

       studentId=id;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        db = new DBHelper1(getActivity());

       v= inflater.inflate(R.layout.fragment_view_student_profile, container, false);


        studId = v.findViewById(R.id.viewProfileId);
        studName = v.findViewById(R.id.viewProfileName);
        studPhn = v.findViewById(R.id.viewProfilePhoneNumber);
        studEmail = v.findViewById(R.id.radFemale);
        rd1 = v.findViewById(R.id.radMale);
        rd2 = v.findViewById(R.id.radFemala);


        getDetails();

        return v;

    }


    public void getDetails(){



        ///String nic = studNic.getText().toString();

        List<StudentDTO> s = db.getDetails(studentId);

        if (s.size() == 0) {
            StyleableToast.makeText(getActivity(), "Not Found", R.style.mytoast).show();

        } else {
            StudentDTO values = new StudentDTO();

            for (StudentDTO d : s) {
                values.setStudentNic(d.getStudentNic());
                values.setStudentId(d.getStudentId());
                values.setStudentName(d.getStudentName());
                values.setStudentPhone(d.getStudentPhone());
                values.setStudentEmail(d.getStudentEmail());

            }


            studId.setText(values.getStudentNic());
            studName.setText(values.getStudentName());
            studPhn.setText(values.getStudentPhone());
            studEmail.setText(values.getStudentEmail());

        }
    }
}
