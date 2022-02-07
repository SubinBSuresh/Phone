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
import ServicePackage.RecentsModel;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, DBVariables.DATABASE_NAME, null, DBVariables.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CONTACT TABLE
        String createContactTable = "CREATE TABLE " + DBVariables.CONTACTS_TABLE + " ("
                + DBVariables.CONTACT_ID + " INTEGER PRIMARY KEY, AUTO_INCREMENT" + DBVariables.CONTACT_NAME
                + " TEXT, " + DBVariables.CONTACT_NUMBER + " TEXT" + ")";
        db.execSQL(createContactTable);

        //FAVORITE TABLE
        String createFavoriteTable = "CREATE TABLE " + DBVariables.FAVORITES_TABLE + " ("
                + DBVariables.CONTACT_ID + " INTEGER PRIMARY KEY, " + DBVariables.FAVORITE_NAME
                + " TEXT, " + DBVariables.FAVORITE_NUMBER + " TEXT" + ")";
        db.execSQL(createFavoriteTable);

        //RECENTS TABLE
        String createRecentTable = "CREATE TABLE " + DBVariables.RECENTS_TABLE + " ("
                + DBVariables.RECENT_ID + " INTEGER PRIMARY KEY, " + DBVariables.RECENT_NAME
                + " TEXT, " + DBVariables.RECENT_NUMBER + " TEXT, " + DBVariables.RECENT_DATE + " TEXT " + ")";
        db.execSQL(createRecentTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + DBVariables.CONTACTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBVariables.FAVORITES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBVariables.RECENTS_TABLE);
        onCreate(db);

    }

    //ContactTableHandler
    public void addContact(ContactModel contact) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBVariables.CONTACT_NAME, contact.getName());
        values.put(DBVariables.CONTACT_NUMBER, contact.getNumber());
        db.insert(DBVariables.CONTACTS_TABLE, null, values);
        Log.d("contactsdb", "Successfully inserted");
        db.close();
    }

    //FETCH CONTACTS
    public List<ContactModel> getContacts(){
        List<ContactModel> contactModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBVariables.CONTACTS_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                ContactModel contacts = new ContactModel();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setNumber(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        db.close();
        return contactModelList;

    }
    public int updateContact(ContactModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBVariables.CONTACT_NAME, contact.getName());
        values.put(DBVariables.CONTACT_NUMBER, contact.getNumber());
        //Update now, return of update() is number of affected rows
        return db.update(DBVariables.CONTACTS_TABLE, values, DBVariables.CONTACT_ID + "=?",
                new String[]{String.valueOf(contact.getId())});


    }

    public void deleteContactById(int contact_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBVariables.CONTACTS_TABLE, DBVariables.CONTACT_ID + "=?", new String[]{String.valueOf(contact_id)});
        db.close();

    }

    public void deleteContact(ContactModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBVariables.CONTACTS_TABLE, DBVariables.CONTACT_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactsCount() {
        String query = "SELECT * FROM " + DBVariables.CONTACTS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
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




    /* FETCH RECENTS
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
*/

























/*    //TEMP
    public void addContact(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + DBVariables.CONTACTS_TABLE + "VALUES (" + name + ", " + number + ")";
        db.execSQL(query);
    }*/


}