package com.example.techquick_store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserLoginHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "yernar.db";
    public static final int VERSION = 6;
    public static final String TABLE_NAME = "User";
    public static final String USER_COLUMN_NAME = "Name";
    public static final String USER_COLUMN_SURNAME = "Surname";
    public static final String USER_COLUMN_PHONE_NUMBER = "Phone_Number";
    public static final String USER_COLUMN_MAIL = "Mail";
    public static final String USER_COLUMN_PASSWORD = "Password";
    public static final String USER_COLUMN_ID = "Id";
    public static final String USER_COLUMN_STATUS = "Status";
    public static final String USER_COLUMN_ROLE = "Role";



    public UserLoginHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " + USER_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                USER_COLUMN_NAME + " TEXT NOT NULL, " + USER_COLUMN_SURNAME + " TEXT NOT NULL," + USER_COLUMN_PHONE_NUMBER + " TEXT NOT NULL, "
                + USER_COLUMN_MAIL + " TEXT NOT NULL, " + USER_COLUMN_PASSWORD + " TEXT NOT NULL, " + USER_COLUMN_STATUS  + " TEXT NOT NULL, "
                + USER_COLUMN_ROLE + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }


}
