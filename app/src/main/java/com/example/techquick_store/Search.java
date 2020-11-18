package com.example.techquick_store;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Search extends Fragment {
    ShopHelper shopHelper;
    SQLiteDatabase shopDatabase;
    String items_name;
    boolean first_time_opened = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_layout,null,false);
    }


    Search(String items_name,SQLiteDatabase shopDatabase){
        this.shopDatabase = shopDatabase;
        this.items_name = items_name;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (first_time_opened) {
            insertData();
            first_time_opened = false;
        }
    }


    public void insertData(){
        ViewGroup view = (ViewGroup) getView();

        Cursor cursor;

        if(items_name.equals("Computers")){
            cursor = shopDatabase.query(ShopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null);
        }
        else if(items_name.equals("Phones")){
            cursor = shopDatabase.query(ShopHelper.TABLE_NAME_PHONE,null,null,null,null,null,null);
        }
        else {
            cursor = shopDatabase.query(ShopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null);
        }

        byte[] image;

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.items);
        if(cursor.moveToFirst()){
            do{





                View item_view = getLayoutInflater().inflate(R.layout.one_row_item_layout,null,false);
                //item item_views (finding by id)
                ImageView item_image = item_view.findViewById(R.id.item_image);
                TextView item_price = item_view.findViewById(R.id.item_price);
                TextView item_name = item_view.findViewById(R.id.item_name);
                TextView item_characteristics = item_view.findViewById(R.id.item_characteristics);
                TextView item_class = item_view.findViewById(R.id.item_class);
                TextView item_id = item_view.findViewById(R.id.item_id);

                //Getting image in byte[] format
                image =cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));





                //Converting it into bitmap
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 4; // Power of 2
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, opts);

                //Copying data from database and pasting on_row_item_layout
                try {
                    item_id.setText(cursor.getString(cursor.getColumnIndex(shopHelper.ID_COLUMN)));
                    item_name.setText(cursor.getString(cursor.getColumnIndex(shopHelper.BRAND_COLUMN)));
                    item_image.setImageBitmap(bitmap);
                    item_price.setText(cursor.getString(cursor.getColumnIndex(shopHelper.PRICE_COLUMN))+"₸");
                    if(items_name.equals("Computers")){
                        item_class.setText("Computers");
                        item_characteristics.setText(
                                cursor.getString(cursor.getColumnIndex(shopHelper.HDD_SPACE_COLUMN))+ "GB " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.PROCESSORTYPE_COLUMN))+ " " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.INSTALLEDOS_COLUMN))+ " " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.RATING_COLUMN))+ "star RAM:" +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.RAM_COLUMN)) +"GB"

                        );
                    }
                    else if(items_name.equals("Phones")){
                        item_class.setText("Phones");
                        item_characteristics.setText(
                                cursor.getString(cursor.getColumnIndex(shopHelper.RATING_COLUMN))+ "star " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.CAMERA_PIXELS_COLUMN))+ "px " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.DIAGONAL_COLUMN))+ "inches RAM" +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.RAM_COLUMN))+"GB") ;
                    }
                    else {
                        item_class.setText("Televisions");
                        item_characteristics.setText(
                                cursor.getString(cursor.getColumnIndex(shopHelper.RATING_COLUMN))+ "star " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.DIAGONAL_COLUMN))+ "inches " +
                                        cursor.getString(cursor.getColumnIndex(shopHelper.QUALITY_COLUMN))

                        );
                    }
                }catch (Exception exception){}





                //and adding it to container
                layout.addView(item_view,0);
            }while (cursor.moveToNext());

        }
    }










}
