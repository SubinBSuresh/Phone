package com.example.phoneservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, DBVariables.DATABASE_NAME, null, DBVariables.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //CONTACT TABLE
        String createContactTable = "CREATE TABLE " + DBVariables.CONTACTS_TABLE + " ("
                + DBVariables.CONTACT_ID + " INTEGER PRIMARY KEY, AUTO_INCREMENT" + DBVariables.CONTACT_NAME
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
    public List<ContactModel> getContacts() {
        List<ContactModel> contactModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.CONTACTS_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ContactModel contacts = new ContactModel();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setNumber(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return contactModelList;

    }

    //FavoriteTableHandler
    public void addFavorite(FavoritesModel favorite) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBVariables.FAVORITE_NAME, favorite.getName());
        values.put(DBVariables.FAVORITE_NUMBER, favorite.getNumber());
        db.insert(DBVariables.FAVORITES_TABLE, null, values);
        db.close();
    }

    public List<FavoritesModel> getAllFavorites() {
        List<FavoritesModel> favoritesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //Generate the query to read from the database
        String select = "SELECT * FROM " + DBVariables.FAVORITES_TABLE;
        Cursor cursor = db.rawQuery(select, null);
        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                FavoritesModel favorites = new FavoritesModel();
                favorites.setId(Integer.parseInt(cursor.getString(0)));
                favorites.setName(cursor.getString(1));
                favorites.setNumber(cursor.getString(2));
                favoritesList.add(0, favorites);
            } while (cursor.moveToNext());
        }
        db.close();
        return favoritesList;

    }

    public int updateFavorite(FavoritesModel favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBVariables.FAVORITE_NAME, favorite.getName());
        values.put(DBVariables.FAVORITE_NUMBER, favorite.getNumber());
        //Update now, return of update() is number of affected rows
        return db.update(DBVariables.FAVORITES_TABLE, values, DBVariables.FAVORITE_ID + "=?",
                new String[]{String.valueOf(favorite.getId())});


    }

    public void deleteFavoriteById(int favorite_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBVariables.FAVORITES_TABLE, DBVariables.FAVORITE_ID + "=?", new String[]{String.valueOf(favorite_id)});
        db.close();

    }

    public void deleteFavorite(FavoritesModel favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBVariables.FAVORITES_TABLE, DBVariables.FAVORITE_ID + "=?", new String[]{String.valueOf(favorite.getId())});
        db.close();
    }

    public int getFavoritesCount() {
        String query = "SELECT * FROM " + DBVariables.FAVORITES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public void addRecent(RecentModel recent) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBVariables.RECENT_NAME, recent.getName());
        values.put(DBVariables.RECENT_NUMBER, recent.getNumber());
        values.put(DBVariables.RECENT_DATE, recent.getDate());
        db.insert(DBVariables.RECENTS_TABLE, null, values);
        Log.d("recentsdb", "Successfully inserted");
        db.close();
    }
    //FETCH RECENTS
    public List<RecentModel> getAllRecents() {
        List<RecentModel> recentsModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.RECENTS_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                RecentModel recents = new RecentModel();
                recents.setId(Integer.parseInt(cursor.getString(0)));
                recents.setName(cursor.getString(1));
                recents.setNumber(cursor.getString(2));
                recents.setDate(cursor.getString(3));
                recentsModelList.add(0,recents);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return recentsModelList;
    }


    //GET SUGGESTION CONTACTS
/*    public List<SuggestionModel> getContactSuggestion(String searchedNumber) {
        List<SuggestionModel> suggestionModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + DBVariables.CONTACT_NAME + ", " + DBVariables.CONTACT_NUMBER + " FROM " + DBVariables.CONTACTS_TABLE + " WHERE " + DBVariables.CONTACT_NUMBER + " LIKE " + "'" + searchedNumber + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext()) {
            SuggestionModel suggestionModel = new SuggestionModel();
            suggestionModel.setContactName(cursor.getString(0));
            suggestionModel.setContactNumber(cursor.getString(1));
            suggestionModelList.add(suggestionModel);
        }
        sqLiteDatabase.close();
        return suggestionModelList;
    }*/







}