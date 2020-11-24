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

public class AdminTelevisionDetailed extends AppCompatActivity {
    EditText brand, diagonal, quality, price;
    Integer item_id;
    ShopHelper shopHelper;
    SQLiteDatabase database;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_television_page_detailed);

        Intent intent = getIntent();
        item_id =  intent.getIntExtra("item_id",1);


        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();

        brand = findViewById(R.id.brand_name_detailed);
        diagonal = findViewById(R.id.diagonal_name_detailed);
        quality = findViewById(R.id.quality_name_detailed);
        price = findViewById(R.id.price_name_detailed);
        imageView = findViewById(R.id.item_image_detailed);
        setData();
    }


    protected void setData(){
        Cursor cursor = database.query(shopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null);
        cursor.moveToPosition(item_id-1);

        byte[] image;

        brand.setText(cursor.getString(cursor.getColumnIndex(shopHelper.BRAND_COLUMN)));
        diagonal.setText(cursor.getString(cursor.getColumnIndex(shopHelper.DIAGONAL_COLUMN)));
        quality.setText(cursor.getString(cursor.getColumnIndex(shopHelper.QUALITY_COLUMN)));
        price.setText(cursor.getString(cursor.getColumnIndex(shopHelper.PRICE_COLUMN)));
        image = cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length,options);
        imageView.setImageBitmap(bitmap);
    }



    public  void saveItemConfigurations(View view){
        ContentValues cv = new ContentValues();
        cv.put(shopHelper.BRAND_COLUMN,brand.getText().toString());
        cv.put(shopHelper.DIAGONAL_COLUMN,diagonal.getText().toString());
        cv.put(shopHelper.QUALITY_COLUMN,quality.getText().toString());
        cv.put(shopHelper.PRICE_COLUMN,price.getText().toString());
        database.update(shopHelper.TABLE_NAME_TELEVISION,cv,"Id=?",new String[]{item_id+""});



        view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        Toast.makeText(this,"The item configurations has been changed",Toast.LENGTH_SHORT).show();
    }
}
