package com.example.phoneservice;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "PHONE";
    public static final int DATABASE_VERSION = 1;

    //CONTACT TABLE COLUMNS
    private static final String CONTACT_TABLE = "CONTACTS";
    private static final String CONTACT_ID = "ID";
    private static final String CONTACT_NAME = "NAME";
    private static final String CONTACT_NUMBER = "NUMBER";
    //FAVORITES TABLE COLUMN
    private static final String FAVORITES_TABLE = "FAVORITES";
    private static final String FAVORITE_ID = "ID";
    private static final String FAVORITE_NAME = "NAME";
    private static final String FAVORITE_NUMBER = "NUMBER";
    //RECENTS TABLE COLUMN
    private static final String RECENTS_TABLE = "RECENTS";
    public static final String RECENT_ID = "ID";
    public static final String RECENT_NAME = "NAME";
    public static final String RECENT_NUMBER = "NUMBER";
    public static final String RECENT_DATE = "DATE";


    //CREATING TABLES QUERIES
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CONTACT_TABLE + " (" + CONTACT_ID + " INTEGER PRIMARY KEY UNIQUE ," + CONTACT_NAME + " TEXT, " + CONTACT_NUMBER + " TEXT" + ")";
    private static final String CREATE_FAVORITES_TABLE = "CREATE TABLE " + FAVORITES_TABLE + " (" + FAVORITE_ID + " INTEGER PRIMARY KEY UNIQUE ," + FAVORITE_NAME + " TEXT, " + FAVORITE_NUMBER + " TEXT" + ")";
    private static final String CREATE_RECENTS_TABLE = "CREATE TABLE " + RECENTS_TABLE + " (" + RECENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + RECENT_NAME + " TEXT, " + RECENT_NUMBER + " TEXT, " + RECENT_DATE + " TEXT " + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_FAVORITES_TABLE);
        sqLiteDatabase.execSQL(CREATE_RECENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECENTS_TABLE);
        onCreate(sqLiteDatabase);

    }

    /************************************************ CONTACT TABLE OPERATIONS *****************************************************/


    //ADD CONTACTS TO TABLE
    public void saveContact(List<ContactModel> contactModelList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        for (int i = 0; i < contactModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            ContactModel contactModel = contactModelList.get(i);
            if (!checkContactPresentInContactTable(contactModel.getId()))
            { contentValues.put(CONTACT_NAME, contactModel.getName());
            contentValues.put(CONTACT_NUMBER, contactModel.getNumber());
            contentValues.put(CONTACT_ID, contactModel.getId());
            sqLiteDatabase.insert(CONTACT_TABLE, null, contentValues);
        }
        }
    }


    //CONDITION FOR CHECKING IF THE DATA IS ALREADY PRESENT IN TABLE

    // CHECK IF A CONTACT IS ALREADY PRESENT IN TABLE
    public boolean checkContactPresentInContactTable(Integer id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + CONTACT_ID + " = " + id ;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            System.out.println("more than 1");

            return true;
        }
        cursor.close();
        return false;
    }


    //GET CONTACTS
    public List<ContactModel> getContacts() {
        List<ContactModel> contactModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE;
//        String query = "SELECT " + CONTACT_NAME +", " +CONTACT_NUMBER +", " +CONTACT_FAVORITE +", " +CONTACT_ID +" FROM " + CONTACT_TABLE + " WHERE "+ CONTACT_FAVORITE + " = " + "'"+isFav+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setName(cursor.getString(1));
            contactModel.setNumber(cursor.getString(2));
            contactModel.setId(Integer.parseInt(cursor.getString(0)));
            contactModelList.add(contactModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return contactModelList;
    }


    /************************************************************ FAVORITES TABLE OPERATION ************************************************************/


    public List<FavoritesModel> getFavorites() {
        List<FavoritesModel> favoriteModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + FAVORITES_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext()) {
            FavoritesModel favoriteModel = new FavoritesModel();
            favoriteModel.setName(cursor.getString(1));
            favoriteModel.setNumber(cursor.getString(2));
            favoriteModel.setId(cursor.getInt(0));
            favoriteModelList.add(favoriteModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return favoriteModelList;
    }


    // ADD A FAVORITE TO FAVORITES TABLE
    public void addContactToFavorites(Integer id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + CONTACT_ID + " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (!checkContactPresentInFavoritesTable(id)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FAVORITE_ID, cursor.getInt(0));
                contentValues.put(FAVORITE_NAME, cursor.getString(1));
                contentValues.put(FAVORITE_NUMBER, cursor.getString(2));
                sqLiteDatabase.insert(FAVORITES_TABLE, null, contentValues);
            }
        }
        cursor.close();
    }

/*    public void addToFavorites(Integer id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Log.e("#################################", "HERE INSIDE DBHANDLER "+id);

        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + CONTACT_ID + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int contactId = cursor.getInt(0);
        String contactName = cursor.getString(1);
        String contactNumber = cursor.getString(2);
        Log.e("#################################", contactName+contactNumber+contactId);
        String addQuery = "INSERT INTO " + FAVORITES_TABLE + " VALUES (" + contactId + ", " + contactName + ", " + contactNumber + ")";
        sqLiteDatabase.execSQL(addQuery);
        cursor.close();
        sqLiteDatabase.close();
    }*/


    // REMOVE A FAVORITE FROM FAVORITES TABLE
    public void removeContactFromFavorites(Integer id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "DELETE FROM " + FAVORITES_TABLE + " WHERE " + FAVORITE_ID + " = '" + id + "'";
        sqLiteDatabase.execSQL(query);
    }


    // CHECK IF THE A CONTACT IS PRESENT IN THE FAVORITE TABLE
    public boolean checkContactPresentInFavoritesTable(Integer id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + FAVORITES_TABLE + " WHERE " + FAVORITE_ID + " = '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            System.out.println("more than 1 in favorites");
            return true;
        }
        cursor.close();
        return false;
    }

    /******************************************************* RECENTS TABLE OPERATION *****************************************************/


    public void addtoRecent(List<RecentModel> recent) {
        SQLiteDatabase db = this.getReadableDatabase();
        for (int i=0;i<recent.size();i++) {
            ContentValues values = new ContentValues();
            RecentModel recentModel=recent.get(i);
            values.put(RECENT_NAME, recentModel.getName());
            values.put(RECENT_NUMBER, recentModel.getNumber());
            values.put(RECENT_DATE, recentModel.getDate());
            db.insert(RECENTS_TABLE, null, values);
            Log.d("recentsdb", "Successfully inserted");
        }
        db.close();

    }


    //FETCH RECENTS
    public List<RecentModel> getAllRecents() {
        List<RecentModel> recentsModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + RECENTS_TABLE;
         Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext()) {

                RecentModel recents = new RecentModel();
                recents.setId(Integer.parseInt(cursor.getString(0)));
                recents.setName(cursor.getString(1));
                recents.setNumber(cursor.getString(2));
                recents.setDate(cursor.getString(3));
                recentsModelList.add(recents);
            }

        sqLiteDatabase.close();
        Log.e("recenmodellist"," "+recentsModelList.size());
        return recentsModelList;
    }





    /***************************************************OTHERS***************************************************************************/
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