package com.example.techquick_store;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Filter extends AppCompatActivity {
    Spinner spinner_categories;
    boolean price_tag_opened,brand_tag_opened,rating_tag_opened,
    hddSpace_tag_opened,installedOs_tag_opened ,camerapixels_tag_opened,
            processorType_tag_opened, ram_tag_opened,diagonal_phone_tag_opened,
            tag_diagonal_television_opened,tag_quality_opened,tag_brand_phone_opened,
            tag_brand_television_opened;
    TagPriceFragment tagPriceFragment;
    TagBrandFragment tagBrandFragment;
    TagRatingFragment tagRatingFragment;
    TagHddSpaceFragment tagHddSpaceFragment;
    TagInstalledOSFragment tagInstalledOSFragment;
    TagRamFragment tagRamFragment;
    TagProcessorTypeFragment tagProcessorTypeFragment;
    TagCameraPixelsFragment tagCameraPixelsFragment;
    TagDiagonalPhoneFragment tagDiagonalPhoneFragment;
    TagDiagonalTelevisionFragment tagDiagonalTelevisionFragment;
    TagQualityFragment tagQualityFragment;
    TagBrandPhoneFragment tagBrandPhoneFragment;
    TagBrandTelevisionFragment tagBrandTelevisionFragment;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);
        spinner_categories = findViewById(R.id.spinner_categories);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.category_names));
        spinner_categories.setAdapter(arrayAdapter);

        getSupportFragmentManager().beginTransaction().add(R.id.container_for_filter,new FragForPhones()).commit();

        spinner_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String)parent.getItemAtPosition(position);

                if(selected.equals("Televisions")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_for_filter,new FragForTelevisions()).commit();
                }


                if(selected.equals("Computers")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_for_filter,new FragForComputers()).commit();
                }


                if(selected.equals("Phones")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_for_filter,new FragForPhones()).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tagPriceFragment = new TagPriceFragment();
        tagBrandFragment = new TagBrandFragment();
        tagRatingFragment = new TagRatingFragment();
        tagHddSpaceFragment = new TagHddSpaceFragment();
        tagInstalledOSFragment = new TagInstalledOSFragment();
        tagProcessorTypeFragment = new TagProcessorTypeFragment();
        tagRamFragment = new TagRamFragment();
        tagCameraPixelsFragment = new TagCameraPixelsFragment();
        tagDiagonalPhoneFragment = new TagDiagonalPhoneFragment();
        tagQualityFragment = new TagQualityFragment();
        tagDiagonalTelevisionFragment = new TagDiagonalTelevisionFragment();
        tagBrandPhoneFragment = new TagBrandPhoneFragment();
        tagBrandTelevisionFragment = new TagBrandTelevisionFragment();
    }



    public void openPriceTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!price_tag_opened) {
            transaction.add(R.id.price_tag_container,tagPriceFragment).commit();
            price_tag_opened = true;}
        else{
            transaction.remove(tagPriceFragment).commit();
            price_tag_opened = false;
        }
    }


    public void openBrandTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if(!brand_tag_opened) {
            transaction.add(R.id.brand_tag_container,tagBrandFragment).commit();
            brand_tag_opened = true;}
        else{
            transaction.remove(tagBrandFragment).commit();
            brand_tag_opened = false;
        }
    }


    public void openRatingTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!rating_tag_opened) {
            transaction.add(R.id.rating_tag_container,tagRatingFragment).commit();
            rating_tag_opened = true;}
        else{
            transaction.remove(tagRatingFragment).commit();
            rating_tag_opened = false;
        }
    }




    public void openInstalledOsTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!installedOs_tag_opened) {
            transaction.add(R.id.installedOS_tag_container,tagInstalledOSFragment).commit();
            installedOs_tag_opened = true;}
        else{
            transaction.remove(tagInstalledOSFragment).commit();
            installedOs_tag_opened = false;
        }
    }


    public void openProcessorTypeTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!processorType_tag_opened) {
            transaction.add(R.id.processorType_tag_container,tagProcessorTypeFragment).commit();
            processorType_tag_opened = true;}
        else{
            transaction.remove(tagProcessorTypeFragment).commit();
            processorType_tag_opened = false;
        }
    }




    public void openHDDSpaceTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!hddSpace_tag_opened) {
            transaction.add(R.id.hddSpace_tag_container,tagHddSpaceFragment).commit();
            hddSpace_tag_opened = true;}
        else{
            transaction.remove(tagHddSpaceFragment).commit();
            hddSpace_tag_opened = false;
        }
    }



    public void openRAMTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!ram_tag_opened) {
            transaction.add(R.id.ram_tag_container,tagRamFragment).commit();
            ram_tag_opened = true;}
        else{
            transaction.remove(tagRamFragment).commit();
            ram_tag_opened = false;
        }
    }


    public void openCameraPixelsPhoneTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!camerapixels_tag_opened) {
            transaction.add(R.id.cameraPixels_tag_container,tagCameraPixelsFragment).commit();
            camerapixels_tag_opened = true;}
        else{
            transaction.remove(tagCameraPixelsFragment).commit();
            camerapixels_tag_opened = false;
        }
    }




    public void openDiagonalPhoneTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!diagonal_phone_tag_opened) {
            transaction.add(R.id.diagonal_tag_container,tagDiagonalPhoneFragment).commit();
            diagonal_phone_tag_opened = true;}
        else{
            transaction.remove(tagDiagonalPhoneFragment).commit();
            diagonal_phone_tag_opened = false;
        }
    }



    public void openDiagonalTelevisionTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!tag_diagonal_television_opened) {
            transaction.add(R.id.diagonal_tag_container,tagDiagonalTelevisionFragment).commit();
            tag_diagonal_television_opened = true;}
        else{
            transaction.remove(tagDiagonalTelevisionFragment).commit();
            tag_diagonal_television_opened = false;
        }
    }




    public void openQualityTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!tag_quality_opened) {
            transaction.add(R.id.quality_tag_container,tagQualityFragment).commit();
            tag_quality_opened = true;}
        else{
            transaction.remove(tagQualityFragment).commit();
            tag_quality_opened = false;
        }
    }





    public void openBrandPhoneTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!tag_brand_phone_opened) {
            transaction.add(R.id.brand_phone_tag_container,tagBrandPhoneFragment).commit();
            tag_brand_phone_opened = true;}
        else{
            transaction.remove(tagBrandPhoneFragment).commit();
            tag_brand_phone_opened = false;
        }


    }




    public void openBrandTelevisionTag(View view){
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        if(!tag_brand_television_opened) {
            transaction.add(R.id.brand_television_tag_container,tagBrandTelevisionFragment).commit();
            tag_brand_television_opened = true;}
        else{
            transaction.remove(tagBrandTelevisionFragment).commit();
            tag_brand_television_opened = false;
        }

    }


}
