package com.example.laboratoriosqlite;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class sing_up extends AppCompatActivity {
    private DatabaseHelper myDb;
    private TextInputLayout editFullName, editUsername,editEmail,editPassword;
    private Button btnAddData;
    private Button btnViewAll;
    private int gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Singup_Form");
        myDb=new DatabaseHelper(this);
        editFullName=(TextInputLayout)findViewById(R.id.editText_fullname);
        editUsername=(TextInputLayout)findViewById(R.id.editText_username);
        editEmail=(TextInputLayout)findViewById(R.id.editText_email);
        editPassword=(TextInputLayout)findViewById(R.id.editText_password);
        btnAddData=(Button)findViewById(R.id.button_register);
        btnViewAll=(Button)findViewById(R.id.button_viewAll);
        addData();
        viewAll();
    }

    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer valueInt = new Integer(gender);
                        User user= new User();
                        user.setFullName(editFullName.getEditText().getText().toString());
                        user.setUserName(editUsername.getEditText().getText().toString());
                        user.seteMail(editEmail.getEditText().getText().toString());
                        user.setPassword(editPassword.getEditText().getText().toString());
                        boolean isInserted=myDb.insertData(user);
                        if(isInserted)
                            Toast.makeText(sing_up.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(sing_up.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id: "+res.getString(0)+"/n");
                            buffer.append("Name: "+res.getString(1)+"/n");
                            buffer.append("UserName: "+res.getString(2)+"/n");
                            buffer.append("EMail: "+res.getString(3)+"/n");
                            buffer.append("Password: "+res.getString(4)+"/n");
                            buffer.append("Gender: "+res.getString(5)+"/n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void onRadioButtonClicked(View view){
        boolean checked=((RadioButton)view).isChecked() ;

        switch (view.getId()){
            case R.id.radioButton_male:
                if(checked)
                    gender=1;
                break;
            case R.id.radioButton_female:
                if(checked)
                    gender=0;
                break;
        }


    }



}