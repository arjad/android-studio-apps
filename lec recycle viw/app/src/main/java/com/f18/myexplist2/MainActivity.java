package com.f18.myexplist2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView elv = (ExpandableListView) findViewById(R.id.e1);
        HashMap<String, List<String>> eld = ListData.getData();
        List<String> listTitle = new ArrayList<String>(eld.keySet());
        CustomExpandableList cel = new CustomExpandableList(this,listTitle, eld);

        elv.setAdapter(cel);
    }
}