package com.laeb.laebproject.expendible_list;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.laeb.laebproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final String expandedListText = (String) getChild(groupPosition, childPosition);
//
//        if (convertView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.list_item_laeb, null);
//        }
//
//        String string = expandedListText;
//        String[] parts = string.split("-");
//        String part1 = parts[0];
//        String part2 = parts[1];
//        String part3 = parts[2];
//
//        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
//        TextView rate = (TextView) convertView.findViewById(R.id.rate);
//        expandedListTextView.setText("From "+part1+"  Till "+part2);
//        rate.setText("Rate "+part3);


        //        final String expandedListText = (String) getChild(groupPosition, childPosition);
        LayoutInflater layoutInflater;
//        if (convertView == null) {
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        }

//        if(childPosition == 0)
//        {
//            convertView = layoutInflater.inflate(R.layout.child_footer, null);
//        }

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
            convertView = layoutInflater.inflate(R.layout.child_footer,null);
            //TextView txtFooter = (TextView)convertView.findViewById(R.id.txtFooter);
            //txtFooter.setText(currentParent.textToFooter);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addDialog();
                    Toast.makeText(context, "child", Toast.LENGTH_SHORT).show();
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
}