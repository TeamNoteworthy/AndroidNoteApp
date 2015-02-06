package edu.gatech.androidnoteapp;

import java.util.ArrayList;

/**
 * Manages the note data and logic.
 */
public class NoteDataController {

    /**
     * The database model that is being used to read and write notes.
     */
    private NoteDBModel dbModel;

    /**
     * Sets the database model.
     *
     * @param newDBModel The new database model.
     */
    public void setModel(NoteDBModel newDBModel) {
        dbModel = newDBModel;
    }

    /**
     * Gets the list of notes.
     *
     * @return The list of notes
     */
    public ArrayList<Note> getNotes() {
        return dbModel.getNotes();
    }

    /**
     * Refreshes th list of notes from the database.
     */
    public void refreshNotes() {
        dbModel.refreshNotes();
    }

    /**
     * Creates a new note in the database.
     */
    public void createNote(Note note) {
        dbModel.createNote(note);
    }

    /**
     * Updates an existing note in the database.
     */
    public void saveNote(Note note) {
        dbModel.saveNote(note);
    }

    /**
     * Deletes a note from the database
     */
    public void deleteNote(Note note) {
        dbModel.deleteNote(note);
    }

    /**
     * Gets a note via its id.
     */
    public Note getNoteByID(long id) {
        return dbModel.getNoteByID(id);
    }
}
