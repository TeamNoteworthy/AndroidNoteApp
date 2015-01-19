package edu.gatech.androidnoteapp;

import java.util.ArrayList;

/**
 * An interface to the note database for reading and writing.
 */
public interface NoteDBModel {

    /**
     * Refreshes the notes ArrayList for later use with getNotes()
     */
    public void refreshNotes();


    /**
     * @return ArrayList of Notes from the Database
     */
    public ArrayList<Note> getNotes();

    /**
     * Saves a note to the database.
     * Will overwrite notes with the same ID.
     *
     * @param note The note to save.
     */
    public void saveNote(Note note);

    /**
     * Deletes a note from the database.
     *
     * @param note The note to delete.
     */
    public void deleteNote(Note note);
}
