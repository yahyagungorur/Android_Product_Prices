package com.example.yahyagungorur.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void Respond(String data) {
        FragmentManager manager = getFragmentManager();
        fragmentB f2 = (fragmentB) manager.findFragmentById(R.id.fragmentB);
        f2.ChangeText(data,context);
    }
}
