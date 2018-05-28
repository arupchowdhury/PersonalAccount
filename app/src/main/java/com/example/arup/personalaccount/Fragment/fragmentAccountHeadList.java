package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.arup.personalaccount.CustomListView.AccountHeadListAdapter;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAccountHeadList extends Fragment implements View.OnClickListener {

    ArrayList<IncomeExpenseHead> dataModels;
    ListView listView;
    Button btnAddNew;
    private static AccountHeadListAdapter adapter;
    public fragmentAccountHeadList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_account_head_list, container, false);

        btnAddNew = (Button)view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(this);

        listView=(ListView)view.findViewById(R.id.lvAccountHead);
        dataModels= new ArrayList<>();

        dataModels.add(new IncomeExpenseHead(1,"Big Fish","Expense"));
        dataModels.add(new IncomeExpenseHead(2,"Black bengal (Mutton)","Expense"));
        dataModels.add(new IncomeExpenseHead(3,"Soyabin oil","Expense"));
        dataModels.add(new IncomeExpenseHead(4,"Nazir shai rice","Expense"));
        dataModels.add(new IncomeExpenseHead(5,"Vegitables","Expense"));
        dataModels.add(new IncomeExpenseHead(6,"Bashmoti rice","Expense"));
        dataModels.add(new IncomeExpenseHead(7,"Mug dal","Expense"));

        adapter = new AccountHeadListAdapter(getActivity(),R.layout.accountheadlistview,dataModels);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        try{
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentAccountHead());
            fragmentTransaction.commit();
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
}
