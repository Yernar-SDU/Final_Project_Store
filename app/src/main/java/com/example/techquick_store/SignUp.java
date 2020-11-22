package com.example.techquick_store;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText name,surname,phone_number,mail,password;
    SQLiteDatabase database;
    UserLoginHelper userLoginHelper;
    int user_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        userLoginHelper = new UserLoginHelper(this);
        findViewByIds();
    }

    private void findViewByIds() {
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        phone_number = findViewById(R.id.phone_number);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        database = userLoginHelper.getWritableDatabase();
    }


    public void signUp(View view){
        if (name.getText()!=null || surname.getText()!=null ||
                phone_number.getText() != null || mail.getText() != null || password.getText() !=null ){
            ContentValues cv = new ContentValues();
            cv.put(userLoginHelper.USER_COLUMN_NAME,name.getText().toString());
            cv.put(userLoginHelper.USER_COLUMN_SURNAME,surname.getText().toString());
            cv.put(userLoginHelper.USER_COLUMN_PHONE_NUMBER,phone_number.getText().toString());
            cv.put(userLoginHelper.USER_COLUMN_MAIL,mail.getText().toString());
            cv.put(userLoginHelper.USER_COLUMN_PASSWORD,password.getText().toString());
            cv.put(userLoginHelper.USER_COLUMN_STATUS,"Active");
            cv.put(userLoginHelper.USER_COLUMN_ROLE,"User");
            database.insert(userLoginHelper.TABLE_NAME,null,cv);



            Toast toast = Toast.makeText(this,"You have registered",Toast.LENGTH_LONG);
            toast.show();



            name.setText("");
            surname.setText("");
            phone_number.setText("");
            mail.setText("");
            password.setText("");

        }
        else {
            Toast toast = Toast.makeText(this,"Please fill all blanks",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
