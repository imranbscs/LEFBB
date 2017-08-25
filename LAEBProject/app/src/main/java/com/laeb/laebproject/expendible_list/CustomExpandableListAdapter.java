package com.laeb.laebproject.expendible_list;

import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.R;
import com.laeb.laebproject.testjson.TestStaticMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    public HashMap<String, List<String>> expandableListDetail;
    public static List<String> sunday = TestStaticMethod.getSunday();
    public static List<String> saturdady = TestStaticMethod.getSaturday();
    public static List<String> monday = TestStaticMethod.getMonday();
    public static List<String> tuesday = TestStaticMethod.getTuesday();
    public static List<String> wednesday = TestStaticMethod.getWednesDay();
    public static List<String> thursday = TestStaticMethod.getThursday();
    public static List<String> friday = TestStaticMethod.getFriday();

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(childPosition<getChildrenCount(groupPosition)-1)
        {
            final String expandedListText = (String) getChild(groupPosition, childPosition);
            convertView = layoutInflater.inflate(R.layout.list_item_laeb, null);
            String string = expandedListText;
            String[] parts = string.split("-");
            String part1 = parts[0];
            String part2 = parts[1];
            String part3 = parts[2];

            TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
            TextView rate = (TextView) convertView.findViewById(R.id.rate);
            expandedListTextView.setText("From "+part1+"  Till "+part2);
            rate.setText("Rate "+part3);
        }

        if(childPosition == getChildrenCount(groupPosition)-1)
        {
            final int a = groupPosition;
            convertView = layoutInflater.inflate(R.layout.child_footer,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String temp = "1600-1800-25";
                    expandableListDetail.get(expandableListTitle.get(a)).add(temp);
                    notifyDataSetChanged();
                    //Toast.makeText(context, expandableListTitle.get(a), Toast.LENGTH_SHORT).show();
                    Log.v("ggg", "  " + a + " ==== " +expandableListDetail.get(expandableListTitle.get(a)));

                    if(a == 0){
                        saturdady = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 1){
                        sunday = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 2){
                        thursday = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 3){
                        tuesday = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 4){
                        friday = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 5){
                        wednesday = expandableListDetail.get(expandableListTitle.get(a));
                    }else if(a == 6){
                        monday = expandableListDetail.get(expandableListTitle.get(a));
                    }
                }
            });
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size()+1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_expendible_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivGroupIndicator);
        imageView.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void addDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_new_slot);
        //dialog.setTitle(R.string.pickup);


        dialog.show();
        TextView okBtn = (TextView) dialog.findViewById(R.id.cencel);
        TextView cencelBtn = (TextView) dialog.findViewById(R.id.done);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawRoute();
                dialog.dismiss();
            }
        });

        cencelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }
    public void setNewItems(List<String> listDataHeader,HashMap<String, List<String>> listChildData) {
        this.expandableListTitle = listDataHeader;
        this.expandableListDetail = listChildData;
        notifyDataSetChanged();
    }
}