package com.whf.messagerelayer.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.whf.messagerelayer.confing.Constant;


/**
 * Created by WHF on 2017/3/28.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "contact.db";
    private static final String CREAD_DB_SQL = "CREATE TABLE "+Constant.DB_TABLE_NAME+
            "("+ Constant.DB_KEY_ID+" integer primary key autoincrement" +
            ","+Constant.DB_KEY_NAME+" varchar(20),"+Constant.DB_KEY_MOBLIE+" varchar(20))";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAD_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
