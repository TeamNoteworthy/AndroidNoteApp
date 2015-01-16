package edu.gatech.androidnoteapp;

import java.util.ArrayList;

/**
 * An interface to the note database for reading and writing.
 */
public interface NoteDBModel {

    /**
     * Loads all notes from the database into a list.
     *
     * @return A list of all notes from the database.
     */
    public ArrayList<Note> loadNotes();

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
