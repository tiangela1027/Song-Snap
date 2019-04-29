package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidtutorialshub.loginregister.model.Song;
import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class SongDBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SongManager.db";

    // User table name
    private static final String TABLE_SONG = "songs";

    // User Table Columns names
    private static final String COLUMN_SONG_ID = "song_id";
    private static final String COLUMN_SONG_NAME = "song_name";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_SONG + "("
            + COLUMN_SONG_ID + " TEXT PRIMARY KEY," + COLUMN_SONG_NAME + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_SONG;

    /**
     * Constructor
     *
     * @param context
     */
    public SongDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param song
     */
    public void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_NAME, song.getName());
        values.put(COLUMN_SONG_ID, song.getId());

        // Inserting Row
        db.insert(TABLE_SONG, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Song> getAllSongs() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_SONG_ID,
                COLUMN_SONG_NAME,
        };
        // sorting orders
        String sortOrder =
                COLUMN_SONG_NAME + " ASC";
        List<Song> songList = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_SONG, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Song song = new Song();
                song.setId(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_ID)));
                song.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_NAME)));
                // Adding user record to list
                songList.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return songList;
    }

    /**
     * This method to update user record
     *
     * @param song
     */
    public void updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_NAME, song.getName());

        // updating row
        db.update(TABLE_SONG, values, COLUMN_SONG_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param song
     */
    public void deleteSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_SONG, COLUMN_SONG_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param id
     * @return true/false
     */

    public boolean checkSong (String id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SONG_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SONG_ID + " = ?";

        // selection argument
        String[] selectionArgs = {id};

        Cursor cursor = db.query(TABLE_SONG, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */

    /*
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SONG_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        Cursor cursor = db.query(TABLE_SONG, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    */
}
