package com.example.techquick_store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ShopHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shop.db";
    public static final int DATABASE_VERSION = 22;


    //Table for Computers
    public static final String TABLE_NAME_COMPUTERS = "Computers";
    public static final String ID_COLUMN = "ID";
    public static final String PRICE_COLUMN = "Price";
    public static final String BRAND_COLUMN = "Brand";
    public static final String RATING_COLUMN = "Rating";
    public static final String INSTALLEDOS_COLUMN = "InstalledOS";
    public static final String PROCESSORTYPE_COLUMN = "ProcessorType";
    public static final String RAM_COLUMN = "RAM";
    public static final String HDD_SPACE_COLUMN = "HDDSpace";
    public static final String IMAGE_COLUMN = "Image";



    //Table for Phones
    public static final String TABLE_NAME_PHONE = "Phones";
    public static final String CAMERA_PIXELS_COLUMN = "CameraPixels";
    public static final String DIAGONAL_COLUMN = "Diagonal";


    //Table for Televisions
    public static final String TABLE_NAME_TELEVISION = "Televisions";
    public static final String QUALITY_COLUMN = "Televisions";



    //TABLE FOR BASKET
    public static final String TABLE_NAME_BASKET = "Basket";
    public static final String USER_ID = "User_id";
    public static final String ITEM_CLASS = "Class";
    public static final String ITEM_ID = "Item_id";



    public ShopHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_TELEVISION + " ( " +
                ID_COLUMN + " INTEGER PRIMARY KEY , " +
                PRICE_COLUMN + " INTEGER , " +
                BRAND_COLUMN + " TEXT , " +
                RATING_COLUMN + " INTEGER , " +
                QUALITY_COLUMN + " TEXT , " +
                DIAGONAL_COLUMN + " INTEGER , " +
                IMAGE_COLUMN + " BLOB)");




    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME_TELEVISION);
        onCreate(db);
    }
}
