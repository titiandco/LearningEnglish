package com.example.a34011_73_06.learningenglish;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class ContactWord {
    public long id;
    public String nameenglish;
    public String namefrench;
    public String exampleenglish;
    public String examplefrench;

    public long getId() {
        return id;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        String text;
        if (ElementsActivity.angfr == true){
            text = nameenglish + " --> "+namefrench;
        }
        else{
            text = namefrench + " --> "+nameenglish;
        }
        return text;
    }
    private SQLiteDatabase database;
    private Database dbHelper;
    private String[] allColumns = { Database.COLUMN_ID,
            Database.COLUMN_NAMEENGLISH,
            Database.COLUMN_NAMEFRENCH,
            Database.COLUMN_EXAMPLEENGLISH,
            Database.COLUMN_EXAMPLEFRENCH};

    public ContactWord(Context context) {
        dbHelper = new Database(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ContactWord createContact(String nameenglish,String namefrench, String exampleenglish, String examplefrench) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAMEENGLISH, nameenglish);
        values.put(Database.COLUMN_NAMEFRENCH, namefrench);
        values.put(Database.COLUMN_EXAMPLEENGLISH, exampleenglish);
        values.put(Database.COLUMN_EXAMPLEFRENCH, examplefrench);
        long insertId = database.insert(Database.TABLE_CONTACTWORDS, null,
                values);
        Cursor cursor = database.query(Database.TABLE_CONTACTWORDS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ContactWord newContact= cursorToContact(cursor);
        cursor.close();
        return newContact;
    }

    public void updateContact(long id, String nameenglish, String namefrench, String exampleenglish, String examplefrench) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAMEENGLISH, nameenglish);
        values.put(Database.COLUMN_NAMEFRENCH, namefrench);
        values.put(Database.COLUMN_EXAMPLEENGLISH, exampleenglish);
        values.put(Database.COLUMN_EXAMPLEFRENCH, examplefrench);
        String where = "id=?";
        String[] whereargs = new String[]{String.valueOf(id)};
        long insertId = database.update(Database.TABLE_CONTACTWORDS,
                values,where,whereargs );
        Cursor cursor = database.query(Database.TABLE_CONTACTWORDS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        cursor.close();

    }

    public void deleteContact(long id) {
        System.out.println("Contact deleted with id: " + id);
        database.delete(Database.TABLE_CONTACTWORDS, Database.COLUMN_ID
                + " = " + id, null);
    }

    public List<ContactWord> getAll() {
        List<ContactWord> comments = new ArrayList<ContactWord>();

        Cursor cursor;

        if (ElementsActivity.angfr == true) {
            cursor = database.query(Database.TABLE_CONTACTWORDS,
                    allColumns, null, null, null, null, Database.COLUMN_NAMEENGLISH);
        }
        else {
            cursor = database.query(Database.TABLE_CONTACTWORDS,
                    allColumns, null, null, null, null, Database.COLUMN_NAMEFRENCH);
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ContactWord contact= cursorToContact(cursor);
            comments.add(contact);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private ContactWord cursorToContact(Cursor cursor) {
        ContactWord c = new ContactWord(null);
        c.id = cursor.getLong(0);
        c.nameenglish = cursor.getString(1);
        c.namefrench = cursor.getString(2);
        c.exampleenglish = cursor.getString(3);
        c.examplefrench = cursor.getString(4);
        return c;
    }
}
