package edu.gatech.androidnoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import java.util.ArrayList;
import java.sql.Date;

/**
 * An implementation of a notes database that uses SQLite for storage.
 */
public class NoteSQLiteDBModel implements NoteDBModel {

    /**
     * List of notes, mirrored from database data.
     */
    private ArrayList<Note> notes = new ArrayList<Note>();

    /**
     * The authoritative database of notes.
     */
    private SQLiteDatabase database;

    /**
     * Helper class to manage common database actions.
     */
    private NoteSQLiteHelper dbHelper;

    /**
     * Simple string array of all the database columns for ease-of-use later
     */
    private String[] allColumns = {
            NoteSQLiteHelper.COLUMN_ID,
            NoteSQLiteHelper.COLUMN_TITLE,
            NoteSQLiteHelper.COLUMN_TEXT,
            NoteSQLiteHelper.COLUMN_DATE,
            NoteSQLiteHelper.COLUMN_COLOR
    };

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

        notes.clear(); //remove all previous note objects from the ArrayList for now
        Cursor cursor = database.query(NoteSQLiteHelper.TABLE_NOTES, allColumns, null, null, null, null, null); //SQL Query for all rows of the database
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) { //go through all rows
            Note temp = cursorToNote(cursor);
            this.notes.add(temp);
            cursor.moveToNext();
        }

        cursor.close(); //not using the SQLite connection anymore, close it

    }

    /**
     * Standard getter for the notes ArrayList
     * @return notes ArrayList
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * Private helper function to convert an Android SQLite Cursor into an actual Java Note Object
     * @param cursor SQLite cursor pointed at a database
     * @return temp, the note object generated by the SQLite cursor
     */
    private Note cursorToNote(Cursor cursor) {

        Note temp = new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2), new Date(Long.parseLong(cursor.getString(3))), new Color());
        return temp;
    }

    /**
     * Adds a note to the database and calls refreshNotes() to fix the ArrayList appropriately
     * @param title note title
     * @param text note body text
     * @param date note date
     * @param color note color
     */
    public void createNote(String title, String text, Date date, Color color) {
        ContentValues values = new ContentValues();

        values.put(NoteSQLiteHelper.COLUMN_TITLE, title);
        values.put(NoteSQLiteHelper.COLUMN_TEXT, text);
        values.put(NoteSQLiteHelper.COLUMN_DATE, date.toString());
        values.put(NoteSQLiteHelper.COLUMN_COLOR, color.toString());

        long insertID = database.insert(NoteSQLiteHelper.TABLE_NOTES, null, values);

        Cursor cursor = database.query(NoteSQLiteHelper.TABLE_NOTES, allColumns, NoteSQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();

        Note temp = cursorToNote(cursor);
        cursor.close();
        refreshNotes();
    }

    /**
     * Saves a note's data in the database, then calls refreshNotes() to rebuild the ArrayList appropriately
     * @param note The note to save.
     */
    public void saveNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteSQLiteHelper.COLUMN_TITLE, note.getTitle());
        values.put(NoteSQLiteHelper.COLUMN_TEXT, note.getText());
        values.put(NoteSQLiteHelper.COLUMN_DATE, note.getDate().toString());
        values.put(NoteSQLiteHelper.COLUMN_COLOR, note.getColor().toString());

        database.update(NoteSQLiteHelper.TABLE_NOTES, values, NoteSQLiteHelper.COLUMN_ID + " = ?", new String[] {String.valueOf(note.getID())});

        refreshNotes();

    }

    /**
     * Deletes a note based on it's ID inside the database
     * @param note The note to delete.
     */
    public void deleteNote(Note note) {

        long id = note.getID();

        System.out.println("Item Deleted with ID = " + id); //console printout for debugging later
        database.delete(NoteSQLiteHelper.TABLE_NOTES, NoteSQLiteHelper.COLUMN_ID + " = " + id, null);

        refreshNotes();

    }
}
