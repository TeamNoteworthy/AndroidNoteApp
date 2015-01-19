package edu.gatech.androidnoteapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * An implementation of a notes database that uses SQLite for storage.
 */
public class NoteSQLiteDBModel implements NoteDBModel {

    private static ArrayList<Note> notes = new ArrayList<Note>();
    private SQLiteDatabase database;
    private NoteSQLiteHelper dbHelper;

    /**
     * Standard constructor for the SQLiteModel, initializes the dbHelper variable so we can access the correct database
     * @param context Android context (usually an activity)
     */
    public NoteSQLiteDBModel(Context context) {
        dbHelper = new NoteSQLiteHelper(context);
    }

    /**
     * Opens a SQL database for read/write
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Refreshes the (ArrayList<Note> notes) from the database
     */
    public void refreshNotes() {

    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void saveNote(Note note) {

    }

    public void deleteNote(Note note) {

    }
}
