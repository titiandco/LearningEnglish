package com.example.a34011_73_06.learningenglish;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Database extends SQLiteOpenHelper {
    public static final String TABLE_CONTACTWORDS = "contactwords";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMEENGLISH = "nameenglish";
    public static final String COLUMN_NAMEFRENCH = "namefrench";
    public static final String COLUMN_EXAMPLEENGLISH = "exampleenglish";
    public static final String COLUMN_EXAMPLEFRENCH = "examplefrench";

    private static final String DATABASE_NAME = "contactwords.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CONTACTWORDS+ "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_NAMEENGLISH+ " text not null,"
            + COLUMN_NAMEFRENCH+ " text not null,"
            + COLUMN_EXAMPLEENGLISH+ " text ,"
            + COLUMN_EXAMPLEFRENCH+ " text "
            +");";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTWORDS);
        onCreate(db);
    }
}
