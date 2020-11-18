package com.example.techquick_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class SignIn extends AppCompatActivity {
    TextView login,enter_without_account;
    EditText mail,password;
    SQLiteDatabase database;
    UserLoginHelper userLoginHelper;
    SQLiteDatabase shop_database;
    ShopHelper shopHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        findViewByIds();
        shopHelper = new ShopHelper(this);
        shop_database = shopHelper.getWritableDatabase();




    }


    // When sign up button clicked
    public void signUp(View view){
        Intent intent_signUp = new Intent(SignIn.this,SignUp.class);
        startActivity(intent_signUp);
    }


    public void openMainPage(View view){
        Intent for_user_intent = new Intent(SignIn.this,MainActivity.class);
        Cursor cursor = database.query(UserLoginHelper.TABLE_NAME,null,null,null,null,null,null,null);

        // If user clicks login button
        if(view.getId() == login.getId()){
            if(cursor.moveToFirst()) {
                do {

                    //user mail and password
                    int mail_index = cursor.getColumnIndex(userLoginHelper.USER_COLUMN_MAIL);
                    int password_index = cursor.getColumnIndex(userLoginHelper.USER_COLUMN_PASSWORD);



                    // check is mail and password are correct or not
                    if(cursor.getString(mail_index).equals(mail.getText().toString()) && cursor.getString(password_index).equals(password.getText().toString())){


                        //check user role

                        //open mainPage for users
                        if(userLoginHelper.USER_COLUMN_ROLE.equals("User")){
                            for_user_intent.putExtra("user_id",Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID))));
                            for_user_intent.putExtra("logged_in",true);
                            cursor.close();
                            startActivity(for_user_intent);
                            break;
                        }



                        //open mainPage for admins
                        if(userLoginHelper.USER_COLUMN_ROLE.equals("Admin")){
                            for_user_intent.putExtra("user_id",Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID))));
                            for_user_intent.putExtra("logged_in",true);
                            cursor.close();
                            startActivity(for_user_intent);
                            break;
                        }




                        //open mainPage for admins
                        if(userLoginHelper.USER_COLUMN_ROLE.equals("Manager")){
                            for_user_intent.putExtra("user_id",Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID))));
                            for_user_intent.putExtra("logged_in",true);
                            cursor.close();
                            startActivity(for_user_intent);
                            break;
                        }







                    }
                    else {
                        Toast toast = Toast.makeText(this,"Wrong password or mail",Toast.LENGTH_LONG);
                        toast.show();
                    }

                } while (cursor.moveToNext());
            }

        }



        // If user clicks enter without account button
        if(view.getId() == enter_without_account.getId()){
            for_user_intent.putExtra("logged_in",false);
            startActivity(for_user_intent);
        }
    }


    //finding all views by ids in one function
    public  void  findViewByIds(){
        userLoginHelper = new UserLoginHelper(this);
        login =  findViewById(R.id.login);
        enter_without_account = findViewById(R.id.enter_without_account);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        database = userLoginHelper.getWritableDatabase();
    }
















}