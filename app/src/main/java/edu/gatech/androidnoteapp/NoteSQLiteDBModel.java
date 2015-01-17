package edu.gatech.androidnoteapp;

import java.util.ArrayList;

/**
 * An implementation of a notes database that uses SQLite for storage.
 */
public class NoteSQLiteDBModel implements NoteDBModel {

    public ArrayList<Note> loadNotes() {
        Note t1 = new Note();
        t1.setTitle("abc");
        Note t2 = new Note();
        t2.setTitle("def");
        Note t3 = new Note();
        t3.setTitle("123");
        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(t1);
        noteList.add(t2);
        noteList.add(t3);
        return noteList;
    }

    public void saveNote(Note note) {

    }

    public void deleteNote(Note note) {

    }
}
