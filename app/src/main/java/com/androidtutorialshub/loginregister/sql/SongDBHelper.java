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
    private static final String DATABASE_NAME = "Songs.db";

    // User table name
    private static final String TABLE_SONG = "song";

    // User Table Columns names
    private static final String COLUMN_SONG_ID = "song_id";
    private static final String COLUMN_SONG_TITLE = "song_name";
    private static final String COLUMN_SONG_ARTIST = "song_artist";
    private static final String COLUMN_SONG_ALBUM = "song_album";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_SONG + "("
            + COLUMN_SONG_ID + " TEXT PRIMARY KEY," + COLUMN_SONG_TITLE + " TEXT,"
            + COLUMN_SONG_ARTIST + " TEXT," + COLUMN_SONG_ALBUM + " TEXT" + ")";

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
        values.put(COLUMN_SONG_ID, song.getId());
        values.put(COLUMN_SONG_TITLE, song.getName());
        values.put(COLUMN_SONG_ARTIST, song.getArtist());
        values.put(COLUMN_SONG_ALBUM, song.getAlbum());

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
                COLUMN_SONG_ARTIST,
                COLUMN_SONG_TITLE,
                COLUMN_SONG_ALBUM
        };
        // sorting orders
        String sortOrder =
                COLUMN_SONG_TITLE + " ASC";
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
                song.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)));
                song.setArtist(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_ARTIST)));
                song.setAlbum(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_ALBUM)));
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
     * This method to update song record
     *
     * @param song
     */
    public void updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, song.getName());
        values.put(COLUMN_SONG_ARTIST, song.getArtist());
        values.put(COLUMN_SONG_ALBUM, song.getAlbum());

        // updating row
        db.update(TABLE_SONG, values, COLUMN_SONG_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_SONG, COLUMN_SONG_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SONG_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_SONG_ARTIST + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
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
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SONG_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SONG_ARTIST + " = ?" + " AND " + COLUMN_SONG_ALBUM + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
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
}