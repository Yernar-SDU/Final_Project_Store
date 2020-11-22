package com.example.techquick_store;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Profile extends Fragment {
    Integer user_id;
    TextView name, surname, phone_number;
    UserLoginHelper userLoginHelper;
    SQLiteDatabase database;
    Profile(Integer user_id){
        this.user_id = user_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userLoginHelper = new UserLoginHelper(getContext());
        database = userLoginHelper.getWritableDatabase();
        return inflater.inflate(R.layout.profile_layout,container,false);
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        name = view.findViewById(R.id.name);
        surname = view.findViewById(R.id.surname);
        phone_number = view.findViewById(R.id.phone_number);

        Cursor cursor = database.query(userLoginHelper.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToPosition(user_id-1);


        name.setText(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_NAME)));
        surname.setText(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_SURNAME)));
        phone_number.setText(cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_PHONE_NUMBER)));


    }



}
