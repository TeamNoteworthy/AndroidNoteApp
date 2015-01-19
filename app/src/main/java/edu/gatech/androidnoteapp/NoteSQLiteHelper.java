package edu.gatech.androidnoteapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

/**
 * Created by Ses on 1/19/2015.
 */
public class NoteSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NOTES =    "notes";
    public static final String COLUMN_ID =      "_id";
    public static final String COLUMN_TITLE =   "title";
    public static final String COLUMN_TEXT =    "text";
    public static final String COLUMN_DATE =    "date";
    public static final String COLUMN_COLOR =   "color";

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;


    /**
     * Database creation string, formatted weirdly in the editor so it's
     * easier to read for a human mostly
     *
     * Creates a table called notes inside of notes.db with row structure:
     * | _id | title | text | date | color |
     *
     * _id = primary key, just an auto-incrementing integer
     * title = title of the note
     * text = body content of the note
     * date = date note was last edited
     * color = for future use, potential color of note
     */
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NOTES
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_TEXT  + " TEXT NOT NULL, "
                + COLUMN_DATE  + " TEXT NOT NULL, "
                + COLUMN_COLOR + " TEXT NOT NULL)";

    public NoteSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLiteDBUpgrade", "Upgrading database");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }


}
