package edu.gatech.androidnoteapp;

import android.content.Context;

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

    //TODO: add database variables

    /**
     * Standard constructor for the SQLiteModel, initializes the dbHelper variable so we can access the correct database
     * @param context Android context (usually an activity)
     */
    public NoteSQLiteDBModel(Context context) {
        //TODO: open database
    }

    /**
     * Sets the adapter to be notified when the data changes
     * @param adapter The adapter to notify
     */
    public void setAdapter(NoteListAdapter adapter) {
        this.adapter = adapter;
    }

    //TODO: add open function

    /**
     * Refreshes the (ArrayList<Note> notes) from the database
     */
    public void refreshNotes() {
        notes.clear();

        //TODO: pull from database instead of placeholders
        notes.add(new Note(0, "Note 1", "Note text 1", new java.sql.Date(0), "#FFFFFF"));
        notes.add(new Note(0, "Note 2", "Note text 2", new java.sql.Date(0), "#FF6666"));
        notes.add(new Note(0, "Note 3", "Note text 3", new java.sql.Date(0), "#4DDB4D"));
        notes.add(new Note(0, "Note 4", "Note text 4", new java.sql.Date(0), "#19A3FF"));
        notes.add(new Note(0, "Note 5", "Note text 5", new java.sql.Date(0), "#FFFACD"));

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

    //TODO: add restart db function

    //TODO: add cursor to note function

    /**
     * Adds a note to the database and calls refreshNotes() to fix the ArrayList appropriately
     * @param note The note to create.
     */
    public void createNote(Note note) {
        //TODO: add a new note to database

        refreshNotes();
    }

    /**
     * Saves a note's data in the database, then calls refreshNotes() to rebuild the ArrayList appropriately
     * @param note The note to save.
     */
    public void saveNote(Note note) {
        //TODO: update note in database

        refreshNotes();
    }

    /**
     * Deletes a note based on it's ID inside the database
     * @param note The note to delete.
     */
    public void deleteNote(Note note) {
        //TODO: delete note from database

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
