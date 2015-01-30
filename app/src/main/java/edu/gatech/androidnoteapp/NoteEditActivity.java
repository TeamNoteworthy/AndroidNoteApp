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
import android.widget.Toast;

import java.sql.Date;

/**
 * The activity that is used to edit notes.
 *
 */
public class NoteEditActivity extends ActionBarActivity {

    EditText bodyText, titleText;
    String[] colors = {"Red", "Green","Blue"};
    private NoteDataController controller = new NoteDataController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Activates the actionbar button at the top
        titleText = (EditText) findViewById(R.id.note_title);
        bodyText = (EditText) findViewById(R.id.note_text);
        controller.setModel(new NoteSQLiteDBModel(this));

    }

    /**
     * Executed when we want to back to the main class. Still incomplete, saving to the database needs to be implemented
     */
    private void saveAndFinish() {
        String title = titleText.getText().toString();
        String body = bodyText.getText().toString();
        Note newNote = new Note(0, title, body,  new Date(System.currentTimeMillis()), new Color() );
        //TODO: save note, via controller.saveNote(note);
        controller.saveNote(newNote);
        //need to get ref to controller here
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
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
//                    switch(which){
//                        case 0: // Red
//                        case 1: // Green
//                        case 2: // Blue
//
//                    }


                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if(id == R.id.action_deletenote) {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Are You Sure?");
            builder.setNegativeButton(R.string.menu_deletenote_no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the No Button (Does not delete note)
                }
            });
            builder.setPositiveButton(R.string.menu_deletenote_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the Yes Button (Deletes note)
                }
            });
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