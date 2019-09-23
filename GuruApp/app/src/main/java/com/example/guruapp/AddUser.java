package com.example.guruapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.InputMismatchException;

import Database.DBHelperUser;
import Model.UserDTO;

public class AddUser extends Fragment {

    EditText txt_uid,txt_username,txt_phone,txt_mail,txt_subject,txt_password;
    Button addUserH;
    String userid,userName,userPhone,userMail,userSubject,userPassword;

    DBHelperUser db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_add_user,container,false);

        txt_uid = v.findViewById(R.id.AddIdH);
        txt_username = v.findViewById(R.id.AddNameH);
        txt_phone = v.findViewById(R.id.AddPhoneH);
        txt_mail = v.findViewById(R.id.AddMailH);
        txt_subject = v.findViewById(R.id.AddsubH);
        txt_password= v.findViewById(R.id.AddPasswordH);

        addUserH = v.findViewById(R.id.AddBtH);

        db = new DBHelperUser(this.getActivity());


        addUserH.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                userid = txt_uid.getText().toString();
                userName = txt_username.getText().toString();
                userPhone = txt_phone.getText().toString();
                userMail = txt_mail.getText().toString();
                userSubject = txt_subject.getText().toString();
                userPassword = txt_password.getText().toString();

                if(userid.length() != 0 ){

                    if(userName.length() != 0 ){

                        if(userPhone.length() !=0 & userPhone.length() == 10){

                            if(userMail.length() != 0 ){

                                if(userSubject.length() !=0 ){

                                    if(userPassword.length() !=0 ){

                                        try {
                                            UserDTO dto = new UserDTO();

                                            dto.setUserID(userid);
                                            dto.setUserName(userName);
                                            dto.setUserPhone(userPhone);
                                            dto.setUserMail(userMail);
                                            dto.setUserSubject(userSubject);
                                            dto.setUserPassword(userPassword);

                                            boolean res = db.addUser(dto);

                                            if (res){
//                                                Toast.makeText(getActivity(), "User Added SuccessFully", Toast.LENGTH_SHORT).show();

                                                StyleableToast.makeText(getActivity(), "User Added SuccessFully",R.style.mytoastSuccess).show();

                                                txt_uid.setText(" ");
                                                txt_username.setText(" ");
                                                txt_phone.setText(" ");
                                                txt_mail.setText(" ");
                                                txt_subject.setText(" ");
                                                txt_password.setText(" ");

                                            }else {

                                                StyleableToast.makeText(getActivity(), "User Added Fail",R.style.mytoast).show();

                                            }
                                        }catch (InputMismatchException s){
                                            StyleableToast.makeText(getActivity(), "Invalid Inputs",R.style.mytoast).show();

                                        }
                                    }else{
                                        StyleableToast.makeText(getActivity(), "Password is required",R.style.mytoast).show();

                                    }
                                }else{
                                    StyleableToast.makeText(getActivity(), "Subject is required",R.style.mytoast).show();

                                }
                            }else{
                                StyleableToast.makeText(getActivity(), "E-mail is required",R.style.mytoast).show();

                            }
                        }else{
                            StyleableToast.makeText(getActivity(), "Phone Number Is Incorrect",R.style.mytoast).show();

                        }
                    }else{
                        StyleableToast.makeText(getActivity(), "User Name is required",R.style.mytoast).show();

                    }
                }else{
                    StyleableToast.makeText(getActivity(), "User ID is required",R.style.mytoast).show();

                }
            }
        });



        return v;




    }

}
