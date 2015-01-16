package edu.gatech.androidnoteapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Note getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        RowViewElements viewElements;
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.note_row_layout, parent, false);
            viewElements = new RowViewElements();
            viewElements.title = (TextView)view.findViewById(R.id.note_title);
            viewElements.date = (TextView)view.findViewById(R.id.note_date);
            view.setTag(viewElements);
        } else {
            view = convertView;
            viewElements = (RowViewElements)view.getTag();
        }

        Note note = getItem(position);
        viewElements.title.setText(note.titleText);
        viewElements.date.setText("<real date here>");

        return view;
    }

    private class RowViewElements {
        public TextView title, date;
    }
}
