package com.example.techquick_store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteUserDialog extends AppCompatDialogFragment {
    Integer user_id;
    UserLoginHelper userLoginHelper;
    ShopHelper shopHelper;
    SQLiteDatabase database;
    SQLiteDatabase shop_database;
    DeleteUserDialog(Integer user_id){
        this.user_id = user_id;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        userLoginHelper = new UserLoginHelper(getContext());
        database = userLoginHelper.getWritableDatabase();
        shop_database = shopHelper.getWritableDatabase();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Account")
                .setMessage("Do you want to delete this user?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.delete(userLoginHelper.TABLE_NAME, "Id=?",new String[]{user_id+""});
                        shop_database.delete(shopHelper.TABLE_NAME_BASKET, "Id=?",new String[]{user_id+""});
                        Intent intent = new Intent(getActivity(),UsersAll.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return  builder.create();
    }



}
