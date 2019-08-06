package com.example.yahyagungorur.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by YAHYA on 7/4/2019.
 */

public class fragmentB extends Fragment {
    Communicator com;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView)getActivity().findViewById(R.id.listview);
    }

    public void ChangeText(final String searchTerm, final Context context){
        WebScraping webScrapings = new WebScraping(lv,context,searchTerm);
        webScrapings.execute();
    }
}
