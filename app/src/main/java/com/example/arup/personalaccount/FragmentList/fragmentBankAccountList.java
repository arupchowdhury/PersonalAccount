package com.example.arup.personalaccount.FragmentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.arup.personalaccount.CustomListView.BankAccListAdapter;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentBankAccountList extends Fragment {


    ArrayList<BankAccInformation> dataModels;
    //BankInfoHelper  bankInfoHelper;
    ListView listView;
    Button btnAddNewBankAcc;
    private static BankAccListAdapter adapter;
    public fragmentBankAccountList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_bank_account_list, container, false);

        listView=(ListView)view.findViewById(R.id.lvBankAccList);
        dataModels= new ArrayList<BankAccInformation>();
        dataModels.add(new BankAccInformation(1,1,"1236547","Mothijhil","Janata Bank"));
        dataModels.add(new BankAccInformation(2,1,"1236545","Gulshan","Janata Bank"));
        dataModels.add(new BankAccInformation(3,1,"1236565","Middle Badda","Janata Bank"));

        adapter = new BankAccListAdapter(getActivity(),R.layout.bankacclistview,dataModels);
        listView.setAdapter(adapter);
        return view;
    }

}
