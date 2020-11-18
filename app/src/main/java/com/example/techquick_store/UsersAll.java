package com.example.techquick_store;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UsersAll extends AppCompatActivity {
    UserLoginHelper userLoginHelper;
    SQLiteDatabase database;
    TextView user_id , user_name , user_surname;
    LinearLayout users_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_admin);

        users_container = findViewById(R.id.container_for_users);


        userLoginHelper = new UserLoginHelper(this);
        database = userLoginHelper.getWritableDatabase();


        Cursor cursor = database.query(userLoginHelper.TABLE_NAME,null,null,null,null,null,null,null);


        if (cursor.moveToFirst()){
            do {
                View view = getLayoutInflater().inflate(R.layout.one_user_layout,null,false);

                user_id = view.findViewById(R.id.user_id);
                user_name = view.findViewById(R.id.user_name);
                user_surname = view.findViewById(R.id.user_surname);

                user_id.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_ID)));
                user_name.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_NAME)));
                user_surname.setText(cursor.getString(cursor.getColumnIndex(UserLoginHelper.USER_COLUMN_SURNAME)));


                users_container.addView(view);

            }while (cursor.moveToNext());
        }
    }
}
