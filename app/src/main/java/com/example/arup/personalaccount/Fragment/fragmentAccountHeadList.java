package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.arup.personalaccount.CustomListView.AccountHeadListAdapter;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAccountHeadList extends Fragment {

    ArrayList<IncomeExpenseHead> dataModels;
    ListView listView;
    private static AccountHeadListAdapter adapter;
    public fragmentAccountHeadList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_account_head_list, container, false);
        listView=(ListView)view.findViewById(R.id.lvAccountHead);
        dataModels= new ArrayList<>();

        dataModels.add(new IncomeExpenseHead(1,"Big Fish"));
        dataModels.add(new IncomeExpenseHead(2,"Black bengal (Mutton)"));
        dataModels.add(new IncomeExpenseHead(3,"Soyabin oil"));
        dataModels.add(new IncomeExpenseHead(4,"Nazir shai rice"));
        dataModels.add(new IncomeExpenseHead(4,"Vegitables"));

        adapter = new AccountHeadListAdapter(getContext(),R.layout.accountheadlistview,dataModels);
        listView.setAdapter(adapter);

        return view;
    }

}
