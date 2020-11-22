package com.example.techquick_store;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileAdminActivity extends AppCompatActivity {
    Spinner roleSpinner,statusSpinner;
    UserLoginHelper userLoginHelper;
    TextView name,surname,phone_number,mail,password;
    SQLiteDatabase database;
    Integer user_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_admin_layout);


        userLoginHelper = new UserLoginHelper(this);
        database = userLoginHelper.getWritableDatabase();
        roleSpinner = (Spinner) findViewById(R.id.roleSpinner);
        statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone_number = findViewById(R.id.phone_number);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);


        Intent intent = getIntent();

        user_id = intent.getIntExtra("user_id",1);




        Cursor cursor = database.query(userLoginHelper.TABLE_NAME,null,null,null,null,null,null);

        cursor.moveToPosition(user_id - 1);


        String name_data = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_NAME));
        String surname_data = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_SURNAME));
        String phone_number_data = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_PHONE_NUMBER));
        String mail_data = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_MAIL));
        String password_data = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_PASSWORD));
        String role = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_ROLE));
        String status = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_STATUS));





        //Set default spinner data for role
        if(role.equals("User")) roleSpinner.setSelection(0);
        if(role.equals("Manager")) roleSpinner.setSelection(1);
        if(role.equals("Admin")) roleSpinner.setSelection(2);

        //Set default spinner data for status
        if(status.equals("Active")) statusSpinner.setSelection(0);
        if(status.equals("Blocked")) statusSpinner.setSelection(1);


        name.setText(name_data);
        surname.setText(surname_data);
        phone_number.setText(phone_number_data);
        mail.setText(mail_data);
        password.setText(password_data);

    }





    public void openSignInPage(View view){
        ContentValues cv = new ContentValues();
        cv.put(userLoginHelper.USER_COLUMN_ROLE,roleSpinner.getSelectedItem().toString());
        cv.put(userLoginHelper.USER_COLUMN_STATUS,statusSpinner.getSelectedItem().toString());

        database.update(userLoginHelper.TABLE_NAME,cv," Id=?" , new String[]{user_id+""});
        Toast.makeText(this,"User configurations are changed",Toast.LENGTH_SHORT).show();
    }
}
