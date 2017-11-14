package com.example.soltani.scrumin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Soltani on 13/11/2017.
 */

public class ProjectAdapter extends BaseAdapter {
    ArrayList<ListProject> arrayList=new ArrayList<ListProject>();

    public ProjectAdapter(ArrayList<ListProject> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position).getApp_name();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_project, parent,false);
        TextView nom=(TextView)view1.findViewById(R.id.Appname);


        nom.setText(arrayList.get(position).getApp_name());





        return view1;
    }
}

