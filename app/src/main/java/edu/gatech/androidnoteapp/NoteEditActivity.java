package edu.gatech.androidnoteapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The activity that is used to edit notes.
 *
 */
public class NoteEditActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Activates the actionbar button at the top
    }

    /**
     * Executed when we want to back to the main class. Still incomplete, saving to the database needs to be implemented
     */
    private void saveAndFinish() {
        setResult(RESULT_OK);
        finish();
    }

    /**
     * Determines which item was selected
     * @param item the item which was selected
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){ //Go back to the main page when the ActionBar button is tapped
            saveAndFinish();
        }
        return false;
    }

    /**
     * Go back when the android back button is pressed
     */
    public void onBackPressed(){
        saveAndFinish();
    }
}
