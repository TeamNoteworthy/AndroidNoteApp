package edu.gatech.androidnoteapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * The main activity that shows the list of notes.
 * It is created on app open and stays until app close.
 *
 */
public class NoteListActivity extends ListActivity {

    //TODO: add static activity ids

    private static final int DELETE_ID = Menu.FIRST;

    /**
     * The controller that manages note data.
     */
    public static NoteDBModel dbModel;
    public NoteListAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        dbModel = new NoteSQLiteDBModel(this);

        dbModel.refreshNotes();

        adapter = new NoteListAdapter(this, dbModel.getNotes());
        dbModel.setAdapter(adapter);
        setListAdapter(adapter);

        registerForContextMenu(getListView());
    }

    /**
     * Creates the menu for the action bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    /**
     * Called when a menu item from the action bar is clicked.
     * Creates a note if add note is clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addnote) {
            createNote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Creates a context menu when user long-presses a list item.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_deletenote);
    }

    /**
     * Called when a context item is clicked.
     * Deletes the note if delete is clicked.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                dbModel.deleteNote(dbModel.getNoteByID(info.id));
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Called when a new note is created.
     * Starts the edit activity.
     */
    private void createNote() {
        //TODO: spawn note edit activity
    }
	
    /**
     * Called when a note in the list is clicked.
     * Starts the edit activity, passing the note to edit.
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //TODO: spawn note edit activity passing note id
    }

    /**
     * Called when this activity is returned to from edit activity.
     * Refreshes the list with the new note data.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        dbModel.refreshNotes();
    }
}
