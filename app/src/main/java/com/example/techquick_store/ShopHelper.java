package com.example.techquick_store;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class ShopHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "shop.db";
    public static final int DATABASE_VERSION = 24;


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
        this.context = context;
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

        db.execSQL("CREATE TABLE " + TABLE_NAME_COMPUTERS + " ( " +
                ID_COLUMN + " INTEGER PRIMARY KEY , " +
                PRICE_COLUMN + " INTEGER , " +
                BRAND_COLUMN + " TEXT , " +
                RATING_COLUMN + " INTEGER , " +
                INSTALLEDOS_COLUMN + " TEXT , " +
                PROCESSORTYPE_COLUMN + " TEXT , " +
                RAM_COLUMN + " INTEGER ," +
                HDD_SPACE_COLUMN + " INTEGER ," +
                IMAGE_COLUMN + " BLOB )");

        db.execSQL("CREATE TABLE " + TABLE_NAME_PHONE + " ( " +
                ID_COLUMN + " INTEGER PRIMARY KEY , " +
                PRICE_COLUMN + " INTEGER , " +
                BRAND_COLUMN + " TEXT , " +
                RATING_COLUMN + " INTEGER , " +
                RAM_COLUMN + " INTEGER ," +
                CAMERA_PIXELS_COLUMN + " INTEGER ," +
                DIAGONAL_COLUMN + " FLOAT , " +
                IMAGE_COLUMN + " BLOB )");


        db.execSQL(" CREATE TABLE " + TABLE_NAME_BASKET + " ( " +
                USER_ID + " INTEGER , " +
                ITEM_CLASS + " TEXT , " +
                ITEM_ID + " INTEGER )");




        insertDataPhones(db);
        insertDataTelevisions(db);
        insertDataComputers(db);
    }



    public void insertDataPhones(SQLiteDatabase db){


        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.apple1);


        ContentValues cv = new ContentValues();
        cv.put(BRAND_COLUMN,"Apple");
        cv.put(PRICE_COLUMN, 389000);
        cv.put(RATING_COLUMN,1);
        cv.put(DIAGONAL_COLUMN,4);
        cv.put(CAMERA_PIXELS_COLUMN,16);
        cv.put(RAM_COLUMN,2);
        cv.put(IMAGE_COLUMN,getBytes(bitmap));
        db.insert(TABLE_NAME_PHONE,null,cv);



        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.apple2);

        ContentValues cv1 = new ContentValues();
        cv1.put(BRAND_COLUMN,"Apple");
        cv1.put(PRICE_COLUMN, 345000);
        cv1.put(RATING_COLUMN,2);
        cv1.put(RAM_COLUMN,4);
        cv1.put(DIAGONAL_COLUMN,4.5);
        cv1.put(CAMERA_PIXELS_COLUMN,13);
        cv1.put(IMAGE_COLUMN,getBytes(bitmap1));
        db.insert(TABLE_NAME_PHONE,null,cv1);




        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.huawei1);

        ContentValues cv2 = new ContentValues();
        cv2.put(BRAND_COLUMN,"Huawei");
        cv2.put(PRICE_COLUMN, 55800);
        cv2.put(RATING_COLUMN,3);
        cv2.put(CAMERA_PIXELS_COLUMN,8);
        cv2.put(DIAGONAL_COLUMN,5);
        cv2.put(RAM_COLUMN,8);
        cv2.put(IMAGE_COLUMN,getBytes(bitmap2));
        db.insert(TABLE_NAME_PHONE,null,cv2);



        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.huawei2);

        ContentValues cv3 = new ContentValues();
        cv3.put(BRAND_COLUMN,"Huawei");
        cv3.put(PRICE_COLUMN, 45000);
        cv3.put(RATING_COLUMN,4);
        cv3.put(RAM_COLUMN,16);
        cv3.put(CAMERA_PIXELS_COLUMN,5);
        cv3.put(DIAGONAL_COLUMN,5.5);
        cv3.put(IMAGE_COLUMN,getBytes(bitmap3));
        db.insert(TABLE_NAME_PHONE,null,cv3);



        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.oppo1);

        ContentValues cv4 = new ContentValues();
        cv4.put(BRAND_COLUMN,"Oppo");
        cv4.put(PRICE_COLUMN, 68000);
        cv4.put(RATING_COLUMN,5);
        cv4.put(RAM_COLUMN,32);
        cv4.put(CAMERA_PIXELS_COLUMN,2);
        cv4.put(DIAGONAL_COLUMN,6);
        cv4.put(IMAGE_COLUMN,getBytes(bitmap4));
        db.insert(TABLE_NAME_PHONE,null,cv4);




        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.samsung4);

        ContentValues cv5 = new ContentValues();
        cv5.put(BRAND_COLUMN,"Samsung");
        cv5.put(PRICE_COLUMN,205000);
        cv5.put(RATING_COLUMN,1);
        cv5.put(RAM_COLUMN,2);
        cv5.put(CAMERA_PIXELS_COLUMN,20);
        cv5.put(DIAGONAL_COLUMN,7);
        cv5.put(IMAGE_COLUMN,getBytes(bitmap5));
        db.insert(TABLE_NAME_PHONE,null,cv5);





        Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.samsung1);

        ContentValues cv6 = new ContentValues();
        cv6.put(BRAND_COLUMN,"Samsung");
        cv6.put(PRICE_COLUMN, 128000);
        cv6.put(RATING_COLUMN,2);
        cv6.put(RAM_COLUMN,4);
        cv6.put(CAMERA_PIXELS_COLUMN,16);
        cv6.put(DIAGONAL_COLUMN,4);
        cv6.put(IMAGE_COLUMN,getBytes(bitmap6));
        db.insert(TABLE_NAME_PHONE,null,cv6);




        Bitmap bitmap7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.samsung2);

        ContentValues cv7 = new ContentValues();
        cv7.put(BRAND_COLUMN,"Samsung");
        cv7.put(PRICE_COLUMN, 78000);
        cv7.put(RATING_COLUMN,3);
        cv7.put(RAM_COLUMN,8);
        cv7.put(CAMERA_PIXELS_COLUMN,13);
        cv7.put(DIAGONAL_COLUMN,4.5);
        cv7.put(IMAGE_COLUMN,getBytes(bitmap7));
        db.insert(TABLE_NAME_PHONE,null,cv7);



        Bitmap bitmap8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.samsung3);

        ContentValues cv8 = new ContentValues();
        cv8.put(BRAND_COLUMN,"Samsung");
        cv8.put(PRICE_COLUMN, 105000);
        cv8.put(RATING_COLUMN,4);
        cv8.put(RAM_COLUMN,16);
        cv8.put(CAMERA_PIXELS_COLUMN,8);
        cv8.put(DIAGONAL_COLUMN,5);
        cv8.put(IMAGE_COLUMN,getBytes(bitmap8));
        db.insert(TABLE_NAME_PHONE,null,cv8);



        Bitmap bitmap9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.xiaomi1);

        ContentValues cv9 = new ContentValues();
        cv9.put(BRAND_COLUMN,"Xiaomi");
        cv9.put(PRICE_COLUMN, 65000);
        cv9.put(RATING_COLUMN,5);
        cv9.put(CAMERA_PIXELS_COLUMN,5);
        cv9.put(DIAGONAL_COLUMN,5.5);
        cv9.put(RAM_COLUMN,32);
        cv9.put(IMAGE_COLUMN,getBytes(bitmap9));
        db.insert(TABLE_NAME_PHONE,null,cv9);



        Bitmap bitmap10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.oppo2);
        ContentValues cv10 = new ContentValues();
        cv10.put(BRAND_COLUMN,"Oppo");
        cv10.put(PRICE_COLUMN, 87000);
        cv10.put(RATING_COLUMN,1);
        cv10.put(RAM_COLUMN,2);
        cv10.put(CAMERA_PIXELS_COLUMN,2);
        cv10.put(DIAGONAL_COLUMN,6);
        cv10.put(IMAGE_COLUMN,getBytes(bitmap10));
        db.insert(TABLE_NAME_PHONE,null,cv10);


    }



    public void insertDataTelevisions(SQLiteDatabase db) {


        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.television1);


        ContentValues cv = new ContentValues();
        cv.put(BRAND_COLUMN,"LG");
        cv.put(PRICE_COLUMN, 44990);
        cv.put(RATING_COLUMN,1);
        cv.put(QUALITY_COLUMN,"1080i HD");
        cv.put(DIAGONAL_COLUMN,60);
        cv.put(IMAGE_COLUMN,getBytes(bitmap));
        db.insert(TABLE_NAME_TELEVISION,null,cv);



        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television2);

        ContentValues cv1 = new ContentValues();
        cv1.put(BRAND_COLUMN,"Samsung");
        cv1.put(PRICE_COLUMN, 215990);
        cv1.put(RATING_COLUMN,4);
        cv1.put(QUALITY_COLUMN,"1080p Full HD");
        cv1.put(DIAGONAL_COLUMN,55);
        cv1.put(IMAGE_COLUMN,getBytes(bitmap1));
        db.insert(TABLE_NAME_TELEVISION,null,cv1);




        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television3);

        ContentValues cv2 = new ContentValues();
        cv2.put(BRAND_COLUMN,"Samsung");
        cv2.put(PRICE_COLUMN, 107990);
        cv2.put(RATING_COLUMN,3);
        cv2.put(QUALITY_COLUMN,"4K HDR");
        cv2.put(DIAGONAL_COLUMN,50);
        cv2.put(IMAGE_COLUMN,getBytes(bitmap2));
        db.insert(TABLE_NAME_TELEVISION,null,cv2);



        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television4);

        ContentValues cv3 = new ContentValues();
        cv3.put(BRAND_COLUMN,"LG");
        cv3.put(PRICE_COLUMN, 79990);
        cv3.put(RATING_COLUMN,5);
        cv3.put(QUALITY_COLUMN,"8K HDR");
        cv3.put(DIAGONAL_COLUMN,45);
        cv3.put(IMAGE_COLUMN,getBytes(bitmap3));
        db.insert(TABLE_NAME_TELEVISION,null,cv3);



        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television5);

        ContentValues cv4 = new ContentValues();
        cv4.put(BRAND_COLUMN,"Samsung");
        cv4.put(PRICE_COLUMN, 188890);
        cv4.put(RATING_COLUMN,1);
        cv4.put(QUALITY_COLUMN,"1080i HD");
        cv4.put(DIAGONAL_COLUMN,40);
        cv4.put(IMAGE_COLUMN,getBytes(bitmap4));
        db.insert(TABLE_NAME_TELEVISION,null,cv4);




        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television6);

        ContentValues cv5 = new ContentValues();
        cv5.put(BRAND_COLUMN,"LG");
        cv5.put(PRICE_COLUMN,215990);
        cv5.put(RATING_COLUMN,5);
        cv5.put(QUALITY_COLUMN,"1080p Full HD");
        cv5.put(DIAGONAL_COLUMN,70);
        cv5.put(IMAGE_COLUMN,getBytes(bitmap5));
        db.insert(TABLE_NAME_TELEVISION,null,cv5);





        Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television7);

        ContentValues cv6 = new ContentValues();
        cv6.put(BRAND_COLUMN,"Samsung");
        cv6.put(PRICE_COLUMN, 166490);
        cv6.put(RATING_COLUMN,4);
        cv6.put(QUALITY_COLUMN,"4K HDR");
        cv6.put(DIAGONAL_COLUMN,60);
        cv6.put(IMAGE_COLUMN,getBytes(bitmap6));
        db.insert(TABLE_NAME_TELEVISION,null,cv6);




        Bitmap bitmap7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television8);

        ContentValues cv7 = new ContentValues();
        cv7.put(BRAND_COLUMN,"LG");
        cv7.put(PRICE_COLUMN, 134990);
        cv7.put(RATING_COLUMN,3);
        cv7.put(QUALITY_COLUMN,"8K HDR");
        cv7.put(DIAGONAL_COLUMN,55);
        cv7.put(IMAGE_COLUMN,getBytes(bitmap7));
        db.insert(TABLE_NAME_TELEVISION,null,cv7);



        Bitmap bitmap8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television9);

        ContentValues cv8 = new ContentValues();
        cv8.put(BRAND_COLUMN,"Samsung");
        cv8.put(PRICE_COLUMN, 188990);
        cv8.put(RATING_COLUMN,2);
        cv8.put(QUALITY_COLUMN,"1080i HD");
        cv8.put(DIAGONAL_COLUMN,50);
        cv8.put(IMAGE_COLUMN,getBytes(bitmap8));
        db.insert(TABLE_NAME_TELEVISION,null,cv8);




        Bitmap bitmap9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television10);

        ContentValues cv9 = new ContentValues();
        cv9.put(BRAND_COLUMN,"LG");
        cv9.put(PRICE_COLUMN, 242990);
        cv9.put(RATING_COLUMN,1);
        cv9.put(QUALITY_COLUMN,"1080p Full HD");
        cv9.put(DIAGONAL_COLUMN,45);
        cv9.put(IMAGE_COLUMN,getBytes(bitmap9));
        db.insert(TABLE_NAME_TELEVISION,null,cv9);



        Bitmap bitmap10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.television11);

        ContentValues cv10 = new ContentValues();
        cv10.put(BRAND_COLUMN,"Samsung");
        cv10.put(PRICE_COLUMN, 170990);
        cv10.put(RATING_COLUMN,5);
        cv10.put(QUALITY_COLUMN,"4K HDR");
        cv10.put(DIAGONAL_COLUMN,40);
        cv10.put(IMAGE_COLUMN,getBytes(bitmap10));
        db.insert(TABLE_NAME_TELEVISION,null,cv10);


    }



    public void insertDataComputers(SQLiteDatabase db) {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.b);


        ContentValues cv = new ContentValues();
        cv.put(BRAND_COLUMN,"Acer");
        cv.put(PRICE_COLUMN, 139990);
        cv.put(RATING_COLUMN,3);
        cv.put(INSTALLEDOS_COLUMN,"Windows");
        cv.put(PROCESSORTYPE_COLUMN,"Intel");
        cv.put(RAM_COLUMN,2);
        cv.put(HDD_SPACE_COLUMN,128);
        cv.put(IMAGE_COLUMN,getBytes(bitmap));
        db.insert(TABLE_NAME_COMPUTERS,null,cv);



        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.c);

        ContentValues cv1 = new ContentValues();
        cv1.put(BRAND_COLUMN,"Apple");
        cv1.put(PRICE_COLUMN, 445990);
        cv1.put(RATING_COLUMN,4);
        cv1.put(INSTALLEDOS_COLUMN,"Mac");
        cv1.put(PROCESSORTYPE_COLUMN,"Intel");
        cv1.put(RAM_COLUMN,4);
        cv1.put(HDD_SPACE_COLUMN,1024);
        cv1.put(IMAGE_COLUMN,getBytes(bitmap1));
        db.insert(TABLE_NAME_COMPUTERS,null,cv1);




        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.d);

        ContentValues cv2 = new ContentValues();
        cv2.put(BRAND_COLUMN,"HP");
        cv2.put(PRICE_COLUMN, 172990);
        cv2.put(RATING_COLUMN,3);
        cv2.put(INSTALLEDOS_COLUMN,"Linux");
        cv2.put(PROCESSORTYPE_COLUMN,"AMD");
        cv2.put(RAM_COLUMN,8);
        cv2.put(HDD_SPACE_COLUMN,512);
        cv2.put(IMAGE_COLUMN,getBytes(bitmap2));
        db.insert(TABLE_NAME_COMPUTERS,null,cv2);



        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.m);

        ContentValues cv3 = new ContentValues();
        cv3.put(BRAND_COLUMN,"Acer");
        cv3.put(PRICE_COLUMN, 96990);
        cv3.put(RATING_COLUMN,16);
        cv3.put(INSTALLEDOS_COLUMN,"Windows");
        cv3.put(PROCESSORTYPE_COLUMN,"Intel");
        cv3.put(RAM_COLUMN,16);
        cv3.put(HDD_SPACE_COLUMN,256);
        cv3.put(IMAGE_COLUMN,getBytes(bitmap3));
        db.insert(TABLE_NAME_COMPUTERS,null,cv3);



        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.f);

        ContentValues cv4 = new ContentValues();
        cv4.put(BRAND_COLUMN,"Apple");
        cv4.put(PRICE_COLUMN, 248990);
        cv4.put(RATING_COLUMN,1);
        cv4.put(INSTALLEDOS_COLUMN,"Mac");
        cv4.put(PROCESSORTYPE_COLUMN,"AMD");
        cv4.put(RAM_COLUMN,32);
        cv4.put(HDD_SPACE_COLUMN,128);
        cv4.put(IMAGE_COLUMN,getBytes(bitmap4));
        db.insert(TABLE_NAME_COMPUTERS,null,cv4);




        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.g);

        ContentValues cv5 = new ContentValues();
        cv5.put(BRAND_COLUMN,"Avalon");
        cv5.put(PRICE_COLUMN,796990);
        cv5.put(RATING_COLUMN,5);
        cv5.put(INSTALLEDOS_COLUMN,"Linux");
        cv5.put(PROCESSORTYPE_COLUMN,"Intel");
        cv5.put(RAM_COLUMN,2);
        cv5.put(HDD_SPACE_COLUMN,1024);
        cv5.put(IMAGE_COLUMN,getBytes(bitmap5));
        db.insert(TABLE_NAME_COMPUTERS,null,cv5);





        Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.h);

        ContentValues cv6 = new ContentValues();
        cv6.put(BRAND_COLUMN,"Dell");
        cv6.put(PRICE_COLUMN, 278990);
        cv6.put(RATING_COLUMN,4);
        cv6.put(INSTALLEDOS_COLUMN,"Windows");
        cv6.put(PROCESSORTYPE_COLUMN,"AMD");
        cv6.put(RAM_COLUMN,4);
        cv6.put(HDD_SPACE_COLUMN,512);
        cv6.put(IMAGE_COLUMN,getBytes(bitmap6));
        db.insert(TABLE_NAME_COMPUTERS,null,cv6);




        Bitmap bitmap7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.i);

        ContentValues cv7 = new ContentValues();
        cv7.put(BRAND_COLUMN,"HP");
        cv7.put(PRICE_COLUMN, 764990);
        cv7.put(RATING_COLUMN,3);
        cv7.put(INSTALLEDOS_COLUMN,"Linux");
        cv7.put(PROCESSORTYPE_COLUMN,"Intel");
        cv7.put(RAM_COLUMN,8);
        cv7.put(HDD_SPACE_COLUMN,256);
        cv7.put(IMAGE_COLUMN,getBytes(bitmap7));
        db.insert(TABLE_NAME_COMPUTERS,null,cv7);



        Bitmap bitmap8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.j);

        ContentValues cv8 = new ContentValues();
        cv8.put(BRAND_COLUMN,"Lenovo");
        cv8.put(PRICE_COLUMN, 396990);
        cv8.put(RATING_COLUMN,2);
        cv8.put(INSTALLEDOS_COLUMN,"Windows");
        cv8.put(PROCESSORTYPE_COLUMN,"AMD");
        cv8.put(RAM_COLUMN,16);
        cv8.put(HDD_SPACE_COLUMN,128);
        cv8.put(IMAGE_COLUMN,getBytes(bitmap8));
        db.insert(TABLE_NAME_COMPUTERS,null,cv8);




        Bitmap bitmap9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.k);

        ContentValues cv9 = new ContentValues();
        cv9.put(BRAND_COLUMN,"Acer");
        cv9.put(PRICE_COLUMN, 590594);
        cv9.put(RATING_COLUMN,1);
        cv9.put(INSTALLEDOS_COLUMN,"Linux");
        cv9.put(PROCESSORTYPE_COLUMN,"Intel");
        cv9.put(RAM_COLUMN,32);
        cv9.put(HDD_SPACE_COLUMN,1024);
        cv9.put(IMAGE_COLUMN,getBytes(bitmap9));
        db.insert(TABLE_NAME_COMPUTERS,null,cv9);



        Bitmap bitmap10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.l);

        ContentValues cv10 = new ContentValues();
        cv10.put(BRAND_COLUMN,"Apple");
        cv10.put(PRICE_COLUMN, 322990);
        cv10.put(RATING_COLUMN,5);
        cv10.put(INSTALLEDOS_COLUMN,"Mac");
        cv10.put(PROCESSORTYPE_COLUMN,"AMD");
        cv10.put(RAM_COLUMN,2);
        cv10.put(HDD_SPACE_COLUMN,512);
        cv10.put(IMAGE_COLUMN,getBytes(bitmap10));
        db.insert(TABLE_NAME_COMPUTERS,null,cv10);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME_TELEVISION);
        db.execSQL("drop table if exists " + TABLE_NAME_BASKET);
        db.execSQL("drop table if exists " + TABLE_NAME_PHONE);
        db.execSQL("drop table if exists " + TABLE_NAME_COMPUTERS);
        onCreate(db);
    }







    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();

    }
}
