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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelevisionDetailed extends AppCompatActivity {
    SQLiteDatabase database;
    ShopHelper shopHelper;
    TextView brand,diagonal,quality,price;
    String rating;
    ImageView itemImage;
    Integer userID,itemID;
    boolean logged_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.television_page_detailed);
        findViewByIds();



        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();


        //Get id's of user and item
        Intent intent = getIntent();
        itemID = intent.getIntExtra("item_id",2);
        userID = intent.getIntExtra("user_id",2);
        logged_in = intent.getBooleanExtra("logged_in",false);


        gettingData(itemID-1);
    }



    public void gettingData(int position){
        Cursor cursor = database.query(shopHelper.TABLE_NAME_TELEVISION,null,null,null,null,null,null);
        cursor.moveToPosition(position);

        rating = cursor.getString(cursor.getColumnIndex(shopHelper.RATING_COLUMN)) +" star";
        brand.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.BRAND_COLUMN)));
        price.setText(":  "+cursor.getString(cursor.getColumnIndex(shopHelper.PRICE_COLUMN))+"₸");
        diagonal.setText(":  " + cursor.getString(cursor.getColumnIndex(shopHelper.DIAGONAL_COLUMN))+" inches");
        quality.setText(":  " + cursor.getString(cursor.getColumnIndex(shopHelper.QUALITY_COLUMN)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        byte[] image =cursor.getBlob(cursor.getColumnIndex(shopHelper.IMAGE_COLUMN));
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length,options);
        itemImage.setImageBitmap(bitmap);



    }






    public void addToBasket(View view){
        if (logged_in == true){
            ContentValues contentValues = new ContentValues();
            contentValues.put(shopHelper.USER_ID,userID);
            contentValues.put(shopHelper.ITEM_ID,itemID);
            contentValues.put(shopHelper.ITEM_CLASS,shopHelper.TABLE_NAME_COMPUTERS);
            database.insert(shopHelper.TABLE_NAME_BASKET,null,contentValues);
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
            Toast.makeText(this,"Item has added to basket",Toast.LENGTH_LONG).show();
        }else{
            ProfileDialog profileDialog = new ProfileDialog();
            profileDialog.show(getSupportFragmentManager(),"dialog");
        }
    }














    public void findViewByIds(){
        brand = findViewById(R.id.brand_name_detailed);
        price = findViewById(R.id.price_name_detailed);
        itemImage = findViewById(R.id.item_image_detailed);
        diagonal = findViewById(R.id.diagonal_name_detailed);
        quality = findViewById(R.id.quality_name_detailed);
    }
}
