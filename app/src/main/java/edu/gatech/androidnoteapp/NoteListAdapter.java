package edu.gatech.androidnoteapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Date;
import java.util.List;

/**
 * An adapter for a list of notes.
 */
public class NoteListAdapter extends BaseAdapter {

    private List<Note> items;

    private Activity context;

    public NoteListAdapter(Activity context, List<Note> items) {
        super();

        this.context = context;
        this.items = items;
    }

    /**
     * @return The number of items in the list of notes
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * Get the item at the indicated position
     * @param position The desired position for the item to get
     * @return The item at the indicated position
     */
    @Override
    public Note getItem(int position) {
        return items.get(position);
    }

    /**
     * Get the ID of the item with the indicated position
     * @param position The desired position for the item whose ID you'll get
     * @return The ID of the desired item
     */
    @Override
    public long getItemId(int position) {
        return getItem(position).getID();
    }

    /**
     * Return a view object for the given position
     * @param position The desired position for the item to get
     * @param convertView The old view to reuse if possible
     * @param parent The parent for the view to which the returned View will be attached
     * @return The resulting View created from the desired item index
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        RowViewElements viewElements;
        if(convertView == null) { // if no existing view, populate all view fields
            // populate the view parent based on XML in note_row_layout
            view = LayoutInflater.from(context).inflate(R.layout.note_row_layout, parent, false);
            // hide data for title and date in RowViewElements as a tag
            viewElements = new RowViewElements();
            viewElements.title = (TextView)view.findViewById(R.id.note_title);
            viewElements.date = (TextView)view.findViewById(R.id.note_date);
            viewElements.layout = (LinearLayout)view.findViewById(R.id.full_row);
            view.setTag(viewElements);
        } else {
            // If there is an existing view,
            view = convertView;
            viewElements = (RowViewElements)view.getTag();
            // TODO do we need to verify that these are correct?
        }

        // update view based on Note at given position
        Note note = getItem(position);
        viewElements.setTitle(note.getTitle());
        viewElements.setDate(note.getDate());
        viewElements.setBackgroundColor(note.getColor());

        return view;
    }

    /**
     * For any given item in the Notes, we'll return a row whose data will be
     * stored in this class while we're creating it
     */
    private class RowViewElements {
        /**
         * The encompassing layout for the entire row
         */
        private LinearLayout layout;
        /**
         * The TextView for the title for the returned row for the given note
         */
        private TextView title;
        /**
         * The TextView for the last modified date for the returned row for the
         * given note
         */
        private TextView date;

        /**
         * @param color String color of the note
         */
        public void setBackgroundColor(String color){ this.layout.setBackgroundColor(Color.parseColor(color)); }

        /**
         * @return Title for this RowViewElement
         */
        public TextView getTitle() {
            return title;
        }

//        /**
//         * @param title Title for this RowViewElement
//         */
//        public void setTitle(TextView title) {
//            this.title = title;
//        }

        /**
         * @param title Title for this RowViewElement
         */
        public void setTitle(String title) {
            this.title.setText(title);
        }

        /**
         * @return Date for this RowViewElement
         */
        public TextView getDate() {
            return date;
        }

//        /**
//         * @param date Date for this RowViewElement
//         */
//        public void setDate(TextView date) {
//            this.date = date;
//        }

        /**
         * @param date Date for this RowViewElement
         */
        public void setDate(Date date) {
            this.date.setText(date.toString());
        }
    }
}
