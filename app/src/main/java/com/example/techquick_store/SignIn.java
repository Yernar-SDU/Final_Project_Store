package com.example.techquick_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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

    }


    // When sign up button clicked
    public void signUp(View view){
        Intent intent_signUp = new Intent(SignIn.this,SignUp.class);
        startActivity(intent_signUp);
    }


    public void openMainPage(View view){
        Cursor cursor = database.query(UserLoginHelper.TABLE_NAME,null,null,null,null,null,null,null);

        // If user clicks login button
        if(view.getId() == login.getId() && cursor.moveToFirst()){
            do {

                //user mail and password
                String mail_text = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_MAIL));
                String password_text = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_PASSWORD));



                Log.i("tag", mail_text + " : " + password_text);
                // check is mail and password are correct or not
                if(mail_text.trim().equals(mail.getText().toString().trim()) && password_text.trim().equals(password.getText().toString().trim())){
                    //check status of user
                    if (cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_STATUS)).equals("Active")){
                        //check user role
                        //open mainPage for users
                        if(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_ROLE)).equals("User")){
                            Intent intent = new Intent(this,MainActivity.class);
                            intent.putExtra("user_id",Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID))));
                            intent.putExtra("logged_in",true);
                            cursor.close();
                            startActivity(intent);
                        }
                        //open mainPage for admins
                        if(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_ROLE)).equals("Admin")){
                            cursor.close();
                            Intent intent = new Intent(this,AdminActivity.class);
                            startActivity(intent);
                        }
                        //open mainPage for admins
                        if(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_ROLE)).equals("Manager")){
                            Intent intent = new Intent(this,ManagerActivity.class);
                            cursor.close();
                            startActivity(intent);
                        }
                    }else{
                        Toast toast = Toast.makeText(this,"This user is blocked",Toast.LENGTH_LONG);
                        toast.show();
                        break;}

                }



            } while (cursor.moveToNext());
            Toast toast = Toast.makeText(this,"Wrong password or mail",Toast.LENGTH_LONG);
            toast.show();


        }



        // If user clicks enter without account button
        if(view.getId() == enter_without_account.getId()){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("logged_in",false);
            cursor.close();
            startActivity(intent);
        }
    }


    //finding all views by ids in one function
    public  void  findViewByIds(){
        shopHelper = new ShopHelper(this);
        shop_database = shopHelper.getWritableDatabase();



        userLoginHelper = new UserLoginHelper(this);
        database = userLoginHelper.getWritableDatabase();



        login =  findViewById(R.id.login);
        enter_without_account = findViewById(R.id.enter_without_account);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
    }











}