package com.example.techquick_store;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainPage extends Fragment {
    ShopHelper shopHelper;
    SQLiteDatabase database;
    LinearLayout linearLayout;
    byte[] image;
    boolean first_time_opened = true;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_layout,container,false);
    }



    @Override
    public void onStart() {
        super.onStart();

        if (first_time_opened){
            insertData();
            first_time_opened = false;
        }

    }

    public void insertData(){
        view = getView();


        //Database
        shopHelper = new ShopHelper(getContext());
        database = shopHelper.getWritableDatabase();


        //Containers for photos
        LinearLayout linearLayout_computers = view.findViewById(R.id.computer_items);
        LinearLayout linearLayout_phones = view.findViewById(R.id.phone_items);
        LinearLayout linearLayout_televisions = view.findViewById(R.id.television_items);


        //Parameters for images
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(320, 320);
        layoutParams.setMargins(0,0,20,0);



        //Getting computer's data
        Cursor cursor = database.query(ShopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{

                //Getting image in byte[] format
                image =cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));


                //Converting it into bitmap
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2; // Power of 2
                Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length, opts);


                //Setting bitmap into new imageView
                ImageView imageView = new ImageView(getContext());
                imageView.setImageBitmap(bmp);


                //and adding it to container
                linearLayout_computers.addView(imageView,layoutParams);
            }while (cursor.moveToNext());

        }





        //Getting phone's data
        Cursor cursor_phone = database.query(ShopHelper.TABLE_NAME_PHONE,null,null,null,null,null,null,null);
        if(cursor_phone.moveToFirst()){
            do{

                //Getting image in byte[] format
                image =cursor_phone.getBlob(cursor_phone.getColumnIndex(shopHelper.IMAGE_COLUMN));


                //Converting it into bitmap
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2; // Power of 2
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, opts);

                //Setting bitmap into new imageView
                ImageView imageView = new ImageView(getContext());
                imageView.setImageBitmap(bitmap);


                //and adding it to container
                linearLayout_phones.addView(imageView,layoutParams);
            }while (cursor_phone.moveToNext());

        }


        //Getting television's data
        Cursor cursor_televisions = database.query(ShopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null,null);
        if(cursor_televisions.moveToFirst()){
            do{

                //Getting image in byte[] format
                image =cursor_televisions.getBlob(cursor_televisions.getColumnIndex(shopHelper.IMAGE_COLUMN));


                //Converting it into bitmap
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2; // Power of 2
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, opts);

                //Setting bitmap into new imageView
                ImageView imageView = new ImageView(getContext());
                imageView.setImageBitmap(bitmap);


                //and adding it to container
                linearLayout_televisions.addView(imageView,layoutParams);
            }while (cursor_televisions.moveToNext());

        }
    }




}
