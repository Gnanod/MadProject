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

import java.util.List;

import Database.DBHelperUser;
import Model.UserDTO;

public class SearchUpdateDeleteUser extends Fragment {

    EditText txt_uid,txt_name,txt_phn,txt_mail,txt_sub,txt_pswd,del_uid;
    Button btnUpdateH,btnDeleteH,btnsearchH;

    DBHelperUser db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_search_update_delete_user,container,false);

        db = new DBHelperUser(getActivity());

        txt_uid = v.findViewById(R.id.editIdsearchH);
        txt_name = v.findViewById(R.id.editnameH);
        txt_phn = v.findViewById(R.id.editphoneH);
        txt_mail = v.findViewById(R.id.editmailH);
        txt_sub = v.findViewById(R.id.editsubH);
        txt_pswd = v.findViewById(R.id.editpswdH);

        del_uid = v.findViewById(R.id.editIdsearchH);

        btnsearchH = v.findViewById(R.id.searchUserH);
        btnUpdateH = v.findViewById(R.id.updateUserH);
        btnDeleteH = v.findViewById(R.id.deleteUserH);


        btnsearchH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String id = txt_uid.getText().toString();

                List<UserDTO> Ld = db.searchUser(id);
                
                if(Ld.size()==0){
                    Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
                }else{
                    UserDTO values = new UserDTO();

                    for (UserDTO d:Ld){
                        values.setUserID(d.getUserID());
                        values.setUserName(d.getUserName());
                        values.setUserPhone(d.getUserPhone());
                        values.setUserMail(d.getUserMail());
                        values.setUserSubject(d.getUserSubject());
                        values.setUserPassword(d.getUserPassword());
                    }
                    txt_name.setText(values.getUserName());
                    txt_phn.setText(values.getUserPhone());
                    txt_mail.setText(values.getUserMail());
                    txt_sub.setText(values.getUserSubject());
                    txt_pswd.setText(values.getUserPassword());

                }
            }
        });

        final EditText edid,edname,edtel,edmail,edsub,edpw;

        edid = v.findViewById(R.id.editIdsearchH);
        edname = v.findViewById(R.id.editnameH);
        edtel = v.findViewById(R.id.editphoneH);
        edmail = v.findViewById(R.id.editmailH);
        edsub = v.findViewById(R.id.editsubH);
        edpw = v.findViewById(R.id.editpswdH);

        btnUpdateH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upID = edid.getText().toString();
                String upName = edname.getText().toString();
                String upPhn = edtel.getText().toString();
                String upMail = edmail.getText().toString();
                String upSub = edsub.getText().toString();
                String upPw = edpw.getText().toString();

                UserDTO udto = new UserDTO();

                udto.setUserID(upID);
                udto.setUserName(upName);
                udto.setUserPhone(upPhn);
                udto.setUserMail(upMail);
                udto.setUserSubject(upSub);
                udto.setUserPassword(upPw);

                boolean res = db.updateUser(udto);

                if (res){
                    Toast.makeText(getActivity(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Updated Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDeleteH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String delID = del_uid.getText().toString();

                boolean res = db.deleteUser(delID);

                if (res){
                    Toast.makeText(getActivity(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Deleted Fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;


    }
}
