package com.damie.layoutproject;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by damie on 05/10/2017.
 */

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(@NonNull Context context, @NonNull List<Student> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.list_row,null);

        TextView lastNameTextView=
                (TextView) row.findViewById(R.id.lastName);
        TextView firstNameTextView=
                (TextView) row.findViewById(R.id.firstName);

        Student currentStudent=getItem(position);

        lastNameTextView.setText(currentStudent.getName());
        firstNameTextView.setText(currentStudent.getFirstName());

        return row;

    }
}
