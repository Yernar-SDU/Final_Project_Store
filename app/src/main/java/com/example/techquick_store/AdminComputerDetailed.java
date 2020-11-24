package com.example.techquick_store;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminComputerDetailed extends AppCompatActivity {
    EditText brand, installedOS, processorType, RAM, HDDSpace, price;
    Integer item_id;
    ShopHelper shopHelper;
    SQLiteDatabase database;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_computer_page_detailed);

        Intent intent = getIntent();
        item_id =  intent.getIntExtra("item_id",1);

        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();

        imageView = findViewById(R.id.item_image_detailed);
        brand = findViewById(R.id.brand_name_detailed);
        price = findViewById(R.id.price_name_detailed);
        installedOS = findViewById(R.id.installed_OS_name_detailed);
        processorType = findViewById(R.id.processor_type_name_detailed);
        RAM = findViewById(R.id.ram_name_detailed);
        HDDSpace = findViewById(R.id.hdd_space_name_detailed);

        setData();
    }



    protected void setData(){
        Cursor cursor = database.query(shopHelper.TABLE_NAME_COMPUTERS,null,null,null,null,null,null);
        cursor.moveToPosition(item_id-1);

        byte[] image;
        HDDSpace.setText(cursor.getString(cursor.getColumnIndex(shopHelper.HDD_SPACE_COLUMN)));
        brand.setText(cursor.getString(cursor.getColumnIndex(shopHelper.BRAND_COLUMN)));
        RAM.setText(cursor.getString(cursor.getColumnIndex(shopHelper.RAM_COLUMN)));
        installedOS.setText(cursor.getString(cursor.getColumnIndex(shopHelper.INSTALLEDOS_COLUMN)));
        processorType.setText(cursor.getString(cursor.getColumnIndex(shopHelper.PROCESSORTYPE_COLUMN)));
        price.setText(cursor.getString(cursor.getColumnIndex(shopHelper.PRICE_COLUMN)));
        image = cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length,options);
        imageView.setImageBitmap(bitmap);
    }



    public void saveItemConfigurations(View view){
        ContentValues cv = new ContentValues();
        cv.put(shopHelper.BRAND_COLUMN,brand.getText().toString());
        cv.put(shopHelper.HDD_SPACE_COLUMN,HDDSpace.getText().toString());
        cv.put(shopHelper.RAM_COLUMN,RAM.getText().toString());
        cv.put(shopHelper.INSTALLEDOS_COLUMN,installedOS.getText().toString());
        cv.put(shopHelper.PROCESSORTYPE_COLUMN,processorType.getText().toString());
        cv.put(shopHelper.PRICE_COLUMN,price.getText().toString());
        database.update(shopHelper.TABLE_NAME_COMPUTERS,cv,"Id=?",new String[]{item_id+""});


        view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        Toast.makeText(this,"The item configurations has been changed",Toast.LENGTH_SHORT).show();
    }
}
