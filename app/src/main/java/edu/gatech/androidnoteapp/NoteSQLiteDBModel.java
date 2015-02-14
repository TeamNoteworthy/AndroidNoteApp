package edu.gatech.androidnoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * An implementation of a notes database that uses SQLite for storage.
 */
public class NoteSQLiteDBModel implements NoteDBModel {

    /**
     * List of notes, mirrored from database data.
     */
    private ArrayList<Note> notes = new ArrayList<Note>();

    /**
     * The adapter that should update
     */
    private NoteListAdapter adapter;

    /**
     * The actual SQLite database being changed
     */
    private SQLiteDatabase database;

    /**
     * Helper class for initializing and clearing the database when necessary
     */
    private NoteSQLiteHelper dbHelper;

    /**
     * String array of all column names in the database for use later
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
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.w("SQLiteDBError", "Failed to find writable database, check system storage capacity");
        }
    }

    /**
     * Sets the adapter to be notified when the data changes
     * @param adapter The adapter to notify
     */
    public void setAdapter(NoteListAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Refreshes the (ArrayList<Note> notes) from the database
     */
    public void refreshNotes() {

        notes.clear(); //clear the notes array so that there aren't any bugs later with the garbage collector

        Cursor cursor = database.query(NoteSQLiteHelper.TABLE_NOTES, allColumns, null, null, null, null, null); //query the database to grab all notes

        if(cursor == null) {
            Log.w("SQLiteDBError", "SQLite cursor returning null, something broke");
            return;
        }

        cursor.moveToFirst(); //set the cursor to look at the very first row in the return query
        while(!cursor.isAfterLast()) {
            Note temp = cursorToNote(cursor);
            this.notes.add(temp);
            cursor.moveToNext();
        }

        cursor.close(); //not using the cursor any more, so don't need it

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Standard getter for the notes ArrayList
     * @return notes ArrayList
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * Restart the database if necessary
     */
    public void restartDB() {
        dbHelper.onUpgrade(database, 1, 2);
    }

    /**
     * Private helper function to convert an Android SQLite Cursor into an actual Java Note Object
     * @param cursor SQLite cursor pointed at a database
     * @return temp, the note object generated by the SQLite cursor
     */
    private Note cursorToNote(Cursor cursor) {
        java.sql.Date noteDate;
        try {
            noteDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(3)).getTime());
        } catch (ParseException e) {
            noteDate = new java.sql.Date(0);
        }

        Note temp = new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2), noteDate, cursor.getString(4));
        return temp;
    }

    /**
     * Adds a note to the database and calls refreshNotes() to fix the ArrayList appropriately
     * @param note The note to create.
     */
    public void createNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteSQLiteHelper.COLUMN_TITLE, note.getTitle());
        values.put(NoteSQLiteHelper.COLUMN_TEXT, note.getText());
        values.put(NoteSQLiteHelper.COLUMN_DATE, note.getDate().toString());
        values.put(NoteSQLiteHelper.COLUMN_COLOR, note.getColor());

        long insertID = database.insert(NoteSQLiteHelper.TABLE_NOTES, null, values);

        Cursor cursor = database.query(NoteSQLiteHelper.TABLE_NOTES, allColumns, NoteSQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);
        if(cursor == null) {
            Log.w("SQLiteDatabaseError", "Null cursor, something broke");
            return;
        }
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
        values.put(NoteSQLiteHelper.COLUMN_COLOR, note.getColor());

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

    public Note getNoteByID(long id) {
        for (Note note : notes) {
            if (note.getID() == id) {
                return note;
            }
        }

        return null;
    }
}
