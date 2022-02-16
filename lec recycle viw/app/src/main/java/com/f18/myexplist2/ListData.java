package com.f18.myexplist2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListData
{
    public  static HashMap<String, List<String>> getData()
    {
        //store key value pair
        HashMap<String, List<String>> detailedData = new HashMap<String, List<String>>();

        //make resizeable array
        List<String> cricket = new ArrayList<String>();

        //adding in array list
        cricket.add("Pakistan");
        cricket.add("India");
        cricket.add("South Africa");
        cricket.add("England");
        cricket.add("Australia");

        //put arrAY list in hashmap
        detailedData.put("Cricket Team",cricket);

        return detailedData;
    }
}
