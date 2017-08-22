package com.laeb.laebproject.expendible_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");

        expandableListDetail.put("Sunday", cricket);
        expandableListDetail.put("Monday", football);
        expandableListDetail.put("Tuesday", basketball);
        expandableListDetail.put("Wednesday", cricket);
        expandableListDetail.put("Thursday", football);
        expandableListDetail.put("Friday", basketball);
        expandableListDetail.put("Saturday", basketball);

        return expandableListDetail;
    }
}
