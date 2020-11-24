package com.example.techquick_store;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ItemsAll extends AppCompatActivity {
    ShopHelper shopHelper;
    SQLiteDatabase database;
    TextView id,brand,edit;
    ImageView image;
    int comp_num = 0;
    int tel_num = 0;
    HashMap<View,Integer> editView_itemID_map;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_items);

        LinearLayout container_for_items = findViewById(R.id.container_for_items);

        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();

        editView_itemID_map = new HashMap<>();

        Cursor cursor_tel = database.query(shopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null);
        Cursor cursor_phone = database.query(shopHelper.TABLE_NAME_PHONE,null,null,null,null,null,null);
        Cursor cursor_comp = database.query(shopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null);



        if(cursor_comp.moveToFirst()){
            do{

                View view = getLayoutInflater().inflate(R.layout.one_item_admin_layout,null,false);

                id = view.findViewById(R.id.id);
                brand = view.findViewById(R.id.brand);
                image = view.findViewById(R.id.image);
                edit = view.findViewById(R.id.edit_button);

                int item_id = Integer.parseInt(cursor_comp.getString(cursor_comp.getColumnIndex(shopHelper.ID_COLUMN)));
                id.setText(item_id+".");
                brand.setText(cursor_comp.getString(cursor_comp.getColumnIndex(shopHelper.BRAND_COLUMN)));

                byte[] image_byte;
                image_byte = cursor_comp.getBlob(cursor_comp.getColumnIndex(shopHelper.IMAGE_COLUMN));


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte,0,image_byte.length,options);
                image.setImageBitmap(bitmap);

                container_for_items.addView(view);
                comp_num++;

                editView_itemID_map.put(edit,Integer.parseInt(cursor_comp.getString(cursor_comp.getColumnIndex(shopHelper.ID_COLUMN))));


            }while (cursor_comp.moveToNext());

        }




        if(cursor_tel.moveToFirst()){
            do{

                View view = getLayoutInflater().inflate(R.layout.one_item_admin_layout,null,false);

                id = view.findViewById(R.id.id);
                brand = view.findViewById(R.id.brand);
                image = view.findViewById(R.id.image);
                edit = view.findViewById(R.id.edit_button);


                int item_id = Integer.parseInt(cursor_tel.getString(cursor_tel.getColumnIndex(shopHelper.ID_COLUMN)))+(comp_num);


                id.setText(item_id+".");
                brand.setText(cursor_tel.getString(cursor_tel.getColumnIndex(shopHelper.BRAND_COLUMN)));

                byte[] image_byte;
                image_byte = cursor_tel.getBlob(cursor_tel.getColumnIndex(shopHelper.IMAGE_COLUMN));


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte,0,image_byte.length,options);
                image.setImageBitmap(bitmap);

                container_for_items.addView(view);
                tel_num++;





                editView_itemID_map.put(edit,item_id);

            }while (cursor_tel.moveToNext());

        }








        if(cursor_phone.moveToFirst()){
            do{

                View view = getLayoutInflater().inflate(R.layout.one_item_admin_layout,null,false);

                id = view.findViewById(R.id.id);
                brand = view.findViewById(R.id.brand);
                image = view.findViewById(R.id.image);
                edit = view.findViewById(R.id.edit_button);


                int item_id = Integer.parseInt(cursor_phone.getString(cursor_phone.getColumnIndex(shopHelper.ID_COLUMN)))+(comp_num+tel_num);

                id.setText(item_id+".");
                brand.setText(cursor_phone.getString(cursor_phone.getColumnIndex(shopHelper.BRAND_COLUMN)));

                byte[] image_byte;
                image_byte = cursor_phone.getBlob(cursor_phone.getColumnIndex(shopHelper.IMAGE_COLUMN));


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte,0,image_byte.length,options);
                image.setImageBitmap(bitmap);

                container_for_items.addView(view);

                editView_itemID_map.put(edit,item_id);


            }while (cursor_phone.moveToNext());

        }

    }



    public void editItem(View view){
        Intent intent = new Intent(this,AdminComputerDetailed.class);
        int item_id = editView_itemID_map.get(view);


        //It means phones
        if(item_id > comp_num + tel_num){
            item_id = item_id - comp_num - tel_num;
            intent = new Intent(this,AdminPhoneDetailed.class);
        }


        //It means tel
        else if(item_id > comp_num){
            item_id = item_id - comp_num;
            intent = new Intent(this,AdminTelevisionDetailed.class);
        }


        intent.putExtra("item_id",item_id);
        startActivity(intent);

    }
}
