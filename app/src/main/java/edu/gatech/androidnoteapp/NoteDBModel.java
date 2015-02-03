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
     * Saves a new note to the database.
     *
     * @param note The note to create.
     */
    public void createNote(Note note);

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

    /**
     * Tries to find a note with the given id.
     *
     * @param id The id to look up.
     * @return The note with the given id, else null
     */
    public Note getNoteByID(long id);
}
