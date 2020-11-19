package com.example.techquick_store;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ChangeStatusDialog extends AppCompatDialogFragment {
    int user_id;
    SQLiteDatabase database;
    UserLoginHelper userLoginHelper;

    ChangeStatusDialog(int user_id){
        this.user_id = user_id;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        userLoginHelper = new UserLoginHelper(getContext());
        database = userLoginHelper.getWritableDatabase();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Status")
                .setMessage("Do yo want to block/activate this user?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Cursor cursor = database.query(userLoginHelper.TABLE_NAME,null,null,null,null,null,null);
                        cursor.moveToPosition(user_id-1);
                        String status = cursor.getString(cursor.getColumnIndex(userLoginHelper.USER_COLUMN_STATUS));

                        ContentValues cv = new ContentValues();
                        if(status.equals("Active")){
                            cv.put(userLoginHelper.USER_COLUMN_STATUS,"Blocked");
                        }else{
                            cv.put(userLoginHelper.USER_COLUMN_STATUS,"Active");
                        }
                        database.update(userLoginHelper.TABLE_NAME,cv,"id=?",new String[]{user_id+""});


                        Intent intent = new Intent(getActivity(),ManagerActivity.class);
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
