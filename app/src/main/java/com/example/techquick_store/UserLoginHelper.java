package com.example.techquick_store;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserLoginHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "yernar.db";
    public static final int VERSION = 9;
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


        insertData(db);
    }


    public void insertData(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME,"Yernar");
        contentValues.put(USER_COLUMN_SURNAME,"Akhmetbek");
        contentValues.put(USER_COLUMN_PHONE_NUMBER,"87778473949");
        contentValues.put(USER_COLUMN_MAIL,"admin");
        contentValues.put(USER_COLUMN_PASSWORD,"admin");
        contentValues.put(USER_COLUMN_STATUS,"Active");
        contentValues.put(USER_COLUMN_ROLE,"Admin");

        db.insert(TABLE_NAME,null,contentValues);



        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(USER_COLUMN_NAME,"Sultanbek");
        contentValues1.put(USER_COLUMN_SURNAME,"Turarbek");
        contentValues1.put(USER_COLUMN_PHONE_NUMBER,"87771234569");
        contentValues1.put(USER_COLUMN_MAIL,"manager");
        contentValues1.put(USER_COLUMN_PASSWORD,"manager");
        contentValues1.put(USER_COLUMN_STATUS,"Active");
        contentValues1.put(USER_COLUMN_ROLE,"Manager");

        db.insert(TABLE_NAME,null,contentValues1);




        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(USER_COLUMN_NAME,"Ardak");
        contentValues2.put(USER_COLUMN_SURNAME,"Maksat");
        contentValues2.put(USER_COLUMN_PHONE_NUMBER,"87774551232");
        contentValues2.put(USER_COLUMN_MAIL,"user");
        contentValues2.put(USER_COLUMN_PASSWORD,"user");
        contentValues2.put(USER_COLUMN_STATUS,"Active");
        contentValues2.put(USER_COLUMN_ROLE,"User");

        db.insert(TABLE_NAME,null,contentValues2);




        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(USER_COLUMN_NAME,"Mukagali");
        contentValues3.put(USER_COLUMN_SURNAME,"Zhamanov");
        contentValues3.put(USER_COLUMN_PHONE_NUMBER,"87776892584");
        contentValues3.put(USER_COLUMN_MAIL,"user2");
        contentValues3.put(USER_COLUMN_PASSWORD,"user2");
        contentValues3.put(USER_COLUMN_STATUS,"Active");
        contentValues3.put(USER_COLUMN_ROLE,"User");

        db.insert(TABLE_NAME,null,contentValues3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }


}
