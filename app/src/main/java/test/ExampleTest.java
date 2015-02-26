package test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.sql.Date;
import java.util.ArrayList;

import edu.gatech.androidnoteapp.*;

/**
 * Created by Ses on 2/25/2015.
 */
public class ExampleTest extends AndroidTestCase {

    Note expected = new Note(1, "test", "test_body", new Date(0), "#FFFFFF");
    ArrayList notesExpected = new ArrayList<Note>();

    RenamingDelegatingContext context;
    NoteSQLiteDBModel dbmodel;

    public int compareNotesArrays(ArrayList<Note> a, ArrayList<Note> b) {


        if(a.size() != b.size()) {
            return -7;
        }

        for(int i=0; i<a.size(); i++) {
            Note tempA = a.get(i);
            Note tempB = b.get(i);

            if(!tempA.getColor().equals(tempB.getColor())) return -1; //Could test getColor, but no real need
            //if(tempA.getDate() != tempB.getDate()) return -2;   //This is created upon note creation, hard to recreate, but could be done in theory
            if(tempA.getID() != tempB.getID()) return -3;
            if(!tempA.getText().equals(tempB.getText())) return -4;
            if(!tempA.getTitle().equals(tempB.getTitle())) return -5;
        }

        return 0;

    }

    public void setUp() {

        notesExpected.add(expected);

        context = new RenamingDelegatingContext(getContext(), "test_");
        dbmodel = new NoteSQLiteDBModel(context);
        dbmodel.setAdapter(null); //prevents it from firing DataSetChanged() stuff
        dbmodel.restartDB(); //ensures a new database every time for testing (for id basically)


    }

    public void test() throws Exception {

        dbmodel.createNote(expected); //put expected note into database, refresh the notes ArrayList
        ArrayList<Note> notesReality = dbmodel.getNotes();

        assertEquals(0, compareNotesArrays(notesExpected, notesReality));

    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

}
