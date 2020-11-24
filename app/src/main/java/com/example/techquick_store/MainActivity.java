package com.example.techquick_store;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TextView computers,phones,televisions;
    ShopHelper shopHelper;
    SQLiteDatabase database;
    boolean logged_in;
    LinearLayout nav_bar;
    Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set database
        shopHelper = new ShopHelper(this);
        database = shopHelper.getWritableDatabase();


        //Set all view id's
        findViewByIds();

        //Set activity with MainPage fragment
        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment,new MainPage()).commit();

        //set nav bar
        nav_bar = findViewById(R.id.nav_bar);
        nav_bar.setBackgroundResource(R.drawable.background_blue);


        //Get information is user logged in or not
        Intent intent = getIntent();
        logged_in = intent.getBooleanExtra("logged_in",false);



        //Get id of user
        userId = intent.getIntExtra("user_id",-1);


        //Close focus on keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    public void openFilterPage(View view){
        Intent intent = new Intent(MainActivity.this,Filter.class);
        startActivity(intent);
    }


    public void openSearchPage(View view){
        //if computers clicked
        if(view.getId()==R.id.computers){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Search("Computers",database)).commit();

        }

        //if phones clicked
        if(view.getId()==R.id.phones){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Search("Phones",database)).commit();
        }

        //if televisions clicked
        if(view.getId()==R.id.televisions){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Search("Televisions",database)).commit();
        }

    }


    public void openMainPage(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new MainPage()).commit();

    }

    public void openProfilePage(View view){
        if(logged_in == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Profile(userId)).commit();
        }
        else{
            ProfileDialog profileDialog = new ProfileDialog();
            profileDialog.show(getSupportFragmentManager(),"example dialog");
        }

    }


    public void openBasketPage(View view){
        if(userId == -1){
            ProfileDialog profileDialog = new ProfileDialog();
            profileDialog.show(getSupportFragmentManager(),"example dialog");
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new Basket(userId)).commit();
        }
    }



    public void openSignInPage(View view){
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }


    //Opens details of clicked item
    public void openDetailPage(View view) {
        TextView class_text = view.findViewById(R.id.item_class);
        TextView id_text = view.findViewById(R.id.item_id);
        String class_name = class_text.getText().toString();

        String id_name = id_text.getText().toString();
        Intent intent;
        if(class_name.equals("Computers")){
            intent = new Intent(this,ComputerDetailed.class);
        }
        else if(class_name.equals("Phones")){
            intent = new Intent(this,PhoneDetailed.class);
        }
        else{
            intent = new Intent(this,TelevisionDetailed.class);
        }
        intent.putExtra("logged_in",logged_in);
        intent.putExtra("item_id",Integer.parseInt(id_name));
        intent.putExtra("user_id",userId);
        startActivity(intent);
    }



    public void findViewByIds(){
        frameLayout = findViewById(R.id.nav_host_fragment);
        computers = findViewById(R.id.computers);
        phones = findViewById(R.id.phones);
        televisions = findViewById(R.id.televisions);
    }


    public void logOut(View view){
        LogoutDialog logoutDialog = new LogoutDialog();
        logoutDialog.show(getSupportFragmentManager(),"ads");
    }





    public void deleteFromBasket(View view){}



    @Override
    public void onBackPressed() {
        openMainPage(computers);
    }











}