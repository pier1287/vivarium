package it.pjlabs.growroom.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.pjlabs.growroom.R;

public class MainContentFragment extends Fragment {

    public MainContentFragment (){}

    public static MainContentFragment newInstance(){
        return new MainContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_content, container, false);
    }
}