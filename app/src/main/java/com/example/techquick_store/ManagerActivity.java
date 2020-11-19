package com.example.techquick_store;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {
    UserLoginHelper userLoginHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView user_id , user_name , user_surname,user_status;
    LinearLayout users_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_layout);

        userLoginHelper = new UserLoginHelper(this);
        sqLiteDatabase = userLoginHelper.getWritableDatabase();

        setData(null);



    }


    public void setData(String query){
        Cursor cursor;
        if(query == null)
            cursor = sqLiteDatabase.query(userLoginHelper.TABLE_NAME,null,null,null,null,null,null,null);

        else{
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + UserLoginHelper.TABLE_NAME +
                    " WHERE " + UserLoginHelper.USER_COLUMN_NAME +  " LIKE " + "'%" +query+"%'" +
                    " OR " + UserLoginHelper.USER_COLUMN_SURNAME + " LIKE " + "'%" +query+"%'",null);
        }


        users_container = findViewById(R.id.container_for_users);



        if (cursor.moveToFirst()) {
            do {
                View view = getLayoutInflater().inflate(R.layout.one_user_manager_layout, null, false);

                user_id = view.findViewById(R.id.user_id);
                user_name = view.findViewById(R.id.user_name);
                user_surname = view.findViewById(R.id.user_surname);
                user_status = view.findViewById(R.id.userStatus);


                user_id.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID)));
                user_name.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_NAME)));
                user_surname.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_SURNAME)));
                user_status.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_STATUS)));

                //Set blocked users background red
                if (cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_STATUS)).equals("Blocked")) {
                    user_status.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                }

                users_container.addView(view);

            } while (cursor.moveToNext());
        }
    }



    public void searchClicked(View view){
        users_container.removeAllViews();
        String search_edit_text;
        EditText editText = findViewById(R.id.search_edit_text);
        search_edit_text = editText.getText().toString();
        if(search_edit_text != null){
            setData(search_edit_text);
        }
        else{
            Toast.makeText(this,"Please write text for search",Toast.LENGTH_SHORT).show();
        }
    }


    public void changeStatus(View view){
        TextView textView = view.findViewById(R.id.user_id);

        ChangeStatusDialog changeStatusDialog = new ChangeStatusDialog(Integer.parseInt(textView.getText().toString()));
        changeStatusDialog.show(getSupportFragmentManager(),"dialog");


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
}
