package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arup.personalaccount.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAccountHead extends Fragment {


    public fragmentAccountHead() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_account_head, container, false);
    }

}
