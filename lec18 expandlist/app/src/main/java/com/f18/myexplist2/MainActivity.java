package com.f18.myexplist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView elv = (ExpandableListView) findViewById(R.id.e1);
        HashMap<String, List<String>> eld = ListData.getData();
        List<String> listTitle = new ArrayList<String>(eld.keySet());
        CustomExpandableList cel = new CustomExpandableList(this,listTitle, eld);
        elv.setAdapter(cel);
    }
}

class ListData
{
    public  static HashMap<String, List<String>> getData()
    {
        HashMap<String, List<String>> detailedData = new HashMap<String, List<String>>();
        List<String> cricket = new ArrayList<String>();
        cricket.add("Pakistan");
        cricket.add("India");
        cricket.add("South Africa");
        cricket.add("England");
        cricket.add("Australia");

        List<String> baseball = new ArrayList<String>();
        baseball.add("USA");
        baseball.add("India");
        baseball.add("South Africa");
        baseball.add("England");
        baseball.add("Australia");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("India");
        football.add("South Africa");
        football.add("England");
        football.add("Australia");

        detailedData.put("Cricket Team",cricket);
        detailedData.put("Football Team",football);
        detailedData.put("Baseball Team",baseball);

        return detailedData;

    }
}

class CustomExpandableList extends BaseExpandableListAdapter
{

    private Context context;
    private List<String> listTitle;
    private HashMap<String, List<String>> listDetail;

    public CustomExpandableList(Context context, List<String> listTitle, HashMap<String, List<String>> listDetail) {
        this.context = context;
        this.listTitle = listTitle;
        this.listDetail = listDetail;
    }

    @Override
    public int getGroupCount() {
        return this.listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        TextView tv1 = convertView.findViewById(R.id.lg1);
        String sGroup = String.valueOf(getGroup(groupPosition));
        tv1.setText(sGroup);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextColor(Color.BLUE);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        TextView tv2 = convertView.findViewById(R.id.li1);
        String sChild = String.valueOf(getChild(groupPosition,childPosition));
        tv2.setText(sChild);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(),sChild,Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
