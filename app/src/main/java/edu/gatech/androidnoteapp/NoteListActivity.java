package edu.gatech.androidnoteapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * The main activity that shows the list of notes.
 * It is created on app open and stays until app close.
 *
 */
public class NoteListActivity extends Activity {

    /**
     * Called when activity opens.
     * Creates the model and populates the note list.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
    }

}
