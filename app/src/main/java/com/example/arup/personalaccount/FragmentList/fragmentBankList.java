package com.example.arup.personalaccount.FragmentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.arup.personalaccount.CustomListView.BankListAdapter;
import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.Fragment.fragmentAccountHead;
import com.example.arup.personalaccount.Fragment.fragmentBankInfo;
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentBankList extends Fragment implements View.OnClickListener {

    ArrayList<BankInformation> dataModels;
    BankInfoHelper bankInfoHelper;
    ListView listView;
    Button btnAddNewBank;
    private static BankListAdapter adapter;

    private SearchView mSearchView;
    android.widget.Filter filter;

    public fragmentBankList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_bank_list, container, false);
        btnAddNewBank = (Button)view.findViewById(R.id.btnAddNewBank);

        listView=(ListView)view.findViewById(R.id.lvBankList);
        bankInfoHelper = new BankInfoHelper(getActivity());
        dataModels= new ArrayList<BankInformation>();
        dataModels = bankInfoHelper.getBankList();

//        dataModels.add(new BankInformation(1,"Janata Bank"));
//        dataModels.add(new BankInformation(2,"Agroni Bank"));
//        dataModels.add(new BankInformation(3,"Sonali Bank"));
//        dataModels.add(new BankInformation(4,"Krishi Bank"));
//        dataModels.add(new BankInformation(5,"Rupali Bank"));

        adapter = new BankListAdapter(getActivity(),R.layout.bankinfolistview,dataModels);
        listView.setAdapter(adapter);

        btnAddNewBank.setOnClickListener(this);

        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(false);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        try{
            if(v==btnAddNewBank){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer,new fragmentBankInfo());
                fragmentTransaction.commit();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
