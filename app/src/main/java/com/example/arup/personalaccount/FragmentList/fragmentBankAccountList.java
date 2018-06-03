package com.example.arup.personalaccount.FragmentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.arup.personalaccount.CustomListView.BankAccListAdapter;
import com.example.arup.personalaccount.DBHelper.BankAccInfoHelper;
import com.example.arup.personalaccount.Fragment.fragmentBankAcc;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentBankAccountList extends Fragment implements View.OnClickListener,SearchView.OnQueryTextListener {



    //BankInfoHelper  bankInfoHelper;
    ListView listView;
    Button btnAddNewBankAcc;
    private static BankAccListAdapter adapter;
    BankAccInfoHelper bankAccInfoHelper;

    private SearchView mSearchView;
    android.widget.Filter filter;

    public fragmentBankAccountList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_bank_account_list, container, false);

        listView=(ListView)view.findViewById(R.id.lvBankAccList);
        btnAddNewBankAcc = view.findViewById(R.id.btnAddNewBankAcc);
        bankAccInfoHelper = new BankAccInfoHelper(getContext());
        //dataModels= new ArrayList<BankAccInformation>();

        ArrayList<BankAccInformation> dataModels=bankAccInfoHelper.getBakAccList();
        adapter = new BankAccListAdapter(getActivity(),R.layout.bankacclistview,dataModels);
        listView.setAdapter(adapter);

        btnAddNewBankAcc.setOnClickListener(this);

        //listView.setAdapter(adapter);

        //listView.setTextFilterEnabled(false);
        //listView.setAdapter(adapter);
        //filter = adapter.getFilter();

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==btnAddNewBankAcc){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentBankAcc());
            fragmentTransaction.commit();
        }
    }


    private void searchView(){
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //ctlist.clearTextFilter();
            filter.filter("");

        } else {
            //ctlist.setFilterText(newText);
            filter.filter(newText);
        }
        return true;
    }
}
