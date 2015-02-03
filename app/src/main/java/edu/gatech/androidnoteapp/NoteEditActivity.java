package edu.gatech.androidnoteapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.Date;

/**
 * The activity that is used to edit notes.
 *
 */
public class NoteEditActivity extends ActionBarActivity {

    public static final String KEY_NOTEEXISTS = "noteexists";
    public static final String KEY_NOTEID = "noteid";
    public RelativeLayout EditLayout = null;

    EditText bodyText, titleText;
    String[] colors = {"Red", "Green","Blue"};
    private NoteDataController controller = NoteListActivity.controller;
    private boolean noteExists;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Activates the actionbar button at the top
        titleText = (EditText) findViewById(R.id.note_title);
        bodyText = (EditText) findViewById(R.id.note_text);
        EditLayout = (RelativeLayout)findViewById(R.id.EditLayout);

        Bundle extras = getIntent().getExtras();
        noteExists = extras.getBoolean(NoteEditActivity.KEY_NOTEEXISTS);
        if (noteExists) {
            note = controller.getNoteByID(extras.getLong(NoteEditActivity.KEY_NOTEID));

            if (note != null) {
                titleText.setText(note.getTitle());
                bodyText.setText(note.getText());
            }
        }
    }

    /**
     * Executed when we want to back to the main class. Still incomplete, saving to the database needs to be implemented
     */
    private void saveAndFinish() {
        String title = titleText.getText().toString();
        String body = bodyText.getText().toString();
        if(title.length() == 0 && body.length()==0){
            controller.deleteNote(note);
        }
        if (noteExists && note != null) {
            note.setTitle(title);
            note.setText(body);
            note.setDate(new Date(System.currentTimeMillis()));
            controller.saveNote(note);
        }
        else {
            Note newNote = new Note(0, title, body, new Date(System.currentTimeMillis()), Color.WHITE);
            controller.createNote(newNote);
        }

        setResult(RESULT_OK);
        finish();
    }

    /**
     * Determines which item was selected
     *
     * @param item the item which was selected
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder;
        int id = item.getItemId();
        if (id == android.R.id.home) { //Go back to the main page when the ActionBar button is tapped
            saveAndFinish();
        }
        if(id == R.id.action_notecolor){
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a color");
            builder.setItems(colors, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    switch(which){
                        case 0: // Red
                            //note.setColor(Color.RED);
                            EditLayout.setBackgroundColor(Color.RED);
                            break;
                        case 1: // Green
                            //note.setColor(Color.GREEN);
                            EditLayout.setBackgroundColor(Color.GREEN);
                            break;
                        case 2: // Blue
                            //note.setColor(Color.BLUE);
                            EditLayout.setBackgroundColor(Color.BLUE);
                            break;

                    }


                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if(id == R.id.action_deletenote) {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Are You Sure?");
            builder.setNegativeButton(R.string.menu_deletenote_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the Yes Button (Deletes Note)
                    // The note is not saved
                    if(noteExists && note != null){
                        controller.deleteNote(note);
                    }
                    setResult(RESULT_OK);
                    finish();
                }
            });
            builder.setPositiveButton(R.string.menu_deletenote_no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the No Button
                    // Nothing happen and returns to edit screen

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);
        return true;
    }
    /**
     * Go back when the android back button is pressed
     */
    public void onBackPressed() {
        saveAndFinish();
    }
}