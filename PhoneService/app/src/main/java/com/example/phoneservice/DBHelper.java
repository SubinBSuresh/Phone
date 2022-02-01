package com.example.phoneservice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, DBVariables.DATABASE_NAME, null, DBVariables.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //CONTACT TABLE
        String createContactTable = "CREATE TABLE " + DBVariables.CONTACTS_TABLE + " ("
                + DBVariables.CONTACT_ID + " INTEGER PRIMARY KEY, " + DBVariables.CONTACT_NAME
                + " TEXT, " + DBVariables.CONTACT_NUMBER + " TEXT" + ")";
        sqLiteDatabase.execSQL(createContactTable);

        //FAVORITE TABLE
        String createFavoriteTable = "CREATE TABLE " + DBVariables.FAVORITES_TABLE + " ("
                + DBVariables.CONTACT_ID + " INTEGER PRIMARY KEY, " + DBVariables.FAVORITE_NAME
                + " TEXT, " + DBVariables.FAVORITE_NUMBER + " TEXT" + ")";
        sqLiteDatabase.execSQL(createFavoriteTable);

        //RECENTS TABLE
        String createRecentTable = "CREATE TABLE " + DBVariables.RECENTS_TABLE + " ("
                + DBVariables.RECENT_ID + " INTEGER PRIMARY KEY, " + DBVariables.RECENT_NAME
                + " TEXT, " + DBVariables.RECENT_NUMBER + " TEXT, " + DBVariables.RECENT_DATE + " TEXT " + ")";
        sqLiteDatabase.execSQL(createRecentTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBVariables.CONTACTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBVariables.FAVORITES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBVariables.RECENTS_TABLE);
        onCreate(sqLiteDatabase);

    }

    //FETCH CONTACTS
    public List<ContactModel> getContacts(){
        List<ContactModel> contactModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.CONTACTS_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                ContactModel contacts = new ContactModel();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setNumber(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return  contactModelList;

    }


    //FETCH FAVORITES
    public List<FavoritesModel> getFavorites(){
        List<FavoritesModel> favoritesModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.FAVORITES_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                FavoritesModel favorites = new FavoritesModel();
                favorites.setId(Integer.parseInt(cursor.getString(0)));
                favorites.setName(cursor.getString(1));
                favorites.setNumber(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return  favoritesModelList;
    }

    //FETCH RECENTS
    public List<RecentsModel> getRecents(){
        List<RecentsModel> recentsModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.RECENTS_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                RecentsModel recents = new RecentsModel();
                recents.setId(Integer.parseInt(cursor.getString(0)));
                recents.setName(cursor.getString(1));
                recents.setNumber(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return  recentsModelList;
    }



}