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
    public void setModel(NoteDBModel newDBModel)
    {
        dbModel = newDBModel;
        //shuld reload too
    }

    public ArrayList<Note> loadNotes() {
        dbModel.refreshNotes();
        return dbModel.getNotes();
    }

    public void saveNote(Note note) {
        dbModel.saveNote(note);
    }

    public void deleteNote(Note note) {
        dbModel.deleteNote(note);
    }
}
