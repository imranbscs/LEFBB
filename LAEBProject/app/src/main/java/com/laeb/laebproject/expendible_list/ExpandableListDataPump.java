package com.laeb.laebproject.expendible_list;

import com.laeb.laebproject.testjson.ModelTest1;
import com.laeb.laebproject.testjson.TestStaticMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = TestStaticMethod.getSunday();
//        cricket.add("India");
//        cricket.add("Pakistan");
//        cricket.add("Australia");

        List<String> sunday = TestStaticMethod.getSunday();

        List<String> monday = TestStaticMethod.getMonday();

        List<String> tuesday = TestStaticMethod.getTuesday();

        List<String> wednesday = TestStaticMethod.getWednesDay();

        List<String> thursday = TestStaticMethod.getThursday();

        List<String> friday = TestStaticMethod.getFriday();

        List<String> saturday = TestStaticMethod.getSaturday();

        List<String> basketball = new ArrayList<String>();
        basketball.add("Sunday");
        basketball.add("Monday");
        basketball.add("Tuesday");
        basketball.add("Wednesday");
        basketball.add("Thursday");
        basketball.add("Friday");
        basketball.add("Saturday");


        expandableListDetail.put("Sunday", sunday);
        expandableListDetail.put("Monday", monday);
        expandableListDetail.put("Tuesday", tuesday);
        expandableListDetail.put("Wednesday", wednesday);
        expandableListDetail.put("Thursday", thursday);
        expandableListDetail.put("Friday", friday);
        expandableListDetail.put("Saturday", saturday);

        return expandableListDetail;
    }
}
