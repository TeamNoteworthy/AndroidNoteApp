package edu.gatech.androidnoteapp;

import android.graphics.Color;

import java.sql.Date;

/**
 * The authoritative representation of a note in memory.
 */
public class Note {

    /**
     * A unique id for this note.
     */
    public int noteID;

    /**
     * The title of the note.
     */
    public String titleText;

    /**
     * The body of the note.
     */
    public String bodyText;

    /**
     * The date when the note was last saved.
     */
    public Date lastEditDate;

    /**
     * The color the note should render with.
     */
    public Color displayColor;
}
