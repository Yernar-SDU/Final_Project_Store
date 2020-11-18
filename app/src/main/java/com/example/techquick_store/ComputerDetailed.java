package com.example.techquick_store;

import android.content.ContentValues;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ComputerDetailed extends AppCompatActivity {
    SQLiteDatabase database;
    ShopHelper shopHelper;
    TextView brand,hdd_space,processorType,installedOS,RAM,price;
    ImageView itemImage;
    Integer userID,itemID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computer_page_detailed);
        //get id's of views
        findViewByIds();

        //Database declaring
        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();


        //Get id's of user and item
        Intent intent = getIntent();
        itemID = intent.getIntExtra("item_id",2);
        userID = intent.getIntExtra("user_id",2);



        gettingData(itemID-1);}



    public void gettingData(int position){
        Cursor cursor = database.query(shopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null);
        cursor.moveToPosition(position);

        brand.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.BRAND_COLUMN)));
        hdd_space.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.HDD_SPACE_COLUMN))+"GB");
        processorType.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.PROCESSORTYPE_COLUMN)));
        installedOS.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.INSTALLEDOS_COLUMN)));
        RAM.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.RAM_COLUMN))+"GB");
        price.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.PRICE_COLUMN))+"₸");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        byte[] image =cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length,options);
        itemImage.setImageBitmap(bitmap);



    }




    public void addToBasket(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put(shopHelper.USER_ID,userID);
        contentValues.put(shopHelper.ITEM_ID,itemID);
        contentValues.put(shopHelper.ITEM_CLASS,shopHelper.TABLE_NAME_COMPUTERS);
        database.insert(shopHelper.TABLE_NAME_BASKET,null,contentValues);



        Toast toast = Toast.makeText(this,"Item has added to basket",Toast.LENGTH_LONG);
        toast.show();
    }

















    public void findViewByIds(){
        brand = findViewById(R.id.brand_name_detailed);
        hdd_space = findViewById(R.id.hdd_space_name_detailed);
        processorType = findViewById(R.id.processor_type_name_detailed);
        installedOS = findViewById(R.id.installed_OS_name_detailed);
        RAM = findViewById(R.id.ram_name_detailed);
        price = findViewById(R.id.price_name_detailed);
        itemImage = findViewById(R.id.item_image_detailed);
    }

}
