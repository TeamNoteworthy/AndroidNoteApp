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
    private long noteID;

    /**
     * The title of the note.
     */
    private String titleText;

    /**
     * The body of the note.
     */
    private String bodyText;

    /**
     * The date when the note was last saved.
     */
    private Date lastEditDate;

    /**
     * The color the note should render with.
     */
    private String displayColor;

    public Note(long id, String title, String body, Date date, String color) {
        this.noteID = id;
        this.titleText = title;
        this.bodyText = body;
        this.lastEditDate = date;
        this.displayColor = color;
    }

    /**
     * Returns the ID of the note
     */
    public long getID() {
    	return noteID;
    }
    /**
     * Returns the Title of the note
     */
    public String getTitle() {
    	return titleText;
    }
    /**
     * Returns the Text of the note
     */
    public String getText() {
    	return bodyText;
    }
    /**
     * Returns the Date of the last edit to the note
     */
    public Date getDate() {
    	return lastEditDate;
    }
    /**
     * Returns the Color of the note
     */
    public String getColor() {
    	return displayColor;
    }
    
    /**
     * Sets the ID of the note
     */
    public void setID(int ID) {
    	noteID = ID;
    }
    /**
     * Sets the Title of the note
     */
    public void setTitle(String title) {
    	titleText = title;
    }
    /**
     * Sets the Text of the note
     */
    public void setText(String body) {
    	bodyText = body;
    }
    /**
     * Sets the Date of the last edit to the note
     */
    public void setDate(Date newDate) {
    	lastEditDate = newDate;
    }
    /**
     * Sets the Color of the note
     */
    public void setColor(String newColor) {
    	displayColor = newColor;
    }
    
}
