package com.example.techquick_store;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

    }


    public void openAllUsersList(View view){
        Intent intent = new Intent(this,UsersAll.class);
        startActivity(intent);
    }



    public void openAllItemsList(View view){
        Intent intent = new Intent(this,ItemsAll.class);
        startActivity(intent);
    }

}
