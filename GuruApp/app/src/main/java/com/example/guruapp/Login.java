package com.example.guruapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.muddzdev.styleabletoast.StyleableToast;

import Database.DBHelper;

public class Login extends AppCompatActivity {

    DBHelper db;
    EditText userNameId,passwordId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameId= findViewById(R.id.username);
        passwordId= findViewById(R.id.password);
        db = new DBHelper(this);

    }

    public void Login(View view){
        String userName =userNameId.getText().toString();
        String password = passwordId.getText().toString();

        if(userName.equals("Admin")){

            Intent admin  = new Intent(this,MainActivity.class);
            startActivity(admin);

        }else{

            String s1 = db.LoginFunction(userName,password);
            System.out.println("KKKKKKKKKKKKKKKKKKKK"+s1);

            if(s1!=null){

                Intent student  = new Intent(this,StudentProfile.class);
                student.putExtra("studentId",s1);
                startActivity(student);


            }else{

                StyleableToast.makeText(this, "Login Details Are Invalid",R.style.mytoast).show();

            }



        }








    }
}
