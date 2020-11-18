package com.example.techquick_store;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Basket extends Fragment {
    ShopHelper shopHelper;
    SQLiteDatabase database;
    Integer userID;

    Basket(Integer userID){
        this.userID = userID;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ShopHelper shopHelper = new ShopHelper(getContext());
        database = shopHelper.getWritableDatabase();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View main_view = inflater.inflate(R.layout.basket_layout,container,false);
        LinearLayout basket_items = main_view.findViewById(R.id.basket_items);





        ArrayList<Integer> computer_positions = new ArrayList<Integer>();
        ArrayList<Integer> phone_positions = new ArrayList<Integer>();
        ArrayList<Integer> television_positions = new ArrayList<Integer>();


        Cursor cursor = database.query(shopHelper.TABLE_NAME_BASKET,null,null,null,null,null,null,null);



        if(cursor.moveToFirst()){

            do {
                int user_id_basket = cursor.getInt(cursor.getColumnIndex(shopHelper.USER_ID));
                String item_class_basket = cursor.getString(cursor.getColumnIndex(shopHelper.ITEM_CLASS));
                int item_id_basket = cursor.getInt(cursor.getColumnIndex(shopHelper.ITEM_ID));

                Log.i("tag", item_class_basket + " : " + shopHelper.TABLE_NAME_TELEVISION);
                if( user_id_basket == userID && item_class_basket.equals(shopHelper.TABLE_NAME_COMPUTERS)){
                    computer_positions.add(item_id_basket);
                }

                else if( user_id_basket == userID && item_class_basket.equals(shopHelper.TABLE_NAME_PHONE)){
                    phone_positions.add(item_id_basket);
                }
                else if (user_id_basket == userID){
                    television_positions.add(item_id_basket);
                }
            }while (cursor.moveToNext());
        }


        for (int i = 0; i < computer_positions.size() ; i++) {
            basket_items.addView(insertData(shopHelper.TABLE_NAME_COMPUTERS,computer_positions.get(i) - 1));
        }

        for (int i = 0; i < phone_positions.size() ; i++) {
            basket_items.addView(insertData(shopHelper.TABLE_NAME_PHONE,phone_positions.get(i) - 1));
        }

        for (int i = 0; i < television_positions.size() ; i++) {
            basket_items.addView(insertData(shopHelper.TABLE_NAME_TELEVISION,television_positions.get(i) - 1));
        }

        television_positions.clear();
        phone_positions.clear();
        computer_positions.clear();

        return main_view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }





    public View insertData(String items_name,int position){
        Cursor cursor;

        View item_view;

        if(items_name.equals("Computers")){
            cursor = database.query(ShopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null);
        }
        else if(items_name.equals("Phones")){
            cursor = database.query(ShopHelper.TABLE_NAME_PHONE,null,null,null,null,null,null);
        }
        else {
            cursor = database.query(ShopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null);
        }

        byte[] image;

        cursor.moveToPosition(position);

        item_view = getLayoutInflater().inflate(R.layout.one_row_item_layout,null,false);
        //item item_views (finding by id)
        ImageView item_image = item_view.findViewById(R.id.item_image);
        TextView item_price = item_view.findViewById(R.id.item_price);
        TextView item_name = item_view.findViewById(R.id.item_name);
        TextView item_characteristics = item_view.findViewById(R.id.item_characteristics);
        TextView item_class = item_view.findViewById(R.id.item_class);
        TextView item_id = item_view.findViewById(R.id.item_id);

        //Getting image in byte[] format
        image =cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));



        ImageView delete = item_view.findViewById(R.id.delete);
        TextView buy_button = item_view.findViewById(R.id.button_buy);
        delete.setVisibility(View.VISIBLE);
        buy_button.setVisibility(View.INVISIBLE);



        //Converting it into bitmap
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4; // Power of 2
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, opts);

        //Copying data from database and pasting on_row_item_layout

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


        return item_view;
    }
}
