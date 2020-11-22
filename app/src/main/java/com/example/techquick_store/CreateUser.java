package com.example.techquick_store;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateUser extends AppCompatActivity {
    EditText name,surname,phone_number,mail,password;
    Spinner role,status;
    UserLoginHelper userLoginHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_layout);

        userLoginHelper = new UserLoginHelper(this);
        database = userLoginHelper.getWritableDatabase();

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone_number = findViewById(R.id.phone_number);
        role = findViewById(R.id.roleSpinner);
        status = findViewById(R.id.statusSpinner);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
    }


    public void createUser(View view){
        if(name.getText() != null && surname.getText() != null && phone_number.getText() != null && mail.getText() != null && password.getText() != null){
            ContentValues cv = new ContentValues();
            cv.put(UserLoginHelper.USER_COLUMN_NAME,name.getText().toString());
            cv.put(UserLoginHelper.USER_COLUMN_SURNAME,surname.getText().toString());
            cv.put(UserLoginHelper.USER_COLUMN_PHONE_NUMBER,phone_number.getText().toString());
            cv.put(UserLoginHelper.USER_COLUMN_ROLE,role.getSelectedItem().toString());
            cv.put(UserLoginHelper.USER_COLUMN_STATUS,status.getSelectedItem().toString());
            cv.put(UserLoginHelper.USER_COLUMN_MAIL,mail.getText().toString());
            cv.put(UserLoginHelper.USER_COLUMN_PASSWORD,password.getText().toString());
            database.insert(userLoginHelper.TABLE_NAME,null,cv);


            name.setText("");
            surname.setText("");
            phone_number.setText("");
            role.setSelection(0);
            status.setSelection(0);
            mail.setText("");
            password.setText("");

            Toast.makeText(this,"User have been registered",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,UsersAll.class);
            startActivity(intent);

        }else{
            Toast.makeText(this,"Please fill all blanks",Toast.LENGTH_SHORT).show();
        }
    }
}
