package com.damie.layoutproject;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {
    String days[]=new String[]{"MON","TUE","WED","THU","FRI","SAT","SUN","MON","TUE","WED","THU","FRI","SAT","SUN","MON","TUE","WED","THU","FRI","SAT","SUN"};
    List<Student> students= Arrays.asList(
            new Student("Damien","Chaudois1"),
            new Student("Damien","Chaudois2"),
            new Student("Damien","Chaudois3"),
            new Student("Damien","Chaudois4"),
            new Student("Damien","Chaudois5"),
            new Student("Damien","Chaudois6"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> dayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,days);
//        setListAdapter(dayAdapter);
//        ArrayAdapter<Student> studentAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                students);
        StudentAdapter studentAdapter=
                new StudentAdapter(this,students);
        setListAdapter(studentAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String day=getListAdapter().getItem(position).toString();
        Toast.makeText(this,day,Toast.LENGTH_SHORT).show();
    }
}
